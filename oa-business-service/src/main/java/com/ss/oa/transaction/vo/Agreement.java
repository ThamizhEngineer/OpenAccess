package com.ss.oa.transaction.vo;

import java.util.List;

public class Agreement {

	private String id,code;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String sellerOrgId,sellerOrgName,sellerOrgCode;
	private String sellerSubstationid,sellerSubstationCode,sellerSubstationName;
	private String sellerFeederId,sellerFeederCode,sellerFeederName;
	private String version,agreementDate;
	private String isActive;
	private String fromDate,toDate,fromMonth,toMonth,fromYear,toYear;
	private String totalUnits;
	private String c1,c2,c3,c4,c5;
	private String peakUnits,offPeakUnits;
	private String signatoryParty1,signatoryParty2,signatoryParty3;
	private String hasLines;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String buyerOrgId,buyerOrgName,buyerOrgCode;
	private String buyerSubstationid,buyerSubstationCode,buyerSubstationName;
	private String buyerFeederId,buyerFeederCode,buyerFeederName;
	private String versionChangeReasonCode,versionChangeReasonName;
	private String statusCode,statusName;
	private String agreementPeriodCode,agreementPeriodName;
	private String flowTypeCode,flowTypeName;
	private String isLatest;
	private String intervalTypeCode,intervalTypeName;
	private String sharePercent;
	private String isCaptive;
	private String esIntentId,esIntentCode;	
	private String ewaId,oaaId,nocGeneratorId,ipaaId,epaId,csId;
	private String enabled,createdDate,createdBy,modifiedDate,modifiedBy;
	private List<AgreementLine> agreementLines;
	
	public Agreement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Agreement(String id, String code, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyId, String sellerCompanyName, String sellerCompanyCode, String sellerOrgId,
			String sellerOrgName, String sellerOrgCode, String sellerSubstationid, String sellerSubstationCode,
			String sellerSubstationName, String sellerFeederId, String sellerFeederCode, String sellerFeederName,
			String version, String agreementDate, String isActive, String fromDate, String toDate, String fromMonth,
			String toMonth, String fromYear, String toYear, String totalUnits, String c1, String c2, String c3,
			String c4, String c5, String peakUnits, String offPeakUnits, String signatoryParty1, String signatoryParty2,
			String signatoryParty3, String hasLines, String buyerCompanyServiceId, String buyerCompanyServiceNumber,
			String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode, String buyerOrgId,
			String buyerOrgName, String buyerOrgCode, String buyerSubstationid, String buyerSubstationCode,
			String buyerSubstationName, String buyerFeederId, String buyerFeederCode, String buyerFeederName,
			String versionChangeReasonCode, String versionChangeReasonName, String statusCode, String statusName,
			String agreementPeriodCode, String agreementPeriodName, String flowTypeCode, String flowTypeName,
			String isLatest, String intervalTypeCode, String intervalTypeName, String sharePercent, String isCaptive,
			String esIntentId, String esIntentCode, String ewaId, String oaaId, String nocGeneratorId, String ipaaId,
			String epaId, String csId, String enabled, String createdDate, String createdBy, String modifiedDate,
			String modifiedBy, List<AgreementLine> agreementLines) {
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
		this.sellerSubstationid = sellerSubstationid;
		this.sellerSubstationCode = sellerSubstationCode;
		this.sellerSubstationName = sellerSubstationName;
		this.sellerFeederId = sellerFeederId;
		this.sellerFeederCode = sellerFeederCode;
		this.sellerFeederName = sellerFeederName;
		this.version = version;
		this.agreementDate = agreementDate;
		this.isActive = isActive;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.totalUnits = totalUnits;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.peakUnits = peakUnits;
		this.offPeakUnits = offPeakUnits;
		this.signatoryParty1 = signatoryParty1;
		this.signatoryParty2 = signatoryParty2;
		this.signatoryParty3 = signatoryParty3;
		this.hasLines = hasLines;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerOrgId = buyerOrgId;
		this.buyerOrgName = buyerOrgName;
		this.buyerOrgCode = buyerOrgCode;
		this.buyerSubstationid = buyerSubstationid;
		this.buyerSubstationCode = buyerSubstationCode;
		this.buyerSubstationName = buyerSubstationName;
		this.buyerFeederId = buyerFeederId;
		this.buyerFeederCode = buyerFeederCode;
		this.buyerFeederName = buyerFeederName;
		this.versionChangeReasonCode = versionChangeReasonCode;
		this.versionChangeReasonName = versionChangeReasonName;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.agreementPeriodCode = agreementPeriodCode;
		this.agreementPeriodName = agreementPeriodName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.isLatest = isLatest;
		this.intervalTypeCode = intervalTypeCode;
		this.intervalTypeName = intervalTypeName;
		this.sharePercent = sharePercent;
		this.isCaptive = isCaptive;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.ewaId = ewaId;
		this.oaaId = oaaId;
		this.nocGeneratorId = nocGeneratorId;
		this.ipaaId = ipaaId;
		this.epaId = epaId;
		this.csId = csId;
		this.enabled = enabled;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.agreementLines = agreementLines;
	}

	@Override
	public String toString() {
		return "Agreement [id=" + id + ", code=" + code + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", sellerOrgId=" + sellerOrgId + ", sellerOrgName=" + sellerOrgName + ", sellerOrgCode="
				+ sellerOrgCode + ", sellerSubstationid=" + sellerSubstationid + ", sellerSubstationCode="
				+ sellerSubstationCode + ", sellerSubstationName=" + sellerSubstationName + ", sellerFeederId="
				+ sellerFeederId + ", sellerFeederCode=" + sellerFeederCode + ", sellerFeederName=" + sellerFeederName
				+ ", version=" + version + ", agreementDate=" + agreementDate + ", isActive=" + isActive + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", fromMonth=" + fromMonth + ", toMonth=" + toMonth + ", fromYear="
				+ fromYear + ", toYear=" + toYear + ", totalUnits=" + totalUnits + ", c1=" + c1 + ", c2=" + c2 + ", c3="
				+ c3 + ", c4=" + c4 + ", c5=" + c5 + ", peakUnits=" + peakUnits + ", offPeakUnits=" + offPeakUnits
				+ ", signatoryParty1=" + signatoryParty1 + ", signatoryParty2=" + signatoryParty2 + ", signatoryParty3="
				+ signatoryParty3 + ", hasLines=" + hasLines + ", buyerCompanyServiceId=" + buyerCompanyServiceId
				+ ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId
				+ ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", buyerOrgId="
				+ buyerOrgId + ", buyerOrgName=" + buyerOrgName + ", buyerOrgCode=" + buyerOrgCode
				+ ", buyerSubstationid=" + buyerSubstationid + ", buyerSubstationCode=" + buyerSubstationCode
				+ ", buyerSubstationName=" + buyerSubstationName + ", buyerFeederId=" + buyerFeederId
				+ ", buyerFeederCode=" + buyerFeederCode + ", buyerFeederName=" + buyerFeederName
				+ ", versionChangeReasonCode=" + versionChangeReasonCode + ", versionChangeReasonName="
				+ versionChangeReasonName + ", statusCode=" + statusCode + ", statusName=" + statusName
				+ ", agreementPeriodCode=" + agreementPeriodCode + ", agreementPeriodName=" + agreementPeriodName
				+ ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName + ", isLatest=" + isLatest
				+ ", intervalTypeCode=" + intervalTypeCode + ", intervalTypeName=" + intervalTypeName
				+ ", sharePercent=" + sharePercent + ", isCaptive=" + isCaptive + ", esIntentId=" + esIntentId
				+ ", esIntentCode=" + esIntentCode + ", ewaId=" + ewaId + ", oaaId=" + oaaId + ", nocGeneratorId="
				+ nocGeneratorId + ", ipaaId=" + ipaaId + ", epaId=" + epaId + ", csId=" + csId + ", enabled=" + enabled
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + ", agreementLines=" + agreementLines + "]";
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

	public String getSellerSubstationid() {
		return sellerSubstationid;
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

	public String getVersion() {
		return version;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public String getIsActive() {
		return isActive;
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

	public String getTotalUnits() {
		return totalUnits;
	}

	public String getC1() {
		return c1;
	}

	public String getC2() {
		return c2;
	}

	public String getC3() {
		return c3;
	}

	public String getC4() {
		return c4;
	}

	public String getC5() {
		return c5;
	}

	public String getPeakUnits() {
		return peakUnits;
	}

	public String getOffPeakUnits() {
		return offPeakUnits;
	}

	public String getSignatoryParty1() {
		return signatoryParty1;
	}

	public String getSignatoryParty2() {
		return signatoryParty2;
	}

	public String getSignatoryParty3() {
		return signatoryParty3;
	}

	public String getHasLines() {
		return hasLines;
	}

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}

	public String getBuyerOrgId() {
		return buyerOrgId;
	}

	public String getBuyerOrgName() {
		return buyerOrgName;
	}

	public String getBuyerOrgCode() {
		return buyerOrgCode;
	}

	public String getBuyerSubstationid() {
		return buyerSubstationid;
	}

	public String getBuyerSubstationCode() {
		return buyerSubstationCode;
	}

	public String getBuyerSubstationName() {
		return buyerSubstationName;
	}

	public String getBuyerFeederId() {
		return buyerFeederId;
	}

	public String getBuyerFeederCode() {
		return buyerFeederCode;
	}

	public String getBuyerFeederName() {
		return buyerFeederName;
	}

	public String getVersionChangeReasonCode() {
		return versionChangeReasonCode;
	}

	public String getVersionChangeReasonName() {
		return versionChangeReasonName;
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

	public String getIsLatest() {
		return isLatest;
	}

	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}

	public String getIntervalTypeName() {
		return intervalTypeName;
	}

	public String getSharePercent() {
		return sharePercent;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public String getEsIntentId() {
		return esIntentId;
	}

	public String getEsIntentCode() {
		return esIntentCode;
	}

	public String getEwaId() {
		return ewaId;
	}

	public String getOaaId() {
		return oaaId;
	}

	public String getNocGeneratorId() {
		return nocGeneratorId;
	}

	public String getIpaaId() {
		return ipaaId;
	}

	public String getEpaId() {
		return epaId;
	}

	public String getCsId() {
		return csId;
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

	public List<AgreementLine> getAgreementLines() {
		return agreementLines;
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

	public void setSellerSubstationid(String sellerSubstationid) {
		this.sellerSubstationid = sellerSubstationid;
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

	public void setVersion(String version) {
		this.version = version;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public void setTotalUnits(String totalUnits) {
		this.totalUnits = totalUnits;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}

	public void setPeakUnits(String peakUnits) {
		this.peakUnits = peakUnits;
	}

	public void setOffPeakUnits(String offPeakUnits) {
		this.offPeakUnits = offPeakUnits;
	}

	public void setSignatoryParty1(String signatoryParty1) {
		this.signatoryParty1 = signatoryParty1;
	}

	public void setSignatoryParty2(String signatoryParty2) {
		this.signatoryParty2 = signatoryParty2;
	}

	public void setSignatoryParty3(String signatoryParty3) {
		this.signatoryParty3 = signatoryParty3;
	}

	public void setHasLines(String hasLines) {
		this.hasLines = hasLines;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}

	public void setBuyerOrgId(String buyerOrgId) {
		this.buyerOrgId = buyerOrgId;
	}

	public void setBuyerOrgName(String buyerOrgName) {
		this.buyerOrgName = buyerOrgName;
	}

	public void setBuyerOrgCode(String buyerOrgCode) {
		this.buyerOrgCode = buyerOrgCode;
	}

	public void setBuyerSubstationid(String buyerSubstationid) {
		this.buyerSubstationid = buyerSubstationid;
	}

	public void setBuyerSubstationCode(String buyerSubstationCode) {
		this.buyerSubstationCode = buyerSubstationCode;
	}

	public void setBuyerSubstationName(String buyerSubstationName) {
		this.buyerSubstationName = buyerSubstationName;
	}

	public void setBuyerFeederId(String buyerFeederId) {
		this.buyerFeederId = buyerFeederId;
	}

	public void setBuyerFeederCode(String buyerFeederCode) {
		this.buyerFeederCode = buyerFeederCode;
	}

	public void setBuyerFeederName(String buyerFeederName) {
		this.buyerFeederName = buyerFeederName;
	}

	public void setVersionChangeReasonCode(String versionChangeReasonCode) {
		this.versionChangeReasonCode = versionChangeReasonCode;
	}

	public void setVersionChangeReasonName(String versionChangeReasonName) {
		this.versionChangeReasonName = versionChangeReasonName;
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

	public void setIsLatest(String isLatest) {
		this.isLatest = isLatest;
	}

	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
	}

	public void setIntervalTypeName(String intervalTypeName) {
		this.intervalTypeName = intervalTypeName;
	}

	public void setSharePercent(String sharePercent) {
		this.sharePercent = sharePercent;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}

	public void setEwaId(String ewaId) {
		this.ewaId = ewaId;
	}

	public void setOaaId(String oaaId) {
		this.oaaId = oaaId;
	}

	public void setNocGeneratorId(String nocGeneratorId) {
		this.nocGeneratorId = nocGeneratorId;
	}

	public void setIpaaId(String ipaaId) {
		this.ipaaId = ipaaId;
	}

	public void setEpaId(String epaId) {
		this.epaId = epaId;
	}

	public void setCsId(String csId) {
		this.csId = csId;
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

	public void setAgreementLines(List<AgreementLine> agreementLines) {
		this.agreementLines = agreementLines;
	}
	
	
	
	
}
