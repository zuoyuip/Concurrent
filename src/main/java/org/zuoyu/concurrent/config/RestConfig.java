package org.zuoyu.concurrent.config;


import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
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
@AutoConfigureBefore(RestTemplateAutoConfiguration.class)
public class RestConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		return restTemplateBuilder.messageConverters(converter).build();
	}
}
