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
@Table(name = "V_T_NOC")
@Getter
public class NocSummary {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String powerSaleId;
	@Column(columnDefinition="CHAR")
	private String processTypeCode;
	private String code;
	private String consumerCompServId;
	private String saleTypeCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate applnDt;
	private String proposedQuantum;
	private String agmtPeriodCode;
	private String statusCode;
	private String consumerCompId;
	private String consumerCompServNumber;
	private String fuelTypeCode;
	private String consumerEndOrgId;
	@Formula("(SELECT cs.M_ORG_NAME FROM V_COMPANY_SERVICE cs WHERE cs.ID=CONSUMER_COMP_SERV_ID)")
	private String consumerEndOrgName;
	@Formula("(SELECT cs.M_COMPANY_NAME FROM V_COMPANY_SERVICE cs WHERE cs.ID=CONSUMER_COMP_SERV_ID)")
	private String consumerCompName;
	private String consumerVoltageCode;
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDate;
	@Formula("(SELECT to_char(ps.APPLN_DT,'mm') FROM V_T_NOC ps WHERE ps.ID=id)")
	private String month;
	@Formula("(SELECT to_char(ps.APPLN_DT,'yyyy') FROM V_T_NOC ps WHERE ps.ID=id)")
	private String year;
	
	public NocSummary() {
		super();
	}

	public NocSummary(String id, String powerSaleId, String processTypeCode, String code, String consumerCompServId,
			String saleTypeCode, LocalDate applnDt, String proposedQuantum, String agmtPeriodCode, String statusCode,
			String consumerCompId, String consumerCompServNumber, String fuelTypeCode, String consumerEndOrgId,
			String consumerVoltageCode, LocalDate createdDate, LocalDate modifiedDate, String month, String year) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.code = code;
		this.consumerCompServId = consumerCompServId;
		this.saleTypeCode = saleTypeCode;
		this.applnDt = applnDt;
		this.proposedQuantum = proposedQuantum;
		this.agmtPeriodCode = agmtPeriodCode;
		this.statusCode = statusCode;
		this.consumerCompId = consumerCompId;
		this.consumerCompServNumber = consumerCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.consumerEndOrgId = consumerEndOrgId;
		this.consumerVoltageCode = consumerVoltageCode;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.month = month;
		this.year = year;
		
	}

	public NocSummary(String id, String powerSaleId, String processTypeCode, String code, String consumerCompServId,
			String saleTypeCode, LocalDate applnDt, String proposedQuantum, String agmtPeriodCode, String statusCode,
			String consumerCompId, String consumerCompServNumber, String fuelTypeCode, String consumerEndOrgId,
			String consumerVoltageCode, String month, String year,String consumerEndOrgName,String consumerCompName) {
		super();
		this.id = id;
		this.powerSaleId = powerSaleId;
		this.processTypeCode = processTypeCode;
		this.code = code;
		this.consumerCompServId = consumerCompServId;
		this.saleTypeCode = saleTypeCode;
		this.applnDt = applnDt;
		this.proposedQuantum = proposedQuantum;
		this.agmtPeriodCode = agmtPeriodCode;
		this.statusCode = statusCode;
		this.consumerCompId = consumerCompId;
		this.consumerCompServNumber = consumerCompServNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.consumerEndOrgId = consumerEndOrgId;
		this.consumerVoltageCode = consumerVoltageCode;
		this.month = month;
		this.year = year;
		this.consumerEndOrgName =consumerEndOrgName;
		this.consumerCompName =consumerCompName;
	}

	@Override
	public String toString() {
		return "NocSummary [id=" + id + ", powerSaleId=" + powerSaleId + ", processTypeCode=" + processTypeCode
				+ ", code=" + code + ", consumerCompServId=" + consumerCompServId + ", saleTypeCode=" + saleTypeCode
				+ ", applnDt=" + applnDt + ", proposedQuantum=" + proposedQuantum + ", agmtPeriodCode=" + agmtPeriodCode
				+ ", statusCode=" + statusCode + ", consumerCompId=" + consumerCompId + ", consumerCompServNumber="
				+ consumerCompServNumber + ", fuelTypeCode=" + fuelTypeCode + ", consumerEndOrgId=" + consumerEndOrgId
				+ ", consumerVoltageCode=" + consumerVoltageCode + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", month=" + month + ", year=" + year + "]";
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

	public String getConsumerCompServId() {
		return consumerCompServId;
	}

	public void setConsumerCompServId(String consumerCompServId) {
		this.consumerCompServId = consumerCompServId;
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

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
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

	public void setConsumerEndOrgName(String consumerEndOrgName) {
		this.consumerEndOrgName = consumerEndOrgName;
	}

	public void setConsumerCompName(String consumerCompName) {
		this.consumerCompName = consumerCompName;
	}

	
}
