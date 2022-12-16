package org.zuoyu.concurrent.vo;

import java.io.Serializable;

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
public class Re implements Serializable {

	private static final long serialVersionUID = 2250651363097305610L;

	/**
	 * 成功率
	 */
	private Double successRatio;

	/**
	 * 超时率
	 */
	private Double timeOutRatio;

	/**
	 * 失败率
	 */
	private Double failRatio;

	/**
	 * 有效率
	 */
	private Double validRatio;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Re)) {
			return false;
		}
		Re re = (Re) o;
		return Objects
				.equal(getSuccessRatio(), re.getSuccessRatio()) && Objects
				.equal(getTimeOutRatio(), re.getTimeOutRatio()) && Objects
				.equal(getFailRatio(), re.getFailRatio()) && Objects
				.equal(getValidRatio(), re.getValidRatio());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getSuccessRatio(), getTimeOutRatio(), getFailRatio(), getValidRatio());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("successRatio", successRatio)
				.add("timeOutRatio", timeOutRatio)
				.add("failRatio", failRatio)
				.add("validRatio", validRatio)
				.toString();
	}
}
