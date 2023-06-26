package com.ss.oa.transaction.energysaleintent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.Oaa;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class ProcessEsiForThirdParty {

	public ProcessEsiForThirdParty() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	EnergySaleIntentService energySaleIntentService;

	@Autowired
	EsiProcessHelper esiProcessHelper;
	
	@Autowired
	OaaService oaaService;
	
	
	String energySaleIntentId="";
	
	String ipaaId="";
	
	String nocGeneratorId="";
	
	String ewaId=""; 
	
	String nocId ="";
	
	String consentId="";
	
	String ooaId="";
	
	public String processEnergySaleIntentForThirdParty(EnergySaleIntent energySaleIntent) {
		Boolean isTrade = false;
		energySaleIntentId = energySaleIntent.getId();
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(energySaleIntentId);	
		processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
		processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
		processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
		if (energySaleIntent.getFlowTypeCode().equals("THIRD-PARTY")) {
			
			if(energySaleIntent.getIpaaId()==null || energySaleIntent.getIpaaId().isEmpty()) {
				ipaaId = esiProcessHelper.addIpaa(energySaleIntent);
				System.out.println("ipaaId"+ipaaId);
				energySaleIntent.setIpaaId(ipaaId);
				energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
				processLog.setProcessName("IPAA");
				processLog.setProcessStatus("CREATED");
				processLog.setProcessId(ipaaId);
				esiProcessHelper.addProcessLog(processLog);
			}else if( energySaleIntent.getIpaaId()!=null&& (energySaleIntent.getNocGeneratorId()==null || energySaleIntent.getNocGeneratorId().isEmpty() ) ) {
				if(energySaleIntent.getIpaaStatusCode()!=null&& energySaleIntent.getIpaaStatusCode().equals("COMPLETED")) {
					nocGeneratorId = esiProcessHelper.addNocGenerator(energySaleIntent);
					System.out.println("nocGeneratorId"+nocGeneratorId);
					energySaleIntent.setNocGeneratorId(nocGeneratorId);
					energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
					processLog.setProcessName("NOC-GENERATOR");
					processLog.setProcessStatus("CREATED");
					processLog.setProcessId(nocGeneratorId);
					esiProcessHelper.addProcessLog(processLog);
				}
			} else if(energySaleIntent.getNocGeneratorId()!=null && (energySaleIntent.getEwaId()==null || energySaleIntent.getEwaId().isEmpty()) ) {
				if(energySaleIntent.getNocGeneratorStatusCode()!=null&& energySaleIntent.getNocGeneratorStatusCode().equals("COMPLETED")) {
					
				ewaId = esiProcessHelper.addEwaForCaptive(energySaleIntent)	;
				System.out.println("ewaId"+ewaId);
				energySaleIntent.setEwaId(ewaId);
				energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
				processLog.setProcessName("EWA");
				processLog.setProcessStatus("CREATED");
				processLog.setProcessId(ewaId);
				esiProcessHelper.addProcessLog(processLog);
				}
			}

			if(energySaleIntent.getEwaStatusCode()!=null && energySaleIntent.getEwaStatusCode().equals("COMPLETED") ) {
			
				if (energySaleIntent.getEnergySaleIntentLines() != null) {
					for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
						if (energySaleIntentLine.getId() != null) {							
			
							if (energySaleIntent.getEwaId()!=null && (energySaleIntentLine.getNocId() == null || energySaleIntentLine.getNocId().isEmpty())) {
						
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
								
								
							}else if (energySaleIntentLine.getNocId() != null && (energySaleIntentLine.getConsentId() == null || energySaleIntentLine.getConsentId().isEmpty())) {
									System.out.println("In add consent");
									if (energySaleIntentLine.getNocStatusCode() != null
											&& energySaleIntentLine.getNocStatusCode().equals("COMPLETED")) {
				
										consentId = esiProcessHelper.addConsent(energySaleIntentLine);
										System.out.println("consentId" + consentId);
										energySaleIntentLine.setConsentId(consentId);
										energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
										processLog.setProcessName("CONSENT");
										processLog.setProcessStatus("CREATED");
										processLog.setProcessId(consentId);
										esiProcessHelper.addProcessLog(processLog);
									} else {
											System.out.println("Consent Status is not complete!" + consentId);
	
							// throw new OpenAccessException("Consent Status is not complete!");
											}
	
							}else 	if ( energySaleIntentLine.getConsentId()!=null && (energySaleIntentLine.getOaAgmtId()==null || energySaleIntentLine.getOaAgmtId().isEmpty() )) {
								if (energySaleIntentLine.getConsentStatusCode() != null
										&& energySaleIntentLine.getConsentStatusCode().equals("COMPLETED")) {
			
										Oaa oaa = new Oaa();
										oaa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
										oaa.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
										oaa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
										oaa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
										oaa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
										oaa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
										oaa.setFromDate(energySaleIntent.getFromDate());
										oaa.setToDate(energySaleIntent.getToDate());
										oaa.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
										oaa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
										oaa.setProposedTotalUnits(energySaleIntentLine.getConsentApprovedCapacity());
										oaa.setApprovedTotalUnits(energySaleIntentLine.getConsentApprovedCapacity());
										ooaId = esiProcessHelper.addOaa(oaa, energySaleIntentLine);
										System.out.println("ooaId" + ooaId);
										energySaleIntentLine.setOaAgmtId(ooaId);
										energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
										processLog.setProcessName("OAA");
										processLog.setProcessStatus("CREATED");
										processLog.setProcessId(ooaId);
										esiProcessHelper.addProcessLog(processLog);
								}else {
									System.out.println("Oaa Status is not complete!" + ooaId);
									// throw new OpenAccessException("Oaa Status is not complete!");
								} 
				
							}else if (energySaleIntentLine.getTradeRelationshipId() == null
									&& (energySaleIntentLine.getOaAgmtStatusCode() != null
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
		
					}else if(energySaleIntentLine.getTradeRelationshipId()!=null&& (energySaleIntentLine.getOaAgmtStatusCode() != null
							&& energySaleIntentLine.getOaAgmtStatusCode().equals("COMPLETED"))) {
							isTrade =true;
				}else {
						isTrade =false;
					}
		
				}
				if(isTrade) {
					energySaleIntent.setStatusCode("COMPLETED");
					energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
					processLog.setProcessName("ENERGY-SALE-INTENT");
					processLog.setProcessStatus("COMPLETED");
					esiProcessHelper.addProcessLog(processLog);
				}
					}
				}
			}
			
	
		}
		
	
		return energySaleIntentId;
	}

}
