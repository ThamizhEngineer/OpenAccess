package com.ss.oa.transaction.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class EnergySaleUsageDetail {
	
	private String id;
	private String energySaleId;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber;
	private String buyerCompanyId,buyerCompanyName,buyerCompanyCode;
	private String usageDate;
	private String c1,c2,c3,c4,c5,total;
	
	public EnergySaleUsageDetail() {
		
	}

	public EnergySaleUsageDetail(String id, String energySaleId, String buyerCompanyServiceId,
			String buyerCompanyServiceNumber, String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode,
			String usageDate, String c1, String c2, String c3, String c4, String c5, String total) {
		super();
		this.id = id;
		this.energySaleId = energySaleId;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.usageDate = usageDate;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.total = total;
	}

	@Override
	public String toString() {
		return "EnergySaleUsageDetail [id=" + id + ", energySaleId=" + energySaleId + ", buyerCompanyServiceId="
				+ buyerCompanyServiceId + ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber
				+ ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyName=" + buyerCompanyName
				+ ", buyerCompanyCode=" + buyerCompanyCode + ", usageDate=" + usageDate + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", total=" + total + "]";
	}

	public String getId() {
		return id;
	}

	public String getEnergySaleId() {
		return energySaleId;
	}

	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}

	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}

	public String getUsageDate() {
		return usageDate;
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

	public String getTotal() {
		return total;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}

	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}

	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}

	public void setUsageDate(String usageDate) {
		this.usageDate = usageDate;
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

	public void setTotal(String total) {
		this.total = total;
	}

	

}
