package com.ss.oa.transaction.vo;

import java.util.List;

public class EnergySaleIntent {
	private String id,code;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String sellerEndOrgId,sellerEndOrgName,sellerEndOrgCode;
	private String sellerSubstationid,sellerSubstationCode,sellerSubstationName;
	private String sellerFeederId,sellerFeederCode,sellerFeederName;
	private String sellerInjectingVoltageCode,sellerInjectingVoltageName;
	private String agmtPeriodCode;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear;
	private String isCaptive,statusCode,statusName;
	private String ewaId,ewaStatusCode,ewaStatusName,ewaTotalApprovedCapacity;
	private String ipaaId,ipaaStatusCode,ipaaStatusName;
	private String nocGeneratorId,nocGeneratorStatusCode,nocGeneratorStatusName;
	private String flowTypeCode,flowTypeName;
	private String proposedCapacity;
	private String sldcAwaitedDt,sldcApprovalDt,sldcRejectedDt,sldcApproved,sldcRemarks;
	private List<EnergySaleIntentLine> energySaleIntentLines;
	
	public EnergySaleIntent() {
		super();
	}

	public EnergySaleIntent(String id, String code, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyId, String sellerCompanyName, String sellerCompanyCode, String sellerEndOrgId,
			String sellerEndOrgName, String sellerEndOrgCode, String sellerSubstationid, String sellerSubstationCode,
			String sellerSubstationName, String sellerFeederId, String sellerFeederCode, String sellerFeederName,
			String sellerInjectingVoltageCode, String sellerInjectingVoltageName, String agmtPeriodCode,
			String fromDate, String toDate, String fromMonth, String toMonth, String fromYear, String toYear,
			String isCaptive, String statusCode, String statusName, String ewaId, String ewaStatusCode,
			String ewaStatusName, String ewaTotalApprovedCapacity, String ipaaId, String ipaaStatusCode,
			String ipaaStatusName, String nocGeneratorId, String nocGeneratorStatusCode, String nocGeneratorStatusName,
			String flowTypeCode, String flowTypeName, String proposedCapacity, String sldcAwaitedDt,
			String sldcApprovalDt, String sldcRejectedDt, String sldcApproved, String sldcRemarks,
			List<EnergySaleIntentLine> energySaleIntentLines) {
		super();
		this.id = id;
		this.code = code;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerEndOrgId = sellerEndOrgId;
		this.sellerEndOrgName = sellerEndOrgName;
		this.sellerEndOrgCode = sellerEndOrgCode;
		this.sellerSubstationid = sellerSubstationid;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerFeederName = sellerFeederName;
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
		this.agmtPeriodCode = agmtPeriodCode;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.isCaptive = isCaptive;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.ewaId = ewaId;
		this.ewaStatusCode = ewaStatusCode;
		this.ewaStatusName = ewaStatusName;
		this.ewaTotalApprovedCapacity = ewaTotalApprovedCapacity;
		this.ipaaId = ipaaId;
		this.ipaaStatusCode = ipaaStatusCode;
		this.ipaaStatusName = ipaaStatusName;
		this.nocGeneratorId = nocGeneratorId;
		this.nocGeneratorStatusCode = nocGeneratorStatusCode;
		this.nocGeneratorStatusName = nocGeneratorStatusName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.proposedCapacity = proposedCapacity;
		this.sldcAwaitedDt = sldcAwaitedDt;
		this.sldcApprovalDt = sldcApprovalDt;
		this.sldcRejectedDt = sldcRejectedDt;
		this.sldcApproved = sldcApproved;
		this.sldcRemarks = sldcRemarks;
		this.energySaleIntentLines = energySaleIntentLines;
	}

	@Override
	public String toString() {
		return "EnergySaleIntent [id=" + id + ", code=" + code + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", sellerEndOrgId=" + sellerEndOrgId + ", sellerEndOrgName=" + sellerEndOrgName
				+ ", sellerEndOrgCode=" + sellerEndOrgCode + ", sellerSubstationid=" + sellerSubstationid
				+ ", sellerSubstationCode=" + sellerSubstationCode + ", sellerSubstationName=" + sellerSubstationName
				+ ", sellerFeederId=" + sellerFeederId + ", sellerFeederCode=" + sellerFeederCode
				+ ", sellerFeederName=" + sellerFeederName + ", sellerInjectingVoltageCode="
				+ sellerInjectingVoltageCode + ", sellerInjectingVoltageName=" + sellerInjectingVoltageName
				+ ", agmtPeriodCode=" + agmtPeriodCode + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear=" + fromYear + ", toYear=" + toYear
				+ ", isCaptive=" + isCaptive + ", statusCode=" + statusCode + ", statusName=" + statusName + ", ewaId="
				+ ewaId + ", ewaStatusCode=" + ewaStatusCode + ", ewaStatusName=" + ewaStatusName
				+ ", ewaTotalApprovedCapacity=" + ewaTotalApprovedCapacity + ", ipaaId=" + ipaaId + ", ipaaStatusCode="
				+ ipaaStatusCode + ", ipaaStatusName=" + ipaaStatusName + ", nocGeneratorId=" + nocGeneratorId
				+ ", nocGeneratorStatusCode=" + nocGeneratorStatusCode + ", nocGeneratorStatusName="
				+ nocGeneratorStatusName + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName
				+ ", proposedCapacity=" + proposedCapacity + ", sldcAwaitedDt=" + sldcAwaitedDt + ", sldcApprovalDt="
				+ sldcApprovalDt + ", sldcRejectedDt=" + sldcRejectedDt + ", sldcApproved=" + sldcApproved
				+ ", sldcRemarks=" + sldcRemarks + ", energySaleIntentLines=" + energySaleIntentLines + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}

	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}

	public String getSellerEndOrgId() {
		return sellerEndOrgId;
	}

	public void setSellerEndOrgId(String sellerEndOrgId) {
		this.sellerEndOrgId = sellerEndOrgId;
	}

	public String getSellerEndOrgName() {
		return sellerEndOrgName;
	}

	public void setSellerEndOrgName(String sellerEndOrgName) {
		this.sellerEndOrgName = sellerEndOrgName;
	}

	public String getSellerEndOrgCode() {
		return sellerEndOrgCode;
	}

	public void setSellerEndOrgCode(String sellerEndOrgCode) {
		this.sellerEndOrgCode = sellerEndOrgCode;
	}

	public String getSellerSubstationid() {
		return sellerSubstationid;
	}

	public void setSellerSubstationid(String sellerSubstationid) {
		this.sellerSubstationid = sellerSubstationid;
	}

	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}

	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}

	public String getSellerSubstationName() {
		return sellerSubstationName;
	}

	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}

	public String getSellerFeederId() {
		return sellerFeederId;
	}

	public void setSellerFeederId(String sellerFeederId) {
		this.sellerFeederId = sellerFeederId;
	}

	public String getSellerFeederCode() {
		return sellerFeederCode;
	}

	public void setSellerFeederCode(String sellerFeederCode) {
		this.sellerFeederCode = sellerFeederCode;
	}

	public String getSellerFeederName() {
		return sellerFeederName;
	}

	public void setSellerFeederName(String sellerFeederName) {
		this.sellerFeederName = sellerFeederName;
	}

	public String getSellerInjectingVoltageCode() {
		return sellerInjectingVoltageCode;
	}

	public void setSellerInjectingVoltageCode(String sellerInjectingVoltageCode) {
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
	}

	public String getSellerInjectingVoltageName() {
		return sellerInjectingVoltageName;
	}

	public void setSellerInjectingVoltageName(String sellerInjectingVoltageName) {
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
	}

	public String getAgmtPeriodCode() {
		return agmtPeriodCode;
	}

	public void setAgmtPeriodCode(String agmtPeriodCode) {
		this.agmtPeriodCode = agmtPeriodCode;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getEwaId() {
		return ewaId;
	}

	public void setEwaId(String ewaId) {
		this.ewaId = ewaId;
	}

	public String getEwaStatusCode() {
		return ewaStatusCode;
	}

	public void setEwaStatusCode(String ewaStatusCode) {
		this.ewaStatusCode = ewaStatusCode;
	}

	public String getEwaStatusName() {
		return ewaStatusName;
	}

	public void setEwaStatusName(String ewaStatusName) {
		this.ewaStatusName = ewaStatusName;
	}

	public String getEwaTotalApprovedCapacity() {
		return ewaTotalApprovedCapacity;
	}

	public void setEwaTotalApprovedCapacity(String ewaTotalApprovedCapacity) {
		this.ewaTotalApprovedCapacity = ewaTotalApprovedCapacity;
	}

	public String getIpaaId() {
		return ipaaId;
	}

	public void setIpaaId(String ipaaId) {
		this.ipaaId = ipaaId;
	}

	public String getIpaaStatusCode() {
		return ipaaStatusCode;
	}

	public void setIpaaStatusCode(String ipaaStatusCode) {
		this.ipaaStatusCode = ipaaStatusCode;
	}

	public String getIpaaStatusName() {
		return ipaaStatusName;
	}

	public void setIpaaStatusName(String ipaaStatusName) {
		this.ipaaStatusName = ipaaStatusName;
	}

	public String getNocGeneratorId() {
		return nocGeneratorId;
	}

	public void setNocGeneratorId(String nocGeneratorId) {
		this.nocGeneratorId = nocGeneratorId;
	}

	public String getNocGeneratorStatusCode() {
		return nocGeneratorStatusCode;
	}

	public void setNocGeneratorStatusCode(String nocGeneratorStatusCode) {
		this.nocGeneratorStatusCode = nocGeneratorStatusCode;
	}

	public String getNocGeneratorStatusName() {
		return nocGeneratorStatusName;
	}

	public void setNocGeneratorStatusName(String nocGeneratorStatusName) {
		this.nocGeneratorStatusName = nocGeneratorStatusName;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public String getProposedCapacity() {
		return proposedCapacity;
	}

	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}

	public String getSldcAwaitedDt() {
		return sldcAwaitedDt;
	}

	public void setSldcAwaitedDt(String sldcAwaitedDt) {
		this.sldcAwaitedDt = sldcAwaitedDt;
	}

	public String getSldcApprovalDt() {
		return sldcApprovalDt;
	}

	public void setSldcApprovalDt(String sldcApprovalDt) {
		this.sldcApprovalDt = sldcApprovalDt;
	}

	public String getSldcRejectedDt() {
		return sldcRejectedDt;
	}

	public void setSldcRejectedDt(String sldcRejectedDt) {
		this.sldcRejectedDt = sldcRejectedDt;
	}

	public String getSldcApproved() {
		return sldcApproved;
	}

	public void setSldcApproved(String sldcApproved) {
		this.sldcApproved = sldcApproved;
	}

	public String getSldcRemarks() {
		return sldcRemarks;
	}

	public void setSldcRemarks(String sldcRemarks) {
		this.sldcRemarks = sldcRemarks;
	}

	public List<EnergySaleIntentLine> getEnergySaleIntentLines() {
		return energySaleIntentLines;
	}

	public void setEnergySaleIntentLines(List<EnergySaleIntentLine> energySaleIntentLines) {
		this.energySaleIntentLines = energySaleIntentLines;
	}

	
	
}
