package com.ss.oa.oadocumentservice.vo;

public class EsChargeForPrint {

	 String c001TotalCharge;
	 String c002TotalCharge ;
	 String c003TotalCharge ;
	 String c004TotalCharge ;
	 String c005TotalCharge ;
	 String c006TotalCharge ;
	 String c007TotalCharge ;
	 String c008TotalCharge ;
	 String companyServiceNumber ;
	 String companyName ;
	 
	public EsChargeForPrint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EsChargeForPrint(String c001TotalCharge, String c002TotalCharge, String c003TotalCharge,
			String c004TotalCharge, String c005TotalCharge, String c006TotalCharge, String c007TotalCharge,
			String c008TotalCharge, String companyServiceNumber, String companyName) {
		super();
		this.c001TotalCharge = c001TotalCharge;
		this.c002TotalCharge = c002TotalCharge;
		this.c003TotalCharge = c003TotalCharge;
		this.c004TotalCharge = c004TotalCharge;
		this.c005TotalCharge = c005TotalCharge;
		this.c006TotalCharge = c006TotalCharge;
		this.c007TotalCharge = c007TotalCharge;
		this.c008TotalCharge = c008TotalCharge;
		this.companyServiceNumber = companyServiceNumber;
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "EsChargeForPrint [c001TotalCharge=" + c001TotalCharge + ", c002TotalCharge=" + c002TotalCharge
				+ ", c003TotalCharge=" + c003TotalCharge + ", c004TotalCharge=" + c004TotalCharge + ", c005TotalCharge="
				+ c005TotalCharge + ", c006TotalCharge=" + c006TotalCharge + ", c007TotalCharge=" + c007TotalCharge
				+ ", c008TotalCharge=" + c008TotalCharge + ", companyServiceNumber=" + companyServiceNumber
				+ ", companyName=" + companyName + "]";
	}

	public String getC001TotalCharge() {
		return c001TotalCharge;
	}

	public void setC001TotalCharge(String c001TotalCharge) {
		this.c001TotalCharge = c001TotalCharge;
	}

	public String getC002TotalCharge() {
		return c002TotalCharge;
	}

	public void setC002TotalCharge(String c002TotalCharge) {
		this.c002TotalCharge = c002TotalCharge;
	}

	public String getC003TotalCharge() {
		return c003TotalCharge;
	}

	public void setC003TotalCharge(String c003TotalCharge) {
		this.c003TotalCharge = c003TotalCharge;
	}

	public String getC004TotalCharge() {
		return c004TotalCharge;
	}

	public void setC004TotalCharge(String c004TotalCharge) {
		this.c004TotalCharge = c004TotalCharge;
	}

	public String getC005TotalCharge() {
		return c005TotalCharge;
	}

	public void setC005TotalCharge(String c005TotalCharge) {
		this.c005TotalCharge = c005TotalCharge;
	}

	public String getC006TotalCharge() {
		return c006TotalCharge;
	}

	public void setC006TotalCharge(String c006TotalCharge) {
		this.c006TotalCharge = c006TotalCharge;
	}

	public String getC007TotalCharge() {
		return c007TotalCharge;
	}

	public void setC007TotalCharge(String c007TotalCharge) {
		this.c007TotalCharge = c007TotalCharge;
	}

	public String getC008TotalCharge() {
		return c008TotalCharge;
	}

	public void setC008TotalCharge(String c008TotalCharge) {
		this.c008TotalCharge = c008TotalCharge;
	}

	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}

	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	 
	
	 
}
