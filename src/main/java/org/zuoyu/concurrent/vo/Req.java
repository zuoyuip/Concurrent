package org.zuoyu.concurrent.vo;

import java.io.Serializable;

import cn.hutool.core.date.DateTime;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description TODO 请求包装
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/28 14:06
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Req implements Serializable {

	private static final long serialVersionUID = 3290236263880839377L;

	/**
	 * 起始时间
	 */
	private DateTime start;

	/**
	 * 结束时间
	 */
	private DateTime end;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Req)) {
			return false;
		}
		Req req = (Req) o;
		return Objects.equal(getStart(), req.getStart()) && Objects
				.equal(getEnd(), req.getEnd());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getStart(), getEnd());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("start", start)
				.add("end", end)
				.toString();
	}
}
