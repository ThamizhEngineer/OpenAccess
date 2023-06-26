package com.ss.oa.transaction.vo;

public class NocGeneratorLine {
	private String  id ;
	private String  nocGeneratorId ;
	private String  buyerOrgId ;
	private String  buyerOrgName ;
	private String  buyerCompServiceId ;
	private String  buyerCompServiceNumber ;
	private String  buyerCompanyName ;
	private String  buyerCompanyId ;
	private String  buyerCapacity ;
	private String  drawalVoltageCode ;
	private String  drawalVoltageDesc ;
	private String  injectionVoltageCode ;
	private String  injectionVoltageDesc ;
	private String  injectionPeakUnits ;
	private String  injectionOffPeakUnits ;
	private String  loss ;
	private String  drawalPeakUnits ;
	private String  drawalOffPeakUnits ;
	private String  proposedUnits,approvedUnits,isCaptive;
	private String  appliedDate ;
	private String  approvedDate ;
	private String  statusCode ;
	private String  statusDesc ;
	private String  remarks ;
	private String  createdBy ;
	private String  createdDate ; 
	private String  modifiedBy ;
	private String  modifiedDate ;
	
	public NocGeneratorLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NocGeneratorLine(String id, String nocGeneratorId, String buyerOrgId, String buyerOrgName,
			String buyerCompServiceId, String buyerCompServiceNumber, String buyerCompanyName, String buyerCompanyId,
			String buyerCapacity, String drawalVoltageCode, String drawalVoltageDesc, String injectionVoltageCode,
			String injectionVoltageDesc, String injectionPeakUnits, String injectionOffPeakUnits, String loss,
			String drawalPeakUnits, String drawalOffPeakUnits, String proposedUnits, String approvedUnits,
			String isCaptive, String appliedDate, String approvedDate, String statusCode, String statusDesc,
			String remarks, String createdBy, String createdDate, String modifiedBy, String modifiedDate) {
		super();
		this.id = id;
		this.nocGeneratorId = nocGeneratorId;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerCompServiceId = buyerCompServiceId;
		this.buyerCompServiceNumber = buyerCompServiceNumber;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCapacity = buyerCapacity;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageDesc = drawalVoltageDesc;
		this.injectionVoltageCode = injectionVoltageCode;
		this.injectionVoltageDesc = injectionVoltageDesc;
		this.injectionPeakUnits = injectionPeakUnits;
		this.injectionOffPeakUnits = injectionOffPeakUnits;
		this.loss = loss;
		this.drawalPeakUnits = drawalPeakUnits;
		this.drawalOffPeakUnits = drawalOffPeakUnits;
		this.proposedUnits = proposedUnits;
		this.approvedUnits = approvedUnits;
		this.isCaptive = isCaptive;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "NocGeneratorLine [id=" + id + ", nocGeneratorId=" + nocGeneratorId + ", buyerOrgId=" + buyerOrgId
				+ ", buyerOrgName=" + buyerOrgName + ", buyerCompServiceId=" + buyerCompServiceId
				+ ", buyerCompServiceNumber=" + buyerCompServiceNumber + ", buyerCompanyName=" + buyerCompanyName
				+ ", buyerCompanyId=" + buyerCompanyId + ", buyerCapacity=" + buyerCapacity + ", drawalVoltageCode="
				+ drawalVoltageCode + ", drawalVoltageDesc=" + drawalVoltageDesc + ", injectionVoltageCode="
				+ injectionVoltageCode + ", injectionVoltageDesc=" + injectionVoltageDesc + ", injectionPeakUnits="
				+ injectionPeakUnits + ", injectionOffPeakUnits=" + injectionOffPeakUnits + ", loss=" + loss
				+ ", drawalPeakUnits=" + drawalPeakUnits + ", drawalOffPeakUnits=" + drawalOffPeakUnits
				+ ", proposedUnits=" + proposedUnits + ", approvedUnits=" + approvedUnits + ", isCaptive=" + isCaptive
				+ ", appliedDate=" + appliedDate + ", approvedDate=" + approvedDate + ", statusCode=" + statusCode
				+ ", statusDesc=" + statusDesc + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

	public String getId() {
		return id;
	}

	public String getNocGeneratorId() {
		return nocGeneratorId;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerCompServiceId() {
		return buyerCompServiceId;
	}

	public String getBuyerCompServiceNumber() {
		return buyerCompServiceNumber;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public String getBuyerCapacity() {
		return buyerCapacity;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public String getDrawalVoltageDesc() {
		return drawalVoltageDesc;
	}

	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}

	public String getInjectionVoltageDesc() {
		return injectionVoltageDesc;
	}

	public String getInjectionPeakUnits() {
		return injectionPeakUnits;
	}

	public String getInjectionOffPeakUnits() {
		return injectionOffPeakUnits;
	}

	public String getLoss() {
		return loss;
	}

	public String getDrawalPeakUnits() {
		return drawalPeakUnits;
	}

	public String getDrawalOffPeakUnits() {
		return drawalOffPeakUnits;
	}

	public String getProposedUnits() {
		return proposedUnits;
	}

	public String getApprovedUnits() {
		return approvedUnits;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public String getRemarks() {
		return remarks;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setNocGeneratorId(String nocGeneratorId) {
		this.nocGeneratorId = nocGeneratorId;
	}

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerCompServiceId(String buyerCompServiceId) {
		this.buyerCompServiceId = buyerCompServiceId;
	}

	public void setBuyerCompServiceNumber(String buyerCompServiceNumber) {
		this.buyerCompServiceNumber = buyerCompServiceNumber;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public void setBuyerCapacity(String buyerCapacity) {
		this.buyerCapacity = buyerCapacity;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public void setDrawalVoltageDesc(String drawalVoltageDesc) {
		this.drawalVoltageDesc = drawalVoltageDesc;
	}

	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}

	public void setInjectionVoltageDesc(String injectionVoltageDesc) {
		this.injectionVoltageDesc = injectionVoltageDesc;
	}

	public void setInjectionPeakUnits(String injectionPeakUnits) {
		this.injectionPeakUnits = injectionPeakUnits;
	}

	public void setInjectionOffPeakUnits(String injectionOffPeakUnits) {
		this.injectionOffPeakUnits = injectionOffPeakUnits;
	}

	public void setLoss(String loss) {
		this.loss = loss;
	}

	public void setDrawalPeakUnits(String drawalPeakUnits) {
		this.drawalPeakUnits = drawalPeakUnits;
	}

	public void setDrawalOffPeakUnits(String drawalOffPeakUnits) {
		this.drawalOffPeakUnits = drawalOffPeakUnits;
	}

	public void setProposedUnits(String proposedUnits) {
		this.proposedUnits = proposedUnits;
	}

	public void setApprovedUnits(String approvedUnits) {
		this.approvedUnits = approvedUnits;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	
	
	
}
