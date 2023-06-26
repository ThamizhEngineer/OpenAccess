package com.ss.oa.vo;

import org.springframework.context.annotation.Scope;

public class TempSurplusUnit {
	private String serviceNo;
	private String suplrCode;
	private String suplrType;
	private String suplrName;
	private String readingDt;
	private String readingMnth;
	private String readingYr;
	private String c24;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	
	public TempSurplusUnit() {
		super();
	}

	public TempSurplusUnit(String serviceNo, String suplrCode, String suplrType, String suplrName, String readingDt,
			String readingMnth, String readingYr, String c24, String c1, String c2, String c3, String c4, String c5) {
		super();
		this.serviceNo = serviceNo;
		this.suplrCode = suplrCode;
		this.suplrType = suplrType;
		this.suplrName = suplrName;
		this.readingDt = readingDt;
		this.readingMnth = readingMnth;
		this.readingYr = readingYr;
		this.c24 = c24;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
	}

	@Override
	public String toString() {
		return "TempSurplusUnit [serviceNo=" + serviceNo + ", suplrCode=" + suplrCode + ", suplrType=" + suplrType
				+ ", suplrName=" + suplrName + ", readingDt=" + readingDt + ", readingMnth=" + readingMnth
				+ ", readingYr=" + readingYr + ", c24=" + c24 + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + "]";
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getSuplrCode() {
		return suplrCode;
	}

	public void setSuplrCode(String suplrCode) {
		this.suplrCode = suplrCode;
	}

	public String getSuplrType() {
		return suplrType;
	}

	public void setSuplrType(String suplrType) {
		this.suplrType = suplrType;
	}

	public String getSuplrName() {
		return suplrName;
	}

	public void setSuplrName(String suplrName) {
		this.suplrName = suplrName;
	}

	public String getReadingDt() {
		return readingDt;
	}

	public void setReadingDt(String readingDt) {
		this.readingDt = readingDt;
	}

	public String getReadingMnth() {
		return readingMnth;
	}

	public void setReadingMnth(String readingMnth) {
		this.readingMnth = readingMnth;
	}

	public String getReadingYr() {
		return readingYr;
	}

	public void setReadingYr(String readingYr) {
		this.readingYr = readingYr;
	}

	public String getC24() {
		return c24;
	}

	public void setC24(String c24) {
		this.c24 = c24;
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
	
	
}
