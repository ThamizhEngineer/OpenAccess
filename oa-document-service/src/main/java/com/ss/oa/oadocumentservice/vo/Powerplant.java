package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Powerplant{

	  String id;
	  String code;
	  String name;
	  String plantTypeCode;
	  String plantTypeName;
	  String fuelTypeCode;
	  String fuelTypeName;
	  String serviceId ;
	  String serviceNumber ; 
	  String orgId; 
	  String orgCode;
	  String orgName;
	  String gcId;
	  String totalCapacity;
	  String subStationId;
	  String companyId;
	  String companyCode;
	  String companyName;
	  String subStationCode;
	  String subStationName;
	  String subStationVoltage;
	  String interfaceVoltagePhase;
	  String interfaceVoltageFrequency;
	  String alreadyHtSupply;
	  Date CommissionDate;  
	  String purpose;  
	  String status;
	  String line1;
	  String city;
	  String stateCode;
	  String stateName;
	  String pinCode; 
	  String village;
	  String town;
	  String talukCode;
	  String talukName;
	  String districtCode;
	  String districtName;
	  String plSfNo;
	  String plVillage;
	  String plTown;
	  String plTalukCode;
	  String plTalukName;
	  String plDistrictCode;
	  String plDistrictName;
	  String windPassCode;
	  String windPassName;
	  String windZoneAreaCode;
	  String windZoneAreaName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate applicationDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate approvalDate;
	  String remarks;
	  String importRemarks;
	  String plantClassTypeCode;
	  String feederId;
	  List<VtradeRelationship> tradeRelationships;
	  Company company;
	  List<Generator> generators;

	public Powerplant() {
		super();
	}

	public Powerplant(String id, String code, String name, String plantTypeCode, String plantTypeName,
			String fuelTypeCode, String fuelTypeName, String serviceId, String serviceNumber, String orgId,
			String orgCode, String orgName, String gcId, String totalCapacity, String subStationId, String companyId,
			String companyCode, String companyName, String subStationCode, String subStationName,
			String subStationVoltage, String interfaceVoltagePhase, String interfaceVoltageFrequency,
			String alreadyHtSupply, Date commissionDate, String purpose, String status, String line1, String city,
			String stateCode, String stateName, String pinCode, String village, String town, String talukCode,
			String talukName, String districtCode, String districtName, String plSfNo, String plVillage, String plTown,
			String plTalukCode, String plTalukName, String plDistrictCode, String plDistrictName, String windPassCode,
			String windPassName, String windZoneAreaCode, String windZoneAreaName, LocalDate applicationDate,
			LocalDate approvalDate, String remarks, String importRemarks, String plantClassTypeCode, String feederId,
			List<VtradeRelationship> tradeRelationships, Company company, List<Generator> generators) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.plantTypeCode = plantTypeCode;
		this.plantTypeName = plantTypeName;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.serviceId = serviceId;
		this.serviceNumber = serviceNumber;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.gcId = gcId;
		this.totalCapacity = totalCapacity;
		this.subStationId = subStationId;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.subStationCode = subStationCode;
		this.subStationName = subStationName;
		this.subStationVoltage = subStationVoltage;
		this.interfaceVoltagePhase = interfaceVoltagePhase;
		this.interfaceVoltageFrequency = interfaceVoltageFrequency;
		this.alreadyHtSupply = alreadyHtSupply;
		CommissionDate = commissionDate;
		this.purpose = purpose;
		this.status = status;
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
		this.applicationDate = applicationDate;
		this.approvalDate = approvalDate;
		this.remarks = remarks;
		this.importRemarks = importRemarks;
		this.plantClassTypeCode = plantClassTypeCode;
		this.feederId = feederId;
		this.tradeRelationships = tradeRelationships;
		this.company = company;
		this.generators = generators;
	}

	@Override
	public String toString() {
		return "Powerplant [id=" + id + ", code=" + code + ", name=" + name + ", plantTypeCode=" + plantTypeCode
				+ ", plantTypeName=" + plantTypeName + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName="
				+ fuelTypeName + ", serviceId=" + serviceId + ", serviceNumber=" + serviceNumber + ", orgId=" + orgId
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + ", gcId=" + gcId + ", totalCapacity="
				+ totalCapacity + ", subStationId=" + subStationId + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", subStationCode=" + subStationCode
				+ ", subStationName=" + subStationName + ", subStationVoltage=" + subStationVoltage
				+ ", interfaceVoltagePhase=" + interfaceVoltagePhase + ", interfaceVoltageFrequency="
				+ interfaceVoltageFrequency + ", alreadyHtSupply=" + alreadyHtSupply + ", CommissionDate="
				+ CommissionDate + ", purpose=" + purpose + ", status=" + status + ", line1=" + line1 + ", city=" + city
				+ ", stateCode=" + stateCode + ", stateName=" + stateName + ", pinCode=" + pinCode + ", village="
				+ village + ", town=" + town + ", talukCode=" + talukCode + ", talukName=" + talukName
				+ ", districtCode=" + districtCode + ", districtName=" + districtName + ", plSfNo=" + plSfNo
				+ ", plVillage=" + plVillage + ", plTown=" + plTown + ", plTalukCode=" + plTalukCode + ", plTalukName="
				+ plTalukName + ", plDistrictCode=" + plDistrictCode + ", plDistrictName=" + plDistrictName
				+ ", windPassCode=" + windPassCode + ", windPassName=" + windPassName + ", windZoneAreaCode="
				+ windZoneAreaCode + ", windZoneAreaName=" + windZoneAreaName + ", applicationDate=" + applicationDate
				+ ", approvalDate=" + approvalDate + ", remarks=" + remarks + ", importRemarks=" + importRemarks
				+ ", plantClassTypeCode=" + plantClassTypeCode + ", feederId=" + feederId + ", tradeRelationships="
				+ tradeRelationships + ", company=" + company + ", generators=" + generators + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlantTypeCode() {
		return plantTypeCode;
	}

	public void setPlantTypeCode(String plantTypeCode) {
		this.plantTypeCode = plantTypeCode;
	}

	public String getPlantTypeName() {
		return plantTypeName;
	}

	public void setPlantTypeName(String plantTypeName) {
		this.plantTypeName = plantTypeName;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getGcId() {
		return gcId;
	}

	public void setGcId(String gcId) {
		this.gcId = gcId;
	}

	public String getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public String getSubStationId() {
		return subStationId;
	}

	public void setSubStationId(String subStationId) {
		this.subStationId = subStationId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSubStationCode() {
		return subStationCode;
	}

	public void setSubStationCode(String subStationCode) {
		this.subStationCode = subStationCode;
	}

	public String getSubStationName() {
		return subStationName;
	}

	public void setSubStationName(String subStationName) {
		this.subStationName = subStationName;
	}

	public String getSubStationVoltage() {
		return subStationVoltage;
	}

	public void setSubStationVoltage(String subStationVoltage) {
		this.subStationVoltage = subStationVoltage;
	}

	public String getInterfaceVoltagePhase() {
		return interfaceVoltagePhase;
	}

	public void setInterfaceVoltagePhase(String interfaceVoltagePhase) {
		this.interfaceVoltagePhase = interfaceVoltagePhase;
	}

	public String getInterfaceVoltageFrequency() {
		return interfaceVoltageFrequency;
	}

	public void setInterfaceVoltageFrequency(String interfaceVoltageFrequency) {
		this.interfaceVoltageFrequency = interfaceVoltageFrequency;
	}

	public String getAlreadyHtSupply() {
		return alreadyHtSupply;
	}

	public void setAlreadyHtSupply(String alreadyHtSupply) {
		this.alreadyHtSupply = alreadyHtSupply;
	}

	public Date getCommissionDate() {
		return CommissionDate;
	}

	public void setCommissionDate(Date commissionDate) {
		CommissionDate = commissionDate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public String getPlSfNo() {
		return plSfNo;
	}

	public void setPlSfNo(String plSfNo) {
		this.plSfNo = plSfNo;
	}

	public String getPlVillage() {
		return plVillage;
	}

	public void setPlVillage(String plVillage) {
		this.plVillage = plVillage;
	}

	public String getPlTown() {
		return plTown;
	}

	public void setPlTown(String plTown) {
		this.plTown = plTown;
	}

	public String getPlTalukCode() {
		return plTalukCode;
	}

	public void setPlTalukCode(String plTalukCode) {
		this.plTalukCode = plTalukCode;
	}

	public String getPlTalukName() {
		return plTalukName;
	}

	public void setPlTalukName(String plTalukName) {
		this.plTalukName = plTalukName;
	}

	public String getPlDistrictCode() {
		return plDistrictCode;
	}

	public void setPlDistrictCode(String plDistrictCode) {
		this.plDistrictCode = plDistrictCode;
	}

	public String getPlDistrictName() {
		return plDistrictName;
	}

	public void setPlDistrictName(String plDistrictName) {
		this.plDistrictName = plDistrictName;
	}

	public String getWindPassCode() {
		return windPassCode;
	}

	public void setWindPassCode(String windPassCode) {
		this.windPassCode = windPassCode;
	}

	public String getWindPassName() {
		return windPassName;
	}

	public void setWindPassName(String windPassName) {
		this.windPassName = windPassName;
	}

	public String getWindZoneAreaCode() {
		return windZoneAreaCode;
	}

	public void setWindZoneAreaCode(String windZoneAreaCode) {
		this.windZoneAreaCode = windZoneAreaCode;
	}

	public String getWindZoneAreaName() {
		return windZoneAreaName;
	}

	public void setWindZoneAreaName(String windZoneAreaName) {
		this.windZoneAreaName = windZoneAreaName;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getPlantClassTypeCode() {
		return plantClassTypeCode;
	}

	public void setPlantClassTypeCode(String plantClassTypeCode) {
		this.plantClassTypeCode = plantClassTypeCode;
	}

	public String getFeederId() {
		return feederId;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public List<VtradeRelationship> getTradeRelationships() {
		return tradeRelationships;
	}

	public void setTradeRelationships(List<VtradeRelationship> tradeRelationships) {
		this.tradeRelationships = tradeRelationships;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Generator> getGenerators() {
		return generators;
	}

	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
	}


}
