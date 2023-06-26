package com.ss.oa.oadocumentservice.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class FinancialUnutilBanking {

	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String sno;
	private String ServiceNumber;
	private String edcNo;
	private String edcName;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String stMonth;
	private String stYear;
	private String rate;
	private String serviceName;
	private String amount;
	private String stTotal;
	private String unutilisedEnergy;
	private String wegGroupCode;
	private String wegGroupDesc;
	
	public FinancialUnutilBanking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public FinancialUnutilBanking(String id, String sno, String serviceNumber, String edcNo, String edcName, String c1,
			String c2, String c3, String c4, String c5, String stMonth, String stYear, String rate, String serviceName,
			String amount, String stTotal, String unutilisedEnergy, String wegGroupCode, String wegGroupDesc) {
		super();
		this.id = id;
		this.sno = sno;
		ServiceNumber = serviceNumber;
		this.edcNo = edcNo;
		this.edcName = edcName;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.stMonth = stMonth;
		this.stYear = stYear;
		this.rate = rate;
		this.serviceName = serviceName;
		this.amount = amount;
		this.stTotal = stTotal;
		this.unutilisedEnergy = unutilisedEnergy;
		this.wegGroupCode = wegGroupCode;
		this.wegGroupDesc = wegGroupDesc;
	}

	@Override
	public String toString() {
		return "FinancialUnutilBanking [id=" + id + ", sno=" + sno + ", ServiceNumber=" + ServiceNumber + ", edcNo="
				+ edcNo + ", edcName=" + edcName + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5="
				+ c5 + ", stMonth=" + stMonth + ", stYear=" + stYear + ", rate=" + rate + ", serviceName=" + serviceName
				+ ", amount=" + amount + ", stTotal=" + stTotal + ", unutilisedEnergy=" + unutilisedEnergy
				+ ", wegGroupCode=" + wegGroupCode + ", wegGroupDesc=" + wegGroupDesc + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSno() {
		return sno;
	}


	public void setSno(String sno) {
		this.sno = sno;
	}


	public String getServiceNumber() {
		return ServiceNumber;
	}


	public void setServiceNumber(String serviceNumber) {
		ServiceNumber = serviceNumber;
	}


	public String getEdcNo() {
		return edcNo;
	}


	public void setEdcNo(String edcNo) {
		this.edcNo = edcNo;
	}


	public String getEdcName() {
		return edcName;
	}


	public void setEdcName(String edcName) {
		this.edcName = edcName;
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


	public String getStMonth() {
		return stMonth;
	}


	public void setStMonth(String stMonth) {
		this.stMonth = stMonth;
	}


	public String getStYear() {
		return stYear;
	}


	public void setStYear(String stYear) {
		this.stYear = stYear;
	}


	public String getRate() {
		return rate;
	}


	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getStTotal() {
		return stTotal;
	}


	public void setStTotal(String stTotal) {
		this.stTotal = stTotal;
	}


	public String getUnutilisedEnergy() {
		return unutilisedEnergy;
	}


	public void setUnutilisedEnergy(String unutilisedEnergy) {
		this.unutilisedEnergy = unutilisedEnergy;
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

	
}
