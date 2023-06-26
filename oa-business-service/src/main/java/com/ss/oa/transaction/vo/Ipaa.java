package com.ss.oa.transaction.vo;

import java.util.List;

public class Ipaa {
	
	private String id,code;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String sellerOrgId,sellerOrgName,sellerOrgCode;
	private String sellerSubstationid,sellerSubstationCode,sellerSubstationName;
	private String sellerFeederId,sellerFeederCode,sellerFeederName;
	private String sellerInjectingVoltageCode,sellerInjectingVoltageName;
	private String agmtPeriodCode,flowTypeCode,flowTypeName;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear;
	private String isCaptive,statusCode,statusName;
	private String enabled,esIntentId,esIntentCode;
	private String proposedCapacity;
	private List<IpaaLine> ipaaLines;
	
	
	public Ipaa() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Ipaa(String id, String code, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyId, String sellerCompanyName, String sellerCompanyCode, String sellerOrgId,
			String sellerOrgName, String sellerOrgCode, String sellerSubstationid, String sellerSubstationCode,
			String sellerSubstationName, String sellerFeederId, String sellerFeederCode, String sellerFeederName,
			String sellerInjectingVoltageCode, String sellerInjectingVoltageName, String agmtPeriodCode,
			String flowTypeCode, String flowTypeName, String fromDate, String toDate, String fromMonth, String toMonth,
			String fromYear, String toYear, String isCaptive, String statusCode, String statusName, String enabled,
			String esIntentId, String esIntentCode, String proposedCapacity, List<IpaaLine> ipaaLines) {
		super();
		this.id = id;
		this.code = code;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerOrgCode = sellerOrgCode;
		this.sellerSubstationid = sellerSubstationid;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerFeederName = sellerFeederName;
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
		this.agmtPeriodCode = agmtPeriodCode;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.isCaptive = isCaptive;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.enabled = enabled;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.proposedCapacity = proposedCapacity;
		this.ipaaLines = ipaaLines;
	}


	@Override
	public String toString() {
		return "Ipaa [id=" + id + ", code=" + code + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", sellerOrgId=" + sellerOrgId + ", sellerOrgName=" + sellerOrgName + ", sellerOrgCode="
				+ sellerOrgCode + ", sellerSubstationid=" + sellerSubstationid + ", sellerSubstationCode="
				+ sellerSubstationCode + ", sellerSubstationName=" + sellerSubstationName + ", sellerFeederId="
				+ sellerFeederId + ", sellerFeederCode=" + sellerFeederCode + ", sellerFeederName=" + sellerFeederName
				+ ", sellerInjectingVoltageCode=" + sellerInjectingVoltageCode + ", sellerInjectingVoltageName="
				+ sellerInjectingVoltageName + ", agmtPeriodCode=" + agmtPeriodCode + ", flowTypeCode=" + flowTypeCode
				+ ", flowTypeName=" + flowTypeName + ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromMonth="
				+ fromMonth + ", toMonth=" + toMonth + ", fromYear=" + fromYear + ", toYear=" + toYear + ", isCaptive="
				+ isCaptive + ", statusCode=" + statusCode + ", statusName=" + statusName + ", enabled=" + enabled
				+ ", esIntentId=" + esIntentId + ", esIntentCode=" + esIntentCode + ", proposedCapacity="
				+ proposedCapacity + ", ipaaLines=" + ipaaLines + "]";
	}


	public String getId() {
		return id;
	}


	public String getCode() {
		return code;
	}


	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}


	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}


	public String getSellerCompanyId() {
		return sellerCompanyId;
	}


	public String getSellerCompanyName() {
		return sellerCompanyName;
	}


	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}


	public String getSellerOrgId() {
		return sellerOrgId;
	}


	public String getSellerOrgName() {
		return sellerOrgName;
	}


	public String getSellerOrgCode() {
		return sellerOrgCode;
	}


	public String getSellerSubstationid() {
		return sellerSubstationid;
	}


	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}


	public String getSellerSubstationName() {
		return sellerSubstationName;
	}


	public String getSellerFeederId() {
		return sellerFeederId;
	}


	public String getSellerFeederCode() {
		return sellerFeederCode;
	}


	public String getSellerFeederName() {
		return sellerFeederName;
	}


	public String getSellerInjectingVoltageCode() {
		return sellerInjectingVoltageCode;
	}


	public String getSellerInjectingVoltageName() {
		return sellerInjectingVoltageName;
	}


	public String getAgmtPeriodCode() {
		return agmtPeriodCode;
	}


	public String getFlowTypeCode() {
		return flowTypeCode;
	}


	public String getFlowTypeName() {
		return flowTypeName;
	}


	public String getFromDate() {
		return fromDate;
	}


	public String getToDate() {
		return toDate;
	}


	public String getFromMonth() {
		return fromMonth;
	}


	public String getToMonth() {
		return toMonth;
	}


	public String getFromYear() {
		return fromYear;
	}


	public String getToYear() {
		return toYear;
	}


	public String getIsCaptive() {
		return isCaptive;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public String getStatusName() {
		return statusName;
	}


	public String getEnabled() {
		return enabled;
	}


	public String getEsIntentId() {
		return esIntentId;
	}


	public String getEsIntentCode() {
		return esIntentCode;
	}


	public String getProposedCapacity() {
		return proposedCapacity;
	}


	public List<IpaaLine> getIpaaLines() {
		return ipaaLines;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}


	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}


	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}


	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}


	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}


	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}


	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}


	public void setSellerOrgCode(String sellerOrgCode) {
		this.sellerOrgCode = sellerOrgCode;
	}


	public void setSellerSubstationid(String sellerSubstationid) {
		this.sellerSubstationid = sellerSubstationid;
	}


	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}


	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}


	public void setSellerFeederId(String sellerFeederId) {
		this.sellerFeederId = sellerFeederId;
	}


	public void setSellerFeederCode(String sellerFeederCode) {
		this.sellerFeederCode = sellerFeederCode;
	}


	public void setSellerFeederName(String sellerFeederName) {
		this.sellerFeederName = sellerFeederName;
	}


	public void setSellerInjectingVoltageCode(String sellerInjectingVoltageCode) {
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
	}


	public void setSellerInjectingVoltageName(String sellerInjectingVoltageName) {
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
	}


	public void setAgmtPeriodCode(String agmtPeriodCode) {
		this.agmtPeriodCode = agmtPeriodCode;
	}


	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}


	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}


	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}


	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}


	public void setToYear(String toYear) {
		this.toYear = toYear;
	}


	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}


	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}


	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}


	public void setIpaaLines(List<IpaaLine> ipaaLines) {
		this.ipaaLines = ipaaLines;
	}

	
	
}
