package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;

/**
 * @author huangpan
 * @ClassName: CtripSegment
 * @Description: TODO(携程直连航段信息实体)
 * @date 2017年12月13日 下午5:38:56
 */
public class CtripSegment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4013153909287056271L;

    /**
     * 航司 IATA 二字码，必须与 flightNumber 航司相同
     */
    private String carrier;

    /**
     * 航班号，如：CA123。
     * 航班号数字前若有多余的数字 0，必须去掉；如 CZ006 需返回 CZ6
     */
    private String flightNumber;

    /**
     * 出发机场；IATA 三字码
     */
    private String depAirport;

    /**
     * 出发航站楼；使用简写，例如T1
     */
    private String depTerminal;

    /**
     * 起飞日期时间，格式：YYYYMMDDHHMM  例：201203100300 表示 2012 年 3 月 10 日 3 时 0 分
     */
    private String depTime;

    /**
     * 到达机场 IATA 三字码
     */
    private String arrAirport;

    /**
     * 抵达航站楼，使用简写，例如T1
     */
    private String arrTerminal;

    /**
     * 到达日期时间，格式：YYYYMMDDHHMM  例：201203101305 表示 2012 年 3 月 10 日 13 时 5 分
     */
    private String arrTime;

    /**
     * 经停城市； IATA 三字码
     */
    private String stopCities;

    /**
     * 经停机场； IATA 三字码
     */
    private String stopAirports;

    /**
     * 代码共享标识（true 代码共享/false 非代码共享）
     */
    private boolean codeShare;

    /**
     * 实际承运航司
     */
    private String operatingCarrier;

    /**
     * 不能为空。
     * 实际承运航班号
     * 若codeShare=true， operatingCarrier
     */
    private String operatingFlightNo;

    /**
     * 子舱位
     */
    private String cabin;

    /**
     * 剩余舱位数量
     */
    private Integer cabinCount;

    /**
     * 舱等；头等：F；商务：C；超经：S；经济：Y【目前仅支持返回Y】
     * 支持超级经济舱投放  2019年5月22日17:44:46
     */
    private String cabinGrade;

    /**
     * 机型 ，IATA标准3字码,并过滤下列机型运价信息BUS|ICE|LCH|LMO|MTL|RFS|TGV|THS|THT|TRN|TSL|
     */
    private String aircraftCode;

    /**
     * 飞行时长；
     * 单位为分钟，通过时差转换后的结果。
     */
    private Integer duration;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getDepTerminal() {
        return depTerminal;
    }

    public void setDepTerminal(String depTerminal) {
        this.depTerminal = depTerminal;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public String getArrTerminal() {
        return arrTerminal;
    }

    public void setArrTerminal(String arrTerminal) {
        this.arrTerminal = arrTerminal;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getStopCities() {
        return stopCities;
    }

    public void setStopCities(String stopCities) {
        this.stopCities = stopCities;
    }

    public String getStopAirports() {
        return stopAirports;
    }

    public void setStopAirports(String stopAirports) {
        this.stopAirports = stopAirports;
    }

    public boolean isCodeShare() {
        return codeShare;
    }

    public void setCodeShare(boolean codeShare) {
        this.codeShare = codeShare;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getOperatingFlightNo() {
        return operatingFlightNo;
    }

    public void setOperatingFlightNo(String operatingFlightNo) {
        this.operatingFlightNo = operatingFlightNo;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Integer getCabinCount() {
        return cabinCount;
    }

    public void setCabinCount(Integer cabinCount) {
        this.cabinCount = cabinCount;
    }

    public String getCabinGrade() {
        return cabinGrade;
    }

    public void setCabinGrade(String cabinGrade) {
        this.cabinGrade = cabinGrade;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
