package com.ss.oa.transaction.solarLineLoss;

public class FeederLineLoss {
	
	private String feederName;
	private String feederVoltage;
	private String serviceNumber;
	private String companyName;
	private String companyMeterNo;
	private String exportUnits;
	private String feederLineLength;
	private String feederEndMeterNo;
	private String feederEnd;
	
	public FeederLineLoss() {
		super();
	}	
	
	public FeederLineLoss(String feederName, String feederVoltage, String serviceNumber, String companyName,
			String exportUnits, String feederLineLength, String feederEndMeterNo, String feederEnd) {
		super();
		this.feederName = feederName;
		this.feederVoltage = feederVoltage;
		this.serviceNumber = serviceNumber;
		this.companyName = companyName;
		this.exportUnits = exportUnits;
		this.feederLineLength = feederLineLength;
		this.feederEndMeterNo = feederEndMeterNo;
		this.feederEnd = feederEnd;
	}

	public String getCompanyMeterNo() {
		return companyMeterNo;
	}

	public void setCompanyMeterNo(String companyMeterNo) {
		this.companyMeterNo = companyMeterNo;
	}

	public String getFeederName() {
		return feederName;
	}
	public void setFeederName(String feederName) {
		this.feederName = feederName;
	}
	public String getFeederVoltage() {
		return feederVoltage;
	}
	public void setFeederVoltage(String feederVoltage) {
		this.feederVoltage = feederVoltage;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExportUnits() {
		return exportUnits;
	}
	public void setExportUnits(String exportUnits) {
		this.exportUnits = exportUnits;
	}
	public String getFeederLineLength() {
		return feederLineLength;
	}
	public void setFeederLineLength(String feederLineLength) {
		this.feederLineLength = feederLineLength;
	}
	public String getFeederEndMeterNo() {
		return feederEndMeterNo;
	}
	public void setFeederEndMeterNo(String feederEndMeterNo) {
		this.feederEndMeterNo = feederEndMeterNo;
	}
	public String getFeederEnd() {
		return feederEnd;
	}
	public void setFeederEnd(String feederEnd) {
		this.feederEnd = feederEnd;
	}
}
