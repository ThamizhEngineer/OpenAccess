package com.ss.oa.transaction.vo;

public class CsCaptiveUserContribution {
	private String id;
	private String csId;
	private String classOfShareHolder;
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
	
	public CsCaptiveUserContribution() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CsCaptiveUserContribution(String id, String csId, String classOfShareHolder, String noOfEquityShares,
			String valueOfEquityShares, String amountOfEquityShares, String noOfVotingRights,
			String percentageHoldingInEquityShares, String percentageHoldingInVotingRights,
			String percentageHoldingInVotingWithEquity, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String enabled, String remarks) {
		super();
		this.id = id;
		this.csId = csId;
		this.classOfShareHolder = classOfShareHolder;
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
		return "CsCaptiveUserContribution [id=" + id + ", csId=" + csId + ", classOfShareHolder=" + classOfShareHolder
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


	public String getCsId() {
		return csId;
	}


	public String getClassOfShareHolder() {
		return classOfShareHolder;
	}


	public String getNoOfEquityShares() {
		return noOfEquityShares;
	}


	public String getValueOfEquityShares() {
		return valueOfEquityShares;
	}


	public String getAmountOfEquityShares() {
		return amountOfEquityShares;
	}


	public String getNoOfVotingRights() {
		return noOfVotingRights;
	}


	public String getPercentageHoldingInEquityShares() {
		return percentageHoldingInEquityShares;
	}


	public String getPercentageHoldingInVotingRights() {
		return percentageHoldingInVotingRights;
	}


	public String getPercentageHoldingInVotingWithEquity() {
		return percentageHoldingInVotingWithEquity;
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


	public String getEnabled() {
		return enabled;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setCsId(String csId) {
		this.csId = csId;
	}


	public void setClassOfShareHolder(String classOfShareHolder) {
		this.classOfShareHolder = classOfShareHolder;
	}


	public void setNoOfEquityShares(String noOfEquityShares) {
		this.noOfEquityShares = noOfEquityShares;
	}


	public void setValueOfEquityShares(String valueOfEquityShares) {
		this.valueOfEquityShares = valueOfEquityShares;
	}


	public void setAmountOfEquityShares(String amountOfEquityShares) {
		this.amountOfEquityShares = amountOfEquityShares;
	}


	public void setNoOfVotingRights(String noOfVotingRights) {
		this.noOfVotingRights = noOfVotingRights;
	}


	public void setPercentageHoldingInEquityShares(String percentageHoldingInEquityShares) {
		this.percentageHoldingInEquityShares = percentageHoldingInEquityShares;
	}


	public void setPercentageHoldingInVotingRights(String percentageHoldingInVotingRights) {
		this.percentageHoldingInVotingRights = percentageHoldingInVotingRights;
	}


	public void setPercentageHoldingInVotingWithEquity(String percentageHoldingInVotingWithEquity) {
		this.percentageHoldingInVotingWithEquity = percentageHoldingInVotingWithEquity;
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


	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
