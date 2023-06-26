package com.ss.oa.transaction.oaa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.transaction.agreement.AgreementService;
import com.ss.oa.transaction.energysaleintent.EnergySaleIntentService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.Oaa;
import com.ss.oa.transaction.vo.ProcessLog;


@Component
public class OaaService {
	
	@Autowired
	OaaDao dao;
	@Autowired
	EnergySaleIntentService energySaleIntentService;
	
	@Autowired
	TradeRelationshipService tradeRelationshipService;
	
	@Autowired
	AgreementService agreementService;
	
	@Autowired
	ProcessLogService processLogService;
	
	String oaaId;
	
	String tradeRelationshipId="";
	
	public OaaService() {
		
		super();

	}
	
	public List<Oaa> getAllOaa(Map<String,String> criteria){
		
		return dao.getAllOaas(criteria);
	}
	
	public Oaa getOaaById(String id) {
		
		return dao.getOaaById(id);
	}
	public String addOaa(Oaa oaa) {
		oaaId = dao.addOaa(oaa);
		System.out.println("oaaid" + oaaId);
		return oaaId;

			
		}
	
	public String updateOaa(String id,Oaa oaa) {
		oaaId = dao.updateOaa(id, oaa);

		return oaaId;
	}
	
	public String completeOaa(String oaaId,Oaa aggrementOaa) {
		Oaa oaa =null;
		String tEsIntentId = "";
		if(aggrementOaa.getId()!=null) {
			oaa = getOaaById(aggrementOaa.getId());
		}else {
			oaa = getOaaById(oaaId);
		}
		
	
		oaa.setAgreementDate(aggrementOaa.getAgreementDate());		
		
		oaa.setStatusCode("COMPLETED");

		dao.updateOaa(oaaId,oaa);		

		if(oaa.getFlowTypeCode().equals("CAPTIVE")) {
			addTradeRelationship(oaaId);
			tEsIntentId = oaa.gettEsIntentId();
		}else {
			tEsIntentId = energySaleIntentService.processEnergySaleIntent(oaa.gettEsIntentId());
		}
		
	
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(oaa.gettEsIntentId());
		processLog.setProcessName("OAA");
		processLog.setProcessStatus("COMPLETED");
		processLog.setGenCompId(oaa.getSellerCompanyId());
		processLog.setGenServiceId(oaa.getSellerCompServiceId());
		processLog.setGenEdcId(oaa.getSellerOrgId());
		processLog.setProcessId(oaa.getId());
		processLogService.addProcessLog(processLog);
		
		return tEsIntentId;
}
	
	public String approveOaa(String oaaId, Oaa oaa) {	
		
		oaa.setStatusCode("APPROVED");		
		oaaId = dao.updateOaa(oaaId,oaa);	
		agreementService.oaaAgreement(oaa);

		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(oaa.gettEsIntentId());
		processLog.setProcessName("OAA");
		processLog.setProcessStatus("APPROVED");
		processLog.setGenCompId(oaa.getSellerCompanyId());
		processLog.setGenServiceId(oaa.getSellerCompServiceId());
		processLog.setGenEdcId(oaa.getSellerOrgId());
		processLog.setProcessId(oaa.getId());
		processLogService.addProcessLog(processLog);
		return oaaId;
	}
	
	public String addTradeRelationship(String oaaId) {
		
		Oaa oaa = getOaaById(oaaId);
		TradeRelationship tradeRelationship =  new TradeRelationship();
		tradeRelationship.setSellerCompServiceId(oaa.getSellerCompServiceId());
		tradeRelationship.setSellerCompServiceNumber(oaa.getSellerCompServiceNumber());
		tradeRelationship.setSellerCompanyId(oaa.getSellerCompanyId());
		tradeRelationship.setSellerCompanyName(oaa.getSellerCompanyName());
		tradeRelationship.setBuyerCompanyId(oaa.getBuyerCompanyId());
		tradeRelationship.setBuyerCompanyName(oaa.getBuyerCompanyName());
		tradeRelationship.setBuyerCompServiceId(oaa.getBuyerCompServiceId());
		tradeRelationship.setBuyerCompServiceNumber(oaa.getBuyerCompServiceNumber());
		tradeRelationship.setQuantum(oaa.getApprovedTotalUnits());
		tradeRelationship.setFromDate(oaa.getFromDate());
		tradeRelationship.setToDate(oaa.getToDate());
		tradeRelationship.setC1(oaa.getC1Units());
		tradeRelationship.setC2(oaa.getC2Units());
		tradeRelationship.setC3(oaa.getC3Units());
		tradeRelationship.setC4(oaa.getC4Units());
		tradeRelationship.setC5(oaa.getC5Units());
		tradeRelationship.setPeakUnits(oaa.getPeakUnits());
		tradeRelationship.setOffPeakUnits(oaa.getOffPeakUnits());
		tradeRelationship.setIntervalTypeCode(oaa.getIntervalTypeCode());
		tradeRelationship.setSharePercent(oaa.getSharePercent());
		tradeRelationship.setIsCaptive(oaa.getSellerIsCaptive());
		tradeRelationship.setTraderelationshipSourceCode("OAA");
		tradeRelationship.setAgreementType(oaa.getAgreementPeriodCode());
		tradeRelationship.setReferenceNumber(oaaId);
		
		System.out.println(tradeRelationship);
		
		tradeRelationshipId = tradeRelationshipService.addTradeRelationship(tradeRelationship);
		return tradeRelationshipId;
	}
	
	

}
