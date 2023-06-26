package com.ss.oa.transaction.epa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.agreement.AgreementService;
import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Epa;
import com.ss.oa.transaction.vo.EpaLine;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class EpaService {
	@Autowired
	EpaDao dao;
	
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	AgreementService agreementService;
	
	
	@Autowired
	ProcessLogService processLogService;
	String epaId="";

	
	public List<Epa> getAllEpas(Map<String, String> criteria){
		return dao.getAllEpas(criteria);
	}
	public Epa getEpaById(String id) {
		
		return dao.getEpaById(id);
	}
	
	public String addEpa(Epa epa) {
		epaId = dao.addEpa(epa);
		if(epa.getEpaLine()!=null) {
			System.out.println("In line add");
			for(EpaLine epaline :epa.getEpaLine()) {
				epaline.settEpaId(epaId);
				System.out.println("In line add");
				System.out.println(epa);
				System.out.println(epaline);
				dao.addEpaline(epaline);
			}
		}
		return epaId;
	}
	
	public String updateEpa(String id,Epa epa){
		epaId = dao.updateEpa(id,epa);
		if(epa.getEpaLine()!=null) {
			for(EpaLine epaline :epa.getEpaLine()) {
				if(epa.getId()!=null) {
					
					System.out.println(epaline);
					dao.updateEpaline(epaline);
				}else {
					
					epaline.settEpaId(id);
					dao.addEpaline(epaline);					
				}				
			}
		}		
		return epaId ;
	}
	
	public String approveEpa(String id, Epa epa) {
		
		epa.setStatusCode("APPROVED");
		epaId = dao.updateEpa(id,epa);
		agreementService.epaAgreement(epa);
		
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(epa.getEsIntentId());
		processLog.setProcessName("EPA");
		processLog.setProcessStatus("APPROVED");
		processLog.setGenCompId(epa.getSellerCompanyId());
		processLog.setProcessId(epa.getId());
		processLog.setGenServiceId(epa.getSellerCompServiceId());
		processLog.setGenEdcId(epa.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		return epaId;
	}
	public String completeEpa(String id,Epa aggrementEpa) {
		Epa epa =null;
		String esIntentId = "";
		
		if(aggrementEpa.getId()!=null) {
			epa = getEpaById(aggrementEpa.getId()); 	
		}else {
			 epa = getEpaById(id); 
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = epa.getFromDate();
		String toDate =  epa.getToDate();
		
		 try {
			Date fromDate1 = dateFormat.parse(fromDate);
			
			Date toDate1 = dateFormat.parse(toDate);
		
			System.out.println(fromDate1 );
			System.out.println(toDate1);
			System.out.println(dateFormat.format(fromDate1));
			epa.setFromDate(dateFormat.format(fromDate1).toString());
			epa.setToDate(dateFormat.format(toDate1).toString());
		
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		epa.setAgreementDate(aggrementEpa.getAgreementDate());		
	
		epa.setStatusCode("COMPLETED");	
		System.out.println(epa);
		dao.updateEpa(id,epa);		
		esIntentId = energySaleIntentService.processEnergySaleIntent(epa.getEsIntentId());	

		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(epa.getEsIntentId());
		processLog.setProcessName("EPA");
		processLog.setProcessStatus("COMPLETED");
		processLog.setProcessId(epa.getId());
		processLog.setGenCompId(epa.getSellerCompanyId());
		processLog.setGenServiceId(epa.getSellerCompServiceId());
		processLog.setGenEdcId(epa.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		return esIntentId;
}
	
}
