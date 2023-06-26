package com.ss.oa.transaction.excessunit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.transaction.vo.ExcessUnit;
import com.ss.oashared.common.OpenAccessException;

@Service
@RestController
@RequestMapping("transaction/excessunit")
public class ExcessUnitService {
	
	@Autowired
	ExcessUnitRepository excessUnitRepository;
	
	@CrossOrigin(origins = "*")  
	@GetMapping
	public List<ExcessUnit> getExcessUnit(@RequestParam(name="supplierid",required=false) String companyServiceId,
			@RequestParam(name="month",required=false) String readingMonth,
			@RequestParam(name="year",required=false) String readingYear) throws OpenAccessException{
		return excessUnitRepository.getExcessUnitByCriteria(companyServiceId, readingMonth, readingYear);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/_internal")
	public List<ExcessUnit> getExcessUnitforprint(@RequestParam(name="companyServiceId",required=false) String companyServiceId,
			@RequestParam(name="readingMonth",required=false) String readingMonth,
			@RequestParam(name="readingYear",required=false) String readingYear) throws OpenAccessException{
		if(companyServiceId != null && readingMonth!=null && readingYear!=null) {
			System.out.println("came for banking--"+companyServiceId+readingMonth+readingYear);
		return excessUnitRepository.getExcessUnitByCriteriaForPrint(companyServiceId, readingMonth, readingYear);
		}
		List<ExcessUnit>  a = null;
		return a;
	}

}
