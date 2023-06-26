package com.ss.oa.oadocumentservice.vo;

import java.time.LocalDate;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EnergyAdjustedOrder {
	private String id;
	private String buyerServiceNumber;
	private String sellerServiceNumber;
	private String dSellCompName;
	private String dSellOrgName;
	private String dBuyerCompName;
	private String dBuyerOrgName;
	private String energySaleOrderId;
	private String energySaleOrderLineId;
	private String buyerCompServId;
	private String sellerCompServId;
	private String cleanStatus;
	private String readingMonth;
	private String readingYear;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate readingDt;
	
	
	
	
	//variables for total of adjusted
	private Long totaladjustedC1;
	private Long totaladjustedC2;
	private Long totaladjustedC3;
	private Long totaladjustedC4;
	private Long totaladjustedC5;
	private Long Totalupadj;	

	private String adjustedC1;
	private String adjustedC2;
	private String adjustedC3;
	private String adjustedC4;
	private String adjustedC5;
	
	//variable for total of htBanking
	private Long totalhtBankingC1;
	private Long totalhtBankingC2;
	private Long totalhtBankingC3;
	private Long totalhtBankingC4;
	private Long totalhtBankingC5;
	private Long TotaluphtBanking;
	
	private String htBankingC1;
	private String htBankingC2;
	private String htBankingC3;
	private String htBankingC4;
	private String htBankingC5;
	
	//variable for total of htbanking with loss
	private Long totalhtBankingWithLossC1;
	private Long totalhtBankingWithLossC2;
	private Long totalhtBankingWithLossC3;
	private Long totalhtBankingWithLossC4;
	private Long totalhtBankingWithLossC5;
	private Long TotaluphtBankingWithLoss;
	
	
	private String htBankingWithLossC1;
	private String htBankingWithLossC2;
	private String htBankingWithLossC3;
	private String htBankingWithLossC4;
	private String htBankingWithLossC5;
	
    //variables for total of allocatedgross
	
	private Long totalalloctedgrossC1;
	private Long totalalloctedgrossC2;
	private  Long totalalloctedgrossC3;
	private Long totalalloctedgrossC4;
	private Long totalalloctedgrossC5;
	private Long totalupalloctedgross;
	
	private String allotedC1;
	private String allotedC2;
	private String allotedC3;
	private String allotedC4;
	private String allotedC5;
	
	//variables for total of alloctednet
	private Long totalalloctednetC1;
	private Long totalalloctednetC2;
	private Long totalalloctednetC3;
	private Long totalalloctednetC4;
	private Long totalalloctednetC5;
	private Long totalupalloctednet;
	
	private String allotedNetC1;
	private String allotedNetC2;
	private String allotedNetC3;
	private String allotedNetC4;
	private String allotedNetC5;
	
	private String lossPercent;
	private Integer sequenceNumber;
	private String fuelTypeCode;
	private String fuelName;
	
	//total variable of each colunm
	private Long totaladj=0l;
	private Long totalallo=0l;
	private Long totalallonet=0l;
	private Long totalhtBanking=0l;
	private Long totalhtBankingwithloss=0l;
	
	
	
	private float totalLossPercentage;
	
	public EnergyAdjustedOrder() {
		super();
	}

	public EnergyAdjustedOrder(String id, String buyerServiceNumber, String sellerServiceNumber, String dSellCompName,
			String dSellOrgName, String dBuyerCompName, String dBuyerOrgName, String energySaleOrderId,
			String energySaleOrderLineId, String buyerCompServId, String sellerCompServId, String cleanStatus,
			String readingMonth, String readingYear, LocalDate readingDt, String adjustedC1, String adjustedC2,
			String adjustedC3, String adjustedC4, String adjustedC5, String htBankingC1, String htBankingC2,
			String htBankingC3, String htBankingC4, String htBankingC5, String htBankingWithLossC1,
			String htBankingWithLossC2, String htBankingWithLossC3, String htBankingWithLossC4,
			String htBankingWithLossC5, String allotedC1, String allotedC2, String allotedC3, String allotedC4,
			String allotedC5, String allotedNetC1, String allotedNetC2, String allotedNetC3, String allotedNetC4,
			String allotedNetC5, String lossPercent, Integer sequenceNumber, String fuelTypeCode, String fuelName) {
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
		this.readingDt = readingDt;
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
		this.sequenceNumber = sequenceNumber;
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
				+ ", readingMonth=" + readingMonth + ", readingYear=" + readingYear + ", readingDt=" + readingDt
				+ ", adjustedC1=" + adjustedC1 + ", adjustedC2=" + adjustedC2 + ", adjustedC3=" + adjustedC3
				+ ", adjustedC4=" + adjustedC4 + ", adjustedC5=" + adjustedC5 + ", htBankingC1=" + htBankingC1
				+ ", htBankingC2=" + htBankingC2 + ", htBankingC3=" + htBankingC3 + ", htBankingC4=" + htBankingC4
				+ ", htBankingC5=" + htBankingC5 + ", htBankingWithLossC1=" + htBankingWithLossC1
				+ ", htBankingWithLossC2=" + htBankingWithLossC2 + ", htBankingWithLossC3=" + htBankingWithLossC3
				+ ", htBankingWithLossC4=" + htBankingWithLossC4 + ", htBankingWithLossC5=" + htBankingWithLossC5
				+ ", allotedC1=" + allotedC1 + ", allotedC2=" + allotedC2 + ", allotedC3=" + allotedC3 + ", allotedC4="
				+ allotedC4 + ", allotedC5=" + allotedC5 + ", allotedNetC1=" + allotedNetC1 + ", allotedNetC2="
				+ allotedNetC2 + ", allotedNetC3=" + allotedNetC3 + ", allotedNetC4=" + allotedNetC4 + ", allotedNetC5="
				+ allotedNetC5 + ", lossPercent=" + lossPercent + ", sequenceNumber=" + sequenceNumber
				+ ", fuelTypeCode=" + fuelTypeCode + ", fuelName=" + fuelName + "]";
	}
	
	
	

	public Long getTotaluphtBankingWithLoss() {
		return TotaluphtBankingWithLoss;
	}

	public void setTotaluphtBankingWithLoss(Long totaluphtBankingWithLoss) {
		TotaluphtBankingWithLoss = totaluphtBankingWithLoss;
	}

	public Long getTotalupalloctednet() {
		return totalupalloctednet;
	}

	public void setTotalupalloctednet(Long totalupalloctednet) {
		this.totalupalloctednet = totalupalloctednet;
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

	public LocalDate getReadingDt() {
		return readingDt;
	}

	public void setReadingDt(LocalDate readingDt) {
		this.readingDt = readingDt;
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

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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

	public Long getTotaladj() {
		return totaladj;
	}

	public void setTotaladj(Long totaladj) {
		this.totaladj = totaladj;
	}

	public Long getTotalallo() {
		return totalallo;
	}

	public void setTotalallo(Long totalallo) {
		this.totalallo = totalallo;
	}

	public Long getTotalallonet() {
		return totalallonet;
	}

	public void setTotalallonet(Long totalallonet) {
		this.totalallonet = totalallonet;
	}

	public Long getTotalhtBanking() {
		return totalhtBanking;
	}

	public void setTotalhtBanking(Long totalhtBanking) {
		this.totalhtBanking = totalhtBanking;
	}

	public Long getTotalhtBankingwithloss() {
		return totalhtBankingwithloss;
	}

	public void setTotalhtBankingwithloss(Long totalhtBankingwithloss) {
		this.totalhtBankingwithloss = totalhtBankingwithloss;
	}

	public Long getTotalalloctedgrossC1() {
		return totalalloctedgrossC1;
	}

	public void setTotalalloctedgrossC1(Long totalalloctedgrossC1) {
		this.totalalloctedgrossC1 = totalalloctedgrossC1;
	}

	

	public Long getTotalalloctedgrossC2() {
		return totalalloctedgrossC2;
	}

	public void setTotalalloctedgrossC2(Long totalalloctedgrossC2) {
		this.totalalloctedgrossC2 = totalalloctedgrossC2;
	}

	public Long getTotalalloctedgrossC3() {
		return totalalloctedgrossC3;
	}

	public void setTotalalloctedgrossC3(Long totalalloctedgrossC3) {
		this.totalalloctedgrossC3 = totalalloctedgrossC3;
	}

	public Long getTotalalloctedgrossC4() {
		return totalalloctedgrossC4;
	}

	public void setTotalalloctedgrossC4(Long totalalloctedgrossC4) {
		this.totalalloctedgrossC4 = totalalloctedgrossC4;
	}

	public Long getTotalalloctedgrossC5() {
		return totalalloctedgrossC5;
	}

	public void setTotalalloctedgrossC5(Long totalalloctedgrossC5) {
		this.totalalloctedgrossC5 = totalalloctedgrossC5;
	}

	public Long getTotaladjustedC1() {
		return totaladjustedC1;
	}

	public void setTotaladjustedC1(Long totaladjustedC1) {
		this.totaladjustedC1 = totaladjustedC1;
	}

	public Long getTotaladjustedC2() {
		return totaladjustedC2;
	}

	public void setTotaladjustedC2(Long totaladjustedC2) {
		this.totaladjustedC2 = totaladjustedC2;
	}

	public Long getTotaladjustedC3() {
		return totaladjustedC3;
	}

	public void setTotaladjustedC3(Long totaladjustedC3) {
		this.totaladjustedC3 = totaladjustedC3;
	}

	public Long getTotaladjustedC4() {
		return totaladjustedC4;
	}

	public void setTotaladjustedC4(Long totaladjustedC4) {
		this.totaladjustedC4 = totaladjustedC4;
	}

	public Long getTotaladjustedC5() {
		return totaladjustedC5;
	}

	public void setTotaladjustedC5(Long totaladjustedC5) {
		this.totaladjustedC5 = totaladjustedC5;
	}

	public Long getTotalhtBankingC1() {
		return totalhtBankingC1;
	}

	public void setTotalhtBankingC1(Long totalhtBankingC1) {
		this.totalhtBankingC1 = totalhtBankingC1;
	}

	public Long getTotalhtBankingC2() {
		return totalhtBankingC2;
	}

	public void setTotalhtBankingC2(Long totalhtBankingC2) {
		this.totalhtBankingC2 = totalhtBankingC2;
	}

	public Long getTotalhtBankingC3() {
		return totalhtBankingC3;
	}

	public void setTotalhtBankingC3(Long totalhtBankingC3) {
		this.totalhtBankingC3 = totalhtBankingC3;
	}

	public Long getTotalhtBankingC4() {
		return totalhtBankingC4;
	}

	public void setTotalhtBankingC4(Long totalhtBankingC4) {
		this.totalhtBankingC4 = totalhtBankingC4;
	}

	public Long getTotalhtBankingC5() {
		return totalhtBankingC5;
	}

	public void setTotalhtBankingC5(Long totalhtBankingC5) {
		this.totalhtBankingC5 = totalhtBankingC5;
	}

	public Long getTotalhtBankingWithLossC1() {
		return totalhtBankingWithLossC1;
	}

	public void setTotalhtBankingWithLossC1(Long totalhtBankingWithLossC1) {
		this.totalhtBankingWithLossC1 = totalhtBankingWithLossC1;
	}

	public Long getTotalhtBankingWithLossC2() {
		return totalhtBankingWithLossC2;
	}

	public void setTotalhtBankingWithLossC2(Long totalhtBankingWithLossC2) {
		this.totalhtBankingWithLossC2 = totalhtBankingWithLossC2;
	}

	public Long getTotalhtBankingWithLossC3() {
		return totalhtBankingWithLossC3;
	}

	public void setTotalhtBankingWithLossC3(Long totalhtBankingWithLossC3) {
		this.totalhtBankingWithLossC3 = totalhtBankingWithLossC3;
	}

	public Long getTotalhtBankingWithLossC4() {
		return totalhtBankingWithLossC4;
	}

	public void setTotalhtBankingWithLossC4(Long totalhtBankingWithLossC4) {
		this.totalhtBankingWithLossC4 = totalhtBankingWithLossC4;
	}

	public Long getTotalhtBankingWithLossC5() {
		return totalhtBankingWithLossC5;
	}

	public void setTotalhtBankingWithLossC5(Long totalhtBankingWithLossC5) {
		this.totalhtBankingWithLossC5 = totalhtBankingWithLossC5;
	}

	public Long getTotalalloctednetC1() {
		return totalalloctednetC1;
	}

	public void setTotalalloctednetC1(Long totalalloctednetC1) {
		this.totalalloctednetC1 = totalalloctednetC1;
	}

	public Long getTotalalloctednetC2() {
		return totalalloctednetC2;
	}

	public void setTotalalloctednetC2(Long totalalloctednetC2) {
		this.totalalloctednetC2 = totalalloctednetC2;
	}

	public Long getTotalalloctednetC3() {
		return totalalloctednetC3;
	}

	public void setTotalalloctednetC3(Long totalalloctednetC3) {
		this.totalalloctednetC3 = totalalloctednetC3;
	}

	public Long getTotalalloctednetC4() {
		return totalalloctednetC4;
	}

	public void setTotalalloctednetC4(Long totalalloctednetC4) {
		this.totalalloctednetC4 = totalalloctednetC4;
	}

	public Long getTotalalloctednetC5() {
		return totalalloctednetC5;
	}

	public void setTotalalloctednetC5(Long totalalloctednetC5) {
		this.totalalloctednetC5 = totalalloctednetC5;
	}

	public Long getTotalupalloctedgross() {
		return totalupalloctedgross;
	}

	public void setTotalupalloctedgross(Long totalupalloctedgross) {
		this.totalupalloctedgross = totalupalloctedgross;
	}

	
	public Long getTotalupadj() {
		return Totalupadj;
	}

	public void setTotalupadj(Long totalupadj) {
		Totalupadj = totalupadj;
	}

	public Long getTotaluphtBanking() {
		return TotaluphtBanking;
	}

	public void setTotaluphtBanking(Long totaluphtBanking) {
		TotaluphtBanking = totaluphtBanking;
	}

	public float getTotalLossPercentage() {
		return totalLossPercentage;
	}

	public void setTotalLossPercentage(float totalLossPercentage) {
		this.totalLossPercentage = totalLossPercentage;
	}

	

	
	
	
	
}
