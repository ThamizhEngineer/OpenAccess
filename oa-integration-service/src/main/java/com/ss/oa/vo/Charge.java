package com.ss.oa.vo;

import org.springframework.context.annotation.Scope;

public class Charge {
	private String chargeCode, chargeDesc, chargeAmount;
	
	public Charge() {
		
	}

	public Charge(String chargeCode, String chargeDesc, String chargeAmount) {
		super();
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeAmount = chargeAmount;
	}

	@Override
	public String toString() {
		return "Charge [chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc + ", chargeAmount=" + chargeAmount
				+ "]";
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
	}

	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	

}
