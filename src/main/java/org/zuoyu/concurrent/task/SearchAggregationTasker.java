package org.zuoyu.concurrent.task;

import java.util.Objects;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilters;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.QueryParams;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchAggregation;
import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.data.elasticsearch.core.AggregationsContainer;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 查询结果聚合任务
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/27 10:51
 * @Version 1.0
 */
@Component
@Slf4j
public class SearchAggregationTasker {

	private static final String RESULT = "result";
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public SearchAggregationTasker(ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * 每整分钟执行
	 */
	@Scheduled(cron = "0 * * * * ? ")
	@Async("scheduledPoolTaskExecutor")
	public void searchAggregation() {
		DateTime nowDateTime = DateUtil.dateSecond();
		DateTime offsetMinute = DateUtil.offsetMinute(nowDateTime, -1);

		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(offsetMinute.getTime(), false).to(nowDateTime.getTime(), false);
		// 数量
		FiltersAggregator.KeyedFilter countQuery = new FiltersAggregator.KeyedFilter(QueryParams.COUNT_QUERY, QueryBuilders
				.matchAllQuery());
		// 有效查询的数量
		FiltersAggregator.KeyedFilter validQuery = new FiltersAggregator.KeyedFilter(QueryParams.VALID_QUERY, QueryBuilders
				.termQuery("isValid", Boolean.TRUE));
		// 查询成功的数量
		FiltersAggregator.KeyedFilter successQuery = new FiltersAggregator.KeyedFilter(QueryParams.SUCCESS_QUERY, QueryBuilders
				.termQuery("status", Status.SUCCESS));
		// 查询失败的数量
		FiltersAggregator.KeyedFilter failQuery = new FiltersAggregator.KeyedFilter(QueryParams.FAIL_QUERY, QueryBuilders
				.termQuery("status", Status.FAIL));
		// 查询超时的数量
		FiltersAggregator.KeyedFilter timeOutQuery = new FiltersAggregator.KeyedFilter(QueryParams.TIME_OUT_QUERY, QueryBuilders
				.termQuery("status", Status.TIME_OUT));
		FiltersAggregationBuilder resultBuilder = AggregationBuilders
				.filters(RESULT, countQuery, validQuery, successQuery, failQuery, timeOutQuery);
		Query query = new NativeSearchQueryBuilder().withAggregations(resultBuilder).withQuery(rangeQueryBuilder)
				.build();
		SearchHits<SearchResult> searchHits = elasticsearchRestTemplate.search(query, SearchResult.class);
		if (searchHits.hasAggregations()) {
			AggregationsContainer<?> aggregationsContainer = searchHits.getAggregations();
			Aggregations aggregations = (Aggregations) Objects.requireNonNull(aggregationsContainer).aggregations();
			ParsedFilters parsedFilters = aggregations.get(RESULT);
			ParsedFilters.ParsedBucket countQueryBucket = parsedFilters.getBucketByKey(QueryParams.COUNT_QUERY);
			ParsedFilters.ParsedBucket validQueryBucket = parsedFilters.getBucketByKey(QueryParams.VALID_QUERY);
			ParsedFilters.ParsedBucket successQueryBucket = parsedFilters.getBucketByKey(QueryParams.SUCCESS_QUERY);
			ParsedFilters.ParsedBucket failQueryBucket = parsedFilters.getBucketByKey(QueryParams.FAIL_QUERY);
			ParsedFilters.ParsedBucket timeOutQueryBucket = parsedFilters.getBucketByKey(QueryParams.TIME_OUT_QUERY);

			// 总数量
			long countQueryBucketDocCount = countQueryBucket.getDocCount();
			// 有效数据量
			long validQueryBucketDocCount = validQueryBucket.getDocCount();
			// 成功数据量
			long successQueryBucketDocCount = successQueryBucket.getDocCount();
			// 失败数据量
			long failQueryBucketDocCount = failQueryBucket.getDocCount();
			// 超时数据量
			long timeOutQueryBucketDocCount = timeOutQueryBucket.getDocCount();

			SearchAggregation searchAggregation = new SearchAggregation();
			searchAggregation.setModule(Modules.XCSZJZGJ);
			searchAggregation.setDateTime(nowDateTime.getTime());
			searchAggregation.setCount(countQueryBucketDocCount);

			// 有效率
			searchAggregation.setValidRate(NumberUtil.div(validQueryBucketDocCount, countQueryBucketDocCount));
			// 成功率
			searchAggregation.setSuccessRate(NumberUtil.div(successQueryBucketDocCount, countQueryBucketDocCount));
			// 失败率
			searchAggregation.setFailRate(NumberUtil.div(failQueryBucketDocCount, countQueryBucketDocCount));
			// 超时率
			searchAggregation.setTimeOutRate(NumberUtil.div(timeOutQueryBucketDocCount, countQueryBucketDocCount));
			elasticsearchRestTemplate.save(searchAggregation);
		}

	}
}
