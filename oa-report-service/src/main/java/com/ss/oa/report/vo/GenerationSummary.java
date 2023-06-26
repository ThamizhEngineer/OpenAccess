package com.ss.oa.report.vo;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Entity
@Table(name = "T_GEN_STMT")
@Getter
public class GenerationSummary implements Serializable{
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id", columnDefinition="VARCHAR2")
	private String id;
	@Column(name="M_ORG_ID")
	private String orgId;

	@Formula("(SELECT org.CODE FROM M_ORG org WHERE org.ID = M_ORG_ID)")
	@Column(name="CODE")
	private String orgCode;
	@Column(name="DISP_ORG_NAME")
	private String orgName;
	
	@Column(name="M_COMPANY_ID")
	private String companyId;
	@Column(name="DISP_COMPANY_NAME")
	private String companyName;
	@Column(name="M_COMPANY_SERVICE_ID")
	private String sellerServiceId;
	@Column(name="DISP_SERVICE_NUMBER")
	private String sellerServiceNumber;
	@Column(name="INJECTING_VOLTAGE_CODE")
	private String voltageCode;
	@Formula("(SELECT codes.VALUE_DESC FROM V_CODES codes WHERE codes.VALUE_CODE=INJECTING_VOLTAGE_CODE AND codes.LIST_NAME='Voltage')")
	@Column(name="VALUE_DESC")
	private String voltageName;
	@Column(name="DISP_FUEL_TYPE_CODE")
	private String fuelCode;
	@Column(name="DISP_FUEL_TYPE_NAME")
	private String fuelName;
	@Column(name="STMT_GEN_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate genReportDate;
	@Column(name="NET_GENERATION", columnDefinition="varchar2(50)" )
	private String netGeneration;
	@Column(name="STMT_MONTH")
	private String month;
	@Column(name="STMT_YEAR")
	private String year;
	@Transient
	private long totalUnits;
	@Column(name="FLOW_TYPE_CODE")
	private String flowTypeCode;
	@Column(name="IS_REC",columnDefinition = "char")
	private String isRec;
	@Column(name="TOTAL_IMPORT_GEN")
	private String totalImportGeneration;
	@Column(name="TOTAL_EXPORT_GEN")
	private String totalExportGeneration;
	@Column(name="PLANT_CLASS_TYPE_CODE")
	private String plantClassTypeCode;
	@Column(name="DISP_FUEL_TYPE_GROUP")
	private String dispFuelTypeGroup;
	@Formula("(SELECT codes.VALUE_DESC FROM V_CODES codes WHERE codes.VALUE_CODE=PLANT_CLASS_TYPE_CODE AND codes.LIST_NAME='Plant Class Type')")
	@Column(name="VALUE_DESC")
	private String plantClassTypeDesc;
	@Transient
	private long totalImportUnits;
	@Transient
	private long totalExportUnits;
	@Transient
	private long sumTotalImportUnits;
	@Transient
	private long sumTotalExportUnits;
	@Transient
	private long sumTotalUnits;
	@Column(name="MACHINE_CAPACITY")
	private String machineCapacity;
	@Transient
	private long totalMachineCapacity;
	@Transient
	private long sumMachineCapacity;
	@Transient
	private long recCount;
	@Transient
	private long SumserviceCount;

		
	public GenerationSummary() {
		super();
	}

	public GenerationSummary(String orgId,String orgName,long totalUnits, String month, String year,String flowTypeCode,String isRec,long totalImportUnits,long totalExportUnits,String plantClassTypeCode,String plantClassTypeDesc) {
		super();
		this.orgId = orgId;
		this.orgName=orgName;
		this.totalUnits = totalUnits;
		this.month=month;
		this.year=year;
		this.flowTypeCode=flowTypeCode;
		this.isRec=isRec;
		this.totalImportUnits=totalImportUnits;
		this.totalExportUnits=totalExportUnits;
		this.plantClassTypeCode=plantClassTypeCode;
		this.plantClassTypeDesc=plantClassTypeDesc;
	}
	
	
	public GenerationSummary(String orgId, String month, String year, long totalUnits, long totalImportUnits,
			long totalExportUnits) {
		super();
		this.orgId = orgId;
		this.month = month;
		this.year = year;
		this.totalUnits = totalUnits;
		this.totalImportUnits = totalImportUnits;
		this.totalExportUnits = totalExportUnits;
	}

	public GenerationSummary(String orgId,String orgName,long totalUnits, String month, String year,String flowTypeCode,String isRec,long totalImportUnits,long totalExportUnits,long totalMachineCapacity,String fuelCode,String fuelName, long recCount,String dispFuelTypeGroup) {
		super();
		this.orgId = orgId;
		this.orgName=orgName;
		this.totalUnits = totalUnits;
		this.month=month;
		this.year=year;
		this.flowTypeCode=flowTypeCode;
		this.isRec=isRec;
		this.totalImportUnits=totalImportUnits;
		this.totalExportUnits=totalExportUnits;
		this.totalMachineCapacity=totalMachineCapacity;
		this.fuelCode=fuelCode;
		this.fuelName=fuelName;
		this.recCount=recCount;
		this.dispFuelTypeGroup=dispFuelTypeGroup;
	}
	
	public GenerationSummary(String orgId,String orgName,long totalUnits, String month, String year,String flowTypeCode,String isRec) {
		super();
		this.orgId = orgId;
		this.orgName=orgName;
		this.totalUnits = totalUnits;
		this.month=month;
		this.year=year;
		this.flowTypeCode=flowTypeCode;
		this.isRec=isRec;
	}
	
	public GenerationSummary(String orgId,String orgName,String month,String year,long totalUnits) {
		super();
		this.orgId = orgId;
		this.orgName=orgName;
		this.month=month;
		this.year=year;
		this.totalUnits = totalUnits;
	}
	
	

	public GenerationSummary(String month, String year, long totalUnits, String flowTypeCode) {
		super();
		this.month = month;
		this.year = year;
		this.totalUnits = totalUnits;
		this.flowTypeCode = flowTypeCode;
	}
	
	

	public GenerationSummary(String month, String year, long totalUnits, String flowTypeCode, String isRec) {
		super();
		this.month = month;
		this.year = year;
		this.totalUnits = totalUnits;
		this.flowTypeCode = flowTypeCode;
		this.isRec = isRec;
	}

	public GenerationSummary(String orgId,String orgName,long totalUnits, String month, String year,String flowTypeCode,String isRec,long totalImportUnits,long totalExportUnits,long totalMachineCapacity,String fuelCode,String fuelName,String plantClassTypeCode,long recCount) {
		super();
		this.orgId = orgId;
		this.orgName=orgName;
		this.totalUnits = totalUnits;
		this.month=month;
		this.year=year;
		this.flowTypeCode=flowTypeCode;
		this.isRec=isRec;
		this.totalImportUnits=totalImportUnits;
		this.totalExportUnits=totalExportUnits;
		this.totalMachineCapacity=totalMachineCapacity;
		this.fuelCode=fuelCode;
		this.fuelName=fuelName;
		this.plantClassTypeCode = plantClassTypeCode;
		this.recCount=recCount;

	}

	

	
	

	

	public GenerationSummary(String id, String orgId, String orgCode, String orgName, String companyId,
			String companyName, String sellerServiceId, String sellerServiceNumber, String voltageCode,
			String voltageName, String fuelCode, String fuelName, LocalDate genReportDate, String netGeneration,
			String month, String year, long totalUnits, String flowTypeCode, String isRec, String totalImportGeneration,
			String totalExportGeneration, String plantClassTypeCode, String dispFuelTypeGroup,
			String plantClassTypeDesc, long totalImportUnits, long totalExportUnits, long sumTotalImportUnits,
			long sumTotalExportUnits, long sumTotalUnits, String machineCapacity, long totalMachineCapacity,
			long sumMachineCapacity, long recCount, long sumserviceCount) {
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
		this.dispFuelTypeGroup = dispFuelTypeGroup;
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
				+ plantClassTypeCode + ", dispFuelTypeGroup=" + dispFuelTypeGroup + ", plantClassTypeDesc="
				+ plantClassTypeDesc + ", totalImportUnits=" + totalImportUnits + ", totalExportUnits="
				+ totalExportUnits + ", sumTotalImportUnits=" + sumTotalImportUnits + ", sumTotalExportUnits="
				+ sumTotalExportUnits + ", sumTotalUnits=" + sumTotalUnits + ", machineCapacity=" + machineCapacity
				+ ", totalMachineCapacity=" + totalMachineCapacity + ", sumMachineCapacity=" + sumMachineCapacity
				+ ", recCount=" + recCount + ", SumserviceCount=" + SumserviceCount + "]";
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

	public String getDispFuelTypeGroup() {
		return dispFuelTypeGroup;
	}

	public void setDispFuelTypeGroup(String dispFuelTypeGroup) {
		this.dispFuelTypeGroup = dispFuelTypeGroup;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}
