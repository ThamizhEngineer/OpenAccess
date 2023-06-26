package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class CompanyEmployee {
	//designationName is for display purpose only and not stored
	private String id, companyId, purpose, name, employeeNumber, designationCode, designationName,landline, mobile, fax, email, enabled;
	public CompanyEmployee() {

	}
	public CompanyEmployee(String id, String companyId, String purpose, String name, String employeeNumber,
			String designationCode, String designationName, String landline, String mobile, String fax, String email,
			String enabled) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.purpose = purpose;
		this.name = name;
		this.employeeNumber = employeeNumber;
		this.designationCode = designationCode;
		this.designationName = designationName;
		this.landline = landline;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "CompanyEmployee [id=" + id + ", companyId=" + companyId + ", purpose=" + purpose + ", name=" + name
				+ ", employeeNumber=" + employeeNumber + ", designationCode=" + designationCode + ", designationName="
				+ designationName + ", landline=" + landline + ", mobile=" + mobile + ", fax=" + fax + ", email="
				+ email + ", enabled=" + enabled + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	

}
