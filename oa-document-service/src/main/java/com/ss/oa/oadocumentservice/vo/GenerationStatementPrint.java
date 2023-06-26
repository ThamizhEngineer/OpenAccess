package com.ss.oa.oadocumentservice.vo;

public class GenerationStatementPrint {

	private String bankedBalanceC1,bankedBalanceC2,bankedBalanceC3,bankedBalanceC4,bankedBalanceC5;
	private String netc1,netc2,netc3,netc4,netc5;
	public GenerationStatementPrint() {
		super();
	}
	

	public GenerationStatementPrint(String bankedBalanceC1, String bankedBalanceC2, String bankedBalanceC3,
			String bankedBalanceC4, String bankedBalanceC5, String netc1, String netc2, String netc3, String netc4,
			String netc5) {
		super();
		this.bankedBalanceC1 = bankedBalanceC1;
		this.bankedBalanceC2 = bankedBalanceC2;
		this.bankedBalanceC3 = bankedBalanceC3;
		this.bankedBalanceC4 = bankedBalanceC4;
		this.bankedBalanceC5 = bankedBalanceC5;
		this.netc1 = netc1;
		this.netc2 = netc2;
		this.netc3 = netc3;
		this.netc4 = netc4;
		this.netc5 = netc5;
	}
	@Override
	public String toString() {
		return "GenerationStatementPrint [bankedBalanceC1=" + bankedBalanceC1 + ", bankedBalanceC2=" + bankedBalanceC2
				+ ", bankedBalanceC3=" + bankedBalanceC3 + ", bankedBalanceC4=" + bankedBalanceC4 + ", bankedBalanceC5="
				+ bankedBalanceC5 + ", netc1=" + netc1 + ", netc2=" + netc2 + ", netc3=" + netc3 + ", netc4=" + netc4
				+ ", netc5=" + netc5 + ", getBankedBalanceC1()=" + getBankedBalanceC1() + ", getBankedBalanceC2()="
				+ getBankedBalanceC2() + ", getBankedBalanceC3()=" + getBankedBalanceC3() + ", getBankedBalanceC4()="
				+ getBankedBalanceC4() + ", getBankedBalanceC5()=" + getBankedBalanceC5() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getBankedBalanceC1() {
		return bankedBalanceC1;
	}

	public void setBankedBalanceC1(String bankedBalanceC1) {
		this.bankedBalanceC1 = bankedBalanceC1;
	}

	public String getBankedBalanceC2() {
		return bankedBalanceC2;
	}

	public void setBankedBalanceC2(String bankedBalanceC2) {
		this.bankedBalanceC2 = bankedBalanceC2;
	}

	public String getBankedBalanceC3() {
		return bankedBalanceC3;
	}

	public void setBankedBalanceC3(String bankedBalanceC3) {
		this.bankedBalanceC3 = bankedBalanceC3;
	}

	public String getBankedBalanceC4() {
		return bankedBalanceC4;
	}

	public void setBankedBalanceC4(String bankedBalanceC4) {
		this.bankedBalanceC4 = bankedBalanceC4;
	}

	public String getBankedBalanceC5() {
		return bankedBalanceC5;
	}

	public void setBankedBalanceC5(String bankedBalanceC5) {
		this.bankedBalanceC5 = bankedBalanceC5;
	}


	public String getNetc1() {
		return netc1;
	}


	public void setNetc1(String netc1) {
		this.netc1 = netc1;
	}


	public String getNetc2() {
		return netc2;
	}


	public void setNetc2(String netc2) {
		this.netc2 = netc2;
	}


	public String getNetc3() {
		return netc3;
	}


	public void setNetc3(String netc3) {
		this.netc3 = netc3;
	}


	public String getNetc4() {
		return netc4;
	}


	public void setNetc4(String netc4) {
		this.netc4 = netc4;
	}


	public String getNetc5() {
		return netc5;
	}


	public void setNetc5(String netc5) {
		this.netc5 = netc5;
	}
	
	

}
