package com.ss.oa.model.master;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.vo.CompanyServiceVO;

@Table(name="M_COMPANY_SERVICE")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class ServiceContactInfo implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="COMP_SER_TYPE_CODE")
	private String typeCode;
	@Column(name="\"number\"", nullable = false)
	private String number;
	@Column(name="M_COMPANY_ID")
	private String companyId;
	@Column(name="M_ORG_ID")
	private String orgId;
	@Column(name="TOTAL_CAPACITY")
	private String Totalcapacity;
	@Column(columnDefinition = "char") 
	private String enabled;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate ModifiedDate;
    private String contactFullName,contactDesignation,contactEmail,contactPhoneNo,regOfficeAddr,plantAddr,isContactVerified;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime contactLastVerifiedDate; 
	@Column(name="BANK_IFSC_CODE")
	private String bankIfscCode;
	@Column(name="BANK_NAME")
	private String bankName;
	@Column(name="BANK_ACCOUNT_NO")
	private String bankAccountNo;
	
	@Formula("(SELECT typecodes.VALUE_DESC FROM M_COMPANY_SERVICE service JOIN v_codes typecodes on service.COMP_SER_TYPE_CODE = typecodes.value_code and typecodes.list_code='SERVICE_TYPE_CODE' WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String companytypeName;
	@Formula("(SELECT company.COMPANY_TYPE_CODE FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String companyTypeCode;
	@Formula("(SELECT company.PAN FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String pan;
	@Formula("(SELECT company.CIN FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String cin;
	@Formula("(SELECT company.LLPIN FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String llpin;
	@Formula("(SELECT company.GST FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String gst; 
	@Formula("(SELECT company.TAN FROM M_COMPANY_SERVICE service JOIN M_COMPANY company on company.id =service.M_COMPANY_ID WHERE service.M_COMPANY_ID=M_COMPANY_ID)")
	private String tan;
	
	
	public ServiceContactInfo() {
		super();

	}


	public ServiceContactInfo(String id, String typeCode, String number, String companyId, String orgId,
			String totalcapacity, String enabled, String modifiedBy, LocalDate modifiedDate, String contactFullName,
			String contactDesignation, String contactEmail, String contactPhoneNo, String regOfficeAddr,
			String plantAddr, String isContactVerified, LocalDateTime contactLastVerifiedDate, String bankIfscCode,
			String bankName, String bankAccountNo, String companytypeName, String companyTypeCode, String pan,
			String cin, String llpin, String gst, String tan) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.number = number;
		this.companyId = companyId;
		this.orgId = orgId;
		Totalcapacity = totalcapacity;
		this.enabled = enabled;
		this.modifiedBy = modifiedBy;
		ModifiedDate = modifiedDate;
		this.contactFullName = contactFullName;
		this.contactDesignation = contactDesignation;
		this.contactEmail = contactEmail;
		this.contactPhoneNo = contactPhoneNo;
		this.regOfficeAddr = regOfficeAddr;
		this.plantAddr = plantAddr;
		this.isContactVerified = isContactVerified;
		this.contactLastVerifiedDate = contactLastVerifiedDate;
		this.bankIfscCode = bankIfscCode;
		this.bankName = bankName;
		this.bankAccountNo = bankAccountNo;
		this.companytypeName = companytypeName;
		this.companyTypeCode = companyTypeCode;
		this.pan = pan;
		this.cin = cin;
		this.llpin = llpin;
		this.gst = gst;
		this.tan = tan;
	}


	@Override
	public String toString() {
		return "ServiceContactInfo [id=" + id + ", typeCode=" + typeCode + ", number=" + number + ", companyId="
				+ companyId + ", orgId=" + orgId + ", Totalcapacity=" + Totalcapacity + ", enabled=" + enabled
				+ ", modifiedBy=" + modifiedBy + ", ModifiedDate=" + ModifiedDate + ", contactFullName="
				+ contactFullName + ", contactDesignation=" + contactDesignation + ", contactEmail=" + contactEmail
				+ ", contactPhoneNo=" + contactPhoneNo + ", regOfficeAddr=" + regOfficeAddr + ", plantAddr=" + plantAddr
				+ ", isContactVerified=" + isContactVerified + ", contactLastVerifiedDate=" + contactLastVerifiedDate
				+ ", bankIfscCode=" + bankIfscCode + ", bankName=" + bankName + ", bankAccountNo=" + bankAccountNo
				+ ", companytypeName=" + companytypeName + ", companyTypeCode=" + companyTypeCode + ", pan=" + pan
				+ ", cin=" + cin + ", llpin=" + llpin + ", gst=" + gst + ", tan=" + tan + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTypeCode() {
		return typeCode;
	}


	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getTotalcapacity() {
		return Totalcapacity;
	}


	public void setTotalcapacity(String totalcapacity) {
		Totalcapacity = totalcapacity;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDate getModifiedDate() {
		return ModifiedDate;
	}


	public void setModifiedDate(LocalDate modifiedDate) {
		ModifiedDate = modifiedDate;
	}


	public String getContactFullName() {
		return contactFullName;
	}


	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}


	public String getContactDesignation() {
		return contactDesignation;
	}


	public void setContactDesignation(String contactDesignation) {
		this.contactDesignation = contactDesignation;
	}


	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}


	public String getContactPhoneNo() {
		return contactPhoneNo;
	}


	public void setContactPhoneNo(String contactPhoneNo) {
		this.contactPhoneNo = contactPhoneNo;
	}


	public String getRegOfficeAddr() {
		return regOfficeAddr;
	}


	public void setRegOfficeAddr(String regOfficeAddr) {
		this.regOfficeAddr = regOfficeAddr;
	}


	public String getPlantAddr() {
		return plantAddr;
	}


	public void setPlantAddr(String plantAddr) {
		this.plantAddr = plantAddr;
	}


	public String getIsContactVerified() {
		return isContactVerified;
	}


	public void setIsContactVerified(String isContactVerified) {
		this.isContactVerified = isContactVerified;
	}


	public LocalDateTime getContactLastVerifiedDate() {
		return contactLastVerifiedDate;
	}


	public void setContactLastVerifiedDate(LocalDateTime contactLastVerifiedDate) {
		this.contactLastVerifiedDate = contactLastVerifiedDate;
	}


	public String getBankIfscCode() {
		return bankIfscCode;
	}


	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getBankAccountNo() {
		return bankAccountNo;
	}


	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}


	public String getCompanytypeName() {
		return companytypeName;
	}


	public void setCompanytypeName(String companytypeName) {
		this.companytypeName = companytypeName;
	}


	public String getCompanyTypeCode() {
		return companyTypeCode;
	}


	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getCin() {
		return cin;
	}


	public void setCin(String cin) {
		this.cin = cin;
	}


	public String getLlpin() {
		return llpin;
	}


	public void setLlpin(String llpin) {
		this.llpin = llpin;
	}


	public String getGst() {
		return gst;
	}


	public void setGst(String gst) {
		this.gst = gst;
	}


	public String getTan() {
		return tan;
	}


	public void setTan(String tan) {
		this.tan = tan;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	} 


}
