package com.ss.oa.master.vo;

import java.util.List;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class Company {
	
	private String id, code, name, companyTypeCode,companyTypeName, registrationNo;
	private String registrationDate, cobDate;
	private String incorpPlace, serviceTypeCode,serviceTypeName,isCaptive, captivePurpose, pan, tan, gst, cst,cin, llpin, enabled;
	private String remarks;
	private String isInternal, bankingServiceId, unadjustedServiceId, bankingServiceNumber, unadjustedServiceNumber,isBuyer,isSeller;
	private List<CompanyEmployee> employees;
	private List<CompanyLocation> locations;
	private List<CompanyShareHolder> shareHolders;
	private List<CompanyServiceVO> services;
	private List<Meter> meters;
	
	public Company() {
		super();
	}


	public Company(String id, String code, String name, String companyTypeCode, String companyTypeName,
			String registrationNo, String registrationDate, String cobDate, String incorpPlace, String serviceTypeCode,
			String serviceTypeName, String isCaptive, String captivePurpose, String pan, String tan, String gst,
			String cst, String cin, String llpin, String enabled, String remarks, String isInternal,
			String bankingServiceId, String unadjustedServiceId, String bankingServiceNumber,
			String unadjustedServiceNumber, String isBuyer, String isSeller, List<CompanyEmployee> employees,
			List<CompanyLocation> locations, List<CompanyShareHolder> shareHolders, List<CompanyServiceVO> services,
			List<Meter> meters) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.companyTypeCode = companyTypeCode;
		this.companyTypeName = companyTypeName;
		this.registrationNo = registrationNo;
		this.registrationDate = registrationDate;
		this.cobDate = cobDate;
		this.incorpPlace = incorpPlace;
		this.serviceTypeCode = serviceTypeCode;
		this.serviceTypeName = serviceTypeName;
		this.isCaptive = isCaptive;
		this.captivePurpose = captivePurpose;
		this.pan = pan;
		this.tan = tan;
		this.gst = gst;
		this.cst = cst;
		this.cin = cin;
		this.llpin = llpin;
		this.enabled = enabled;
		this.remarks = remarks;
		this.isInternal = isInternal;
		this.bankingServiceId = bankingServiceId;
		this.unadjustedServiceId = unadjustedServiceId;
		this.bankingServiceNumber = bankingServiceNumber;
		this.unadjustedServiceNumber = unadjustedServiceNumber;
		this.isBuyer = isBuyer;
		this.isSeller = isSeller;
		this.employees = employees;
		this.locations = locations;
		this.shareHolders = shareHolders;
		this.services = services;
		this.meters = meters;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", code=" + code + ", name=" + name + ", companyTypeCode=" + companyTypeCode
				+ ", companyTypeName=" + companyTypeName + ", registrationNo=" + registrationNo + ", registrationDate="
				+ registrationDate + ", cobDate=" + cobDate + ", incorpPlace=" + incorpPlace + ", serviceTypeCode="
				+ serviceTypeCode + ", serviceTypeName=" + serviceTypeName + ", isCaptive=" + isCaptive
				+ ", captivePurpose=" + captivePurpose + ", pan=" + pan + ", tan=" + tan + ", gst=" + gst + ", cst="
				+ cst + ", cin=" + cin + ", llpin=" + llpin + ", enabled=" + enabled + ", remarks=" + remarks
				+ ", isInternal=" + isInternal + ", bankingServiceId=" + bankingServiceId + ", unadjustedServiceId="
				+ unadjustedServiceId + ", bankingServiceNumber=" + bankingServiceNumber + ", unadjustedServiceNumber="
				+ unadjustedServiceNumber + ", isBuyer=" + isBuyer + ", isSeller=" + isSeller + ", employees="
				+ employees + ", locations=" + locations + ", shareHolders=" + shareHolders + ", services=" + services
				+ ", meters=" + meters + "]";
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


	public String getCompanyTypeName() {
		return companyTypeName;
	}


	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}


	public String getRegistrationNo() {
		return registrationNo;
	}


	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}


	public String getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}


	public String getCobDate() {
		return cobDate;
	}


	public void setCobDate(String cobDate) {
		this.cobDate = cobDate;
	}


	public String getIncorpPlace() {
		return incorpPlace;
	}


	public void setIncorpPlace(String incorpPlace) {
		this.incorpPlace = incorpPlace;
	}


	public String getServiceTypeCode() {
		return serviceTypeCode;
	}


	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}


	public String getServiceTypeName() {
		return serviceTypeName;
	}


	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
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


	public List<CompanyEmployee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<CompanyEmployee> employees) {
		this.employees = employees;
	}


	public List<CompanyLocation> getLocations() {
		return locations;
	}


	public void setLocations(List<CompanyLocation> locations) {
		this.locations = locations;
	}


	public List<CompanyShareHolder> getShareHolders() {
		return shareHolders;
	}


	public void setShareHolders(List<CompanyShareHolder> shareHolders) {
		this.shareHolders = shareHolders;
	}


	public List<CompanyServiceVO> getServices() {
		return services;
	}


	public void setServices(List<CompanyServiceVO> services) {
		this.services = services;
	}


	public List<Meter> getMeters() {
		return meters;
	}


	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	
			
}
