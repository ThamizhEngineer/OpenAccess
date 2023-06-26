package com.ss.oa.oadocumentservice.vo;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TranscoInvoice {

	private String id;

	private String mOrgId;
	private String mOrgName;

	private String InvCode;

	private String invoiceType;
	
	
	private String commDt;
	
	
	private String invoiceDt;

	private String lineYear;

	private String lineMonth;

	private String cusName;

	private String cusEmail;

	private String cusPhNo;

	private String cusState;

	private String cusAddress;

	private String cusPin;

	private String cusPan;

	private String cusStateCode;

	private String agmtType;

	private String cusGst;

	private String mCompanyId;

	private String mCompServId;

	private String mCompServNo;

	private String supName;

	private String mSubstationId;

	private String mSubstationCode;

	private String mSubstationName;

	private String eeOperation;

	private String refInvNo;

	private String supState;

	private String supStateCode;

	private String supPan;

	private String supGst;
	
	private String invFromDt;
	
	private String invToDt;
	
	private String agmtDt;
	
	private String amenDt;

	private String agmtDuration;

	private BigDecimal subTotalForGst;

	private BigDecimal subTotalForOthers;

	private BigDecimal igstTaxPer;

	private BigDecimal cgstTaxPer;

	private BigDecimal sgstTaxPer;

	private BigDecimal sgstTaxAmt;

	private BigDecimal igstTaxAmt;

	private BigDecimal cgstTaxAmt;

	private BigDecimal totalTaxAmt;

	private BigDecimal totalInvAmt;

	private String invAmtInWords;

	private String bankName;

	private String bankBranch;

	private String bankIfscCode;

	private String bankAccNo;

	private String createdBy;
	
	private String createdDate;

	private String modifiedBy;
	
	private String modifiedDate;

	private String pdfDocId;

	private String docId;

	private String invStatus;

	private String invCreatedBy;
	
	private String invCreatedDate;
	
	private String invForwardDate;

	private String invForwardBy;
	
	private String invApprovedDate;

	private String invApprovedBy;
	
	private String zenPostedDate;

	private String totalinwords;
	
	private String qrCode;
	
	private String qrCoderecivedDate;
	
	private String viritualAccno;
	
	private String capacity;
	
	private String transactiontype;
	
	private String reversecharge;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmOrgId() {
		return mOrgId;
	}

	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}

	public String getmOrgName() {
		return mOrgName;
	}

	public void setmOrgName(String mOrgName) {
		this.mOrgName = mOrgName;
	}

	public String getInvCode() {
		return InvCode;
	}

	public void setInvCode(String invCode) {
		InvCode = invCode;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getCommDt() {
		return commDt;
	}

	public void setCommDt(String commDt) {
		this.commDt = commDt;
	}

	public String getInvoiceDt() {
		return invoiceDt;
	}

	public void setInvoiceDt(String invoiceDt) {
		this.invoiceDt = invoiceDt;
	}

	public String getLineYear() {
		return lineYear;
	}

	public void setLineYear(String lineYear) {
		this.lineYear = lineYear;
	}

	public String getLineMonth() {
		return lineMonth;
	}

	public void setLineMonth(String lineMonth) {
		this.lineMonth = lineMonth;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusPhNo() {
		return cusPhNo;
	}

	public void setCusPhNo(String cusPhNo) {
		this.cusPhNo = cusPhNo;
	}

	public String getCusState() {
		return cusState;
	}

	public void setCusState(String cusState) {
		this.cusState = cusState;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusPin() {
		return cusPin;
	}

	public void setCusPin(String cusPin) {
		this.cusPin = cusPin;
	}

	public String getCusPan() {
		return cusPan;
	}

	public void setCusPan(String cusPan) {
		this.cusPan = cusPan;
	}

	public String getCusStateCode() {
		return cusStateCode;
	}

	public void setCusStateCode(String cusStateCode) {
		this.cusStateCode = cusStateCode;
	}

	public String getAgmtType() {
		return agmtType;
	}

	public void setAgmtType(String agmtType) {
		this.agmtType = agmtType;
	}

	public String getCusGst() {
		return cusGst;
	}

	public void setCusGst(String cusGst) {
		this.cusGst = cusGst;
	}

	public String getmCompanyId() {
		return mCompanyId;
	}

	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}

	public String getmCompServId() {
		return mCompServId;
	}

	public void setmCompServId(String mCompServId) {
		this.mCompServId = mCompServId;
	}

	public String getmCompServNo() {
		return mCompServNo;
	}

	public void setmCompServNo(String mCompServNo) {
		this.mCompServNo = mCompServNo;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getmSubstationId() {
		return mSubstationId;
	}

	public void setmSubstationId(String mSubstationId) {
		this.mSubstationId = mSubstationId;
	}

	public String getmSubstationCode() {
		return mSubstationCode;
	}

	public void setmSubstationCode(String mSubstationCode) {
		this.mSubstationCode = mSubstationCode;
	}

	public String getmSubstationName() {
		return mSubstationName;
	}

	public void setmSubstationName(String mSubstationName) {
		this.mSubstationName = mSubstationName;
	}

	public String getEeOperation() {
		return eeOperation;
	}

	public void setEeOperation(String eeOperation) {
		this.eeOperation = eeOperation;
	}

	public String getRefInvNo() {
		return refInvNo;
	}

	public void setRefInvNo(String refInvNo) {
		this.refInvNo = refInvNo;
	}

	public String getSupState() {
		return supState;
	}

	public void setSupState(String supState) {
		this.supState = supState;
	}

	public String getSupStateCode() {
		return supStateCode;
	}

	public void setSupStateCode(String supStateCode) {
		this.supStateCode = supStateCode;
	}

	public String getSupPan() {
		return supPan;
	}

	public void setSupPan(String supPan) {
		this.supPan = supPan;
	}

	public String getSupGst() {
		return supGst;
	}

	public void setSupGst(String supGst) {
		this.supGst = supGst;
	}

	public String getInvFromDt() {
		return invFromDt;
	}

	public void setInvFromDt(String invFromDt) {
		this.invFromDt = invFromDt;
	}

	public String getInvToDt() {
		return invToDt;
	}

	public void setInvToDt(String invToDt) {
		this.invToDt = invToDt;
	}

	public String getAgmtDt() {
		return agmtDt;
	}

	public void setAgmtDt(String agmtDt) {
		this.agmtDt = agmtDt;
	}

	public String getAmenDt() {
		return amenDt;
	}

	public void setAmenDt(String amenDt) {
		this.amenDt = amenDt;
	}

	public String getAgmtDuration() {
		return agmtDuration;
	}

	public void setAgmtDuration(String agmtDuration) {
		this.agmtDuration = agmtDuration;
	}

	public BigDecimal getSubTotalForGst() {
		return subTotalForGst;
	}

	public void setSubTotalForGst(BigDecimal subTotalForGst) {
		this.subTotalForGst = subTotalForGst;
	}

	public BigDecimal getSubTotalForOthers() {
		return subTotalForOthers;
	}

	public void setSubTotalForOthers(BigDecimal subTotalForOthers) {
		this.subTotalForOthers = subTotalForOthers;
	}

	public BigDecimal getIgstTaxPer() {
		return igstTaxPer;
	}

	public void setIgstTaxPer(BigDecimal igstTaxPer) {
		this.igstTaxPer = igstTaxPer;
	}

	public BigDecimal getCgstTaxPer() {
		return cgstTaxPer;
	}

	public void setCgstTaxPer(BigDecimal cgstTaxPer) {
		this.cgstTaxPer = cgstTaxPer;
	}

	public BigDecimal getSgstTaxPer() {
		return sgstTaxPer;
	}

	public void setSgstTaxPer(BigDecimal sgstTaxPer) {
		this.sgstTaxPer = sgstTaxPer;
	}

	public BigDecimal getSgstTaxAmt() {
		return sgstTaxAmt;
	}

	public void setSgstTaxAmt(BigDecimal sgstTaxAmt) {
		this.sgstTaxAmt = sgstTaxAmt;
	}

	public BigDecimal getIgstTaxAmt() {
		return igstTaxAmt;
	}

	public void setIgstTaxAmt(BigDecimal igstTaxAmt) {
		this.igstTaxAmt = igstTaxAmt;
	}

	public BigDecimal getCgstTaxAmt() {
		return cgstTaxAmt;
	}

	public void setCgstTaxAmt(BigDecimal cgstTaxAmt) {
		this.cgstTaxAmt = cgstTaxAmt;
	}

	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}

	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}

	public BigDecimal getTotalInvAmt() {
		return totalInvAmt;
	}

	public void setTotalInvAmt(BigDecimal totalInvAmt) {
		this.totalInvAmt = totalInvAmt;
	}

	public String getInvAmtInWords() {
		return invAmtInWords;
	}

	public void setInvAmtInWords(String invAmtInWords) {
		this.invAmtInWords = invAmtInWords;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankIfscCode() {
		return bankIfscCode;
	}

	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
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

	public String getPdfDocId() {
		return pdfDocId;
	}

	public void setPdfDocId(String pdfDocId) {
		this.pdfDocId = pdfDocId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getInvStatus() {
		return invStatus;
	}

	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
	}

	public String getInvCreatedBy() {
		return invCreatedBy;
	}

	public void setInvCreatedBy(String invCreatedBy) {
		this.invCreatedBy = invCreatedBy;
	}

	public String getInvCreatedDate() {
		return invCreatedDate;
	}

	public void setInvCreatedDate(String invCreatedDate) {
		this.invCreatedDate = invCreatedDate;
	}

	public String getInvForwardDate() {
		return invForwardDate;
	}

	public void setInvForwardDate(String invForwardDate) {
		this.invForwardDate = invForwardDate;
	}

	public String getInvForwardBy() {
		return invForwardBy;
	}

	public void setInvForwardBy(String invForwardBy) {
		this.invForwardBy = invForwardBy;
	}

	public String getInvApprovedDate() {
		return invApprovedDate;
	}

	public void setInvApprovedDate(String invApprovedDate) {
		this.invApprovedDate = invApprovedDate;
	}

	public String getInvApprovedBy() {
		return invApprovedBy;
	}

	public void setInvApprovedBy(String invApprovedBy) {
		this.invApprovedBy = invApprovedBy;
	}

	public String getZenPostedDate() {
		return zenPostedDate;
	}

	public void setZenPostedDate(String zenPostedDate) {
		this.zenPostedDate = zenPostedDate;
	}

	public String getTotalinwords() {
		return totalinwords;
	}

	public void setTotalinwords(String totalinwords) {
		this.totalinwords = totalinwords;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getQrCoderecivedDate() {
		return qrCoderecivedDate;
	}

	public void setQrCoderecivedDate(String qrCoderecivedDate) {
		this.qrCoderecivedDate = qrCoderecivedDate;
	}

	public String getViritualAccno() {
		return viritualAccno;
	}

	public void setViritualAccno(String viritualAccno) {
		this.viritualAccno = viritualAccno;
	}

	@Override
	public String toString() {
		return "TranscoInvoice [id=" + id + ", mOrgId=" + mOrgId + ", mOrgName=" + mOrgName + ", InvCode=" + InvCode
				+ ", invoiceType=" + invoiceType + ", commDt=" + commDt + ", invoiceDt=" + invoiceDt + ", lineYear="
				+ lineYear + ", lineMonth=" + lineMonth + ", cusName=" + cusName + ", cusEmail=" + cusEmail
				+ ", cusPhNo=" + cusPhNo + ", cusState=" + cusState + ", cusAddress=" + cusAddress + ", cusPin="
				+ cusPin + ", cusPan=" + cusPan + ", cusStateCode=" + cusStateCode + ", agmtType=" + agmtType
				+ ", cusGst=" + cusGst + ", mCompanyId=" + mCompanyId + ", mCompServId=" + mCompServId
				+ ", mCompServNo=" + mCompServNo + ", supName=" + supName + ", mSubstationId=" + mSubstationId
				+ ", mSubstationCode=" + mSubstationCode + ", mSubstationName=" + mSubstationName + ", eeOperation="
				+ eeOperation + ", refInvNo=" + refInvNo + ", supState=" + supState + ", supStateCode=" + supStateCode
				+ ", supPan=" + supPan + ", supGst=" + supGst + ", invFromDt=" + invFromDt + ", invToDt=" + invToDt
				+ ", agmtDt=" + agmtDt + ", amenDt=" + amenDt + ", agmtDuration=" + agmtDuration + ", subTotalForGst="
				+ subTotalForGst + ", subTotalForOthers=" + subTotalForOthers + ", igstTaxPer=" + igstTaxPer
				+ ", cgstTaxPer=" + cgstTaxPer + ", sgstTaxPer=" + sgstTaxPer + ", sgstTaxAmt=" + sgstTaxAmt
				+ ", igstTaxAmt=" + igstTaxAmt + ", cgstTaxAmt=" + cgstTaxAmt + ", totalTaxAmt=" + totalTaxAmt
				+ ", totalInvAmt=" + totalInvAmt + ", invAmtInWords=" + invAmtInWords + ", bankName=" + bankName
				+ ", bankBranch=" + bankBranch + ", bankIfscCode=" + bankIfscCode + ", bankAccNo=" + bankAccNo
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", pdfDocId=" + pdfDocId + ", docId=" + docId + ", invStatus="
				+ invStatus + ", invCreatedBy=" + invCreatedBy + ", invCreatedDate=" + invCreatedDate
				+ ", invForwardDate=" + invForwardDate + ", invForwardBy=" + invForwardBy + ", invApprovedDate="
				+ invApprovedDate + ", invApprovedBy=" + invApprovedBy + ", zenPostedDate=" + zenPostedDate
				+ ", totalinwords=" + totalinwords + ", qrCode=" + qrCode + ", qrCoderecivedDate=" + qrCoderecivedDate
				+ ", viritualAccno=" + viritualAccno + ", getId()=" + getId() + ", getmOrgId()=" + getmOrgId()
				+ ", getmOrgName()=" + getmOrgName() + ", getInvCode()=" + getInvCode() + ", getInvoiceType()="
				+ getInvoiceType() + ", getCommDt()=" + getCommDt() + ", getInvoiceDt()=" + getInvoiceDt()
				+ ", getLineYear()=" + getLineYear() + ", getLineMonth()=" + getLineMonth() + ", getCusName()="
				+ getCusName() + ", getCusEmail()=" + getCusEmail() + ", getCusPhNo()=" + getCusPhNo()
				+ ", getCusState()=" + getCusState() + ", getCusAddress()=" + getCusAddress() + ", getCusPin()="
				+ getCusPin() + ", getCusPan()=" + getCusPan() + ", getCusStateCode()=" + getCusStateCode()
				+ ", getAgmtType()=" + getAgmtType() + ", getCusGst()=" + getCusGst() + ", getmCompanyId()="
				+ getmCompanyId() + ", getmCompServId()=" + getmCompServId() + ", getmCompServNo()=" + getmCompServNo()
				+ ", getSupName()=" + getSupName() + ", getmSubstationId()=" + getmSubstationId()
				+ ", getmSubstationCode()=" + getmSubstationCode() + ", getmSubstationName()=" + getmSubstationName()
				+ ", getEeOperation()=" + getEeOperation() + ", getRefInvNo()=" + getRefInvNo() + ", getSupState()="
				+ getSupState() + ", getSupStateCode()=" + getSupStateCode() + ", getSupPan()=" + getSupPan()
				+ ", getSupGst()=" + getSupGst() + ", getInvFromDt()=" + getInvFromDt() + ", getInvToDt()="
				+ getInvToDt() + ", getAgmtDt()=" + getAgmtDt() + ", getAmenDt()=" + getAmenDt()
				+ ", getAgmtDuration()=" + getAgmtDuration() + ", getSubTotalForGst()=" + getSubTotalForGst()
				+ ", getSubTotalForOthers()=" + getSubTotalForOthers() + ", getIgstTaxPer()=" + getIgstTaxPer()
				+ ", getCgstTaxPer()=" + getCgstTaxPer() + ", getSgstTaxPer()=" + getSgstTaxPer() + ", getSgstTaxAmt()="
				+ getSgstTaxAmt() + ", getIgstTaxAmt()=" + getIgstTaxAmt() + ", getCgstTaxAmt()=" + getCgstTaxAmt()
				+ ", getTotalTaxAmt()=" + getTotalTaxAmt() + ", getTotalInvAmt()=" + getTotalInvAmt()
				+ ", getInvAmtInWords()=" + getInvAmtInWords() + ", getBankName()=" + getBankName()
				+ ", getBankBranch()=" + getBankBranch() + ", getBankIfscCode()=" + getBankIfscCode()
				+ ", getBankAccNo()=" + getBankAccNo() + ", getCreatedBy()=" + getCreatedBy() + ", getCreatedDate()="
				+ getCreatedDate() + ", getModifiedBy()=" + getModifiedBy() + ", getModifiedDate()=" + getModifiedDate()
				+ ", getPdfDocId()=" + getPdfDocId() + ", getDocId()=" + getDocId() + ", getInvStatus()="
				+ getInvStatus() + ", getInvCreatedBy()=" + getInvCreatedBy() + ", getInvCreatedDate()="
				+ getInvCreatedDate() + ", getInvForwardDate()=" + getInvForwardDate() + ", getInvForwardBy()="
				+ getInvForwardBy() + ", getInvApprovedDate()=" + getInvApprovedDate() + ", getInvApprovedBy()="
				+ getInvApprovedBy() + ", getZenPostedDate()=" + getZenPostedDate() + ", getTotalinwords()="
				+ getTotalinwords() + ", getQrCode()=" + getQrCode() + ", getQrCoderecivedDate()="
				+ getQrCoderecivedDate() + ", getViritualAccno()=" + getViritualAccno() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public TranscoInvoice(String id, String mOrgId, String mOrgName, String invCode, String invoiceType, String commDt,
			String invoiceDt, String lineYear, String lineMonth, String cusName, String cusEmail, String cusPhNo,
			String cusState, String cusAddress, String cusPin, String cusPan, String cusStateCode, String agmtType,
			String cusGst, String mCompanyId, String mCompServId, String mCompServNo, String supName,
			String mSubstationId, String mSubstationCode, String mSubstationName, String eeOperation, String refInvNo,
			String supState, String supStateCode, String supPan, String supGst, String invFromDt, String invToDt,
			String agmtDt, String amenDt, String agmtDuration, BigDecimal subTotalForGst, BigDecimal subTotalForOthers,
			BigDecimal igstTaxPer, BigDecimal cgstTaxPer, BigDecimal sgstTaxPer, BigDecimal sgstTaxAmt,
			BigDecimal igstTaxAmt, BigDecimal cgstTaxAmt, BigDecimal totalTaxAmt, BigDecimal totalInvAmt,
			String invAmtInWords, String bankName, String bankBranch, String bankIfscCode, String bankAccNo,
			String createdBy, String createdDate, String modifiedBy, String modifiedDate, String pdfDocId, String docId,
			String invStatus, String invCreatedBy, String invCreatedDate, String invForwardDate, String invForwardBy,
			String invApprovedDate, String invApprovedBy, String zenPostedDate, String totalinwords, String qrCode,
			String qrCoderecivedDate, String viritualAccno) {
		super();
		this.id = id;
		this.mOrgId = mOrgId;
		this.mOrgName = mOrgName;
		InvCode = invCode;
		this.invoiceType = invoiceType;
		this.commDt = commDt;
		this.invoiceDt = invoiceDt;
		this.lineYear = lineYear;
		this.lineMonth = lineMonth;
		this.cusName = cusName;
		this.cusEmail = cusEmail;
		this.cusPhNo = cusPhNo;
		this.cusState = cusState;
		this.cusAddress = cusAddress;
		this.cusPin = cusPin;
		this.cusPan = cusPan;
		this.cusStateCode = cusStateCode;
		this.agmtType = agmtType;
		this.cusGst = cusGst;
		this.mCompanyId = mCompanyId;
		this.mCompServId = mCompServId;
		this.mCompServNo = mCompServNo;
		this.supName = supName;
		this.mSubstationId = mSubstationId;
		this.mSubstationCode = mSubstationCode;
		this.mSubstationName = mSubstationName;
		this.eeOperation = eeOperation;
		this.refInvNo = refInvNo;
		this.supState = supState;
		this.supStateCode = supStateCode;
		this.supPan = supPan;
		this.supGst = supGst;
		this.invFromDt = invFromDt;
		this.invToDt = invToDt;
		this.agmtDt = agmtDt;
		this.amenDt = amenDt;
		this.agmtDuration = agmtDuration;
		this.subTotalForGst = subTotalForGst;
		this.subTotalForOthers = subTotalForOthers;
		this.igstTaxPer = igstTaxPer;
		this.cgstTaxPer = cgstTaxPer;
		this.sgstTaxPer = sgstTaxPer;
		this.sgstTaxAmt = sgstTaxAmt;
		this.igstTaxAmt = igstTaxAmt;
		this.cgstTaxAmt = cgstTaxAmt;
		this.totalTaxAmt = totalTaxAmt;
		this.totalInvAmt = totalInvAmt;
		this.invAmtInWords = invAmtInWords;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.bankIfscCode = bankIfscCode;
		this.bankAccNo = bankAccNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.pdfDocId = pdfDocId;
		this.docId = docId;
		this.invStatus = invStatus;
		this.invCreatedBy = invCreatedBy;
		this.invCreatedDate = invCreatedDate;
		this.invForwardDate = invForwardDate;
		this.invForwardBy = invForwardBy;
		this.invApprovedDate = invApprovedDate;
		this.invApprovedBy = invApprovedBy;
		this.zenPostedDate = zenPostedDate;
		this.totalinwords = totalinwords;
		this.qrCode = qrCode;
		this.qrCoderecivedDate = qrCoderecivedDate;
		this.viritualAccno = viritualAccno;
	}

	public TranscoInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getReversecharge() {
		return reversecharge;
	}

	public void setReversecharge(String reversecharge) {
		this.reversecharge = reversecharge;
	}

	
	
	
	
	
	
	
	
			}