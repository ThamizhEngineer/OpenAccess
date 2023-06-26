package com.ss.oa.ledger.energycharge;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.LedgerRestService;

import com.ss.oa.ledger.vo.EnergyCharge;

@RestController
@Scope("prototype")
public class EnergyChargeRestService  extends LedgerRestService {
	
	@Autowired
	EnergyChargeService service;
	
 @RequestMapping(value="/energycharges", method = RequestMethod.GET)
	public ResponseEntity<List<EnergyCharge>> getEnergyCharges(@RequestParam(name="seller-end-org-id",required=false) String sellerEndOrgId,			
     	@RequestParam(name="month",required=false) String month,
	    @RequestParam(name="year",required=false) String year,
	    @RequestParam(name="company-id",required=false) String companyId,
	    @RequestParam(name="company-service-id",required=false) String companyServiceId
	   )
  {

try {
							
			
	Map<String,String> criteria =new HashMap<String,String>();
	if(sellerEndOrgId!=null) {
		criteria.put("seller-end-org-id",sellerEndOrgId);
	}
	if(month!=null) {
		criteria.put("month",month);
	}
	if(year!=null) {
		criteria.put("year",year);
	} 
	if(companyId!=null) {
		criteria.put("company-id",companyId);
	}	
	if(companyServiceId!=null) {
		criteria.put("company-service-id",companyServiceId);
	}		
			
		
		return ResponseEntity.ok(service.getAllEnergyCharges(criteria));
     }

	catch(Exception e) 
	{
				e.printStackTrace();				
		   		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
	}
}

@RequestMapping(value="/energycharge/{id}", method = RequestMethod.GET)
public ResponseEntity<EnergyCharge> getEnergyChargeId(@PathVariable("id")String id)
{
	try {
		return ResponseEntity.ok(service.getEnergyChargeById(id));
	}catch (Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}

}