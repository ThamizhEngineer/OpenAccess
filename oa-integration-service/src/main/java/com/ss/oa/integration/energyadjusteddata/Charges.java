package com.ss.oa.integration.energyadjusteddata;

public class Charges {
	public String chargesCode;
	public String charge;
	public Charges() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Charges(String chargesCode, String charge) {
		super();
		this.chargesCode = chargesCode;
		this.charge = charge;
	}
	public String getChargesCode() {
		return chargesCode;
	}
	public void setChargesCode(String chargesCode) {
		this.chargesCode = chargesCode;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	@Override
	public String toString() {
		return "Charges [chargesCode=" + chargesCode + ", charge=" + charge + "]";
	}	
	
}
