package com.ss.oa.oadocumentservice.vo;

import java.util.List;

public class SldcNoc {
	
	private String id;
	private String nocPurpose;
	private String nocCode;
	private String appCategory;
	private String status;
	private String appliedNo;
	private String appliedDt;
	private String periodStartDate;
	private String periodEndDate;
	private String compServNo;
	private String compName;
	private String plantAdr;
	private String commsAdr;
	private String installCapPlant;
	private String intlAuxilary;
	private String intlInhouseConsump;
	private String exBusAval;
	private String approvPwrEvacCap;
	private String fuelType;
	private String feederWithVoltLevel;
	private String substationWithVoltLevel;
	private String gridApprovalDt;
	private String codDt;
	private String isAbtAndAmr;
	private String isMeterDownloadAndInstall;
	private String abyMeterName;
	private String meterMfName;
	private String ctRatioAndClassAcc;
	private String ptRatioAndClassAcc;
	private String tnebTangedcoThrowPpa;
	private String isDataMonitoringWithSldc;
	private String isCommitToTangedco;
	private String ccArrearsAval;
	private String isComplyWithOaRequirment;
	private String techRemark;
	private String legalRemark;
	private String commRemark;
	private String isExisting;
	private String tarrif;
	private String cntrctDemand;
	private String region;
	private String edc;
	private String section;
	private String extgTotalCommit;
	private String connFeederAndVoltLevel;
	private String connSubstationAndVoltLevel;
	private String existingKv;
	private String existingFeederType;
	private String newTotalCommit;
	private String eligibleQuantumInWv;
	private String isHtConPermitForOa;
	private String dirRemark;
	private String dirReason;
	private String ceRemark;
	private String ceReason;
	private String quantity;
	private String tariff;
	private List<SldcNocLine> sldcNocLine;
	
	public SldcNoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SldcNoc(String id, String nocPurpose, String nocCode, String appCategory, String status, String appliedNo,
			String appliedDt, String periodStartDate, String periodEndDate, String compServNo, String compName,
			String plantAdr, String commsAdr, String installCapPlant, String intlAuxilary, String intlInhouseConsump,
			String exBusAval, String approvPwrEvacCap, String fuelType, String feederWithVoltLevel,
			String substationWithVoltLevel, String gridApprovalDt, String codDt, String isAbtAndAmr,
			String isMeterDownloadAndInstall, String abyMeterName, String meterMfName, String ctRatioAndClassAcc,
			String ptRatioAndClassAcc, String tnebTangedcoThrowPpa, String isDataMonitoringWithSldc,
			String isCommitToTangedco, String ccArrearsAval, String isComplyWithOaRequirment, String techRemark,
			String legalRemark, String commRemark, String isExisting, String tarrif, String cntrctDemand, String region,
			String edc, String section, String extgTotalCommit, String connFeederAndVoltLevel,
			String connSubstationAndVoltLevel, String existingKv, String existingFeederType, String newTotalCommit,
			String eligibleQuantumInWv, String isHtConPermitForOa, String dirRemark, String dirReason, String ceRemark,
			String ceReason, String quantity, String tariff, List<SldcNocLine> sldcNocLine) {
		super();
		this.id = id;
		this.nocPurpose = nocPurpose;
		this.nocCode = nocCode;
		this.appCategory = appCategory;
		this.status = status;
		this.appliedNo = appliedNo;
		this.appliedDt = appliedDt;
		this.periodStartDate = periodStartDate;
		this.periodEndDate = periodEndDate;
		this.compServNo = compServNo;
		this.compName = compName;
		this.plantAdr = plantAdr;
		this.commsAdr = commsAdr;
		this.installCapPlant = installCapPlant;
		this.intlAuxilary = intlAuxilary;
		this.intlInhouseConsump = intlInhouseConsump;
		this.exBusAval = exBusAval;
		this.approvPwrEvacCap = approvPwrEvacCap;
		this.fuelType = fuelType;
		this.feederWithVoltLevel = feederWithVoltLevel;
		this.substationWithVoltLevel = substationWithVoltLevel;
		this.gridApprovalDt = gridApprovalDt;
		this.codDt = codDt;
		this.isAbtAndAmr = isAbtAndAmr;
		this.isMeterDownloadAndInstall = isMeterDownloadAndInstall;
		this.abyMeterName = abyMeterName;
		this.meterMfName = meterMfName;
		this.ctRatioAndClassAcc = ctRatioAndClassAcc;
		this.ptRatioAndClassAcc = ptRatioAndClassAcc;
		this.tnebTangedcoThrowPpa = tnebTangedcoThrowPpa;
		this.isDataMonitoringWithSldc = isDataMonitoringWithSldc;
		this.isCommitToTangedco = isCommitToTangedco;
		this.ccArrearsAval = ccArrearsAval;
		this.isComplyWithOaRequirment = isComplyWithOaRequirment;
		this.techRemark = techRemark;
		this.legalRemark = legalRemark;
		this.commRemark = commRemark;
		this.isExisting = isExisting;
		this.tarrif = tarrif;
		this.cntrctDemand = cntrctDemand;
		this.region = region;
		this.edc = edc;
		this.section = section;
		this.extgTotalCommit = extgTotalCommit;
		this.connFeederAndVoltLevel = connFeederAndVoltLevel;
		this.connSubstationAndVoltLevel = connSubstationAndVoltLevel;
		this.existingKv = existingKv;
		this.existingFeederType = existingFeederType;
		this.newTotalCommit = newTotalCommit;
		this.eligibleQuantumInWv = eligibleQuantumInWv;
		this.isHtConPermitForOa = isHtConPermitForOa;
		this.dirRemark = dirRemark;
		this.dirReason = dirReason;
		this.ceRemark = ceRemark;
		this.ceReason = ceReason;
		this.quantity = quantity;
		this.tariff = tariff;
		this.sldcNocLine = sldcNocLine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNocPurpose() {
		return nocPurpose;
	}

	public void setNocPurpose(String nocPurpose) {
		this.nocPurpose = nocPurpose;
	}

	public String getNocCode() {
		return nocCode;
	}

	public void setNocCode(String nocCode) {
		this.nocCode = nocCode;
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppliedNo() {
		return appliedNo;
	}

	public void setAppliedNo(String appliedNo) {
		this.appliedNo = appliedNo;
	}

	public String getAppliedDt() {
		return appliedDt;
	}

	public void setAppliedDt(String appliedDt) {
		this.appliedDt = appliedDt;
	}

	public String getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	public String getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	public String getCompServNo() {
		return compServNo;
	}

	public void setCompServNo(String compServNo) {
		this.compServNo = compServNo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPlantAdr() {
		return plantAdr;
	}

	public void setPlantAdr(String plantAdr) {
		this.plantAdr = plantAdr;
	}

	public String getCommsAdr() {
		return commsAdr;
	}

	public void setCommsAdr(String commsAdr) {
		this.commsAdr = commsAdr;
	}

	public String getInstallCapPlant() {
		return installCapPlant;
	}

	public void setInstallCapPlant(String installCapPlant) {
		this.installCapPlant = installCapPlant;
	}

	public String getIntlAuxilary() {
		return intlAuxilary;
	}

	public void setIntlAuxilary(String intlAuxilary) {
		this.intlAuxilary = intlAuxilary;
	}

	public String getIntlInhouseConsump() {
		return intlInhouseConsump;
	}

	public void setIntlInhouseConsump(String intlInhouseConsump) {
		this.intlInhouseConsump = intlInhouseConsump;
	}

	public String getExBusAval() {
		return exBusAval;
	}

	public void setExBusAval(String exBusAval) {
		this.exBusAval = exBusAval;
	}

	public String getApprovPwrEvacCap() {
		return approvPwrEvacCap;
	}

	public void setApprovPwrEvacCap(String approvPwrEvacCap) {
		this.approvPwrEvacCap = approvPwrEvacCap;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getFeederWithVoltLevel() {
		return feederWithVoltLevel;
	}

	public void setFeederWithVoltLevel(String feederWithVoltLevel) {
		this.feederWithVoltLevel = feederWithVoltLevel;
	}

	public String getSubstationWithVoltLevel() {
		return substationWithVoltLevel;
	}

	public void setSubstationWithVoltLevel(String substationWithVoltLevel) {
		this.substationWithVoltLevel = substationWithVoltLevel;
	}

	public String getGridApprovalDt() {
		return gridApprovalDt;
	}

	public void setGridApprovalDt(String gridApprovalDt) {
		this.gridApprovalDt = gridApprovalDt;
	}

	public String getCodDt() {
		return codDt;
	}

	public void setCodDt(String codDt) {
		this.codDt = codDt;
	}

	public String getIsAbtAndAmr() {
		return isAbtAndAmr;
	}

	public void setIsAbtAndAmr(String isAbtAndAmr) {
		this.isAbtAndAmr = isAbtAndAmr;
	}

	public String getIsMeterDownloadAndInstall() {
		return isMeterDownloadAndInstall;
	}

	public void setIsMeterDownloadAndInstall(String isMeterDownloadAndInstall) {
		this.isMeterDownloadAndInstall = isMeterDownloadAndInstall;
	}

	public String getAbyMeterName() {
		return abyMeterName;
	}

	public void setAbyMeterName(String abyMeterName) {
		this.abyMeterName = abyMeterName;
	}

	public String getMeterMfName() {
		return meterMfName;
	}

	public void setMeterMfName(String meterMfName) {
		this.meterMfName = meterMfName;
	}

	public String getCtRatioAndClassAcc() {
		return ctRatioAndClassAcc;
	}

	public void setCtRatioAndClassAcc(String ctRatioAndClassAcc) {
		this.ctRatioAndClassAcc = ctRatioAndClassAcc;
	}

	public String getPtRatioAndClassAcc() {
		return ptRatioAndClassAcc;
	}

	public void setPtRatioAndClassAcc(String ptRatioAndClassAcc) {
		this.ptRatioAndClassAcc = ptRatioAndClassAcc;
	}

	public String getTnebTangedcoThrowPpa() {
		return tnebTangedcoThrowPpa;
	}

	public void setTnebTangedcoThrowPpa(String tnebTangedcoThrowPpa) {
		this.tnebTangedcoThrowPpa = tnebTangedcoThrowPpa;
	}

	public String getIsDataMonitoringWithSldc() {
		return isDataMonitoringWithSldc;
	}

	public void setIsDataMonitoringWithSldc(String isDataMonitoringWithSldc) {
		this.isDataMonitoringWithSldc = isDataMonitoringWithSldc;
	}

	public String getIsCommitToTangedco() {
		return isCommitToTangedco;
	}

	public void setIsCommitToTangedco(String isCommitToTangedco) {
		this.isCommitToTangedco = isCommitToTangedco;
	}

	public String getCcArrearsAval() {
		return ccArrearsAval;
	}

	public void setCcArrearsAval(String ccArrearsAval) {
		this.ccArrearsAval = ccArrearsAval;
	}

	public String getIsComplyWithOaRequirment() {
		return isComplyWithOaRequirment;
	}

	public void setIsComplyWithOaRequirment(String isComplyWithOaRequirment) {
		this.isComplyWithOaRequirment = isComplyWithOaRequirment;
	}

	public String getTechRemark() {
		return techRemark;
	}

	public void setTechRemark(String techRemark) {
		this.techRemark = techRemark;
	}

	public String getLegalRemark() {
		return legalRemark;
	}

	public void setLegalRemark(String legalRemark) {
		this.legalRemark = legalRemark;
	}

	public String getCommRemark() {
		return commRemark;
	}

	public void setCommRemark(String commRemark) {
		this.commRemark = commRemark;
	}

	public String getIsExisting() {
		return isExisting;
	}

	public void setIsExisting(String isExisting) {
		this.isExisting = isExisting;
	}

	public String getTarrif() {
		return tarrif;
	}

	public void setTarrif(String tarrif) {
		this.tarrif = tarrif;
	}

	public String getCntrctDemand() {
		return cntrctDemand;
	}

	public void setCntrctDemand(String cntrctDemand) {
		this.cntrctDemand = cntrctDemand;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEdc() {
		return edc;
	}

	public void setEdc(String edc) {
		this.edc = edc;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getExtgTotalCommit() {
		return extgTotalCommit;
	}

	public void setExtgTotalCommit(String extgTotalCommit) {
		this.extgTotalCommit = extgTotalCommit;
	}

	public String getConnFeederAndVoltLevel() {
		return connFeederAndVoltLevel;
	}

	public void setConnFeederAndVoltLevel(String connFeederAndVoltLevel) {
		this.connFeederAndVoltLevel = connFeederAndVoltLevel;
	}

	public String getConnSubstationAndVoltLevel() {
		return connSubstationAndVoltLevel;
	}

	public void setConnSubstationAndVoltLevel(String connSubstationAndVoltLevel) {
		this.connSubstationAndVoltLevel = connSubstationAndVoltLevel;
	}

	public String getExistingKv() {
		return existingKv;
	}

	public void setExistingKv(String existingKv) {
		this.existingKv = existingKv;
	}

	public String getExistingFeederType() {
		return existingFeederType;
	}

	public void setExistingFeederType(String existingFeederType) {
		this.existingFeederType = existingFeederType;
	}

	public String getNewTotalCommit() {
		return newTotalCommit;
	}

	public void setNewTotalCommit(String newTotalCommit) {
		this.newTotalCommit = newTotalCommit;
	}

	public String getEligibleQuantumInWv() {
		return eligibleQuantumInWv;
	}

	public void setEligibleQuantumInWv(String eligibleQuantumInWv) {
		this.eligibleQuantumInWv = eligibleQuantumInWv;
	}

	public String getIsHtConPermitForOa() {
		return isHtConPermitForOa;
	}

	public void setIsHtConPermitForOa(String isHtConPermitForOa) {
		this.isHtConPermitForOa = isHtConPermitForOa;
	}

	public String getDirRemark() {
		return dirRemark;
	}

	public void setDirRemark(String dirRemark) {
		this.dirRemark = dirRemark;
	}

	public String getDirReason() {
		return dirReason;
	}

	public void setDirReason(String dirReason) {
		this.dirReason = dirReason;
	}

	public String getCeRemark() {
		return ceRemark;
	}

	public void setCeRemark(String ceRemark) {
		this.ceRemark = ceRemark;
	}

	public String getCeReason() {
		return ceReason;
	}

	public void setCeReason(String ceReason) {
		this.ceReason = ceReason;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public List<SldcNocLine> getSldcNocLine() {
		return sldcNocLine;
	}

	public void setSldcNocLine(List<SldcNocLine> sldcNocLine) {
		this.sldcNocLine = sldcNocLine;
	}

	@Override
	public String toString() {
		return "SldcNoc [id=" + id + ", nocPurpose=" + nocPurpose + ", nocCode=" + nocCode + ", appCategory="
				+ appCategory + ", status=" + status + ", appliedNo=" + appliedNo + ", appliedDt=" + appliedDt
				+ ", periodStartDate=" + periodStartDate + ", periodEndDate=" + periodEndDate + ", compServNo="
				+ compServNo + ", compName=" + compName + ", plantAdr=" + plantAdr + ", commsAdr=" + commsAdr
				+ ", installCapPlant=" + installCapPlant + ", intlAuxilary=" + intlAuxilary + ", intlInhouseConsump="
				+ intlInhouseConsump + ", exBusAval=" + exBusAval + ", approvPwrEvacCap=" + approvPwrEvacCap
				+ ", fuelType=" + fuelType + ", feederWithVoltLevel=" + feederWithVoltLevel
				+ ", substationWithVoltLevel=" + substationWithVoltLevel + ", gridApprovalDt=" + gridApprovalDt
				+ ", codDt=" + codDt + ", isAbtAndAmr=" + isAbtAndAmr + ", isMeterDownloadAndInstall="
				+ isMeterDownloadAndInstall + ", abyMeterName=" + abyMeterName + ", meterMfName=" + meterMfName
				+ ", ctRatioAndClassAcc=" + ctRatioAndClassAcc + ", ptRatioAndClassAcc=" + ptRatioAndClassAcc
				+ ", tnebTangedcoThrowPpa=" + tnebTangedcoThrowPpa + ", isDataMonitoringWithSldc="
				+ isDataMonitoringWithSldc + ", isCommitToTangedco=" + isCommitToTangedco + ", ccArrearsAval="
				+ ccArrearsAval + ", isComplyWithOaRequirment=" + isComplyWithOaRequirment + ", techRemark="
				+ techRemark + ", legalRemark=" + legalRemark + ", commRemark=" + commRemark + ", isExisting="
				+ isExisting + ", tarrif=" + tarrif + ", cntrctDemand=" + cntrctDemand + ", region=" + region + ", edc="
				+ edc + ", section=" + section + ", extgTotalCommit=" + extgTotalCommit + ", connFeederAndVoltLevel="
				+ connFeederAndVoltLevel + ", connSubstationAndVoltLevel=" + connSubstationAndVoltLevel
				+ ", existingKv=" + existingKv + ", existingFeederType=" + existingFeederType + ", newTotalCommit="
				+ newTotalCommit + ", eligibleQuantumInWv=" + eligibleQuantumInWv + ", isHtConPermitForOa="
				+ isHtConPermitForOa + ", dirRemark=" + dirRemark + ", dirReason=" + dirReason + ", ceRemark="
				+ ceRemark + ", ceReason=" + ceReason + ", quantity=" + quantity + ", tariff=" + tariff
				+ ", sldcNocLine=" + sldcNocLine + "]";
	}

	

}