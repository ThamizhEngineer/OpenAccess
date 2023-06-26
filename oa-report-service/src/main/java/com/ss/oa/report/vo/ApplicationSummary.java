package com.ss.oa.report.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Entity
@Table(name = "V_APPLICATION")
@Getter
public class ApplicationSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String processTypeCode;
	private String saleTypeCode;
	@Column(columnDefinition="CHAR")
	private String fuelTypeCode;
	private String agmtPeriodCode;
	@Column(columnDefinition="CHAR")
	private String oaaId;
	private String powerSaleId;
	private String genCompServId;
	@Column(columnDefinition="CHAR")
	private String genCompServNumber;
	private String genEndOrgId;
	@Column(columnDefinition="CHAR")
	private String genVoltageCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDt;
	@Column(columnDefinition="CHAR")
	private String genCapacity;
	@Column(columnDefinition="CHAR")
	private String proposedQuantum;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate applnDt;
	private String consumerCompServId;
	private String consumerEndOrgId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	private String statusCode;

	@Transient
	private String recCounts;
	
	@Formula("(SELECT to_char(ps.APPLN_DT,'mm') FROM V_APPLICATION ps WHERE ps.ID=id)")
	private String month;
	@Formula("(SELECT to_char(ps.APPLN_DT,'yyyy') FROM V_APPLICATION ps WHERE ps.ID=id)")
	private String year;
	
	public ApplicationSummary() {
		super();
	}
	
	
	
	public ApplicationSummary(String id, String processTypeCode, String saleTypeCode, String fuelTypeCode,
			String agmtPeriodCode, String oaaId, String powerSaleId, String genCompServId, String genCompServNumber,
			String genEndOrgId, String genVoltageCode, LocalDate fromDt, LocalDate toDt, String genCapacity,
			String proposedQuantum, LocalDate applnDt, String consumerCompServId, String consumerEndOrgId,
			LocalDate createdDate, LocalDate modifiedDate, String statusCode,String recCounts, String month,
			String year) {
		super();
		this.id = id;
		this.processTypeCode = processTypeCode;
		this.saleTypeCode = saleTypeCode;
		this.fuelTypeCode = fuelTypeCode;
		this.agmtPeriodCode = agmtPeriodCode;
		this.oaaId = oaaId;
		this.powerSaleId = powerSaleId;
		this.genCompServId = genCompServId;
		this.genCompServNumber = genCompServNumber;
		this.genEndOrgId = genEndOrgId;
		this.genVoltageCode = genVoltageCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.genCapacity = genCapacity;
		this.proposedQuantum = proposedQuantum;
		this.applnDt = applnDt;
		this.consumerCompServId = consumerCompServId;
		this.consumerEndOrgId = consumerEndOrgId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.statusCode = statusCode;
		this.recCounts = recCounts;
		this.month = month;
		this.year = year;
	}



	public ApplicationSummary(String id, String processTypeCode, String saleTypeCode, String fuelTypeCode, String agmtPeriodCode,
			String oaaId, String powerSaleId, String genCompServId, String genCompServNumber, String genEndOrgId,
			String genVoltageCode, LocalDate fromDt, LocalDate toDt, String genCapacity, String proposedQuantum,
			LocalDate applnDt, String consumerCompServId, String consumerEndOrgId,
			LocalDate createdDate,
			LocalDate modifiedDate,String statusCode) {
		super();
		this.id = id;
		this.processTypeCode = processTypeCode;
		this.saleTypeCode = saleTypeCode;
		this.fuelTypeCode = fuelTypeCode;
		this.agmtPeriodCode = agmtPeriodCode;
		this.oaaId = oaaId;
		this.powerSaleId = powerSaleId;
		this.genCompServId = genCompServId;
		this.genCompServNumber = genCompServNumber;
		this.genEndOrgId = genEndOrgId;
		this.genVoltageCode = genVoltageCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.genCapacity = genCapacity;
		this.proposedQuantum = proposedQuantum;
		this.applnDt = applnDt;
		this.consumerCompServId = consumerCompServId;
		this.consumerEndOrgId = consumerEndOrgId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.statusCode=statusCode;
	}



	@Override
	public String toString() {
		return "ApplicationSummary [id=" + id + ", processTypeCode=" + processTypeCode + ", saleTypeCode="
				+ saleTypeCode + ", fuelTypeCode=" + fuelTypeCode + ", agmtPeriodCode=" + agmtPeriodCode + ", oaaId="
				+ oaaId + ", powerSaleId=" + powerSaleId + ", genCompServId=" + genCompServId + ", genCompServNumber="
				+ genCompServNumber + ", genEndOrgId=" + genEndOrgId + ", genVoltageCode=" + genVoltageCode
				+ ", fromDt=" + fromDt + ", toDt=" + toDt + ", genCapacity=" + genCapacity + ", proposedQuantum="
				+ proposedQuantum + ", applnDt=" + applnDt + ", consumerCompServId=" + consumerCompServId
				+ ", consumerEndOrgId=" + consumerEndOrgId + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", statusCode=" + statusCode + ", recCounts=" + recCounts + ", month=" + month
				+ ", year=" + year + "]";
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getProcessTypeCode() {
		return processTypeCode;
	}



	public void setProcessTypeCode(String processTypeCode) {
		this.processTypeCode = processTypeCode;
	}



	public String getSaleTypeCode() {
		return saleTypeCode;
	}



	public void setSaleTypeCode(String saleTypeCode) {
		this.saleTypeCode = saleTypeCode;
	}



	public String getFuelTypeCode() {
		return fuelTypeCode;
	}



	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}



	public String getAgmtPeriodCode() {
		return agmtPeriodCode;
	}



	public void setAgmtPeriodCode(String agmtPeriodCode) {
		this.agmtPeriodCode = agmtPeriodCode;
	}



	public String getOaaId() {
		return oaaId;
	}



	public void setOaaId(String oaaId) {
		this.oaaId = oaaId;
	}



	public String getPowerSaleId() {
		return powerSaleId;
	}



	public void setPowerSaleId(String powerSaleId) {
		this.powerSaleId = powerSaleId;
	}



	public String getGenCompServId() {
		return genCompServId;
	}



	public void setGenCompServId(String genCompServId) {
		this.genCompServId = genCompServId;
	}



	public String getGenCompServNumber() {
		return genCompServNumber;
	}



	public void setGenCompServNumber(String genCompServNumber) {
		this.genCompServNumber = genCompServNumber;
	}



	public String getGenEndOrgId() {
		return genEndOrgId;
	}



	public void setGenEndOrgId(String genEndOrgId) {
		this.genEndOrgId = genEndOrgId;
	}



	public String getGenVoltageCode() {
		return genVoltageCode;
	}



	public void setGenVoltageCode(String genVoltageCode) {
		this.genVoltageCode = genVoltageCode;
	}



	public LocalDate getFromDt() {
		return fromDt;
	}



	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}



	public LocalDate getToDt() {
		return toDt;
	}



	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}



	public String getGenCapacity() {
		return genCapacity;
	}



	public void setGenCapacity(String genCapacity) {
		this.genCapacity = genCapacity;
	}



	public String getProposedQuantum() {
		return proposedQuantum;
	}



	public void setProposedQuantum(String proposedQuantum) {
		this.proposedQuantum = proposedQuantum;
	}



	public LocalDate getApplnDt() {
		return applnDt;
	}



	public void setApplnDt(LocalDate applnDt) {
		this.applnDt = applnDt;
	}



	public String getConsumerCompServId() {
		return consumerCompServId;
	}



	public void setConsumerCompServId(String consumerCompServId) {
		this.consumerCompServId = consumerCompServId;
	}



	public String getConsumerEndOrgId() {
		return consumerEndOrgId;
	}



	public void setConsumerEndOrgId(String consumerEndOrgId) {
		this.consumerEndOrgId = consumerEndOrgId;
	}



	public LocalDate getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}



	public LocalDate getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public String getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}



	public String getRecCounts() {
		return recCounts;
	}



	public void setRecCounts(String recCounts) {
		this.recCounts = recCounts;
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

	
}
