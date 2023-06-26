package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CompanyShareHolder {

	private String id;
	private String companyId;
	private String shareHolderCompanyId;
	private String share;
	private String measure;
	private String enabled;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String shareHolderCompanyServiceId;
	private String quantum;
	private String importRemarks;
	private String companyCode;
	private String companyName; 
	private String shareHolderCompanyCode;
	private String shareHolderCompanyName;


	public CompanyShareHolder() {
		super();
	}


	public CompanyShareHolder(String id, String companyId, String shareHolderCompanyId, String share, String measure,
			String enabled, String remarks, String createdBy, LocalDate createdDate, String modifiedBy,
			LocalDate modifiedDate, String shareHolderCompanyServiceId, String quantum, String importRemarks,
			String companyCode, String companyName, String shareHolderCompanyCode, String shareHolderCompanyName) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.shareHolderCompanyId = shareHolderCompanyId;
		this.share = share;
		this.measure = measure;
		this.enabled = enabled;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.shareHolderCompanyServiceId = shareHolderCompanyServiceId;
		this.quantum = quantum;
		this.importRemarks = importRemarks;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.shareHolderCompanyCode = shareHolderCompanyCode;
		this.shareHolderCompanyName = shareHolderCompanyName;
	}


	@Override
	public String toString() {
		return "CompanyShareHolder [id=" + id + ", companyId=" + companyId + ", shareHolderCompanyId="
				+ shareHolderCompanyId + ", share=" + share + ", measure=" + measure + ", enabled=" + enabled
				+ ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", shareHolderCompanyServiceId="
				+ shareHolderCompanyServiceId + ", quantum=" + quantum + ", importRemarks=" + importRemarks
				+ ", companyCode=" + companyCode + ", companyName=" + companyName + ", shareHolderCompanyCode="
				+ shareHolderCompanyCode + ", shareHolderCompanyName=" + shareHolderCompanyName + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getShareHolderCompanyId() {
		return shareHolderCompanyId;
	}


	public void setShareHolderCompanyId(String shareHolderCompanyId) {
		this.shareHolderCompanyId = shareHolderCompanyId;
	}


	public String getShare() {
		return share;
	}


	public void setShare(String share) {
		this.share = share;
	}


	public String getMeasure() {
		return measure;
	}


	public void setMeasure(String measure) {
		this.measure = measure;
	}


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
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


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public LocalDate getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getShareHolderCompanyServiceId() {
		return shareHolderCompanyServiceId;
	}


	public void setShareHolderCompanyServiceId(String shareHolderCompanyServiceId) {
		this.shareHolderCompanyServiceId = shareHolderCompanyServiceId;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}


	public String getImportRemarks() {
		return importRemarks;
	}


	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}


	public String getCompanyCode() {
		return companyCode;
	}


	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getShareHolderCompanyCode() {
		return shareHolderCompanyCode;
	}


	public void setShareHolderCompanyCode(String shareHolderCompanyCode) {
		this.shareHolderCompanyCode = shareHolderCompanyCode;
	}


	public String getShareHolderCompanyName() {
		return shareHolderCompanyName;
	}


	public void setShareHolderCompanyName(String shareHolderCompanyName) {
		this.shareHolderCompanyName = shareHolderCompanyName;
	}

}
