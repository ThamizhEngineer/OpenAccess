package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="IMP_FEEDER_LOSS")
@CreationTimestamp @UpdateTimestamp
@Entity
public class ImportFeederLoss {
	
	@Id
	private String id;
	@Column(name = "BATCH_KEY")
	private String batchKey;
	@Column(name = "M_ORG_NAME")
	private String orgName;
	@Column(name = "M_SUBSTATION_NAME")
	private String substationName;
	@Column(name = "M_FEEDER_NAME")
	private String feederName;
	@Column(name = "MONTH")
	private String month;
	@Column(name = "YEAR")
	private String year;
	@Column(name = "BULK_METER_READING")
	private String bulkMeterReading;
	@Column(name = "TOTAL_ALL_GENERATORS")
	private String totalAllGen;
	@Column(name = "LOSS_PERCENT")
	private String lossPercentage;
	@Column(name = "M_ORG_ID")
	private String orgId;
	@Column(name = "M_SUBSTATION_ID")
	private String substationId;
	@Column(name = "M_FEEDER_ID")
	private String feederId;
	@Column(name = "ENABLED")
	private String enabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	@Column(name = "IS_IMPORTED")
	private String isImport;
	@Column(name = "RESULT_DESC")
	private String resultDesc;

	
	
	public ImportFeederLoss() {
		super();
	}



	public ImportFeederLoss(String id, String batchKey, String orgName, String substationName, String feederName,
			String month, String year, String bulkMeterReading, String totalAllGen, String lossPercentage, String orgId,
			String substationId, String feederId, String enabled, String createdBy, LocalDate createdDt,
			String modifiedBy, LocalDate modifiedDt, String isImport, String resultDesc) {
		super();
		this.id = id;
		this.batchKey = batchKey;
		this.orgName = orgName;
		this.substationName = substationName;
		this.feederName = feederName;
		this.month = month;
		this.year = year;
		this.bulkMeterReading = bulkMeterReading;
		this.totalAllGen = totalAllGen;
		this.lossPercentage = lossPercentage;
		this.orgId = orgId;
		this.substationId = substationId;
		this.feederId = feederId;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.isImport = isImport;
		this.resultDesc = resultDesc;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getBatchKey() {
		return batchKey;
	}



	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getSubstationName() {
		return substationName;
	}



	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}



	public String getFeederName() {
		return feederName;
	}



	public void setFeederName(String feederName) {
		this.feederName = feederName;
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



	public String getBulkMeterReading() {
		return bulkMeterReading;
	}



	public void setBulkMeterReading(String bulkMeterReading) {
		this.bulkMeterReading = bulkMeterReading;
	}



	public String getTotalAllGen() {
		return totalAllGen;
	}



	public void setTotalAllGen(String totalAllGen) {
		this.totalAllGen = totalAllGen;
	}



	public String getLossPercentage() {
		return lossPercentage;
	}



	public void setLossPercentage(String lossPercentage) {
		this.lossPercentage = lossPercentage;
	}



	public String getOrgId() {
		return orgId;
	}



	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}



	public String getSubstationId() {
		return substationId;
	}



	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}



	public String getFeederId() {
		return feederId;
	}



	public void setFeederId(String feederId) {
		this.feederId = feederId;
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



	public String getIsImport() {
		return isImport;
	}



	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}



	public String getResultDesc() {
		return resultDesc;
	}



	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}



	@Override
	public String toString() {
		return "ImportFeederLoss [id=" + id + ", batchKey=" + batchKey + ", orgName=" + orgName + ", substationName="
				+ substationName + ", feederName=" + feederName + ", month=" + month + ", year=" + year
				+ ", bulkMeterReading=" + bulkMeterReading + ", totalAllGen=" + totalAllGen + ", lossPercentage="
				+ lossPercentage + ", orgId=" + orgId + ", substationId=" + substationId + ", feederId=" + feederId
				+ ", enabled=" + enabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", isImport=" + isImport + ", resultDesc=" + resultDesc
				+ "]";
	}


	}
