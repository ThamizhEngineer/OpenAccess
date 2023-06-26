package com.ss.oa.report.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Entity
@Table(name = "V_T_ES_INTENT")
@Getter
public class EsiSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String powerSaleId;
	@Column(columnDefinition="CHAR")
	private String processTypeCode;
	private String genCompServId;
	private String saleTypeCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate applnDt;
	private String proposedQuantum;
	private String agmtPeriodCode;
	private String statusCode;
	private String genCompId;
	@Formula("(SELECT cs.M_COMPANY_NAME FROM V_COMPANY_SERVICE cs WHERE cs.ID=GEN_COMP_SERV_ID)")
	private String genCompName;
	private String genCompServNumber;
	private String fuelTypeCode;
	private String genEndOrgId;
	@Formula("(SELECT cs.M_ORG_NAME FROM V_COMPANY_SERVICE cs WHERE cs.ID=GEN_COMP_SERV_ID)")
	private String genEndOrgName;
	private String genVoltageCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@Formula("(SELECT to_char(ps.APPLN_DT,'mm') FROM V_T_ES_INTENT ps WHERE ps.ID=id)")
	private String month;
	@Formula("(SELECT to_char(ps.APPLN_DT,'yyyy') FROM V_T_ES_INTENT ps WHERE ps.ID=id)")
	private String year;
	public EsiSummary() {
		super();
	}
	public EsiSummary(String id, String powerSaleId, String processTypeCode, String genCompServId, String saleTypeCode,
			LocalDate fromDt, LocalDate toDt, LocalDate applnDt, String proposedQuantum, String agmtPeriodCode,
			String statusCode, String genCompId, String genCompServNumber, String fuelTypeCode, String genEndOrgId,
			String genVoltageCode, LocalDate createdDate, String month, String year,String genEndOrgName,String genCompName ) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.genCompServId = genCompServId;
		this.saleTypeCode = saleTypeCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.applnDt = applnDt;
		this.proposedQuantum = proposedQuantum;
		this.agmtPeriodCode = agmtPeriodCode;
		this.statusCode = statusCode;
		this.genCompId = genCompId;
		this.genCompServNumber = genCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.genEndOrgId = genEndOrgId;
		this.genVoltageCode = genVoltageCode;
		this.createdDate = createdDate;
		this.month = month;
		this.year = year;
		this.genEndOrgName = genEndOrgName;
		this.genCompName = genCompName;
	}
	@Override
	public String toString() {
		return "EsiSummary [id=" + id + ", powerSaleId=" + powerSaleId + ", processTypeCode=" + processTypeCode
				+ ", genCompServId=" + genCompServId + ", saleTypeCode=" + saleTypeCode + ", fromDt=" + fromDt
				+ ", toDt=" + toDt + ", applnDt=" + applnDt + ", proposedQuantum=" + proposedQuantum
				+ ", agmtPeriodCode=" + agmtPeriodCode + ", statusCode=" + statusCode + ", genCompId=" + genCompId
				+ ", genCompServNumber=" + genCompServNumber + ", fuelTypeCode=" + fuelTypeCode + ", genEndOrgId="
				+ genEndOrgId + ", genVoltageCode=" + genVoltageCode + ", createdDate=" + createdDate + ", month="
				+ month + ", year=" + year + "]";
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
	public LocalDate getApplnDt() {
		return applnDt;
	}
	public void setApplnDt(LocalDate applnDt) {
		this.applnDt = applnDt;
	}
	public String getProposedQuantum() {
		return proposedQuantum;
	}
	public void setProposedQuantum(String proposedQuantum) {
		this.proposedQuantum = proposedQuantum;
	}
	public String getAgmtPeriodCode() {
		return agmtPeriodCode;
	}
	public void setAgmtPeriodCode(String agmtPeriodCode) {
		this.agmtPeriodCode = agmtPeriodCode;
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
	public void setGenEndOrgName(String genEndOrgName) {
		this.genEndOrgName = genEndOrgName;
	}
	public void setGenCompName(String genCompName) {
		this.genCompName = genCompName;
	}
	
	
}
