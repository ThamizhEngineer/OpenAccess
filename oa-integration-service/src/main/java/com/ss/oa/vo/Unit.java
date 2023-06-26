package com.ss.oa.vo;

import org.springframework.context.annotation.Scope;

public class Unit {
	private String injectedUnits, transLossUnits, distLossUnits, totalLossUnits, drawalUnits;
	public Unit() {
		super();
	}
	
	public Unit(String injectedUnits, String transLossUnits, String distLossUnits, String totalLossUnits,
			String drawalUnits) {
		super();
		this.injectedUnits = injectedUnits;
		this.transLossUnits = transLossUnits;
		this.distLossUnits = distLossUnits;
		this.totalLossUnits = totalLossUnits;
		this.drawalUnits = drawalUnits;
	}

	@Override
	public String toString() {
		return "Unit [injectedUnits=" + injectedUnits + ", transLossUnits=" + transLossUnits + ", distLossUnits="
				+ distLossUnits + ", totalLossUnits=" + totalLossUnits + ", drawalUnits=" + drawalUnits + "]";
	}

	public String getInjectedUnits() {
		return injectedUnits;
	}
	public void setInjectedUnits(String injectedUnits) {
		this.injectedUnits = injectedUnits;
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
	public String getDrawalUnits() {
		return drawalUnits;
	}
	public void setDrawalUnits(String drawalUnits) {
		this.drawalUnits = drawalUnits;
	}
	

}
