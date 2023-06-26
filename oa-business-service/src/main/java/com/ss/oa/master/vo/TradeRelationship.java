package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class TradeRelationship {
	private String id;
	private String quantum;
	private String fromDate,toDate,month,year;
	private String statusCode,statusName;
	private String sellerCompanyId,sellerCompanyCode,sellerCompanyName;
	private String sellerCompServiceId,sellerCompServiceNumber, sellerOrgId, sellerOrgName;
	private String buyerCompanyId,buyerCompanyCode,buyerCompanyName;
	private String buyerCompServiceId,buyerCompServiceNumber, buyerOrgId, buyerOrgName,buyerOrgCode;
	private String referenceNumber,remarks,createdBy,createdDate,modifiedBy,modifiedDate;
	private String isCaptive,sharePercent;
	private String signupId; //used in signup-trade-relationship
	private String c1,c2,c3,c4,c5;
	private String peakUnits,offPeakUnits,intervalTypeCode,intervalTypeName,traderelationshipSourceCode,agreementType;
	private String flowTypeCode;

	
	public TradeRelationship() {
		super();
	}


	public TradeRelationship(String id, String quantum, String fromDate, String toDate, String month, String year,
			String statusCode, String statusName, String sellerCompanyId, String sellerCompanyCode,
			String sellerCompanyName, String sellerCompServiceId, String sellerCompServiceNumber, String sellerOrgId,
			String sellerOrgName, String buyerCompanyId, String buyerCompanyCode, String buyerCompanyName,
			String buyerCompServiceId, String buyerCompServiceNumber, String buyerOrgId, String buyerOrgName,
			String buyerOrgCode, String referenceNumber, String remarks, String createdBy, String createdDate,
			String modifiedBy, String modifiedDate, String isCaptive, String sharePercent, String signupId, String c1,
			String c2, String c3, String c4, String c5, String peakUnits, String offPeakUnits, String intervalTypeCode,
			String intervalTypeName, String traderelationshipSourceCode, String agreementType, String flowTypeCode) {
		super();
		this.id = id;
		this.quantum = quantum;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.month = month;
		this.year = year;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerOrgCode = buyerOrgCode;
		this.referenceNumber = referenceNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isCaptive = isCaptive;
		this.sharePercent = sharePercent;
		this.signupId = signupId;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.intervalTypeCode = intervalTypeCode;
		this.intervalTypeName = intervalTypeName;
		this.traderelationshipSourceCode = traderelationshipSourceCode;
		this.agreementType = agreementType;
		this.flowTypeCode = flowTypeCode;
	}


	@Override
	public String toString() {
		return "TradeRelationship [id=" + id + ", quantum=" + quantum + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", month=" + month + ", year=" + year + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompServiceId=" + sellerCompServiceId
				+ ", sellerCompServiceNumber=" + sellerCompServiceNumber + ", sellerOrgId=" + sellerOrgId
				+ ", sellerOrgName=" + sellerOrgName + ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyCode="
				+ buyerCompanyCode + ", buyerCompanyName=" + buyerCompanyName + ", buyerCompServiceId="
				+ buyerCompServiceId + ", buyerCompServiceNumber=" + buyerCompServiceNumber + ", buyerOrgId="
				+ buyerOrgId + ", buyerOrgName=" + buyerOrgName + ", buyerOrgCode=" + buyerOrgCode
				+ ", referenceNumber=" + referenceNumber + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", isCaptive=" + isCaptive + ", sharePercent=" + sharePercent + ", signupId=" + signupId + ", c1="
				+ c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", peakUnits=" + peakUnits
				+ ", offPeakUnits=" + offPeakUnits + ", intervalTypeCode=" + intervalTypeCode + ", intervalTypeName="
				+ intervalTypeName + ", traderelationshipSourceCode=" + traderelationshipSourceCode + ", agreementType="
				+ agreementType + ", flowTypeCode=" + flowTypeCode + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
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


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
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


	public String getSellerCompanyId() {
		return sellerCompanyId;
	}


	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}


	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}


	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}


	public String getSellerCompanyName() {
		return sellerCompanyName;
	}


	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
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


	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}


	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}


	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}


	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}


	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}


	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
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


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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


	public String getIsCaptive() {
		return isCaptive;
	}


	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}


	public String getSharePercent() {
		return sharePercent;
	}


	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}


	public String getSignupId() {
		return signupId;
	}


	public void setSignupId(String signupId) {
		this.signupId = signupId;
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


	public String getTraderelationshipSourceCode() {
		return traderelationshipSourceCode;
	}


	public void setTraderelationshipSourceCode(String traderelationshipSourceCode) {
		this.traderelationshipSourceCode = traderelationshipSourceCode;
	}


	public String getAgreementType() {
		return agreementType;
	}


	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}


	public String getFlowTypeCode() {
		return flowTypeCode;
	}


	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}


	

	
}
