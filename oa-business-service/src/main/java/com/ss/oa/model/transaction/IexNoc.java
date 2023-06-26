package com.ss.oa.model.transaction;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="T_IEX_NOC")
@CreationTimestamp @UpdateTimestamp
@Entity
public class IexNoc {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String typeCode;
	@Transient
	private String typeName;
	private String statusCode;
	@Column(name = "BUYER_COMP_SERV_ID") 
	private String buyerCompanyServiceId;
	@Formula("(SELECT buyerservice.M_COMPANY_ID FROM V_COMPANY_SERVICE buyerservice WHERE buyerservice.ID=BUYER_COMP_SERV_ID)")
	private String buyerCompanyId;
	@Formula("(SELECT buyerservice.M_COMPANY_NAME FROM V_COMPANY_SERVICE buyerservice WHERE buyerservice.ID=BUYER_COMP_SERV_ID)")
	private String buyerCompanyName;
	@Formula("(SELECT buyerservice.M_ORG_ID FROM V_COMPANY_SERVICE buyerservice WHERE buyerservice.ID=BUYER_COMP_SERV_ID)")
	private String buyerOrgId;
	@Formula("(SELECT buyerservice.M_ORG_NAME FROM V_COMPANY_SERVICE buyerservice WHERE buyerservice.ID=BUYER_COMP_SERV_ID)")
	private String buyerOrgName;
	@Column(name = "T_ES_INTENT_ID") 
	private String energySaleIntentId;
	private String proposedCapacity;
	@Column(columnDefinition = "char") 
	private String exemptRc;
	@Column(columnDefinition = "char") 
	private String hasDues;
	private String dueDetails;
	private String pendingCaseDetails;
	private String technicalFeasibilityDetails;
	private String approvedCapacity;
	@Column(columnDefinition = "char") 
	private String isCaptive;
	@Column(name = "AGMT_PERIOD_CODE") 
	private String agreementPeriodCode;
	@Column(name = "AGREEMENT_DT") 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate agreementDate;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	@Column(columnDefinition = "char") 
	private String enabled;
	private String code;
	private String flowTypeCode;
	private String importRemarks;
	@Column(name = "APPLIED_DT") 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate appliedDate;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	
	public IexNoc() {
		super();
	}

	public IexNoc(String id, String typeCode, String typeName, String statusCode, String buyerCompanyServiceId,
			String buyerCompanyId, String buyerCompanyName, String buyerOrgId, String buyerOrgName,
			String energySaleIntentId, String proposedCapacity, String exemptRc, String hasDues, String dueDetails,
			String pendingCaseDetails, String technicalFeasibilityDetails, String approvedCapacity, String isCaptive,
			String agreementPeriodCode, LocalDate agreementDate, LocalDateTime createdDate, String enabled, String code,
			String flowTypeCode, String importRemarks, LocalDate appliedDate, LocalDateTime modifiedDate) {
		super();
		this.id = id;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.statusCode = statusCode;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.energySaleIntentId = energySaleIntentId;
		this.proposedCapacity = proposedCapacity;
		this.exemptRc = exemptRc;
		this.hasDues = hasDues;
		this.dueDetails = dueDetails;
		this.pendingCaseDetails = pendingCaseDetails;
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
		this.approvedCapacity = approvedCapacity;
		this.isCaptive = isCaptive;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementDate = agreementDate;
		this.createdDate = createdDate;
		this.enabled = enabled;
		this.code = code;
		this.flowTypeCode = flowTypeCode;
		this.importRemarks = importRemarks;
		this.appliedDate = appliedDate;
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "IexNoc [id=" + id + ", typeCode=" + typeCode + ", typeName=" + typeName + ", statusCode=" + statusCode
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyId=" + buyerCompanyId
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerOrgId=" + buyerOrgId + ", buyerOrgName="
				+ buyerOrgName + ", energySaleIntentId=" + energySaleIntentId + ", proposedCapacity=" + proposedCapacity
				+ ", exemptRc=" + exemptRc + ", hasDues=" + hasDues + ", dueDetails=" + dueDetails
				+ ", pendingCaseDetails=" + pendingCaseDetails + ", technicalFeasibilityDetails="
				+ technicalFeasibilityDetails + ", approvedCapacity=" + approvedCapacity + ", isCaptive=" + isCaptive
				+ ", agreementPeriodCode=" + agreementPeriodCode + ", agreementDate=" + agreementDate + ", createdDate="
				+ createdDate + ", enabled=" + enabled + ", code=" + code + ", flowTypeCode=" + flowTypeCode
				+ ", importRemarks=" + importRemarks + ", appliedDate=" + appliedDate + ", modifiedDate=" + modifiedDate
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public String getEnergySaleIntentId() {
		return energySaleIntentId;
	}

	public void setEnergySaleIntentId(String energySaleIntentId) {
		this.energySaleIntentId = energySaleIntentId;
	}

	public String getProposedCapacity() {
		return proposedCapacity;
	}

	public void setProposedCapacity(String proposedCapacity) {
		this.proposedCapacity = proposedCapacity;
	}

	public String getExemptRc() {
		return exemptRc;
	}

	public void setExemptRc(String exemptRc) {
		this.exemptRc = exemptRc;
	}

	public String getHasDues() {
		return hasDues;
	}

	public void setHasDues(String hasDues) {
		this.hasDues = hasDues;
	}

	public String getDueDetails() {
		return dueDetails;
	}

	public void setDueDetails(String dueDetails) {
		this.dueDetails = dueDetails;
	}

	public String getPendingCaseDetails() {
		return pendingCaseDetails;
	}

	public void setPendingCaseDetails(String pendingCaseDetails) {
		this.pendingCaseDetails = pendingCaseDetails;
	}

	public String getTechnicalFeasibilityDetails() {
		return technicalFeasibilityDetails;
	}

	public void setTechnicalFeasibilityDetails(String technicalFeasibilityDetails) {
		this.technicalFeasibilityDetails = technicalFeasibilityDetails;
	}

	public String getApprovedCapacity() {
		return approvedCapacity;
	}

	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}

	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}

	public LocalDate getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(LocalDate agreementDate) {
		this.agreementDate = agreementDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



//	private String statusName;
//	private String buyerCompanyServiceNumber;
//	private String approvedCapacity;
//	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
//	private String buyerOrgId,buyerOrgName,buyerOrgCode;
//	private String buyerCompanyServiceTypeCode,buyerCompanyServiceTypeName;
//	private String buyerSanctionedCapacity;
//	private String buyerSubstationId,buyerSubstationName,buyerSubstationCode;
//	private String buyerFeederId,buyerFeederName,buyerFeederCode;
//	private String buyerVoltageCode,buyerVoltageName;
//	private String buyerSanctionedDemand;
//	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
//	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear,isCaptive,esIntentCode;
//	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
//	private String sellerOrgId,sellerOrgName,sellerOrgCode;
//	private String sellerSubstationId,sellerSubstationName,sellerSubstationCode;
//	private String sellerFeederId,sellerFeederName,sellerFeederCode;
//	private String sellerVoltageCode,sellerVoltageName,sellerSanctionedCapacity;
//	private String sellerCompanyServiceTypeCode,sellerCompanyServiceTypeName;
//	private String sellerCompanyMeterId,sellerCompanyMeterNumber,isAbtMeter,modemNumber;
//	private String buyerDistrictCode,buyerDistrictName;
//	private String flowTypeCode,flowTypeName;
	
	
}
