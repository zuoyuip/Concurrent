package org.zuoyu.concurrent.model.vo.direct.request;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * BaseRequest
 * TODO(携程直连公共请求参数)
 *
 * @author huangpan
 * @since 2017年12月14日 上午9:44:33
 */
public class CtripBaseRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 接口身份标识用户名（渠道唯一标识）
     */
    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CtripBaseRequest)) {
            return false;
        }

        CtripBaseRequest that = (CtripBaseRequest) o;

        return new EqualsBuilder().append(getCid(), that.getCid()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getCid()).toHashCode();
    }
}
