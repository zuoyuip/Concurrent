package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author huangpan
 * @ClassName: CtripChanges
 * @Description: TODO(携程直连改期规定实体)
 * @date 2017年12月13日 下午5:34:04
 */
public class CtripChanges implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4676722554322613070L;

    /**
     * 改期类型
     * 0：客票全部未使用；
     * 1：客票部分使用【即去程已使用，在往返行程中使用，代表回程的退票信息】
     * 【单程时0；往返时0、1都要有】
     */
    private Integer changesType;

    /**
     * 改期标识
     * T：不可改期
     * H：有条件改期
     * F：免费改期
     * E：按航司客规【公布运价专用】
     */
    private String changesStatus;

    /**
     * 改期费
     * 1）当changesStatus =H,必须赋值；
     * 2）若changesStatus =T/F,此字段可不赋值。
     */
    private BigDecimal changesFee;

    /**
     * 改期费币种
     * 当refundStatus =H，必须赋值。
     */
    private String currency;

    /**
     * 乘客类型，0 成人/1 儿童/2 婴儿
     * 1）对于对乘客类型的查询、验价，必须按乘客类型返回；如成人+儿童的查询，成人和儿童的退改签都要有。
     */
    private Integer passengerType;

    /**
     * 是否允许NoShow改期
     * T：不可改； H：有条件改；F：免费改；E：按航司客规为准【公布运价专用】
     */
    private String revNoshow;

    /**
     * 改期时航班起飞前多久算NoShow，单位：小时
     * 1）若无法确认此时间，请默认赋0。
     */
    private Integer revNoShowCondition;

    /**
     * NoShow改期费用
     * 1）当revNoshow =H，必须赋值；
     * 2）展示给客人的noshow改期费= changesFee + revNoshowFee。
     */
    private BigDecimal revNoshowFee;

    /**
     * 中文改期备注
     */
    private String cnRevRemark;

    /**
     * 英文改期备注
     */
    private String enRevRemark;

    public Integer getChangesType() {
        return changesType;
    }

    public void setChangesType(Integer changesType) {
        this.changesType = changesType;
    }

    public String getChangesStatus() {
        return changesStatus;
    }

    public void setChangesStatus(String changesStatus) {
        this.changesStatus = changesStatus;
    }

    public BigDecimal getChangesFee() {
        return changesFee;
    }

    public void setChangesFee(BigDecimal changesFee) {
        this.changesFee = changesFee;
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

    public String getRevNoshow() {
        return revNoshow;
    }

    public void setRevNoshow(String revNoshow) {
        this.revNoshow = revNoshow;
    }

    public Integer getRevNoShowCondition() {
        return revNoShowCondition;
    }

    public void setRevNoShowCondition(Integer revNoShowCondition) {
        this.revNoShowCondition = revNoShowCondition;
    }

    public BigDecimal getRevNoshowFee() {
        return revNoshowFee;
    }

    public void setRevNoshowFee(BigDecimal revNoshowFee) {
        this.revNoshowFee = revNoshowFee;
    }

    public String getCnRevRemark() {
        return cnRevRemark;
    }

    public void setCnRevRemark(String cnRevRemark) {
        this.cnRevRemark = cnRevRemark;
    }

    public String getEnRevRemark() {
        return enRevRemark;
    }

    public void setEnRevRemark(String enRevRemark) {
        this.enRevRemark = enRevRemark;
    }

}
