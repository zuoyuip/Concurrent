package org.zuoyu.concurrent.controller;

import org.zuoyu.concurrent.service.direct.SearchService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO 控制器
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/16 10:07
 * @Version 1.0
 */
@RestController
@RequestMapping
public class BusinessController {

	private final SearchService searchService;

	public BusinessController(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * 执行
	 */
	@GetMapping("run")
	public void run() {
		searchService.search();
	}
}
