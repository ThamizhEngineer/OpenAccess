package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Meter {
	
	private String id;
	private String serviceId;
	private String meterNumber,modemNumber;
	private String meterMakeCode;
	private String meterMakeName;
	private String accuracyClassCode;
	private String accuracyClassName;
	private String isAbtMeter;
	private String multiplicationFactor;
	private String companyId;
	private String companyName;
	private String serviceNumber;
	private String serviceTypeCode;
	private String serviceTypeName;
	private String enabled ;
	private String meterCt1,meterCt2,meterCt3;
    private String meterPt1,meterPt2,meterPt3;
	  
	
	public Meter() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Meter(String id, String serviceId, String meterNumber, String modemNumber, String meterMakeCode,
			String meterMakeName, String accuracyClassCode, String accuracyClassName, String isAbtMeter,
			String multiplicationFactor, String companyId, String companyName, String serviceNumber,
			String serviceTypeCode, String serviceTypeName, String enabled, String meterCt1, String meterCt2,
			String meterCt3, String meterPt1, String meterPt2, String meterPt3) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.meterNumber = meterNumber;
		this.modemNumber = modemNumber;
		this.meterMakeCode = meterMakeCode;
		this.meterMakeName = meterMakeName;
		this.accuracyClassCode = accuracyClassCode;
		this.accuracyClassName = accuracyClassName;
		this.isAbtMeter = isAbtMeter;
		this.multiplicationFactor = multiplicationFactor;
		this.companyId = companyId;
		this.companyName = companyName;
		this.serviceNumber = serviceNumber;
		this.serviceTypeCode = serviceTypeCode;
		this.serviceTypeName = serviceTypeName;
		this.enabled = enabled;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
	}


	@Override
	public String toString() {
		return "Meter [id=" + id + ", serviceId=" + serviceId + ", meterNumber=" + meterNumber + ", modemNumber="
				+ modemNumber + ", meterMakeCode=" + meterMakeCode + ", meterMakeName=" + meterMakeName
				+ ", accuracyClassCode=" + accuracyClassCode + ", accuracyClassName=" + accuracyClassName
				+ ", isAbtMeter=" + isAbtMeter + ", multiplicationFactor=" + multiplicationFactor + ", companyId="
				+ companyId + ", companyName=" + companyName + ", serviceNumber=" + serviceNumber + ", serviceTypeCode="
				+ serviceTypeCode + ", serviceTypeName=" + serviceTypeName + ", enabled=" + enabled + ", meterCt1="
				+ meterCt1 + ", meterCt2=" + meterCt2 + ", meterCt3=" + meterCt3 + ", meterPt1=" + meterPt1
				+ ", meterPt2=" + meterPt2 + ", meterPt3=" + meterPt3 + "]";
	}


	public String getId() {
		return id;
	}


	public String getServiceId() {
		return serviceId;
	}


	public String getMeterNumber() {
		return meterNumber;
	}


	public String getModemNumber() {
		return modemNumber;
	}


	public String getMeterMakeCode() {
		return meterMakeCode;
	}


	public String getMeterMakeName() {
		return meterMakeName;
	}


	public String getAccuracyClassCode() {
		return accuracyClassCode;
	}


	public String getAccuracyClassName() {
		return accuracyClassName;
	}


	public String getIsAbtMeter() {
		return isAbtMeter;
	}


	public String getMultiplicationFactor() {
		return multiplicationFactor;
	}


	public String getCompanyId() {
		return companyId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public String getServiceNumber() {
		return serviceNumber;
	}


	public String getServiceTypeCode() {
		return serviceTypeCode;
	}


	public String getServiceTypeName() {
		return serviceTypeName;
	}


	public String getEnabled() {
		return enabled;
	}


	public String getMeterCt1() {
		return meterCt1;
	}


	public String getMeterCt2() {
		return meterCt2;
	}


	public String getMeterCt3() {
		return meterCt3;
	}


	public String getMeterPt1() {
		return meterPt1;
	}


	public String getMeterPt2() {
		return meterPt2;
	}


	public String getMeterPt3() {
		return meterPt3;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}


	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}


	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
	}


	public void setMeterMakeName(String meterMakeName) {
		this.meterMakeName = meterMakeName;
	}


	public void setAccuracyClassCode(String accuracyClassCode) {
		this.accuracyClassCode = accuracyClassCode;
	}


	public void setAccuracyClassName(String accuracyClassName) {
		this.accuracyClassName = accuracyClassName;
	}


	public void setIsAbtMeter(String isAbtMeter) {
		this.isAbtMeter = isAbtMeter;
	}


	public void setMultiplicationFactor(String multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}


	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}


	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public void setMeterCt1(String meterCt1) {
		this.meterCt1 = meterCt1;
	}


	public void setMeterCt2(String meterCt2) {
		this.meterCt2 = meterCt2;
	}


	public void setMeterCt3(String meterCt3) {
		this.meterCt3 = meterCt3;
	}


	public void setMeterPt1(String meterPt1) {
		this.meterPt1 = meterPt1;
	}


	public void setMeterPt2(String meterPt2) {
		this.meterPt2 = meterPt2;
	}


	public void setMeterPt3(String meterPt3) {
		this.meterPt3 = meterPt3;
	}



	
}
