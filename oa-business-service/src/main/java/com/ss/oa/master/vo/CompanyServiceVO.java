package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class CompanyServiceVO {
	
	//companyCode, companyName,  orgCode, orgName, feederCode, feederName,substationId, substationCode, substationName are for display purpose only and not stored
	private String id, typeCode,typeName, code, companyId, companyCode, companyName, number, orgId, orgCode, orgName, capacity,accuracyClassCode,meterNumber,isAbtMeter,meterMakeCode,
	feederId, feederCode, feederName,substationId, substationCode, substationName,  refNumber, voltageCode,voltageName, tariff, enabled,multiplicationFactor,modemNumber,fuelTypeCode,fuelTypeName;
	private String type,bankingServiceId,bankingServiceNumber,tlServiceId,tlServiceNumber,dlServiceId,dlServiceNumber,unadjustedServiceId,unadjustedServiceNumber;
	private String meterCt1,meterCt2,meterCt3;
    private String meterPt1,meterPt2,meterPt3;
	public enum ServiceTypes {

		Banking {
			@Override
			public String toString() {
				return "01";
			}
		},
		Buyer {
			@Override
			public String toString() {
				return "02";
			}
		},
		Seller {
			@Override
			public String toString() {
				return "03";
			}
		}

	}
	
	public static void main(String[] args) {
		System.out.println(ServiceTypes.Banking); 
		System.out.println(ServiceTypes.Buyer); 
		if("02".equals(Signup.SignupPurposeTypes.Seller.toString())) {
			System.out.println("correct");
		}else

			System.out.println("wrong");
	}
	
	public CompanyServiceVO() {
	}

	public CompanyServiceVO(String id, String typeCode, String typeName, String code, String companyId,
			String companyCode, String companyName, String number, String orgId, String orgCode, String orgName,
			String capacity, String accuracyClassCode, String meterNumber, String isAbtMeter, String meterMakeCode,
			String feederId, String feederCode, String feederName, String substationId, String substationCode,
			String substationName, String refNumber, String voltageCode, String voltageName, String tariff,
			String enabled, String multiplicationFactor, String modemNumber, String fuelTypeCode, String fuelTypeName,
			String type, String bankingServiceId, String bankingServiceNumber, String tlServiceId,
			String tlServiceNumber, String dlServiceId, String dlServiceNumber, String unadjustedServiceId,
			String unadjustedServiceNumber, String meterCt1, String meterCt2, String meterCt3, String meterPt1,
			String meterPt2, String meterPt3) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.code = code;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.number = number;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.capacity = capacity;
		this.accuracyClassCode = accuracyClassCode;
		this.meterNumber = meterNumber;
		this.isAbtMeter = isAbtMeter;
		this.meterMakeCode = meterMakeCode;
		this.feederId = feederId;
		this.feederCode = feederCode;
		this.feederName = feederName;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.substationName = substationName;
		this.refNumber = refNumber;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.tariff = tariff;
		this.enabled = enabled;
		this.multiplicationFactor = multiplicationFactor;
		this.modemNumber = modemNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.type = type;
		this.bankingServiceId = bankingServiceId;
		this.bankingServiceNumber = bankingServiceNumber;
		this.tlServiceId = tlServiceId;
		this.tlServiceNumber = tlServiceNumber;
		this.dlServiceId = dlServiceId;
		this.dlServiceNumber = dlServiceNumber;
		this.unadjustedServiceId = unadjustedServiceId;
		this.unadjustedServiceNumber = unadjustedServiceNumber;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
	}

	@Override
	public String toString() {
		return "CompanyServiceVO [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + ", code=" + code
				+ ", companyId=" + companyId + ", companyCode=" + companyCode + ", companyName=" + companyName
				+ ", number=" + number + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", capacity=" + capacity + ", accuracyClassCode=" + accuracyClassCode + ", meterNumber=" + meterNumber
				+ ", isAbtMeter=" + isAbtMeter + ", meterMakeCode=" + meterMakeCode + ", feederId=" + feederId
				+ ", feederCode=" + feederCode + ", feederName=" + feederName + ", substationId=" + substationId
				+ ", substationCode=" + substationCode + ", substationName=" + substationName + ", refNumber="
				+ refNumber + ", voltageCode=" + voltageCode + ", voltageName=" + voltageName + ", tariff=" + tariff
				+ ", enabled=" + enabled + ", multiplicationFactor=" + multiplicationFactor + ", modemNumber="
				+ modemNumber + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", type=" + type
				+ ", bankingServiceId=" + bankingServiceId + ", bankingServiceNumber=" + bankingServiceNumber
				+ ", tlServiceId=" + tlServiceId + ", tlServiceNumber=" + tlServiceNumber + ", dlServiceId="
				+ dlServiceId + ", dlServiceNumber=" + dlServiceNumber + ", unadjustedServiceId=" + unadjustedServiceId
				+ ", unadjustedServiceNumber=" + unadjustedServiceNumber + ", meterCt1=" + meterCt1 + ", meterCt2="
				+ meterCt2 + ", meterCt3=" + meterCt3 + ", meterPt1=" + meterPt1 + ", meterPt2=" + meterPt2
				+ ", meterPt3=" + meterPt3 + "]";
	}

	public String getId() {
		return id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeName() {
		return typeName;
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

	public String getNumber() {
		return number;
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

	public String getCapacity() {
		return capacity;
	}

	public String getAccuracyClassCode() {
		return accuracyClassCode;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public String getIsAbtMeter() {
		return isAbtMeter;
	}

	public String getMeterMakeCode() {
		return meterMakeCode;
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

	public String getRefNumber() {
		return refNumber;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public String getVoltageName() {
		return voltageName;
	}

	public String getTariff() {
		return tariff;
	}

	public String getEnabled() {
		return enabled;
	}

	public String getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public String getModemNumber() {
		return modemNumber;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public String getType() {
		return type;
	}

	public String getBankingServiceId() {
		return bankingServiceId;
	}

	public String getBankingServiceNumber() {
		return bankingServiceNumber;
	}

	public String getTlServiceId() {
		return tlServiceId;
	}

	public String getTlServiceNumber() {
		return tlServiceNumber;
	}

	public String getDlServiceId() {
		return dlServiceId;
	}

	public String getDlServiceNumber() {
		return dlServiceNumber;
	}

	public String getUnadjustedServiceId() {
		return unadjustedServiceId;
	}

	public String getUnadjustedServiceNumber() {
		return unadjustedServiceNumber;
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

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public void setNumber(String number) {
		this.number = number;
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

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public void setAccuracyClassCode(String accuracyClassCode) {
		this.accuracyClassCode = accuracyClassCode;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public void setIsAbtMeter(String isAbtMeter) {
		this.isAbtMeter = isAbtMeter;
	}

	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
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

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public void setMultiplicationFactor(String multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}

	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBankingServiceId(String bankingServiceId) {
		this.bankingServiceId = bankingServiceId;
	}

	public void setBankingServiceNumber(String bankingServiceNumber) {
		this.bankingServiceNumber = bankingServiceNumber;
	}

	public void setTlServiceId(String tlServiceId) {
		this.tlServiceId = tlServiceId;
	}

	public void setTlServiceNumber(String tlServiceNumber) {
		this.tlServiceNumber = tlServiceNumber;
	}

	public void setDlServiceId(String dlServiceId) {
		this.dlServiceId = dlServiceId;
	}

	public void setDlServiceNumber(String dlServiceNumber) {
		this.dlServiceNumber = dlServiceNumber;
	}

	public void setUnadjustedServiceId(String unadjustedServiceId) {
		this.unadjustedServiceId = unadjustedServiceId;
	}

	public void setUnadjustedServiceNumber(String unadjustedServiceNumber) {
		this.unadjustedServiceNumber = unadjustedServiceNumber;
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
