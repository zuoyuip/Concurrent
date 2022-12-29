package org.zuoyu.concurrent.service.direct.impl;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.zuoyu.concurrent.service.direct.SearchAggregation;
import org.zuoyu.concurrent.vo.Result;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/28 11:53
 * @Version 1.0
 */
@Service
public class SearchAggregationImpl implements SearchAggregation {

	/**
	 * 返回的数据条数
	 */
	private static final int SIZE = 200;
	private static final String RATE = "#.##%";
	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public SearchAggregationImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * 查询聚合信息
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @return 聚合信息
	 */
	@Override
	public Result search(@NonNull Date start, @NonNull Date end) {
		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		// 构建查询
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withSort(Sort.by("dateTime"))
				.withQuery(rangeQueryBuilder).build();
		nativeSearchQuery.setMaxResults(10000);
		SearchHits<org.zuoyu.concurrent.model.SearchAggregation> searchHits = elasticsearchRestTemplate
				.search(nativeSearchQuery, org.zuoyu.concurrent.model.SearchAggregation.class);
		List<org.zuoyu.concurrent.model.SearchAggregation> searchAggregations = searchHits.stream()
				.map(SearchHit::getContent)
				.sorted(Comparator
						.comparing(org.zuoyu.concurrent.model.SearchAggregation::getDateTime))
				.collect(Collectors.toList());

		Result result = new Result();
		result.setCount(searchAggregations.stream().map(org.zuoyu.concurrent.model.SearchAggregation::getCount)
				.collect(Collectors.toList()));
		result.setDateTime(searchAggregations.stream().map(org.zuoyu.concurrent.model.SearchAggregation::getDateTime)
				.map(time -> DateUtil.date(time).toString(DatePattern.NORM_DATETIME_FORMAT))
				.collect(Collectors.toList()));
		result.setSuccessRate(searchAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getSuccessRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setFailRate(searchAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getFailRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setValidRate(searchAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getValidRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setTimeOutRate(searchAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getTimeOutRate(), new Double(100)))
				.collect(Collectors.toList()));
		return result;
	}
}
