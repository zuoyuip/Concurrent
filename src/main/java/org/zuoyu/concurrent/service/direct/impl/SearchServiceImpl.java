package org.zuoyu.concurrent.service.direct.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.SerializeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.aggregations.metrics.StatsAggregationBuilder;
import org.elasticsearch.xcontent.XContentBuilder;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;
import org.zuoyu.concurrent.utils.ConcurrencyUtil;
import org.zuoyu.concurrent.utils.ReqBuilderUtil;
import org.zuoyu.concurrent.utils.Results;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/13 18:42
 * @Version 1.0
 */
@Service
public class SearchServiceImpl implements SearchService {

	//	private static final String URL = "http://192.168.1.99:8080/foreign/international/ctrip_szjz/search";
	private static final String URL = "http://47.102.104.115:8087/international/ctrip_szjz/search";

	private static final String SUCCESS = "成功";

	private static final long TIME_OUT = 7000;

	private final Set<CtripSearchReq> ctripSearchReqs;

	private final RestTemplate restTemplate;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	private final IndexCoordinates indexCoordinates;

	public SearchServiceImpl(@NonNull GdsPolicyService gdsPolicyService, RestTemplate restTemplate, @NonNull ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.restTemplate = restTemplate;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
		this.indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(SearchResult.class);
		Set<GdsPolicy> gdsPolicySet = gdsPolicyService.getGdsPolicySet();
		this.ctripSearchReqs = ReqBuilderUtil.getCtripSearchReqSet(gdsPolicySet);
	}


	/**
	 * 询价
	 */
	@Override
	public void search() {
		Set<Runnable> runnableList = ctripSearchReqs.stream().map(ctripSearchReq -> (Runnable) () -> {
			Stopwatch stopwatch = Stopwatch.createStarted();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(ctripSearchReq), httpHeaders);
			ResponseEntity<CtripQDirectVo.CtripQSearchResponse> responseEntity = restTemplate
					.postForEntity(URL, httpEntity, CtripQDirectVo.CtripQSearchResponse.class);
			stopwatch.stop();
			long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
			SearchResult searchResult = new SearchResult();
			searchResult.setModule(Modules.XCSZJZGJ);
			searchResult.setDateTime(DateUtil.current());
			searchResult.setTimeOut(elapsed);
			searchResult.setCode(responseEntity.getStatusCodeValue());

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				if (elapsed > TIME_OUT) {
					searchResult.setStatus(Status.TIME_OUT);
				}
				else {
					searchResult.setStatus(Status.SUCCESS);
				}

				CtripQDirectVo.CtripQSearchResponse body = responseEntity.getBody();
				searchResult.setData(body);
				if (Objects.nonNull(body)) {
					searchResult.setMessage(body.getMsg());
					if (body.getMsg().contains(SUCCESS)) {
						searchResult.setIsValid(Boolean.TRUE);
					}
					else {
						searchResult.setIsValid(Boolean.FALSE);
					}
				}
			}
			else {
				if (elapsed > TIME_OUT) {
					searchResult.setStatus(Status.TIME_OUT);
				}
				else {
					searchResult.setStatus(Status.FAIL);
				}
			}
			elasticsearchRestTemplate.save(searchResult);

		}).collect(Collectors.toSet());
		try {
			do {
				ThreadUtil.sleep(1, TimeUnit.SECONDS);
			}
			while (ConcurrencyUtil.startTask(RandomUtil.randomEleSet(runnableList, 1000), 1000));
		}
		catch (InterruptedException e) {
			Log.get().error(ExceptionUtil.getRootCauseMessage(e));
		}
	}

	/**
	 * 检索询价结果
	 * @param start - 起始时间
	 * @param end - 结束时间
	 * @return 询价结果集合
	 */
	@Override
	public List<SearchResult> searchResults(@NonNull Date start, @NonNull Date end) {
		final int pageSize = 10000;
		List<String> scrollIds = Lists.newArrayList();
		Results.Search scrollStart = scrollStart(start, end);
		List<SearchResult> searchResults = scrollStart.getSearchResults();
		String scrollId = scrollStart.getScrollId();

		// 记录游标
		scrollIds.add(SerializeUtil.clone(scrollId));

		while (StrUtil.isNotBlank(scrollId)) {
			Results.Search scrollNext = scrollNext(scrollId);
			searchResults.addAll(scrollNext.getSearchResults());
			scrollId = scrollNext.getScrollId();
			// 记录游标
			scrollIds.add(SerializeUtil.clone(scrollId));
		}
		elasticsearchRestTemplate.searchScrollClear(scrollIds);
		return searchResults;
	}

	/**
	 * 初次查询
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @return - 结果
	 */
	@NonNull
	private Results.Search scrollStart(@NonNull Date start, @NonNull Date end) {
		// 设置滚动id的存在时间
		long scrollTimeInMillis = 30 * 1000;
		SearchScrollHits<SearchResult> searchHits = elasticsearchRestTemplate
				.searchScrollStart(scrollTimeInMillis, nativeSearchQuery(start, end, 10000), SearchResult.class, indexCoordinates);

		if (searchHits.hasSearchHits()) {
			List<SearchResult> searchResults = searchHits.get().map(SearchHit::getContent)
					.collect(Collectors.toList());
			return Results.Search.of(searchResults, searchHits.getScrollId());
		}

		return Results.Search.of(Collections.emptyList(), null);
	}

	/**
	 * 继续查询
	 * @param scrollId - 游标
	 * @return - 结果
	 */
	@NonNull
	private Results.Search scrollNext(String scrollId) {
		// 设置滚动id的存在时间
		long scrollTimeInMillis = 10 * 1000;
		SearchScrollHits<SearchResult> searchHits = elasticsearchRestTemplate
				.searchScrollContinue(scrollId, scrollTimeInMillis, SearchResult.class, indexCoordinates);

		if (searchHits.hasSearchHits()) {
			List<SearchResult> searchResults = searchHits.get().map(SearchHit::getContent)
					.collect(Collectors.toList());
			return Results.Search.of(searchResults, searchHits.getScrollId());
		}
		return Results.Search.of(Collections.emptyList(), null);
	}

	/**
	 * 构建查询
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @param pageSize - 查询个数
	 * @return - 结果
	 */
	@NonNull
	private NativeSearchQuery nativeSearchQuery(@NonNull Date start, @NonNull Date end, int pageSize) {
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		// 构建查询
		NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withSort(Sort.by("dateTime"))
				.withQuery(rangeQueryBuilder).build();
		// 设置每页数据量
		nativeSearchQuery.setMaxResults(pageSize);
		return nativeSearchQuery;
	}

	/**
	 * 关于超时信息的数据
	 * @param start - 起始时间
	 * @param end - 结束时间
	 * @return 聚合结果
	 */
	@Override
	public Stats timeOutQuery(@NonNull Date start, @NonNull Date end) {
		StatsAggregationBuilder statsAggregationBuilder = AggregationBuilders.stats("stats").field("timeOut");
		Query query = new NativeSearchQueryBuilder().withAggregations(statsAggregationBuilder).build();
		SearchHits<SearchResult> searchHits = elasticsearchRestTemplate.search(query, SearchResult.class);
		if (searchHits.hasAggregations()) {
			AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
			Aggregations aggregations = (Aggregations) Objects.requireNonNull(aggregationsContainer).aggregations();
			return aggregations.get("stats");
		}
		return new ParsedStats();
	}
}
