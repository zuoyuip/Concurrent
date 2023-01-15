package org.zuoyu.concurrent.utils;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.NamedThreadFactory;

import org.springframework.lang.NonNull;

/**
 * @Description TODO 高并发工具
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/13 17:52
 * @Version 1.0
 */
@Slf4j
public class ConcurrencyUtil {

	/**
	 * CPU核心线程数量
	 */
	private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

	/**
	 * 根据服务器cpu自定义线程池
	 */
	private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
			AVAILABLE_PROCESSORS * 800,
			1600 * AVAILABLE_PROCESSORS,
			10,
			TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<>(AVAILABLE_PROCESSORS * 200),
			new NamedThreadFactory("CONCURRENT"),
			new ThreadPoolExecutor.DiscardOldestPolicy());

	/**
	 * 并发
	 * @param tasks - 任务集合
	 * @param permits - 并发数量
	 */
	public static boolean startTask(@NonNull final Set<Runnable> tasks, final int permits) throws InterruptedException {
		// 用于控制并发数（限制）
		final Semaphore semaphore = new Semaphore(permits);
		// 闭锁（聚集线程，释放以达到一起执行）
		final CountDownLatch countDownLatch = new CountDownLatch(tasks.size());
		tasks.forEach(task -> THREAD_POOL_EXECUTOR.execute(() -> {
			try {
				// 获取执行许可，否则线程阻塞等待，直到获取许可
				semaphore.acquire();
				// 执行逻辑
				task.run();
			}
			catch (InterruptedException ie) {
				log.info(ExceptionUtil.getRootCauseMessage(ie));
			}
			finally {
				// 释放许可
				semaphore.release();
				// 将闭锁减1，减到0时，就可以开启执行了
				countDownLatch.countDown();
			}
		}));

		// 线程阻塞，直到闭锁为0时，阻塞才会释放，达到一起执行
		countDownLatch.await();
		return true;
	}
}
