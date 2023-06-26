package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Transient;

@Table(name="CEE_SHARE_REPORT")
@Entity
public class CeeReport{
	@Id
	@Column(name="ROW_NO")
	private Integer id;

	private String totalCapacitySum;
	private String typeOfShare;
	private String installedBy;
	private String netGenerationSum;
	private String month;
	@Column(name="STMT_YEAR")
	private String year;
	private String fuelTypeCode;
	private String fuelTypeName;
	
	public CeeReport() {
		super();
	}

	public CeeReport(Integer id, String totalCapacitySum, String typeOfShare, String installedBy,
			String netGenerationSum, String month, String year, String fuelTypeCode, String fuelTypeName) {
		super();
		this.id = id;
		this.totalCapacitySum = totalCapacitySum;
		this.typeOfShare = typeOfShare;
		this.installedBy = installedBy;
		this.netGenerationSum = netGenerationSum;
		this.month = month;
		this.year = year;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
	}

	@Override
	public String toString() {
		return "CeeReport [id=" + id + ", totalCapacitySum=" + totalCapacitySum + ", typeOfShare=" + typeOfShare
				+ ", installedBy=" + installedBy + ", netGenerationSum=" + netGenerationSum + ", month=" + month
				+ ", year=" + year + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTotalCapacitySum() {
		return totalCapacitySum;
	}

	public void setTotalCapacitySum(String totalCapacitySum) {
		this.totalCapacitySum = totalCapacitySum;
	}

	public String getTypeOfShare() {
		return typeOfShare;
	}

	public void setTypeOfShare(String typeOfShare) {
		this.typeOfShare = typeOfShare;
	}

	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public String getNetGenerationSum() {
		return netGenerationSum;
	}

	public void setNetGenerationSum(String netGenerationSum) {
		this.netGenerationSum = netGenerationSum;
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

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	
	
}
