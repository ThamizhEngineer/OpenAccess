package com.ss.oa.transaction.vo;

public class CompanyMeterChange {
	
	private String id,code;
	private String companyId, companyCode, companyName;
	private String serviceId,serviceNumber, orgId, orgCode, orgName; 
	private String feederId, feederCode, feederName,substationId, substationCode, substationName;
	private String companyMeterId,meterChangeDate;
	private String oldMeterNumber,newMeterNumber;
	private String oldMeterMakeCode,newMeterMakeCode;
	private String oldAccuracyClassCode,newAccuracyClassCode;
	private String oldIsAbtMeter,newIsAbtMeter;
	private String oldMf,newMf;	
	private String oldModemNumber,newModemNumber;	
	private String oldMeterCt1,newMeterCt1;
	private String oldMeterCt2,newMeterCt2;
	private String oldMeterCt3,newMeterCt3;
	private String oldMeterPt1,newMeterPt1;
	private String oldMeterPt2,newMeterPt2;
	private String oldMeterPt3,newMeterPt3;	
	private String isMeterNumberChange,isMeterSetChange,isModemNumberChange,typeOfMeterChange;
	private String meterNumber,modemNumber;
	private String meterMakeCode;
	private String meterMakeName;
	private String accuracyClassCode;
	private String accuracyClassName;
	private String isAbtMeter;
	private String multiplicationFactor;
	private String meterCt1,meterCt2,meterCt3;
    private String meterPt1,meterPt2,meterPt3;
    private String statusCode,statusName;
	
	public CompanyMeterChange() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyMeterChange(String id, String code, String companyId, String companyCode, String companyName,
			String serviceId, String serviceNumber, String orgId, String orgCode, String orgName, String feederId,
			String feederCode, String feederName, String substationId, String substationCode, String substationName,
			String companyMeterId, String meterChangeDate, String oldMeterNumber, String newMeterNumber,
			String oldMeterMakeCode, String newMeterMakeCode, String oldAccuracyClassCode, String newAccuracyClassCode,
			String oldIsAbtMeter, String newIsAbtMeter, String oldMf, String newMf, String oldModemNumber,
			String newModemNumber, String oldMeterCt1, String newMeterCt1, String oldMeterCt2, String newMeterCt2,
			String oldMeterCt3, String newMeterCt3, String oldMeterPt1, String newMeterPt1, String oldMeterPt2,
			String newMeterPt2, String oldMeterPt3, String newMeterPt3, String isMeterNumberChange,
			String isMeterSetChange, String isModemNumberChange, String typeOfMeterChange, String meterNumber,
			String modemNumber, String meterMakeCode, String meterMakeName, String accuracyClassCode,
			String accuracyClassName, String isAbtMeter, String multiplicationFactor, String meterCt1, String meterCt2,
			String meterCt3, String meterPt1, String meterPt2, String meterPt3, String statusCode, String statusName) {
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
		this.meterChangeDate = meterChangeDate;
		this.oldMeterNumber = oldMeterNumber;
		this.newMeterNumber = newMeterNumber;
		this.oldMeterMakeCode = oldMeterMakeCode;
		this.newMeterMakeCode = newMeterMakeCode;
		this.oldAccuracyClassCode = oldAccuracyClassCode;
		this.newAccuracyClassCode = newAccuracyClassCode;
		this.oldIsAbtMeter = oldIsAbtMeter;
		this.newIsAbtMeter = newIsAbtMeter;
		this.oldMf = oldMf;
		this.newMf = newMf;
		this.oldModemNumber = oldModemNumber;
		this.newModemNumber = newModemNumber;
		this.oldMeterCt1 = oldMeterCt1;
		this.newMeterCt1 = newMeterCt1;
		this.oldMeterCt2 = oldMeterCt2;
		this.newMeterCt2 = newMeterCt2;
		this.oldMeterCt3 = oldMeterCt3;
		this.newMeterCt3 = newMeterCt3;
		this.oldMeterPt1 = oldMeterPt1;
		this.newMeterPt1 = newMeterPt1;
		this.oldMeterPt2 = oldMeterPt2;
		this.newMeterPt2 = newMeterPt2;
		this.oldMeterPt3 = oldMeterPt3;
		this.newMeterPt3 = newMeterPt3;
		this.isMeterNumberChange = isMeterNumberChange;
		this.isMeterSetChange = isMeterSetChange;
		this.isModemNumberChange = isModemNumberChange;
		this.typeOfMeterChange = typeOfMeterChange;
		this.meterNumber = meterNumber;
		this.modemNumber = modemNumber;
		this.meterMakeCode = meterMakeCode;
		this.meterMakeName = meterMakeName;
		this.accuracyClassCode = accuracyClassCode;
		this.accuracyClassName = accuracyClassName;
		this.isAbtMeter = isAbtMeter;
		this.multiplicationFactor = multiplicationFactor;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
		this.statusCode = statusCode;
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "CompanyMeterChange [id=" + id + ", code=" + code + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", serviceId=" + serviceId + ", serviceNumber="
				+ serviceNumber + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName + ", feederId="
				+ feederId + ", feederCode=" + feederCode + ", feederName=" + feederName + ", substationId="
				+ substationId + ", substationCode=" + substationCode + ", substationName=" + substationName
				+ ", companyMeterId=" + companyMeterId + ", meterChangeDate=" + meterChangeDate + ", oldMeterNumber="
				+ oldMeterNumber + ", newMeterNumber=" + newMeterNumber + ", oldMeterMakeCode=" + oldMeterMakeCode
				+ ", newMeterMakeCode=" + newMeterMakeCode + ", oldAccuracyClassCode=" + oldAccuracyClassCode
				+ ", newAccuracyClassCode=" + newAccuracyClassCode + ", oldIsAbtMeter=" + oldIsAbtMeter
				+ ", newIsAbtMeter=" + newIsAbtMeter + ", oldMf=" + oldMf + ", newMf=" + newMf + ", oldModemNumber="
				+ oldModemNumber + ", newModemNumber=" + newModemNumber + ", oldMeterCt1=" + oldMeterCt1
				+ ", newMeterCt1=" + newMeterCt1 + ", oldMeterCt2=" + oldMeterCt2 + ", newMeterCt2=" + newMeterCt2
				+ ", oldMeterCt3=" + oldMeterCt3 + ", newMeterCt3=" + newMeterCt3 + ", oldMeterPt1=" + oldMeterPt1
				+ ", newMeterPt1=" + newMeterPt1 + ", oldMeterPt2=" + oldMeterPt2 + ", newMeterPt2=" + newMeterPt2
				+ ", oldMeterPt3=" + oldMeterPt3 + ", newMeterPt3=" + newMeterPt3 + ", isMeterNumberChange="
				+ isMeterNumberChange + ", isMeterSetChange=" + isMeterSetChange + ", isModemNumberChange="
				+ isModemNumberChange + ", typeOfMeterChange=" + typeOfMeterChange + ", meterNumber=" + meterNumber
				+ ", modemNumber=" + modemNumber + ", meterMakeCode=" + meterMakeCode + ", meterMakeName="
				+ meterMakeName + ", accuracyClassCode=" + accuracyClassCode + ", accuracyClassName="
				+ accuracyClassName + ", isAbtMeter=" + isAbtMeter + ", multiplicationFactor=" + multiplicationFactor
				+ ", meterCt1=" + meterCt1 + ", meterCt2=" + meterCt2 + ", meterCt3=" + meterCt3 + ", meterPt1="
				+ meterPt1 + ", meterPt2=" + meterPt2 + ", meterPt3=" + meterPt3 + ", statusCode=" + statusCode
				+ ", statusName=" + statusName + "]";
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

	public String getMeterChangeDate() {
		return meterChangeDate;
	}

	public String getOldMeterNumber() {
		return oldMeterNumber;
	}

	public String getNewMeterNumber() {
		return newMeterNumber;
	}

	public String getOldMeterMakeCode() {
		return oldMeterMakeCode;
	}

	public String getNewMeterMakeCode() {
		return newMeterMakeCode;
	}

	public String getOldAccuracyClassCode() {
		return oldAccuracyClassCode;
	}

	public String getNewAccuracyClassCode() {
		return newAccuracyClassCode;
	}

	public String getOldIsAbtMeter() {
		return oldIsAbtMeter;
	}

	public String getNewIsAbtMeter() {
		return newIsAbtMeter;
	}

	public String getOldMf() {
		return oldMf;
	}

	public String getNewMf() {
		return newMf;
	}

	public String getOldModemNumber() {
		return oldModemNumber;
	}

	public String getNewModemNumber() {
		return newModemNumber;
	}

	public String getOldMeterCt1() {
		return oldMeterCt1;
	}

	public String getNewMeterCt1() {
		return newMeterCt1;
	}

	public String getOldMeterCt2() {
		return oldMeterCt2;
	}

	public String getNewMeterCt2() {
		return newMeterCt2;
	}

	public String getOldMeterCt3() {
		return oldMeterCt3;
	}

	public String getNewMeterCt3() {
		return newMeterCt3;
	}

	public String getOldMeterPt1() {
		return oldMeterPt1;
	}

	public String getNewMeterPt1() {
		return newMeterPt1;
	}

	public String getOldMeterPt2() {
		return oldMeterPt2;
	}

	public String getNewMeterPt2() {
		return newMeterPt2;
	}

	public String getOldMeterPt3() {
		return oldMeterPt3;
	}

	public String getNewMeterPt3() {
		return newMeterPt3;
	}

	public String getIsMeterNumberChange() {
		return isMeterNumberChange;
	}

	public String getIsMeterSetChange() {
		return isMeterSetChange;
	}

	public String getIsModemNumberChange() {
		return isModemNumberChange;
	}

	public String getTypeOfMeterChange() {
		return typeOfMeterChange;
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

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
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

	public void setMeterChangeDate(String meterChangeDate) {
		this.meterChangeDate = meterChangeDate;
	}

	public void setOldMeterNumber(String oldMeterNumber) {
		this.oldMeterNumber = oldMeterNumber;
	}

	public void setNewMeterNumber(String newMeterNumber) {
		this.newMeterNumber = newMeterNumber;
	}

	public void setOldMeterMakeCode(String oldMeterMakeCode) {
		this.oldMeterMakeCode = oldMeterMakeCode;
	}

	public void setNewMeterMakeCode(String newMeterMakeCode) {
		this.newMeterMakeCode = newMeterMakeCode;
	}

	public void setOldAccuracyClassCode(String oldAccuracyClassCode) {
		this.oldAccuracyClassCode = oldAccuracyClassCode;
	}

	public void setNewAccuracyClassCode(String newAccuracyClassCode) {
		this.newAccuracyClassCode = newAccuracyClassCode;
	}

	public void setOldIsAbtMeter(String oldIsAbtMeter) {
		this.oldIsAbtMeter = oldIsAbtMeter;
	}

	public void setNewIsAbtMeter(String newIsAbtMeter) {
		this.newIsAbtMeter = newIsAbtMeter;
	}

	public void setOldMf(String oldMf) {
		this.oldMf = oldMf;
	}

	public void setNewMf(String newMf) {
		this.newMf = newMf;
	}

	public void setOldModemNumber(String oldModemNumber) {
		this.oldModemNumber = oldModemNumber;
	}

	public void setNewModemNumber(String newModemNumber) {
		this.newModemNumber = newModemNumber;
	}

	public void setOldMeterCt1(String oldMeterCt1) {
		this.oldMeterCt1 = oldMeterCt1;
	}

	public void setNewMeterCt1(String newMeterCt1) {
		this.newMeterCt1 = newMeterCt1;
	}

	public void setOldMeterCt2(String oldMeterCt2) {
		this.oldMeterCt2 = oldMeterCt2;
	}

	public void setNewMeterCt2(String newMeterCt2) {
		this.newMeterCt2 = newMeterCt2;
	}

	public void setOldMeterCt3(String oldMeterCt3) {
		this.oldMeterCt3 = oldMeterCt3;
	}

	public void setNewMeterCt3(String newMeterCt3) {
		this.newMeterCt3 = newMeterCt3;
	}

	public void setOldMeterPt1(String oldMeterPt1) {
		this.oldMeterPt1 = oldMeterPt1;
	}

	public void setNewMeterPt1(String newMeterPt1) {
		this.newMeterPt1 = newMeterPt1;
	}

	public void setOldMeterPt2(String oldMeterPt2) {
		this.oldMeterPt2 = oldMeterPt2;
	}

	public void setNewMeterPt2(String newMeterPt2) {
		this.newMeterPt2 = newMeterPt2;
	}

	public void setOldMeterPt3(String oldMeterPt3) {
		this.oldMeterPt3 = oldMeterPt3;
	}

	public void setNewMeterPt3(String newMeterPt3) {
		this.newMeterPt3 = newMeterPt3;
	}

	public void setIsMeterNumberChange(String isMeterNumberChange) {
		this.isMeterNumberChange = isMeterNumberChange;
	}

	public void setIsMeterSetChange(String isMeterSetChange) {
		this.isMeterSetChange = isMeterSetChange;
	}

	public void setIsModemNumberChange(String isModemNumberChange) {
		this.isModemNumberChange = isModemNumberChange;
	}

	public void setTypeOfMeterChange(String typeOfMeterChange) {
		this.typeOfMeterChange = typeOfMeterChange;
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

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	
	
	

}
