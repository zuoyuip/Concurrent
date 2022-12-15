package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huangpan
 * @ClassName: CtripRefund
 * @Description: TODO(携程直连退票规定实体)
 * @date 2017年12月13日 下午5:29:40
 */
public class CtripRefund implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6902032304439261303L;

    /**
     * 退票类型
     * 0：客票全部未使用；
     * 1：客票部分使用【即去程已使用，在往返行程中使用，代表回程的退票信息】
     * 【单程时0；往返时0、1都要有】
     */
    private Integer refundType;

    /**
     * 退票标识
     * T：不可退
     * H：有条件退
     * F：免费退
     * E：按航司客规【公布运价专用】
     */
    private String refundStatus;

    /**
     * 退票费
     * 1）当refundStatus =H,必须赋值；
     * 2）若refundStatus =T/F,此字段可不赋值。
     */
    private BigDecimal refundFee;

    /**
     * 退票费币种
     * 当refundStatus =H，必须赋值。
     */
    private String currency;

    /**
     * 乘客类型，0 成人/1 儿童/2 婴儿
     * 1）对于多乘客类型的查询、验价，必须按乘客类型返回；如成人+儿童的查询，成人和儿童的退改签都要有。
     */
    private Integer passengerType;

    /**
     * 是否允许NoShow退票
     * T：不可退； H：有条件退；F：免费退；E：按航司客规为准【公布运价专用】
     */
    private String refNoshow;

    /**
     * 退票时航班起飞前多久算NoShow，单位：小时
     * 1）若无法确认此时间，请默认赋0。
     */
    private Integer refNoShowCondition;

    /**
     * NoShow退票费用
     * 1）当IsRefNoshow =H，必须赋值；
     * 2）展示给客人的noshow退票费= refundFee+ refNoshowFee。
     */
    private BigDecimal refNoshowFee;

    /**
     * 中文退票备注
     */
    private String cnRefRemark;

    /**
     * 英文退票备注
     */
    private String enRefRemark;

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(Integer passengerType) {
        this.passengerType = passengerType;
    }

    public String getRefNoshow() {
        return refNoshow;
    }

    public void setRefNoshow(String refNoshow) {
        this.refNoshow = refNoshow;
    }

    public Integer getRefNoShowCondition() {
        return refNoShowCondition;
    }

    public void setRefNoShowCondition(Integer refNoShowCondition) {
        this.refNoShowCondition = refNoShowCondition;
    }

    public BigDecimal getRefNoshowFee() {
        return refNoshowFee;
    }

    public void setRefNoshowFee(BigDecimal refNoshowFee) {
        this.refNoshowFee = refNoshowFee;
    }

    public String getCnRefRemark() {
        return cnRefRemark;
    }

    public void setCnRefRemark(String cnRefRemark) {
        this.cnRefRemark = cnRefRemark;
    }

    public String getEnRefRemark() {
        return enRefRemark;
    }

    public void setEnRefRemark(String enRefRemark) {
        this.enRefRemark = enRefRemark;
    }


}
