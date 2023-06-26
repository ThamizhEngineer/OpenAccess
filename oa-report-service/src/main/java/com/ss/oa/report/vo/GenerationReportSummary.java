package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="R_GEN_SUMMARY")
@Entity
public class GenerationReportSummary {
	  
	  @Id
	  @Column(name="ID")
	  private int id;
	  @Column(name="REP_TYPE")
	  private String repType;
	  @Column(name="ORG_NAME")
	  private String orgName;
	  @Column(name="MONTH")
	  private String month;
	  @Column(name="YEAR")
	  private String year;
	  @Column(name="TOTAL_SELLER_COUNT")
	  private String totalSellerCount;
	  @Column(name="BILLED_SELLER_COUNT")
	  private String billedSellerCount;
	  @Column(name="CAPTIVE_TPP_COUNT")
	  private String captiveTppCount;
	  @Column(name="TOTAL_COUNT")
	  private String totalCount;
	
	  public GenerationReportSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerationReportSummary(int id, String repType, String orgName, String month, String year,
			String totalSellerCount, String billedSellerCount, String captiveTppCount, String totalCount) {
		super();
		this.id = id;
		this.repType = repType;
		this.orgName = orgName;
		this.month = month;
		this.year = year;
		this.totalSellerCount = totalSellerCount;
		this.billedSellerCount = billedSellerCount;
		this.captiveTppCount = captiveTppCount;
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "GenerationReportSummary [id=" + id + ", repType=" + repType + ", orgName=" + orgName + ", month="
				+ month + ", year=" + year + ", totalSellerCount=" + totalSellerCount + ", billedSellerCount="
				+ billedSellerCount + ", captiveTppCount=" + captiveTppCount + ", totalCount=" + totalCount + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepType() {
		return repType;
	}

	public void setRepType(String repType) {
		this.repType = repType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getTotalSellerCount() {
		return totalSellerCount;
	}

	public void setTotalSellerCount(String totalSellerCount) {
		this.totalSellerCount = totalSellerCount;
	}

	public String getBilledSellerCount() {
		return billedSellerCount;
	}

	public void setBilledSellerCount(String billedSellerCount) {
		this.billedSellerCount = billedSellerCount;
	}

	public String getCaptiveTppCount() {
		return captiveTppCount;
	}

	public void setCaptiveTppCount(String captiveTppCount) {
		this.captiveTppCount = captiveTppCount;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	
	    

}
