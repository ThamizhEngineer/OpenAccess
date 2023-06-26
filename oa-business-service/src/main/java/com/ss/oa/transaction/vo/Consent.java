package com.ss.oa.transaction.vo;

public class Consent {
	
	String id,code;
	//-------------(BuyerDetails)-------------
	String buyerOrgId;
	String buyerOrgName; //(display based on buyerOrgId)
	String buyerCompServiceId;
	String buyerCompServiceNumber; //(display based buyerCompServiceId)
	String buyerCompanyName; //(display based buyerCompServiceId)
	String buyerCompanyId; //(display based buyerCompServiceId) 
	String drawalSubstationId;
	String drawalSubstationName; // (display)
	String drawalFeederId;
	String drawalFeederName;  //(display)
	String drawalVoltageCode;
	String drawalVoltageDesc; //(display)
	String isCaptive;
    String buyerDistrictCode,buyerDistrictName;
	//-------------(EnergyDetails)-------------
	String agreementPeriodCode;
	String agreementPeriodDesc; //(display)
	String agreementDate;
	String fromDate;
	String toDate;
	String fromMonth,fromYear;
	String toMonth,toYear;
	String periodDuration; //(calculation)
	String tNocId; // (nullable)
	String isNocEnclosed; //(Y - calculated based on above)
	String proposedCapacity; // (it was wrongly mentioned as proposedSupply)
	String approvedCapacity;
	String isAbtInstalled; // (Y/N)
	String noAbtReason;
	String hasRealTimeCon; // (Y/N)
	String exemptRc;       // (Y/N)
	String hasDues;        // (Y/N)
	String dueDetails;
	String pendingCaseDetails;
	String technicalFeasibilityDetails;
	//-------------(SellerDetails)-------------
	String sellerOrgId;
	String sellerOrgName; //(display)
	String sellerCompServiceId;
	String sellerCompServiceNumber; //(display based on sellerCompServiceId)
	String sellerCompanyName; //(display based on sellerCompServiceId)
	String sellerCompanyId; //(display based on sellerCompServiceId)
	 //(Y/N)
	String injectionVoltageCode; //(display)
	String injectionVoltageDesc; //(display) 
	String injectionSubstationId;
	String injectionSubstationName; //(display)
	String injectionFeederId;
	String injectionFeederName; //(display)
	//-------------(Metadata)-------------
	String appliedDate;
	String approvedDate;
	String statusCode;
	String statusDesc; //(display)
	String consentNumber;
	String remarks;
	String createdBy;
	String createdDate;
	String modifiedBy;
	String modifiedDate;
	//-------------(miscDetails)-------------
	String tEsIntentId;
	String existingOAAs; //List //( display list of OpenAccess Applications for the same duration for that consumer-buyerCompServiceId)
	String esIntentCode;

	private String flowTypeCode,flowTypeName;
	public Consent() {
		super();
	
	}
	@Override
	public String toString() {
		return "Consent [id=" + id + ", code=" + code + ", buyerOrgId=" + buyerOrgId + ", buyerOrgName=" + buyerOrgName
				+ ", buyerCompServiceId=" + buyerCompServiceId + ", buyerCompServiceNumber=" + buyerCompServiceNumber
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyId=" + buyerCompanyId
				+ ", drawalSubstationId=" + drawalSubstationId + ", drawalSubstationName=" + drawalSubstationName
				+ ", drawalFeederId=" + drawalFeederId + ", drawalFeederName=" + drawalFeederName
				+ ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageDesc=" + drawalVoltageDesc
				+ ", isCaptive=" + isCaptive + ", buyerDistrictCode=" + buyerDistrictCode + ", buyerDistrictName="
				+ buyerDistrictName + ", agreementPeriodCode=" + agreementPeriodCode + ", agreementPeriodDesc="
				+ agreementPeriodDesc + ", agreementDate=" + agreementDate + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", fromMonth=" + fromMonth + ", fromYear=" + fromYear + ", toMonth=" + toMonth + ", toYear="
				+ toYear + ", periodDuration=" + periodDuration + ", tNocId=" + tNocId + ", isNocEnclosed="
				+ isNocEnclosed + ", proposedCapacity=" + proposedCapacity + ", approvedCapacity=" + approvedCapacity
				+ ", isAbtInstalled=" + isAbtInstalled + ", noAbtReason=" + noAbtReason + ", hasRealTimeCon="
				+ hasRealTimeCon + ", exemptRc=" + exemptRc + ", hasDues=" + hasDues + ", dueDetails=" + dueDetails
				+ ", pendingCaseDetails=" + pendingCaseDetails + ", technicalFeasibilityDetails="
				+ technicalFeasibilityDetails + ", sellerOrgId=" + sellerOrgId + ", sellerOrgName=" + sellerOrgName
				+ ", sellerCompServiceId=" + sellerCompServiceId + ", sellerCompServiceNumber="
				+ sellerCompServiceNumber + ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyId="
				+ sellerCompanyId + ", injectionVoltageCode=" + injectionVoltageCode + ", injectionVoltageDesc="
				+ injectionVoltageDesc + ", injectionSubstationId=" + injectionSubstationId
				+ ", injectionSubstationName=" + injectionSubstationName + ", injectionFeederId=" + injectionFeederId
				+ ", injectionFeederName=" + injectionFeederName + ", appliedDate=" + appliedDate + ", approvedDate="
				+ approvedDate + ", statusCode=" + statusCode + ", statusDesc=" + statusDesc + ", consentNumber="
				+ consentNumber + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", tEsIntentId=" + tEsIntentId
				+ ", existingOAAs=" + existingOAAs + ", esIntentCode=" + esIntentCode + ", flowTypeCode=" + flowTypeCode
				+ ", flowTypeName=" + flowTypeName + "]";
	}
	public Consent(String id, String code, String buyerOrgId, String buyerOrgName, String buyerCompServiceId,
			String buyerCompServiceNumber, String buyerCompanyName, String buyerCompanyId, String drawalSubstationId,
			String drawalSubstationName, String drawalFeederId, String drawalFeederName, String drawalVoltageCode,
			String drawalVoltageDesc, String isCaptive, String buyerDistrictCode, String buyerDistrictName,
			String agreementPeriodCode, String agreementPeriodDesc, String agreementDate, String fromDate,
			String toDate, String fromMonth, String fromYear, String toMonth, String toYear, String periodDuration,
			String tNocId, String isNocEnclosed, String proposedCapacity, String approvedCapacity,
			String isAbtInstalled, String noAbtReason, String hasRealTimeCon, String exemptRc, String hasDues,
			String dueDetails, String pendingCaseDetails, String technicalFeasibilityDetails, String sellerOrgId,
			String sellerOrgName, String sellerCompServiceId, String sellerCompServiceNumber, String sellerCompanyName,
			String sellerCompanyId, String injectionVoltageCode, String injectionVoltageDesc,
			String injectionSubstationId, String injectionSubstationName, String injectionFeederId,
			String injectionFeederName, String appliedDate, String approvedDate, String statusCode, String statusDesc,
			String consentNumber, String remarks, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String tEsIntentId, String existingOAAs, String esIntentCode, String flowTypeCode,
			String flowTypeName) {
		super();
		this.id = id;
		this.code = code;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyId = buyerCompanyId;
		this.drawalSubstationId = drawalSubstationId;
		this.drawalSubstationName = drawalSubstationName;
		this.drawalFeederId = drawalFeederId;
		this.drawalFeederName = drawalFeederName;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageDesc = drawalVoltageDesc;
		this.isCaptive = isCaptive;
		this.buyerDistrictCode = buyerDistrictCode;
		this.buyerDistrictName = buyerDistrictName;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodDesc = agreementPeriodDesc;
		this.agreementDate = agreementDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.fromYear = fromYear;
		this.toMonth = toMonth;
		this.toYear = toYear;
		this.periodDuration = periodDuration;
		this.tNocId = tNocId;
		this.isNocEnclosed = isNocEnclosed;
		this.proposedCapacity = proposedCapacity;
		this.approvedCapacity = approvedCapacity;
		this.isAbtInstalled = isAbtInstalled;
		this.noAbtReason = noAbtReason;
		this.hasRealTimeCon = hasRealTimeCon;
		this.exemptRc = exemptRc;
		this.hasDues = hasDues;
		this.dueDetails = dueDetails;
		this.pendingCaseDetails = pendingCaseDetails;
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyId = sellerCompanyId;
		this.injectionVoltageCode = injectionVoltageCode;
		this.injectionVoltageDesc = injectionVoltageDesc;
		this.injectionSubstationId = injectionSubstationId;
		this.injectionSubstationName = injectionSubstationName;
		this.injectionFeederId = injectionFeederId;
		this.injectionFeederName = injectionFeederName;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
		this.consentNumber = consentNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.tEsIntentId = tEsIntentId;
		this.existingOAAs = existingOAAs;
		this.esIntentCode = esIntentCode;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
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
	public String getBuyerOrgId() {
		return buyerOrgId;
	}
	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}
	public String getBuyerOrgName() {
		return buyerOrgName;
	}
	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}
	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}
	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}
	public String getBuyerCompServiceNumber() {
		return buyerCompServiceNumber;
	}
	public void setBuyerCompServiceNumber(String buyerCompServiceNumber) {
		this.buyerCompServiceNumber = buyerCompServiceNumber;
	}
	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}
	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}
	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}
	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}
	public String getDrawalSubstationId() {
		return drawalSubstationId;
	}
	public void setDrawalSubstationId(String drawalSubstationId) {
		this.drawalSubstationId = drawalSubstationId;
	}
	public String getDrawalSubstationName() {
		return drawalSubstationName;
	}
	public void setDrawalSubstationName(String drawalSubstationName) {
		this.drawalSubstationName = drawalSubstationName;
	}
	public String getDrawalFeederId() {
		return drawalFeederId;
	}
	public void setDrawalFeederId(String drawalFeederId) {
		this.drawalFeederId = drawalFeederId;
	}
	public String getDrawalFeederName() {
		return drawalFeederName;
	}
	public void setDrawalFeederName(String drawalFeederName) {
		this.drawalFeederName = drawalFeederName;
	}
	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}
	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}
	public String getDrawalVoltageDesc() {
		return drawalVoltageDesc;
	}
	public void setDrawalVoltageDesc(String drawalVoltageDesc) {
		this.drawalVoltageDesc = drawalVoltageDesc;
	}
	public String getIsCaptive() {
		return isCaptive;
	}
	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}
	public String getBuyerDistrictCode() {
		return buyerDistrictCode;
	}
	public void setBuyerDistrictCode(String buyerDistrictCode) {
		this.buyerDistrictCode = buyerDistrictCode;
	}
	public String getBuyerDistrictName() {
		return buyerDistrictName;
	}
	public void setBuyerDistrictName(String buyerDistrictName) {
		this.buyerDistrictName = buyerDistrictName;
	}
	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}
	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}
	public String getAgreementPeriodDesc() {
		return agreementPeriodDesc;
	}
	public void setAgreementPeriodDesc(String agreementPeriodDesc) {
		this.agreementPeriodDesc = agreementPeriodDesc;
	}
	public String getAgreementDate() {
		return agreementDate;
	}
	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
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
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getPeriodDuration() {
		return periodDuration;
	}
	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}
	public String gettNocId() {
		return tNocId;
	}
	public void settNocId(String tNocId) {
		this.tNocId = tNocId;
	}
	public String getIsNocEnclosed() {
		return isNocEnclosed;
	}
	public void setIsNocEnclosed(String isNocEnclosed) {
		this.isNocEnclosed = isNocEnclosed;
	}
	public String getProposedCapacity() {
		return proposedCapacity;
	}
	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}
	public String getApprovedCapacity() {
		return approvedCapacity;
	}
	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
	}
	public String getIsAbtInstalled() {
		return isAbtInstalled;
	}
	public void setIsAbtInstalled(String isAbtInstalled) {
		this.isAbtInstalled = isAbtInstalled;
	}
	public String getNoAbtReason() {
		return noAbtReason;
	}
	public void setNoAbtReason(String noAbtReason) {
		this.noAbtReason = noAbtReason;
	}
	public String getHasRealTimeCon() {
		return hasRealTimeCon;
	}
	public void setHasRealTimeCon(String hasRealTimeCon) {
		this.hasRealTimeCon = hasRealTimeCon;
	}
	public String getExemptRc() {
		return exemptRc;
	}
	public void setExemptRc(String exemptRc) {
		this.exemptRc = exemptRc;
	}
	public String getHasDues() {
		return hasDues;
	}
	public void setHasDues(String hasDues) {
		this.hasDues = hasDues;
	}
	public String getDueDetails() {
		return dueDetails;
	}
	public void setDueDetails(String dueDetails) {
		this.dueDetails = dueDetails;
	}
	public String getPendingCaseDetails() {
		return pendingCaseDetails;
	}
	public void setPendingCaseDetails(String pendingCaseDetails) {
		this.pendingCaseDetails = pendingCaseDetails;
	}
	public String getTechnicalFeasibilityDetails() {
		return technicalFeasibilityDetails;
	}
	public void setTechnicalFeasibilityDetails(String technicalFeasibilityDetails) {
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
	}
	public String getSellerOrgId() {
		return sellerOrgId;
	}
	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}
	public String getSellerOrgName() {
		return sellerOrgName;
	}
	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}
	public String getSellerCompServiceId() {
		return sellerCompServiceId;
	}
	public void setSellerCompServiceId(String sellerCompServiceId) {
		this.sellerCompServiceId = sellerCompServiceId;
	}
	public String getSellerCompServiceNumber() {
		return sellerCompServiceNumber;
	}
	public void setSellerCompServiceNumber(String sellerCompServiceNumber) {
		this.sellerCompServiceNumber = sellerCompServiceNumber;
	}
	public String getSellerCompanyName() {
		return sellerCompanyName;
	}
	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}
	public String getSellerCompanyId() {
		return sellerCompanyId;
	}
	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}
	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}
	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}
	public String getInjectionVoltageDesc() {
		return injectionVoltageDesc;
	}
	public void setInjectionVoltageDesc(String injectionVoltageDesc) {
		this.injectionVoltageDesc = injectionVoltageDesc;
	}
	public String getInjectionSubstationId() {
		return injectionSubstationId;
	}
	public void setInjectionSubstationId(String injectionSubstationId) {
		this.injectionSubstationId = injectionSubstationId;
	}
	public String getInjectionSubstationName() {
		return injectionSubstationName;
	}
	public void setInjectionSubstationName(String injectionSubstationName) {
		this.injectionSubstationName = injectionSubstationName;
	}
	public String getInjectionFeederId() {
		return injectionFeederId;
	}
	public void setInjectionFeederId(String injectionFeederId) {
		this.injectionFeederId = injectionFeederId;
	}
	public String getInjectionFeederName() {
		return injectionFeederName;
	}
	public void setInjectionFeederName(String injectionFeederName) {
		this.injectionFeederName = injectionFeederName;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getConsentNumber() {
		return consentNumber;
	}
	public void setConsentNumber(String consentNumber) {
		this.consentNumber = consentNumber;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String gettEsIntentId() {
		return tEsIntentId;
	}
	public void settEsIntentId(String tEsIntentId) {
		this.tEsIntentId = tEsIntentId;
	}
	public String getExistingOAAs() {
		return existingOAAs;
	}
	public void setExistingOAAs(String existingOAAs) {
		this.existingOAAs = existingOAAs;
	}
	public String getEsIntentCode() {
		return esIntentCode;
	}
	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
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
	 
	
}