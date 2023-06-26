package com.ss.oa.transaction.vo;

import java.util.List;

public class MeterReading {

	private String id;
	private String companyMeterId;	
	private String companyMeterNumber,companyServiceId,companyServiceNumber;
	private String companyId,companyName,companyCode;
	private String statusCode,statusName;
	private String remarks;
	private String referenceNumber;
	private String mf;
	private String systemDate;
	private String readingMonthCode,readingMonthName,readingYear;
	private String initReadingDate,finalReadingDate;
	private String rKvahInitial,rKvahFinal,rKvahDifference,rKvahUnits;
	private String kVahInitial,kVahFinal,kVahDifference,kVahUnits;
	private String eXpKvahInitial,eXpKvahFinal,eXpRkvahInitial,eXpRkvahFinal;
	private String totalImportGeneration,totalExportGeneration;
	private String createdBy,createdDate;
	private String modifiedBy,modifiedDate;		
	private String modemNumber,mrSourceCode;
	private String orgId,orgName;
	private String fuelTypeCode,fuelTypeName,fuelGroupe;
	private List<MeterReadingSlot> meterReadingSlot;
	
	public MeterReading() {
		super();
	}

	 
	public MeterReading(String id, String companyMeterId, String companyMeterNumber, String companyServiceId,
			String companyServiceNumber, String companyId, String companyName, String companyCode, String statusCode,
			String statusName, String remarks, String referenceNumber, String mf, String systemDate,
			String readingMonthCode, String readingMonthName, String readingYear, String initReadingDate,
			String finalReadingDate, String rKvahInitial, String rKvahFinal, String rKvahDifference, String rKvahUnits,
			String kVahInitial, String kVahFinal, String kVahDifference, String kVahUnits, String eXpKvahInitial,
			String eXpKvahFinal, String eXpRkvahInitial, String eXpRkvahFinal, String totalImportGeneration,
			String totalExportGeneration, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
			String modemNumber, String mrSourceCode, String orgId, String orgName, String fuelTypeCode,
			String fuelTypeName, String fuelGroupe, List<MeterReadingSlot> meterReadingSlot) {
		super();
		this.id = id;
		this.companyMeterId = companyMeterId;
		this.companyMeterNumber = companyMeterNumber;
		this.companyServiceId = companyServiceId;
		this.companyServiceNumber = companyServiceNumber;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.remarks = remarks;
		this.referenceNumber = referenceNumber;
		this.mf = mf;
		this.systemDate = systemDate;
		this.readingMonthCode = readingMonthCode;
		this.readingMonthName = readingMonthName;
		this.readingYear = readingYear;
		this.initReadingDate = initReadingDate;
		this.finalReadingDate = finalReadingDate;
		this.rKvahInitial = rKvahInitial;
		this.rKvahFinal = rKvahFinal;
		this.rKvahDifference = rKvahDifference;
		this.rKvahUnits = rKvahUnits;
		this.kVahInitial = kVahInitial;
		this.kVahFinal = kVahFinal;
		this.kVahDifference = kVahDifference;
		this.kVahUnits = kVahUnits;
		this.eXpKvahInitial = eXpKvahInitial;
		this.eXpKvahFinal = eXpKvahFinal;
		this.eXpRkvahInitial = eXpRkvahInitial;
		this.eXpRkvahFinal = eXpRkvahFinal;
		this.totalImportGeneration = totalImportGeneration;
		this.totalExportGeneration = totalExportGeneration;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.modemNumber = modemNumber;
		this.mrSourceCode = mrSourceCode;
		this.orgId = orgId;
		this.orgName = orgName;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.fuelGroupe = fuelGroupe;
		this.meterReadingSlot = meterReadingSlot;
	}


	 
	@Override
	public String toString() {
		return "MeterReading [id=" + id + ", companyMeterId=" + companyMeterId + ", companyMeterNumber="
				+ companyMeterNumber + ", companyServiceId=" + companyServiceId + ", companyServiceNumber="
				+ companyServiceNumber + ", companyId=" + companyId + ", companyName=" + companyName + ", companyCode="
				+ companyCode + ", statusCode=" + statusCode + ", statusName=" + statusName + ", remarks=" + remarks
				+ ", referenceNumber=" + referenceNumber + ", mf=" + mf + ", systemDate=" + systemDate
				+ ", readingMonthCode=" + readingMonthCode + ", readingMonthName=" + readingMonthName + ", readingYear="
				+ readingYear + ", initReadingDate=" + initReadingDate + ", finalReadingDate=" + finalReadingDate
				+ ", rKvahInitial=" + rKvahInitial + ", rKvahFinal=" + rKvahFinal + ", rKvahDifference="
				+ rKvahDifference + ", rKvahUnits=" + rKvahUnits + ", kVahInitial=" + kVahInitial + ", kVahFinal="
				+ kVahFinal + ", kVahDifference=" + kVahDifference + ", kVahUnits=" + kVahUnits + ", eXpKvahInitial="
				+ eXpKvahInitial + ", eXpKvahFinal=" + eXpKvahFinal + ", eXpRkvahInitial=" + eXpRkvahInitial
				+ ", eXpRkvahFinal=" + eXpRkvahFinal + ", totalImportGeneration=" + totalImportGeneration
				+ ", totalExportGeneration=" + totalExportGeneration + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", modemNumber="
				+ modemNumber + ", mrSourceCode=" + mrSourceCode + ", orgId=" + orgId + ", orgName=" + orgName
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", fuelGroupe=" + fuelGroupe
				+ ", meterReadingSlot=" + meterReadingSlot + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getCompanyServiceId() {
		return companyServiceId;
	}


	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}


	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}


	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}


	public String getMf() {
		return mf;
	}


	public void setMf(String mf) {
		this.mf = mf;
	}


	public String getSystemDate() {
		return systemDate;
	}


	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}


	public String getReadingMonthCode() {
		return readingMonthCode;
	}


	public void setReadingMonthCode(String readingMonthCode) {
		this.readingMonthCode = readingMonthCode;
	}


	public String getReadingMonthName() {
		return readingMonthName;
	}


	public void setReadingMonthName(String readingMonthName) {
		this.readingMonthName = readingMonthName;
	}


	public String getReadingYear() {
		return readingYear;
	}


	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}


	public String getInitReadingDate() {
		return initReadingDate;
	}


	public void setInitReadingDate(String initReadingDate) {
		this.initReadingDate = initReadingDate;
	}


	public String getFinalReadingDate() {
		return finalReadingDate;
	}


	public void setFinalReadingDate(String finalReadingDate) {
		this.finalReadingDate = finalReadingDate;
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


	public String geteXpKvahInitial() {
		return eXpKvahInitial;
	}


	public void seteXpKvahInitial(String eXpKvahInitial) {
		this.eXpKvahInitial = eXpKvahInitial;
	}


	public String geteXpKvahFinal() {
		return eXpKvahFinal;
	}


	public void seteXpKvahFinal(String eXpKvahFinal) {
		this.eXpKvahFinal = eXpKvahFinal;
	}


	public String geteXpRkvahInitial() {
		return eXpRkvahInitial;
	}


	public void seteXpRkvahInitial(String eXpRkvahInitial) {
		this.eXpRkvahInitial = eXpRkvahInitial;
	}


	public String geteXpRkvahFinal() {
		return eXpRkvahFinal;
	}


	public void seteXpRkvahFinal(String eXpRkvahFinal) {
		this.eXpRkvahFinal = eXpRkvahFinal;
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


	public String getModemNumber() {
		return modemNumber;
	}


	public void setModemNumber(String modemNumber) {
		this.modemNumber = modemNumber;
	}


	public String getMrSourceCode() {
		return mrSourceCode;
	}


	public void setMrSourceCode(String mrSourceCode) {
		this.mrSourceCode = mrSourceCode;
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


	public String getFuelGroupe() {
		return fuelGroupe;
	}


	public void setFuelGroupe(String fuelGroupe) {
		this.fuelGroupe = fuelGroupe;
	}


	public List<MeterReadingSlot> getMeterReadingSlot() {
		return meterReadingSlot;
	}


	public void setMeterReadingSlot(List<MeterReadingSlot> meterReadingSlot) {
		this.meterReadingSlot = meterReadingSlot;
	}

 		
	 
	
}
