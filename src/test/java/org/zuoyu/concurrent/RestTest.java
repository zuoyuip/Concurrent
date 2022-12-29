package org.zuoyu.concurrent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.model.Aggregation;
import org.zuoyu.concurrent.model.SearchAggregation;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.vo.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO 请求测试
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/23 14:20
 * @Version 1.0
 */
@SpringBootTest
@Slf4j
public class RestTest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	private static final String URL = "http://tts.justgotrip.cn:8087/international/ctrip_szjz/search";

	@Test
	void requestTest() {
		CtripSearchReq ctripSearchReq = new CtripSearchReq();

		Integer tripType = 1;
		ctripSearchReq.setTripType(tripType.toString());
		ctripSearchReq.setFromCity("FRA");
		ctripSearchReq.setToCity("KUL");
		ctripSearchReq.setAdultNumber(1);
		ctripSearchReq.setChildNumber(1);
		ctripSearchReq.setChannel("");
		ctripSearchReq.setRequestSource(3);
		ctripSearchReq.setFromDate("20230120");
		ctripSearchReq.setCid("szjzkj_gj");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(ctripSearchReq), httpHeaders);
		ResponseEntity<CtripQDirectVo.CtripQSearchResponse> responseEntity = restTemplate
				.postForEntity(URL, httpEntity, CtripQDirectVo.CtripQSearchResponse.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {

			CtripQDirectVo.CtripQSearchResponse body = responseEntity.getBody();
			System.out.println(body);
		}
		else {
			System.err.println(responseEntity);
		}
	}

	@Test
	void aggregationSearch() {
		// 构建查询条件
		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("dateTime")
				.from(1672129320000L, false).to(1672198740000L, false);
		// 构建查询
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withSort(Sort.by("dateTime"))
				.withQuery(rangeQueryBuilder).build();
		nativeSearchQuery.setMaxResults(10000);
		SearchHits<SearchAggregation> searchHits = elasticsearchRestTemplate
				.search(nativeSearchQuery, org.zuoyu.concurrent.model.SearchAggregation.class);
		List<SearchAggregation> searchAggregations = searchHits.stream().map(SearchHit::getContent)
				.sorted(Comparator.comparing(SearchAggregation::getDateTime)).collect(Collectors.toList());


	}
}
