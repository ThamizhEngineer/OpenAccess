package com.ss.oa.oadocumentservice.vo;

import java.util.List;

import javax.persistence.Column;





public class GenerationStatement {
	
	
	private String id;
	private String statusCode,statusName;
	private String remarks;
	private String companyMeterId;	
	private String companyMeterNumber;
	private String referenceNumber;
	private String mrIds;
	private String mf;
	private String machineCapacity,commissionDate;
	private String statementGenerationDate;
	private String statementMonth;
	private String statementYear;
	private String initStatementDate;
	private String finalStatementDate;
	private String rKvahInitial,rKvahFinal,rKvahDifference,rKvahUnits;
	private String kVahInitial,kVahFinal,kVahDifference,kVahUnits;
	private String totalImportGeneration,totalExportGeneration;
	private String orgId,dispOrgName,orgCode;
	private String companyId,dispCompanyName,dispOrgCode,companyCode;
	private String companyServiceId,dispCompanyServiceNumber;
	private String injectingVoltageCode,injectingVoltageName,injectingVoltageDesc;
	private String powerFactor;
	private String netGeneration;
	private String totalChargedAmount;
	private String c1,c2,c3,c4,c5;
	private String fileName;
	private String isCaptive;
	private String isStb;
	private String plantClassTypeCode,plantClassTypeDesc;
	private String tariffRate,netPayable,tariffNetAmount;
	private String typeOfSS;
	private String flowTypeCode;
	private String flowTypeName;
	private String isRec;
	private String totalSsLoss;
	private String ssLossPercent;
	private String disSubstationId;
	private String disSubstationName;
	private String isMeterChange;
	private String dispFuelTypeCode;
	private String dispFuelTypeName;
	private String dispFuelTypeGroup;
	private String mrSourceCode;
	private String docId;
	private String stmtRemarks;
	
	private List<GenerationStatementCharge> generationStatementCharges;
	private List<GenerationStatementSlot> generationStatementSlots;
	
	
	public GenerationStatement() {
		super();
		
		
	}


	public GenerationStatement(String id, String statusCode, String statusName, String remarks, String companyMeterId,
			String companyMeterNumber, String referenceNumber, String mrIds, String mf, String machineCapacity,
			String commissionDate, String statementGenerationDate, String statementMonth, String statementYear,
			String initStatementDate, String finalStatementDate, String rKvahInitial, String rKvahFinal,
			String rKvahDifference, String rKvahUnits, String kVahInitial, String kVahFinal, String kVahDifference,
			String kVahUnits, String totalImportGeneration, String totalExportGeneration, String orgId,
			String dispOrgName, String orgCode, String companyId, String dispCompanyName, String dispOrgCode,
			String companyCode, String companyServiceId, String dispCompanyServiceNumber, String injectingVoltageCode,
			String injectingVoltageName, String injectingVoltageDesc, String powerFactor, String netGeneration,
			String totalChargedAmount, String c1, String c2, String c3, String c4, String c5, String fileName,
			String isCaptive, String isStb, String plantClassTypeCode, String plantClassTypeDesc, String tariffRate,
			String netPayable, String tariffNetAmount, String typeOfSS, String flowTypeCode, String flowTypeName,
			String isRec, String totalSsLoss, String ssLossPercent, String disSubstationId, String disSubstationName,
			String isMeterChange, String dispFuelTypeCode, String dispFuelTypeName, String dispFuelTypeGroup,
			String mrSourceCode, String docId, String stmtRemarks,
			List<GenerationStatementCharge> generationStatementCharges,
			List<GenerationStatementSlot> generationStatementSlots) {
		super();
		this.id = id;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.remarks = remarks;
		this.companyMeterId = companyMeterId;
		this.companyMeterNumber = companyMeterNumber;
		this.referenceNumber = referenceNumber;
		this.mrIds = mrIds;
		this.mf = mf;
		this.machineCapacity = machineCapacity;
		this.commissionDate = commissionDate;
		this.statementGenerationDate = statementGenerationDate;
		this.statementMonth = statementMonth;
		this.statementYear = statementYear;
		this.initStatementDate = initStatementDate;
		this.finalStatementDate = finalStatementDate;
		this.rKvahInitial = rKvahInitial;
		this.rKvahFinal = rKvahFinal;
		this.rKvahDifference = rKvahDifference;
		this.rKvahUnits = rKvahUnits;
		this.kVahInitial = kVahInitial;
		this.kVahFinal = kVahFinal;
		this.kVahDifference = kVahDifference;
		this.kVahUnits = kVahUnits;
		this.totalImportGeneration = totalImportGeneration;
		this.totalExportGeneration = totalExportGeneration;
		this.orgId = orgId;
		this.dispOrgName = dispOrgName;
		this.orgCode = orgCode;
		this.companyId = companyId;
		this.dispCompanyName = dispCompanyName;
		this.dispOrgCode = dispOrgCode;
		this.companyCode = companyCode;
		this.companyServiceId = companyServiceId;
		this.dispCompanyServiceNumber = dispCompanyServiceNumber;
		this.injectingVoltageCode = injectingVoltageCode;
		this.injectingVoltageName = injectingVoltageName;
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
		this.netPayable = netPayable;
		this.tariffNetAmount = tariffNetAmount;
		this.typeOfSS = typeOfSS;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.isRec = isRec;
		this.totalSsLoss = totalSsLoss;
		this.ssLossPercent = ssLossPercent;
		this.disSubstationId = disSubstationId;
		this.disSubstationName = disSubstationName;
		this.isMeterChange = isMeterChange;
		this.dispFuelTypeCode = dispFuelTypeCode;
		this.dispFuelTypeName = dispFuelTypeName;
		this.dispFuelTypeGroup = dispFuelTypeGroup;
		this.mrSourceCode = mrSourceCode;
		this.docId = docId;
		this.stmtRemarks = stmtRemarks;
		this.generationStatementCharges = generationStatementCharges;
		this.generationStatementSlots = generationStatementSlots;
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


	public String getCompanyMeterId() {
		return companyMeterId;
	}


	public void setCompanyMeterId(String companyMeterId) {
		this.companyMeterId = companyMeterId;
	}


	public String getCompanyMeterNumber() {
		return companyMeterNumber;
	}


	public void setCompanyMeterNumber(String companyMeterNumber) {
		this.companyMeterNumber = companyMeterNumber;
	}


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
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


	public String getStatementGenerationDate() {
		return statementGenerationDate;
	}


	public void setStatementGenerationDate(String statementGenerationDate) {
		this.statementGenerationDate = statementGenerationDate;
	}


	public String getStatementMonth() {
		return statementMonth;
	}


	public void setStatementMonth(String statementMonth) {
		this.statementMonth = statementMonth;
	}


	public String getStatementYear() {
		return statementYear;
	}


	public void setStatementYear(String statementYear) {
		this.statementYear = statementYear;
	}


	public String getInitStatementDate() {
		return initStatementDate;
	}


	public void setInitStatementDate(String initStatementDate) {
		this.initStatementDate = initStatementDate;
	}


	public String getFinalStatementDate() {
		return finalStatementDate;
	}


	public void setFinalStatementDate(String finalStatementDate) {
		this.finalStatementDate = finalStatementDate;
	}


	public String getrKvahInitial() {
		return rKvahInitial;
	}


	public void setrKvahInitial(String rKvahInitial) {
		this.rKvahInitial = rKvahInitial;
	}


	public String getrKvahFinal() {
		return rKvahFinal;
	}


	public void setrKvahFinal(String rKvahFinal) {
		this.rKvahFinal = rKvahFinal;
	}


	public String getrKvahDifference() {
		return rKvahDifference;
	}


	public void setrKvahDifference(String rKvahDifference) {
		this.rKvahDifference = rKvahDifference;
	}


	public String getrKvahUnits() {
		return rKvahUnits;
	}


	public void setrKvahUnits(String rKvahUnits) {
		this.rKvahUnits = rKvahUnits;
	}


	public String getkVahInitial() {
		return kVahInitial;
	}


	public void setkVahInitial(String kVahInitial) {
		this.kVahInitial = kVahInitial;
	}


	public String getkVahFinal() {
		return kVahFinal;
	}


	public void setkVahFinal(String kVahFinal) {
		this.kVahFinal = kVahFinal;
	}


	public String getkVahDifference() {
		return kVahDifference;
	}


	public void setkVahDifference(String kVahDifference) {
		this.kVahDifference = kVahDifference;
	}


	public String getkVahUnits() {
		return kVahUnits;
	}


	public void setkVahUnits(String kVahUnits) {
		this.kVahUnits = kVahUnits;
	}


	public String getTotalImportGeneration() {
		return totalImportGeneration;
	}


	public void setTotalImportGeneration(String totalImportGeneration) {
		this.totalImportGeneration = totalImportGeneration;
	}


	public String getTotalExportGeneration() {
		return totalExportGeneration;
	}


	public void setTotalExportGeneration(String totalExportGeneration) {
		this.totalExportGeneration = totalExportGeneration;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getDispOrgName() {
		return dispOrgName;
	}


	public void setDispOrgName(String dispOrgName) {
		this.dispOrgName = dispOrgName;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getDispCompanyName() {
		return dispCompanyName;
	}


	public void setDispCompanyName(String dispCompanyName) {
		this.dispCompanyName = dispCompanyName;
	}


	public String getDispOrgCode() {
		return dispOrgCode;
	}


	public void setDispOrgCode(String dispOrgCode) {
		this.dispOrgCode = dispOrgCode;
	}


	public String getCompanyCode() {
		return companyCode;
	}


	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public String getCompanyServiceId() {
		return companyServiceId;
	}


	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}


	public String getDispCompanyServiceNumber() {
		return dispCompanyServiceNumber;
	}


	public void setDispCompanyServiceNumber(String dispCompanyServiceNumber) {
		this.dispCompanyServiceNumber = dispCompanyServiceNumber;
	}


	public String getInjectingVoltageCode() {
		return injectingVoltageCode;
	}


	public void setInjectingVoltageCode(String injectingVoltageCode) {
		this.injectingVoltageCode = injectingVoltageCode;
	}


	public String getInjectingVoltageName() {
		return injectingVoltageName;
	}


	public void setInjectingVoltageName(String injectingVoltageName) {
		this.injectingVoltageName = injectingVoltageName;
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


	public String getNetPayable() {
		return netPayable;
	}


	public void setNetPayable(String netPayable) {
		this.netPayable = netPayable;
	}


	public String getTariffNetAmount() {
		return tariffNetAmount;
	}


	public void setTariffNetAmount(String tariffNetAmount) {
		this.tariffNetAmount = tariffNetAmount;
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


	public String getFlowTypeName() {
		return flowTypeName;
	}


	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
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


	public String getDisSubstationId() {
		return disSubstationId;
	}


	public void setDisSubstationId(String disSubstationId) {
		this.disSubstationId = disSubstationId;
	}


	public String getDisSubstationName() {
		return disSubstationName;
	}


	public void setDisSubstationName(String disSubstationName) {
		this.disSubstationName = disSubstationName;
	}


	public String getIsMeterChange() {
		return isMeterChange;
	}


	public void setIsMeterChange(String isMeterChange) {
		this.isMeterChange = isMeterChange;
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


	public String getDispFuelTypeGroup() {
		return dispFuelTypeGroup;
	}


	public void setDispFuelTypeGroup(String dispFuelTypeGroup) {
		this.dispFuelTypeGroup = dispFuelTypeGroup;
	}


	public String getMrSourceCode() {
		return mrSourceCode;
	}


	public void setMrSourceCode(String mrSourceCode) {
		this.mrSourceCode = mrSourceCode;
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


	public List<GenerationStatementCharge> getGenerationStatementCharges() {
		return generationStatementCharges;
	}


	public void setGenerationStatementCharges(List<GenerationStatementCharge> generationStatementCharges) {
		this.generationStatementCharges = generationStatementCharges;
	}


	public List<GenerationStatementSlot> getGenerationStatementSlots() {
		return generationStatementSlots;
	}


	public void setGenerationStatementSlots(List<GenerationStatementSlot> generationStatementSlots) {
		this.generationStatementSlots = generationStatementSlots;
	}


	@Override
	public String toString() {
		return "GenerationStatement [id=" + id + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", companyMeterId=" + companyMeterId + ", companyMeterNumber="
				+ companyMeterNumber + ", referenceNumber=" + referenceNumber + ", mrIds=" + mrIds + ", mf=" + mf
				+ ", machineCapacity=" + machineCapacity + ", commissionDate=" + commissionDate
				+ ", statementGenerationDate=" + statementGenerationDate + ", statementMonth=" + statementMonth
				+ ", statementYear=" + statementYear + ", initStatementDate=" + initStatementDate
				+ ", finalStatementDate=" + finalStatementDate + ", rKvahInitial=" + rKvahInitial + ", rKvahFinal="
				+ rKvahFinal + ", rKvahDifference=" + rKvahDifference + ", rKvahUnits=" + rKvahUnits + ", kVahInitial="
				+ kVahInitial + ", kVahFinal=" + kVahFinal + ", kVahDifference=" + kVahDifference + ", kVahUnits="
				+ kVahUnits + ", totalImportGeneration=" + totalImportGeneration + ", totalExportGeneration="
				+ totalExportGeneration + ", orgId=" + orgId + ", dispOrgName=" + dispOrgName + ", orgCode=" + orgCode
				+ ", companyId=" + companyId + ", dispCompanyName=" + dispCompanyName + ", dispOrgCode=" + dispOrgCode
				+ ", companyCode=" + companyCode + ", companyServiceId=" + companyServiceId
				+ ", dispCompanyServiceNumber=" + dispCompanyServiceNumber + ", injectingVoltageCode="
				+ injectingVoltageCode + ", injectingVoltageName=" + injectingVoltageName + ", injectingVoltageDesc="
				+ injectingVoltageDesc + ", powerFactor=" + powerFactor + ", netGeneration=" + netGeneration
				+ ", totalChargedAmount=" + totalChargedAmount + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + ", fileName=" + fileName + ", isCaptive=" + isCaptive + ", isStb=" + isStb
				+ ", plantClassTypeCode=" + plantClassTypeCode + ", plantClassTypeDesc=" + plantClassTypeDesc
				+ ", tariffRate=" + tariffRate + ", netPayable=" + netPayable + ", tariffNetAmount=" + tariffNetAmount
				+ ", typeOfSS=" + typeOfSS + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName
				+ ", isRec=" + isRec + ", totalSsLoss=" + totalSsLoss + ", ssLossPercent=" + ssLossPercent
				+ ", disSubstationId=" + disSubstationId + ", disSubstationName=" + disSubstationName
				+ ", isMeterChange=" + isMeterChange + ", dispFuelTypeCode=" + dispFuelTypeCode + ", dispFuelTypeName="
				+ dispFuelTypeName + ", dispFuelTypeGroup=" + dispFuelTypeGroup + ", mrSourceCode=" + mrSourceCode
				+ ", docId=" + docId + ", stmtRemarks=" + stmtRemarks + ", generationStatementCharges="
				+ generationStatementCharges + ", generationStatementSlots=" + generationStatementSlots + "]";
	}


	
}
