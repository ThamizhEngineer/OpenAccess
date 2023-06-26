package com.ss.oa.model.transaction;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(name="T_JOB_HDR")
@CreationTimestamp @UpdateTimestamp
@Entity
public class Job {

	@Id
	private String id;
	private String jobStatusCode;
	private String jobCode;
	private String tEnergySaleId;
	private String oResultCode;
	private String oResultDesc;
	private String createdDate;
	private String modifiedDate;
	private String createdName;
	private String modifiedName;
	@Column(name="MONTH")
	private String month;
	@Column(name="YEAR")
	private String year;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobStatusCode() {
		return jobStatusCode;
	}
	public void setJobStatusCode(String jobStatusCode) {
		this.jobStatusCode = jobStatusCode;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String gettEnergySaleId() {
		return tEnergySaleId;
	}
	public void settEnergySaleId(String tEnergySaleId) {
		this.tEnergySaleId = tEnergySaleId;
	}
	public String getoResultCode() {
		return oResultCode;
	}
	public void setoResultCode(String oResultCode) {
		this.oResultCode = oResultCode;
	}
	public String getoResultDesc() {
		return oResultDesc;
	}
	public void setoResultDesc(String oResultDesc) {
		this.oResultDesc = oResultDesc;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedName() {
		return createdName;
	}
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	public String getModifiedName() {
		return modifiedName;
	}
	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
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
	public Job(String id, String jobStatusCode, String jobCode, String tEnergySaleId, String oResultCode,
			String oResultDesc, String createdDate, String modifiedDate, String createdName, String modifiedName,
			String month, String year) {
		super();
		this.id = id;
		this.jobStatusCode = jobStatusCode;
		this.jobCode = jobCode;
		this.tEnergySaleId = tEnergySaleId;
		this.oResultCode = oResultCode;
		this.oResultDesc = oResultDesc;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdName = createdName;
		this.modifiedName = modifiedName;
		this.month = month;
		this.year = year;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobStatusCode=" + jobStatusCode + ", jobCode=" + jobCode + ", tEnergySaleId="
				+ tEnergySaleId + ", oResultCode=" + oResultCode + ", oResultDesc=" + oResultDesc + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", createdName=" + createdName + ", modifiedName="
				+ modifiedName + ", month=" + month + ", year=" + year + ", getId()=" + getId()
				+ ", getJobStatusCode()=" + getJobStatusCode() + ", getJobCode()=" + getJobCode()
				+ ", gettEnergySaleId()=" + gettEnergySaleId() + ", getoResultCode()=" + getoResultCode()
				+ ", getoResultDesc()=" + getoResultDesc() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + ", getCreatedName()=" + getCreatedName()
				+ ", getModifiedName()=" + getModifiedName() + ", getMonth()=" + getMonth() + ", getYear()=" + getYear()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	





}
	

