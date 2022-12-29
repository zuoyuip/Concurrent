package org.zuoyu.concurrent.maker;

import java.util.concurrent.atomic.AtomicBoolean;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.util.Strings;
import org.zuoyu.concurrent.vo.Watcher;

import org.springframework.lang.NonNull;


/**
 * @Description TODO 数据控制
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/29 11:05
 * @Version 1.0
 */
public final class Switcher {

	/**
	 * 用于记录运行耗时
	 */
	private static final StopWatch STOP_WATCH = StopWatch.create();

	/**
	 * 用于控制询价服务开关
	 */
	private static final AtomicBoolean SEARCH_IS_RUN = new AtomicBoolean(false);

	/**
	 * 用于控制验价服务开关
	 */
	private static final AtomicBoolean VERIFY_IS_RUN = new AtomicBoolean(false);

	/**
	 * 用于记录上次启动时间
	 */
	private static volatile String lastStartTime = Strings.EMPTY;

	/**
	 * 用于标记是否启动过
	 */
	private static volatile boolean isStarted = false;

	/**
	 * 开始计时
	 */
	public static void startWatch() {
		if (STOP_WATCH.isStarted() || STOP_WATCH.isSuspended()) {
			STOP_WATCH.stop();
		}
		STOP_WATCH.reset();
		STOP_WATCH.start();
		long startTime = STOP_WATCH.getStartTime();
		isStarted = true;
		lastStartTime = SerializationUtils.clone(DateUtil.date(startTime).toString());
	}

	/**
	 * 停止计时
	 */
	public static void stopWatch() {
		STOP_WATCH.stop();
	}

	/**
	 * 查看当前计时信息
	 * @return 计时信息
	 */
	@NonNull
	public static Watcher watchInfo() {
		Watcher watcher = new Watcher();
		if (isStarted) {
			if (STOP_WATCH.isStarted()) {
				long startTime = STOP_WATCH.getStartTime();
				watcher.setStartTime(DateUtil.date(startTime).toString());
			}
			if (STOP_WATCH.isStopped()) {
				watcher.setStartTime(lastStartTime);
				long stopTime = STOP_WATCH.getStopTime();
				watcher.setEndTime(DateUtil.date(stopTime).toString());
			}
			long time = STOP_WATCH.getTime();
			watcher.setTime(DateUtil.formatBetween(time, BetweenFormatter.Level.SECOND));
			return watcher;
		}
		watcher.setStartTime("无启动记录");
		watcher.setEndTime("无启动记录");
		watcher.setTime("无启动记录");
		return watcher;
	}

	/**
	 * 开启询价服务
	 */
	public static void openSearchService() {
		SEARCH_IS_RUN.set(true);
		startWatch();
	}

	/**
	 * 关闭询价服务
	 */
	public static void closeSearchService() {
		SEARCH_IS_RUN.set(false);
		stopWatch();
	}

	/**
	 * 询价服务是否打开
	 */
	public static boolean isOpenSearchService() {
		return SEARCH_IS_RUN.get();
	}

	/**
	 * 开启验价服务
	 */
	public static void openVerifyService() {
		VERIFY_IS_RUN.set(true);
	}

	/**
	 * 关闭验价服务
	 */
	public static void closeVerifyService() {
		VERIFY_IS_RUN.set(false);
	}

	/**
	 * 验价服务是否打开
	 */
	public static boolean isOpenVerifyService() {
		return VERIFY_IS_RUN.get();
	}
}
