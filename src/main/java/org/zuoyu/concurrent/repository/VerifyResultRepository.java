package org.zuoyu.concurrent.repository;

import org.zuoyu.concurrent.model.VerifyResult;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO 验价结果存储仓库
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/12/12 10:51
 * @Version 1.0
 */
@Repository
public interface VerifyResultRepository extends ElasticsearchRepository<VerifyResult, String> {
}
