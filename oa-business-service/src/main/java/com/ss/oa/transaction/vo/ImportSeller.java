package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name="IMPORT_SELLERS")
@CreationTimestamp @UpdateTimestamp
@Entity
public class ImportSeller {
   @Id
	@Column(name = "GENERATOR_SERVICE_NO_NEW")
	private String generatorServiceNoNew;
	@Column(name = "GENERATOR_SERVICE_NO_OLD")
	private String generatorServiceNoOld;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "EDC_NAME")
	private String edcName;
	@Column(name = "M_ORG_ID")
	private String mOrgId;
	@Column(name = "GEN_MAKE_NAME_OA")
	private String genMakeNameOa;
	@Column(name = "GEN_MAKE_CODE")
	private String genMakeCode;
	@Column(name = "NO_OF_GEN_UNITS")
	private String noOfGenUnits;
	@Column(name = "GEN_UNIT_CAPACITY")
	private String genUnitCapacity;
	@Column(name = "TOTAL_CAPACITY_KW")
	private String totalCapacityKw;
	@Column(name = "PT_RATIO")
	private String ptRatio;
	@Column(name = "CT_RATIO")
	private String ctRatio;
	@Column(name = "MF")
	private String mf;
	@Column(name = "SF_NO")
	private String sfNo;
	@Column(name = "VILLAGE")
	private String village;
	@Column(name = "TURBINE_SL_NO")
	private String turbineSlNo;
	@Column(name = "TURBINE_ROTOR_DIA")
	private String turbineRotorDia;
	@Column(name = "TURBINE_HUB_HEIGHT")
	private String turbineHubHe;
	@Column(name = "SS_NAME_IN_OA")
	private String ssNameInOa;
	@Column(name = "M_SUBSTATION_ID")
	private String mSubstationId;
	@Column(name = "NATURE_OF_BOARD")
	private String natureOfBoard;
	@Column(name = "FEEDER_NAME_IN_OA")
	private String feederNameInOa;
	@Column(name = "M_FEEDER_ID")
	private String mFeederId;
	@Column(name = "INJECTION_VOLTAGE")
	private String injectionVoltage;
	@Column(name = "VOLTAGE_CODE")
	private String voltageCode;
	@Column(name = "METER_MAKE")
	private String meterMake;
	@Column(name = "METER_MAKE_CODE")
	private String meterMakeCode;
	@Column(name = "METER_NUMBER")
	private String meterNumber;
	@Column(name = "IS_DLMS")
	private String isDlms;
	@Column(name = "IS_ABT")
	private String isAbt;
	@Column(name = "is_REC")
	private String isRec;
	@Column(name = "WEG_GROUP_NAME")
	private String wegGroupName;
	@Column(name = "MODEM_NO")
	private String modemNo;
	@Column(name = "PURPOSE")
	private String purpose;
	@Column(name = "COMMISSION_DATE_STR")
	private String commissionDateStr;
	@Column(name = "ACCURACY_CLASS")
	private String accuracyClass;
	@Column(name = "WIND_PASS_NAME")
	private String windPassName;
	@Column(name = "REMARKS")
	private String remarks;
	@Column(name = "IMPORTED")
	private String imported;
	@Column(name = "COMMISSION_DATE")
	private LocalDate commisionDate;
	@Column(name = "WIND_PASS_CODE")
	private String windPassCode;
	@Column(name = "WEG_GROUP_CODE")
	private String wegGroupCode;
	@Column(name = "IMPORT_REMARKS")
	private String importRemarks;
	@Column(name = "CLEAN_REC")
	private String cleanRec;
	@Column(name = "FUEL")
	private String fuel;
	@Column(name = "FUEl_CODE")
	private String fuelCode;
	@Column(name = "SURPLUS_ENERGY")
	private String surplusEnergy;
	@Column(name = "ACCELERATED_DEPRECIATION")
	private String acceleratedDepreciation;
	@Column(name = "FLOW_TYPE_CODE")
	private String flowTypeCode;
	
	public ImportSeller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImportSeller(String generatorServiceNoOld, String generatorServiceNoNew, String companyName, String edcName,
			String mOrgId, String genMakeNameOa, String genMakeCode, String noOfGenUnits, String genUnitCapacity,
			String totalCapacityKw, String ptRatio, String ctRatio, String mf, String sfNo, String village,
			String turbineSlNo, String turbineRotorDia, String turbineHubHe, String ssNameInOa, String mSubstationId,
			String natureOfBoard, String feederNameInOa, String mFeederId, String injectionVoltage, String voltageCode,
			String meterMake, String meterMakeCode, String meterNumber, String isDlms, String isAbt, String isRec,
			String wegGroupName, String modemNo, String purpose, String commissionDateStr, String accuracyClass,
			String windPassName, String remarks, String imported, LocalDate commisionDate, String windPassCode,
			String wegGroupCode, String importRemarks, String cleanRec, String fuel, String fuelCode,
			String surplusEnergy, String acceleratedDepreciation, String flowTypeCode) {
		super();
		this.generatorServiceNoOld = generatorServiceNoOld;
		this.generatorServiceNoNew = generatorServiceNoNew;
		this.companyName = companyName;
		this.edcName = edcName;
		this.mOrgId = mOrgId;
		this.genMakeNameOa = genMakeNameOa;
		this.genMakeCode = genMakeCode;
		this.noOfGenUnits = noOfGenUnits;
		this.genUnitCapacity = genUnitCapacity;
		this.totalCapacityKw = totalCapacityKw;
		this.ptRatio = ptRatio;
		this.ctRatio = ctRatio;
		this.mf = mf;
		this.sfNo = sfNo;
		this.village = village;
		this.turbineSlNo = turbineSlNo;
		this.turbineRotorDia = turbineRotorDia;
		this.turbineHubHe = turbineHubHe;
		this.ssNameInOa = ssNameInOa;
		this.mSubstationId = mSubstationId;
		this.natureOfBoard = natureOfBoard;
		this.feederNameInOa = feederNameInOa;
		this.mFeederId = mFeederId;
		this.injectionVoltage = injectionVoltage;
		this.voltageCode = voltageCode;
		this.meterMake = meterMake;
		this.meterMakeCode = meterMakeCode;
		this.meterNumber = meterNumber;
		this.isDlms = isDlms;
		this.isAbt = isAbt;
		this.isRec = isRec;
		this.wegGroupName = wegGroupName;
		this.modemNo = modemNo;
		this.purpose = purpose;
		this.commissionDateStr = commissionDateStr;
		this.accuracyClass = accuracyClass;
		this.windPassName = windPassName;
		this.remarks = remarks;
		this.imported = imported;
		this.commisionDate = commisionDate;
		this.windPassCode = windPassCode;
		this.wegGroupCode = wegGroupCode;
		this.importRemarks = importRemarks;
		this.cleanRec = cleanRec;
		this.fuel = fuel;
		this.fuelCode = fuelCode;
		this.surplusEnergy = surplusEnergy;
		this.acceleratedDepreciation = acceleratedDepreciation;
		this.flowTypeCode = flowTypeCode;
	}

	public String getGeneratorServiceNoOld() {
		return generatorServiceNoOld;
	}

	public void setGeneratorServiceNoOld(String generatorServiceNoOld) {
		this.generatorServiceNoOld = generatorServiceNoOld;
	}

	public String getGeneratorServiceNoNew() {
		return generatorServiceNoNew;
	}

	public void setGeneratorServiceNoNew(String generatorServiceNoNew) {
		this.generatorServiceNoNew = generatorServiceNoNew;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEdcName() {
		return edcName;
	}

	public void setEdcName(String edcName) {
		this.edcName = edcName;
	}

	public String getmOrgId() {
		return mOrgId;
	}

	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}

	public String getGenMakeNameOa() {
		return genMakeNameOa;
	}

	public void setGenMakeNameOa(String genMakeNameOa) {
		this.genMakeNameOa = genMakeNameOa;
	}

	public String getGenMakeCode() {
		return genMakeCode;
	}

	public void setGenMakeCode(String genMakeCode) {
		this.genMakeCode = genMakeCode;
	}

	public String getNoOfGenUnits() {
		return noOfGenUnits;
	}

	public void setNoOfGenUnits(String noOfGenUnits) {
		this.noOfGenUnits = noOfGenUnits;
	}

	public String getGenUnitCapacity() {
		return genUnitCapacity;
	}

	public void setGenUnitCapacity(String genUnitCapacity) {
		this.genUnitCapacity = genUnitCapacity;
	}

	public String getTotalCapacityKw() {
		return totalCapacityKw;
	}

	public void setTotalCapacityKw(String totalCapacityKw) {
		this.totalCapacityKw = totalCapacityKw;
	}

	public String getPtRatio() {
		return ptRatio;
	}

	public void setPtRatio(String ptRatio) {
		this.ptRatio = ptRatio;
	}

	public String getCtRatio() {
		return ctRatio;
	}

	public void setCtRatio(String ctRatio) {
		this.ctRatio = ctRatio;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getSfNo() {
		return sfNo;
	}

	public void setSfNo(String sfNo) {
		this.sfNo = sfNo;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
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

	public String getTurbineHubHe() {
		return turbineHubHe;
	}

	public void setTurbineHubHe(String turbineHubHe) {
		this.turbineHubHe = turbineHubHe;
	}

	public String getSsNameInOa() {
		return ssNameInOa;
	}

	public void setSsNameInOa(String ssNameInOa) {
		this.ssNameInOa = ssNameInOa;
	}

	public String getmSubstationId() {
		return mSubstationId;
	}

	public void setmSubstationId(String mSubstationId) {
		this.mSubstationId = mSubstationId;
	}

	public String getNatureOfBoard() {
		return natureOfBoard;
	}

	public void setNatureOfBoard(String natureOfBoard) {
		this.natureOfBoard = natureOfBoard;
	}

	public String getFeederNameInOa() {
		return feederNameInOa;
	}

	public void setFeederNameInOa(String feederNameInOa) {
		this.feederNameInOa = feederNameInOa;
	}

	public String getmFeederId() {
		return mFeederId;
	}

	public void setmFeederId(String mFeederId) {
		this.mFeederId = mFeederId;
	}

	public String getInjectionVoltage() {
		return injectionVoltage;
	}

	public void setInjectionVoltage(String injectionVoltage) {
		this.injectionVoltage = injectionVoltage;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public String getMeterMake() {
		return meterMake;
	}

	public void setMeterMake(String meterMake) {
		this.meterMake = meterMake;
	}

	public String getMeterMakeCode() {
		return meterMakeCode;
	}

	public void setMeterMakeCode(String meterMakeCode) {
		this.meterMakeCode = meterMakeCode;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getIsDlms() {
		return isDlms;
	}

	public void setIsDlms(String isDlms) {
		this.isDlms = isDlms;
	}

	public String getIsAbt() {
		return isAbt;
	}

	public void setIsAbt(String isAbt) {
		this.isAbt = isAbt;
	}

	public String getIsRec() {
		return isRec;
	}

	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}

	public String getWegGroupName() {
		return wegGroupName;
	}

	public void setWegGroupName(String wegGroupName) {
		this.wegGroupName = wegGroupName;
	}

	public String getModemNo() {
		return modemNo;
	}

	public void setModemNo(String modemNo) {
		this.modemNo = modemNo;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCommissionDateStr() {
		return commissionDateStr;
	}

	public void setCommissionDateStr(String commissionDateStr) {
		this.commissionDateStr = commissionDateStr;
	}

	public String getAccuracyClass() {
		return accuracyClass;
	}

	public void setAccuracyClass(String accuracyClass) {
		this.accuracyClass = accuracyClass;
	}

	public String getWindPassName() {
		return windPassName;
	}

	public void setWindPassName(String windPassName) {
		this.windPassName = windPassName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public LocalDate getCommisionDate() {
		return commisionDate;
	}

	public void setCommisionDate(LocalDate commisionDate) {
		this.commisionDate = commisionDate;
	}

	public String getWindPassCode() {
		return windPassCode;
	}

	public void setWindPassCode(String windPassCode) {
		this.windPassCode = windPassCode;
	}

	public String getWegGroupCode() {
		return wegGroupCode;
	}

	public void setWegGroupCode(String wegGroupCode) {
		this.wegGroupCode = wegGroupCode;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getCleanRec() {
		return cleanRec;
	}

	public void setCleanRec(String cleanRec) {
		this.cleanRec = cleanRec;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getFuelCode() {
		return fuelCode;
	}

	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}

	public String getSurplusEnergy() {
		return surplusEnergy;
	}

	public void setSurplusEnergy(String surplusEnergy) {
		this.surplusEnergy = surplusEnergy;
	}

	public String getAcceleratedDepreciation() {
		return acceleratedDepreciation;
	}

	public void setAcceleratedDepreciation(String acceleratedDepreciation) {
		this.acceleratedDepreciation = acceleratedDepreciation;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	@Override
	public String toString() {
		return "ImportSeller [generatorServiceNoOld=" + generatorServiceNoOld + ", generatorServiceNoNew="
				+ generatorServiceNoNew + ", companyName=" + companyName + ", edcName=" + edcName + ", mOrgId=" + mOrgId
				+ ", genMakeNameOa=" + genMakeNameOa + ", genMakeCode=" + genMakeCode + ", noOfGenUnits=" + noOfGenUnits
				+ ", genUnitCapacity=" + genUnitCapacity + ", totalCapacityKw=" + totalCapacityKw + ", ptRatio="
				+ ptRatio + ", ctRatio=" + ctRatio + ", mf=" + mf + ", sfNo=" + sfNo + ", village=" + village
				+ ", turbineSlNo=" + turbineSlNo + ", turbineRotorDia=" + turbineRotorDia + ", turbineHubHe="
				+ turbineHubHe + ", ssNameInOa=" + ssNameInOa + ", mSubstationId=" + mSubstationId + ", natureOfBoard="
				+ natureOfBoard + ", feederNameInOa=" + feederNameInOa + ", mFeederId=" + mFeederId
				+ ", injectionVoltage=" + injectionVoltage + ", voltageCode=" + voltageCode + ", meterMake=" + meterMake
				+ ", meterMakeCode=" + meterMakeCode + ", meterNumber=" + meterNumber + ", isDlms=" + isDlms
				+ ", isAbt=" + isAbt + ", isRec=" + isRec + ", wegGroupName=" + wegGroupName + ", modemNo=" + modemNo
				+ ", purpose=" + purpose + ", commissionDateStr=" + commissionDateStr + ", accuracyClass="
				+ accuracyClass + ", windPassName=" + windPassName + ", remarks=" + remarks + ", imported=" + imported
				+ ", commisionDate=" + commisionDate + ", windPassCode=" + windPassCode + ", wegGroupCode="
				+ wegGroupCode + ", importRemarks=" + importRemarks + ", cleanRec=" + cleanRec + ", fuel=" + fuel
				+ ", fuelCode=" + fuelCode + ", surplusEnergy=" + surplusEnergy + ", acceleratedDepreciation="
				+ acceleratedDepreciation + ", flowTypeCode=" + flowTypeCode + "]";
	}
	
}
