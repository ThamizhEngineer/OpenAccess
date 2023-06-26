package com.ss.oa.transaction.vo;

import java.util.List;

public class TempMeterReadingImport {
	private String id,code;
	private String importDate,fromDate,toDate;
	private String statusCode,statusName;
	private String remarks,error;
	private String mrSourceCode,mrSourceName;
	private String createdBy,createdDate;
	private String modifiedBy,modifiedDate;	
	private List<TempMeterReadingImportLines> tempMeterReadingImportLines;
	public TempMeterReadingImport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempMeterReadingImport(String id, String code, String importDate, String fromDate, String toDate,
			String statusCode, String statusName, String remarks, String error, String mrSourceCode,
			String mrSourceName, String createdBy, String createdDate, String modifiedBy, String modifiedDate,
			List<TempMeterReadingImportLines> tempMeterReadingImportLines) {
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
		this.tempMeterReadingImportLines = tempMeterReadingImportLines;
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
	public String getImportDate() {
		return importDate;
	}
	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMrSourceCode() {
		return mrSourceCode;
	}
	public void setMrSourceCode(String mrSourceCode) {
		this.mrSourceCode = mrSourceCode;
	}
	public String getMrSourceName() {
		return mrSourceName;
	}
	public void setMrSourceName(String mrSourceName) {
		this.mrSourceName = mrSourceName;
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
	public List<TempMeterReadingImportLines> getTempMeterReadingImportLines() {
		return tempMeterReadingImportLines;
	}
	public void setTempMeterReadingImportLines(List<TempMeterReadingImportLines> tempMeterReadingImportLines) {
		this.tempMeterReadingImportLines = tempMeterReadingImportLines;
	}
	@Override
	public String toString() {
		return "TempMeterReadingImport [id=" + id + ", code=" + code + ", importDate=" + importDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", remarks=" + remarks + ", error=" + error + ", mrSourceCode=" + mrSourceCode + ", mrSourceName="
				+ mrSourceName + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", tempMeterReadingImportLines="
				+ tempMeterReadingImportLines + "]";
	}
	
	
}
