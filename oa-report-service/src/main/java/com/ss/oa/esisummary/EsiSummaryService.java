package com.ss.oa.esisummary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.EsiSummary;

@RestController
@RequestMapping("/application")
public class EsiSummaryService {

	@Autowired
	EsiSummaryRepository esiSummaryRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/summaries/POWER-SALE")
	public Iterable<EsiSummary> esiList(Model model, Pageable pageable,
			@RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "genCompServId", required = false) String genCompServId,
			@RequestParam(value = "saleTypeCode", required = false) String saleTypeCode,
            @RequestParam(value = "statusCode", required = false) String statusCode,
			@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
            @RequestParam(value = "genEndOrgId", required = false) String genEndOrgId,
            @RequestParam(value = "genVoltageCode", required = false) String genVoltageCode,
            @RequestParam(value = "genCompId", required = false) String genCompId,
            @RequestParam(value = "agmtPeriodCode", required = false) String agmtPeriodCode) {
        Page<EsiSummary> pages = esiSummary(pageable, id, genCompServId, saleTypeCode, statusCode, fuelTypeCode, genEndOrgId, genVoltageCode, genCompId, agmtPeriodCode);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("esisummary", pages.getContent());

		return pages;
        
	}
	
	
	
	public Page<EsiSummary> esiSummary(Pageable pageable,
			String id,String genCompServId,String saleTypeCode,String statusCode,String fuelTypeCode,String genEndOrgId,
			String genVoltageCode,String genCompId,String agmtPeriodCode){
    return esiSummaryRepository.getEsiQuery(pageable, id, genCompServId, saleTypeCode, statusCode, fuelTypeCode, genEndOrgId, genVoltageCode, genCompId, agmtPeriodCode);
}
}
