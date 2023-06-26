package com.ss.oa.vo;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.ss.oa.integration.energyadjusteddata.Charges;

@Table(name = "INT_ADJUSTED_UNIT")
@CreationTimestamp
@UpdateTimestamp
@Entity
public class EnergyAdjustedData {
	@Id
    @Column(name="ID")
	private String id;
    @Column(name="BATCH_KEY")
	private String batchKey;
    @Column(name="SERVICE_NO")
	private String buyerServiceNo;
    @Column(name="SUPLR_CODE")
	private String sellerServiceNo;
    @Column(name="SUPLR_TYPE")
	private String sellerType;
    @Column(name="SUPLR_NAME")
	private String sellerName;
    @Column(name="READING_DT")
	private String readingDt;
    @Column(name="READING_MNTH")
	private String readingMnth;
    @Column(name="READING_YR")
	private String readingYr;
    @Column(name="C24")
	private String surplusc24;
    @Column(name="C1")
	private String surplusC1;
    @Column(name="C2")
	private String surplusC2;
    @Column(name="C3")
	private String surplusC3;
    @Column(name="C4")
	private String surplusC4;
    @Column(name="C5")
	private String surplusC5;
    @Column(name="ADJUSTED_C1")
	private String adjustedC1;
    @Column(name="ADJUSTED_C2")
	private String adjustedC2;
    @Column(name="ADJUSTED_C3")
	private String adjustedC3;
    @Column(name="ADJUSTED_C4")
	private String adjustedC4;
    @Column(name="ADJUSTED_C5")
	private String adjustedC5;
    @Column(name="RESULT_CODE")
	private String resultCode;
    @Column(name="RESULT_NAME")
	private String resultName;
    @Transient
    private List<Charges>charges;
    
    
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="ENERGY_ADJUSTED_DATA_ID")
	private List<EnergyAdjustedDataCharge> energyadjusteddatacharge;
	
	public EnergyAdjustedData() {
		super();
	}

	public EnergyAdjustedData(String id, String batchKey, String buyerServiceNo, String sellerServiceNo,
			String sellerType, String sellerName, String readingDt, String readingMnth, String readingYr,
			String surplusc24, String surplusC1, String surplusC2, String surplusC3, String surplusC4, String surplusC5,
			String adjustedC1, String adjustedC2, String adjustedC3, String adjustedC4, String adjustedC5,
			String resultCode, String resultName, List<Charges> charges,
			List<EnergyAdjustedDataCharge> energyadjusteddatacharge) {
		super();
		this.id = id;
		this.batchKey = batchKey;
		this.buyerServiceNo = buyerServiceNo;
		this.sellerServiceNo = sellerServiceNo;
		this.sellerType = sellerType;
		this.sellerName = sellerName;
		this.readingDt = readingDt;
		this.readingMnth = readingMnth;
		this.readingYr = readingYr;
		this.surplusc24 = surplusc24;
		this.surplusC1 = surplusC1;
		this.surplusC2 = surplusC2;
		this.surplusC3 = surplusC3;
		this.surplusC4 = surplusC4;
		this.surplusC5 = surplusC5;
		this.adjustedC1 = adjustedC1;
		this.adjustedC2 = adjustedC2;
		this.adjustedC3 = adjustedC3;
		this.adjustedC4 = adjustedC4;
		this.adjustedC5 = adjustedC5;
		this.resultCode = resultCode;
		this.resultName = resultName;
		this.charges = charges;
		this.energyadjusteddatacharge = energyadjusteddatacharge;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBatchKey() {
		return batchKey;
	}

	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}

	public String getBuyerServiceNo() {
		return buyerServiceNo;
	}

	public void setBuyerServiceNo(String buyerServiceNo) {
		this.buyerServiceNo = buyerServiceNo;
	}

	public String getSellerServiceNo() {
		return sellerServiceNo;
	}

	public void setSellerServiceNo(String sellerServiceNo) {
		this.sellerServiceNo = sellerServiceNo;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getReadingDt() {
		return readingDt;
	}

	public void setReadingDt(String readingDt) {
		this.readingDt = readingDt;
	}

	public String getReadingMnth() {
		return readingMnth;
	}

	public void setReadingMnth(String readingMnth) {
		this.readingMnth = readingMnth;
	}

	public String getReadingYr() {
		return readingYr;
	}

	public void setReadingYr(String readingYr) {
		this.readingYr = readingYr;
	}

	public String getSurplusc24() {
		return surplusc24;
	}

	public void setSurplusc24(String surplusc24) {
		this.surplusc24 = surplusc24;
	}

	public String getSurplusC1() {
		return surplusC1;
	}

	public void setSurplusC1(String surplusC1) {
		this.surplusC1 = surplusC1;
	}

	public String getSurplusC2() {
		return surplusC2;
	}

	public void setSurplusC2(String surplusC2) {
		this.surplusC2 = surplusC2;
	}

	public String getSurplusC3() {
		return surplusC3;
	}

	public void setSurplusC3(String surplusC3) {
		this.surplusC3 = surplusC3;
	}

	public String getSurplusC4() {
		return surplusC4;
	}

	public void setSurplusC4(String surplusC4) {
		this.surplusC4 = surplusC4;
	}

	public String getSurplusC5() {
		return surplusC5;
	}

	public void setSurplusC5(String surplusC5) {
		this.surplusC5 = surplusC5;
	}

	public String getAdjustedC1() {
		return adjustedC1;
	}

	public void setAdjustedC1(String adjustedC1) {
		this.adjustedC1 = adjustedC1;
	}

	public String getAdjustedC2() {
		return adjustedC2;
	}

	public void setAdjustedC2(String adjustedC2) {
		this.adjustedC2 = adjustedC2;
	}

	public String getAdjustedC3() {
		return adjustedC3;
	}

	public void setAdjustedC3(String adjustedC3) {
		this.adjustedC3 = adjustedC3;
	}

	public String getAdjustedC4() {
		return adjustedC4;
	}

	public void setAdjustedC4(String adjustedC4) {
		this.adjustedC4 = adjustedC4;
	}

	public String getAdjustedC5() {
		return adjustedC5;
	}

	public void setAdjustedC5(String adjustedC5) {
		this.adjustedC5 = adjustedC5;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	public List<Charges> getCharges() {
		return charges;
	}

	public void setCharges(List<Charges> charges) {
		this.charges = charges;
	}

	public List<EnergyAdjustedDataCharge> getEnergyadjusteddatacharge() {
		return energyadjusteddatacharge;
	}

	public void setEnergyadjusteddatacharge(List<EnergyAdjustedDataCharge> energyadjusteddatacharge) {
		this.energyadjusteddatacharge = energyadjusteddatacharge;
	}

	@Override
	public String toString() {
		return "EnergyAdjustedData [id=" + id + ", batchKey=" + batchKey + ", buyerServiceNo=" + buyerServiceNo
				+ ", sellerServiceNo=" + sellerServiceNo + ", sellerType=" + sellerType + ", sellerName=" + sellerName
				+ ", readingDt=" + readingDt + ", readingMnth=" + readingMnth + ", readingYr=" + readingYr
				+ ", surplusc24=" + surplusc24 + ", surplusC1=" + surplusC1 + ", surplusC2=" + surplusC2
				+ ", surplusC3=" + surplusC3 + ", surplusC4=" + surplusC4 + ", surplusC5=" + surplusC5 + ", adjustedC1="
				+ adjustedC1 + ", adjustedC2=" + adjustedC2 + ", adjustedC3=" + adjustedC3 + ", adjustedC4="
				+ adjustedC4 + ", adjustedC5=" + adjustedC5 + ", resultCode=" + resultCode + ", resultName="
				+ resultName + ", charges=" + charges + ", energyadjusteddatacharge=" + energyadjusteddatacharge + "]";
	}

	

}
