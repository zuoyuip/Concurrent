package org.zuoyu.concurrent.utils;

import java.util.Objects;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO RestTemplate提供
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/9 11:27
 * @Version 1.0
 */
public final class RestProvider {

	private volatile static RestTemplate REST_TEMPLATE;

	public static RestTemplate getRestTemplate() {
		if (Objects.isNull(REST_TEMPLATE)) {
			synchronized (RestProvider.class) {
				if (Objects.isNull(REST_TEMPLATE)) {
					RestTemplateBuilder restTemplateBuilder = SpringUtil.getBean(RestTemplateBuilder.class);
					FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
					REST_TEMPLATE = restTemplateBuilder.requestFactory(() -> {
						HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
						httpRequestFactory.setConnectionRequestTimeout(7000);
						httpRequestFactory.setConnectTimeout(7000);
						httpRequestFactory.setReadTimeout(7000);
						return httpRequestFactory;
					}).messageConverters(converter).build();
				}
			}
		}
		return REST_TEMPLATE;
	}

}
