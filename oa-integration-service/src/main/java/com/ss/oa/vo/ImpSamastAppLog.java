package com.ss.oa.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "L_IMP_SAMAST_APP")
@CreationTimestamp
@UpdateTimestamp
public class ImpSamastAppLog {
	
	@SequenceGenerator(name = "id_L_IMP_SAMAST_APP_SEQ", sequenceName = "L_IMP_SAMAST_APP_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_L_IMP_SAMAST_APP_SEQ")
	@Id
	private Integer id;
	private String batchKey;
	private String noOfRecords;
	private String appType; // type - NOC, APPLN_APP
	private String remarks;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime toDt;
	private String enabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public ImpSamastAppLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImpSamastAppLog(Integer id, String batchKey, String noOfRecords, String appType, String remarks,
			LocalDateTime fromDt, LocalDateTime toDt, String enabled, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.batchKey = batchKey;
		this.noOfRecords = noOfRecords;
		this.appType = appType;
		this.remarks = remarks;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}

	public String getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(String noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDateTime fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDateTime getToDt() {
		return toDt;
	}

	public void setToDt(LocalDateTime toDt) {
		this.toDt = toDt;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "ImpSamastAppLog [id=" + id + ", batchKey=" + batchKey + ", noOfRecords=" + noOfRecords + ", appType="
				+ appType + ", remarks=" + remarks + ", fromDt=" + fromDt + ", toDt=" + toDt + ", enabled=" + enabled
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	

}
