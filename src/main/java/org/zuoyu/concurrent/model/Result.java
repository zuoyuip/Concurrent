package org.zuoyu.concurrent.model;

import java.util.Date;

import cn.hutool.core.util.IdUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zuoyu.concurrent.model.base.ElasticsearchBaseEntity;

/**
 * @Description TODO 查询结果
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 14:27
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result extends ElasticsearchBaseEntity<String> {

	private static final long serialVersionUID = 4686620889191803559L;

	/**
	 * 时间
	 */
	private Date dateTime;

	/**
	 * 请求结果状态(1 成功； 2 失败 ； 3 超时)
	 */
	private Integer status;

	/**
	 * 耗时
	 */
	private Long timeOut;

	/**
	 * 信息
	 */
	private String message;

	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 结果
	 */
	private String data;

	public Result() {
		super.setId(IdUtil.fastSimpleUUID());
	}
}

