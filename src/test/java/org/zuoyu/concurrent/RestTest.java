package org.zuoyu.concurrent;

import java.util.Objects;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.constant.Status;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;
import org.zuoyu.concurrent.model.vo.direct.vo.CtripQDirectVo;
import org.zuoyu.concurrent.utils.RestProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class RestTest {

	@Autowired
	private RestTemplate restTemplate;

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
}
