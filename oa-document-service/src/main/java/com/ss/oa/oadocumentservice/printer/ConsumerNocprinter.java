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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class ConsumerNocprinter {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Value("${file.location}")
	private String fileLocation;

	@Value("${consumer-noc-data.url}")
	private String consumerNocUrl;
	
	@Autowired
	VCompanyServiceDocrepo compRepo;
	
	@Autowired
	ExtSamastApplnApprovalDocRepo extRepo;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "consumernoc";
	private final String FILE_EXTENSION = "pdf";

	public PrintPayload processCus(PrintPayload pl) throws OpenAccessException {

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
					this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchCusNocData(pl))));

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
		//List<VCompanyService> serv=new ArrayList<VCompanyService>();
		
		try {
			
			ctx.setVariable("appliedDt",  CommonUtils.convertDateFormat(sldcNoc.getAppliedDt(), "DD/MM/YYYY"));
			ctx.setVariable("nocCode", sldcNoc.getNocCode());
			ctx.setVariable("legalRemark", sldcNoc.getLegalRemark());
			ctx.setVariable("techRemark", sldcNoc.getTechRemark());
			ctx.setVariable("appliedNo",sldcNoc.getAppliedNo());
			ctx.setVariable("appCategory",sldcNoc.getAppCategory());
			ctx.setVariable("region", sldcNoc.getRegion());
			ctx.setVariable("edc", sldcNoc.getEdc());
			ctx.setVariable("section", sldcNoc.getSection());
			ctx.setVariable("ccArrearsAval", sldcNoc.getCcArrearsAval());
//			ctx.setVariable("demandMV","kva/1000");
			ctx.setVariable("compName", sldcNoc.getCompName());
			ctx.setVariable("compServNo", sldcNoc.getCompServNo());
			ctx.setVariable("plantAdr", sldcNoc.getPlantAdr());
			ctx.setVariable("commsAdr", sldcNoc.getCommsAdr());
			ctx.setVariable("installCapPlant", sldcNoc.getInstallCapPlant());
			ctx.setVariable("intlAuxilary", sldcNoc.getIntlAuxilary());
			ctx.setVariable("intInhouseConsump", sldcNoc.getIntlInhouseConsump());
			ctx.setVariable("exBusAval", sldcNoc.getExBusAval());
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
			ctx.setVariable("tarrif",sldcNoc.getTarrif());
			ctx.setVariable("isHtConPermitForOa", sldcNoc.getIsHtConPermitForOa());
			ctx.setVariable("isCgpStatusVerified", " ");
			ctx.setVariable("eligibleQuantumInWv", " ");
			//for commit type
			ctx.setVariable("intraSdlcList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra ")).collect(Collectors.toList()));
			ctx.setVariable("interSdlcList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Inter-State Open Access Commitment")).collect(Collectors.toList()));
			
			// for all status 
//			ctx.setVariable("allList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals(sldcLine.getCommitType())).collect(Collectors.toList()));

			ctx.setVariable("tangedcoPpaList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Purchase TANGEDCO")).collect(Collectors.toList()));
			ctx.setVariable("thirdList", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Third Party")).collect(Collectors.toList()));
			ctx.setVariable("noThirdPartyLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("noThirdPartyLines")).collect(Collectors.toList()));
			ctx.setVariable("intraStateLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("intraStateLines")).collect(Collectors.toList()));
			ctx.setVariable("noCaptiveLines", sldcNoc.getSldcNocLine().stream().filter(sldcLine -> sldcLine.getCommitType().equals("Intra State Bilateral Captive Use")).collect(Collectors.toList()));
			
			//for propsed
			List<SldcNocLine> sldc= new ArrayList<SldcNocLine>();
			sldc=sldcNoc.getSldcNocLine();
			ctx.setVariable("sldcLine", sldcNoc.getSldcNocLine());
			String commitType1="";
			String isExisting="";
			String flowType="";
			String fuelGroup="";
			String quantum="";
			String fromPeriod="";
			String toPeriod="";
			String compName="";
			String isExistingOACommitment = "N";
			
			for(SldcNocLine sldcNocLine:sldcNoc.getSldcNocLine()) {
				if(sldcNocLine.getQuantum().equals(sldcNoc.getQuantity()) && sldcNocLine.getIsExisting().equals("N")) {
					isExistingOACommitment = sldcNocLine.getIsExisting();
					flowType=sldcNocLine.getFlowType();
					fuelGroup=sldcNocLine.getFuelGroup();
					quantum=sldcNocLine.getQuantum();
					commitType1=sldcNocLine.getCommitType();
					fromPeriod=CommonUtils.convertDateFormat(sldcNocLine.getFromPeriod(), "DD/MM/YYYY");
					toPeriod=CommonUtils.convertDateFormat(sldcNocLine.getToPeriod(), "DD/MM/YYYY");
					compName=sldcNocLine.getCompName();
				}
			}
			ctx.setVariable("commitType", commitType1);
			ctx.setVariable("isExistingOACommitment", isExistingOACommitment);
		//	ctx.setVariable("isExisting", isExisting);
			ctx.setVariable("flowtype", flowType);
			ctx.setVariable("flowgroup", fuelGroup);
			ctx.setVariable("quantum", quantum);
			ctx.setVariable("fromPeriod", fromPeriod);
			ctx.setVariable("toPeriod", toPeriod);
			ctx.setVariable("compName", compName);
			
			//for companyname and capacity
			VCompanyService serv=compRepo.findByMCompServNumber(sldcNoc.getCompServNo());
			String capacity="";
			String companyName="";
			String capacityDiv="";
			if(serv.getmCompServNumber().equals(sldcNoc.getCompServNo())) {
				capacity=serv.getCapacity();
				companyName=serv.getCompanyName();
				Double capacity1=Double.parseDouble(capacity)/1000*0.9;
				BigDecimal cap=BigDecimal.valueOf(capacity1).setScale(3, RoundingMode.HALF_UP);
				capacityDiv=cap.toString();
			}
			String capRemove=capacity.split("\\.", 2)[0];
			ctx.setVariable("capacity", capRemove);
			ctx.setVariable("capacityBy", capacityDiv);
		    ctx.setVariable("companyName", companyName);
		    
		    //for existing
		    String company="";
		    LocalDate existfromPeriod = null;
		    LocalDate existtoPeriod=null;
		    Float existquantum=null;
		    String existcommitType1="";
		    String existisExisting="";
		    Double existqtm=null;
		    List<ExtSamastApplnApproval> existRecord=extRepo.findByEobHtConsumerNumber(sldcNoc.getCompServNo());
		    ctx.setVariable("exts", existRecord);
		    for(ExtSamastApplnApproval ext:existRecord) {
			 company=ext.getCustomerName();
			 existfromPeriod=ext.getPeriodStartDate();
			 existtoPeriod=ext.getPeriodEndDate();
			 existquantum=ext.getQuantum();
			 existcommitType1=ext.getAppCategory();
			 
		}
		    existqtm=existRecord.stream().filter(extt -> extt.getQuantum().equals(extt.getQuantum())).mapToDouble(exett -> exett.getQuantum()).sum(); 
		  Float extqtn=existqtm.floatValue();
		ctx.setVariable("existcompany", company);
		ctx.setVariable("existstartdt", existfromPeriod);
		ctx.setVariable("existenddt", existtoPeriod);
		ctx.setVariable("existquantum", extqtn);
		ctx.setVariable("existcommit", existcommitType1);
		       
		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		 JSONObject jsonObject = new JSONObject(ctx);
		System.out.println(jsonObject);
		return ctx;
	}

	public SldcNoc fetchCusNocData(PrintPayload pl) {
		SldcNoc sldc = null;
		try {

			String url = consumerNocUrl + pl.getFilterCriteria().get("id") + "/_internal";
			log.debug("url to getCusNoc print from oa service-" + url);

			sldc = CommonUtils.getTemplate().getForObject(url, SldcNoc.class);
			
			// log.info(eso+"");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return sldc;
	}
	
}