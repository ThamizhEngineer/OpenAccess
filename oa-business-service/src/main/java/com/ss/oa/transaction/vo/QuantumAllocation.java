package com.ss.oa.transaction.vo;

public class QuantumAllocation {
	private String id;
	private String gcId;
	private String buyerCompServiceId;
	private String buyerCompServiceName;
	private String buyerCompServiceNumber;
	private String buyerCompName,buyerCompId,buyerCompCode;
	private String buyerOrgId; 
	private String buyerOrgCode;
	private String buyerOrgName;
	private String captiveCompanyName;
	private String quantum;
	private String injectionVoltageCode;
	private String injectionVoltageName;
	private String drawalVoltageCode;
	private String drawalVoltageName;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String enabled;
	private String remarks;
	private String sharedPercentage;
	
	public QuantumAllocation() {
		super();
	}

	@Override
	public String toString() {
		return "QuantumAllocation [id=" + id + ", gcId=" + gcId + ", buyerCompServiceId=" + buyerCompServiceId
				+ ", buyerCompServiceName=" + buyerCompServiceName + ", buyerCompServiceNumber="
				+ buyerCompServiceNumber + ", buyerCompName=" + buyerCompName + ", buyerCompId=" + buyerCompId
				+ ", buyerCompCode=" + buyerCompCode + ", buyerOrgId=" + buyerOrgId + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerOrgName=" + buyerOrgName + ", captiveCompanyName=" + captiveCompanyName + ", quantum="
				+ quantum + ", injectionVoltageCode=" + injectionVoltageCode + ", injectionVoltageName="
				+ injectionVoltageName + ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageName="
				+ drawalVoltageName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", enabled=" + enabled + ", remarks=" + remarks
				+ ", sharedPercentage=" + sharedPercentage + "]";
	}

	public QuantumAllocation(String id, String gcId, String buyerCompServiceId, String buyerCompServiceName,
			String buyerCompServiceNumber, String buyerCompName, String buyerCompId, String buyerCompCode,
			String buyerOrgId, String buyerOrgCode, String buyerOrgName, String captiveCompanyName, String quantum,
			String injectionVoltageCode, String injectionVoltageName, String drawalVoltageCode,
			String drawalVoltageName, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
			String enabled, String remarks, String sharedPercentage) {
		super();
		this.id = id;
		this.gcId = gcId;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceName = buyerCompServiceName;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerCompName = buyerCompName;
		this.buyerCompId = buyerCompId;
		this.buyerCompCode = buyerCompCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerOrgName = buyerOrgName;
		this.captiveCompanyName = captiveCompanyName;
		this.quantum = quantum;
		this.injectionVoltageCode = injectionVoltageCode;
		this.injectionVoltageName = injectionVoltageName;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageName = drawalVoltageName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.enabled = enabled;
		this.remarks = remarks;
		this.sharedPercentage = sharedPercentage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGcId() {
		return gcId;
	}

	public void setGcId(String gcId) {
		this.gcId = gcId;
	}

	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}

	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}

	public String getBuyerCompServiceName() {
		return buyerCompServiceName;
	}

	public void setBuyerCompServiceName(String buyerCompServiceName) {
		this.buyerCompServiceName = buyerCompServiceName;
	}

	public String getBuyerCompServiceNumber() {
		return buyerCompServiceNumber;
	}

	public void setBuyerCompServiceNumber(String buyerCompServiceNumber) {
		this.buyerCompServiceNumber = buyerCompServiceNumber;
	}

	public String getBuyerCompName() {
		return buyerCompName;
	}

	public void setBuyerCompName(String buyerCompName) {
		this.buyerCompName = buyerCompName;
	}

	public String getBuyerCompId() {
		return buyerCompId;
	}

	public void setBuyerCompId(String buyerCompId) {
		this.buyerCompId = buyerCompId;
	}

	public String getBuyerCompCode() {
		return buyerCompCode;
	}

	public void setBuyerCompCode(String buyerCompCode) {
		this.buyerCompCode = buyerCompCode;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public String getCaptiveCompanyName() {
		return captiveCompanyName;
	}

	public void setCaptiveCompanyName(String captiveCompanyName) {
		this.captiveCompanyName = captiveCompanyName;
	}

	public String getQuantum() {
		return quantum;
	}

	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}

	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}

	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}

	public String getInjectionVoltageName() {
		return injectionVoltageName;
	}

	public void setInjectionVoltageName(String injectionVoltageName) {
		this.injectionVoltageName = injectionVoltageName;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public String getDrawalVoltageName() {
		return drawalVoltageName;
	}

	public void setDrawalVoltageName(String drawalVoltageName) {
		this.drawalVoltageName = drawalVoltageName;
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

	public String getSharedPercentage() {
		return sharedPercentage;
	}

	public void setSharedPercentage(String sharedPercentage) {
		this.sharedPercentage = sharedPercentage;
	}

}