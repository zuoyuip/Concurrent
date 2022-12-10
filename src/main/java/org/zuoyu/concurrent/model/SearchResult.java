package org.zuoyu.concurrent.model;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description TODO 查询结果统计
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 14:45
 * @Version 1.0
 */
@Document(indexName = "search_result")
public class SearchResult extends Result{

	private static final long serialVersionUID = -1155143730228105657L;
}
