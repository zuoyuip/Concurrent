package org.zuoyu.concurrent.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import org.zuoyu.concurrent.service.direct.SearchService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO 测试
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/12 17:24
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {


	private final SearchService searchService;

	public TestController(SearchService searchService) {
		this.searchService = searchService;
	}

	@PostMapping
	public void test(@RequestBody String body) {
		Log.get().info("body is {}", body);
		searchService.search();
	}
}
