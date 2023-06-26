package com.ss.oa.energyadjustedorderchargereport;

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


import com.ss.oashared.model.EnergyAdjustedOrderCharge;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@RestController
@RequestMapping("/report/eschargereport")
public class EnergyAdjustedOrderChargeService {
	
	@Value("${print.url}")
	private String printUrl;
	
	@Autowired 
	EnergyAdjustedOrderChargeRepo energyAdjustedOrderChargeRepo;
	
	@Autowired
	CommonUtils commonUtils;

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<EnergyAdjustedOrderCharge> getAlleschargereport(){
		return energyAdjustedOrderChargeRepo.findAll();
}
	@CrossOrigin(origins = "*")
	@GetMapping("/get/_internal")
	public List<EnergyAdjustedOrderCharge> getAllCee(
			@RequestParam(value = "readingMnth", required = false) String readingMnth,
			@RequestParam(value = "readingYr", required = false) String readingYr,
			@RequestParam(value = "serviceNo", required = false) String serviceNo,
			@RequestParam(value = "suplrCode", required = false) String suplrCode){
		return energyAdjustedOrderChargeRepo.getEsc(readingMnth, readingYr, serviceNo, suplrCode);
	}
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printEnergyAdjustedOrderChargeService(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "suplrCode", required = false) String suplrCode,
			@RequestParam(value = "serviceNo", required = false) String serviceNo,
			@RequestParam(value = "readingMnth", required = false) String readingMnth,
			@RequestParam(value = "readingYr", required = false) String readingYr,
			@RequestParam(value = "meterReadingCharges", required = false) String meterReadingCharges,
			@RequestParam(value = "oMCharges", required = false) String oMCharges,
			@RequestParam(value = "systemOperationCharges", required = false) String systemOperationCharges,
			@RequestParam(value = "rkvahPenalty", required = false) String rkvahPenalty,
			@RequestParam(value = "negativeEnergyCharges", required = false) String negativeEnergyCharges,
			@RequestParam(value = "schedulingCharges", required = false) String schedulingCharges,
			@RequestParam(value = "transmissionCharges", required = false) String transmissionCharges)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall( suplrCode, serviceNo, readingMnth, readingYr, meterReadingCharges, oMCharges, systemOperationCharges, rkvahPenalty, negativeEnergyCharges, schedulingCharges, transmissionCharges));

	}
	
	public PrintPayload  printCall( String suplrCode, String serviceNo, String readingMnth, String readingYr, String meterReadingCharges, String oMCharges, String systemOperationCharges, String rkvahPenalty, String negativeEnergyCharges, String schedulingCharges, String transmissionCharges) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.EnergyAdjustedOrderChargeReport);
		
		payload.getFilterCriteria().put("suplrCode", suplrCode);
		payload.getFilterCriteria().put("serviceNo", serviceNo);
		payload.getFilterCriteria().put("readingMnth", readingMnth);
		payload.getFilterCriteria().put("readingYr", readingYr);
		payload.getFilterCriteria().put("meterReadingCharges", meterReadingCharges);
		payload.getFilterCriteria().put("oMCharges", oMCharges);
		payload.getFilterCriteria().put("systemOperationCharges", systemOperationCharges);
		payload.getFilterCriteria().put("rkvahPenalty", rkvahPenalty);
		payload.getFilterCriteria().put("negativeEnergyCharges", negativeEnergyCharges);
		payload.getFilterCriteria().put("schedulingCharges", schedulingCharges);
		payload.getFilterCriteria().put("transmissionCharges", transmissionCharges);
		String url=printUrl;
		System.out.println("EsReport print Caller");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
}

