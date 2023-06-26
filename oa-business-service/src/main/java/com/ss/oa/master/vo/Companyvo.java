package com.ss.oa.master.vo;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name="m_company")
@CreationTimestamp @UpdateTimestamp
@Entity
@Scope("prototype")
public class Companyvo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_M_COMPANY_SEQ")
    @SequenceGenerator(name = "id_M_COMPANY_SEQ", sequenceName ="M_COMPANY_SEQ")
	private String id; 
	private String code;
	private String name;
	private String companyTypeCode;
//	private String companyTypeName; 
	private String registrationNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registrationDate; 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime cobDate;
	private String incorpPlace; 
//	private String serviceTypeCode;
//	private String serviceTypeName;
	private String isCaptive;
	private String captivePurpose; 
	private String pan;
	private String tan;
	private String gst;
	private String cst;
	@Column(columnDefinition = "char") 
	private String enabled;
	private String remarks;
	@Column(columnDefinition = "char")
	private String isInternal;
	private String bankingServiceId; 
	private String unadjustedServiceId; 
	private String bankingServiceNumber; 
	private String unadjustedServiceNumber;
	@Column(columnDefinition = "char") 
	private String isBuyer;
	@Column(columnDefinition = "char") 
	private String isSeller;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
//	private List<CompanyEmployee> employees;
//	private List<CompanyLocation> locations;
//	private List<CompanyShareHolder> shareHolders;
//	private List<CompanyServiceVO> services;
//	private List<Meter> meters;
	
	public Companyvo() {
		super();
	}
public Companyvo(String id, String code, String name, String companyTypeCode, String registrationNo,
		LocalDateTime registrationDate, LocalDateTime cobDate, String incorpPlace, String isCaptive,
		String captivePurpose, String pan, String tan, String gst, String cst, String enabled, String remarks,
		String isInternal, String bankingServiceId, String unadjustedServiceId, String bankingServiceNumber,
		String unadjustedServiceNumber, String isBuyer, String isSeller, LocalDateTime createdDate,
		LocalDateTime modifiedDate) {
	super();
	this.id = id;
	this.code = code;
	this.name = name;
	this.companyTypeCode = companyTypeCode;
	this.registrationNo = registrationNo;
	this.registrationDate = registrationDate;
	this.cobDate = cobDate;
	this.incorpPlace = incorpPlace;
	this.isCaptive = isCaptive;
	this.captivePurpose = captivePurpose;
	this.pan = pan;
	this.tan = tan;
	this.gst = gst;
	this.cst = cst;
	this.enabled = enabled;
	this.remarks = remarks;
	this.isInternal = isInternal;
	this.bankingServiceId = bankingServiceId;
	this.unadjustedServiceId = unadjustedServiceId;
	this.bankingServiceNumber = bankingServiceNumber;
	this.unadjustedServiceNumber = unadjustedServiceNumber;
	this.isBuyer = isBuyer;
	this.isSeller = isSeller;
	this.createdDate = createdDate;
	this.modifiedDate = modifiedDate;
}
@Override
public String toString() {
	return "Companyvo [id=" + id + ", code=" + code + ", name=" + name + ", companyTypeCode=" + companyTypeCode
			+ ", registrationNo=" + registrationNo + ", registrationDate=" + registrationDate + ", cobDate=" + cobDate
			+ ", incorpPlace=" + incorpPlace + ", isCaptive=" + isCaptive + ", captivePurpose=" + captivePurpose
			+ ", pan=" + pan + ", tan=" + tan + ", gst=" + gst + ", cst=" + cst + ", enabled=" + enabled + ", remarks="
			+ remarks + ", isInternal=" + isInternal + ", bankingServiceId=" + bankingServiceId
			+ ", unadjustedServiceId=" + unadjustedServiceId + ", bankingServiceNumber=" + bankingServiceNumber
			+ ", unadjustedServiceNumber=" + unadjustedServiceNumber + ", isBuyer=" + isBuyer + ", isSeller=" + isSeller
			+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCompanyTypeCode() {
	return companyTypeCode;
}
public void setCompanyTypeCode(String companyTypeCode) {
	this.companyTypeCode = companyTypeCode;
}
public String getRegistrationNo() {
	return registrationNo;
}
public void setRegistrationNo(String registrationNo) {
	this.registrationNo = registrationNo;
}
public LocalDateTime getRegistrationDate() {
	return registrationDate;
}
public void setRegistrationDate(LocalDateTime registrationDate) {
	this.registrationDate = registrationDate;
}
public LocalDateTime getCobDate() {
	return cobDate;
}
public void setCobDate(LocalDateTime cobDate) {
	this.cobDate = cobDate;
}
public String getIncorpPlace() {
	return incorpPlace;
}
public void setIncorpPlace(String incorpPlace) {
	this.incorpPlace = incorpPlace;
}
public String getIsCaptive() {
	return isCaptive;
}
public void setIsCaptive(String isCaptive) {
	this.isCaptive = isCaptive;
}
public String getCaptivePurpose() {
	return captivePurpose;
}
public void setCaptivePurpose(String captivePurpose) {
	this.captivePurpose = captivePurpose;
}
public String getPan() {
	return pan;
}
public void setPan(String pan) {
	this.pan = pan;
}
public String getTan() {
	return tan;
}
public void setTan(String tan) {
	this.tan = tan;
}
public String getGst() {
	return gst;
}
public void setGst(String gst) {
	this.gst = gst;
}
public String getCst() {
	return cst;
}
public void setCst(String cst) {
	this.cst = cst;
}
public String getEnabled() {
	return enabled;
}
public void setEnabled(String enabled) {
	this.enabled = enabled;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getIsInternal() {
	return isInternal;
}
public void setIsInternal(String isInternal) {
	this.isInternal = isInternal;
}
public String getBankingServiceId() {
	return bankingServiceId;
}
public void setBankingServiceId(String bankingServiceId) {
	this.bankingServiceId = bankingServiceId;
}
public String getUnadjustedServiceId() {
	return unadjustedServiceId;
}
public void setUnadjustedServiceId(String unadjustedServiceId) {
	this.unadjustedServiceId = unadjustedServiceId;
}
public String getBankingServiceNumber() {
	return bankingServiceNumber;
}
public void setBankingServiceNumber(String bankingServiceNumber) {
	this.bankingServiceNumber = bankingServiceNumber;
}
public String getUnadjustedServiceNumber() {
	return unadjustedServiceNumber;
}
public void setUnadjustedServiceNumber(String unadjustedServiceNumber) {
	this.unadjustedServiceNumber = unadjustedServiceNumber;
}
public String getIsBuyer() {
	return isBuyer;
}
public void setIsBuyer(String isBuyer) {
	this.isBuyer = isBuyer;
}
public String getIsSeller() {
	return isSeller;
}
public void setIsSeller(String isSeller) {
	this.isSeller = isSeller;
}
public LocalDateTime getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(LocalDateTime createdDate) {
	this.createdDate = createdDate;
}
public LocalDateTime getModifiedDate() {
	return modifiedDate;
}
public void setModifiedDate(LocalDateTime modifiedDate) {
	this.modifiedDate = modifiedDate;
}


	
}
