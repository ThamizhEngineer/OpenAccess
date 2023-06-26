package com.ss.oa.transaction.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "T_ES_USAGE_DETAIL")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class EsUsageDetail {
	
	  @Id
	  @Column(name="ID")
	  private String id;
	  @Column(name="T_ENERGY_SALE_ID")
	  private String energySaleId;
	  @Column(name="BUYER_COMP_SERV_ID")
	  private String buyerCompanyServiceId;
	  @Formula("(SELECT ms.M_COMP_SERV_NUMBER FROM T_ES_USAGE_DETAIL us JOIN V_COMPANY_SERVICE ms on ms.ID=us.BUYER_COMP_SERV_ID WHERE us.ID=id)")
	  private String buyerCompanyServiceNumber;
	  @Formula("(SELECT co.id FROM T_ES_USAGE_DETAIL us JOIN V_COMPANY_SERVICE ms on ms.ID=us.BUYER_COMP_SERV_ID \n" +
			  "JOIN M_COMPANY co ON ms.M_COMPANY_ID = co.id WHERE us.ID=id)")
	  private String buyerCompanyId;
	  @Formula("(SELECT co.name FROM T_ES_USAGE_DETAIL us JOIN V_COMPANY_SERVICE ms on ms.ID=us.BUYER_COMP_SERV_ID \n" +
			  "JOIN M_COMPANY co ON ms.M_COMPANY_ID = co.id WHERE us.ID=id)")
	  private String buyerCompanyName;
	  @Formula("(SELECT co.CODE FROM T_ES_USAGE_DETAIL us JOIN V_COMPANY_SERVICE ms on ms.ID=us.BUYER_COMP_SERV_ID \n" +
			  "JOIN M_COMPANY co ON ms.M_COMPANY_ID = co.id WHERE us.ID=id)")
	  private String buyerCompanyCode;
	  @Column(name="USAGE_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	  private LocalDateTime usageDate;
	  @Column(name="C1")
	  private String c1;
	  @Column(name="C2")
	  private String c2;
	  @Column(name="C3")
	  private String c3;
	  @Column(name="C4")
	  private String c4;
	  @Column(name="C5")
	  private String c5;
	  @Column(name="TOTAL")
	  private String total;
	  
	  
	public EsUsageDetail() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "EsUsageDetail [id=" + id + ", energySaleId=" + energySaleId + ", buyerCompanyServiceId="
				+ buyerCompanyServiceId + ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber
				+ ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyName=" + buyerCompanyName
				+ ", buyerCompanyCode=" + buyerCompanyCode + ", usageDate=" + usageDate + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", total=" + total + "]";
	}


	public EsUsageDetail(String id, String energySaleId, String buyerCompanyServiceId, String buyerCompanyServiceNumber,
			String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode, LocalDateTime usageDate, String c1,
			String c2, String c3, String c4, String c5, String total) {
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEnergySaleId() {
		return energySaleId;
	}


	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}


	public String getBuyerCompanyServiceId() {
		return buyerCompanyServiceId;
	}


	public void setBuyerCompanyServiceId(String buyerCompanyServiceId) {
		this.buyerCompanyServiceId = buyerCompanyServiceId;
	}


	public String getBuyerCompanyServiceNumber() {
		return buyerCompanyServiceNumber;
	}


	public void setBuyerCompanyServiceNumber(String buyerCompanyServiceNumber) {
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
	}


	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}


	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}


	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}


	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}


	public String getBuyerCompanyCode() {
		return buyerCompanyCode;
	}


	public void setBuyerCompanyCode(String buyerCompanyCode) {
		this.buyerCompanyCode = buyerCompanyCode;
	}


	public LocalDateTime getUsageDate() {
		return usageDate;
	}


	public void setUsageDate(LocalDateTime usageDate) {
		this.usageDate = usageDate;
	}


	public String getC1() {
		return c1;
	}


	public void setC1(String c1) {
		this.c1 = c1;
	}


	public String getC2() {
		return c2;
	}


	public void setC2(String c2) {
		this.c2 = c2;
	}


	public String getC3() {
		return c3;
	}


	public void setC3(String c3) {
		this.c3 = c3;
	}


	public String getC4() {
		return c4;
	}


	public void setC4(String c4) {
		this.c4 = c4;
	}


	public String getC5() {
		return c5;
	}


	public void setC5(String c5) {
		this.c5 = c5;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	 
}
