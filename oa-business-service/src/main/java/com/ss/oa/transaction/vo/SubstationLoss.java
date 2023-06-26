package com.ss.oa.transaction.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_SUBSTATION_LOSS")
@CreationTimestamp @UpdateTimestamp
@Entity
public class SubstationLoss {
	
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
	@Column(name="M_FEEDER_ID")
	private String feederId;
	@Column(name="M_FEEDER_NAME")
	private String feederName;
	private String lossPercent;
	private String batchKey;
	private String month;
	private String year;
	private String bulkMeterReading;
	private String totalAllWegs;
	@Column(columnDefinition = "char")
	private String enabled;	
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	@Column(name="FUEL_TYPE")
	private String fuelType;

	
	public SubstationLoss() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SubstationLoss(String id, String orgId, String orgName, String substationId, String substationName,
			String lossPercent, String batchKey, String month, String year, String bulkMeterReading,
			String totalAllWegs, String enabled, String createdBy, LocalDate createdDt) {
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
		this.bulkMeterReading = bulkMeterReading;
		this.totalAllWegs = totalAllWegs;
		this.enabled = enabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
	}


	@Override
	public String toString() {
		return "SubstationLoss [id=" + id + ", orgId=" + orgId + ", orgName=" + orgName + ", substationId="
				+ substationId + ", substationName=" + substationName + ", lossPercent=" + lossPercent + ", batchKey="
				+ batchKey + ", month=" + month + ", year=" + year + ", bulkMeterReading=" + bulkMeterReading
				+ ", totalAllWegs=" + totalAllWegs + ", enabled=" + enabled + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getFeederId() {
		return feederId;
	}


	public String getFuelType() {
		return fuelType;
	}


	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
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



	

	
	
}
