package org.zuoyu.concurrent;

import org.zuoyu.concurrent.model.SearchAggregation;
import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @Description TODO 启动执行
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/23 15:38
 * @Version 1.0
 */
@Component
@Order(value = 0)
public class Runner implements CommandLineRunner {

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	public Runner(@NonNull ElasticsearchRestTemplate elasticsearchRestTemplate) {
		this.elasticsearchRestTemplate = elasticsearchRestTemplate;
	}

	/**
	 * 添加索引.
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
		// 询价结果
		IndexOperations searchResultIndexOperations = elasticsearchRestTemplate.indexOps(SearchResult.class);
		if (!searchResultIndexOperations.exists()) {
			searchResultIndexOperations.create();
			searchResultIndexOperations.putMapping();
		}

		// 询价聚合
		IndexOperations searchAggregationIndexOperations = elasticsearchRestTemplate.indexOps(SearchAggregation.class);
		if (!searchAggregationIndexOperations.exists()) {
			searchAggregationIndexOperations.create();
			searchAggregationIndexOperations.putMapping();
		}
	}
}
