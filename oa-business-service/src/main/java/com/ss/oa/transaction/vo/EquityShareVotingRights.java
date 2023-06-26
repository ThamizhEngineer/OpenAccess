package com.ss.oa.transaction.vo;

public class EquityShareVotingRights {
	
	private String id;
	private String gcId;
	private String classOfEquityShares; 
	private String noOfEquityShares;
	private String valueOfEquityShares;
	private String amountOfEquityShares;
	private String noOfVotingRights; 
	private String percentageHoldingInEquityShares;
	private String percentageHoldingInVotingRights; 
	private String percentageHoldingInVotingWithEquity;
	private String createdBy;
	private String createdDate;
	private String modifiedBy;
	private String modifiedDate;
	private String enabled;
	private String remarks;
	
	public EquityShareVotingRights() {
		super();
	}

	public EquityShareVotingRights(String id, String gcId, String classOfEquityShares, String noOfEquityShares,
			String valueOfEquityShares, String amountOfEquityShares, String noOfVotingRights,
			String percentageHoldingInEquityShares, String percentageHoldingInVotingRights,
			String percentageHoldingInVotingWithEquity, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String enabled, String remarks) {
		super();
		this.id = id;
		this.gcId = gcId;
		this.classOfEquityShares = classOfEquityShares;
		this.noOfEquityShares = noOfEquityShares;
		this.valueOfEquityShares = valueOfEquityShares;
		this.amountOfEquityShares = amountOfEquityShares;
		this.noOfVotingRights = noOfVotingRights;
		this.percentageHoldingInEquityShares = percentageHoldingInEquityShares;
		this.percentageHoldingInVotingRights = percentageHoldingInVotingRights;
		this.percentageHoldingInVotingWithEquity = percentageHoldingInVotingWithEquity;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.enabled = enabled;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "EquityShareVotingRights [id=" + id + ", gcId=" + gcId + ", classOfEquityShares=" + classOfEquityShares
				+ ", noOfEquityShares=" + noOfEquityShares + ", valueOfEquityShares=" + valueOfEquityShares
				+ ", amountOfEquityShares=" + amountOfEquityShares + ", noOfVotingRights=" + noOfVotingRights
				+ ", percentageHoldingInEquityShares=" + percentageHoldingInEquityShares
				+ ", percentageHoldingInVotingRights=" + percentageHoldingInVotingRights
				+ ", percentageHoldingInVotingWithEquity=" + percentageHoldingInVotingWithEquity + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", enabled=" + enabled + ", remarks=" + remarks + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGcId() {
		return gcId;
	}

	public void setGcId(String gcId) {
		this.gcId = gcId;
	}

	public String getClassOfEquityShares() {
		return classOfEquityShares;
	}

	public void setClassOfEquityShares(String classOfEquityShares) {
		this.classOfEquityShares = classOfEquityShares;
	}

	public String getNoOfEquityShares() {
		return noOfEquityShares;
	}

	public void setNoOfEquityShares(String noOfEquityShares) {
		this.noOfEquityShares = noOfEquityShares;
	}

	public String getValueOfEquityShares() {
		return valueOfEquityShares;
	}

	public void setValueOfEquityShares(String valueOfEquityShares) {
		this.valueOfEquityShares = valueOfEquityShares;
	}

	public String getAmountOfEquityShares() {
		return amountOfEquityShares;
	}

	public void setAmountOfEquityShares(String amountOfEquityShares) {
		this.amountOfEquityShares = amountOfEquityShares;
	}

	public String getNoOfVotingRights() {
		return noOfVotingRights;
	}

	public void setNoOfVotingRights(String noOfVotingRights) {
		this.noOfVotingRights = noOfVotingRights;
	}

	public String getPercentageHoldingInEquityShares() {
		return percentageHoldingInEquityShares;
	}

	public void setPercentageHoldingInEquityShares(String percentageHoldingInEquityShares) {
		this.percentageHoldingInEquityShares = percentageHoldingInEquityShares;
	}

	public String getPercentageHoldingInVotingRights() {
		return percentageHoldingInVotingRights;
	}

	public void setPercentageHoldingInVotingRights(String percentageHoldingInVotingRights) {
		this.percentageHoldingInVotingRights = percentageHoldingInVotingRights;
	}

	public String getPercentageHoldingInVotingWithEquity() {
		return percentageHoldingInVotingWithEquity;
	}

	public void setPercentageHoldingInVotingWithEquity(String percentageHoldingInVotingWithEquity) {
		this.percentageHoldingInVotingWithEquity = percentageHoldingInVotingWithEquity;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
