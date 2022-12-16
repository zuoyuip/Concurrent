package org.zuoyu.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.service.RedisService;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;
import org.zuoyu.concurrent.utils.ReqBuilderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
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
	void clearSearchResult() {

	}

	@Test
	void searchResults() {
		DateTime start = DateUtil.parse("2022-12-16 10:00:00");
		DateTime end = DateUtil.parse("2022-12-16 16:00:00");
		List<SearchResult> searchResults = searchService.searchResults(start, end);
		System.out.println(searchResults.size());
	}
}
