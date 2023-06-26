package com.ss.oa.transaction.vo;

public class CsQuantumAllocation {
	
	private String id;
	private String csId;
	private String buyerCompServiceId;
	private String buyerCompServiceName;
	private String buyerCompServiceNumber;
	private String buyerCompName,buyerCompId,buyerCompCode;
	private String buyerOrgId; 
	private String buyerOrgCode;
	private String buyerOrgName;
	private String buyerSubstationId,buyerSubstationCode,buyerSubstationName;
	private String buyerFeederId,buyerFeederCode,buyerFeederName;
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
	
	public CsQuantumAllocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CsQuantumAllocation(String id, String csId, String buyerCompServiceId, String buyerCompServiceName,
			String buyerCompServiceNumber, String buyerCompName, String buyerCompId, String buyerCompCode,
			String buyerOrgId, String buyerOrgCode, String buyerOrgName, String buyerSubstationId,
			String buyerSubstationCode, String buyerSubstationName, String buyerFeederId, String buyerFeederCode,
			String buyerFeederName, String captiveCompanyName, String quantum, String injectionVoltageCode,
			String injectionVoltageName, String drawalVoltageCode, String drawalVoltageName, String createdBy,
			String createdDate, String modifiedBy, String modifiedDate, String enabled, String remarks) {
		super();
		this.id = id;
		this.csId = csId;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceName = buyerCompServiceName;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerCompName = buyerCompName;
		this.buyerCompId = buyerCompId;
		this.buyerCompCode = buyerCompCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerOrgName = buyerOrgName;
		this.buyerSubstationId = buyerSubstationId;
		this.buyerSubstationCode = buyerSubstationCode;
		this.buyerSubstationName = buyerSubstationName;
		this.buyerFeederId = buyerFeederId;
		this.buyerFeederCode = buyerFeederCode;
		this.buyerFeederName = buyerFeederName;
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
	}

	@Override
	public String toString() {
		return "CsQuantumAllocation [id=" + id + ", csId=" + csId + ", buyerCompServiceId=" + buyerCompServiceId
				+ ", buyerCompServiceName=" + buyerCompServiceName + ", buyerCompServiceNumber="
				+ buyerCompServiceNumber + ", buyerCompName=" + buyerCompName + ", buyerCompId=" + buyerCompId
				+ ", buyerCompCode=" + buyerCompCode + ", buyerOrgId=" + buyerOrgId + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerOrgName=" + buyerOrgName + ", buyerSubstationId=" + buyerSubstationId
				+ ", buyerSubstationCode=" + buyerSubstationCode + ", buyerSubstationName=" + buyerSubstationName
				+ ", buyerFeederId=" + buyerFeederId + ", buyerFeederCode=" + buyerFeederCode + ", buyerFeederName="
				+ buyerFeederName + ", captiveCompanyName=" + captiveCompanyName + ", quantum=" + quantum
				+ ", injectionVoltageCode=" + injectionVoltageCode + ", injectionVoltageName=" + injectionVoltageName
				+ ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageName=" + drawalVoltageName
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", enabled=" + enabled + ", remarks=" + remarks + "]";
	}

	public String getId() {
		return id;
	}

	public String getCsId() {
		return csId;
	}

	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}

	public String getBuyerCompServiceName() {
		return buyerCompServiceName;
	}

	public String getBuyerCompServiceNumber() {
		return buyerCompServiceNumber;
	}

	public String getBuyerCompName() {
		return buyerCompName;
	}

	public String getBuyerCompId() {
		return buyerCompId;
	}

	public String getBuyerCompCode() {
		return buyerCompCode;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerSubstationId() {
		return buyerSubstationId;
	}

	public String getBuyerSubstationCode() {
		return buyerSubstationCode;
	}

	public String getBuyerSubstationName() {
		return buyerSubstationName;
	}

	public String getBuyerFeederId() {
		return buyerFeederId;
	}

	public String getBuyerFeederCode() {
		return buyerFeederCode;
	}

	public String getBuyerFeederName() {
		return buyerFeederName;
	}

	public String getCaptiveCompanyName() {
		return captiveCompanyName;
	}

	public String getQuantum() {
		return quantum;
	}

	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}

	public String getInjectionVoltageName() {
		return injectionVoltageName;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public String getDrawalVoltageName() {
		return drawalVoltageName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public String getEnabled() {
		return enabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCsId(String csId) {
		this.csId = csId;
	}

	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}

	public void setBuyerCompServiceName(String buyerCompServiceName) {
		this.buyerCompServiceName = buyerCompServiceName;
	}

	public void setBuyerCompServiceNumber(String buyerCompServiceNumber) {
		this.buyerCompServiceNumber = buyerCompServiceNumber;
	}

	public void setBuyerCompName(String buyerCompName) {
		this.buyerCompName = buyerCompName;
	}

	public void setBuyerCompId(String buyerCompId) {
		this.buyerCompId = buyerCompId;
	}

	public void setBuyerCompCode(String buyerCompCode) {
		this.buyerCompCode = buyerCompCode;
	}

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerSubstationId(String buyerSubstationId) {
		this.buyerSubstationId = buyerSubstationId;
	}

	public void setBuyerSubstationCode(String buyerSubstationCode) {
		this.buyerSubstationCode = buyerSubstationCode;
	}

	public void setBuyerSubstationName(String buyerSubstationName) {
		this.buyerSubstationName = buyerSubstationName;
	}

	public void setBuyerFeederId(String buyerFeederId) {
		this.buyerFeederId = buyerFeederId;
	}

	public void setBuyerFeederCode(String buyerFeederCode) {
		this.buyerFeederCode = buyerFeederCode;
	}

	public void setBuyerFeederName(String buyerFeederName) {
		this.buyerFeederName = buyerFeederName;
	}

	public void setCaptiveCompanyName(String captiveCompanyName) {
		this.captiveCompanyName = captiveCompanyName;
	}

	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}

	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}

	public void setInjectionVoltageName(String injectionVoltageName) {
		this.injectionVoltageName = injectionVoltageName;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public void setDrawalVoltageName(String drawalVoltageName) {
		this.drawalVoltageName = drawalVoltageName;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

}
