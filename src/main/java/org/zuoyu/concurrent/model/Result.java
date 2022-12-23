package org.zuoyu.concurrent.model;

import cn.hutool.core.util.IdUtil;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.zuoyu.concurrent.model.base.ElasticsearchBaseEntity;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Description TODO 查询结果
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 14:27
 * @Version 1.0
 */
@Getter
@Setter
public class Result<T> extends ElasticsearchBaseEntity<String> {

	private static final long serialVersionUID = 4686620889191803559L;

	/**
	 * 所属模块
	 */
	@Field(type = FieldType.Keyword)
	private String module;

	/**
	 * 时间
	 */
	@Field(type = FieldType.Long)
	private Long dateTime;

	/**
	 * 请求结果状态(1 成功； 2 失败 ； 3 超时)
	 */
	@Field(type = FieldType.Integer)
	private Integer status;

	/**
	 * 耗时
	 */
	@Field(type = FieldType.Integer)
	private Long timeOut;

	/**
	 * 信息
	 */
	@Field(type = FieldType.Keyword)
	private String message;

	/**
	 * 是否有效
	 */
	@Field(type = FieldType.Boolean)
	private Boolean isValid;

	/**
	 * 状态码
	 */
	@Field(type = FieldType.Integer)
	private Integer code;

	/**
	 * 结果
	 */
	@Field(type = FieldType.Auto)
	private T data;

	public Result() {
		super.setId(IdUtil.fastSimpleUUID());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Result)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Result<?> result = (Result<?>) o;
		return Objects.equal(getModule(), result.getModule()) && Objects
				.equal(getDateTime(), result.getDateTime()) && Objects
				.equal(getStatus(), result.getStatus()) && Objects
				.equal(getTimeOut(), result.getTimeOut()) && Objects
				.equal(getMessage(), result.getMessage()) && Objects
				.equal(getCode(), result.getCode()) && Objects.equal(getData(), result.getData());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super
				.hashCode(), getModule(), getDateTime(), getStatus(), getTimeOut(), getMessage(), getCode(), getData());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("module", module)
				.add("dateTime", dateTime)
				.add("status", status)
				.add("timeOut", timeOut)
				.add("message", message)
				.add("code", code)
				.add("data", data)
				.toString();
	}
}

