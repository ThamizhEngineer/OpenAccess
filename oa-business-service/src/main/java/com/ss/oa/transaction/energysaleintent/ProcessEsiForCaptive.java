package com.ss.oa.transaction.energysaleintent;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.Response;
import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class ProcessEsiForCaptive {
	

	@Autowired
	EnergySaleIntentService energySaleIntentService;

	@Autowired
	OaaService oaaService;
	
	
	@Autowired
	EsiProcessHelper esiProcessHelper;
	
	String energySaleIntentId = "";

	String nocId = "";

	String consentId = "";

	String ooaId = "";

	String ewaId = "";

	String epaId = "";
	
	Response result= new Response();

	public ProcessEsiForCaptive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String processEnergySaleIntent(EnergySaleIntent energySaleIntent) {
		Boolean isEwa = true;
		Boolean isEwaCompleted = true;
//		Boolean isTrade = false;
		energySaleIntentId = energySaleIntent.getId();
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(energySaleIntentId);
	
		processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
		processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
		processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
		if (energySaleIntent.getEnergySaleIntentLines() != null) {
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				if (energySaleIntentLine.getId() != null) {
	
					if (energySaleIntentLine.getBuyerCompanyServiceId().startsWith("TNEB")) {
	
//						String epaId = "";
	
						// Epa epa = new Epa();
						// epa.settEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
						// epa.setStatusCode("CREATED");
						//
						//
						// epa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
						// epa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
						// epa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
						// epa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
						// epa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
						// epa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
						//
						//
						//
						//
						// epaId = epaService.addEpa(epa);
	
					} else {
	
						if (energySaleIntentLine.getNocId() == null || energySaleIntentLine.getNocId().isEmpty()) {
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
						} else if (energySaleIntentLine.getNocId() != null
								&& (energySaleIntentLine.getConsentId() == null
										|| energySaleIntentLine.getConsentId().isEmpty())) {
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
	
								
							}
	
						} else if (energySaleIntentLine.getConsentId() != null
								&& (energySaleIntent.getEwaId() == null || energySaleIntent.getEwaId().isEmpty())||energySaleIntent.getEwaId().equals("Failue")) {
	
							if ((energySaleIntentLine.getNocStatusCode() != null && energySaleIntentLine.getNocStatusCode().equals("COMPLETED")) && (energySaleIntentLine.getConsentStatusCode() != null && energySaleIntentLine.getConsentStatusCode().equals("COMPLETED"))) {

	
							} else {
								isEwa = false;
							
							}
						}
					}
				}
			}
			
	
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				
				if(energySaleIntentLine.getConsentId() != null && (energySaleIntentLine.getConsentStatusCode()!=null && energySaleIntentLine.getConsentStatusCode().equals("COMPLETED"))) {
					
					if(isEwa && energySaleIntent.getStatusCode().equals("PROCESS")) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date();
						energySaleIntent.setSldcAwaitedDt(dateFormat.format(date));
						energySaleIntent.setStatusCode("AWATING-SLDC-APPROVAL");
						energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
					}
					
					
					if (isEwa && (energySaleIntent.getEwaId() == null || energySaleIntent.getEwaId().isEmpty()) && (energySaleIntent.getStatusCode().equals("SLDC-APPROVED"))) {
						
						System.out.println("in add ewa calling method");
						if (energySaleIntentLine.getBuyerCompanyServiceId().startsWith("TNEB")) {
		
						} else {
							ewaId = esiProcessHelper.addEwaForCaptive(energySaleIntent);
							energySaleIntent.setEwaId(ewaId);
							energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
							processLog.setProcessName("EWA");
							processLog.setProcessStatus("CREATED");
							processLog.setProcessId(ewaId);
							esiProcessHelper.addProcessLog(processLog);
						}
		
					}	
				}
	
			
				
					if (energySaleIntent.getEwaId() != null && (energySaleIntent.getEwaStatusCode() != null
							&& energySaleIntent.getEwaStatusCode().equals("COMPLETED"))) {					
					
						
					} else {
						isEwaCompleted=false;
					
					
				}
	
				
			}
	
		
			if(isEwaCompleted) {
				energySaleIntent.setStatusCode("COMPLETED");
				energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
				processLog.setProcessName("ENERGY-SALE-INTENT");
				processLog.setProcessStatus("COMPLETED");
				esiProcessHelper.addProcessLog(processLog);
			}
		}
		return energySaleIntentId;
	}
}
