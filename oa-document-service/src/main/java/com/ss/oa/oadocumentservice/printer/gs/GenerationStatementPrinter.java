package com.ss.oa.oadocumentservice.printer.gs;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.ss.oa.oadocumentservice.common.DocumentPrintService;
import com.ss.oa.oadocumentservice.vo.GenerationStatement;
import com.ss.oa.oadocumentservice.vo.GenerationStatementCharge;
import com.ss.oa.oadocumentservice.vo.GenerationStatementPrint;
import com.ss.oa.oadocumentservice.vo.GenerationStatementSlot;
import com.ss.oa.oadocumentservice.vo.TempGenStmt;
import com.ss.oa.oadocumentservice.vo.TempGenStmtSlot;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.doc.DocInfoRepository;
import com.ss.oashared.model.DocInfo;
//import com.ss.oashared.model.MeterChangeMc;
import com.ss.oa.oadocumentservice.vo.MeterChangeMc;
import com.ss.oashared.model.PrintPayload;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Lazy
public class GenerationStatementPrinter {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Value("${file.location}")
	private String fileLocation;

	@Value("${gen-stmt-data.url}")
	private String dataServiceUrl;

	@Value("${tmp-stmt-data.url}")
	private String tmpGsUrl;
	

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	DocInfoRepository docInfoRepository;

	@Autowired
	DocumentPrintService documentPrintService;
	

	@Autowired
	private TemplateEngine htmlTemplateEngine;


	private final String TEMPLATE_NAME = "gen-stmt";
	private final String FILE_EXTENSION = "pdf";
	String commonLogMessage ="GenerationStatment Printer - txnId ";
	int pageSize = 10;int pageNum = 0;
	
	public PrintPayload process(PrintPayload pl, String batch,String txnId,String month,String year) throws OpenAccessException {
			
		String commonLog = "txnId - "+txnId+" ";

		try {

			log.debug(commonLog + "doc-batch -->" +  batch);

			Date date = new Date();
			
			String id = pl.getFilterCriteria().get("id");
			pl.setDocId(id);
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId() + commonUtils.getDateTime(null, "") + "." + pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
//			pl.setDocPath(fileLocation + "/" + TEMPLATE_NAME + "/" + pl.getFileName());
			
			pl.setDocPath(fileLocation + "/" +TEMPLATE_NAME+ "/"+ year+"-"+month+"/"+ pl.getFileName());
//			System.out.println("FolderPath-->"+pl.getDocPath());
//			commonUtils.getOrCreateFolder(pl.getDocPath());

			// 1. Fetch Data for Print
			// 2. Form Context to be passed to template
			// 3. Create the HTML body using Thymeleaf with template
			// 4. generate pdf

			DocInfo doc = new DocInfo();
			doc.setId(commonUtils.generateId().toString());
			doc.setDocName(pl.getName().toString());
			doc.setFilterCriteria(pl.getFilterCriteria().get("id"));
			doc.setDocPath(pl.getDocPath());
			doc.setFileName(pl.getFileName());
			doc.setFileNameToUser(pl.getFileNameToUser());
			doc.setFileExtension(pl.getFileExtension());
			doc.setTableId(pl.getDocId());
			doc.setBatchKey(batch);
			doc.setCreatedBy(this.getClass().getName());
			docInfoRepository.save(doc);

			log.debug(commonLog + " Pdf Start-time -->  " +  LocalDateTime.now());
			commonUtils.generatePdf(pl,this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));
			log.debug(commonLog + " Pdf End-time -->  " +  LocalDateTime.now());

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

	public Context setContext(GenerationStatement generationStatement) {
		final Context ctx = new Context(Locale.ENGLISH);
		try {

			GenerationStatement gs = new GenerationStatement();
//			List<GenerationStatement> gsList = new ArrayList<GenerationStatement>();

			List<GenerationStatementCharge> generationStatementChargeList = new ArrayList<GenerationStatementCharge>();
			List<GenerationStatementSlot> generationStatementSlotList = new ArrayList<GenerationStatementSlot>();

			gs.setrKvahInitial(generationStatement.getrKvahInitial());
			gs.setrKvahFinal(generationStatement.getrKvahFinal());
			gs.setrKvahDifference(generationStatement.getrKvahDifference());
			gs.setrKvahUnits(generationStatement.getrKvahUnits());
			gs.setkVahInitial(generationStatement.getkVahInitial());
			gs.setkVahFinal(generationStatement.getkVahFinal());
			gs.setkVahDifference(generationStatement.getkVahDifference());
			gs.setkVahUnits(generationStatement.getkVahUnits());
			gs.setTotalImportGeneration(generationStatement.getTotalImportGeneration());
			gs.setTotalExportGeneration(generationStatement.getTotalExportGeneration());
			gs.setC1(generationStatement.getC1());
			gs.setC2(generationStatement.getC2());
			gs.setC3(generationStatement.getC3());
			gs.setC4(generationStatement.getC4());
			gs.setC5(generationStatement.getC5());
//			gsList.add(gs);
			GenerationStatementPrint generationStatementPrint = new GenerationStatementPrint();
			for (GenerationStatementSlot generationStatementSlot : generationStatement.getGenerationStatementSlots()) {
				GenerationStatementSlot genSlots = new GenerationStatementSlot();
				genSlots.setId(generationStatementSlot.getId());
				genSlots.setRemarks(generationStatementSlot.getRemarks());
				genSlots.setGenerationStatementId(generationStatementSlot.getGenerationStatementId());
				genSlots.setReferenceNumber(generationStatementSlot.getReferenceNumber());
				genSlots.setSlotCode(generationStatementSlot.getSlotCode());
				genSlots.setSlotName(generationStatementSlot.getSlotName());
				genSlots.setImpInitial(generationStatementSlot.getImpInitial());
				genSlots.setImpFinal(generationStatementSlot.getImpFinal());
				genSlots.setImpDifference(generationStatementSlot.getImpDifference());
				genSlots.setImpUnits(generationStatementSlot.getImpUnits());
				genSlots.setExpInitial(generationStatementSlot.getExpInitial());

				genSlots.setExpFinal(generationStatementSlot.getExpFinal());
				genSlots.setExpDifference(generationStatementSlot.getExpDifference());
				genSlots.setExpUnits(generationStatementSlot.getExpUnits());
				genSlots.setBankedBalance(generationStatementSlot.getBankedBalance());
				genSlots.setNetUnits(generationStatementSlot.getNetUnits());
				generationStatementSlotList.add(genSlots);
				if (generationStatementSlot.getSlotCode().equals("C1")) {
					generationStatementPrint.setBankedBalanceC1(generationStatementSlot.getBankedBalance());
					generationStatementPrint.setNetc1(generationStatementSlot.getNetUnits());
				}
				if (generationStatementSlot.getSlotCode().equals("C2")) {
					generationStatementPrint.setBankedBalanceC2(generationStatementSlot.getBankedBalance());
					generationStatementPrint.setNetc2(generationStatementSlot.getNetUnits());
				}
				if (generationStatementSlot.getSlotCode().equals("C3")) {
					generationStatementPrint.setBankedBalanceC3(generationStatementSlot.getBankedBalance());
					generationStatementPrint.setNetc3(generationStatementSlot.getNetUnits());
				}
				if (generationStatementSlot.getSlotCode().equals("C4")) {
					generationStatementPrint.setBankedBalanceC4(generationStatementSlot.getBankedBalance());
					generationStatementPrint.setNetc4(generationStatementSlot.getNetUnits());
				}
				if (generationStatementSlot.getSlotCode().equals("C5")) {
					generationStatementPrint.setBankedBalanceC5(generationStatementSlot.getBankedBalance());
					generationStatementPrint.setNetc5(generationStatementSlot.getNetUnits());
				}
			}
			for (GenerationStatementCharge generationStatementCharge : generationStatement
					.getGenerationStatementCharges()) {
				GenerationStatementCharge genCharges = new GenerationStatementCharge();
				genCharges.setId(generationStatementCharge.getId());
				genCharges.setRemarks(generationStatementCharge.getRemarks());
				genCharges.setGenerationStatementId(generationStatementCharge.getGenerationStatementId());
				genCharges.setChargeCode(generationStatementCharge.getChargeCode());
				genCharges.setChargeDesc(generationStatementCharge.getChargeDesc());
				genCharges.setChargeTypeCode(generationStatementCharge.getChargeTypeCode());
				genCharges.setChargeTypeName(generationStatementCharge.getChargeTypeName());
				genCharges.setUnitCharge(generationStatementCharge.getUnitCharge());
				genCharges.setTotalCharges(generationStatementCharge.getTotalCharges());
				
				
				
				
				genCharges.setIecRate(generationStatementCharge.getIecRate());
				genCharges.setEtaxRate(generationStatementCharge.getEtaxRate());
				generationStatementChargeList.add(genCharges);

			}
			ctx.setVariable("companyCode", generationStatement.getCompanyCode());
			ctx.setVariable("companyMeterNumber", generationStatement.getCompanyMeterNumber());
			ctx.setVariable("mf", generationStatement.getMf());
			ctx.setVariable("machineCapacity", generationStatement.getMachineCapacity());

			if (generationStatement.getCommissionDate() != null) {
				ctx.setVariable("commissionDate",
						CommonUtils.convertDateFormat(generationStatement.getCommissionDate(), "DD/MM/YYYY"));
			} else {
				ctx.setVariable("commissionDate", "Na");
			}

			ctx.setVariable("initStatementDate",
					CommonUtils.convertDateFormat(generationStatement.getInitStatementDate(), "DD/MM/YYYY"));
			ctx.setVariable("finalStatementDate",
					CommonUtils.convertDateFormat(generationStatement.getFinalStatementDate(), "DD/MM/YYYY"));
			ctx.setVariable("dispOrgName", generationStatement.getDispOrgName());
			// System.out.println(generationStatement.getDispOrgName());
			if(generationStatement.getStmtRemarks()!=null) {
				ctx.setVariable("stmtRemarks", "Note - "+generationStatement.getStmtRemarks());
			}
			else 	ctx.setVariable("stmtRemarks", "");
		
			// System.out.println(generationStatement.getStmtRemarks());
			ctx.setVariable("dispCompanyName", generationStatement.getDispCompanyName());
			ctx.setVariable("injectingVoltageName", generationStatement.getInjectingVoltageName());
			ctx.setVariable("dispFuelTypeGroup", generationStatement.getDispFuelTypeGroup());
			
			ctx.setVariable("statementGenerationDate",
					CommonUtils.convertDateFormat(generationStatement.getStatementGenerationDate(), "DD/MM/YYYY"));
			ctx.setVariable("dispCompanyServiceNumber", generationStatement.getDispCompanyServiceNumber());
			ctx.setVariable("totalImportGeneration", generationStatement.getTotalImportGeneration());
			ctx.setVariable("totalExportGeneration", generationStatement.getTotalExportGeneration());
			ctx.setVariable("netGeneration", generationStatement.getNetGeneration());
			ctx.setVariable("netPayable", generationStatement.getNetPayable());
			ctx.setVariable("totalChargedAmount", generationStatement.getTotalChargedAmount());
			ctx.setVariable("tariffNetAmount", generationStatement.getTariffNetAmount());
			ctx.setVariable("statementMonth",
					CommonUtils.monthConversionString(generationStatement.getStatementMonth()));
			ctx.setVariable("statementYear", generationStatement.getStatementYear());
			ctx.setVariable("typeOfSS", generationStatement.getTypeOfSS());
			ctx.setVariable("ssLossPercent", generationStatement.getSsLossPercent());
			ctx.setVariable("disSsId", generationStatement.getDisSubstationId());
			ctx.setVariable("disSsName", generationStatement.getDisSubstationName());
			

//			String category = "";
//			if (generationStatement.getFlowTypeCode() != null) {
//				if (generationStatement.getFlowTypeCode().equals("CAPTIVE")
//						|| generationStatement.getFlowTypeCode().equals("IS-CAPTIVE")) {
//					category = "WHEELING";
//				} else if (generationStatement.getFlowTypeCode().equals("SELL-TO-BOARD")
//						|| generationStatement.getFlowTypeCode().equals("STB")) {
//					category = "SALE-TO-BOARD";
//				}
//				else if(generationStatement.getFlowTypeCode().equals("THIRD-PARTY")) {
//					category = "THIRD-PARTY";
//				}
//
//			}
			String isRec = "";
			if (generationStatement.getIsRec() != null) {
				if (generationStatement.getIsRec().equals("Y")) {
					isRec = "Rec";
				}
				if (generationStatement.getIsRec().equals("N")) {
					isRec = "Non-Rec";
				}

			} else {
				isRec = "Na";

			}
			// log.debug("category -"+category);

//			ctx.setVariable("category", category);
			ctx.setVariable("category", generationStatement.getFlowTypeCode());
			ctx.setVariable("isRec", isRec);
			ctx.setVariable("plantClassTypeCode", generationStatement.getPlantClassTypeCode());
			ctx.setVariable("plantClassTypeDesc", generationStatement.getPlantClassTypeDesc());
			ctx.setVariable("tariffRate", generationStatement.getTariffRate());
			ctx.setVariable("totalChargedAmount", generationStatement.getTotalChargedAmount());
			ctx.setVariable("netPayable", generationStatement.getNetPayable());
			ctx.setVariable("tariffNetAmount", generationStatement.getTariffNetAmount());
			
		
//			if (generationStatement.getIsMeterChange() == null || generationStatement.getIsMeterChange().isEmpty()) {
//				newMeter = "Y";
//			} else if (generationStatement.getIsMeterChange().equals("Y")) {
//				newMeter = "Y";
//			} else {
//				newMeter = "N";
//			}
			
			ctx.setVariable("c1", nullChecks(generationStatementPrint.getBankedBalanceC1()));
			ctx.setVariable("c2", nullChecks(generationStatementPrint.getBankedBalanceC2()));
			ctx.setVariable("c3", nullChecks(generationStatementPrint.getBankedBalanceC3()));
			ctx.setVariable("c4", nullChecks(generationStatementPrint.getBankedBalanceC4()));
			ctx.setVariable("c5", nullChecks(generationStatementPrint.getBankedBalanceC5()));
			ctx.setVariable("netc1", nullChecks(generationStatementPrint.getNetc1()));
			ctx.setVariable("netc2", nullChecks(generationStatementPrint.getNetc2()));
			ctx.setVariable("netc3", nullChecks(generationStatementPrint.getNetc3()));
			ctx.setVariable("netc4", nullChecks(generationStatementPrint.getNetc4()));
			ctx.setVariable("netc5", nullChecks(generationStatementPrint.getNetc5()));

			ctx.setVariable("generationStatementCharges", generationStatementChargeList);
			System.out.println("fdsfdds----"+generationStatementChargeList);
			
			
			
			
			
			
			
			ctx.setVariable("generationStatementSlots", generationStatementSlotList);
			ctx.setVariable("gs", gs);
//			ctx.setVariable("stmtRemarks", generationStatement.getStmtRemarks());
			List<TempGenStmtSlot> oldSlots = new ArrayList<TempGenStmtSlot>();
			List<TempGenStmtSlot> newSlots = new ArrayList<TempGenStmtSlot>();
			Map<String, String> filterCriteria = new HashMap<String, String>();
			filterCriteria.put("dispServiceNumber", generationStatement.getDispCompanyServiceNumber());
			filterCriteria.put("stmtMonth", generationStatement.getStatementMonth());
			filterCriteria.put("stmtYear", generationStatement.getStatementYear());

			// ONLY FOR METER CHANGE -START
			String isNewMeter = "N";
				List<MeterChangeMc> McGenStmtList = fetchMcGs(filterCriteria);
				
				  if(!McGenStmtList.isEmpty()) {
					  
					  isNewMeter ="Y";
					  
				  }
				   ctx.setVariable("isNewMeter", isNewMeter);
				for (int i=0;i<McGenStmtList.size();i++) {
					
					//System.out.println("cameeer--"+McGenStmtList.get(i).getReadingType().equals("METER CHANGE"));
					MeterChangeMc OldmeterReadings = new MeterChangeMc();
					MeterChangeMc NeWmeterReadings = new MeterChangeMc();
					
					if(McGenStmtList.get(i).getReadingType()!=null) {
					
					if (McGenStmtList.get(i).getReadingType().equals("METER CHANGE")) {
						
						System.out.println("cameeer");
						 OldmeterReadings = McGenStmtList.get(i);
						
						long oldmeterC1net =Math.round(Double.parseDouble(OldmeterReadings.getNetExpC1())- Double.parseDouble(OldmeterReadings.getNetImpC1()));  
						long oldmeterC2net = Math.round(Double.parseDouble(OldmeterReadings.getNetExpC2())- Double.parseDouble(OldmeterReadings.getNetImpC2())); 
						long oldmeterC3net =Math.round(Double.parseDouble(OldmeterReadings.getNetExpC3())- Double.parseDouble(OldmeterReadings.getNetImpC3())); 
						long oldmeterC4net = Math.round(Double.parseDouble(OldmeterReadings.getNetExpC4())- Double.parseDouble(OldmeterReadings.getNetImpC4())); 
						long oldmeterC5net = Math.round(Double.parseDouble(OldmeterReadings.getNetExpC5())- Double.parseDouble(OldmeterReadings.getNetImpC5()));
						
						ctx.setVariable("oldmeterC1net", oldmeterC1net);
						ctx.setVariable("oldmeterC2net", oldmeterC2net);
						ctx.setVariable("oldmeterC3net", oldmeterC3net);
						ctx.setVariable("oldmeterC4net", oldmeterC4net);
						ctx.setVariable("oldmeterC5net", oldmeterC5net);
						
						//ctx.setVariable("oldmeterimpintc1",OldmeterReadings.getImpInitC1());
						ctx.setVariable("OldmeterReadings", OldmeterReadings);
					}
					
                   if (McGenStmtList.get(i).getReadingType().equals("AMR")) {
						
                	   System.out.println("cameeerAMR");
					  NeWmeterReadings = McGenStmtList.get(i);
						
						DecimalFormat format = new DecimalFormat("0.#");
						
						long newmeterC1net = Math.round(Double.parseDouble(NeWmeterReadings.getNetExpC1())- Double.parseDouble(NeWmeterReadings.getNetImpC1()));  
						long newmeterC2net = Math.round(Double.parseDouble(NeWmeterReadings.getNetExpC2())- Double.parseDouble(NeWmeterReadings.getNetImpC2())); 
						long newmeterC3net = Math.round(Double.parseDouble(NeWmeterReadings.getNetExpC3())- Double.parseDouble(NeWmeterReadings.getNetImpC3())); 
						long newmeterC4net = Math.round(Double.parseDouble(NeWmeterReadings.getNetExpC4())- Double.parseDouble(NeWmeterReadings.getNetImpC4())); 
						long newmeterC5net = Math.round(Double.parseDouble(NeWmeterReadings.getNetExpC5())- Double.parseDouble(NeWmeterReadings.getNetImpC5())); 
						
						
						ctx.setVariable("newmeterC1net", format.format(newmeterC1net));
						ctx.setVariable("newmeterC2net", format.format(newmeterC2net));
						ctx.setVariable("newmeterC3net", format.format(newmeterC3net));
						ctx.setVariable("newmeterC4net", format.format(newmeterC4net));
						ctx.setVariable("newmeterC5net", format.format(newmeterC5net));
						
						
						ctx.setVariable("NeWmeterReadings", NeWmeterReadings);
					}
					
					}
				}
				
				
				
				
			// END
			ctx.setVariable("oldSlots", oldSlots);
			ctx.setVariable("newSlots", newSlots);

		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}

		return ctx;
	}

	public GenerationStatement fetchReportData(PrintPayload pl) {
		GenerationStatement gs = null;
		try {
			String url = dataServiceUrl + pl.getFilterCriteria().get("id") + "forview/_internal";
			log.debug("access gs data from oa-service -"+url);
			gs = CommonUtils.getTemplate().getForObject(url, GenerationStatement.class);
			System.out.println("dsadfsadfsa---"+gs);
			// log.debug(gs+"");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return gs;
	}

	public List<MeterChangeMc> fetchMcGs(Map<String, String> filterCriteria) {
		String url = tmpGsUrl + "?" + "ServiceNumber=" + filterCriteria.get("dispServiceNumber") + "&"
				+ "month=" + filterCriteria.get("stmtMonth") + "&" + "year=" + filterCriteria.get("stmtYear");
		log.info(url);
		List<MeterChangeMc> McGenStmt = Arrays.asList(CommonUtils.getTemplate().getForObject(url, MeterChangeMc[].class));
		
		System.out.println("printer---"+McGenStmt);
		return McGenStmt;
	}

	private String nullChecks(String invalue) {
		// null check
		String newValue = "0";
		if (invalue == null || invalue.isEmpty()) {
			newValue = "0";
		} else {
			newValue = invalue;
		}
		return newValue;

	}
	
}
