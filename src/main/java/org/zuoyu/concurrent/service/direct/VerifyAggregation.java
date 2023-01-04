package org.zuoyu.concurrent.service.direct;

import java.util.Date;

import org.zuoyu.concurrent.vo.Result;

/**
 * @Description TODO 验价数据聚合
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2023/1/4 15:26
 * @Version 1.0
 */
public interface VerifyAggregation {

	/**
	 * 查询聚合信息
	 * @param start - 开始时间
	 * @param end - 结束时间
	 * @return 聚合信息
	 */
	Result search(Date start, Date end);
}
