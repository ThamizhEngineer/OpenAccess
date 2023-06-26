package com.ss.oa.transaction.standingclearence;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.model.transaction.IpaStandingClearance;
import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.ipaa.IpaaBusinessHelper;
import com.ss.oa.transaction.ipaastandingclearance.IpaaStandingClearanceRepository;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.ProcessLog;
import com.ss.oa.transaction.vo.StandingClearence;


@Component
public class StandingClearenceService {
	
	@Autowired
	StandingClearenceDao dao;
	
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	ProcessLogService processLogService;
	
	@Autowired
	IpaaBusinessHelper ipaHelper;
	
	@Autowired
	IpaaStandingClearanceRepository ipaRepo;

	
	public List<StandingClearence> getAllStandingClearences(Map<String,String> criteria) throws Exception
	{
		try {
			return dao.getAllStandingClearences(criteria);
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	} 
	public StandingClearence getStandingClearenceById(String id) throws Exception
	{
		try {
			return dao.getStandingClearenceById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public String addStandingClearence(StandingClearence standingClearence)
	{
		return dao.addStandingClearence(standingClearence);
		
	}
	public String updateStandingClearence(String id,StandingClearence standingClearence)
	{
		return dao.updateStandingClearence(id,standingClearence);
	}
	
	public String approveStandingClearence(String id,StandingClearence standingClearence) {	
		standingClearence.setStatusCode("APPROVED");	
		dao.updateStandingClearence(id,standingClearence);		
		
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(standingClearence.getEsIntentId());
		processLog.setProcessName("STANDING-CLEARENCE");
		processLog.setProcessStatus("APPROVED");
		processLog.setProcessId(standingClearence.getId());
		processLogService.addProcessLog(processLog);
		return id;
	}
	
	public String completeStandingClearence(String id,StandingClearence standingClearence) {

		String tEsIntentId = "";
		
		standingClearence.setStatusCode("COMPLETED");
		
		dao.updateStandingClearence(id,standingClearence);
		tEsIntentId = energySaleIntentService.processEnergySaleIntent(standingClearence.getEsIntentId());
		System.out.println(tEsIntentId);
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(standingClearence.getEsIntentId());
		processLog.setProcessName("STANDING-CLEARENCE");
		processLog.setProcessStatus("COMPLETED");
		processLog.setProcessId(standingClearence.getId());
		processLogService.addProcessLog(processLog);
		return id;
}
	//Approve standing clearance
	public StandingClearence approveIpaStandingClearence(StandingClearence standingclearence) {
		standingclearence.setStatusCode("APPROVED");
		String scId= standingclearence.getId();
		dao.updateStandingClearence(scId,standingclearence);
		
		IpaStandingClearance ipasc = ipaRepo.findByScId(scId);
		String ipaScId= ipasc.getId();
		ipaHelper.procesStandingClearance(ipaScId);
		return standingclearence;
		
	}
}



