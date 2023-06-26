package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Meter{

	private String id;
	private String serviceId;
	private String meterNumber;
	private String meterMakeCode;
	private String meterMakeName;
	private String accuracyClassCode;
	private String accuracyClassName;
	private String isAbtMeter;
	private String multiplicationFactor;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String modemNumber;
	private String enabled ;
	private String meterCt1;
	private String meterCt2;
	private String meterCt3;
    private String meterPt1;
	private String meterPt2;
	private String meterPt3;
	private String importRemarks;
	private String companyId;
	private String companyName;
	private String serviceNumber;
	private String serviceTypeCode;
	private String serviceTypeName;
	private String ctRatio;
	private String ptRatio;

	public Meter() {
		super();
	}

	public Meter(String id, String serviceId, String meterNumber, String meterMakeCode, String meterMakeName,
			String accuracyClassCode, String accuracyClassName, String isAbtMeter, String multiplicationFactor,
			String remarks, String createdBy, LocalDate createdDate, String modifiedBy, LocalDate modifiedDate,
			String modemNumber, String enabled, String meterCt1, String meterCt2, String meterCt3, String meterPt1,
			String meterPt2, String meterPt3, String importRemarks, String companyId, String companyName,
			String serviceNumber, String serviceTypeCode, String serviceTypeName, String ctRatio, String ptRatio) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.meterNumber = meterNumber;
		this.meterMakeCode = meterMakeCode;
		this.meterMakeName = meterMakeName;
		this.accuracyClassCode = accuracyClassCode;
		this.accuracyClassName = accuracyClassName;
		this.isAbtMeter = isAbtMeter;
		this.multiplicationFactor = multiplicationFactor;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.modemNumber = modemNumber;
		this.enabled = enabled;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
		this.importRemarks = importRemarks;
		this.companyId = companyId;
		this.companyName = companyName;
		this.serviceNumber = serviceNumber;
		this.serviceTypeCode = serviceTypeCode;
		this.serviceTypeName = serviceTypeName;
		this.ctRatio = ctRatio;
		this.ptRatio = ptRatio;
	}

	@Override
	public String toString() {
		return "Meter [id=" + id + ", serviceId=" + serviceId + ", meterNumber=" + meterNumber + ", meterMakeCode="
				+ meterMakeCode + ", meterMakeName=" + meterMakeName + ", accuracyClassCode=" + accuracyClassCode
				+ ", accuracyClassName=" + accuracyClassName + ", isAbtMeter=" + isAbtMeter + ", multiplicationFactor="
				+ multiplicationFactor + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", modemNumber="
				+ modemNumber + ", enabled=" + enabled + ", meterCt1=" + meterCt1 + ", meterCt2=" + meterCt2
				+ ", meterCt3=" + meterCt3 + ", meterPt1=" + meterPt1 + ", meterPt2=" + meterPt2 + ", meterPt3="
				+ meterPt3 + ", importRemarks=" + importRemarks + ", companyId=" + companyId + ", companyName="
				+ companyName + ", serviceNumber=" + serviceNumber + ", serviceTypeCode=" + serviceTypeCode
				+ ", serviceTypeName=" + serviceTypeName + ", ctRatio=" + ctRatio + ", ptRatio=" + ptRatio + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getMeterMakeCode() {
		return meterMakeCode;
	}

	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
	}

	public String getMeterMakeName() {
		return meterMakeName;
	}

	public void setMeterMakeName(String meterMakeName) {
		this.meterMakeName = meterMakeName;
	}

	public String getAccuracyClassCode() {
		return accuracyClassCode;
	}

	public void setAccuracyClassCode(String accuracyClassCode) {
		this.accuracyClassCode = accuracyClassCode;
	}

	public String getAccuracyClassName() {
		return accuracyClassName;
	}

	public void setAccuracyClassName(String accuracyClassName) {
		this.accuracyClassName = accuracyClassName;
	}

	public String getIsAbtMeter() {
		return isAbtMeter;
	}

	public void setIsAbtMeter(String isAbtMeter) {
		this.isAbtMeter = isAbtMeter;
	}

	public String getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(String multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
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

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModemNumber() {
		return modemNumber;
	}

	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getMeterCt1() {
		return meterCt1;
	}

	public void setMeterCt1(String meterCt1) {
		this.meterCt1 = meterCt1;
	}

	public String getMeterCt2() {
		return meterCt2;
	}

	public void setMeterCt2(String meterCt2) {
		this.meterCt2 = meterCt2;
	}

	public String getMeterCt3() {
		return meterCt3;
	}

	public void setMeterCt3(String meterCt3) {
		this.meterCt3 = meterCt3;
	}

	public String getMeterPt1() {
		return meterPt1;
	}

	public void setMeterPt1(String meterPt1) {
		this.meterPt1 = meterPt1;
	}

	public String getMeterPt2() {
		return meterPt2;
	}

	public void setMeterPt2(String meterPt2) {
		this.meterPt2 = meterPt2;
	}

	public String getMeterPt3() {
		return meterPt3;
	}

	public void setMeterPt3(String meterPt3) {
		this.meterPt3 = meterPt3;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
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

	public String getCtRatio() {
		return ctRatio;
	}

	public void setCtRatio(String ctRatio) {
		this.ctRatio = ctRatio;
	}

	public String getPtRatio() {
		return ptRatio;
	}

	public void setPtRatio(String ptRatio) {
		this.ptRatio = ptRatio;
	}

}
