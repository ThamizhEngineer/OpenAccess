package com.ss.oa.oadocumentservice.vo;

public class TempSrcp {
	private String instBy;
	private String ncesDivCode;
	private Long ttGenSum;
	private Long ttCapSum;
	private String month;
	private String year;
	private Integer ttWeg;
	private Integer tvlWeg;
	private Integer uduWeg;
	private Long tvlTotalCapacity;
	private Long uduTotalCapacity;
	private Long tvlTotalGeneration;
	private Long uduTotalGeneration;
	
	public TempSrcp() {
		super();
		}

	public TempSrcp(String instBy, String ncesDivCode, Long ttGenSum, Long ttCapSum, String month, String year,
			Integer ttWeg, Integer tvlWeg, Integer uduWeg, Long tvlTotalCapacity, Long uduTotalCapacity,
			Long tvlTotalGeneration, Long uduTotalGeneration) {
		super();
		this.instBy = instBy;
		this.ncesDivCode = ncesDivCode;
		this.ttGenSum = ttGenSum;
		this.ttCapSum = ttCapSum;
		this.month = month;
		this.year = year;
		this.ttWeg = ttWeg;
		this.tvlWeg = tvlWeg;
		this.uduWeg = uduWeg;
		this.tvlTotalCapacity = tvlTotalCapacity;
		this.uduTotalCapacity = uduTotalCapacity;
		this.tvlTotalGeneration = tvlTotalGeneration;
		this.uduTotalGeneration = uduTotalGeneration;
	}

	@Override
	public String toString() {
		return "TempSrcp [instBy=" + instBy + ", ncesDivCode=" + ncesDivCode + ", ttGenSum=" + ttGenSum + ", ttCapSum="
				+ ttCapSum + ", month=" + month + ", year=" + year + ", ttWeg=" + ttWeg + ", tvlWeg=" + tvlWeg
				+ ", uduWeg=" + uduWeg + ", tvlTotalCapacity=" + tvlTotalCapacity + ", uduTotalCapacity="
				+ uduTotalCapacity + ", tvlTotalGeneration=" + tvlTotalGeneration + ", uduTotalGeneration="
				+ uduTotalGeneration + "]";
	}

	public String getInstBy() {
		return instBy;
	}

	public void setInstBy(String instBy) {
		this.instBy = instBy;
	}

	public String getNcesDivCode() {
		return ncesDivCode;
	}

	public void setNcesDivCode(String ncesDivCode) {
		this.ncesDivCode = ncesDivCode;
	}

	public Long getTtGenSum() {
		return ttGenSum;
	}

	public void setTtGenSum(Long ttGenSum) {
		this.ttGenSum = ttGenSum;
	}

	public Long getTtCapSum() {
		return ttCapSum;
	}

	public void setTtCapSum(Long ttCapSum) {
		this.ttCapSum = ttCapSum;
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

	public Integer getTtWeg() {
		return ttWeg;
	}

	public void setTtWeg(Integer ttWeg) {
		this.ttWeg = ttWeg;
	}

	public Integer getTvlWeg() {
		return tvlWeg;
	}

	public void setTvlWeg(Integer tvlWeg) {
		this.tvlWeg = tvlWeg;
	}

	public Integer getUduWeg() {
		return uduWeg;
	}

	public void setUduWeg(Integer uduWeg) {
		this.uduWeg = uduWeg;
	}

	public Long getTvlTotalCapacity() {
		return tvlTotalCapacity;
	}

	public void setTvlTotalCapacity(Long tvlTotalCapacity) {
		this.tvlTotalCapacity = tvlTotalCapacity;
	}

	public Long getUduTotalCapacity() {
		return uduTotalCapacity;
	}

	public void setUduTotalCapacity(Long uduTotalCapacity) {
		this.uduTotalCapacity = uduTotalCapacity;
	}

	public Long getTvlTotalGeneration() {
		return tvlTotalGeneration;
	}

	public void setTvlTotalGeneration(Long tvlTotalGeneration) {
		this.tvlTotalGeneration = tvlTotalGeneration;
	}

	public Long getUduTotalGeneration() {
		return uduTotalGeneration;
	}

	public void setUduTotalGeneration(Long uduTotalGeneration) {
		this.uduTotalGeneration = uduTotalGeneration;
	}

	
	
}
