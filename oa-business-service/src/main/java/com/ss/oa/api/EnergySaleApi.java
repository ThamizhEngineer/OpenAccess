package com.ss.oa.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.transaction.energysale.EnergySaleRestService;
import com.ss.oa.transaction.vo.EnergySale;

@RestController
@RequestMapping("/api/es")
@Scope("prototype")
public class EnergySaleApi extends BaseDaoJdbc{
	
	@Autowired
	EnergySaleRestService energySaleRestService;
	
	@CrossOrigin(origins="*")
	@GetMapping("/energySales")
	public ResponseEntity <List<EnergySale>>  getEs(HttpServletRequest request,			
			@RequestParam(name="company-id",required=false) String companyId,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="edc-id",required=false) String ordId,	
			@RequestParam(name="service-id",required=false) String companyServiceId,
			@RequestParam(name="service-number",required=false) String companyServiceNumber,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="generation-statement-id",required=false) String generationStatementId,
			@RequestParam(name="is-stb",required=false) String isStb,
			@RequestParam(name="multiple-buyers",required=false) String multipleBuyers,
			@RequestParam(name="simple-energy-sale",required=false) String simpleEnergySale,
			@RequestParam(name="fuelTypeCode",required=false) String dispFuelTypeCode,
			@RequestParam(name="fuelTypeName",required=false) String dispFuelTypeName,
			@RequestParam(name="fuelGroupe",required=false) String fuelGroupe){
		return energySaleRestService.getAllEnergysales(request, companyId, companyName, ordId, companyServiceId, companyServiceNumber, month, year, generationStatementId, isStb, multipleBuyers, simpleEnergySale, dispFuelTypeCode, dispFuelTypeName, fuelGroupe, null);
	}
	
	@RequestMapping(value="/energySale/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnergySale> getEsById(@PathVariable("id")String id)
	{
		return energySaleRestService.getEnergySaleById(id);
	}

}
