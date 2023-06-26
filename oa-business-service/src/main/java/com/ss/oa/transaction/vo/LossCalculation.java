package com.ss.oa.transaction.vo;

public class LossCalculation {

	private String id;
	private String injectionVoltageCode,injectionVoltageDesc;
	private String drawalVoltageCode,drawalVoltageDesc;
	private String transLossPercent,distLossPercent,totalLossPercent;
	private String injectedUnits,drawalUnits;
	private String transLossUnits,distLossUnits,totalLossUnits;
	
	public LossCalculation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LossCalculation(String id, String injectionVoltageCode, String injectionVoltageDesc,
			String drawalVoltageCode, String drawalVoltageDesc, String transLossPercent, String distLossPercent,
			String totalLossPercent, String injectedUnits, String drawalUnits, String transLossUnits,
			String distLossUnits, String totalLossUnits) {
		super();
		this.id = id;
		this.injectionVoltageCode = injectionVoltageCode;
		this.injectionVoltageDesc = injectionVoltageDesc;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageDesc = drawalVoltageDesc;
		this.transLossPercent = transLossPercent;
		this.distLossPercent = distLossPercent;
		this.totalLossPercent = totalLossPercent;
		this.injectedUnits = injectedUnits;
		this.drawalUnits = drawalUnits;
		this.transLossUnits = transLossUnits;
		this.distLossUnits = distLossUnits;
		this.totalLossUnits = totalLossUnits;
	}

	@Override
	public String toString() {
		return "LossCalculation [id=" + id + ", injectionVoltageCode=" + injectionVoltageCode
				+ ", injectionVoltageDesc=" + injectionVoltageDesc + ", drawalVoltageCode=" + drawalVoltageCode
				+ ", drawalVoltageDesc=" + drawalVoltageDesc + ", transLossPercent=" + transLossPercent
				+ ", distLossPercent=" + distLossPercent + ", totalLossPercent=" + totalLossPercent + ", injectedUnits="
				+ injectedUnits + ", drawalUnits=" + drawalUnits + ", transLossUnits=" + transLossUnits
				+ ", distLossUnits=" + distLossUnits + ", totalLossUnits=" + totalLossUnits + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInjectionVoltageCode() {
		return injectionVoltageCode;
	}

	public void setInjectionVoltageCode(String injectionVoltageCode) {
		this.injectionVoltageCode = injectionVoltageCode;
	}

	public String getInjectionVoltageDesc() {
		return injectionVoltageDesc;
	}

	public void setInjectionVoltageDesc(String injectionVoltageDesc) {
		this.injectionVoltageDesc = injectionVoltageDesc;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public String getDrawalVoltageDesc() {
		return drawalVoltageDesc;
	}

	public void setDrawalVoltageDesc(String drawalVoltageDesc) {
		this.drawalVoltageDesc = drawalVoltageDesc;
	}

	public String getTransLossPercent() {
		return transLossPercent;
	}

	public void setTransLossPercent(String transLossPercent) {
		this.transLossPercent = transLossPercent;
	}

	public String getDistLossPercent() {
		return distLossPercent;
	}

	public void setDistLossPercent(String distLossPercent) {
		this.distLossPercent = distLossPercent;
	}

	public String getTotalLossPercent() {
		return totalLossPercent;
	}

	public void setTotalLossPercent(String totalLossPercent) {
		this.totalLossPercent = totalLossPercent;
	}

	public String getInjectedUnits() {
		return injectedUnits;
	}

	public void setInjectedUnits(String injectedUnits) {
		this.injectedUnits = injectedUnits;
	}

	public String getDrawalUnits() {
		return drawalUnits;
	}

	public void setDrawalUnits(String drawalUnits) {
		this.drawalUnits = drawalUnits;
	}

	public String getTransLossUnits() {
		return transLossUnits;
	}

	public void setTransLossUnits(String transLossUnits) {
		this.transLossUnits = transLossUnits;
	}

	public String getDistLossUnits() {
		return distLossUnits;
	}

	public void setDistLossUnits(String distLossUnits) {
		this.distLossUnits = distLossUnits;
	}

	public String getTotalLossUnits() {
		return totalLossUnits;
	}

	public void setTotalLossUnits(String totalLossUnits) {
		this.totalLossUnits = totalLossUnits;
	}

}
