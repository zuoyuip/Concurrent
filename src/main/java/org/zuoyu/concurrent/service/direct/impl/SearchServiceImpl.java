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
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.ParsedStats;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.aggregations.metrics.StatsAggregationBuilder;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.maker.Switcher;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.direct.VerifyService;
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
@Slf4j
public class SearchServiceImpl implements SearchService {

//	private static final String URL = "http://192.168.1.99:8080/foreign/international/ctrip_szjz/search";
	private static final String URL = "http://47.102.104.115:8087/international/ctrip_szjz/search";

	private static final String SUCCESS = "??????";

	private static final long TIME_OUT = 7000;

	private final Set<CtripSearchReq> ctripSearchReqs;

	private final RestTemplate restTemplate;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	private final IndexCoordinates indexCoordinates;

	private final VerifyService verifyService;

	public SearchServiceImpl(@NonNull GdsPolicyService gdsPolicyService, RestTemplate restTemplate, @NonNull ElasticsearchRestTemplate elasticsearchRestTemplate, VerifyService verifyService) {
		this.restTemplate = restTemplate;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
		this.indexCoordinates = elasticsearchRestTemplate.getIndexCoordinatesFor(SearchResult.class);
		this.verifyService = verifyService;
		Set<GdsPolicy> gdsPolicySet = gdsPolicyService.getGdsPolicySet();
		this.ctripSearchReqs = ReqBuilderUtil.getCtripSearchReqSet(gdsPolicySet);
		log.info("/***\n" +
				" *      ?????????       ?????????\n" +
				" *   ???????????? ??????????????????????????? ????????????\n" +
				" *   ???                 ???\n" +
				" *   ???       ?????????       ???\n" +
				" *   ???  ?????????       ?????????  ???\n" +
				" *   ???                 ???\n" +
				" *   ???       ?????????       ???\n" +
				" *   ???                 ???\n" +
				" *   ???????????????         ???????????????\n" +
				" *       ???         ???\n" +
				" *       ???         ???\n" +
				" *       ???         ???\n" +
				" *       ???         ????????????????????????????????????????????????\n" +
				" *       ???                        ???\n" +
				" *       ???                        ?????????\n" +
				" *       ???                        ?????????\n" +
				" *       ???                        ???\n" +
				" *       ?????????  ???  ????????????????????????????????????  ????????????\n" +
				" *         ??? ?????? ??????       ??? ?????? ??????\n" +
				" *         ?????????????????????       ?????????????????????\n" +
				" *                           \n" +
				" *           ????????????????????????!\n" +
				" */");
	}



	/**
	 * ??????????????????
	 */
	@Override
	public void runSearchService() {
		Switcher.openSearchService();
		ThreadUtil.execute(this::search);
	}

	/**
	 * ??????????????????
	 */
	@Override
	public void stopSearchService() {
		Switcher.closeSearchService();
	}


	/**
	 * ??????
	 */
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
						if (Switcher.isOpenVerifyService()) {
							// ??????????????????
							ThreadUtil.execute(() -> verifyService.verify(body));
						}
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
				// ???????????????????????????
				if (!Switcher.isOpenSearchService()) {
					break;
				}
				ThreadUtil.sleep(1, TimeUnit.SECONDS);
			}
			// ??????????????????
			while (ConcurrencyUtil
					.startTask(RandomUtil.randomEleSet(runnableList, 500), 500));
		}
		catch (InterruptedException e) {
			Log.get().error(ExceptionUtil.getRootCauseMessage(e));
		}
	}

	/**
	 * ??????????????????
	 * @param start - ????????????
	 * @param end - ????????????
	 * @return ??????????????????
	 */
	@Override
	public List<SearchResult> searchResults(@NonNull Date start, @NonNull Date end) {
		final int pageSize = 10000;
		List<String> scrollIds = Lists.newArrayList();
		Results.Search scrollStart = scrollStart(start, end);
		List<SearchResult> searchResults = scrollStart.getSearchResults();
		String scrollId = scrollStart.getScrollId();

		// ????????????
		scrollIds.add(SerializeUtil.clone(scrollId));

		while (StrUtil.isNotBlank(scrollId)) {
			Results.Search scrollNext = scrollNext(scrollId);
			searchResults.addAll(scrollNext.getSearchResults());
			scrollId = scrollNext.getScrollId();
			// ????????????
			scrollIds.add(SerializeUtil.clone(scrollId));
		}
		elasticsearchRestTemplate.searchScrollClear(scrollIds);
		return searchResults;
	}

	/**
	 * ????????????
	 * @param start - ????????????
	 * @param end - ????????????
	 * @return - ??????
	 */
	@NonNull
	private Results.Search scrollStart(@NonNull Date start, @NonNull Date end) {
		// ????????????id???????????????
		long scrollTimeInMillis = 30 * 1000;
		SearchScrollHits<SearchResult> searchHits = elasticsearchRestTemplate
				.searchScrollStart(scrollTimeInMillis, nativeSearchQuery(start, end), SearchResult.class, indexCoordinates);

		if (searchHits.hasSearchHits()) {
			List<SearchResult> searchResults = searchHits.get().map(SearchHit::getContent)
					.collect(Collectors.toList());
			return Results.Search.of(searchResults, searchHits.getScrollId());
		}

		return Results.Search.of(Collections.emptyList(), null);
	}

	/**
	 * ????????????
	 * @param scrollId - ??????
	 * @return - ??????
	 */
	@NonNull
	private Results.Search scrollNext(String scrollId) {
		// ????????????id???????????????
		long scrollTimeInMillis = 30 * 1000;
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
	 * ????????????
	 * @param start - ????????????
	 * @param end - ????????????
	 * @return - ??????
	 */
	@NonNull
	private NativeSearchQuery nativeSearchQuery(@NonNull Date start, @NonNull Date end) {
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		// ??????????????????
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		// ????????????
		NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withSort(Sort.by("dateTime"))
				.withQuery(rangeQueryBuilder).build();
		// ?????????????????????
		nativeSearchQuery.setMaxResults(10000);
		return nativeSearchQuery;
	}

	/**
	 * ???????????????????????????
	 * @param start - ????????????
	 * @param end - ????????????
	 * @return ????????????
	 */
	@Override
	public Stats timeOutQuery(@NonNull Date start, @NonNull Date end) {
		StatsAggregationBuilder statsAggregationBuilder = AggregationBuilders.stats("stats").field("timeOut");
		// ??????????????????
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		Query query = new NativeSearchQueryBuilder().withAggregations(statsAggregationBuilder).withQuery(rangeQueryBuilder).build();
		SearchHits<SearchResult> searchHits = elasticsearchRestTemplate.search(query, SearchResult.class);
		if (searchHits.hasAggregations()) {
			AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
			Aggregations aggregations = (Aggregations) Objects.requireNonNull(aggregationsContainer).aggregations();
			return aggregations.get("stats");
		}
		return new ParsedStats();
	}
}
