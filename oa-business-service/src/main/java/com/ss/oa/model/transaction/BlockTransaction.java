package com.ss.oa.model.transaction;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "T_BLOCK_TXN")

public class BlockTransaction {
	
	@SequenceGenerator(name = "id_T_BLOCK_TXN_SEQ", sequenceName = "T_BLOCK_TXN_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_T_BLOCK_TXN_SEQ")
	@Id
	private Long id;
	@Column(name="M_ORG_ID")
	private String orgId;
	@Column(name="M_COMP_SERVICE_ID")
	private String compServiceId;
	@Column(name="M_COMP_SERVICE_NUMBER")
	private String compServiceNumber;
	private String year;
	private String month;
	private String remarks;
	private String transactionType;
	private String status;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String modifiedBy=" ";
	private String createdBy=" ";
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getmOrgId() {
		return orgId;
	}
	public void setmOrgId(String mOrgId) {
		this.orgId = mOrgId;
	}
	public String getmCompServiceId() {
		return compServiceId;
	}
	public void setmCompServiceId(String mCompServiceId) {
		this.compServiceId = mCompServiceId;
	}
	public BlockTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlockTransaction(Long id, String mOrgId, String mCompServiceId, String mCompServiceNumber, String year,
			String month, String remarks, String transactionType, String status, LocalDate modifiedDate,
			String modifiedBy, String createdBy, LocalDate createdDt) {
		super();
		this.id = id;
		this.orgId = mOrgId;
		this.compServiceId = mCompServiceId;
		this.compServiceNumber = mCompServiceNumber;
		this.year = year;
		this.month = month;
		this.remarks = remarks;
		this.transactionType = transactionType;
		this.status = status;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
	}
	@Override
	public String toString() {
		return "BlockTransaction [id=" + id + ", mOrgId=" + orgId + ", mCompServiceId=" + compServiceId
				+ ", mCompServiceNumber=" + compServiceNumber + ", year=" + year + ", month=" + month + ", remarks="
				+ remarks + ", transactionType=" + transactionType + ", status=" + status + ", modifiedDate="
				+ modifiedDate + ", modifiedBy=" + modifiedBy + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", getId()=" + getId() + ", getmOrgId()=" + getmOrgId() + ", getmCompServiceId()="
				+ getmCompServiceId() + ", getmCompServiceNumber()=" + getmCompServiceNumber() + ", getYear()="
				+ getYear() + ", getMonth()=" + getMonth() + ", getRemarks()=" + getRemarks()
				+ ", getTransactionType()=" + getTransactionType() + ", getStatus()=" + getStatus()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getModifiedBy()=" + getModifiedBy()
				+ ", getCreatedBy()=" + getCreatedBy() + ", getCreatedDt()=" + getCreatedDt() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getmCompServiceNumber() {
		return compServiceNumber;
	}
	public void setmCompServiceNumber(String mCompServiceNumber) {
		this.compServiceNumber = mCompServiceNumber;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	
	
}