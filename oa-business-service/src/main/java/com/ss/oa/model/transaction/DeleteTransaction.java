package com.ss.oa.model.transaction;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="INT_DELETE_TXN")
@Entity
public class DeleteTransaction {
	
	@Id
	private String remarks;
	private String genServiceNumber;
    @Formula("(select vcs.fuel_group_name from v_company_service vcs where vcs.M_COMP_SERV_NUMBER= gen_service_number)")
	private String fuelGroupName;
    @Formula("(select vcs.m_org_id from v_company_service vcs where vcs.M_COMP_SERV_NUMBER= gen_service_number)")
    private String orgId;
	private String readingMonth;
	private String readingYear;
	@Column(columnDefinition = "char")
	private String deletedLedger;
	@Column(columnDefinition = "char")
	private String deletedEs;
	@Column(columnDefinition = "char")
	private String deletedGs;
	@Column(columnDefinition = "char")
	private String deletedMr;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	private String deleteRemarks;
	@Column(columnDefinition = "char")
	private String processed;
	
	
	public DeleteTransaction() {
		super();
	}
	
	



	@Override
	public String toString() {
		return "DeleteTransaction [remarks=" + remarks + ", genServiceNumber=" + genServiceNumber + ", fuelGroupName="
				+ fuelGroupName + ", orgId=" + orgId + ", readingMonth=" + readingMonth + ", readingYear=" + readingYear
				+ ", deletedLedger=" + deletedLedger + ", deletedEs=" + deletedEs + ", deletedGs=" + deletedGs
				+ ", deletedMr=" + deletedMr + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", deleteRemarks=" + deleteRemarks + ", processed="
				+ processed + "]";
	}

	public DeleteTransaction(String remarks, String genServiceNumber, String fuelGroupName, String orgId,
			String readingMonth, String readingYear, String deletedLedger, String deletedEs, String deletedGs,
			String deletedMr, String createdBy, LocalDate createdDt, String modifiedBy, LocalDate modifiedDt,
			String deleteRemarks, String processed) {
		super();
		this.remarks = remarks;
		this.genServiceNumber = genServiceNumber;
		this.fuelGroupName = fuelGroupName;
		this.orgId = orgId;
		this.readingMonth = readingMonth;
		this.readingYear = readingYear;
		this.deletedLedger = deletedLedger;
		this.deletedEs = deletedEs;
		this.deletedGs = deletedGs;
		this.deletedMr = deletedMr;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.deleteRemarks = deleteRemarks;
		this.processed = processed;
	}



	


	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public String getGenServiceNumber() {
		return genServiceNumber;
	}



	public void setGenServiceNumber(String genServiceNumber) {
		this.genServiceNumber = genServiceNumber;
	}



	public String getFuelGroupName() {
		return fuelGroupName;
	}



	public void setFuelGroupName(String fuelGroupName) {
		this.fuelGroupName = fuelGroupName;
	}



	public String getOrgId() {
		return orgId;
	}



	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}



	public String getReadingMonth() {
		return readingMonth;
	}



	public void setReadingMonth(String readingMonth) {
		this.readingMonth = readingMonth;
	}



	public String getReadingYear() {
		return readingYear;
	}



	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}



	public String getDeletedLedger() {
		return deletedLedger;
	}



	public void setDeletedLedger(String deletedLedger) {
		this.deletedLedger = deletedLedger;
	}



	public String getDeletedEs() {
		return deletedEs;
	}



	public void setDeletedEs(String deletedEs) {
		this.deletedEs = deletedEs;
	}



	public String getDeletedGs() {
		return deletedGs;
	}



	public void setDeletedGs(String deletedGs) {
		this.deletedGs = deletedGs;
	}



	public String getDeletedMr() {
		return deletedMr;
	}



	public void setDeletedMr(String deletedMr) {
		this.deletedMr = deletedMr;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public LocalDate getCreatedDt() {
		return createdDt;
	}



	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}



	public String getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}



	public LocalDate getModifiedDt() {
		return modifiedDt;
	}



	public void setModifiedDt(LocalDate modifiedDt) {
		this.modifiedDt = modifiedDt;
	}



	public String getDeleteRemarks() {
		return deleteRemarks;
	}



	public void setDeleteRemarks(String deleteRemarks) {
		this.deleteRemarks = deleteRemarks;
	}



	public String getProcessed() {
		return processed;
	}



	public void setProcessed(String processed) {
		this.processed = processed;
	}


	

}
