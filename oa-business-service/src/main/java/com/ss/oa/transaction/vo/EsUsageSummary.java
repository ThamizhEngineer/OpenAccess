package com.ss.oa.transaction.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

@Table(name = "T_ES_USAGE_SUMMARY")
@CreationTimestamp
@UpdateTimestamp
@Entity
@Scope("prototype")
public class EsUsageSummary {
	
	  @Id
	  @Column(name="ID")
	  private String id;
	  @Column(name="T_ENERGY_SALE_ID")
	  private String energySaleId;
	  @Formula("(SELECT tr.id FROM T_ES_USAGE_SUMMARY su JOIN M_TRADE_RELATIONSHIP tr ON su.M_TRADE_RELATIONSHIP_ID=tr.ID WHERE su.ID=id)")
	  private String tradeRelationshipId;
	  @Formula("(SELECT tr.QUANTUM FROM T_ES_USAGE_SUMMARY su JOIN M_TRADE_RELATIONSHIP tr ON su.M_TRADE_RELATIONSHIP_ID=tr.ID WHERE su.ID=id)")
	  private String quantum;
	  @Formula("(SELECT ms.M_ORG_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndOrgId;
	  @Formula("(SELECT ms.M_ORG_NAME FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndOrgName;
	  @Formula("(SELECT ms.M_ORG_CODE FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndOrgCode;
	  @Column(name="BUYER_COMP_SERV_ID")
	  private String buyerCompanyServiceId;
	  @Formula("(SELECT ms.M_COMP_SERV_NUMBER FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompanyServiceNumber;
	  @Formula("(SELECT ms.m_company_id FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompanyId;
	  @Formula("(SELECT ms.m_company_name FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompanyName;
	  @Formula("(SELECT ms.m_company_CODE FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompanyCode;
	  @Formula("(SELECT ms.M_SUBSTATION_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndSsId;
	  @Formula("(SELECT ms.M_SUBSTATION_NAME FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndSsName;
	  @Formula("(SELECT ms.M_SUBSTATION_CODE FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndSsCode;
	  @Formula("(SELECT ms.M_FEEDER_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndFeederId;
	  @Formula("(SELECT ms.M_FEEDER_NAME FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndFeederName;
	  @Formula("(SELECT ms.M_FEEDER_CODE FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerEndFeederCode;
	  @Formula("(SELECT ms.BANKING_SERVICE_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompBankingServiceId;
	  @Formula("(SELECT ms.BANKING_SERVICE_NUMBER FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerCompBankingServiceNumber;
	  @Formula("(SELECT ms.TL_SERVICE_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerTlServiceId;
	  @Formula("(SELECT ms.TL_SERVICE_NUMBER FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerTlServiceNumber;
	  @Formula("(SELECT ms.DL_SERVICE_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerDlServiceId;
	  @Formula("(SELECT ms.DL_SERVICE_NUMBER FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerDlServiceNumber;
	  @Formula("(SELECT ms.UNADJUSTED_SERVICE_ID FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerUnAdjustedServiceId;
	  @Formula("(SELECT ms.UNADJUSTED_SERVICE_NUMBER FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String buyerUnAdjustedServiceNumber;
	  @Formula("(SELECT ms.VOLTAGE_CODE FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String drawalVoltageCode;
	  @Formula("(SELECT ms.VOLTAGE_NAME FROM T_ES_USAGE_SUMMARY su JOIN V_COMPANY_SERVICE ms ON su.BUYER_COMP_SERV_ID =ms.id WHERE su.ID=id)")
	  private String drawalVoltageName;
	  @Formula("(SELECT tr.INTERVAL_TYPE_CODE FROM T_ES_USAGE_SUMMARY su JOIN M_TRADE_RELATIONSHIP tr ON su.M_TRADE_RELATIONSHIP_ID =tr.ID WHERE su.ID=id)")
	  private String intervalTypeCode;
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
	  @Column(name="REMARKS")
	  private String remarks;
//	  @Formula("(SELECT ch.ID FROM T_ES_USAGE_SUMMARY su JOIN T_ES_CHARGE ch ON su.BUYER_COMP_SERV_ID =ch.M_COMP_SERV_ID WHERE su.ID=id)")
	  @Transient
	  private String c001Id;
	  @Transient
	  private String c001ChargeCode,c001ChargeDesc,c001TotalCharge;
	  @Transient
	  private String c002Id,c002ChargeCode,c002ChargeDesc,c002TotalCharge;
	  @Transient
	  private String c003Id,c003ChargeCode,c003ChargeDesc,c003TotalCharge;
	  @Transient
	  private String c004Id,c004ChargeCode,c004ChargeDesc,c004TotalCharge;
	  @Transient
	  private String c005Id,c005ChargeCode,c005ChargeDesc,c005TotalCharge;
	  @Transient
	  private String c006Id,c006ChargeCode,c006ChargeDesc,c006TotalCharge;
	  @Transient
	  private String c007Id,c007ChargeCode,c007ChargeDesc,c007TotalCharge;
	  @Transient
	  private String c008Id,c008ChargeCode,c008ChargeDesc,c008TotalCharge;
	  @Transient
	  private String c009Id,c009ChargeCode,c009ChargeDesc,c009TotalCharge;
	  @Column(name="UNIT_COST")
	  private String unitCost;
	  @Column(name="TOTAL_AMOUNT_PAYABLE")
	  private String totalAmountPayable;
	  @Column(name="TOTAL_AMOUNT_CHARGABLE")
	  private String totalAmountChargable;
	  @Column(name="NET_AMOUNT_PAYABLE")
	  private String netAmountPayable;
	  @Formula("(SELECT tr.AGREEMENT_TYPE FROM T_ES_USAGE_SUMMARY su JOIN M_TRADE_RELATIONSHIP tr ON su.M_TRADE_RELATIONSHIP_ID=tr.ID WHERE su.ID=id)")
	  private String agreementType;
	  @Transient
	  private String slot1;
	  @Transient
	  private String slot2;
	  @Transient
	  private String slot3;
	  @Transient
	  private String slot4;
	  @Transient
	  private String slot5;
	  @Transient
	  private String resultCode;
	  @Transient
	  private String resultDesc;
	
	  
	  public EsUsageSummary() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EsUsageSummary(String id, String energySaleId, String tradeRelationshipId, String quantum,
			String buyerEndOrgId, String buyerEndOrgName, String buyerEndOrgCode, String buyerCompanyServiceId,
			String buyerCompanyServiceNumber, String buyerCompanyId, String buyerCompanyName, String buyerCompanyCode,
			String buyerEndSsId, String buyerEndSsName, String buyerEndSsCode, String buyerEndFeederId,
			String buyerEndFeederName, String buyerEndFeederCode, String buyerCompBankingServiceId,
			String buyerCompBankingServiceNumber, String buyerTlServiceId, String buyerTlServiceNumber,
			String buyerDlServiceId, String buyerDlServiceNumber, String buyerUnAdjustedServiceId,
			String buyerUnAdjustedServiceNumber, String drawalVoltageCode, String drawalVoltageName,
			String intervalTypeCode, String c1, String c2, String c3, String c4, String c5, String total,
			String remarks, String c001Id, String c001ChargeCode, String c001ChargeDesc, String c001TotalCharge,
			String c002Id, String c002ChargeCode, String c002ChargeDesc, String c002TotalCharge, String c003Id,
			String c003ChargeCode, String c003ChargeDesc, String c003TotalCharge, String c004Id, String c004ChargeCode,
			String c004ChargeDesc, String c004TotalCharge, String c005Id, String c005ChargeCode, String c005ChargeDesc,
			String c005TotalCharge, String c006Id, String c006ChargeCode, String c006ChargeDesc, String c006TotalCharge,
			String c007Id, String c007ChargeCode, String c007ChargeDesc, String c007TotalCharge, String c008Id,
			String c008ChargeCode, String c008ChargeDesc, String c008TotalCharge, String c009Id, String c009ChargeCode,
			String c009ChargeDesc, String c009TotalCharge, String unitCost, String totalAmountPayable,
			String totalAmountChargable, String netAmountPayable, String agreementType, String slot1, String slot2,
			String slot3, String slot4, String slot5, String resultCode, String resultDesc) {
		super();
		this.id = id;
		this.energySaleId = energySaleId;
		this.tradeRelationshipId = tradeRelationshipId;
		this.quantum = quantum;
		this.buyerEndOrgId = buyerEndOrgId;
		this.buyerEndOrgName = buyerEndOrgName;
		this.buyerEndOrgCode = buyerEndOrgCode;
		this.buyerCompanyServiceId = buyerCompanyServiceId;
		this.buyerCompanyServiceNumber = buyerCompanyServiceNumber;
		this.buyerCompanyId = buyerCompanyId;
		this.buyerCompanyName = buyerCompanyName;
		this.buyerCompanyCode = buyerCompanyCode;
		this.buyerEndSsId = buyerEndSsId;
		this.buyerEndSsName = buyerEndSsName;
		this.buyerEndSsCode = buyerEndSsCode;
		this.buyerEndFeederId = buyerEndFeederId;
		this.buyerEndFeederName = buyerEndFeederName;
		this.buyerEndFeederCode = buyerEndFeederCode;
		this.buyerCompBankingServiceId = buyerCompBankingServiceId;
		this.buyerCompBankingServiceNumber = buyerCompBankingServiceNumber;
		this.buyerTlServiceId = buyerTlServiceId;
		this.buyerTlServiceNumber = buyerTlServiceNumber;
		this.buyerDlServiceId = buyerDlServiceId;
		this.buyerDlServiceNumber = buyerDlServiceNumber;
		this.buyerUnAdjustedServiceId = buyerUnAdjustedServiceId;
		this.buyerUnAdjustedServiceNumber = buyerUnAdjustedServiceNumber;
		this.drawalVoltageCode = drawalVoltageCode;
		this.drawalVoltageName = drawalVoltageName;
		this.intervalTypeCode = intervalTypeCode;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.total = total;
		this.remarks = remarks;
		this.c001Id = c001Id;
		this.c001ChargeCode = c001ChargeCode;
		this.c001ChargeDesc = c001ChargeDesc;
		this.c001TotalCharge = c001TotalCharge;
		this.c002Id = c002Id;
		this.c002ChargeCode = c002ChargeCode;
		this.c002ChargeDesc = c002ChargeDesc;
		this.c002TotalCharge = c002TotalCharge;
		this.c003Id = c003Id;
		this.c003ChargeCode = c003ChargeCode;
		this.c003ChargeDesc = c003ChargeDesc;
		this.c003TotalCharge = c003TotalCharge;
		this.c004Id = c004Id;
		this.c004ChargeCode = c004ChargeCode;
		this.c004ChargeDesc = c004ChargeDesc;
		this.c004TotalCharge = c004TotalCharge;
		this.c005Id = c005Id;
		this.c005ChargeCode = c005ChargeCode;
		this.c005ChargeDesc = c005ChargeDesc;
		this.c005TotalCharge = c005TotalCharge;
		this.c006Id = c006Id;
		this.c006ChargeCode = c006ChargeCode;
		this.c006ChargeDesc = c006ChargeDesc;
		this.c006TotalCharge = c006TotalCharge;
		this.c007Id = c007Id;
		this.c007ChargeCode = c007ChargeCode;
		this.c007ChargeDesc = c007ChargeDesc;
		this.c007TotalCharge = c007TotalCharge;
		this.c008Id = c008Id;
		this.c008ChargeCode = c008ChargeCode;
		this.c008ChargeDesc = c008ChargeDesc;
		this.c008TotalCharge = c008TotalCharge;
		this.c009Id = c009Id;
		this.c009ChargeCode = c009ChargeCode;
		this.c009ChargeDesc = c009ChargeDesc;
		this.c009TotalCharge = c009TotalCharge;
		this.unitCost = unitCost;
		this.totalAmountPayable = totalAmountPayable;
		this.totalAmountChargable = totalAmountChargable;
		this.netAmountPayable = netAmountPayable;
		this.agreementType = agreementType;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
		this.slot4 = slot4;
		this.slot5 = slot5;
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}


	@Override
	public String toString() {
		return "EsUsageSummary [id=" + id + ", energySaleId=" + energySaleId + ", tradeRelationshipId="
				+ tradeRelationshipId + ", quantum=" + quantum + ", buyerEndOrgId=" + buyerEndOrgId
				+ ", buyerEndOrgName=" + buyerEndOrgName + ", buyerEndOrgCode=" + buyerEndOrgCode
				+ ", buyerCompanyServiceId=" + buyerCompanyServiceId + ", buyerCompanyServiceNumber="
				+ buyerCompanyServiceNumber + ", buyerCompanyId=" + buyerCompanyId + ", buyerCompanyName="
				+ buyerCompanyName + ", buyerCompanyCode=" + buyerCompanyCode + ", buyerEndSsId=" + buyerEndSsId
				+ ", buyerEndSsName=" + buyerEndSsName + ", buyerEndSsCode=" + buyerEndSsCode + ", buyerEndFeederId="
				+ buyerEndFeederId + ", buyerEndFeederName=" + buyerEndFeederName + ", buyerEndFeederCode="
				+ buyerEndFeederCode + ", buyerCompBankingServiceId=" + buyerCompBankingServiceId
				+ ", buyerCompBankingServiceNumber=" + buyerCompBankingServiceNumber + ", buyerTlServiceId="
				+ buyerTlServiceId + ", buyerTlServiceNumber=" + buyerTlServiceNumber + ", buyerDlServiceId="
				+ buyerDlServiceId + ", buyerDlServiceNumber=" + buyerDlServiceNumber + ", buyerUnAdjustedServiceId="
				+ buyerUnAdjustedServiceId + ", buyerUnAdjustedServiceNumber=" + buyerUnAdjustedServiceNumber
				+ ", drawalVoltageCode=" + drawalVoltageCode + ", drawalVoltageName=" + drawalVoltageName
				+ ", intervalTypeCode=" + intervalTypeCode + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4
				+ ", c5=" + c5 + ", total=" + total + ", remarks=" + remarks + ", c001Id=" + c001Id
				+ ", c001ChargeCode=" + c001ChargeCode + ", c001ChargeDesc=" + c001ChargeDesc + ", c001TotalCharge="
				+ c001TotalCharge + ", c002Id=" + c002Id + ", c002ChargeCode=" + c002ChargeCode + ", c002ChargeDesc="
				+ c002ChargeDesc + ", c002TotalCharge=" + c002TotalCharge + ", c003Id=" + c003Id + ", c003ChargeCode="
				+ c003ChargeCode + ", c003ChargeDesc=" + c003ChargeDesc + ", c003TotalCharge=" + c003TotalCharge
				+ ", c004Id=" + c004Id + ", c004ChargeCode=" + c004ChargeCode + ", c004ChargeDesc=" + c004ChargeDesc
				+ ", c004TotalCharge=" + c004TotalCharge + ", c005Id=" + c005Id + ", c005ChargeCode=" + c005ChargeCode
				+ ", c005ChargeDesc=" + c005ChargeDesc + ", c005TotalCharge=" + c005TotalCharge + ", c006Id=" + c006Id
				+ ", c006ChargeCode=" + c006ChargeCode + ", c006ChargeDesc=" + c006ChargeDesc + ", c006TotalCharge="
				+ c006TotalCharge + ", c007Id=" + c007Id + ", c007ChargeCode=" + c007ChargeCode + ", c007ChargeDesc="
				+ c007ChargeDesc + ", c007TotalCharge=" + c007TotalCharge + ", c008Id=" + c008Id + ", c008ChargeCode="
				+ c008ChargeCode + ", c008ChargeDesc=" + c008ChargeDesc + ", c008TotalCharge=" + c008TotalCharge
				+ ", c009Id=" + c009Id + ", c009ChargeCode=" + c009ChargeCode + ", c009ChargeDesc=" + c009ChargeDesc
				+ ", c009TotalCharge=" + c009TotalCharge + ", unitCost=" + unitCost + ", totalAmountPayable="
				+ totalAmountPayable + ", totalAmountChargable=" + totalAmountChargable + ", netAmountPayable="
				+ netAmountPayable + ", agreementType=" + agreementType + ", slot1=" + slot1 + ", slot2=" + slot2
				+ ", slot3=" + slot3 + ", slot4=" + slot4 + ", slot5=" + slot5 + ", resultCode=" + resultCode
				+ ", resultDesc=" + resultDesc + "]";
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


	public String getTradeRelationshipId() {
		return tradeRelationshipId;
	}


	public void setTradeRelationshipId(String tradeRelationshipId) {
		this.tradeRelationshipId = tradeRelationshipId;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
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


	public String getIntervalTypeCode() {
		return intervalTypeCode;
	}


	public void setIntervalTypeCode(String intervalTypeCode) {
		this.intervalTypeCode = intervalTypeCode;
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


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getC001Id() {
		return c001Id;
	}


	public void setC001Id(String c001Id) {
		this.c001Id = c001Id;
	}


	public String getC001ChargeCode() {
		return c001ChargeCode;
	}


	public void setC001ChargeCode(String c001ChargeCode) {
		this.c001ChargeCode = c001ChargeCode;
	}


	public String getC001ChargeDesc() {
		return c001ChargeDesc;
	}


	public void setC001ChargeDesc(String c001ChargeDesc) {
		this.c001ChargeDesc = c001ChargeDesc;
	}


	public String getC001TotalCharge() {
		return c001TotalCharge;
	}


	public void setC001TotalCharge(String c001TotalCharge) {
		this.c001TotalCharge = c001TotalCharge;
	}


	public String getC002Id() {
		return c002Id;
	}


	public void setC002Id(String c002Id) {
		this.c002Id = c002Id;
	}


	public String getC002ChargeCode() {
		return c002ChargeCode;
	}


	public void setC002ChargeCode(String c002ChargeCode) {
		this.c002ChargeCode = c002ChargeCode;
	}


	public String getC002ChargeDesc() {
		return c002ChargeDesc;
	}


	public void setC002ChargeDesc(String c002ChargeDesc) {
		this.c002ChargeDesc = c002ChargeDesc;
	}


	public String getC002TotalCharge() {
		return c002TotalCharge;
	}


	public void setC002TotalCharge(String c002TotalCharge) {
		this.c002TotalCharge = c002TotalCharge;
	}


	public String getC003Id() {
		return c003Id;
	}


	public void setC003Id(String c003Id) {
		this.c003Id = c003Id;
	}


	public String getC003ChargeCode() {
		return c003ChargeCode;
	}


	public void setC003ChargeCode(String c003ChargeCode) {
		this.c003ChargeCode = c003ChargeCode;
	}


	public String getC003ChargeDesc() {
		return c003ChargeDesc;
	}


	public void setC003ChargeDesc(String c003ChargeDesc) {
		this.c003ChargeDesc = c003ChargeDesc;
	}


	public String getC003TotalCharge() {
		return c003TotalCharge;
	}


	public void setC003TotalCharge(String c003TotalCharge) {
		this.c003TotalCharge = c003TotalCharge;
	}


	public String getC004Id() {
		return c004Id;
	}


	public void setC004Id(String c004Id) {
		this.c004Id = c004Id;
	}


	public String getC004ChargeCode() {
		return c004ChargeCode;
	}


	public void setC004ChargeCode(String c004ChargeCode) {
		this.c004ChargeCode = c004ChargeCode;
	}


	public String getC004ChargeDesc() {
		return c004ChargeDesc;
	}


	public void setC004ChargeDesc(String c004ChargeDesc) {
		this.c004ChargeDesc = c004ChargeDesc;
	}


	public String getC004TotalCharge() {
		return c004TotalCharge;
	}


	public void setC004TotalCharge(String c004TotalCharge) {
		this.c004TotalCharge = c004TotalCharge;
	}


	public String getC005Id() {
		return c005Id;
	}


	public void setC005Id(String c005Id) {
		this.c005Id = c005Id;
	}


	public String getC005ChargeCode() {
		return c005ChargeCode;
	}


	public void setC005ChargeCode(String c005ChargeCode) {
		this.c005ChargeCode = c005ChargeCode;
	}


	public String getC005ChargeDesc() {
		return c005ChargeDesc;
	}


	public void setC005ChargeDesc(String c005ChargeDesc) {
		this.c005ChargeDesc = c005ChargeDesc;
	}


	public String getC005TotalCharge() {
		return c005TotalCharge;
	}


	public void setC005TotalCharge(String c005TotalCharge) {
		this.c005TotalCharge = c005TotalCharge;
	}


	public String getC006Id() {
		return c006Id;
	}


	public void setC006Id(String c006Id) {
		this.c006Id = c006Id;
	}


	public String getC006ChargeCode() {
		return c006ChargeCode;
	}


	public void setC006ChargeCode(String c006ChargeCode) {
		this.c006ChargeCode = c006ChargeCode;
	}


	public String getC006ChargeDesc() {
		return c006ChargeDesc;
	}


	public void setC006ChargeDesc(String c006ChargeDesc) {
		this.c006ChargeDesc = c006ChargeDesc;
	}


	public String getC006TotalCharge() {
		return c006TotalCharge;
	}


	public void setC006TotalCharge(String c006TotalCharge) {
		this.c006TotalCharge = c006TotalCharge;
	}


	public String getC007Id() {
		return c007Id;
	}


	public void setC007Id(String c007Id) {
		this.c007Id = c007Id;
	}


	public String getC007ChargeCode() {
		return c007ChargeCode;
	}


	public void setC007ChargeCode(String c007ChargeCode) {
		this.c007ChargeCode = c007ChargeCode;
	}


	public String getC007ChargeDesc() {
		return c007ChargeDesc;
	}


	public void setC007ChargeDesc(String c007ChargeDesc) {
		this.c007ChargeDesc = c007ChargeDesc;
	}


	public String getC007TotalCharge() {
		return c007TotalCharge;
	}


	public void setC007TotalCharge(String c007TotalCharge) {
		this.c007TotalCharge = c007TotalCharge;
	}


	public String getC008Id() {
		return c008Id;
	}


	public void setC008Id(String c008Id) {
		this.c008Id = c008Id;
	}


	public String getC008ChargeCode() {
		return c008ChargeCode;
	}


	public void setC008ChargeCode(String c008ChargeCode) {
		this.c008ChargeCode = c008ChargeCode;
	}


	public String getC008ChargeDesc() {
		return c008ChargeDesc;
	}


	public void setC008ChargeDesc(String c008ChargeDesc) {
		this.c008ChargeDesc = c008ChargeDesc;
	}


	public String getC008TotalCharge() {
		return c008TotalCharge;
	}


	public void setC008TotalCharge(String c008TotalCharge) {
		this.c008TotalCharge = c008TotalCharge;
	}


	public String getC009Id() {
		return c009Id;
	}


	public void setC009Id(String c009Id) {
		this.c009Id = c009Id;
	}


	public String getC009ChargeCode() {
		return c009ChargeCode;
	}


	public void setC009ChargeCode(String c009ChargeCode) {
		this.c009ChargeCode = c009ChargeCode;
	}


	public String getC009ChargeDesc() {
		return c009ChargeDesc;
	}


	public void setC009ChargeDesc(String c009ChargeDesc) {
		this.c009ChargeDesc = c009ChargeDesc;
	}


	public String getC009TotalCharge() {
		return c009TotalCharge;
	}


	public void setC009TotalCharge(String c009TotalCharge) {
		this.c009TotalCharge = c009TotalCharge;
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


	public String getAgreementType() {
		return agreementType;
	}


	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}


	public String getSlot1() {
		return slot1;
	}


	public void setSlot1(String slot1) {
		this.slot1 = slot1;
	}


	public String getSlot2() {
		return slot2;
	}


	public void setSlot2(String slot2) {
		this.slot2 = slot2;
	}


	public String getSlot3() {
		return slot3;
	}


	public void setSlot3(String slot3) {
		this.slot3 = slot3;
	}


	public String getSlot4() {
		return slot4;
	}


	public void setSlot4(String slot4) {
		this.slot4 = slot4;
	}


	public String getSlot5() {
		return slot5;
	}


	public void setSlot5(String slot5) {
		this.slot5 = slot5;
	}


	public String getResultCode() {
		return resultCode;
	}


	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	public String getResultDesc() {
		return resultDesc;
	}


	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}



}
