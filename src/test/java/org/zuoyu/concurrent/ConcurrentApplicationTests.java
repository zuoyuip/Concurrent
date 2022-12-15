package org.zuoyu.concurrent;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.repository.SearchResultRepository;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;
import org.zuoyu.concurrent.service.RedisService;
import org.zuoyu.concurrent.utils.ReqBuilderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConcurrentApplicationTests {

	@Autowired
	private SearchResultRepository searchResultRepository;

	private static final String KEY = "gds:policy";

	@Autowired
	private RedisService redisService;

	@Autowired
	private GdsPolicyService gdsPolicyService;

	@Autowired
	private SearchService searchService;

	@Test
	void contextLoads() {
	}

	@Test
	void saveSearchResult() {
		SearchResult searchResult = new SearchResult();
		searchResult.setDateTime(new Date());
		searchResult.setStatus(Status.SUCCESS);
		searchResult.setTimeOut(1000L);
		SearchResult save = searchResultRepository.save(searchResult);
		System.out.println(searchResult);
	}

	@Test
	void selectSearchResult() {
		searchResultRepository.findAll().forEach(System.out::println);
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
}
