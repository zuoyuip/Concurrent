package org.zuoyu.concurrent.vo;

import java.io.Serializable;
import java.util.List;

import cn.hutool.core.date.DateTime;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description TODO 返回结果包装
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/16 14:42
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

	private static final long serialVersionUID = 2250651363097305610L;

	/**
	 * 时间
	 */
	private List<String> dateTime;

	/**
	 * 成功率
	 */
	private List<Double> successRate;

	/**
	 * 超时率
	 */
	private List<Double> timeOutRate;

	/**
	 * 失败率
	 */
	private List<Double> failRate;

	/**
	 * 有效率
	 */
	private List<Double> validRate;

	/**
	 * 数据量
	 */
	private List<Long> count;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Result)) {
			return false;
		}
		Result result = (Result) o;
		return Objects.equal(getDateTime(), result.getDateTime()) && Objects
				.equal(getSuccessRate(), result.getSuccessRate()) && Objects
				.equal(getTimeOutRate(), result.getTimeOutRate()) && Objects
				.equal(getFailRate(), result.getFailRate()) && Objects
				.equal(getValidRate(), result.getValidRate()) && Objects
				.equal(getCount(), result.getCount());
	}

	@Override
	public int hashCode() {
		return Objects
				.hashCode(getDateTime(), getSuccessRate(), getTimeOutRate(), getFailRate(), getValidRate(), getCount());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("dateTime", dateTime)
				.add("successRate", successRate)
				.add("timeOutRate", timeOutRate)
				.add("failRate", failRate)
				.add("validRate", validRate)
				.add("count", count)
				.toString();
	}
}
