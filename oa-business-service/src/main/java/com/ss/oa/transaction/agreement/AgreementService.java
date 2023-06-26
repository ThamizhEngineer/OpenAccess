package com.ss.oa.transaction.agreement;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.epa.EpaService;
import com.ss.oa.transaction.ewa.EwaService;
import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.vo.Agreement;
import com.ss.oa.transaction.vo.AgreementLine;
import com.ss.oa.transaction.vo.Epa;

import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.EwaLine;
import com.ss.oa.transaction.vo.Oaa;


@Component
public class AgreementService {
	
	@Autowired
	AgreementDao dao;
	
	String agreementId="";
	
	@Autowired
	EwaService ewaService;
	
	@Autowired
	EpaService epaService;
	
	@Autowired
	OaaService oaaService;
	

	public List<Agreement> getAllAgreements(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.getAllAgreements(criteria);
	}


	public Agreement getAgreementById(String id) {
		// TODO Auto-generated method stub
		return dao.getAgreementById(id);
	}


	public String addAgreement(Agreement agreement) {
		agreementId =dao.addAgreement(agreement);
		if(agreement.getAgreementLines()!=null) {
			for(AgreementLine agreementLine : agreement.getAgreementLines()) {
				agreementLine.setAgreementId(agreementId);
				dao.addAgreementLine(agreementLine);
			}
			
		}
		return agreementId;
	}


	public String updateAgreement(String agreementId, Agreement agreement) {
		System.out.println(agreement);
		agreementId =dao.updateAgreement(agreementId, agreement);
		if(agreement.getAgreementLines()!=null) {
			for(AgreementLine agreementLine : agreement.getAgreementLines()) {
				agreementLine.setAgreementId(agreementId);
				
				if(agreementLine.getId()!=null) {
					dao.updateAgreementLine(agreementLine);
				}else {
					agreementLine.setAgreementId(agreementId);
					dao.addAgreementLine(agreementLine);
				}
			}
			
		}
		return agreementId;
	}
	
	public String agreementCancelled(String agreementId, Agreement agreement) {
		if(agreement.getStatusCode().equals("CANCELLED")) {
			throw new OpenAccessException("Agreement Cannot Cancelled - cannot cancel already cancelled agreement");
		}else {	
			agreement.setStatusCode("CANCELLED");	
			System.out.println(agreement);
			agreementId =dao.updateAgreement(agreementId, agreement);
		}	
		return agreementId;
	}
	
	public String ewaAgreement(Ewa ewa) {
		String agreementId="";
		Agreement agreement =  new Agreement();
		List<AgreementLine> agreementLineList =  new ArrayList<AgreementLine>();
		agreement.setSellerCompanyServiceId(ewa.getSellerCompServiceId());
		agreement.setSellerOrgId(ewa.getSellerOrgId());
		agreement.setSellerCompanyId(ewa.getSellerCompanyId());
		agreement.setAgreementDate(ewa.getAgreementDate());
		agreement.setFromDate(ewa.getFromDate());
		agreement.setToDate(ewa.getToDate());
		agreement.setFromMonth(ewa.getFromMonth());
		agreement.setToMonth(ewa.getToMonth());
		agreement.setFromYear(ewa.getFromYear());
		agreement.setToYear(ewa.getToYear());
		agreement.setAgreementPeriodCode(ewa.getAgreementPeriodCode());	
		agreement.setFlowTypeCode(ewa.getFlowTypeCode());
		agreement.setEsIntentId(ewa.gettEsIntentId());
		agreement.setEwaId(ewa.getId());
		if(ewa.getEwaLines()!=null) {
			for(EwaLine ewaLine:ewa.getEwaLines()) {
				AgreementLine agreementLine =  new AgreementLine();
				agreementLine.setBuyerCompanyServiceId(ewaLine.getBuyerCompServiceId());
				agreementLine.setBuyerOrgId(ewaLine.getBuyerOrgId());
				agreementLine.setDrawalVoltageCode(ewaLine.getDrawalVoltageCode());
				agreementLine.setProposedCapacity(ewaLine.getApprovedUnits());
				agreementLine.setApprovedCapacity(ewaLine.getApprovedUnits());
				agreementLine.setAgreementPeriodCode(ewa.getAgreementPeriodCode());
				agreementLine.setFlowTypeCode(ewa.getFlowTypeCode());
				agreementLine.setAppliedDate(ewaLine.getAppliedDate());
				agreementLine.setApprovedDate(ewaLine.getApprovedDate());
				agreementLine.setAgreementDate(ewa.getAgreementDate());
				agreementLine.setIsCaptive(ewaLine.getIsCaptive());
				agreementLine.setPeakUnits(ewaLine.getDrawalPeakUnits());
				agreementLine.setOffPeakUnits(ewaLine.getDrawalOffPeakUnits());
				agreementLine.setEwaLineId(ewaLine.getId());
				agreementLineList.add(agreementLine);
				
			}
		}
		agreement.setStatusCode("CREATED");
		agreement.setAgreementLines(agreementLineList);
		agreementId = addAgreement(agreement);
		return agreementId;
		
	}
	
	public String oaaAgreement(Oaa oaa) {

		Agreement agreement =  new Agreement();	
		agreement.setSellerCompanyServiceId(oaa.getSellerCompServiceId());
		agreement.setSellerOrgId(oaa.getSellerOrgId());
		agreement.setSellerCompanyId(oaa.getSellerCompanyId());
		agreement.setAgreementDate(oaa.getAgreementDate());
		agreement.setFromDate(oaa.getFromDate());
		agreement.setToDate(oaa.getToDate());	
		agreement.setAgreementPeriodCode(oaa.getAgreementPeriodCode());	
		agreement.setFlowTypeCode(oaa.getFlowTypeCode());
		agreement.setEsIntentId(oaa.gettEsIntentId());
		agreement.setOaaId(oaa.getId());	
		agreement.setBuyerCompanyServiceId(oaa.getBuyerCompServiceId());
		agreement.setBuyerOrgId(oaa.getBuyerOrgId());	
		agreement.setBuyerCompanyId(oaa.getBuyerCompanyId());
		agreement.setAgreementPeriodCode(oaa.getAgreementPeriodCode());
		agreement.setIntervalTypeCode(oaa.getIntervalTypeCode());	
		agreement.setAgreementDate(oaa.getAgreementDate());
		agreement.setC1(oaa.getC1Units());
		agreement.setC2(oaa.getC2Units());
		agreement.setC3(oaa.getC3Units());
		agreement.setC4(oaa.getC4Units());
		agreement.setC5(oaa.getC5Units());
		agreement.setTotalUnits(oaa.getApprovedTotalUnits());
		agreement.setEsIntentId(oaa.gettEsIntentId());
		agreement.setStatusCode("CREATED");
		agreementId = addAgreement(agreement);
		return agreementId;
		
	}
	
	public String agreementSigned(String agreementId, Agreement agreement) {
		if(agreement.getStatusCode().equals("SIGNED")) {
			throw new OpenAccessException("Agreement Cannot Signed - cannot sign already signed agreement");
		}else {
			if(agreement.getEwaId()!=null) {
				agreement.setStatusCode("SIGNED");
				Ewa ewa = new Ewa();
				agreementId = updateAgreement(agreementId, agreement);
				System.out.println(agreement);
				ewaService.completeEwa(agreement.getEwaId(), ewa);		
			}else if(agreement.getOaaId()!=null) {
				agreement.setStatusCode("SIGNED");
				Oaa oaa = new Oaa();
				agreementId = updateAgreement(agreementId, agreement);
				System.out.println(agreement);
				oaaService.completeOaa(agreement.getOaaId(), oaa);	
			}else if(agreement.getEpaId()!=null) {
				agreement.setStatusCode("SIGNED");
				Epa epa = new Epa();
				agreementId = updateAgreement(agreementId, agreement);
				System.out.println(agreement);
				epaService.completeEpa(agreement.getEpaId(), epa);	
			}
		}
		
		return agreementId;
	}
	
	public String epaAgreement(Epa epa) {
		Agreement agreement =  new Agreement();	
		agreement.setSellerCompanyServiceId(epa.getSellerCompServiceId());
		agreement.setSellerOrgId(epa.getSellerOrgId());
		agreement.setSellerCompanyId(epa.getSellerCompanyId());
		agreement.setAgreementDate(epa.getAgreementDate());
		agreement.setFromDate(epa.getFromDate());
		agreement.setToDate(epa.getToDate());	
		agreement.setAgreementPeriodCode(epa.getAgreementPeriodCode());	
		agreement.setFlowTypeCode(epa.getFlowTypeCode());
		agreement.setEsIntentId(epa.getEsIntentId());
		agreement.setEpaId(epa.getId());	
		agreement.setBuyerCompanyServiceId(epa.getBuyerCompanyServiceId());
		agreement.setBuyerOrgId(epa.getBuyerOrgId());	
		agreement.setBuyerCompanyId(epa.getBuyerCompanyId());
		agreement.setIsCaptive(epa.getSellerIsCaptive());
		agreement.setAgreementPeriodCode(epa.getAgreementPeriodCode());
		agreement.setIntervalTypeCode(epa.getIntervalTypeCode());	
		agreement.setAgreementDate(epa.getAgreementDate());
		agreement.setC1(epa.getC1());
		agreement.setC2(epa.getC2());
		agreement.setC3(epa.getC3());
		agreement.setC4(epa.getC4());
		agreement.setC5(epa.getC5());
		agreement.setPeakUnits(epa.getPeakUnits());		
		agreement.setOffPeakUnits(epa.getOffPeakUnits());
		agreement.setSharePercent(epa.getSharePercent());
		agreement.setTotalUnits(epa.getApprovedTotalUnits());
		agreement.setEsIntentId(epa.getEsIntentId());
		agreement.setStatusCode("CREATED");
		agreementId = addAgreement(agreement);
		return agreementId;
	}
	


}
