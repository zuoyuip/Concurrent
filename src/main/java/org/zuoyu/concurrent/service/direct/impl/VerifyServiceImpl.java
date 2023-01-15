package org.zuoyu.concurrent.service.direct.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Stopwatch;
import org.zuoyu.concurrent.constant.Modules;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.VerifyResult;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.service.direct.VerifyService;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO 验价服务
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/29 13:44
 * @Version 1.0
 */
@Service
public class VerifyServiceImpl implements VerifyService {

	/**
	 * 去
	 */
	private static final String FROM = "FROM";

	/**
	 * 返
	 */
	private static final String BACK = "BACK";

	private static final String SUCCESS = "成功";

	private static final long TIME_OUT = 10000;

		private static final String URL = "http://106.14.157.239:8087/international/ctrip_szjz/verify";
//	private static final String URL = "http://47.102.104.115:8087/international/ctrip_szjz/verify";


	private final RestTemplate restTemplate;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public VerifyServiceImpl(RestTemplate restTemplate, ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.restTemplate = restTemplate;
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * 验价
	 * @param searchResponse - 询价返回参数
	 */
	@Override
	public void verify(@NonNull CtripQDirectVo.CtripQSearchResponse searchResponse) {
		CtripQDirectVo.ShoppingResult shoppingResult = searchResponse.getShoppingResultList().get(0);
		List<CtripQDirectVo.FlightRef> flightRefList = shoppingResult.getFlightRefList();
		List<CtripQDirectVo.Flight> flightList = searchResponse.getFlightList();
		CtripQDirectVo.Routing routing = new CtripQDirectVo.Routing();

		Map<String, List<CtripQDirectVo.Segment>> map = flightRefList.stream().map(flightRef -> {
			Optional<CtripQDirectVo.Flight> flightOptional = flightList.stream()
					.filter(flight -> flight.getFlightRefNum().intValue() == flightRef.getFlightRefNum().intValue())
					.findFirst();
			CtripQDirectVo.Flight flight = flightOptional.orElse(new CtripQDirectVo.Flight());
			CtripQDirectVo.Segment segment = new CtripQDirectVo.Segment();
			segment.setMarketingCarrier(flight.getMarketingCarrier());
			segment.setFlightNumber(flight.getFlightNumber());
			if (StrUtil.isNotBlank(flight.getOperatingCarrier())) {
				segment.setOperatingCarrier(flight.getOperatingCarrier());
			}
			if (StrUtil.isNotBlank(flight.getOperatingFlightNo())) {
				segment.setOperatingFlightNo(flight.getOperatingFlightNo());
			}
			segment.setDepAirport(flight.getDepAirport());
			segment.setArrAirport(flight.getArrAirport());
			segment.setDepTime(flight.getDepTime());
			segment.setArrTime(flight.getArrTime());
			segment.setCodeShare(flight.getCodeShare());
			segment.setAircraftCode(flight.getAircraftCode());
			segment.setSeatClass(flightRef.getSeatClass());
			segment.setSegmentNo(flightRef.getSegmentNo());
			return segment;
		}).collect(Collectors.groupingBy(segment -> {
			if (segment.getSegmentNo() == 1) {
				return FROM;
			}
			return BACK;
		}, Collectors.toList()));

		// 赋值Data
		routing.setData(shoppingResult.getData());
		routing.setFromSegments(map.getOrDefault(FROM, Collections.emptyList()));
		routing.setRetSegments(map.getOrDefault(BACK, Collections.emptyList()));
		CtripQDirectVo.CtripQVerifyRequest verifyRequest = new CtripQDirectVo.CtripQVerifyRequest();
		verifyRequest.setRouting(routing);
		if (CollectionUtil.isNotEmpty(routing.getRetSegments())) {
			verifyRequest.setTripType(String.valueOf(2));
		}
		else {
			verifyRequest.setTripType(String.valueOf(1));
		}
		Stopwatch stopwatch = Stopwatch.createStarted();


		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(verifyRequest), httpHeaders);
		ResponseEntity<CtripQDirectVo.CtripQOrderResponse> responseEntity = restTemplate
				.postForEntity(URL, httpEntity, CtripQDirectVo.CtripQOrderResponse.class);

		stopwatch.stop();
		long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

		VerifyResult verifyResult = new VerifyResult();
		verifyResult.setModule(Modules.XCSZJZGJ);
		verifyResult.setDateTime(DateUtil.current());
		verifyResult.setTimeOut(elapsed);
		verifyResult.setCode(responseEntity.getStatusCodeValue());

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			if (elapsed > TIME_OUT) {
				verifyResult.setStatus(Status.TIME_OUT);
			}
			else {
				verifyResult.setStatus(Status.SUCCESS);
			}

			CtripQDirectVo.CtripQOrderResponse body = responseEntity.getBody();
			verifyResult.setData(body);
			if (Objects.nonNull(body)) {
				verifyResult.setMessage(body.getMsg());
				if (body.getMsg().contains(SUCCESS)) {
					verifyResult.setIsValid(Boolean.TRUE);
				}
				else {
					verifyResult.setIsValid(Boolean.FALSE);
				}
			}
		}
		else {
			if (elapsed > TIME_OUT) {
				verifyResult.setStatus(Status.TIME_OUT);
			}
			else {
				verifyResult.setStatus(Status.FAIL);
			}
		}
		elasticsearchRestTemplate.save(verifyResult);
	}
}
