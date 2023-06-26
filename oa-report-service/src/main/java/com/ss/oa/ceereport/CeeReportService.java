package com.ss.oa.ceereport;

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

import com.ss.oa.report.vo.CeeReport;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@RestController
@RequestMapping("/report/ceereport")
public class CeeReportService {
	@Value("${print.url}")
	private String printUrl;
	
	@Autowired 
	CeeReportRepository ceeReportRepository;
	
	@Autowired
	CommonUtils commonUtils;

	@CrossOrigin(origins = "*")
	@GetMapping("/findall")
	public Iterable<CeeReport> getAllCeeReport(){
		return ceeReportRepository.findAll();
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getall")
	public List<CeeReport> getAllCee(
			@RequestParam(value = "typeOfShare", required = false) String typeOfShare,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode){
		System.out.println(month);
		System.out.println(year);
		return ceeReportRepository.getCee(typeOfShare,month,year,fuelTypeCode);
	}
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printCeeReportService(@RequestParam(value = "id", required = false) Integer id,
			
			@RequestParam(value = "totalCapacitySum", required = false) Integer totalCapacitySum,
			@RequestParam(value = "typeOfShare", required = false) String typeOfShare,
			@RequestParam(value = "installedBy", required = false) String installedBy,
			@RequestParam(value = "netGenerationSum", required = false) Integer netGenerationSum,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
			@RequestParam(value = "fuelTypeName", required = false) String fuelTypeName)
			throws FileNotFoundException{
		System.out.println(fuelTypeCode);
		System.out.println(fuelTypeName);
		System.out.println(month);
		return  commonUtils.fetchFileAsStreamResponse(printCall( typeOfShare, installedBy, month,year, fuelTypeCode, fuelTypeName));

	}
	
	public PrintPayload  printCall( String typeOfShare, String installedBy, String month, String year,String fuelTypeCode, String fuelTypeName) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.CeeReport);
		
		payload.getFilterCriteria().put("typeOfShare", typeOfShare);
		payload.getFilterCriteria().put("installedBy", installedBy);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		payload.getFilterCriteria().put("fuelTypeCode", fuelTypeCode);
		payload.getFilterCriteria().put("fuelTypeName", fuelTypeName);
		String url=printUrl;
		System.out.println("CeeReport print Caller");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/getall/_internal")
	public List<CeeReport> getAllCeeInternal(
			@RequestParam(value = "typeOfShare", required = false) String typeOfShare,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode){
		return getAllCee(typeOfShare,month,year,fuelTypeCode);
	}
}
