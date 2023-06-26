package com.ss.oa.transaction.vo;

import java.util.List;

public class MeterReadingImport {
	
	private String id,code;
	private String importDate,fromDate,toDate;
	private String statusCode,statusName;
	private String remarks,error;
	private String mrSourceCode,mrSourceName;
	private String createdBy,createdDate;
	private String modifiedBy,modifiedDate;	
	private List<MeterReadingImportLines> meterReadingImportLines;
	
	public MeterReadingImport() {
		super();
	}

	public MeterReadingImport(String id, String code, String importDate, String fromDate, String toDate,
			String statusCode, String statusName, String remarks, String error, String mrSourceCode,
			String mrSourceName, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
			List<MeterReadingImportLines> meterReadingImportLines) {
		super();
		this.id = id;
		this.code = code;
		this.importDate = importDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.remarks = remarks;
		this.error = error;
		this.mrSourceCode = mrSourceCode;
		this.mrSourceName = mrSourceName;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.meterReadingImportLines = meterReadingImportLines;
	}

	@Override
	public String toString() {
		return "MeterReadingImport [id=" + id + ", code=" + code + ", importDate=" + importDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", error=" + error + ", mrSourceCode=" + mrSourceCode + ", mrSourceName="
				+ mrSourceName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", meterReadingImportLines=" + meterReadingImportLines
				+ "]";
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getImportDate() {
		return importDate;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getError() {
		return error;
	}

	public String getMrSourceCode() {
		return mrSourceCode;
	}

	public String getMrSourceName() {
		return mrSourceName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public List<MeterReadingImportLines> getMeterReadingImportLines() {
		return meterReadingImportLines;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMrSourceCode(String mrSourceCode) {
		this.mrSourceCode = mrSourceCode;
	}

	public void setMrSourceName(String mrSourceName) {
		this.mrSourceName = mrSourceName;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setMeterReadingImportLines(List<MeterReadingImportLines> meterReadingImportLines) {
		this.meterReadingImportLines = meterReadingImportLines;
	}

	

}
