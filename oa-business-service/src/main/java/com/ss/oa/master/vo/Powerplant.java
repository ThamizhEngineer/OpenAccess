package com.ss.oa.master.vo;

import java.util.List;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class Powerplant {
	  String id;
	  String isPrimary ; // Y or N
	  String version;
	  String code;
	  String name;
	  String plantTypeCode;
	  String plantTypeName;  // for display purpose - not stored
	  String fuelTypeCode;
	  String fuelTypeName;  // for display purpose - not stored
	  String orgId; // for reference
	  String orgCode; 	// for display purpose - not stored
	  String orgName;   // for display purpose - not stored
	  String serviceId ; // for reference
	  String serviceNumber ; // for display purpose - not stored
	  String gcId;       //for reference
	  String companyId; // for display purpose - not stored 
	  String companyCode; // for display purpose - not stored 
	  String companyName; // for display purpose - not stored 
	  String totalCapacity;
	  String subStationId;
	  String subStationCode;  // for display purpose - not stored
	  String subStationName;  // for display purpose - not stored
	  String subStationVoltage; // for display purpose - not stored
	  String interfaceVoltagePhase;
	  String interfaceVoltageFrequency;
	  String alreadyHtSupply;   //not needed
	  String CommissionDate;  
	  String applicationDate;
	  String approvalDate;
	  String purpose;  
	  String status;   // Default - Active
	  String enabled;   // Y or N
	  //PostalAddress fields
	  String line1;
	  String city;
	  String stateCode;
	  String stateName; // for display purpose - not stored
	  String pinCode; 
	  String village;
	  String town;
	  String talukCode;
	  String talukName; // for display purpose - not stored
	  String districtCode;
	  String districtName; // for display purpose - not stored
	  //PhysicalLocation fields
	  String plSfNo;
	  String plVillage;
	  String plTown;
	  String plTalukCode;
	  String plTalukName; // for display purpose - not stored
	  String plDistrictCode;
	  String plDistrictName; // for display purpose - not stored
	  String windPassCode;
	  String windPassName; // for display purpose - not stored
	  String windZoneAreaCode;
	  String windZoneAreaName; // for display purpose - not stored
	  List<Generator> generators;
	  List<Meter> meters;
	  
	public Powerplant() {
		super();
	}

	public Powerplant(String id, String isPrimary, String version, String code, String name, String plantTypeCode,
			String plantTypeName, String fuelTypeCode, String fuelTypeName, String orgId, String orgCode,
			String orgName, String serviceId, String serviceNumber, String gcId, String companyId, String companyCode,
			String companyName, String totalCapacity, String subStationId, String subStationCode, String subStationName,
			String subStationVoltage, String interfaceVoltagePhase, String interfaceVoltageFrequency,
			String alreadyHtSupply, String commissionDate, String applicationDate, String approvalDate, String purpose,
			String status, String enabled, String line1, String city, String stateCode, String stateName,
			String pinCode, String village, String town, String talukCode, String talukName, String districtCode,
			String districtName, String plSfNo, String plVillage, String plTown, String plTalukCode, String plTalukName,
			String plDistrictCode, String plDistrictName, String windPassCode, String windPassName,
			String windZoneAreaCode, String windZoneAreaName, List<Generator> generators, List<Meter> meters) {
		super();
		this.id = id;
		this.isPrimary = isPrimary;
		this.version = version;
		this.code = code;
		this.name = name;
		this.plantTypeCode = plantTypeCode;
		this.plantTypeName = plantTypeName;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.serviceId = serviceId;
		this.serviceNumber = serviceNumber;
		this.gcId = gcId;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.totalCapacity = totalCapacity;
		this.subStationId = subStationId;
		this.subStationCode = subStationCode;
		this.subStationName = subStationName;
		this.subStationVoltage = subStationVoltage;
		this.interfaceVoltagePhase = interfaceVoltagePhase;
		this.interfaceVoltageFrequency = interfaceVoltageFrequency;
		this.alreadyHtSupply = alreadyHtSupply;
		CommissionDate = commissionDate;
		this.applicationDate = applicationDate;
		this.approvalDate = approvalDate;
		this.purpose = purpose;
		this.status = status;
		this.enabled = enabled;
		this.line1 = line1;
		this.city = city;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.pinCode = pinCode;
		this.village = village;
		this.town = town;
		this.talukCode = talukCode;
		this.talukName = talukName;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.plSfNo = plSfNo;
		this.plVillage = plVillage;
		this.plTown = plTown;
		this.plTalukCode = plTalukCode;
		this.plTalukName = plTalukName;
		this.plDistrictCode = plDistrictCode;
		this.plDistrictName = plDistrictName;
		this.windPassCode = windPassCode;
		this.windPassName = windPassName;
		this.windZoneAreaCode = windZoneAreaCode;
		this.windZoneAreaName = windZoneAreaName;
		this.generators = generators;
		this.meters = meters;
	}

	@Override
	public String toString() {
		return "Powerplant [id=" + id + ", isPrimary=" + isPrimary + ", version=" + version + ", code=" + code
				+ ", name=" + name + ", plantTypeCode=" + plantTypeCode + ", plantTypeName=" + plantTypeName
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", orgId=" + orgId
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + ", serviceId=" + serviceId + ", serviceNumber="
				+ serviceNumber + ", gcId=" + gcId + ", companyId=" + companyId + ", companyCode=" + companyCode
				+ ", companyName=" + companyName + ", totalCapacity=" + totalCapacity + ", subStationId=" + subStationId
				+ ", subStationCode=" + subStationCode + ", subStationName=" + subStationName + ", subStationVoltage="
				+ subStationVoltage + ", interfaceVoltagePhase=" + interfaceVoltagePhase
				+ ", interfaceVoltageFrequency=" + interfaceVoltageFrequency + ", alreadyHtSupply=" + alreadyHtSupply
				+ ", CommissionDate=" + CommissionDate + ", applicationDate=" + applicationDate + ", approvalDate="
				+ approvalDate + ", purpose=" + purpose + ", status=" + status + ", enabled=" + enabled + ", line1="
				+ line1 + ", city=" + city + ", stateCode=" + stateCode + ", stateName=" + stateName + ", pinCode="
				+ pinCode + ", village=" + village + ", town=" + town + ", talukCode=" + talukCode + ", talukName="
				+ talukName + ", districtCode=" + districtCode + ", districtName=" + districtName + ", plSfNo=" + plSfNo
				+ ", plVillage=" + plVillage + ", plTown=" + plTown + ", plTalukCode=" + plTalukCode + ", plTalukName="
				+ plTalukName + ", plDistrictCode=" + plDistrictCode + ", plDistrictName=" + plDistrictName
				+ ", windPassCode=" + windPassCode + ", windPassName=" + windPassName + ", windZoneAreaCode="
				+ windZoneAreaCode + ", windZoneAreaName=" + windZoneAreaName + ", generators=" + generators
				+ ", meters=" + meters + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public String getId() {
		return id;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public String getVersion() {
		return version;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getPlantTypeCode() {
		return plantTypeCode;
	}

	public String getPlantTypeName() {
		return plantTypeName;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
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

	public String getServiceId() {
		return serviceId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public String getGcId() {
		return gcId;
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

	public String getTotalCapacity() {
		return totalCapacity;
	}

	public String getSubStationId() {
		return subStationId;
	}

	public String getSubStationCode() {
		return subStationCode;
	}

	public String getSubStationName() {
		return subStationName;
	}

	public String getSubStationVoltage() {
		return subStationVoltage;
	}

	public String getInterfaceVoltagePhase() {
		return interfaceVoltagePhase;
	}

	public String getInterfaceVoltageFrequency() {
		return interfaceVoltageFrequency;
	}

	public String getAlreadyHtSupply() {
		return alreadyHtSupply;
	}

	public String getCommissionDate() {
		return CommissionDate;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getStatus() {
		return status;
	}

	public String getEnabled() {
		return enabled;
	}

	public String getLine1() {
		return line1;
	}

	public String getCity() {
		return city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public String getPinCode() {
		return pinCode;
	}

	public String getVillage() {
		return village;
	}

	public String getTown() {
		return town;
	}

	public String getTalukCode() {
		return talukCode;
	}

	public String getTalukName() {
		return talukName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public String getPlSfNo() {
		return plSfNo;
	}

	public String getPlVillage() {
		return plVillage;
	}

	public String getPlTown() {
		return plTown;
	}

	public String getPlTalukCode() {
		return plTalukCode;
	}

	public String getPlTalukName() {
		return plTalukName;
	}

	public String getPlDistrictCode() {
		return plDistrictCode;
	}

	public String getPlDistrictName() {
		return plDistrictName;
	}

	public String getWindPassCode() {
		return windPassCode;
	}

	public String getWindPassName() {
		return windPassName;
	}

	public String getWindZoneAreaCode() {
		return windZoneAreaCode;
	}

	public String getWindZoneAreaName() {
		return windZoneAreaName;
	}

	public List<Generator> getGenerators() {
		return generators;
	}

	public List<Meter> getMeters() {
		return meters;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlantTypeCode(String plantTypeCode) {
		this.plantTypeCode = plantTypeCode;
	}

	public void setPlantTypeName(String plantTypeName) {
		this.plantTypeName = plantTypeName;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
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

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public void setGcId(String gcId) {
		this.gcId = gcId;
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

	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public void setSubStationId(String subStationId) {
		this.subStationId = subStationId;
	}

	public void setSubStationCode(String subStationCode) {
		this.subStationCode = subStationCode;
	}

	public void setSubStationName(String subStationName) {
		this.subStationName = subStationName;
	}

	public void setSubStationVoltage(String subStationVoltage) {
		this.subStationVoltage = subStationVoltage;
	}

	public void setInterfaceVoltagePhase(String interfaceVoltagePhase) {
		this.interfaceVoltagePhase = interfaceVoltagePhase;
	}

	public void setInterfaceVoltageFrequency(String interfaceVoltageFrequency) {
		this.interfaceVoltageFrequency = interfaceVoltageFrequency;
	}

	public void setAlreadyHtSupply(String alreadyHtSupply) {
		this.alreadyHtSupply = alreadyHtSupply;
	}

	public void setCommissionDate(String commissionDate) {
		CommissionDate = commissionDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setTalukCode(String talukCode) {
		this.talukCode = talukCode;
	}

	public void setTalukName(String talukName) {
		this.talukName = talukName;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setPlSfNo(String plSfNo) {
		this.plSfNo = plSfNo;
	}

	public void setPlVillage(String plVillage) {
		this.plVillage = plVillage;
	}

	public void setPlTown(String plTown) {
		this.plTown = plTown;
	}

	public void setPlTalukCode(String plTalukCode) {
		this.plTalukCode = plTalukCode;
	}

	public void setPlTalukName(String plTalukName) {
		this.plTalukName = plTalukName;
	}

	public void setPlDistrictCode(String plDistrictCode) {
		this.plDistrictCode = plDistrictCode;
	}

	public void setPlDistrictName(String plDistrictName) {
		this.plDistrictName = plDistrictName;
	}

	public void setWindPassCode(String windPassCode) {
		this.windPassCode = windPassCode;
	}

	public void setWindPassName(String windPassName) {
		this.windPassName = windPassName;
	}

	public void setWindZoneAreaCode(String windZoneAreaCode) {
		this.windZoneAreaCode = windZoneAreaCode;
	}

	public void setWindZoneAreaName(String windZoneAreaName) {
		this.windZoneAreaName = windZoneAreaName;
	}

	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
	}

	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	
	
}
