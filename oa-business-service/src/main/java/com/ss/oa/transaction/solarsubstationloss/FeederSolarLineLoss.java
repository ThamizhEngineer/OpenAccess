package com.ss.oa.transaction.solarsubstationloss;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name ="T_FEEDER_SOLAR_LINE_LOSS")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class FeederSolarLineLoss {

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
	@Column(name="LOSS_PERCENT")
	private String lossPercent;
	@Column(name="BATCH_KEY")
	private String batchKey;
	@Column(name="MONTH")
	private String month;
	@Column(name="YEAR")
	private String year;
	@Column(name="BULK_METER_READING")
	private String bulkMeterReading;
	@Column(name="TOTAL_ALL_WEGS")
	private String totalAllWegs;
	@Column(name ="ENABLED",columnDefinition = "char")
	private String enabled;
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="CREATED_DT")
	private LocalDate createdDt;

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
