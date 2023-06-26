package com.ss.oa.oadocumentservice.vo;

import java.sql.Date;

public class NilGenerationReport {
	
	private String  dispCompanyName;
	private String  dispOrgName ;
	private String  dispServiceNumber ;
	private String  netGeneration ;
	private Date    stmtGenDate ;
	private String  stmtMonth ;
	private String  stmtYear;
	private String  schedulingCharges ;
	private String  systemOperationCharges ;
	private String  transmissionCharges ;
	private String  meterReadingCharges ;
	private String  negativeEnergyCharges ;
	private String 	omCharges ;
	private String 	rkvahPenalty; 
	private String 	totalExportGen;
	private String 	totalImportGen ;
	
	
	public NilGenerationReport() {
		super();
		// TODO Auto-generated constructor stub
	}


	public NilGenerationReport(String dispCompanyName, String dispOrgName, String dispServiceNumber,
			String netGeneration, Date stmtGenDate, String stmtMonth, String stmtYear, String schedulingCharges,
			String systemOperationCharges, String transmissionCharges, String meterReadingCharges,
			String negativeEnergyCharges, String omCharges, String rkvahPenalty, String totalExportGen,
			String totalImportGen) {
		super();
		this.dispCompanyName = dispCompanyName;
		this.dispOrgName = dispOrgName;
		this.dispServiceNumber = dispServiceNumber;
		this.netGeneration = netGeneration;
		this.stmtGenDate = stmtGenDate;
		this.stmtMonth = stmtMonth;
		this.stmtYear = stmtYear;
		this.schedulingCharges = schedulingCharges;
		this.systemOperationCharges = systemOperationCharges;
		this.transmissionCharges = transmissionCharges;
		this.meterReadingCharges = meterReadingCharges;
		this.negativeEnergyCharges = negativeEnergyCharges;
		this.omCharges = omCharges;
		this.rkvahPenalty = rkvahPenalty;
		this.totalExportGen = totalExportGen;
		this.totalImportGen = totalImportGen;
	}


	@Override
	public String toString() {
		return "NilGenerationReport [dispCompanyName=" + dispCompanyName + ", dispOrgName=" + dispOrgName
				+ ", dispServiceNumber=" + dispServiceNumber + ", netGeneration=" + netGeneration + ", stmtGenDate="
				+ stmtGenDate + ", stmtMonth=" + stmtMonth + ", stmtYear=" + stmtYear + ", schedulingCharges="
				+ schedulingCharges + ", systemOperationCharges=" + systemOperationCharges + ", transmissionCharges="
				+ transmissionCharges + ", meterReadingCharges=" + meterReadingCharges + ", negativeEnergyCharges="
				+ negativeEnergyCharges + ", omCharges=" + omCharges + ", rkvahPenalty=" + rkvahPenalty
				+ ", totalExportGen=" + totalExportGen + ", totalImportGen=" + totalImportGen + "]";
	}


	public String getDispCompanyName() {
		return dispCompanyName;
	}


	public void setDispCompanyName(String dispCompanyName) {
		this.dispCompanyName = dispCompanyName;
	}


	public String getDispOrgName() {
		return dispOrgName;
	}


	public void setDispOrgName(String dispOrgName) {
		this.dispOrgName = dispOrgName;
	}


	public String getDispServiceNumber() {
		return dispServiceNumber;
	}


	public void setDispServiceNumber(String dispServiceNumber) {
		this.dispServiceNumber = dispServiceNumber;
	}


	public String getNetGeneration() {
		return netGeneration;
	}


	public void setNetGeneration(String netGeneration) {
		this.netGeneration = netGeneration;
	}


	public Date getStmtGenDate() {
		return stmtGenDate;
	}


	public void setStmtGenDate(Date stmtGenDate) {
		this.stmtGenDate = stmtGenDate;
	}


	public String getStmtMonth() {
		return stmtMonth;
	}


	public void setStmtMonth(String stmtMonth) {
		this.stmtMonth = stmtMonth;
	}


	public String getStmtYear() {
		return stmtYear;
	}


	public void setStmtYear(String stmtYear) {
		this.stmtYear = stmtYear;
	}


	public String getSchedulingCharges() {
		return schedulingCharges;
	}


	public void setSchedulingCharges(String schedulingCharges) {
		this.schedulingCharges = schedulingCharges;
	}


	public String getSystemOperationCharges() {
		return systemOperationCharges;
	}


	public void setSystemOperationCharges(String systemOperationCharges) {
		this.systemOperationCharges = systemOperationCharges;
	}


	public String getTransmissionCharges() {
		return transmissionCharges;
	}


	public void setTransmissionCharges(String transmissionCharges) {
		this.transmissionCharges = transmissionCharges;
	}


	public String getMeterReadingCharges() {
		return meterReadingCharges;
	}


	public void setMeterReadingCharges(String meterReadingCharges) {
		this.meterReadingCharges = meterReadingCharges;
	}


	public String getNegativeEnergyCharges() {
		return negativeEnergyCharges;
	}


	public void setNegativeEnergyCharges(String negativeEnergyCharges) {
		this.negativeEnergyCharges = negativeEnergyCharges;
	}


	public String getOmCharges() {
		return omCharges;
	}


	public void setOmCharges(String omCharges) {
		this.omCharges = omCharges;
	}


	public String getRkvahPenalty() {
		return rkvahPenalty;
	}


	public void setRkvahPenalty(String rkvahPenalty) {
		this.rkvahPenalty = rkvahPenalty;
	}


	public String getTotalExportGen() {
		return totalExportGen;
	}


	public void setTotalExportGen(String totalExportGen) {
		this.totalExportGen = totalExportGen;
	}


	public String getTotalImportGen() {
		return totalImportGen;
	}


	public void setTotalImportGen(String totalImportGen) {
		this.totalImportGen = totalImportGen;
	}


	 
	
}
