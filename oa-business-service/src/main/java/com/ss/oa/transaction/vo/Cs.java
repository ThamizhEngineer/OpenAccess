package com.ss.oa.transaction.vo;

import java.util.List;

public class Cs {

	private String id,code;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String sellerOrgId,sellerOrgName,sellerOrgCode;
	private String sellerSubstationId,sellerSubstationCode,sellerSubstationName;
	private String sellerFeederId,sellerFeederCode,sellerFeederName;
	private String agreementDate,effectiveDate;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear;
	private String approvedCapacity,voltageCode,voltageName;
	private String statusCode,statusName;
	private String agreementPeriodCode,agreementPeriodName;
	private String flowTypeCode,flowTypeName;
	private String idTotalCost,idTotalCurrency,idTotalExchangeRate;
	private String esIntentId,esIntentCode;
	private String enabled,createdDate,createdBy,modifiedDate,modifiedBy;
    private List <CsCaptiveUserContribution> csCaptiveUserContributions;
    private List <CsQuantumAllocation> csQuantumAllocations;
    private List <CsEquityShareVotingRight> csEquityShareVotingRights;
    private List <CsLoan> csLoans;
    
    
	public Cs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cs(String id, String code, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyId, String sellerCompanyName, String sellerCompanyCode, String sellerOrgId,
			String sellerOrgName, String sellerOrgCode, String sellerSubstationId, String sellerSubstationCode,
			String sellerSubstationName, String sellerFeederId, String sellerFeederCode, String sellerFeederName,
			String agreementDate, String effectiveDate, String fromDate, String toDate, String fromMonth,
			String toMonth, String fromYear, String toYear, String approvedCapacity, String voltageCode,
			String voltageName, String statusCode, String statusName, String agreementPeriodCode,
			String agreementPeriodName, String flowTypeCode, String flowTypeName, String idTotalCost,
			String idTotalCurrency, String idTotalExchangeRate, String esIntentId, String esIntentCode, String enabled,
			String createdDate, String createdBy, String modifiedDate, String modifiedBy,
			List<CsCaptiveUserContribution> csCaptiveUserContributions, List<CsQuantumAllocation> csQuantumAllocations,
			List<CsEquityShareVotingRight> csEquityShareVotingRights, List<CsLoan> csLoans) {
		super();
		this.id = id;
		this.code = code;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerOrgId = sellerOrgId;
		this.sellerOrgName = sellerOrgName;
		this.sellerOrgCode = sellerOrgCode;
		this.sellerSubstationId = sellerSubstationId;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerFeederName = sellerFeederName;
		this.agreementDate = agreementDate;
		this.effectiveDate = effectiveDate;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.approvedCapacity = approvedCapacity;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodName = agreementPeriodName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.idTotalCost = idTotalCost;
		this.idTotalCurrency = idTotalCurrency;
		this.idTotalExchangeRate = idTotalExchangeRate;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.enabled = enabled;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.csCaptiveUserContributions = csCaptiveUserContributions;
		this.csQuantumAllocations = csQuantumAllocations;
		this.csEquityShareVotingRights = csEquityShareVotingRights;
		this.csLoans = csLoans;
	}
	@Override
	public String toString() {
		return "Cs [id=" + id + ", code=" + code + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", sellerOrgId=" + sellerOrgId + ", sellerOrgName=" + sellerOrgName + ", sellerOrgCode="
				+ sellerOrgCode + ", sellerSubstationId=" + sellerSubstationId + ", sellerSubstationCode="
				+ sellerSubstationCode + ", sellerSubstationName=" + sellerSubstationName + ", sellerFeederId="
				+ sellerFeederId + ", sellerFeederCode=" + sellerFeederCode + ", sellerFeederName=" + sellerFeederName
				+ ", agreementDate=" + agreementDate + ", effectiveDate=" + effectiveDate + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear=" + fromYear
				+ ", toYear=" + toYear + ", approvedCapacity=" + approvedCapacity + ", voltageCode=" + voltageCode
				+ ", voltageName=" + voltageName + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", agreementPeriodCode=" + agreementPeriodCode + ", agreementPeriodName=" + agreementPeriodName
				+ ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + ", idTotalCost=" + idTotalCost
				+ ", idTotalCurrency=" + idTotalCurrency + ", idTotalExchangeRate=" + idTotalExchangeRate
				+ ", esIntentId=" + esIntentId + ", esIntentCode=" + esIntentCode + ", enabled=" + enabled
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + ", csCaptiveUserContributions=" + csCaptiveUserContributions
				+ ", csQuantumAllocations=" + csQuantumAllocations + ", csEquityShareVotingRights="
				+ csEquityShareVotingRights + ", csLoans=" + csLoans + "]";
	}
	public String getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}
	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}
	public String getSellerCompanyId() {
		return sellerCompanyId;
	}
	public String getSellerCompanyName() {
		return sellerCompanyName;
	}
	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}
	public String getSellerOrgId() {
		return sellerOrgId;
	}
	public String getSellerOrgName() {
		return sellerOrgName;
	}
	public String getSellerOrgCode() {
		return sellerOrgCode;
	}
	public String getSellerSubstationId() {
		return sellerSubstationId;
	}
	public String getSellerSubstationCode() {
		return sellerSubstationCode;
	}
	public String getSellerSubstationName() {
		return sellerSubstationName;
	}
	public String getSellerFeederId() {
		return sellerFeederId;
	}
	public String getSellerFeederCode() {
		return sellerFeederCode;
	}
	public String getSellerFeederName() {
		return sellerFeederName;
	}
	public String getAgreementDate() {
		return agreementDate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public String getToMonth() {
		return toMonth;
	}
	public String getFromYear() {
		return fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public String getApprovedCapacity() {
		return approvedCapacity;
	}
	public String getVoltageCode() {
		return voltageCode;
	}
	public String getVoltageName() {
		return voltageName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public String getAgreementPeriodCode() {
		return agreementPeriodCode;
	}
	public String getAgreementPeriodName() {
		return agreementPeriodName;
	}
	public String getFlowTypeCode() {
		return flowTypeCode;
	}
	public String getFlowTypeName() {
		return flowTypeName;
	}
	public String getIdTotalCost() {
		return idTotalCost;
	}
	public String getIdTotalCurrency() {
		return idTotalCurrency;
	}
	public String getIdTotalExchangeRate() {
		return idTotalExchangeRate;
	}
	public String getEsIntentId() {
		return esIntentId;
	}
	public String getEsIntentCode() {
		return esIntentCode;
	}
	public String getEnabled() {
		return enabled;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public List<CsCaptiveUserContribution> getCsCaptiveUserContributions() {
		return csCaptiveUserContributions;
	}
	public List<CsQuantumAllocation> getCsQuantumAllocations() {
		return csQuantumAllocations;
	}
	public List<CsEquityShareVotingRight> getCsEquityShareVotingRights() {
		return csEquityShareVotingRights;
	}
	public List<CsLoan> getCsLoans() {
		return csLoans;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}
	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}
	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}
	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}
	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}
	public void setSellerOrgId(String sellerOrgId) {
		this.sellerOrgId = sellerOrgId;
	}
	public void setSellerOrgName(String sellerOrgName) {
		this.sellerOrgName = sellerOrgName;
	}
	public void setSellerOrgCode(String sellerOrgCode) {
		this.sellerOrgCode = sellerOrgCode;
	}
	public void setSellerSubstationId(String sellerSubstationId) {
		this.sellerSubstationId = sellerSubstationId;
	}
	public void setSellerSubstationCode(String sellerSubstationCode) {
		this.sellerSubstationCode = sellerSubstationCode;
	}
	public void setSellerSubstationName(String sellerSubstationName) {
		this.sellerSubstationName = sellerSubstationName;
	}
	public void setSellerFeederId(String sellerFeederId) {
		this.sellerFeederId = sellerFeederId;
	}
	public void setSellerFeederCode(String sellerFeederCode) {
		this.sellerFeederCode = sellerFeederCode;
	}
	public void setSellerFeederName(String sellerFeederName) {
		this.sellerFeederName = sellerFeederName;
	}
	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public void setApprovedCapacity(String approvedCapacity) {
		this.approvedCapacity = approvedCapacity;
	}
	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}
	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public void setAgreementPeriodCode(String agreementPeriodCode) {
		this.agreementPeriodCode = agreementPeriodCode;
	}
	public void setAgreementPeriodName(String agreementPeriodName) {
		this.agreementPeriodName = agreementPeriodName;
	}
	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}
	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}
	public void setIdTotalCost(String idTotalCost) {
		this.idTotalCost = idTotalCost;
	}
	public void setIdTotalCurrency(String idTotalCurrency) {
		this.idTotalCurrency = idTotalCurrency;
	}
	public void setIdTotalExchangeRate(String idTotalExchangeRate) {
		this.idTotalExchangeRate = idTotalExchangeRate;
	}
	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}
	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public void setCsCaptiveUserContributions(List<CsCaptiveUserContribution> csCaptiveUserContributions) {
		this.csCaptiveUserContributions = csCaptiveUserContributions;
	}
	public void setCsQuantumAllocations(List<CsQuantumAllocation> csQuantumAllocations) {
		this.csQuantumAllocations = csQuantumAllocations;
	}
	public void setCsEquityShareVotingRights(List<CsEquityShareVotingRight> csEquityShareVotingRights) {
		this.csEquityShareVotingRights = csEquityShareVotingRights;
	}
	public void setCsLoans(List<CsLoan> csLoans) {
		this.csLoans = csLoans;
	}
    
    
}
