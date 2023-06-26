package com.ss.oa.energyorderchargereport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.EnergyOrderCharge;

@RestController
@RequestMapping("/report/esorderchargereport")
public class EnergyOrderChargeReportService {
	
	@Autowired
	EnergyOrderChargeReportRepo repo;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<EnergyOrderCharge> getAll(){
		return repo.findAll();
}
	@CrossOrigin(origins = "*")
	@GetMapping("/get/_internal")
	public List<EnergyOrderCharge> getByCrietria(
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "buyerServiceNumber", required = false) String buyerServiceNumber,
			@RequestParam(value = "sellerServiceNumber", required = false) String sellerServiceNumber){
		return repo.getByFilter(month, year, buyerServiceNumber, sellerServiceNumber);
	}

}
