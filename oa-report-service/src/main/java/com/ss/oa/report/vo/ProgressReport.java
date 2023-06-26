package com.ss.oa.report.vo;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="PROGRESS_REPORT")
public class ProgressReport {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private Integer totalServices;
	private Integer validCaptiveServices;
	private Integer validStbServices;
	private Integer validWegThirdPartyServices;
	private Integer totalInvalidServices;
	private Integer invalidCaptiveServices;
	private Integer invalidStbServices;
	private Integer invalidWegThirdPartyServices;
	private Integer totalStatements;
	private Integer captiveStatements;
	private Integer stbStatements;
	private Integer wegThirdPartyStatements;
	private Integer allotmentsCreated;
	private Integer allotmentsCompleted;
	private Integer meterReadingsDownloaded;
	private Integer manualMrReading;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	private String month;
	private String year;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String type;
	private String mOrgId;
	private long totalImportIsCaptive;
	private long totalExportIsCaptive;
	private long netGenerationIsCaptive;
	private long totalImportStb;
	private long totalExportStb;
	private long netGenerationStb;
	private long totalImportThirdParty;
	private long totalExportThirdParty;
	private long netGenerationThirdParty;
	private String fuelTypeCode;
	private String fuelTypeName;
	private double bankedUnits;
	private long totalValidServices;
	
	public ProgressReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProgressReport(String id, Integer totalServices, Integer validCaptiveServices, Integer validStbServices,
			Integer validWegThirdPartyServices, Integer totalInvalidServices, Integer invalidCaptiveServices,
			Integer invalidStbServices, Integer invalidWegThirdPartyServices, Integer totalStatements,
			Integer captiveStatements, Integer stbStatements, Integer wegThirdPartyStatements,
			Integer allotmentsCreated, Integer allotmentsCompleted, Integer meterReadingsDownloaded,
			Integer manualMrReading, LocalDate createdDate, String month, String year, LocalDate modifiedDate,
			String type, String mOrgId, long totalImportIsCaptive, long totalExportIsCaptive,
			long netGenerationIsCaptive, long totalImportStb, long totalExportStb, long netGenerationStb,
			long totalImportThirdParty, long totalExportThirdParty, long netGenerationThirdParty, String fuelTypeCode,
			String fuelTypeName, double bankedUnits, long totalValidServices) {
		super();
		this.id = id;
		this.totalServices = totalServices;
		this.validCaptiveServices = validCaptiveServices;
		this.validStbServices = validStbServices;
		this.validWegThirdPartyServices = validWegThirdPartyServices;
		this.totalInvalidServices = totalInvalidServices;
		this.invalidCaptiveServices = invalidCaptiveServices;
		this.invalidStbServices = invalidStbServices;
		this.invalidWegThirdPartyServices = invalidWegThirdPartyServices;
		this.totalStatements = totalStatements;
		this.captiveStatements = captiveStatements;
		this.stbStatements = stbStatements;
		this.wegThirdPartyStatements = wegThirdPartyStatements;
		this.allotmentsCreated = allotmentsCreated;
		this.allotmentsCompleted = allotmentsCompleted;
		this.meterReadingsDownloaded = meterReadingsDownloaded;
		this.manualMrReading = manualMrReading;
		this.createdDate = createdDate;
		this.month = month;
		this.year = year;
		this.modifiedDate = modifiedDate;
		this.type = type;
		this.mOrgId = mOrgId;
		this.totalImportIsCaptive = totalImportIsCaptive;
		this.totalExportIsCaptive = totalExportIsCaptive;
		this.netGenerationIsCaptive = netGenerationIsCaptive;
		this.totalImportStb = totalImportStb;
		this.totalExportStb = totalExportStb;
		this.netGenerationStb = netGenerationStb;
		this.totalImportThirdParty = totalImportThirdParty;
		this.totalExportThirdParty = totalExportThirdParty;
		this.netGenerationThirdParty = netGenerationThirdParty;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.bankedUnits = bankedUnits;
		this.totalValidServices = totalValidServices;
	}

	@Override
	public String toString() {
		return "ProgressReport [id=" + id + ", totalServices=" + totalServices + ", validCaptiveServices="
				+ validCaptiveServices + ", validStbServices=" + validStbServices + ", validWegThirdPartyServices="
				+ validWegThirdPartyServices + ", totalInvalidServices=" + totalInvalidServices
				+ ", invalidCaptiveServices=" + invalidCaptiveServices + ", invalidStbServices=" + invalidStbServices
				+ ", invalidWegThirdPartyServices=" + invalidWegThirdPartyServices + ", totalStatements="
				+ totalStatements + ", captiveStatements=" + captiveStatements + ", stbStatements=" + stbStatements
				+ ", wegThirdPartyStatements=" + wegThirdPartyStatements + ", allotmentsCreated=" + allotmentsCreated
				+ ", allotmentsCompleted=" + allotmentsCompleted + ", meterReadingsDownloaded="
				+ meterReadingsDownloaded + ", manualMrReading=" + manualMrReading + ", createdDate=" + createdDate
				+ ", month=" + month + ", year=" + year + ", modifiedDate=" + modifiedDate + ", type=" + type
				+ ", mOrgId=" + mOrgId + ", totalImportIsCaptive=" + totalImportIsCaptive + ", totalExportIsCaptive="
				+ totalExportIsCaptive + ", netGenerationIsCaptive=" + netGenerationIsCaptive + ", totalImportStb="
				+ totalImportStb + ", totalExportStb=" + totalExportStb + ", netGenerationStb=" + netGenerationStb
				+ ", totalImportThirdParty=" + totalImportThirdParty + ", totalExportThirdParty="
				+ totalExportThirdParty + ", netGenerationThirdParty=" + netGenerationThirdParty + ", fuelTypeCode="
				+ fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", bankedUnits=" + bankedUnits
				+ ", totalValidServices=" + totalValidServices + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTotalServices() {
		return totalServices;
	}

	public void setTotalServices(Integer totalServices) {
		this.totalServices = totalServices;
	}

	public Integer getValidCaptiveServices() {
		return validCaptiveServices;
	}

	public void setValidCaptiveServices(Integer validCaptiveServices) {
		this.validCaptiveServices = validCaptiveServices;
	}

	public Integer getValidStbServices() {
		return validStbServices;
	}

	public void setValidStbServices(Integer validStbServices) {
		this.validStbServices = validStbServices;
	}

	public Integer getValidWegThirdPartyServices() {
		return validWegThirdPartyServices;
	}

	public void setValidWegThirdPartyServices(Integer validWegThirdPartyServices) {
		this.validWegThirdPartyServices = validWegThirdPartyServices;
	}

	public Integer getTotalInvalidServices() {
		return totalInvalidServices;
	}

	public void setTotalInvalidServices(Integer totalInvalidServices) {
		this.totalInvalidServices = totalInvalidServices;
	}

	public Integer getInvalidCaptiveServices() {
		return invalidCaptiveServices;
	}

	public void setInvalidCaptiveServices(Integer invalidCaptiveServices) {
		this.invalidCaptiveServices = invalidCaptiveServices;
	}

	public Integer getInvalidStbServices() {
		return invalidStbServices;
	}

	public void setInvalidStbServices(Integer invalidStbServices) {
		this.invalidStbServices = invalidStbServices;
	}

	public Integer getInvalidWegThirdPartyServices() {
		return invalidWegThirdPartyServices;
	}

	public void setInvalidWegThirdPartyServices(Integer invalidWegThirdPartyServices) {
		this.invalidWegThirdPartyServices = invalidWegThirdPartyServices;
	}

	public Integer getTotalStatements() {
		return totalStatements;
	}

	public void setTotalStatements(Integer totalStatements) {
		this.totalStatements = totalStatements;
	}

	public Integer getCaptiveStatements() {
		return captiveStatements;
	}

	public void setCaptiveStatements(Integer captiveStatements) {
		this.captiveStatements = captiveStatements;
	}

	public Integer getStbStatements() {
		return stbStatements;
	}

	public void setStbStatements(Integer stbStatements) {
		this.stbStatements = stbStatements;
	}

	public Integer getWegThirdPartyStatements() {
		return wegThirdPartyStatements;
	}

	public void setWegThirdPartyStatements(Integer wegThirdPartyStatements) {
		this.wegThirdPartyStatements = wegThirdPartyStatements;
	}

	public Integer getAllotmentsCreated() {
		return allotmentsCreated;
	}

	public void setAllotmentsCreated(Integer allotmentsCreated) {
		this.allotmentsCreated = allotmentsCreated;
	}

	public Integer getAllotmentsCompleted() {
		return allotmentsCompleted;
	}

	public void setAllotmentsCompleted(Integer allotmentsCompleted) {
		this.allotmentsCompleted = allotmentsCompleted;
	}

	public Integer getMeterReadingsDownloaded() {
		return meterReadingsDownloaded;
	}

	public void setMeterReadingsDownloaded(Integer meterReadingsDownloaded) {
		this.meterReadingsDownloaded = meterReadingsDownloaded;
	}

	public Integer getManualMrReading() {
		return manualMrReading;
	}

	public void setManualMrReading(Integer manualMrReading) {
		this.manualMrReading = manualMrReading;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
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

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getmOrgId() {
		return mOrgId;
	}

	public void setmOrgId(String mOrgId) {
		this.mOrgId = mOrgId;
	}

	public long getTotalImportIsCaptive() {
		return totalImportIsCaptive;
	}

	public void setTotalImportIsCaptive(long totalImportIsCaptive) {
		this.totalImportIsCaptive = totalImportIsCaptive;
	}

	public long getTotalExportIsCaptive() {
		return totalExportIsCaptive;
	}

	public void setTotalExportIsCaptive(long totalExportIsCaptive) {
		this.totalExportIsCaptive = totalExportIsCaptive;
	}

	public long getNetGenerationIsCaptive() {
		return netGenerationIsCaptive;
	}

	public void setNetGenerationIsCaptive(long netGenerationIsCaptive) {
		this.netGenerationIsCaptive = netGenerationIsCaptive;
	}

	public long getTotalImportStb() {
		return totalImportStb;
	}

	public void setTotalImportStb(long totalImportStb) {
		this.totalImportStb = totalImportStb;
	}

	public long getTotalExportStb() {
		return totalExportStb;
	}

	public void setTotalExportStb(long totalExportStb) {
		this.totalExportStb = totalExportStb;
	}

	public long getNetGenerationStb() {
		return netGenerationStb;
	}

	public void setNetGenerationStb(long netGenerationStb) {
		this.netGenerationStb = netGenerationStb;
	}

	public long getTotalImportThirdParty() {
		return totalImportThirdParty;
	}

	public void setTotalImportThirdParty(long totalImportThirdParty) {
		this.totalImportThirdParty = totalImportThirdParty;
	}

	public long getTotalExportThirdParty() {
		return totalExportThirdParty;
	}

	public void setTotalExportThirdParty(long totalExportThirdParty) {
		this.totalExportThirdParty = totalExportThirdParty;
	}

	public long getNetGenerationThirdParty() {
		return netGenerationThirdParty;
	}

	public void setNetGenerationThirdParty(long netGenerationThirdParty) {
		this.netGenerationThirdParty = netGenerationThirdParty;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public double getBankedUnits() {
		return bankedUnits;
	}

	public void setBankedUnits(double bankedUnits) {
		this.bankedUnits = bankedUnits;
	}

	public long getTotalValidServices() {
		return totalValidServices;
	}

	public void setTotalValidServices(long totalValidServices) {
		this.totalValidServices = totalValidServices;
	}

	
}
