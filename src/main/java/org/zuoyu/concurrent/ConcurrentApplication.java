package org.zuoyu.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author EDY
 */
@SpringBootApplication
@EnableRedisRepositories
public class ConcurrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrentApplication.class, args);
	}

}
