package com.ss.oa.oadocumentservice.api;

import com.google.common.util.concurrent.RateLimiter;
import com.ss.oa.oadocumentservice.common.DocumentPrintService;
import com.ss.oa.oadocumentservice.common.vo.ApiException;
import com.ss.oa.oadocumentservice.printer.ConsumerNocprinter;
import com.ss.oa.oadocumentservice.printer.EnergyAdjustedOrderReportPrinter;
import com.ss.oa.oadocumentservice.printer.EnergyAllotmentOrderReportPrinter;
import com.ss.oa.oadocumentservice.printer.SldcNocPrinter;
import com.ss.oa.oadocumentservice.printer.gs.GenerationStatementPrinter;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;


//import com.ss.oa.oadocumentservice.printer.EnergyAuditReportPrinter;



@RestController
@RequestMapping("/api/")
public class ApiService { 

	Logger log=org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DocumentPrintService documentPrintService;


	@Autowired
	GenerationStatementPrinter generationStatementPrinter;
	
	@Autowired
	EnergyAllotmentOrderReportPrinter energyAllotmentOrderReportPrinter;
	
	@Autowired
	EnergyAdjustedOrderReportPrinter energyAdjustedOrderReportPrinter;
	
	@Autowired
	SldcNocPrinter sldcNocPrinter;
	
	@Autowired
	ConsumerNocprinter  consumerNocPrinter;
	
//	@Autowired
//	EnergyAuditReportPrinter energyAuditReportPrinter;
		
	@Autowired
	CommonUtils commonUtils;
	
	@Value("${rate-limiter.count}")
	private double limitCount;
	 
	String commonLog ="ApiService";
	 
	RateLimiter rateLimiter;
	
	@PostConstruct
	public void postConstruct() {
		rateLimiter = RateLimiter.create(limitCount);
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/doc-print/_internal")
	public PrintPayload getProcess(@RequestBody PrintPayload printPayload)
			throws OpenAccessException {

		return documentPrintService.process(printPayload);
	}
	

	@CrossOrigin(origins="*")
	@RequestMapping(value = "/print-old/{printType}/{id}", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody>  printDocumentOld(@PathVariable("printType") String printType, @PathVariable("id") String id,
			@RequestParam(value="readingMonth",required=false)String readingMonth,
			@RequestParam(value="readingYear",required=false)String readingYear,
			@RequestParam(value="seller-service-number",required=false)String sellerServiceNumber)
			throws ApiException, OpenAccessException, Exception  {
		long startTime = System.currentTimeMillis();
		PrintPayload payload = new PrintPayload();
		LocalDate date = LocalDate.now();
		switch (printType) {
		case "GenerationStatement":		
			payload.setName(PrintPayload.PrintTypes.GenerationStatement);
			payload.getFilterCriteria().put("id", id);
			payload = generationStatementPrinter.process(payload, "",id, date.getMonthValue()+"", date.getYear()+"");
			break;
//			        

		case "EnergyAllotmentOrderReport":
			payload.setName(PrintPayload.PrintTypes.EnergyAllotmentOrderReport);
			payload.getFilterCriteria().put("id", id);
			payload = energyAllotmentOrderReportPrinter.process(payload);
			break;
			
		case "EnergyAdjustedOrderReport":
			payload.setName(PrintPayload.PrintTypes.EnergyAdjustedOrderReport);
//			payload.getFilterCriteria().put("id", id);
			payload.getFilterCriteria().put("readingMonth", readingMonth);
			payload.getFilterCriteria().put("readingYear", readingYear);
			payload.getFilterCriteria().put("seller-service-number", sellerServiceNumber);
			payload = energyAdjustedOrderReportPrinter.process(payload);
			break;

		default:
			break;
		} 
		log.debug(commonLog+" Document Generation Time - "+(System.currentTimeMillis()-startTime));
		return commonUtils.fetchFileAsStreamResponse(payload);

	}
	
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/print/{printType}/{id}", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody> printDocument(@PathVariable("printType") String printType,@PathVariable("id") String id,
			@RequestParam(value="readingMonth",required=false)String readingMonth,
			@RequestParam(value="readingYear",required=false)String readingYear,
			@RequestParam(value="seller-service-number",required=false)String sellerServiceNumber) throws Exception{
		System.out.println("entering  :"+id+ "--" + LocalDateTime.now());
		boolean test = rateLimiter.tryAcquire();
			if(test) {
					long startTime = System.currentTimeMillis();
					PrintPayload payload = new PrintPayload();
					LocalDate date = LocalDate.now();
					switch (printType) {
					case "GenerationStatement":
					
						payload.setName(PrintPayload.PrintTypes.GenerationStatement);
						payload.getFilterCriteria().put("id", id);
						payload = generationStatementPrinter.process(payload, "",id, date.getMonthValue()+"", date.getYear()+"");
						break;
//						        

					case "EnergyAllotmentOrderReport":
						payload.setName(PrintPayload.PrintTypes.EnergyAllotmentOrderReport);
						payload.getFilterCriteria().put("id", id);
						payload = energyAllotmentOrderReportPrinter.process(payload);
						break;										
						
					case "EnergyAdjustedOrderReport":
						payload.setName(PrintPayload.PrintTypes.EnergyAdjustedOrderReport);
//						payload.getFilterCriteria().put("id", id);
						payload.getFilterCriteria().put("readingMonth", readingMonth);
						payload.getFilterCriteria().put("readingYear", readingYear);
						payload.getFilterCriteria().put("seller-service-number", sellerServiceNumber);
						payload = energyAdjustedOrderReportPrinter.process(payload);
						break;
						
					case "EnergyAuditReport":
						payload.setName(PrintPayload.PrintTypes.EnergyAuditReport);
						payload.getFilterCriteria().put("readingMonth", readingMonth);
						payload.getFilterCriteria().put("readingYear", readingYear);
						payload.getFilterCriteria().put("seller-service-number", sellerServiceNumber);
//						payload = energyAuditReportPrinter.process(payload);
						break;
						
					case "SldcNocPrint":
						payload.setName(PrintPayload.PrintTypes.SldcNocPrint);
						payload.getFilterCriteria().put("id", id);
						payload = sldcNocPrinter.process(payload);
						break;
					case "ConsumerNocPrint":
						payload.setName(PrintPayload.PrintTypes.ConsumerNocPrint);
						payload.getFilterCriteria().put("id", id);
						payload = consumerNocPrinter.processCus(payload);
						break;

					default:
						break;
					} 
					log.debug(commonLog+" Document Generation Time - "+(System.currentTimeMillis()-startTime));
					return commonUtils.fetchFileAsStreamResponse(payload);			
				}
				else {
					throw new Exception("Too many Request please wait");
				}
	}
}
