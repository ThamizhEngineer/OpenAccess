package com.ss.oa.transaction.nocgenerator;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.NocGenerator;
import com.ss.oa.transaction.vo.NocGeneratorLine;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class NocGeneratorService {
	
	@Autowired
	NocGeneratorDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;

	@Autowired
	ProcessLogService processLogService;
	
	String nocGenertorId="";
	public List<NocGenerator> getAllNocGenerators(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.getAllNocGenerators(criteria);
	}

	
	public NocGenerator getNocGeneratorById(String id) {
		
		return dao.getNocGeneratorById(id);
	}

	
	public String addNocGenerator(NocGenerator nocGenerator) {
		nocGenertorId = dao.addNocGenerator(nocGenerator);
		if(nocGenerator.getNocGeneratorLines()!=null) {
			for(NocGeneratorLine nocGeneratorLine:nocGenerator.getNocGeneratorLines()) {
				nocGeneratorLine.setNocGeneratorId(nocGenertorId);
				dao.addNocGeneratorLine(nocGeneratorLine);
			}
			
		}
		
		return nocGenertorId;
	}

	
	public String updateNocGenerator(String id, NocGenerator nocGenerator) {
		nocGenertorId = dao.updateNocGenerator(id,nocGenerator);
		if(nocGenerator.getNocGeneratorLines()!=null) {
			for(NocGeneratorLine nocGeneratorLine:nocGenerator.getNocGeneratorLines()) {
				if(nocGeneratorLine.getId()!=null) {
					dao.updateNocGeneratorLine(nocGeneratorLine);
				}else {
					nocGeneratorLine.setNocGeneratorId(id);
					dao.addNocGeneratorLine(nocGeneratorLine);
					
				}
				
			}
			
		}
		
		return nocGenertorId;
	}
	
	public String approveNocGenerator(String id, NocGenerator nocGenerator) {	
		nocGenerator.setStatusCode("APPROVED");	
		dao.updateNocGenerator(id,nocGenerator);		
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(nocGenerator.getEsIntentId());
		processLog.setProcessName("NOC-GENERATOR");
		processLog.setProcessStatus("APPROVED");
		processLog.setGenCompId(nocGenerator.getSellerCompanyId());
		processLog.setGenServiceId(nocGenerator.getSellerCompanyServiceId());
		processLog.setProcessId(nocGenerator.getId());
		processLogService.addProcessLog(processLog);
		return id;
	}
	
	public String completeNocGenerator(String id, NocGenerator nocGenerator) {

		String tEsIntentId = "";
		
		nocGenerator.setStatusCode("COMPLETED");
		
		dao.updateNocGenerator(id,nocGenerator);
		tEsIntentId = energySaleIntentService.processEnergySaleIntent(nocGenerator.getEsIntentId());
		System.out.println(tEsIntentId);
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(nocGenerator.getEsIntentId());
		processLog.setProcessName("NOC-GENERATOR");
		processLog.setProcessStatus("COMPLETED");
		processLog.setProcessId(nocGenerator.getId());
		processLog.setGenCompId(nocGenerator.getSellerCompanyId());
		processLog.setGenServiceId(nocGenerator.getSellerCompanyServiceId());
		processLogService.addProcessLog(processLog);
		return "success";
}
}
