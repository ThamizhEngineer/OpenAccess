package com.ss.oa.transaction.energysaleintent;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.model.transaction.IexNoc;
import com.ss.oa.transaction.consent.ConsentService;
import com.ss.oa.transaction.epa.EpaService;
import com.ss.oa.transaction.ewa.EwaService;
import com.ss.oa.transaction.iexnoc.IexNocRepository;
import com.ss.oa.transaction.ipaa.IpaaService;
import com.ss.oa.transaction.noc.NocService;
import com.ss.oa.transaction.nocgenerator.NocGeneratorService;
import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.standingclearence.StandingClearenceService;
import com.ss.oa.transaction.vo.Consent;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.Epa;
import com.ss.oa.transaction.vo.EpaLine;
import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.EwaLine;
import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.IpaaLine;
import com.ss.oa.transaction.vo.Noc;
import com.ss.oa.transaction.vo.NocGenerator;
import com.ss.oa.transaction.vo.NocGeneratorLine;
import com.ss.oa.transaction.vo.Oaa;
import com.ss.oa.transaction.vo.ProcessLog;
import com.ss.oa.transaction.vo.StandingClearence;

@Scope("prototype")
@Component
public class EsiProcessHelper {
	
	public EsiProcessHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	NocService nocService;

	@Autowired
	ConsentService consentService;
	
	@Autowired
	OaaService oaaService;

	@Autowired
	EwaService ewaService;
	
	@Autowired
	EpaService epaService;
	
	@Autowired
	IpaaService ipaaService;

	@Autowired
	NocGeneratorService nocGeneratorService;
	
	@Autowired
	StandingClearenceService standingClearenceService;
	
	@Autowired
	IexNocRepository iexNocRepository;
	
	
	@Autowired
	ProcessLogService processLogService;
	
	String energySaleIntentId = "";

	String nocId = "";

	String consentId = "";

	String ooaId = "";

	String ewaId = "";

	
	
	
	
	public String addNoc(EnergySaleIntentLine energySaleIntentLine,Noc noc) {

		
		noc.setTypeCode("BUYER-NOC");
		noc.setStatusCode("CREATED");
		noc.setBuyerCompanyServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
		noc.setBuyerCompanyServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
		noc.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
		noc.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
		noc.setBuyerCompanyCode(energySaleIntentLine.getBuyerCompanyCode());
		noc.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
		noc.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
		noc.setBuyerOrgCode(energySaleIntentLine.getBuyerEndOrgCode());
		noc.setEnergySaleIntentId(energySaleIntentLine.getEnergySaleIntentId());
		noc.setProposedCapacity(energySaleIntentLine.getProposedQuantum());
		noc.setApprovedCapacity(energySaleIntentLine.getProposedQuantum());
		noc.setIsCaptive(energySaleIntentLine.getIsCaptive());
		
		
		nocId = nocService.addNoc(noc);

		return nocId;
	}
	

	public String addConsent(EnergySaleIntentLine energySaleIntentLine) {
		String consentId = "";
		
		Noc noc = nocService.getNocById(energySaleIntentLine.getNocId())
;
		
		Consent consent = new Consent();
		consent.setFlowTypeCode(noc.getFlowTypeCode());
		consent.settEsIntentId(noc.getEnergySaleIntentId());
		consent.setStatusCode("CREATED");
		consent.setBuyerCompServiceId(noc.getBuyerCompanyServiceId());
		consent.setBuyerCompServiceNumber(noc.getBuyerCompanyServiceNumber());
		consent.setBuyerCompanyId(noc.getBuyerCompanyId());
		consent.setBuyerCompanyName(noc.getBuyerCompanyName());
		consent.setBuyerOrgId(noc.getBuyerOrgId());
		consent.setBuyerOrgName(noc.getBuyerOrgName());
		consent.settNocId(noc.getId());
		consent.setProposedCapacity(noc.getApprovedCapacity());
		consent.setApprovedCapacity(noc.getApprovedCapacity());
		consent.setAgreementPeriodCode(noc.getAgreementPeriodCode());
		consent.setDrawalSubstationId(noc.getBuyerSubstationId());
		consent.setDrawalFeederId(noc.getBuyerFeederId());
		consent.setDrawalVoltageCode(noc.getBuyerVoltageCode());
		consent.setFromDate(noc.getFromDate());
		consent.setToDate(noc.getToDate());
		consent.setSellerCompServiceId(noc.getSellerCompanyServiceId());
		consent.setSellerCompServiceNumber(noc.getSellerCompanyServiceNumber());
		consent.setSellerCompanyId(noc.getSellerCompanyId());
		consent.setSellerCompanyName(noc.getSellerCompanyName());
		consent.setSellerOrgId(noc.getSellerOrgId());
		consent.setSellerOrgName(noc.getSellerOrgName());
		consent.setInjectionSubstationId(noc.getSellerSubstationId());
		consent.setInjectionFeederId(noc.getSellerFeederId());
		consent.setInjectionVoltageCode(noc.getSellerVoltageCode());
		consent.setIsCaptive(noc.getIsCaptive());
		consent.setExemptRc(noc.getExemptRc());
		consent.setHasDues(noc.getHasDues());
		consent.setDueDetails(noc.getDueDetails());
		consent.setPendingCaseDetails(noc.getPendingCaseDetails());
		consent.setTechnicalFeasibilityDetails(noc.getTechnicalFeasibilityDetails());
		
		consentId = consentService.addConsent(consent);

		return consentId;
	}

	public String addOaa(Oaa oaa, EnergySaleIntentLine energySaleIntentLine) {

		oaa.setStatusCode("CREATED");
		oaa.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
		oaa.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
		oaa.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
		oaa.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
		oaa.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
		oaa.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
		oaa.settEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
		oaa.setSellerIsCaptive(energySaleIntentLine.getIsCaptive());
		if(energySaleIntentLine.getEwaLineApprovedCapacity()!=null) {
			oaa.setProposedTotalUnits(energySaleIntentLine.getEwaLineApprovedCapacity());
			oaa.setApprovedTotalUnits(energySaleIntentLine.getEwaLineApprovedCapacity());
		}
		

		ooaId = oaaService.addOaa(oaa);

		return ooaId;
	}
	
	public String addOaaForStb(Oaa oaa, EnergySaleIntentLine energySaleIntentLine) {

		oaa.setStatusCode("CREATED");
		oaa.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
		oaa.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
		oaa.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
		oaa.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
		oaa.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
		oaa.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
		oaa.settEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
		oaa.setSellerIsCaptive(energySaleIntentLine.getIsCaptive());
		if(energySaleIntentLine.getEpaApprovedCapacity()!=null) {
			oaa.setProposedTotalUnits(energySaleIntentLine.getEpaApprovedCapacity());
			oaa.setApprovedTotalUnits(energySaleIntentLine.getEpaApprovedCapacity());
		}
//		if(energySaleIntentLine.getEwaLineApprovedCapacity()!=null) {
//			oaa.setProposedTotalUnits(energySaleIntentLine.getEwaLineApprovedCapacity());
//			oaa.setApprovedTotalUnits(energySaleIntentLine.getEwaLineApprovedCapacity());
//		}
		

		ooaId = oaaService.addOaa(oaa);

		return ooaId;
	}

	public String addEwaForCaptive(EnergySaleIntent energySaleIntent) {
		String ewaId = "";
		System.out.println("In ewa add method");
		Float totalApprovedUnits = 0.0F;
		Ewa ewa = new Ewa();
		EwaLine ewaLine = new EwaLine();
		List<EwaLine> ewaLineList = new ArrayList<EwaLine>();
		
	
			ewa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
			ewa.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
			ewa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
			ewa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
			ewa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
			ewa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
			ewa.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
			ewa.setFromDate(energySaleIntent.getFromDate());
			ewa.setToDate(energySaleIntent.getToDate());
			ewa.setSellerIsCaptive(energySaleIntent.getIsCaptive());
			ewa.setEsIntentCode(energySaleIntent.getCode());
			ewa.setTotalProposedUnits(energySaleIntent.getProposedCapacity());
			ewa.setStatusCode("CREATED");
			ewa.settEsIntentId(energySaleIntent.getId());
			ewa.setSellerInjectionVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());
			ewa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
			if (energySaleIntent.getEnergySaleIntentLines() != null) {
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				
				if(energySaleIntentLine.getConsentStatusCode().equals("COMPLETED")) {
					ewaLine = new EwaLine();
					
					ewa.settEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
					ewa.setStatusCode("CREATED");

					ewaLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
					ewaLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
					ewaLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
					ewaLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
					ewaLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
					ewaLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
					ewaLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
					ewaLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
					ewaLine.setProposedUnits(energySaleIntentLine.getConsentApprovedCapacity());
					ewaLine.setApprovedUnits(energySaleIntentLine.getConsentApprovedCapacity());
					ewaLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
					ewa.setSellerInjectionVoltageCode(energySaleIntentLine.getInjectingVoltageCode());
					ewa.setSellerInjectionVoltageDesc(energySaleIntentLine.getInjectingVoltageName());
					if(energySaleIntentLine.getConsentApprovedCapacity()!=null) {
						//totalApprovedUnits +=Integer.parseInt(energySaleIntentLine.getConsentApprovedCapacity());
						 totalApprovedUnits +=Float.parseFloat(energySaleIntentLine.getConsentApprovedCapacity());
						
					}
					
					ewaLineList.add(ewaLine);
				}
				

			}
		}
			if(totalApprovedUnits!=0 && !(ewa.getFlowTypeCode().equals("THIRD-PARTY"))) {
				ewa.setTotalApprovedUnits(totalApprovedUnits.toString());
				ewa.setTotalProposedUnits(totalApprovedUnits.toString());
			}
		
			
		ewa.setEwaLines(ewaLineList);
		System.out.println("Ewa for test");

		System.out.println(ewa);
		ewaId = ewaService.addewa(ewa);

		return ewaId;
	}
	public String addEwaForWeg(EnergySaleIntent energySaleIntent) {
		String ewaId = "";
		System.out.println("In ewa add method");
		Float totalApprovedUnits = 0.0F;
		Ewa ewa = new Ewa();
		EwaLine ewaLine = new EwaLine();
		List<EwaLine> ewaLineList = new ArrayList<EwaLine>();
		
	
			ewa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
			ewa.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
			ewa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
			ewa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
			ewa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
			ewa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
			ewa.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
			ewa.setFromDate(energySaleIntent.getFromDate());
			ewa.setToDate(energySaleIntent.getToDate());
			ewa.setSellerIsCaptive(energySaleIntent.getIsCaptive());
			ewa.setEsIntentCode(energySaleIntent.getCode());
			ewa.setStatusCode("CREATED");
			ewa.settEsIntentId(energySaleIntent.getId());
			ewa.setSellerInjectionVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());
			ewa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
			if (energySaleIntent.getEnergySaleIntentLines() != null) {
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				ewaLine = new EwaLine();
				
				ewa.settEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
				ewa.setStatusCode("CREATED");

				ewaLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
				ewaLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
				ewaLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
				ewaLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
				ewaLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
				ewaLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
				ewaLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
				ewaLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
				ewaLine.setProposedUnits(energySaleIntentLine.getNocGeneratorLineApprovedCapacity());
				ewaLine.setApprovedUnits(energySaleIntentLine.getNocGeneratorLineApprovedCapacity());
				ewaLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
				ewa.setSellerInjectionVoltageCode(energySaleIntentLine.getInjectingVoltageCode());
				ewa.setSellerInjectionVoltageDesc(energySaleIntentLine.getInjectingVoltageName());
				if(energySaleIntentLine.getConsentApprovedCapacity()!=null) {
					// totalApprovedUnits +=Integer.parseInt(energySaleIntentLine.getConsentApprovedCapacity());
					totalApprovedUnits +=Float.parseFloat(energySaleIntentLine.getConsentApprovedCapacity());
				}
				
				ewaLineList.add(ewaLine);

			}
		}
			if(totalApprovedUnits!=null) {
				ewa.setTotalApprovedUnits(totalApprovedUnits.toString());
				ewa.setTotalProposedUnits(totalApprovedUnits.toString());
			}
		

		ewa.setEwaLines(ewaLineList);
		System.out.println(ewa);
		
		ewaId = ewaService.addewa(ewa);

		return ewaId;
	}
	public String addIpaa(EnergySaleIntent energySaleIntent) {
		String ipaaId="";
		Ipaa ipaa = new Ipaa();
		List<IpaaLine> ipaaLineList = new ArrayList<IpaaLine>();
		ipaa.setSellerCompanyServiceId(energySaleIntent.getSellerCompanyServiceId());
		ipaa.setAgmtPeriodCode(energySaleIntent.getAgmtPeriodCode());
		ipaa.setEsIntentId(energySaleIntent.getId());
		ipaa.setFromDate(energySaleIntent.getFromDate());
		ipaa.setToDate(energySaleIntent.getToDate());
		ipaa.setFromMonth(energySaleIntent.getFromMonth());
		ipaa.setFromYear(energySaleIntent.getFromYear());
		ipaa.setToMonth(energySaleIntent.getToMonth());
		ipaa.setToYear(energySaleIntent.getToYear());
		ipaa.setIsCaptive(energySaleIntent.getIsCaptive());
		ipaa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
		ipaa.setProposedCapacity(energySaleIntent.getProposedCapacity());
		ipaa.setStatusCode("CREATED");
		if (energySaleIntent.getEnergySaleIntentLines() != null) {
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
					IpaaLine ipaaLine = new IpaaLine();
					ipaaLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
					ipaaLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
					ipaaLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
					ipaaLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
					ipaaLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
					ipaaLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
					ipaaLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
					ipaaLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
					ipaaLine.setProposedUnits(energySaleIntentLine.getProposedQuantum());
					ipaaLine.setApprovedUnits(energySaleIntentLine.getProposedQuantum());
					ipaaLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
					System.out.println(ipaaLine);
					ipaaLineList.add(ipaaLine);
				
			}
		}
		ipaa.setIpaaLines(ipaaLineList);
		System.out.println(ipaa);
		ipaaId= ipaaService.addIpaa(ipaa);
		return ipaaId;
	}
	
	public String addNocGenerator(EnergySaleIntent energySaleIntent) {
		String nocGeneratorId="";
		NocGenerator nocGenerator = new NocGenerator();
		List<NocGeneratorLine> nocGeneratorLineList = new ArrayList<NocGeneratorLine>();
		nocGenerator.setSellerCompanyServiceId(energySaleIntent.getSellerCompanyServiceId());		
		nocGenerator.setEsIntentId(energySaleIntent.getId());		
		nocGenerator.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
		nocGenerator.setOrgId(energySaleIntent.getSellerEndOrgId());		
		nocGenerator.setSubstationId(energySaleIntent.getSellerSubstationid());
		nocGenerator.setFeederId(energySaleIntent.getSellerFeederId());
		nocGenerator.setSellerInjectingVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());	
		nocGenerator.setCapacity(energySaleIntent.getProposedCapacity());
		nocGenerator.setStatusCode("CREATED");
		System.out.println(nocGenerator);

		if (energySaleIntent.getEnergySaleIntentLines() != null) {
		for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				NocGeneratorLine nocGeneratorLine = new NocGeneratorLine();
				nocGeneratorLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
				nocGeneratorLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
				nocGeneratorLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
				nocGeneratorLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
				nocGeneratorLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
				nocGeneratorLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
				nocGeneratorLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
				nocGeneratorLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
				nocGeneratorLine.setProposedUnits(energySaleIntentLine.getConsentApprovedCapacity());
				nocGeneratorLine.setApprovedUnits(energySaleIntentLine.getConsentApprovedCapacity());
				nocGeneratorLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
				nocGeneratorLineList.add(nocGeneratorLine);
			
		}
	}
		nocGenerator.setNocGeneratorLines(nocGeneratorLineList);
		nocGeneratorId= nocGeneratorService.addNocGenerator(nocGenerator);
		return nocGeneratorId;
	}
	
	public String addNocGeneratorForWeg(EnergySaleIntent energySaleIntent) {
		String nocGeneratorId="";
		NocGenerator nocGenerator = new NocGenerator();
		List<NocGeneratorLine> nocGeneratorLineList = new ArrayList<NocGeneratorLine>();
		nocGenerator.setSellerCompanyServiceId(energySaleIntent.getSellerCompanyServiceId());		
		nocGenerator.setEsIntentId(energySaleIntent.getId());		
		nocGenerator.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
		nocGenerator.setOrgId(energySaleIntent.getSellerEndOrgId());		
		nocGenerator.setSubstationId(energySaleIntent.getSellerSubstationid());
		nocGenerator.setFeederId(energySaleIntent.getSellerFeederId());
		nocGenerator.setSellerInjectingVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());		
		nocGenerator.setStatusCode("CREATED");
		System.out.println(nocGenerator);

		if (energySaleIntent.getEnergySaleIntentLines() != null) {
		for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				NocGeneratorLine nocGeneratorLine = new NocGeneratorLine();
				nocGeneratorLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
				nocGeneratorLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
				nocGeneratorLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
				nocGeneratorLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
				nocGeneratorLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
				nocGeneratorLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
				nocGeneratorLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
				nocGeneratorLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
				nocGeneratorLine.setProposedUnits(energySaleIntentLine.getIpaaLineApprovedCapacity());
				nocGeneratorLine.setApprovedUnits(energySaleIntentLine.getIpaaLineApprovedCapacity());
				nocGeneratorLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
				nocGeneratorLineList.add(nocGeneratorLine);
			
		}
	}
		nocGenerator.setNocGeneratorLines(nocGeneratorLineList);
		nocGeneratorId= nocGeneratorService.addNocGenerator(nocGenerator);
		return nocGeneratorId;
	}
	
	
	public String addNocGeneratorForStb(EnergySaleIntent energySaleIntent) {
		String nocGeneratorId="";
		NocGenerator nocGenerator = new NocGenerator();
		List<NocGeneratorLine> nocGeneratorLineList = new ArrayList<NocGeneratorLine>();
		nocGenerator.setSellerCompanyServiceId(energySaleIntent.getSellerCompanyServiceId());		
		nocGenerator.setEsIntentId(energySaleIntent.getId());		
		nocGenerator.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
		nocGenerator.setOrgId(energySaleIntent.getSellerEndOrgId());		
		nocGenerator.setSubstationId(energySaleIntent.getSellerSubstationid());
		nocGenerator.setFeederId(energySaleIntent.getSellerFeederId());
		nocGenerator.setSellerInjectingVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());		
		nocGenerator.setStatusCode("CREATED");
		System.out.println(nocGenerator);

		if (energySaleIntent.getEnergySaleIntentLines() != null) {
		for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				NocGeneratorLine nocGeneratorLine = new NocGeneratorLine();
				nocGeneratorLine.setBuyerCompServiceNumber(energySaleIntentLine.getBuyerCompanyServiceNumber());
				nocGeneratorLine.setBuyerCompServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
				nocGeneratorLine.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
				nocGeneratorLine.setBuyerCompanyName(energySaleIntentLine.getBuyerCompanyName());
				nocGeneratorLine.setBuyerOrgId(energySaleIntentLine.getBuyerEndOrgId());
				nocGeneratorLine.setBuyerOrgName(energySaleIntentLine.getBuyerEndOrgName());
				nocGeneratorLine.setDrawalVoltageCode(energySaleIntentLine.getDrawalVoltageCode());
				nocGeneratorLine.setDrawalVoltageDesc(energySaleIntentLine.getDrawalVoltageName());
				nocGeneratorLine.setProposedUnits(energySaleIntentLine.getProposedQuantum());
				nocGeneratorLine.setApprovedUnits(energySaleIntentLine.getProposedQuantum());
				nocGeneratorLine.setIsCaptive(energySaleIntentLine.getIsCaptive());
				nocGeneratorLineList.add(nocGeneratorLine);
			
		}
	}
		nocGenerator.setNocGeneratorLines(nocGeneratorLineList);
		nocGeneratorId= nocGeneratorService.addNocGenerator(nocGenerator);
		return nocGeneratorId;
	}
	
	
	public String addStandingClearence( StandingClearence standingClearence ,EnergySaleIntentLine energySaleIntentLine) {
		String standingClearenceId="";
		

			
				
				standingClearence.setBuyerCompanyServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
				standingClearence.setBuyerCompanyId(energySaleIntentLine.getBuyerCompanyId());
				standingClearence.setEsIntentId(energySaleIntentLine.getEnergySaleIntentId());
				standingClearence.setOrgId(energySaleIntentLine.getBuyerEndOrgId());
			
				standingClearenceId=standingClearenceService.addStandingClearence(standingClearence);
			
			
		
		return standingClearenceId;
	}
	
	 public String addEpa(EnergySaleIntent energySaleIntent) {
			String epaId = "";
			System.out.println("In epa add method");
		
			Epa epa = new Epa();
			EpaLine epaLine = new EpaLine();
			List<EpaLine> epaLineList = new ArrayList<EpaLine>();
			
		
				epa.setSellerCompServiceId(energySaleIntent.getSellerCompanyServiceId());
				epa.setSellerCompServiceNumber(energySaleIntent.getSellerCompanyServiceNumber());
				epa.setSellerCompanyId(energySaleIntent.getSellerCompanyId());
				epa.setSellerCompanyName(energySaleIntent.getSellerCompanyName());
				epa.setSellerOrgId(energySaleIntent.getSellerEndOrgId());
				epa.setSellerOrgName(energySaleIntent.getSellerEndOrgName());
				epa.setAgreementPeriodCode(energySaleIntent.getAgmtPeriodCode());
				epa.setFromDate(energySaleIntent.getFromDate());
				epa.setToDate(energySaleIntent.getToDate());
				epa.setSellerIsCaptive(energySaleIntent.getIsCaptive());
				epa.setEsIntentCode(energySaleIntent.getCode());
				epa.setStatusCode("CREATED");
				epa.setEsIntentId(energySaleIntent.getId());
				epa.setVoltageCode(energySaleIntent.getSellerInjectingVoltageCode());
				epa.setFlowTypeCode(energySaleIntent.getFlowTypeCode());
				epa.setSellerIsCaptive(energySaleIntent.getIsCaptive());
				if (energySaleIntent.getEnergySaleIntentLines() != null) {
				for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
					epaLine = new EpaLine();
					epa.setBuyerCompanyServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
					epa.setProposedTotalUnits(energySaleIntentLine.getNocGeneratorLineApprovedCapacity());
					epa.setApprovedTotalUnits(energySaleIntentLine.getNocGeneratorLineApprovedCapacity());
//					epa.setProposedTotalUnits(energySaleIntentLine.getProposedQuantum());
//					epa.setApprovedTotalUnits(energySaleIntentLine.getProposedQuantum());
					epaLineList.add(epaLine);

				}
			}
			epa.setEpaLine(epaLineList);
			System.out.println(epa);
			
			epaId = epaService.addEpa(epa);

			return epaId;
		}
	 
	 public String addProcessLog(ProcessLog processLog)  {
		 
		 processLogService.addProcessLog(processLog);
		return "success";
		 
	 }
}
