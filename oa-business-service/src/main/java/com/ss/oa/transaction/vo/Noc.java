package com.ss.oa.transaction.vo;

public class Noc {
	
	private String id,code;
	private String typeCode,typeName;
	private String statusCode,statusName;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String energySaleIntentId;
	private String proposedCapacity;
	private String exemptRc;
	private String hasDues;
	private String dueDetails;
	private String pendingCaseDetails;
	private String technicalFeasibilityDetails;
	private String approvedCapacity;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String buyerOrgId,buyerOrgName,buyerOrgCode;
	private String buyerCompanyServiceTypeCode,buyerCompanyServiceTypeName;
	private String buyerSanctionedCapacity;
	private String buyerSubstationId,buyerSubstationName,buyerSubstationCode;
	private String buyerFeederId,buyerFeederName,buyerFeederCode;
	private String buyerVoltageCode,buyerVoltageName;
	private String buyerSanctionedDemand;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear,isCaptive,esIntentCode;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String sellerOrgId,sellerOrgName,sellerOrgCode;
	private String sellerSubstationId,sellerSubstationName,sellerSubstationCode;
	private String sellerFeederId,sellerFeederName,sellerFeederCode;
	private String sellerVoltageCode,sellerVoltageName,sellerSanctionedCapacity;
	private String sellerCompanyServiceTypeCode,sellerCompanyServiceTypeName;
	private String sellerCompanyMeterId,sellerCompanyMeterNumber,isAbtMeter,modemNumber;
	private String buyerDistrictCode,buyerDistrictName;

	private String agreementDate;
	private String agreementPeriodCode;
	private String flowTypeCode,flowTypeName;
	
	public Noc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Noc(String id, String code, String typeCode, String typeName, String statusCode, String statusName,
			String buyerCompanyServiceId, String buyerCompanyServiceNumber, String energySaleIntentId,
			String proposedCapacity, String exemptRc, String hasDues, String dueDetails, String pendingCaseDetails,
			String technicalFeasibilityDetails, String approvedCapacity, String buyerCompanyId, String buyerCompanyName,
			String buyerCompanyCode, String buyerOrgId, String buyerOrgName, String buyerOrgCode,
			String buyerCompanyServiceTypeCode, String buyerCompanyServiceTypeName, String buyerSanctionedCapacity,
			String buyerSubstationId, String buyerSubstationName, String buyerSubstationCode, String buyerFeederId,
			String buyerFeederName, String buyerFeederCode, String buyerVoltageCode, String buyerVoltageName,
			String buyerSanctionedDemand, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String fromDate, String toDate, String fromMonth, String toMonth, String fromYear, String toYear,
			String isCaptive, String esIntentCode, String sellerCompanyId, String sellerCompanyName,
			String sellerCompanyCode, String sellerOrgId, String sellerOrgName, String sellerOrgCode,
			String sellerSubstationId, String sellerSubstationName, String sellerSubstationCode, String sellerFeederId,
			String sellerFeederName, String sellerFeederCode, String sellerVoltageCode, String sellerVoltageName,
			String sellerSanctionedCapacity, String sellerCompanyServiceTypeCode, String sellerCompanyServiceTypeName,
			String sellerCompanyMeterId, String sellerCompanyMeterNumber, String isAbtMeter, String modemNumber,
			String buyerDistrictCode, String buyerDistrictName, String agreementDate, String agreementPeriodCode,
			String flowTypeCode, String flowTypeName) {
		super();
		this.id = id;
		this.code = code;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.energySaleIntentId = energySaleIntentId;
		this.proposedCapacity = proposedCapacity;
		this.exemptRc = exemptRc;
		this.hasDues = hasDues;
		this.dueDetails = dueDetails;
		this.pendingCaseDetails = pendingCaseDetails;
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
		this.approvedCapacity = approvedCapacity;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerCompanyServiceTypeCode = buyerCompanyServiceTypeCode;
		this.buyerCompanyServiceTypeName = buyerCompanyServiceTypeName;
		this.buyerSanctionedCapacity = buyerSanctionedCapacity;
		this.buyerSubstationId = buyerSubstationId;
		this.buyerSubstationName = buyerSubstationName;
		this.buyerSubstationCode = buyerSubstationCode;
		this.buyerFeederId = buyerFeederId;
		this.buyerFeederName = buyerFeederName;
		this.buyerFeederCode = buyerFeederCode;
		this.buyerVoltageCode = buyerVoltageCode;
		this.buyerVoltageName = buyerVoltageName;
		this.buyerSanctionedDemand = buyerSanctionedDemand;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.isCaptive = isCaptive;
		this.esIntentCode = esIntentCode;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerOrgCode = sellerOrgCode;
		this.sellerSubstationId = sellerSubstationId;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederName = sellerFeederName;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerVoltageCode = sellerVoltageCode;
		this.sellerVoltageName = sellerVoltageName;
		this.sellerSanctionedCapacity = sellerSanctionedCapacity;
		this.sellerCompanyServiceTypeCode = sellerCompanyServiceTypeCode;
		this.sellerCompanyServiceTypeName = sellerCompanyServiceTypeName;
		this.sellerCompanyMeterId = sellerCompanyMeterId;
		this.sellerCompanyMeterNumber = sellerCompanyMeterNumber;
		this.isAbtMeter = isAbtMeter;
		this.modemNumber = modemNumber;
		this.buyerDistrictCode = buyerDistrictCode;
		this.buyerDistrictName = buyerDistrictName;
		this.agreementDate = agreementDate;
		this.agreementPeriodCode = agreementPeriodCode;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
	}

	@Override
	public String toString() {
		return "Noc [id=" + id + ", code=" + code + ", typeCode=" + typeCode + ", typeName=" + typeName
				+ ", statusCode=" + statusCode + ", statusName=" + statusName + ", buyerCompanyServiceId="
				+ buyerCompanyServiceId + ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber
				+ ", energySaleIntentId=" + energySaleIntentId + ", proposedCapacity=" + proposedCapacity
				+ ", exemptRc=" + exemptRc + ", hasDues=" + hasDues + ", dueDetails=" + dueDetails
				+ ", pendingCaseDetails=" + pendingCaseDetails + ", technicalFeasibilityDetails="
				+ technicalFeasibilityDetails + ", approvedCapacity=" + approvedCapacity + ", buyerCompanyId="
				+ buyerCompanyId + ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode
				+ ", buyerOrgId=" + buyerOrgId + ", buyerOrgName=" + buyerOrgName + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerCompanyServiceTypeCode=" + buyerCompanyServiceTypeCode + ", buyerCompanyServiceTypeName="
				+ buyerCompanyServiceTypeName + ", buyerSanctionedCapacity=" + buyerSanctionedCapacity
				+ ", buyerSubstationId=" + buyerSubstationId + ", buyerSubstationName=" + buyerSubstationName
				+ ", buyerSubstationCode=" + buyerSubstationCode + ", buyerFeederId=" + buyerFeederId
				+ ", buyerFeederName=" + buyerFeederName + ", buyerFeederCode=" + buyerFeederCode
				+ ", buyerVoltageCode=" + buyerVoltageCode + ", buyerVoltageName=" + buyerVoltageName
				+ ", buyerSanctionedDemand=" + buyerSanctionedDemand + ", sellerCompanyServiceId="
				+ sellerCompanyServiceId + ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear="
				+ fromYear + ", toYear=" + toYear + ", isCaptive=" + isCaptive + ", esIntentCode=" + esIntentCode
				+ ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyName=" + sellerCompanyName
				+ ", sellerCompanyCode=" + sellerCompanyCode + ", sellerOrgId=" + sellerOrgId + ", sellerOrgName="
				+ sellerOrgName + ", sellerOrgCode=" + sellerOrgCode + ", sellerSubstationId=" + sellerSubstationId
				+ ", sellerSubstationName=" + sellerSubstationName + ", sellerSubstationCode=" + sellerSubstationCode
				+ ", sellerFeederId=" + sellerFeederId + ", sellerFeederName=" + sellerFeederName
				+ ", sellerFeederCode=" + sellerFeederCode + ", sellerVoltageCode=" + sellerVoltageCode
				+ ", sellerVoltageName=" + sellerVoltageName + ", sellerSanctionedCapacity=" + sellerSanctionedCapacity
				+ ", sellerCompanyServiceTypeCode=" + sellerCompanyServiceTypeCode + ", sellerCompanyServiceTypeName="
				+ sellerCompanyServiceTypeName + ", sellerCompanyMeterId=" + sellerCompanyMeterId
				+ ", sellerCompanyMeterNumber=" + sellerCompanyMeterNumber + ", isAbtMeter=" + isAbtMeter
				+ ", modemNumber=" + modemNumber + ", buyerDistrictCode=" + buyerDistrictCode + ", buyerDistrictName="
				+ buyerDistrictName + ", agreementDate=" + agreementDate + ", agreementPeriodCode="
				+ agreementPeriodCode + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + "]";
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}

	public String getEnergySaleIntentId() {
		return energySaleIntentId;
	}

	public String getProposedCapacity() {
		return proposedCapacity;
	}

	public String getExemptRc() {
		return exemptRc;
	}

	public String getHasDues() {
		return hasDues;
	}

	public String getDueDetails() {
		return dueDetails;
	}

	public String getPendingCaseDetails() {
		return pendingCaseDetails;
	}

	public String getTechnicalFeasibilityDetails() {
		return technicalFeasibilityDetails;
	}

	public String getApprovedCapacity() {
		return approvedCapacity;
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

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public String getBuyerCompanyServiceTypeCode() {
		return buyerCompanyServiceTypeCode;
	}

	public String getBuyerCompanyServiceTypeName() {
		return buyerCompanyServiceTypeName;
	}

	public String getBuyerSanctionedCapacity() {
		return buyerSanctionedCapacity;
	}

	public String getBuyerSubstationId() {
		return buyerSubstationId;
	}

	public String getBuyerSubstationName() {
		return buyerSubstationName;
	}

	public String getBuyerSubstationCode() {
		return buyerSubstationCode;
	}

	public String getBuyerFeederId() {
		return buyerFeederId;
	}

	public String getBuyerFeederName() {
		return buyerFeederName;
	}

	public String getBuyerFeederCode() {
		return buyerFeederCode;
	}

	public String getBuyerVoltageCode() {
		return buyerVoltageCode;
	}

	public String getBuyerVoltageName() {
		return buyerVoltageName;
	}

	public String getBuyerSanctionedDemand() {
		return buyerSanctionedDemand;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
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

	public String getEsIntentCode() {
		return esIntentCode;
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

	public String getSellerSubstationId() {
		return sellerSubstationId;
	}

	public String getSellerSubstationName() {
		return sellerSubstationName;
	}

	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}

	public String getSellerFeederId() {
		return sellerFeederId;
	}

	public String getSellerFeederName() {
		return sellerFeederName;
	}

	public String getSellerFeederCode() {
		return sellerFeederCode;
	}

	public String getSellerVoltageCode() {
		return sellerVoltageCode;
	}

	public String getSellerVoltageName() {
		return sellerVoltageName;
	}

	public String getSellerSanctionedCapacity() {
		return sellerSanctionedCapacity;
	}

	public String getSellerCompanyServiceTypeCode() {
		return sellerCompanyServiceTypeCode;
	}

	public String getSellerCompanyServiceTypeName() {
		return sellerCompanyServiceTypeName;
	}

	public String getSellerCompanyMeterId() {
		return sellerCompanyMeterId;
	}

	public String getSellerCompanyMeterNumber() {
		return sellerCompanyMeterNumber;
	}

	public String getIsAbtMeter() {
		return isAbtMeter;
	}

	public String getModemNumber() {
		return modemNumber;
	}

	public String getBuyerDistrictCode() {
		return buyerDistrictCode;
	}

	public String getBuyerDistrictName() {
		return buyerDistrictName;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
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

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}

	public void setEnergySaleIntentId(String energySaleIntentId) {
		this.energySaleIntentId = energySaleIntentId;
	}

	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}

	public void setExemptRc(String exemptRc) {
		this.exemptRc = exemptRc;
	}

	public void setHasDues(String hasDues) {
		this.hasDues = hasDues;
	}

	public void setDueDetails(String dueDetails) {
		this.dueDetails = dueDetails;
	}

	public void setPendingCaseDetails(String pendingCaseDetails) {
		this.pendingCaseDetails = pendingCaseDetails;
	}

	public void setTechnicalFeasibilityDetails(String technicalFeasibilityDetails) {
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
	}

	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
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

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public void setBuyerCompanyServiceTypeCode(String buyerCompanyServiceTypeCode) {
		this.buyerCompanyServiceTypeCode = buyerCompanyServiceTypeCode;
	}

	public void setBuyerCompanyServiceTypeName(String buyerCompanyServiceTypeName) {
		this.buyerCompanyServiceTypeName = buyerCompanyServiceTypeName;
	}

	public void setBuyerSanctionedCapacity(String buyerSanctionedCapacity) {
		this.buyerSanctionedCapacity = buyerSanctionedCapacity;
	}

	public void setBuyerSubstationId(String buyerSubstationId) {
		this.buyerSubstationId = buyerSubstationId;
	}

	public void setBuyerSubstationName(String buyerSubstationName) {
		this.buyerSubstationName = buyerSubstationName;
	}

	public void setBuyerSubstationCode(String buyerSubstationCode) {
		this.buyerSubstationCode = buyerSubstationCode;
	}

	public void setBuyerFeederId(String buyerFeederId) {
		this.buyerFeederId = buyerFeederId;
	}

	public void setBuyerFeederName(String buyerFeederName) {
		this.buyerFeederName = buyerFeederName;
	}

	public void setBuyerFeederCode(String buyerFeederCode) {
		this.buyerFeederCode = buyerFeederCode;
	}

	public void setBuyerVoltageCode(String buyerVoltageCode) {
		this.buyerVoltageCode = buyerVoltageCode;
	}

	public void setBuyerVoltageName(String buyerVoltageName) {
		this.buyerVoltageName = buyerVoltageName;
	}

	public void setBuyerSanctionedDemand(String buyerSanctionedDemand) {
		this.buyerSanctionedDemand = buyerSanctionedDemand;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
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

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
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

	public void setSellerSubstationId(String sellerSubstationId) {
		this.sellerSubstationId = sellerSubstationId;
	}

	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}

	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}

	public void setSellerFeederId(String sellerFeederId) {
		this.sellerFeederId = sellerFeederId;
	}

	public void setSellerFeederName(String sellerFeederName) {
		this.sellerFeederName = sellerFeederName;
	}

	public void setSellerFeederCode(String sellerFeederCode) {
		this.sellerFeederCode = sellerFeederCode;
	}

	public void setSellerVoltageCode(String sellerVoltageCode) {
		this.sellerVoltageCode = sellerVoltageCode;
	}

	public void setSellerVoltageName(String sellerVoltageName) {
		this.sellerVoltageName = sellerVoltageName;
	}

	public void setSellerSanctionedCapacity(String sellerSanctionedCapacity) {
		this.sellerSanctionedCapacity = sellerSanctionedCapacity;
	}

	public void setSellerCompanyServiceTypeCode(String sellerCompanyServiceTypeCode) {
		this.sellerCompanyServiceTypeCode = sellerCompanyServiceTypeCode;
	}

	public void setSellerCompanyServiceTypeName(String sellerCompanyServiceTypeName) {
		this.sellerCompanyServiceTypeName = sellerCompanyServiceTypeName;
	}

	public void setSellerCompanyMeterId(String sellerCompanyMeterId) {
		this.sellerCompanyMeterId = sellerCompanyMeterId;
	}

	public void setSellerCompanyMeterNumber(String sellerCompanyMeterNumber) {
		this.sellerCompanyMeterNumber = sellerCompanyMeterNumber;
	}

	public void setIsAbtMeter(String isAbtMeter) {
		this.isAbtMeter = isAbtMeter;
	}

	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}

	public void setBuyerDistrictCode(String buyerDistrictCode) {
		this.buyerDistrictCode = buyerDistrictCode;
	}

	public void setBuyerDistrictName(String buyerDistrictName) {
		this.buyerDistrictName = buyerDistrictName;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	
	
}
