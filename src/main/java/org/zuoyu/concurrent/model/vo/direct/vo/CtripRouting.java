package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangpan
 * @ClassName: CtripRouting
 * @Description: TODO(携程直连报价信息实体)
 * @date 2017年12月13日 下午4:10:38
 */
public class CtripRouting implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1770412422012631461L;

    /**
     * 可保存必要信息，验价时会放在请求报文中传给供应商；
     * 最大 1000 个字符 。
     */
    private String data;

    /**
     * 【公布运价强校验】成人公布价（以CNY为单位），不含税【文档所有跟价格、税费相关的参数，都是以人民币为单位】
     */
    private Integer publishPrice;

    /**
     * 成人单价，不含税
     */
    private Integer adultPrice;

    /**
     * 成人税费【注意不能是0，若存在为0的情况，请与我们联系】
     */
    private Integer adultTax;

    /**
     * 儿童公布价，不含税
     */
    private Integer childPublishPrice;

    /**
     * 儿童单价，不含税【对于含儿童的查询，必须返回】
     */
    private Integer childPrice;

    /**
     * 儿童税费
     * 1）对于含儿童的查询，必须返回；
     * 2）不能是0，若存在为0的情况，请与我们联系。
     */
    private Integer childTax;

    /**
     * 婴儿公布价
     */
    private Integer infantPublishPrice;

    /**
     * 婴儿单价，不含税【对于含婴儿的查询，必须返回】
     */
    private Integer infantPrice;

    /**
     * 婴儿税费
     * 1）对于含婴儿的查询，必须返回；
     * 2）可以为0。
     */
    private Integer infantTax;

    /**
     * 成人税费类型：0 未含税 / 1 已含税 【正常赋0，如赋1请提前告知】
     */
    private Integer adultTaxType;

    /**
     * 儿童税费类型：0 未含税 / 1 已含税 【正常赋0，如赋1请提前告知】
     */
    private Integer childTaxType;

    /**
     * 报价类型：0 普通价 / 1 留学生价 【请全部赋0】
     */
    private Integer priceType = 0;

    /**
     * 报价类型：0 预定价 / 1 申请价 【请全部赋0】
     */
    private Integer applyType = 0;

    /**
     * 【公布运价强校验】汇率
     */
    private BigDecimal exchange;

    /**
     * 适用年龄区间【如要使用此字段请提前通知我们，盲目使用会影响价格展示】
     * 1）使用“-”表示“至”，例如*-12，表示12岁及以下；
     * 2）置空表示无限制
     */
    private String adultAgeRestriction;

    /**
     * 【公布运价强校验】
     * 1）旅客资质，标准三字码：
     * NOR：普通成人
     * LAB：劳务人员
     * SEA：海员
     * SNR：老年人
     * STU：学生
     * YOU：青年
     * 2）如果投放非NOR的政策，请提前告知我们。
     */
    private String eligibility;

    /**
     * 允许国籍，使用标准国家二字码
     * 【如要使用此字段请提前通知我们，盲目使用会影响价格展示】
     * 1）置空表示无限制（一般都是置空的）；
     * 2）若多个使用“/”分割；
     * 3）与forbiddenNationality只能2选1，若同时出现，为非法数据，将被过滤。
     */
    private String nationality;

    /**
     * 禁用国籍，使用标准国家二字码
     * 【如要使用此字段请提前通知我们，盲目使用会影响价格展示】
     * 1）置空表示无限制（一般都是置空的）；
     * 2）若多个使用“/”分割；
     * 3）与nationality只能2选1，若同时出现，为非法数据，将被过滤。
     */
    private String forbiddenNationality;

    /**
     * 【公布运价强校验】
     * 产品类型：0 旅行套餐 /1 商务优选/2 特惠推荐
     * 新上线供应商请赋值为0
     */
    private Integer planCategory;

    /**
     * 报销凭证：T 行程单 / F 发票 / E 电子发票
     * 默认发票；廉航票台可赋值为E
     */
    private String invoiceType;

    /**
     * 最短停留时间【单位是天】【如要使用此字段请提前通知我们，盲目使用会影响价格展示】
     */
    private String minStay;

    /**
     * 最长停留时间【单位是天】【如要使用此字段请提前通知我们，盲目使用会影响价格展示】
     */
    private String maxStay;

    /**
     * 最小出行人数【无返回，默认为1】
     */
    private Integer minPassengerCount;

    /**
     * 最大出行人数【无返回，默认为9】
     */
    private Integer maxPassengerCount;

    /**
     * 订位Office号【可为空】
     */
    private String bookingOfficeNo;

    /**
     * 出票Office号【可为空】
     */
    private String ticketingOfficeNo;

    /**
     * 【公布运价强校验】出票航司
     * 1）整个行程只能赋一个航司；
     * 2）如不赋值会取行程第一航段的carrier作为出票航司；
     * 3）此字段非常重要，请务必准确赋值。
     */
    private String validatingCarrier;

    /**
     * 出票地国家2字码，多个出票地用‘|’隔开
     *
     * @deprecated 添加时间 2018年12月10日17:56:08
     */
    private String posCode;

    /**
     * 【公布运价强校验】政策来源
     * 1）非公布运价此字段可不赋值；
     * 2）公布运价此字段必须按要求返回，否则产品将按照未知订座系统，输出到旅行套餐；
     * 3）使用IATA标准2字代码
     * 	1E：TravelSky
     * 	1A：Amadeus
     * 	1B：Abacus
     * 	1S：Sabre
     * 	1P：WorldSpan
     * 	1G：Galileo
     * OT：未知订座系统来源
     */
    private String reservationType;

    /**
     * 【公布运价强校验】运价类型
     * 1）公布运价请赋值为：PUB：公布运价；
     * 2）控位产品请务必赋值为：KWP
     * 3 ) 积分票产品请务必赋值为：JFP(只有在积分票查询请求时,返回积分票产品才有意义)
     */
    private String productType;

    /**
     * 【公布运价强校验】
     * 每航段1个，使用“ ; ”分割
     */
    private String fareBasis;

    /**
     * 增值服务信息；参考 AirlineAncillaries Element
     */
    private CtripAirlineAncillaries airlineAncillaries;

    /**
     * 去程航段按顺序；参考下面的 Segment Element
     * 多程行程信息全部输出到此节点。
     */
    private List<CtripSegment> fromSegments;

    /**
     * 回程航段按顺序；参考下面的 Segment Element（单程搜索为空）
     */
    private List<CtripSegment> retSegments;

    /**
     * 免费行李额信息、退改签信息；参考 Rule Element
     */
    private CtripRule rule;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getPublishPrice() {
        return publishPrice;
    }

    public void setPublishPrice(Integer publishPrice) {
        this.publishPrice = publishPrice;
    }

    public Integer getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Integer adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Integer getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(Integer adultTax) {
        this.adultTax = adultTax;
    }

    public Integer getChildPublishPrice() {
        return childPublishPrice;
    }

    public void setChildPublishPrice(Integer childPublishPrice) {
        this.childPublishPrice = childPublishPrice;
    }

    public Integer getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Integer childPrice) {
        this.childPrice = childPrice;
    }

    public Integer getChildTax() {
        return childTax;
    }

    public void setChildTax(Integer childTax) {
        this.childTax = childTax;
    }

    public Integer getInfantPublishPrice() {
        return infantPublishPrice;
    }

    public void setInfantPublishPrice(Integer infantPublishPrice) {
        this.infantPublishPrice = infantPublishPrice;
    }

    public Integer getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(Integer infantPrice) {
        this.infantPrice = infantPrice;
    }

    public Integer getInfantTax() {
        return infantTax;
    }

    public void setInfantTax(Integer infantTax) {
        this.infantTax = infantTax;
    }

    public Integer getAdultTaxType() {
        return adultTaxType;
    }

    public void setAdultTaxType(Integer adultTaxType) {
        this.adultTaxType = adultTaxType;
    }

    public Integer getChildTaxType() {
        return childTaxType;
    }

    public void setChildTaxType(Integer childTaxType) {
        this.childTaxType = childTaxType;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

    public String getAdultAgeRestriction() {
        return adultAgeRestriction;
    }

    public void setAdultAgeRestriction(String adultAgeRestriction) {
        this.adultAgeRestriction = adultAgeRestriction;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getForbiddenNationality() {
        return forbiddenNationality;
    }

    public void setForbiddenNationality(String forbiddenNationality) {
        this.forbiddenNationality = forbiddenNationality;
    }

    public Integer getPlanCategory() {
        return planCategory;
    }

    public void setPlanCategory(Integer planCategory) {
        this.planCategory = planCategory;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getMinStay() {
        return minStay;
    }

    public void setMinStay(String minStay) {
        this.minStay = minStay;
    }

    public String getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(String maxStay) {
        this.maxStay = maxStay;
    }

    public Integer getMinPassengerCount() {
        return minPassengerCount;
    }

    public void setMinPassengerCount(Integer minPassengerCount) {
        this.minPassengerCount = minPassengerCount;
    }

    public Integer getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(Integer maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public String getBookingOfficeNo() {
        return bookingOfficeNo;
    }

    public void setBookingOfficeNo(String bookingOfficeNo) {
        this.bookingOfficeNo = bookingOfficeNo;
    }

    public String getTicketingOfficeNo() {
        return ticketingOfficeNo;
    }

    public void setTicketingOfficeNo(String ticketingOfficeNo) {
        this.ticketingOfficeNo = ticketingOfficeNo;
    }

    public String getValidatingCarrier() {
        return validatingCarrier;
    }

    public void setValidatingCarrier(String validatingCarrier) {
        this.validatingCarrier = validatingCarrier;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public CtripAirlineAncillaries getAirlineAncillaries() {
        return airlineAncillaries;
    }

    public void setAirlineAncillaries(CtripAirlineAncillaries airlineAncillaries) {
        this.airlineAncillaries = airlineAncillaries;
    }

    public List<CtripSegment> getFromSegments() {
        return fromSegments;
    }

    public void setFromSegments(List<CtripSegment> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public List<CtripSegment> getRetSegments() {
        return retSegments;
    }

    public void setRetSegments(List<CtripSegment> retSegments) {
        this.retSegments = retSegments;
    }

    public CtripRule getRule() {
        return rule;
    }

    public void setRule(CtripRule rule) {
        this.rule = rule;
    }

}
