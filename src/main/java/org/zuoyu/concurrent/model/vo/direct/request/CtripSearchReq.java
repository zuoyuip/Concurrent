package org.zuoyu.concurrent.model.vo.direct.request;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author huangpan
 * @ClassName: CtripSearchReq
 * @Description: TODO(携程直连询价请求实体)
 * @date 2017年12月13日 下午3:27:54
 */
public class CtripSearchReq extends CtripBaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = 2049310955467269729L;


    /**
     * 行程类型，1：单程；2：往返；3：多程（暂不支持）；
     */
    private String tripType;

    /**
     * 出发地城市 IATA 三字码代码 【注意请求的是城市不是机场】
     * （如果为多程，会按照 PEK/HKG，HKG/SHA 格式请求，第一程为北京->香港，第二程为香港->上海）
     */
    private String fromCity;

    /**
     * 目的地城市 IATA 三字码代码【注意请求的是城市不是机场】
     */
    private String toCity;

    /**
     * 出发日期，格式为 YYYYMMDD
     * （如果为多程，按照20130729,20130804的方式传输数据）
     */
    private String fromDate;

    /**
     * 回程日期，格式为 YYYYMMDD（留空表示单程/多程）
     */
    private String retDate;

    /**
     * 成人人数，1-9，供应商请按照实际请求出行人数返回相关运价数据【特别注意：查询请求是带人数的】
     */
    private Integer adultNumber;

    /**
     * 儿童人数，0-9（新上线供应商默认只开放成人，不开放多乘客类型）
     */
    private Integer childNumber;

    /**
     * 婴儿人数，0-9
     */
    private Integer infantNumber;

    /**
     * 搜索请求渠道来源：F：FlightIntlOnline；M：Mobile ; K:积分票(对于积分票的查询请求, 查询返回报文的productType务必赋值为JFP,否则过滤)
     * 1）若为F，要求在7S内返回结果；若为M，要求在5S内返回结果；
     * 2） 若查询超时，直接熔断。
     * 3）此字段非常重要，请务必注意
     */
    private String channel;
    /**
     * 子渠道号（Qunar不传）
     */
    private String subChannelID;
    /**
     * 主渠道，如FlightIntlOnline/ Mobile/ EnglishSite等（Qunar不传）
     */
    private String mainChannel;

    /**
     * 标识供应商查询返回报文是否需要压缩
     * 1) 默认不压缩；如果为T，压缩编码；
     * 2）若需要压缩，请联系我们处理。
     */
    private String isCompressEncode = "";

    /**
     * 区别请求数据源
     * 2 为本机报价检测数据源
     * 3 为本地直连测试报价
     */
    private int requestSource;

    /**
     * 0标识请求来源于Ctrip； 1标识请求来源于Qunar
     */
    private Integer source;

    public int getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(int requestSource) {
        this.requestSource = requestSource;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getRetDate() {
        return retDate;
    }

    public void setRetDate(String retDate) {
        this.retDate = retDate;
    }

    public Integer getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(Integer adultNumber) {
        this.adultNumber = adultNumber;
    }

    public Integer getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(Integer childNumber) {
        this.childNumber = childNumber;
    }

    public Integer getInfantNumber() {
        return infantNumber;
    }

    public void setInfantNumber(Integer infantNumber) {
        this.infantNumber = infantNumber;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIsCompressEncode() {
        return isCompressEncode;
    }

    public void setIsCompressEncode(String isCompressEncode) {
        this.isCompressEncode = isCompressEncode;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSubChannelID() {
        return subChannelID;
    }

    public void setSubChannelID(String subChannelID) {
        this.subChannelID = subChannelID;
    }

    public String getMainChannel() {
        return mainChannel;
    }

    public void setMainChannel(String mainChannel) {
        this.mainChannel = mainChannel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CtripSearchReq)) {
            return false;
        }

        CtripSearchReq that = (CtripSearchReq) o;

        return new EqualsBuilder().appendSuper(super.equals(o))
                .append(getRequestSource(), that.getRequestSource()).append(getTripType(), that.getTripType())
                .append(getFromCity(), that.getFromCity()).append(getToCity(), that.getToCity())
                .append(getFromDate(), that.getFromDate()).append(getRetDate(), that.getRetDate())
                .append(getAdultNumber(), that.getAdultNumber()).append(getChildNumber(), that.getChildNumber())
                .append(getInfantNumber(), that.getInfantNumber()).append(getChannel(), that.getChannel())
                .append(getSubChannelID(), that.getSubChannelID()).append(getMainChannel(), that.getMainChannel())
                .append(getIsCompressEncode(), that.getIsCompressEncode()).append(getSource(), that.getSource())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(getTripType()).append(getFromCity())
                .append(getToCity()).append(getFromDate()).append(getRetDate()).append(getAdultNumber())
                .append(getChildNumber()).append(getInfantNumber()).append(getChannel()).append(getSubChannelID())
                .append(getMainChannel()).append(getIsCompressEncode()).append(getRequestSource()).append(getSource())
                .toHashCode();
    }
}
