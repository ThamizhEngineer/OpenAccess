package com.ss.oa.transaction.vo;

import java.util.List;

public class NocGenerator {
	
	private String id,code;
	private String sellerCompanyServiceId,sellerCompanyServiceNumber;
	private String sellerCompanyId,sellerCompanyName,sellerCompanyCode;
	private String esIntentId,esIntentCode,ipaaId,ipaaQuantum,ipaaApprovedDate;
	private String sellerInjectingVoltageCode,sellerInjectingVoltageName;
	private String orgId,orgCode,orgName;
	private String substationName,substationId,substationCode,feederName,feederId,feederCode;
	private String fuelTypeCode,fuelTypeName,capacity;
	private String auxiliaryConsumption,inHouseConsumption,exBus;
	private String approvedPowerCapacity;
	private String isOnlineDataMonitoring;
	private String isTangedco,tangedcoRefNumber,tangedcoApprovedQuantum,tangedcoDated,tangedcoTillDate;
	private String captiveQuantum,thirdPartyQuantum,traderQuantum,otherQuantum,totalPowerSaleCommitments;
	private String maximumSurplusQuantum;
	private String statusCode,statusName;
	private String flowTypeCode,flowTypeName;
	private List<NocGeneratorLine> nocGeneratorLines;
	
	public NocGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NocGenerator(String id, String code, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyId, String sellerCompanyName, String sellerCompanyCode, String esIntentId,
			String esIntentCode, String ipaaId, String ipaaQuantum, String ipaaApprovedDate,
			String sellerInjectingVoltageCode, String sellerInjectingVoltageName, String orgId, String orgCode,
			String orgName, String substationName, String substationId, String substationCode, String feederName,
			String feederId, String feederCode, String fuelTypeCode, String fuelTypeName, String capacity,
			String auxiliaryConsumption, String inHouseConsumption, String exBus, String approvedPowerCapacity,
			String isOnlineDataMonitoring, String isTangedco, String tangedcoRefNumber, String tangedcoApprovedQuantum,
			String tangedcoDated, String tangedcoTillDate, String captiveQuantum, String thirdPartyQuantum,
			String traderQuantum, String otherQuantum, String totalPowerSaleCommitments, String maximumSurplusQuantum,
			String statusCode, String statusName, String flowTypeCode, String flowTypeName,
			List<NocGeneratorLine> nocGeneratorLines) {
		super();
		this.id = id;
		this.code = code;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.esIntentId = esIntentId;
		this.esIntentCode = esIntentCode;
		this.ipaaId = ipaaId;
		this.ipaaQuantum = ipaaQuantum;
		this.ipaaApprovedDate = ipaaApprovedDate;
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.substationName = substationName;
		this.substationId = substationId;
		this.substationCode = substationCode;
		this.feederName = feederName;
		this.feederId = feederId;
		this.feederCode = feederCode;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.capacity = capacity;
		this.auxiliaryConsumption = auxiliaryConsumption;
		this.inHouseConsumption = inHouseConsumption;
		this.exBus = exBus;
		this.approvedPowerCapacity = approvedPowerCapacity;
		this.isOnlineDataMonitoring = isOnlineDataMonitoring;
		this.isTangedco = isTangedco;
		this.tangedcoRefNumber = tangedcoRefNumber;
		this.tangedcoApprovedQuantum = tangedcoApprovedQuantum;
		this.tangedcoDated = tangedcoDated;
		this.tangedcoTillDate = tangedcoTillDate;
		this.captiveQuantum = captiveQuantum;
		this.thirdPartyQuantum = thirdPartyQuantum;
		this.traderQuantum = traderQuantum;
		this.otherQuantum = otherQuantum;
		this.totalPowerSaleCommitments = totalPowerSaleCommitments;
		this.maximumSurplusQuantum = maximumSurplusQuantum;
		this.statusCode = statusCode;
		this.statusName = statusName;
		this.flowTypeCode = flowTypeCode;
		this.flowTypeName = flowTypeName;
		this.nocGeneratorLines = nocGeneratorLines;
	}

	@Override
	public String toString() {
		return "NocGenerator [id=" + id + ", code=" + code + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode
				+ ", esIntentId=" + esIntentId + ", esIntentCode=" + esIntentCode + ", ipaaId=" + ipaaId
				+ ", ipaaQuantum=" + ipaaQuantum + ", ipaaApprovedDate=" + ipaaApprovedDate
				+ ", sellerInjectingVoltageCode=" + sellerInjectingVoltageCode + ", sellerInjectingVoltageName="
				+ sellerInjectingVoltageName + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", substationName=" + substationName + ", substationId=" + substationId + ", substationCode="
				+ substationCode + ", feederName=" + feederName + ", feederId=" + feederId + ", feederCode="
				+ feederCode + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + ", capacity="
				+ capacity + ", auxiliaryConsumption=" + auxiliaryConsumption + ", inHouseConsumption="
				+ inHouseConsumption + ", exBus=" + exBus + ", approvedPowerCapacity=" + approvedPowerCapacity
				+ ", isOnlineDataMonitoring=" + isOnlineDataMonitoring + ", isTangedco=" + isTangedco
				+ ", tangedcoRefNumber=" + tangedcoRefNumber + ", tangedcoApprovedQuantum=" + tangedcoApprovedQuantum
				+ ", tangedcoDated=" + tangedcoDated + ", tangedcoTillDate=" + tangedcoTillDate + ", captiveQuantum="
				+ captiveQuantum + ", thirdPartyQuantum=" + thirdPartyQuantum + ", traderQuantum=" + traderQuantum
				+ ", otherQuantum=" + otherQuantum + ", totalPowerSaleCommitments=" + totalPowerSaleCommitments
				+ ", maximumSurplusQuantum=" + maximumSurplusQuantum + ", statusCode=" + statusCode + ", statusName="
				+ statusName + ", flowTypeCode=" + flowTypeCode + ", flowTypeName=" + flowTypeName
				+ ", nocGeneratorLines=" + nocGeneratorLines + "]";
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

	public String getEsIntentId() {
		return esIntentId;
	}

	public String getEsIntentCode() {
		return esIntentCode;
	}

	public String getIpaaId() {
		return ipaaId;
	}

	public String getIpaaQuantum() {
		return ipaaQuantum;
	}

	public String getIpaaApprovedDate() {
		return ipaaApprovedDate;
	}

	public String getSellerInjectingVoltageCode() {
		return sellerInjectingVoltageCode;
	}

	public String getSellerInjectingVoltageName() {
		return sellerInjectingVoltageName;
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

	public String getSubstationName() {
		return substationName;
	}

	public String getSubstationId() {
		return substationId;
	}

	public String getSubstationCode() {
		return substationCode;
	}

	public String getFeederName() {
		return feederName;
	}

	public String getFeederId() {
		return feederId;
	}

	public String getFeederCode() {
		return feederCode;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public String getCapacity() {
		return capacity;
	}

	public String getAuxiliaryConsumption() {
		return auxiliaryConsumption;
	}

	public String getInHouseConsumption() {
		return inHouseConsumption;
	}

	public String getExBus() {
		return exBus;
	}

	public String getApprovedPowerCapacity() {
		return approvedPowerCapacity;
	}

	public String getIsOnlineDataMonitoring() {
		return isOnlineDataMonitoring;
	}

	public String getIsTangedco() {
		return isTangedco;
	}

	public String getTangedcoRefNumber() {
		return tangedcoRefNumber;
	}

	public String getTangedcoApprovedQuantum() {
		return tangedcoApprovedQuantum;
	}

	public String getTangedcoDated() {
		return tangedcoDated;
	}

	public String getTangedcoTillDate() {
		return tangedcoTillDate;
	}

	public String getCaptiveQuantum() {
		return captiveQuantum;
	}

	public String getThirdPartyQuantum() {
		return thirdPartyQuantum;
	}

	public String getTraderQuantum() {
		return traderQuantum;
	}

	public String getOtherQuantum() {
		return otherQuantum;
	}

	public String getTotalPowerSaleCommitments() {
		return totalPowerSaleCommitments;
	}

	public String getMaximumSurplusQuantum() {
		return maximumSurplusQuantum;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public String getFlowTypeName() {
		return flowTypeName;
	}

	public List<NocGeneratorLine> getNocGeneratorLines() {
		return nocGeneratorLines;
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

	public void setEsIntentId(String esIntentId) {
		this.esIntentId = esIntentId;
	}

	public void setEsIntentCode(String esIntentCode) {
		this.esIntentCode = esIntentCode;
	}

	public void setIpaaId(String ipaaId) {
		this.ipaaId = ipaaId;
	}

	public void setIpaaQuantum(String ipaaQuantum) {
		this.ipaaQuantum = ipaaQuantum;
	}

	public void setIpaaApprovedDate(String ipaaApprovedDate) {
		this.ipaaApprovedDate = ipaaApprovedDate;
	}

	public void setSellerInjectingVoltageCode(String sellerInjectingVoltageCode) {
		this.sellerInjectingVoltageCode = sellerInjectingVoltageCode;
	}

	public void setSellerInjectingVoltageName(String sellerInjectingVoltageName) {
		this.sellerInjectingVoltageName = sellerInjectingVoltageName;
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

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public void setSubstationCode(String substationCode) {
		this.substationCode = substationCode;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public void setFeederCode(String feederCode) {
		this.feederCode = feederCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public void setAuxiliaryConsumption(String auxiliaryConsumption) {
		this.auxiliaryConsumption = auxiliaryConsumption;
	}

	public void setInHouseConsumption(String inHouseConsumption) {
		this.inHouseConsumption = inHouseConsumption;
	}

	public void setExBus(String exBus) {
		this.exBus = exBus;
	}

	public void setApprovedPowerCapacity(String approvedPowerCapacity) {
		this.approvedPowerCapacity = approvedPowerCapacity;
	}

	public void setIsOnlineDataMonitoring(String isOnlineDataMonitoring) {
		this.isOnlineDataMonitoring = isOnlineDataMonitoring;
	}

	public void setIsTangedco(String isTangedco) {
		this.isTangedco = isTangedco;
	}

	public void setTangedcoRefNumber(String tangedcoRefNumber) {
		this.tangedcoRefNumber = tangedcoRefNumber;
	}

	public void setTangedcoApprovedQuantum(String tangedcoApprovedQuantum) {
		this.tangedcoApprovedQuantum = tangedcoApprovedQuantum;
	}

	public void setTangedcoDated(String tangedcoDated) {
		this.tangedcoDated = tangedcoDated;
	}

	public void setTangedcoTillDate(String tangedcoTillDate) {
		this.tangedcoTillDate = tangedcoTillDate;
	}

	public void setCaptiveQuantum(String captiveQuantum) {
		this.captiveQuantum = captiveQuantum;
	}

	public void setThirdPartyQuantum(String thirdPartyQuantum) {
		this.thirdPartyQuantum = thirdPartyQuantum;
	}

	public void setTraderQuantum(String traderQuantum) {
		this.traderQuantum = traderQuantum;
	}

	public void setOtherQuantum(String otherQuantum) {
		this.otherQuantum = otherQuantum;
	}

	public void setTotalPowerSaleCommitments(String totalPowerSaleCommitments) {
		this.totalPowerSaleCommitments = totalPowerSaleCommitments;
	}

	public void setMaximumSurplusQuantum(String maximumSurplusQuantum) {
		this.maximumSurplusQuantum = maximumSurplusQuantum;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public void setNocGeneratorLines(List<NocGeneratorLine> nocGeneratorLines) {
		this.nocGeneratorLines = nocGeneratorLines;
	}
	
	



	

	
	
	
}
