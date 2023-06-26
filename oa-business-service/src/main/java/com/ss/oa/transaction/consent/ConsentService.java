package com.ss.oa.transaction.consent;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Consent;
import com.ss.oa.transaction.vo.ProcessLog;


@Scope("prototype")
@Component
public class ConsentService {
	
	
	@Autowired
	ConsentDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	ProcessLogService processLogService;
	
	String consentId;
	
	public ConsentService() {
		
		super();

	}
	
	public List<Consent> getAllConsents(Map<String,String> criteria){
		
		return dao.getAllConsents(criteria);
	}
	
	public Consent getConsentById(String id) {
		
		return dao.getConsentById(id);
	}
	public String addConsent(Consent consent) {
		consentId = dao.addConsent(consent);
		System.out.println("consentid" + consentId);
		return consentId;

			
		}
	
	public String updateConsent(String id,Consent consent) {
		consentId = dao.updateConsent(id, consent);

		return consentId;
	}
	
	
	public String completeConsent(String conId,Consent aggrementConsent) {
		
		String tEsIntentId = "";
		System.out.println(aggrementConsent);
		System.out.println(conId);
		System.out.println("String ID");	
		Consent consent = getConsentById(aggrementConsent.getId()); 
		System.out.println(consent);
		consent.setAgreementDate(aggrementConsent.getAgreementDate());	
		System.out.println("Agreement Date Setted");
		consent.setStatusCode("COMPLETED");
		System.out.println("changed to completed");		
		dao.updateConsent(conId,consent);
		System.out.println("Consent Updated");
		tEsIntentId = energySaleIntentService.processEnergySaleIntent(consent.gettEsIntentId());
		System.out.println(tEsIntentId);
		
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(consent.gettEsIntentId());
		processLog.setProcessName("CONSENT");
		processLog.setProcessStatus("COMPLETED");
		processLog.settEnergySaleId(consent.gettEsIntentId());
		processLog.setProcessId(consent.getId());
		processLog.setGenCompId(consent.getSellerCompanyId());
		processLog.setGenServiceId(consent.getSellerCompServiceId());
		processLog.setGenEdcId(consent.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		
		return "success";
}
	
	public String approveConsent(String conId, Consent consent) {
		
		System.out.println(conId);
		System.out.println("Consent ID");
		consent.setStatusCode("APPROVED");
		System.out.println("Status changed to Approved");	
		dao.updateConsent(conId,consent);		
		System.out.println("Status Updated");
	
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(consent.gettEsIntentId());
		processLog.setProcessName("CONSENT");
		processLog.setProcessStatus("APPROVED");
		processLog.setProcessId(consent.getId());
		processLog.setGenCompId(consent.getSellerCompanyId());
		processLog.setGenServiceId(consent.getSellerCompServiceId());
		processLog.setGenEdcId(consent.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		return "Approved";
	}
		
	}
	

