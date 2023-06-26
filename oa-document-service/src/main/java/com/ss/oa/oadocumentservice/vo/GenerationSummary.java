package com.ss.oa.oadocumentservice.vo;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GenerationSummary{

	private String id;
	private String orgId;
	private String orgCode;
	private String orgName;
	private String companyId;
	private String companyName;
	private String sellerServiceId;
	private String sellerServiceNumber;
	private String voltageCode;
	private String voltageName;
	private String fuelCode;
	private String fuelName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate genReportDate;
	private String netGeneration;
	private String month;
	private String year;
	private long totalUnits;
	private String flowTypeCode;
	private String isRec;
	private String totalImportGeneration;
	private String totalExportGeneration;
	private String plantClassTypeCode;
	private String plantClassTypeDesc;
	private long totalImportUnits;
	private long totalExportUnits;
	private long sumTotalImportUnits;
	private long sumTotalExportUnits;
	private long sumTotalUnits;
	private String machineCapacity;
	private long totalMachineCapacity;
	private long sumMachineCapacity;
	private long recCount;
	private long SumserviceCount;
	private String rec;
	
	public GenerationSummary() {
		super();
	}

	public GenerationSummary(String id, String orgId, String orgCode, String orgName, String companyId,
			String companyName, String sellerServiceId, String sellerServiceNumber, String voltageCode,
			String voltageName, String fuelCode, String fuelName, LocalDate genReportDate, String netGeneration,
			String month, String year, long totalUnits, String flowTypeCode, String isRec, String totalImportGeneration,
			String totalExportGeneration, String plantClassTypeCode, String plantClassTypeDesc, long totalImportUnits,
			long totalExportUnits, long sumTotalImportUnits, long sumTotalExportUnits, long sumTotalUnits,
			String machineCapacity, long totalMachineCapacity, long sumMachineCapacity, long recCount,
			long sumserviceCount, String rec) {
		super();
		this.id = id;
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.companyId = companyId;
		this.companyName = companyName;
		this.sellerServiceId = sellerServiceId;
		this.sellerServiceNumber = sellerServiceNumber;
		this.voltageCode = voltageCode;
		this.voltageName = voltageName;
		this.fuelCode = fuelCode;
		this.fuelName = fuelName;
		this.genReportDate = genReportDate;
		this.netGeneration = netGeneration;
		this.month = month;
		this.year = year;
		this.totalUnits = totalUnits;
		this.flowTypeCode = flowTypeCode;
		this.isRec = isRec;
		this.totalImportGeneration = totalImportGeneration;
		this.totalExportGeneration = totalExportGeneration;
		this.plantClassTypeCode = plantClassTypeCode;
		this.plantClassTypeDesc = plantClassTypeDesc;
		this.totalImportUnits = totalImportUnits;
		this.totalExportUnits = totalExportUnits;
		this.sumTotalImportUnits = sumTotalImportUnits;
		this.sumTotalExportUnits = sumTotalExportUnits;
		this.sumTotalUnits = sumTotalUnits;
		this.machineCapacity = machineCapacity;
		this.totalMachineCapacity = totalMachineCapacity;
		this.sumMachineCapacity = sumMachineCapacity;
		this.recCount = recCount;
		SumserviceCount = sumserviceCount;
		this.rec = rec;
	}

	@Override
	public String toString() {
		return "GenerationSummary [id=" + id + ", orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName
				+ ", companyId=" + companyId + ", companyName=" + companyName + ", sellerServiceId=" + sellerServiceId
				+ ", sellerServiceNumber=" + sellerServiceNumber + ", voltageCode=" + voltageCode + ", voltageName="
				+ voltageName + ", fuelCode=" + fuelCode + ", fuelName=" + fuelName + ", genReportDate=" + genReportDate
				+ ", netGeneration=" + netGeneration + ", month=" + month + ", year=" + year + ", totalUnits="
				+ totalUnits + ", flowTypeCode=" + flowTypeCode + ", isRec=" + isRec + ", totalImportGeneration="
				+ totalImportGeneration + ", totalExportGeneration=" + totalExportGeneration + ", plantClassTypeCode="
				+ plantClassTypeCode + ", plantClassTypeDesc=" + plantClassTypeDesc + ", totalImportUnits="
				+ totalImportUnits + ", totalExportUnits=" + totalExportUnits + ", sumTotalImportUnits="
				+ sumTotalImportUnits + ", sumTotalExportUnits=" + sumTotalExportUnits + ", sumTotalUnits="
				+ sumTotalUnits + ", machineCapacity=" + machineCapacity + ", totalMachineCapacity="
				+ totalMachineCapacity + ", sumMachineCapacity=" + sumMachineCapacity + ", recCount=" + recCount
				+ ", SumserviceCount=" + SumserviceCount + ", rec=" + rec + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSellerServiceId() {
		return sellerServiceId;
	}

	public void setSellerServiceId(String sellerServiceId) {
		this.sellerServiceId = sellerServiceId;
	}

	public String getSellerServiceNumber() {
		return sellerServiceNumber;
	}

	public void setSellerServiceNumber(String sellerServiceNumber) {
		this.sellerServiceNumber = sellerServiceNumber;
	}

	public String getVoltageCode() {
		return voltageCode;
	}

	public void setVoltageCode(String voltageCode) {
		this.voltageCode = voltageCode;
	}

	public String getVoltageName() {
		return voltageName;
	}

	public void setVoltageName(String voltageName) {
		this.voltageName = voltageName;
	}

	public String getFuelCode() {
		return fuelCode;
	}

	public void setFuelCode(String fuelCode) {
		this.fuelCode = fuelCode;
	}

	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}

	public LocalDate getGenReportDate() {
		return genReportDate;
	}

	public void setGenReportDate(LocalDate genReportDate) {
		this.genReportDate = genReportDate;
	}

	public String getNetGeneration() {
		return netGeneration;
	}

	public void setNetGeneration(String netGeneration) {
		this.netGeneration = netGeneration;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public long getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(long totalUnits) {
		this.totalUnits = totalUnits;
	}

	public String getFlowTypeCode() {
		return flowTypeCode;
	}

	public void setFlowTypeCode(String flowTypeCode) {
		this.flowTypeCode = flowTypeCode;
	}

	public String getIsRec() {
		return isRec;
	}

	public void setIsRec(String isRec) {
		this.isRec = isRec;
	}

	public String getTotalImportGeneration() {
		return totalImportGeneration;
	}

	public void setTotalImportGeneration(String totalImportGeneration) {
		this.totalImportGeneration = totalImportGeneration;
	}

	public String getTotalExportGeneration() {
		return totalExportGeneration;
	}

	public void setTotalExportGeneration(String totalExportGeneration) {
		this.totalExportGeneration = totalExportGeneration;
	}

	public String getPlantClassTypeCode() {
		return plantClassTypeCode;
	}

	public void setPlantClassTypeCode(String plantClassTypeCode) {
		this.plantClassTypeCode = plantClassTypeCode;
	}

	public String getPlantClassTypeDesc() {
		return plantClassTypeDesc;
	}

	public void setPlantClassTypeDesc(String plantClassTypeDesc) {
		this.plantClassTypeDesc = plantClassTypeDesc;
	}

	public long getTotalImportUnits() {
		return totalImportUnits;
	}

	public void setTotalImportUnits(long totalImportUnits) {
		this.totalImportUnits = totalImportUnits;
	}

	public long getTotalExportUnits() {
		return totalExportUnits;
	}

	public void setTotalExportUnits(long totalExportUnits) {
		this.totalExportUnits = totalExportUnits;
	}

	public long getSumTotalImportUnits() {
		return sumTotalImportUnits;
	}

	public void setSumTotalImportUnits(long sumTotalImportUnits) {
		this.sumTotalImportUnits = sumTotalImportUnits;
	}

	public long getSumTotalExportUnits() {
		return sumTotalExportUnits;
	}

	public void setSumTotalExportUnits(long sumTotalExportUnits) {
		this.sumTotalExportUnits = sumTotalExportUnits;
	}

	public long getSumTotalUnits() {
		return sumTotalUnits;
	}

	public void setSumTotalUnits(long sumTotalUnits) {
		this.sumTotalUnits = sumTotalUnits;
	}

	public String getMachineCapacity() {
		return machineCapacity;
	}

	public void setMachineCapacity(String machineCapacity) {
		this.machineCapacity = machineCapacity;
	}

	public long getTotalMachineCapacity() {
		return totalMachineCapacity;
	}

	public void setTotalMachineCapacity(long totalMachineCapacity) {
		this.totalMachineCapacity = totalMachineCapacity;
	}

	public long getSumMachineCapacity() {
		return sumMachineCapacity;
	}

	public void setSumMachineCapacity(long sumMachineCapacity) {
		this.sumMachineCapacity = sumMachineCapacity;
	}

	public long getRecCount() {
		return recCount;
	}

	public void setRecCount(long recCount) {
		this.recCount = recCount;
	}

	public long getSumserviceCount() {
		return SumserviceCount;
	}

	public void setSumserviceCount(long sumserviceCount) {
		SumserviceCount = sumserviceCount;
	}

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}


}
