package com.ss.oa.transaction.vo;
public class Loan {
	
	  private String id;
	  private String gcId;
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
	  
		public Loan() {
			super();
		}

		public Loan(String id, String gcId, String loanOrigin, String loanSourceName, String loanSourceAddress,
				String loanAmount, String loanCurrency, String loanExchangeRate, String createdBy, String createdDate,
				String modifiedBy, String modifiedDate, String enabled, String remarks) {
			super();
			this.id = id;
			this.gcId = gcId;
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
			return "Loan [id=" + id + ", gcId=" + gcId + ", loanOrigin=" + loanOrigin + ", loanSourceName="
					+ loanSourceName + ", loanSourceAddress=" + loanSourceAddress + ", loanAmount=" + loanAmount
					+ ", loanCurrency=" + loanCurrency + ", loanExchangeRate=" + loanExchangeRate + ", createdBy="
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

		public String getLoanOrigin() {
			return loanOrigin;
		}

		public void setLoanOrigin(String loanOrigin) {
			this.loanOrigin = loanOrigin;
		}

		public String getLoanSourceName() {
			return loanSourceName;
		}

		public void setLoanSourceName(String loanSourceName) {
			this.loanSourceName = loanSourceName;
		}

		public String getLoanSourceAddress() {
			return loanSourceAddress;
		}

		public void setLoanSourceAddress(String loanSourceAddress) {
			this.loanSourceAddress = loanSourceAddress;
		}

		public String getLoanAmount() {
			return loanAmount;
		}

		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}

		public String getLoanCurrency() {
			return loanCurrency;
		}

		public void setLoanCurrency(String loanCurrency) {
			this.loanCurrency = loanCurrency;
		}

		public String getLoanExchangeRate() {
			return loanExchangeRate;
		}

		public void setLoanExchangeRate(String loanExchangeRate) {
			this.loanExchangeRate = loanExchangeRate;
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
