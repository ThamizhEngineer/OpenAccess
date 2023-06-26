package com.ss.oashared.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
@Service
@Table(name="OA_EINVDATA")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class ZenInvoice {
	@Id
	
	@Column(name="ID")
	private String id;
	@Column(name="SERVICENO")
	private String serviceNo;
	@Column(name="BILL_MNTH")
	private String billMonth;
	@Column(name="BILL_YR")
	private String billYear;
	@Column(name="TAXPAYERGSTIN")
	private String taxPayerGstIn;
	@Column(name="CUSTOMERGSTIN")
	private String customerGstIn;
	@Column(name="DOCUMENTNO")
	private String documentNo;
	@Column(name="DOCUMENTDATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private String documentDate;
	@Column(name="DOCUMENTTYPE")
	private String documentType;
	@Column(name="TRANSACTIONTYPE")
	private String transactionType;
	@Column(name="HSNSAC")
	private String hsnSac;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="TAXABLEVALUE")
	private String taxableValue;
	@Column(name="GSTTAXRATE")
	private String gstTaxRate;
	@Column(name="UQC")
	private String uqc;
	@Column(name="QUANTITY")
	private String quantity;
	@Column(name="IGSTAMOUNT")
	private BigDecimal igstAmount;
	@Column(name="SGSTAMOUNT")
	private BigDecimal sgstAmount;
	@Column(name="CGSTAMOUNT")
	private BigDecimal cgstAmount;
	@Column(name="CESS")
	private String cess;
	@Column(name="PLACEOFSUPPLY")
	private String placeOfsupply;
	@Column(name="REVERSECHRG")
	private Character reverseChrg;
	@Column(name="SUPLRLEGALNAME")
	private String suplrLegalName;
	@Column(name="SUPLRADDR1")
	private String suplrAddr1;
	@Column(name="SUPLRADDR2")
	private String suplrAddr2;
	@Column(name="SUPLIERLOCALITY")
	private String suplrLocality;
	@Column(name="SUPLRPINCODE")	
	private String suplrPinCode;
	@Column(name="SUPLRPHONE")
	private String suplrPhone;
	@Column(name="SUPLREMAIL")
	private String suplrEmail;
	@Column(name="RECIPIENTLEGALNAME")
	private String recipientLegalName;
	@Column(name="RECIPIENTADDR1")
	private String recipientAddr1;
	@Column(name="RECIPIENTADDR2")
	private String recipientAddr2;
	@Column(name="RECIPIENTLOCALITY")
	private String recipientLocality;
	@Column(name="RECIPIENTPIN")
	private String recipientPin;
	@Column(name="RECIPIENTPHONE")
	private String recipientPhone;
	@Column(name="RECIPIENTEMAIL")
	private String recipientEmail;
	@Column(name="IRN")
	private String irn;	
	@Column(name="SQRCODE")
	private String sqrCode;
	@Column(name="STATUSEINV")
	private String statusEinv;
	@Column(name="ACKNO")
	private String ackNo;
	@Column(name="ACKDATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime ackDate;
	@Column(name="DEPARTMENT")
	private String department;
	@Column(name="OTHERCHRG")
	private String otherChrg;
	@Column(name="TOTALAMT")
	private String totalAmount;
	@Column(name="STATUS_CONS")
	private String statusCons;
	
	
	public ZenInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ZenInvoice(String id, String serviceNo, String billMonth, String billYear, String taxPayerGstIn,
			String customerGstIn, String documentNo, String documentDate, String documentType,
			String transactionType, String hsnSac, String description, String taxableValue, String gstTaxRate,
			String uqc, String quantity, BigDecimal igstAmount, BigDecimal sgstAmount, BigDecimal cgstAmount,
			String cess, String placeOfsupply, Character reverseChrg, String suplrLegalName, String suplrAddr1,
			String suplrAddr2, String suplrLocality, String suplrPinCode, String suplrPhone, String suplrEmail,
			String recipientLegalName, String recipientAddr1, String recipientAddr2, String recipientLocality,
			String recipientPin, String recipientPhone, String recipientEmail, String irn, String sqrCode,
			String statusEinv, String ackNo, LocalDateTime ackDate, String department, String otherChrg,
			String totalAmount, String statusCons) {
		super();
		this.id = id;
		this.serviceNo = serviceNo;
		this.billMonth = billMonth;
		this.billYear = billYear;
		this.taxPayerGstIn = taxPayerGstIn;
		this.customerGstIn = customerGstIn;
		this.documentNo = documentNo;
		this.documentDate = documentDate;
		this.documentType = documentType;
		this.transactionType = transactionType;
		this.hsnSac = hsnSac;
		this.description = description;
		this.taxableValue = taxableValue;
		this.gstTaxRate = gstTaxRate;
		this.uqc = uqc;
		this.quantity = quantity;
		this.igstAmount = igstAmount;
		this.sgstAmount = sgstAmount;
		this.cgstAmount = cgstAmount;
		this.cess = cess;
		this.placeOfsupply = placeOfsupply;
		this.reverseChrg = reverseChrg;
		this.suplrLegalName = suplrLegalName;
		this.suplrAddr1 = suplrAddr1;
		this.suplrAddr2 = suplrAddr2;
		this.suplrLocality = suplrLocality;
		this.suplrPinCode = suplrPinCode;
		this.suplrPhone = suplrPhone;
		this.suplrEmail = suplrEmail;
		this.recipientLegalName = recipientLegalName;
		this.recipientAddr1 = recipientAddr1;
		this.recipientAddr2 = recipientAddr2;
		this.recipientLocality = recipientLocality;
		this.recipientPin = recipientPin;
		this.recipientPhone = recipientPhone;
		this.recipientEmail = recipientEmail;
		this.irn = irn;
		this.sqrCode = sqrCode;
		this.statusEinv = statusEinv;
		this.ackNo = ackNo;
		this.ackDate = ackDate;
		this.department = department;
		this.otherChrg = otherChrg;
		this.totalAmount = totalAmount;
		this.statusCons = statusCons;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getServiceNo() {
		return serviceNo;
	}


	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}


	public String getBillMonth() {
		return billMonth;
	}


	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}


	public String getBillYear() {
		return billYear;
	}


	public void setBillYear(String billYear) {
		this.billYear = billYear;
	}


	public String getTaxPayerGstIn() {
		return taxPayerGstIn;
	}


	public void setTaxPayerGstIn(String taxPayerGstIn) {
		this.taxPayerGstIn = taxPayerGstIn;
	}


	public String getCustomerGstIn() {
		return customerGstIn;
	}


	public void setCustomerGstIn(String customerGstIn) {
		this.customerGstIn = customerGstIn;
	}


	public String getDocumentNo() {
		return documentNo;
	}


	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}


	public String getDocumentDate() {
		return documentDate;
	}


	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}


	public String getDocumentType() {
		return documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public String getHsnSac() {
		return hsnSac;
	}


	public void setHsnSac(String hsnSac) {
		this.hsnSac = hsnSac;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getTaxableValue() {
		return taxableValue;
	}


	public void setTaxableValue(String taxableValue) {
		this.taxableValue = taxableValue;
	}


	public String getGstTaxRate() {
		return gstTaxRate;
	}


	public void setGstTaxRate(String gstTaxRate) {
		this.gstTaxRate = gstTaxRate;
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


	public BigDecimal getIgstAmount() {
		return igstAmount;
	}


	public void setIgstAmount(BigDecimal igstAmount) {
		this.igstAmount = igstAmount;
	}


	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}


	public void setSgstAmount(BigDecimal sgstAmount) {
		this.sgstAmount = sgstAmount;
	}


	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}


	public void setCgstAmount(BigDecimal cgstAmount) {
		this.cgstAmount = cgstAmount;
	}


	public String getCess() {
		return cess;
	}


	public void setCess(String cess) {
		this.cess = cess;
	}


	public String getPlaceOfsupply() {
		return placeOfsupply;
	}


	public void setPlaceOfsupply(String placeOfsupply) {
		this.placeOfsupply = placeOfsupply;
	}


	public Character getReverseChrg() {
		return reverseChrg;
	}


	public void setReverseChrg(Character reverseChrg) {
		this.reverseChrg = reverseChrg;
	}


	public String getSuplrLegalName() {
		return suplrLegalName;
	}


	public void setSuplrLegalName(String suplrLegalName) {
		this.suplrLegalName = suplrLegalName;
	}


	public String getSuplrAddr1() {
		return suplrAddr1;
	}


	public void setSuplrAddr1(String suplrAddr1) {
		this.suplrAddr1 = suplrAddr1;
	}


	public String getSuplrAddr2() {
		return suplrAddr2;
	}


	public void setSuplrAddr2(String suplrAddr2) {
		this.suplrAddr2 = suplrAddr2;
	}


	public String getSuplrLocality() {
		return suplrLocality;
	}


	public void setSuplrLocality(String suplrLocality) {
		this.suplrLocality = suplrLocality;
	}


	public String getSuplrPinCode() {
		return suplrPinCode;
	}


	public void setSuplrPinCode(String suplrPinCode) {
		this.suplrPinCode = suplrPinCode;
	}


	public String getSuplrPhone() {
		return suplrPhone;
	}


	public void setSuplrPhone(String suplrPhone) {
		this.suplrPhone = suplrPhone;
	}


	public String getSuplrEmail() {
		return suplrEmail;
	}


	public void setSuplrEmail(String suplrEmail) {
		this.suplrEmail = suplrEmail;
	}


	public String getRecipientLegalName() {
		return recipientLegalName;
	}


	public void setRecipientLegalName(String recipientLegalName) {
		this.recipientLegalName = recipientLegalName;
	}


	public String getRecipientAddr1() {
		return recipientAddr1;
	}


	public void setRecipientAddr1(String recipientAddr1) {
		this.recipientAddr1 = recipientAddr1;
	}


	public String getRecipientAddr2() {
		return recipientAddr2;
	}


	public void setRecipientAddr2(String recipientAddr2) {
		this.recipientAddr2 = recipientAddr2;
	}


	public String getRecipientLocality() {
		return recipientLocality;
	}


	public void setRecipientLocality(String recipientLocality) {
		this.recipientLocality = recipientLocality;
	}


	public String getRecipientPin() {
		return recipientPin;
	}


	public void setRecipientPin(String recipientPin) {
		this.recipientPin = recipientPin;
	}


	public String getRecipientPhone() {
		return recipientPhone;
	}


	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}


	public String getRecipientEmail() {
		return recipientEmail;
	}


	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}


	public String getIrn() {
		return irn;
	}


	public void setIrn(String irn) {
		this.irn = irn;
	}


	public String getSqrCode() {
		return sqrCode;
	}


	public void setSqrCode(String sqrCode) {
		this.sqrCode = sqrCode;
	}


	public String getStatusEinv() {
		return statusEinv;
	}


	public void setStatusEinv(String statusEinv) {
		this.statusEinv = statusEinv;
	}


	public String getAckNo() {
		return ackNo;
	}


	public void setAckNo(String ackNo) {
		this.ackNo = ackNo;
	}


	public LocalDateTime getAckDate() {
		return ackDate;
	}


	public void setAckDate(LocalDateTime ackDate) {
		this.ackDate = ackDate;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getOtherChrg() {
		return otherChrg;
	}


	public void setOtherChrg(String otherChrg) {
		this.otherChrg = otherChrg;
	}


	public String getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getStatusCons() {
		return statusCons;
	}


	public void setStatusCons(String statusCons) {
		this.statusCons = statusCons;
	}


	@Override
	public String toString() {
		return "ZenInvoice [id=" + id + ", serviceNo=" + serviceNo + ", billMonth=" + billMonth + ", billYear="
				+ billYear + ", taxPayerGstIn=" + taxPayerGstIn + ", customerGstIn=" + customerGstIn + ", documentNo="
				+ documentNo + ", documentDate=" + documentDate + ", documentType=" + documentType
				+ ", transactionType=" + transactionType + ", hsnSac=" + hsnSac + ", description=" + description
				+ ", taxableValue=" + taxableValue + ", gstTaxRate=" + gstTaxRate + ", uqc=" + uqc + ", quantity="
				+ quantity + ", igstAmount=" + igstAmount + ", sgstAmount=" + sgstAmount + ", cgstAmount=" + cgstAmount
				+ ", cess=" + cess + ", placeOfsupply=" + placeOfsupply + ", reverseChrg=" + reverseChrg
				+ ", suplrLegalName=" + suplrLegalName + ", suplrAddr1=" + suplrAddr1 + ", suplrAddr2=" + suplrAddr2
				+ ", suplrLocality=" + suplrLocality + ", suplrPinCode=" + suplrPinCode + ", suplrPhone=" + suplrPhone
				+ ", suplrEmail=" + suplrEmail + ", recipientLegalName=" + recipientLegalName + ", recipientAddr1="
				+ recipientAddr1 + ", recipientAddr2=" + recipientAddr2 + ", recipientLocality=" + recipientLocality
				+ ", recipientPin=" + recipientPin + ", recipientPhone=" + recipientPhone + ", recipientEmail="
				+ recipientEmail + ", irn=" + irn + ", sqrCode=" + sqrCode + ", statusEinv=" + statusEinv + ", ackNo="
				+ ackNo + ", ackDate=" + ackDate + ", department=" + department + ", otherChrg=" + otherChrg
				+ ", totalAmount=" + totalAmount + ", statusCons=" + statusCons + "]";
	}
	
	
	
}