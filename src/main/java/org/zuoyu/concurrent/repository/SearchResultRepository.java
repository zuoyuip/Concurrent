package org.zuoyu.concurrent.repository;

import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO 查询结果存储仓库
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/10 14:48
 * @Version 1.0
 */
@Repository
public interface SearchResultRepository extends ElasticsearchRepository<SearchResult, String> {
}
