package com.ss.oa.model.transaction;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="IMP_BANKING_BALANCE")
@CreationTimestamp @UpdateTimestamp
@Entity
@Scope("prototype")
public class ImportBankingBalance {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String remarks;
	private String genServiceNumber;
	private String month;
	private String year;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	@Column(columnDefinition = "char")
	private String enabled;
	private String mCompanyId;
	private String bankingServiceId;
	private String importRemarks;
	@Column(columnDefinition = "char")
	private String cleanRec;
	@Column(columnDefinition = "char")
	private String imported;
	private String mCompanyServiceId;
	
	public ImportBankingBalance() {
		super();
	}

	public ImportBankingBalance(String id, String remarks, String genServiceNumber, String month, String year,
			String c1, String c2, String c3, String c4, String c5, String createdBy, LocalDate createdDt,
			String modifiedBy, LocalDate modifiedDt, String enabled, String mCompanyId, String bankingServiceId,
			String importRemarks, String cleanRec, String imported, String mCompanyServiceId) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.genServiceNumber = genServiceNumber;
		this.month = month;
		this.year = year;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.enabled = enabled;
		this.mCompanyId = mCompanyId;
		this.bankingServiceId = bankingServiceId;
		this.importRemarks = importRemarks;
		this.cleanRec = cleanRec;
		this.imported = imported;
		this.mCompanyServiceId = mCompanyServiceId;
	}

	@Override
	public String toString() {
		return "ImportBankingBalance [id=" + id + ", remarks=" + remarks + ", genServiceNumber=" + genServiceNumber
				+ ", month=" + month + ", year=" + year + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4
				+ ", c5=" + c5 + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", enabled=" + enabled + ", mCompanyId=" + mCompanyId
				+ ", bankingServiceId=" + bankingServiceId + ", importRemarks=" + importRemarks + ", cleanRec="
				+ cleanRec + ", imported=" + imported + ", mCompanyServiceId=" + mCompanyServiceId + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getmCompanyId() {
		return mCompanyId;
	}

	public void setmCompanyId(String mCompanyId) {
		this.mCompanyId = mCompanyId;
	}

	public String getBankingServiceId() {
		return bankingServiceId;
	}

	public void setBankingServiceId(String bankingServiceId) {
		this.bankingServiceId = bankingServiceId;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public String getCleanRec() {
		return cleanRec;
	}

	public void setCleanRec(String cleanRec) {
		this.cleanRec = cleanRec;
	}

	public String getImported() {
		return imported;
	}

	public void setImported(String imported) {
		this.imported = imported;
	}

	public String getmCompanyServiceId() {
		return mCompanyServiceId;
	}

	public void setmCompanyServiceId(String mCompanyServiceId) {
		this.mCompanyServiceId = mCompanyServiceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
