package com.ss.oa.ledger.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name="OA_HT_CHARGES")
@CreationTimestamp @UpdateTimestamp
@Entity
@Scope("prototype")
public class OaHtCharges {
	@Id
	private String id;
	@Column(name="SELLER_SERV_ID")
	private String sellerCompanyServiceId;
	@Column(name="SELLER_SERV_NUM")
	private String sellerCompanyServiceNumber;
	@Formula("(select sellercomp.M_COMPANY_NAME from v_company_service sellercomp where sellercomp.M_COMP_SERV_NUMBER=SELLER_SERV_NUM)")
	private String sellerCompanyName;
	@Column(name="BUYER_SERV_ID")
	private String buyerCompanyServiceId;
	@Column(name="BUYER_SERV_NUM")
	private String buyerCompanyServiceNumber;
	@Formula("(select buyercomp.M_COMPANY_NAME from v_company_service buyercomp where buyercomp.M_COMP_SERV_NUMBER=BUYER_SERV_NUM)")
	private String buyerCompanyName;
	@Column(name="C001")
	private String c001;
	@Column(name="C002")
	private String c002;
	@Column(name="C003")
	private String c003;
	@Column(name="C004")
	private String c004;
	@Column(name="C005")
	private String c005;
	@Column(name="C006")
	private String c006;
	@Column(name="C007")
	private String c007;
	@Column(name="HT_C001")
	private String htC001;
	@Column(name="HT_C002")
	private String htC002;
	@Column(name="HT_C003")
	private String htC003;
	@Column(name="HT_C004")
	private String htC004;
	@Column(name="HT_C005")
	private String htC005;
	@Column(name="HT_C006")
	private String htC006;
	@Column(name="HT_C007")
	private String htC007;
	@Column(name="SUM")
	private String sum;
	@Column(name="HT_SUM")
	private String htSum;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifiedDt;
	@Column(columnDefinition = "char")
	private String enabled;
	
	public OaHtCharges() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OaHtCharges(String id, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerCompanyName, String buyerCompanyServiceId, String buyerCompanyServiceNumber,
			String buyerCompanyName, String c001, String c002, String c003, String c004, String c005, String c006,
			String c007, String htC001, String htC002, String htC003, String htC004, String htC005, String htC006,
			String htC007, String sum, String htSum, LocalDate createdDt, String modifiedBy, LocalDate modifiedDt,
			String enabled) {
		super();
		this.id = id;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyName = sellerCompanyName;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyName = buyerCompanyName;
		this.c001 = c001;
		this.c002 = c002;
		this.c003 = c003;
		this.c004 = c004;
		this.c005 = c005;
		this.c006 = c006;
		this.c007 = c007;
		this.htC001 = htC001;
		this.htC002 = htC002;
		this.htC003 = htC003;
		this.htC004 = htC004;
		this.htC005 = htC005;
		this.htC006 = htC006;
		this.htC007 = htC007;
		this.sum = sum;
		this.htSum = htSum;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "OaHtCharges [id=" + id + ", sellerCompanyServiceId=" + sellerCompanyServiceId
				+ ", sellerCompanyServiceNumber=" + sellerCompanyServiceNumber + ", sellerCompanyName="
				+ sellerCompanyName + ", buyerCompanyServiceId=" + buyerCompanyServiceId
				+ ", buyerCompanyServiceNumber=" + buyerCompanyServiceNumber + ", buyerCompanyName=" + buyerCompanyName
				+ ", c001=" + c001 + ", c002=" + c002 + ", c003=" + c003 + ", c004=" + c004 + ", c005=" + c005
				+ ", c006=" + c006 + ", c007=" + c007 + ", htC001=" + htC001 + ", htC002=" + htC002 + ", htC003="
				+ htC003 + ", htC004=" + htC004 + ", htC005=" + htC005 + ", htC006=" + htC006 + ", htC007=" + htC007
				+ ", sum=" + sum + ", htSum=" + htSum + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + ", enabled=" + enabled + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
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

	public String getBuyerCompanyName() {
		return buyerCompanyName;
	}

	public void setBuyerCompanyName(String buyerCompanyName) {
		this.buyerCompanyName = buyerCompanyName;
	}

	public String getC001() {
		return c001;
	}

	public void setC001(String c001) {
		this.c001 = c001;
	}

	public String getC002() {
		return c002;
	}

	public void setC002(String c002) {
		this.c002 = c002;
	}

	public String getC003() {
		return c003;
	}

	public void setC003(String c003) {
		this.c003 = c003;
	}

	public String getC004() {
		return c004;
	}

	public void setC004(String c004) {
		this.c004 = c004;
	}

	public String getC005() {
		return c005;
	}

	public void setC005(String c005) {
		this.c005 = c005;
	}

	public String getC006() {
		return c006;
	}

	public void setC006(String c006) {
		this.c006 = c006;
	}

	public String getC007() {
		return c007;
	}

	public void setC007(String c007) {
		this.c007 = c007;
	}

	public String getHtC001() {
		return htC001;
	}

	public void setHtC001(String htC001) {
		this.htC001 = htC001;
	}

	public String getHtC002() {
		return htC002;
	}

	public void setHtC002(String htC002) {
		this.htC002 = htC002;
	}

	public String getHtC003() {
		return htC003;
	}

	public void setHtC003(String htC003) {
		this.htC003 = htC003;
	}

	public String getHtC004() {
		return htC004;
	}

	public void setHtC004(String htC004) {
		this.htC004 = htC004;
	}

	public String getHtC005() {
		return htC005;
	}

	public void setHtC005(String htC005) {
		this.htC005 = htC005;
	}

	public String getHtC006() {
		return htC006;
	}

	public void setHtC006(String htC006) {
		this.htC006 = htC006;
	}

	public String getHtC007() {
		return htC007;
	}

	public void setHtC007(String htC007) {
		this.htC007 = htC007;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getHtSum() {
		return htSum;
	}

	public void setHtSum(String htSum) {
		this.htSum = htSum;
	}

	public LocalDate getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDate createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDate modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	
	
	
	
	
}
