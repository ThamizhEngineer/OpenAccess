package com.ss.oa.transaction.vo;

import java.util.List;

import com.ss.oa.master.vo.Generator;

public class GridConnectivity {

	private String id;
	private String generatingTypeName, generatingTypeIsCaptive;
	private String applnNumber;
	private String plantName, plantTypeCode, plantTypeName;
	private String fuelTypeCode, fuelTypeName;
	// companyCode // remove companyCode
	private String orgId, orgName;
	private String companyName;
	private String appliedDate, approvedDate, activatedDate;
	private String statusCode, statusName;
	private String line1, city, state, pinCode, village, town, taluk, district;
	private String plSfNo, plVillage, plPinCode, plTown, plTalukCode, plTalukName, plDistrictCode, plDistrictName,
			plWindPassCode, plWindPassName, plWindZoneArea;
	private String fuelCode, fuelLinkageArranged, fuelLinkageDetails;
	// cg - cogen info

	private String cgIndustryType, cgSupportFuel, cgIsParallelRun, cgIsStandBy, cgCycle, cgHasProof, cgProofUrl;
	// sf - Support Fuel Linkage
	private String sfLinkageFuelSource, sfLinkageFrom, sfLinkageTo, sfRate, sfDocUrl;
	private String classVoltagePhase, classVoltageFrequency;
	private String ssCode, ssName, ssId;
	private String ssVoltageCode, ssVoltageName;
	private String tempHtSupplyNumber; // it was alreadyHTSupply
	private String proposedCommissionDate;
	private String plantCapacity;
	private String annualExpectedQuantum;
	private String expectedCuf;
	private String auxiliaryConsumption, industrialConsumption;
	private String perUnitCost;
	private String proposedPowerCaptive, proposedPower3PT, proposedPowerSTB;
	private String firmPower, infirmPower;

	private String idTotalCost, idTotalCurrency, idTotalExchangeRate;
	private String idproposedDebtEquityRatio;
	private String idEBPrefShareCapAmt, idEBPrefShareCapPer;
	private String idEBEquityShareCapAmt, idEBEquityShareCapPer, idEBPromEquityAmt, idEBPromEquityPer;

	private String idPCPrefShareCapAmt, idPCPrefShareCapPer, idPCEquityShareCapAmt, idPCEquityShareCapPer;

	private String idCUCPrefShareCapAmt, idCUCPrefShareCapPer, idCUCEquityShareCapAmt, idCUCEquityShareCapPer;

	private String idOwnPercPromoter, idOwnPercCaptive, idOwnPercOwnRule;
	private String genServiceNumber, genServiceApprovalNumber, genServiceDate;
	private String finalOrgId, finalOrgName, finalOrgCode;
	private String finalSsTypeCode, finalSsTypeName, finalSsId, finalSsName, finalSsCode;
	private String finalFeederTypeCode, finalFeederTypeName, finalFeederId, finalFeederName, finalFeederCode;
	private String finalIsStb, finalIsWheeling, finalPpRate;
	private String finalStbTariffOrder, finalStbTenderNumber, finalStbTenderDate;
	private String finalWheelingFromDate, finalWheelingToDate;
	private String genServiceDetails;
	private String infrastructure;
	private String tariff;

	private String availedHtSupply, availedHtSupplyNo, availedSanctionedDemand;
	private String scoTamilNadu;
	private String scoMinistry;
	private String scoCivil;
	private String finalCod;
	private String finalCopd;
	private String remarks;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String enabled;

	private String meterNumber;
	private String meterMakeCode;
	private String meterMakeName;
	private String accuracyClassCode;
	private String accuracyClassName;
	private String isAbtMeter;
	private String multiplicationFactor, modemNumber;
	private String meterCt1, meterCt2, meterCt3;
	private String meterPt1, meterPt2, meterPt3;
	private String parallelOperation;

	private List<Generator> genUnits;
	private List<Transformer> transformers;
	private List<CaptiveEnergyNeed> captiveEnergyNeeds;
	private List<DocCheckListItem> checkList;
	private List<ApplicationStatus> applicationStatus;
	private List<Loan> idLoans;
	private List<Clearance> clearance;
	private List<SupportFuelLinkageDetail> supportFuelLinkageDetail;
	private List<CaptiveUserContribution> idCaptiveUserContributions;
	private List<QuantumAllocation> captiveQuantumAllocation;
	private List<EquityShareVotingRights> idEquityShareVotingRights;

	public GridConnectivity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGeneratingTypeName() {
		return generatingTypeName;
	}

	public void setGeneratingTypeName(String generatingTypeName) {
		this.generatingTypeName = generatingTypeName;
	}

	public String getGeneratingTypeIsCaptive() {
		return generatingTypeIsCaptive;
	}

	public void setGeneratingTypeIsCaptive(String generatingTypeIsCaptive) {
		this.generatingTypeIsCaptive = generatingTypeIsCaptive;
	}

	public String getApplnNumber() {
		return applnNumber;
	}

	public void setApplnNumber(String applnNumber) {
		this.applnNumber = applnNumber;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getActivatedDate() {
		return activatedDate;
	}

	public void setActivatedDate(String activatedDate) {
		this.activatedDate = activatedDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getPlPinCode() {
		return plPinCode;
	}

	public void setPlPinCode(String plPinCode) {
		this.plPinCode = plPinCode;
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

	public String getPlWindPassCode() {
		return plWindPassCode;
	}

	public void setPlWindPassCode(String plWindPassCode) {
		this.plWindPassCode = plWindPassCode;
	}

	public String getPlWindPassName() {
		return plWindPassName;
	}

	public void setPlWindPassName(String plWindPassName) {
		this.plWindPassName = plWindPassName;
	}

	public String getPlWindZoneArea() {
		return plWindZoneArea;
	}

	public void setPlWindZoneArea(String plWindZoneArea) {
		this.plWindZoneArea = plWindZoneArea;
	}

	public String getFuelCode() {
		return fuelCode;
	}

	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}

	public String getFuelLinkageArranged() {
		return fuelLinkageArranged;
	}

	public void setFuelLinkageArranged(String fuelLinkageArranged) {
		this.fuelLinkageArranged = fuelLinkageArranged;
	}

	public String getFuelLinkageDetails() {
		return fuelLinkageDetails;
	}

	public void setFuelLinkageDetails(String fuelLinkageDetails) {
		this.fuelLinkageDetails = fuelLinkageDetails;
	}

	public String getCgIndustryType() {
		return cgIndustryType;
	}

	public void setCgIndustryType(String cgIndustryType) {
		this.cgIndustryType = cgIndustryType;
	}

	public String getCgSupportFuel() {
		return cgSupportFuel;
	}

	public void setCgSupportFuel(String cgSupportFuel) {
		this.cgSupportFuel = cgSupportFuel;
	}

	public String getCgIsParallelRun() {
		return cgIsParallelRun;
	}

	public void setCgIsParallelRun(String cgIsParallelRun) {
		this.cgIsParallelRun = cgIsParallelRun;
	}

	public String getCgIsStandBy() {
		return cgIsStandBy;
	}

	public void setCgIsStandBy(String cgIsStandBy) {
		this.cgIsStandBy = cgIsStandBy;
	}

	public String getCgCycle() {
		return cgCycle;
	}

	public void setCgCycle(String cgCycle) {
		this.cgCycle = cgCycle;
	}

	public String getCgHasProof() {
		return cgHasProof;
	}

	public void setCgHasProof(String cgHasProof) {
		this.cgHasProof = cgHasProof;
	}

	public String getCgProofUrl() {
		return cgProofUrl;
	}

	public void setCgProofUrl(String cgProofUrl) {
		this.cgProofUrl = cgProofUrl;
	}

	public String getSfLinkageFuelSource() {
		return sfLinkageFuelSource;
	}

	public void setSfLinkageFuelSource(String sfLinkageFuelSource) {
		this.sfLinkageFuelSource = sfLinkageFuelSource;
	}

	public String getSfLinkageFrom() {
		return sfLinkageFrom;
	}

	public void setSfLinkageFrom(String sfLinkageFrom) {
		this.sfLinkageFrom = sfLinkageFrom;
	}

	public String getSfLinkageTo() {
		return sfLinkageTo;
	}

	public void setSfLinkageTo(String sfLinkageTo) {
		this.sfLinkageTo = sfLinkageTo;
	}

	public String getSfRate() {
		return sfRate;
	}

	public void setSfRate(String sfRate) {
		this.sfRate = sfRate;
	}

	public String getSfDocUrl() {
		return sfDocUrl;
	}

	public void setSfDocUrl(String sfDocUrl) {
		this.sfDocUrl = sfDocUrl;
	}

	public String getClassVoltagePhase() {
		return classVoltagePhase;
	}

	public void setClassVoltagePhase(String classVoltagePhase) {
		this.classVoltagePhase = classVoltagePhase;
	}

	public String getClassVoltageFrequency() {
		return classVoltageFrequency;
	}

	public void setClassVoltageFrequency(String classVoltageFrequency) {
		this.classVoltageFrequency = classVoltageFrequency;
	}

	public String getSsCode() {
		return ssCode;
	}

	public void setSsCode(String ssCode) {
		this.ssCode = ssCode;
	}

	public String getSsName() {
		return ssName;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getSsVoltageCode() {
		return ssVoltageCode;
	}

	public void setSsVoltageCode(String ssVoltageCode) {
		this.ssVoltageCode = ssVoltageCode;
	}

	public String getSsVoltageName() {
		return ssVoltageName;
	}

	public void setSsVoltageName(String ssVoltageName) {
		this.ssVoltageName = ssVoltageName;
	}

	public String getTempHtSupplyNumber() {
		return tempHtSupplyNumber;
	}

	public void setTempHtSupplyNumber(String tempHtSupplyNumber) {
		this.tempHtSupplyNumber = tempHtSupplyNumber;
	}

	public String getProposedCommissionDate() {
		return proposedCommissionDate;
	}

	public void setProposedCommissionDate(String proposedCommissionDate) {
		this.proposedCommissionDate = proposedCommissionDate;
	}

	public String getPlantCapacity() {
		return plantCapacity;
	}

	public void setPlantCapacity(String plantCapacity) {
		this.plantCapacity = plantCapacity;
	}

	public String getAnnualExpectedQuantum() {
		return annualExpectedQuantum;
	}

	public void setAnnualExpectedQuantum(String annualExpectedQuantum) {
		this.annualExpectedQuantum = annualExpectedQuantum;
	}

	public String getExpectedCuf() {
		return expectedCuf;
	}

	public void setExpectedCuf(String expectedCuf) {
		this.expectedCuf = expectedCuf;
	}

	public String getAuxiliaryConsumption() {
		return auxiliaryConsumption;
	}

	public void setAuxiliaryConsumption(String auxiliaryConsumption) {
		this.auxiliaryConsumption = auxiliaryConsumption;
	}

	public String getIndustrialConsumption() {
		return industrialConsumption;
	}

	public void setIndustrialConsumption(String industrialConsumption) {
		this.industrialConsumption = industrialConsumption;
	}

	public String getPerUnitCost() {
		return perUnitCost;
	}

	public void setPerUnitCost(String perUnitCost) {
		this.perUnitCost = perUnitCost;
	}

	public String getProposedPowerCaptive() {
		return proposedPowerCaptive;
	}

	public void setProposedPowerCaptive(String proposedPowerCaptive) {
		this.proposedPowerCaptive = proposedPowerCaptive;
	}

	public String getProposedPower3PT() {
		return proposedPower3PT;
	}

	public void setProposedPower3PT(String proposedPower3PT) {
		this.proposedPower3PT = proposedPower3PT;
	}

	public String getProposedPowerSTB() {
		return proposedPowerSTB;
	}

	public void setProposedPowerSTB(String proposedPowerSTB) {
		this.proposedPowerSTB = proposedPowerSTB;
	}

	public String getFirmPower() {
		return firmPower;
	}

	public void setFirmPower(String firmPower) {
		this.firmPower = firmPower;
	}

	public String getInfirmPower() {
		return infirmPower;
	}

	public void setInfirmPower(String infirmPower) {
		this.infirmPower = infirmPower;
	}

	public String getIdTotalCost() {
		return idTotalCost;
	}

	public void setIdTotalCost(String idTotalCost) {
		this.idTotalCost = idTotalCost;
	}

	public String getIdTotalCurrency() {
		return idTotalCurrency;
	}

	public void setIdTotalCurrency(String idTotalCurrency) {
		this.idTotalCurrency = idTotalCurrency;
	}

	public String getIdTotalExchangeRate() {
		return idTotalExchangeRate;
	}

	public void setIdTotalExchangeRate(String idTotalExchangeRate) {
		this.idTotalExchangeRate = idTotalExchangeRate;
	}

	public String getIdproposedDebtEquityRatio() {
		return idproposedDebtEquityRatio;
	}

	public void setIdproposedDebtEquityRatio(String idproposedDebtEquityRatio) {
		this.idproposedDebtEquityRatio = idproposedDebtEquityRatio;
	}

	public String getIdEBPrefShareCapAmt() {
		return idEBPrefShareCapAmt;
	}

	public void setIdEBPrefShareCapAmt(String idEBPrefShareCapAmt) {
		this.idEBPrefShareCapAmt = idEBPrefShareCapAmt;
	}

	public String getIdEBPrefShareCapPer() {
		return idEBPrefShareCapPer;
	}

	public void setIdEBPrefShareCapPer(String idEBPrefShareCapPer) {
		this.idEBPrefShareCapPer = idEBPrefShareCapPer;
	}

	public String getIdEBEquityShareCapAmt() {
		return idEBEquityShareCapAmt;
	}

	public void setIdEBEquityShareCapAmt(String idEBEquityShareCapAmt) {
		this.idEBEquityShareCapAmt = idEBEquityShareCapAmt;
	}

	public String getIdEBEquityShareCapPer() {
		return idEBEquityShareCapPer;
	}

	public void setIdEBEquityShareCapPer(String idEBEquityShareCapPer) {
		this.idEBEquityShareCapPer = idEBEquityShareCapPer;
	}

	public String getIdEBPromEquityAmt() {
		return idEBPromEquityAmt;
	}

	public void setIdEBPromEquityAmt(String idEBPromEquityAmt) {
		this.idEBPromEquityAmt = idEBPromEquityAmt;
	}

	public String getIdEBPromEquityPer() {
		return idEBPromEquityPer;
	}

	public void setIdEBPromEquityPer(String idEBPromEquityPer) {
		this.idEBPromEquityPer = idEBPromEquityPer;
	}

	public String getIdPCPrefShareCapAmt() {
		return idPCPrefShareCapAmt;
	}

	public void setIdPCPrefShareCapAmt(String idPCPrefShareCapAmt) {
		this.idPCPrefShareCapAmt = idPCPrefShareCapAmt;
	}

	public String getIdPCPrefShareCapPer() {
		return idPCPrefShareCapPer;
	}

	public void setIdPCPrefShareCapPer(String idPCPrefShareCapPer) {
		this.idPCPrefShareCapPer = idPCPrefShareCapPer;
	}

	public String getIdPCEquityShareCapAmt() {
		return idPCEquityShareCapAmt;
	}

	public void setIdPCEquityShareCapAmt(String idPCEquityShareCapAmt) {
		this.idPCEquityShareCapAmt = idPCEquityShareCapAmt;
	}

	public String getIdPCEquityShareCapPer() {
		return idPCEquityShareCapPer;
	}

	public void setIdPCEquityShareCapPer(String idPCEquityShareCapPer) {
		this.idPCEquityShareCapPer = idPCEquityShareCapPer;
	}

	public String getIdCUCPrefShareCapAmt() {
		return idCUCPrefShareCapAmt;
	}

	public void setIdCUCPrefShareCapAmt(String idCUCPrefShareCapAmt) {
		this.idCUCPrefShareCapAmt = idCUCPrefShareCapAmt;
	}

	public String getIdCUCPrefShareCapPer() {
		return idCUCPrefShareCapPer;
	}

	public void setIdCUCPrefShareCapPer(String idCUCPrefShareCapPer) {
		this.idCUCPrefShareCapPer = idCUCPrefShareCapPer;
	}

	public String getIdCUCEquityShareCapAmt() {
		return idCUCEquityShareCapAmt;
	}

	public void setIdCUCEquityShareCapAmt(String idCUCEquityShareCapAmt) {
		this.idCUCEquityShareCapAmt = idCUCEquityShareCapAmt;
	}

	public String getIdCUCEquityShareCapPer() {
		return idCUCEquityShareCapPer;
	}

	public void setIdCUCEquityShareCapPer(String idCUCEquityShareCapPer) {
		this.idCUCEquityShareCapPer = idCUCEquityShareCapPer;
	}

	public String getIdOwnPercPromoter() {
		return idOwnPercPromoter;
	}

	public void setIdOwnPercPromoter(String idOwnPercPromoter) {
		this.idOwnPercPromoter = idOwnPercPromoter;
	}

	public String getIdOwnPercCaptive() {
		return idOwnPercCaptive;
	}

	public void setIdOwnPercCaptive(String idOwnPercCaptive) {
		this.idOwnPercCaptive = idOwnPercCaptive;
	}

	public String getIdOwnPercOwnRule() {
		return idOwnPercOwnRule;
	}

	public void setIdOwnPercOwnRule(String idOwnPercOwnRule) {
		this.idOwnPercOwnRule = idOwnPercOwnRule;
	}

	public String getGenServiceNumber() {
		return genServiceNumber;
	}

	public void setGenServiceNumber(String genServiceNumber) {
		this.genServiceNumber = genServiceNumber;
	}

	public String getGenServiceApprovalNumber() {
		return genServiceApprovalNumber;
	}

	public void setGenServiceApprovalNumber(String genServiceApprovalNumber) {
		this.genServiceApprovalNumber = genServiceApprovalNumber;
	}

	public String getGenServiceDate() {
		return genServiceDate;
	}

	public void setGenServiceDate(String genServiceDate) {
		this.genServiceDate = genServiceDate;
	}

	public String getFinalOrgId() {
		return finalOrgId;
	}

	public void setFinalOrgId(String finalOrgId) {
		this.finalOrgId = finalOrgId;
	}

	public String getFinalOrgName() {
		return finalOrgName;
	}

	public void setFinalOrgName(String finalOrgName) {
		this.finalOrgName = finalOrgName;
	}

	public String getFinalOrgCode() {
		return finalOrgCode;
	}

	public void setFinalOrgCode(String finalOrgCode) {
		this.finalOrgCode = finalOrgCode;
	}

	public String getFinalSsTypeCode() {
		return finalSsTypeCode;
	}

	public void setFinalSsTypeCode(String finalSsTypeCode) {
		this.finalSsTypeCode = finalSsTypeCode;
	}

	public String getFinalSsTypeName() {
		return finalSsTypeName;
	}

	public void setFinalSsTypeName(String finalSsTypeName) {
		this.finalSsTypeName = finalSsTypeName;
	}

	public String getFinalSsId() {
		return finalSsId;
	}

	public void setFinalSsId(String finalSsId) {
		this.finalSsId = finalSsId;
	}

	public String getFinalSsName() {
		return finalSsName;
	}

	public void setFinalSsName(String finalSsName) {
		this.finalSsName = finalSsName;
	}

	public String getFinalSsCode() {
		return finalSsCode;
	}

	public void setFinalSsCode(String finalSsCode) {
		this.finalSsCode = finalSsCode;
	}

	public String getFinalFeederTypeCode() {
		return finalFeederTypeCode;
	}

	public void setFinalFeederTypeCode(String finalFeederTypeCode) {
		this.finalFeederTypeCode = finalFeederTypeCode;
	}

	public String getFinalFeederTypeName() {
		return finalFeederTypeName;
	}

	public void setFinalFeederTypeName(String finalFeederTypeName) {
		this.finalFeederTypeName = finalFeederTypeName;
	}

	public String getFinalFeederId() {
		return finalFeederId;
	}

	public void setFinalFeederId(String finalFeederId) {
		this.finalFeederId = finalFeederId;
	}

	public String getFinalFeederName() {
		return finalFeederName;
	}

	public void setFinalFeederName(String finalFeederName) {
		this.finalFeederName = finalFeederName;
	}

	public String getFinalFeederCode() {
		return finalFeederCode;
	}

	public void setFinalFeederCode(String finalFeederCode) {
		this.finalFeederCode = finalFeederCode;
	}

	public String getFinalIsStb() {
		return finalIsStb;
	}

	public void setFinalIsStb(String finalIsStb) {
		this.finalIsStb = finalIsStb;
	}

	public String getFinalIsWheeling() {
		return finalIsWheeling;
	}

	public void setFinalIsWheeling(String finalIsWheeling) {
		this.finalIsWheeling = finalIsWheeling;
	}

	public String getFinalPpRate() {
		return finalPpRate;
	}

	public void setFinalPpRate(String finalPpRate) {
		this.finalPpRate = finalPpRate;
	}

	public String getFinalStbTariffOrder() {
		return finalStbTariffOrder;
	}

	public void setFinalStbTariffOrder(String finalStbTariffOrder) {
		this.finalStbTariffOrder = finalStbTariffOrder;
	}

	public String getFinalStbTenderNumber() {
		return finalStbTenderNumber;
	}

	public void setFinalStbTenderNumber(String finalStbTenderNumber) {
		this.finalStbTenderNumber = finalStbTenderNumber;
	}

	public String getFinalStbTenderDate() {
		return finalStbTenderDate;
	}

	public void setFinalStbTenderDate(String finalStbTenderDate) {
		this.finalStbTenderDate = finalStbTenderDate;
	}

	public String getFinalWheelingFromDate() {
		return finalWheelingFromDate;
	}

	public void setFinalWheelingFromDate(String finalWheelingFromDate) {
		this.finalWheelingFromDate = finalWheelingFromDate;
	}

	public String getFinalWheelingToDate() {
		return finalWheelingToDate;
	}

	public void setFinalWheelingToDate(String finalWheelingToDate) {
		this.finalWheelingToDate = finalWheelingToDate;
	}

	public String getGenServiceDetails() {
		return genServiceDetails;
	}

	public void setGenServiceDetails(String genServiceDetails) {
		this.genServiceDetails = genServiceDetails;
	}

	public String getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(String infrastructure) {
		this.infrastructure = infrastructure;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getAvailedHtSupply() {
		return availedHtSupply;
	}

	public void setAvailedHtSupply(String availedHtSupply) {
		this.availedHtSupply = availedHtSupply;
	}

	public String getAvailedHtSupplyNo() {
		return availedHtSupplyNo;
	}

	public void setAvailedHtSupplyNo(String availedHtSupplyNo) {
		this.availedHtSupplyNo = availedHtSupplyNo;
	}

	public String getAvailedSanctionedDemand() {
		return availedSanctionedDemand;
	}

	public void setAvailedSanctionedDemand(String availedSanctionedDemand) {
		this.availedSanctionedDemand = availedSanctionedDemand;
	}

	public String getScoTamilNadu() {
		return scoTamilNadu;
	}

	public void setScoTamilNadu(String scoTamilNadu) {
		this.scoTamilNadu = scoTamilNadu;
	}

	public String getScoMinistry() {
		return scoMinistry;
	}

	public void setScoMinistry(String scoMinistry) {
		this.scoMinistry = scoMinistry;
	}

	public String getScoCivil() {
		return scoCivil;
	}

	public void setScoCivil(String scoCivil) {
		this.scoCivil = scoCivil;
	}

	public String getFinalCod() {
		return finalCod;
	}

	public void setFinalCod(String finalCod) {
		this.finalCod = finalCod;
	}

	public String getFinalCopd() {
		return finalCopd;
	}

	public void setFinalCopd(String finalCopd) {
		this.finalCopd = finalCopd;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
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

	public String getParallelOperation() {
		return parallelOperation;
	}

	public void setParallelOperation(String parallelOperation) {
		this.parallelOperation = parallelOperation;
	}

	public List<Generator> getGenUnits() {
		return genUnits;
	}

	public void setGenUnits(List<Generator> genUnits) {
		this.genUnits = genUnits;
	}

	public List<Transformer> getTransformers() {
		return transformers;
	}

	public void setTransformers(List<Transformer> transformers) {
		this.transformers = transformers;
	}

	public List<CaptiveEnergyNeed> getCaptiveEnergyNeeds() {
		return captiveEnergyNeeds;
	}

	public void setCaptiveEnergyNeeds(List<CaptiveEnergyNeed> captiveEnergyNeeds) {
		this.captiveEnergyNeeds = captiveEnergyNeeds;
	}

	public List<DocCheckListItem> getCheckList() {
		return checkList;
	}

	public void setCheckList(List<DocCheckListItem> checkList) {
		this.checkList = checkList;
	}

	public List<ApplicationStatus> getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(List<ApplicationStatus> applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public List<Loan> getIdLoans() {
		return idLoans;
	}

	public void setIdLoans(List<Loan> idLoans) {
		this.idLoans = idLoans;
	}

	public List<Clearance> getClearance() {
		return clearance;
	}

	public void setClearance(List<Clearance> clearance) {
		this.clearance = clearance;
	}

	public List<SupportFuelLinkageDetail> getSupportFuelLinkageDetail() {
		return supportFuelLinkageDetail;
	}

	public void setSupportFuelLinkageDetail(List<SupportFuelLinkageDetail> supportFuelLinkageDetail) {
		this.supportFuelLinkageDetail = supportFuelLinkageDetail;
	}

	public List<CaptiveUserContribution> getIdCaptiveUserContributions() {
		return idCaptiveUserContributions;
	}

	public void setIdCaptiveUserContributions(List<CaptiveUserContribution> idCaptiveUserContributions) {
		this.idCaptiveUserContributions = idCaptiveUserContributions;
	}

	public List<QuantumAllocation> getCaptiveQuantumAllocation() {
		return captiveQuantumAllocation;
	}

	public void setCaptiveQuantumAllocation(List<QuantumAllocation> captiveQuantumAllocation) {
		this.captiveQuantumAllocation = captiveQuantumAllocation;
	}

	public List<EquityShareVotingRights> getIdEquityShareVotingRights() {
		return idEquityShareVotingRights;
	}

	public void setIdEquityShareVotingRights(List<EquityShareVotingRights> idEquityShareVotingRights) {
		this.idEquityShareVotingRights = idEquityShareVotingRights;
	}

	public GridConnectivity(String id, String generatingTypeName, String generatingTypeIsCaptive, String applnNumber,
			String plantName, String plantTypeCode, String plantTypeName, String fuelTypeCode, String fuelTypeName,
			String orgId, String orgName, String companyName, String appliedDate, String approvedDate,
			String activatedDate, String statusCode, String statusName, String line1, String city, String state,
			String pinCode, String village, String town, String taluk, String district, String plSfNo, String plVillage,
			String plPinCode, String plTown, String plTalukCode, String plTalukName, String plDistrictCode,
			String plDistrictName, String plWindPassCode, String plWindPassName, String plWindZoneArea, String fuelCode,
			String fuelLinkageArranged, String fuelLinkageDetails, String cgIndustryType, String cgSupportFuel,
			String cgIsParallelRun, String cgIsStandBy, String cgCycle, String cgHasProof, String cgProofUrl,
			String sfLinkageFuelSource, String sfLinkageFrom, String sfLinkageTo, String sfRate, String sfDocUrl,
			String classVoltagePhase, String classVoltageFrequency, String ssCode, String ssName, String ssId,
			String ssVoltageCode, String ssVoltageName, String tempHtSupplyNumber, String proposedCommissionDate,
			String plantCapacity, String annualExpectedQuantum, String expectedCuf, String auxiliaryConsumption,
			String industrialConsumption, String perUnitCost, String proposedPowerCaptive, String proposedPower3PT,
			String proposedPowerSTB, String firmPower, String infirmPower, String idTotalCost, String idTotalCurrency,
			String idTotalExchangeRate, String idproposedDebtEquityRatio, String idEBPrefShareCapAmt,
			String idEBPrefShareCapPer, String idEBEquityShareCapAmt, String idEBEquityShareCapPer,
			String idEBPromEquityAmt, String idEBPromEquityPer, String idPCPrefShareCapAmt, String idPCPrefShareCapPer,
			String idPCEquityShareCapAmt, String idPCEquityShareCapPer, String idCUCPrefShareCapAmt,
			String idCUCPrefShareCapPer, String idCUCEquityShareCapAmt, String idCUCEquityShareCapPer,
			String idOwnPercPromoter, String idOwnPercCaptive, String idOwnPercOwnRule, String genServiceNumber,
			String genServiceApprovalNumber, String genServiceDate, String finalOrgId, String finalOrgName,
			String finalOrgCode, String finalSsTypeCode, String finalSsTypeName, String finalSsId, String finalSsName,
			String finalSsCode, String finalFeederTypeCode, String finalFeederTypeName, String finalFeederId,
			String finalFeederName, String finalFeederCode, String finalIsStb, String finalIsWheeling,
			String finalPpRate, String finalStbTariffOrder, String finalStbTenderNumber, String finalStbTenderDate,
			String finalWheelingFromDate, String finalWheelingToDate, String genServiceDetails, String infrastructure,
			String tariff, String availedHtSupply, String availedHtSupplyNo, String availedSanctionedDemand,
			String scoTamilNadu, String scoMinistry, String scoCivil, String finalCod, String finalCopd, String remarks,
			String createdBy, String createdDate, String modifiedBy, String modifiedDate, String enabled,
			String meterNumber, String meterMakeCode, String meterMakeName, String accuracyClassCode,
			String accuracyClassName, String isAbtMeter, String multiplicationFactor, String modemNumber,
			String meterCt1, String meterCt2, String meterCt3, String meterPt1, String meterPt2, String meterPt3,
			String parallelOperation, List<Generator> genUnits, List<Transformer> transformers,
			List<CaptiveEnergyNeed> captiveEnergyNeeds, List<DocCheckListItem> checkList,
			List<ApplicationStatus> applicationStatus, List<Loan> idLoans, List<Clearance> clearance,
			List<SupportFuelLinkageDetail> supportFuelLinkageDetail,
			List<CaptiveUserContribution> idCaptiveUserContributions, List<QuantumAllocation> captiveQuantumAllocation,
			List<EquityShareVotingRights> idEquityShareVotingRights) {
		super();
		this.id = id;
		this.generatingTypeName = generatingTypeName;
		this.generatingTypeIsCaptive = generatingTypeIsCaptive;
		this.applnNumber = applnNumber;
		this.plantName = plantName;
		this.plantTypeCode = plantTypeCode;
		this.plantTypeName = plantTypeName;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.companyName = companyName;
		this.appliedDate = appliedDate;
		this.approvedDate = approvedDate;
		this.activatedDate = activatedDate;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.line1 = line1;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.village = village;
		this.town = town;
		this.taluk = taluk;
		this.district = district;
		this.plSfNo = plSfNo;
		this.plVillage = plVillage;
		this.plPinCode = plPinCode;
		this.plTown = plTown;
		this.plTalukCode = plTalukCode;
		this.plTalukName = plTalukName;
		this.plDistrictCode = plDistrictCode;
		this.plDistrictName = plDistrictName;
		this.plWindPassCode = plWindPassCode;
		this.plWindPassName = plWindPassName;
		this.plWindZoneArea = plWindZoneArea;
		this.fuelCode = fuelCode;
		this.fuelLinkageArranged = fuelLinkageArranged;
		this.fuelLinkageDetails = fuelLinkageDetails;
		this.cgIndustryType = cgIndustryType;
		this.cgSupportFuel = cgSupportFuel;
		this.cgIsParallelRun = cgIsParallelRun;
		this.cgIsStandBy = cgIsStandBy;
		this.cgCycle = cgCycle;
		this.cgHasProof = cgHasProof;
		this.cgProofUrl = cgProofUrl;
		this.sfLinkageFuelSource = sfLinkageFuelSource;
		this.sfLinkageFrom = sfLinkageFrom;
		this.sfLinkageTo = sfLinkageTo;
		this.sfRate = sfRate;
		this.sfDocUrl = sfDocUrl;
		this.classVoltagePhase = classVoltagePhase;
		this.classVoltageFrequency = classVoltageFrequency;
		this.ssCode = ssCode;
		this.ssName = ssName;
		this.ssId = ssId;
		this.ssVoltageCode = ssVoltageCode;
		this.ssVoltageName = ssVoltageName;
		this.tempHtSupplyNumber = tempHtSupplyNumber;
		this.proposedCommissionDate = proposedCommissionDate;
		this.plantCapacity = plantCapacity;
		this.annualExpectedQuantum = annualExpectedQuantum;
		this.expectedCuf = expectedCuf;
		this.auxiliaryConsumption = auxiliaryConsumption;
		this.industrialConsumption = industrialConsumption;
		this.perUnitCost = perUnitCost;
		this.proposedPowerCaptive = proposedPowerCaptive;
		this.proposedPower3PT = proposedPower3PT;
		this.proposedPowerSTB = proposedPowerSTB;
		this.firmPower = firmPower;
		this.infirmPower = infirmPower;
		this.idTotalCost = idTotalCost;
		this.idTotalCurrency = idTotalCurrency;
		this.idTotalExchangeRate = idTotalExchangeRate;
		this.idproposedDebtEquityRatio = idproposedDebtEquityRatio;
		this.idEBPrefShareCapAmt = idEBPrefShareCapAmt;
		this.idEBPrefShareCapPer = idEBPrefShareCapPer;
		this.idEBEquityShareCapAmt = idEBEquityShareCapAmt;
		this.idEBEquityShareCapPer = idEBEquityShareCapPer;
		this.idEBPromEquityAmt = idEBPromEquityAmt;
		this.idEBPromEquityPer = idEBPromEquityPer;
		this.idPCPrefShareCapAmt = idPCPrefShareCapAmt;
		this.idPCPrefShareCapPer = idPCPrefShareCapPer;
		this.idPCEquityShareCapAmt = idPCEquityShareCapAmt;
		this.idPCEquityShareCapPer = idPCEquityShareCapPer;
		this.idCUCPrefShareCapAmt = idCUCPrefShareCapAmt;
		this.idCUCPrefShareCapPer = idCUCPrefShareCapPer;
		this.idCUCEquityShareCapAmt = idCUCEquityShareCapAmt;
		this.idCUCEquityShareCapPer = idCUCEquityShareCapPer;
		this.idOwnPercPromoter = idOwnPercPromoter;
		this.idOwnPercCaptive = idOwnPercCaptive;
		this.idOwnPercOwnRule = idOwnPercOwnRule;
		this.genServiceNumber = genServiceNumber;
		this.genServiceApprovalNumber = genServiceApprovalNumber;
		this.genServiceDate = genServiceDate;
		this.finalOrgId = finalOrgId;
		this.finalOrgName = finalOrgName;
		this.finalOrgCode = finalOrgCode;
		this.finalSsTypeCode = finalSsTypeCode;
		this.finalSsTypeName = finalSsTypeName;
		this.finalSsId = finalSsId;
		this.finalSsName = finalSsName;
		this.finalSsCode = finalSsCode;
		this.finalFeederTypeCode = finalFeederTypeCode;
		this.finalFeederTypeName = finalFeederTypeName;
		this.finalFeederId = finalFeederId;
		this.finalFeederName = finalFeederName;
		this.finalFeederCode = finalFeederCode;
		this.finalIsStb = finalIsStb;
		this.finalIsWheeling = finalIsWheeling;
		this.finalPpRate = finalPpRate;
		this.finalStbTariffOrder = finalStbTariffOrder;
		this.finalStbTenderNumber = finalStbTenderNumber;
		this.finalStbTenderDate = finalStbTenderDate;
		this.finalWheelingFromDate = finalWheelingFromDate;
		this.finalWheelingToDate = finalWheelingToDate;
		this.genServiceDetails = genServiceDetails;
		this.infrastructure = infrastructure;
		this.tariff = tariff;
		this.availedHtSupply = availedHtSupply;
		this.availedHtSupplyNo = availedHtSupplyNo;
		this.availedSanctionedDemand = availedSanctionedDemand;
		this.scoTamilNadu = scoTamilNadu;
		this.scoMinistry = scoMinistry;
		this.scoCivil = scoCivil;
		this.finalCod = finalCod;
		this.finalCopd = finalCopd;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.enabled = enabled;
		this.meterNumber = meterNumber;
		this.meterMakeCode = meterMakeCode;
		this.meterMakeName = meterMakeName;
		this.accuracyClassCode = accuracyClassCode;
		this.accuracyClassName = accuracyClassName;
		this.isAbtMeter = isAbtMeter;
		this.multiplicationFactor = multiplicationFactor;
		this.modemNumber = modemNumber;
		this.meterCt1 = meterCt1;
		this.meterCt2 = meterCt2;
		this.meterCt3 = meterCt3;
		this.meterPt1 = meterPt1;
		this.meterPt2 = meterPt2;
		this.meterPt3 = meterPt3;
		this.parallelOperation = parallelOperation;
		this.genUnits = genUnits;
		this.transformers = transformers;
		this.captiveEnergyNeeds = captiveEnergyNeeds;
		this.checkList = checkList;
		this.applicationStatus = applicationStatus;
		this.idLoans = idLoans;
		this.clearance = clearance;
		this.supportFuelLinkageDetail = supportFuelLinkageDetail;
		this.idCaptiveUserContributions = idCaptiveUserContributions;
		this.captiveQuantumAllocation = captiveQuantumAllocation;
		this.idEquityShareVotingRights = idEquityShareVotingRights;
	}

	@Override
	public String toString() {
		return "GridConnectivity [id=" + id + ", generatingTypeName=" + generatingTypeName
				+ ", generatingTypeIsCaptive=" + generatingTypeIsCaptive + ", applnNumber=" + applnNumber
				+ ", plantName=" + plantName + ", plantTypeCode=" + plantTypeCode + ", plantTypeName=" + plantTypeName
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", companyName=" + companyName + ", appliedDate=" + appliedDate
				+ ", approvedDate=" + approvedDate + ", activatedDate=" + activatedDate + ", statusCode=" + statusCode
				+ ", statusName=" + statusName + ", line1=" + line1 + ", city=" + city + ", state=" + state
				+ ", pinCode=" + pinCode + ", village=" + village + ", town=" + town + ", taluk=" + taluk
				+ ", district=" + district + ", plSfNo=" + plSfNo + ", plVillage=" + plVillage + ", plPinCode="
				+ plPinCode + ", plTown=" + plTown + ", plTalukCode=" + plTalukCode + ", plTalukName=" + plTalukName
				+ ", plDistrictCode=" + plDistrictCode + ", plDistrictName=" + plDistrictName + ", plWindPassCode="
				+ plWindPassCode + ", plWindPassName=" + plWindPassName + ", plWindZoneArea=" + plWindZoneArea
				+ ", fuelCode=" + fuelCode + ", fuelLinkageArranged=" + fuelLinkageArranged + ", fuelLinkageDetails="
				+ fuelLinkageDetails + ", cgIndustryType=" + cgIndustryType + ", cgSupportFuel=" + cgSupportFuel
				+ ", cgIsParallelRun=" + cgIsParallelRun + ", cgIsStandBy=" + cgIsStandBy + ", cgCycle=" + cgCycle
				+ ", cgHasProof=" + cgHasProof + ", cgProofUrl=" + cgProofUrl + ", sfLinkageFuelSource="
				+ sfLinkageFuelSource + ", sfLinkageFrom=" + sfLinkageFrom + ", sfLinkageTo=" + sfLinkageTo
				+ ", sfRate=" + sfRate + ", sfDocUrl=" + sfDocUrl + ", classVoltagePhase=" + classVoltagePhase
				+ ", classVoltageFrequency=" + classVoltageFrequency + ", ssCode=" + ssCode + ", ssName=" + ssName
				+ ", ssId=" + ssId + ", ssVoltageCode=" + ssVoltageCode + ", ssVoltageName=" + ssVoltageName
				+ ", tempHtSupplyNumber=" + tempHtSupplyNumber + ", proposedCommissionDate=" + proposedCommissionDate
				+ ", plantCapacity=" + plantCapacity + ", annualExpectedQuantum=" + annualExpectedQuantum
				+ ", expectedCuf=" + expectedCuf + ", auxiliaryConsumption=" + auxiliaryConsumption
				+ ", industrialConsumption=" + industrialConsumption + ", perUnitCost=" + perUnitCost
				+ ", proposedPowerCaptive=" + proposedPowerCaptive + ", proposedPower3PT=" + proposedPower3PT
				+ ", proposedPowerSTB=" + proposedPowerSTB + ", firmPower=" + firmPower + ", infirmPower=" + infirmPower
				+ ", idTotalCost=" + idTotalCost + ", idTotalCurrency=" + idTotalCurrency + ", idTotalExchangeRate="
				+ idTotalExchangeRate + ", idproposedDebtEquityRatio=" + idproposedDebtEquityRatio
				+ ", idEBPrefShareCapAmt=" + idEBPrefShareCapAmt + ", idEBPrefShareCapPer=" + idEBPrefShareCapPer
				+ ", idEBEquityShareCapAmt=" + idEBEquityShareCapAmt + ", idEBEquityShareCapPer="
				+ idEBEquityShareCapPer + ", idEBPromEquityAmt=" + idEBPromEquityAmt + ", idEBPromEquityPer="
				+ idEBPromEquityPer + ", idPCPrefShareCapAmt=" + idPCPrefShareCapAmt + ", idPCPrefShareCapPer="
				+ idPCPrefShareCapPer + ", idPCEquityShareCapAmt=" + idPCEquityShareCapAmt + ", idPCEquityShareCapPer="
				+ idPCEquityShareCapPer + ", idCUCPrefShareCapAmt=" + idCUCPrefShareCapAmt + ", idCUCPrefShareCapPer="
				+ idCUCPrefShareCapPer + ", idCUCEquityShareCapAmt=" + idCUCEquityShareCapAmt
				+ ", idCUCEquityShareCapPer=" + idCUCEquityShareCapPer + ", idOwnPercPromoter=" + idOwnPercPromoter
				+ ", idOwnPercCaptive=" + idOwnPercCaptive + ", idOwnPercOwnRule=" + idOwnPercOwnRule
				+ ", genServiceNumber=" + genServiceNumber + ", genServiceApprovalNumber=" + genServiceApprovalNumber
				+ ", genServiceDate=" + genServiceDate + ", finalOrgId=" + finalOrgId + ", finalOrgName=" + finalOrgName
				+ ", finalOrgCode=" + finalOrgCode + ", finalSsTypeCode=" + finalSsTypeCode + ", finalSsTypeName="
				+ finalSsTypeName + ", finalSsId=" + finalSsId + ", finalSsName=" + finalSsName + ", finalSsCode="
				+ finalSsCode + ", finalFeederTypeCode=" + finalFeederTypeCode + ", finalFeederTypeName="
				+ finalFeederTypeName + ", finalFeederId=" + finalFeederId + ", finalFeederName=" + finalFeederName
				+ ", finalFeederCode=" + finalFeederCode + ", finalIsStb=" + finalIsStb + ", finalIsWheeling="
				+ finalIsWheeling + ", finalPpRate=" + finalPpRate + ", finalStbTariffOrder=" + finalStbTariffOrder
				+ ", finalStbTenderNumber=" + finalStbTenderNumber + ", finalStbTenderDate=" + finalStbTenderDate
				+ ", finalWheelingFromDate=" + finalWheelingFromDate + ", finalWheelingToDate=" + finalWheelingToDate
				+ ", genServiceDetails=" + genServiceDetails + ", infrastructure=" + infrastructure + ", tariff="
				+ tariff + ", availedHtSupply=" + availedHtSupply + ", availedHtSupplyNo=" + availedHtSupplyNo
				+ ", availedSanctionedDemand=" + availedSanctionedDemand + ", scoTamilNadu=" + scoTamilNadu
				+ ", scoMinistry=" + scoMinistry + ", scoCivil=" + scoCivil + ", finalCod=" + finalCod + ", finalCopd="
				+ finalCopd + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", enabled=" + enabled
				+ ", meterNumber=" + meterNumber + ", meterMakeCode=" + meterMakeCode + ", meterMakeName="
				+ meterMakeName + ", accuracyClassCode=" + accuracyClassCode + ", accuracyClassName="
				+ accuracyClassName + ", isAbtMeter=" + isAbtMeter + ", multiplicationFactor=" + multiplicationFactor
				+ ", modemNumber=" + modemNumber + ", meterCt1=" + meterCt1 + ", meterCt2=" + meterCt2 + ", meterCt3="
				+ meterCt3 + ", meterPt1=" + meterPt1 + ", meterPt2=" + meterPt2 + ", meterPt3=" + meterPt3
				+ ", parallelOperation=" + parallelOperation + ", genUnits=" + genUnits + ", transformers="
				+ transformers + ", captiveEnergyNeeds=" + captiveEnergyNeeds + ", checkList=" + checkList
				+ ", applicationStatus=" + applicationStatus + ", idLoans=" + idLoans + ", clearance=" + clearance
				+ ", supportFuelLinkageDetail=" + supportFuelLinkageDetail + ", idCaptiveUserContributions="
				+ idCaptiveUserContributions + ", captiveQuantumAllocation=" + captiveQuantumAllocation
				+ ", idEquityShareVotingRights=" + idEquityShareVotingRights + "]";
	}

}