package org.zuoyu.concurrent.model.base;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;

/**
 * @Description TODO 用于Elasticsearch持久化的基础Entity
 * @Author z
 * @Email zuoyuip@foxmail.com
 * @Date 2022/7/20 10:11
 * @Version 1.0
 */
@Getter
@Setter
public class ElasticsearchBaseEntity<ID> implements Serializable {

    private static final long serialVersionUID = -2202360546313412422L;
    /**
     * 统一ID字段
     */
    @Id
    private ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElasticsearchBaseEntity)) {
            return false;
        }
        ElasticsearchBaseEntity<?> that = (ElasticsearchBaseEntity<?>) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
