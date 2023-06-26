package com.ss.oa.substationsummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.SubstationSummary;
@RestController
@RequestMapping("/report/substation-summaries")
public class SubstationSummaryService {
	@Autowired
	SubstationSummaryRepository substationSummaryRepository;
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<SubstationSummary>getSubstation(@RequestParam(value = "orgId", required = false) String orgId,@RequestParam(value = "type-of-ss", required = false) String typeOfSs) {
        return substationSummaryRepository.getSubstation(orgId,typeOfSs);		
	}
}
