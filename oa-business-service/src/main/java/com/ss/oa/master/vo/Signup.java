package com.ss.oa.master.vo;

import java.util.List;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class Signup {
	private String id;
	private String companyName;
	private String companyCode;  // autogenerated
	private String purposeCode,purposeName;    //buyer,seller,trader
	private String registrationNumber,registrationDate,dateOfCommission,orgName,orgId;
	private String substationName,substationId,feederName,feederId,voltageCode,voltageName,tariff,totalCapacity;
	private String htscNumberOld,htscNumber;
	private String isCaptive;  // y or n
	private String userName;
	private String userId;  // for logging
	private String password;  // for logging
	private String powerPlantTypeCode; 
	private String powerPlantTypeName; 
	private String powerPlantName;  //auto-generated  substation name+org name
	private String fuelCode;
	private String fuelName;
	private String noOfGenerators;
	private String generatorModelCode;
	private String generatorModelName;
	private String isREC; //y or n
	private String windPassName,windPassCode;
	private String location,addressLine,village,town,talukCode,talukName,city,districtCode,districtName,stateCode,stateName;
	private String isComplete; //y or n
	private String companyAddress;
	private String applicationDate;
	private String dateOfApproval;
	private String plantClassTypeCode;
	private String plantClassTypeName;
	private String buyerTypeCode;
	private String buyerTypeName;
	private String generatorCapacity;
	private String meterNumber;
	private String meterMakeCode;
	private String meterMakeName;
	private String accuracyClassCode;
	private String accuracyClassName;
	private String isAbtMeter;
	private String multiplicationFactor,modemNumber;
	private String guModel1,guCapacity1,noOfGu1;
	private String guModel2,guCapacity2,noOfGu2;
	private String guModel3,guCapacity3,noOfGu3;
	private String guModel4,guCapacity4,noOfGu4;
	private String guModel5,guCapacity5,noOfGu5;
    private String guModel6,guCapacity6,noOfGu6;
    private String meterCt1,meterCt2,meterCt3;
    private String meterBt1,meterBt2,meterBt3;
    private String surplusEnergyCode;    //BG(banking),STB(sale to board)
	private String turbineSlNo,turbineRotorDia,turbineHubHeight;
	private String isDlmsMeter;
	private String meterCtRatio,meterPtRatio ;
	private String totalGeneratorUnit;
	private String createdBy,createdDate,modifiedBy,modifiedDate;
	private String acceleratedDepreciation;
    private List<TradeRelationship> signupTradeRelationships;
    private List<Generator> genUnits;
    

	public enum SignupPurposeTypes {
		Buyer {
			@Override
			public String toString() {
				return "01";
			}
		},
		Seller {
			@Override
			public String toString() {
				return "02";
			}
		}

	}
//	public enum SurplusEnergyTypes {
//		BG {
//			@Override
//			public String toString() {
//				return "01";
//			}
//		},
//		STB {
//			@Override
//			public String toString() {
//				return "02";
//			}
//		}
//
//	}
	
	
    public Signup() {
    	super();
    }
public Signup(String id, String companyName, String companyCode, String purposeCode, String purposeName,
		String registrationNumber, String registrationDate, String dateOfCommission, String orgName, String orgId,
		String substationName, String substationId, String feederName, String feederId, String voltageCode,
		String voltageName, String tariff, String totalCapacity, String htscNumberOld, String htscNumber,
		String isCaptive, String userName, String userId, String password, String powerPlantTypeCode,
		String powerPlantTypeName, String powerPlantName, String fuelCode, String fuelName, String noOfGenerators,
		String generatorModelCode, String generatorModelName, String isREC, String windPassName, String windPassCode,
		String location, String addressLine, String village, String town, String talukCode, String talukName,
		String city, String districtCode, String districtName, String stateCode, String stateName, String isComplete,
		String companyAddress, String applicationDate, String dateOfApproval, String plantClassTypeCode,
		String plantClassTypeName, String buyerTypeCode, String buyerTypeName, String generatorCapacity,
		String meterNumber, String meterMakeCode, String meterMakeName, String accuracyClassCode,
		String accuracyClassName, String isAbtMeter, String multiplicationFactor, String modemNumber, String guModel1,
		String guCapacity1, String noOfGu1, String guModel2, String guCapacity2, String noOfGu2, String guModel3,
		String guCapacity3, String noOfGu3, String guModel4, String guCapacity4, String noOfGu4, String guModel5,
		String guCapacity5, String noOfGu5, String guModel6, String guCapacity6, String noOfGu6, String meterCt1,
		String meterCt2, String meterCt3, String meterBt1, String meterBt2, String meterBt3, String surplusEnergyCode,
		String turbineSlNo, String turbineRotorDia, String turbineHubHeight, String isDlmsMeter, String meterCtRatio,
		String meterPtRatio, String totalGeneratorUnit, String createdBy, String createdDate, String modifiedBy,
		String modifiedDate, String acceleratedDepreciation, List<TradeRelationship> signupTradeRelationships,
		List<Generator> genUnits) {
	super();
	this.id = id;
	this.companyName = companyName;
	this.companyCode = companyCode;
	this.purposeCode = purposeCode;
	this.purposeName = purposeName;
	this.registrationNumber = registrationNumber;
	this.registrationDate = registrationDate;
	this.dateOfCommission = dateOfCommission;
	this.orgName = orgName;
	this.orgId = orgId;
	this.substationName = substationName;
	this.substationId = substationId;
	this.feederName = feederName;
	this.feederId = feederId;
	this.voltageCode = voltageCode;
	this.voltageName = voltageName;
	this.tariff = tariff;
	this.totalCapacity = totalCapacity;
	this.htscNumberOld = htscNumberOld;
	this.htscNumber = htscNumber;
	this.isCaptive = isCaptive;
	this.userName = userName;
	this.userId = userId;
	this.password = password;
	this.powerPlantTypeCode = powerPlantTypeCode;
	this.powerPlantTypeName = powerPlantTypeName;
	this.powerPlantName = powerPlantName;
	this.fuelCode = fuelCode;
	this.fuelName = fuelName;
	this.noOfGenerators = noOfGenerators;
	this.generatorModelCode = generatorModelCode;
	this.generatorModelName = generatorModelName;
	this.isREC = isREC;
	this.windPassName = windPassName;
	this.windPassCode = windPassCode;
	this.location = location;
	this.addressLine = addressLine;
	this.village = village;
	this.town = town;
	this.talukCode = talukCode;
	this.talukName = talukName;
	this.city = city;
	this.districtCode = districtCode;
	this.districtName = districtName;
	this.stateCode = stateCode;
	this.stateName = stateName;
	this.isComplete = isComplete;
	this.companyAddress = companyAddress;
	this.applicationDate = applicationDate;
	this.dateOfApproval = dateOfApproval;
	this.plantClassTypeCode = plantClassTypeCode;
	this.plantClassTypeName = plantClassTypeName;
	this.buyerTypeCode = buyerTypeCode;
	this.buyerTypeName = buyerTypeName;
	this.generatorCapacity = generatorCapacity;
	this.meterNumber = meterNumber;
	this.meterMakeCode = meterMakeCode;
	this.meterMakeName = meterMakeName;
	this.accuracyClassCode = accuracyClassCode;
	this.accuracyClassName = accuracyClassName;
	this.isAbtMeter = isAbtMeter;
	this.multiplicationFactor = multiplicationFactor;
	this.modemNumber = modemNumber;
	this.guModel1 = guModel1;
	this.guCapacity1 = guCapacity1;
	this.noOfGu1 = noOfGu1;
	this.guModel2 = guModel2;
	this.guCapacity2 = guCapacity2;
	this.noOfGu2 = noOfGu2;
	this.guModel3 = guModel3;
	this.guCapacity3 = guCapacity3;
	this.noOfGu3 = noOfGu3;
	this.guModel4 = guModel4;
	this.guCapacity4 = guCapacity4;
	this.noOfGu4 = noOfGu4;
	this.guModel5 = guModel5;
	this.guCapacity5 = guCapacity5;
	this.noOfGu5 = noOfGu5;
	this.guModel6 = guModel6;
	this.guCapacity6 = guCapacity6;
	this.noOfGu6 = noOfGu6;
	this.meterCt1 = meterCt1;
	this.meterCt2 = meterCt2;
	this.meterCt3 = meterCt3;
	this.meterBt1 = meterBt1;
	this.meterBt2 = meterBt2;
	this.meterBt3 = meterBt3;
	this.surplusEnergyCode = surplusEnergyCode;
	this.turbineSlNo = turbineSlNo;
	this.turbineRotorDia = turbineRotorDia;
	this.turbineHubHeight = turbineHubHeight;
	this.isDlmsMeter = isDlmsMeter;
	this.meterCtRatio = meterCtRatio;
	this.meterPtRatio = meterPtRatio;
	this.totalGeneratorUnit = totalGeneratorUnit;
	this.createdBy = createdBy;
	this.createdDate = createdDate;
	this.modifiedBy = modifiedBy;
	this.modifiedDate = modifiedDate;
	this.acceleratedDepreciation = acceleratedDepreciation;
	this.signupTradeRelationships = signupTradeRelationships;
	this.genUnits = genUnits;
}
@Override
public String toString() {
	return "Signup [id=" + id + ", companyName=" + companyName + ", companyCode=" + companyCode + ", purposeCode="
			+ purposeCode + ", purposeName=" + purposeName + ", registrationNumber=" + registrationNumber
			+ ", registrationDate=" + registrationDate + ", dateOfCommission=" + dateOfCommission + ", orgName="
			+ orgName + ", orgId=" + orgId + ", substationName=" + substationName + ", substationId=" + substationId
			+ ", feederName=" + feederName + ", feederId=" + feederId + ", voltageCode=" + voltageCode
			+ ", voltageName=" + voltageName + ", tariff=" + tariff + ", totalCapacity=" + totalCapacity
			+ ", htscNumberOld=" + htscNumberOld + ", htscNumber=" + htscNumber + ", isCaptive=" + isCaptive
			+ ", userName=" + userName + ", userId=" + userId + ", password=" + password + ", powerPlantTypeCode="
			+ powerPlantTypeCode + ", powerPlantTypeName=" + powerPlantTypeName + ", powerPlantName=" + powerPlantName
			+ ", fuelCode=" + fuelCode + ", fuelName=" + fuelName + ", noOfGenerators=" + noOfGenerators
			+ ", generatorModelCode=" + generatorModelCode + ", generatorModelName=" + generatorModelName + ", isREC="
			+ isREC + ", windPassName=" + windPassName + ", windPassCode=" + windPassCode + ", location=" + location
			+ ", addressLine=" + addressLine + ", village=" + village + ", town=" + town + ", talukCode=" + talukCode
			+ ", talukName=" + talukName + ", city=" + city + ", districtCode=" + districtCode + ", districtName="
			+ districtName + ", stateCode=" + stateCode + ", stateName=" + stateName + ", isComplete=" + isComplete
			+ ", companyAddress=" + companyAddress + ", applicationDate=" + applicationDate + ", dateOfApproval="
			+ dateOfApproval + ", plantClassTypeCode=" + plantClassTypeCode + ", plantClassTypeName="
			+ plantClassTypeName + ", buyerTypeCode=" + buyerTypeCode + ", buyerTypeName=" + buyerTypeName
			+ ", generatorCapacity=" + generatorCapacity + ", meterNumber=" + meterNumber + ", meterMakeCode="
			+ meterMakeCode + ", meterMakeName=" + meterMakeName + ", accuracyClassCode=" + accuracyClassCode
			+ ", accuracyClassName=" + accuracyClassName + ", isAbtMeter=" + isAbtMeter + ", multiplicationFactor="
			+ multiplicationFactor + ", modemNumber=" + modemNumber + ", guModel1=" + guModel1 + ", guCapacity1="
			+ guCapacity1 + ", noOfGu1=" + noOfGu1 + ", guModel2=" + guModel2 + ", guCapacity2=" + guCapacity2
			+ ", noOfGu2=" + noOfGu2 + ", guModel3=" + guModel3 + ", guCapacity3=" + guCapacity3 + ", noOfGu3="
			+ noOfGu3 + ", guModel4=" + guModel4 + ", guCapacity4=" + guCapacity4 + ", noOfGu4=" + noOfGu4
			+ ", guModel5=" + guModel5 + ", guCapacity5=" + guCapacity5 + ", noOfGu5=" + noOfGu5 + ", guModel6="
			+ guModel6 + ", guCapacity6=" + guCapacity6 + ", noOfGu6=" + noOfGu6 + ", meterCt1=" + meterCt1
			+ ", meterCt2=" + meterCt2 + ", meterCt3=" + meterCt3 + ", meterBt1=" + meterBt1 + ", meterBt2=" + meterBt2
			+ ", meterBt3=" + meterBt3 + ", surplusEnergyCode=" + surplusEnergyCode + ", turbineSlNo=" + turbineSlNo
			+ ", turbineRotorDia=" + turbineRotorDia + ", turbineHubHeight=" + turbineHubHeight + ", isDlmsMeter="
			+ isDlmsMeter + ", meterCtRatio=" + meterCtRatio + ", meterPtRatio=" + meterPtRatio
			+ ", totalGeneratorUnit=" + totalGeneratorUnit + ", createdBy=" + createdBy + ", createdDate=" + createdDate
			+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", acceleratedDepreciation="
			+ acceleratedDepreciation + ", signupTradeRelationships=" + signupTradeRelationships + ", genUnits="
			+ genUnits + "]";
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getCompanyCode() {
	return companyCode;
}
public void setCompanyCode(String companyCode) {
	this.companyCode = companyCode;
}
public String getPurposeCode() {
	return purposeCode;
}
public void setPurposeCode(String purposeCode) {
	this.purposeCode = purposeCode;
}
public String getPurposeName() {
	return purposeName;
}
public void setPurposeName(String purposeName) {
	this.purposeName = purposeName;
}
public String getRegistrationNumber() {
	return registrationNumber;
}
public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
}
public String getRegistrationDate() {
	return registrationDate;
}
public void setRegistrationDate(String registrationDate) {
	this.registrationDate = registrationDate;
}
public String getDateOfCommission() {
	return dateOfCommission;
}
public void setDateOfCommission(String dateOfCommission) {
	this.dateOfCommission = dateOfCommission;
}
public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
public String getOrgId() {
	return orgId;
}
public void setOrgId(String orgId) {
	this.orgId = orgId;
}
public String getSubstationName() {
	return substationName;
}
public void setSubstationName(String substationName) {
	this.substationName = substationName;
}
public String getSubstationId() {
	return substationId;
}
public void setSubstationId(String substationId) {
	this.substationId = substationId;
}
public String getFeederName() {
	return feederName;
}
public void setFeederName(String feederName) {
	this.feederName = feederName;
}
public String getFeederId() {
	return feederId;
}
public void setFeederId(String feederId) {
	this.feederId = feederId;
}
public String getVoltageCode() {
	return voltageCode;
}
public void setVoltageCode(String voltageCode) {
	this.voltageCode = voltageCode;
}
public String getVoltageName() {
	return voltageName;
}
public void setVoltageName(String voltageName) {
	this.voltageName = voltageName;
}
public String getTariff() {
	return tariff;
}
public void setTariff(String tariff) {
	this.tariff = tariff;
}
public String getTotalCapacity() {
	return totalCapacity;
}
public void setTotalCapacity(String totalCapacity) {
	this.totalCapacity = totalCapacity;
}
public String getHtscNumberOld() {
	return htscNumberOld;
}
public void setHtscNumberOld(String htscNumberOld) {
	this.htscNumberOld = htscNumberOld;
}
public String getHtscNumber() {
	return htscNumber;
}
public void setHtscNumber(String htscNumber) {
	this.htscNumber = htscNumber;
}
public String getIsCaptive() {
	return isCaptive;
}
public void setIsCaptive(String isCaptive) {
	this.isCaptive = isCaptive;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPowerPlantTypeCode() {
	return powerPlantTypeCode;
}
public void setPowerPlantTypeCode(String powerPlantTypeCode) {
	this.powerPlantTypeCode = powerPlantTypeCode;
}
public String getPowerPlantTypeName() {
	return powerPlantTypeName;
}
public void setPowerPlantTypeName(String powerPlantTypeName) {
	this.powerPlantTypeName = powerPlantTypeName;
}
public String getPowerPlantName() {
	return powerPlantName;
}
public void setPowerPlantName(String powerPlantName) {
	this.powerPlantName = powerPlantName;
}
public String getFuelCode() {
	return fuelCode;
}
public void setFuelCode(String fuelCode) {
	this.fuelCode = fuelCode;
}
public String getFuelName() {
	return fuelName;
}
public void setFuelName(String fuelName) {
	this.fuelName = fuelName;
}
public String getNoOfGenerators() {
	return noOfGenerators;
}
public void setNoOfGenerators(String noOfGenerators) {
	this.noOfGenerators = noOfGenerators;
}
public String getGeneratorModelCode() {
	return generatorModelCode;
}
public void setGeneratorModelCode(String generatorModelCode) {
	this.generatorModelCode = generatorModelCode;
}
public String getGeneratorModelName() {
	return generatorModelName;
}
public void setGeneratorModelName(String generatorModelName) {
	this.generatorModelName = generatorModelName;
}
public String getIsREC() {
	return isREC;
}
public void setIsREC(String isREC) {
	this.isREC = isREC;
}
public String getWindPassName() {
	return windPassName;
}
public void setWindPassName(String windPassName) {
	this.windPassName = windPassName;
}
public String getWindPassCode() {
	return windPassCode;
}
public void setWindPassCode(String windPassCode) {
	this.windPassCode = windPassCode;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getAddressLine() {
	return addressLine;
}
public void setAddressLine(String addressLine) {
	this.addressLine = addressLine;
}
public String getVillage() {
	return village;
}
public void setVillage(String village) {
	this.village = village;
}
public String getTown() {
	return town;
}
public void setTown(String town) {
	this.town = town;
}
public String getTalukCode() {
	return talukCode;
}
public void setTalukCode(String talukCode) {
	this.talukCode = talukCode;
}
public String getTalukName() {
	return talukName;
}
public void setTalukName(String talukName) {
	this.talukName = talukName;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getDistrictCode() {
	return districtCode;
}
public void setDistrictCode(String districtCode) {
	this.districtCode = districtCode;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public String getStateCode() {
	return stateCode;
}
public void setStateCode(String stateCode) {
	this.stateCode = stateCode;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public String getIsComplete() {
	return isComplete;
}
public void setIsComplete(String isComplete) {
	this.isComplete = isComplete;
}
public String getCompanyAddress() {
	return companyAddress;
}
public void setCompanyAddress(String companyAddress) {
	this.companyAddress = companyAddress;
}
public String getApplicationDate() {
	return applicationDate;
}
public void setApplicationDate(String applicationDate) {
	this.applicationDate = applicationDate;
}
public String getDateOfApproval() {
	return dateOfApproval;
}
public void setDateOfApproval(String dateOfApproval) {
	this.dateOfApproval = dateOfApproval;
}
public String getPlantClassTypeCode() {
	return plantClassTypeCode;
}
public void setPlantClassTypeCode(String plantClassTypeCode) {
	this.plantClassTypeCode = plantClassTypeCode;
}
public String getPlantClassTypeName() {
	return plantClassTypeName;
}
public void setPlantClassTypeName(String plantClassTypeName) {
	this.plantClassTypeName = plantClassTypeName;
}
public String getBuyerTypeCode() {
	return buyerTypeCode;
}
public void setBuyerTypeCode(String buyerTypeCode) {
	this.buyerTypeCode = buyerTypeCode;
}
public String getBuyerTypeName() {
	return buyerTypeName;
}
public void setBuyerTypeName(String buyerTypeName) {
	this.buyerTypeName = buyerTypeName;
}
public String getGeneratorCapacity() {
	return generatorCapacity;
}
public void setGeneratorCapacity(String generatorCapacity) {
	this.generatorCapacity = generatorCapacity;
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
public String getModemNumber() {
	return modemNumber;
}
public void setModemNumber(String modemNumber) {
	this.modemNumber = modemNumber;
}
public String getGuModel1() {
	return guModel1;
}
public void setGuModel1(String guModel1) {
	this.guModel1 = guModel1;
}
public String getGuCapacity1() {
	return guCapacity1;
}
public void setGuCapacity1(String guCapacity1) {
	this.guCapacity1 = guCapacity1;
}
public String getNoOfGu1() {
	return noOfGu1;
}
public void setNoOfGu1(String noOfGu1) {
	this.noOfGu1 = noOfGu1;
}
public String getGuModel2() {
	return guModel2;
}
public void setGuModel2(String guModel2) {
	this.guModel2 = guModel2;
}
public String getGuCapacity2() {
	return guCapacity2;
}
public void setGuCapacity2(String guCapacity2) {
	this.guCapacity2 = guCapacity2;
}
public String getNoOfGu2() {
	return noOfGu2;
}
public void setNoOfGu2(String noOfGu2) {
	this.noOfGu2 = noOfGu2;
}
public String getGuModel3() {
	return guModel3;
}
public void setGuModel3(String guModel3) {
	this.guModel3 = guModel3;
}
public String getGuCapacity3() {
	return guCapacity3;
}
public void setGuCapacity3(String guCapacity3) {
	this.guCapacity3 = guCapacity3;
}
public String getNoOfGu3() {
	return noOfGu3;
}
public void setNoOfGu3(String noOfGu3) {
	this.noOfGu3 = noOfGu3;
}
public String getGuModel4() {
	return guModel4;
}
public void setGuModel4(String guModel4) {
	this.guModel4 = guModel4;
}
public String getGuCapacity4() {
	return guCapacity4;
}
public void setGuCapacity4(String guCapacity4) {
	this.guCapacity4 = guCapacity4;
}
public String getNoOfGu4() {
	return noOfGu4;
}
public void setNoOfGu4(String noOfGu4) {
	this.noOfGu4 = noOfGu4;
}
public String getGuModel5() {
	return guModel5;
}
public void setGuModel5(String guModel5) {
	this.guModel5 = guModel5;
}
public String getGuCapacity5() {
	return guCapacity5;
}
public void setGuCapacity5(String guCapacity5) {
	this.guCapacity5 = guCapacity5;
}
public String getNoOfGu5() {
	return noOfGu5;
}
public void setNoOfGu5(String noOfGu5) {
	this.noOfGu5 = noOfGu5;
}
public String getGuModel6() {
	return guModel6;
}
public void setGuModel6(String guModel6) {
	this.guModel6 = guModel6;
}
public String getGuCapacity6() {
	return guCapacity6;
}
public void setGuCapacity6(String guCapacity6) {
	this.guCapacity6 = guCapacity6;
}
public String getNoOfGu6() {
	return noOfGu6;
}
public void setNoOfGu6(String noOfGu6) {
	this.noOfGu6 = noOfGu6;
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
public String getMeterBt1() {
	return meterBt1;
}
public void setMeterBt1(String meterBt1) {
	this.meterBt1 = meterBt1;
}
public String getMeterBt2() {
	return meterBt2;
}
public void setMeterBt2(String meterBt2) {
	this.meterBt2 = meterBt2;
}
public String getMeterBt3() {
	return meterBt3;
}
public void setMeterBt3(String meterBt3) {
	this.meterBt3 = meterBt3;
}
public String getSurplusEnergyCode() {
	return surplusEnergyCode;
}
public void setSurplusEnergyCode(String surplusEnergyCode) {
	this.surplusEnergyCode = surplusEnergyCode;
}
public String getTurbineSlNo() {
	return turbineSlNo;
}
public void setTurbineSlNo(String turbineSlNo) {
	this.turbineSlNo = turbineSlNo;
}
public String getTurbineRotorDia() {
	return turbineRotorDia;
}
public void setTurbineRotorDia(String turbineRotorDia) {
	this.turbineRotorDia = turbineRotorDia;
}
public String getTurbineHubHeight() {
	return turbineHubHeight;
}
public void setTurbineHubHeight(String turbineHubHeight) {
	this.turbineHubHeight = turbineHubHeight;
}
public String getIsDlmsMeter() {
	return isDlmsMeter;
}
public void setIsDlmsMeter(String isDlmsMeter) {
	this.isDlmsMeter = isDlmsMeter;
}
public String getMeterCtRatio() {
	return meterCtRatio;
}
public void setMeterCtRatio(String meterCtRatio) {
	this.meterCtRatio = meterCtRatio;
}
public String getMeterPtRatio() {
	return meterPtRatio;
}
public void setMeterPtRatio(String meterPtRatio) {
	this.meterPtRatio = meterPtRatio;
}
public String getTotalGeneratorUnit() {
	return totalGeneratorUnit;
}
public void setTotalGeneratorUnit(String totalGeneratorUnit) {
	this.totalGeneratorUnit = totalGeneratorUnit;
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
public String getAcceleratedDepreciation() {
	return acceleratedDepreciation;
}
public void setAcceleratedDepreciation(String acceleratedDepreciation) {
	this.acceleratedDepreciation = acceleratedDepreciation;
}
public List<TradeRelationship> getSignupTradeRelationships() {
	return signupTradeRelationships;
}
public void setSignupTradeRelationships(List<TradeRelationship> signupTradeRelationships) {
	this.signupTradeRelationships = signupTradeRelationships;
}
public List<Generator> getGenUnits() {
	return genUnits;
}
public void setGenUnits(List<Generator> genUnits) {
	this.genUnits = genUnits;
}
    
}