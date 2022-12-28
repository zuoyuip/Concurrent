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
 * @Description TODO 结果聚合
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/27 10:34
 * @Version 1.0
 */
@Getter
@Setter
public class Aggregation extends ElasticsearchBaseEntity<String> {

	private static final long serialVersionUID = -6555026575601044017L;

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
	 * 成功率
	 */
	@Field(type = FieldType.Double)
	private Double successRate;

	/**
	 * 失败率
	 */
	@Field(type = FieldType.Double)
	private Double failRate;

	/**
	 * 超时率
	 */
	@Field(type = FieldType.Double)
	private Double timeOutRate;

	/**
	 * 有效率
	 */
	@Field(type = FieldType.Double)
	private Double validRate;

	/**
	 * 数据数量
	 */
	@Field(type = FieldType.Long)
	private Long count;

	public Aggregation() {
		super.setId(IdUtil.fastSimpleUUID());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Aggregation)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		Aggregation that = (Aggregation) o;
		return Objects.equal(getModule(), that.getModule()) && Objects
				.equal(getDateTime(), that.getDateTime()) && Objects
				.equal(getSuccessRate(), that.getSuccessRate()) && Objects
				.equal(getFailRate(), that.getFailRate()) && Objects
				.equal(getTimeOutRate(), that.getTimeOutRate()) && Objects
				.equal(getValidRate(), that.getValidRate()) && Objects
				.equal(getCount(), that.getCount());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super
				.hashCode(), getModule(), getDateTime(), getSuccessRate(), getFailRate(), getTimeOutRate(), getValidRate(), getCount());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("module", module)
				.add("dateTime", dateTime)
				.add("successRate", successRate)
				.add("failRate", failRate)
				.add("timeOutRate", timeOutRate)
				.add("validRate", validRate)
				.add("count", count)
				.toString();
	}
}
