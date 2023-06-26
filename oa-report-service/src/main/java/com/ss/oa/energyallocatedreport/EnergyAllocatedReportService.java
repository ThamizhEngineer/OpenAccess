//package com.ss.oa.energyallocatedreport;
//
//import java.io.FileNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.ss.oa.report.vo.EnergyAllocatedReport;
//import com.ss.oa.report.vo.SrcpReport;
//import com.ss.oashared.common.CommonUtils;
//import com.ss.oashared.common.OpenAccessException;
//import com.ss.oashared.model.PrintPayload;
//
//@RestController
//@RequestMapping("/energyallocationreport")
//public class EnergyAllocatedReportService {
//	@Value("${print.url}")
//	private String printUrl;
//	
//	@Autowired
//	EnergyAllocatedReportRepository energyAllocatedReportRepository;
//	
//	@Autowired
//	CommonUtils commonUtils;
//	
//	
//	@CrossOrigin(origins = "*")
//	@GetMapping("/getall")
//	public List<EnergyAllocatedReport> getAllocatedReports(
//			@RequestParam(value = "serviceNo", required = false) String serviceNo,
//			@RequestParam(value = "month", required = false) String month,
//			@RequestParam(value = "year", required = false) String year){
//		System.out.println(serviceNo);
//		System.out.println(month);
//		System.out.println(year);
//		return energyAllocatedReportRepository.getEnergy(serviceNo,month,year);
//	}
//	
//
//	
//	@GetMapping("/print")
//	public ResponseEntity<StreamingResponseBody>printEnergyReportService(@RequestParam(value = "id", required = false) int id,	
//			@RequestParam(value = "serviceNo", required = false) String serviceNo,
//			@RequestParam(value = "month", required = false) String month,
//			@RequestParam(value = "year", required = false) String year)
////			@RequestParam(value = "openingBalance", required = false) long openingBalance,
////			@RequestParam(value = "netGeneration", required = false) String netGeneration,
////			@RequestParam(value = "allotedGross", required = false) long allotedGross,
////			@RequestParam(value = "allottedNet", required = false) long allottedNet,
////			@RequestParam(value = "adjustedNet", required = false) long adjustedNet,
////			@RequestParam(value = "htBanking", required = false) long htBanking,
////			@RequestParam(value = "closingWithSurplus", required = false) long closingWithSurplus)
//			throws FileNotFoundException{
//		return  commonUtils.fetchFileAsStreamResponse(printCall( serviceNo, month, year));
//
//	}
//	
//	public PrintPayload  printCall( String serviceNo, String month, String year) throws OpenAccessException {
//		RestTemplate restTemplate = CommonUtils.getTemplate();
//		PrintPayload payload = new PrintPayload();
//		payload.setName(PrintPayload.PrintTypes.EnergyAllotmentReport);
//		
//		payload.getFilterCriteria().put("serviceNo", serviceNo);
//		payload.getFilterCriteria().put("month", month);
//		payload.getFilterCriteria().put("year", year);
//		String url=printUrl;
//		System.out.println("EnergyAllocatedReport print Caller");
//		System.out.println(url);
//		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
//		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
//		return payload;
//	}
//	
//	@GetMapping("/getall/_internal")
//	public List<EnergyAllocatedReport> getAllInternal(
//			@RequestParam(value = "serviceNo", required = false) String serviceNo,
//			@RequestParam(value = "month", required = false) String month,
//			@RequestParam(value = "year", required = false) String year){
//		return getAllocatedReports(serviceNo,month,year);
//	}
//
//}
