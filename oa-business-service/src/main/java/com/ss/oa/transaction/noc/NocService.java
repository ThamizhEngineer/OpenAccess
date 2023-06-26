package com.ss.oa.transaction.noc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.ProcessLog;


@Component
public class NocService {
	
	@Autowired
	NocDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	ProcessLogService processLogService;
	
	public List<Noc> getAllNocs(Map<String, String> criteria) {
	
		return dao.getAllNocs(criteria);
	}


	public Noc getNocById(String Id) {
	
		return dao.getNocById(Id);
	}


	public String addNoc(Noc noc) {
		return dao.addNoc(noc);
	}


	public String updateNoc(String id,Noc noc) {
		
		return dao.updateNoc(id,noc);
	}
	
	public String completeNoc(String id,Noc aggrementNoc) {

		String tEsIntentId = "";
		System.out.println(aggrementNoc);
		System.out.println(id);
		System.out.println("String ID");	
		Noc noc = getNocById(aggrementNoc.getId()); 
		System.out.println(noc);
		noc.setAgreementDate(aggrementNoc.getAgreementDate());	
		System.out.println("Agreement Date Setted");
		noc.setStatusCode("COMPLETED");
		System.out.println("changed to completed");		
		dao.updateNoc(id,noc);
		System.out.println("Noc Updated");
		tEsIntentId = energySaleIntentService.processEnergySaleIntent(noc.getEnergySaleIntentId());
		System.out.println(tEsIntentId);
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(noc.getEnergySaleIntentId());
		processLog.setProcessName("NOC");
		processLog.setProcessStatus("COMPLETED");
		processLog.setGenCompId(noc.getSellerCompanyId());
		processLog.setGenServiceId(noc.getSellerCompanyServiceId());
		processLog.setGenEdcId(noc.getSellerOrgId());
		processLog.setProcessId(noc.getId());
		processLogService.addProcessLog(processLog);
		return "success";
}
	
	public String approveNoc(String id, Noc noc) {
		
		System.out.println(id);
		System.out.println("noc ID");
		

		
		noc.setStatusCode("APPROVED");
		System.out.println("Status changed to Approved");	
		dao.updateNoc(id,noc);		
		System.out.println("Status Updated");
	
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(noc.getEnergySaleIntentId());
		processLog.setProcessName("NOC");
		processLog.setProcessStatus("APPROVED");
		processLog.setProcessId(noc.getId());
		processLog.setGenCompId(noc.getSellerCompanyId());
		processLog.setGenServiceId(noc.getSellerCompanyServiceId());
		processLog.setGenEdcId(noc.getSellerOrgId());
		processLogService.addProcessLog(processLog);
		return "Approved";
	}

}
