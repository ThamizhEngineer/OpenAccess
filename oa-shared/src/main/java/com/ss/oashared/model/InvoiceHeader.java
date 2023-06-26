package com.ss.oashared.model;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "T_INVOICE_HDR")
public class InvoiceHeader {
	@Id
	@Column(name = "ID", columnDefinition = "VARCHAR2")
	private String id;

	@Column(name = "M_ORG_ID")
	private String mOrgId;
	@Column(name = "M_ORG_NAME")
	private String mOrgName;
	@Column(name = "INVCODE")
	private String InvCode;
	@Column(name = "INVOICETYPE")
	private String invoiceType;
	@Column(name = "COMMDT")
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String commDt;
	@Column(name = "INVOICEDT")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime invoiceDt;
	@Column(name = "LINE_YEAR")
	private String lineYear;
	@Column(name = "LINE_MONTH")
	private String lineMonth;
	@Column(name = "CUSNAME")
	private String cusName;
	@Column(name = "CUSEMAIL")
	private String cusEmail;
	@Column(name = "CUSPHNO")
	private String cusPhNo;
	@Column(name = "CUSSTATE")
	private String cusState;
	@Column(name = "CUSADDRESS")
	private String cusAddress;
	@Column(name = "CUSPIN")
	private String cusPin;
	@Column(name = "CUSPAN")
	private String cusPan;
	@Column(name = "CUSSTATECODE")
	private String cusStateCode;
	@Column(name = "AGMTTYPE")
	private String agmtType;
	@Column(name = "CUSGST")
	private String cusGst;

	@Column(name = "M_COMPANY_ID")
	private String mCompanyId;
	@Column(name = "M_COMP_SERV_ID")
	private String mCompServId;
	@Column(name = "M_COMP_SERV_NO")
	private String mCompServNo;
	@Column(name = "SUPNAME")
	private String supName;
	@Column(name = "M_SUBSTATION_ID")
	private String mSubstationId;
	@Column(name = "M_SUBSTATION_CODE")
	private String mSubstationCode;
	@Column(name = "M_SUBSTATION_NAME")
	private String mSubstationName;

	@Column(name = "EEOPERATION")
	private String eeOperation;
	@Column(name = "REFINVNO")
	private String refInvNo;
	@Column(name = "SUPSTATE")
	private String supState;
	@Column(name = "SUPSTATECODE")
	private String supStateCode;
	@Column(name = "SUPPAN")
	private String supPan;
	@Column(name = "SUPGST")
	private String supGst;
	@Column(name = "INVFROMDT")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invFromDt;
	@Column(name = "INVTODT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime invToDt;
	@Column(name = "AGMTDT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime agmtDt;
	@Column(name = "AMENDT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime amenDt;
	@Column(name = "AGMTDURATION")
	private String agmtDuration;
	@Column(name = "SUBTOTALFORGST", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal subTotalForGst;
	@Column(name = "SUBTOTALFOROTHERS", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal subTotalForOthers;
	@Column(name = "IGSTTAXPER", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal igstTaxPer;
	@Column(name = "CGSTTAXPER", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal cgstTaxPer;
	@Column(name = "SGSTTAXPER", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal sgstTaxPer;
	@Column(name = "SGSTTAXAMT", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal sgstTaxAmt;
	@Column(name = "IGSTTAXAMT", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal igstTaxAmt;
	@Column(name = "CGSTTAXAMT", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal cgstTaxAmt;

	@Column(name = "TOTALTAXAMT", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal totalTaxAmt;

//@Formula("sgstTaxamt+igstTaxamt+cgstTaxamt")
	@Column(name = "TOTALINVAMT")
	private BigDecimal totalInvAmt;

	@Column(name = "SUBTOTALOTHERS", columnDefinition = "decimal", precision = 20, scale = 4)
	private BigDecimal subtotalothers;

	@Column(name = "INVAMTINWORDS")
	private String invAmtInWords;

	@Column(name = "BANKNAME")
	private String bankName;
	@Column(name = "BANKBRANCH")
	private String bankBranch;
	@Column(name = "BANKIFSCCODE")
	private String bankIfscCode;
	@Column(name = "BANKACCNO")
	private String bankAccNo;
	@Column(name = "CREATEDBY")
	private String createdBy;
	@Column(name = "CREATEDDATE")
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@Column(name = "MODIFIEDBY")
	private String modifiedBy;
	@Column(name = "MODIFIEDDATE")
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	@Transient
	private List<InvoiceLine> invoiceLines;
	@Column(name = "PDFDOCID")
	private String pdfDocId;
	@Column(name = "DOCID")
	private String docId;
	@Column(name = "INVSTATUS")
	private String invStatus;
	@Column(name = "INVCREATEDBY")
	private String invCreatedBy;
	@Column(name = "INVCREATEDDATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invCreatedDate;
	@Column(name = "INVFORWARDDATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invForwardDate;
	@Column(name = "INVFORWARDBY")
	private String invForwardBy;
	@Column(name = "INVAPPROVEDDATE")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime invApprovedDate;
	@Column(name = "INVAPPROVEDBY")
	private String invApprovedBy;
	@Column(name = "ZENPOSTEDDATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime zenPostedDate;
	@Column(name = "ISEMAILSENT")
	private String isEmailSent;
	@Column(name = "EMAILSENTDT")
	private LocalDateTime emailSentDt;
	@Column(name = "EMAILSENTBY")
	private String emailSentBy;
	@Column(name = "QRCODE")
	private String qrCode;
	@Column(name = "QRCODERECEIVEDDT")
	private LocalDateTime qrCodeReceivedDt;
	@Column(name = "TRANSACTIONTYPE")
	private String transactiontype;
	@Column(name = "DOCUMENTTYPE")
	private String documenttype;
	@Column(name = "HSNSAC")
	private String hsnsac;
	@Column(name = "DESCRIPTI0N")
	private String description;
	@Column(name = "UQC")
	private String uqc;
	@Column(name = "QUANTITY")
	private String quantity;
	@Column(name = "CESS")
	private String cess;
	@Column(name = "REVERSECHRG")
	private Character reversecharge;
	@Column(name = "SUPLRADDR1")
	private String supladdr1;
	@Column(name = "SUPLIERLOCALITY")
	private String suplierlocality;
	@Column(name = "SUPLRPINCODE")
	private String suplierpincode;
	@Column(name = "SUPLRPHONE")
	private String suplierphone;
	@Column(name = "SUPLREMAIL")
	private String suplieremail;
	@Column(name = "ZENIRN")
	private String irn;
	@Column(name = "ZENACKNO")
	private String ackno;
	@Column(name = "STATUSCONS")
	private String statuscons;
	@Column(name = "VIRTUALACCOUNTNO")
	private String viritualAccno;
	@Column(name = "PAYMENTRECIV")
	private Character paymentreciv;
	@Column(name = "PAYMENTRECIVDATE")

	private String paymentDate;
	@Column(name = "ZENREMARKS")
	private String zenremarks;
	@Column(name = "FUEL_TYPE")
	private String fueltype;
	@Column(name = "CAPACITY")
	private String capacity;
	@Column(name = "FLOW_TYPE")
	private String flowtype;
	@Transient
	private String totalinwords;
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
	public LocalDateTime getInvoiceDt() {
		return invoiceDt;
	}
	public void setInvoiceDt(LocalDateTime invoiceDt) {
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
	public LocalDateTime getInvFromDt() {
		return invFromDt;
	}
	public void setInvFromDt(LocalDateTime invFromDt) {
		this.invFromDt = invFromDt;
	}
	public LocalDateTime getInvToDt() {
		return invToDt;
	}
	public void setInvToDt(LocalDateTime invToDt) {
		this.invToDt = invToDt;
	}
	public LocalDateTime getAgmtDt() {
		return agmtDt;
	}
	public void setAgmtDt(LocalDateTime agmtDt) {
		this.agmtDt = agmtDt;
	}
	public LocalDateTime getAmenDt() {
		return amenDt;
	}
	public void setAmenDt(LocalDateTime amenDt) {
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
	public BigDecimal getSubtotalothers() {
		return subtotalothers;
	}
	public void setSubtotalothers(BigDecimal subtotalothers) {
		this.subtotalothers = subtotalothers;
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
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}
	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
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
	public LocalDateTime getInvCreatedDate() {
		return invCreatedDate;
	}
	public void setInvCreatedDate(LocalDateTime invCreatedDate) {
		this.invCreatedDate = invCreatedDate;
	}
	public LocalDateTime getInvForwardDate() {
		return invForwardDate;
	}
	public void setInvForwardDate(LocalDateTime invForwardDate) {
		this.invForwardDate = invForwardDate;
	}
	public String getInvForwardBy() {
		return invForwardBy;
	}
	public void setInvForwardBy(String invForwardBy) {
		this.invForwardBy = invForwardBy;
	}
	public LocalDateTime getInvApprovedDate() {
		return invApprovedDate;
	}
	public void setInvApprovedDate(LocalDateTime invApprovedDate) {
		this.invApprovedDate = invApprovedDate;
	}
	public String getInvApprovedBy() {
		return invApprovedBy;
	}
	public void setInvApprovedBy(String invApprovedBy) {
		this.invApprovedBy = invApprovedBy;
	}
	public LocalDateTime getZenPostedDate() {
		return zenPostedDate;
	}
	public void setZenPostedDate(LocalDateTime zenPostedDate) {
		this.zenPostedDate = zenPostedDate;
	}
	public String getIsEmailSent() {
		return isEmailSent;
	}
	public void setIsEmailSent(String isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
	public LocalDateTime getEmailSentDt() {
		return emailSentDt;
	}
	public void setEmailSentDt(LocalDateTime emailSentDt) {
		this.emailSentDt = emailSentDt;
	}
	public String getEmailSentBy() {
		return emailSentBy;
	}
	public void setEmailSentBy(String emailSentBy) {
		this.emailSentBy = emailSentBy;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public LocalDateTime getQrCodeReceivedDt() {
		return qrCodeReceivedDt;
	}
	public void setQrCodeReceivedDt(LocalDateTime qrCodeReceivedDt) {
		this.qrCodeReceivedDt = qrCodeReceivedDt;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public String getDocumenttype() {
		return documenttype;
	}
	public void setDocumenttype(String documenttype) {
		this.documenttype = documenttype;
	}
	public String getHsnsac() {
		return hsnsac;
	}
	public void setHsnsac(String hsnsac) {
		this.hsnsac = hsnsac;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUqc() {
		return uqc;
	}
	public void setUqc(String uqc) {
		this.uqc = uqc;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCess() {
		return cess;
	}
	public void setCess(String cess) {
		this.cess = cess;
	}
	public Character getReversecharge() {
		return reversecharge;
	}
	public void setReversecharge(Character reversecharge) {
		this.reversecharge = reversecharge;
	}
	public String getSupladdr1() {
		return supladdr1;
	}
	public void setSupladdr1(String supladdr1) {
		this.supladdr1 = supladdr1;
	}
	public String getSuplierlocality() {
		return suplierlocality;
	}
	public void setSuplierlocality(String suplierlocality) {
		this.suplierlocality = suplierlocality;
	}
	public String getSuplierpincode() {
		return suplierpincode;
	}
	public void setSuplierpincode(String suplierpincode) {
		this.suplierpincode = suplierpincode;
	}
	public String getSuplierphone() {
		return suplierphone;
	}
	public void setSuplierphone(String suplierphone) {
		this.suplierphone = suplierphone;
	}
	public String getSuplieremail() {
		return suplieremail;
	}
	public void setSuplieremail(String suplieremail) {
		this.suplieremail = suplieremail;
	}
	public String getIrn() {
		return irn;
	}
	public void setIrn(String irn) {
		this.irn = irn;
	}
	public String getAckno() {
		return ackno;
	}
	public void setAckno(String ackno) {
		this.ackno = ackno;
	}
	public String getStatuscons() {
		return statuscons;
	}
	public void setStatuscons(String statuscons) {
		this.statuscons = statuscons;
	}
	public String getViritualAccno() {
		return viritualAccno;
	}
	public void setViritualAccno(String viritualAccno) {
		this.viritualAccno = viritualAccno;
	}
	public Character getPaymentreciv() {
		return paymentreciv;
	}
	public void setPaymentreciv(Character paymentreciv) {
		this.paymentreciv = paymentreciv;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getZenremarks() {
		return zenremarks;
	}
	public void setZenremarks(String zenremarks) {
		this.zenremarks = zenremarks;
	}
	public String getFueltype() {
		return fueltype;
	}
	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getFlowtype() {
		return flowtype;
	}
	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}
	public String getTotalinwords() {
		return totalinwords;
	}
	public void setTotalinwords(String totalinwords) {
		this.totalinwords = totalinwords;
	}
	@Override
	public String toString() {
		return "InvoiceHeader [id=" + id + ", mOrgId=" + mOrgId + ", mOrgName=" + mOrgName + ", InvCode=" + InvCode
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
				+ ", totalInvAmt=" + totalInvAmt + ", subtotalothers=" + subtotalothers + ", invAmtInWords="
				+ invAmtInWords + ", bankName=" + bankName + ", bankBranch=" + bankBranch + ", bankIfscCode="
				+ bankIfscCode + ", bankAccNo=" + bankAccNo + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", invoiceLines="
				+ invoiceLines + ", pdfDocId=" + pdfDocId + ", docId=" + docId + ", invStatus=" + invStatus
				+ ", invCreatedBy=" + invCreatedBy + ", invCreatedDate=" + invCreatedDate + ", invForwardDate="
				+ invForwardDate + ", invForwardBy=" + invForwardBy + ", invApprovedDate=" + invApprovedDate
				+ ", invApprovedBy=" + invApprovedBy + ", zenPostedDate=" + zenPostedDate + ", isEmailSent="
				+ isEmailSent + ", emailSentDt=" + emailSentDt + ", emailSentBy=" + emailSentBy + ", qrCode=" + qrCode
				+ ", qrCodeReceivedDt=" + qrCodeReceivedDt + ", transactiontype=" + transactiontype + ", documenttype="
				+ documenttype + ", hsnsac=" + hsnsac + ", description=" + description + ", uqc=" + uqc + ", quantity="
				+ quantity + ", cess=" + cess + ", reversecharge=" + reversecharge + ", supladdr1=" + supladdr1
				+ ", suplierlocality=" + suplierlocality + ", suplierpincode=" + suplierpincode + ", suplierphone="
				+ suplierphone + ", suplieremail=" + suplieremail + ", irn=" + irn + ", ackno=" + ackno
				+ ", statuscons=" + statuscons + ", viritualAccno=" + viritualAccno + ", paymentreciv=" + paymentreciv
				+ ", paymentDate=" + paymentDate + ", zenremarks=" + zenremarks + ", fueltype=" + fueltype
				+ ", capacity=" + capacity + ", flowtype=" + flowtype + ", totalinwords=" + totalinwords + ", getId()="
				+ getId() + ", getmOrgId()=" + getmOrgId() + ", getmOrgName()=" + getmOrgName() + ", getInvCode()="
				+ getInvCode() + ", getInvoiceType()=" + getInvoiceType() + ", getCommDt()=" + getCommDt()
				+ ", getInvoiceDt()=" + getInvoiceDt() + ", getLineYear()=" + getLineYear() + ", getLineMonth()="
				+ getLineMonth() + ", getCusName()=" + getCusName() + ", getCusEmail()=" + getCusEmail()
				+ ", getCusPhNo()=" + getCusPhNo() + ", getCusState()=" + getCusState() + ", getCusAddress()="
				+ getCusAddress() + ", getCusPin()=" + getCusPin() + ", getCusPan()=" + getCusPan()
				+ ", getCusStateCode()=" + getCusStateCode() + ", getAgmtType()=" + getAgmtType() + ", getCusGst()="
				+ getCusGst() + ", getmCompanyId()=" + getmCompanyId() + ", getmCompServId()=" + getmCompServId()
				+ ", getmCompServNo()=" + getmCompServNo() + ", getSupName()=" + getSupName() + ", getmSubstationId()="
				+ getmSubstationId() + ", getmSubstationCode()=" + getmSubstationCode() + ", getmSubstationName()="
				+ getmSubstationName() + ", getEeOperation()=" + getEeOperation() + ", getRefInvNo()=" + getRefInvNo()
				+ ", getSupState()=" + getSupState() + ", getSupStateCode()=" + getSupStateCode() + ", getSupPan()="
				+ getSupPan() + ", getSupGst()=" + getSupGst() + ", getInvFromDt()=" + getInvFromDt()
				+ ", getInvToDt()=" + getInvToDt() + ", getAgmtDt()=" + getAgmtDt() + ", getAmenDt()=" + getAmenDt()
				+ ", getAgmtDuration()=" + getAgmtDuration() + ", getSubTotalForGst()=" + getSubTotalForGst()
				+ ", getSubTotalForOthers()=" + getSubTotalForOthers() + ", getIgstTaxPer()=" + getIgstTaxPer()
				+ ", getCgstTaxPer()=" + getCgstTaxPer() + ", getSgstTaxPer()=" + getSgstTaxPer() + ", getSgstTaxAmt()="
				+ getSgstTaxAmt() + ", getIgstTaxAmt()=" + getIgstTaxAmt() + ", getCgstTaxAmt()=" + getCgstTaxAmt()
				+ ", getTotalTaxAmt()=" + getTotalTaxAmt() + ", getTotalInvAmt()=" + getTotalInvAmt()
				+ ", getSubtotalothers()=" + getSubtotalothers() + ", getInvAmtInWords()=" + getInvAmtInWords()
				+ ", getBankName()=" + getBankName() + ", getBankBranch()=" + getBankBranch() + ", getBankIfscCode()="
				+ getBankIfscCode() + ", getBankAccNo()=" + getBankAccNo() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getModifiedBy()=" + getModifiedBy()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getInvoiceLines()=" + getInvoiceLines()
				+ ", getPdfDocId()=" + getPdfDocId() + ", getDocId()=" + getDocId() + ", getInvStatus()="
				+ getInvStatus() + ", getInvCreatedBy()=" + getInvCreatedBy() + ", getInvCreatedDate()="
				+ getInvCreatedDate() + ", getInvForwardDate()=" + getInvForwardDate() + ", getInvForwardBy()="
				+ getInvForwardBy() + ", getInvApprovedDate()=" + getInvApprovedDate() + ", getInvApprovedBy()="
				+ getInvApprovedBy() + ", getZenPostedDate()=" + getZenPostedDate() + ", getIsEmailSent()="
				+ getIsEmailSent() + ", getEmailSentDt()=" + getEmailSentDt() + ", getEmailSentBy()=" + getEmailSentBy()
				+ ", getQrCode()=" + getQrCode() + ", getQrCodeReceivedDt()=" + getQrCodeReceivedDt()
				+ ", getTransactiontype()=" + getTransactiontype() + ", getDocumenttype()=" + getDocumenttype()
				+ ", getHsnsac()=" + getHsnsac() + ", getDescription()=" + getDescription() + ", getUqc()=" + getUqc()
				+ ", getQuantity()=" + getQuantity() + ", getCess()=" + getCess() + ", getReversecharge()="
				+ getReversecharge() + ", getSupladdr1()=" + getSupladdr1() + ", getSuplierlocality()="
				+ getSuplierlocality() + ", getSuplierpincode()=" + getSuplierpincode() + ", getSuplierphone()="
				+ getSuplierphone() + ", getSuplieremail()=" + getSuplieremail() + ", getIrn()=" + getIrn()
				+ ", getAckno()=" + getAckno() + ", getStatuscons()=" + getStatuscons() + ", getViritualAccno()="
				+ getViritualAccno() + ", getPaymentreciv()=" + getPaymentreciv() + ", getPaymentDate()="
				+ getPaymentDate() + ", getZenremarks()=" + getZenremarks() + ", getFueltype()=" + getFueltype()
				+ ", getCapacity()=" + getCapacity() + ", getFlowtype()=" + getFlowtype() + ", getTotalinwords()="
				+ getTotalinwords() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	public InvoiceHeader(String id, String mOrgId, String mOrgName, String invCode, String invoiceType, String commDt,
			LocalDateTime invoiceDt, String lineYear, String lineMonth, String cusName, String cusEmail, String cusPhNo,
			String cusState, String cusAddress, String cusPin, String cusPan, String cusStateCode, String agmtType,
			String cusGst, String mCompanyId, String mCompServId, String mCompServNo, String supName,
			String mSubstationId, String mSubstationCode, String mSubstationName, String eeOperation, String refInvNo,
			String supState, String supStateCode, String supPan, String supGst, LocalDateTime invFromDt,
			LocalDateTime invToDt, LocalDateTime agmtDt, LocalDateTime amenDt, String agmtDuration,
			BigDecimal subTotalForGst, BigDecimal subTotalForOthers, BigDecimal igstTaxPer, BigDecimal cgstTaxPer,
			BigDecimal sgstTaxPer, BigDecimal sgstTaxAmt, BigDecimal igstTaxAmt, BigDecimal cgstTaxAmt,
			BigDecimal totalTaxAmt, BigDecimal totalInvAmt, BigDecimal subtotalothers, String invAmtInWords,
			String bankName, String bankBranch, String bankIfscCode, String bankAccNo, String createdBy,
			LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate, List<InvoiceLine> invoiceLines,
			String pdfDocId, String docId, String invStatus, String invCreatedBy, LocalDateTime invCreatedDate,
			LocalDateTime invForwardDate, String invForwardBy, LocalDateTime invApprovedDate, String invApprovedBy,
			LocalDateTime zenPostedDate, String isEmailSent, LocalDateTime emailSentDt, String emailSentBy,
			String qrCode, LocalDateTime qrCodeReceivedDt, String transactiontype, String documenttype, String hsnsac,
			String description, String uqc, String quantity, String cess, Character reversecharge, String supladdr1,
			String suplierlocality, String suplierpincode, String suplierphone, String suplieremail, String irn,
			String ackno, String statuscons, String viritualAccno, Character paymentreciv, String paymentDate,
			String zenremarks, String fueltype, String capacity, String flowtype, String totalinwords) {
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
		this.subtotalothers = subtotalothers;
		this.invAmtInWords = invAmtInWords;
		this.bankName = bankName;
		this.bankBranch = bankBranch;
		this.bankIfscCode = bankIfscCode;
		this.bankAccNo = bankAccNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.invoiceLines = invoiceLines;
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
		this.isEmailSent = isEmailSent;
		this.emailSentDt = emailSentDt;
		this.emailSentBy = emailSentBy;
		this.qrCode = qrCode;
		this.qrCodeReceivedDt = qrCodeReceivedDt;
		this.transactiontype = transactiontype;
		this.documenttype = documenttype;
		this.hsnsac = hsnsac;
		this.description = description;
		this.uqc = uqc;
		this.quantity = quantity;
		this.cess = cess;
		this.reversecharge = reversecharge;
		this.supladdr1 = supladdr1;
		this.suplierlocality = suplierlocality;
		this.suplierpincode = suplierpincode;
		this.suplierphone = suplierphone;
		this.suplieremail = suplieremail;
		this.irn = irn;
		this.ackno = ackno;
		this.statuscons = statuscons;
		this.viritualAccno = viritualAccno;
		this.paymentreciv = paymentreciv;
		this.paymentDate = paymentDate;
		this.zenremarks = zenremarks;
		this.fueltype = fueltype;
		this.capacity = capacity;
		this.flowtype = flowtype;
		this.totalinwords = totalinwords;
	}
	public InvoiceHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	

	
}