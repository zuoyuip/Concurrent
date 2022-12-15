package org.zuoyu.concurrent.config;

import java.util.List;

import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author hmydts
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(@NonNull List<HttpMessageConverter<?>> converters) {
		converters.add(new FastJsonHttpMessageConverter());
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}
}
