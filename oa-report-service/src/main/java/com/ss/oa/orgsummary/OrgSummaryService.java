package com.ss.oa.orgsummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.OrgSummary;


@RestController
@RequestMapping("/report/org-summaries")
public class OrgSummaryService {
	@Autowired
	OrgSummaryRepository orgSummaryRepository;
	
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<OrgSummary> getSubstation(@RequestParam(value = "parentCode", required = false) String parentCode,@RequestParam(value = "fuelTypeCode", required = false) String fuelTypeCode,
			@RequestParam(value = "ncesDivisionCode", required = false) String ncesDivisionCode,@RequestParam(value = "typeCode", required = false) String typeCode) {
        return orgSummaryRepository.getOrg(parentCode,ncesDivisionCode,typeCode,fuelTypeCode);
        }
}
