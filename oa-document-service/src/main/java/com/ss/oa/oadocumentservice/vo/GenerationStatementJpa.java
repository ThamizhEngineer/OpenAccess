package com.ss.oa.oadocumentservice.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "T_GEN_STMT")
public class GenerationStatementJpa {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String statusCode;
	@Transient
	private String statusName;
	private String remarks;
	private String mCompanyMeterId;	
	@Transient
	private String companyMeterNumber;
	private String refNumber;
	@Transient
	private String mrIds;
	private String mf;
	private String machineCapacity;
	private String commissionDate;
	private String stmtGenDate;
	private String stmtMonth;
	private String stmtYear;
	private String initStmtDt;
	private String finalStmtDt;
	private String rkvahInit;
	private String rkvahFinal;
	private String rkvahDiff;
	private String rkvahUnits;
	private String kvahInit;
	private String kvahFinal;
	private String kvahDiff;
	private String kvahUnits;
	private String totalImportGen;
	private String totalExportGen;
	private String mOrgId;
	private String dispOrgName;
	private String dispOrgCode;
	private String mCompanyId;
	private String dispCompanyName;
	@Transient
	private String companyCode;
	private String mCompanyServiceId;
	private String dispServiceNumber;
	private String injectingVoltageCode;
	private String injectingVoltageDesc;
	private String powerFactor;
	private String netGeneration;
	private String totalChargedAmount;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String fileName;
	private String isCaptive;
	private String isStb;
	private String plantClassTypeCode;
	private String plantClassTypeDesc;
	private String tariffRate;
	private String tariffNetAmount;
	private String netPayable;
	private String flowTypeCode;
//	@Formula("(SELECT fuel.FUEL_NAME FROM T_GEN_STMT gs JOIN M_FUEL fuel on gs.DISP_FUEL_TYPE_CODE = fuel.FUEL_CODE WHERE gs.ID=id)")
	@Transient
	private String flowTypeName;
	private String typeOfSs;
	private String isRec;
	private String totalSsLoss;
	private String ssLossPercent;
	private String mSubstationId;
	private String mSubstationName;
	private String isMeterChange;
	private String docId;
	private String stmtRemarks;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="T_GEN_STMT_ID")
	private List<GenerationStatementChargeJpa> generationStatementChargesJpa;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="T_GEN_STMT_ID")
	private List<GenerationStatementSlotJpa> generationStatementSlotsJpa;
	
	public GenerationStatementJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerationStatementJpa(String id, String statusCode, String statusName, String remarks,
			String mCompanyMeterId, String companyMeterNumber, String refNumber, String mrIds, String mf,
			String machineCapacity, String commissionDate, String stmtGenDate, String stmtMonth, String stmtYear,
			String initStmtDt, String finalStmtDt, String rkvahInit, String rkvahFinal, String rkvahDiff,
			String rkvahUnits, String kvahInit, String kvahFinal, String kvahDiff, String kvahUnits,
			String totalImportGen, String totalExportGen, String mOrgId, String dispOrgName, String dispOrgCode,
			String mCompanyId, String dispCompanyName, String companyCode, String mCompanyServiceId,
			String dispServiceNumber, String injectingVoltageCode, String injectingVoltageDesc, String powerFactor,
			String netGeneration, String totalChargedAmount, String c1, String c2, String c3, String c4, String c5,
			String fileName, String isCaptive, String isStb, String plantClassTypeCode, String plantClassTypeDesc,
			String tariffRate, String tariffNetAmount, String netPayable, String flowTypeCode, String flowTypeName,
			String typeOfSs, String isRec, String totalSsLoss, String ssLossPercent, String mSubstationId,
			String mSubstationName, String isMeterChange, String docId, String stmtRemarks,
			List<GenerationStatementChargeJpa> generationStatementChargesJpa,
			List<GenerationStatementSlotJpa> generationStatementSlotsJpa) {
		super();
		this.id = id;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.remarks = remarks;
		this.mCompanyMeterId = mCompanyMeterId;
		this.companyMeterNumber = companyMeterNumber;
		this.refNumber = refNumber;
		this.mrIds = mrIds;
		this.mf = mf;
		this.machineCapacity = machineCapacity;
		this.commissionDate = commissionDate;
		this.stmtGenDate = stmtGenDate;
		this.stmtMonth = stmtMonth;
		this.stmtYear = stmtYear;
		this.initStmtDt = initStmtDt;
		this.finalStmtDt = finalStmtDt;
		this.rkvahInit = rkvahInit;
		this.rkvahFinal = rkvahFinal;
		this.rkvahDiff = rkvahDiff;
		this.rkvahUnits = rkvahUnits;
		this.kvahInit = kvahInit;
		this.kvahFinal = kvahFinal;
		this.kvahDiff = kvahDiff;
		this.kvahUnits = kvahUnits;
		this.totalImportGen = totalImportGen;
		this.totalExportGen = totalExportGen;
		this.mOrgId = mOrgId;
		this.dispOrgName = dispOrgName;
		this.dispOrgCode = dispOrgCode;
		this.mCompanyId = mCompanyId;
		this.dispCompanyName = dispCompanyName;
		this.companyCode = companyCode;
		this.mCompanyServiceId = mCompanyServiceId;
		this.dispServiceNumber = dispServiceNumber;
		this.injectingVoltageCode = injectingVoltageCode;
		this.injectingVoltageDesc = injectingVoltageDesc;
		this.powerFactor = powerFactor;
		this.netGeneration = netGeneration;
		this.totalChargedAmount = totalChargedAmount;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.fileName = fileName;
		this.isCaptive = isCaptive;
		this.isStb = isStb;
		this.plantClassTypeCode = plantClassTypeCode;
		this.plantClassTypeDesc = plantClassTypeDesc;
		this.tariffRate = tariffRate;
		this.tariffNetAmount = tariffNetAmount;
		this.netPayable = netPayable;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.typeOfSs = typeOfSs;
		this.isRec = isRec;
		this.totalSsLoss = totalSsLoss;
		this.ssLossPercent = ssLossPercent;
		this.mSubstationId = mSubstationId;
		this.mSubstationName = mSubstationName;
		this.isMeterChange = isMeterChange;
		this.docId = docId;
		this.stmtRemarks = stmtRemarks;
		this.generationStatementChargesJpa = generationStatementChargesJpa;
		this.generationStatementSlotsJpa = generationStatementSlotsJpa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getmCompanyMeterId() {
		return mCompanyMeterId;
	}

	public void setmCompanyMeterId(String mCompanyMeterId) {
		this.mCompanyMeterId = mCompanyMeterId;
	}

	public String getCompanyMeterNumber() {
		return companyMeterNumber;
	}

	public void setCompanyMeterNumber(String companyMeterNumber) {
		this.companyMeterNumber = companyMeterNumber;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getMrIds() {
		return mrIds;
	}

	public void setMrIds(String mrIds) {
		this.mrIds = mrIds;
	}

	public String getMf() {
		return mf;
	}

	public void setMf(String mf) {
		this.mf = mf;
	}

	public String getMachineCapacity() {
		return machineCapacity;
	}

	public void setMachineCapacity(String machineCapacity) {
		this.machineCapacity = machineCapacity;
	}

	public String getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}

	public String getStmtGenDate() {
		return stmtGenDate;
	}

	public void setStmtGenDate(String stmtGenDate) {
		this.stmtGenDate = stmtGenDate;
	}

	public String getStmtMonth() {
		return stmtMonth;
	}

	public void setStmtMonth(String stmtMonth) {
		this.stmtMonth = stmtMonth;
	}

	public String getStmtYear() {
		return stmtYear;
	}

	public void setStmtYear(String stmtYear) {
		this.stmtYear = stmtYear;
	}

	public String getInitStmtDt() {
		return initStmtDt;
	}

	public void setInitStmtDt(String initStmtDt) {
		this.initStmtDt = initStmtDt;
	}

	public String getFinalStmtDt() {
		return finalStmtDt;
	}

	public void setFinalStmtDt(String finalStmtDt) {
		this.finalStmtDt = finalStmtDt;
	}

	public String getRkvahInit() {
		return rkvahInit;
	}

	public void setRkvahInit(String rkvahInit) {
		this.rkvahInit = rkvahInit;
	}

	public String getRkvahFinal() {
		return rkvahFinal;
	}

	public void setRkvahFinal(String rkvahFinal) {
		this.rkvahFinal = rkvahFinal;
	}

	public String getRkvahDiff() {
		return rkvahDiff;
	}

	public void setRkvahDiff(String rkvahDiff) {
		this.rkvahDiff = rkvahDiff;
	}

	public String getRkvahUnits() {
		return rkvahUnits;
	}

	public void setRkvahUnits(String rkvahUnits) {
		this.rkvahUnits = rkvahUnits;
	}

	public String getKvahInit() {
		return kvahInit;
	}

	public void setKvahInit(String kvahInit) {
		this.kvahInit = kvahInit;
	}

	public String getKvahFinal() {
		return kvahFinal;
	}

	public void setKvahFinal(String kvahFinal) {
		this.kvahFinal = kvahFinal;
	}

	public String getKvahDiff() {
		return kvahDiff;
	}

	public void setKvahDiff(String kvahDiff) {
		this.kvahDiff = kvahDiff;
	}

	public String getKvahUnits() {
		return kvahUnits;
	}

	public void setKvahUnits(String kvahUnits) {
		this.kvahUnits = kvahUnits;
	}

	public String getTotalImportGen() {
		return totalImportGen;
	}

	public void setTotalImportGen(String totalImportGen) {
		this.totalImportGen = totalImportGen;
	}

	public String getTotalExportGen() {
		return totalExportGen;
	}

	public void setTotalExportGen(String totalExportGen) {
		this.totalExportGen = totalExportGen;
	}

	public String getmOrgId() {
		return mOrgId;
	}

	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}

	public String getDispOrgName() {
		return dispOrgName;
	}

	public void setDispOrgName(String dispOrgName) {
		this.dispOrgName = dispOrgName;
	}

	public String getDispOrgCode() {
		return dispOrgCode;
	}

	public void setDispOrgCode(String dispOrgCode) {
		this.dispOrgCode = dispOrgCode;
	}

	public String getmCompanyId() {
		return mCompanyId;
	}

	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}

	public String getDispCompanyName() {
		return dispCompanyName;
	}

	public void setDispCompanyName(String dispCompanyName) {
		this.dispCompanyName = dispCompanyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getmCompanyServiceId() {
		return mCompanyServiceId;
	}

	public void setmCompanyServiceId(String mCompanyServiceId) {
		this.mCompanyServiceId = mCompanyServiceId;
	}

	public String getDispServiceNumber() {
		return dispServiceNumber;
	}

	public void setDispServiceNumber(String dispServiceNumber) {
		this.dispServiceNumber = dispServiceNumber;
	}

	public String getInjectingVoltageCode() {
		return injectingVoltageCode;
	}

	public void setInjectingVoltageCode(String injectingVoltageCode) {
		this.injectingVoltageCode = injectingVoltageCode;
	}

	public String getInjectingVoltageDesc() {
		return injectingVoltageDesc;
	}

	public void setInjectingVoltageDesc(String injectingVoltageDesc) {
		this.injectingVoltageDesc = injectingVoltageDesc;
	}

	public String getPowerFactor() {
		return powerFactor;
	}

	public void setPowerFactor(String powerFactor) {
		this.powerFactor = powerFactor;
	}

	public String getNetGeneration() {
		return netGeneration;
	}

	public void setNetGeneration(String netGeneration) {
		this.netGeneration = netGeneration;
	}

	public String getTotalChargedAmount() {
		return totalChargedAmount;
	}

	public void setTotalChargedAmount(String totalChargedAmount) {
		this.totalChargedAmount = totalChargedAmount;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public String getIsStb() {
		return isStb;
	}

	public void setIsStb(String isStb) {
		this.isStb = isStb;
	}

	public String getPlantClassTypeCode() {
		return plantClassTypeCode;
	}

	public void setPlantClassTypeCode(String plantClassTypeCode) {
		this.plantClassTypeCode = plantClassTypeCode;
	}

	public String getPlantClassTypeDesc() {
		return plantClassTypeDesc;
	}

	public void setPlantClassTypeDesc(String plantClassTypeDesc) {
		this.plantClassTypeDesc = plantClassTypeDesc;
	}

	public String getTariffRate() {
		return tariffRate;
	}

	public void setTariffRate(String tariffRate) {
		this.tariffRate = tariffRate;
	}

	public String getTariffNetAmount() {
		return tariffNetAmount;
	}

	public void setTariffNetAmount(String tariffNetAmount) {
		this.tariffNetAmount = tariffNetAmount;
	}

	public String getNetPayable() {
		return netPayable;
	}

	public void setNetPayable(String netPayable) {
		this.netPayable = netPayable;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public String getTypeOfSs() {
		return typeOfSs;
	}

	public void setTypeOfSs(String typeOfSs) {
		this.typeOfSs = typeOfSs;
	}

	public String getIsRec() {
		return isRec;
	}

	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}

	public String getTotalSsLoss() {
		return totalSsLoss;
	}

	public void setTotalSsLoss(String totalSsLoss) {
		this.totalSsLoss = totalSsLoss;
	}

	public String getSsLossPercent() {
		return ssLossPercent;
	}

	public void setSsLossPercent(String ssLossPercent) {
		this.ssLossPercent = ssLossPercent;
	}

	public String getmSubstationId() {
		return mSubstationId;
	}

	public void setmSubstationId(String mSubstationId) {
		this.mSubstationId = mSubstationId;
	}

	public String getmSubstationName() {
		return mSubstationName;
	}

	public void setmSubstationName(String mSubstationName) {
		this.mSubstationName = mSubstationName;
	}

	public String getIsMeterChange() {
		return isMeterChange;
	}

	public void setIsMeterChange(String isMeterChange) {
		this.isMeterChange = isMeterChange;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getStmtRemarks() {
		return stmtRemarks;
	}

	public void setStmtRemarks(String stmtRemarks) {
		this.stmtRemarks = stmtRemarks;
	}

	public List<GenerationStatementChargeJpa> getGenerationStatementChargesJpa() {
		return generationStatementChargesJpa;
	}

	public void setGenerationStatementChargesJpa(List<GenerationStatementChargeJpa> generationStatementChargesJpa) {
		this.generationStatementChargesJpa = generationStatementChargesJpa;
	}

	public List<GenerationStatementSlotJpa> getGenerationStatementSlotsJpa() {
		return generationStatementSlotsJpa;
	}

	public void setGenerationStatementSlotsJpa(List<GenerationStatementSlotJpa> generationStatementSlotsJpa) {
		this.generationStatementSlotsJpa = generationStatementSlotsJpa;
	}

	@Override
	public String toString() {
		return "GenerationStatementJpa [id=" + id + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", mCompanyMeterId=" + mCompanyMeterId + ", companyMeterNumber="
				+ companyMeterNumber + ", refNumber=" + refNumber + ", mrIds=" + mrIds + ", mf=" + mf
				+ ", machineCapacity=" + machineCapacity + ", commissionDate=" + commissionDate + ", stmtGenDate="
				+ stmtGenDate + ", stmtMonth=" + stmtMonth + ", stmtYear=" + stmtYear + ", initStmtDt=" + initStmtDt
				+ ", finalStmtDt=" + finalStmtDt + ", rkvahInit=" + rkvahInit + ", rkvahFinal=" + rkvahFinal
				+ ", rkvahDiff=" + rkvahDiff + ", rkvahUnits=" + rkvahUnits + ", kvahInit=" + kvahInit + ", kvahFinal="
				+ kvahFinal + ", kvahDiff=" + kvahDiff + ", kvahUnits=" + kvahUnits + ", totalImportGen="
				+ totalImportGen + ", totalExportGen=" + totalExportGen + ", mOrgId=" + mOrgId + ", dispOrgName="
				+ dispOrgName + ", dispOrgCode=" + dispOrgCode + ", mCompanyId=" + mCompanyId + ", dispCompanyName="
				+ dispCompanyName + ", companyCode=" + companyCode + ", mCompanyServiceId=" + mCompanyServiceId
				+ ", dispServiceNumber=" + dispServiceNumber + ", injectingVoltageCode=" + injectingVoltageCode
				+ ", injectingVoltageDesc=" + injectingVoltageDesc + ", powerFactor=" + powerFactor + ", netGeneration="
				+ netGeneration + ", totalChargedAmount=" + totalChargedAmount + ", c1=" + c1 + ", c2=" + c2 + ", c3="
				+ c3 + ", c4=" + c4 + ", c5=" + c5 + ", fileName=" + fileName + ", isCaptive=" + isCaptive + ", isStb="
				+ isStb + ", plantClassTypeCode=" + plantClassTypeCode + ", plantClassTypeDesc=" + plantClassTypeDesc
				+ ", tariffRate=" + tariffRate + ", tariffNetAmount=" + tariffNetAmount + ", netPayable=" + netPayable
				+ ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + ", typeOfSs=" + typeOfSs
				+ ", isRec=" + isRec + ", totalSsLoss=" + totalSsLoss + ", ssLossPercent=" + ssLossPercent
				+ ", mSubstationId=" + mSubstationId + ", mSubstationName=" + mSubstationName + ", isMeterChange="
				+ isMeterChange + ", docId=" + docId + ", stmtRemarks=" + stmtRemarks
				+ ", generationStatementChargesJpa=" + generationStatementChargesJpa + ", generationStatementSlotsJpa="
				+ generationStatementSlotsJpa + "]";
	}

	

//	public GenerationStatementJpa(String id, String stmtMonth, String stmtYear) {
//		super();
//		this.id = id;
//		this.stmtMonth = stmtMonth;
//		this.stmtYear = stmtYear;
//	}
	
	



	

		
}
