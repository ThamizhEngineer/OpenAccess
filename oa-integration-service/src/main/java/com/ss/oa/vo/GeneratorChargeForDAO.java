package com.ss.oa.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;

public class GeneratorChargeForDAO {
	private String serviceNumber, generatorName, billingMonth, billingYear;
	private String chargeCode, chargeDesc, chargeAmount;
	private List<Charge> charges;
	public GeneratorChargeForDAO() {
		super();
		this.charges = new ArrayList<Charge>();
	}
	public GeneratorChargeForDAO(String serviceNumber, String generatorName, String billingMonth, String billingYear,
			String chargeCode, String chargeDesc, String chargeAmount, List<Charge> charges) {
		super();
		this.serviceNumber = serviceNumber;
		this.generatorName = generatorName;
		this.billingMonth = billingMonth;
		this.billingYear = billingYear;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeAmount = chargeAmount;
		this.charges = charges;
	}
	@Override
	public String toString() {
		return "GeneratorChargeForDAO [serviceNumber=" + serviceNumber + ", generatorName=" + generatorName
				+ ", billingMonth=" + billingMonth + ", billingYear=" + billingYear + ", chargeCode=" + chargeCode
				+ ", chargeDesc=" + chargeDesc + ", chargeAmount=" + chargeAmount + ", charges=" + charges + "]";
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
	public List<Charge> getCharges() {
		return charges;
	}
	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}

	
}