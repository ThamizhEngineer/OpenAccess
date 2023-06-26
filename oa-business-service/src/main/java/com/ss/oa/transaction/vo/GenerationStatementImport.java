package com.ss.oa.transaction.vo;

public class GenerationStatementImport {

	
	private String heading,c1,c2,c3,c4,c5; 
	
	public GenerationStatementImport() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public GenerationStatementImport(String heading, String c1, String c2, String c3, String c4, String c5) {
		super();
		this.heading = heading;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
	}

	@Override
	public String toString() {
		return "GenerationStatementImport [heading=" + heading + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4="
				+ c4 + ", c5=" + c5 + "]";
	}

	public String getHeading() {
		return heading;
	}

	public String getC1() {
		return c1;
	}

	public String getC2() {
		return c2;
	}

	public String getC3() {
		return c3;
	}

	public String getC4() {
		return c4;
	}

	public String getC5() {
		return c5;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}

	public void setC3(String c3) {
		this.c3 = c3;
	}

	public void setC4(String c4) {
		this.c4 = c4;
	}

	public void setC5(String c5) {
		this.c5 = c5;
	}
	
	

}
