package org.zuoyu.concurrent.config;

import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description TODO Redis序列化配置
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 15:07
 * @Version 1.0
 */
@Configuration
@EnableCaching
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();


		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(serializer);

		// Hash的key也采用StringRedisSerializer的序列化方式
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(serializer);

		template.afterPropertiesSet();
		return template;
	}
}
