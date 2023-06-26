package com.ss.oa.oadocumentservice.vo;

public class EnergyAllotmentOrderLine {
	
	private String id;
	private String energySaleOrderId;
	private String energySaleId;
	private String buyerEndOrgId,buyerEndOrgName,buyerEndOrgCode;
	private String buyerEndSsId,buyerEndSsName,buyerEndSsCode;
	private String buyerEndFeederId,buyerEndFeederName,buyerEndFeederCode;
	private String buyerCompanyServiceId,buyerCompanyServiceNumber,companyServiceTypeCode;
	private String c1,c2,c3,c4,c5;
	private String bc1,bc2,bc3,bc4,bc5;
	private String gc1,gc2,gc3,gc4,gc5;
	private String bankingUnitsSold;
	private String totalUnitsSold;
	private String genUnitsSold;
	private String drawalVoltageCode;
	private String drawalVoltageName;
	private String buyerCompanyName,buyerCompanyCode,buyerCompanyId;
	private String buyerCompBankingServiceId,buyerCompBankingServiceNumber,buyerTlServiceId,buyerTlServiceNumber,buyerDlServiceId,buyerDlServiceNumber;
	private String buyerUnAdjustedServiceId,buyerUnAdjustedServiceNumber;
	private String unitCost,totalAmountPayable,totalAmountChargable,netAmountPayable;
	
	public EnergyAllotmentOrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnergyAllotmentOrderLine(String id, String energySaleOrderId, String energySaleId, String buyerEndOrgId,
			String buyerEndOrgName, String buyerEndOrgCode, String buyerEndSsId, String buyerEndSsName,
			String buyerEndSsCode, String buyerEndFeederId, String buyerEndFeederName, String buyerEndFeederCode,
			String buyerCompanyServiceId, String buyerCompanyServiceNumber, String companyServiceTypeCode, String c1,
			String c2, String c3, String c4, String c5, String bc1, String bc2, String bc3, String bc4, String bc5,
			String gc1, String gc2, String gc3, String gc4, String gc5, String bankingUnitsSold, String totalUnitsSold,
			String genUnitsSold, String drawalVoltageCode, String drawalVoltageName, String buyerCompanyName,
			String buyerCompanyCode, String buyerCompanyId, String buyerCompBankingServiceId,
			String buyerCompBankingServiceNumber, String buyerTlServiceId, String buyerTlServiceNumber,
			String buyerDlServiceId, String buyerDlServiceNumber, String buyerUnAdjustedServiceId,
			String buyerUnAdjustedServiceNumber, String unitCost, String totalAmountPayable,
			String totalAmountChargable, String netAmountPayable) {
		super();
		this.id = id;
		this.energySaleOrderId = energySaleOrderId;
		this.energySaleId = energySaleId;
		this.buyerEndOrgId = buyerEndOrgId;
		this.buyerEndOrgName = buyerEndOrgName;
		this.buyerEndOrgCode = buyerEndOrgCode;
		this.buyerEndSsId = buyerEndSsId;
		this.buyerEndSsName = buyerEndSsName;
		this.buyerEndSsCode = buyerEndSsCode;
		this.buyerEndFeederId = buyerEndFeederId;
		this.buyerEndFeederName = buyerEndFeederName;
		this.buyerEndFeederCode = buyerEndFeederCode;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.companyServiceTypeCode = companyServiceTypeCode;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.bc1 = bc1;
		this.bc2 = bc2;
		this.bc3 = bc3;
		this.bc4 = bc4;
		this.bc5 = bc5;
		this.gc1 = gc1;
		this.gc2 = gc2;
		this.gc3 = gc3;
		this.gc4 = gc4;
		this.gc5 = gc5;
		this.bankingUnitsSold = bankingUnitsSold;
		this.totalUnitsSold = totalUnitsSold;
		this.genUnitsSold = genUnitsSold;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageName = drawalVoltageName;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompBankingServiceId = buyerCompBankingServiceId;
		this.buyerCompBankingServiceNumber = buyerCompBankingServiceNumber;
		this.buyerTlServiceId = buyerTlServiceId;
		this.buyerTlServiceNumber = buyerTlServiceNumber;
		this.buyerDlServiceId = buyerDlServiceId;
		this.buyerDlServiceNumber = buyerDlServiceNumber;
		this.buyerUnAdjustedServiceId = buyerUnAdjustedServiceId;
		this.buyerUnAdjustedServiceNumber = buyerUnAdjustedServiceNumber;
		this.unitCost = unitCost;
		this.totalAmountPayable = totalAmountPayable;
		this.totalAmountChargable = totalAmountChargable;
		this.netAmountPayable = netAmountPayable;
	}

	@Override
	public String toString() {
		return "EnergyAllotmentOrderLine [id=" + id + ", energySaleOrderId=" + energySaleOrderId + ", energySaleId="
				+ energySaleId + ", buyerEndOrgId=" + buyerEndOrgId + ", buyerEndOrgName=" + buyerEndOrgName
				+ ", buyerEndOrgCode=" + buyerEndOrgCode + ", buyerEndSsId=" + buyerEndSsId + ", buyerEndSsName="
				+ buyerEndSsName + ", buyerEndSsCode=" + buyerEndSsCode + ", buyerEndFeederId=" + buyerEndFeederId
				+ ", buyerEndFeederName=" + buyerEndFeederName + ", buyerEndFeederCode=" + buyerEndFeederCode
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyServiceNumber="
				+ buyerCompanyServiceNumber + ", companyServiceTypeCode=" + companyServiceTypeCode + ", c1=" + c1
				+ ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", bc1=" + bc1 + ", bc2=" + bc2 + ", bc3="
				+ bc3 + ", bc4=" + bc4 + ", bc5=" + bc5 + ", gc1=" + gc1 + ", gc2=" + gc2 + ", gc3=" + gc3 + ", gc4="
				+ gc4 + ", gc5=" + gc5 + ", bankingUnitsSold=" + bankingUnitsSold + ", totalUnitsSold=" + totalUnitsSold
				+ ", genUnitsSold=" + genUnitsSold + ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageName="
				+ drawalVoltageName + ", buyerCompanyName=" + buyerCompanyName + ", buyerCompanyCode="
				+ buyerCompanyCode + ", buyerCompanyId=" + buyerCompanyId + ", buyerCompBankingServiceId="
				+ buyerCompBankingServiceId + ", buyerCompBankingServiceNumber=" + buyerCompBankingServiceNumber
				+ ", buyerTlServiceId=" + buyerTlServiceId + ", buyerTlServiceNumber=" + buyerTlServiceNumber
				+ ", buyerDlServiceId=" + buyerDlServiceId + ", buyerDlServiceNumber=" + buyerDlServiceNumber
				+ ", buyerUnAdjustedServiceId=" + buyerUnAdjustedServiceId + ", buyerUnAdjustedServiceNumber="
				+ buyerUnAdjustedServiceNumber + ", unitCost=" + unitCost + ", totalAmountPayable=" + totalAmountPayable
				+ ", totalAmountChargable=" + totalAmountChargable + ", netAmountPayable=" + netAmountPayable + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnergySaleOrderId() {
		return energySaleOrderId;
	}

	public void setEnergySaleOrderId(String energySaleOrderId) {
		this.energySaleOrderId = energySaleOrderId;
	}

	public String getEnergySaleId() {
		return energySaleId;
	}

	public void setEnergySaleId(String energySaleId) {
		this.energySaleId = energySaleId;
	}

	public String getBuyerEndOrgId() {
		return buyerEndOrgId;
	}

	public void setBuyerEndOrgId(String buyerEndOrgId) {
		this.buyerEndOrgId = buyerEndOrgId;
	}

	public String getBuyerEndOrgName() {
		return buyerEndOrgName;
	}

	public void setBuyerEndOrgName(String buyerEndOrgName) {
		this.buyerEndOrgName = buyerEndOrgName;
	}

	public String getBuyerEndOrgCode() {
		return buyerEndOrgCode;
	}

	public void setBuyerEndOrgCode(String buyerEndOrgCode) {
		this.buyerEndOrgCode = buyerEndOrgCode;
	}

	public String getBuyerEndSsId() {
		return buyerEndSsId;
	}

	public void setBuyerEndSsId(String buyerEndSsId) {
		this.buyerEndSsId = buyerEndSsId;
	}

	public String getBuyerEndSsName() {
		return buyerEndSsName;
	}

	public void setBuyerEndSsName(String buyerEndSsName) {
		this.buyerEndSsName = buyerEndSsName;
	}

	public String getBuyerEndSsCode() {
		return buyerEndSsCode;
	}

	public void setBuyerEndSsCode(String buyerEndSsCode) {
		this.buyerEndSsCode = buyerEndSsCode;
	}

	public String getBuyerEndFeederId() {
		return buyerEndFeederId;
	}

	public void setBuyerEndFeederId(String buyerEndFeederId) {
		this.buyerEndFeederId = buyerEndFeederId;
	}

	public String getBuyerEndFeederName() {
		return buyerEndFeederName;
	}

	public void setBuyerEndFeederName(String buyerEndFeederName) {
		this.buyerEndFeederName = buyerEndFeederName;
	}

	public String getBuyerEndFeederCode() {
		return buyerEndFeederCode;
	}

	public void setBuyerEndFeederCode(String buyerEndFeederCode) {
		this.buyerEndFeederCode = buyerEndFeederCode;
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

	public String getCompanyServiceTypeCode() {
		return companyServiceTypeCode;
	}

	public void setCompanyServiceTypeCode(String companyServiceTypeCode) {
		this.companyServiceTypeCode = companyServiceTypeCode;
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

	public String getBc1() {
		return bc1;
	}

	public void setBc1(String bc1) {
		this.bc1 = bc1;
	}

	public String getBc2() {
		return bc2;
	}

	public void setBc2(String bc2) {
		this.bc2 = bc2;
	}

	public String getBc3() {
		return bc3;
	}

	public void setBc3(String bc3) {
		this.bc3 = bc3;
	}

	public String getBc4() {
		return bc4;
	}

	public void setBc4(String bc4) {
		this.bc4 = bc4;
	}

	public String getBc5() {
		return bc5;
	}

	public void setBc5(String bc5) {
		this.bc5 = bc5;
	}

	public String getGc1() {
		return gc1;
	}

	public void setGc1(String gc1) {
		this.gc1 = gc1;
	}

	public String getGc2() {
		return gc2;
	}

	public void setGc2(String gc2) {
		this.gc2 = gc2;
	}

	public String getGc3() {
		return gc3;
	}

	public void setGc3(String gc3) {
		this.gc3 = gc3;
	}

	public String getGc4() {
		return gc4;
	}

	public void setGc4(String gc4) {
		this.gc4 = gc4;
	}

	public String getGc5() {
		return gc5;
	}

	public void setGc5(String gc5) {
		this.gc5 = gc5;
	}

	public String getBankingUnitsSold() {
		return bankingUnitsSold;
	}

	public void setBankingUnitsSold(String bankingUnitsSold) {
		this.bankingUnitsSold = bankingUnitsSold;
	}

	public String getTotalUnitsSold() {
		return totalUnitsSold;
	}

	public void setTotalUnitsSold(String totalUnitsSold) {
		this.totalUnitsSold = totalUnitsSold;
	}

	public String getGenUnitsSold() {
		return genUnitsSold;
	}

	public void setGenUnitsSold(String genUnitsSold) {
		this.genUnitsSold = genUnitsSold;
	}

	public String getDrawalVoltageCode() {
		return drawalVoltageCode;
	}

	public void setDrawalVoltageCode(String drawalVoltageCode) {
		this.drawalVoltageCode = drawalVoltageCode;
	}

	public String getDrawalVoltageName() {
		return drawalVoltageName;
	}

	public void setDrawalVoltageName(String drawalVoltageName) {
		this.drawalVoltageName = drawalVoltageName;
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

	public String getBuyerCompanyId() {
		return buyerCompanyId;
	}

	public void setBuyerCompanyId(String buyerCompanyId) {
		this.buyerCompanyId = buyerCompanyId;
	}

	public String getBuyerCompBankingServiceId() {
		return buyerCompBankingServiceId;
	}

	public void setBuyerCompBankingServiceId(String buyerCompBankingServiceId) {
		this.buyerCompBankingServiceId = buyerCompBankingServiceId;
	}

	public String getBuyerCompBankingServiceNumber() {
		return buyerCompBankingServiceNumber;
	}

	public void setBuyerCompBankingServiceNumber(String buyerCompBankingServiceNumber) {
		this.buyerCompBankingServiceNumber = buyerCompBankingServiceNumber;
	}

	public String getBuyerTlServiceId() {
		return buyerTlServiceId;
	}

	public void setBuyerTlServiceId(String buyerTlServiceId) {
		this.buyerTlServiceId = buyerTlServiceId;
	}

	public String getBuyerTlServiceNumber() {
		return buyerTlServiceNumber;
	}

	public void setBuyerTlServiceNumber(String buyerTlServiceNumber) {
		this.buyerTlServiceNumber = buyerTlServiceNumber;
	}

	public String getBuyerDlServiceId() {
		return buyerDlServiceId;
	}

	public void setBuyerDlServiceId(String buyerDlServiceId) {
		this.buyerDlServiceId = buyerDlServiceId;
	}

	public String getBuyerDlServiceNumber() {
		return buyerDlServiceNumber;
	}

	public void setBuyerDlServiceNumber(String buyerDlServiceNumber) {
		this.buyerDlServiceNumber = buyerDlServiceNumber;
	}

	public String getBuyerUnAdjustedServiceId() {
		return buyerUnAdjustedServiceId;
	}

	public void setBuyerUnAdjustedServiceId(String buyerUnAdjustedServiceId) {
		this.buyerUnAdjustedServiceId = buyerUnAdjustedServiceId;
	}

	public String getBuyerUnAdjustedServiceNumber() {
		return buyerUnAdjustedServiceNumber;
	}

	public void setBuyerUnAdjustedServiceNumber(String buyerUnAdjustedServiceNumber) {
		this.buyerUnAdjustedServiceNumber = buyerUnAdjustedServiceNumber;
	}

	public String getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(String unitCost) {
		this.unitCost = unitCost;
	}

	public String getTotalAmountPayable() {
		return totalAmountPayable;
	}

	public void setTotalAmountPayable(String totalAmountPayable) {
		this.totalAmountPayable = totalAmountPayable;
	}

	public String getTotalAmountChargable() {
		return totalAmountChargable;
	}

	public void setTotalAmountChargable(String totalAmountChargable) {
		this.totalAmountChargable = totalAmountChargable;
	}

	public String getNetAmountPayable() {
		return netAmountPayable;
	}

	public void setNetAmountPayable(String netAmountPayable) {
		this.netAmountPayable = netAmountPayable;
	}
	
	

}
