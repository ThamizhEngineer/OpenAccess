package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="SRCP_REPORT")
@Entity
public class SrcpReport {
	@Id
	@Column(name="ROW_NO")
	private Integer id;
	private Long totalCapacitySum;
	private Long totalGenerationSum;
	private String ncesDivisionCode;
	private Integer noOfWeg;
	private String month;
	private String year;
	private String installedBy;
	
	public SrcpReport() {
		super();
	}

	public SrcpReport(Integer id, Long totalCapacitySum, Long totalGenerationSum, String ncesDivisionCode,
			Integer noOfWeg, String month, String year, String installedBy) {
		super();
		this.id = id;
		this.totalCapacitySum = totalCapacitySum;
		this.totalGenerationSum = totalGenerationSum;
		this.ncesDivisionCode = ncesDivisionCode;
		this.noOfWeg = noOfWeg;
		this.month = month;
		this.year = year;
		this.installedBy = installedBy;
	}

	@Override
	public String toString() {
		return "SrcpReport [id=" + id + ", totalCapacitySum=" + totalCapacitySum + ", totalGenerationSum="
				+ totalGenerationSum + ", ncesDivisionCode=" + ncesDivisionCode + ", noOfWeg=" + noOfWeg + ", month="
				+ month + ", year=" + year + ", installedBy=" + installedBy + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getTotalCapacitySum() {
		return totalCapacitySum;
	}

	public void setTotalCapacitySum(Long totalCapacitySum) {
		this.totalCapacitySum = totalCapacitySum;
	}

	public Long getTotalGenerationSum() {
		return totalGenerationSum;
	}

	public void setTotalGenerationSum(Long totalGenerationSum) {
		this.totalGenerationSum = totalGenerationSum;
	}

	public String getNcesDivisionCode() {
		return ncesDivisionCode;
	}

	public void setNcesDivisionCode(String ncesDivisionCode) {
		this.ncesDivisionCode = ncesDivisionCode;
	}

	public Integer getNoOfWeg() {
		return noOfWeg;
	}

	public void setNoOfWeg(Integer noOfWeg) {
		this.noOfWeg = noOfWeg;
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

	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	
	

}
