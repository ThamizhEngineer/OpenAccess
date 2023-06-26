package com.ss.oa.oadocumentservice.vo;

public class GenerationStatementSlot {
	private String id;
	private String remarks;
	private String generationStatementId;
	private String referenceNumber;
	private String slotCode,slotName;
	private String impInitial,impFinal,impDifference,impUnits;
	private String expInitial,expFinal,expDifference,expUnits;
	private String bankedBalance,netUnits;
	
	public GenerationStatementSlot() {
		super();
	}

	public GenerationStatementSlot(String id, String remarks, String generationStatementId, String referenceNumber,
			String slotCode, String slotName, String impInitial, String impFinal, String impDifference, String impUnits,
			String expInitial, String expFinal, String expDifference, String expUnits, String bankedBalance,
			String netUnits) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.generationStatementId = generationStatementId;
		this.referenceNumber = referenceNumber;
		this.slotCode = slotCode;
		this.slotName = slotName;
		this.impInitial = impInitial;
		this.impFinal = impFinal;
		this.impDifference = impDifference;
		this.impUnits = impUnits;
		this.expInitial = expInitial;
		this.expFinal = expFinal;
		this.expDifference = expDifference;
		this.expUnits = expUnits;
		this.bankedBalance = bankedBalance;
		this.netUnits = netUnits;
	}

	@Override
	public String toString() {
		return "GenerationStatementSlot [id=" + id + ", remarks=" + remarks + ", generationStatementId="
				+ generationStatementId + ", referenceNumber=" + referenceNumber + ", slotCode=" + slotCode
				+ ", slotName=" + slotName + ", impInitial=" + impInitial + ", impFinal=" + impFinal
				+ ", impDifference=" + impDifference + ", impUnits=" + impUnits + ", expInitial=" + expInitial
				+ ", expFinal=" + expFinal + ", expDifference=" + expDifference + ", expUnits=" + expUnits
				+ ", bankedBalance=" + bankedBalance + ", netUnits=" + netUnits + "]";
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public String getSlotName() {
		return slotName;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public String getImpInitial() {
		return impInitial;
	}

	public void setImpInitial(String impInitial) {
		this.impInitial = impInitial;
	}

	public String getImpFinal() {
		return impFinal;
	}

	public void setImpFinal(String impFinal) {
		this.impFinal = impFinal;
	}

	public String getImpDifference() {
		return impDifference;
	}

	public void setImpDifference(String impDifference) {
		this.impDifference = impDifference;
	}

	public String getImpUnits() {
		return impUnits;
	}

	public void setImpUnits(String impUnits) {
		this.impUnits = impUnits;
	}

	public String getExpInitial() {
		return expInitial;
	}

	public void setExpInitial(String expInitial) {
		this.expInitial = expInitial;
	}

	public String getExpFinal() {
		return expFinal;
	}

	public void setExpFinal(String expFinal) {
		this.expFinal = expFinal;
	}

	public String getExpDifference() {
		return expDifference;
	}

	public void setExpDifference(String expDifference) {
		this.expDifference = expDifference;
	}

	public String getExpUnits() {
		return expUnits;
	}

	public void setExpUnits(String expUnits) {
		this.expUnits = expUnits;
	}

	public String getBankedBalance() {
		return bankedBalance;
	}

	public void setBankedBalance(String bankedBalance) {
		this.bankedBalance = bankedBalance;
	}

	public String getNetUnits() {
		return netUnits;
	}

	public void setNetUnits(String netUnits) {
		this.netUnits = netUnits;
	}
	
}
