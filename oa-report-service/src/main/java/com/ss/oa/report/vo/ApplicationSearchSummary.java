package com.ss.oa.report.vo;

public class ApplicationSearchSummary  implements Comparable<ApplicationSearchSummary> {

	private String saleTypeCode;
	private String statusCode;
	private Long recCount;
	private Long orderCount;
	public ApplicationSearchSummary(String saleTypeCode, String statusCode, Long recCount) {
		super();
		this.saleTypeCode = saleTypeCode;
		this.statusCode = statusCode;
		this.recCount = recCount;

	}
	@Override
	public String toString() {
		return "ApplicationSearchSummary [saleTypeCode=" + saleTypeCode + ", statusCode=" + statusCode + ", recCount="
				+ recCount + ", orderCount=" + orderCount + "]";
	}
	public String getSaleTypeCode() {
		return saleTypeCode;
	}
	public void setSaleTypeCode(String saleTypeCode) {
		this.saleTypeCode = saleTypeCode;
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
	public Long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Long orderCount) {
		this.orderCount = orderCount;
	}
	
	public int compareTo(ApplicationSearchSummary applicationSearchSummaries) {
		
		Long compareQuantity = ((ApplicationSearchSummary) applicationSearchSummaries).getOrderCount(); 
		
		//ascending order
		return (int) (this.orderCount - compareQuantity);
		
		//descending order
		//return compareQuantity - this.quantity;
		
	}	
}
