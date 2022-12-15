package org.zuoyu.concurrent.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Sets;
import org.zuoyu.concurrent.enmus.TripTypeEnum;
import org.zuoyu.concurrent.model.vo.GdsPolicy;
import org.zuoyu.concurrent.model.vo.direct.request.CtripSearchReq;

import org.springframework.lang.NonNull;

/**
 * @Description TODO 请求构建工具
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/14 17:20
 * @Version 1.0
 */
public final class ReqBuilderUtil {

	/**
	 * 航班时间格式
	 */
	private static final String FLIGHT_DATE_PATTERN = "yyyyMMddHHmm";

	/**
	 * 获取请求集合
	 * @param gdsPolicySet - 政策集合
	 * @return 请求
	 */
	public static Set<CtripSearchReq> getCtripSearchReqSet(@NonNull Set<GdsPolicy> gdsPolicySet) {
		return gdsPolicySet.stream().map(gdsPolicy -> {
			// 去程日期范围
			DateRange goDateRange = DateUtil.range(gdsPolicy.getGoFlightDateStart(), gdsPolicy
					.getGoFlightDateEnd(), DateField.DAY_OF_MONTH);
			Set<DateTime> goFlightTimes = Sets.newHashSet(goDateRange.iterator());
			// 行程类型
			Integer tripType = gdsPolicy.getTripType();
			if (TripTypeEnum.ROUND.getValue().equals(tripType)) {
				// 返程日期范围
				DateRange retDateRange = DateUtil.range(gdsPolicy.getRetFlightDateStart(), gdsPolicy
						.getRetFlightDateEnd(), DateField.DAY_OF_MONTH);
				Set<DateTime> retFlightTimes = Sets.newHashSet(retDateRange.iterator());
				Set<List<DateTime>> cartesianSet = Sets.cartesianProduct(goFlightTimes, retFlightTimes);
				// 根据笛卡尔积组合往返类型
				return cartesianSet.stream().map(cartesian -> {
					CtripSearchReq ctripSearchReq = ctripSearchReq(gdsPolicy);
					ctripSearchReq.setFromDate(cartesian.get(0).toString(FLIGHT_DATE_PATTERN));
					ctripSearchReq.setRetDate(cartesian.get(1).toString(FLIGHT_DATE_PATTERN));
					return ctripSearchReq;
				}).collect(Collectors.toSet());
			}
			else {
				// 根据去程范围例举单程
				return goFlightTimes.stream().map(goFlightTime -> {
					CtripSearchReq ctripSearchReq = ctripSearchReq(gdsPolicy);
					ctripSearchReq.setFromDate(goFlightTime.toString(FLIGHT_DATE_PATTERN));
					return ctripSearchReq;
				}).collect(Collectors.toSet());
			}

		}).flatMap(Collection::stream).collect(Collectors.toSet());
	}

	@NonNull
	private static CtripSearchReq ctripSearchReq(@NonNull GdsPolicy gdsPolicy) {
		CtripSearchReq ctripSearchReq = new CtripSearchReq();

		Integer tripType = gdsPolicy.getTripType();
		ctripSearchReq.setTripType(tripType.toString());
		ctripSearchReq.setFromCity(gdsPolicy.getOrg());
		ctripSearchReq.setToCity(gdsPolicy.getDes());
		ctripSearchReq.setAdultNumber(1);
		ctripSearchReq.setChildNumber(1);
		ctripSearchReq.setChannel(gdsPolicy.getChannel());
		ctripSearchReq.setRequestSource(3);
		return ctripSearchReq;
	}
}
