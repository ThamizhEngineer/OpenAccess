package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="DELETE_TXN_LOG")
@Entity
public class DeleteTransactionLog {
	
	@Id
	private String id;
	private String serviceNo;
    private String mOrgId;
	private String month;
	private String year;
	private String remarks;
	private String reading;
	private String statement;
	private String allotment;
	private String ledger;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String createdBy;
	private String result;
	
	public DeleteTransactionLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeleteTransactionLog(String id, String serviceNo, String mOrgId, String month, String year, String remarks,
			String reading, String statement, String allotment, String ledger, LocalDate createdDate, String createdBy,
			String result) {
		super();
		this.id = id;
		this.serviceNo = serviceNo;
		this.mOrgId = mOrgId;
		this.month = month;
		this.year = year;
		this.remarks = remarks;
		this.reading = reading;
		this.statement = statement;
		this.allotment = allotment;
		this.ledger = ledger;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.result = result;
	}
	
	

	public DeleteTransactionLog(String serviceNo, String month, String year) {
		super();
		this.serviceNo = serviceNo;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "DeleteTransactionLog [id=" + id + ", serviceNo=" + serviceNo + ", mOrgId=" + mOrgId + ", month=" + month
				+ ", year=" + year + ", remarks=" + remarks + ", reading=" + reading + ", statement=" + statement
				+ ", allotment=" + allotment + ", ledger=" + ledger + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", result=" + result + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getmOrgId() {
		return mOrgId;
	}

	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getAllotment() {
		return allotment;
	}

	public void setAllotment(String allotment) {
		this.allotment = allotment;
	}

	public String getLedger() {
		return ledger;
	}

	public void setLedger(String ledger) {
		this.ledger = ledger;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
	

}
