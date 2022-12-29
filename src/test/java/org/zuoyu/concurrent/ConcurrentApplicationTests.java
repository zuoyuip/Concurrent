package org.zuoyu.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilters;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.service.RedisService;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;
import org.zuoyu.concurrent.utils.ReqBuilderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

@SpringBootTest
@Slf4j
class ConcurrentApplicationTests {


	private static final String KEY = "gds:policy";

	@Autowired
	private RedisService redisService;

	@Autowired
	private GdsPolicyService gdsPolicyService;

	@Autowired
	private SearchService searchService;

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void saveRedisTemplate() {
		Set<String> keys = redisService.hashKeys(KEY).stream().filter(s -> s.endsWith(Modules.XCSZJZGJ))
				.collect(Collectors.toSet());
		List<Set<JSONObject>> multiCacheMapValue = redisService.getMultiCacheMapValue(KEY, keys);
		System.out.println("multiCacheMapValue" + multiCacheMapValue.size());
		Set<JSONObject> collect = multiCacheMapValue.stream().flatMap(Collection::stream).collect(Collectors.toSet());
		collect.forEach(System.out::println);
	}

	@Test
	void gdsPolicyService() {
		Set<GdsPolicy> gdsPolicySet = gdsPolicyService.getGdsPolicySet();
		System.out.println("gdsPolicySet.size() is " + gdsPolicySet.size());
		Set<CtripSearchReq> ctripSearchReqSet = ReqBuilderUtil.getCtripSearchReqSet(gdsPolicySet);
		System.out.println("ctripSearchReqSet.size() is " + ctripSearchReqSet.size());
	}

	@Test
	void timeOutQuery() {
		DateTime start = DateUtil.parse("2022-12-23 16:00:00");
		DateTime end = DateUtil.parse("2022-12-29 18:00:00");
		Stats stats = searchService.timeOutQuery(start, end);
		System.out.println("stats.getMax()：" + stats.getMax());
		System.out.println("stats.getCount()：" + stats.getCount());
		System.out.println("stats.getSum()：" + stats.getSum());
		System.out.println("stats.getMin()：" + stats.getMin());
		System.out.println("stats.getAvg()：" + stats.getAvg());
	}

	@Test
	void searchResults() {
		DateTime start = DateUtil.parse("2022-12-23 10:00:00");
		DateTime end = DateUtil.parse("2022-12-23 18:00:00");
		Stopwatch stopwatch = Stopwatch.createStarted();
		List<SearchResult> searchResults = searchService.searchResults(start, end);
		System.out.println(searchResults.size());
		stopwatch.stop();
		System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

	@Test
	void countResults() {
		DateTime start = DateUtil.parse("2022-12-23 10:00:00");
		DateTime end = DateUtil.parse("2022-12-23 18:00:00");
		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		// 数量
		FiltersAggregator.KeyedFilter countQuery = new FiltersAggregator.KeyedFilter("countQuery", QueryBuilders
				.matchAllQuery());
		// 有效查询的数量
		FiltersAggregator.KeyedFilter validQuery = new FiltersAggregator.KeyedFilter("validQuery", QueryBuilders
				.termQuery("isValid", Boolean.TRUE));
		// 查询成功的数量
		FiltersAggregator.KeyedFilter successQuery = new FiltersAggregator.KeyedFilter("successQuery", QueryBuilders
				.termQuery("status", Status.SUCCESS));
		// 查询失败的数量
		FiltersAggregator.KeyedFilter failQuery = new FiltersAggregator.KeyedFilter("failQuery", QueryBuilders
				.termQuery("status", Status.FAIL));
		// 查询超时的数量
		FiltersAggregator.KeyedFilter timeOutQuery = new FiltersAggregator.KeyedFilter("timeOutQuery", QueryBuilders
				.termQuery("status", Status.TIME_OUT));
		FiltersAggregationBuilder resultBuilder = AggregationBuilders.filters("result", countQuery, validQuery, successQuery, failQuery, timeOutQuery);
		Query query = new NativeSearchQueryBuilder().withAggregations(resultBuilder).withQuery(rangeQueryBuilder).build();
		SearchHits<SearchResult> searchHits = elasticsearchRestTemplate.search(query, SearchResult.class);
		if (searchHits.hasAggregations()) {
			AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
			Aggregations aggregations = (Aggregations) Objects.requireNonNull(aggregationsContainer).aggregations();
			ParsedFilters parsedFilters = aggregations.get("result");
			ParsedFilters.ParsedBucket countQueryBucket = parsedFilters.getBucketByKey("countQuery");
			ParsedFilters.ParsedBucket validQueryBucket = parsedFilters.getBucketByKey("validQuery");
			ParsedFilters.ParsedBucket successQueryBucket = parsedFilters.getBucketByKey("successQuery");
			ParsedFilters.ParsedBucket failQueryBucket = parsedFilters.getBucketByKey("failQuery");
			ParsedFilters.ParsedBucket timeOutQueryBucket = parsedFilters.getBucketByKey("timeOutQuery");
			log.info("countQueryBucket is {}", countQueryBucket.getDocCount());
			log.info("validQueryBucket is {}", validQueryBucket.getDocCount());
			log.info("successQueryBucket is {}", successQueryBucket.getDocCount());
			log.info("failQueryBucket is {}", failQueryBucket.getDocCount());
			log.info("timeOutQueryBucket is {}", timeOutQueryBucket.getDocCount());

		}
	}
}
