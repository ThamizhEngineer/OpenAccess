package com.ss.oa.PowerplantSummary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.ss.oa.report.vo.PowerplantSummary;
import com.ss.oashared.common.OpenAccessException;

@RestController
@RequestMapping("/report/pp-summaries")
public class PowerplantSummaryService {

	
	@Autowired
	private PowerplantSummaryRepository powerplantSummaryRepository;
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<PowerplantSummary> getPowerplantSummaryById(@PathVariable(value="id")String id)throws OpenAccessException{
		return powerplantSummaryRepository.findById(id);
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<PowerplantSummary> getGenOrgCount(@RequestParam(value = "orgId", required = false) String orgId,
            @RequestParam(value = "sellerServiceId", required = false) String sellerServiceId,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "fuelCode", required = false) String fuelCode,
            @RequestParam(value = "voltageCode", required = false) String voltageCode,
            @RequestParam(value = "ncesDivisionCode", required = false) String ncesDivisionCode,
            @RequestParam(value = "substationId", required = false) String substationId,
            @RequestParam(value = "meterNumber", required = false) String meterNumber,
            @RequestParam(value = "sellerServiceNumber", required = false) String  sellerServiceNumber) {
		return powerplantSummaryRepository.getPpOrgCount(orgId,sellerServiceId,companyId,fuelCode,voltageCode,ncesDivisionCode,substationId,meterNumber,sellerServiceNumber);
	}
}
