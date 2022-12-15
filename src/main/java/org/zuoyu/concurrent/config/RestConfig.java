package org.zuoyu.concurrent.config;


import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO RestTemplate的相关配置
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 11:03
 * @Version 1.0
 */
@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}
}
