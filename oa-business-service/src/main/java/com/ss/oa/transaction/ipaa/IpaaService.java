package com.ss.oa.transaction.ipaa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.IpaaLine;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class IpaaService {
	
	@Autowired
	IpaaDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	ProcessLogService processLogService;
	
	String ipaaId="";
	
	public List<Ipaa> getAllIpaa(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.getAllIpaa(criteria);
	}


	public Ipaa getIpaaById(String id) {
		// TODO Auto-generated method stub
		return dao.getIpaaById(id);
	}


	public String addIpaa(Ipaa ipaa) {
		ipaaId = dao.addIpaa(ipaa);
		if(ipaa.getIpaaLines()!=null) {
			for(IpaaLine ipaaLine:ipaa.getIpaaLines()) {
				ipaaLine.setIpaaId(ipaaId);
				dao.addIpaaLine(ipaaLine);
			}
			
		}
		
		return ipaaId;
	}


	public String updateIpaa(String id, Ipaa ipaa) {
		ipaaId = dao.updateIpaa(id,ipaa);
		if(ipaa.getIpaaLines()!=null) {
			for(IpaaLine ipaaLine:ipaa.getIpaaLines()) {
				if(ipaaLine.getId()!=null) {
					dao.updateIpaaLine(ipaaLine);
				}else {
					ipaaLine.setIpaaId(id);
					dao.addIpaaLine(ipaaLine);
				}
					
			}
			
		}
		return ipaaId;
	}

	public String approveIpaa(String id, Ipaa ipaa) {	
		ipaa.setStatusCode("APPROVED");	
		dao.updateIpaa(id,ipaa);	
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(ipaa.getEsIntentId());
		processLog.setProcessName("IPAA");
		processLog.setProcessStatus("APPROVED");
		processLog.setGenCompId(ipaa.getSellerCompanyId());
		processLog.setGenServiceId(ipaa.getSellerCompanyServiceId());
		processLog.setGenEdcId(ipaa.getSellerOrgId());
		processLog.setProcessId(ipaa.getId());
		processLogService.addProcessLog(processLog);
		return id;
	}
	
	public String completeIpaa(String id, Ipaa ipaa) {

		String tEsIntentId = "";
		
		ipaa.setStatusCode("COMPLETED");
		
		dao.updateIpaa(id,ipaa);
//		tEsIntentId = energySaleIntentService.processEnergySaleIntent(ipaa.getEsIntentId());
		System.out.println(tEsIntentId);
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(ipaa.getEsIntentId());
		processLog.setProcessName("IPAA");
		processLog.setProcessStatus("COMPLETED");
		processLog.setGenCompId(ipaa.getSellerCompanyId());
		processLog.setGenServiceId(ipaa.getSellerCompanyServiceId());
		processLog.setGenEdcId(ipaa.getSellerOrgId());
		processLog.setProcessId(ipaa.getId());
		processLogService.addProcessLog(processLog);
		
		return "success";
}
}
