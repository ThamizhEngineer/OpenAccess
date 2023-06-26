package com.ss.oa.transaction.vo;

public class TempMeterReadingImportLines {

	private String id;
	private String statusCode,statusName;
	private String remarks;
	private String impMcMrHeaderId;
	private String meterNumber;
	private String mf;
	private String serviceNumber;
	private String downloadStatus;
	private String systemDate;
	private String initReadingDate,finalReadingDate;
	private String impInitS1,impInitS2,impInitS3,impInitS4,impInitS5;
	private String impFinalS1,impFinalS2,impFinalS3,impFinalS4,impFinalS5;
	private String expInitS1,expInitS2,expInitS3,expInitS4,expInitS5;
	private String expFinalS1,expFinalS2,expFinalS3,expFinalS4,expFinalS5;
	private String impRkvahInit,impRkvahFinal;
	private String expRkvahInit,expRkvahFinal;
	private String impkVahInit,impkVahFinal;
	private String expkVahInit,expkVahFinal;
	private String createdBy,createdDate;
	private String modifiedBy,modifiedDate;	
	private String readingMonthCode,readingMonthName,readingYear;
	public TempMeterReadingImportLines() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempMeterReadingImportLines(String id, String statusCode, String statusName, String remarks,
			String impMcMrHeaderId, String meterNumber, String mf, String serviceNumber, String downloadStatus,
			String systemDate, String initReadingDate, String finalReadingDate, String impInitS1, String impInitS2,
			String impInitS3, String impInitS4, String impInitS5, String impFinalS1, String impFinalS2,
			String impFinalS3, String impFinalS4, String impFinalS5, String expInitS1, String expInitS2,
			String expInitS3, String expInitS4, String expInitS5, String expFinalS1, String expFinalS2,
			String expFinalS3, String expFinalS4, String expFinalS5, String impRkvahInit, String impRkvahFinal,
			String expRkvahInit, String expRkvahFinal, String impkVahInit, String impkVahFinal, String expkVahInit,
			String expkVahFinal, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
			String readingMonthCode, String readingMonthName, String readingYear) {
		super();
		this.id = id;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.remarks = remarks;
		this.impMcMrHeaderId = impMcMrHeaderId;
		this.meterNumber = meterNumber;
		this.mf = mf;
		this.serviceNumber = serviceNumber;
		this.downloadStatus = downloadStatus;
		this.systemDate = systemDate;
		this.initReadingDate = initReadingDate;
		this.finalReadingDate = finalReadingDate;
		this.impInitS1 = impInitS1;
		this.impInitS2 = impInitS2;
		this.impInitS3 = impInitS3;
		this.impInitS4 = impInitS4;
		this.impInitS5 = impInitS5;
		this.impFinalS1 = impFinalS1;
		this.impFinalS2 = impFinalS2;
		this.impFinalS3 = impFinalS3;
		this.impFinalS4 = impFinalS4;
		this.impFinalS5 = impFinalS5;
		this.expInitS1 = expInitS1;
		this.expInitS2 = expInitS2;
		this.expInitS3 = expInitS3;
		this.expInitS4 = expInitS4;
		this.expInitS5 = expInitS5;
		this.expFinalS1 = expFinalS1;
		this.expFinalS2 = expFinalS2;
		this.expFinalS3 = expFinalS3;
		this.expFinalS4 = expFinalS4;
		this.expFinalS5 = expFinalS5;
		this.impRkvahInit = impRkvahInit;
		this.impRkvahFinal = impRkvahFinal;
		this.expRkvahInit = expRkvahInit;
		this.expRkvahFinal = expRkvahFinal;
		this.impkVahInit = impkVahInit;
		this.impkVahFinal = impkVahFinal;
		this.expkVahInit = expkVahInit;
		this.expkVahFinal = expkVahFinal;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.readingMonthCode = readingMonthCode;
		this.readingMonthName = readingMonthName;
		this.readingYear = readingYear;
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
	public String getImpMcMrHeaderId() {
		return impMcMrHeaderId;
	}
	public void setImpMcMrHeaderId(String impMcMrHeaderId) {
		this.impMcMrHeaderId = impMcMrHeaderId;
	}
	public String getMeterNumber() {
		return meterNumber;
	}
	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}
	public String getMf() {
		return mf;
	}
	public void setMf(String mf) {
		this.mf = mf;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getDownloadStatus() {
		return downloadStatus;
	}
	public void setDownloadStatus(String downloadStatus) {
		this.downloadStatus = downloadStatus;
	}
	public String getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
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
	public String getImpInitS1() {
		return impInitS1;
	}
	public void setImpInitS1(String impInitS1) {
		this.impInitS1 = impInitS1;
	}
	public String getImpInitS2() {
		return impInitS2;
	}
	public void setImpInitS2(String impInitS2) {
		this.impInitS2 = impInitS2;
	}
	public String getImpInitS3() {
		return impInitS3;
	}
	public void setImpInitS3(String impInitS3) {
		this.impInitS3 = impInitS3;
	}
	public String getImpInitS4() {
		return impInitS4;
	}
	public void setImpInitS4(String impInitS4) {
		this.impInitS4 = impInitS4;
	}
	public String getImpInitS5() {
		return impInitS5;
	}
	public void setImpInitS5(String impInitS5) {
		this.impInitS5 = impInitS5;
	}
	public String getImpFinalS1() {
		return impFinalS1;
	}
	public void setImpFinalS1(String impFinalS1) {
		this.impFinalS1 = impFinalS1;
	}
	public String getImpFinalS2() {
		return impFinalS2;
	}
	public void setImpFinalS2(String impFinalS2) {
		this.impFinalS2 = impFinalS2;
	}
	public String getImpFinalS3() {
		return impFinalS3;
	}
	public void setImpFinalS3(String impFinalS3) {
		this.impFinalS3 = impFinalS3;
	}
	public String getImpFinalS4() {
		return impFinalS4;
	}
	public void setImpFinalS4(String impFinalS4) {
		this.impFinalS4 = impFinalS4;
	}
	public String getImpFinalS5() {
		return impFinalS5;
	}
	public void setImpFinalS5(String impFinalS5) {
		this.impFinalS5 = impFinalS5;
	}
	public String getExpInitS1() {
		return expInitS1;
	}
	public void setExpInitS1(String expInitS1) {
		this.expInitS1 = expInitS1;
	}
	public String getExpInitS2() {
		return expInitS2;
	}
	public void setExpInitS2(String expInitS2) {
		this.expInitS2 = expInitS2;
	}
	public String getExpInitS3() {
		return expInitS3;
	}
	public void setExpInitS3(String expInitS3) {
		this.expInitS3 = expInitS3;
	}
	public String getExpInitS4() {
		return expInitS4;
	}
	public void setExpInitS4(String expInitS4) {
		this.expInitS4 = expInitS4;
	}
	public String getExpInitS5() {
		return expInitS5;
	}
	public void setExpInitS5(String expInitS5) {
		this.expInitS5 = expInitS5;
	}
	public String getExpFinalS1() {
		return expFinalS1;
	}
	public void setExpFinalS1(String expFinalS1) {
		this.expFinalS1 = expFinalS1;
	}
	public String getExpFinalS2() {
		return expFinalS2;
	}
	public void setExpFinalS2(String expFinalS2) {
		this.expFinalS2 = expFinalS2;
	}
	public String getExpFinalS3() {
		return expFinalS3;
	}
	public void setExpFinalS3(String expFinalS3) {
		this.expFinalS3 = expFinalS3;
	}
	public String getExpFinalS4() {
		return expFinalS4;
	}
	public void setExpFinalS4(String expFinalS4) {
		this.expFinalS4 = expFinalS4;
	}
	public String getExpFinalS5() {
		return expFinalS5;
	}
	public void setExpFinalS5(String expFinalS5) {
		this.expFinalS5 = expFinalS5;
	}
	public String getImpRkvahInit() {
		return impRkvahInit;
	}
	public void setImpRkvahInit(String impRkvahInit) {
		this.impRkvahInit = impRkvahInit;
	}
	public String getImpRkvahFinal() {
		return impRkvahFinal;
	}
	public void setImpRkvahFinal(String impRkvahFinal) {
		this.impRkvahFinal = impRkvahFinal;
	}
	public String getExpRkvahInit() {
		return expRkvahInit;
	}
	public void setExpRkvahInit(String expRkvahInit) {
		this.expRkvahInit = expRkvahInit;
	}
	public String getExpRkvahFinal() {
		return expRkvahFinal;
	}
	public void setExpRkvahFinal(String expRkvahFinal) {
		this.expRkvahFinal = expRkvahFinal;
	}
	public String getImpkVahInit() {
		return impkVahInit;
	}
	public void setImpkVahInit(String impkVahInit) {
		this.impkVahInit = impkVahInit;
	}
	public String getImpkVahFinal() {
		return impkVahFinal;
	}
	public void setImpkVahFinal(String impkVahFinal) {
		this.impkVahFinal = impkVahFinal;
	}
	public String getExpkVahInit() {
		return expkVahInit;
	}
	public void setExpkVahInit(String expkVahInit) {
		this.expkVahInit = expkVahInit;
	}
	public String getExpkVahFinal() {
		return expkVahFinal;
	}
	public void setExpkVahFinal(String expkVahFinal) {
		this.expkVahFinal = expkVahFinal;
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
	@Override
	public String toString() {
		return "TempMeterReadingImportLines [id=" + id + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", impMcMrHeaderId=" + impMcMrHeaderId + ", meterNumber=" + meterNumber
				+ ", mf=" + mf + ", serviceNumber=" + serviceNumber + ", downloadStatus=" + downloadStatus
				+ ", systemDate=" + systemDate + ", initReadingDate=" + initReadingDate + ", finalReadingDate="
				+ finalReadingDate + ", impInitS1=" + impInitS1 + ", impInitS2=" + impInitS2 + ", impInitS3="
				+ impInitS3 + ", impInitS4=" + impInitS4 + ", impInitS5=" + impInitS5 + ", impFinalS1=" + impFinalS1
				+ ", impFinalS2=" + impFinalS2 + ", impFinalS3=" + impFinalS3 + ", impFinalS4=" + impFinalS4
				+ ", impFinalS5=" + impFinalS5 + ", expInitS1=" + expInitS1 + ", expInitS2=" + expInitS2
				+ ", expInitS3=" + expInitS3 + ", expInitS4=" + expInitS4 + ", expInitS5=" + expInitS5 + ", expFinalS1="
				+ expFinalS1 + ", expFinalS2=" + expFinalS2 + ", expFinalS3=" + expFinalS3 + ", expFinalS4="
				+ expFinalS4 + ", expFinalS5=" + expFinalS5 + ", impRkvahInit=" + impRkvahInit + ", impRkvahFinal="
				+ impRkvahFinal + ", expRkvahInit=" + expRkvahInit + ", expRkvahFinal=" + expRkvahFinal
				+ ", impkVahInit=" + impkVahInit + ", impkVahFinal=" + impkVahFinal + ", expkVahInit=" + expkVahInit
				+ ", expkVahFinal=" + expkVahFinal + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", readingMonthCode="
				+ readingMonthCode + ", readingMonthName=" + readingMonthName + ", readingYear=" + readingYear + "]";
	}
	
}
