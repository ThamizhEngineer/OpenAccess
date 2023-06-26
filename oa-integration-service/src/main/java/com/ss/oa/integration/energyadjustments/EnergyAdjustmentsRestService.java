package com.ss.oa.integration.energyadjustments;
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
import com.ss.oa.vo.EnergyAdjustment;

@RestController
public class EnergyAdjustmentsRestService extends IntegrationRestService {
	@Autowired
	private EnergyAdjustmentService service;
	@RequestMapping(value="/energy-adjustments", method = RequestMethod.GET)
	public ResponseEntity <List<EnergyAdjustment>> getEnergyAdjustments(@RequestParam(name="service-number",required=false) String serviceNumber,
															@RequestParam(name="billing-month",required=false) String billingMonth,
															@RequestParam(name="billing-year",required=false) String billingYear,
															@RequestParam(name="org-code",required=false) String orgCode)
	{
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(serviceNumber!=null)
				criteria.put("serviceNumber",serviceNumber);
			if(billingMonth!=null)
					criteria.put("billingMonth",billingMonth);
			if(billingYear!=null)
				criteria.put("billingYear",billingYear);
			if(orgCode!=null)
				criteria.put("orgCode",orgCode);
//			System.out.println(criteria);
			return ResponseEntity.ok(service.getAllEnergyAdjustments(criteria));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}

}
