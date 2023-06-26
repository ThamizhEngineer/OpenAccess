package com.ss.oa.transaction.vo;

public class Oaa {
	
	String id,code;
	//-------------(BuyerDetails)-------------
	String buyerOrgId;
	String buyerOrgName; //(display based on buyerOrgId)
	String buyerEndUtility;
	String buyerCompServiceId;
	String buyerCompServiceNumber; //(display based buyerCompServiceId)
	String buyerCompanyName; //(display based buyerCompServiceId)
	String buyerCompanyId; //(display based buyerCompServiceId) 
	String drawalTransSubstationId;
	String drawalTransSubstationName; // (display)
	String drawalTransVoltageCode;
	String drawalTransVoltageDesc; //(display)
	String drawalDistSubstationId;
	String drawalDistSubstationName; // (display)
	String drawalDistVoltageCode;
	String drawalDistVoltageDesc; //(display)
	String drawalFeederId;
	String drawalFeederName; // (display)
	//-------------(EnergyDetails)-------------
	String agreementPeriodCode;
	String agreementPeriodDesc; //(display)
	String agreementDate;
	String fromDate;
	String toDate;
	String periodDuration; //(calculation)
	String proposedTotalUnits;
	String approvedTotalUnits;
	String c1Units;
	String c2Units;
	String c3Units;
	String c4Units;
	String c5Units;
	String intervalTypeCode,peakUnits,offPeakUnits,sharePercent;
	//-------------(SellerDetails)-------------
	String sellerOrgId;
	String sellerOrgName; //(display)
	String sellerEndUtility;
	String sellerCompServiceId;
	String sellerCompServiceNumber; //(display based on sellerCompServiceId)
	String sellerCompanyName; //(display based on sellerCompServiceId)
	String sellerCompanyId; //(display based on sellerCompServiceId)
	String sellerIsCaptive; //(Y/N)
	String injectionTransSubstationId;
	String injectionTransSubstationName; //(display)
	String injectionTransVoltageCode;
	String injectionTransVoltageDesc; //(display)
	String injectionDistSubstationId;
	String injectionDistSubstationName; // (display)
	String injectionDistVoltageCode;
	String injectionDistVoltageDesc; //(display)
	String injectionFeederId;
	String injectionFeederName; // (display)
	String sellerCapacity;
	//-------------(Payment Details)-------------
	String paymentTypeCode;
	String paymentTypeDesc;
	String paymentBankDetails;
	String paymentTxnNo;
	String paymentDate;
	String paymentAmount;
	//-------------(Metadata)-------------
	String appliedDate;
	String approvedDate;
	String statusCode;
	String statusDesc; // (display)
	String oaaAppNumber;
	String remarks;
	String createdBy;
	String createdDate;
	String modifiedBy;
	String modifiedDate;
	//-------------(miscDetails)-------------
	String tEsIntentId,esIntentCode;
	String licensee;
	private String flowTypeCode,flowTypeName;
	
	public Oaa() {
		super();
	
	}

	public Oaa(String id, String code, String buyerOrgId, String buyerOrgName, String buyerEndUtility,
			String buyerCompServiceId, String buyerCompServiceNumber, String buyerCompanyName, String buyerCompanyId,
			String drawalTransSubstationId, String drawalTransSubstationName, String drawalTransVoltageCode,
			String drawalTransVoltageDesc, String drawalDistSubstationId, String drawalDistSubstationName,
			String drawalDistVoltageCode, String drawalDistVoltageDesc, String drawalFeederId, String drawalFeederName,
			String agreementPeriodCode, String agreementPeriodDesc, String agreementDate, String fromDate,
			String toDate, String periodDuration, String proposedTotalUnits, String approvedTotalUnits, String c1Units,
			String c2Units, String c3Units, String c4Units, String c5Units, String intervalTypeCode, String peakUnits,
			String offPeakUnits, String sharePercent, String sellerOrgId, String sellerOrgName, String sellerEndUtility,
			String sellerCompServiceId, String sellerCompServiceNumber, String sellerCompanyName,
			String sellerCompanyId, String sellerIsCaptive, String injectionTransSubstationId,
			String injectionTransSubstationName, String injectionTransVoltageCode, String injectionTransVoltageDesc,
			String injectionDistSubstationId, String injectionDistSubstationName, String injectionDistVoltageCode,
			String injectionDistVoltageDesc, String injectionFeederId, String injectionFeederName,
			String sellerCapacity, String paymentTypeCode, String paymentTypeDesc, String paymentBankDetails,
			String paymentTxnNo, String paymentDate, String paymentAmount, String appliedDate, String approvedDate,
			String statusCode, String statusDesc, String oaaAppNumber, String remarks, String createdBy,
			String createdDate, String modifiedBy, String modifiedDate, String tEsIntentId, String esIntentCode,
			String licensee, String flowTypeCode, String flowTypeName) {
		super();
		this.id = id;
		this.code = code;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerEndUtility = buyerEndUtility;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyId = buyerCompanyId;
		this.drawalTransSubstationId = drawalTransSubstationId;
		this.drawalTransSubstationName = drawalTransSubstationName;
		this.drawalTransVoltageCode = drawalTransVoltageCode;
		this.drawalTransVoltageDesc = drawalTransVoltageDesc;
		this.drawalDistSubstationId = drawalDistSubstationId;
		this.drawalDistSubstationName = drawalDistSubstationName;
		this.drawalDistVoltageCode = drawalDistVoltageCode;
		this.drawalDistVoltageDesc = drawalDistVoltageDesc;
		this.drawalFeederId = drawalFeederId;
		this.drawalFeederName = drawalFeederName;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodDesc = agreementPeriodDesc;
		this.agreementDate = agreementDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.periodDuration = periodDuration;
		this.proposedTotalUnits = proposedTotalUnits;
		this.approvedTotalUnits = approvedTotalUnits;
		this.c1Units = c1Units;
		this.c2Units = c2Units;
		this.c3Units = c3Units;
		this.c4Units = c4Units;
		this.c5Units = c5Units;
		this.intervalTypeCode = intervalTypeCode;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.sharePercent = sharePercent;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerEndUtility = sellerEndUtility;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerIsCaptive = sellerIsCaptive;
		this.injectionTransSubstationId = injectionTransSubstationId;
		this.injectionTransSubstationName = injectionTransSubstationName;
		this.injectionTransVoltageCode = injectionTransVoltageCode;
		this.injectionTransVoltageDesc = injectionTransVoltageDesc;
		this.injectionDistSubstationId = injectionDistSubstationId;
		this.injectionDistSubstationName = injectionDistSubstationName;
		this.injectionDistVoltageCode = injectionDistVoltageCode;
		this.injectionDistVoltageDesc = injectionDistVoltageDesc;
		this.injectionFeederId = injectionFeederId;
		this.injectionFeederName = injectionFeederName;
		this.sellerCapacity = sellerCapacity;
		this.paymentTypeCode = paymentTypeCode;
		this.paymentTypeDesc = paymentTypeDesc;
		this.paymentBankDetails = paymentBankDetails;
		this.paymentTxnNo = paymentTxnNo;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
		this.oaaAppNumber = oaaAppNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.tEsIntentId = tEsIntentId;
		this.esIntentCode = esIntentCode;
		this.licensee = licensee;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
	}

	@Override
	public String toString() {
		return "Oaa [id=" + id + ", code=" + code + ", buyerOrgId=" + buyerOrgId + ", buyerOrgName=" + buyerOrgName
				+ ", buyerEndUtility=" + buyerEndUtility + ", buyerCompServiceId=" + buyerCompServiceId
				+ ", buyerCompServiceNumber=" + buyerCompServiceNumber + ", buyerCompanyName=" + buyerCompanyName
				+ ", buyerCompanyId=" + buyerCompanyId + ", drawalTransSubstationId=" + drawalTransSubstationId
				+ ", drawalTransSubstationName=" + drawalTransSubstationName + ", drawalTransVoltageCode="
				+ drawalTransVoltageCode + ", drawalTransVoltageDesc=" + drawalTransVoltageDesc
				+ ", drawalDistSubstationId=" + drawalDistSubstationId + ", drawalDistSubstationName="
				+ drawalDistSubstationName + ", drawalDistVoltageCode=" + drawalDistVoltageCode
				+ ", drawalDistVoltageDesc=" + drawalDistVoltageDesc + ", drawalFeederId=" + drawalFeederId
				+ ", drawalFeederName=" + drawalFeederName + ", agreementPeriodCode=" + agreementPeriodCode
				+ ", agreementPeriodDesc=" + agreementPeriodDesc + ", agreementDate=" + agreementDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", periodDuration=" + periodDuration + ", proposedTotalUnits="
				+ proposedTotalUnits + ", approvedTotalUnits=" + approvedTotalUnits + ", c1Units=" + c1Units
				+ ", c2Units=" + c2Units + ", c3Units=" + c3Units + ", c4Units=" + c4Units + ", c5Units=" + c5Units
				+ ", intervalTypeCode=" + intervalTypeCode + ", peakUnits=" + peakUnits + ", offPeakUnits="
				+ offPeakUnits + ", sharePercent=" + sharePercent + ", sellerOrgId=" + sellerOrgId + ", sellerOrgName="
				+ sellerOrgName + ", sellerEndUtility=" + sellerEndUtility + ", sellerCompServiceId="
				+ sellerCompServiceId + ", sellerCompServiceNumber=" + sellerCompServiceNumber + ", sellerCompanyName="
				+ sellerCompanyName + ", sellerCompanyId=" + sellerCompanyId + ", sellerIsCaptive=" + sellerIsCaptive
				+ ", injectionTransSubstationId=" + injectionTransSubstationId + ", injectionTransSubstationName="
				+ injectionTransSubstationName + ", injectionTransVoltageCode=" + injectionTransVoltageCode
				+ ", injectionTransVoltageDesc=" + injectionTransVoltageDesc + ", injectionDistSubstationId="
				+ injectionDistSubstationId + ", injectionDistSubstationName=" + injectionDistSubstationName
				+ ", injectionDistVoltageCode=" + injectionDistVoltageCode + ", injectionDistVoltageDesc="
				+ injectionDistVoltageDesc + ", injectionFeederId=" + injectionFeederId + ", injectionFeederName="
				+ injectionFeederName + ", sellerCapacity=" + sellerCapacity + ", paymentTypeCode=" + paymentTypeCode
				+ ", paymentTypeDesc=" + paymentTypeDesc + ", paymentBankDetails=" + paymentBankDetails
				+ ", paymentTxnNo=" + paymentTxnNo + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", appliedDate=" + appliedDate + ", approvedDate=" + approvedDate + ", statusCode=" + statusCode
				+ ", statusDesc=" + statusDesc + ", oaaAppNumber=" + oaaAppNumber + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", tEsIntentId=" + tEsIntentId + ", esIntentCode=" + esIntentCode
				+ ", licensee=" + licensee + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + "]";
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerEndUtility() {
		return buyerEndUtility;
	}

	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}

	public String getBuyerCompServiceNumber() {
		return buyerCompServiceNumber;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public String getDrawalTransSubstationId() {
		return drawalTransSubstationId;
	}

	public String getDrawalTransSubstationName() {
		return drawalTransSubstationName;
	}

	public String getDrawalTransVoltageCode() {
		return drawalTransVoltageCode;
	}

	public String getDrawalTransVoltageDesc() {
		return drawalTransVoltageDesc;
	}

	public String getDrawalDistSubstationId() {
		return drawalDistSubstationId;
	}

	public String getDrawalDistSubstationName() {
		return drawalDistSubstationName;
	}

	public String getDrawalDistVoltageCode() {
		return drawalDistVoltageCode;
	}

	public String getDrawalDistVoltageDesc() {
		return drawalDistVoltageDesc;
	}

	public String getDrawalFeederId() {
		return drawalFeederId;
	}

	public String getDrawalFeederName() {
		return drawalFeederName;
	}

	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}

	public String getAgreementPeriodDesc() {
		return agreementPeriodDesc;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public String getPeriodDuration() {
		return periodDuration;
	}

	public String getProposedTotalUnits() {
		return proposedTotalUnits;
	}

	public String getApprovedTotalUnits() {
		return approvedTotalUnits;
	}

	public String getC1Units() {
		return c1Units;
	}

	public String getC2Units() {
		return c2Units;
	}

	public String getC3Units() {
		return c3Units;
	}

	public String getC4Units() {
		return c4Units;
	}

	public String getC5Units() {
		return c5Units;
	}

	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}

	public String getPeakUnits() {
		return peakUnits;
	}

	public String getOffPeakUnits() {
		return offPeakUnits;
	}

	public String getSharePercent() {
		return sharePercent;
	}

	public String getSellerOrgId() {
		return sellerOrgId;
	}

	public String getSellerOrgName() {
		return sellerOrgName;
	}

	public String getSellerEndUtility() {
		return sellerEndUtility;
	}

	public String getSellerCompServiceId() {
		return sellerCompServiceId;
	}

	public String getSellerCompServiceNumber() {
		return sellerCompServiceNumber;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public String getSellerIsCaptive() {
		return sellerIsCaptive;
	}

	public String getInjectionTransSubstationId() {
		return injectionTransSubstationId;
	}

	public String getInjectionTransSubstationName() {
		return injectionTransSubstationName;
	}

	public String getInjectionTransVoltageCode() {
		return injectionTransVoltageCode;
	}

	public String getInjectionTransVoltageDesc() {
		return injectionTransVoltageDesc;
	}

	public String getInjectionDistSubstationId() {
		return injectionDistSubstationId;
	}

	public String getInjectionDistSubstationName() {
		return injectionDistSubstationName;
	}

	public String getInjectionDistVoltageCode() {
		return injectionDistVoltageCode;
	}

	public String getInjectionDistVoltageDesc() {
		return injectionDistVoltageDesc;
	}

	public String getInjectionFeederId() {
		return injectionFeederId;
	}

	public String getInjectionFeederName() {
		return injectionFeederName;
	}

	public String getSellerCapacity() {
		return sellerCapacity;
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

	public String getAppliedDate() {
		return appliedDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public String getOaaAppNumber() {
		return oaaAppNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public String gettEsIntentId() {
		return tEsIntentId;
	}

	public String getEsIntentCode() {
		return esIntentCode;
	}

	public String getLicensee() {
		return licensee;
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

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerEndUtility(String buyerEndUtility) {
		this.buyerEndUtility = buyerEndUtility;
	}

	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}

	public void setBuyerCompServiceNumber(String buyerCompServiceNumber) {
		this.buyerCompServiceNumber = buyerCompServiceNumber;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public void setDrawalTransSubstationId(String drawalTransSubstationId) {
		this.drawalTransSubstationId = drawalTransSubstationId;
	}

	public void setDrawalTransSubstationName(String drawalTransSubstationName) {
		this.drawalTransSubstationName = drawalTransSubstationName;
	}

	public void setDrawalTransVoltageCode(String drawalTransVoltageCode) {
		this.drawalTransVoltageCode = drawalTransVoltageCode;
	}

	public void setDrawalTransVoltageDesc(String drawalTransVoltageDesc) {
		this.drawalTransVoltageDesc = drawalTransVoltageDesc;
	}

	public void setDrawalDistSubstationId(String drawalDistSubstationId) {
		this.drawalDistSubstationId = drawalDistSubstationId;
	}

	public void setDrawalDistSubstationName(String drawalDistSubstationName) {
		this.drawalDistSubstationName = drawalDistSubstationName;
	}

	public void setDrawalDistVoltageCode(String drawalDistVoltageCode) {
		this.drawalDistVoltageCode = drawalDistVoltageCode;
	}

	public void setDrawalDistVoltageDesc(String drawalDistVoltageDesc) {
		this.drawalDistVoltageDesc = drawalDistVoltageDesc;
	}

	public void setDrawalFeederId(String drawalFeederId) {
		this.drawalFeederId = drawalFeederId;
	}

	public void setDrawalFeederName(String drawalFeederName) {
		this.drawalFeederName = drawalFeederName;
	}

	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}

	public void setAgreementPeriodDesc(String agreementPeriodDesc) {
		this.agreementPeriodDesc = agreementPeriodDesc;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public void setProposedTotalUnits(String proposedTotalUnits) {
		this.proposedTotalUnits = proposedTotalUnits;
	}

	public void setApprovedTotalUnits(String approvedTotalUnits) {
		this.approvedTotalUnits = approvedTotalUnits;
	}

	public void setC1Units(String c1Units) {
		this.c1Units = c1Units;
	}

	public void setC2Units(String c2Units) {
		this.c2Units = c2Units;
	}

	public void setC3Units(String c3Units) {
		this.c3Units = c3Units;
	}

	public void setC4Units(String c4Units) {
		this.c4Units = c4Units;
	}

	public void setC5Units(String c5Units) {
		this.c5Units = c5Units;
	}

	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
	}

	public void setPeakUnits(String peakUnits) {
		this.peakUnits = peakUnits;
	}

	public void setOffPeakUnits(String offPeakUnits) {
		this.offPeakUnits = offPeakUnits;
	}

	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}

	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}

	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}

	public void setSellerEndUtility(String sellerEndUtility) {
		this.sellerEndUtility = sellerEndUtility;
	}

	public void setSellerCompServiceId(String sellerCompServiceId) {
		this.sellerCompServiceId = sellerCompServiceId;
	}

	public void setSellerCompServiceNumber(String sellerCompServiceNumber) {
		this.sellerCompServiceNumber = sellerCompServiceNumber;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public void setSellerIsCaptive(String sellerIsCaptive) {
		this.sellerIsCaptive = sellerIsCaptive;
	}

	public void setInjectionTransSubstationId(String injectionTransSubstationId) {
		this.injectionTransSubstationId = injectionTransSubstationId;
	}

	public void setInjectionTransSubstationName(String injectionTransSubstationName) {
		this.injectionTransSubstationName = injectionTransSubstationName;
	}

	public void setInjectionTransVoltageCode(String injectionTransVoltageCode) {
		this.injectionTransVoltageCode = injectionTransVoltageCode;
	}

	public void setInjectionTransVoltageDesc(String injectionTransVoltageDesc) {
		this.injectionTransVoltageDesc = injectionTransVoltageDesc;
	}

	public void setInjectionDistSubstationId(String injectionDistSubstationId) {
		this.injectionDistSubstationId = injectionDistSubstationId;
	}

	public void setInjectionDistSubstationName(String injectionDistSubstationName) {
		this.injectionDistSubstationName = injectionDistSubstationName;
	}

	public void setInjectionDistVoltageCode(String injectionDistVoltageCode) {
		this.injectionDistVoltageCode = injectionDistVoltageCode;
	}

	public void setInjectionDistVoltageDesc(String injectionDistVoltageDesc) {
		this.injectionDistVoltageDesc = injectionDistVoltageDesc;
	}

	public void setInjectionFeederId(String injectionFeederId) {
		this.injectionFeederId = injectionFeederId;
	}

	public void setInjectionFeederName(String injectionFeederName) {
		this.injectionFeederName = injectionFeederName;
	}

	public void setSellerCapacity(String sellerCapacity) {
		this.sellerCapacity = sellerCapacity;
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

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public void setOaaAppNumber(String oaaAppNumber) {
		this.oaaAppNumber = oaaAppNumber;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void settEsIntentId(String tEsIntentId) {
		this.tEsIntentId = tEsIntentId;
	}

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}

	public void setLicensee(String licensee) {
		this.licensee = licensee;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	



	
}
