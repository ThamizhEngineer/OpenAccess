package com.ss.oa.fuelsummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.FuelSummary;

@RestController
@RequestMapping("/report/fuel-summaries")
public class FuelSummaryService {
	
	@Autowired
	FuelSummaryRepository fuelSummaryRepository;
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<FuelSummary>getFuels(@RequestParam(value = "fuelCode", required = false) String fuelCode,
			@RequestParam(value = "fuelName", required = false) String fuelName,
			@RequestParam(value = "fuelGroup", required = false) String fuelGroup) 
			{
				return fuelSummaryRepository.getFuels(fuelCode,fuelName,fuelGroup);
			}
	
}
