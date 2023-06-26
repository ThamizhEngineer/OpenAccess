package com.ss.oa.transaction.vo;

public class GenerationStatementSummary {

	private String heading,rKvah,kvah,totalImport,totalExport; 
	
	public GenerationStatementSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerationStatementSummary(String heading, String rKvah, String kvah, String totalImport,
			String totalExport) {
		super();
		this.heading = heading;
		this.rKvah = rKvah;
		this.kvah = kvah;
		this.totalImport = totalImport;
		this.totalExport = totalExport;
	}

	@Override
	public String toString() {
		return "GenerationStatementSummary [heading=" + heading + ", rKvah=" + rKvah + ", kvah=" + kvah
				+ ", totalImport=" + totalImport + ", totalExport=" + totalExport + "]";
	}

	public String getHeading() {
		return heading;
	}

	public String getrKvah() {
		return rKvah;
	}

	public String getKvah() {
		return kvah;
	}

	public String getTotalImport() {
		return totalImport;
	}

	public String getTotalExport() {
		return totalExport;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void setrKvah(String rKvah) {
		this.rKvah = rKvah;
	}

	public void setKvah(String kvah) {
		this.kvah = kvah;
	}

	public void setTotalImport(String totalImport) {
		this.totalImport = totalImport;
	}

	public void setTotalExport(String totalExport) {
		this.totalExport = totalExport;
	}
	
	

}
