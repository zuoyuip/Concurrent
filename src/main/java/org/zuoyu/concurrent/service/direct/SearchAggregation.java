package org.zuoyu.concurrent.service.direct;

import java.util.Date;

import org.zuoyu.concurrent.vo.Result;

/**
 * @Description TODO 查询信息聚合服务
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/28 11:43
 * @Version 1.0
 */
public interface SearchAggregation {

	/**
	 * 查询聚合信息
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @return 聚合信息
	 */
	Result search(Date start, Date end);
}
