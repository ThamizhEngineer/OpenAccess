package com.ss.oa.model.master;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "M_TRADE_RELATIONSHIP")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class TradeRelationship {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	private String id;
	@Column(name="QUANTUM")
	private String quantum;
	@Column(name="FROM_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;
	@Column(name="TO_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	@Transient
	private String month;
	@Transient
	private String year;
	@Column(name="STATUS_CODE")
	private String statusCode;
	@Transient
	private String statusName;
	@Column(name="M_SELLER_COMPANY_ID")
	private String sellerCompanyId;
	@Formula("(SELECT com.NAME FROM M_COMPANY com WHERE com.id=M_SELLER_COMPANY_ID)")
	private String sellerCompanyName;
	@Column(name="M_SELLER_COMP_SERVICE_ID")
	private String sellerCompServiceId;
//	private String sellerCompServiceNumber;
	@Formula("(SELECT service.M_ORG_ID FROM M_TRADE_RELATIONSHIP trade LEFT JOIN M_COMPANY_SERVICE service ON trade.M_SELLER_COMP_SERVICE_ID=service.ID WHERE trade.M_SELLER_COMP_SERVICE_ID=M_SELLER_COMP_SERVICE_ID and trade.id=id)")
	private String sellerOrgId;
//	private String sellerOrgName;
	@Column(name="M_BUYER_COMPANY_ID")
	private String buyerCompanyId;
//	private String buyerCompanyCode;
	@Formula("(SELECT com.NAME FROM M_COMPANY com WHERE com.id=M_BUYER_COMPANY_ID)")
	private String buyerCompanyName;
	@Column(name="M_BUYER_COMP_SERVICE_ID")
	private String buyerCompServiceId;
//	private String buyerCompServiceNumber;
	@Formula("(SELECT service.M_ORG_ID FROM M_TRADE_RELATIONSHIP trade LEFT JOIN M_COMPANY_SERVICE service ON trade.M_BUYER_COMP_SERVICE_ID=service.ID WHERE trade.M_BUYER_COMP_SERVICE_ID=M_BUYER_COMP_SERVICE_ID and trade.id=id)")
	private String buyerOrgId;
//	private String buyerOrgName;
//	private String buyerOrgCode;
	@Column(name="REFERENCENUMBER")
	private String referenceNumber;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	@Column(name="IS_CAPTIVE", columnDefinition="CHAR(1)")
	private String isCaptive;
	@Column(name="ENABLED", columnDefinition="CHAR(1)")
	private String enabled;
	private String peakUnits;
	private String offPeakUnits;
	private String intervalTypeCode;
//	private String intervalTypeName;
	private String sharePercent;
	@Column(name="TRADE_RELATIONSHIP_SOURCE_CODE")
	private String traderelationshipSourceCode;
	private String agreementType;

	
	public TradeRelationship() {
		super();
	}


	public TradeRelationship(String id, String quantum, LocalDate fromDate, LocalDate toDate, String month, String year,
			String statusCode, String statusName, String sellerCompanyId, String sellerCompanyName,
			String sellerCompServiceId, String sellerOrgId, String buyerCompanyId, String buyerCompanyName,
			String buyerCompServiceId, String buyerOrgId, String referenceNumber, String remarks, String createdBy,
			LocalDate createdDate, String modifiedBy, LocalDate modifiedDate, String c1, String c2, String c3,
			String c4, String c5, String isCaptive, String enabled, String peakUnits, String offPeakUnits,
			String intervalTypeCode, String sharePercent, String traderelationshipSourceCode, String agreementType) {
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
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerOrgId = sellerOrgId;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerOrgId = buyerOrgId;
		this.referenceNumber = referenceNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.isCaptive = isCaptive;
		this.enabled = enabled;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.intervalTypeCode = intervalTypeCode;
		this.sharePercent = sharePercent;
		this.traderelationshipSourceCode = traderelationshipSourceCode;
		this.agreementType = agreementType;
	}


	@Override
	public String toString() {
		return "TradeRelationship [id=" + id + ", quantum=" + quantum + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", month=" + month + ", year=" + year + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyName=" + sellerCompanyName
				+ ", sellerCompServiceId=" + sellerCompServiceId + ", sellerOrgId=" + sellerOrgId + ", buyerCompanyId="
				+ buyerCompanyId + ", buyerCompanyName=" + buyerCompanyName + ", buyerCompServiceId="
				+ buyerCompServiceId + ", buyerOrgId=" + buyerOrgId + ", referenceNumber=" + referenceNumber
				+ ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + ", isCaptive=" + isCaptive + ", enabled=" + enabled + ", peakUnits=" + peakUnits
				+ ", offPeakUnits=" + offPeakUnits + ", intervalTypeCode=" + intervalTypeCode + ", sharePercent="
				+ sharePercent + ", traderelationshipSourceCode=" + traderelationshipSourceCode + ", agreementType="
				+ agreementType + "]";
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


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
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


	public String getSellerOrgId() {
		return sellerOrgId;
	}


	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
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


	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}


	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}


	public String getBuyerOrgId() {
		return buyerOrgId;
	}


	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
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


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDate getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
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


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
