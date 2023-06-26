package com.ss.oa.transaction.vo;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "IMP_SUBSTATION_LOSS")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class ImportSubstationLoss {

	@Id
	private String id;
	@Column(name = "M_ORG_ID")
	private String orgId;
	@Column(name = "M_ORG_NAME")
	private String orgName;
	@Column(name = "M_SUBSTATION_ID")
	private String substationId;
	@Column(name = "M_SUBSTATION_NAME")
	private String substationName;
	@Column(name = "M_FEEDER_ID")
	private String feederId;
	@Column(name = "M_FEEDER_NAME")
	private String feederName;
	private String lossPercent;
	private String batchKey;
	private String month;
	private String year;
	private String resultDesc;
	private String bulkMeterReading;
	private String totalAllWegs;
	@Column(name = "FUEL_TYPE")
	private String fuelType;
	@Column(name = "IS_IMPORTED", columnDefinition = "char")
	private String isImported;
	@Column(columnDefinition = "char")
	private String enabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;

	public ImportSubstationLoss() {
		super();
	}

	public ImportSubstationLoss(String id, String orgId, String orgName, String substationId, String substationName,
			String lossPercent, String batchKey, String month, String year, String resultDesc, String bulkMeterReading,
			String totalAllWegs, String isImported, String enabled, String createdBy, LocalDate createdDt,
			String modifiedBy, LocalDate modifiedDt) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.orgName = orgName;
		this.substationId = substationId;
		this.substationName = substationName;
		this.lossPercent = lossPercent;
		this.batchKey = batchKey;
		this.month = month;
		this.year = year;
		this.resultDesc = resultDesc;
		this.bulkMeterReading = bulkMeterReading;
		this.totalAllWegs = totalAllWegs;
		this.isImported = isImported;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "ImportSubstationLoss [id=" + id + ", orgId=" + orgId + ", orgName=" + orgName + ", substationId="
				+ substationId + ", substationName=" + substationName + ", lossPercent=" + lossPercent + ", batchKey="
				+ batchKey + ", month=" + month + ", year=" + year + ", resultDesc=" + resultDesc
				+ ", bulkMeterReading=" + bulkMeterReading + ", totalAllWegs=" + totalAllWegs + ", isImported="
				+ isImported + ", enabled=" + enabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt
				+ ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt + "]";
	}

	public String getFeederId() {
		return feederId;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public String getFeederName() {
		return feederName;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSubstationId() {
		return substationId;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public String getLossPercent() {
		return lossPercent;
	}

	public void setLossPercent(String lossPercent) {
		this.lossPercent = lossPercent;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
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

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getBulkMeterReading() {
		return bulkMeterReading;
	}

	public void setBulkMeterReading(String bulkMeterReading) {
		this.bulkMeterReading = bulkMeterReading;
	}

	public String getTotalAllWegs() {
		return totalAllWegs;
	}

	public void setTotalAllWegs(String totalAllWegs) {
		this.totalAllWegs = totalAllWegs;
	}

	public String getIsImported() {
		return isImported;
	}

	public void setIsImported(String isImported) {
		this.isImported = isImported;
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

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

}
