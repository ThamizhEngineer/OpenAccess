package com.ss.oa.transaction.energysaleintent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class ProcessEsiForIexConsumer {

public ProcessEsiForIexConsumer() {
	super();
	// TODO Auto-generated constructor stub
}
	
@Autowired
EnergySaleIntentService energySaleIntentService;
@Autowired
EsiProcessHelper esiProcessHelper;
@Autowired
TradeRelationshipService tradeRelationshipService;

String tradeRelationshipId="";	
String standingClearenceId="";	
String energySaleIntentId="";	
String ipaaId="";
String nocGeneratorId="";	
String ewaId="";
String nocId ="";	
String consentId="";	
String ooaId="";
	
public String processEnergySaleIntentForIexConsumer(EnergySaleIntent energySaleIntent) {
System.out.println("IEX-CONSUMER");
energySaleIntentId = energySaleIntent.getId();
ProcessLog  processLog = new ProcessLog();
processLog.settEnergySaleId(energySaleIntentId);	
processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
	
if (energySaleIntent.getFlowTypeCode().equals("IEX-CONSUMER")) {		
	if (energySaleIntent.getEnergySaleIntentLines() != null) {			
		for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
			if (energySaleIntentLine.getId() != null) {	
				
				 if (energySaleIntentLine.getNocId() == null || energySaleIntentLine.getNocId().isEmpty())
					{
						System.out.println("In add noc");
						Noc noc= new Noc();
						noc.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
						noc.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
						nocId = esiProcessHelper.addNoc(energySaleIntentLine,noc);
						System.out.println("Noc Id" + nocId);
						energySaleIntentLine.setNocId(nocId);
						energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);		
						processLog.setProcessName("NOC");
						processLog.setProcessStatus("CREATED");
						processLog.setProcessId(nocId);
						esiProcessHelper.addProcessLog(processLog);
					}
						
				 else if(energySaleIntentLine.getNocId()!=null&&(energySaleIntent.getIpaaId()==null || energySaleIntent.getIpaaId().isEmpty()))
				 {
					ipaaId = esiProcessHelper.addIpaa(energySaleIntent);
					System.out.println("ipaaId"+ipaaId);
					energySaleIntent.setIpaaId(ipaaId);
					energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);	
					processLog.setProcessName("IPAA");
					processLog.setProcessStatus("CREATED");
					processLog.setProcessId(ipaaId);
					esiProcessHelper.addProcessLog(processLog);
					}
				 
//---------------------------------------------------------------------------------		
				 
				 
//				 else if (energySaleIntentLine.getNocId() != null && (energySaleIntentLine.getStandingClearenceId() == null || energySaleIntentLine.getStandingClearenceId().isEmpty())) {
//					System.out.println("In add Standing clear");
//					if (energySaleIntentLine.getNocStatusCode() != null && energySaleIntentLine.getNocStatusCode().equals("COMPLETED"))
//					{
//						StandingClearence standingClearence = new StandingClearence();
//						standingClearence.setStatusCode("CREATED");
//						standingClearence.setFromDate(energySaleIntent.getFromDate());;
//						standingClearence.setFromMonth(energySaleIntent.getFromMonth());
//						standingClearence.setFromYear(energySaleIntent.getFromYear());
//						standingClearence.setToDate(energySaleIntent.getToDate());
//						standingClearence.setToMonth(energySaleIntent.getToMonth());
//						standingClearence.setToYear(energySaleIntent.getToYear());
//						standingClearence.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
//						standingClearenceId = esiProcessHelper.addStandingClearence(standingClearence,energySaleIntentLine);
//						System.out.println(standingClearenceId);
//						energySaleIntentLine.setStandingClearenceId(standingClearenceId);
//						energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);	
//						processLog.setProcessName("STANDING-CLEARENCE");
//						processLog.setProcessStatus("CREATED");
//						processLog.setProcessId(standingClearenceId);
//						esiProcessHelper.addProcessLog(processLog);		
//					}
//					
//					else
//					{
//						System.out.println("Standing Clearence Status is not complete!" + consentId);
//						// throw new OpenAccessException("Consent Status is not complete!");
//					}
//					
//					} //else if ends here
				 	
//				 else if (energySaleIntentLine.getTradeRelationshipId() == null
//							&& (energySaleIntentLine.getStandingClearenceStatusCode() != null
//							&& energySaleIntentLine.getStandingClearenceStatusCode().equals("COMPLETED"))) {
//						
//						TradeRelationship tradeRelationship =  new TradeRelationship();
//						tradeRelationship.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
//						tradeRelationship.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
//						tradeRelationship.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
//						tradeRelationship.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
//						tradeRelationship.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
//						tradeRelationship.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
//						tradeRelationship.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
//						tradeRelationship.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
//						tradeRelationship.setQuantum(energySaleIntentLine.getNocApprovedCapacity());
//						tradeRelationship.setFromDate(energySaleIntent.getFromDate());
//						tradeRelationship.setToDate(energySaleIntent.getToDate());
////						tradeRelationship.setC1(energySaleIntent.getC1Units());
////						tradeRelationship.setC2(oaa.getC2Units());
////						tradeRelationship.setC3(oaa.getC3Units());
////						tradeRelationship.setC4(oaa.getC4Units());
////						tradeRelationship.setC5(oaa.getC5Units());
//						tradeRelationship.setIsCaptive(energySaleIntent.getIsCaptive());
//						tradeRelationship.setReferenceNumber(energySaleIntentLine.getStandingClearenceId());
//						System.out.println(tradeRelationship);
//						
//						tradeRelationshipId = tradeRelationshipService.addTradeRelationship(tradeRelationship);
//						processLog.setProcessName("TRADERELATIONSHIP");
//						processLog.setProcessStatus("CREATED");
//						processLog.setProcessId(tradeRelationshipId);
//						esiProcessHelper.addProcessLog(processLog);
//						energySaleIntentLine.setTradeRelationshipId(tradeRelationshipId);
//						if(energySaleIntentLine.getTradeRelationshipId()!=null) {
//							energySaleIntent.setStatusCode("COMPLETED");
//						}
//						energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);	
//				
//						processLog.setProcessName("ENERGY-SALE-INTENT");
//						processLog.setProcessStatus("COMPLETED");
//						esiProcessHelper.addProcessLog(processLog);
//			}

				} //if(geLine ID INSIDE for loop is not null) ends here
				} //for loop for esLine ends here
			} //if (esLine not equals NULL) ends here	
		} //flow type code condition ends here
		
		return energySaleIntentId;
		
	}

}
