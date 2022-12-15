package org.zuoyu.concurrent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.hutool.log.Log;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.utils.ConcurrencyUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO CountDownLatch测试
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/12 14:11
 * @Version 1.0
 */
public class CountDownLatchTest {



	@Test
	void test_one() throws InterruptedException {
//		Set<Runnable> runnableList = new HashSet<>(100);
//		for (int i = 0; i < 1000; i++) {
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setContentType(MediaType.TEXT_PLAIN);
//			String reqJson = JSON.toJSONString(i);
//			HttpEntity<String> httpEntity = new HttpEntity<>(reqJson, httpHeaders);
//
//			Runnable taskTemp = () -> {
//				ResponseEntity<String> responseEntity = restTemplate
//						.postForEntity("http://localhost:8080/test", httpEntity, String.class);
//				Log.get().info(responseEntity.getBody());
//			};
//			runnableList.add(taskTemp);
//		}
//		ConcurrencyUtil.startTask(runnableList, 500);
	}

}
