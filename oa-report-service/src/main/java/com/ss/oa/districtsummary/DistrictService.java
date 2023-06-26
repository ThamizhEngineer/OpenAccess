package com.ss.oa.districtsummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.DistrictSummary;

@RestController
@RequestMapping("report/district-summaries")
public class DistrictService {
	
	@Autowired
	DistrictRepository districtRepo;
	
	
	@CrossOrigin(origins="*")
	@GetMapping
	public Iterable<DistrictSummary> getDist(){
		return districtRepo.findAll();
	}

}
