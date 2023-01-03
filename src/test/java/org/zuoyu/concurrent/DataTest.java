package org.zuoyu.concurrent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Sets;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.zuoyu.concurrent.maker.Switcher;

import org.springframework.lang.NonNull;

/**
 * @Description TODO 日期测试
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/14 14:59
 * @Version 1.0
 */
@Slf4j
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

	@Test
	void testRandomEleSet() {
		Set<Integer> integerSet = new HashSet<>(100);
		for (int i = 0; i < 100; i++) {
			integerSet.add(i);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtil.randomEleSet(integerSet, 10));
		}
	}

	@Test
	@SneakyThrows
	void countDownLatch() {
		final CountDownLatch countDownLatch = new CountDownLatch(2);

		Thread threadOne = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				log.info("{}：饭好了", Thread.currentThread().getName());
				countDownLatch.countDown();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "threadOne");

		Thread threadTwo = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				log.info("{}：菜好了", Thread.currentThread().getName());
				countDownLatch.countDown();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "threadTwo");

		threadOne.start();
		threadTwo.start();
		countDownLatch.await();
		log.info("{}：开始干饭", Thread.currentThread().getName());
	}

	@Test
	@SneakyThrows
	void semaphore() {
		final Semaphore semaphoreOne = new Semaphore(1);
		final Semaphore semaphoreTwo = new Semaphore(0);
		final int LOOP_NUM = 10;

		Thread threadOne = new Thread(() -> {
			for (int i = 0; i < LOOP_NUM; i++) {
				try {
					semaphoreOne.acquire();
					log.info("{}：threadOne", Thread.currentThread().getName());
					semaphoreTwo.release();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "threadOne");

		Thread threadTwo = new Thread(() -> {
			for (int i = 0; i < LOOP_NUM; i++) {
				try {
					semaphoreTwo.acquire();
					log.info("{}：threadTwo", Thread.currentThread().getName());
					semaphoreOne.release();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "threadTwo");

		threadOne.start();
		threadTwo.start();
	}

	@Test
	void cyclicBarrier() {
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> log.info("--------所有人已经就位--------"));

		Thread threadOne = new Thread(() -> eat(cyclicBarrier), "threadOne");

		Thread threadTwo = new Thread(() -> eat(cyclicBarrier), "threadTwo");

		threadOne.start();
		threadTwo.start();
		eat(cyclicBarrier);
	}

	@SneakyThrows
	void eat(@NonNull CyclicBarrier cyclicBarrier) {
		log.info("{}：人还没到齐", Thread.currentThread().getName());
		cyclicBarrier.await();
		log.info("{}：人到齐了", Thread.currentThread().getName());
	}
}
