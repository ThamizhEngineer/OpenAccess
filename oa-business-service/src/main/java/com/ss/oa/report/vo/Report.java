package com.ss.oa.report.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Report {

	private String orgId,orgName,orgCode;
	private String companyId,companyName,companyCode;
	private String companyServiceId,companyServiceNumber;
	private String fuelTypeCode,isCaptive,totalQuantum;
	private String month,year,totalCapacity;
	private String districtCode,districtName,voltageCode,voltageName;
	private String parentOrgId,parentOrgName,parentOrgCode;
	private String noOfBuyers,totalUnitsBought;
	
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Report(String orgId, String orgName, String orgCode, String companyId, String companyName,
			String companyCode, String companyServiceId, String companyServiceNumber, String fuelTypeCode,
			String isCaptive, String totalQuantum, String month, String year, String totalCapacity, String districtCode,
			String districtName, String voltageCode, String voltageName, String parentOrgId, String parentOrgName,
			String parentOrgCode, String noOfBuyers, String totalUnitsBought) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.companyServiceId = companyServiceId;
		this.companyServiceNumber = companyServiceNumber;
		this.fuelTypeCode = fuelTypeCode;
		this.isCaptive = isCaptive;
		this.totalQuantum = totalQuantum;
		this.month = month;
		this.year = year;
		this.totalCapacity = totalCapacity;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.parentOrgId = parentOrgId;
		this.parentOrgName = parentOrgName;
		this.parentOrgCode = parentOrgCode;
		this.noOfBuyers = noOfBuyers;
		this.totalUnitsBought = totalUnitsBought;
	}

	@Override
	public String toString() {
		return "Report [orgId=" + orgId + ", orgName=" + orgName + ", orgCode=" + orgCode + ", companyId=" + companyId
				+ ", companyName=" + companyName + ", companyCode=" + companyCode + ", companyServiceId="
				+ companyServiceId + ", companyServiceNumber=" + companyServiceNumber + ", fuelTypeCode=" + fuelTypeCode
				+ ", isCaptive=" + isCaptive + ", totalQuantum=" + totalQuantum + ", month=" + month + ", year=" + year
				+ ", totalCapacity=" + totalCapacity + ", districtCode=" + districtCode + ", districtName="
				+ districtName + ", voltageCode=" + voltageCode + ", voltageName=" + voltageName + ", parentOrgId="
				+ parentOrgId + ", parentOrgName=" + parentOrgName + ", parentOrgCode=" + parentOrgCode
				+ ", noOfBuyers=" + noOfBuyers + ", totalUnitsBought=" + totalUnitsBought + "]";
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public String getCompanyServiceId() {
		return companyServiceId;
	}

	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public String getTotalQuantum() {
		return totalQuantum;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getTotalCapacity() {
		return totalCapacity;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public String getVoltageName() {
		return voltageName;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public String getNoOfBuyers() {
		return noOfBuyers;
	}

	public String getTotalUnitsBought() {
		return totalUnitsBought;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public void setTotalQuantum(String totalQuantum) {
		this.totalQuantum = totalQuantum;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setTotalCapacity(String totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public void setNoOfBuyers(String noOfBuyers) {
		this.noOfBuyers = noOfBuyers;
	}

	public void setTotalUnitsBought(String totalUnitsBought) {
		this.totalUnitsBought = totalUnitsBought;
	}
	
	
	
	
}
