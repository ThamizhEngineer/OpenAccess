package com.ss.oa.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

public class GeneratorCharge {
	private String serviceNumber, generatorName, billingMonth, billingYear;
	private List<Charge> charges;
	public GeneratorCharge() {
		super();
		this.charges = new ArrayList<Charge>();
	}
	public GeneratorCharge(String serviceNumber, String generatorName, String billingMonth, String billingYear,
			List<Charge> charges) {
		super();
		this.serviceNumber = serviceNumber;
		this.generatorName = generatorName;
		this.billingMonth = billingMonth;
		this.billingYear = billingYear;
		this.charges = charges;
	}
	@Override
	public String toString() {
		return "GeneratorCharge [serviceNumber=" + serviceNumber + ", generatorName=" + generatorName
				+ ", billingMonth=" + billingMonth + ", billingYear=" + billingYear + ", charges=" + charges + "]";
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getGeneratorName() {
		return generatorName;
	}
	public void setGeneratorName(String generatorName) {
		this.generatorName = generatorName;
	}
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public String getBillingYear() {
		return billingYear;
	}
	public void setBillingYear(String billingYear) {
		this.billingYear = billingYear;
	}
	public List<Charge> getCharges() {
		return charges;
	}
	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
	
}