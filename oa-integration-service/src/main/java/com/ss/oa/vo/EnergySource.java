package com.ss.oa.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

public class EnergySource {
	private String sequence, serviceNumber, sellerName,injectedVoltage,allowLowerSlotAdmt,commissionDate,isRec,adjustmentPriority;
	
	private Unit c1,c2,c3,c4,c5;
	private List<Charge> charges;
	public EnergySource() {
		super();
		this.charges = new ArrayList<Charge>();
	}
	public EnergySource(String sequence, String serviceNumber, String sellerName, String injectedVoltage,
			String allowLowerSlotAdmt, String commissionDate, String isRec, String adjustmentPriority, Unit c1, Unit c2,
			Unit c3, Unit c4, Unit c5, List<Charge> charges) {
		super();
		this.sequence = sequence;
		this.serviceNumber = serviceNumber;
		this.sellerName = sellerName;
		this.injectedVoltage = injectedVoltage;
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
		this.commissionDate = commissionDate;
		this.isRec = isRec;
		this.adjustmentPriority = adjustmentPriority;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.charges = charges;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getInjectedVoltage() {
		return injectedVoltage;
	}
	public void setInjectedVoltage(String injectedVoltage) {
		this.injectedVoltage = injectedVoltage;
	}
	public String getAllowLowerSlotAdmt() {
		return allowLowerSlotAdmt;
	}
	public void setAllowLowerSlotAdmt(String allowLowerSlotAdmt) {
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}
	public String getCommissionDate() {
		return commissionDate;
	}
	public void setCommissionDate(String commissionDate) {
		this.commissionDate = commissionDate;
	}
	public String getIsRec() {
		return isRec;
	}
	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}
	public String getAdjustmentPriority() {
		return adjustmentPriority;
	}
	public void setAdjustmentPriority(String adjustmentPriority) {
		this.adjustmentPriority = adjustmentPriority;
	}
	public Unit getC1() {
		return c1;
	}
	public void setC1(Unit c1) {
		this.c1 = c1;
	}
	public Unit getC2() {
		return c2;
	}
	public void setC2(Unit c2) {
		this.c2 = c2;
	}
	public Unit getC3() {
		return c3;
	}
	public void setC3(Unit c3) {
		this.c3 = c3;
	}
	public Unit getC4() {
		return c4;
	}
	public void setC4(Unit c4) {
		this.c4 = c4;
	}
	public Unit getC5() {
		return c5;
	}
	public void setC5(Unit c5) {
		this.c5 = c5;
	}
	public List<Charge> getCharges() {
		return charges;
	}
	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
	@Override
	public String toString() {
		return "EnergySource [sequence=" + sequence + ", serviceNumber=" + serviceNumber + ", sellerName=" + sellerName
				+ ", injectedVoltage=" + injectedVoltage + ", allowLowerSlotAdmt=" + allowLowerSlotAdmt
				+ ", commissionDate=" + commissionDate + ", isRec=" + isRec + ", adjustmentPriority="
				+ adjustmentPriority + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5
				+ ", charges=" + charges + "]";
	}
	
	
}