package com.ss.oa.transaction.vo;
 
import java.time.LocalDate;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; 


@JsonIgnoreProperties 
@Scope("prototype")
public interface EnergyAllotmentView {
	  
	public String getId();

	public String getGenerationStatementId();

	public String getSellerCompanyServiceId();
	public String getSellerCompanyServiceNumber();	
	public String getSellerCompBankingServiceId();	
	public String getSellerCompBankingServiceNumber();
	public String getSellerEndOrgId();
	public String getSellerEndOrgName();
	public String getSellerEndOrgCode();
	public String getSellerEndSubstationId();
	public String getSellerEndSubstationName();
	public String getSellerEndSubstationCode();
	public String getSellerEndFeederId();
	public String getSellerEndFeederName();
	public String getSellerEndFeederCode();
	public String getSellerCompanyId() ;
	public String getSellerCompanyName();
	public String getSellerCompanyCode() ;
	public String getMonth() ;
	public String getYear() ;
	public String getInjectingVoltageCode();
	public String getInjectingVoltageName();
	public LocalDate getFromDate();
	public LocalDate getToDate();
	public String getAllowLowerSlotAdmt();
	public String getLoss();
	public Character getMultipleBuyers();
	public Character getUsageDetailAvail();
	public String getSanctionedCapacity();
	public Character getSimpleEnergySale();
	public String getStatusCode() ;
	public String getC1();
	public String getC2() ;
	public String getC3();
	public String getC4() ;
	public String getC5();
	public String getNetGeneration();
	public String getNetAllocation();
	public String getNetChargesAllocated() ;
	public String getBc1();
	public String getBc2();
	public String getBc3();
	public String getBc4();
	public String getBc5();
	public String getTotalBankUnitsUsed() ;
	public String getGc1() ;
	public String getGc2() ;
	public String getGc3() ;
	public String getGc4() ;
	public String getGc5();
	public Character getIsStb();
	public String getAvailc1();
	public String getAvailc2();
	public String getAvailc3() ;
	public String getAvailc4() ;
	public String getAvailc5() ;
	public String getAvailGc1();
	public String getAvailGc2() ;
	public String getAvailGc3();
	public String getAvailGc4();
	public String getAvailGc5() ;
	public String getAvailBc1() ;
	public String getAvailBc2();
	public String getAvailBc3();
	public String getAvailBc4() ;
	public String getAvailBc5() ;
	public String getSellerTlServiceId();
	public String getSellerTlServiceNumber() ;
	public String getSellerDlServiceId() ;
	public String getSellerDlServiceNumber();
	public String getSellerUnAdjustedServiceId() ;
	public String getSellerUnAdjustedServiceNumber();
	public String getGenStmtCount();
	public String getSavedOnce() ;
	public String getFuelTypeCode() ;
	public String getFuelTypeName() ;
	public String getFuelGroupe() ;
	public String getEnergyAllotmentStatus();
	public String getStringFromDate() ;

	public String getStringToDate();

	  
 
 	  	 	 
}
