package com.ss.oa.report.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Entity
@Table(name = "V_PP_SUMMARY")
@Getter
public class PowerplantSummary implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id", columnDefinition="VARCHAR2")
	private String id;
	private String powerplantName;
	private String orgId;
	private String orgCode;
	private String orgName;
	private String companyId;
	private String companyName;
	private String sellerServiceId;
	private String sellerServiceNumber;
	private String voltageCode;
	private String voltageName;
	private String fuelCode;
	private String fuelName;
	private String windPassCode;
	private String windPassName;
	private String totalCapacity;
	private String approvedCapacity;
	@Column(name="COMMISSION_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate commissionDate;
	private String ncesDivisionCode;
	private String ncesDesc;
	private String substationId;
	private String substationName;
	private String feederId;
	private String feederName;
	private String wegGroupCode;
	private String wegGroupDesc;
	private String meterNumber;



	
	public PowerplantSummary() {
		super();
	}




	public PowerplantSummary(String id, String powerplantName, String orgId, String orgCode, String orgName,
			String companyId, String companyName, String sellerServiceId, String sellerServiceNumber,
			String voltageCode, String voltageName, String fuelCode, String fuelName, String windPassCode,
			String windPassName, String totalCapacity, String approvedCapacity, LocalDate commissionDate,
			String ncesDivisionCode, String ncesDesc, String substationId, String substationName, String feederId,
			String feederName, String wegGroupCode, String wegGroupDesc, String meterNumber) {
		super();
		this.id = id;
		this.powerplantName = powerplantName;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.sellerServiceId = sellerServiceId;
		this.sellerServiceNumber = sellerServiceNumber;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.fuelCode = fuelCode;
		this.fuelName = fuelName;
		this.windPassCode = windPassCode;
		this.windPassName = windPassName;
		this.totalCapacity = totalCapacity;
		this.approvedCapacity = approvedCapacity;
		this.commissionDate = commissionDate;
		this.ncesDivisionCode = ncesDivisionCode;
		this.ncesDesc = ncesDesc;
		this.substationId = substationId;
		this.substationName = substationName;
		this.feederId = feederId;
		this.feederName = feederName;
		this.wegGroupCode = wegGroupCode;
		this.wegGroupDesc = wegGroupDesc;
		this.meterNumber=meterNumber;
	}




	@Override
	public String toString() {
		return "PowerplantSummary [id=" + id + ", powerplantName=" + powerplantName + ", orgId=" + orgId + ", orgCode="
				+ orgCode + ", orgName=" + orgName + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", sellerServiceId=" + sellerServiceId + ", sellerServiceNumber=" + sellerServiceNumber
				+ ", voltageCode=" + voltageCode + ", voltageName=" + voltageName + ", fuelCode=" + fuelCode
				+ ", fuelName=" + fuelName + ", windPassCode=" + windPassCode + ", windPassName=" + windPassName
				+ ", totalCapacity=" + totalCapacity + ", approvedCapacity=" + approvedCapacity + ", commissionDate="
				+ commissionDate + ", ncesDivisionCode=" + ncesDivisionCode + ", ncesDesc=" + ncesDesc
				+ ", substationId=" + substationId + ", substationName=" + substationName + ", feederId=" + feederId
				+ ", feederName=" + feederName + ", wegGroupCode=" + wegGroupCode + ", wegGroupDesc=" + wegGroupDesc
				+ ", meterNumber=" + meterNumber + "]";
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getPowerplantName() {
		return powerplantName;
	}




	public void setPowerplantName(String powerplantName) {
		this.powerplantName = powerplantName;
	}




	public String getOrgId() {
		return orgId;
	}




	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}




	public String getOrgCode() {
		return orgCode;
	}




	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}




	public String getOrgName() {
		return orgName;
	}




	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}




	public String getCompanyId() {
		return companyId;
	}




	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}




	public String getCompanyName() {
		return companyName;
	}




	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}




	public String getSellerServiceId() {
		return sellerServiceId;
	}




	public void setSellerServiceId(String sellerServiceId) {
		this.sellerServiceId = sellerServiceId;
	}




	public String getSellerServiceNumber() {
		return sellerServiceNumber;
	}




	public void setSellerServiceNumber(String sellerServiceNumber) {
		this.sellerServiceNumber = sellerServiceNumber;
	}




	public String getVoltageCode() {
		return voltageCode;
	}




	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}




	public String getVoltageName() {
		return voltageName;
	}




	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}




	public String getFuelCode() {
		return fuelCode;
	}




	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}




	public String getFuelName() {
		return fuelName;
	}




	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}




	public String getWindPassCode() {
		return windPassCode;
	}




	public void setWindPassCode(String windPassCode) {
		this.windPassCode = windPassCode;
	}




	public String getWindPassName() {
		return windPassName;
	}




	public void setWindPassName(String windPassName) {
		this.windPassName = windPassName;
	}




	public String getTotalCapacity() {
		return totalCapacity;
	}




	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}




	public String getApprovedCapacity() {
		return approvedCapacity;
	}




	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
	}




	public LocalDate getCommissionDate() {
		return commissionDate;
	}




	public void setCommissionDate(LocalDate commissionDate) {
		this.commissionDate = commissionDate;
	}




	public String getNcesDivisionCode() {
		return ncesDivisionCode;
	}




	public void setNcesDivisionCode(String ncesDivisionCode) {
		this.ncesDivisionCode = ncesDivisionCode;
	}




	public String getNcesDesc() {
		return ncesDesc;
	}




	public void setNcesDesc(String ncesDesc) {
		this.ncesDesc = ncesDesc;
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




	public String getWegGroupCode() {
		return wegGroupCode;
	}




	public void setWegGroupCode(String wegGroupCode) {
		this.wegGroupCode = wegGroupCode;
	}




	public String getWegGroupDesc() {
		return wegGroupDesc;
	}




	public void setWegGroupDesc(String wegGroupDesc) {
		this.wegGroupDesc = wegGroupDesc;
	}




	public String getMeterNumber() {
		return meterNumber;
	}




	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
