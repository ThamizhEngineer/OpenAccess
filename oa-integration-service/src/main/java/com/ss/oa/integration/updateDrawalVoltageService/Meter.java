package com.ss.oa.integration.updateDrawalVoltageService;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "M_COMPANY_METER")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class Meter implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="M_COMPANY_SERVICE_ID")
	private String serviceId;
	private String meterNumber;
	private String meterMakeCode;
	@Formula("(SELECT metercodes.VALUE_DESC FROM M_COMPANY_METER meter JOIN v_codes metercodes on meter.METER_MAKE_CODE = metercodes.value_code and metercodes.list_code='METER_MAKE_CODE' WHERE meter.M_COMPANY_SERVICE_ID=M_COMPANY_SERVICE_ID)")
	private String meterMakeName;
	private String accuracyClassCode;
	@Transient
	private String accuracyClassName;
	@Column(name="IS_ABTMETER",columnDefinition = "char")
	private String isAbtMeter;
	@Column(name="MF")
	private String multiplicationFactor;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String modemNumber;
	@Column(columnDefinition = "char") 
	private String enabled ;
	private String meterCt1;
	private String meterCt2;
	private String meterCt3;
    private String meterPt1;
	private String meterPt2;
	private String meterPt3;
	private String importRemarks;
	@Formula("(SELECT service.M_COMPANY_ID FROM M_COMPANY_METER meter JOIN M_COMPANY_SERVICE service ON meter.M_COMPANY_SERVICE_ID = service.ID WHERE meter.M_COMPANY_SERVICE_ID=M_COMPANY_SERVICE_ID)")
	private String companyId;
	@Formula("(SELECT company.NAME FROM M_COMPANY_SERVICE service JOIN M_COMPANY company ON service.M_COMPANY_ID = company.ID JOIN M_COMPANY_METER meter ON service.ID = meter.M_COMPANY_SERVICE_ID WHERE meter.M_COMPANY_SERVICE_ID=M_COMPANY_SERVICE_ID)")
	private String companyName;
//	@Formula("(SELECT service.\"number\" FROM M_COMPANY_METER meter JOIN M_COMPANY_SERVICE service ON meter.M_COMPANY_SERVICE_ID = service.ID WHERE meter.M_COMPANY_SERVICE_ID=M_COMPANY_SERVICE_ID)")
//	private String serviceNumber;
	@Formula("(SELECT service.COMP_SER_TYPE_CODE FROM M_COMPANY_METER meter JOIN M_COMPANY_SERVICE service ON meter.M_COMPANY_SERVICE_ID = service.ID WHERE meter.M_COMPANY_SERVICE_ID=M_COMPANY_SERVICE_ID)")
	private String serviceTypeCode;
	@Formula("(SELECT typecodes.VALUE_DESC FROM M_COMPANY_SERVICE service JOIN v_codes typecodes on service.COMP_SER_TYPE_CODE = typecodes.value_code and typecodes.list_code='SERVICE_TYPE_CODE' WHERE service.ID=M_COMPANY_SERVICE_ID)")
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
			String serviceTypeCode, String serviceTypeName, String ctRatio, String ptRatio) {
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
				+ companyName + ", serviceTypeCode=" + serviceTypeCode + ", serviceTypeName=" + serviceTypeName
				+ ", ctRatio=" + ctRatio + ", ptRatio=" + ptRatio + "]";
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
