package com.ss.oa.transaction.vo;

public class EpaLine {
	private String id;
	private String tEpaId;
	private String proposedTotalUnits;
	private String approvedTotalUnits;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String mGeneratorId;
	private String noOfUnits;
	private String genCapacity;
	private String generatorName;
	private String powerplantId;
	private String commsionDate;
	private String districtCode;
	private String districtName;
	private String remarks;
	
	public EpaLine() {
		super();
	}

	public EpaLine(String id, String tEpaId, String proposedTotalUnits, String approvedTotalUnits, String createdBy,
			String createdDate, String modifiedBy, String modifiedDate, String mGeneratorId, String noOfUnits,
			String genCapacity, String generatorName, String powerplantId, String commsionDate, String districtCode,
			String districtName, String remarks) {
		super();
		this.id = id;
		this.tEpaId = tEpaId;
		this.proposedTotalUnits = proposedTotalUnits;
		this.approvedTotalUnits = approvedTotalUnits;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.mGeneratorId = mGeneratorId;
		this.noOfUnits = noOfUnits;
		this.genCapacity = genCapacity;
		this.generatorName = generatorName;
		this.powerplantId = powerplantId;
		this.commsionDate = commsionDate;
		this.districtCode = districtCode;
		this.districtName = districtName;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "EpaLine [id=" + id + ", tEpaId=" + tEpaId + ", proposedTotalUnits=" + proposedTotalUnits
				+ ", approvedTotalUnits=" + approvedTotalUnits + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", mGeneratorId="
				+ mGeneratorId + ", noOfUnits=" + noOfUnits + ", genCapacity=" + genCapacity + ", generatorName="
				+ generatorName + ", powerplantId=" + powerplantId + ", commsionDate=" + commsionDate
				+ ", districtCode=" + districtCode + ", districtName=" + districtName + ", remarks=" + remarks + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettEpaId() {
		return tEpaId;
	}

	public void settEpaId(String tEpaId) {
		this.tEpaId = tEpaId;
	}

	public String getProposedTotalUnits() {
		return proposedTotalUnits;
	}

	public void setProposedTotalUnits(String proposedTotalUnits) {
		this.proposedTotalUnits = proposedTotalUnits;
	}

	public String getApprovedTotalUnits() {
		return approvedTotalUnits;
	}

	public void setApprovedTotalUnits(String approvedTotalUnits) {
		this.approvedTotalUnits = approvedTotalUnits;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getmGeneratorId() {
		return mGeneratorId;
	}

	public void setmGeneratorId(String mGeneratorId) {
		this.mGeneratorId = mGeneratorId;
	}

	public String getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public String getGenCapacity() {
		return genCapacity;
	}

	public void setGenCapacity(String genCapacity) {
		this.genCapacity = genCapacity;
	}

	public String getGeneratorName() {
		return generatorName;
	}

	public void setGeneratorName(String generatorName) {
		this.generatorName = generatorName;
	}

	public String getPowerplantId() {
		return powerplantId;
	}

	public void setPowerplantId(String powerplantId) {
		this.powerplantId = powerplantId;
	}

	public String getCommsionDate() {
		return commsionDate;
	}

	public void setCommsionDate(String commsionDate) {
		this.commsionDate = commsionDate;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
