package com.ss.oa.transaction.energysaleintent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.common.Response;
import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.master.vo.Company;

import com.ss.oa.master.vo.CompanyShareHolder;
import com.ss.oa.transaction.consent.ConsentService;
import com.ss.oa.transaction.epa.EpaService;
import com.ss.oa.transaction.ewa.EwaService;
import com.ss.oa.transaction.noc.NocDao;
import com.ss.oa.transaction.noc.NocService;
import com.ss.oa.transaction.oaa.OaaService;
import com.ss.oa.transaction.processlog.ProcessLogService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.ProcessLog;
import com.ss.oa.master.company.CompanyService;


@Component
public class EnergySaleIntentService {

	@Autowired
	EnergySaleIntentDao dao;

	@Autowired
	NocDao nocDao;

	@Autowired
	CompanyService companyService;

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
	TradeRelationshipService tradeRelationshipService;

	@Autowired
	ProcessEsiForCaptive processEsiForCaptive;

	@Autowired
	ProcessEsiForThirdParty processEsiForThirdParty;

	@Autowired
	ProcessEsiForWeg processEsiForWeg;

	@Autowired
	ProcessEsiForIexConsumer processEsiForIexConsumer;

	@Autowired
	ProcessEsiForIexGenerator processEsiForIexGenerator;

	@Autowired
	ProcessEsiForStb processEsiForStb;

	@Autowired
	ProcessLogService processLogService;

	String energySaleIntentId = "";

	String nocId = "";

	String consentId = "";

	String ooaId = "";

	String ewaId = "";

	String epaId = "";

	Response result = new Response();

	public EnergySaleIntentService() {
		super();
	}

	public List<EnergySaleIntent> getAllEnergySaleIntents(Map<String, String> criteria) {

		return dao.getAllEnergySaleIntents(criteria);
	}

	public EnergySaleIntent getEnergySaleIntentById(String id) {

		return dao.getEnergySaleIntentById(id);
	}

	public EnergySaleIntent setEsIntentLineForShareholders(String id, EnergySaleIntent energySaleIntent) {
		Company Company = companyService.getCompanyById(energySaleIntent.getSellerCompanyId());

		System.out.println(Company);
		List<EnergySaleIntentLine> energySaleIntentLineList = new ArrayList<EnergySaleIntentLine>();

		for (CompanyShareHolder companyShareHolder : Company.getShareHolders()) {

			EnergySaleIntentLine energySaleIntentLine = new EnergySaleIntentLine();
			energySaleIntentLine.setBuyerCompanyServiceId(companyShareHolder.getShareHolderCompanyServiceId());
			energySaleIntentLine.setBuyerCompanyId(companyShareHolder.getShareHolderCompanyId());
			energySaleIntentLine.setBuyerCompanyCode(companyShareHolder.getShareHolderCompanyCode());
			energySaleIntentLine.setBuyerCompanyName(companyShareHolder.getShareHolderCompanyName());
			energySaleIntentLine.setProposedQuantum(companyShareHolder.getQuantum());
			energySaleIntentLine.setSharedPercentage(companyShareHolder.getShare());

			energySaleIntentLineList.add(energySaleIntentLine);

		}
		System.out.println(energySaleIntentLineList);

		energySaleIntent.setEnergySaleIntentLines(energySaleIntentLineList);

		System.out.println("setEsIntentLineForShareholders done");

		return energySaleIntent;
	}

	public Response addEnergySaleIntent(EnergySaleIntent energySaleIntent) {

		result = dao.addEnergySaleIntent(energySaleIntent);
		System.out.println("ESINTENT ADD in B-service Started");

		// energySaleIntentService.setEsIntentLineForShareholders(energySaleIntentId,
		// energySaleIntent);

		if (energySaleIntent.getEnergySaleIntentLines() != null) {

			System.out.println("EsintentLine Add in B-service Started");

			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				energySaleIntentLine.setEnergySaleIntentId(result.getId());
				System.out.println("Esintent LOOP in B-service");
				System.out.println(energySaleIntent);
				System.out.println("EsintentLine LOOP in B-service");
				System.out.println(energySaleIntentLine);
				dao.addEnergySaleIntentLine(energySaleIntentLine);
			}
		}
		System.out.println("ESINTENT ADDED");

		ProcessLog processLog = new ProcessLog();
		processLog.settEnergySaleId(result.getId());
		processLog.setProcessName("ENERGY-SALE-INTENT");
		processLog.setProcessStatus("CREATED");
		processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
		processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
		processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
		processLogService.addProcessLog(processLog);
		System.out.println("PROCESSES LOG ADDED");
		System.out.println(processLog);

		return result;
	}
	// public String addFromShareHolders(CompanyShareHolder companyShareHolder) {
	// EnergySaleIntentLine energySaleIntentLine =new EnergySaleIntentLine();
	// List <EnergySaleIntentLine> energySaleIntentLineList = new
	// ArrayList<EnergySaleIntentLine>();
	//
	// energySaleIntentLine.setBuyerCompanyId(companyShareHolder.getShareHolderCompanyId());
	// energySaleIntentLine.setBuyerCompanyCode(companyShareHolder.getShareHolderCompanyCode());
	// energySaleIntentLine.setBuyerCompanyName(companyShareHolder.getShareHolderCompanyName());
	// energySaleIntentLine.setProposedQuantum(companyShareHolder.getQuantum());
	// energySaleIntentLine.setBuyerCompanyServiceId(companyShareHolder.getShareHolderCompanyServiceId());
	// energySaleIntentLine.setSharedPercentage(companyShareHolder.getShare());
	//
	//
	//
	// energySaleIntentLineList.add(energySaleIntentLine);
	//
	//
	// return energySaleIntentId;
	// }

	public String updateEnergySaleIntent(String id, EnergySaleIntent energySaleIntent) {
	
		
	
		try {
			if(energySaleIntent.getSldcAwaitedDt()!=null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String sldcAwaitedDate = energySaleIntent.getSldcAwaitedDt();
				Date sldcAwaitedDate1 = dateFormat.parse(sldcAwaitedDate);
				energySaleIntent.setSldcAwaitedDt(dateFormat.format(sldcAwaitedDate1));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		energySaleIntentId = dao.updateEnergySaleIntent(id, energySaleIntent);
		if (energySaleIntent.getEnergySaleIntentLines() != null) {
			for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
				if (energySaleIntentLine.getId() != null) {

					dao.updateEnergySaleIntentLine(energySaleIntentLine);
				} else {

					energySaleIntentLine.setEnergySaleIntentId(id);
					dao.addEnergySaleIntentLine(energySaleIntentLine);
				}
			}
		}
		return energySaleIntentId;
	}

	// public String processEnergySaleIntent(EnergySaleIntent energySaleIntent) {
	//
	// if (energySaleIntent.getEnergySaleIntentLines() != null) {
	// for (EnergySaleIntentLine energySaleIntentLine :
	// energySaleIntent.getEnergySaleIntentLines()) {
	// if (energySaleIntentLine.getId() != null) {
	// Noc noc = new Noc();
	// noc.setTypeCode("BUYER-NOC");
	// noc.setStatusCode("CREATED");
	// noc.setBuyerCompanyServiceId(energySaleIntentLine.getBuyerCompanyServiceId());
	// noc.setEnergySaleIntentId(energySaleIntentLine.getEnergySaleIntentId());
	// noc.setProposedCapacity(energySaleIntentLine.getProposedQuantum());
	// nocId = nocDao.addNoc(noc);
	// energySaleIntentLine.setNocId(nocId);
	// dao.updateEnergySaleIntentLine(energySaleIntentLine);
	//
	// }
	// }
	// }
	// return energySaleIntentId;
	// }

	public String processEnergySaleIntent(String energySaleIntentId) {

		EnergySaleIntent energySaleIntent = getEnergySaleIntentById(energySaleIntentId);
		if (energySaleIntent.getStatusCode().equals("COMPLETED")) {
			throw new OpenAccessException(
					"Energy Sale Intent Process Failed - cannot process already completed Energy sale Intent");
		} else {
			energySaleIntent.setFromDate(energySaleIntent.getFromDate().substring(0, 10));
			energySaleIntent.setToDate(energySaleIntent.getToDate().substring(0, 10));
			if (energySaleIntent.getStatusCode().equals("CREATED")) {
				energySaleIntent.setStatusCode("PROCESS");
				dao.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);

			}
			System.out.println(energySaleIntent);

			if (energySaleIntent.getFlowTypeCode() != null) {
				if (energySaleIntent.getFlowTypeCode().equals("CAPTIVE")) {
					energySaleIntentId = processEsiForCaptive.processEnergySaleIntent(energySaleIntent);
				} else if (energySaleIntent.getFlowTypeCode().equals("THIRD-PARTY")) {

					energySaleIntentId = processEsiForThirdParty.processEnergySaleIntentForThirdParty(energySaleIntent);
				} else if (energySaleIntent.getFlowTypeCode().equals("WEG")) {

					energySaleIntentId = processEsiForWeg.processEnergySaleIntentForWeg(energySaleIntent);
				} else if (energySaleIntent.getFlowTypeCode().equals("IEX-CONSUMER")) {

					energySaleIntentId = processEsiForIexConsumer
							.processEnergySaleIntentForIexConsumer(energySaleIntent);
				} else if (energySaleIntent.getFlowTypeCode().equals("STB")) {
					System.out.println("Stb");
					energySaleIntentId = processEsiForStb.processEnergySaleIntentForStb(energySaleIntent);
				} else if (energySaleIntent.getFlowTypeCode().equals("IEX-GENERATOR")) {

					energySaleIntentId = processEsiForIexGenerator
							.processEnergySaleIntentForIexGenerator(energySaleIntent);
				}

			}
		}

		return energySaleIntentId;
	}
	
	public String sldcApprovalEnergySaleIntent(String energySaleIntentId,String status,EnergySaleIntent energySaleIntent) {
		
		energySaleIntent.setId(energySaleIntentId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(energySaleIntent.getStatusCode().equals("AWATING-SLDC-APPROVAL")) {
			System.out.println("status");
			System.out.println(status);
			if(status.equals("SLDC-APPROVED")) {
				System.out.println(energySaleIntent.getStatusCode());
				energySaleIntent.setStatusCode(status);
				System.out.println(energySaleIntent.getStatusCode());
				energySaleIntent.setSldcApproved("Y");			
				energySaleIntent.setSldcApprovalDt(dateFormat.format(date));
			}else if(status.equals("SLDC-REJECTED")) {
				energySaleIntent.setStatusCode(status);
				energySaleIntent.setSldcApproved("N");
				energySaleIntent.setSldcRejectedDt(dateFormat.format(date));
			}
		System.out.println(energySaleIntent);
			dao.updateApprovalEnergySaleIntent(energySaleIntentId, energySaleIntent);
			processEnergySaleIntent(energySaleIntentId);
		}
		
		return energySaleIntentId;
	}
	

}
