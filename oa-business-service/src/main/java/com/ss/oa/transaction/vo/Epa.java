package com.ss.oa.transaction.vo;

import java.util.List;

public class Epa {
	private String id,code;
	private String agreementPeriodCode;
	private String agreementPeriodDesc; //(display)
	private String agreementDate;
	private String agreementNumber;
	private String fromDate;
	private String toDate;
	private String periodDuration; //(calculation)
	private String sellerOrgId,sellerOrgCode;
	private String sellerOrgName; 
	private String sellerCompServiceId;
	private String sellerCompServiceNumber; //(display based   sellerCompServiceId)
	private String sellerCompanyName; //(display based on sellerCompServiceId)
	private String sellerCompanyId; //(display based on sellerCompServiceId)
	private String sellerIsCaptive; //(Y/N)
	private String sellerSubstationId;
	private String sellerSubstationName; //(display)
	private String sellerSubstationCode;
	private String voltageCode;
	private String voltageDesc; //(display)
	private String sellerFeederId;
	private String sellerFeederCode;
	private String sellerFeederName; // (display)
	private String appliedDate;
	private String approvedDate;
	private String statusCode;
	private String statusDesc; // (display)
	private String epaNumber;
	private String epaAppNumber;
	private String proposedTotalUnits;
	private String approvedTotalUnits;
	private String totalGenCapacity;
	private String remarks;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String esIntentId,esIntentCode;
	private String districtCode,districtName;
	private String fuelTypeCode,fuelTypeName,fuelGroupe;
	private String c1,c2,c3,c4,c5;
	private String peakUnits,offPeakUnits;
	private String fromMonth,toMonth,fromYear,toYear;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String buyerOrgId,buyerOrgName,buyerOrgCode;
	private String buyerSubstationId,buyerSubstationCode,buyerSubstationName;
	private String buyerFeederId,buyerFeederCode,buyerFeederName;
	private String intervalTypeCode,intervalTypeName;
	private String sharePercent,flowTypeCode,flowTypeName;
	
    private List<EpaLine> epaLine;
    
	public Epa() {
		super();
	}

	public Epa(String id, String code, String agreementPeriodCode, String agreementPeriodDesc, String agreementDate,
			String agreementNumber, String fromDate, String toDate, String periodDuration, String sellerOrgId,
			String sellerOrgCode, String sellerOrgName, String sellerCompServiceId, String sellerCompServiceNumber,
			String sellerCompanyName, String sellerCompanyId, String sellerIsCaptive, String sellerSubstationId,
			String sellerSubstationName, String sellerSubstationCode, String voltageCode, String voltageDesc,
			String sellerFeederId, String sellerFeederCode, String sellerFeederName, String appliedDate,
			String approvedDate, String statusCode, String statusDesc, String epaNumber, String epaAppNumber,
			String proposedTotalUnits, String approvedTotalUnits, String totalGenCapacity, String remarks,
			String createdBy, String createdDate, String modifiedBy, String modifiedDate, String esIntentId,
			String esIntentCode, String districtCode, String districtName, String fuelTypeCode, String fuelTypeName,
			String fuelGroupe, String c1, String c2, String c3, String c4, String c5, String peakUnits,
			String offPeakUnits, String fromMonth, String toMonth, String fromYear, String toYear,
			String buyerCompanyServiceId, String buyerCompanyServiceNumber, String buyerCompanyId,
			String buyerCompanyName, String buyerCompanyCode, String buyerOrgId, String buyerOrgName,
			String buyerOrgCode, String buyerSubstationId, String buyerSubstationCode, String buyerSubstationName,
			String buyerFeederId, String buyerFeederCode, String buyerFeederName, String intervalTypeCode,
			String intervalTypeName, String sharePercent, String flowTypeCode, String flowTypeName,
			List<EpaLine> epaLine) {
		super();
		this.id = id;
		this.code = code;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodDesc = agreementPeriodDesc;
		this.agreementDate = agreementDate;
		this.agreementNumber = agreementNumber;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.periodDuration = periodDuration;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgCode = sellerOrgCode;
		this.sellerOrgName = sellerOrgName;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerIsCaptive = sellerIsCaptive;
		this.sellerSubstationId = sellerSubstationId;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerSubstationCode = sellerSubstationCode;
		this.voltageCode = voltageCode;
		this.voltageDesc = voltageDesc;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerFeederName = sellerFeederName;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
		this.epaNumber = epaNumber;
		this.epaAppNumber = epaAppNumber;
		this.proposedTotalUnits = proposedTotalUnits;
		this.approvedTotalUnits = approvedTotalUnits;
		this.totalGenCapacity = totalGenCapacity;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.fuelGroupe = fuelGroupe;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerSubstationId = buyerSubstationId;
		this.buyerSubstationCode = buyerSubstationCode;
		this.buyerSubstationName = buyerSubstationName;
		this.buyerFeederId = buyerFeederId;
		this.buyerFeederCode = buyerFeederCode;
		this.buyerFeederName = buyerFeederName;
		this.intervalTypeCode = intervalTypeCode;
		this.intervalTypeName = intervalTypeName;
		this.sharePercent = sharePercent;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.epaLine = epaLine;
	}

	@Override
	public String toString() {
		return "Epa [id=" + id + ", code=" + code + ", agreementPeriodCode=" + agreementPeriodCode
				+ ", agreementPeriodDesc=" + agreementPeriodDesc + ", agreementDate=" + agreementDate
				+ ", agreementNumber=" + agreementNumber + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", periodDuration=" + periodDuration + ", sellerOrgId=" + sellerOrgId + ", sellerOrgCode="
				+ sellerOrgCode + ", sellerOrgName=" + sellerOrgName + ", sellerCompServiceId=" + sellerCompServiceId
				+ ", sellerCompServiceNumber=" + sellerCompServiceNumber + ", sellerCompanyName=" + sellerCompanyName
				+ ", sellerCompanyId=" + sellerCompanyId + ", sellerIsCaptive=" + sellerIsCaptive
				+ ", sellerSubstationId=" + sellerSubstationId + ", sellerSubstationName=" + sellerSubstationName
				+ ", sellerSubstationCode=" + sellerSubstationCode + ", voltageCode=" + voltageCode + ", voltageDesc="
				+ voltageDesc + ", sellerFeederId=" + sellerFeederId + ", sellerFeederCode=" + sellerFeederCode
				+ ", sellerFeederName=" + sellerFeederName + ", appliedDate=" + appliedDate + ", approvedDate="
				+ approvedDate + ", statusCode=" + statusCode + ", statusDesc=" + statusDesc + ", epaNumber="
				+ epaNumber + ", epaAppNumber=" + epaAppNumber + ", proposedTotalUnits=" + proposedTotalUnits
				+ ", approvedTotalUnits=" + approvedTotalUnits + ", totalGenCapacity=" + totalGenCapacity + ", remarks="
				+ remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", esIntentId=" + esIntentId + ", esIntentCode=" + esIntentCode
				+ ", districtCode=" + districtCode + ", districtName=" + districtName + ", fuelTypeCode=" + fuelTypeCode
				+ ", fuelTypeName=" + fuelTypeName + ", fuelGroupe=" + fuelGroupe + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", peakUnits=" + peakUnits + ", offPeakUnits="
				+ offPeakUnits + ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear=" + fromYear
				+ ", toYear=" + toYear + ", buyerCompanyServiceId=" + buyerCompanyServiceId
				+ ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", buyerOrgId="
				+ buyerOrgId + ", buyerOrgName=" + buyerOrgName + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerSubstationId=" + buyerSubstationId + ", buyerSubstationCode=" + buyerSubstationCode
				+ ", buyerSubstationName=" + buyerSubstationName + ", buyerFeederId=" + buyerFeederId
				+ ", buyerFeederCode=" + buyerFeederCode + ", buyerFeederName=" + buyerFeederName
				+ ", intervalTypeCode=" + intervalTypeCode + ", intervalTypeName=" + intervalTypeName
				+ ", sharePercent=" + sharePercent + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName
				+ ", epaLine=" + epaLine + "]";
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

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
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

	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public String getSellerOrgId() {
		return sellerOrgId;
	}

	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}

	public String getSellerOrgCode() {
		return sellerOrgCode;
	}

	public void setSellerOrgCode(String sellerOrgCode) {
		this.sellerOrgCode = sellerOrgCode;
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

	public String getSellerIsCaptive() {
		return sellerIsCaptive;
	}

	public void setSellerIsCaptive(String sellerIsCaptive) {
		this.sellerIsCaptive = sellerIsCaptive;
	}

	public String getSellerSubstationId() {
		return sellerSubstationId;
	}

	public void setSellerSubstationId(String sellerSubstationId) {
		this.sellerSubstationId = sellerSubstationId;
	}

	public String getSellerSubstationName() {
		return sellerSubstationName;
	}

	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}

	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}

	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public String getVoltageDesc() {
		return voltageDesc;
	}

	public void setVoltageDesc(String voltageDesc) {
		this.voltageDesc = voltageDesc;
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

	public String getEpaNumber() {
		return epaNumber;
	}

	public void setEpaNumber(String epaNumber) {
		this.epaNumber = epaNumber;
	}

	public String getEpaAppNumber() {
		return epaAppNumber;
	}

	public void setEpaAppNumber(String epaAppNumber) {
		this.epaAppNumber = epaAppNumber;
	}

	public String getProposedTotalUnits() {
		return proposedTotalUnits;
	}

	public void setProposedTotalUnits(String proposedTotalUnits) {
		this.proposedTotalUnits = proposedTotalUnits;
	}

	public String getApprovedTotalUnits() {
		return approvedTotalUnits;
	}

	public void setApprovedTotalUnits(String approvedTotalUnits) {
		this.approvedTotalUnits = approvedTotalUnits;
	}

	public String getTotalGenCapacity() {
		return totalGenCapacity;
	}

	public void setTotalGenCapacity(String totalGenCapacity) {
		this.totalGenCapacity = totalGenCapacity;
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

	public String getEsIntentId() {
		return esIntentId;
	}

	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}

	public String getEsIntentCode() {
		return esIntentCode;
	}

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public String getFuelGroupe() {
		return fuelGroupe;
	}

	public void setFuelGroupe(String fuelGroupe) {
		this.fuelGroupe = fuelGroupe;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getPeakUnits() {
		return peakUnits;
	}

	public void setPeakUnits(String peakUnits) {
		this.peakUnits = peakUnits;
	}

	public String getOffPeakUnits() {
		return offPeakUnits;
	}

	public void setOffPeakUnits(String offPeakUnits) {
		this.offPeakUnits = offPeakUnits;
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

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}

	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}

	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
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

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public String getBuyerSubstationId() {
		return buyerSubstationId;
	}

	public void setBuyerSubstationId(String buyerSubstationId) {
		this.buyerSubstationId = buyerSubstationId;
	}

	public String getBuyerSubstationCode() {
		return buyerSubstationCode;
	}

	public void setBuyerSubstationCode(String buyerSubstationCode) {
		this.buyerSubstationCode = buyerSubstationCode;
	}

	public String getBuyerSubstationName() {
		return buyerSubstationName;
	}

	public void setBuyerSubstationName(String buyerSubstationName) {
		this.buyerSubstationName = buyerSubstationName;
	}

	public String getBuyerFeederId() {
		return buyerFeederId;
	}

	public void setBuyerFeederId(String buyerFeederId) {
		this.buyerFeederId = buyerFeederId;
	}

	public String getBuyerFeederCode() {
		return buyerFeederCode;
	}

	public void setBuyerFeederCode(String buyerFeederCode) {
		this.buyerFeederCode = buyerFeederCode;
	}

	public String getBuyerFeederName() {
		return buyerFeederName;
	}

	public void setBuyerFeederName(String buyerFeederName) {
		this.buyerFeederName = buyerFeederName;
	}

	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}

	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
	}

	public String getIntervalTypeName() {
		return intervalTypeName;
	}

	public void setIntervalTypeName(String intervalTypeName) {
		this.intervalTypeName = intervalTypeName;
	}

	public String getSharePercent() {
		return sharePercent;
	}

	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
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

	public List<EpaLine> getEpaLine() {
		return epaLine;
	}

	public void setEpaLine(List<EpaLine> epaLine) {
		this.epaLine = epaLine;
	}

	
	
	
}
