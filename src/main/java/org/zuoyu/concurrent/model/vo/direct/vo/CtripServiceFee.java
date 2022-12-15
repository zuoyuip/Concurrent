package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huangpan
 * @ClassName: CtripServiceFee
 * @Description: TODO(携程直连询价【已弃用】服务费规定)
 * @date 2017年12月14日 下午5:27:23
 */
public class CtripServiceFee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8961698451114846580L;

    /**
     * 航司改签服务费
     */
    private BigDecimal revalidationFeeByCarrier;

    /**
     * 航司退票服务费
     */
    private BigDecimal refundFeeByCarrier;

    /**
     * 服务费币种，IATA标准币种编码，仅限人民币：CNY
     */
    private String currency;

    public BigDecimal getRevalidationFeeByCarrier() {
        return revalidationFeeByCarrier;
    }

    public void setRevalidationFeeByCarrier(BigDecimal revalidationFeeByCarrier) {
        this.revalidationFeeByCarrier = revalidationFeeByCarrier;
    }

    public BigDecimal getRefundFeeByCarrier() {
        return refundFeeByCarrier;
    }

    public void setRefundFeeByCarrier(BigDecimal refundFeeByCarrier) {
        this.refundFeeByCarrier = refundFeeByCarrier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
