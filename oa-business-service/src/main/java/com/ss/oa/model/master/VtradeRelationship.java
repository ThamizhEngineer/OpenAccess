package com.ss.oa.model.master;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;


@Table(name = "V_TRADERELATIONSHIP")
@Entity
@Scope("prototype")
public class VtradeRelationship {
	@Id
	@Column(name="ID")
	private String id;
	@Column(name="QUANTUM")
	private String quantum;
	@Column(name="FROM_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@Column(name="FROM_MONTH")
	private String month;
	@Column(name="FROM_YEAR")
	private String year;
	@Column(name="TO_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	@Column(name="STATUS_CODE")
	private String statusCode;
	@Column(name="STATUS_NAME")
	private String statusName;
	@Column(name="M_SELLER_COMPANY_ID")
	private String sellerCompanyId;
	@Column(name="M_SELLER_COMPANY_NAME")
	private String sellerCompanyName;
	@Column(name="M_SELLER_COMPANY_CODE")
	private String sellerCompanyCode;
	@Column(name="M_SELLER_COMP_SERVICE_ID")
	private String sellerCompServiceId;
	@Column(name="M_SELLER_COMP_SERVICE_NUMBER")
	private String sellerCompServiceNumber;
	@Column(name="M_BUYER_COMPANY_ID")
	private String buyerCompanyId;
	@Column(name="M_BUYER_COMPANY_NAME")
	private String buyerCompanyName;
	@Column(name="M_BUYER_COMPANY_CODE")
	private String buyerCompanyCode;
	@Column(name="M_BUYER_COMP_SERVICE_ID")
	private String buyerCompServiceId;
	@Column(name="M_BUYER_COMP_SERVICE_NUMBER")
	private String buyerCompServiceNumber;
	@Column(name="REFERENCENUMBER")
	private String referenceNumber;
	private String remarks;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	@Column(name="IS_CAPTIVE", columnDefinition="CHAR(1)")
	private String isCaptive;
	private String peakUnits;
	private String offPeakUnits;
	private String intervalTypeCode;
	private String sharePercent;
	private String intervalTypeName;
	private String sellerOrgId;
	private String buyerOrgId;
	@Column(name="TRADE_RELATIONSHIP_SOURCE_CODE")
	private String traderelationshipSourceCode;
	private String agreementType;
	private String buyerOrgName;

	
	public VtradeRelationship() {
		super();
	}


	public VtradeRelationship(String id, String quantum, LocalDate fromDate, String month, String year,
			LocalDate toDate, String statusCode, String statusName, String sellerCompanyId, String sellerCompanyName,
			String sellerCompanyCode, String sellerCompServiceId, String sellerCompServiceNumber, String buyerCompanyId,
			String buyerCompanyName, String buyerCompanyCode, String buyerCompServiceId, String buyerCompServiceNumber,
			String referenceNumber, String remarks, String c1, String c2, String c3, String c4, String c5,
			String isCaptive, String peakUnits, String offPeakUnits, String intervalTypeCode, String sharePercent,
			String intervalTypeName, String sellerOrgId, String buyerOrgId, String traderelationshipSourceCode,
			String agreementType, String buyerOrgName) {
		super();
		this.id = id;
		this.quantum = quantum;
		this.fromDate = fromDate;
		this.month = month;
		this.year = year;
		this.toDate = toDate;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.referenceNumber = referenceNumber;
		this.remarks = remarks;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.isCaptive = isCaptive;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.intervalTypeCode = intervalTypeCode;
		this.sharePercent = sharePercent;
		this.intervalTypeName = intervalTypeName;
		this.sellerOrgId = sellerOrgId;
		this.buyerOrgId = buyerOrgId;
		this.traderelationshipSourceCode = traderelationshipSourceCode;
		this.agreementType = agreementType;
		this.buyerOrgName = buyerOrgName;
	}


	@Override
	public String toString() {
		return "VtradeRelationship [id=" + id + ", quantum=" + quantum + ", fromDate=" + fromDate + ", month=" + month
				+ ", year=" + year + ", toDate=" + toDate + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyName=" + sellerCompanyName
				+ ", sellerCompanyCode=" + sellerCompanyCode + ", sellerCompServiceId=" + sellerCompServiceId
				+ ", sellerCompServiceNumber=" + sellerCompServiceNumber + ", buyerCompanyId=" + buyerCompanyId
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode
				+ ", buyerCompServiceId=" + buyerCompServiceId + ", buyerCompServiceNumber=" + buyerCompServiceNumber
				+ ", referenceNumber=" + referenceNumber + ", remarks=" + remarks + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", isCaptive=" + isCaptive + ", peakUnits=" + peakUnits
				+ ", offPeakUnits=" + offPeakUnits + ", intervalTypeCode=" + intervalTypeCode + ", sharePercent="
				+ sharePercent + ", intervalTypeName=" + intervalTypeName + ", sellerOrgId=" + sellerOrgId
				+ ", buyerOrgId=" + buyerOrgId + ", traderelationshipSourceCode=" + traderelationshipSourceCode
				+ ", agreementType=" + agreementType + ", buyerOrgName=" + buyerOrgName + "]";
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


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
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


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
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


	public String getIsCaptive() {
		return isCaptive;
	}


	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
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


	public String getSharePercent() {
		return sharePercent;
	}


	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}


	public String getIntervalTypeName() {
		return intervalTypeName;
	}


	public void setIntervalTypeName(String intervalTypeName) {
		this.intervalTypeName = intervalTypeName;
	}


	public String getSellerOrgId() {
		return sellerOrgId;
	}


	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}


	public String getBuyerOrgId() {
		return buyerOrgId;
	}


	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
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


	public String getBuyerOrgName() {
		return buyerOrgName;
	}


	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}


}
