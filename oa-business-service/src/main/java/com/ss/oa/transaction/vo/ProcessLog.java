package com.ss.oa.transaction.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
@Table(name="T_PROCESS_LOG")
@CreationTimestamp	@UpdateTimestamp
@Component
public class ProcessLog {
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "T_PROCESS_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private Integer id;
	private String processName;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime logDt;
	private String processStatus;
	private String userId;
	private String tEnergySaleId;
	private String genCompId;
	private String genServiceId;
	private String genEdcId;
	private String processId;
	private String attr1;
	private String attr2;
	private String attr3;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	private String remarks;
	@Formula("(SELECT esi.CODE FROM T_PROCESS_LOG pr JOIN T_ES_INTENT esi ON pr.T_ENERGY_SALE_ID=esi.ID where pr.T_ENERGY_SALE_ID=pr.T_ENERGY_SALE_ID and pr.id=id)")
	private String esiNumber;
	
	public ProcessLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProcessLog(Integer id, String processName, LocalDateTime logDt, String processStatus, String userId,
			String tEnergySaleId, String genCompId, String genServiceId, String genEdcId, String processId,
			String attr1, String attr2, String attr3, String createdBy, LocalDateTime createdDate, String modifiedBy,
			LocalDateTime modifiedDate, String remarks, String esiNumber) {
		super();
		this.id = id;
		this.processName = processName;
		this.logDt = logDt;
		this.processStatus = processStatus;
		this.userId = userId;
		this.tEnergySaleId = tEnergySaleId;
		this.genCompId = genCompId;
		this.genServiceId = genServiceId;
		this.genEdcId = genEdcId;
		this.processId = processId;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.remarks = remarks;
		this.esiNumber = esiNumber;
	}

	@Override
	public String toString() {
		return "ProcessLog [id=" + id + ", processName=" + processName + ", logDt=" + logDt + ", processStatus="
				+ processStatus + ", userId=" + userId + ", tEnergySaleId=" + tEnergySaleId + ", genCompId=" + genCompId
				+ ", genServiceId=" + genServiceId + ", genEdcId=" + genEdcId + ", processId=" + processId + ", attr1="
				+ attr1 + ", attr2=" + attr2 + ", attr3=" + attr3 + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", remarks=" + remarks
				+ ", esiNumber=" + esiNumber + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public LocalDateTime getLogDt() {
		return logDt;
	}

	public void setLogDt(LocalDateTime logDt) {
		this.logDt = logDt;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String gettEnergySaleId() {
		return tEnergySaleId;
	}

	public void settEnergySaleId(String tEnergySaleId) {
		this.tEnergySaleId = tEnergySaleId;
	}

	public String getGenCompId() {
		return genCompId;
	}

	public void setGenCompId(String genCompId) {
		this.genCompId = genCompId;
	}

	public String getGenServiceId() {
		return genServiceId;
	}

	public void setGenServiceId(String genServiceId) {
		this.genServiceId = genServiceId;
	}

	public String getGenEdcId() {
		return genEdcId;
	}

	public void setGenEdcId(String genEdcId) {
		this.genEdcId = genEdcId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEsiNumber() {
		return esiNumber;
	}

	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}

}
