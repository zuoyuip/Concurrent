package org.zuoyu.concurrent.controller;

import cn.hutool.core.date.DateTime;
import org.zuoyu.concurrent.maker.Switcher;
import org.zuoyu.concurrent.service.direct.SearchAggregation;
import org.zuoyu.concurrent.service.direct.SearchService;
import org.zuoyu.concurrent.service.direct.VerifyAggregation;
import org.zuoyu.concurrent.vo.Req;
import org.zuoyu.concurrent.vo.Result;
import org.zuoyu.concurrent.vo.Watcher;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	private final SearchAggregation searchAggregation;

	private final VerifyAggregation verifyAggregation;

	public BusinessController(SearchService searchService, SearchAggregation searchAggregation, VerifyAggregation verifyAggregation) {
		this.searchService = searchService;
		this.searchAggregation = searchAggregation;
		this.verifyAggregation = verifyAggregation;
	}

	/**
	 * 启动询价服务
	 */
	@GetMapping("runSearch")
	public void runSearchService() {
		searchService.runSearchService();
	}

	/**
	 * 停止询价服务
	 */
	@GetMapping("stopSearch")
	public void stopSearchService() {
		searchService.stopSearchService();
	}

	/**
	 * 是否在运行询价
	 */
	@GetMapping("isRunSearch")
	public boolean isRunSearchService() {
		return Switcher.isOpenSearchService();
	}

	/**
	 * 启动验价服务
	 */
	@GetMapping("runVerify")
	public void runVerifyService() {
		Switcher.openVerifyService();
	}

	/**
	 * 停止验价服务
	 */
	@GetMapping("stopVerify")
	public void stopVerifyService() {
		Switcher.closeVerifyService();
	}

	/**
	 * 是否在运行验价
	 */
	@GetMapping("isRunVerify")
	public boolean isRunVerifyService() {
		return Switcher.isOpenVerifyService();
	}

	/**
	 * 获取时间监视数据
	 */
	@GetMapping("getWatcher")
	public Watcher getWatcher() {
		return Switcher.watchInfo();
	}

	/**
	 * 询价聚合数据
	 * @param req - 请求
	 * @return 聚合数据
	 */
	@PostMapping("searchAggregation")
	public Result searchAggregation(@RequestBody @NonNull Req req) {
		DateTime start = req.getStart();
		DateTime end = req.getEnd();
		return searchAggregation.search(start, end);
	}

	/**
	 * 验价聚合数据
	 * @param req - 请求
	 * @return 聚合数据
	 */
	@PostMapping("verifyAggregation")
	public Result verifyAggregation(@RequestBody @NonNull Req req) {
		DateTime start = req.getStart();
		DateTime end = req.getEnd();
		return verifyAggregation.search(start, end);
	}
}
