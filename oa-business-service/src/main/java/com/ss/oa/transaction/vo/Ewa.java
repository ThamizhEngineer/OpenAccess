package com.ss.oa.transaction.vo;

import java.util.List;

public class Ewa {
	
	private String id ;
	private String version ;
	private String code ;
	private String ewaApprovalNumber;
	private String  sellerOrgId ;
	private String  sellerOrgName ;
	private String  sellerCompServiceId ;
	private String  sellerCompServiceNumber;
	private String  sellerCompanyName;
	private String  sellerCompanyId;
	private String  sellerCapacity; 
	private String  sellerPowerPlantFuelCode;
	private String  sellerPowerPlantFuelDesc;
	private String  fuelGroupe;
	private String  sellerInjectionVoltageCode;
	private String  sellerInjectionVoltageDesc;
	private String  sellerIsCaptive;
	private String  agreementPeriodCode ;
	private String  agreementPeriodDesc;
	private String  fromDate ,fromMonth,fromYear;
	private String  toDate, toMonth,toYear;
	private String  periodDuration ;
	private String  totalProposedUnits ;
	private String  totalApprovedUnits ;
	private String  totalInjectionPeakUnits ;
	private String  totalInjectionOffPeakUnits ;
	private String  totalDrawalPeakUnits ;
	private String  totalDrawalOffPeakUnits ;
	private String  appliedDate ;
	private String  approvedDate ;
	private String  statusCode ;
	private String  statusDesc ;
	private String  remarks ;
	private String  createdBy ;
	private String  createdDate ;
	private String  modifiedBy ;
	private String  modifiedDate ;
	private String  tEsIntentId,esIntentCode ;
	private String  agreementDate;
	private String flowTypeCode,flowTypeName;
    private List<EwaLine> ewaLines;

	public Ewa() {
		super();
	}

	public Ewa(String id, String version, String code, String ewaApprovalNumber, String sellerOrgId,
			String sellerOrgName, String sellerCompServiceId, String sellerCompServiceNumber, String sellerCompanyName,
			String sellerCompanyId, String sellerCapacity, String sellerPowerPlantFuelCode,
			String sellerPowerPlantFuelDesc, String fuelGroupe, String sellerInjectionVoltageCode,
			String sellerInjectionVoltageDesc, String sellerIsCaptive, String agreementPeriodCode,
			String agreementPeriodDesc, String fromDate, String fromMonth, String fromYear, String toDate,
			String toMonth, String toYear, String periodDuration, String totalProposedUnits, String totalApprovedUnits,
			String totalInjectionPeakUnits, String totalInjectionOffPeakUnits, String totalDrawalPeakUnits,
			String totalDrawalOffPeakUnits, String appliedDate, String approvedDate, String statusCode,
			String statusDesc, String remarks, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String tEsIntentId, String esIntentCode, String agreementDate, String flowTypeCode,
			String flowTypeName, List<EwaLine> ewaLines) {
		super();
		this.id = id;
		this.version = version;
		this.code = code;
		this.ewaApprovalNumber = ewaApprovalNumber;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerCompServiceId = sellerCompServiceId;
		this.sellerCompServiceNumber = sellerCompServiceNumber;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCapacity = sellerCapacity;
		this.sellerPowerPlantFuelCode = sellerPowerPlantFuelCode;
		this.sellerPowerPlantFuelDesc = sellerPowerPlantFuelDesc;
		this.fuelGroupe = fuelGroupe;
		this.sellerInjectionVoltageCode = sellerInjectionVoltageCode;
		this.sellerInjectionVoltageDesc = sellerInjectionVoltageDesc;
		this.sellerIsCaptive = sellerIsCaptive;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodDesc = agreementPeriodDesc;
		this.fromDate = fromDate;
		this.fromMonth = fromMonth;
		this.fromYear = fromYear;
		this.toDate = toDate;
		this.toMonth = toMonth;
		this.toYear = toYear;
		this.periodDuration = periodDuration;
		this.totalProposedUnits = totalProposedUnits;
		this.totalApprovedUnits = totalApprovedUnits;
		this.totalInjectionPeakUnits = totalInjectionPeakUnits;
		this.totalInjectionOffPeakUnits = totalInjectionOffPeakUnits;
		this.totalDrawalPeakUnits = totalDrawalPeakUnits;
		this.totalDrawalOffPeakUnits = totalDrawalOffPeakUnits;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.tEsIntentId = tEsIntentId;
		this.esIntentCode = esIntentCode;
		this.agreementDate = agreementDate;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.ewaLines = ewaLines;
	}

	@Override
	public String toString() {
		return "Ewa [id=" + id + ", version=" + version + ", code=" + code + ", ewaApprovalNumber=" + ewaApprovalNumber
				+ ", sellerOrgId=" + sellerOrgId + ", sellerOrgName=" + sellerOrgName + ", sellerCompServiceId="
				+ sellerCompServiceId + ", sellerCompServiceNumber=" + sellerCompServiceNumber + ", sellerCompanyName="
				+ sellerCompanyName + ", sellerCompanyId=" + sellerCompanyId + ", sellerCapacity=" + sellerCapacity
				+ ", sellerPowerPlantFuelCode=" + sellerPowerPlantFuelCode + ", sellerPowerPlantFuelDesc="
				+ sellerPowerPlantFuelDesc + ", fuelGroupe=" + fuelGroupe + ", sellerInjectionVoltageCode="
				+ sellerInjectionVoltageCode + ", sellerInjectionVoltageDesc=" + sellerInjectionVoltageDesc
				+ ", sellerIsCaptive=" + sellerIsCaptive + ", agreementPeriodCode=" + agreementPeriodCode
				+ ", agreementPeriodDesc=" + agreementPeriodDesc + ", fromDate=" + fromDate + ", fromMonth=" + fromMonth
				+ ", fromYear=" + fromYear + ", toDate=" + toDate + ", toMonth=" + toMonth + ", toYear=" + toYear
				+ ", periodDuration=" + periodDuration + ", totalProposedUnits=" + totalProposedUnits
				+ ", totalApprovedUnits=" + totalApprovedUnits + ", totalInjectionPeakUnits=" + totalInjectionPeakUnits
				+ ", totalInjectionOffPeakUnits=" + totalInjectionOffPeakUnits + ", totalDrawalPeakUnits="
				+ totalDrawalPeakUnits + ", totalDrawalOffPeakUnits=" + totalDrawalOffPeakUnits + ", appliedDate="
				+ appliedDate + ", approvedDate=" + approvedDate + ", statusCode=" + statusCode + ", statusDesc="
				+ statusDesc + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", tEsIntentId=" + tEsIntentId
				+ ", esIntentCode=" + esIntentCode + ", agreementDate=" + agreementDate + ", flowTypeCode="
				+ flowTypeCode + ", flowTypeName=" + flowTypeName + ", ewaLines=" + ewaLines + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEwaApprovalNumber() {
		return ewaApprovalNumber;
	}

	public void setEwaApprovalNumber(String ewaApprovalNumber) {
		this.ewaApprovalNumber = ewaApprovalNumber;
	}

	public String getSellerOrgId() {
		return sellerOrgId;
	}

	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}

	public String getSellerOrgName() {
		return sellerOrgName;
	}

	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}

	public String getSellerCompServiceId() {
		return sellerCompServiceId;
	}

	public void setSellerCompServiceId(String sellerCompServiceId) {
		this.sellerCompServiceId = sellerCompServiceId;
	}

	public String getSellerCompServiceNumber() {
		return sellerCompServiceNumber;
	}

	public void setSellerCompServiceNumber(String sellerCompServiceNumber) {
		this.sellerCompServiceNumber = sellerCompServiceNumber;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public String getSellerCapacity() {
		return sellerCapacity;
	}

	public void setSellerCapacity(String sellerCapacity) {
		this.sellerCapacity = sellerCapacity;
	}

	public String getSellerPowerPlantFuelCode() {
		return sellerPowerPlantFuelCode;
	}

	public void setSellerPowerPlantFuelCode(String sellerPowerPlantFuelCode) {
		this.sellerPowerPlantFuelCode = sellerPowerPlantFuelCode;
	}

	public String getSellerPowerPlantFuelDesc() {
		return sellerPowerPlantFuelDesc;
	}

	public void setSellerPowerPlantFuelDesc(String sellerPowerPlantFuelDesc) {
		this.sellerPowerPlantFuelDesc = sellerPowerPlantFuelDesc;
	}

	public String getFuelGroupe() {
		return fuelGroupe;
	}

	public void setFuelGroupe(String fuelGroupe) {
		this.fuelGroupe = fuelGroupe;
	}

	public String getSellerInjectionVoltageCode() {
		return sellerInjectionVoltageCode;
	}

	public void setSellerInjectionVoltageCode(String sellerInjectionVoltageCode) {
		this.sellerInjectionVoltageCode = sellerInjectionVoltageCode;
	}

	public String getSellerInjectionVoltageDesc() {
		return sellerInjectionVoltageDesc;
	}

	public void setSellerInjectionVoltageDesc(String sellerInjectionVoltageDesc) {
		this.sellerInjectionVoltageDesc = sellerInjectionVoltageDesc;
	}

	public String getSellerIsCaptive() {
		return sellerIsCaptive;
	}

	public void setSellerIsCaptive(String sellerIsCaptive) {
		this.sellerIsCaptive = sellerIsCaptive;
	}

	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}

	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}

	public String getAgreementPeriodDesc() {
		return agreementPeriodDesc;
	}

	public void setAgreementPeriodDesc(String agreementPeriodDesc) {
		this.agreementPeriodDesc = agreementPeriodDesc;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public String getTotalProposedUnits() {
		return totalProposedUnits;
	}

	public void setTotalProposedUnits(String totalProposedUnits) {
		this.totalProposedUnits = totalProposedUnits;
	}

	public String getTotalApprovedUnits() {
		return totalApprovedUnits;
	}

	public void setTotalApprovedUnits(String totalApprovedUnits) {
		this.totalApprovedUnits = totalApprovedUnits;
	}

	public String getTotalInjectionPeakUnits() {
		return totalInjectionPeakUnits;
	}

	public void setTotalInjectionPeakUnits(String totalInjectionPeakUnits) {
		this.totalInjectionPeakUnits = totalInjectionPeakUnits;
	}

	public String getTotalInjectionOffPeakUnits() {
		return totalInjectionOffPeakUnits;
	}

	public void setTotalInjectionOffPeakUnits(String totalInjectionOffPeakUnits) {
		this.totalInjectionOffPeakUnits = totalInjectionOffPeakUnits;
	}

	public String getTotalDrawalPeakUnits() {
		return totalDrawalPeakUnits;
	}

	public void setTotalDrawalPeakUnits(String totalDrawalPeakUnits) {
		this.totalDrawalPeakUnits = totalDrawalPeakUnits;
	}

	public String getTotalDrawalOffPeakUnits() {
		return totalDrawalOffPeakUnits;
	}

	public void setTotalDrawalOffPeakUnits(String totalDrawalOffPeakUnits) {
		this.totalDrawalOffPeakUnits = totalDrawalOffPeakUnits;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String gettEsIntentId() {
		return tEsIntentId;
	}

	public void settEsIntentId(String tEsIntentId) {
		this.tEsIntentId = tEsIntentId;
	}

	public String getEsIntentCode() {
		return esIntentCode;
	}

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public List<EwaLine> getEwaLines() {
		return ewaLines;
	}

	public void setEwaLines(List<EwaLine> ewaLines) {
		this.ewaLines = ewaLines;
	}

	

	
	}

	
