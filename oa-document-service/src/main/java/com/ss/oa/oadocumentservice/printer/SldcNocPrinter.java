package com.ss.oa.oadocumentservice.printer;

import com.ss.oa.oadocumentservice.repo.ExtSamastApplnApprovalDocRepo;
import com.ss.oa.oadocumentservice.repo.VCompanyServiceDocrepo;
import com.ss.oa.oadocumentservice.vo.ExtSamastApplnApproval;
import com.ss.oa.oadocumentservice.vo.SldcNoc;
import com.ss.oa.oadocumentservice.vo.SldcNocLine;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.VCompanyService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class SldcNocPrinter {

	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Value("${file.location}")
	private String fileLocation;

	@Value("${sldc-noc-data.url}")
	private String sldcNocUrl;

	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	ExtSamastApplnApprovalDocRepo extRepo;
	
	@Autowired
    VCompanyServiceDocrepo compRepo;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "sldc-noc-print";
	private final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) throws OpenAccessException {

		try {

			String id = pl.getFilterCriteria().get("id");
			pl.setDocId(id);

			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId() + "." + pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation + "/" + TEMPLATE_NAME + "/" + pl.getFileName());

			// 1. Fetch Data for Print
			// 2. Form Context to be passed to template
			// 3. Create the HTML body using Thymeleaf with template
			// 4. generate pdf
			commonUtils.generatePdf(pl,
					this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchSldcNocData(pl))));

		} catch (OpenAccessException oae) {
			log.error(oae.getMessage());
			throw oae;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {

		}
		return pl;
	}

	public Context setContext(SldcNoc sldcNoc) {
		
		final Context ctx = new Context(Locale.ENGLISH);
		
		try {
						
			ctx.setVariable("appliedDt",  CommonUtils.convertDateFormat(sldcNoc.getAppliedDt(), "DD/MM/YYYY"));
			ctx.setVariable("nocCode", sldcNoc.getNocCode());
			
			ctx.setVariable("appliedNo", sldcNoc.getAppliedNo());
			ctx.setVariable("appCategory", sldcNoc.getAppCategory());
			ctx.setVariable("compName", sldcNoc.getCompName());
			ctx.setVariable("compServNo", sldcNoc.getCompServNo());
			ctx.setVariable("plantAdr", sldcNoc.getPlantAdr());
			ctx.setVariable("commsAdr", sldcNoc.getCommsAdr());
			ctx.setVariable("edc", sldcNoc.getEdc());
			//calculation for install cap
			String instCapBy="";
			String installCap=sldcNoc.getInstallCapPlant();
			Double inst=Double.parseDouble(installCap)/1000;
			instCapBy=inst.toString();
			
			ctx.setVariable("installCapPlant", instCapBy);
			ctx.setVariable("intlAuxilary", sldcNoc.getIntlAuxilary());
			ctx.setVariable("intInhouseConsump", sldcNoc.getIntlInhouseConsump());
			// calculation for ex-bus
			Double instCap=Double.parseDouble(instCapBy);
			String intAux=sldcNoc.getIntlAuxilary();
			Double aux=Double.parseDouble(intAux);
			String intInhouse=sldcNoc.getIntlInhouseConsump();
			Double inhoused=Double.parseDouble(intInhouse);
			Double subInthouse=aux+inhoused;
			BigDecimal sub=BigDecimal.valueOf(instCap).subtract(BigDecimal.valueOf(subInthouse));
			String busAvail=sub.toString();
			
			ctx.setVariable("exBusAval", busAvail);
			ctx.setVariable("approvPwrEvacCap", sldcNoc.getApprovPwrEvacCap());
			ctx.setVariable("fuelType", sldcNoc.getFuelType());
			ctx.setVariable("feederWithVoltLevel", sldcNoc.getFeederWithVoltLevel());
			ctx.setVariable("substationWithVoltLevel", sldcNoc.getSubstationWithVoltLevel());
			ctx.setVariable("gridApprovalDt", sldcNoc.getGridApprovalDt());
			ctx.setVariable("codDt",CommonUtils.convertDateFormat(sldcNoc.getCodDt(), "DD/MM/YYYY"));
//			ctx.setVariable("isAbtAndAmr", ((sldcNoc.getIsAbtAndAmr().equals("Y")) ? "Yes" : "No"));
			ctx.setVariable("isAbtAndAmr", sldcNoc.getIsAbtAndAmr());
//			ctx.setVariable("isMeterDownloadAndInstall", ((sldcNoc.getIsMeterDownloadAndInstall().equals("Y")) ? "Yes" : "No"));
			ctx.setVariable("isMeterDownloadAndInstall", sldcNoc.getIsMeterDownloadAndInstall());
			ctx.setVariable("abyMeterName", sldcNoc.getAbyMeterName());
			ctx.setVariable("meterMfName", sldcNoc.getMeterMfName());
			ctx.setVariable("ctRatioAndClassAcc", sldcNoc.getCtRatioAndClassAcc());
			ctx.setVariable("ptRatioAndClassAcc", sldcNoc.getPtRatioAndClassAcc());
			ctx.setVariable("tnebTangedcoThrowPpa", sldcNoc.getTnebTangedcoThrowPpa());
//			ctx.setVariable("isDataMonitoringWithSldc", ((sldcNoc.getIsDataMonitoringWithSldc().equals("Y")) ? "Yes" : "No"));
			ctx.setVariable("isDataMonitoringWithSldc", sldcNoc.getIsDataMonitoringWithSldc());
//			ctx.setVariable("isCommitToTangedco",((sldcNoc.getIsCommitToTangedco().equals("Y")) ? "Yes" : "No"));
			ctx.setVariable("isCommitToTangedco",sldcNoc.getIsCommitToTangedco());
			ctx.setVariable("approvPwrEvacCap", sldcNoc.getApprovPwrEvacCap());
			ctx.setVariable("commRemark", sldcNoc.getCommRemark());
			ctx.setVariable("isCgpStatusVerified", " ");
			ctx.setVariable("eligibleQuantumInWv", " ");
			ctx.setVariable("intraSdlcList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra ")).collect(Collectors.toList()));
			ctx.setVariable("interSdlcList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Inter-State Open Access Commitment")).collect(Collectors.toList()));
			
			ctx.setVariable("tangedcoPpaList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Purchase TANGEDCO")).collect(Collectors.toList()));
			ctx.setVariable("thirdPartyList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Third Party")).collect(Collectors.toList()));
			ctx.setVariable("noThirdPartyLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("noThirdPartyLines")).collect(Collectors.toList()));
			ctx.setVariable("intraStateLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("intraStateLines")).collect(Collectors.toList()));
			ctx.setVariable("noCaptiveLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Captive Use")).collect(Collectors.toList()));
			
			 // For captive use count
			  List<ExtSamastApplnApproval> ExistingCount=extRepo.findByEosGcApprovalNumber(sldcNoc.getCompServNo());
			  List<ExtSamastApplnApproval> ExistingRec=ExistingCount.stream().filter(ext -> ext.getAppCategory().equals("Intra State Bilateral Captive Use")).collect(Collectors.toList());
			  ctx.setVariable("ExistingCount", ExistingRec.size());
			  
			  //for calculate sum of quantum captiveuse
			Double existqtm=ExistingRec.stream().filter(extt -> extt.getQuantum().equals(extt.getQuantum())).mapToDouble(exett -> exett.getQuantum()).sum(); 
			BigDecimal extCount=BigDecimal.valueOf(existqtm).setScale(3, RoundingMode.HALF_UP);
			ctx.setVariable("ExistingQtm", extCount);
			
			  //for proposed count
			List<SldcNocLine> ProposedCount= new ArrayList<SldcNocLine>();
			ProposedCount=sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getIsExisting().equals("N")&& sldcLine.getCommitType().equals("Intra State Bilateral Captive Use")).collect(Collectors.toList());
			ctx.setVariable("ProposedCount", ProposedCount.size());
			
			//for proposed quantum count
			Double ProposedQtm=ProposedCount.stream().filter(sldcLine -> sldcLine.getIsExisting().equals("N")&& sldcLine.getCommitType().equals("Intra State Bilateral Captive Use")).mapToDouble(s -> Double.parseDouble(s.getQuantum())).sum();
		   ctx.setVariable("Propsedqtm", ProposedQtm);
		   
		   //for total count of captive use
			Integer TotalCount=ProposedCount.size()+ExistingRec.size();
			ctx.setVariable("TotalCount", TotalCount);
			
			//for total count of quantum in captive use
		   Double TotalQtm=existqtm+ProposedQtm;
		   BigDecimal totaQuant=BigDecimal.valueOf(TotalQtm).setScale(3, RoundingMode.HALF_UP);
		   ctx.setVariable("TotalQtm", totaQuant);
			
		// For existing third party count
		List<ExtSamastApplnApproval> ThirdPartyCount=extRepo.findByEosGcApprovalNumber(sldcNoc.getCompServNo());
		List<ExtSamastApplnApproval> ThirdPartyRec=ThirdPartyCount.stream().filter(ext -> ext.getAppCategory().equals("Intra State Bilateral Third Party")).collect(Collectors.toList());
		ctx.setVariable("ThirdPartyData", ThirdPartyRec);
		ctx.setVariable("ThirdPartyCount", ThirdPartyRec.size());
		
		 //for calculate sum of quantum thirdparty
		Double existThirdQtm=ThirdPartyRec.stream().filter(extt -> extt.getQuantum().equals(extt.getQuantum())).mapToDouble(exett -> exett.getQuantum()).sum(); 
		BigDecimal thirdCount=BigDecimal.valueOf(existThirdQtm).setScale(3, RoundingMode.HALF_UP);
		ctx.setVariable("ExistingThirdQtm", thirdCount);
		
		//for proposed third party count
		List<SldcNocLine> ThirdProposedCount= new ArrayList<SldcNocLine>();
		ThirdProposedCount=sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getIsExisting().equals("N")&& sldcLine.getCommitType().equals("Intra State Bilateral Third Party")).collect(Collectors.toList());
		ctx.setVariable("ThirdProposedData", ThirdProposedCount);
		ctx.setVariable("ThirdProposedCount", ThirdProposedCount.size());
		
		//for proposed quantum count
		Double ProposedThirdQtm=ThirdProposedCount.stream().filter(sldcLine -> sldcLine.getIsExisting().equals("N")&& sldcLine.getCommitType().equals("Intra State Bilateral Third Party")).mapToDouble(s -> Double.parseDouble(s.getQuantum())).sum();
	   ctx.setVariable("PropsedThirdqtm", ProposedThirdQtm);
	   
		// for total count of third party
		Integer ThirdTotalCount=ThirdProposedCount.size()+ThirdPartyRec.size();
		ctx.setVariable("ThirdTotalCount", ThirdTotalCount);
		
		//for total count of quantum in third party
		 Double TotalThirdQtm=existThirdQtm+ProposedThirdQtm;
		 BigDecimal totalAllThird=BigDecimal.valueOf(TotalThirdQtm).setScale(3, RoundingMode.HALF_UP);
		   ctx.setVariable("TotalThirdQtm", totalAllThird);
		
		// get existing data from vcomserv
		VCompanyService serv=compRepo.findByMCompServNumber(sldcNoc.getCompServNo());
		String companyName="";
		if(serv.getmCompServNumber().equals(sldcNoc.getCompServNo())) {
			companyName=serv.getCompanyName();
		}
		ctx.setVariable("companyName", companyName);
		
		//for existing Intra State Bilateral TANGEDCO
		List<ExtSamastApplnApproval> Existingtangedco=extRepo.findByEosGcApprovalNumber(sldcNoc.getCompServNo());
		List<ExtSamastApplnApproval> ExistingtangedcoRec=Existingtangedco.stream().filter(ext -> ext.getAppCategory().equals("Intra State Bilateral TANGEDCO")).collect(Collectors.toList());
		ctx.setVariable("ExistingtangedcoRec", ExistingtangedcoRec);
		
		//for proposed Intra State Bilateral TANGEDCO
		List<SldcNocLine> PropsedTangedco= new ArrayList<SldcNocLine>();
		PropsedTangedco=sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getIsExisting().equals("N")&& sldcLine.getCommitType().equals("Intra State Bilateral TANGEDCO")).collect(Collectors.toList());
		ctx.setVariable("PropsedTangedco", PropsedTangedco);
		
		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		
		return ctx;
	}

	public SldcNoc fetchSldcNocData(PrintPayload pl) {
		SldcNoc sldc = null;
		try {

			String url = sldcNocUrl + pl.getFilterCriteria().get("id") + "/_internal";
			log.debug("url to getSldcNoc print from oa service-" + url);

			sldc = CommonUtils.getTemplate().getForObject(url, SldcNoc.class);
			
			// log.info(eso+"");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return sldc;
	}

}