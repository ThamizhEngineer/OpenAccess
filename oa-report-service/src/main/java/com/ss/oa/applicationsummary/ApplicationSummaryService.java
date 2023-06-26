package com.ss.oa.applicationsummary;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.consentsummary.ConsentSummaryRepository;
import com.ss.oa.epasummary.EpaSummaryRepository;
import com.ss.oa.esisummary.EsiSummaryRepository;
import com.ss.oa.ewasummary.EwaSummaryRepository;
import com.ss.oa.ipasummary.IpaSummaryRepository;
import com.ss.oa.nocgensummary.NocGenSummaryRepository;
import com.ss.oa.nocsummary.NocSummaryRepository;
import com.ss.oa.oaasummary.OaaSummaryRepository;
import com.ss.oa.report.vo.ApplicationOaaSearch;
import com.ss.oa.report.vo.ApplicationSearchSummary;
import com.ss.oa.report.vo.EsiSummary;
import com.ss.oa.report.vo.EwaSummary;
import com.ss.oa.report.vo.IpaSummary;
import com.ss.oa.report.vo.NocGenSummary;
import com.ss.oa.report.vo.NocSummary;
import com.ss.oa.report.vo.OaaSummary;
import com.ss.oa.report.vo.ScSummary;
import com.ss.oa.scsummary.ScSummaryRepository;
import com.ss.oa.report.vo.ApplicationSummary;
import com.ss.oa.report.vo.ConsentSummary;
import com.ss.oa.report.vo.EpaSummary;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/application")
public class ApplicationSummaryService {

	@Autowired
	ApplicationSummaryRepository applicationSummaryRepository;

	@Autowired
	EsiSummaryRepository esiSummaryRepository;

	@Autowired
	NocSummaryRepository nocSummaryRepository;

	@Autowired
	IpaSummaryRepository ipaSummaryRepository;

	@Autowired
	NocGenSummaryRepository nocGenSummaryRepository;

	@Autowired
	ConsentSummaryRepository consentSummaryRepository;

	@Autowired
	EwaSummaryRepository ewaSummaryRepository;

	@Autowired
	EpaSummaryRepository epaSummaryRepository;

	@Autowired
	ScSummaryRepository scSummaryRepository;

	@Autowired
	OaaSummaryRepository oaaSummaryRepository;

	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ApplicationSummary> getPowerSale(
			@RequestParam(value = "processTypeCode", required = false) String processTypeCode,
			@RequestParam(value = "powerSaleId", required = false) String powerSaleId,
			@RequestParam(value = "consumerCompServId", required = false) String consumerCompServId,
			@RequestParam(value = "genCompServId", required = false) String genCompServId) {
		boolean searchByProcessTypeCode = false;
		boolean searchByPowerSaleId = false;
		boolean searchByConsumerCompServId = false;
		boolean searchByGenCompServId = false;
		if (processTypeCode != null && !processTypeCode.isEmpty())
			searchByProcessTypeCode = true;
		if (powerSaleId != null && !powerSaleId.isEmpty())
			searchByPowerSaleId = true;
		if (consumerCompServId != null && !consumerCompServId.isEmpty())
			searchByConsumerCompServId = true;
		if (genCompServId != null && !genCompServId.isEmpty())
			searchByGenCompServId = true;

		if (searchByProcessTypeCode) {
			return applicationSummaryRepository.findByProcessTypeCode(processTypeCode);
		} else if (searchByPowerSaleId) {
			return applicationSummaryRepository.findByPowerSaleId(powerSaleId);
		} else if (searchByConsumerCompServId) {
			return applicationSummaryRepository.findByConsumerCompServId(consumerCompServId);
		} else if (searchByGenCompServId) {
			return applicationSummaryRepository.findByGenCompServId(genCompServId);
		}

		return applicationSummaryRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/application-summary")
	public List<ApplicationSummary> getAllPowerSales(
			@RequestParam(value = "powerSaleId", required = false) String powerSaleId,
			@RequestParam(value = "genCompServId", required = false) String genCompServId,
			@RequestParam(value = "genCompServNumber", required = false) String genCompServNumber,
			@RequestParam(value = "genEndOrgId", required = false) String genEndOrgId,
			@RequestParam(value = "consumerCompServId", required = false) String consumerCompServId,
			@RequestParam(value = "consumerEndOrgId", required = false) String consumerEndOrgId,
			@RequestParam(value = "processTypeCode", required = false) String processTypeCode) {

		return applicationSummaryRepository.getPowerSales(powerSaleId, genCompServId, genCompServNumber, genEndOrgId,
				consumerCompServId, consumerEndOrgId, processTypeCode);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/summary-counts/{process}")
	public Iterable<ApplicationSearchSummary> getAppSearchSummary(@PathVariable(value = "process") String process,
			String processTypeCode, @RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "genCompServId", required = false) String genCompServId) {

		if (process != null && !process.isEmpty()) {
			System.out.println(process);

			if (process.equals("POWER-SALE")) {
				processTypeCode = "POWER-SALE";
			} else if (process.equals("NOC")) {
				processTypeCode = "NOC";
			} else if (process.equals("NOC-GEN")) {
				processTypeCode = "NOC-GEN";
			} else if (process.equals("IPA")) {
				processTypeCode = "IPA";
			} else if (process.equals("EWA")) {
				processTypeCode = "EWA";
			} else if (process.equals("EPA")) {
				processTypeCode = "EPA";
			} else if (process.equals("SC")) {
				processTypeCode = "SC";
			} else if (process.equals("CONSENT")) {
				processTypeCode = "CONSENT";
			} else if (process.equals("ALL")) {
				processTypeCode = "";
			}

		}
		Iterable<ApplicationSearchSummary> applicationSearchSummaries= applicationSummaryRepository.findAppSearchSummary(processTypeCode, month, year,genCompServId);
		applicationSearchSummaries =formatOrderCount(applicationSearchSummaries);
		return applicationSearchSummaries;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/summary-counts/OAA")
	public Iterable<ApplicationOaaSearch> getAppOaaSearch(
			@RequestParam(value = "processTypeCode", required = false) String processTypeCode) {
		return applicationSummaryRepository.findAppOaaSearch(processTypeCode);
	}


	//  ----------------------------------------START----------------------------------------------- 

	// POWER-SALE summary ------------------------------------------------------
	
	@CrossOrigin(origins = "*")
	@GetMapping("/esi")
	public Iterable<EsiSummary> getEsi() {
		return esiSummaryRepository.findAll();
	}

	// NOC summary ------------------------------------------------------
	
	
	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/NOC")
	public Iterable<NocSummary> nocRest(Model model, Pageable pageable,
			@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "consumerCompServId", required = false) String consumerCompServId,
			@RequestParam(value = "saleTypeCode", required = false) String saleTypeCode,
            @RequestParam(value = "statusCode", required = false) String statusCode,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
            @RequestParam(value = "consumerEndOrgId", required = false) String consumerEndOrgId,
            @RequestParam(value = "consumerVoltageCode", required = false) String consumerVoltageCode,
            @RequestParam(value = "consumerCompId", required = false) String consumerCompId,
            @RequestParam(value = "agmtPeriodCode", required = false) String agmtPeriodCode,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year) {
        Page<NocSummary> pages = nocSummary(pageable, id, consumerCompServId, saleTypeCode, statusCode, fuelTypeCode, consumerEndOrgId, consumerVoltageCode, consumerCompId, agmtPeriodCode,month,year);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("nocsummary", pages.getContent());

		return pages;
        
	}


	public Page<NocSummary> nocSummary(Pageable pageable, String id, String consumerCompServId, String saleTypeCode,
			String statusCode, String fuelTypeCode, String consumerEndOrgId, String consumerVoltageCode,
			String consumerCompId, String agmtPeriodCode,String month,String year) {
		return nocSummaryRepository.getNocQuery(pageable, id, consumerCompServId, saleTypeCode, statusCode,
				fuelTypeCode, consumerEndOrgId, consumerVoltageCode, consumerCompId, agmtPeriodCode,month,year);
	}

	// IPA summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/IPA")
	public Iterable<IpaSummary> ipaRest(Model model, Pageable pageable,
			@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "processTypeCode", required = false) String processTypeCode,
			@RequestParam(value = "genCompServId", required = false) String genCompServId,
            @RequestParam(value = "saleTypeCode", required = false) String saleTypeCode,
			@RequestParam(value = "agmtPeriodCode", required = false) String agmtPeriodCode,
            @RequestParam(value = "statusCode", required = false) String statusCode,
            @RequestParam(value = "genCompId", required = false) String genCompId,
            @RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
            @RequestParam(value = "genVoltageCode", required = false) String genVoltageCode,
            @RequestParam(value = "genCompServNumber", required = false) String genCompServNumber,
            @RequestParam(value = "genEndOrgId", required = false) String genEndOrgId,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year) {
		Page<IpaSummary> pages = ipaSummary(pageable, id, processTypeCode, genCompServId, saleTypeCode, agmtPeriodCode, statusCode, genCompId, fuelTypeCode, genVoltageCode, genCompServNumber, genEndOrgId,month, year);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("ipasummary", pages.getContent());
		return pages;
	}
	
	public Page<IpaSummary> ipaSummary(Pageable pageable,String id,String processTypeCode,String genCompServId,String saleTypeCode,String agmtPeriodCode,String statusCode,String genCompId,String fuelTypeCode,String genVoltageCode,String genCompServNumber,String genEndOrgId,String month,String year)  {
		return ipaSummaryRepository.getIpaQuery(pageable, id, processTypeCode, genCompServId, saleTypeCode, agmtPeriodCode, statusCode, genCompId, fuelTypeCode, genVoltageCode, genCompServNumber, genEndOrgId,month, year);
	}

	// NOC-GEN summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/NOC-GEN")
	public Iterable<NocGenSummary> nocGenRest(Model model, Pageable pageable,
			@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "processTypeCode", required = false) String processTypeCode,
			@RequestParam(value = "genCompId", required = false) String genCompId,
            @RequestParam(value = "genCompServId", required = false) String genCompServId,
			@RequestParam(value = "saleTypeCode", required = false) String saleTypeCode,
            @RequestParam(value = "genCompServNumber", required = false) String genCompServNumber,
            @RequestParam(value = "statusCode", required = false) String statusCode,
            @RequestParam(value = "genVoltageCode", required = false) String genVoltageCode,
            @RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
            @RequestParam(value = "genEndOrgId", required = false) String genEndOrgId,
            @RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year) {
		Page<NocGenSummary> pages = nocGenSummary(pageable, id, processTypeCode, genCompId, genCompServId, saleTypeCode, genCompServNumber, statusCode, genVoltageCode, fuelTypeCode, genEndOrgId,month,year);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("nocgensummary", pages.getContent());
		return pages;
	}
	
	public Page<NocGenSummary> nocGenSummary(Pageable pageable,String id,String processTypeCode,String genCompId,String genCompServId,String saleTypeCode,String genCompServNumber,String statusCode,String genVoltageCode,String fuelTypeCode,String genEndOrgId,String month,String year)  {
		return nocGenSummaryRepository.getNocQuery(pageable, id, processTypeCode, genCompId, genCompServId, saleTypeCode, genCompServNumber, statusCode, genVoltageCode, fuelTypeCode, genEndOrgId,month,year);
	}

	// CONSENT summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/CONSENT")
	public Iterable<ConsentSummary> consentRest(Model model, Pageable pageable,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "statusCode", required = false) String statusCode) {
		Page<ConsentSummary>pages = consentSummary(pageable, month, year, statusCode);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("consentsummary", pages.getContent());
		return pages;
		
	}
	
	public Page<ConsentSummary> consentSummary(Pageable pageable,String month,String year,String statusCode){
		return consentSummaryRepository.consentQuery(pageable, month, year, statusCode);
	}

	// EWA summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/EWA")
	public Iterable<EwaSummary> ewaRest(Model model, Pageable pageable,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "statusCode", required = false) String statusCode) {
		Page<EwaSummary>pages = ewaSummary(pageable, month, year, statusCode);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("ewasummary", pages.getContent());
		return pages;
	}
	
	public Page<EwaSummary> ewaSummary(Pageable pageable,String month,String year,String statusCode){
		return ewaSummaryRepository.ewaQuery(pageable, month, year, statusCode);
	}
	// EPA summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/EPA")
	public Iterable<EpaSummary> getEpa(Model model, Pageable pageable,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "statusCode", required = false) String statusCode) {
		Page<EpaSummary>pages = epaSummary(pageable, month, year, statusCode);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("epasummary", pages.getContent());
		return pages;
	}
	
	public Page<EpaSummary> epaSummary(Pageable pageable,String month,String year,String statusCode){
		return epaSummaryRepository.epaQuery(pageable, month, year, statusCode);
	}

	// SC summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/SC")
	public Iterable<ScSummary> scRest(Model model, Pageable pageable,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "statusCode", required = false) String statusCode) {
		Page<ScSummary>pages = scSummary(pageable, month, year, statusCode);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("scsummary", pages.getContent());
		return pages;
	}
	
	public Page<ScSummary> scSummary(Pageable pageable,String month,String year,String statusCode){
		return scSummaryRepository.scQuery(pageable, month, year, statusCode);
	}

	// OAA summary ------------------------------------------------------

	@CrossOrigin(origins = "*")
	@GetMapping("/oaa")
	public Iterable<OaaSummary> getOaa() {
		return oaaSummaryRepository.findAll();
	}

	public Iterable<ApplicationSearchSummary> formatOrderCount(Iterable<ApplicationSearchSummary> applicationSearchSummaries ){
		
		for(ApplicationSearchSummary applicationSearchSummary:applicationSearchSummaries) {
			if(applicationSearchSummary.getStatusCode().equals("CREATED")) {
				applicationSearchSummary.setOrderCount(1L);
			}
			if(applicationSearchSummary.getStatusCode().equals("APPROVED")) {
				applicationSearchSummary.setOrderCount(2L);
			}
			if(applicationSearchSummary.getStatusCode().equals("PROCESS")) {
				applicationSearchSummary.setOrderCount(3L);
			}
			if(applicationSearchSummary.getStatusCode().equals("AWATING-SLDC-APPROVAL")) {
				applicationSearchSummary.setOrderCount(4L);
			}
			if(applicationSearchSummary.getStatusCode().equals("SLDC-APPROVED")) {
				applicationSearchSummary.setOrderCount(5L);
			}
			if(applicationSearchSummary.getStatusCode().equals("SLDC-REJECTED")) {
				applicationSearchSummary.setOrderCount(6L);
			}
			if(applicationSearchSummary.getStatusCode().equals("COMPLETED")) {
				applicationSearchSummary.setOrderCount(7L);
			}
			
		}
		Collections.sort((List<ApplicationSearchSummary>) applicationSearchSummaries);
		
		return applicationSearchSummaries;
	}
}
