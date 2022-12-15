package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;

/**
 * @author huangpan
 * @ClassName: CtripAirlineAncillaries
 * @Description: TODO(携程直连询价增值服务信息实体)
 * @date 2017年12月13日 下午4:12:57
 */
public class CtripAirlineAncillaries implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7595670349059942479L;

    /**
     * 行李增值服务（true 包含/false不包含，默认false）
     */
    private Boolean baggageService = false;

    /**
     * 有无免费行李额（True为无免费行李额；默认False，包含免费行李额，或部分无免费行李额）
     */
    private Boolean unFreeBaggage = false;

    /**
     * 值机服务状态 0：不支持在线值机 / 1：支持在线值机 / 2：必须在线值机（此选项代表供应商打包值机价格到机票）
     */
    private Integer checkInServiceStatus;

    public Boolean isBaggageService() {
        return baggageService;
    }

    public void setBaggageService(Boolean baggageService) {
        this.baggageService = baggageService;
    }

    public Boolean isUnFreeBaggage() {
        return unFreeBaggage;
    }

    public void setUnFreeBaggage(Boolean unFreeBaggage) {
        this.unFreeBaggage = unFreeBaggage;
    }

    public Integer getCheckInServiceStatus() {
        return checkInServiceStatus;
    }

    public void setCheckInServiceStatus(Integer checkInServiceStatus) {
        this.checkInServiceStatus = checkInServiceStatus;
    }

}
