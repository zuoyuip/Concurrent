package org.zuoyu.concurrent.model.vo.direct.request;


import org.zuoyu.concurrent.model.vo.direct.vo.CtripRouting;

/**
 * @author huangpan
 * @ClassName: CtripVerifyReq
 * @Description: TODO(携程直连价格校验实体)
 * @date 2017年12月14日 上午9:45:58
 */
public class CtripVerifyReq extends CtripBaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = 2459805687007393096L;

    /**
     * 携程关联ID；携程用来查问题用的。
     */
    private String referenceId;

    /**
     * 行程类型，1：单程；2：往返；3：多程。
     */
    private String tripType;

    /**
     * 请求类型，全部为1：验价；
     */
    private String requesttype;

    /**
     * 成人人数，1-9
     */
    private Integer adultNumber;

    /**
     * 人童人数，0-9
     */
    private Integer childNumber;

    /**
     * 婴儿人数，0-9
     */
    private Integer infantNumber;

    /**
     * 报价信息，参考搜索返回结果中的 Routing Elements。
     * 1）只含航班信息,航班信息不含经停城市/机场，机型；
     * 2）不含价格信息、退改签信息、行李额信息等。
     */
    private CtripRouting routing;

    /**
     * 0标识请求来源于Ctrip； 1标识请求来源于Qunar
     */
    private Integer source;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
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

    public CtripRouting getRouting() {
        return routing;
    }

    public void setRouting(CtripRouting routing) {
        this.routing = routing;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
