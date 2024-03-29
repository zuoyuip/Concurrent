package org.zuoyu.concurrent.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Sets;
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

	private static final Set<CtripSearchReq> EMPTY_CTRIP_SEARCH_REQS = Collections.emptySet();

	/**
	 * 航班时间格式
	 */
	private static final String FLIGHT_DATE_PATTERN = "yyyyMMdd";

	private static final String CID = "szjzkj_gj";

	/**
	 * 获取请求集合（根据笛卡尔积进行组合请求）
	 * @param gdsPolicySet - 政策集合
	 * @return 请求
	 */
	public static Set<CtripSearchReq> getCtripSearchReqSet(@NonNull Set<GdsPolicy> gdsPolicySet) {

		return gdsPolicySet.stream().map(gdsPolicy -> {
			Date flightDateStart = gdsPolicy.getGoFlightDateStart();
			Date flightDateEnd = gdsPolicy.getGoFlightDateEnd();
			DateTime dateTime = DateUtil.date().offset(DateField.DAY_OF_MONTH, 1);

			// 如果最晚日期不在明天之后，不测试此政策
			if (flightDateEnd.before(dateTime.toJdkDate())) {
				return EMPTY_CTRIP_SEARCH_REQS;
			}

			// 如果开始日期不在明天之后，则把测试范围往后移动
			if (flightDateStart.before(dateTime.toJdkDate())) {
				flightDateStart = dateTime.toJdkDate();
			}

			// 去程日期范围（这里需要调整日期为从当前日期起）
			DateRange goDateRange = DateUtil.range(flightDateStart, flightDateEnd, DateField.DAY_OF_MONTH);
			Set<DateTime> goFlightTimes = Sets.newHashSet(goDateRange.iterator());
			// 目前只询价单程

			// 根据去程范围例举单程
			return goFlightTimes.stream().map(goFlightTime -> {
				CtripSearchReq ctripSearchReq = ctripSearchReq(gdsPolicy);
				ctripSearchReq.setFromDate(goFlightTime.toString(FLIGHT_DATE_PATTERN));
				return ctripSearchReq;
			}).collect(Collectors.toSet());

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
		ctripSearchReq.setCid(CID);
		return ctripSearchReq;
	}
}
