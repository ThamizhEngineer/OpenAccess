package com.ss.oa.integration.generatorcharges;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ss.oa.common.IntegrationRestService;
import com.ss.oa.vo.GeneratorCharge;

@RestController
public class GeneratorChargesRestService extends IntegrationRestService {
	@Autowired
	private GeneratorChargeService service;
	@RequestMapping(value="/generator-charges", method = RequestMethod.GET)
	public ResponseEntity <List<GeneratorCharge>> getAllGeneratorCharges(@RequestParam(name="service-number",required=false) String serviceNumber,
															@RequestParam(name="billing-month",required=false) String billingMonth,
															@RequestParam(name="billing-year",required=false) String billingYear)
	{
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(serviceNumber!=null)
				criteria.put("serviceNumber",serviceNumber);
			if(billingMonth!=null)
					criteria.put("billingMonth",billingMonth);
			if(billingYear!=null)
				criteria.put("billingYear",billingYear);
			
			return ResponseEntity.ok(service.getAllGeneratorCharges(criteria));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}

}
