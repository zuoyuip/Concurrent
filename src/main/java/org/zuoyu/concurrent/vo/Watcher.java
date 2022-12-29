package org.zuoyu.concurrent.vo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description TODO 监控信息
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/29 11:47
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Watcher {

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 耗时
	 */
	private String time;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Watcher)) {
			return false;
		}
		Watcher watcher = (Watcher) o;
		return Objects
				.equal(getStartTime(), watcher.getStartTime()) && Objects
				.equal(getEndTime(), watcher.getEndTime()) && Objects
				.equal(getTime(), watcher.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getStartTime(), getEndTime(), getTime());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("startTime", startTime)
				.add("endTime", endTime)
				.add("time", time)
				.toString();
	}
}
