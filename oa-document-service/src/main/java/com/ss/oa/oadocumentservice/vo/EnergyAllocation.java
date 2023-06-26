package com.ss.oa.oadocumentservice.vo;

public class EnergyAllocation {
	
	private Integer id;
	private String companyName;
	private String companyServiceNumber;
	private String edcName;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	private String total;
	private String injectionVoltageCode,injectionVoltageName;
	private String tranCharges;
	private String omCharges;
	private String negativeCharges;
	private String schedulingCharges;
	public EnergyAllocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EnergyAllocation(Integer id, String companyName, String companyServiceNumber, String edcName, String c1,
			String c2, String c3, String c4, String c5, String total, String injectionVoltageCode,
			String injectionVoltageName, String tranCharges, String omCharges, String negativeCharges,
			String schedulingCharges) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.companyServiceNumber = companyServiceNumber;
		this.edcName = edcName;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.total = total;
		this.injectionVoltageCode = injectionVoltageCode;
		this.injectionVoltageName = injectionVoltageName;
		this.tranCharges = tranCharges;
		this.omCharges = omCharges;
		this.negativeCharges = negativeCharges;
		this.schedulingCharges = schedulingCharges;
	}
	@Override
	public String toString() {
		return "EnergyAllocation [id=" + id + ", companyName=" + companyName + ", companyServiceNumber="
				+ companyServiceNumber + ", edcName=" + edcName + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + ", total=" + total + ", injectionVoltageCode=" + injectionVoltageCode
				+ ", injectionVoltageName=" + injectionVoltageName + ", tranCharges=" + tranCharges + ", omCharges="
				+ omCharges + ", negativeCharges=" + negativeCharges + ", schedulingCharges=" + schedulingCharges + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}
	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
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
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}
	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}
	public String getInjectionVoltageName() {
		return injectionVoltageName;
	}
	public void setInjectionVoltageName(String injectionVoltageName) {
		this.injectionVoltageName = injectionVoltageName;
	}
	public String getTranCharges() {
		return tranCharges;
	}
	public void setTranCharges(String tranCharges) {
		this.tranCharges = tranCharges;
	}
	public String getOmCharges() {
		return omCharges;
	}
	public void setOmCharges(String omCharges) {
		this.omCharges = omCharges;
	}
	public String getNegativeCharges() {
		return negativeCharges;
	}
	public void setNegativeCharges(String negativeCharges) {
		this.negativeCharges = negativeCharges;
	}
	public String getSchedulingCharges() {
		return schedulingCharges;
	}
	public void setSchedulingCharges(String schedulingCharges) {
		this.schedulingCharges = schedulingCharges;
	}
	
	

}
