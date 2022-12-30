package org.zuoyu.concurrent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.maker.Switcher;

/**
 * @Description TODO 日期测试
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/14 14:59
 * @Version 1.0
 */
public class DataTest {

	@Test
	void roundDate() {
		DateTime dateTime = DateUtil.parse("2022/10/1");
		DateTime dateTime1 = DateUtil.parse("2022/12/12");
		DateRange dateRange1 = DateUtil.range(dateTime, dateTime1, DateField.DAY_OF_MONTH);

		DateTime dateTime2 = DateUtil.parse("2021/10/1");
		DateTime dateTime3 = DateUtil.parse("2021/12/12");
		DateRange dateRange2 = DateUtil.range(dateTime2, dateTime3, DateField.DAY_OF_MONTH);

		Set<List<DateTime>> lists = Sets
				.cartesianProduct(Sets.newHashSet(dateRange1.iterator()), Sets.newHashSet(dateRange2.iterator()));

		lists.forEach(System.out::println);
	}

	@SneakyThrows
	@Test
	void stopWatch() {
		Switcher.startWatch();
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Switcher.watchInfo());


		TimeUnit.SECONDS.sleep(1);
		System.out.println("-------------------");
		System.out.println(Switcher.watchInfo());

		System.out.println("-----------------------------------------------");

		Switcher.startWatch();
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Switcher.watchInfo());
		Switcher.stopWatch();

		TimeUnit.SECONDS.sleep(1);
		System.out.println("-------------------");
		System.out.println(Switcher.watchInfo());
	}

	boolean service() {
		System.out.println("---------service----------");
		return true;
	}

	@Test
	void testWhile() {
		System.out.println(DateUtil.parse("2022-12-29 18:00:18").getTime());
	}
}
