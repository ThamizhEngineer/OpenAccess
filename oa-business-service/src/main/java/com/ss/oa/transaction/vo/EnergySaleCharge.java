package com.ss.oa.transaction.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class EnergySaleCharge {
	private String id;
	private String energySaleId;
	private String companyId,companyCode,companyName;
	private String companyServiceId,companyServiceNumber;
	private String orgId,orgCode,orgName;
	private String substationId,substationCode,substationName;
	private String feederId,feederCode,feederName;
	private String chargeCode,chargeName,totalCharge;
	private String createdBy,createdDate,enabled;
	public EnergySaleCharge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EnergySaleCharge(String id, String energySaleId, String companyId, String companyCode, String companyName,
			String companyServiceId, String companyServiceNumber, String orgId, String orgCode, String orgName,
			String substationId, String substationCode, String substationName, String feederId, String feederCode,
			String feederName, String chargeCode, String chargeName, String totalCharge, String createdBy,
			String createdDate, String enabled) {
		super();
		this.id = id;
		this.energySaleId = energySaleId;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyServiceId = companyServiceId;
		this.companyServiceNumber = companyServiceNumber;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.substationName = substationName;
		this.feederId = feederId;
		this.feederCode = feederCode;
		this.feederName = feederName;
		this.chargeCode = chargeCode;
		this.chargeName = chargeName;
		this.totalCharge = totalCharge;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "EnergySaleCharge [id=" + id + ", energySaleId=" + energySaleId + ", companyId=" + companyId
				+ ", companyCode=" + companyCode + ", companyName=" + companyName + ", companyServiceId="
				+ companyServiceId + ", companyServiceNumber=" + companyServiceNumber + ", orgId=" + orgId
				+ ", orgCode=" + orgCode + ", orgName=" + orgName + ", substationId=" + substationId
				+ ", substationCode=" + substationCode + ", substationName=" + substationName + ", feederId=" + feederId
				+ ", feederCode=" + feederCode + ", feederName=" + feederName + ", chargeCode=" + chargeCode
				+ ", chargeName=" + chargeName + ", totalCharge=" + totalCharge + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", enabled=" + enabled + "]";
	}
	public String getId() {
		return id;
	}
	public String getEnergySaleId() {
		return energySaleId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyServiceId() {
		return companyServiceId;
	}
	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}
	public String getOrgId() {
		return orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public String getSubstationId() {
		return substationId;
	}
	public String getSubstationCode() {
		return substationCode;
	}
	public String getSubstationName() {
		return substationName;
	}
	public String getFeederId() {
		return feederId;
	}
	public String getFeederCode() {
		return feederCode;
	}
	public String getFeederName() {
		return feederName;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public String getChargeName() {
		return chargeName;
	}
	public String getTotalCharge() {
		return totalCharge;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}
	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}
	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}
	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}
	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}
	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}
	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	
	

}
