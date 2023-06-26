package com.ss.oa.report.vo;

public class ApplicationOaaSearch {
	private String agmtPeriodCode;
	private String statusCode;
	private Long recCount;
	public ApplicationOaaSearch(String agmtPeriodCode, String statusCode, Long recCount) {
		super();
		this.agmtPeriodCode = agmtPeriodCode;
		this.statusCode = statusCode;
		this.recCount = recCount;
	}
	@Override
	public String toString() {
		return "ApplicationOaaSearch [agmtPeriodCode=" + agmtPeriodCode + ", statusCode=" + statusCode + ", recCount="
				+ recCount + "]";
	}
	public String getAgmtPeriodCode() {
		return agmtPeriodCode;
	}
	public void setAgmtPeriodCode(String agmtPeriodCode) {
		this.agmtPeriodCode = agmtPeriodCode;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Long getRecCount() {
		return recCount;
	}
	public void setRecCount(Long recCount) {
		this.recCount = recCount;
	}

	
}
