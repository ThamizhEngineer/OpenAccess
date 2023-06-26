package com.ss.oa.model.master;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name = "M_POWERPLANT")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class Powerplant implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	  @Id
	  @Column(name="ID")
	  private String id;
//	  int version;
	  String code;
	  String name;
	  String plantTypeCode;
	  
	  @Formula("(SELECT plantcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes plantcodes on pp.PLANT_TYPE_CODE= plantcodes.Value_Code AND plantcodes.list_code = 'PLANT_TYPE_CODE' WHERE pp.ID=id)")
	  String plantTypeName;  // for display purpose - not stored
	  String fuelTypeCode;
	  @Formula("(SELECT fuelcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes fuelcodes on pp.FUEL_TYPE_CODE= fuelcodes.Value_Code AND fuelcodes.list_code = 'FUEL_TYPE_CODE' WHERE pp.ID=id)")
	  String fuelTypeName;  // for display purpose - not stored
	  @Column(name="M_SERVICE_ID")
	  String serviceId ; // for reference
//	  @Formula("(SELECT service.'number' FROM M_COMPANY_SERVICE service JOIN M_POWERPLANT pp ON service.ID=pp.M_SERVICE_ID WHERE pp.ID=ID)")
	  @Transient
	  String serviceNumber ; // for display purpose - not stored
	  @Column(name="M_ORG_ID")
	  String orgId; // for reference
	  @Formula("(SELECT org.CODE FROM M_POWERPLANT pp JOIN M_ORG org ON pp.M_ORG_ID = org.ID WHERE pp.ID=id)")
	  String orgCode; 	// for display purpose - not stored
	  @Formula("(SELECT org.NAME FROM M_POWERPLANT pp JOIN M_ORG org ON pp.M_ORG_ID = org.ID WHERE pp.ID=id)")
	  String orgName;   // for display purpose - not stored
	  @Column(name="T_GRID_CONN_APPLN_ID")
	  String gcId;       //for reference
	  String totalCapacity;
	  @Column(name="M_SUBSTATION_ID")
	  String subStationId;
	  @Formula("(SELECT company.ID FROM M_POWERPLANT pp JOIN M_COMPANY_SERVICE service ON pp.M_SERVICE_ID = service.ID \n" + 
	  		"JOIN M_COMPANY company ON service.M_COMPANY_ID=company.ID WHERE pp.ID=id)")
	  String companyId; // for display purpose - not stored 
	  @Formula("(SELECT company.CODE FROM M_POWERPLANT pp JOIN M_COMPANY_SERVICE service ON pp.M_SERVICE_ID = service.ID \n" + 
	  		"JOIN M_COMPANY company ON service.M_COMPANY_ID=company.ID WHERE pp.ID=id)")
	  String companyCode; // for display purpose - not stored 
	  @Formula("(SELECT company.NAME FROM M_POWERPLANT pp JOIN M_COMPANY_SERVICE service ON pp.M_SERVICE_ID = service.ID \n" + 
	  		"JOIN M_COMPANY company ON service.M_COMPANY_ID=company.ID WHERE pp.ID=id)")
	  String companyName; // for display purpose - not stored 
	  @Formula("(SELECT substation.CODE FROM M_POWERPLANT pp JOIN M_SUBSTATION substation on substation.id = pp.M_SUBSTATION_ID WHERE pp.ID=id)")
	  String subStationCode;  // for display purpose - not stored
	  @Formula("(SELECT substation.NAME FROM M_POWERPLANT pp JOIN M_SUBSTATION substation on substation.id = pp.M_SUBSTATION_ID WHERE pp.ID=id)")
	  String subStationName;  // for display purpose - not stored
	  @Transient
	  String subStationVoltage; // for display purpose - not stored
	  String interfaceVoltagePhase;
	  String interfaceVoltageFrequency;
	  @Transient
	  String alreadyHtSupply;   //not needed
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate CommissionDate;  

	  String purpose;  
//	  char enabled;   // Y or N
	  String status;   // Default - Active
	 
	  //PostalAddress fields
	  String line1;
	  String city;
	  String stateCode;
	  @Formula("(SELECT statecodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes statecodes on pp.STATE_CODE= statecodes.Value_Code AND statecodes.list_code = 'STATE_CODE' WHERE pp.ID=id)")
	  String stateName; // for display purpose - not stored
	  @Column(name="PINCODE")
	  String pinCode; 
	  String village;
	  @Transient
	  String town;
	  String talukCode;
	  @Formula("(SELECT talukcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes talukcodes on pp.TALUK_CODE= talukcodes.Value_Code AND talukcodes.list_code = 'TALUK_CODE' WHERE pp.ID=id)")
	  String talukName; // for display purpose - not stored
	  String districtCode;
	  @Formula("(SELECT districtcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes districtcodes on pp.DISTRICT_CODE= districtcodes.Value_Code AND districtcodes.list_code = 'DISTRICT_CODE' WHERE pp.ID=id)")
	  String districtName; // for display purpose - not stored
	  //PhysicalLocation fields
	  @Column(name="PLS_SF_NO")
	  String plSfNo;
	  @Column(name="PL_VILLAGE")
	  String plVillage;
	  String plTown;
	  String plTalukCode;
	  @Formula("(SELECT pltalukcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes pltalukcodes on pp.PL_TALUK_CODE= pltalukcodes.Value_Code AND pltalukcodes.list_code = 'TALUK_CODE' WHERE pp.ID=id)")
	  String plTalukName; // for display purpose - not stored
	  String plDistrictCode;
	  @Formula("(SELECT pldistrictcodes.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes pldistrictcodes on pp.PL_DISTRICT_CODE= pldistrictcodes.Value_Code AND pldistrictcodes.list_code = 'DISTRICT_CODE' WHERE pp.ID=id)")
	  String plDistrictName; // for display purpose - not stored
	  String windPassCode;
	  @Formula("(SELECT windpasscode.VALUE_DESC FROM M_POWERPLANT pp JOIN v_codes windpasscode on pp.WIND_PASS_CODE= windpasscode.Value_Code AND windpasscode.list_code = 'WIND_PASS_CODE' WHERE pp.ID=id)")
	  String windPassName; // for display purpose - not stored
	  String windZoneAreaCode;
	  @Transient
	  String windZoneAreaName; // for display purpose - not stored
	  @Column(name="APPLICATION_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate applicationDate;
	  @Column(name="APPROVAL_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate approvalDate;
	  String remarks;
	  String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate createdDate;
	  String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	  LocalDate modifiedDate;
	  String importRemarks;
	  String plantClassTypeCode;
	  @Column(name="M_FEEDER_ID")
	  String feederId;
	  @Formula("(SELECT company.EMAIL_ID FROM M_POWERPLANT pp JOIN M_COMPANY_SERVICE service ON pp.M_SERVICE_ID = service.ID \n" + 
		  		"JOIN M_COMPANY company ON service.M_COMPANY_ID=company.ID WHERE pp.ID=id)")
	  @Column(name="IS_UPDATED")
	  String currentUpdationStatus;
	  
	  @Column(columnDefinition = "char") 
	  String adBenefits;
	  
	  private String emailId;
	  @Transient
	  private List<VtradeRelationship> tradeRelationships;
	  @Transient
	  private Company company;
	  
	  @Transient
	  private Optional<Service> services;
	  
	  @Transient
	  private List<TradeRelationship> tradeRelationshipss;
	  
	  @OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	  @Fetch(value = FetchMode.SUBSELECT)
	  @JoinColumn(name="M_POWERPLANT_ID")
	  List<Generator> generators;

	public Powerplant() {
		super();
	}

	public Powerplant(String id, String code, String name, String plantTypeCode, String plantTypeName,
			String fuelTypeCode, String fuelTypeName, String serviceId, String serviceNumber, String orgId,
			String orgCode, String orgName, String gcId, String totalCapacity, String subStationId, String companyId,
			String companyCode, String companyName, String subStationCode, String subStationName,
			String subStationVoltage, String interfaceVoltagePhase, String interfaceVoltageFrequency,
			String alreadyHtSupply, LocalDate commissionDate, String purpose, String status, String line1, String city,
			String stateCode, String stateName, String pinCode, String village, String town, String talukCode,
			String talukName, String districtCode, String districtName, String plSfNo, String plVillage, String plTown,
			String plTalukCode, String plTalukName, String plDistrictCode, String plDistrictName, String windPassCode,
			String windPassName, String windZoneAreaCode, String windZoneAreaName, LocalDate applicationDate,
			LocalDate approvalDate, String remarks, String createdBy, LocalDate createdDate, String modifiedBy,
			LocalDate modifiedDate, String importRemarks, String plantClassTypeCode, String feederId,
			String currentUpdationStatus, String adBenefits, String emailId,
			List<VtradeRelationship> tradeRelationships, Company company, Optional<Service> services,
			List<TradeRelationship> tradeRelationshipss, List<Generator> generators) {
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
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.importRemarks = importRemarks;
		this.plantClassTypeCode = plantClassTypeCode;
		this.feederId = feederId;
		this.currentUpdationStatus = currentUpdationStatus;
		this.adBenefits = adBenefits;
		this.emailId = emailId;
		this.tradeRelationships = tradeRelationships;
		this.company = company;
		this.services = services;
		this.tradeRelationshipss = tradeRelationshipss;
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
				+ ", approvalDate=" + approvalDate + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", importRemarks=" + importRemarks + ", plantClassTypeCode=" + plantClassTypeCode + ", feederId="
				+ feederId + ", currentUpdationStatus=" + currentUpdationStatus + ", adBenefits=" + adBenefits
				+ ", emailId=" + emailId + ", tradeRelationships=" + tradeRelationships + ", company=" + company
				+ ", services=" + services + ", tradeRelationshipss=" + tradeRelationshipss + ", generators="
				+ generators + "]";
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

	public LocalDate getCommissionDate() {
		return CommissionDate;
	}

	public void setCommissionDate(LocalDate commissionDate) {
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

	public String getCurrentUpdationStatus() {
		return currentUpdationStatus;
	}

	public void setCurrentUpdationStatus(String currentUpdationStatus) {
		this.currentUpdationStatus = currentUpdationStatus;
	}

	public String getAdBenefits() {
		return adBenefits;
	}

	public void setAdBenefits(String adBenefits) {
		this.adBenefits = adBenefits;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public Optional<Service> getServices() {
		return services;
	}

	public void setServices(Optional<Service> services) {
		this.services = services;
	}

	public List<TradeRelationship> getTradeRelationshipss() {
		return tradeRelationshipss;
	}

	public void setTradeRelationshipss(List<TradeRelationship> tradeRelationshipss) {
		this.tradeRelationshipss = tradeRelationshipss;
	}

	public List<Generator> getGenerators() {
		return generators;
	}

	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
