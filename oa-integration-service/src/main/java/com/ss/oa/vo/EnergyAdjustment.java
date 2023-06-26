package com.ss.oa.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

public class EnergyAdjustment {
	private String serviceNumber,orgCode,orgName, buyerName, billingMonth, billingYear, drawalVoltage;
	private List<EnergySource> energySources;
	public EnergyAdjustment() {
		super();
		this.energySources = new ArrayList<EnergySource>();
	}
	public EnergyAdjustment(String serviceNumber, String orgCode, String orgName, String buyerName, String billingMonth,
			String billingYear, String drawalVoltage, List<EnergySource> energySources) {
		super();
		this.serviceNumber = serviceNumber;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.buyerName = buyerName;
		this.billingMonth = billingMonth;
		this.billingYear = billingYear;
		this.drawalVoltage = drawalVoltage;
		this.energySources = energySources;
	}
	@Override
	public String toString() {
		return "EnergyAdjustment [serviceNumber=" + serviceNumber + ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", buyerName=" + buyerName + ", billingMonth=" + billingMonth + ", billingYear=" + billingYear
				+ ", drawalVoltage=" + drawalVoltage + ", energySources=" + energySources + "]";
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
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
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public String getBillingYear() {
		return billingYear;
	}
	public void setBillingYear(String billingYear) {
		this.billingYear = billingYear;
	}
	public String getDrawalVoltage() {
		return drawalVoltage;
	}
	public void setDrawalVoltage(String drawalVoltage) {
		this.drawalVoltage = drawalVoltage;
	}
	public List<EnergySource> getEnergySources() {
		return energySources;
	}
	public void setEnergySources(List<EnergySource> energySources) {
		this.energySources = energySources;
	}
	
}