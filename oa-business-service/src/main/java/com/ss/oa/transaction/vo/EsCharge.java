package com.ss.oa.transaction.vo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "T_ES_CHARGE")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class EsCharge {
	
	  @Id
	  @Column(name="ID") 
	  private String id;
	  @Column(name="T_ENERGY_SALE_ID")
	  private String energySaleId;
//	  @Transient
	  @Formula("(SELECT ms.m_company_id FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String companyId;
	  @Formula("(SELECT ms.m_company_CODE FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String companyCode;
	  @Formula("(SELECT ms.m_company_name FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String companyName;
	  @Column(name="M_COMP_SERV_ID")
	  private String companyServiceId;
//	  @Transient
	  @Formula("(SELECT ms.M_COMP_SERV_NUMBER FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String companyServiceNumber;
//	  @Transient
	  @Formula("(SELECT ms.M_ORG_ID FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String orgId;
	  @Formula("(SELECT ms.M_ORG_CODE FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String orgCode;
	  @Formula("(SELECT ms.M_ORG_NAME FROM T_ES_CHARGE ch JOIN V_COMPANY_SERVICE ms ON ms.id=ch.M_COMP_SERV_ID WHERE ch.ID=id)")
	  private String orgName;
	  @Transient
	  private String substationId,substationCode,substationName;
	  @Transient
	  private String feederId,feederCode,feederName;
	  @Column(name="CHARGE_CODE")
	  private String chargeCode;
//	  @Transient
	  @Formula("(SELECT cd.CHARGE_DESC FROM T_ES_CHARGE ch JOIN T_CHARGE_DEFN cd ON ch.CHARGE_CODE=cd.CHARGE_CODE WHERE ch.ID=id)")
      private String chargeName;
	  @Column(name="TOTAL_CHARGE")
	  private String totalCharge;
	  @Column(name="CREATED_BY")
	  private String createdBy;
	 // @CreationTimestamp
	  @Column(name = "CREATED_DATE", columnDefinition = "DATE")
	  @JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate createdDate;
	  @Column(name="ENABLED")
	  private Character enabled;
	  @Transient
	  private String resultCode;
	  @Transient
	  private String resultDesc;
	
	  public EsCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EsCharge [id=" + id + ", energySaleId=" + energySaleId + ", companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", companyServiceId=" + companyServiceId
				+ ", companyServiceNumber=" + companyServiceNumber + ", orgId=" + orgId + ", orgCode=" + orgCode
				+ ", orgName=" + orgName + ", substationId=" + substationId + ", substationCode=" + substationCode
				+ ", substationName=" + substationName + ", feederId=" + feederId + ", feederCode=" + feederCode
				+ ", feederName=" + feederName + ", chargeCode=" + chargeCode + ", chargeName=" + chargeName
				+ ", totalCharge=" + totalCharge + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", enabled=" + enabled + ", resultCode=" + resultCode + ", resultDesc=" + resultDesc + "]";
	}

	public EsCharge(String id, String energySaleId, String companyId, String companyCode, String companyName,
			String companyServiceId, String companyServiceNumber, String orgId, String orgCode, String orgName,
			String substationId, String substationCode, String substationName, String feederId, String feederCode,
			String feederName, String chargeCode, String chargeName, String totalCharge, String createdBy,
			LocalDate createdDate, Character enabled, String resultCode, String resultDesc) {
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
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergySaleId() {
		return energySaleId;
	}

	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyServiceId() {
		return companyServiceId;
	}

	public void setCompanyServiceId(String companyServiceId) {
		this.companyServiceId = companyServiceId;
	}

	public String getCompanyServiceNumber() {
		return companyServiceNumber;
	}

	public void setCompanyServiceNumber(String companyServiceNumber) {
		this.companyServiceNumber = companyServiceNumber;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSubstationId() {
		return substationId;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public String getSubstationCode() {
		return substationCode;
	}

	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public String getFeederId() {
		return feederId;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public String getFeederCode() {
		return feederCode;
	}

	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}

	public String getFeederName() {
		return feederName;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getTotalCharge() {
		return totalCharge;
	}

	public void setTotalCharge(String totalCharge) {
		this.totalCharge = totalCharge;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Character getEnabled() {
		return enabled;
	}

	public void setEnabled(Character enabled) {
		this.enabled = enabled;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	 	 
}
