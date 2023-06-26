package com.ss.oa.transaction.ewa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.agreement.AgreementService;
import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.EwaLine;
import com.ss.oa.transaction.vo.ProcessLog;


@Component
public class EwaService {
	
	@Autowired
	EwaDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	AgreementService agreementService;
	
	@Autowired
	ProcessLogService processLogService;
	
	
	String ewaId="";
	
	public EwaService() {
		super();
	}

	

	public List<Ewa> getAllewas(Map<String, String> criteria) {
		
		return dao.getAllewas(criteria);
	}

	
	public Ewa getewaById(String id) {
		
		return dao.getewaById(id);
	}

	public String addewa(Ewa ewa) {
		ewaId = dao.addewa(ewa);
		if(ewa.getEwaLines()!=null) {
			System.out.println("In line add");
			for(EwaLine ewaline :ewa.getEwaLines()) {
				ewaline.setEwaId(ewaId);
				System.out.println("In line add");
				System.out.println(ewa);
				System.out.println(ewaline);
				dao.addewaline(ewaline);
			}
		}
		return ewaId;
	}

	
	public String updateewa(String id,Ewa ewa){
		ewaId = dao.updateewa(id,ewa);
		if(ewa.getEwaLines()!=null) {
			for(EwaLine ewaline :ewa.getEwaLines()) {
				if(ewa.getId()!=null) {
					
					System.out.println(ewaline);
					dao.updateewaline(ewaline);
				}else {
					
					ewaline.setEwaId(id);
					dao.addewaline(ewaline);					
				}				
			}
		}		
		return ewaId ;
	}
	

	public String approveEwa(String id, Ewa ewa) {
	
		ewa.setStatusCode("APPROVED");
		ewaId = dao.updateewa(id,ewa);
		agreementService.ewaAgreement(ewa);
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(ewa.gettEsIntentId());
		processLog.setProcessName("EWA");
		processLog.setProcessStatus("APPROVED");
		processLog.setGenCompId(ewa.getSellerCompanyId());
		processLog.setGenServiceId(ewa.getSellerCompServiceId());
		processLog.setGenEdcId(ewa.getSellerOrgId());
		processLog.setProcessId(ewa.getId());
		processLogService.addProcessLog(processLog);
		return ewaId;
	}
	public String completeEwa(String id,Ewa aggrementEwa) {
		Ewa ewa =null;
		String esIntentId = "";
		if(aggrementEwa.getId()!=null) {
			ewa = getewaById(aggrementEwa.getId()); 	
		}else {
			 ewa = getewaById(id); 
		}
		
		ewa.setAgreementDate(aggrementEwa.getAgreementDate());		
		ewa.setStatusCode("COMPLETED");			
		dao.updateewa(id,ewa);		
		
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(ewa.gettEsIntentId());
		processLog.setProcessName("EWA");
		processLog.setProcessStatus("COMPLETED");
		processLog.setGenCompId(ewa.getSellerCompanyId());
		processLog.setProcessId(ewa.getId());
		processLog.setGenServiceId(ewa.getSellerCompServiceId());
		processLog.setGenEdcId(ewa.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		
		esIntentId = energySaleIntentService.processEnergySaleIntent(ewa.gettEsIntentId());		
		
		return esIntentId;
}
	
}
