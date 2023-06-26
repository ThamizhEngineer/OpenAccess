package com.ss.oa.transaction.vo;

public class EnergySaleDescription {
	private String id;
	private String remarks;
	private String chargeCode,chargeDesc,chargeTypeCode,unitCharge;
	private String formula,createdDate,enabled;
	
	public EnergySaleDescription() {
		super();
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getChargeTypeCode() {
		return chargeTypeCode;
	}
	public void setChargeTypeCode(String chargeTypeCode) {
		this.chargeTypeCode = chargeTypeCode;
	}
	public String getUnitCharge() {
		return unitCharge;
	}
	public void setUnitCharge(String unitCharge) {
		this.unitCharge = unitCharge;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "EnergySaleDescription [id=" + id + ", remarks=" + remarks + ", chargeCode=" + chargeCode
				+ ", chargeDesc=" + chargeDesc + ", chargeTypeCode=" + chargeTypeCode + ", unitCharge=" + unitCharge
				+ ", formula=" + formula + ", createdDate=" + createdDate + ", enabled=" + enabled + "]";
	}
	public EnergySaleDescription(String id, String remarks, String chargeCode, String chargeDesc, String chargeTypeCode,
			String unitCharge, String formula, String createdDate, String enabled) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.unitCharge = unitCharge;
		this.formula = formula;
		this.createdDate = createdDate;
		this.enabled = enabled;
	}

}
