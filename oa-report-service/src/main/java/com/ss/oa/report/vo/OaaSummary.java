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
@Table(name = "V_T_OAA")
@Getter
public class OaaSummary {
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
	private String statusCode;
	private String genCompId;
	private String genCompServNumber;
	private String fuelTypeCode;
	private String genVoltageCode;
	private String genEndOrgId;
	private String consumerCompServId;
	private String consumerCompId;
	private String consumerCompServNumber;
	private String consumerEndOrgId;
	private String consumerVoltageCode;
	@Formula("(SELECT to_char(ps.APPLN_DT,'mm') FROM V_T_OAA ps WHERE ps.ID=id)")
	private String month;
	@Formula("(SELECT to_char(ps.APPLN_DT,'yyyy') FROM V_T_OAA ps WHERE ps.ID=id)")
	private String year;
	public OaaSummary() {
		super();
	}
	public OaaSummary(String id, String powerSaleId, String processTypeCode, String genCompServId, String saleTypeCode,
			LocalDate fromDt, LocalDate toDt, LocalDate applnDt, String statusCode, String genCompId,
			String genCompServNumber, String fuelTypeCode, String genVoltageCode, String genEndOrgId,
			String consumerCompServId, String consumerCompId, String consumerCompServNumber, String consumerEndOrgId,
			String consumerVoltageCode, String month, String year) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.genCompServId = genCompServId;
		this.saleTypeCode = saleTypeCode;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.applnDt = applnDt;
		this.statusCode = statusCode;
		this.genCompId = genCompId;
		this.genCompServNumber = genCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.genVoltageCode = genVoltageCode;
		this.genEndOrgId = genEndOrgId;
		this.consumerCompServId = consumerCompServId;
		this.consumerCompId = consumerCompId;
		this.consumerCompServNumber = consumerCompServNumber;
		this.consumerEndOrgId = consumerEndOrgId;
		this.consumerVoltageCode = consumerVoltageCode;
		this.month = month;
		this.year = year;
	}
	@Override
	public String toString() {
		return "OaaSummary [id=" + id + ", powerSaleId=" + powerSaleId + ", processTypeCode=" + processTypeCode
				+ ", genCompServId=" + genCompServId + ", saleTypeCode=" + saleTypeCode + ", fromDt=" + fromDt
				+ ", toDt=" + toDt + ", applnDt=" + applnDt + ", statusCode=" + statusCode + ", genCompId=" + genCompId
				+ ", genCompServNumber=" + genCompServNumber + ", fuelTypeCode=" + fuelTypeCode + ", genVoltageCode="
				+ genVoltageCode + ", genEndOrgId=" + genEndOrgId + ", consumerCompServId=" + consumerCompServId
				+ ", consumerCompId=" + consumerCompId + ", consumerCompServNumber=" + consumerCompServNumber
				+ ", consumerEndOrgId=" + consumerEndOrgId + ", consumerVoltageCode=" + consumerVoltageCode + ", month="
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
	public String getGenVoltageCode() {
		return genVoltageCode;
	}
	public void setGenVoltageCode(String genVoltageCode) {
		this.genVoltageCode = genVoltageCode;
	}
	public String getGenEndOrgId() {
		return genEndOrgId;
	}
	public void setGenEndOrgId(String genEndOrgId) {
		this.genEndOrgId = genEndOrgId;
	}
	public String getConsumerCompServId() {
		return consumerCompServId;
	}
	public void setConsumerCompServId(String consumerCompServId) {
		this.consumerCompServId = consumerCompServId;
	}
	public String getConsumerCompId() {
		return consumerCompId;
	}
	public void setConsumerCompId(String consumerCompId) {
		this.consumerCompId = consumerCompId;
	}
	public String getConsumerCompServNumber() {
		return consumerCompServNumber;
	}
	public void setConsumerCompServNumber(String consumerCompServNumber) {
		this.consumerCompServNumber = consumerCompServNumber;
	}
	public String getConsumerEndOrgId() {
		return consumerEndOrgId;
	}
	public void setConsumerEndOrgId(String consumerEndOrgId) {
		this.consumerEndOrgId = consumerEndOrgId;
	}
	public String getConsumerVoltageCode() {
		return consumerVoltageCode;
	}
	public void setConsumerVoltageCode(String consumerVoltageCode) {
		this.consumerVoltageCode = consumerVoltageCode;
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
