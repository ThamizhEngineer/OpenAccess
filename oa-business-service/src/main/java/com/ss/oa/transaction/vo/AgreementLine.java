package com.ss.oa.transaction.vo;

public class AgreementLine {
	
	private String id,code;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String buyerOrgId,buyerOrgName,buyerOrgCode;
	private String buyerSubstationid,buyerSubstationCode,buyerSubstationName;
	private String buyerFeederId,buyerFeederCode,buyerFeederName;
	private String agreementId,drawalVoltageCode,drawalVoltageName;
	private String proposedCapacity,approvedCapacity;
	private String agreementPeriodCode,agreementPeriodName;
	private String flowTypeCode,flowTypeName;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear;
	private String appliedDate,approvedDate,agreementDate;
	private String isCaptive;
	private String c1,c2,c3,c4,c5;
	private String peakUnits,offPeakUnits;
	private String intervalTypeCode,intervalTypeName;
	private String sharePercent;
	private String paymentTypeCode;
	private String paymentTypeDesc;
	private String paymentBankDetails;
	private String paymentTxnNo;
	private String paymentDate;
	private String paymentAmount;
	private String licensee;
	private String  stateTransLoss ,stateTransLossPercent;  
	private String  stateTransCharges,stateTransChargesPercent ;  
	private String  schedulingCharges,schedulingChargesPercent; 
	private String  systemOprCharges,systemoprChargesPercent; 
	private String  statusCode,statusName;
	private String esIntentLineId;
	private String nocId,consentId,ewaLineId,ipaaLineId,nocGeneratorLineId,standingClearenceId;
	
	public AgreementLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgreementLine(String id, String code, String buyerCompanyServiceId, String buyerCompanyServiceNumber,
			String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode, String buyerOrgId,
			String buyerOrgName, String buyerOrgCode, String buyerSubstationid, String buyerSubstationCode,
			String buyerSubstationName, String buyerFeederId, String buyerFeederCode, String buyerFeederName,
			String agreementId, String drawalVoltageCode, String drawalVoltageName, String proposedCapacity,
			String approvedCapacity, String agreementPeriodCode, String agreementPeriodName, String flowTypeCode,
			String flowTypeName, String fromDate, String toDate, String fromMonth, String toMonth, String fromYear,
			String toYear, String appliedDate, String approvedDate, String agreementDate, String isCaptive, String c1,
			String c2, String c3, String c4, String c5, String peakUnits, String offPeakUnits, String intervalTypeCode,
			String intervalTypeName, String sharePercent, String paymentTypeCode, String paymentTypeDesc,
			String paymentBankDetails, String paymentTxnNo, String paymentDate, String paymentAmount, String licensee,
			String stateTransLoss, String stateTransLossPercent, String stateTransCharges,
			String stateTransChargesPercent, String schedulingCharges, String schedulingChargesPercent,
			String systemOprCharges, String systemoprChargesPercent, String statusCode, String statusName,
			String esIntentLineId, String nocId, String consentId, String ewaLineId, String ipaaLineId,
			String nocGeneratorLineId, String standingClearenceId) {
		super();
		this.id = id;
		this.code = code;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerSubstationid = buyerSubstationid;
		this.buyerSubstationCode = buyerSubstationCode;
		this.buyerSubstationName = buyerSubstationName;
		this.buyerFeederId = buyerFeederId;
		this.buyerFeederCode = buyerFeederCode;
		this.buyerFeederName = buyerFeederName;
		this.agreementId = agreementId;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageName = drawalVoltageName;
		this.proposedCapacity = proposedCapacity;
		this.approvedCapacity = approvedCapacity;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodName = agreementPeriodName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.agreementDate = agreementDate;
		this.isCaptive = isCaptive;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.intervalTypeCode = intervalTypeCode;
		this.intervalTypeName = intervalTypeName;
		this.sharePercent = sharePercent;
		this.paymentTypeCode = paymentTypeCode;
		this.paymentTypeDesc = paymentTypeDesc;
		this.paymentBankDetails = paymentBankDetails;
		this.paymentTxnNo = paymentTxnNo;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.licensee = licensee;
		this.stateTransLoss = stateTransLoss;
		this.stateTransLossPercent = stateTransLossPercent;
		this.stateTransCharges = stateTransCharges;
		this.stateTransChargesPercent = stateTransChargesPercent;
		this.schedulingCharges = schedulingCharges;
		this.schedulingChargesPercent = schedulingChargesPercent;
		this.systemOprCharges = systemOprCharges;
		this.systemoprChargesPercent = systemoprChargesPercent;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.esIntentLineId = esIntentLineId;
		this.nocId = nocId;
		this.consentId = consentId;
		this.ewaLineId = ewaLineId;
		this.ipaaLineId = ipaaLineId;
		this.nocGeneratorLineId = nocGeneratorLineId;
		this.standingClearenceId = standingClearenceId;
	}

	@Override
	public String toString() {
		return "AgreementLine [id=" + id + ", code=" + code + ", buyerCompanyServiceId=" + buyerCompanyServiceId
				+ ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", buyerOrgId="
				+ buyerOrgId + ", buyerOrgName=" + buyerOrgName + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerSubstationid=" + buyerSubstationid + ", buyerSubstationCode=" + buyerSubstationCode
				+ ", buyerSubstationName=" + buyerSubstationName + ", buyerFeederId=" + buyerFeederId
				+ ", buyerFeederCode=" + buyerFeederCode + ", buyerFeederName=" + buyerFeederName + ", agreementId="
				+ agreementId + ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageName=" + drawalVoltageName
				+ ", proposedCapacity=" + proposedCapacity + ", approvedCapacity=" + approvedCapacity
				+ ", agreementPeriodCode=" + agreementPeriodCode + ", agreementPeriodName=" + agreementPeriodName
				+ ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear=" + fromYear
				+ ", toYear=" + toYear + ", appliedDate=" + appliedDate + ", approvedDate=" + approvedDate
				+ ", agreementDate=" + agreementDate + ", isCaptive=" + isCaptive + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", peakUnits=" + peakUnits + ", offPeakUnits="
				+ offPeakUnits + ", intervalTypeCode=" + intervalTypeCode + ", intervalTypeName=" + intervalTypeName
				+ ", sharePercent=" + sharePercent + ", paymentTypeCode=" + paymentTypeCode + ", paymentTypeDesc="
				+ paymentTypeDesc + ", paymentBankDetails=" + paymentBankDetails + ", paymentTxnNo=" + paymentTxnNo
				+ ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount + ", licensee=" + licensee
				+ ", stateTransLoss=" + stateTransLoss + ", stateTransLossPercent=" + stateTransLossPercent
				+ ", stateTransCharges=" + stateTransCharges + ", stateTransChargesPercent=" + stateTransChargesPercent
				+ ", schedulingCharges=" + schedulingCharges + ", schedulingChargesPercent=" + schedulingChargesPercent
				+ ", systemOprCharges=" + systemOprCharges + ", systemoprChargesPercent=" + systemoprChargesPercent
				+ ", statusCode=" + statusCode + ", statusName=" + statusName + ", esIntentLineId=" + esIntentLineId
				+ ", nocId=" + nocId + ", consentId=" + consentId + ", ewaLineId=" + ewaLineId + ", ipaaLineId="
				+ ipaaLineId + ", nocGeneratorLineId=" + nocGeneratorLineId + ", standingClearenceId="
				+ standingClearenceId + "]";
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
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

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public String getBuyerSubstationid() {
		return buyerSubstationid;
	}

	public String getBuyerSubstationCode() {
		return buyerSubstationCode;
	}

	public String getBuyerSubstationName() {
		return buyerSubstationName;
	}

	public String getBuyerFeederId() {
		return buyerFeederId;
	}

	public String getBuyerFeederCode() {
		return buyerFeederCode;
	}

	public String getBuyerFeederName() {
		return buyerFeederName;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public String getDrawalVoltageName() {
		return drawalVoltageName;
	}

	public String getProposedCapacity() {
		return proposedCapacity;
	}

	public String getApprovedCapacity() {
		return approvedCapacity;
	}

	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}

	public String getAgreementPeriodName() {
		return agreementPeriodName;
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

	public String getAppliedDate() {
		return appliedDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public String getC1() {
		return c1;
	}

	public String getC2() {
		return c2;
	}

	public String getC3() {
		return c3;
	}

	public String getC4() {
		return c4;
	}

	public String getC5() {
		return c5;
	}

	public String getPeakUnits() {
		return peakUnits;
	}

	public String getOffPeakUnits() {
		return offPeakUnits;
	}

	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}

	public String getIntervalTypeName() {
		return intervalTypeName;
	}

	public String getSharePercent() {
		return sharePercent;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}

	public String getPaymentBankDetails() {
		return paymentBankDetails;
	}

	public String getPaymentTxnNo() {
		return paymentTxnNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public String getLicensee() {
		return licensee;
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

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getEsIntentLineId() {
		return esIntentLineId;
	}

	public String getNocId() {
		return nocId;
	}

	public String getConsentId() {
		return consentId;
	}

	public String getEwaLineId() {
		return ewaLineId;
	}

	public String getIpaaLineId() {
		return ipaaLineId;
	}

	public String getNocGeneratorLineId() {
		return nocGeneratorLineId;
	}

	public String getStandingClearenceId() {
		return standingClearenceId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
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

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public void setBuyerSubstationid(String buyerSubstationid) {
		this.buyerSubstationid = buyerSubstationid;
	}

	public void setBuyerSubstationCode(String buyerSubstationCode) {
		this.buyerSubstationCode = buyerSubstationCode;
	}

	public void setBuyerSubstationName(String buyerSubstationName) {
		this.buyerSubstationName = buyerSubstationName;
	}

	public void setBuyerFeederId(String buyerFeederId) {
		this.buyerFeederId = buyerFeederId;
	}

	public void setBuyerFeederCode(String buyerFeederCode) {
		this.buyerFeederCode = buyerFeederCode;
	}

	public void setBuyerFeederName(String buyerFeederName) {
		this.buyerFeederName = buyerFeederName;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public void setDrawalVoltageName(String drawalVoltageName) {
		this.drawalVoltageName = drawalVoltageName;
	}

	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}

	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
	}

	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}

	public void setAgreementPeriodName(String agreementPeriodName) {
		this.agreementPeriodName = agreementPeriodName;
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

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public void setPeakUnits(String peakUnits) {
		this.peakUnits = peakUnits;
	}

	public void setOffPeakUnits(String offPeakUnits) {
		this.offPeakUnits = offPeakUnits;
	}

	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
	}

	public void setIntervalTypeName(String intervalTypeName) {
		this.intervalTypeName = intervalTypeName;
	}

	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
	}

	public void setPaymentBankDetails(String paymentBankDetails) {
		this.paymentBankDetails = paymentBankDetails;
	}

	public void setPaymentTxnNo(String paymentTxnNo) {
		this.paymentTxnNo = paymentTxnNo;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public void setLicensee(String licensee) {
		this.licensee = licensee;
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

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setEsIntentLineId(String esIntentLineId) {
		this.esIntentLineId = esIntentLineId;
	}

	public void setNocId(String nocId) {
		this.nocId = nocId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public void setEwaLineId(String ewaLineId) {
		this.ewaLineId = ewaLineId;
	}

	public void setIpaaLineId(String ipaaLineId) {
		this.ipaaLineId = ipaaLineId;
	}

	public void setNocGeneratorLineId(String nocGeneratorLineId) {
		this.nocGeneratorLineId = nocGeneratorLineId;
	}

	public void setStandingClearenceId(String standingClearenceId) {
		this.standingClearenceId = standingClearenceId;
	}

	
	
	
	
	
	
}
