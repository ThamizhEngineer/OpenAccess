package com.ss.oa.transaction.vo;

public class CsLoan {
	  private String id;
	  private String csId;
	  private String loanOrigin;
	  private String loanSourceName;
	  private String loanSourceAddress;
	  private String loanAmount;
	  private String loanCurrency;
	  private String loanExchangeRate;
	  private String createdBy;
	  private String createdDate;
	  private String modifiedBy;
	  private String modifiedDate;
	  private String enabled;
	  private String remarks;
	  
	public CsLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CsLoan(String id, String csId, String loanOrigin, String loanSourceName, String loanSourceAddress,
			String loanAmount, String loanCurrency, String loanExchangeRate, String createdBy, String createdDate,
			String modifiedBy, String modifiedDate, String enabled, String remarks) {
		super();
		this.id = id;
		this.csId = csId;
		this.loanOrigin = loanOrigin;
		this.loanSourceName = loanSourceName;
		this.loanSourceAddress = loanSourceAddress;
		this.loanAmount = loanAmount;
		this.loanCurrency = loanCurrency;
		this.loanExchangeRate = loanExchangeRate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.enabled = enabled;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "CsLoan [id=" + id + ", csId=" + csId + ", loanOrigin=" + loanOrigin + ", loanSourceName="
				+ loanSourceName + ", loanSourceAddress=" + loanSourceAddress + ", loanAmount=" + loanAmount
				+ ", loanCurrency=" + loanCurrency + ", loanExchangeRate=" + loanExchangeRate + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", enabled=" + enabled + ", remarks=" + remarks + "]";
	}

	public String getId() {
		return id;
	}

	public String getCsId() {
		return csId;
	}

	public String getLoanOrigin() {
		return loanOrigin;
	}

	public String getLoanSourceName() {
		return loanSourceName;
	}

	public String getLoanSourceAddress() {
		return loanSourceAddress;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public String getLoanCurrency() {
		return loanCurrency;
	}

	public String getLoanExchangeRate() {
		return loanExchangeRate;
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

	public void setLoanOrigin(String loanOrigin) {
		this.loanOrigin = loanOrigin;
	}

	public void setLoanSourceName(String loanSourceName) {
		this.loanSourceName = loanSourceName;
	}

	public void setLoanSourceAddress(String loanSourceAddress) {
		this.loanSourceAddress = loanSourceAddress;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public void setLoanCurrency(String loanCurrency) {
		this.loanCurrency = loanCurrency;
	}

	public void setLoanExchangeRate(String loanExchangeRate) {
		this.loanExchangeRate = loanExchangeRate;
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
