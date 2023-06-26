package com.ss.oa.model.master;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name = "M_COMPANY_SHAREHOLDER")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class CompanyShareHolder  implements Serializable {

@Transient	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="M_COMPANY_ID")
	private String companyId;
	@Column(name="M_SHAREHOLDER_COMPANY_ID")
	private String shareHolderCompanyId;
	@Column(name="\"share\"" )
	private String share;
	private String measure;
	@Column(columnDefinition = "char") 
	private String enabled;
	private String remarks;
	private String createdBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String modifiedBy;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	@Column(name="M_SHAREHOLDER_COMP_SERV_ID")
	private String shareHolderCompanyServiceId;
	private String quantum;
	private String importRemarks;
//	@Formula("(SELECT comp.CODE FROM M_COMPANY_SHAREHOLDER shares JOIN M_COMPANY comp ON shares.M_COMPANY_ID=comp.ID WHERE shares.M_COMPANY_ID=M_COMPANY_ID)")
	@Formula("(SELECT comp.CODE FROM M_COMPANY_SHAREHOLDER shares JOIN M_COMPANY comp ON shares.M_COMPANY_ID=comp.ID WHERE shares.ID=id)")
	private String companyCode;
	@Formula("(SELECT comp.NAME FROM M_COMPANY_SHAREHOLDER shares JOIN M_COMPANY comp ON shares.M_COMPANY_ID=comp.ID WHERE shares.ID=id)")
	private String companyName; 
	@Formula("(SELECT comp.NAME FROM M_COMPANY_SHAREHOLDER sharescomp JOIN M_COMPANY comp ON sharescomp.M_SHAREHOLDER_COMPANY_ID=comp.ID WHERE sharescomp.ID=id)")
	private String shareHolderCompanyCode;
	@Formula("(SELECT comp.CODE FROM M_COMPANY_SHAREHOLDER sharescomp JOIN M_COMPANY comp ON sharescomp.M_SHAREHOLDER_COMPANY_ID=comp.ID WHERE sharescomp.ID=id)")
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
