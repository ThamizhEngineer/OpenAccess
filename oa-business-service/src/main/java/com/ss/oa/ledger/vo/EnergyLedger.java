package com.ss.oa.ledger.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class EnergyLedger {
	
	private String id;
	private String orgId;
	private String companyId;
	private String companyServiceId;
	private String serviceTypeCode;
	private String month;
	private String year;
	private String fromDate,toDate;
	private String c1,c2,c3,c4,c5;
	private String ledgerDate;
	private String energyIn,energyOut;
	private String nullify;
	private String remarks;
	private String energySaleOrderId,energySaleOrderLineId;
	private String companyName,companyCode;
	private String companyServiceNumber;
	private String orgName,orgCode;
	private String allowLowerSlotAdmt;
	
	public EnergyLedger(){
		super();
	}

	public EnergyLedger(String id, String orgId, String companyId, String companyServiceId, String serviceTypeCode,
			String month, String year, String fromDate, String toDate, String c1, String c2, String c3, String c4,
			String c5, String ledgerDate, String energyIn, String energyOut, String nullify, String remarks,
			String energySaleOrderId, String energySaleOrderLineId, String companyName, String companyCode,
			String companyServiceNumber, String orgName, String orgCode, String allowLowerSlotAdmt) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.companyId = companyId;
		this.companyServiceId = companyServiceId;
		this.serviceTypeCode = serviceTypeCode;
		this.month = month;
		this.year = year;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.ledgerDate = ledgerDate;
		this.energyIn = energyIn;
		this.energyOut = energyOut;
		this.nullify = nullify;
		this.remarks = remarks;
		this.energySaleOrderId = energySaleOrderId;
		this.energySaleOrderLineId = energySaleOrderLineId;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.companyServiceNumber = companyServiceNumber;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyServiceId() {
		return companyServiceId;
	}

	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(String ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public String getEnergyIn() {
		return energyIn;
	}

	public void setEnergyIn(String energyIn) {
		this.energyIn = energyIn;
	}

	public String getEnergyOut() {
		return energyOut;
	}

	public void setEnergyOut(String energyOut) {
		this.energyOut = energyOut;
	}

	public String getNullify() {
		return nullify;
	}

	public void setNullify(String nullify) {
		this.nullify = nullify;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEnergySaleOrderId() {
		return energySaleOrderId;
	}

	public void setEnergySaleOrderId(String energySaleOrderId) {
		this.energySaleOrderId = energySaleOrderId;
	}

	public String getEnergySaleOrderLineId() {
		return energySaleOrderLineId;
	}

	public void setEnergySaleOrderLineId(String energySaleOrderLineId) {
		this.energySaleOrderLineId = energySaleOrderLineId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}

	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getAllowLowerSlotAdmt() {
		return allowLowerSlotAdmt;
	}

	public void setAllowLowerSlotAdmt(String allowLowerSlotAdmt) {
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}

	@Override
	public String toString() {
		return "EnergyLedger [id=" + id + ", orgId=" + orgId + ", companyId=" + companyId + ", companyServiceId="
				+ companyServiceId + ", serviceTypeCode=" + serviceTypeCode + ", month=" + month + ", year=" + year
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + ", ledgerDate=" + ledgerDate + ", energyIn=" + energyIn + ", energyOut="
				+ energyOut + ", nullify=" + nullify + ", remarks=" + remarks + ", energySaleOrderId="
				+ energySaleOrderId + ", energySaleOrderLineId=" + energySaleOrderLineId + ", companyName="
				+ companyName + ", companyCode=" + companyCode + ", companyServiceNumber=" + companyServiceNumber
				+ ", orgName=" + orgName + ", orgCode=" + orgCode + ", allowLowerSlotAdmt=" + allowLowerSlotAdmt + "]";
	}

	
	
}
