package com.ss.oa.transaction.tempgenerationstatement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="TMP_GEN_STMT")
@CreationTimestamp @UpdateTimestamp
@Entity
public class TempGenStmt {
	@Id
	private String id;
	private String statusCode;
	private String remarks;
	private String mCompanyMeterId;
	private String refNumber;
	private String tMrIds;
	private String mf;
	private String machineCapacity;
	@Formula("(SELECT meter.ENABLED FROM M_COMPANY_METER meter LEFT JOIN TMP_GEN_STMT temp  ON temp.M_COMPANY_METER_ID=meter.ID WHERE temp.M_COMPANY_METER_ID=m_company_meter_id)")
	private String isNewMeter;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate stmtGenDate;
	private String stmtMonth;
	private String stmtYear;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate initStmtDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate finalStmtDt;
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
	private String  mOrgId;
	private String mCompanyId;
	private String mCompanyServiceId;
	private String dispCompanyName;
	private String dispServiceNumber;
	private String injectingVoltageCode;
	private String dispOrgName;
	private String powerFactor;
	private String netGeneration;
	private String totalChargedAmount;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String penaltyRate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate commissionDate;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private char enabled;
	private String fileName;
	private String dispFuelTypeCode;
	private String dispFuelTypeName;
	private String importRemarks;
	private String netPayable;
	private String tariffRate;
	private String plantClassTypeCode;
	private String plantClassTypeDesc;
	private String tariffNetAmount;
	private String isStb;
	private String injectingVoltageDesc;
	private String dispOrgCode;
	private String isCaptive;
	@Column(name="m_substation_id")
	private String mSubstationId;
	@Column(name="m_substation_name")
	private String mSubstationName;
	private String mFeederId;
	private String mFeederName;
	@Column(name="type_of_ss")
	private String typeOfSS;
	private String flowTypeCode;
	private char isRec;
	@Column(name="total_ss_loss")
	private Integer totalSSLoss;
	private Integer ssLossPercent;
	private String amendmentType;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="TMP_GEN_STMT_ID")
	private List<TempGenStmtCharge> tempGenStmtCharges;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="TMP_GEN_STMT_ID")
	private List<TempGenStmtSlot> tempGenStmtSlots;
	
	public TempGenStmt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TempGenStmt(String id, String statusCode, String remarks, String mCompanyMeterId, String refNumber,
			String tMrIds, String mf, String machineCapacity, String isNewMeter, LocalDate stmtGenDate,
			String stmtMonth, String stmtYear, LocalDate initStmtDt, LocalDate finalStmtDt, String rkvahInit,
			String rkvahFinal, String rkvahDiff, String rkvahUnits, String kvahInit, String kvahFinal, String kvahDiff,
			String kvahUnits, String totalImportGen, String totalExportGen, String mOrgId, String mCompanyId,
			String mCompanyServiceId, String dispCompanyName, String dispServiceNumber, String injectingVoltageCode,
			String dispOrgName, String powerFactor, String netGeneration, String totalChargedAmount, String c1,
			String c2, String c3, String c4, String c5, String createdBy, String modifiedBy, LocalDateTime modifiedDt,
			String penaltyRate, LocalDate commissionDate, LocalDate createdDate, char enabled, String fileName,
			String dispFuelTypeCode, String dispFuelTypeName, String importRemarks, String netPayable,
			String tariffRate, String plantClassTypeCode, String plantClassTypeDesc, String tariffNetAmount,
			String isStb, String injectingVoltageDesc, String dispOrgCode, String isCaptive, String mSubstationId,
			String mSubstationName, String mFeederId, String mFeederName, String typeOfSS, String flowTypeCode,
			char isRec, Integer totalSSLoss, Integer ssLossPercent, String amendmentType,
			List<TempGenStmtCharge> tempGenStmtCharges, List<TempGenStmtSlot> tempGenStmtSlots) {
		super();
		this.id = id;
		this.statusCode = statusCode;
		this.remarks = remarks;
		this.mCompanyMeterId = mCompanyMeterId;
		this.refNumber = refNumber;
		this.tMrIds = tMrIds;
		this.mf = mf;
		this.machineCapacity = machineCapacity;
		this.isNewMeter = isNewMeter;
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
		this.mCompanyId = mCompanyId;
		this.mCompanyServiceId = mCompanyServiceId;
		this.dispCompanyName = dispCompanyName;
		this.dispServiceNumber = dispServiceNumber;
		this.injectingVoltageCode = injectingVoltageCode;
		this.dispOrgName = dispOrgName;
		this.powerFactor = powerFactor;
		this.netGeneration = netGeneration;
		this.totalChargedAmount = totalChargedAmount;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.penaltyRate = penaltyRate;
		this.commissionDate = commissionDate;
		this.createdDate = createdDate;
		this.enabled = enabled;
		this.fileName = fileName;
		this.dispFuelTypeCode = dispFuelTypeCode;
		this.dispFuelTypeName = dispFuelTypeName;
		this.importRemarks = importRemarks;
		this.netPayable = netPayable;
		this.tariffRate = tariffRate;
		this.plantClassTypeCode = plantClassTypeCode;
		this.plantClassTypeDesc = plantClassTypeDesc;
		this.tariffNetAmount = tariffNetAmount;
		this.isStb = isStb;
		this.injectingVoltageDesc = injectingVoltageDesc;
		this.dispOrgCode = dispOrgCode;
		this.isCaptive = isCaptive;
		this.mSubstationId = mSubstationId;
		this.mSubstationName = mSubstationName;
		this.mFeederId = mFeederId;
		this.mFeederName = mFeederName;
		this.typeOfSS = typeOfSS;
		this.flowTypeCode = flowTypeCode;
		this.isRec = isRec;
		this.totalSSLoss = totalSSLoss;
		this.ssLossPercent = ssLossPercent;
		this.amendmentType = amendmentType;
		this.tempGenStmtCharges = tempGenStmtCharges;
		this.tempGenStmtSlots = tempGenStmtSlots;
	}

	@Override
	public String toString() {
		return "TempGenStmt [id=" + id + ", statusCode=" + statusCode + ", remarks=" + remarks + ", mCompanyMeterId="
				+ mCompanyMeterId + ", refNumber=" + refNumber + ", tMrIds=" + tMrIds + ", mf=" + mf
				+ ", machineCapacity=" + machineCapacity + ", isNewMeter=" + isNewMeter + ", stmtGenDate=" + stmtGenDate
				+ ", stmtMonth=" + stmtMonth + ", stmtYear=" + stmtYear + ", initStmtDt=" + initStmtDt
				+ ", finalStmtDt=" + finalStmtDt + ", rkvahInit=" + rkvahInit + ", rkvahFinal=" + rkvahFinal
				+ ", rkvahDiff=" + rkvahDiff + ", rkvahUnits=" + rkvahUnits + ", kvahInit=" + kvahInit + ", kvahFinal="
				+ kvahFinal + ", kvahDiff=" + kvahDiff + ", kvahUnits=" + kvahUnits + ", totalImportGen="
				+ totalImportGen + ", totalExportGen=" + totalExportGen + ", mOrgId=" + mOrgId + ", mCompanyId="
				+ mCompanyId + ", mCompanyServiceId=" + mCompanyServiceId + ", dispCompanyName=" + dispCompanyName
				+ ", dispServiceNumber=" + dispServiceNumber + ", injectingVoltageCode=" + injectingVoltageCode
				+ ", dispOrgName=" + dispOrgName + ", powerFactor=" + powerFactor + ", netGeneration=" + netGeneration
				+ ", totalChargedAmount=" + totalChargedAmount + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", modifiedDt="
				+ modifiedDt + ", penaltyRate=" + penaltyRate + ", commissionDate=" + commissionDate + ", createdDate="
				+ createdDate + ", enabled=" + enabled + ", fileName=" + fileName + ", dispFuelTypeCode="
				+ dispFuelTypeCode + ", dispFuelTypeName=" + dispFuelTypeName + ", importRemarks=" + importRemarks
				+ ", netPayable=" + netPayable + ", tariffRate=" + tariffRate + ", plantClassTypeCode="
				+ plantClassTypeCode + ", plantClassTypeDesc=" + plantClassTypeDesc + ", tariffNetAmount="
				+ tariffNetAmount + ", isStb=" + isStb + ", injectingVoltageDesc=" + injectingVoltageDesc
				+ ", dispOrgCode=" + dispOrgCode + ", isCaptive=" + isCaptive + ", mSubstationId=" + mSubstationId
				+ ", mSubstationName=" + mSubstationName + ", mFeederId=" + mFeederId + ", mFeederName=" + mFeederName
				+ ", typeOfSS=" + typeOfSS + ", flowTypeCode=" + flowTypeCode + ", isRec=" + isRec + ", totalSSLoss="
				+ totalSSLoss + ", ssLossPercent=" + ssLossPercent + ", amendmentType=" + amendmentType
				+ ", tempGenStmtCharges=" + tempGenStmtCharges + ", tempGenStmtSlots=" + tempGenStmtSlots + "]";
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

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String gettMrIds() {
		return tMrIds;
	}

	public void settMrIds(String tMrIds) {
		this.tMrIds = tMrIds;
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

	public String getIsNewMeter() {
		return isNewMeter;
	}

	public void setIsNewMeter(String isNewMeter) {
		this.isNewMeter = isNewMeter;
	}

	public LocalDate getStmtGenDate() {
		return stmtGenDate;
	}

	public void setStmtGenDate(LocalDate stmtGenDate) {
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

	public LocalDate getInitStmtDt() {
		return initStmtDt;
	}

	public void setInitStmtDt(LocalDate initStmtDt) {
		this.initStmtDt = initStmtDt;
	}

	public LocalDate getFinalStmtDt() {
		return finalStmtDt;
	}

	public void setFinalStmtDt(LocalDate finalStmtDt) {
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

	public String getmCompanyId() {
		return mCompanyId;
	}

	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}

	public String getmCompanyServiceId() {
		return mCompanyServiceId;
	}

	public void setmCompanyServiceId(String mCompanyServiceId) {
		this.mCompanyServiceId = mCompanyServiceId;
	}

	public String getDispCompanyName() {
		return dispCompanyName;
	}

	public void setDispCompanyName(String dispCompanyName) {
		this.dispCompanyName = dispCompanyName;
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

	public String getDispOrgName() {
		return dispOrgName;
	}

	public void setDispOrgName(String dispOrgName) {
		this.dispOrgName = dispOrgName;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(String penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public LocalDate getCommissionDate() {
		return commissionDate;
	}

	public void setCommissionDate(LocalDate commissionDate) {
		this.commissionDate = commissionDate;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDispFuelTypeCode() {
		return dispFuelTypeCode;
	}

	public void setDispFuelTypeCode(String dispFuelTypeCode) {
		this.dispFuelTypeCode = dispFuelTypeCode;
	}

	public String getDispFuelTypeName() {
		return dispFuelTypeName;
	}

	public void setDispFuelTypeName(String dispFuelTypeName) {
		this.dispFuelTypeName = dispFuelTypeName;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getNetPayable() {
		return netPayable;
	}

	public void setNetPayable(String netPayable) {
		this.netPayable = netPayable;
	}

	public String getTariffRate() {
		return tariffRate;
	}

	public void setTariffRate(String tariffRate) {
		this.tariffRate = tariffRate;
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

	public String getTariffNetAmount() {
		return tariffNetAmount;
	}

	public void setTariffNetAmount(String tariffNetAmount) {
		this.tariffNetAmount = tariffNetAmount;
	}

	public String getIsStb() {
		return isStb;
	}

	public void setIsStb(String isStb) {
		this.isStb = isStb;
	}

	public String getInjectingVoltageDesc() {
		return injectingVoltageDesc;
	}

	public void setInjectingVoltageDesc(String injectingVoltageDesc) {
		this.injectingVoltageDesc = injectingVoltageDesc;
	}

	public String getDispOrgCode() {
		return dispOrgCode;
	}

	public void setDispOrgCode(String dispOrgCode) {
		this.dispOrgCode = dispOrgCode;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
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

	public String getmFeederId() {
		return mFeederId;
	}

	public void setmFeederId(String mFeederId) {
		this.mFeederId = mFeederId;
	}

	public String getmFeederName() {
		return mFeederName;
	}

	public void setmFeederName(String mFeederName) {
		this.mFeederName = mFeederName;
	}

	public String getTypeOfSS() {
		return typeOfSS;
	}

	public void setTypeOfSS(String typeOfSS) {
		this.typeOfSS = typeOfSS;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public char getIsRec() {
		return isRec;
	}

	public void setIsRec(char isRec) {
		this.isRec = isRec;
	}

	public Integer getTotalSSLoss() {
		return totalSSLoss;
	}

	public void setTotalSSLoss(Integer totalSSLoss) {
		this.totalSSLoss = totalSSLoss;
	}

	public Integer getSsLossPercent() {
		return ssLossPercent;
	}

	public void setSsLossPercent(Integer ssLossPercent) {
		this.ssLossPercent = ssLossPercent;
	}

	public String getAmendmentType() {
		return amendmentType;
	}

	public void setAmendmentType(String amendmentType) {
		this.amendmentType = amendmentType;
	}

	public List<TempGenStmtCharge> getTempGenStmtCharges() {
		return tempGenStmtCharges;
	}

	public void setTempGenStmtCharges(List<TempGenStmtCharge> tempGenStmtCharges) {
		this.tempGenStmtCharges = tempGenStmtCharges;
	}

	public List<TempGenStmtSlot> getTempGenStmtSlots() {
		return tempGenStmtSlots;
	}

	public void setTempGenStmtSlots(List<TempGenStmtSlot> tempGenStmtSlots) {
		this.tempGenStmtSlots = tempGenStmtSlots;
	}
	
	

}
