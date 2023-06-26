package com.ss.oa.transaction.vo;

public class StandingClearence {
	private String  id, code ;
	private String  sldcName;
	private String  region;
	private String  buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String  buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String  esIntentId,esIntentCode ;
	private String  orgId,orgCode,orgName;
	private String  substationName,substationId,substationCode,feederName,feederId,feederCode;
	private String  maxDrawalCeiling ;
	private String  createdBy ,createdDate; 
	private String  approvalDate; 
	private String  enabled; 
	private String  fromDate,fromMonth,fromYear  ;
	private String  toDate,toMonth,toYear ;  
	private String  stateTransLoss ,stateTransLossPercent;  
	private String  stateTransCharges,stateTransChargesPercent ;  
	private String  schedulingCharges,schedulingChargesPercent; 
	private String  systemOprCharges,systemoprChargesPercent; 
	private String  modifiedDate,modifiedBy;
	private String  statusCode,flowTypeCode,flowTypeName;
	
	
	public StandingClearence() {
		super();
		// TODO Auto-generated constructor stub
	}


	public StandingClearence(String id, String code, String sldcName, String region, String buyerCompanyServiceId,
			String buyerCompanyServiceNumber, String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode,
			String esIntentId, String esIntentCode, String orgId, String orgCode, String orgName, String substationName,
			String substationId, String substationCode, String feederName, String feederId, String feederCode,
			String maxDrawalCeiling, String createdBy, String createdDate, String approvalDate, String enabled,
			String fromDate, String fromMonth, String fromYear, String toDate, String toMonth, String toYear,
			String stateTransLoss, String stateTransLossPercent, String stateTransCharges,
			String stateTransChargesPercent, String schedulingCharges, String schedulingChargesPercent,
			String systemOprCharges, String systemoprChargesPercent, String modifiedDate, String modifiedBy,
			String statusCode, String flowTypeCode, String flowTypeName) {
		super();
		this.id = id;
		this.code = code;
		this.sldcName = sldcName;
		this.region = region;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.substationName = substationName;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.feederName = feederName;
		this.feederId = feederId;
		this.feederCode = feederCode;
		this.maxDrawalCeiling = maxDrawalCeiling;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.approvalDate = approvalDate;
		this.enabled = enabled;
		this.fromDate = fromDate;
		this.fromMonth = fromMonth;
		this.fromYear = fromYear;
		this.toDate = toDate;
		this.toMonth = toMonth;
		this.toYear = toYear;
		this.stateTransLoss = stateTransLoss;
		this.stateTransLossPercent = stateTransLossPercent;
		this.stateTransCharges = stateTransCharges;
		this.stateTransChargesPercent = stateTransChargesPercent;
		this.schedulingCharges = schedulingCharges;
		this.schedulingChargesPercent = schedulingChargesPercent;
		this.systemOprCharges = systemOprCharges;
		this.systemoprChargesPercent = systemoprChargesPercent;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.statusCode = statusCode;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
	}


	@Override
	public String toString() {
		return "StandingClearence [id=" + id + ", code=" + code + ", sldcName=" + sldcName + ", region=" + region
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyServiceNumber="
				+ buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyName="
				+ buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", esIntentId=" + esIntentId
				+ ", esIntentCode=" + esIntentCode + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName="
				+ orgName + ", substationName=" + substationName + ", substationId=" + substationId
				+ ", substationCode=" + substationCode + ", feederName=" + feederName + ", feederId=" + feederId
				+ ", feederCode=" + feederCode + ", maxDrawalCeiling=" + maxDrawalCeiling + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", approvalDate=" + approvalDate + ", enabled=" + enabled
				+ ", fromDate=" + fromDate + ", fromMonth=" + fromMonth + ", fromYear=" + fromYear + ", toDate="
				+ toDate + ", toMonth=" + toMonth + ", toYear=" + toYear + ", stateTransLoss=" + stateTransLoss
				+ ", stateTransLossPercent=" + stateTransLossPercent + ", stateTransCharges=" + stateTransCharges
				+ ", stateTransChargesPercent=" + stateTransChargesPercent + ", schedulingCharges=" + schedulingCharges
				+ ", schedulingChargesPercent=" + schedulingChargesPercent + ", systemOprCharges=" + systemOprCharges
				+ ", systemoprChargesPercent=" + systemoprChargesPercent + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + ", statusCode=" + statusCode + ", flowTypeCode=" + flowTypeCode
				+ ", flowTypeName=" + flowTypeName + "]";
	}


	public String getId() {
		return id;
	}


	public String getCode() {
		return code;
	}


	public String getSldcName() {
		return sldcName;
	}


	public String getRegion() {
		return region;
	}


	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}


	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}


	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}


	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}


	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}


	public String getEsIntentId() {
		return esIntentId;
	}


	public String getEsIntentCode() {
		return esIntentCode;
	}


	public String getOrgId() {
		return orgId;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public String getOrgName() {
		return orgName;
	}


	public String getSubstationName() {
		return substationName;
	}


	public String getSubstationId() {
		return substationId;
	}


	public String getSubstationCode() {
		return substationCode;
	}


	public String getFeederName() {
		return feederName;
	}


	public String getFeederId() {
		return feederId;
	}


	public String getFeederCode() {
		return feederCode;
	}


	public String getMaxDrawalCeiling() {
		return maxDrawalCeiling;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public String getApprovalDate() {
		return approvalDate;
	}


	public String getEnabled() {
		return enabled;
	}


	public String getFromDate() {
		return fromDate;
	}


	public String getFromMonth() {
		return fromMonth;
	}


	public String getFromYear() {
		return fromYear;
	}


	public String getToDate() {
		return toDate;
	}


	public String getToMonth() {
		return toMonth;
	}


	public String getToYear() {
		return toYear;
	}


	public String getStateTransLoss() {
		return stateTransLoss;
	}


	public String getStateTransLossPercent() {
		return stateTransLossPercent;
	}


	public String getStateTransCharges() {
		return stateTransCharges;
	}


	public String getStateTransChargesPercent() {
		return stateTransChargesPercent;
	}


	public String getSchedulingCharges() {
		return schedulingCharges;
	}


	public String getSchedulingChargesPercent() {
		return schedulingChargesPercent;
	}


	public String getSystemOprCharges() {
		return systemOprCharges;
	}


	public String getSystemoprChargesPercent() {
		return systemoprChargesPercent;
	}


	public String getModifiedDate() {
		return modifiedDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public String getFlowTypeCode() {
		return flowTypeCode;
	}


	public String getFlowTypeName() {
		return flowTypeName;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setSldcName(String sldcName) {
		this.sldcName = sldcName;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}


	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}


	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}


	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}


	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}


	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}


	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}


	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}


	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}


	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}


	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}


	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}


	public void setMaxDrawalCeiling(String maxDrawalCeiling) {
		this.maxDrawalCeiling = maxDrawalCeiling;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}


	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}


	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}


	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}


	public void setToYear(String toYear) {
		this.toYear = toYear;
	}


	public void setStateTransLoss(String stateTransLoss) {
		this.stateTransLoss = stateTransLoss;
	}


	public void setStateTransLossPercent(String stateTransLossPercent) {
		this.stateTransLossPercent = stateTransLossPercent;
	}


	public void setStateTransCharges(String stateTransCharges) {
		this.stateTransCharges = stateTransCharges;
	}


	public void setStateTransChargesPercent(String stateTransChargesPercent) {
		this.stateTransChargesPercent = stateTransChargesPercent;
	}


	public void setSchedulingCharges(String schedulingCharges) {
		this.schedulingCharges = schedulingCharges;
	}


	public void setSchedulingChargesPercent(String schedulingChargesPercent) {
		this.schedulingChargesPercent = schedulingChargesPercent;
	}


	public void setSystemOprCharges(String systemOprCharges) {
		this.systemOprCharges = systemOprCharges;
	}


	public void setSystemoprChargesPercent(String systemoprChargesPercent) {
		this.systemoprChargesPercent = systemoprChargesPercent;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}


	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}
	
	
	


	
	
}
