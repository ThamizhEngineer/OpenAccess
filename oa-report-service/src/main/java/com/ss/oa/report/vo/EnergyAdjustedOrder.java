package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "ENERGY_ADJUSTED_ORDER")
@Getter
public class EnergyAdjustedOrder {
	@Id
	@Column(name="ID", columnDefinition="VARCHAR2")
	private String id;
	private String buyerServiceNumber;
	private String sellerServiceNumber;
	@Column(name="D_SELL_COMP_NAME")
	private String dSellCompName;
	@Column(name="D_SELL_ORG_NAME")
	private String dSellOrgName;
	@Column(name="D_BUYER_COMP_NAME")
	private String dBuyerCompName;
	@Column(name="D_BUYER_ORG_NAME")
	private String dBuyerOrgName;
	@Column(name="F_ENERGY_SALE_ORDER_ID")
	private String energySaleOrderId;
	@Column(name="F_ENERGY_SALE_ORDER_LINE_ID")
	private String energySaleOrderLineId;
	private String buyerCompServId;
	private String sellerCompServId;
	private String cleanStatus;
	@Column(name="RD_MONTH")
	private String readingMonth;
	@Column(name="RD_YEAR")
	private String readingYear;
	@Column(name="RD_DATE")
	private String readingDate;
	@Column(name="ADJC1")
	private String adjustedC1;
	@Column(name="ADJC2")
	private String adjustedC2;
	@Column(name="ADJC3")
	private String adjustedC3;
	@Column(name="ADJC4")
	private String adjustedC4;
	@Column(name="ADJC5")
	private String adjustedC5;
	@Column(name="HT_BB_C1")
	private String htBankingC1;
	@Column(name="HT_BB_C2")
	private String htBankingC2;
	@Column(name="HT_BB_C3")
	private String htBankingC3;
	@Column(name="HT_BB_C4")
	private String htBankingC4;
	@Column(name="HT_BB_C5")
	private String htBankingC5;
	@Column(name="C1_WITHLOSS",columnDefinition="NUMBER")
	private String htBankingWithLossC1;
	@Column(name="C2_WITHLOSS",columnDefinition="NUMBER")
	private String htBankingWithLossC2;
	@Column(name="C3_WITHLOSS",columnDefinition="NUMBER")
	private String htBankingWithLossC3;
	@Column(name="C4_WITHLOSS",columnDefinition="NUMBER")
	private String htBankingWithLossC4;
	@Column(name="C5_WITHLOSS",columnDefinition="NUMBER")
	private String htBankingWithLossC5;
	@Column(name="ALLOTED_C1")
	private String allotedC1;
	@Column(name="ALLOTED_C2")
	private String allotedC2;
	@Column(name="ALLOTED_C3")
	private String allotedC3;
	@Column(name="ALLOTED_C4")
	private String allotedC4;
	@Column(name="ALLOTED_C5")
	private String allotedC5;
	@Column(name="LD_C1")
	private String allotedNetC1;
	@Column(name="LD_C2")
	private String allotedNetC2;
	@Column(name="LD_C3")
	private String allotedNetC3;
	@Column(name="LD_C4")
	private String allotedNetC4;
	@Column(name="LD_C5")
	private String allotedNetC5;
	@Column(name="LOSS_PERCENT")
	private String lossPercent;
//	@Column(name="FUEL_TYPE_CODE")
	private String fuelTypeCode;
//	@Column(name="FUEL_NAME")
	private String fuelName;

	public EnergyAdjustedOrder() {
		super();
	}

	public EnergyAdjustedOrder(String id, String buyerServiceNumber, String sellerServiceNumber, String dSellCompName,
			String dSellOrgName, String dBuyerCompName, String dBuyerOrgName, String energySaleOrderId,
			String energySaleOrderLineId, String buyerCompServId, String sellerCompServId, String cleanStatus,
			String readingMonth, String readingYear, String readingDate, String adjustedC1, String adjustedC2,
			String adjustedC3, String adjustedC4, String adjustedC5, String htBankingC1, String htBankingC2,
			String htBankingC3, String htBankingC4, String htBankingC5, String htBankingWithLossC1,
			String htBankingWithLossC2, String htBankingWithLossC3, String htBankingWithLossC4,
			String htBankingWithLossC5, String allotedC1, String allotedC2, String allotedC3, String allotedC4,
			String allotedC5, String allotedNetC1, String allotedNetC2, String allotedNetC3, String allotedNetC4,
			String allotedNetC5, String lossPercent, String fuelTypeCode, String fuelName) {
		super();
		this.id = id;
		this.buyerServiceNumber = buyerServiceNumber;
		this.sellerServiceNumber = sellerServiceNumber;
		this.dSellCompName = dSellCompName;
		this.dSellOrgName = dSellOrgName;
		this.dBuyerCompName = dBuyerCompName;
		this.dBuyerOrgName = dBuyerOrgName;
		this.energySaleOrderId = energySaleOrderId;
		this.energySaleOrderLineId = energySaleOrderLineId;
		this.buyerCompServId = buyerCompServId;
		this.sellerCompServId = sellerCompServId;
		this.cleanStatus = cleanStatus;
		this.readingMonth = readingMonth;
		this.readingYear = readingYear;
		this.readingDate = readingDate;
		this.adjustedC1 = adjustedC1;
		this.adjustedC2 = adjustedC2;
		this.adjustedC3 = adjustedC3;
		this.adjustedC4 = adjustedC4;
		this.adjustedC5 = adjustedC5;
		this.htBankingC1 = htBankingC1;
		this.htBankingC2 = htBankingC2;
		this.htBankingC3 = htBankingC3;
		this.htBankingC4 = htBankingC4;
		this.htBankingC5 = htBankingC5;
		this.htBankingWithLossC1 = htBankingWithLossC1;
		this.htBankingWithLossC2 = htBankingWithLossC2;
		this.htBankingWithLossC3 = htBankingWithLossC3;
		this.htBankingWithLossC4 = htBankingWithLossC4;
		this.htBankingWithLossC5 = htBankingWithLossC5;
		this.allotedC1 = allotedC1;
		this.allotedC2 = allotedC2;
		this.allotedC3 = allotedC3;
		this.allotedC4 = allotedC4;
		this.allotedC5 = allotedC5;
		this.allotedNetC1 = allotedNetC1;
		this.allotedNetC2 = allotedNetC2;
		this.allotedNetC3 = allotedNetC3;
		this.allotedNetC4 = allotedNetC4;
		this.allotedNetC5 = allotedNetC5;
		this.lossPercent = lossPercent;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelName = fuelName;
	}

	@Override
	public String toString() {
		return "EnergyAdjustedOrder [id=" + id + ", buyerServiceNumber=" + buyerServiceNumber + ", sellerServiceNumber="
				+ sellerServiceNumber + ", dSellCompName=" + dSellCompName + ", dSellOrgName=" + dSellOrgName
				+ ", dBuyerCompName=" + dBuyerCompName + ", dBuyerOrgName=" + dBuyerOrgName + ", energySaleOrderId="
				+ energySaleOrderId + ", energySaleOrderLineId=" + energySaleOrderLineId + ", buyerCompServId="
				+ buyerCompServId + ", sellerCompServId=" + sellerCompServId + ", cleanStatus=" + cleanStatus
				+ ", readingMonth=" + readingMonth + ", readingYear=" + readingYear + ", readingDate=" + readingDate
				+ ", adjustedC1=" + adjustedC1 + ", adjustedC2=" + adjustedC2 + ", adjustedC3=" + adjustedC3
				+ ", adjustedC4=" + adjustedC4 + ", adjustedC5=" + adjustedC5 + ", htBankingC1=" + htBankingC1
				+ ", htBankingC2=" + htBankingC2 + ", htBankingC3=" + htBankingC3 + ", htBankingC4=" + htBankingC4
				+ ", htBankingC5=" + htBankingC5 + ", htBankingWithLossC1=" + htBankingWithLossC1
				+ ", htBankingWithLossC2=" + htBankingWithLossC2 + ", htBankingWithLossC3=" + htBankingWithLossC3
				+ ", htBankingWithLossC4=" + htBankingWithLossC4 + ", htBankingWithLossC5=" + htBankingWithLossC5
				+ ", allotedC1=" + allotedC1 + ", allotedC2=" + allotedC2 + ", allotedC3=" + allotedC3 + ", allotedC4="
				+ allotedC4 + ", allotedC5=" + allotedC5 + ", allotedNetC1=" + allotedNetC1 + ", allotedNetC2="
				+ allotedNetC2 + ", allotedNetC3=" + allotedNetC3 + ", allotedNetC4=" + allotedNetC4 + ", allotedNetC5="
				+ allotedNetC5 + ", lossPercent=" + lossPercent + ", fuelTypeCode=" + fuelTypeCode + ", fuelName="
				+ fuelName + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuyerServiceNumber() {
		return buyerServiceNumber;
	}

	public void setBuyerServiceNumber(String buyerServiceNumber) {
		this.buyerServiceNumber = buyerServiceNumber;
	}

	public String getSellerServiceNumber() {
		return sellerServiceNumber;
	}

	public void setSellerServiceNumber(String sellerServiceNumber) {
		this.sellerServiceNumber = sellerServiceNumber;
	}

	public String getdSellCompName() {
		return dSellCompName;
	}

	public void setdSellCompName(String dSellCompName) {
		this.dSellCompName = dSellCompName;
	}

	public String getdSellOrgName() {
		return dSellOrgName;
	}

	public void setdSellOrgName(String dSellOrgName) {
		this.dSellOrgName = dSellOrgName;
	}

	public String getdBuyerCompName() {
		return dBuyerCompName;
	}

	public void setdBuyerCompName(String dBuyerCompName) {
		this.dBuyerCompName = dBuyerCompName;
	}

	public String getdBuyerOrgName() {
		return dBuyerOrgName;
	}

	public void setdBuyerOrgName(String dBuyerOrgName) {
		this.dBuyerOrgName = dBuyerOrgName;
	}

	public String getEnergySaleOrderId() {
		return energySaleOrderId;
	}

	public void setEnergySaleOrderId(String energySaleOrderId) {
		this.energySaleOrderId = energySaleOrderId;
	}

	public String getEnergySaleOrderLineId() {
		return energySaleOrderLineId;
	}

	public void setEnergySaleOrderLineId(String energySaleOrderLineId) {
		this.energySaleOrderLineId = energySaleOrderLineId;
	}

	public String getBuyerCompServId() {
		return buyerCompServId;
	}

	public void setBuyerCompServId(String buyerCompServId) {
		this.buyerCompServId = buyerCompServId;
	}

	public String getSellerCompServId() {
		return sellerCompServId;
	}

	public void setSellerCompServId(String sellerCompServId) {
		this.sellerCompServId = sellerCompServId;
	}

	public String getCleanStatus() {
		return cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public String getReadingMonth() {
		return readingMonth;
	}

	public void setReadingMonth(String readingMonth) {
		this.readingMonth = readingMonth;
	}

	public String getReadingYear() {
		return readingYear;
	}

	public void setReadingYear(String readingYear) {
		this.readingYear = readingYear;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
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

	public String getHtBankingC1() {
		return htBankingC1;
	}

	public void setHtBankingC1(String htBankingC1) {
		this.htBankingC1 = htBankingC1;
	}

	public String getHtBankingC2() {
		return htBankingC2;
	}

	public void setHtBankingC2(String htBankingC2) {
		this.htBankingC2 = htBankingC2;
	}

	public String getHtBankingC3() {
		return htBankingC3;
	}

	public void setHtBankingC3(String htBankingC3) {
		this.htBankingC3 = htBankingC3;
	}

	public String getHtBankingC4() {
		return htBankingC4;
	}

	public void setHtBankingC4(String htBankingC4) {
		this.htBankingC4 = htBankingC4;
	}

	public String getHtBankingC5() {
		return htBankingC5;
	}

	public void setHtBankingC5(String htBankingC5) {
		this.htBankingC5 = htBankingC5;
	}

	public String getHtBankingWithLossC1() {
		return htBankingWithLossC1;
	}

	public void setHtBankingWithLossC1(String htBankingWithLossC1) {
		this.htBankingWithLossC1 = htBankingWithLossC1;
	}

	public String getHtBankingWithLossC2() {
		return htBankingWithLossC2;
	}

	public void setHtBankingWithLossC2(String htBankingWithLossC2) {
		this.htBankingWithLossC2 = htBankingWithLossC2;
	}

	public String getHtBankingWithLossC3() {
		return htBankingWithLossC3;
	}

	public void setHtBankingWithLossC3(String htBankingWithLossC3) {
		this.htBankingWithLossC3 = htBankingWithLossC3;
	}

	public String getHtBankingWithLossC4() {
		return htBankingWithLossC4;
	}

	public void setHtBankingWithLossC4(String htBankingWithLossC4) {
		this.htBankingWithLossC4 = htBankingWithLossC4;
	}

	public String getHtBankingWithLossC5() {
		return htBankingWithLossC5;
	}

	public void setHtBankingWithLossC5(String htBankingWithLossC5) {
		this.htBankingWithLossC5 = htBankingWithLossC5;
	}

	public String getAllotedC1() {
		return allotedC1;
	}

	public void setAllotedC1(String allotedC1) {
		this.allotedC1 = allotedC1;
	}

	public String getAllotedC2() {
		return allotedC2;
	}

	public void setAllotedC2(String allotedC2) {
		this.allotedC2 = allotedC2;
	}

	public String getAllotedC3() {
		return allotedC3;
	}

	public void setAllotedC3(String allotedC3) {
		this.allotedC3 = allotedC3;
	}

	public String getAllotedC4() {
		return allotedC4;
	}

	public void setAllotedC4(String allotedC4) {
		this.allotedC4 = allotedC4;
	}

	public String getAllotedC5() {
		return allotedC5;
	}

	public void setAllotedC5(String allotedC5) {
		this.allotedC5 = allotedC5;
	}

	public String getAllotedNetC1() {
		return allotedNetC1;
	}

	public void setAllotedNetC1(String allotedNetC1) {
		this.allotedNetC1 = allotedNetC1;
	}

	public String getAllotedNetC2() {
		return allotedNetC2;
	}

	public void setAllotedNetC2(String allotedNetC2) {
		this.allotedNetC2 = allotedNetC2;
	}

	public String getAllotedNetC3() {
		return allotedNetC3;
	}

	public void setAllotedNetC3(String allotedNetC3) {
		this.allotedNetC3 = allotedNetC3;
	}

	public String getAllotedNetC4() {
		return allotedNetC4;
	}

	public void setAllotedNetC4(String allotedNetC4) {
		this.allotedNetC4 = allotedNetC4;
	}

	public String getAllotedNetC5() {
		return allotedNetC5;
	}

	public void setAllotedNetC5(String allotedNetC5) {
		this.allotedNetC5 = allotedNetC5;
	}

	public String getLossPercent() {
		return lossPercent;
	}

	public void setLossPercent(String lossPercent) {
		this.lossPercent = lossPercent;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelName() {
		return fuelName;
	}

	public void setFuelName(String fuelName) {
		this.fuelName = fuelName;
	}

	

	
}
