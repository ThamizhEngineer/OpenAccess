package com.ss.oa.oadocumentservice.vo;

import java.util.List;

public class EnergyAllotmentOrder {
	private String id;
	private String energySaleId;
	private String sellerCompanyServiceId;
	private String sellerEndOrgId;
	private String year;
	private String month;
	private String injectingVoltageCode;
	private String injectingVoltageName;
	private String fromDate,toDate,createdDate;
	private String loss;
	private String multipleBuyers;
	private String usageDetailAvail;
	private String simpleEnergySale;
	private String totalC1,totalC2,totalC3,totalC4,totalC5;
	private String totalGenUnitsSold;
	private String totalBc1,totalBc2,totalBc3,totalBc4,totalBc5;
	private String totalBankingUnitsSold;
	private String totalUnitsSold;
	private String allowLowerSlotAdmt;
	private String sellerCompanyName,sellerCompanyCode,sellerCompanyId;
	private String sellerCompanyServiceNumber,sellerCompanyServiceTypeCode,sellerCompBankingServiceId,sellerCompBankingServiceNumber;
	private String sellerTlServiceId,sellerTlServiceNumber,sellerDlServiceId,sellerDlServiceNumber;
	private String sellerUnAdjustedServiceId,sellerUnAdjustedServiceNumber;
	private String sellerEndOrgName,sellerEndOrgCode;
	private String sellerEndFeederId,sellerEndFeederName,sellerEndFeederCode;
	private String sellerEndSubstationId,sellerEndSubstationName,sellerEndSubstationCode;
	private String totalGc1,totalGc2,totalGc3,totalGc4,totalGc5;
	private String fuelTypeCode;
	private String fuelTypeName;
	private String fuelGroupe;
	private List<EnergyAllotmentOrderLine> energySaleOrderLines;
	private List<EnergyCharge> energyCharge;
	
	public EnergyAllotmentOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "EnergyAllotmentOrder [id=" + id + ", energySaleId=" + energySaleId + ", sellerCompanyServiceId="
				+ sellerCompanyServiceId + ", sellerEndOrgId=" + sellerEndOrgId + ", year=" + year + ", month=" + month
				+ ", injectingVoltageCode=" + injectingVoltageCode + ", injectingVoltageName=" + injectingVoltageName
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", createdDate=" + createdDate + ", loss=" + loss
				+ ", multipleBuyers=" + multipleBuyers + ", usageDetailAvail=" + usageDetailAvail
				+ ", simpleEnergySale=" + simpleEnergySale + ", totalC1=" + totalC1 + ", totalC2=" + totalC2
				+ ", totalC3=" + totalC3 + ", totalC4=" + totalC4 + ", totalC5=" + totalC5 + ", totalGenUnitsSold="
				+ totalGenUnitsSold + ", totalBc1=" + totalBc1 + ", totalBc2=" + totalBc2 + ", totalBc3=" + totalBc3
				+ ", totalBc4=" + totalBc4 + ", totalBc5=" + totalBc5 + ", totalBankingUnitsSold="
				+ totalBankingUnitsSold + ", totalUnitsSold=" + totalUnitsSold + ", allowLowerSlotAdmt="
				+ allowLowerSlotAdmt + ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode="
				+ sellerCompanyCode + ", sellerCompanyId=" + sellerCompanyId + ", sellerCompanyServiceNumber="
				+ sellerCompanyServiceNumber + ", sellerCompanyServiceTypeCode=" + sellerCompanyServiceTypeCode
				+ ", sellerCompBankingServiceId=" + sellerCompBankingServiceId + ", sellerCompBankingServiceNumber="
				+ sellerCompBankingServiceNumber + ", sellerTlServiceId=" + sellerTlServiceId
				+ ", sellerTlServiceNumber=" + sellerTlServiceNumber + ", sellerDlServiceId=" + sellerDlServiceId
				+ ", sellerDlServiceNumber=" + sellerDlServiceNumber + ", sellerUnAdjustedServiceId="
				+ sellerUnAdjustedServiceId + ", sellerUnAdjustedServiceNumber=" + sellerUnAdjustedServiceNumber
				+ ", sellerEndOrgName=" + sellerEndOrgName + ", sellerEndOrgCode=" + sellerEndOrgCode
				+ ", sellerEndFeederId=" + sellerEndFeederId + ", sellerEndFeederName=" + sellerEndFeederName
				+ ", sellerEndFeederCode=" + sellerEndFeederCode + ", sellerEndSubstationId=" + sellerEndSubstationId
				+ ", sellerEndSubstationName=" + sellerEndSubstationName + ", sellerEndSubstationCode="
				+ sellerEndSubstationCode + ", totalGc1=" + totalGc1 + ", totalGc2=" + totalGc2 + ", totalGc3="
				+ totalGc3 + ", totalGc4=" + totalGc4 + ", totalGc5=" + totalGc5 + ", fuelTypeCode=" + fuelTypeCode
				+ ", fuelTypeName=" + fuelTypeName + ", fuelGroupe=" + fuelGroupe + ", energySaleOrderLines="
				+ energySaleOrderLines + ", energyCharge=" + energyCharge + "]";
	}



	public EnergyAllotmentOrder(String id, String energySaleId, String sellerCompanyServiceId, String sellerEndOrgId,
			String year, String month, String injectingVoltageCode, String injectingVoltageName, String fromDate,
			String toDate, String createdDate, String loss, String multipleBuyers, String usageDetailAvail,
			String simpleEnergySale, String totalC1, String totalC2, String totalC3, String totalC4, String totalC5,
			String totalGenUnitsSold, String totalBc1, String totalBc2, String totalBc3, String totalBc4,
			String totalBc5, String totalBankingUnitsSold, String totalUnitsSold, String allowLowerSlotAdmt,
			String sellerCompanyName, String sellerCompanyCode, String sellerCompanyId,
			String sellerCompanyServiceNumber, String sellerCompanyServiceTypeCode, String sellerCompBankingServiceId,
			String sellerCompBankingServiceNumber, String sellerTlServiceId, String sellerTlServiceNumber,
			String sellerDlServiceId, String sellerDlServiceNumber, String sellerUnAdjustedServiceId,
			String sellerUnAdjustedServiceNumber, String sellerEndOrgName, String sellerEndOrgCode,
			String sellerEndFeederId, String sellerEndFeederName, String sellerEndFeederCode,
			String sellerEndSubstationId, String sellerEndSubstationName, String sellerEndSubstationCode,
			String totalGc1, String totalGc2, String totalGc3, String totalGc4, String totalGc5, String fuelTypeCode,
			String fuelTypeName, String fuelGroupe, List<EnergyAllotmentOrderLine> energySaleOrderLines,
			List<EnergyCharge> energyCharge) {
		super();
		this.id = id;
		this.energySaleId = energySaleId;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerEndOrgId = sellerEndOrgId;
		this.year = year;
		this.month = month;
		this.injectingVoltageCode = injectingVoltageCode;
		this.injectingVoltageName = injectingVoltageName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.createdDate = createdDate;
		this.loss = loss;
		this.multipleBuyers = multipleBuyers;
		this.usageDetailAvail = usageDetailAvail;
		this.simpleEnergySale = simpleEnergySale;
		this.totalC1 = totalC1;
		this.totalC2 = totalC2;
		this.totalC3 = totalC3;
		this.totalC4 = totalC4;
		this.totalC5 = totalC5;
		this.totalGenUnitsSold = totalGenUnitsSold;
		this.totalBc1 = totalBc1;
		this.totalBc2 = totalBc2;
		this.totalBc3 = totalBc3;
		this.totalBc4 = totalBc4;
		this.totalBc5 = totalBc5;
		this.totalBankingUnitsSold = totalBankingUnitsSold;
		this.totalUnitsSold = totalUnitsSold;
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompanyServiceTypeCode = sellerCompanyServiceTypeCode;
		this.sellerCompBankingServiceId = sellerCompBankingServiceId;
		this.sellerCompBankingServiceNumber = sellerCompBankingServiceNumber;
		this.sellerTlServiceId = sellerTlServiceId;
		this.sellerTlServiceNumber = sellerTlServiceNumber;
		this.sellerDlServiceId = sellerDlServiceId;
		this.sellerDlServiceNumber = sellerDlServiceNumber;
		this.sellerUnAdjustedServiceId = sellerUnAdjustedServiceId;
		this.sellerUnAdjustedServiceNumber = sellerUnAdjustedServiceNumber;
		this.sellerEndOrgName = sellerEndOrgName;
		this.sellerEndOrgCode = sellerEndOrgCode;
		this.sellerEndFeederId = sellerEndFeederId;
		this.sellerEndFeederName = sellerEndFeederName;
		this.sellerEndFeederCode = sellerEndFeederCode;
		this.sellerEndSubstationId = sellerEndSubstationId;
		this.sellerEndSubstationName = sellerEndSubstationName;
		this.sellerEndSubstationCode = sellerEndSubstationCode;
		this.totalGc1 = totalGc1;
		this.totalGc2 = totalGc2;
		this.totalGc3 = totalGc3;
		this.totalGc4 = totalGc4;
		this.totalGc5 = totalGc5;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.fuelGroupe = fuelGroupe;
		this.energySaleOrderLines = energySaleOrderLines;
		this.energyCharge = energyCharge;
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

	public String getSellerCompanyServiceId() {
		return sellerCompanyServiceId;
	}

	public void setSellerCompanyServiceId(String sellerCompanyServiceId) {
		this.sellerCompanyServiceId = sellerCompanyServiceId;
	}

	public String getSellerEndOrgId() {
		return sellerEndOrgId;
	}

	public void setSellerEndOrgId(String sellerEndOrgId) {
		this.sellerEndOrgId = sellerEndOrgId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getInjectingVoltageCode() {
		return injectingVoltageCode;
	}

	public void setInjectingVoltageCode(String injectingVoltageCode) {
		this.injectingVoltageCode = injectingVoltageCode;
	}

	public String getInjectingVoltageName() {
		return injectingVoltageName;
	}

	public void setInjectingVoltageName(String injectingVoltageName) {
		this.injectingVoltageName = injectingVoltageName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLoss() {
		return loss;
	}

	public void setLoss(String loss) {
		this.loss = loss;
	}

	public String getMultipleBuyers() {
		return multipleBuyers;
	}

	public void setMultipleBuyers(String multipleBuyers) {
		this.multipleBuyers = multipleBuyers;
	}

	public String getUsageDetailAvail() {
		return usageDetailAvail;
	}

	public void setUsageDetailAvail(String usageDetailAvail) {
		this.usageDetailAvail = usageDetailAvail;
	}

	public String getSimpleEnergySale() {
		return simpleEnergySale;
	}

	public void setSimpleEnergySale(String simpleEnergySale) {
		this.simpleEnergySale = simpleEnergySale;
	}

	public String getTotalC1() {
		return totalC1;
	}

	public void setTotalC1(String totalC1) {
		this.totalC1 = totalC1;
	}

	public String getTotalC2() {
		return totalC2;
	}

	public void setTotalC2(String totalC2) {
		this.totalC2 = totalC2;
	}

	public String getTotalC3() {
		return totalC3;
	}

	public void setTotalC3(String totalC3) {
		this.totalC3 = totalC3;
	}

	public String getTotalC4() {
		return totalC4;
	}

	public void setTotalC4(String totalC4) {
		this.totalC4 = totalC4;
	}

	public String getTotalC5() {
		return totalC5;
	}

	public void setTotalC5(String totalC5) {
		this.totalC5 = totalC5;
	}

	public String getTotalGenUnitsSold() {
		return totalGenUnitsSold;
	}

	public void setTotalGenUnitsSold(String totalGenUnitsSold) {
		this.totalGenUnitsSold = totalGenUnitsSold;
	}

	public String getTotalBc1() {
		return totalBc1;
	}

	public void setTotalBc1(String totalBc1) {
		this.totalBc1 = totalBc1;
	}

	public String getTotalBc2() {
		return totalBc2;
	}

	public void setTotalBc2(String totalBc2) {
		this.totalBc2 = totalBc2;
	}

	public String getTotalBc3() {
		return totalBc3;
	}

	public void setTotalBc3(String totalBc3) {
		this.totalBc3 = totalBc3;
	}

	public String getTotalBc4() {
		return totalBc4;
	}

	public void setTotalBc4(String totalBc4) {
		this.totalBc4 = totalBc4;
	}

	public String getTotalBc5() {
		return totalBc5;
	}

	public void setTotalBc5(String totalBc5) {
		this.totalBc5 = totalBc5;
	}

	public String getTotalBankingUnitsSold() {
		return totalBankingUnitsSold;
	}

	public void setTotalBankingUnitsSold(String totalBankingUnitsSold) {
		this.totalBankingUnitsSold = totalBankingUnitsSold;
	}

	public String getTotalUnitsSold() {
		return totalUnitsSold;
	}

	public void setTotalUnitsSold(String totalUnitsSold) {
		this.totalUnitsSold = totalUnitsSold;
	}

	public String getAllowLowerSlotAdmt() {
		return allowLowerSlotAdmt;
	}

	public void setAllowLowerSlotAdmt(String allowLowerSlotAdmt) {
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}

	public String getSellerCompanyName() {
		return sellerCompanyName;
	}

	public void setSellerCompanyName(String sellerCompanyName) {
		this.sellerCompanyName = sellerCompanyName;
	}

	public String getSellerCompanyCode() {
		return sellerCompanyCode;
	}

	public void setSellerCompanyCode(String sellerCompanyCode) {
		this.sellerCompanyCode = sellerCompanyCode;
	}

	public String getSellerCompanyId() {
		return sellerCompanyId;
	}

	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
	}

	public String getSellerCompanyServiceNumber() {
		return sellerCompanyServiceNumber;
	}

	public void setSellerCompanyServiceNumber(String sellerCompanyServiceNumber) {
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
	}

	public String getSellerCompanyServiceTypeCode() {
		return sellerCompanyServiceTypeCode;
	}

	public void setSellerCompanyServiceTypeCode(String sellerCompanyServiceTypeCode) {
		this.sellerCompanyServiceTypeCode = sellerCompanyServiceTypeCode;
	}

	public String getSellerCompBankingServiceId() {
		return sellerCompBankingServiceId;
	}

	public void setSellerCompBankingServiceId(String sellerCompBankingServiceId) {
		this.sellerCompBankingServiceId = sellerCompBankingServiceId;
	}

	public String getSellerCompBankingServiceNumber() {
		return sellerCompBankingServiceNumber;
	}

	public void setSellerCompBankingServiceNumber(String sellerCompBankingServiceNumber) {
		this.sellerCompBankingServiceNumber = sellerCompBankingServiceNumber;
	}

	public String getSellerTlServiceId() {
		return sellerTlServiceId;
	}

	public void setSellerTlServiceId(String sellerTlServiceId) {
		this.sellerTlServiceId = sellerTlServiceId;
	}

	public String getSellerTlServiceNumber() {
		return sellerTlServiceNumber;
	}

	public void setSellerTlServiceNumber(String sellerTlServiceNumber) {
		this.sellerTlServiceNumber = sellerTlServiceNumber;
	}

	public String getSellerDlServiceId() {
		return sellerDlServiceId;
	}

	public void setSellerDlServiceId(String sellerDlServiceId) {
		this.sellerDlServiceId = sellerDlServiceId;
	}

	public String getSellerDlServiceNumber() {
		return sellerDlServiceNumber;
	}

	public void setSellerDlServiceNumber(String sellerDlServiceNumber) {
		this.sellerDlServiceNumber = sellerDlServiceNumber;
	}

	public String getSellerUnAdjustedServiceId() {
		return sellerUnAdjustedServiceId;
	}

	public void setSellerUnAdjustedServiceId(String sellerUnAdjustedServiceId) {
		this.sellerUnAdjustedServiceId = sellerUnAdjustedServiceId;
	}

	public String getSellerUnAdjustedServiceNumber() {
		return sellerUnAdjustedServiceNumber;
	}

	public void setSellerUnAdjustedServiceNumber(String sellerUnAdjustedServiceNumber) {
		this.sellerUnAdjustedServiceNumber = sellerUnAdjustedServiceNumber;
	}

	public String getSellerEndOrgName() {
		return sellerEndOrgName;
	}

	public void setSellerEndOrgName(String sellerEndOrgName) {
		this.sellerEndOrgName = sellerEndOrgName;
	}

	public String getSellerEndOrgCode() {
		return sellerEndOrgCode;
	}

	public void setSellerEndOrgCode(String sellerEndOrgCode) {
		this.sellerEndOrgCode = sellerEndOrgCode;
	}

	public String getSellerEndFeederId() {
		return sellerEndFeederId;
	}

	public void setSellerEndFeederId(String sellerEndFeederId) {
		this.sellerEndFeederId = sellerEndFeederId;
	}

	public String getSellerEndFeederName() {
		return sellerEndFeederName;
	}

	public void setSellerEndFeederName(String sellerEndFeederName) {
		this.sellerEndFeederName = sellerEndFeederName;
	}

	public String getSellerEndFeederCode() {
		return sellerEndFeederCode;
	}

	public void setSellerEndFeederCode(String sellerEndFeederCode) {
		this.sellerEndFeederCode = sellerEndFeederCode;
	}

	public String getSellerEndSubstationId() {
		return sellerEndSubstationId;
	}

	public void setSellerEndSubstationId(String sellerEndSubstationId) {
		this.sellerEndSubstationId = sellerEndSubstationId;
	}

	public String getSellerEndSubstationName() {
		return sellerEndSubstationName;
	}

	public void setSellerEndSubstationName(String sellerEndSubstationName) {
		this.sellerEndSubstationName = sellerEndSubstationName;
	}

	public String getSellerEndSubstationCode() {
		return sellerEndSubstationCode;
	}

	public void setSellerEndSubstationCode(String sellerEndSubstationCode) {
		this.sellerEndSubstationCode = sellerEndSubstationCode;
	}

	public String getTotalGc1() {
		return totalGc1;
	}

	public void setTotalGc1(String totalGc1) {
		this.totalGc1 = totalGc1;
	}

	public String getTotalGc2() {
		return totalGc2;
	}

	public void setTotalGc2(String totalGc2) {
		this.totalGc2 = totalGc2;
	}

	public String getTotalGc3() {
		return totalGc3;
	}

	public void setTotalGc3(String totalGc3) {
		this.totalGc3 = totalGc3;
	}

	public String getTotalGc4() {
		return totalGc4;
	}

	public void setTotalGc4(String totalGc4) {
		this.totalGc4 = totalGc4;
	}

	public String getTotalGc5() {
		return totalGc5;
	}

	public void setTotalGc5(String totalGc5) {
		this.totalGc5 = totalGc5;
	}

	
	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeName() {
		return fuelTypeName;
	}

	public void setFuelTypeName(String fuelTypeName) {
		this.fuelTypeName = fuelTypeName;
	}

	public String getFuelGroupe() {
		return fuelGroupe;
	}

	public void setFuelGroupe(String fuelGroupe) {
		this.fuelGroupe = fuelGroupe;
	}

	public List<EnergyAllotmentOrderLine> getEnergySaleOrderLines() {
		return energySaleOrderLines;
	}

	public void setEnergySaleOrderLines(List<EnergyAllotmentOrderLine> energySaleOrderLines) {
		this.energySaleOrderLines = energySaleOrderLines;
	}

	public List<EnergyCharge> getEnergyCharge() {
		return energyCharge;
	}

	public void setEnergyCharge(List<EnergyCharge> energyCharge) {
		this.energyCharge = energyCharge;
	}	
	
}
