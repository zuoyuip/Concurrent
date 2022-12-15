package org.zuoyu.concurrent.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * GDS政策对象 cc_gds_policy
 *
 * @author hmydts
 * @date 2022-09-13
 */
public class GdsPolicy implements Serializable {

	private static final long serialVersionUID = -4799933021609378707L;
	/** 政策ID */
	private Long id;

	/** 批次号 */
	private String batchNo;

	/** 原始id */
	private Long originId;

	/** ota类型 */
	private String otaType;

	/** 投放平台二级子类 */
	private String childOtaType;

	/** $column.columnComment */
	private Integer policyType;

	/** 开票航司 */
	private String tktAirline;

	/** 行程类型 */
	private Integer tripType;

	/** 出发地 */
	private String org;

	/** 目的地 */
	private String des;

	/** 出发地除外 */
	private String exOrg;

	/** 目的地除外 */
	private String exDes;

	/** 适用舱位 */
	private String cabin;

	/** 舱位等级 Y-经济  C-商务 F-头等 */
	private String cabinClass;

	/** 舱位排除 */
	private String exCabin;

	/** 是否联运 */
	private Integer isTransport;

	/** 适用联运航司 */
	private String transporAir;

	/** 除外联运航司 */
	private String exTransporAirline;

	/** 是否适用中转 */
	private Integer isTransfer;

	/** 指定转机点 */
	private String transferPoint;

	/** 是否共享航班 */
	private Integer isShareFlight;

	/** 去程航班开始日期 */
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date goFlightDateStart;

	/** 去程航班结束日期 */
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date goFlightDateEnd;

	/** 回程航班开始日期 */
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date retFlightDateStart;

	/** 回程航班结束日期 */
	@JSONField(format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date retFlightDateEnd;

	/** 销售开始日期 */
	private String salesDateStart;

	/** 销售结束日期 */
	private String salesDateEnd;

	/** 成人返点 */
	private Long rate;

	/** 成人留钱 */
	private Long amount;

	/** 工作时间 */
	private String workTime;

	/** 1/2RT 0:否，1:是 */
	private Integer isAllowRt;

	/** 提前出票时限 */
	private Long advanceTicketingDeadline;

	/** 创建人ID */
	private String createUserId;

	/** 创建人 */
	private String createUserName;

	/** 指定航班号 */
	private String flightNos;

	/** 状态1：可用，2：不可用 */
	private Integer status;

	/** 最晚出票时限 */
	private Long lastIssueDeadline;

	/** 最长停留天数 */
	private String longestStopDay;

	/** 最短停留天数 */
	private String shortestStopDay;

	/** 去程班期限制 */
	private String toFlightLimit;

	/** 回程班期限制 */
	private String reFlightLimit;

	/** gdsCabinInfo的字符串 */
	private String cabinInfosStr;

	/** 航班号排除 */
	private String exFlightNos;

	/** 乘客年龄限制 格式：20-25 */
	private String passengerAgeLimit;

	/** 出票备注 */
	private String ticketingRemark;

	/** 适用共享航司 */
	private String shareAirline;

	/** 退改规则 */
	private Integer returned;

	/** 预定系统(Eterm/Sabre/Abacus/Galileo/Worldspan/Amadues/官网/GDS) */
	private String gdsType;

	/** 回程指定航班号 */
	private String reFlightNos;

	/** 回程排除航班号 */
	private String exReFlightNos;

	/** 是否精确比价 YesOrNoOrAllEnum */
	private Integer isExactParity;

	/** 热门航线标识 */
	private Integer isHot;

	/** 去程排除日期 */
	private String toExceptDate;

	/** 回程排除日期 */
	private String returnExceptDate;

	/** 往返时间间隔（天） */
	private Integer timeInterval;

	/** 政策备注 */
	private String policyRemark;

	/** 比价日期范围 */
	private String pkPriceDate;

	/** 是否包机:0非，1是 默认是0 */
	private Integer isCharter;

	/** 是否特殊运价标识 1-是 2-否 */
	private Integer isSpecialFare;

	/** 出发地机场 */
	private String orgAirport;

	/** 目的地机场 */
	private String desAirport;

	/** 出发地除外机场 */
	private String exOrgAirport;

	/** 目的地除外机场 */
	private String exDesAirport;

	/** 调价航线  */
	private String modifyPriceFlightLine;

	/** 调价总价 */
	private Long modifyPriceCount;

	/** 调价范围 */
	private String modifyPriceRange;

	/** 每次调价金额 */
	private Long modifyPricePerTime;

	/** 是否使用默认退改 */
	private Integer isDefaultRule;

	/** 去程可否更改 */
	private Integer isChangeOut;

	/** 去程改签费用 */
	private String changeFeeOut;

	/** 回程可否更改 */
	private Integer isChangeReturn;

	/** 回程改期费用 */
	private String changeFeeReturn;

	/** 是否允许NOSHOW改签 */
	private Integer isNoshowChangeOut;

	/** NOSHOW改期条件 */
	private String noshowChangeCondition;

	/** 去程NOSHOW改期费用 */
	private String noshowChangeFeeOut;

	/** 回程NOSHOW改期费用 */
	private String noshowChangeFeeReturn;

	/** 全部未使用可否退票 */
	private Integer isCancelAll;

	/** 全部未使用退票费用 */
	private String cancelFeeAll;

	/** 部分未使用可否退票 */
	private Integer isCancelPart;

	/** 部分未使用退票费 */
	private String cancelFeePart;

	/** 是否允许NOSHOW退票 */
	private Integer isNoshowCancel;

	/** NOSHOW退票条件 */
	private String noshowCancelCondition;

	/** NOSHOW全部未使用退票费用 */
	private String noshowCancelFeeAll;

	/** NOSHOW部分未使用退票费用 */
	private String noshowCancelFeePart;

	/** NOSHOW罚金 */
	private String noShowAmount;

	/** 公司类型 */
	private String companyType;

	/** 报销凭证 */
	private Integer invoiceType;

	/** 退税金额 */
	private String cancelTax;

	/** 乘客人数限制最小数 */
	private Integer passengerNumMin;

	/** 乘客人数限制最大数 */
	private Integer passengerNumLimit;

	/** 境外、境内渠道 */
	private String channel;

	/** PCC */
	private String pcc;

	/** 是否使用航司默认退改签规则 */
	private Integer isUseCarrierRule;

	/** 成人去程行李额 */
	private String adtBaggageOut;

	/** 成人回程行李额 */
	private String adtBaggageReturn;

	/** 儿童去程行李额 */
	private String chdBaggageOut;

	/** 儿童回程行李额 */
	private String chdBaggageReturn;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Long getOriginId() {
		return originId;
	}

	public void setOriginId(Long originId) {
		this.originId = originId;
	}

	public String getOtaType() {
		return otaType;
	}

	public void setOtaType(String otaType) {
		this.otaType = otaType;
	}

	public String getChildOtaType() {
		return childOtaType;
	}

	public void setChildOtaType(String childOtaType) {
		this.childOtaType = childOtaType;
	}

	public Integer getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}

	public String getTktAirline() {
		return tktAirline;
	}

	public void setTktAirline(String tktAirline) {
		this.tktAirline = tktAirline;
	}

	public Integer getTripType() {
		return tripType;
	}

	public void setTripType(Integer tripType) {
		this.tripType = tripType;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getExOrg() {
		return exOrg;
	}

	public void setExOrg(String exOrg) {
		this.exOrg = exOrg;
	}

	public String getExDes() {
		return exDes;
	}

	public void setExDes(String exDes) {
		this.exDes = exDes;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	public String getExCabin() {
		return exCabin;
	}

	public void setExCabin(String exCabin) {
		this.exCabin = exCabin;
	}

	public Integer getIsTransport() {
		return isTransport;
	}

	public void setIsTransport(Integer isTransport) {
		this.isTransport = isTransport;
	}

	public String getTransporAir() {
		return transporAir;
	}

	public void setTransporAir(String transporAir) {
		this.transporAir = transporAir;
	}

	public String getExTransporAirline() {
		return exTransporAirline;
	}

	public void setExTransporAirline(String exTransporAirline) {
		this.exTransporAirline = exTransporAirline;
	}

	public Integer getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(Integer isTransfer) {
		this.isTransfer = isTransfer;
	}

	public String getTransferPoint() {
		return transferPoint;
	}

	public void setTransferPoint(String transferPoint) {
		this.transferPoint = transferPoint;
	}

	public Integer getIsShareFlight() {
		return isShareFlight;
	}

	public void setIsShareFlight(Integer isShareFlight) {
		this.isShareFlight = isShareFlight;
	}

	public Date getGoFlightDateStart() {
		return goFlightDateStart;
	}

	public void setGoFlightDateStart(Date goFlightDateStart) {
		this.goFlightDateStart = goFlightDateStart;
	}

	public Date getGoFlightDateEnd() {
		return goFlightDateEnd;
	}

	public void setGoFlightDateEnd(Date goFlightDateEnd) {
		this.goFlightDateEnd = goFlightDateEnd;
	}

	public Date getRetFlightDateStart() {
		return retFlightDateStart;
	}

	public void setRetFlightDateStart(Date retFlightDateStart) {
		this.retFlightDateStart = retFlightDateStart;
	}

	public Date getRetFlightDateEnd() {
		return retFlightDateEnd;
	}

	public void setRetFlightDateEnd(Date retFlightDateEnd) {
		this.retFlightDateEnd = retFlightDateEnd;
	}

	public String getSalesDateStart() {
		return salesDateStart;
	}

	public void setSalesDateStart(String salesDateStart) {
		this.salesDateStart = salesDateStart;
	}

	public String getSalesDateEnd() {
		return salesDateEnd;
	}

	public void setSalesDateEnd(String salesDateEnd) {
		this.salesDateEnd = salesDateEnd;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public Integer getIsAllowRt() {
		return isAllowRt;
	}

	public void setIsAllowRt(Integer isAllowRt) {
		this.isAllowRt = isAllowRt;
	}

	public Long getAdvanceTicketingDeadline() {
		return advanceTicketingDeadline;
	}

	public void setAdvanceTicketingDeadline(Long advanceTicketingDeadline) {
		this.advanceTicketingDeadline = advanceTicketingDeadline;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getFlightNos() {
		return flightNos;
	}

	public void setFlightNos(String flightNos) {
		this.flightNos = flightNos;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLastIssueDeadline() {
		return lastIssueDeadline;
	}

	public void setLastIssueDeadline(Long lastIssueDeadline) {
		this.lastIssueDeadline = lastIssueDeadline;
	}

	public String getLongestStopDay() {
		return longestStopDay;
	}

	public void setLongestStopDay(String longestStopDay) {
		this.longestStopDay = longestStopDay;
	}

	public String getShortestStopDay() {
		return shortestStopDay;
	}

	public void setShortestStopDay(String shortestStopDay) {
		this.shortestStopDay = shortestStopDay;
	}

	public String getToFlightLimit() {
		return toFlightLimit;
	}

	public void setToFlightLimit(String toFlightLimit) {
		this.toFlightLimit = toFlightLimit;
	}

	public String getReFlightLimit() {
		return reFlightLimit;
	}

	public void setReFlightLimit(String reFlightLimit) {
		this.reFlightLimit = reFlightLimit;
	}

	public String getCabinInfosStr() {
		return cabinInfosStr;
	}

	public void setCabinInfosStr(String cabinInfosStr) {
		this.cabinInfosStr = cabinInfosStr;
	}

	public String getExFlightNos() {
		return exFlightNos;
	}

	public void setExFlightNos(String exFlightNos) {
		this.exFlightNos = exFlightNos;
	}

	public String getPassengerAgeLimit() {
		return passengerAgeLimit;
	}

	public void setPassengerAgeLimit(String passengerAgeLimit) {
		this.passengerAgeLimit = passengerAgeLimit;
	}

	public String getTicketingRemark() {
		return ticketingRemark;
	}

	public void setTicketingRemark(String ticketingRemark) {
		this.ticketingRemark = ticketingRemark;
	}

	public String getShareAirline() {
		return shareAirline;
	}

	public void setShareAirline(String shareAirline) {
		this.shareAirline = shareAirline;
	}

	public Integer getReturned() {
		return returned;
	}

	public void setReturned(Integer returned) {
		this.returned = returned;
	}

	public String getGdsType() {
		return gdsType;
	}

	public void setGdsType(String gdsType) {
		this.gdsType = gdsType;
	}

	public String getReFlightNos() {
		return reFlightNos;
	}

	public void setReFlightNos(String reFlightNos) {
		this.reFlightNos = reFlightNos;
	}

	public String getExReFlightNos() {
		return exReFlightNos;
	}

	public void setExReFlightNos(String exReFlightNos) {
		this.exReFlightNos = exReFlightNos;
	}

	public Integer getIsExactParity() {
		return isExactParity;
	}

	public void setIsExactParity(Integer isExactParity) {
		this.isExactParity = isExactParity;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public String getToExceptDate() {
		return toExceptDate;
	}

	public void setToExceptDate(String toExceptDate) {
		this.toExceptDate = toExceptDate;
	}

	public String getReturnExceptDate() {
		return returnExceptDate;
	}

	public void setReturnExceptDate(String returnExceptDate) {
		this.returnExceptDate = returnExceptDate;
	}

	public Integer getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(Integer timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getPolicyRemark() {
		return policyRemark;
	}

	public void setPolicyRemark(String policyRemark) {
		this.policyRemark = policyRemark;
	}

	public String getPkPriceDate() {
		return pkPriceDate;
	}

	public void setPkPriceDate(String pkPriceDate) {
		this.pkPriceDate = pkPriceDate;
	}

	public Integer getIsCharter() {
		return isCharter;
	}

	public void setIsCharter(Integer isCharter) {
		this.isCharter = isCharter;
	}

	public Integer getIsSpecialFare() {
		return isSpecialFare;
	}

	public void setIsSpecialFare(Integer isSpecialFare) {
		this.isSpecialFare = isSpecialFare;
	}

	public String getOrgAirport() {
		return orgAirport;
	}

	public void setOrgAirport(String orgAirport) {
		this.orgAirport = orgAirport;
	}

	public String getDesAirport() {
		return desAirport;
	}

	public void setDesAirport(String desAirport) {
		this.desAirport = desAirport;
	}

	public String getExOrgAirport() {
		return exOrgAirport;
	}

	public void setExOrgAirport(String exOrgAirport) {
		this.exOrgAirport = exOrgAirport;
	}

	public String getExDesAirport() {
		return exDesAirport;
	}

	public void setExDesAirport(String exDesAirport) {
		this.exDesAirport = exDesAirport;
	}

	public String getModifyPriceFlightLine() {
		return modifyPriceFlightLine;
	}

	public void setModifyPriceFlightLine(String modifyPriceFlightLine) {
		this.modifyPriceFlightLine = modifyPriceFlightLine;
	}

	public Long getModifyPriceCount() {
		return modifyPriceCount;
	}

	public void setModifyPriceCount(Long modifyPriceCount) {
		this.modifyPriceCount = modifyPriceCount;
	}

	public String getModifyPriceRange() {
		return modifyPriceRange;
	}

	public void setModifyPriceRange(String modifyPriceRange) {
		this.modifyPriceRange = modifyPriceRange;
	}

	public Long getModifyPricePerTime() {
		return modifyPricePerTime;
	}

	public void setModifyPricePerTime(Long modifyPricePerTime) {
		this.modifyPricePerTime = modifyPricePerTime;
	}

	public Integer getIsDefaultRule() {
		return isDefaultRule;
	}

	public void setIsDefaultRule(Integer isDefaultRule) {
		this.isDefaultRule = isDefaultRule;
	}

	public Integer getIsChangeOut() {
		return isChangeOut;
	}

	public void setIsChangeOut(Integer isChangeOut) {
		this.isChangeOut = isChangeOut;
	}

	public String getChangeFeeOut() {
		return changeFeeOut;
	}

	public void setChangeFeeOut(String changeFeeOut) {
		this.changeFeeOut = changeFeeOut;
	}

	public Integer getIsChangeReturn() {
		return isChangeReturn;
	}

	public void setIsChangeReturn(Integer isChangeReturn) {
		this.isChangeReturn = isChangeReturn;
	}

	public String getChangeFeeReturn() {
		return changeFeeReturn;
	}

	public void setChangeFeeReturn(String changeFeeReturn) {
		this.changeFeeReturn = changeFeeReturn;
	}

	public Integer getIsNoshowChangeOut() {
		return isNoshowChangeOut;
	}

	public void setIsNoshowChangeOut(Integer isNoshowChangeOut) {
		this.isNoshowChangeOut = isNoshowChangeOut;
	}

	public String getNoshowChangeCondition() {
		return noshowChangeCondition;
	}

	public void setNoshowChangeCondition(String noshowChangeCondition) {
		this.noshowChangeCondition = noshowChangeCondition;
	}

	public String getNoshowChangeFeeOut() {
		return noshowChangeFeeOut;
	}

	public void setNoshowChangeFeeOut(String noshowChangeFeeOut) {
		this.noshowChangeFeeOut = noshowChangeFeeOut;
	}

	public String getNoshowChangeFeeReturn() {
		return noshowChangeFeeReturn;
	}

	public void setNoshowChangeFeeReturn(String noshowChangeFeeReturn) {
		this.noshowChangeFeeReturn = noshowChangeFeeReturn;
	}

	public Integer getIsCancelAll() {
		return isCancelAll;
	}

	public void setIsCancelAll(Integer isCancelAll) {
		this.isCancelAll = isCancelAll;
	}

	public String getCancelFeeAll() {
		return cancelFeeAll;
	}

	public void setCancelFeeAll(String cancelFeeAll) {
		this.cancelFeeAll = cancelFeeAll;
	}

	public Integer getIsCancelPart() {
		return isCancelPart;
	}

	public void setIsCancelPart(Integer isCancelPart) {
		this.isCancelPart = isCancelPart;
	}

	public String getCancelFeePart() {
		return cancelFeePart;
	}

	public void setCancelFeePart(String cancelFeePart) {
		this.cancelFeePart = cancelFeePart;
	}

	public Integer getIsNoshowCancel() {
		return isNoshowCancel;
	}

	public void setIsNoshowCancel(Integer isNoshowCancel) {
		this.isNoshowCancel = isNoshowCancel;
	}

	public String getNoshowCancelCondition() {
		return noshowCancelCondition;
	}

	public void setNoshowCancelCondition(String noshowCancelCondition) {
		this.noshowCancelCondition = noshowCancelCondition;
	}

	public String getNoshowCancelFeeAll() {
		return noshowCancelFeeAll;
	}

	public void setNoshowCancelFeeAll(String noshowCancelFeeAll) {
		this.noshowCancelFeeAll = noshowCancelFeeAll;
	}

	public String getNoshowCancelFeePart() {
		return noshowCancelFeePart;
	}

	public void setNoshowCancelFeePart(String noshowCancelFeePart) {
		this.noshowCancelFeePart = noshowCancelFeePart;
	}

	public String getNoShowAmount() {
		return noShowAmount;
	}

	public void setNoShowAmount(String noShowAmount) {
		this.noShowAmount = noShowAmount;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getCancelTax() {
		return cancelTax;
	}

	public void setCancelTax(String cancelTax) {
		this.cancelTax = cancelTax;
	}

	public Integer getPassengerNumMin() {
		return passengerNumMin;
	}

	public void setPassengerNumMin(Integer passengerNumMin) {
		this.passengerNumMin = passengerNumMin;
	}

	public Integer getPassengerNumLimit() {
		return passengerNumLimit;
	}

	public void setPassengerNumLimit(Integer passengerNumLimit) {
		this.passengerNumLimit = passengerNumLimit;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPcc() {
		return pcc;
	}

	public void setPcc(String pcc) {
		this.pcc = pcc;
	}

	public Integer getIsUseCarrierRule() {
		return isUseCarrierRule;
	}

	public void setIsUseCarrierRule(Integer isUseCarrierRule) {
		this.isUseCarrierRule = isUseCarrierRule;
	}

	public String getAdtBaggageOut() {
		return adtBaggageOut;
	}

	public void setAdtBaggageOut(String adtBaggageOut) {
		this.adtBaggageOut = adtBaggageOut;
	}

	public String getAdtBaggageReturn() {
		return adtBaggageReturn;
	}

	public void setAdtBaggageReturn(String adtBaggageReturn) {
		this.adtBaggageReturn = adtBaggageReturn;
	}

	public String getChdBaggageOut() {
		return chdBaggageOut;
	}

	public void setChdBaggageOut(String chdBaggageOut) {
		this.chdBaggageOut = chdBaggageOut;
	}

	public String getChdBaggageReturn() {
		return chdBaggageReturn;
	}

	public void setChdBaggageReturn(String chdBaggageReturn) {
		this.chdBaggageReturn = chdBaggageReturn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof GdsPolicy)) {
			return false;
		}
		GdsPolicy gdsPolicy = (GdsPolicy) o;
		return Objects.equal(getId(), gdsPolicy.getId()) && Objects
				.equal(getBatchNo(), gdsPolicy.getBatchNo()) && Objects
				.equal(getOriginId(), gdsPolicy.getOriginId()) && Objects
				.equal(getOtaType(), gdsPolicy.getOtaType()) && Objects
				.equal(getChildOtaType(), gdsPolicy.getChildOtaType()) && Objects
				.equal(getPolicyType(), gdsPolicy.getPolicyType()) && Objects
				.equal(getTktAirline(), gdsPolicy.getTktAirline()) && Objects
				.equal(getTripType(), gdsPolicy.getTripType()) && Objects
				.equal(getOrg(), gdsPolicy.getOrg()) && Objects
				.equal(getDes(), gdsPolicy.getDes()) && Objects
				.equal(getExOrg(), gdsPolicy.getExOrg()) && Objects
				.equal(getExDes(), gdsPolicy.getExDes()) && Objects
				.equal(getCabin(), gdsPolicy.getCabin()) && Objects
				.equal(getCabinClass(), gdsPolicy.getCabinClass()) && Objects
				.equal(getExCabin(), gdsPolicy.getExCabin()) && Objects
				.equal(getIsTransport(), gdsPolicy.getIsTransport()) && Objects
				.equal(getTransporAir(), gdsPolicy.getTransporAir()) && Objects
				.equal(getExTransporAirline(), gdsPolicy.getExTransporAirline()) && Objects
				.equal(getIsTransfer(), gdsPolicy.getIsTransfer()) && Objects
				.equal(getTransferPoint(), gdsPolicy.getTransferPoint()) && Objects
				.equal(getIsShareFlight(), gdsPolicy.getIsShareFlight()) && Objects
				.equal(getGoFlightDateStart(), gdsPolicy.getGoFlightDateStart()) && Objects
				.equal(getGoFlightDateEnd(), gdsPolicy.getGoFlightDateEnd()) && Objects
				.equal(getRetFlightDateStart(), gdsPolicy.getRetFlightDateStart()) && Objects
				.equal(getRetFlightDateEnd(), gdsPolicy.getRetFlightDateEnd()) && Objects
				.equal(getSalesDateStart(), gdsPolicy.getSalesDateStart()) && Objects
				.equal(getSalesDateEnd(), gdsPolicy.getSalesDateEnd()) && Objects
				.equal(getRate(), gdsPolicy.getRate()) && Objects
				.equal(getAmount(), gdsPolicy.getAmount()) && Objects
				.equal(getWorkTime(), gdsPolicy.getWorkTime()) && Objects
				.equal(getIsAllowRt(), gdsPolicy.getIsAllowRt()) && Objects
				.equal(getAdvanceTicketingDeadline(), gdsPolicy
						.getAdvanceTicketingDeadline()) && Objects
				.equal(getCreateUserId(), gdsPolicy.getCreateUserId()) && Objects
				.equal(getCreateUserName(), gdsPolicy.getCreateUserName()) && Objects
				.equal(getFlightNos(), gdsPolicy.getFlightNos()) && Objects
				.equal(getStatus(), gdsPolicy.getStatus()) && Objects
				.equal(getLastIssueDeadline(), gdsPolicy.getLastIssueDeadline()) && Objects
				.equal(getLongestStopDay(), gdsPolicy.getLongestStopDay()) && Objects
				.equal(getShortestStopDay(), gdsPolicy.getShortestStopDay()) && Objects
				.equal(getToFlightLimit(), gdsPolicy.getToFlightLimit()) && Objects
				.equal(getReFlightLimit(), gdsPolicy.getReFlightLimit()) && Objects
				.equal(getCabinInfosStr(), gdsPolicy.getCabinInfosStr()) && Objects
				.equal(getExFlightNos(), gdsPolicy.getExFlightNos()) && Objects
				.equal(getPassengerAgeLimit(), gdsPolicy.getPassengerAgeLimit()) && Objects
				.equal(getTicketingRemark(), gdsPolicy.getTicketingRemark()) && Objects
				.equal(getShareAirline(), gdsPolicy.getShareAirline()) && Objects
				.equal(getReturned(), gdsPolicy.getReturned()) && Objects
				.equal(getGdsType(), gdsPolicy.getGdsType()) && Objects
				.equal(getReFlightNos(), gdsPolicy.getReFlightNos()) && Objects
				.equal(getExReFlightNos(), gdsPolicy.getExReFlightNos()) && Objects
				.equal(getIsExactParity(), gdsPolicy.getIsExactParity()) && Objects
				.equal(getIsHot(), gdsPolicy.getIsHot()) && Objects
				.equal(getToExceptDate(), gdsPolicy.getToExceptDate()) && Objects
				.equal(getReturnExceptDate(), gdsPolicy.getReturnExceptDate()) && Objects
				.equal(getTimeInterval(), gdsPolicy.getTimeInterval()) && Objects
				.equal(getPolicyRemark(), gdsPolicy.getPolicyRemark()) && Objects
				.equal(getPkPriceDate(), gdsPolicy.getPkPriceDate()) && Objects
				.equal(getIsCharter(), gdsPolicy.getIsCharter()) && Objects
				.equal(getIsSpecialFare(), gdsPolicy.getIsSpecialFare()) && Objects
				.equal(getOrgAirport(), gdsPolicy.getOrgAirport()) && Objects
				.equal(getDesAirport(), gdsPolicy.getDesAirport()) && Objects
				.equal(getExOrgAirport(), gdsPolicy.getExOrgAirport()) && Objects
				.equal(getExDesAirport(), gdsPolicy.getExDesAirport()) && Objects
				.equal(getModifyPriceFlightLine(), gdsPolicy.getModifyPriceFlightLine()) && Objects
				.equal(getModifyPriceCount(), gdsPolicy.getModifyPriceCount()) && Objects
				.equal(getModifyPriceRange(), gdsPolicy.getModifyPriceRange()) && Objects
				.equal(getModifyPricePerTime(), gdsPolicy.getModifyPricePerTime()) && Objects
				.equal(getIsDefaultRule(), gdsPolicy.getIsDefaultRule()) && Objects
				.equal(getIsChangeOut(), gdsPolicy.getIsChangeOut()) && Objects
				.equal(getChangeFeeOut(), gdsPolicy.getChangeFeeOut()) && Objects
				.equal(getIsChangeReturn(), gdsPolicy.getIsChangeReturn()) && Objects
				.equal(getChangeFeeReturn(), gdsPolicy.getChangeFeeReturn()) && Objects
				.equal(getIsNoshowChangeOut(), gdsPolicy.getIsNoshowChangeOut()) && Objects
				.equal(getNoshowChangeCondition(), gdsPolicy.getNoshowChangeCondition()) && Objects
				.equal(getNoshowChangeFeeOut(), gdsPolicy.getNoshowChangeFeeOut()) && Objects
				.equal(getNoshowChangeFeeReturn(), gdsPolicy.getNoshowChangeFeeReturn()) && Objects
				.equal(getIsCancelAll(), gdsPolicy.getIsCancelAll()) && Objects
				.equal(getCancelFeeAll(), gdsPolicy.getCancelFeeAll()) && Objects
				.equal(getIsCancelPart(), gdsPolicy.getIsCancelPart()) && Objects
				.equal(getCancelFeePart(), gdsPolicy.getCancelFeePart()) && Objects
				.equal(getIsNoshowCancel(), gdsPolicy.getIsNoshowCancel()) && Objects
				.equal(getNoshowCancelCondition(), gdsPolicy.getNoshowCancelCondition()) && Objects
				.equal(getNoshowCancelFeeAll(), gdsPolicy.getNoshowCancelFeeAll()) && Objects
				.equal(getNoshowCancelFeePart(), gdsPolicy.getNoshowCancelFeePart()) && Objects
				.equal(getNoShowAmount(), gdsPolicy.getNoShowAmount()) && Objects
				.equal(getCompanyType(), gdsPolicy.getCompanyType()) && Objects
				.equal(getInvoiceType(), gdsPolicy.getInvoiceType()) && Objects
				.equal(getCancelTax(), gdsPolicy.getCancelTax()) && Objects
				.equal(getPassengerNumMin(), gdsPolicy.getPassengerNumMin()) && Objects
				.equal(getPassengerNumLimit(), gdsPolicy.getPassengerNumLimit()) && Objects
				.equal(getChannel(), gdsPolicy.getChannel()) && Objects
				.equal(getPcc(), gdsPolicy.getPcc()) && Objects
				.equal(getIsUseCarrierRule(), gdsPolicy.getIsUseCarrierRule()) && Objects
				.equal(getAdtBaggageOut(), gdsPolicy.getAdtBaggageOut()) && Objects
				.equal(getAdtBaggageReturn(), gdsPolicy.getAdtBaggageReturn()) && Objects
				.equal(getChdBaggageOut(), gdsPolicy.getChdBaggageOut()) && Objects
				.equal(getChdBaggageReturn(), gdsPolicy.getChdBaggageReturn());
	}

	@Override
	public int hashCode() {
		return Objects
				.hashCode(getId(), getBatchNo(), getOriginId(), getOtaType(), getChildOtaType(), getPolicyType(), getTktAirline(), getTripType(), getOrg(), getDes(), getExOrg(), getExDes(), getCabin(), getCabinClass(), getExCabin(), getIsTransport(), getTransporAir(), getExTransporAirline(), getIsTransfer(), getTransferPoint(), getIsShareFlight(), getGoFlightDateStart(), getGoFlightDateEnd(), getRetFlightDateStart(), getRetFlightDateEnd(), getSalesDateStart(), getSalesDateEnd(), getRate(), getAmount(), getWorkTime(), getIsAllowRt(), getAdvanceTicketingDeadline(), getCreateUserId(), getCreateUserName(), getFlightNos(), getStatus(), getLastIssueDeadline(), getLongestStopDay(), getShortestStopDay(), getToFlightLimit(), getReFlightLimit(), getCabinInfosStr(), getExFlightNos(), getPassengerAgeLimit(), getTicketingRemark(), getShareAirline(), getReturned(), getGdsType(), getReFlightNos(), getExReFlightNos(), getIsExactParity(), getIsHot(), getToExceptDate(), getReturnExceptDate(), getTimeInterval(), getPolicyRemark(), getPkPriceDate(), getIsCharter(), getIsSpecialFare(), getOrgAirport(), getDesAirport(), getExOrgAirport(), getExDesAirport(), getModifyPriceFlightLine(), getModifyPriceCount(), getModifyPriceRange(), getModifyPricePerTime(), getIsDefaultRule(), getIsChangeOut(), getChangeFeeOut(), getIsChangeReturn(), getChangeFeeReturn(), getIsNoshowChangeOut(), getNoshowChangeCondition(), getNoshowChangeFeeOut(), getNoshowChangeFeeReturn(), getIsCancelAll(), getCancelFeeAll(), getIsCancelPart(), getCancelFeePart(), getIsNoshowCancel(), getNoshowCancelCondition(), getNoshowCancelFeeAll(), getNoshowCancelFeePart(), getNoShowAmount(), getCompanyType(), getInvoiceType(), getCancelTax(), getPassengerNumMin(), getPassengerNumLimit(), getChannel(), getPcc(), getIsUseCarrierRule(), getAdtBaggageOut(), getAdtBaggageReturn(), getChdBaggageOut(), getChdBaggageReturn());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("batchNo", batchNo)
				.add("originId", originId)
				.add("otaType", otaType)
				.add("childOtaType", childOtaType)
				.add("policyType", policyType)
				.add("tktAirline", tktAirline)
				.add("tripType", tripType)
				.add("org", org)
				.add("des", des)
				.add("exOrg", exOrg)
				.add("exDes", exDes)
				.add("cabin", cabin)
				.add("cabinClass", cabinClass)
				.add("exCabin", exCabin)
				.add("isTransport", isTransport)
				.add("transporAir", transporAir)
				.add("exTransporAirline", exTransporAirline)
				.add("isTransfer", isTransfer)
				.add("transferPoint", transferPoint)
				.add("isShareFlight", isShareFlight)
				.add("goFlightDateStart", goFlightDateStart)
				.add("goFlightDateEnd", goFlightDateEnd)
				.add("retFlightDateStart", retFlightDateStart)
				.add("retFlightDateEnd", retFlightDateEnd)
				.add("salesDateStart", salesDateStart)
				.add("salesDateEnd", salesDateEnd)
				.add("rate", rate)
				.add("amount", amount)
				.add("workTime", workTime)
				.add("isAllowRt", isAllowRt)
				.add("advanceTicketingDeadline", advanceTicketingDeadline)
				.add("createUserId", createUserId)
				.add("createUserName", createUserName)
				.add("flightNos", flightNos)
				.add("status", status)
				.add("lastIssueDeadline", lastIssueDeadline)
				.add("longestStopDay", longestStopDay)
				.add("shortestStopDay", shortestStopDay)
				.add("toFlightLimit", toFlightLimit)
				.add("reFlightLimit", reFlightLimit)
				.add("cabinInfosStr", cabinInfosStr)
				.add("exFlightNos", exFlightNos)
				.add("passengerAgeLimit", passengerAgeLimit)
				.add("ticketingRemark", ticketingRemark)
				.add("shareAirline", shareAirline)
				.add("returned", returned)
				.add("gdsType", gdsType)
				.add("reFlightNos", reFlightNos)
				.add("exReFlightNos", exReFlightNos)
				.add("isExactParity", isExactParity)
				.add("isHot", isHot)
				.add("toExceptDate", toExceptDate)
				.add("returnExceptDate", returnExceptDate)
				.add("timeInterval", timeInterval)
				.add("policyRemark", policyRemark)
				.add("pkPriceDate", pkPriceDate)
				.add("isCharter", isCharter)
				.add("isSpecialFare", isSpecialFare)
				.add("orgAirport", orgAirport)
				.add("desAirport", desAirport)
				.add("exOrgAirport", exOrgAirport)
				.add("exDesAirport", exDesAirport)
				.add("modifyPriceFlightLine", modifyPriceFlightLine)
				.add("modifyPriceCount", modifyPriceCount)
				.add("modifyPriceRange", modifyPriceRange)
				.add("modifyPricePerTime", modifyPricePerTime)
				.add("isDefaultRule", isDefaultRule)
				.add("isChangeOut", isChangeOut)
				.add("changeFeeOut", changeFeeOut)
				.add("isChangeReturn", isChangeReturn)
				.add("changeFeeReturn", changeFeeReturn)
				.add("isNoshowChangeOut", isNoshowChangeOut)
				.add("noshowChangeCondition", noshowChangeCondition)
				.add("noshowChangeFeeOut", noshowChangeFeeOut)
				.add("noshowChangeFeeReturn", noshowChangeFeeReturn)
				.add("isCancelAll", isCancelAll)
				.add("cancelFeeAll", cancelFeeAll)
				.add("isCancelPart", isCancelPart)
				.add("cancelFeePart", cancelFeePart)
				.add("isNoshowCancel", isNoshowCancel)
				.add("noshowCancelCondition", noshowCancelCondition)
				.add("noshowCancelFeeAll", noshowCancelFeeAll)
				.add("noshowCancelFeePart", noshowCancelFeePart)
				.add("noShowAmount", noShowAmount)
				.add("companyType", companyType)
				.add("invoiceType", invoiceType)
				.add("cancelTax", cancelTax)
				.add("passengerNumMin", passengerNumMin)
				.add("passengerNumLimit", passengerNumLimit)
				.add("channel", channel)
				.add("pcc", pcc)
				.add("isUseCarrierRule", isUseCarrierRule)
				.add("adtBaggageOut", adtBaggageOut)
				.add("adtBaggageReturn", adtBaggageReturn)
				.add("chdBaggageOut", chdBaggageOut)
				.add("chdBaggageReturn", chdBaggageReturn)
				.toString();
	}
}
