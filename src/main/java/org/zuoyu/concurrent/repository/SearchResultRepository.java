package org.zuoyu.concurrent.repository;

import org.zuoyu.concurrent.model.SearchResult;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO 查询结果仓库
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/11/30 14:48
 * @Version 1.0
 */
@Repository
public interface SearchResultRepository extends ElasticsearchRepository<SearchResult, String> {
}
