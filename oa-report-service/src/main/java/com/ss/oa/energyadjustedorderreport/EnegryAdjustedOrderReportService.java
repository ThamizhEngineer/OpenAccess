package com.ss.oa.energyadjustedorderreport;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.report.vo.EnergyAdjustedOrder;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@RestController
@RequestMapping("report/energy-adjusted-order")
public class EnegryAdjustedOrderReportService {
	@Value("${print.url}")
	private String printUrl;
	
	@Autowired
	EnergyAdjustedOrderReportRepository energyAdjustedOrderReportRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<EnergyAdjustedOrder> getEnergyAdjustedOrder(@RequestParam(value="readingMonth",required=false)String readingMonth,
			@RequestParam(value="readingYear",required=false)String readingYear,
			@RequestParam(value="seller-service-number",required=false)String sellerServiceNumber)throws OpenAccessException{
		
		boolean searchBymonth=false,searchByYear=false,searchBysellerServiceNumber=false,searchBymonthYearService=false;
		
		if(readingMonth!=null&&!readingMonth.isEmpty()) {
			searchBymonth=true;
		}

		if(readingYear!=null&&!readingYear.isEmpty()) {
			searchByYear=true;
		}

		if(sellerServiceNumber!=null&&!sellerServiceNumber.isEmpty()) {
			searchBysellerServiceNumber=true;
		}
		
		if(readingMonth!=null&&!readingMonth.isEmpty()&&readingYear!=null&&!readingYear.isEmpty()&&sellerServiceNumber!=null&&!sellerServiceNumber.isEmpty()) {
			searchBymonthYearService=true;
		}
		
		if(searchBymonthYearService) {
			return energyAdjustedOrderReportRepository.findByReadingMonthAndReadingYearAndSellerServiceNumber(readingMonth, readingYear, sellerServiceNumber);
		}
		if(searchBymonth) {
			return energyAdjustedOrderReportRepository.findByReadingMonth(readingMonth);
		}
		if(searchByYear) {
			return energyAdjustedOrderReportRepository.findByReadingYear(readingYear);
		}
		if(searchBysellerServiceNumber) {
			return energyAdjustedOrderReportRepository.findBySellerServiceNumber(sellerServiceNumber);
		}

		return energyAdjustedOrderReportRepository.findAll();
		
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/_internal")
	public Iterable<EnergyAdjustedOrder> getEnergyInternal(@RequestParam(value="readingMonth",required=false)String readingMonth,
			@RequestParam(value="readingYear",required=false)String readingYear,
			@RequestParam(value="seller-service-number",required=false)String sellerServiceNumber)throws OpenAccessException{
		return getEnergyAdjustedOrder(readingMonth, readingYear, sellerServiceNumber);
	}
	
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printEnergyAdjOrder(@RequestParam(value="readingMonth",required=false)String readingMonth,
			@RequestParam(value="readingYear",required=false)String readingYear,
			@RequestParam(value="seller-service-number",required=false)String sellerServiceNumber)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall(readingMonth, readingYear, sellerServiceNumber));

	}
	
	public PrintPayload  printCall(String readingMonth,String readingYear,String sellerServiceNumber) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.EnergyAdjustedOrderReport);
		payload.getFilterCriteria().put("readingMonth", readingMonth);
		payload.getFilterCriteria().put("readingYear", readingYear);
		payload.getFilterCriteria().put("seller-service-number", sellerServiceNumber);
		String url=printUrl;
		System.out.println("EnergyAdjustedOrderReport print Caller");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
}


