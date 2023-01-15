package org.zuoyu.concurrent.config;


import java.util.Arrays;

import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
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
		converter.setSupportedMediaTypes(Arrays.asList(
				MediaType.APPLICATION_ATOM_XML,
				MediaType.APPLICATION_CBOR,
				MediaType.APPLICATION_FORM_URLENCODED,
				MediaType.APPLICATION_GRAPHQL,
				MediaType.APPLICATION_JSON,
				MediaType.APPLICATION_NDJSON,
				MediaType.APPLICATION_OCTET_STREAM,
				MediaType.APPLICATION_PDF,
				MediaType.APPLICATION_PROBLEM_JSON,
				MediaType.APPLICATION_PROBLEM_XML,
				MediaType.APPLICATION_RSS_XML,
				MediaType.APPLICATION_XHTML_XML,
				MediaType.APPLICATION_XML,
				MediaType.IMAGE_GIF,
				MediaType.IMAGE_JPEG,
				MediaType.IMAGE_PNG,
				MediaType.MULTIPART_FORM_DATA,
				MediaType.MULTIPART_MIXED,
				MediaType.MULTIPART_RELATED,
				MediaType.TEXT_EVENT_STREAM,
				MediaType.TEXT_HTML,
				MediaType.TEXT_MARKDOWN,
				MediaType.TEXT_PLAIN,
				MediaType.TEXT_XML

		));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}
}
