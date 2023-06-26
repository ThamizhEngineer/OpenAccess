package com.ss.oa.oadocumentservice.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import com.ss.oa.oadocumentservice.printer.TranscoInvoiceOandMChargesPrinter;
import com.ss.oa.oadocumentservice.printer.AmrDownlodeStatusReport;
import com.ss.oa.oadocumentservice.printer.CeeReportPrinter;
import com.ss.oa.oadocumentservice.printer.ConsEnergyAdjustedChargesReportPrint;
import com.ss.oa.oadocumentservice.printer.ConsolidateEnergyAdjustmentReportPrint;
import com.ss.oa.oadocumentservice.printer.ConsumerWiseEnergyAdjustmentOrderReport;
import com.ss.oa.oadocumentservice.printer.EnergyAdjustedOrderChargeReportPrinter;
import com.ss.oa.oadocumentservice.printer.EnergyAdjustedOrderReportPrinter;
// import com.ss.oa.oadocumentservice.printer.EnergyAllocatedReportPrinted;
import com.ss.oa.oadocumentservice.printer.EnergyAllotmentOrderReportPrinter;
import com.ss.oa.oadocumentservice.printer.EnergyAllotmentReportPrinter;
import com.ss.oa.oadocumentservice.printer.FinancialUnutilBankingPrint;
import com.ss.oa.oadocumentservice.printer.GenChargesAllocToHtReport;
import com.ss.oa.oadocumentservice.printer.GenerationstmtReportPrint;
import com.ss.oa.oadocumentservice.printer.GeneratorWiseGenerationReport;
import com.ss.oa.oadocumentservice.printer.MasterSolarReportPrinter;
import com.ss.oa.oadocumentservice.printer.MasterWindReportPrinter;
import com.ss.oa.oadocumentservice.printer.MeterReadingServicesImportStatusReport;
import com.ss.oa.oadocumentservice.printer.MonthlyProgressReportForTotal;
import com.ss.oa.oadocumentservice.printer.NilGenerationReportPrinter;
import com.ss.oa.oadocumentservice.printer.OrgWiseGenerationSummaryPrinter;
import com.ss.oa.oadocumentservice.printer.PowerplantGeneratorPrinter;
import com.ss.oa.oadocumentservice.printer.ProgressReportNew;
import com.ss.oa.oadocumentservice.printer.ProgressReportPrint;
import com.ss.oa.oadocumentservice.printer.SaleToBoardLedgerReport;
import com.ss.oa.oadocumentservice.printer.SolarFeederEdcWiseReport;
import com.ss.oa.oadocumentservice.printer.SolarFeederLineLossPrinter;
import com.ss.oa.oadocumentservice.printer.SrcpReportPrinter;
import com.ss.oa.oadocumentservice.printer.TechnicalInfoReport;
import com.ss.oa.oadocumentservice.printer.TnercBankingReport092022;
import com.ss.oa.oadocumentservice.printer.TnercEnergySummaryReportPrint;
import com.ss.oa.oadocumentservice.printer.TnercNetGenerationReportPrint;
import com.ss.oa.oadocumentservice.printer.TranscoInvoicePrinter;
import com.ss.oa.oadocumentservice.printer.TranscoRecipt;
import com.ss.oa.oadocumentservice.printer.UnalloctedGenStmtReportPrinter;
import com.ss.oa.oadocumentservice.printer.UnimportedWegReportPrinter;
import com.ss.oa.oadocumentservice.printer.UnutilisedBankingMar2020;
import com.ss.oa.oadocumentservice.printer.UnutilisedBankingMarNew;
import com.ss.oa.oadocumentservice.printer.UnutilisedBankingReport;
import com.ss.oa.oadocumentservice.printer.WegWithBuyerReportPrinter;
import com.ss.oa.oadocumentservice.printer.gs.GenerationStatementAsyncProcessor;
import com.ss.oa.oadocumentservice.printer.gs.GenerationStatementPrinter;
import com.ss.oa.oadocumentservice.vo.GenerationStatement;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;

import com.ss.oashared.doc.DocInfoRepository;
import com.ss.oashared.model.DocInfo;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.TranscoInvoiceRecipt;


@RestController
@RequestMapping(value = "/doc-print")
public class DocumentPrintService { 
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EnergyAllotmentOrderReportPrinter energyAllotmentOrderReportPrinter;
	
	@Autowired
	MasterSolarReportPrinter masterSolarPrinter;
	
	@Autowired 
	MasterWindReportPrinter masterWindPrinter;
	
	@Autowired
	EnergyAllotmentReportPrinter energyAllotmentReportPrinter;
	
	@Autowired
	MeterReadingServicesImportStatusReport meterReadingImportStatusPrinter;
	
	@Autowired
	MonthlyProgressReportForTotal monthlyProgressReportForTotal;

	@Autowired
	WegWithBuyerReportPrinter wegWithBuyerReportPrinter;
	
	@Autowired
	GenerationStatementPrinter generationStatementPrinter;
	
	@Autowired
	UnimportedWegReportPrinter unimportedWegReportPrinter;
	
	@Autowired
	PowerplantGeneratorPrinter powerplantGeneratorPrinter;
	
	@Autowired
	EnergyAdjustedOrderReportPrinter energyAdjustedOrderReportPrinter;
	
	@Autowired
	NilGenerationReportPrinter nilGenerationReportPrinter ;
	
	@Autowired
	TnercNetGenerationReportPrint tnercNetGenerationReportPrint ;
	
	@Autowired
	TnercEnergySummaryReportPrint tnercEnergySummaryReportPrint;
	
	@Autowired
	ConsolidateEnergyAdjustmentReportPrint consolidateEnergyAdjustmentReportPrint;
	
	@Autowired
	ConsEnergyAdjustedChargesReportPrint consEnergyAdjustedChargesReportPrint;
	
	@Autowired
	UnalloctedGenStmtReportPrinter unalloctedGenStmtReportPrinter ;
	
	@Autowired
	CeeReportPrinter ceeReportPrinter;
	
	@Autowired
	SrcpReportPrinter srcpReportPrinter;
	
	@Autowired
	ProgressReportPrint progressReportPrint;
	
	@Autowired
	TranscoInvoiceOandMChargesPrinter transcoInvoiceChargesPrinter;

	@Autowired
	FinancialUnutilBankingPrint financialUnutilBankingPrint;
	
	@Autowired
	EnergyAdjustedOrderChargeReportPrinter energyAdjustedOrderChargeReportPrinter;
	
	@Autowired
	OrgWiseGenerationSummaryPrinter orgWiseGenerationSummaryPrinter;
	
	@Autowired
	GeneratorWiseGenerationReport generatorWiseGenerationReport;
	
	@Autowired
	ConsumerWiseEnergyAdjustmentOrderReport consumerWiseEnergyAdjustmentOrderReport;
	
	@Autowired
	UnutilisedBankingReport unutilisedBankingReport;

	@Autowired
	SaleToBoardLedgerReport saleToBoardLedgerReport;
	
	@Autowired
	GenChargesAllocToHtReport genChargesAllocToHtReport;
	
	@Autowired
	UnutilisedBankingMarNew unutilisedBankingMarNew;
	
	@Autowired
	UnutilisedBankingMar2020 unutilisedBankingMar2020;
	
	@Autowired
	ProgressReportNew progressReportNew;
	
	
	@Autowired
	AmrDownlodeStatusReport amrdownlodestatusreport;
	
	@Autowired
	TechnicalInfoReport technicalInfoReport;
	
	@Autowired
	TranscoInvoicePrinter transcoInvoicePrinter;
	
	@Autowired
	TranscoRecipt transcoRecipt;
	
	@Autowired
	SolarFeederEdcWiseReport solarfeederEdc;
	
	@Autowired
	TnercBankingReport092022 tnercBankingReport092022;
	
	@Autowired
	GenerationstmtReportPrint generationstmtPrint;
	
	@Autowired
	SolarFeederLineLossPrinter solarFeederLineLossPrinter;
	
	
	private GenerationStatementAsyncProcessor generationStatementAsyncProcessor;
	 
    @Autowired
    public DocumentPrintService(@Lazy GenerationStatementAsyncProcessor generationStatementAsyncProcessor) {
        this.generationStatementAsyncProcessor = generationStatementAsyncProcessor;
    }
	// @Autowired
	// EnergyAllocatedReportPrinted EnergyAllotmentReport;
//	@Autowired
//	PlantWiseGenerationReportForStbPrinter plantWiseGenerationReportForStbPrinter;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	DocInfoRepository docInfoRepository;
	
	@Value("${docs.folder.delimitter}")
	private String folderDelimitter;
	
//	String printUrl="http://localhost:4216/oa-document-service//doc-print/_internal";
	@Value("${print.url}")
	private String printUrl;
	
	@PostMapping	
	public PrintPayload process(@RequestBody PrintPayload printPayload)
			throws OpenAccessException {
		
		System.out.println("process-Name-->"+printPayload);
		if(printPayload.getName().equals(PrintPayload.PrintTypes.EnergyAllotmentReport)) {
			printPayload = energyAllotmentReportPrinter.process(printPayload);
		}
		else if(printPayload.getName().equals(PrintPayload.PrintTypes.EnergyAllotmentOrderReport)) {
			printPayload = energyAllotmentOrderReportPrinter.process(printPayload);
		}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.WegWithBuyerReport)) {
			printPayload = wegWithBuyerReportPrinter.process(printPayload);			
		}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.GenerationStatement)) {
			printPayload = generationStatementPrinter.process(printPayload,null,null,null,null);			
		}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.UnimportedWegReport)) {
			printPayload = unimportedWegReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.PowerplantGenerator)) {
			printPayload = powerplantGeneratorPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.EnergyAdjustedOrderReport)) {
			printPayload = energyAdjustedOrderReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.NilGenerationReport)) {
			printPayload = nilGenerationReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.UnalloctedGenStmtReport)) {
			printPayload = unalloctedGenStmtReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.CeeReport)) {
			printPayload = ceeReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.SrcpReport)) {
			printPayload = srcpReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.EnergyAdjustedOrderChargeReport)) {
			printPayload = energyAdjustedOrderChargeReportPrinter.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.ProgressReport)) {
			printPayload = progressReportPrint.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.FinancialUnutilBankReport)) {
			printPayload = financialUnutilBankingPrint.process(printPayload);}
		else if (printPayload.getName().equals(PrintPayload.PrintTypes.orgWiseGenerationReport)) {
			printPayload = orgWiseGenerationSummaryPrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.TnercNetGenerationReport)) {
		 	printPayload = tnercNetGenerationReportPrint.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.TnercEnergySummaryReport)) {
			 	printPayload = tnercEnergySummaryReportPrint.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.ConsolidateEnergyAdjustmentReport)) {
			 	printPayload = consolidateEnergyAdjustmentReportPrint.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.ConsolidateEnergyAdjustedChargesReport)) {
			 	printPayload = consEnergyAdjustedChargesReportPrint.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.GeneratorWiseGenerationReport)) {
			 	printPayload = generatorWiseGenerationReport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.ConsumerWiseEnergyAdjustmentOrderReport)) {
			 	printPayload = consumerWiseEnergyAdjustmentOrderReport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.UnutilisedBankingReport)) {
			 	printPayload = unutilisedBankingReport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.SaleToBoardLedgerReport)) {
			 	printPayload = saleToBoardLedgerReport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.GenChargesAllocToHtReport)) {
			 	printPayload = genChargesAllocToHtReport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.UnutilisedBankingMarNew)) {
			 	printPayload = unutilisedBankingMarNew.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.UnutilisedBankingMar2020)) {
			 	printPayload = unutilisedBankingMar2020.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.ProgressReportNew)) {
			 	printPayload = progressReportNew.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.AmrDownloadReport)) {
			 	printPayload = amrdownlodestatusreport.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.TechnicalInfoReport)) {
			 	printPayload = technicalInfoReport.process(printPayload);}
	    else if (printPayload.getName().equals(PrintPayload.PrintTypes.TranscoInvoiceReport)) {
				 	printPayload = transcoInvoicePrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.TranscoInvoiceChargesReport)) {
					printPayload = transcoInvoiceChargesPrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.TnercBankingReport092022)) {
				printPayload = tnercBankingReport092022.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.SolarFeederEDCWiseReport)) {
				printPayload = solarfeederEdc.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.MeterReadingServicesImportStatusReport)) {
             printPayload = meterReadingImportStatusPrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.MonthyProgressReportForTotal)) {
             printPayload = monthlyProgressReportForTotal.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.MasterSolarReport)) {
             printPayload = masterSolarPrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.MasterWindReport)) {
             printPayload = masterWindPrinter.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.GenerationstmtReport)) {
             printPayload = generationstmtPrint.process(printPayload);}
		 else if (printPayload.getName().equals(PrintPayload.PrintTypes.SolarFeederLineLoss)) {
             printPayload = solarFeederLineLossPrinter.process(printPayload);}
		
		return printPayload;
	}
	@CrossOrigin(origins="*")
	@PostMapping("/_internal")
	public PrintPayload processInternal(@RequestBody PrintPayload printPayload)throws OpenAccessException{
		log.info("_internal rest in document sevice");
		log.info(printPayload.toString());
		System.out.println("PrintPayload-->"+printPayload);
		return process(printPayload);
		
	}
	
	
	@CrossOrigin(origins="*")
	@PostMapping("/guidelines/print")
	public ResponseEntity<StreamingResponseBody> downloadGuidelines(@RequestBody PrintPayload printPayload)throws OpenAccessException{
	
		printPayload.setDocPath("/opt/oa/data/guidelines/oa-generator-guidelines.pdf");
		return commonUtils.fetchFileAsStreamResponse(printPayload);
		
		
	}
	
	@RequestMapping(value = "/genstmt/{id}/print", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody> printStmtDoc(@PathVariable("id") String id) throws FileNotFoundException{
		return commonUtils.fetchFileAsStreamResponse(printStmt(id));
	}
	
	
	public PrintPayload printStmt(String genStmtId) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.GenerationStatement);
		payload.getFilterCriteria().put("id", genStmtId);
		String url=printUrl;
		System.out.println("printStmt-url-->"+url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class);
		System.out.println("ForDocPrint"+payload);
		return payload;
	}
	
//	-------------------Create Pdf for GS
	
	@RequestMapping(value = "/genPdf", method = RequestMethod.GET)
	public String getData(@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,Model model){

		String txnId = commonUtils.generateId();
		try {
			Map<String, String> criteria = new HashMap<String,String>();
			if(month!=null && year!=null) {
				criteria.put("month", month);
				criteria.put("year", year);
			}
			System.out.println("month "+criteria.get("month")+"year "+criteria.get("year"));
			generationStatementAsyncProcessor.triggerGSPdfGeneration(criteria, txnId,model);
			
//			System.out.println("final-->"+data);
		}catch(Exception e) {
			e.printStackTrace();
			return txnId;
		}
		return txnId;
		
	}
	
//	------------------ Regenerate Pdf for GS
	
	@RequestMapping(value = "/regen-pdf", method =RequestMethod.POST)
	public String reProcessPdf(@RequestBody GenerationStatement gs) throws OpenAccessException, IOException {
		String txnId = commonUtils.generateId();
		return generationStatementAsyncProcessor.regeneratePdf(gs.getId(),txnId);
	}
	
//	---------------------- Get Pdf from doc_info
	
	@RequestMapping(value = "/getpdf/{id}", method = RequestMethod.GET)
	 public ResponseEntity<StreamingResponseBody> getDocByDocId(@PathVariable("id") String id)
	            throws FileNotFoundException {

				log.debug("id-->"+id);

	        try {
	            Optional<DocInfo> docInfo = docInfoRepository.findById(id);
	            log.debug("DocFile-->"+docInfo);
	            
	            HttpHeaders respHeaders = new HttpHeaders();
	            respHeaders.setContentType(MediaType.parseMediaType("application/"+docInfo.get().getFileExtension()));
	            respHeaders.add("Content-Disposition", "attachment; filename="+ docInfo.get().getFileNameToUser());
	            respHeaders.add("X-filename", docInfo.get().getFileNameToUser());
	            return ResponseEntity.ok()
	            				.headers(respHeaders)
	            				.body(commonUtils.prepareStreamResponse(docInfo.get().getDocPath()));
	        } catch (Exception e) {

	            e.printStackTrace();
	            throw new OpenAccessException(e.getMessage());
	        }

	    }
	
//	--------------Delete Pdf from doc_info
	
	@RequestMapping(value = "/clean-pdf",method = RequestMethod.GET)
	public String cleanRecords() {
		String txnId = commonUtils.generateId();
		return generationStatementAsyncProcessor.deleteFile(txnId);	
	
	
	}
	
	@CrossOrigin(origins="*")
    @PostMapping("/invrecpit-print/_internal")
    public TranscoInvoiceRecipt EmailReciptInternal(@RequestBody TranscoInvoiceRecipt transcoInvoiceRecipt)throws OpenAccessException{
	log.info("_internal rest in document sevice");
	log.info(transcoInvoiceRecipt.toString());
    System.out.println("PrintPayload-->"+transcoInvoiceRecipt);
	return transcoRecipt.process(transcoInvoiceRecipt);
	

}
	
}
