package com.ss.oa.oadocumentservice.vo;

public class GenerationStatementCharge {
	private String id;
	private String remarks;
	private String generationStatementId;
	private String chargeCode,chargeDesc,chargeTypeCode,chargeTypeName,etaxRate,iecRate;
	
	private String unitCharge,totalCharges;
	
	public GenerationStatementCharge() {
		super();
	}
     
	
	
	
	@Override
	public String toString() {
		return "GenerationStatementCharge [id=" + id + ", remarks=" + remarks + ", generationStatementId="
				+ generationStatementId + ", chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc
				+ ", chargeTypeCode=" + chargeTypeCode + ", chargeTypeName=" + chargeTypeName + ", etaxRate=" + etaxRate
				+ ", iecRate=" + iecRate + ", unitCharge=" + unitCharge + ", totalCharges=" + totalCharges + "]";
	}




	public GenerationStatementCharge(String id, String remarks, String generationStatementId, String chargeCode,
			String chargeDesc, String chargeTypeCode, String chargeTypeName, String etaxRate, String iecRate,
			String unitCharge, String totalCharges) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.generationStatementId = generationStatementId;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.chargeTypeName = chargeTypeName;
		this.etaxRate = etaxRate;
		this.iecRate = iecRate;
		this.unitCharge = unitCharge;
		this.totalCharges = totalCharges;
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

	public String getGenerationStatementId() {
		return generationStatementId;
	}

	public void setGenerationStatementId(String generationStatementId) {
		this.generationStatementId = generationStatementId;
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

	public String getChargeTypeName() {
		return chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}

	public String getUnitCharge() {
		return unitCharge;
	}

	public void setUnitCharge(String unitCharge) {
		this.unitCharge = unitCharge;
	}

	public String getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(String totalCharges) {
		this.totalCharges = totalCharges;
	}

	public String getIecRate() {
		return iecRate;
	}

	public void setIecRate(String iecRate) {
		this.iecRate = iecRate;
	}

	public String getEtaxRate() {
		return etaxRate;
	}

	public void setEtaxRate(String etaxRate) {
		this.etaxRate = etaxRate;
	}

}
