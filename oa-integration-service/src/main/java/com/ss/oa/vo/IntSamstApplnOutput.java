package com.ss.oa.vo;

public class IntSamstApplnOutput {
	
	private String noOfRows;
	private String batchKey;
	private String importRemarks;

	public IntSamstApplnOutput() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IntSamstApplnOutput(String noOfRows, String batchKey, String importRemarks) {
		super();
		this.noOfRows = noOfRows;
		this.batchKey = batchKey;
		this.importRemarks = importRemarks;
	}

	public String getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(String noOfRows) {
		this.noOfRows = noOfRows;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}

	public String getImportRemarks() {
		return importRemarks;
	}

	public void setImportRemarks(String importRemarks) {
		this.importRemarks = importRemarks;
	}

	@Override
	public String toString() {
		return "IntSamstApplnOutput [noOfRows=" + noOfRows + ", batchKey=" + batchKey + ", importRemarks="
				+ importRemarks + "]";
	}
	
	
	
	

}
