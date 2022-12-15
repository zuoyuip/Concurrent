package org.zuoyu.concurrent.model.vo.direct.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created with IDEA
 * author:wwj
 * Date: Create in2021/1/4 11:42
 */
public class CtripQDirectVo {

    public static class CtripQSearchResponse implements Serializable {
        private static final long serialVersionUID = -497792803927579017L;
        /**
         * 返回状态
         * 0，接口响应成功；
         * 3，其他失败原因；
         * 4，ctrip请求参数错误；
         * 5，程序异常；
         * -1，网络异常(ctrip使用)
         * -2，response数据异常（ctrip使用）
         * 【注】无航线政策数据时请返回status = 0，shoppingResultList和flightList空即可；status不为0都将计入查询接口响应失败
         */
        private Integer status;
        /**
         * 提示信息，长度小于 64
         */
        private String msg;

        private List<ShoppingResult> shoppingResultList;
        /**
         * 航班信息通过索引号对应flightRefList，可复用。即同一航班可用于不同的报价组合
         */
        private List<Flight> flightList;
        /**
         * 自定义运价缓存时长，单位：秒
         * 自定义缓存时长上限为3600秒，下限为10秒，不赋值时使用默认缓存时长
         */
        private Integer cacheTime = 180;

        public CtripQSearchResponse() {
        }

        public CtripQSearchResponse(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<ShoppingResult> getShoppingResultList() {
            return shoppingResultList;
        }

        public void setShoppingResultList(List<ShoppingResult> shoppingResultList) {
            this.shoppingResultList = shoppingResultList;
        }

        public List<Flight> getFlightList() {
            return flightList;
        }

        public void setFlightList(List<Flight> flightList) {
            this.flightList = flightList;
        }

        public Integer getCacheTime() {
            return cacheTime;
        }

        public void setCacheTime(Integer cacheTime) {
            this.cacheTime = cacheTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof CtripQSearchResponse)) {
                return false;
            }

            CtripQSearchResponse that = (CtripQSearchResponse) o;

            return new EqualsBuilder().append(getStatus(), that.getStatus())
                    .append(getMsg(), that.getMsg()).append(getShoppingResultList(), that.getShoppingResultList())
                    .append(getFlightList(), that.getFlightList()).append(getCacheTime(), that.getCacheTime())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getStatus()).append(getMsg()).append(getShoppingResultList())
                    .append(getFlightList()).append(getCacheTime()).toHashCode();
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("status", status)
                    .add("msg", msg)
                    .add("shoppingResultList", shoppingResultList)
                    .add("flightList", flightList)
                    .add("cacheTime", cacheTime)
                    .toString();
        }
    }

    public static class CtripQVerifyRequest implements Serializable {

        private static final long serialVersionUID = -3745510724494610115L;
        /**
         * 接口身份标识名
         */
        private String cid;
        /**
         * 携程关联 ID；携程用来查问题用的。（Qunar不传）
         */
        private String referenceId;
        /**
         * 行程类型，1：单程；2：往返；3：多程。
         * Ctrip暂不支持多程
         */
        private String tripType;
        /**
         * 成人人数，1-9
         */
        private Integer adultNumber;
        /**
         * 儿童人数，0-8
         */
        private Integer childNumber;
        /**
         * 婴儿人数，0-9（Qunar暂不支持婴儿）
         */
        private Integer infantNumber;
        /**
         * 目前只包含航班消息，不含价格信息
         */
        private Routing routing;
        /**
         * 请求来源
         * 0标识请求来源于Ctrip； 1标识请求来源于Qunar
         */
        private Integer source;
        /**
         * 多程航程标识（Ctrip不要，Qunar用）
         * Qunar多程需要使用
         */
        private Integer group;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

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

        public Routing getRouting() {
            return routing;
        }

        public void setRouting(Routing routing) {
            this.routing = routing;
        }

        public Integer getSource() {
            return source;
        }

        public void setSource(Integer source) {
            this.source = source;
        }

        public Integer getGroup() {
            return group;
        }

        public void setGroup(Integer group) {
            this.group = group;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof CtripQVerifyRequest)) {
                return false;
            }

            CtripQVerifyRequest that = (CtripQVerifyRequest) o;

            return new EqualsBuilder().append(getCid(), that.getCid())
                    .append(getReferenceId(), that.getReferenceId()).append(getTripType(), that.getTripType())
                    .append(getAdultNumber(), that.getAdultNumber()).append(getChildNumber(), that.getChildNumber())
                    .append(getInfantNumber(), that.getInfantNumber()).append(getRouting(), that.getRouting())
                    .append(getSource(), that.getSource()).append(getGroup(), that.getGroup()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getCid()).append(getReferenceId()).append(getTripType())
                    .append(getAdultNumber()).append(getChildNumber()).append(getInfantNumber()).append(getRouting())
                    .append(getSource()).append(getGroup()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("cid", cid)
                    .append("referenceId", referenceId)
                    .append("tripType", tripType)
                    .append("adultNumber", adultNumber)
                    .append("childNumber", childNumber)
                    .append("infantNumber", infantNumber)
                    .append("routing", routing)
                    .append("source", source)
                    .append("group", group)
                    .toString();
        }
    }

    public static class CtripQVerifyResponse implements Serializable {
        private static final long serialVersionUID = -3569427041269240382L;
        /**
         * 返回状态
         * 0，成功；
         * 1，舱位失败（请求舱位数大于查询的最大舱位数）；
         * 2，满舱（舱位已售完）；
         * 3，其他失败原因；
         * 4，ctrip 请求参数错误（即将废弃）；
         * 5，程序异常；
         * 6，币种不一致；
         * 7，航司或 GDS 超时；
         * 111，无效的日期范围（第二段的航段时间早于第一段或航段 重复）；
         * 112，参数验证不通过（如：没有传乘客或航班、乘客信息错 误等）；
         * 200，航司或 GDS 结果异常；
         * 201，航司或 GDS 无可用的运价；
         * 202，航司或 GDS 指定的票价不可用；
         * 203，航司或 GDS QTE 出错（航班限制：比如不同时间价格 不一样的航班未被过滤）；
         * 204，航司或 GDS 无联运协议
         */
        private Integer status;
        /**
         * 提示信息，长度小于 64
         */
        private String msg;
        /**
         * 供应商实际外部错误码（Ctrip用，Qunar不用）
         */
        private String cerrorCode;
        /**
         * 供应商实际外部错误信息描述，长度小于 300（Ctrip用，Qunar不用）
         */
        private String cerrorMsg;
        /**
         * 会话标识：标记服务接口调用的唯一标识数字或字母，长度小于 20 个字符 。
         */
        private String sessionId;
        /**
         * 可预订的座位数，最大为 9； maxSeats int 供应商需要确保下 maxSeats 不小于验价请求人数。
         */
        private Integer maxSeats;
        /**
         * 验价返回报文 1）参考搜索返回结果中的 Routing Elements routing object 2）posCode 必须返回
         */
        private Routing routing;

        public CtripQVerifyResponse() {
        }

        public CtripQVerifyResponse(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCerrorCode() {
            return cerrorCode;
        }

        public void setCerrorCode(String cerrorCode) {
            this.cerrorCode = cerrorCode;
        }

        public String getCerrorMsg() {
            return cerrorMsg;
        }

        public void setCerrorMsg(String cerrorMsg) {
            this.cerrorMsg = cerrorMsg;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public Integer getMaxSeats() {
            return maxSeats;
        }

        public void setMaxSeats(Integer maxSeats) {
            this.maxSeats = maxSeats;
        }

        public Routing getRouting() {
            return routing;
        }

        public void setRouting(Routing routing) {
            this.routing = routing;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof CtripQVerifyResponse)) {
                return false;
            }

            CtripQVerifyResponse that = (CtripQVerifyResponse) o;

            return new EqualsBuilder().append(getStatus(), that.getStatus())
                    .append(getMsg(), that.getMsg()).append(getCerrorCode(), that.getCerrorCode())
                    .append(getCerrorMsg(), that.getCerrorMsg()).append(getSessionId(), that.getSessionId())
                    .append(getMaxSeats(), that.getMaxSeats()).append(getRouting(), that.getRouting()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getStatus()).append(getMsg()).append(getCerrorCode())
                    .append(getCerrorMsg()).append(getSessionId()).append(getMaxSeats()).append(getRouting())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("status", status)
                    .append("msg", msg)
                    .append("cerrorCode", cerrorCode)
                    .append("cerrorMsg", cerrorMsg)
                    .append("sessionId", sessionId)
                    .append("maxSeats", maxSeats)
                    .append("routing", routing)
                    .toString();
        }
    }


    public static class CtripQOrderRequest implements Serializable {

        private static final long serialVersionUID = 3878682352372796695L;
        /**
         * 接口身份标识用户名（渠道唯一标识）
         */
        private String cid;
        /**
         * 携程订单号（携程必传，qunar不传）
         */
        private String ctripOrderId;
        /**
         * 携程关联 ID
         */
        private String referenceId;
        /**
         * 行程类型，1：单程；2：往返；3：多程；
         */
        private String tripType;
        /**
         * 会话标识：标记服务接口调用的唯一标 识，会将价格校验接口中的 sessionId 原值传给供应商
         */
        private String sessionId;
        /**
         * 报价信息
         * 参考验仓验价接口中的object/routing Elements
         */
        private Routing routing;
        /**
         * 乘机人信息
         */
        private List<Passenger> passengers;
        /**
         * 联 系 人 信 息
         */
        private Contact contact;
        /**
         * 乘机人预订行李信息
         */
        private List<PassengerBaggage> passengerBaggages;
        /**
         * 增 值 服 务 请 求 信 息
         */
        private BookingValueAddRequest bookingValueAddRequest;

        /**
         * 请求来源
         * 0标识请求来源于Ctrip； 1标识请求来源于Qunar
         */
        private Integer source;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCtripOrderId() {
            return ctripOrderId;
        }

        public void setCtripOrderId(String ctripOrderId) {
            this.ctripOrderId = ctripOrderId;
        }

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

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public Routing getRouting() {
            return routing;
        }

        public void setRouting(Routing routing) {
            this.routing = routing;
        }

        public List<Passenger> getPassengers() {
            return passengers;
        }

        public void setPassengers(List<Passenger> passengers) {
            this.passengers = passengers;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public List<PassengerBaggage> getPassengerBaggages() {
            return passengerBaggages;
        }

        public void setPassengerBaggages(List<PassengerBaggage> passengerBaggages) {
            this.passengerBaggages = passengerBaggages;
        }

        public BookingValueAddRequest getBookingValueAddRequest() {
            return bookingValueAddRequest;
        }

        public void setBookingValueAddRequest(BookingValueAddRequest bookingValueAddRequest) {
            this.bookingValueAddRequest = bookingValueAddRequest;
        }

        public Integer getSource() {
            return source;
        }

        public void setSource(Integer source) {
            this.source = source;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof CtripQOrderRequest)) {
                return false;
            }

            CtripQOrderRequest that = (CtripQOrderRequest) o;

            return new EqualsBuilder().append(getCid(), that.getCid())
                    .append(getCtripOrderId(), that.getCtripOrderId()).append(getReferenceId(), that.getReferenceId())
                    .append(getTripType(), that.getTripType()).append(getSessionId(), that.getSessionId())
                    .append(getRouting(), that.getRouting()).append(getPassengers(), that.getPassengers())
                    .append(getContact(), that.getContact()).append(getPassengerBaggages(), that.getPassengerBaggages())
                    .append(getBookingValueAddRequest(), that.getBookingValueAddRequest())
                    .append(getSource(), that.getSource())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getCid()).append(getCtripOrderId()).append(getReferenceId())
                    .append(getTripType()).append(getSessionId()).append(getRouting()).append(getPassengers())
                    .append(getContact()).append(getPassengerBaggages()).append(getBookingValueAddRequest())
                    .append(getSource())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("cid", cid)
                    .append("ctripOrderId", ctripOrderId)
                    .append("referenceId", referenceId)
                    .append("tripType", tripType)
                    .append("sessionId", sessionId)
                    .append("routing", routing)
                    .append("passengers", passengers)
                    .append("contact", contact)
                    .append("passengerBaggages", passengerBaggages)
                    .append("bookingValueAddRequest", bookingValueAddRequest)
                    .append("source", source)
                    .toString();
        }
    }

    public static class CtripQOrderResponse {
        /**
         * 返回类型
         * 0，成功；
         * 1，舱位已售完；
         * 2，舱位数不足；
         * 3，其他失败原因；
         * 4，ctrip 请求参数错误（即将废弃）；
         * 5，程序异常；
         * 6，订位失败（变价超过阈值）；
         * 7，PNR 为空；
         * 8，未匹配到航班信息；
         * 9，未找到指定运价；
         * 10，航司或 GDS 超时；
         * 101，客户重复在多个平台系统使用同样客户信息预订相 同的航班舱位信息，导致订位失败；
         * 200，航司或 GDS 服务异常；
         * 211，请求乘客信息错误；
         * 212，姓名组格式错误；
         * 213，姓名组长度错误；
         * 214，乘客数错误；
         * 215，airline 错误（承运或开票航司错误）；
         * 216，常旅客卡号不符；
         */
        private Integer status;
        /**
         * 提示信息，长度小于 64
         */
        private String msg;
        /**
         * 供应商实际外部错误码（Ctrip用，Qunar不用）
         */
        private String cerrorCode;
        /**
         * 供应商实际外部错误信息描述，长度小于 300（Ctrip用，Qunar不用）
         */
        private String cerrorMsg;
        /**
         * 会话标识：标记服务接口调用的唯一标识，将生单请求中 的 sessionId 原值返回。
         */
        private String sessionId;
        /**
         * 订单号，最大 100 个字符
         */
        private String orderNo;
        /**
         * PNR 编码，最大 6 个字符
         */
        private String pnrCode;
        /**
         * 可预订的座位数，最大为 9 【生单时携程不会再校验座位数，只要供应商返回的 status 为 0 且 pnrCode 为 6 个字符，就认为生单成功】
         */
        private Integer maxSeats;
        /**
         * 订位联系人信息，参考生单请求中的 Contact Element，需返回预订机票时所用邮箱及联系方式。【廉航票台必须 返回】（Ctrip用，Qunar不用）
         */
        private Contact orderContact;
        /**
         * 报价信息
         * 参考验仓验价返回中的object/responseRouting Elements
         */
        private Routing routing;
        /**
         * 乘机人预订行李信息参考，同请求
         */
        private List<PassengerBaggage> passengerBaggages;

        /**
         * 增值服务预定结果
         */
        private ValueAddResult valueAddResult;

        public CtripQOrderResponse() {
        }

        public CtripQOrderResponse(Integer status, String msg) {
            this.status = status;
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCerrorCode() {
            return cerrorCode;
        }

        public void setCerrorCode(String cerrorCode) {
            this.cerrorCode = cerrorCode;
        }

        public String getCerrorMsg() {
            return cerrorMsg;
        }

        public void setCerrorMsg(String cerrorMsg) {
            this.cerrorMsg = cerrorMsg;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPnrCode() {
            return pnrCode;
        }

        public void setPnrCode(String pnrCode) {
            this.pnrCode = pnrCode;
        }

        public Integer getMaxSeats() {
            return maxSeats;
        }

        public void setMaxSeats(Integer maxSeats) {
            this.maxSeats = maxSeats;
        }

        public Contact getOrderContact() {
            return orderContact;
        }

        public void setOrderContact(Contact orderContact) {
            this.orderContact = orderContact;
        }

        public Routing getRouting() {
            return routing;
        }

        public void setRouting(Routing routing) {
            this.routing = routing;
        }

        public List<PassengerBaggage> getPassengerBaggages() {
            return passengerBaggages;
        }

        public void setPassengerBaggages(List<PassengerBaggage> passengerBaggages) {
            this.passengerBaggages = passengerBaggages;
        }

        public ValueAddResult getValueAddResult() {
            return valueAddResult;
        }

        public void setValueAddResult(ValueAddResult valueAddResult) {
            this.valueAddResult = valueAddResult;
        }
    }

    public static class ShoppingResult implements Serializable {
        private static final long serialVersionUID = 5080722406022938625L;
        /**
         * 航段信息
         */
        private List<FlightRef> flightRefList;
        /**
         * 可保存必要信息，验价时会放在请求报文中传给供应商
         */
        private String data;
        /**
         * 票信息
         */
        private List<Tu> tuList;

        public List<FlightRef> getFlightRefList() {
            return flightRefList;
        }

        public void setFlightRefList(List<FlightRef> flightRefList) {
            this.flightRefList = flightRefList;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<Tu> getTuList() {
            return tuList;
        }

        public void setTuList(List<Tu> tuList) {
            this.tuList = tuList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof ShoppingResult)) {
                return false;
            }

            ShoppingResult that = (ShoppingResult) o;

            return new EqualsBuilder().append(getFlightRefList(), that.getFlightRefList())
                    .append(getData(), that.getData()).append(getTuList(), that.getTuList()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getFlightRefList()).append(getData()).append(getTuList())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("flightRefList", flightRefList)
                    .append("data", data)
                    .append("tuList", tuList)
                    .toString();
        }
    }

    public static class Flight implements Serializable {

        private static final long serialVersionUID = -8108048491665842088L;
        /**
         * 索引号，与flightRefList中的索引号对应，从1开始
         */
        private Integer flightRefNum;
        /**
         * 票面航司 IATA 二字码，必须与 flightNumber 航司相同
         */
        private String marketingCarrier;
        /**
         * 航班号，如：CA123
         * 航班号数字前若有多余的数字 0，必须去掉；如 CZ006 需返回 CZ6 。
         */
        private String flightNumber;
        /**
         * 实际承运航司
         * 若codeShare=true， operatingCarrier不能为空。
         */
        private String operatingCarrier;
        /**
         * 实际承运航班号
         */
        private String operatingFlightNo;
        /**
         * 出发机场；IATA 三字码
         */
        private String depAirport;
        /**
         * 到达机场 IATA 三字码
         */
        private String arrAirport;
        /**
         *
         * 出发航站楼；使用简写，例如T1（Qunar不用）
         */
        private String depTerminal;
        /**
         * 抵达航站楼，使用简写，例如T1（Qunar不用）
         */
        private String arrTerminal;
        /**
         * 起飞日期时间（当地时间），格式：YYYYMMDDHHMM 例：201203100300 表示 2012 年 3 月 10 日 3 时 0 分
         */
        private String depTime;
        /**
         * 到达日期时间（当地时间），格式：YYYYMMDDHHMM 例：201203101305 表示 2012 年 3 月 10 日 13 时 5 分
         */
        private String arrTime;
        /**
         * 代码共享标识（true 代码共享/false 非代码共享）
         */
        private Boolean codeShare;
        /**
         * 机型 ，IATA标准3字码，并过滤下列机型运价信息BUS|ICE|LCH|LMO|MTL|RFS|TGV|THS|THT|TRN|TSL|
         *
         * IATA标准3字码，非标运价会
         */
        private String aircraftCode;
        /**
         * 经停信息，没有经停信息改节点可不传
         */
        private List<Stop> stops;

        public Integer getFlightRefNum() {
            return flightRefNum;
        }

        public void setFlightRefNum(Integer flightRefNum) {
            this.flightRefNum = flightRefNum;
        }

        public String getMarketingCarrier() {
            return marketingCarrier;
        }

        public void setMarketingCarrier(String marketingCarrier) {
            this.marketingCarrier = marketingCarrier;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
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

        public String getDepAirport() {
            return depAirport;
        }

        public void setDepAirport(String depAirport) {
            this.depAirport = depAirport;
        }

        public String getArrAirport() {
            return arrAirport;
        }

        public void setArrAirport(String arrAirport) {
            this.arrAirport = arrAirport;
        }

        public String getDepTerminal() {
            return depTerminal;
        }

        public void setDepTerminal(String depTerminal) {
            this.depTerminal = depTerminal;
        }

        public String getArrTerminal() {
            return arrTerminal;
        }

        public void setArrTerminal(String arrTerminal) {
            this.arrTerminal = arrTerminal;
        }

        public String getDepTime() {
            return depTime;
        }

        public void setDepTime(String depTime) {
            this.depTime = depTime;
        }

        public String getArrTime() {
            return arrTime;
        }

        public void setArrTime(String arrTime) {
            this.arrTime = arrTime;
        }

        public Boolean getCodeShare() {
            return codeShare;
        }

        public void setCodeShare(Boolean codeShare) {
            this.codeShare = codeShare;
        }

        public String getAircraftCode() {
            return aircraftCode;
        }

        public void setAircraftCode(String aircraftCode) {
            this.aircraftCode = aircraftCode;
        }

        public List<Stop> getStops() {
            return stops;
        }

        public void setStops(List<Stop> stops) {
            this.stops = stops;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Flight)) {
                return false;
            }

            Flight flight = (Flight) o;

            return new EqualsBuilder().append(getFlightRefNum(), flight.getFlightRefNum())
                    .append(getMarketingCarrier(), flight.getMarketingCarrier())
                    .append(getFlightNumber(), flight.getFlightNumber())
                    .append(getOperatingCarrier(), flight.getOperatingCarrier())
                    .append(getOperatingFlightNo(), flight.getOperatingFlightNo())
                    .append(getDepAirport(), flight.getDepAirport()).append(getArrAirport(), flight.getArrAirport())
                    .append(getDepTerminal(), flight.getDepTerminal()).append(getArrTerminal(), flight.getArrTerminal())
                    .append(getDepTime(), flight.getDepTime()).append(getArrTime(), flight.getArrTime())
                    .append(getCodeShare(), flight.getCodeShare()).append(getAircraftCode(), flight.getAircraftCode())
                    .append(getStops(), flight.getStops()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getFlightRefNum()).append(getMarketingCarrier())
                    .append(getFlightNumber())
                    .append(getOperatingCarrier()).append(getOperatingFlightNo()).append(getDepAirport())
                    .append(getArrAirport()).append(getDepTerminal()).append(getArrTerminal()).append(getDepTime())
                    .append(getArrTime()).append(getCodeShare()).append(getAircraftCode()).append(getStops())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("flightRefNum", flightRefNum)
                    .append("marketingCarrier", marketingCarrier)
                    .append("flightNumber", flightNumber)
                    .append("operatingCarrier", operatingCarrier)
                    .append("operatingFlightNo", operatingFlightNo)
                    .append("depAirport", depAirport)
                    .append("arrAirport", arrAirport)
                    .append("depTerminal", depTerminal)
                    .append("arrTerminal", arrTerminal)
                    .append("depTime", depTime)
                    .append("arrTime", arrTime)
                    .append("codeShare", codeShare)
                    .append("aircraftCode", aircraftCode)
                    .append("stops", stops)
                    .toString();
        }
    }

    public static class FlightRef implements Serializable {
        private static final long serialVersionUID = 5186668083312170403L;
        /**
         * 航班索引号
         */
        private Integer flightRefNum;

        /**
         * 航程标识，1标识去程，2标识回程
         */
        private Integer segmentNo;
        /**
         * 航程中的航段顺序，往返分别从1开始
         */
        private Integer flightSeq;
        /**
         * 属于第几张票，从1开始赋值，第一张票赋值为1。供应商自组多票产品才存在多个值。
         * Qunar目前不支持多张票，默认赋值为1
         */
        private Integer tuSequence;
        /**
         *
         * 舱等； 头等：F；商务：C；超经：S（非廉航和廉航均已支持，需联系我方配置）；经济：Y
         */
        private String seatGrade;
        /**
         *
         * 子舱位
         * 携程会根据子舱位调用携程舱等接口去获取舱等，携程会校验子舱位是否规范（默认为两位字符，特殊舱位与我们联系）
         */
        private String seatClass;
        /**
         *
         * 子舱位数量，如果舱位数量超过9 ，请返回9。如果没有返回，默认为 9
         * 该字段的准确赋值可有效拦截用户在乘机人页填写人数大于剩余舱位数的情况
         */
        private Integer seatCount;
        /**
         * 航司原始子舱位，如果航司无原始子舱位名，则赋值为Y
         * 供携程匹配退改签，赋值比价属性用
         */
        private String originSeatClass;
        /**
         * 品牌运价名
         * 供携程匹配退改签，赋值比价属性用
         */
        private String brandName;

        public Integer getSegmentNo() {
            return segmentNo;
        }

        public void setSegmentNo(Integer segmentNo) {
            this.segmentNo = segmentNo;
        }

        public Integer getFlightRefNum() {
            return flightRefNum;
        }

        public void setFlightRefNum(Integer flightRefNum) {
            this.flightRefNum = flightRefNum;
        }

        public Integer getFlightSeq() {
            return flightSeq;
        }

        public void setFlightSeq(Integer flightSeq) {
            this.flightSeq = flightSeq;
        }

        public Integer getTuSequence() {
            return tuSequence;
        }

        public void setTuSequence(Integer tuSequence) {
            this.tuSequence = tuSequence;
        }

        public String getSeatGrade() {
            return seatGrade;
        }

        public void setSeatGrade(String seatGrade) {
            this.seatGrade = seatGrade;
        }

        public String getSeatClass() {
            return seatClass;
        }

        public void setSeatClass(String seatClass) {
            this.seatClass = seatClass;
        }

        public Integer getSeatCount() {
            return seatCount;
        }

        public void setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
        }

        public String getOriginSeatClass() {
            return originSeatClass;
        }

        public void setOriginSeatClass(String originSeatClass) {
            this.originSeatClass = originSeatClass;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof FlightRef)) {
                return false;
            }

            FlightRef flightRef = (FlightRef) o;

            return new EqualsBuilder().append(getFlightRefNum(), flightRef.getFlightRefNum())
                    .append(getSegmentNo(), flightRef.getSegmentNo()).append(getFlightSeq(), flightRef.getFlightSeq())
                    .append(getTuSequence(), flightRef.getTuSequence()).append(getSeatGrade(), flightRef.getSeatGrade())
                    .append(getSeatClass(), flightRef.getSeatClass()).append(getSeatCount(), flightRef.getSeatCount())
                    .append(getOriginSeatClass(), flightRef.getOriginSeatClass())
                    .append(getBrandName(), flightRef.getBrandName()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getFlightRefNum()).append(getSegmentNo()).append(getFlightSeq())
                    .append(getTuSequence()).append(getSeatGrade()).append(getSeatClass()).append(getSeatCount())
                    .append(getOriginSeatClass()).append(getBrandName()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("flightRefNum", flightRefNum)
                    .append("segmentNo", segmentNo)
                    .append("flightSeq", flightSeq)
                    .append("tuSequence", tuSequence)
                    .append("seatGrade", seatGrade)
                    .append("seatClass", seatClass)
                    .append("seatCount", seatCount)
                    .append("originSeatClass", originSeatClass)
                    .append("brandName", brandName)
                    .toString();
        }
    }


    public static class Tu implements Serializable {
        private static final long serialVersionUID = -7919006126871068009L;
        /**
         * 属于第几张票
         * 非供应商自组多票产品赋值只有1
         */
        private Integer tuSequence;
        /**
         * 运价信息
         */
        private List<Price> priceList;
        /**
         * 旅客资质，标准三字码： NOR：普通成人 LAB：劳务人员 SEA：海员 SNR：老年人 STU：学生 YOU：青年
         * 如果投放非NOR的政策，请提前告知我们。去哪儿普通价传NOR，留学生价传STU
         */
        private String eligibility;

        /**
         * 出票航司（Qunar可不传）
         * 1）一个行程只能赋一个出票航司； 2）如不赋值会取行程第一航段的carrier作为出票航司； 3）此字段非常重要，请务必准确赋值
         */
        private String validatingCarrier;
        /**
         * 产品类型 （注意Ctrip和Qunar不一样）
         * Ctrip: 公布运价产品：PUB 控位产品：KWP 私有运价产品：PRV【注】PUB产品需要申请才能投放，KWP为控位票台专用。
         * Qunar: 公布运价产品：PUB 控位(含包机、切位、K位)产品：KWP 私有运价产品：PRV 积分票产品：JFP 官网（含官网B2B本票）产品：GWP
         * 【注】使用控位产品、积分票产品类型，请提前联系客户经理；不传默认私有运价
         */
        private String productType;
        /**
         * 票价等级
         * 1）fareBasis数量必须和航段数一致 2）同一旅行方向的farebasis可以不一致（多段） 3）不同旅行方向farebasis可以不一致 4）每航段1个，使用“ ; ”分割
         */
        private String fareBasis;
        /**
         * 是否要与携程退改签匹配，true ：需要匹配 / false：不匹配 （Qunar可不传）
         * 1）默认false；2）匹配成功将展示携程退改；3）匹配失败则展示航司客规。为true时需要赋值公布运价相关参数
         */
        private Boolean isUseCtripRule;
        /**
         * 公布运价相关参数，地理区间见运价集群编码 （Qunar可不传）
         * 【注】因供应商无法确定准确的tariffNo，此节点传运价类型，公布运价PUB，私有运价PRI，强校验情况下，不传会过滤运价
         */
        private String tariffNo;
        /**
         *运价规则编码，公布运价相关参数（Qunar可不传）
         */
        private String ruleNo;
        /**
         * 订位Office号，可为空（Qunar可不传）
         */
        private String bookingOfficeNo;
        /**
         * 出票Of­fice号，可为空（Qunar可不传）
         */
        private String ticketingOfficeNo;
        /**
         * 政策来源
         * 1）非公布运价此字段必须按要求返回； 2）公布运价此字段必须按要求返回，否则产品将按照未知订座系统，输出到旅行套餐；
         * 3）使用IATA标准2字代码 1E：TravelSky 1A：Amadeus 1B：Abacus 1S：Sabre 1P：WorldSpan 1G：Galileo Website：航司官网 OT：未知订座系统来源
         */
        private String reservationType;
        /**
         *出票地国家2字码，多个出票地用‘|’隔开
         */
        private String posCode;
        /**
         *特殊产品复合类型（Qunar可不传）
         * 1）需要使用该节点请提前告知；2）多票组合报价目前不支持特殊产品投放。
         */
        private Integer complexTerm;
        /**
         *最小年龄，置空表示无限制
         * 如要使用此字段请提前通知我们，盲目使用会影响价格展示
         */
        private Integer minAge;
        /**
         *最大年龄，置空表示无限制
         * 如要使用此字段请提前通知我们，盲目使用会影响价格展示
         */
        private Integer maxAge;
        /**
         *乘客允许国籍类型，0全部，1适用部分国籍，2不适用部分国籍，与nationality配合使用
         * 限制国籍会造成价格属性劣势
         */
        private Integer nationalityType;
        /**
         *乘客国籍，可为空
         * 限制国籍会造成价格属性劣势
         */
        private String nationality;
        /**
         *套餐类型（注意Ctrip和Qunar不一样）
         * Ctrip:0：旅行套餐 / 1：商务优选 / 2：特惠推荐 Qunar:0：特惠产品 / 1：标准产品 / 2：特殊产品
         */
        private Integer planCategory;
        /**
         *报销凭证，T：行程单 / F：发票 / E：电子发票 （Qunar只支持T和E）
         * 1）默认F发票； 2）廉航票台可赋值为E电子发票； 3）非Eterm出票则无法打印行程单，不可赋值T行程单；否则出票后系统无法打印，将自动转为F发票开票； 4）赋值T行程单或者E电子发票，请提前通知我们调整配置
         */
        private String invoiceType;
        /**
         *最小出行人数（Qunar可不传）
         * 如果没有返回，默认为1
         */
        private Integer minPassengerCount;
        /**
         *最大出行人数（Qunar可不传）
         * 如果没有返回，默认为9
         */
        private Integer maxPassengerCount;
        /**
         *备注信息，最大 300 个字符
         * 目前此节点不展示
         */
        private String note;

        private AirlineAncillaries airlineAncillaries;
        /**
         *行李额信息
         */
        private List<FormatBaggageDetail> formatBaggageDetailList;
        /**
         *退票信息
         */
        private List<RefundInfo> refundInfoList;
        /**
         *改签信息
         */
        private List<ChangesInfo> changesInfoList;
        /**
         *
         */
        private PackageInfo packageInfo;
        /**
         *币种
         * 默认为CNY，Ctrip要求与结算币种一致。若要投放外币请与我们联系
         */
        private String currency;
        /**
         *报价类型（Ctrip不用传，Qunar需要传）
         * 0预定价； 1 申请价
         */
        private Integer applyType;
        /**
         *出票速度，单位分钟（Ctrip不用传，Qunar可以传）
         */
        private Integer ticketTimeUnit;
        /**
         *
         * 小团报价，0普通价/1小团报价（Ctrip不用传，Qunar可以传）
         */
        private Integer tuanType;
        /**
         *拼接报价拼接点（Ctrip不用传，Qunar可以传）
         */
        private List<String> combineIndexs;
        /**
         *是否支持签转，0表示不支持，1表示支持（Ctrip不用传，Qunar必须传）
         * 默认为0
         */
        private Integer endorsement;
        /**
         * 扩展字段（Qunar可以传）Ctrip用于本地证件本地语言下单。
         * 本地证件本地语言下单： {"languageOfGovernmentIssuedPhotoId": "KO|EN|ZH"}key固定的，value的话采用ISO 639-1标准，多个语言用“|”隔开，示例：KO|EN|ZH
         */
        private Map<String, String> extraInfo;

        public Integer getTuSequence() {
            return tuSequence;
        }

        public void setTuSequence(Integer tuSequence) {
            this.tuSequence = tuSequence;
        }

        public List<Price> getPriceList() {
            return priceList;
        }

        public void setPriceList(List<Price> priceList) {
            this.priceList = priceList;
        }

        public String getEligibility() {
            return eligibility;
        }

        public void setEligibility(String eligibility) {
            this.eligibility = eligibility;
        }

        public String getValidatingCarrier() {
            return validatingCarrier;
        }

        public void setValidatingCarrier(String validatingCarrier) {
            this.validatingCarrier = validatingCarrier;
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

        public Boolean getUseCtripRule() {
            return isUseCtripRule;
        }

        public void setUseCtripRule(Boolean useCtripRule) {
            isUseCtripRule = useCtripRule;
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

        public String getReservationType() {
            return reservationType;
        }

        public void setReservationType(String reservationType) {
            this.reservationType = reservationType;
        }

        public String getPosCode() {
            return posCode;
        }

        public void setPosCode(String posCode) {
            this.posCode = posCode;
        }

        public Integer getComplexTerm() {
            return complexTerm;
        }

        public void setComplexTerm(Integer complexTerm) {
            this.complexTerm = complexTerm;
        }

        public Integer getMinAge() {
            return minAge;
        }

        public void setMinAge(Integer minAge) {
            this.minAge = minAge;
        }

        public Integer getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Integer maxAge) {
            this.maxAge = maxAge;
        }

        public Integer getNationalityType() {
            return nationalityType;
        }

        public void setNationalityType(Integer nationalityType) {
            this.nationalityType = nationalityType;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
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

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public AirlineAncillaries getAirlineAncillaries() {
            return airlineAncillaries;
        }

        public void setAirlineAncillaries(AirlineAncillaries airlineAncillaries) {
            this.airlineAncillaries = airlineAncillaries;
        }

        public List<FormatBaggageDetail> getFormatBaggageDetailList() {
            return formatBaggageDetailList;
        }

        public void setFormatBaggageDetailList(List<FormatBaggageDetail> formatBaggageDetailList) {
            this.formatBaggageDetailList = formatBaggageDetailList;
        }

        public List<RefundInfo> getRefundInfoList() {
            return refundInfoList;
        }

        public void setRefundInfoList(List<RefundInfo> refundInfoList) {
            this.refundInfoList = refundInfoList;
        }

        public List<ChangesInfo> getChangesInfoList() {
            return changesInfoList;
        }

        public void setChangesInfoList(List<ChangesInfo> changesInfoList) {
            this.changesInfoList = changesInfoList;
        }

        public PackageInfo getPackageInfo() {
            return packageInfo;
        }

        public void setPackageInfo(PackageInfo packageInfo) {
            this.packageInfo = packageInfo;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getApplyType() {
            return applyType;
        }

        public void setApplyType(Integer applyType) {
            this.applyType = applyType;
        }

        public Integer getTicketTimeUnit() {
            return ticketTimeUnit;
        }

        public void setTicketTimeUnit(Integer ticketTimeUnit) {
            this.ticketTimeUnit = ticketTimeUnit;
        }

        public Integer getTuanType() {
            return tuanType;
        }

        public void setTuanType(Integer tuanType) {
            this.tuanType = tuanType;
        }

        public List<String> getCombineIndexs() {
            return combineIndexs;
        }

        public void setCombineIndexs(List<String> combineIndexs) {
            this.combineIndexs = combineIndexs;
        }

        public Integer getEndorsement() {
            return endorsement;
        }

        public void setEndorsement(Integer endorsement) {
            this.endorsement = endorsement;
        }

        public Map<String, String> getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(Map<String, String> extraInfo) {
            this.extraInfo = extraInfo;
        }
    }

    public static class Stop implements Serializable {
        private static final long serialVersionUID = -8038185671046606043L;
        /**
         * 经停机场； IATA 三字码（Qunar不用）
         */
        private String stopAirport;
        /**
         * 经停时长，拿不到请置空，单位分钟（Qunar不用）
         */
        private Integer stopDuration;
        /**
         * 经停城市； IATA 三字码
         */
        private String stopCity;

        public String getStopAirport() {
            return stopAirport;
        }

        public void setStopAirport(String stopAirport) {
            this.stopAirport = stopAirport;
        }

        public Integer getStopDuration() {
            return stopDuration;
        }

        public void setStopDuration(Integer stopDuration) {
            this.stopDuration = stopDuration;
        }

        public String getStopCity() {
            return stopCity;
        }

        public void setStopCity(String stopCity) {
            this.stopCity = stopCity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Stop)) {
                return false;
            }

            Stop stop = (Stop) o;

            return new EqualsBuilder().append(getStopAirport(), stop.getStopAirport())
                    .append(getStopDuration(), stop.getStopDuration()).append(getStopCity(), stop.getStopCity())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getStopAirport()).append(getStopDuration()).append(getStopCity())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("stopAirport", stopAirport)
                    .append("stopDuration", stopDuration)
                    .append("stopCity", stopCity)
                    .toString();
        }
    }

    public static class Price implements Serializable {
        private static final long serialVersionUID = -867261193599034734L;
        /**
         * 乘客类型，0 成人/1 儿童/2 婴儿
         */
        private Integer passengerType;
        /**
         * 公布运价，不含税（Ctrip用，Qunar不用）
         * 不清楚请赋值0
         */
        private BigDecimal publishPrice;
        /**
         * 底价，不含税
         */
        private BigDecimal price;
        /**
         * 税费
         * 1）不能是0；2）若存在为0的情况，请与我们联系
         */
        private BigDecimal taxFeeAmount;

        public Integer getPassengerType() {
            return passengerType;
        }

        public void setPassengerType(Integer passengerType) {
            this.passengerType = passengerType;
        }

        public BigDecimal getPublishPrice() {
            return publishPrice;
        }

        public void setPublishPrice(BigDecimal publishPrice) {
            this.publishPrice = publishPrice;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getTaxFeeAmount() {
            return taxFeeAmount;
        }

        public void setTaxFeeAmount(BigDecimal taxFeeAmount) {
            this.taxFeeAmount = taxFeeAmount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Price)) {
                return false;
            }

            Price price1 = (Price) o;

            return new EqualsBuilder().append(getPassengerType(), price1.getPassengerType())
                    .append(getPublishPrice(), price1.getPublishPrice()).append(getPrice(), price1.getPrice())
                    .append(getTaxFeeAmount(), price1.getTaxFeeAmount()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getPassengerType()).append(getPublishPrice()).append(getPrice())
                    .append(getTaxFeeAmount()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("passengerType", passengerType)
                    .append("publishPrice", publishPrice)
                    .append("price", price)
                    .append("taxFeeAmount", taxFeeAmount)
                    .toString();
        }
    }

    public static class AirlineAncillaries implements Serializable {
        private static final long serialVersionUID = 4347827013965098071L;
        /**
         * 是否售卖行李，true：包含 / false：不包含
         * 默认false（Ctrip在Search阶段传，Qunar在验价阶段传）
         */
        private Boolean baggageService;
        /**
         * 有无免费行李额，true：无免费行李额 / false：全部有免费行李额或部分有免费行李额
         * 默认false
         */
        private Boolean unFreeBaggage;

        public Boolean getBaggageService() {
            return baggageService;
        }

        public void setBaggageService(Boolean baggageService) {
            this.baggageService = baggageService;
        }

        public Boolean getUnFreeBaggage() {
            return unFreeBaggage;
        }

        public void setUnFreeBaggage(Boolean unFreeBaggage) {
            this.unFreeBaggage = unFreeBaggage;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof AirlineAncillaries)) {
                return false;
            }

            AirlineAncillaries that = (AirlineAncillaries) o;

            return new EqualsBuilder().append(getBaggageService(), that.getBaggageService())
                    .append(getUnFreeBaggage(), that.getUnFreeBaggage()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getBaggageService()).append(getUnFreeBaggage()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("baggageService", baggageService)
                    .append("unFreeBaggage", unFreeBaggage)
                    .toString();
        }
    }

    public static class FormatBaggageDetail implements Serializable {
        private static final long serialVersionUID = -1998678892060557944L;
        /**
         * 航程标识，1标识去程，2标识回程（与flightRefList中的segmentNo对应）
         */
        private Integer segmentNo;
        /**
         * 航程中的航段顺序，往返分别从1开始（与flightRefList中的flightSeq对应）
         */
        private Integer flightSeq;
        /**
         * 乘客类型，0：成人 / 1：儿童 / 2：婴儿 （Qunar不区分乘客类型，全部传0）
         * 1）对于多乘客类型的查询、验价，必须按乘客类型返回；如成人+儿童的查询，成人和儿童的行李额都要有
         */
        private Integer passengerType;
        /**
         * 0：表示无免费托运行李，baggageWeight需赋值-1；
         * -1：表示计重制，baggageWeight表示每人可携带的总重量baggageWeight必须赋正值
         * n（n>0）：表示计件制，每人可携带n件行李，对应的对应的baggageWeight表示每件行李重量（若计件制时不知每件行李额的重量，baggageWeight必须赋值为-1）
         */
        private Integer baggagePiece;
        /**
         * 行李额重量，单位KG，跟BaggagePiece配合使用
         */
        private Integer baggageWeight;

        public Integer getSegmentNo() {
            return segmentNo;
        }

        public void setSegmentNo(Integer segmentNo) {
            this.segmentNo = segmentNo;
        }

        public Integer getFlightSeq() {
            return flightSeq;
        }

        public void setFlightSeq(Integer flightSeq) {
            this.flightSeq = flightSeq;
        }

        public Integer getPassengerType() {
            return passengerType;
        }

        public void setPassengerType(Integer passengerType) {
            this.passengerType = passengerType;
        }

        public Integer getBaggagePiece() {
            return baggagePiece;
        }

        public void setBaggagePiece(Integer baggagePiece) {
            this.baggagePiece = baggagePiece;
        }

        public Integer getBaggageWeight() {
            return baggageWeight;
        }

        public void setBaggageWeight(Integer baggageWeight) {
            this.baggageWeight = baggageWeight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof FormatBaggageDetail)) {
                return false;
            }

            FormatBaggageDetail that = (FormatBaggageDetail) o;

            return new EqualsBuilder().append(getSegmentNo(), that.getSegmentNo())
                    .append(getFlightSeq(), that.getFlightSeq()).append(getPassengerType(), that.getPassengerType())
                    .append(getBaggagePiece(), that.getBaggagePiece())
                    .append(getBaggageWeight(), that.getBaggageWeight())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getSegmentNo()).append(getFlightSeq()).append(getPassengerType())
                    .append(getBaggagePiece()).append(getBaggageWeight()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("segmentNo", segmentNo)
                    .append("flightSeq", flightSeq)
                    .append("passengerType", passengerType)
                    .append("baggagePiece", baggagePiece)
                    .append("baggageWeight", baggageWeight)
                    .toString();
        }
    }

    public static class RefundInfo implements Serializable {
        private static final long serialVersionUID = 4989435398298002754L;
        /**
         * 乘客类型，0：成人 / 1：儿童 / 2：婴儿（Qunar不区分乘客类型，全部传0）
         * 对于多乘客类型的查询、验价，必须按乘客类型返回；如成人+儿童的查询，成人和儿童的退改签都要有
         */
        private Integer passengerType;
        /**
         * 退票类型 0：客票全部未使用； 1：客票部分使用【即去程已使用，在往返行程中使用，代表回程的退票信息】
         * 单程时0；往返时0、1都要有（指一张票里面有往返）
         */
        private Integer refundType;
        /**
         *退票标识 T：不可退 H：有条件退 F：免费退 E：按航司客规【商务优选产品类型专用，其他产品类型传E统一过滤】
         */
        private String refundStatus;
        /**
         * 退票费 （退改币种与运价币种一致）
         * 1）若refundStatus =H，必须赋值； 2）若refundStatus =T/F，此字段可不赋值
         */
        private BigDecimal refundFee;
        /**
         * 是否允许NoShow退票  T：不可退；  H：有条件退； F：免费退； E：按航司客规为准，【商务优选产品类型专用，其他产品类型传E统一过滤】
         */
        private String refNoshow;
        /**
         * 退票时航班起飞前多久算NoShow，单位：小时（Qunar用的是分钟）
         * 1）若无法确认此时间，请默认赋0。
         */
        private Integer refNoShowCondition;
        /**
         * NoShow退票费用
         * 1）当refNoshow =H，必须赋值； 2）当refundStatus = H 且 refNoshow = H 时，展示给客人的noshow退票费= refundFee+ refNoshowFee 3）当refundStatus = T 且 refNoshow = H时，展示给客人的noshow退票费 = 0+refNoshowFee
         */
        private BigDecimal refNoshowFee;
        /**
         * 多段退改新节点
         * 多段noshow退改可使用此节点，三段以下退改使用上述老节点，此节点不需要传值
         */
        private List<Condition> conditionList;

        public Integer getPassengerType() {
            return passengerType;
        }

        public void setPassengerType(Integer passengerType) {
            this.passengerType = passengerType;
        }

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

        public List<Condition> getConditionList() {
            return conditionList;
        }

        public void setConditionList(List<Condition> conditionList) {
            this.conditionList = conditionList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof RefundInfo)) {
                return false;
            }

            RefundInfo that = (RefundInfo) o;

            return new EqualsBuilder().append(getPassengerType(), that.getPassengerType())
                    .append(getRefundType(), that.getRefundType()).append(getRefundStatus(), that.getRefundStatus())
                    .append(getRefundFee(), that.getRefundFee()).append(getRefNoshow(), that.getRefNoshow())
                    .append(getRefNoShowCondition(), that.getRefNoShowCondition())
                    .append(getRefNoshowFee(), that.getRefNoshowFee())
                    .append(getConditionList(), that.getConditionList())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getPassengerType()).append(getRefundType())
                    .append(getRefundStatus())
                    .append(getRefundFee()).append(getRefNoshow()).append(getRefNoShowCondition())
                    .append(getRefNoshowFee())
                    .append(getConditionList()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("passengerType", passengerType)
                    .append("refundType", refundType)
                    .append("refundStatus", refundStatus)
                    .append("refundFee", refundFee)
                    .append("refNoshow", refNoshow)
                    .append("refNoShowCondition", refNoShowCondition)
                    .append("refNoshowFee", refNoshowFee)
                    .append("conditionList", conditionList)
                    .toString();
        }
    }

    public static class Condition implements Serializable {
        private static final long serialVersionUID = -8971928611999720574L;
        /**
         * 退票/改期标识 T：不可退票/改期 H：有条件退票/改期 F：免费退票/改期 E：按航司客规【商务优选产品类型专用，其他产品类型传E统一过滤】
         */
        private String status;
        /**
         * 起飞前时间,以分钟为单位，与amount配合使用。如填入60，表示起飞前60分钟改期费。
         * 最后一段的endminute必须以-1结尾。
         */
        private Integer endMinute;
        /**
         * 退票/改期费 1）当Status =H，必须赋值； 2）若Status =T/F，此字段可不赋值。
         */
        private BigDecimal amount;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getEndMinute() {
            return endMinute;
        }

        public void setEndMinute(Integer endMinute) {
            this.endMinute = endMinute;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Condition)) {
                return false;
            }

            Condition condition = (Condition) o;

            return new EqualsBuilder().append(getStatus(), condition.getStatus())
                    .append(getEndMinute(), condition.getEndMinute()).append(getAmount(), condition.getAmount())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getStatus()).append(getEndMinute()).append(getAmount())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("status", status)
                    .append("endMinute", endMinute)
                    .append("amount", amount)
                    .toString();
        }
    }

    public static class ChangesInfo implements Serializable {
        private static final long serialVersionUID = -8793092198582687773L;
        /**
         * 乘客类型，0 成人/1 儿童/2 婴儿 （Qunar不区分乘客类型，全部传0）
         * 1）对于对乘客类型的查询、验价，必须按乘客类型返回；如成人+儿童的查询，成人和儿童的退改签都要有。
         */
        private Integer passengerType;
        /**
         * 改期类型 0：客票全部未使用； 1：客票部分已使用【即去程已使用，在往返行程中使用，代表回程的改期信息】
         * 单程时赋值为0；往返时0、1都要有（指一张票里面有往返）
         */
        private Integer changesType;
        /**
         * 改期标识 T：不可改期 H：有条件改期 F：免费改期 E：按航司客规【商务优选产品类型专用，其他产品类型传E统一过滤】
         */
        private String changesStatus;
        /**
         * 改期费 （退改币种与运价币种一致）
         * 1）当changesStatus =H，必须赋值； 2）若changesStatus =T/F，此字段可不赋值。
         */
        private BigDecimal changesFee;
        /**
         * 是否允许NoShow改期 T：不可改期； H：有条件改期； F：免费改期； E：按航司客规为准【商务优选产品类型专用，其他产品类型传E统一过滤】
         */
        private String revNoshow;
        /**
         * 改期时航班起飞前多久算NoShow，单位：小时（Qunar用的是分钟）
         * 1）若无法确认此时间，请默认赋0。
         */
        private Integer revNoShowCondition;
        /**
         * NoShow改期费用
         * 1）当revNoshow = H，必须赋值； 2）当changesStatus = H 且 revNoshow = H 时，展示给客人的noshow改期费= changesFee+ revNoshowFee 3）当changesStatus = T 且 revNoshow = H 时，展示给客人的noshow改期费 = 0+ revNoshowFee
         */
        private BigDecimal revNoshowFee;
        /**
         * 多段退改新节点
         * 多段noshow退改可使用此节点，三段以下退改使用上述老节点，此节点不需要传值
         */
        private List<Condition> conditionList;

        public Integer getPassengerType() {
            return passengerType;
        }

        public void setPassengerType(Integer passengerType) {
            this.passengerType = passengerType;
        }

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

        public List<Condition> getConditionList() {
            return conditionList;
        }

        public void setConditionList(List<Condition> conditionList) {
            this.conditionList = conditionList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof ChangesInfo)) {
                return false;
            }

            ChangesInfo that = (ChangesInfo) o;

            return new EqualsBuilder().append(getPassengerType(), that.getPassengerType())
                    .append(getChangesType(), that.getChangesType()).append(getChangesStatus(), that.getChangesStatus())
                    .append(getChangesFee(), that.getChangesFee()).append(getRevNoshow(), that.getRevNoshow())
                    .append(getRevNoShowCondition(), that.getRevNoShowCondition())
                    .append(getRevNoshowFee(), that.getRevNoshowFee())
                    .append(getConditionList(), that.getConditionList())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getPassengerType()).append(getChangesType())
                    .append(getChangesStatus())
                    .append(getChangesFee()).append(getRevNoshow()).append(getRevNoShowCondition())
                    .append(getRevNoshowFee())
                    .append(getConditionList()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("passengerType", passengerType)
                    .append("changesType", changesType)
                    .append("changesStatus", changesStatus)
                    .append("changesFee", changesFee)
                    .append("revNoshow", revNoshow)
                    .append("revNoShowCondition", revNoShowCondition)
                    .append("revNoshowFee", revNoshowFee)
                    .append("conditionList", conditionList)
                    .toString();
        }
    }

    public static class PackageInfo implements Serializable {
        private static final long serialVersionUID = -6316335129021904136L;
        /**
         * 套餐类型
         */
        private String packageType;
        /**
         * 套餐名字
         */
        private String packageName;

        public String getPackageType() {
            return packageType;
        }

        public void setPackageType(String packageType) {
            this.packageType = packageType;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof PackageInfo)) {
                return false;
            }

            PackageInfo that = (PackageInfo) o;

            return new EqualsBuilder().append(getPackageType(), that.getPackageType())
                    .append(getPackageName(), that.getPackageName()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getPackageType()).append(getPackageName()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("packageType", packageType)
                    .append("packageName", packageName)
                    .toString();
        }
    }

    public static class Routing implements Serializable {
        private static final long serialVersionUID = 3348647056928845297L;
        /**
         * 票台返回的运价标识，源自查询结果
         */
        private String data;
        /**
         * 去程航班信息
         */
        private List<Segment> fromSegments;
        /**
         * 回程航班信息
         */
        private List<Segment> retSegments;
        /**
         * 套餐信息（Ctrip暂未启用，Qunar可以传）
         * 参考查询中的object/packageInfo Element
         */
        private PackageInfo packageInfo;
        /**
         *运价信息
         */
        private List<Price> priceList;
        /**
         * 旅客资质，标准三字码： NOR：普通成人 LAB：劳务人员 SEA：海员 SNR：老年人 STU：学生 YOU：青年
         * 如果投放非NOR的政策，请提前告知我们。去哪儿普通价传NOR，留学生价传STU
         */
        private String eligibility;
        /**
         * 出票航司（Qunar可不传）
         * 1）一个行程只能赋一个出票航司； 2）如不赋值会取行程第一航段的carrier作为出票航司； 3）此字段非常重要，请务必准确赋值
         */
        private String validatingCarrier;
        /**
         * 产品类型 （注意Ctrip和Qunar不一样）
         * Ctrip: 公布运价产品：PUB 控位产品：KWP 私有运价产品：PRV【注】PUB产品需要申请才能投放，KWP为控位票台专用。 Qunar: 公布运价产品：PUB 控位(含包机、切位、K位)产品：KWP 私有运价产品：PRV 积分票产品：JFP 官网（含官网B2B本票）产品：GWP 【注】使用控位产品、积分票产品类型，请提前联系客户经理；不传默认私有运价
         */
        private String productType;
        /**
         * 票价等级
         * 1）fareBasis数量必须和航段数一致 2）同一旅行方向的farebasis可以不一致（多段） 3）不同旅行方向farebasis可以不一致 4）每航段1个，使用“ ; ”分割
         */
        private String fareBasis;
        /**
         * 是否要与携程退改签匹配，true ：需要匹配 / false：不匹配 （Qunar可不传）
         * 1）默认false；2）匹配成功将展示携程退改；3）匹配失败则展示航司客规
         */
        private Boolean isUseCtripRule;
        /**
         * 公布运价相关参数，地理区间见运价集群编码 （Qunar可不传）
         * 【注】因供应商无法确定准确的tariffNo，此节点传运价类型，公布运价PUB，私有运价PRI，强校验情况下，不传会过滤运价
         */
        private String tariffNo;
        /**
         * 运价规则编码，公布运价相关参数（Qunar可不传）
         */
        private String bookingOfficeNo;
        /**
         * 出票Office号，可为空（Qunar可不传）
         */
        private String ticketingOfficeNo;
        /**
         * 政策来源
         * 1）非公布运价此字段必须按要求返回； 2）公布运价此字段必须按要求返回，否则产品将按照未知订座系统，输出到旅行套餐； 3）使用IATA标准2字代码 1E：TravelSky 1A：Amadeus 1B：Abacus 1S：Sabre 1P：WorldSpan 1G：Galileo Website：航司官网 OT：未知订座系统来源
         */
        private String reservationType;
        /**
         * 出票地国家2字码，多个出票地用‘|’隔开
         */
        private String posCode;
        /**
         * 特殊产品复合类型（Qunar可不传）
         * 1）需要使用该节点请提前告知；2）多票组合报价目前不支持特殊产品投放。
         */
        private Integer complexTerm;
        /**
         * 最小年龄，置空表示无限制
         * 如要使用此字段请提前通知我们，盲目使用会影响价格展示
         */
        private Integer minAge;
        /**
         * 最大年龄，置空表示无限制
         * 如要使用此字段请提前通知我们，盲目使用会影响价格展示
         */
        private Integer maxAge;
        /**
         * 乘客国籍，可为空
         * 限制国籍会造成价格属性劣势
         */
        private String nationality;
        /**
         * 套餐类型（注意Ctrip和Qunar不一样）
         * Ctrip:0：旅行套餐 / 1：商务优选 / 2：特惠推荐 Qunar:0：特惠产品 / 1：标准产品 / 2：特殊产品
         */
        private Integer planCategory;
        /**
         * 报销凭证，T：行程单 / F：发票 / E：电子发票 （Qunar只支持T和E）
         * 1）默认F发票； 2）廉航票台可赋值为E电子发票； 3）非Eterm出票则无法打印行程单，不可赋值T行程单；否则出票后系统无法打印，将自动转为F发票开票； 4）赋值T行程单或者E电子发票，请提前通知我们调整配置
         */
        private String invoiceType;
        /**
         * 最小出行人数（Qunar可不传）
         * 如果没有返回，默认为1
         */
        private Integer minPassengerCount;
        /**
         * 最大出行人数（Qunar可不传）
         * 如果没有返回，默认为9
         */
        private Integer maxPassengerCount;
        /**
         * 备注信息，最大 300 个字符
         * 目前此节点不展示
         */
        private String note;
        /**
         * 参考查询返回中的报文结构
         */
        private AirlineAncillaries airlineAncillaries;
        /**
         * 行李额信息
         * 参考查询返回中的报文结构
         */
        private List<FormatBaggageDetail> formatBaggageDetailList;
        /**
         * 退票信息
         * 参考查询返回中的报文结构
         */
        private List<RefundInfo> refundInfoList;
        /**
         * 改签信息
         * 参考查询返回中的报文结构
         */
        private List<ChangesInfo> changesInfoList;
        /**
         * 币种
         * 默认为CNY，Ctrip要求与结算币种一致
         */
        private String currency;
        /**
         * 报价类型（Ctrip不用传，Qunar需要传）
         * 0预定价； 1 申请价
         */
        private Integer applyType;
        /**
         * 出票速度，单位分钟（Ctrip不用传，Qunar可以传）
         */
        private Integer ticketTimeUnit;
        /**
         * 小团报价，0普通价/1小团报价（Ctrip不用传，Qunar可以传）
         */
        private Integer tuanType;
        /**
         * 拼接报价拼接点（Ctrip不用传，Qunar可以传）
         */
        private List<String> combineIndexs;
        /**
         *是否支持签转，0表示不支持，1表示支持（Ctrip不用传，Qunar必须传）
         * 默认为0
         */
        private Integer endorsement;
        /**
         *扩展字段（Ctrip不用传，Qunar可以传）
         */
        private Map<String, String> extraInfo;
        /**
         *乘客允许国籍类型，0全部，1适用部分国籍，2不适用部分国籍，与nationality配合使用
         * 限制国籍会造成价格属性劣势
         */
        private Integer nationalityType;



        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<Segment> getFromSegments() {
            return fromSegments;
        }

        public void setFromSegments(List<Segment> fromSegments) {
            this.fromSegments = fromSegments;
        }

        public List<Segment> getRetSegments() {
            return retSegments;
        }

        public void setRetSegments(List<Segment> retSegments) {
            this.retSegments = retSegments;
        }

        public PackageInfo getPackageInfo() {
            return packageInfo;
        }

        public void setPackageInfo(PackageInfo packageInfo) {
            this.packageInfo = packageInfo;
        }

        public List<Price> getPriceList() {
            return priceList;
        }

        public void setPriceList(List<Price> priceList) {
            this.priceList = priceList;
        }

        public String getEligibility() {
            return eligibility;
        }

        public void setEligibility(String eligibility) {
            this.eligibility = eligibility;
        }

        public String getValidatingCarrier() {
            return validatingCarrier;
        }

        public void setValidatingCarrier(String validatingCarrier) {
            this.validatingCarrier = validatingCarrier;
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

        public Boolean getIsUseCtripRule() {
            return isUseCtripRule;
        }

        public void setIsUseCtripRule(Boolean isUseCtripRule) {
            this.isUseCtripRule = isUseCtripRule;
        }

        public String getTariffNo() {
            return tariffNo;
        }

        public void setTariffNo(String tariffNo) {
            this.tariffNo = tariffNo;
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

        public String getReservationType() {
            return reservationType;
        }

        public void setReservationType(String reservationType) {
            this.reservationType = reservationType;
        }

        public String getPosCode() {
            return posCode;
        }

        public void setPosCode(String posCode) {
            this.posCode = posCode;
        }

        public Integer getComplexTerm() {
            return complexTerm;
        }

        public void setComplexTerm(Integer complexTerm) {
            this.complexTerm = complexTerm;
        }

        public Integer getMinAge() {
            return minAge;
        }

        public void setMinAge(Integer minAge) {
            this.minAge = minAge;
        }

        public Integer getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Integer maxAge) {
            this.maxAge = maxAge;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
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

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public AirlineAncillaries getAirlineAncillaries() {
            return airlineAncillaries;
        }

        public void setAirlineAncillaries(AirlineAncillaries airlineAncillaries) {
            this.airlineAncillaries = airlineAncillaries;
        }

        public List<FormatBaggageDetail> getFormatBaggageDetailList() {
            return formatBaggageDetailList;
        }

        public void setFormatBaggageDetailList(List<FormatBaggageDetail> formatBaggageDetailList) {
            this.formatBaggageDetailList = formatBaggageDetailList;
        }

        public List<RefundInfo> getRefundInfoList() {
            return refundInfoList;
        }

        public void setRefundInfoList(List<RefundInfo> refundInfoList) {
            this.refundInfoList = refundInfoList;
        }

        public List<ChangesInfo> getChangesInfoList() {
            return changesInfoList;
        }

        public void setChangesInfoList(List<ChangesInfo> changesInfoList) {
            this.changesInfoList = changesInfoList;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getApplyType() {
            return applyType;
        }

        public void setApplyType(Integer applyType) {
            this.applyType = applyType;
        }

        public Integer getTicketTimeUnit() {
            return ticketTimeUnit;
        }

        public void setTicketTimeUnit(Integer ticketTimeUnit) {
            this.ticketTimeUnit = ticketTimeUnit;
        }

        public Integer getTuanType() {
            return tuanType;
        }

        public void setTuanType(Integer tuanType) {
            this.tuanType = tuanType;
        }

        public List<String> getCombineIndexs() {
            return combineIndexs;
        }

        public void setCombineIndexs(List<String> combineIndexs) {
            this.combineIndexs = combineIndexs;
        }

        public Integer getEndorsement() {
            return endorsement;
        }

        public void setEndorsement(Integer endorsement) {
            this.endorsement = endorsement;
        }

        public Map<String, String> getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(Map<String, String> extraInfo) {
            this.extraInfo = extraInfo;
        }

        public Integer getNationalityType() {
            return nationalityType;
        }

        public void setNationalityType(Integer nationalityType) {
            this.nationalityType = nationalityType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Routing)) {
                return false;
            }

            Routing routing = (Routing) o;

            return new EqualsBuilder().append(getData(), routing.getData())
                    .append(getFromSegments(), routing.getFromSegments())
                    .append(getRetSegments(), routing.getRetSegments())
                    .append(getPackageInfo(), routing.getPackageInfo()).append(getPriceList(), routing.getPriceList())
                    .append(getEligibility(), routing.getEligibility())
                    .append(getValidatingCarrier(), routing.getValidatingCarrier())
                    .append(getProductType(), routing.getProductType()).append(getFareBasis(), routing.getFareBasis())
                    .append(getIsUseCtripRule(), routing.getIsUseCtripRule())
                    .append(getTariffNo(), routing.getTariffNo())
                    .append(getBookingOfficeNo(), routing.getBookingOfficeNo())
                    .append(getTicketingOfficeNo(), routing.getTicketingOfficeNo())
                    .append(getReservationType(), routing.getReservationType())
                    .append(getPosCode(), routing.getPosCode())
                    .append(getComplexTerm(), routing.getComplexTerm()).append(getMinAge(), routing.getMinAge())
                    .append(getMaxAge(), routing.getMaxAge()).append(getNationality(), routing.getNationality())
                    .append(getPlanCategory(), routing.getPlanCategory())
                    .append(getInvoiceType(), routing.getInvoiceType())
                    .append(getMinPassengerCount(), routing.getMinPassengerCount())
                    .append(getMaxPassengerCount(), routing.getMaxPassengerCount()).append(getNote(), routing.getNote())
                    .append(getAirlineAncillaries(), routing.getAirlineAncillaries())
                    .append(getFormatBaggageDetailList(), routing.getFormatBaggageDetailList())
                    .append(getRefundInfoList(), routing.getRefundInfoList())
                    .append(getChangesInfoList(), routing.getChangesInfoList())
                    .append(getCurrency(), routing.getCurrency())
                    .append(getApplyType(), routing.getApplyType())
                    .append(getTicketTimeUnit(), routing.getTicketTimeUnit())
                    .append(getTuanType(), routing.getTuanType()).append(getCombineIndexs(), routing.getCombineIndexs())
                    .append(getEndorsement(), routing.getEndorsement()).append(getExtraInfo(), routing.getExtraInfo())
                    .append(getNationalityType(), routing.getNationalityType()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getData()).append(getFromSegments()).append(getRetSegments())
                    .append(getPackageInfo()).append(getPriceList()).append(getEligibility())
                    .append(getValidatingCarrier())
                    .append(getProductType()).append(getFareBasis()).append(getIsUseCtripRule()).append(getTariffNo())
                    .append(getBookingOfficeNo()).append(getTicketingOfficeNo()).append(getReservationType())
                    .append(getPosCode()).append(getComplexTerm()).append(getMinAge()).append(getMaxAge())
                    .append(getNationality()).append(getPlanCategory()).append(getInvoiceType())
                    .append(getMinPassengerCount())
                    .append(getMaxPassengerCount()).append(getNote()).append(getAirlineAncillaries())
                    .append(getFormatBaggageDetailList()).append(getRefundInfoList()).append(getChangesInfoList())
                    .append(getCurrency()).append(getApplyType()).append(getTicketTimeUnit()).append(getTuanType())
                    .append(getCombineIndexs()).append(getEndorsement()).append(getExtraInfo())
                    .append(getNationalityType())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("data", data)
                    .append("fromSegments", fromSegments)
                    .append("retSegments", retSegments)
                    .append("packageInfo", packageInfo)
                    .append("priceList", priceList)
                    .append("eligibility", eligibility)
                    .append("validatingCarrier", validatingCarrier)
                    .append("productType", productType)
                    .append("fareBasis", fareBasis)
                    .append("isUseCtripRule", isUseCtripRule)
                    .append("tariffNo", tariffNo)
                    .append("bookingOfficeNo", bookingOfficeNo)
                    .append("ticketingOfficeNo", ticketingOfficeNo)
                    .append("reservationType", reservationType)
                    .append("posCode", posCode)
                    .append("complexTerm", complexTerm)
                    .append("minAge", minAge)
                    .append("maxAge", maxAge)
                    .append("nationality", nationality)
                    .append("planCategory", planCategory)
                    .append("invoiceType", invoiceType)
                    .append("minPassengerCount", minPassengerCount)
                    .append("maxPassengerCount", maxPassengerCount)
                    .append("note", note)
                    .append("airlineAncillaries", airlineAncillaries)
                    .append("formatBaggageDetailList", formatBaggageDetailList)
                    .append("refundInfoList", refundInfoList)
                    .append("changesInfoList", changesInfoList)
                    .append("currency", currency)
                    .append("applyType", applyType)
                    .append("ticketTimeUnit", ticketTimeUnit)
                    .append("tuanType", tuanType)
                    .append("combineIndexs", combineIndexs)
                    .append("endorsement", endorsement)
                    .append("extraInfo", extraInfo)
                    .append("nationalityType", nationalityType)
                    .toString();
        }
    }

    public static class Segment implements Serializable {
        private static final long serialVersionUID = -5921511131021773766L;
        /**
         * 票面航司 IATA 二字码
         */
        private String marketingCarrier;
        /**
         * 出发机场；IATA 三字码
         */
        private String depAirport;
        /**
         * 起飞日期时间（当地时间），格式：YYYYMMDDHHMM 例：201203100300 表示 2012 年 3 月 10 日 3 时 0 分
         */
        private String depTime;
        /**
         * 到达机场 IATA 三字码
         */
        private String arrAirport;
        /**
         * 到达日期时间（当地时间），格式：YYYYMMDDHHMM 例：201203101305 表示 2012 年 3 月 10 日 13 时 5 分
         */
        private String arrTime;
        /**
         * 子舱位
         */
        private String seatClass;
        /**
         * 航班号，如：CA123
         */
        private String flightNumber;
        /**
         * 代码共享标识（true 代码共享/false 非代码共享）
         */
        private Boolean codeShare;
        /**
         * 机型 ，IATA标准3字码
         */
        private String aircraftCode;
        /**
         * 实际承运航司（Ctrip用，Qunar不用）
         */
        private String operatingCarrier;
        /**
         * 实际承运航班号（Ctrip用，Qunar不用）
         */
        private String operatingFlightNo;
        /**
         * 属于第几个用户段，同group（Ctrip不用，Qunar用）
         */
        private Integer segmentNo;
        /**
         * 经停城市，多个用/分开（Ctrip不用，Qunar用）
         */
        private String stopCities;

        public String getMarketingCarrier() {
            return marketingCarrier;
        }

        public void setMarketingCarrier(String marketingCarrier) {
            this.marketingCarrier = marketingCarrier;
        }

        public String getDepAirport() {
            return depAirport;
        }

        public void setDepAirport(String depAirport) {
            this.depAirport = depAirport;
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

        public String getArrTime() {
            return arrTime;
        }

        public void setArrTime(String arrTime) {
            this.arrTime = arrTime;
        }

        public String getSeatClass() {
            return seatClass;
        }

        public void setSeatClass(String seatClass) {
            this.seatClass = seatClass;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }

        public Boolean getCodeShare() {
            return codeShare;
        }

        public void setCodeShare(Boolean codeShare) {
            this.codeShare = codeShare;
        }

        public String getAircraftCode() {
            return aircraftCode;
        }

        public void setAircraftCode(String aircraftCode) {
            this.aircraftCode = aircraftCode;
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

        public Integer getSegmentNo() {
            return segmentNo;
        }

        public void setSegmentNo(Integer segmentNo) {
            this.segmentNo = segmentNo;
        }

        public String getStopCities() {
            return stopCities;
        }

        public void setStopCities(String stopCities) {
            this.stopCities = stopCities;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Segment)) {
                return false;
            }

            Segment segment = (Segment) o;

            return new EqualsBuilder()
                    .append(getMarketingCarrier(), segment.getMarketingCarrier())
                    .append(getDepAirport(), segment.getDepAirport()).append(getDepTime(), segment.getDepTime())
                    .append(getArrAirport(), segment.getArrAirport()).append(getArrTime(), segment.getArrTime())
                    .append(getSeatClass(), segment.getSeatClass()).append(getFlightNumber(), segment.getFlightNumber())
                    .append(getCodeShare(), segment.getCodeShare()).append(getAircraftCode(), segment.getAircraftCode())
                    .append(getOperatingCarrier(), segment.getOperatingCarrier())
                    .append(getOperatingFlightNo(), segment.getOperatingFlightNo())
                    .append(getSegmentNo(), segment.getSegmentNo()).append(getStopCities(), segment.getStopCities())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getMarketingCarrier()).append(getDepAirport())
                    .append(getDepTime())
                    .append(getArrAirport()).append(getArrTime()).append(getSeatClass()).append(getFlightNumber())
                    .append(getCodeShare()).append(getAircraftCode()).append(getOperatingCarrier())
                    .append(getOperatingFlightNo()).append(getSegmentNo()).append(getStopCities()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("marketingCarrier", marketingCarrier)
                    .append("depAirport", depAirport)
                    .append("depTime", depTime)
                    .append("arrAirport", arrAirport)
                    .append("arrTime", arrTime)
                    .append("seatClass", seatClass)
                    .append("flightNumber", flightNumber)
                    .append("codeShare", codeShare)
                    .append("aircraftCode", aircraftCode)
                    .append("operatingCarrier", operatingCarrier)
                    .append("operatingFlightNo", operatingFlightNo)
                    .append("segmentNo", segmentNo)
                    .append("stopCities", stopCities)
                    .toString();
        }
    }

    public static class Passenger implements Serializable{
        private static final long serialVersionUID = -2157940385068177620L;
        /**
         * LastName/FirstName MiddleName，姓/名
         */
        private String name;
        /**
         * 乘客类型，0 成人/1 儿童/2 婴儿
         */
        private Integer ageType;
        /**
         * 生日，格式：YYYYMMDD
         */
        private String birthday;
        /**
         * 乘客性别，M 男 / F 女
         */
        private String gender;
        /**
         * 证件号码，最大 15 个字符
         */
        private String cardNum;
        /**
         * 证件类型： PP - 护照 GA - 港澳通行证 TW - 台湾通行证 TB - 台胞证（台湾往返内地通行证） HX - 回乡证（港澳往返内地通行证） HY - 国际海员证
         */
        private String cardType;

        /**
         * 证件发行国家，国家二字码
         */
        private String cardIssuePlace;
        /**
         * 证件有效时间，格式：YYYYMMDD
         */
        private String cardExpired;
        /**
         * 乘客国籍，国家二字码
         */
        private String nationality;
        /**
         * 常 旅 客 信 息
         */
        private FFPNo ffpNo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAgeType() {
            return ageType;
        }

        public void setAgeType(Integer ageType) {
            this.ageType = ageType;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardIssuePlace() {
            return cardIssuePlace;
        }

        public void setCardIssuePlace(String cardIssuePlace) {
            this.cardIssuePlace = cardIssuePlace;
        }

        public String getCardExpired() {
            return cardExpired;
        }

        public void setCardExpired(String cardExpired) {
            this.cardExpired = cardExpired;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public FFPNo getFfpNo() {
            return ffpNo;
        }

        public void setFfpNo(FFPNo ffpNo) {
            this.ffpNo = ffpNo;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Passenger)) {
                return false;
            }

            Passenger passenger = (Passenger) o;

            return new EqualsBuilder().append(getName(), passenger.getName())
                    .append(getAgeType(), passenger.getAgeType()).append(getBirthday(), passenger.getBirthday())
                    .append(getGender(), passenger.getGender()).append(getCardNum(), passenger.getCardNum())
                    .append(getCardType(), passenger.getCardType())
                    .append(getCardIssuePlace(), passenger.getCardIssuePlace())
                    .append(getCardExpired(), passenger.getCardExpired())
                    .append(getNationality(), passenger.getNationality())
                    .append(getFfpNo(), passenger.getFfpNo()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getName()).append(getAgeType()).append(getBirthday())
                    .append(getGender())
                    .append(getCardNum()).append(getCardType()).append(getCardIssuePlace()).append(getCardExpired())
                    .append(getNationality()).append(getFfpNo()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("name", name)
                    .append("ageType", ageType)
                    .append("birthday", birthday)
                    .append("gender", gender)
                    .append("cardNum", cardNum)
                    .append("cardType", cardType)
                    .append("cardIssuePlace", cardIssuePlace)
                    .append("cardExpired", cardExpired)
                    .append("nationality", nationality)
                    .append("ffpNo", ffpNo)
                    .toString();
        }
    }

    public static class FFPNo implements Serializable{
        private static final long serialVersionUID = 497131526957609402L;
        /**
         * 常旅客卡号
         */
        private String cardNo;
        /**
         * 常旅客卡航司
         */
        private String carrier;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof FFPNo)) {
                return false;
            }

            FFPNo ffpNo = (FFPNo) o;

            return new EqualsBuilder().append(getCardNo(), ffpNo.getCardNo())
                    .append(getCarrier(), ffpNo.getCarrier()).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getCardNo()).append(getCarrier()).toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("cardNo", cardNo)
                    .append("carrier", carrier)
                    .toString();
        }
    }

    public static class Contact {
        /**
         * 联系人姓名，不单独区分姓和名
         */
        private String name;
        /**
         * 详细地址
         */
        private String address;
        /**
         * 邮编
         */
        private String postcode;
        /**
         * 联系人邮箱
         */
        private String email;
        /**
         * 联系人手机号
         */
        private String mobile;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class PassengerBaggage {
        /**
         * LastName/FirstName MiddleName，姓/名
         */
        private String passengerName;
        /**
         * 行李信息集合参考 payBaggages Element （增值服务查询接口中的行李额节点）
         */
        private List<PayBaggage> payBaggages;

        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public List<PayBaggage> getPayBaggages() {
            return payBaggages;
        }

        public void setPayBaggages(List<PayBaggage> payBaggages) {
            this.payBaggages = payBaggages;
        }
    }

    public static class PayBaggage {
        /**
         * 航班号
         */
        private String flight;
        /**
         * 出发机场
         */
        private String fromAirport;
        /**
         * 到达机场
         */
        private String toAirport;
        /**
         * 起飞日期时间，格式：YYYYMMDDHHMM
         * 例：201203101305 表示 2012 年 3 月 10 日 13 时 5 分
         */
        private String depTime;
        /**
         * 舱位
         */
        private String cabin;
        /**
         *购买价格 集合
         */
        private List<BaggagePrice> baggagePrices;

        public String getFlight() {
            return flight;
        }

        public void setFlight(String flight) {
            this.flight = flight;
        }

        public String getFromAirport() {
            return fromAirport;
        }

        public void setFromAirport(String fromAirport) {
            this.fromAirport = fromAirport;
        }

        public String getToAirport() {
            return toAirport;
        }

        public void setToAirport(String toAirport) {
            this.toAirport = toAirport;
        }

        public String getDepTime() {
            return depTime;
        }

        public void setDepTime(String depTime) {
            this.depTime = depTime;
        }

        public String getCabin() {
            return cabin;
        }

        public void setCabin(String cabin) {
            this.cabin = cabin;
        }

        public List<BaggagePrice> getBaggagePrices() {
            return baggagePrices;
        }

        public void setBaggagePrices(List<BaggagePrice> baggagePrices) {
            this.baggagePrices = baggagePrices;
        }
    }

    public static class BaggagePrice {
        /**
         *行李件数
         */
        private Integer pkgNumber;
        /**
         *行李单件重量
         */
        private String weight;
        /**
         *预订购买价【CNY】
         */
        private BigDecimal bookSalePrice;
        /**
         *出票后购买价 【CNY】
         */
        private BigDecimal ticketSalePrice;
        /**
         *机场购买价 【CNY】
         */
        private BigDecimal airportSalePrice;
        /**
         *退行李费【如果退行李，请提供退的费用】【CNY】
         */
        private BigDecimal refundFee;
        /**
         *服务描述【可以包括备注，重量，件数说明
         */
        private String desc;

        public Integer getPkgNumber() {
            return pkgNumber;
        }

        public void setPkgNumber(Integer pkgNumber) {
            this.pkgNumber = pkgNumber;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public BigDecimal getBookSalePrice() {
            return bookSalePrice;
        }

        public void setBookSalePrice(BigDecimal bookSalePrice) {
            this.bookSalePrice = bookSalePrice;
        }

        public BigDecimal getTicketSalePrice() {
            return ticketSalePrice;
        }

        public void setTicketSalePrice(BigDecimal ticketSalePrice) {
            this.ticketSalePrice = ticketSalePrice;
        }

        public BigDecimal getAirportSalePrice() {
            return airportSalePrice;
        }

        public void setAirportSalePrice(BigDecimal airportSalePrice) {
            this.airportSalePrice = airportSalePrice;
        }

        public BigDecimal getRefundFee() {
            return refundFee;
        }

        public void setRefundFee(BigDecimal refundFee) {
            this.refundFee = refundFee;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class BookingValueAddRequest {
        /**
         * 增 值 服 务 购 物 车
         */
        private List<ShoppingCart> shoppingCarts;
        /**
         * 在线值机服务产品列表
         */
        private List<OnlineCheckInDetail> onlineCheckInDetail;

        public List<ShoppingCart> getShoppingCarts() {
            return shoppingCarts;
        }

        public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
            this.shoppingCarts = shoppingCarts;
        }

        public List<OnlineCheckInDetail> getOnlineCheckInDetail() {
            return onlineCheckInDetail;
        }

        public void setOnlineCheckInDetail(List<OnlineCheckInDetail> onlineCheckInDetail) {
            this.onlineCheckInDetail = onlineCheckInDetail;
        }
    }
    public static class ShoppingCart {
        /**
         *LastName/FirstName MiddleName，姓/名
         */
        private String passengerName;
        /**
         * 选 购 的 产 品 列 表
         */
        private List<Product> products;

        public String getPassengerName() {
            return passengerName;
        }

        public void setPassengerName(String passengerName) {
            this.passengerName = passengerName;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
    public static class OnlineCheckInDetail {
        /**
         *开 放 值 机 时 间 ， 格 式 ：yyyyMMddHHmm 例 ：201203101305 表示 2012 年 3 月10 日 13 时 5 分
         */
        private String openCheckInTime;
        /**
         *最小值机人年龄
         */
        private Integer minAge;
        /**
         *底价
         */
        private BigDecimal costPrice;
        /**
         *产品 Id
         */
        private String id;
        /**
         *航班号
         */
        private String flight;
        /**
         *出发机场
         */
        private String fromAirport;
        /**
         *到达机场
         */
        private String toAirport;
        /**
         *起 飞 日 期 时 间 ， 格 式 ：yyyyMMddHHmm 例 ：201203101305 表示 2012 年 3 月10 日 13 时 5 分
         */
        private String depTime;

        public String getOpenCheckInTime() {
            return openCheckInTime;
        }

        public void setOpenCheckInTime(String openCheckInTime) {
            this.openCheckInTime = openCheckInTime;
        }

        public Integer getMinAge() {
            return minAge;
        }

        public void setMinAge(Integer minAge) {
            this.minAge = minAge;
        }

        public BigDecimal getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(BigDecimal costPrice) {
            this.costPrice = costPrice;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFlight() {
            return flight;
        }

        public void setFlight(String flight) {
            this.flight = flight;
        }

        public String getFromAirport() {
            return fromAirport;
        }

        public void setFromAirport(String fromAirport) {
            this.fromAirport = fromAirport;
        }

        public String getToAirport() {
            return toAirport;
        }

        public void setToAirport(String toAirport) {
            this.toAirport = toAirport;
        }

        public String getDepTime() {
            return depTime;
        }

        public void setDepTime(String depTime) {
            this.depTime = depTime;
        }
    }
    public static class Product {
        /**
         *产 品 ID ， 值 机 服 务 映 射OnlineCheckInDetail.Id
         */
        private String productId;
        /**
         *产品类型 1：值机服务
         */
        private Integer type;
        /**
         *购买数量
         */
        private Integer count;
        /**
         *定制化产品附加信息
         */
        private String tag;
        /**
         *处理状态 0： 待处理 1：处理成功 2：处理失败
         */
        private Integer processStatus;
        /**
         *处理结果详细信息
         */
        private String processDetail;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Integer getProcessStatus() {
            return processStatus;
        }

        public void setProcessStatus(Integer processStatus) {
            this.processStatus = processStatus;
        }

        public String getProcessDetail() {
            return processDetail;
        }

        public void setProcessDetail(String processDetail) {
            this.processDetail = processDetail;
        }
    }
    public static class ValueAddResult {
        /**
         *增 值 服 务 预 定 结 果 预定成功或者失败，需要修改 processStatus 的状态
         */
        private List<ShoppingCart> shoppingCarts;

        public List<ShoppingCart> getShoppingCarts() {
            return shoppingCarts;
        }

        public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
            this.shoppingCarts = shoppingCarts;
        }
    }
}
