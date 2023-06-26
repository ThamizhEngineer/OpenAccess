package com.ss.oa.transaction.vo;

public class EnergySaleIntentLine {
	
	private String id;
	private String energySaleIntentId;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String buyerEndOrgId,buyerEndOrgName,buyerEndOrgCode;
	private String statusCode,statusName;
	private String nocId,consentId,oaAgmtId,epaId,tradeRelationshipId;
	private String nocStatusCode,nocStatusName,nocApprovedCapacity,consentStatusCode,consentStatusName,consentApprovedCapacity,oaAgmtStatusCode,oaAgmtStatusName;
	private String epaStatusCode,epaStatusName,epaApprovedCapacity,ipaaLineId,ipaaLineApprovedCapacity,nocGeneratorLineId,nocGeneratorLineApprovedCapacity;
	private String proposedQuantum,isCaptive,drawalVoltageCode,drawalVoltageName,injectingVoltageCode,injectingVoltageName;
	private String standingClearenceId,standingClearenceStatusCode,standingClearenceStatusName;
	private String ewaLineId,ewaLineApprovedCapacity,sharedPercentage;
	
	public EnergySaleIntentLine() {
		super();
	}

	public EnergySaleIntentLine(String id, String energySaleIntentId, String buyerCompanyServiceId,
			String buyerCompanyServiceNumber, String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode,
			String buyerEndOrgId, String buyerEndOrgName, String buyerEndOrgCode, String statusCode, String statusName,
			String nocId, String consentId, String oaAgmtId, String epaId, String tradeRelationshipId,
			String nocStatusCode, String nocStatusName, String nocApprovedCapacity, String consentStatusCode,
			String consentStatusName, String consentApprovedCapacity, String oaAgmtStatusCode, String oaAgmtStatusName,
			String epaStatusCode, String epaStatusName, String epaApprovedCapacity, String ipaaLineId,
			String ipaaLineApprovedCapacity, String nocGeneratorLineId, String nocGeneratorLineApprovedCapacity,
			String proposedQuantum, String isCaptive, String drawalVoltageCode, String drawalVoltageName,
			String injectingVoltageCode, String injectingVoltageName, String standingClearenceId,
			String standingClearenceStatusCode, String standingClearenceStatusName, String ewaLineId,
			String ewaLineApprovedCapacity, String sharedPercentage) {
		super();
		this.id = id;
		this.energySaleIntentId = energySaleIntentId;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerEndOrgId = buyerEndOrgId;
		this.buyerEndOrgName = buyerEndOrgName;
		this.buyerEndOrgCode = buyerEndOrgCode;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.nocId = nocId;
		this.consentId = consentId;
		this.oaAgmtId = oaAgmtId;
		this.epaId = epaId;
		this.tradeRelationshipId = tradeRelationshipId;
		this.nocStatusCode = nocStatusCode;
		this.nocStatusName = nocStatusName;
		this.nocApprovedCapacity = nocApprovedCapacity;
		this.consentStatusCode = consentStatusCode;
		this.consentStatusName = consentStatusName;
		this.consentApprovedCapacity = consentApprovedCapacity;
		this.oaAgmtStatusCode = oaAgmtStatusCode;
		this.oaAgmtStatusName = oaAgmtStatusName;
		this.epaStatusCode = epaStatusCode;
		this.epaStatusName = epaStatusName;
		this.epaApprovedCapacity = epaApprovedCapacity;
		this.ipaaLineId = ipaaLineId;
		this.ipaaLineApprovedCapacity = ipaaLineApprovedCapacity;
		this.nocGeneratorLineId = nocGeneratorLineId;
		this.nocGeneratorLineApprovedCapacity = nocGeneratorLineApprovedCapacity;
		this.proposedQuantum = proposedQuantum;
		this.isCaptive = isCaptive;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageName = drawalVoltageName;
		this.injectingVoltageCode = injectingVoltageCode;
		this.injectingVoltageName = injectingVoltageName;
		this.standingClearenceId = standingClearenceId;
		this.standingClearenceStatusCode = standingClearenceStatusCode;
		this.standingClearenceStatusName = standingClearenceStatusName;
		this.ewaLineId = ewaLineId;
		this.ewaLineApprovedCapacity = ewaLineApprovedCapacity;
		this.sharedPercentage = sharedPercentage;
	}

	@Override
	public String toString() {
		return "EnergySaleIntentLine [id=" + id + ", energySaleIntentId=" + energySaleIntentId
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyServiceNumber="
				+ buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyName="
				+ buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", buyerEndOrgId=" + buyerEndOrgId
				+ ", buyerEndOrgName=" + buyerEndOrgName + ", buyerEndOrgCode=" + buyerEndOrgCode + ", statusCode="
				+ statusCode + ", statusName=" + statusName + ", nocId=" + nocId + ", consentId=" + consentId
				+ ", oaAgmtId=" + oaAgmtId + ", epaId=" + epaId + ", tradeRelationshipId=" + tradeRelationshipId
				+ ", nocStatusCode=" + nocStatusCode + ", nocStatusName=" + nocStatusName + ", nocApprovedCapacity="
				+ nocApprovedCapacity + ", consentStatusCode=" + consentStatusCode + ", consentStatusName="
				+ consentStatusName + ", consentApprovedCapacity=" + consentApprovedCapacity + ", oaAgmtStatusCode="
				+ oaAgmtStatusCode + ", oaAgmtStatusName=" + oaAgmtStatusName + ", epaStatusCode=" + epaStatusCode
				+ ", epaStatusName=" + epaStatusName + ", epaApprovedCapacity=" + epaApprovedCapacity + ", ipaaLineId="
				+ ipaaLineId + ", ipaaLineApprovedCapacity=" + ipaaLineApprovedCapacity + ", nocGeneratorLineId="
				+ nocGeneratorLineId + ", nocGeneratorLineApprovedCapacity=" + nocGeneratorLineApprovedCapacity
				+ ", proposedQuantum=" + proposedQuantum + ", isCaptive=" + isCaptive + ", drawalVoltageCode="
				+ drawalVoltageCode + ", drawalVoltageName=" + drawalVoltageName + ", injectingVoltageCode="
				+ injectingVoltageCode + ", injectingVoltageName=" + injectingVoltageName + ", standingClearenceId="
				+ standingClearenceId + ", standingClearenceStatusCode=" + standingClearenceStatusCode
				+ ", standingClearenceStatusName=" + standingClearenceStatusName + ", ewaLineId=" + ewaLineId
				+ ", ewaLineApprovedCapacity=" + ewaLineApprovedCapacity + ", sharedPercentage=" + sharedPercentage
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergySaleIntentId() {
		return energySaleIntentId;
	}

	public void setEnergySaleIntentId(String energySaleIntentId) {
		this.energySaleIntentId = energySaleIntentId;
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

	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}

	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}

	public String getBuyerEndOrgId() {
		return buyerEndOrgId;
	}

	public void setBuyerEndOrgId(String buyerEndOrgId) {
		this.buyerEndOrgId = buyerEndOrgId;
	}

	public String getBuyerEndOrgName() {
		return buyerEndOrgName;
	}

	public void setBuyerEndOrgName(String buyerEndOrgName) {
		this.buyerEndOrgName = buyerEndOrgName;
	}

	public String getBuyerEndOrgCode() {
		return buyerEndOrgCode;
	}

	public void setBuyerEndOrgCode(String buyerEndOrgCode) {
		this.buyerEndOrgCode = buyerEndOrgCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getNocId() {
		return nocId;
	}

	public void setNocId(String nocId) {
		this.nocId = nocId;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getOaAgmtId() {
		return oaAgmtId;
	}

	public void setOaAgmtId(String oaAgmtId) {
		this.oaAgmtId = oaAgmtId;
	}

	public String getEpaId() {
		return epaId;
	}

	public void setEpaId(String epaId) {
		this.epaId = epaId;
	}

	public String getTradeRelationshipId() {
		return tradeRelationshipId;
	}

	public void setTradeRelationshipId(String tradeRelationshipId) {
		this.tradeRelationshipId = tradeRelationshipId;
	}

	public String getNocStatusCode() {
		return nocStatusCode;
	}

	public void setNocStatusCode(String nocStatusCode) {
		this.nocStatusCode = nocStatusCode;
	}

	public String getNocStatusName() {
		return nocStatusName;
	}

	public void setNocStatusName(String nocStatusName) {
		this.nocStatusName = nocStatusName;
	}

	public String getNocApprovedCapacity() {
		return nocApprovedCapacity;
	}

	public void setNocApprovedCapacity(String nocApprovedCapacity) {
		this.nocApprovedCapacity = nocApprovedCapacity;
	}

	public String getConsentStatusCode() {
		return consentStatusCode;
	}

	public void setConsentStatusCode(String consentStatusCode) {
		this.consentStatusCode = consentStatusCode;
	}

	public String getConsentStatusName() {
		return consentStatusName;
	}

	public void setConsentStatusName(String consentStatusName) {
		this.consentStatusName = consentStatusName;
	}

	public String getConsentApprovedCapacity() {
		return consentApprovedCapacity;
	}

	public void setConsentApprovedCapacity(String consentApprovedCapacity) {
		this.consentApprovedCapacity = consentApprovedCapacity;
	}

	public String getOaAgmtStatusCode() {
		return oaAgmtStatusCode;
	}

	public void setOaAgmtStatusCode(String oaAgmtStatusCode) {
		this.oaAgmtStatusCode = oaAgmtStatusCode;
	}

	public String getOaAgmtStatusName() {
		return oaAgmtStatusName;
	}

	public void setOaAgmtStatusName(String oaAgmtStatusName) {
		this.oaAgmtStatusName = oaAgmtStatusName;
	}

	public String getEpaStatusCode() {
		return epaStatusCode;
	}

	public void setEpaStatusCode(String epaStatusCode) {
		this.epaStatusCode = epaStatusCode;
	}

	public String getEpaStatusName() {
		return epaStatusName;
	}

	public void setEpaStatusName(String epaStatusName) {
		this.epaStatusName = epaStatusName;
	}

	public String getEpaApprovedCapacity() {
		return epaApprovedCapacity;
	}

	public void setEpaApprovedCapacity(String epaApprovedCapacity) {
		this.epaApprovedCapacity = epaApprovedCapacity;
	}

	public String getIpaaLineId() {
		return ipaaLineId;
	}

	public void setIpaaLineId(String ipaaLineId) {
		this.ipaaLineId = ipaaLineId;
	}

	public String getIpaaLineApprovedCapacity() {
		return ipaaLineApprovedCapacity;
	}

	public void setIpaaLineApprovedCapacity(String ipaaLineApprovedCapacity) {
		this.ipaaLineApprovedCapacity = ipaaLineApprovedCapacity;
	}

	public String getNocGeneratorLineId() {
		return nocGeneratorLineId;
	}

	public void setNocGeneratorLineId(String nocGeneratorLineId) {
		this.nocGeneratorLineId = nocGeneratorLineId;
	}

	public String getNocGeneratorLineApprovedCapacity() {
		return nocGeneratorLineApprovedCapacity;
	}

	public void setNocGeneratorLineApprovedCapacity(String nocGeneratorLineApprovedCapacity) {
		this.nocGeneratorLineApprovedCapacity = nocGeneratorLineApprovedCapacity;
	}

	public String getProposedQuantum() {
		return proposedQuantum;
	}

	public void setProposedQuantum(String proposedQuantum) {
		this.proposedQuantum = proposedQuantum;
	}

	public String getIsCaptive() {
		return isCaptive;
	}

	public void setIsCaptive(String isCaptive) {
		this.isCaptive = isCaptive;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public String getDrawalVoltageName() {
		return drawalVoltageName;
	}

	public void setDrawalVoltageName(String drawalVoltageName) {
		this.drawalVoltageName = drawalVoltageName;
	}

	public String getInjectingVoltageCode() {
		return injectingVoltageCode;
	}

	public void setInjectingVoltageCode(String injectingVoltageCode) {
		this.injectingVoltageCode = injectingVoltageCode;
	}

	public String getInjectingVoltageName() {
		return injectingVoltageName;
	}

	public void setInjectingVoltageName(String injectingVoltageName) {
		this.injectingVoltageName = injectingVoltageName;
	}

	public String getStandingClearenceId() {
		return standingClearenceId;
	}

	public void setStandingClearenceId(String standingClearenceId) {
		this.standingClearenceId = standingClearenceId;
	}

	public String getStandingClearenceStatusCode() {
		return standingClearenceStatusCode;
	}

	public void setStandingClearenceStatusCode(String standingClearenceStatusCode) {
		this.standingClearenceStatusCode = standingClearenceStatusCode;
	}

	public String getStandingClearenceStatusName() {
		return standingClearenceStatusName;
	}

	public void setStandingClearenceStatusName(String standingClearenceStatusName) {
		this.standingClearenceStatusName = standingClearenceStatusName;
	}

	public String getEwaLineId() {
		return ewaLineId;
	}

	public void setEwaLineId(String ewaLineId) {
		this.ewaLineId = ewaLineId;
	}

	public String getEwaLineApprovedCapacity() {
		return ewaLineApprovedCapacity;
	}

	public void setEwaLineApprovedCapacity(String ewaLineApprovedCapacity) {
		this.ewaLineApprovedCapacity = ewaLineApprovedCapacity;
	}

	public String getSharedPercentage() {
		return sharedPercentage;
	}

	public void setSharedPercentage(String sharedPercentage) {
		this.sharedPercentage = sharedPercentage;
	}

	
}
