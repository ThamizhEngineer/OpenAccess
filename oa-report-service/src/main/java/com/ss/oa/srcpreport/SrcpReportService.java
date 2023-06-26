package com.ss.oa.srcpreport;

import java.io.FileNotFoundException;
import java.util.List;

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

import com.ss.oa.report.vo.SrcpReport;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@RestController
@RequestMapping("/report/srcpreport")
public class SrcpReportService {
	
	@Value("${print.url}")
	private String printUrl;
	
	@Autowired
	SrcpReportRepository srcpReportRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@CrossOrigin(origins="*")
	@GetMapping("/findall")
	public Iterable<SrcpReport> getAllscrpReport(){
		return srcpReportRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/getall")
	public List<SrcpReport> getAllScrp(
			
			@RequestParam(value = "ncesDivisionCode", required = false) String ncesDivisionCode,
			@RequestParam(value = "month", required = false) String month,	@RequestParam(value = "year", required = false) String year){
		return srcpReportRepository.getSrcp( ncesDivisionCode, month,year);
	}
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printSrcpReportService(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "totalCapacitySum", required = false) Integer totalCapacitySum,
			@RequestParam(value = "ncesDivisionCode", required = false) String ncesDivisionCode,
			@RequestParam(value = "noOfWeg", required = false) String noOfWeg,
			@RequestParam(value = "totalGenerationSum", required = false) Integer totalGenerationSum,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "installedBy", required = false) String installedBy)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall( ncesDivisionCode, month,year));

	}

	public PrintPayload  printCall(String ncesDivisionCode, String month, String year) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.SrcpReport);
		payload.getFilterCriteria().put("ncesDivisionCode", ncesDivisionCode);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		String url=printUrl;
		System.out.println("SrcpReport print Caller");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/getall/_internal")
	public List<SrcpReport> getAllScrpInternal(
			
			@RequestParam(value = "ncesDivisionCode", required = false) String ncesDivisionCode,
			@RequestParam(value = "month", required = false) String month,	@RequestParam(value = "year", required = false) String year){
		return getAllScrp( ncesDivisionCode, month,year);
	}
}
