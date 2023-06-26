package com.ss.oa.oadocumentservice.vo;

public class Vcompany {
	private String orgId;
	private String orgName;
	private String substationName;
	private String substationId;
	private String feederId;
	private String feederName;
	private String totalExportUnits;
	private String totalServices;
	private String billedService;
	private String exportDifference; 
	private String lossUnits;
	private String bulkMeterReading;
	private String meterNo;
	private String subbulkreading;
	
	public Vcompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vcompany(String orgId, String orgName, String substationName, String substationId, String feederId,
			String feederName, String totalExportUnits, String totalServices, String billedService,
			String exportDifference, String lossUnits, String bulkMeterReading, String meterNo, String subbulkreading) {
		super();
		this.orgId = orgId;
		this.orgName = orgName;
		this.substationName = substationName;
		this.substationId = substationId;
		this.feederId = feederId;
		this.feederName = feederName;
		this.totalExportUnits = totalExportUnits;
		this.totalServices = totalServices;
		this.billedService = billedService;
		this.exportDifference = exportDifference;
		this.lossUnits = lossUnits;
		this.bulkMeterReading = bulkMeterReading;
		this.meterNo = meterNo;
		this.subbulkreading = subbulkreading;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSubstationName() {
		return substationName;
	}

	public void setSubstationName(String substationName) {
		this.substationName = substationName;
	}

	public String getSubstationId() {
		return substationId;
	}

	public void setSubstationId(String substationId) {
		this.substationId = substationId;
	}

	public String getFeederId() {
		return feederId;
	}

	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}

	public String getFeederName() {
		return feederName;
	}

	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}

	public String getTotalExportUnits() {
		return totalExportUnits;
	}

	public void setTotalExportUnits(String totalExportUnits) {
		this.totalExportUnits = totalExportUnits;
	}

	public String getTotalServices() {
		return totalServices;
	}

	public void setTotalServices(String totalServices) {
		this.totalServices = totalServices;
	}

	public String getBilledService() {
		return billedService;
	}

	public void setBilledService(String billedService) {
		this.billedService = billedService;
	}

	public String getExportDifference() {
		return exportDifference;
	}

	public void setExportDifference(String exportDifference) {
		this.exportDifference = exportDifference;
	}

	public String getLossUnits() {
		return lossUnits;
	}

	public void setLossUnits(String lossUnits) {
		this.lossUnits = lossUnits;
	}

	public String getBulkMeterReading() {
		return bulkMeterReading;
	}

	public void setBulkMeterReading(String bulkMeterReading) {
		this.bulkMeterReading = bulkMeterReading;
	}

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public String getSubbulkreading() {
		return subbulkreading;
	}

	public void setSubbulkreading(String subbulkreading) {
		this.subbulkreading = subbulkreading;
	}

	@Override
	public String toString() {
		return "Vcompany [orgId=" + orgId + ", orgName=" + orgName + ", substationName=" + substationName
				+ ", substationId=" + substationId + ", feederId=" + feederId + ", feederName=" + feederName
				+ ", totalExportUnits=" + totalExportUnits + ", totalServices=" + totalServices + ", billedService="
				+ billedService + ", exportDifference=" + exportDifference + ", lossUnits=" + lossUnits
				+ ", bulkMeterReading=" + bulkMeterReading + ", meterNo=" + meterNo + ", subbulkreading="
				+ subbulkreading + "]";
	}

}
