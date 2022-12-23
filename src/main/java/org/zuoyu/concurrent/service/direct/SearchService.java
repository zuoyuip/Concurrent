package org.zuoyu.concurrent.service.direct;

import java.util.Date;
import java.util.List;

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
	 * 询价
	 */
	void search();

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
