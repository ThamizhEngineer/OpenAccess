package com.ss.oa.transaction.vo;

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

	public String getRemarks() {
		return remarks;
	}

	public String getGenerationStatementId() {
		return generationStatementId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public String getSlotCode() {
		return slotCode;
	}

	public String getSlotName() {
		return slotName;
	}

	public String getImpInitial() {
		return impInitial;
	}

	public String getImpFinal() {
		return impFinal;
	}

	public String getImpDifference() {
		return impDifference;
	}

	public String getImpUnits() {
		return impUnits;
	}

	public String getExpInitial() {
		return expInitial;
	}

	public String getExpFinal() {
		return expFinal;
	}

	public String getExpDifference() {
		return expDifference;
	}

	public String getExpUnits() {
		return expUnits;
	}

	public String getBankedBalance() {
		return bankedBalance;
	}

	public String getNetUnits() {
		return netUnits;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setGenerationStatementId(String generationStatementId) {
		this.generationStatementId = generationStatementId;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public void setSlotCode(String slotCode) {
		this.slotCode = slotCode;
	}

	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public void setImpInitial(String impInitial) {
		this.impInitial = impInitial;
	}

	public void setImpFinal(String impFinal) {
		this.impFinal = impFinal;
	}

	public void setImpDifference(String impDifference) {
		this.impDifference = impDifference;
	}

	public void setImpUnits(String impUnits) {
		this.impUnits = impUnits;
	}

	public void setExpInitial(String expInitial) {
		this.expInitial = expInitial;
	}

	public void setExpFinal(String expFinal) {
		this.expFinal = expFinal;
	}

	public void setExpDifference(String expDifference) {
		this.expDifference = expDifference;
	}

	public void setExpUnits(String expUnits) {
		this.expUnits = expUnits;
	}

	public void setBankedBalance(String bankedBalance) {
		this.bankedBalance = bankedBalance;
	}

	public void setNetUnits(String netUnits) {
		this.netUnits = netUnits;
	}
		
}
