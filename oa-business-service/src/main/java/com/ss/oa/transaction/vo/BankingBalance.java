package com.ss.oa.transaction.vo;

public class BankingBalance {
	
	String id;
	String bankingCompanyId;
	String bankingServiceId;
	String c1;
	String c2;
	String c3;
	String c4;
	String c5;
	String currC1;
	String currC2;
	String currC3;
	String currC4;
	String currC5;
	String calculated;
	String remarks;
	String createdBy;
	String createdDate;
	String modifiedBy;
	String modifiedDate;
	String bankingServiceNumber;
	String sellerOrgId;
	String sellerSubstationId;
	String sellerCompanyServiceId;
	String sellerCompanyServiceNumber;
	String sellerOrgName;
	String sellerSubstationName;
	String companyName;
	String month,year;
	
	public BankingBalance() {
		
		super();
	}

	public BankingBalance(String id, String bankingCompanyId, String bankingServiceId, String c1, String c2, String c3,
			String c4, String c5, String currC1, String currC2, String currC3, String currC4, String currC5,
			String calculated, String remarks, String createdBy, String createdDate, String modifiedBy,
			String modifiedDate, String bankingServiceNumber, String sellerOrgId, String sellerSubstationId,
			String sellerCompanyServiceId, String sellerCompanyServiceNumber, String sellerOrgName,
			String sellerSubstationName, String companyName, String month, String year) {
		super();
		this.id = id;
		this.bankingCompanyId = bankingCompanyId;
		this.bankingServiceId = bankingServiceId;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.currC1 = currC1;
		this.currC2 = currC2;
		this.currC3 = currC3;
		this.currC4 = currC4;
		this.currC5 = currC5;
		this.calculated = calculated;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.bankingServiceNumber = bankingServiceNumber;
		this.sellerOrgId = sellerOrgId;
		this.sellerSubstationId = sellerSubstationId;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerOrgName = sellerOrgName;
		this.sellerSubstationName = sellerSubstationName;
		this.companyName = companyName;
		this.month = month;
		this.year = year;
	}

	@Override
	public String toString() {
		return "BankingBalance [id=" + id + ", bankingCompanyId=" + bankingCompanyId + ", bankingServiceId="
				+ bankingServiceId + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5
				+ ", currC1=" + currC1 + ", currC2=" + currC2 + ", currC3=" + currC3 + ", currC4=" + currC4
				+ ", currC5=" + currC5 + ", calculated=" + calculated + ", remarks=" + remarks + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", bankingServiceNumber=" + bankingServiceNumber + ", sellerOrgId=" + sellerOrgId
				+ ", sellerSubstationId=" + sellerSubstationId + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerOrgName=" + sellerOrgName
				+ ", sellerSubstationName=" + sellerSubstationName + ", companyName=" + companyName + ", month=" + month
				+ ", year=" + year + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBankingCompanyId() {
		return bankingCompanyId;
	}

	public void setBankingCompanyId(String bankingCompanyId) {
		this.bankingCompanyId = bankingCompanyId;
	}

	public String getBankingServiceId() {
		return bankingServiceId;
	}

	public void setBankingServiceId(String bankingServiceId) {
		this.bankingServiceId = bankingServiceId;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public String getC3() {
		return c3;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public String getC4() {
		return c4;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public String getC5() {
		return c5;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public String getCurrC1() {
		return currC1;
	}

	public void setCurrC1(String currC1) {
		this.currC1 = currC1;
	}

	public String getCurrC2() {
		return currC2;
	}

	public void setCurrC2(String currC2) {
		this.currC2 = currC2;
	}

	public String getCurrC3() {
		return currC3;
	}

	public void setCurrC3(String currC3) {
		this.currC3 = currC3;
	}

	public String getCurrC4() {
		return currC4;
	}

	public void setCurrC4(String currC4) {
		this.currC4 = currC4;
	}

	public String getCurrC5() {
		return currC5;
	}

	public void setCurrC5(String currC5) {
		this.currC5 = currC5;
	}

	public String getCalculated() {
		return calculated;
	}

	public void setCalculated(String calculated) {
		this.calculated = calculated;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getBankingServiceNumber() {
		return bankingServiceNumber;
	}

	public void setBankingServiceNumber(String bankingServiceNumber) {
		this.bankingServiceNumber = bankingServiceNumber;
	}

	public String getSellerOrgId() {
		return sellerOrgId;
	}

	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}

	public String getSellerSubstationId() {
		return sellerSubstationId;
	}

	public void setSellerSubstationId(String sellerSubstationId) {
		this.sellerSubstationId = sellerSubstationId;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}

	public String getSellerOrgName() {
		return sellerOrgName;
	}

	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}

	public String getSellerSubstationName() {
		return sellerSubstationName;
	}

	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	
}
