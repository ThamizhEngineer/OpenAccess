package com.ss.oa.transaction.vo;

public class MeterReadingSlot {
	
	private String id;
	private String meterReadingHeaderId;
	private String remarks;
	private String referenceNumber;
	private String slotCode,slotName;
	private String impInitial,impFinal,impDifference,impUnits;
	private String expInitial,expFinal,expDifference,expUnits;
	private String createdBy,createdDate;
	private String modifiedBy,modifiedDate;	
	
	
	public MeterReadingSlot() {
		super();
	}


	public MeterReadingSlot(String id, String meterReadingHeaderId, String remarks, String referenceNumber,
			String slotCode, String slotName, String impInitial, String impFinal, String impDifference, String impUnits,
			String expInitial, String expFinal, String expDifference, String expUnits, String createdBy,
			String createdDate, String modifiedBy, String modifiedDate) {
		super();
		this.id = id;
		this.meterReadingHeaderId = meterReadingHeaderId;
		this.remarks = remarks;
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
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}


	@Override
	public String toString() {
		return "MeterReadingSlot [id=" + id + ", meterReadingHeaderId=" + meterReadingHeaderId + ", remarks=" + remarks
				+ ", referenceNumber=" + referenceNumber + ", slotCode=" + slotCode + ", slotName=" + slotName
				+ ", impInitial=" + impInitial + ", impFinal=" + impFinal + ", impDifference=" + impDifference
				+ ", impUnits=" + impUnits + ", expInitial=" + expInitial + ", expFinal=" + expFinal
				+ ", expDifference=" + expDifference + ", expUnits=" + expUnits + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ "]";
	}


	public String getId() {
		return id;
	}


	public String getMeterReadingHeaderId() {
		return meterReadingHeaderId;
	}


	public String getRemarks() {
		return remarks;
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


	public String getCreatedBy() {
		return createdBy;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public String getModifiedDate() {
		return modifiedDate;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setMeterReadingHeaderId(String meterReadingHeaderId) {
		this.meterReadingHeaderId = meterReadingHeaderId;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


}
