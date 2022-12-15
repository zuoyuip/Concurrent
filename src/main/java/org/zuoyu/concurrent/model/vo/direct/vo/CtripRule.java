package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangpan
 * @ClassName: CtripRule
 * @Description: TODO(携程直连免费行李额信息、退改签信息实体)
 * @date 2017年12月13日 下午5:24:31
 */
public class CtripRule implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9095410664195293189L;

    /**
     * 行李额规定；参考下方Baggage Element
     * 1）查询及验价时，Baggage和FormatBaggage需同时返回，缺一不可
     * TODO 已启用属性
     */
    private List<CtripBaggage> baggageInfoList;

    /**
     * 格式化行李额规定；参考下方FormatBaggage Element
     * 1）查询及验价时，Baggage和FormatBaggage需同时返回，缺一不可
     */
    private List<CtripFormatBaggage> formatBaggageInfoList;

    /**
     * 退票规定；参考下面的Refund Element
     * 1）单程和往返格式不同；
     * 2）需要按照乘客类型分别赋值
     */
    private List<CtripRefund> refundInfoList;

    /**
     * 改期规定；参考下面的Changes Element
     */
    private List<CtripChanges> changesInfoList;

    /**
     * 【已弃用】服务费规定；参考下面CtripServiceFee Element
     */
    private CtripServiceFee serviceFee;

    /**
     * 备注信息，最大 300 个字符
     */
    private String note;

    /**
     * 是否要使用携程退改签：（true 使用；false 不使用）
     */
    private boolean isUseCtripRule;

    /**
     * 公布运价相关参数，地理区间见运价集群编码
     */
    private String tariffNo;

    /**
     * 公布运价相关参数
     */
    private String ruleNo;

    /**
     * 退改签匹配模式：（0准确匹配；1模糊匹配）
     */
    private Integer fareRuleMatchMode;

    public List<CtripFormatBaggage> getFormatBaggageInfoList() {
        return formatBaggageInfoList;
    }

    public void setFormatBaggageInfoList(List<CtripFormatBaggage> formatBaggageInfoList) {
        this.formatBaggageInfoList = formatBaggageInfoList;
    }

    public List<CtripRefund> getRefundInfoList() {
        return refundInfoList;
    }

    public void setRefundInfoList(List<CtripRefund> refundInfoList) {
        this.refundInfoList = refundInfoList;
    }

    public List<CtripChanges> getChangesInfoList() {
        return changesInfoList;
    }

    public void setChangesInfoList(List<CtripChanges> changesInfoList) {
        this.changesInfoList = changesInfoList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean getIsUseCtripRule() {
        return isUseCtripRule;
    }

    public void setIsUseCtripRule(boolean isUseCtripRule) {
        this.isUseCtripRule = isUseCtripRule;
    }

    public String getTariffNo() {
        return tariffNo;
    }

    public void setTariffNo(String tariffNo) {
        this.tariffNo = tariffNo;
    }

    public String getRuleNo() {
        return ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        this.ruleNo = ruleNo;
    }

    public Integer getFareRuleMatchMode() {
        return fareRuleMatchMode;
    }

    public void setFareRuleMatchMode(Integer fareRuleMatchMode) {
        this.fareRuleMatchMode = fareRuleMatchMode;
    }

    public List<CtripBaggage> getBaggageInfoList() {
        return baggageInfoList;
    }

    public void setBaggageInfoList(List<CtripBaggage> baggageInfoList) {
        this.baggageInfoList = baggageInfoList;
    }

    public CtripServiceFee getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(CtripServiceFee serviceFee) {
        this.serviceFee = serviceFee;
    }

}
