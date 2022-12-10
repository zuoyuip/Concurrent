package org.zuoyu.concurrent.model.base;

import java.io.Serializable;

import lombok.Data;

import org.springframework.data.annotation.Id;

/**
 * @Description TODO 用于Elasticsearch持久化的基础Entity
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/7/20 10:11
 * @Version 1.0
 */
@Data
public class ElasticsearchBaseEntity<ID> implements Serializable {

    private static final long serialVersionUID = -2202360546313412422L;
    /**
     * 统一ID字段
     */
    @Id
    private ID id;

}
