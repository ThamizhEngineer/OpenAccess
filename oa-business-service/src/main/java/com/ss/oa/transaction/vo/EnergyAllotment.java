package com.ss.oa.transaction.vo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ss.oa.model.master.Service;
import com.ss.oa.model.master.TradeRelationship;



@Table(name = "T_ENERGY_SALE")
@CreationTimestamp
@UpdateTimestamp
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Scope("prototype")
public class EnergyAllotment {
	  @Id
	  @Column(name="ID")
	  private String id;
	  @Column(name="T_GEN_STMT_ID")
	  private String generationStatementId;
	  @Column(name="SELLER_COMP_SERV_ID")
	  private String sellerCompanyServiceId;
	  @Formula("(SELECT ms.M_COMP_SERV_NUMBER FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompanyServiceNumber;
	  @Formula("(SELECT ms.BANKING_SERVICE_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompBankingServiceId;
	  @Formula("(SELECT ms.BANKING_SERVICE_NUMBER FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompBankingServiceNumber;
	  @Column(name="SELLER_END_ORG_ID")
	  private String sellerEndOrgId;
	  @Formula("(SELECT ms.M_ORG_NAME FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndOrgName;
	  @Formula("(SELECT ms.M_ORG_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndOrgCode;
	  @Formula("(SELECT ms.M_SUBSTATION_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndSubstationId;
	  @Formula("(SELECT ms.M_SUBSTATION_NAME FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndSubstationName;
	  @Formula("(SELECT ms.M_SUBSTATION_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndSubstationCode;
	  @Formula("(SELECT ms.M_FEEDER_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndFeederId;
	  @Formula("(SELECT ms.M_FEEDER_NAME FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndFeederName;
	  @Formula("(SELECT ms.M_FEEDER_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerEndFeederCode;
	  @Formula("(SELECT ms.m_company_id FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompanyId;
	  @Formula("(SELECT ms.m_company_name FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompanyName;
	  @Formula("(SELECT ms.m_company_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerCompanyCode;
	  @Column(name="MONTH")
	  private String month;
	  @Column(name="YEAR")
	  private String year;
	  @Column(name="INJECTING_VOLTAGE_CODE")
	  private String injectingVoltageCode;
	  @Formula("(SELECT ms.VOLTAGE_NAME FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String injectingVoltageName;
//	  @CreationTimestamp
//	  @JsonFormat(pattern = "yyyy-MM-dd")
	  @Column(name="FROM_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate fromDate;
	 // @CreationTimestamp
	 // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	  @Column(name="TO_DT")
	@JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate toDate;
	  @Column(name="ALLOW_LOWER_SLOT_ADMT")
	  private String allowLowerSlotAdmt;
	  @Column(name="LOSS")
	  private String loss;
	  @Column(name="MULTIPLE_BUYERS")
	  private Character multipleBuyers;
	  @Column(name="USAGE_DETAIL_AVAIL")
	  private Character usageDetailAvail;
	  @Formula("(SELECT ms.capacity FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sanctionedCapacity;
	  @Column(name="SIMPLE_ENERGY_SALE")
	  private Character simpleEnergySale;
	  @Column(name="STATUS_CODE")
	  private String statusCode;
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
	  @Column(name="NET_GENERATION")
	  private String netGeneration;
	  @Column(name="NET_ALLOCATION")
	  private String netAllocation;
	  @Column(name="NET_CHARGes_ALLOCATED")
	  private String netChargesAllocated;
	  @Column(name="BC1")
	  private String bc1;
	  @Column(name="BC2")
	  private String bc2;
	  @Column(name="BC3")
	  private String bc3;
	  @Column(name="BC4")
	  private String bc4;
	  @Column(name="BC5")
	  private String bc5;
	  @Column(name="TOTAL_BANK_UNITS_USED")
	  private String totalBankUnitsUsed;
	  @Column(name="GC1")
	  private String gc1;
	  @Column(name="GC2")
	  private String gc2;
	  @Column(name="GC3")
	  private String gc3;
	  @Column(name="GC4")
	  private String gc4;
	  @Column(name="GC5")
	  private String gc5;
	  @Column(name="IS_STB")
	  private Character isStb;
	  @Column(name="AVAIL_C1")
	  private String availc1;
	  @Column(name="AVAIL_C2")
	  private String availc2;
	  @Column(name="AVAIL_C3")
	  private String availc3;
	  @Column(name="AVAIL_C4")
	  private String availc4;
	  @Column(name="AVAIL_C5")
	  private String availc5;
	  @Column(name="AVAIL_GC1")
	  private String availGc1;
	  @Column(name="AVAIL_GC2")
	  private String availGc2;
	  @Column(name="AVAIL_GC3")
	  private String availGc3;
	  @Column(name="AVAIL_GC4")
	  private String availGc4;
	  @Column(name="AVAIL_GC5")
	  private String availGc5;
	  @Column(name="AVAIL_BC1")
	  private String availBc1;
	  @Column(name="AVAIL_BC2")
	  private String availBc2;
	  @Column(name="AVAIL_BC3")
	  private String availBc3;
	  @Column(name="AVAIL_BC4")
	  private String availBc4;
	  @Column(name="AVAIL_BC5")
	  private String availBc5;
	  @Formula("(SELECT ms.TL_SERVICE_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerTlServiceId;
	  @Formula("(SELECT ms.TL_SERVICE_NUMBER FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerTlServiceNumber;
	  @Formula("(SELECT ms.DL_SERVICE_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerDlServiceId;
	  @Formula("(SELECT ms.DL_SERVICE_NUMBER FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerDlServiceNumber;
	  @Formula("(SELECT ms.UNADJUSTED_SERVICE_ID FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerUnAdjustedServiceId;
	  @Formula("(SELECT ms.UNADJUSTED_SERVICE_NUMBER FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String sellerUnAdjustedServiceNumber;
	  @Transient
	  private String genStmtCount;
	  @Column(name="SAVED_ONCE")
	  private String savedOnce;
	  @Formula("(SELECT ms.FUEL_TYPE_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String fuelTypeCode;
	  @Formula("(SELECT ff.FUEL_NAME FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID \n"+
			  "JOIN M_FUEL ff ON ms.FUEL_TYPE_CODE = ff.FUEL_CODE WHERE es.ID=id)")
	  private String fuelTypeName;
	  @Formula("(SELECT ff.FUEL_GROUP FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID \n"+
			  "JOIN M_FUEL ff ON ms.FUEL_TYPE_CODE = ff.FUEL_CODE WHERE es.ID=id)")
	  private String fuelGroupe;

	  @Formula("(SELECT ms.TR_FLOW_TYPE_CODE FROM T_ENERGY_SALE es JOIN V_COMPANY_SERVICE ms ON ms.ID=es.SELLER_COMP_SERV_ID WHERE es.ID=id)")
	  private String flowTypeCode;
	  
	  @Column(name="CREATED_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd")
	  private LocalDate createdDate;
	  
	  @Transient
	  private List<GenerationStatementCharge> gsCharges;
	  @Transient
	  private String energyAllotmentStatus;
	  @Transient
	  private String stringFromDate;
	  @Transient
	  private String stringToDate;

	  @OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	  @Fetch(value = FetchMode.SUBSELECT)
	  @JoinColumn(name="T_ENERGY_SALE_ID")
	  List<EsUsageDetail> esUsageDetails;
	  
	  @OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	  @Fetch(value = FetchMode.SUBSELECT)
	  @JoinColumn(name="T_ENERGY_SALE_ID")
	  List<EsUsageSummary> esUsageSummaries;
	  
	  @OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	  @Fetch(value = FetchMode.SUBSELECT)
	  @JoinColumn(name="T_ENERGY_SALE_ID")
	  List<EsCharge> esCharges;
	  

	public EnergyAllotment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public EnergyAllotment(String id, String generationStatementId, String sellerCompanyServiceId,
			String sellerCompanyServiceNumber, String sellerCompBankingServiceId, String sellerCompBankingServiceNumber,
			String sellerEndOrgId, String sellerEndOrgName, String sellerEndOrgCode, String sellerEndSubstationId,
			String sellerEndSubstationName, String sellerEndSubstationCode, String sellerEndFeederId,
			String sellerEndFeederName, String sellerEndFeederCode, String sellerCompanyId, String sellerCompanyName,
			String sellerCompanyCode, String month, String year, String injectingVoltageCode,
			String injectingVoltageName, LocalDate fromDate, LocalDate toDate, String allowLowerSlotAdmt, String loss,
			Character multipleBuyers, Character usageDetailAvail, String sanctionedCapacity, Character simpleEnergySale,
			String statusCode, String c1, String c2, String c3, String c4, String c5, String netGeneration,
			String netAllocation, String netChargesAllocated, String bc1, String bc2, String bc3, String bc4,
			String bc5, String totalBankUnitsUsed, String gc1, String gc2, String gc3, String gc4, String gc5,
			Character isStb, String availc1, String availc2, String availc3, String availc4, String availc5,
			String availGc1, String availGc2, String availGc3, String availGc4, String availGc5, String availBc1,
			String availBc2, String availBc3, String availBc4, String availBc5, String sellerTlServiceId,
			String sellerTlServiceNumber, String sellerDlServiceId, String sellerDlServiceNumber,
			String sellerUnAdjustedServiceId, String sellerUnAdjustedServiceNumber, String genStmtCount,
			String savedOnce, String fuelTypeCode, String fuelTypeName, String fuelGroupe, String flowTypeCode,
			LocalDate createdDate, List<GenerationStatementCharge> gsCharges, String energyAllotmentStatus,
			String stringFromDate, String stringToDate, List<EsUsageDetail> esUsageDetails,
			List<EsUsageSummary> esUsageSummaries, List<EsCharge> esCharges) {
		super();
		this.id = id;
		this.generationStatementId = generationStatementId;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerCompBankingServiceId = sellerCompBankingServiceId;
		this.sellerCompBankingServiceNumber = sellerCompBankingServiceNumber;
		this.sellerEndOrgId = sellerEndOrgId;
		this.sellerEndOrgName = sellerEndOrgName;
		this.sellerEndOrgCode = sellerEndOrgCode;
		this.sellerEndSubstationId = sellerEndSubstationId;
		this.sellerEndSubstationName = sellerEndSubstationName;
		this.sellerEndSubstationCode = sellerEndSubstationCode;
		this.sellerEndFeederId = sellerEndFeederId;
		this.sellerEndFeederName = sellerEndFeederName;
		this.sellerEndFeederCode = sellerEndFeederCode;
		this.sellerCompanyId = sellerCompanyId;
		this.sellerCompanyName = sellerCompanyName;
		this.sellerCompanyCode = sellerCompanyCode;
		this.month = month;
		this.year = year;
		this.injectingVoltageCode = injectingVoltageCode;
		this.injectingVoltageName = injectingVoltageName;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
		this.loss = loss;
		this.multipleBuyers = multipleBuyers;
		this.usageDetailAvail = usageDetailAvail;
		this.sanctionedCapacity = sanctionedCapacity;
		this.simpleEnergySale = simpleEnergySale;
		this.statusCode = statusCode;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.netGeneration = netGeneration;
		this.netAllocation = netAllocation;
		this.netChargesAllocated = netChargesAllocated;
		this.bc1 = bc1;
		this.bc2 = bc2;
		this.bc3 = bc3;
		this.bc4 = bc4;
		this.bc5 = bc5;
		this.totalBankUnitsUsed = totalBankUnitsUsed;
		this.gc1 = gc1;
		this.gc2 = gc2;
		this.gc3 = gc3;
		this.gc4 = gc4;
		this.gc5 = gc5;
		this.isStb = isStb;
		this.availc1 = availc1;
		this.availc2 = availc2;
		this.availc3 = availc3;
		this.availc4 = availc4;
		this.availc5 = availc5;
		this.availGc1 = availGc1;
		this.availGc2 = availGc2;
		this.availGc3 = availGc3;
		this.availGc4 = availGc4;
		this.availGc5 = availGc5;
		this.availBc1 = availBc1;
		this.availBc2 = availBc2;
		this.availBc3 = availBc3;
		this.availBc4 = availBc4;
		this.availBc5 = availBc5;
		this.sellerTlServiceId = sellerTlServiceId;
		this.sellerTlServiceNumber = sellerTlServiceNumber;
		this.sellerDlServiceId = sellerDlServiceId;
		this.sellerDlServiceNumber = sellerDlServiceNumber;
		this.sellerUnAdjustedServiceId = sellerUnAdjustedServiceId;
		this.sellerUnAdjustedServiceNumber = sellerUnAdjustedServiceNumber;
		this.genStmtCount = genStmtCount;
		this.savedOnce = savedOnce;
		this.fuelTypeCode = fuelTypeCode;
		this.fuelTypeName = fuelTypeName;
		this.fuelGroupe = fuelGroupe;
		this.flowTypeCode = flowTypeCode;
		this.createdDate = createdDate;
		this.gsCharges = gsCharges;
		this.energyAllotmentStatus = energyAllotmentStatus;
		this.stringFromDate = stringFromDate;
		this.stringToDate = stringToDate;
		this.esUsageDetails = esUsageDetails;
		this.esUsageSummaries = esUsageSummaries;
		this.esCharges = esCharges;
	}





	public EnergyAllotment(String id, String sellerCompanyServiceId,  LocalDate createdDate) {
		super();
		this.id = id;
		this.sellerCompanyServiceId = sellerCompanyServiceId; 
		this.createdDate = createdDate;
	}
	
	public EnergyAllotment(String id, String sellerCompanyServiceId, String sellerCompanyServiceNumber,
			String sellerEndOrgName, String sellerCompanyName, String month, String year, String injectingVoltageName,
			String netGeneration, String netAllocation, String netChargesAllocated, String totalBankUnitsUsed,
			String statusCode, String fuelGroupe, LocalDate createdDate) {
		super();
		this.id = id;
		this.sellerCompanyServiceId = sellerCompanyServiceId;
		this.sellerCompanyServiceNumber = sellerCompanyServiceNumber;
		this.sellerEndOrgName = sellerEndOrgName;
		this.sellerCompanyName = sellerCompanyName;
		this.month = month;
		this.year = year;
		this.injectingVoltageName = injectingVoltageName;
		this.netGeneration = netGeneration;
		this.netAllocation = netAllocation;
		this.netChargesAllocated = netChargesAllocated;
		this.totalBankUnitsUsed = totalBankUnitsUsed;
		this.statusCode = statusCode;
		this.fuelGroupe = fuelGroupe;
		this.createdDate = createdDate;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getGenerationStatementId() {
		return generationStatementId;
	}


	public void setGenerationStatementId(String generationStatementId) {
		this.generationStatementId = generationStatementId;
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


	public String getSellerEndOrgId() {
		return sellerEndOrgId;
	}


	public void setSellerEndOrgId(String sellerEndOrgId) {
		this.sellerEndOrgId = sellerEndOrgId;
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


	public String getSellerCompanyId() {
		return sellerCompanyId;
	}


	public void setSellerCompanyId(String sellerCompanyId) {
		this.sellerCompanyId = sellerCompanyId;
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


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
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


	public LocalDate getFromDate() {
		return fromDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}


	public String getAllowLowerSlotAdmt() {
		return allowLowerSlotAdmt;
	}


	public void setAllowLowerSlotAdmt(String allowLowerSlotAdmt) {
		this.allowLowerSlotAdmt = allowLowerSlotAdmt;
	}


	public String getLoss() {
		return loss;
	}


	public void setLoss(String loss) {
		this.loss = loss;
	}


	public Character getMultipleBuyers() {
		return multipleBuyers;
	}


	public void setMultipleBuyers(Character multipleBuyers) {
		this.multipleBuyers = multipleBuyers;
	}


	public Character getUsageDetailAvail() {
		return usageDetailAvail;
	}


	public void setUsageDetailAvail(Character usageDetailAvail) {
		this.usageDetailAvail = usageDetailAvail;
	}


	public String getSanctionedCapacity() {
		return sanctionedCapacity;
	}


	public void setSanctionedCapacity(String sanctionedCapacity) {
		this.sanctionedCapacity = sanctionedCapacity;
	}


	public Character getSimpleEnergySale() {
		return simpleEnergySale;
	}


	public void setSimpleEnergySale(Character simpleEnergySale) {
		this.simpleEnergySale = simpleEnergySale;
	}


	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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


	public String getNetGeneration() {
		return netGeneration;
	}


	public void setNetGeneration(String netGeneration) {
		this.netGeneration = netGeneration;
	}


	public String getNetAllocation() {
		return netAllocation;
	}


	public void setNetAllocation(String netAllocation) {
		this.netAllocation = netAllocation;
	}


	public String getNetChargesAllocated() {
		return netChargesAllocated;
	}


	public void setNetChargesAllocated(String netChargesAllocated) {
		this.netChargesAllocated = netChargesAllocated;
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


	public String getTotalBankUnitsUsed() {
		return totalBankUnitsUsed;
	}


	public void setTotalBankUnitsUsed(String totalBankUnitsUsed) {
		this.totalBankUnitsUsed = totalBankUnitsUsed;
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


	public Character getIsStb() {
		return isStb;
	}


	public void setIsStb(Character isStb) {
		this.isStb = isStb;
	}


	public String getAvailc1() {
		return availc1;
	}


	public void setAvailc1(String availc1) {
		this.availc1 = availc1;
	}


	public String getAvailc2() {
		return availc2;
	}


	public void setAvailc2(String availc2) {
		this.availc2 = availc2;
	}


	public String getAvailc3() {
		return availc3;
	}


	public void setAvailc3(String availc3) {
		this.availc3 = availc3;
	}


	public String getAvailc4() {
		return availc4;
	}


	public void setAvailc4(String availc4) {
		this.availc4 = availc4;
	}


	public String getAvailc5() {
		return availc5;
	}


	public void setAvailc5(String availc5) {
		this.availc5 = availc5;
	}


	public String getAvailGc1() {
		return availGc1;
	}


	public void setAvailGc1(String availGc1) {
		this.availGc1 = availGc1;
	}


	public String getAvailGc2() {
		return availGc2;
	}


	public void setAvailGc2(String availGc2) {
		this.availGc2 = availGc2;
	}


	public String getAvailGc3() {
		return availGc3;
	}


	public void setAvailGc3(String availGc3) {
		this.availGc3 = availGc3;
	}


	public String getAvailGc4() {
		return availGc4;
	}


	public void setAvailGc4(String availGc4) {
		this.availGc4 = availGc4;
	}


	public String getAvailGc5() {
		return availGc5;
	}


	public void setAvailGc5(String availGc5) {
		this.availGc5 = availGc5;
	}


	public String getAvailBc1() {
		return availBc1;
	}


	public void setAvailBc1(String availBc1) {
		this.availBc1 = availBc1;
	}


	public String getAvailBc2() {
		return availBc2;
	}


	public void setAvailBc2(String availBc2) {
		this.availBc2 = availBc2;
	}


	public String getAvailBc3() {
		return availBc3;
	}


	public void setAvailBc3(String availBc3) {
		this.availBc3 = availBc3;
	}


	public String getAvailBc4() {
		return availBc4;
	}


	public void setAvailBc4(String availBc4) {
		this.availBc4 = availBc4;
	}


	public String getAvailBc5() {
		return availBc5;
	}


	public void setAvailBc5(String availBc5) {
		this.availBc5 = availBc5;
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


	public String getGenStmtCount() {
		return genStmtCount;
	}


	public void setGenStmtCount(String genStmtCount) {
		this.genStmtCount = genStmtCount;
	}


	public String getSavedOnce() {
		return savedOnce;
	}


	public void setSavedOnce(String savedOnce) {
		this.savedOnce = savedOnce;
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


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public List<GenerationStatementCharge> getGsCharges() {
		return gsCharges;
	}


	public void setGsCharges(List<GenerationStatementCharge> gsCharges) {
		this.gsCharges = gsCharges;
	}


	public String getEnergyAllotmentStatus() {
		return energyAllotmentStatus;
	}


	public void setEnergyAllotmentStatus(String energyAllotmentStatus) {
		this.energyAllotmentStatus = energyAllotmentStatus;
	}


	public String getStringFromDate() {
		return stringFromDate;
	}


	public void setStringFromDate(String stringFromDate) {
		this.stringFromDate = stringFromDate;
	}


	public String getStringToDate() {
		return stringToDate;
	}


	public void setStringToDate(String stringToDate) {
		this.stringToDate = stringToDate;
	}


	public List<EsUsageDetail> getEsUsageDetails() {
		return esUsageDetails;
	}


	public void setEsUsageDetails(List<EsUsageDetail> esUsageDetails) {
		this.esUsageDetails = esUsageDetails;
	}


	public List<EsUsageSummary> getEsUsageSummaries() {
		return esUsageSummaries;
	}


	public void setEsUsageSummaries(List<EsUsageSummary> esUsageSummaries) {
		this.esUsageSummaries = esUsageSummaries;
	}


	public List<EsCharge> getEsCharges() {
		return esCharges;
	}


	public void setEsCharges(List<EsCharge> esCharges) {
		this.esCharges = esCharges;
	}


	@Override
	public String toString() {
		return "EnergyAllotment [id=" + id + ", generationStatementId=" + generationStatementId
				+ ", sellerCompanyServiceId=" + sellerCompanyServiceId + ", sellerCompanyServiceNumber="
				+ sellerCompanyServiceNumber + ", sellerCompBankingServiceId=" + sellerCompBankingServiceId
				+ ", sellerCompBankingServiceNumber=" + sellerCompBankingServiceNumber + ", sellerEndOrgId="
				+ sellerEndOrgId + ", sellerEndOrgName=" + sellerEndOrgName + ", sellerEndOrgCode=" + sellerEndOrgCode
				+ ", sellerEndSubstationId=" + sellerEndSubstationId + ", sellerEndSubstationName="
				+ sellerEndSubstationName + ", sellerEndSubstationCode=" + sellerEndSubstationCode
				+ ", sellerEndFeederId=" + sellerEndFeederId + ", sellerEndFeederName=" + sellerEndFeederName
				+ ", sellerEndFeederCode=" + sellerEndFeederCode + ", sellerCompanyId=" + sellerCompanyId
				+ ", sellerCompanyName=" + sellerCompanyName + ", sellerCompanyCode=" + sellerCompanyCode + ", month="
				+ month + ", year=" + year + ", injectingVoltageCode=" + injectingVoltageCode
				+ ", injectingVoltageName=" + injectingVoltageName + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", allowLowerSlotAdmt=" + allowLowerSlotAdmt + ", loss=" + loss + ", multipleBuyers=" + multipleBuyers
				+ ", usageDetailAvail=" + usageDetailAvail + ", sanctionedCapacity=" + sanctionedCapacity
				+ ", simpleEnergySale=" + simpleEnergySale + ", statusCode=" + statusCode + ", c1=" + c1 + ", c2=" + c2
				+ ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5 + ", netGeneration=" + netGeneration + ", netAllocation="
				+ netAllocation + ", netChargesAllocated=" + netChargesAllocated + ", bc1=" + bc1 + ", bc2=" + bc2
				+ ", bc3=" + bc3 + ", bc4=" + bc4 + ", bc5=" + bc5 + ", totalBankUnitsUsed=" + totalBankUnitsUsed
				+ ", gc1=" + gc1 + ", gc2=" + gc2 + ", gc3=" + gc3 + ", gc4=" + gc4 + ", gc5=" + gc5 + ", isStb="
				+ isStb + ", availc1=" + availc1 + ", availc2=" + availc2 + ", availc3=" + availc3 + ", availc4="
				+ availc4 + ", availc5=" + availc5 + ", availGc1=" + availGc1 + ", availGc2=" + availGc2 + ", availGc3="
				+ availGc3 + ", availGc4=" + availGc4 + ", availGc5=" + availGc5 + ", availBc1=" + availBc1
				+ ", availBc2=" + availBc2 + ", availBc3=" + availBc3 + ", availBc4=" + availBc4 + ", availBc5="
				+ availBc5 + ", sellerTlServiceId=" + sellerTlServiceId + ", sellerTlServiceNumber="
				+ sellerTlServiceNumber + ", sellerDlServiceId=" + sellerDlServiceId + ", sellerDlServiceNumber="
				+ sellerDlServiceNumber + ", sellerUnAdjustedServiceId=" + sellerUnAdjustedServiceId
				+ ", sellerUnAdjustedServiceNumber=" + sellerUnAdjustedServiceNumber + ", genStmtCount=" + genStmtCount
				+ ", savedOnce=" + savedOnce + ", fuelTypeCode=" + fuelTypeCode + ", fuelTypeName=" + fuelTypeName
				+ ", fuelGroupe=" + fuelGroupe + ", gsCharges=" + gsCharges + ", energyAllotmentStatus="
				+ energyAllotmentStatus + ", stringFromDate=" + stringFromDate + ", stringToDate=" + stringToDate
				+ ", esUsageDetails=" + esUsageDetails + ", esUsageSummaries=" + esUsageSummaries + ", esCharges="
				+ esCharges + "]";
	}


	

 	  	 	 
}
