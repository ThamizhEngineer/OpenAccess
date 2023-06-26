package com.ss.oa.oadocumentservice.vo;

public class EnergyCharge {

	private String id;
	private String energySaleOrderId;
	private String remarks;
	private String chargeCode,chargeDesc;
	private String chargeTypeCode;
	private String unitCharge,totalCharges;
	private String sellerCompanyServiceId,companyId;
	private String sellerEndOrgId;
	
	private String year;
	private String month;
	private String companyName,companyCode,companyServiceNumber;
	private String sellerCompanyServiceNumber;
	private String orgName,orgCode;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	public EnergyCharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EnergyCharge(String id, String energySaleOrderId, String remarks, String chargeCode, String chargeDesc,
			String chargeTypeCode, String unitCharge, String totalCharges, String sellerCompanyServiceId,
			String companyId, String sellerEndOrgId, String year, String month, String companyName, String companyCode,
			String companyServiceNumber, String sellerCompanyServiceNumber, String orgName, String orgCode,
			String buyerCompanyServiceId, String buyerCompanyServiceNumber) {
		super();
		this.id = id;
		this.energySaleOrderId = energySaleOrderId;
		this.remarks = remarks;
		this.chargeCode = chargeCode;
		this.chargeDesc = chargeDesc;
		this.chargeTypeCode = chargeTypeCode;
		this.unitCharge = unitCharge;
		this.totalCharges = totalCharges;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.companyId = companyId;
		this.sellerEndOrgId = sellerEndOrgId;
		this.year = year;
		this.month = month;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.companyServiceNumber = companyServiceNumber;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}
	@Override
	public String toString() {
		return "EnergyCharge [id=" + id + ", energySaleOrderId=" + energySaleOrderId + ", remarks=" + remarks
				+ ", chargeCode=" + chargeCode + ", chargeDesc=" + chargeDesc + ", chargeTypeCode=" + chargeTypeCode
				+ ", unitCharge=" + unitCharge + ", totalCharges=" + totalCharges + ", sellerCompanyServiceId="
				+ sellerCompanyServiceId + ", companyId=" + companyId + ", sellerEndOrgId=" + sellerEndOrgId + ", year="
				+ year + ", month=" + month + ", companyName=" + companyName + ", companyCode=" + companyCode
				+ ", companyServiceNumber=" + companyServiceNumber + ", sellerCompanyServiceNumber="
				+ sellerCompanyServiceNumber + ", orgName=" + orgName + ", orgCode=" + orgCode
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyServiceNumber="
				+ buyerCompanyServiceNumber + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnergySaleOrderId() {
		return energySaleOrderId;
	}
	public void setEnergySaleOrderId(String energySaleOrderId) {
		this.energySaleOrderId = energySaleOrderId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}
	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSellerEndOrgId() {
		return sellerEndOrgId;
	}
	public void setSellerEndOrgId(String sellerEndOrgId) {
		this.sellerEndOrgId = sellerEndOrgId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}
	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}
	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}
	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}
	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}
	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}
	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}
	 
}
