package com.ss.oa.oadocumentservice.vo;



import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class CeeReport {
	@Id
	@Column(name="ROW_NO")
	private Integer id;
	private String totalCapacitySum;
	private String typeOfShare;
	private String installedBy;
	private String netGenerationSum;
	private String month;
	private String year;
	private String fuelTypeCode;
	private String fuelTypeName;
	@Transient
	private String psTotalCapacitySum;
	private String csTotalCapacitySum;
	private String ssTotalCapacitySum;
	private String psNetGenerationSum;
	private String csNetGenerationSum;
	private String ssNetGenerationSum;
	
	public CeeReport() {
		super();
		}

	public CeeReport(Integer id, String totalCapacitySum, String typeOfShare, String installedBy,
			String netGenerationSum, String month, String year, String fuelTypeCode, String fuelTypeName,
			String psTotalCapacitySum, String csTotalCapacitySum, String ssTotalCapacitySum, String psNetGenerationSum,
			String csNetGenerationSum, String ssNetGenerationSum) {
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
		this.psTotalCapacitySum = psTotalCapacitySum;
		this.csTotalCapacitySum = csTotalCapacitySum;
		this.ssTotalCapacitySum = ssTotalCapacitySum;
		this.psNetGenerationSum = psNetGenerationSum;
		this.csNetGenerationSum = csNetGenerationSum;
		this.ssNetGenerationSum = ssNetGenerationSum;
	}

	@Override
	public String toString() {
		return "CeeReport [id=" + id + ", totalCapacitySum=" + totalCapacitySum + ", typeOfShare=" + typeOfShare
				+ ", installedBy=" + installedBy + ", netGenerationSum=" + netGenerationSum + ", month=" + month
				+ ", year=" + year + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName
				+ ", psTotalCapacitySum=" + psTotalCapacitySum + ", csTotalCapacitySum=" + csTotalCapacitySum
				+ ", ssTotalCapacitySum=" + ssTotalCapacitySum + ", psNetGenerationSum=" + psNetGenerationSum
				+ ", csNetGenerationSum=" + csNetGenerationSum + ", ssNetGenerationSum=" + ssNetGenerationSum + "]";
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

	public String getPsTotalCapacitySum() {
		return psTotalCapacitySum;
	}

	public void setPsTotalCapacitySum(String psTotalCapacitySum) {
		this.psTotalCapacitySum = psTotalCapacitySum;
	}

	public String getCsTotalCapacitySum() {
		return csTotalCapacitySum;
	}

	public void setCsTotalCapacitySum(String csTotalCapacitySum) {
		this.csTotalCapacitySum = csTotalCapacitySum;
	}

	public String getSsTotalCapacitySum() {
		return ssTotalCapacitySum;
	}

	public void setSsTotalCapacitySum(String ssTotalCapacitySum) {
		this.ssTotalCapacitySum = ssTotalCapacitySum;
	}

	public String getPsNetGenerationSum() {
		return psNetGenerationSum;
	}

	public void setPsNetGenerationSum(String psNetGenerationSum) {
		this.psNetGenerationSum = psNetGenerationSum;
	}

	public String getCsNetGenerationSum() {
		return csNetGenerationSum;
	}

	public void setCsNetGenerationSum(String csNetGenerationSum) {
		this.csNetGenerationSum = csNetGenerationSum;
	}

	public String getSsNetGenerationSum() {
		return ssNetGenerationSum;
	}

	public void setSsNetGenerationSum(String ssNetGenerationSum) {
		this.ssNetGenerationSum = ssNetGenerationSum;
	}

		
}
