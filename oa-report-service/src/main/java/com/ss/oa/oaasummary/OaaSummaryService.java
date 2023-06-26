package com.ss.oa.oaasummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.OaaSummary;

@RestController
@RequestMapping("/application")
public class OaaSummaryService {
	@Autowired
	 OaaSummaryRepository oaaSummaryRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/OAA")
	public Iterable<OaaSummary> oaaList(Model model, Pageable pageable,
			@RequestParam(value = "genCompServId", required = false) String genCompServId,
			@RequestParam(value = "saleTypeCode", required = false) String saleTypeCode,
			@RequestParam(value = "statusCode", required = false) String statusCode,
			@RequestParam(value = "genCompId", required = false) String genCompId,
			@RequestParam(value = "genEndOrgId", required = false) String genEndOrgId,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
			@RequestParam(value = "genVoltageCode", required = false) String genVoltageCode,
			@RequestParam(value = "month", required = false) String month,
            @RequestParam(value = "year", required = false) String year) {
        Page<OaaSummary> pages = oaaSummary(pageable,genCompServId,saleTypeCode,statusCode,genCompId,genEndOrgId,fuelTypeCode,genVoltageCode,month,year);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("oaasummary", pages.getContent());

		return pages;
        
	}
	public Page<OaaSummary> oaaSummary(Pageable pageable,String genCompServId,String saleTypeCode,
			String statusCode,String genCompId,String genEndOrgId,String fuelTypeCode,String genVoltageCode,String month,String year) {
		boolean searchByGenCompServId = false;boolean searchBySaleTypeCode = false;boolean searchByStatusCode = false;
		boolean searchByGenCompId = false;boolean searchByGenEndOrgId = false;boolean searchByFuelTypeCode = false;
		boolean searchByGenVoltageCode = false;boolean searchByMonth = false;boolean searchByYear = false;
		
		if (genCompServId != null && !genCompServId.isEmpty())
			searchByGenCompServId = true;
        if (saleTypeCode != null && !saleTypeCode.isEmpty())
        	searchBySaleTypeCode = true;
        if (statusCode != null && !statusCode.isEmpty())
        	searchByStatusCode = true;
        if (genCompId != null && !genCompId.isEmpty())
        	searchByGenCompId = true;
        if (genEndOrgId != null && !genEndOrgId.isEmpty())
        	searchByGenEndOrgId = true;
        if (fuelTypeCode != null && !fuelTypeCode.isEmpty())
        	searchByFuelTypeCode = true;
        if (genVoltageCode != null && !genVoltageCode.isEmpty())
        	searchByGenVoltageCode = true;
        if (month != null && !month.isEmpty())
        	searchByMonth = true;
        if (year != null && !year.isEmpty())
        	searchByYear = true;
		
        
        if (searchByGenCompServId) {
            return oaaSummaryRepository.findByGenCompServId(pageable,genCompServId);
        }
        if (searchBySaleTypeCode) {
            return oaaSummaryRepository.findBySaleTypeCode(pageable,saleTypeCode);
        }
        if (searchByStatusCode) {
            return oaaSummaryRepository.findByStatusCode(pageable,statusCode);
        }
        if (searchByGenCompId) {
            return oaaSummaryRepository.findByGenCompId(pageable,genCompId);
        }
        if (searchByGenEndOrgId) {
            return oaaSummaryRepository.findByGenEndOrgId(pageable,genEndOrgId);
        }
        if (searchByFuelTypeCode) {
            return oaaSummaryRepository.findByFuelTypeCode(pageable,fuelTypeCode);
        }
        if (searchByGenVoltageCode) {
            return oaaSummaryRepository.findByGenVoltageCode(pageable,genVoltageCode);
        }
        if (searchByMonth) {
            return oaaSummaryRepository.findByMonth(pageable,month);
        }
        if (searchByYear) {
            return oaaSummaryRepository.findByYear(pageable,year);
        }

        
        
        return oaaSummaryRepository.findAll(pageable);
}
}
