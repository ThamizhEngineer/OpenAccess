package com.ss.oa.transaction.vo;

public class CompanyNameChange {
	
	
	private String id,code;
	private String companyId, companyCode, companyName;
	private String serviceId,serviceNumber, orgId, orgCode, orgName; 
	private String feederId, feederCode, feederName,substationId, substationCode, substationName;
	private String companyMeterId,companyNameChangeDate;
	private String  remarks;
    private String  oldCompanyName,newCompanyName ;
    private String  statusCode,statusName;
    private String flowTypeCode,flowTypeName;
    
	public CompanyNameChange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyNameChange(String id, String code, String companyId, String companyCode, String companyName,
			String serviceId, String serviceNumber, String orgId, String orgCode, String orgName, String feederId,
			String feederCode, String feederName, String substationId, String substationCode, String substationName,
			String companyMeterId, String companyNameChangeDate, String remarks, String oldCompanyName,
			String newCompanyName, String statusCode, String statusName, String flowTypeCode, String flowTypeName) {
		super();
		this.id = id;
		this.code = code;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.serviceId = serviceId;
		this.serviceNumber = serviceNumber;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.feederId = feederId;
		this.feederCode = feederCode;
		this.feederName = feederName;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.substationName = substationName;
		this.companyMeterId = companyMeterId;
		this.companyNameChangeDate = companyNameChangeDate;
		this.remarks = remarks;
		this.oldCompanyName = oldCompanyName;
		this.newCompanyName = newCompanyName;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
	}

	@Override
	public String toString() {
		return "CompanyNameChange [id=" + id + ", code=" + code + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", serviceId=" + serviceId + ", serviceNumber="
				+ serviceNumber + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName + ", feederId="
				+ feederId + ", feederCode=" + feederCode + ", feederName=" + feederName + ", substationId="
				+ substationId + ", substationCode=" + substationCode + ", substationName=" + substationName
				+ ", companyMeterId=" + companyMeterId + ", companyNameChangeDate=" + companyNameChangeDate
				+ ", remarks=" + remarks + ", oldCompanyName=" + oldCompanyName + ", newCompanyName=" + newCompanyName
				+ ", statusCode=" + statusCode + ", statusName=" + statusName + ", flowTypeCode=" + flowTypeCode
				+ ", flowTypeName=" + flowTypeName + "]";
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getFeederId() {
		return feederId;
	}

	public String getFeederCode() {
		return feederCode;
	}

	public String getFeederName() {
		return feederName;
	}

	public String getSubstationId() {
		return substationId;
	}

	public String getSubstationCode() {
		return substationCode;
	}

	public String getSubstationName() {
		return substationName;
	}

	public String getCompanyMeterId() {
		return companyMeterId;
	}

	public String getCompanyNameChangeDate() {
		return companyNameChangeDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getOldCompanyName() {
		return oldCompanyName;
	}

	public String getNewCompanyName() {
		return newCompanyName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public void setCompanyMeterId(String companyMeterId) {
		this.companyMeterId = companyMeterId;
	}

	public void setCompanyNameChangeDate(String companyNameChangeDate) {
		this.companyNameChangeDate = companyNameChangeDate;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setOldCompanyName(String oldCompanyName) {
		this.oldCompanyName = oldCompanyName;
	}

	public void setNewCompanyName(String newCompanyName) {
		this.newCompanyName = newCompanyName;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}


}
