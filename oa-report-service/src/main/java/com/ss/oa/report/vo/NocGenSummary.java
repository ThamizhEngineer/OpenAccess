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
@Table(name = "V_T_NOC_GENERATOR")
@Getter
public class NocGenSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String powerSaleId;
	@Column(columnDefinition="CHAR")
	private String processTypeCode;
	private String code;
	private String genCompServId;
	private String saleTypeCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate applnDt;
	private String statusCode;
	private String genCompId;
	private String genCompServNumber;
	private String fuelTypeCode;
	private String genEndOrgId;
	private String genVoltageCode;
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	
	@Formula("(SELECT to_char(ps.APPLN_DT,'mm') FROM V_T_NOC_GENERATOR ps WHERE ps.ID=id)")
	private String month;
	@Formula("(SELECT to_char(ps.APPLN_DT,'yyyy') FROM V_T_NOC_GENERATOR ps WHERE ps.ID=id)")
	private String year;
	
	public NocGenSummary() {
		super();
	}

	public NocGenSummary(String id, String powerSaleId, String processTypeCode, String code, String genCompServId,
			String saleTypeCode, LocalDate applnDt, String statusCode, String genCompId, String genCompServNumber,
			String fuelTypeCode, String genEndOrgId, String genVoltageCode, LocalDate createdDate,
			LocalDate modifiedDate, String month, String year) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.code = code;
		this.genCompServId = genCompServId;
		this.saleTypeCode = saleTypeCode;
		this.applnDt = applnDt;
		this.statusCode = statusCode;
		this.genCompId = genCompId;
		this.genCompServNumber = genCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.genEndOrgId = genEndOrgId;
		this.genVoltageCode = genVoltageCode;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.month = month;
		this.year = year;
	}

	public NocGenSummary(String id, String powerSaleId, String processTypeCode, String code, String genCompServId,
			String saleTypeCode, LocalDate applnDt, String statusCode, String genCompId, String genCompServNumber,
			String fuelTypeCode, String genEndOrgId, String genVoltageCode, String month, String year) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.code = code;
		this.genCompServId = genCompServId;
		this.saleTypeCode = saleTypeCode;
		this.applnDt = applnDt;
		this.statusCode = statusCode;
		this.genCompId = genCompId;
		this.genCompServNumber = genCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.genEndOrgId = genEndOrgId;
		this.genVoltageCode = genVoltageCode;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "NocGenSummary [id=" + id + ", powerSaleId=" + powerSaleId + ", processTypeCode=" + processTypeCode
				+ ", code=" + code + ", genCompServId=" + genCompServId + ", saleTypeCode=" + saleTypeCode
				+ ", applnDt=" + applnDt + ", statusCode=" + statusCode + ", genCompId=" + genCompId
				+ ", genCompServNumber=" + genCompServNumber + ", fuelTypeCode=" + fuelTypeCode + ", genEndOrgId="
				+ genEndOrgId + ", genVoltageCode=" + genVoltageCode + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", month=" + month + ", year=" + year + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPowerSaleId() {
		return powerSaleId;
	}

	public void setPowerSaleId(String powerSaleId) {
		this.powerSaleId = powerSaleId;
	}

	public String getProcessTypeCode() {
		return processTypeCode;
	}

	public void setProcessTypeCode(String processTypeCode) {
		this.processTypeCode = processTypeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGenCompServId() {
		return genCompServId;
	}

	public void setGenCompServId(String genCompServId) {
		this.genCompServId = genCompServId;
	}

	public String getSaleTypeCode() {
		return saleTypeCode;
	}

	public void setSaleTypeCode(String saleTypeCode) {
		this.saleTypeCode = saleTypeCode;
	}

	public LocalDate getApplnDt() {
		return applnDt;
	}

	public void setApplnDt(LocalDate applnDt) {
		this.applnDt = applnDt;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getGenCompId() {
		return genCompId;
	}

	public void setGenCompId(String genCompId) {
		this.genCompId = genCompId;
	}

	public String getGenCompServNumber() {
		return genCompServNumber;
	}

	public void setGenCompServNumber(String genCompServNumber) {
		this.genCompServNumber = genCompServNumber;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
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
