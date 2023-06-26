package com.ss.oa.generationsummary;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.report.vo.GenerationSummary;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
@RestController
@RequestMapping("/report/gen-summaries")
public class GenerationSummaryService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Value("${print.url}")
	private String printUrl;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	 GenerationSummaryRepository generationSummaryRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<GenerationSummary> getGenerationSummary(
			@RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "sellerServiceId", required = false) String sellerServiceId,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "fuelCode", required = false) String fuelCode,
            @RequestParam(value = "voltageCode", required = false) String voltageCode) throws OpenAccessException {
		boolean searchByorgId = false, searchBysellerServiceId = false, searchBycompanyId = false,searchByfuelCode = false,searchByvoltageCode = false;
        if (orgId != null && !orgId.isEmpty())
        	searchByorgId = true;
        if (sellerServiceId != null && !sellerServiceId.isEmpty())
        	searchBysellerServiceId = true;
        if (companyId != null && !companyId.isEmpty())
        	searchBycompanyId = true;
        if (fuelCode != null && !fuelCode.isEmpty())
        	searchByfuelCode = true;
        if (voltageCode != null && !voltageCode.isEmpty())
        	searchByvoltageCode = true;
        
        if (searchByorgId) {
            return generationSummaryRepository.findByOrgId(orgId);
        }
        else if (searchBysellerServiceId) {
            return generationSummaryRepository.findBySellerServiceId(sellerServiceId);
        }
        else if (searchBycompanyId) {
            return generationSummaryRepository.findBycompanyId(companyId);
        }
        else if (searchByfuelCode) {
            return generationSummaryRepository.findByFuelCode(fuelCode);
        }
        else if (searchByvoltageCode) {
            return generationSummaryRepository.findByVoltageCode(voltageCode);
        }
		return generationSummaryRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<GenerationSummary> getGenerationSummaryById(@PathVariable(value="id")String id)throws OpenAccessException{
		return generationSummaryRepository.findById(id);
		
	}
	
	@GetMapping("/processed-report/{process}/_internal")
	public  List<GenerationSummary> processedGenerationReport(@PathVariable(value = "process") String process,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode,
            @RequestParam(value = "isRec", required = false) String isRec,
            @RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "dispFuelTypeGroup", required = false) String dispFuelTypeGroup) {
		System.out.println(dispFuelTypeGroup);
		if(process.equals("ORG-WISE")) {
			return orgWiseGeneration(month, year, flowTypeCode);
		}
		else if(process.equals("FLOW-WISE")) {
			return flowTypeWise(month, year, flowTypeCode);
		}
		else if(process.equals("FLOW-REC-WISE")) {
			return getFlowWithRec(month, year, flowTypeCode);
		}
		else if(process.equals("ORG-WISE-SUMMARY")) {
			return formedReport(month, year, flowTypeCode,isRec,orgId,dispFuelTypeGroup);
		}
		else {
			return generationWise(month, year, flowTypeCode, isRec);
		}
			
	}
	
	
	
	@CrossOrigin(origins="*")
	@GetMapping("/orgwisegen")
	public List<GenerationSummary> orgWiseGeneration(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode) {
		List<GenerationSummary>  gsList= generationSummaryRepository.orgWiseGeneration(month, year, flowTypeCode);
		return gsList;
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/formedforstb")
	public List<GenerationSummary> formedForStb(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode,
            @RequestParam(value = "isRec", required = false) String isRec,
            @RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "fuelCode", required = false) String fuelCode,
            @RequestParam(value = "plantClassTypeCode", required = false) String plantClassTypeCode) {
		List<GenerationSummary>  gsList= generationSummaryRepository.formedForStb(month, year, flowTypeCode,isRec,orgId,fuelCode,plantClassTypeCode);
		
		Collection<GenerationSummary> gsCollection = gsList;
		List<Long> totalUnits = gsCollection.stream().map(GenerationSummary::getTotalUnits).collect(Collectors.toList());
		List<Long> totalIxportUnits = gsCollection.stream().map(GenerationSummary::getTotalImportUnits).collect(Collectors.toList());
		List<Long> totalExportUnits = gsCollection.stream().map(GenerationSummary::getTotalExportUnits).collect(Collectors.toList());
		List<Long> totalMcapacity = gsCollection.stream().map(GenerationSummary::getTotalMachineCapacity).collect(Collectors.toList());
		List<Long> totalCount = gsCollection.stream().map(GenerationSummary::getRecCount).collect(Collectors.toList());

		long sumTotalUnits = totalUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalIxportUnits = totalIxportUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalExportUnits = totalExportUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalMcapacity = totalMcapacity.stream().mapToLong(Long::intValue).sum();
		long sumServiceCount = totalCount.stream().mapToLong(Long::intValue).sum();

		for(GenerationSummary gs:gsList) {
			gs.setSumTotalUnits(sumTotalUnits);
			gs.setSumTotalImportUnits(sumTotalIxportUnits);
			gs.setSumTotalExportUnits(sumTotalExportUnits);
			gs.setSumMachineCapacity(sumTotalMcapacity);
			gs.setSumserviceCount(sumServiceCount);
		}
		
		return gsList;
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/formedreport")
	public List<GenerationSummary> formedReport(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode,
            @RequestParam(value = "isRec", required = false) String isRec,
            @RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "dispFuelTypeGroup", required = false) String dispFuelTypeGroup) {
		System.out.println(dispFuelTypeGroup);
		List<GenerationSummary>  gsList= generationSummaryRepository.formedReport(month, year, flowTypeCode,isRec,orgId,dispFuelTypeGroup);
		
		Collection<GenerationSummary> gsCollection = gsList;
		List<Long> totalUnits = gsCollection.stream().map(GenerationSummary::getTotalUnits).collect(Collectors.toList());
		List<Long> totalIxportUnits = gsCollection.stream().map(GenerationSummary::getTotalImportUnits).collect(Collectors.toList());
		List<Long> totalExportUnits = gsCollection.stream().map(GenerationSummary::getTotalExportUnits).collect(Collectors.toList());
		List<Long> totalMcapacity = gsCollection.stream().map(GenerationSummary::getTotalMachineCapacity).collect(Collectors.toList());
		List<Long> totalCount = gsCollection.stream().map(GenerationSummary::getRecCount).collect(Collectors.toList());

		long sumTotalUnits = totalUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalIxportUnits = totalIxportUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalExportUnits = totalExportUnits.stream().mapToLong(Long::intValue).sum();
		long sumTotalMcapacity = totalMcapacity.stream().mapToLong(Long::intValue).sum();
		long sumServiceCount = totalCount.stream().mapToLong(Long::intValue).sum();

		for(GenerationSummary gs:gsList) {
			gs.setSumTotalUnits(sumTotalUnits);
			gs.setSumTotalImportUnits(sumTotalIxportUnits);
			gs.setSumTotalExportUnits(sumTotalExportUnits);
			gs.setSumMachineCapacity(sumTotalMcapacity);
			gs.setSumserviceCount(sumServiceCount);
		}
		
		return gsList;
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/withFlowType")
	public List<GenerationSummary> flowTypeWise(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode) {
		List<GenerationSummary>  gsList= generationSummaryRepository.flowTypeWiseGeneration(month, year, flowTypeCode);
		return gsList;
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/flowWithRec")
	public List<GenerationSummary> getFlowWithRec(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode) {
		List<GenerationSummary>  gsList= generationSummaryRepository.flowWithRec(month, year, flowTypeCode);
		return gsList;
	}
	
	
	@CrossOrigin(origins="*")
	@GetMapping("/generationWise")
	public List<GenerationSummary> generationWise(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode,
            @RequestParam(value = "isRec", required = false) String isRec) {
		List<GenerationSummary>  gsList= generationSummaryRepository.generationWise(month, year, flowTypeCode, isRec);
		return gsList;
	}
	
	
	@CrossOrigin(origins="*")
	@GetMapping("/monthwisegen")
	public List<GenerationSummary> getTotalQuantum(@RequestParam(value = "orgId", required = false) String orgId) {
		return generationSummaryRepository.monthWiseGeneration(orgId);
	}
	
	
	
	@GetMapping("/print")
	public ResponseEntity<StreamingResponseBody>
  printEnergyAdjOrder(
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "flowTypeCode", required = false) String flowTypeCode,
            @RequestParam(value = "isRec", required = false) String isRec,
            @RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "fuelCode", required = false) String fuelCode)
			throws FileNotFoundException{
		return  commonUtils.fetchFileAsStreamResponse(printCall(month, year,flowTypeCode,isRec,orgId,fuelCode));

	}
	
	public PrintPayload  printCall(String month,String year,String flowTypeCode,String isRec,String orgId,String fuelCode) throws OpenAccessException {
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.orgWiseGenerationReport);
		payload.getFilterCriteria().put("month", month);
		payload.getFilterCriteria().put("year", year);
		payload.getFilterCriteria().put("flowTypeCode", flowTypeCode);
		payload.getFilterCriteria().put("isRec", isRec);
		payload.getFilterCriteria().put("orgId", orgId);
		payload.getFilterCriteria().put("fuelCode", fuelCode);

		String url=printUrl;
		log.info("Gen-Summary-Print-Url-"+url);
		log.info("Gen-Summary-Payload"+payload);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}
	
	
}
