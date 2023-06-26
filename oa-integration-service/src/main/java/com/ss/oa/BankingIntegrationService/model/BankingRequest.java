package com.ss.oa.BankingIntegrationService.model;

import java.math.BigDecimal;

public class BankingRequest {


	private String virtualAccountNo;
	private String utr;
	private String tranAmt;
	private String mode;
	
	public String getVirtualAccountNo() {
		return virtualAccountNo;
	}
	public void setVirtualAccountNo(String virtualAccountNo) {
		this.virtualAccountNo = virtualAccountNo;
	}
	public String getUtr() {
		return utr;
	}
	public void setUtr(String utr) {
		this.utr = utr;
	}
	@Override
	public String toString() {
		return "BankingRequest [virtualAccountNo=" + virtualAccountNo + ", utr=" + utr + ", tranAmt=" + tranAmt
				+ ", mode=" + mode + ", getVirtualAccountNo()=" + getVirtualAccountNo() + ", getUtr()=" + getUtr()
				+ ", getTranAmt()=" + getTranAmt() + ", getMode()=" + getMode() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public String getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
	
	
}
