package org.zuoyu.concurrent.service.direct;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.util.NamedThreadFactory;
import org.elasticsearch.search.aggregations.metrics.Stats;
import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.lang.NonNull;

/**
 * @Description TODO 查询操作
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/13 18:41
 * @Version 1.0
 */
public interface SearchService {

	/**
	 * CPU核心线程数量
	 */
	int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

	/**
	 * 存储ES的线程
	 */
	ThreadPoolExecutor ELASTICSEARCH_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
			AVAILABLE_PROCESSORS * 800,
			1600 * AVAILABLE_PROCESSORS,
			10,
			TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<>(AVAILABLE_PROCESSORS * 200),
			new NamedThreadFactory("ELASTICSEARCH"),
			new ThreadPoolExecutor.DiscardOldestPolicy());

	/**
	 * 执行验价的线程
	 */
	ThreadPoolExecutor VERIFY_THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
			AVAILABLE_PROCESSORS * 400,
			800 * AVAILABLE_PROCESSORS,
			10,
			TimeUnit.MILLISECONDS,
			new ArrayBlockingQueue<>(AVAILABLE_PROCESSORS * 100),
			new NamedThreadFactory("VERIFY"),
			new ThreadPoolExecutor.DiscardOldestPolicy());


	/**
	 * 启动询价服务
	 */
	void runSearchService();

	/**
	 * 停止询价服务
	 */
	void stopSearchService();

	/**
	 * 检索询价结果
	 * @param start - 起始时间
	 * @param end - 结束时间
	 * @return 询价结果集合
	 */
	List<SearchResult> searchResults(Date start, Date end);

	/**
	 * 关于超时信息的数据
	 * @param start - 起始时间
	 * @param end - 结束时间
	 * @return 聚合结果
	 */
	Stats timeOutQuery(@NonNull Date start, @NonNull Date end);
}
