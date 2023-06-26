package com.ss.oa.transaction.energysaleintent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.transaction.epa.EpaService;
import com.ss.oa.transaction.nocgenerator.NocGeneratorService;
import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.Oaa;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class ProcessEsiForStb {

	public ProcessEsiForStb() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	EnergySaleIntentService energySaleIntentService;

	@Autowired
	NocGeneratorService nocGeneratorService;

	@Autowired
	EpaService epaService;

	@Autowired
	OaaService oaaService;

	@Autowired
	EsiProcessHelper esiProcessHelper;

	@Autowired
	TradeRelationshipService tradeRelationshipService;

	String energySaleIntentId = "";

	String nocGeneratorId = "";

	String epaId = "";

	String ooaId = "";

	String tradeRelationshipId = "";

	public String processEnergySaleIntentForStb(EnergySaleIntent energySaleIntent) {
		Boolean isTrade = false;
		System.out.println("STB");
		energySaleIntentId = energySaleIntent.getId();
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(energySaleIntentId);	
		processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
		processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
		processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
		if (energySaleIntent.getFlowTypeCode().equals("STB")) {

			 if (energySaleIntent.getEnergySaleIntentLines() != null) {
				  for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
						if (energySaleIntentLine.getId() != null) {

						   if ((energySaleIntent.getNocGeneratorId() == null || energySaleIntent.getNocGeneratorId().isEmpty())) {
							nocGeneratorId = esiProcessHelper.addNocGeneratorForStb(energySaleIntent);
							System.out.println("nocGeneratorId" + nocGeneratorId);
							energySaleIntent.setNocGeneratorId(nocGeneratorId);
							energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
							processLog.setProcessName("NOC-GENERATOR");
							processLog.setProcessStatus("CREATED");
							processLog.setProcessId(nocGeneratorId);
							esiProcessHelper.addProcessLog(processLog);
						   }
						  else if ((energySaleIntent.getNocGeneratorId()!= null && (energySaleIntentLine.getEpaId() == null  || energySaleIntentLine.getEpaId().isEmpty())))  {
								 if(energySaleIntent.getNocGeneratorStatusCode()!= null && energySaleIntent.getNocGeneratorStatusCode().equals("COMPLETED")) 
								{
							epaId = esiProcessHelper.addEpa(energySaleIntent);
							System.out.println("epa Id" + epaId);
							energySaleIntentLine.setEpaId(epaId);
							// energySaleIntentLine.setStatusCode("PROCESS");
							energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
							processLog.setProcessName("EPA");
							processLog.setProcessStatus("CREATED");
							processLog.setProcessId(epaId);
							esiProcessHelper.addProcessLog(processLog);
						        }
						     }	 
							
								 
							if(energySaleIntentLine.getEpaId()!= null && (energySaleIntentLine.getOaAgmtId() == null  || energySaleIntentLine.getOaAgmtId().isEmpty())) {
							
							   if(energySaleIntentLine.getEpaStatusCode() != null && energySaleIntentLine.getEpaStatusCode().equals("COMPLETED")) {

							Oaa oaa = new Oaa();
							oaa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
							oaa.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
							oaa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
							oaa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
							oaa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
							oaa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
							oaa.setFromDate(energySaleIntent.getFromDate());
							oaa.setToDate(energySaleIntent.getToDate());
							oaa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
							oaa.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
							ooaId = esiProcessHelper.addOaaForStb(oaa, energySaleIntentLine);
							System.out.println("ooaId" + ooaId);
							energySaleIntentLine.setOaAgmtId(ooaId);
							energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
							processLog.setProcessName("OAA");
							processLog.setProcessStatus("CREATED");
							processLog.setProcessId(ooaId);
							esiProcessHelper.addProcessLog(processLog);

						} else {
							System.out.println("Oaa Status is not complete!" + ooaId);
							// throw new OpenAccessException("Oaa Status is not complete!");
						}

					} else if (energySaleIntentLine.getTradeRelationshipId() == null && (energySaleIntentLine.getOaAgmtStatusCode() != null
									&& energySaleIntentLine.getOaAgmtStatusCode().equals("COMPLETED"))) {
			if (energySaleIntentLine.getOaAgmtId() != null) {
							String tradeRelationshipId = "";
							System.out.println("In add oaatrade");
							tradeRelationshipId = oaaService.addTradeRelationship(energySaleIntentLine.getOaAgmtId());

							System.out.println(tradeRelationshipId);
							energySaleIntentLine.setTradeRelationshipId(tradeRelationshipId);

							energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
							processLog.setProcessName("TRADERELATIONSHIP");
							processLog.setProcessStatus("CREATED");
							processLog.setProcessId(tradeRelationshipId);
							esiProcessHelper.addProcessLog(processLog);
						}

					} else if (energySaleIntentLine.getTradeRelationshipId() != null
							&& (energySaleIntentLine.getOaAgmtStatusCode() != null
									&& energySaleIntentLine.getOaAgmtStatusCode().equals("COMPLETED"))) {
						isTrade = true;

					} else {
						isTrade = false;
					}

				}
				if (isTrade) {
					energySaleIntent.setStatusCode("COMPLETED");
					energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
					processLog.setProcessName("ENERGY-SALE-INTENT");
					processLog.setProcessStatus("COMPLETED");
					esiProcessHelper.addProcessLog(processLog);
				}
			  }
		    }
		  }
		
	 
	
		return energySaleIntentId;
	    }
	
	  }
    
	
    

		

// else if (energySaleIntentLine.getTradeRelationshipId() == null
// && (energySaleIntentLine.getEpaStatusCode() != null
// && energySaleIntentLine.getEpaStatusCode().equals("COMPLETED"))) {
//
// Epa epa = epaService.getEpaById(energySaleIntentLine.getEpaId());
// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
// String fromDate = epa.getFromDate();
// String toDate = epa.getToDate();
//
// try {
// Date fromDate1 = dateFormat.parse(fromDate);
//
// Date toDate1 = dateFormat.parse(toDate);
//
// System.out.println(fromDate1 );
// System.out.println(toDate1);
// System.out.println(dateFormat.format(fromDate1));
// epa.setFromDate(dateFormat.format(fromDate1).toString());
// epa.setToDate(dateFormat.format(toDate1).toString());
//
// }catch (ParseException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// TradeRelationship tradeRelationship = new TradeRelationship();
// tradeRelationship.setSellerCompServiceId(epa.getSellerCompServiceId());
// tradeRelationship.setSellerCompServiceNumber(epa.getSellerCompServiceNumber());
// tradeRelationship.setSellerCompanyId(epa.getSellerCompanyId());
// tradeRelationship.setSellerCompanyName(epa.getSellerCompanyName());
// tradeRelationship.setBuyerCompanyId(epa.getBuyerCompanyId());
// tradeRelationship.setBuyerCompanyName(epa.getBuyerCompanyName());
// tradeRelationship.setBuyerCompServiceId(epa.getBuyerCompanyServiceId());
// tradeRelationship.setBuyerCompServiceNumber(epa.getBuyerCompanyServiceNumber());
// tradeRelationship.setQuantum(epa.getApprovedTotalUnits());
// tradeRelationship.setFromDate(epa.getFromDate());
// tradeRelationship.setToDate(epa.getToDate());
// tradeRelationship.setC1(epa.getC1());
// tradeRelationship.setC2(epa.getC2());
// tradeRelationship.setC3(epa.getC3());
// tradeRelationship.setC4(epa.getC4());
// tradeRelationship.setC5(epa.getC5());
// tradeRelationship.setPeakUnits(epa.getPeakUnits());
// tradeRelationship.setOffPeakUnits(epa.getOffPeakUnits());
// tradeRelationship.setSharePercent(epa.getSharePercent());
// tradeRelationship.setIntervalTypeCode(epa.getIntervalTypeCode());
//
// tradeRelationship.setIsCaptive(epa.getSellerIsCaptive());
// tradeRelationship.setReferenceNumber(epa.getId());
// System.out.println(tradeRelationship);
//
//
// tradeRelationshipId =
// tradeRelationshipService.addTradeRelationship(tradeRelationship);
//
// energySaleIntentLine.setTradeRelationshipId(tradeRelationshipId);
// if(energySaleIntentLine.getTradeRelationshipId()!=null ) {
// energySaleIntent.setStatusCode("COMPLETED");
// }
// energySaleIntentService.updateEnergySaleIntent(energySaleIntentId,
// energySaleIntent);
