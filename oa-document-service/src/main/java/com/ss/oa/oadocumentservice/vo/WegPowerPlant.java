package com.ss.oa.oadocumentservice.vo;

import java.util.List;



public class WegPowerPlant {
	private String id;
	private String powerplantName;
	private String orgId;
	private String orgName;
	private String companyId;
	private String companyName;
	private String serviceId;
	private String serviceNumber;
	private String voltageCode;
	private String voltageName;
	private String windPassCode;
	private String windPassName;
	private String makeCode;
	private String makeName;
	private String capacity;
	private String noOfUnits;
	private String rate;
	private String commissionDate;
	private List<BuyerAgreement> buyerAgreements;
	public WegPowerPlant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WegPowerPlant(String id, String powerplantName, String orgId, String orgName, String companyId,
			String companyName, String serviceId, String serviceNumber, String voltageCode, String voltageName,
			String windPassCode, String windPassName, String makeCode, String makeName, String capacity,
			String noOfUnits, String rate, String commissionDate, List<BuyerAgreement> buyerAgreements) {
		super();
		this.id = id;
		this.powerplantName = powerplantName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.serviceId = serviceId;
		this.serviceNumber = serviceNumber;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.windPassCode = windPassCode;
		this.windPassName = windPassName;
		this.makeCode = makeCode;
		this.makeName = makeName;
		this.capacity = capacity;
		this.noOfUnits = noOfUnits;
		this.rate = rate;
		this.commissionDate = commissionDate;
		this.buyerAgreements = buyerAgreements;
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
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
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
	public String getMakeCode() {
		return makeCode;
	}
	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getNoOfUnits() {
		return noOfUnits;
	}
	public void setNoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCommissionDate() {
		return commissionDate;
	}
	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}
	public List<BuyerAgreement> getBuyerAgreements() {
		return buyerAgreements;
	}
	public void setBuyerAgreements(List<BuyerAgreement> buyerAgreements) {
		this.buyerAgreements = buyerAgreements;
	}
	@Override
	public String toString() {
		return "WegPowerPlant [id=" + id + ", powerplantName=" + powerplantName + ", orgId=" + orgId + ", orgName="
				+ orgName + ", companyId=" + companyId + ", companyName=" + companyName + ", serviceId=" + serviceId
				+ ", serviceNumber=" + serviceNumber + ", voltageCode=" + voltageCode + ", voltageName=" + voltageName
				+ ", windPassCode=" + windPassCode + ", windPassName=" + windPassName + ", makeCode=" + makeCode
				+ ", makeName=" + makeName + ", capacity=" + capacity + ", noOfUnits=" + noOfUnits + ", rate=" + rate
				+ ", commissionDate=" + commissionDate + ", buyerAgreements=" + buyerAgreements + "]";
	}
	
}
