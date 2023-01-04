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
import org.zuoyu.concurrent.service.direct.VerifyAggregation;
import org.zuoyu.concurrent.vo.Result;

import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2023/1/4 15:27
 * @Version 1.0
 */
@Service
public class VerifyAggregationImpl implements VerifyAggregation {

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public VerifyAggregationImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * 查询聚合信息
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @return 聚合信息
	 */
	@Override
	public Result search(Date start, Date end) {
		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(start.getTime(), false).to(end.getTime(), false);
		// 构建查询
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withSort(Sort.by("dateTime"))
				.withQuery(rangeQueryBuilder).build();
		nativeSearchQuery.setMaxResults(10000);
		SearchHits<org.zuoyu.concurrent.model.VerifyAggregation> searchHits = elasticsearchRestTemplate
				.search(nativeSearchQuery, org.zuoyu.concurrent.model.VerifyAggregation.class);
		List<org.zuoyu.concurrent.model.VerifyAggregation> verifyAggregations = searchHits.stream()
				.map(SearchHit::getContent)
				.sorted(Comparator
						.comparing(org.zuoyu.concurrent.model.VerifyAggregation::getDateTime))
				.collect(Collectors.toList());

		Result result = new Result();
		result.setCount(verifyAggregations.stream().map(org.zuoyu.concurrent.model.VerifyAggregation::getCount)
				.collect(Collectors.toList()));
		result.setDateTime(verifyAggregations.stream().map(org.zuoyu.concurrent.model.VerifyAggregation::getDateTime)
				.map(time -> DateUtil.date(time).toString(DatePattern.NORM_DATETIME_FORMAT))
				.collect(Collectors.toList()));
		result.setSuccessRate(verifyAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getSuccessRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setFailRate(verifyAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getFailRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setValidRate(verifyAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getValidRate(), new Double(100)))
				.collect(Collectors.toList()));
		result.setTimeOutRate(verifyAggregations.stream()
				.map(searchAggregation -> NumberUtil.mul(searchAggregation.getTimeOutRate(), new Double(100)))
				.collect(Collectors.toList()));
		return result;
	}
}
