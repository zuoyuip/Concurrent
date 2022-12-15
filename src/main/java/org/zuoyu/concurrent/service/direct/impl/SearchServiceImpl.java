package org.zuoyu.concurrent.service.direct.impl;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Stopwatch;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.SearchResult;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.repository.SearchResultRepository;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.policy.GdsPolicyService;
import org.zuoyu.concurrent.utils.ConcurrencyUtil;
import org.zuoyu.concurrent.utils.ReqBuilderUtil;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

/**
 * @Description TODO
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/13 18:42
 * @Version 1.0
 */
@Service
@RequestScope
public class SearchServiceImpl implements SearchService {

	private static final String URL = "http://192.168.1.99:8080/foreign/international/ctrip_szjz/search";
//	private static final String URL = "http://tts.justgotrip.cn:8087/international/ctrip_szjz/search";

	private static final long TIME_OUT = 7000;

	private final Set<CtripSearchReq> ctripSearchReqs;

	private final SearchResultRepository searchResultRepository;

	private final RestTemplate restTemplate;


	public SearchServiceImpl(SearchResultRepository searchResultRepository, @NonNull GdsPolicyService gdsPolicyService, RestTemplate restTemplate) {
		this.searchResultRepository = searchResultRepository;
		this.restTemplate = restTemplate;
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
			searchResult.setDateTime(DateUtil.date());
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
			SearchResult result = searchResultRepository.save(searchResult);
			Log.get().info("{}", result.toString());

		}).collect(Collectors.toSet());
		try {
			AtomicInteger atomicInteger = new AtomicInteger(0);
			do {
				Log.get().info("atomicInteger.incrementAndGet() is {}", atomicInteger.incrementAndGet());
				ThreadUtil.sleep(1, TimeUnit.SECONDS);
			}
			while (ConcurrencyUtil.startTask(RandomUtil.randomEleSet(runnableList, 1000), 1000));
		}
		catch (InterruptedException e) {
			Log.get().error(ExceptionUtil.getRootCauseMessage(e));
		}
	}
}
