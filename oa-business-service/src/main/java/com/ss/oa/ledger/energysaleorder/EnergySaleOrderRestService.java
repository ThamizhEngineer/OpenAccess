package com.ss.oa.ledger.energysaleorder;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.LedgerRestService;
import com.ss.oa.ledger.vo.EnergySaleOrder;
import com.ss.oashared.common.CommonUtils;



@RestController
@Scope("prototype")
public class EnergySaleOrderRestService extends LedgerRestService {
	@Autowired
	EnergySaleOrderService service;
	
	@Autowired
	CommonUtils commonUtils;
	
	@RequestMapping(value="/energysaleorders", method = RequestMethod.GET)
	public ResponseEntity<List<EnergySaleOrder>> getEnergyLedgers(@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="org-code",required=false) String orgCode,			
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="company-id",required=false) String companyId,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="company-service-id",required=false) String companyServiceId,
			@RequestParam(name="company-service-number",required=false) String companyServiceNumber,
			@RequestParam(name="fuelTypeCode",required=false) String fuelTypeCode,
			@RequestParam(name="fuelTypeName",required=false) String fuelTypeName,
			@RequestParam(name="fuelGroupe",required=false) String fuelGroupe)
	{
		
		try {
									
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			if(orgCode!=null) {
				criteria.put("org-code",orgCode);
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
			if(companyName!=null) {
				criteria.put("company-name",companyName);
			}
			if(companyServiceId!=null) {
				criteria.put("company-service-id",companyServiceId);
			}
			if(companyServiceNumber!=null) {
				criteria.put("company-service-number",companyServiceNumber);
			}
			if(fuelTypeCode!=null) {
				criteria.put("fuelTypeCode",fuelTypeCode);
			}
			if(fuelTypeName!=null) {
				criteria.put("fuelTypeName",fuelTypeName);
			}
			if(fuelGroupe!=null) {
				criteria.put("fuelGroupe",fuelGroupe);
			}
			
	
			
			return ResponseEntity.ok(service.getAllEnergySaleOrders(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	@RequestMapping(value="/energysaleordersbuyers", method = RequestMethod.GET)
	public ResponseEntity<List<EnergySaleOrder>> getEnergyLedgersBuyers(@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="company-service-number",required=false) String companyServiceNumber,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="fuelTypeCode",required=false) String fuelTypeCode)
	{
		
		try {
									
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}		
			if(companyServiceNumber!=null) {
				criteria.put("company-service-number",companyServiceNumber);
			}
			if(month!=null) {
				criteria.put("month",month);
			}
			if(year!=null) {
				criteria.put("year",year);
			} 
			
			if(fuelTypeCode!=null) {
				criteria.put("fuelTypeCode",fuelTypeCode);
			}
			
	
			
			return ResponseEntity.ok(service.getAllEnergySaleOrdersBuyers(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	@RequestMapping(value="/energysaleorder/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnergySaleOrder> getEnergyChargeId(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getEnergySaleOrderById(id));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energysaleorder", method = RequestMethod.POST)
	public ResponseEntity<String> addEnergyCharge(@RequestBody EnergySaleOrder energySaleOrder )
	{
		try {
			return ResponseEntity.ok(service.addEnergySaleOrder(energySaleOrder));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energysaleorder/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEnergyCharge(@PathVariable("id")String id,@RequestBody EnergySaleOrder energySaleOrder)
	{
		try {
			return ResponseEntity.ok(service.updateEnergySaleOrder(id, energySaleOrder));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/energysaleorder/{id}/print", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody>
  printEnergyAllotmentOrderReport(@PathVariable("id") String id)
			throws FileNotFoundException {
		System.out.println(id);
		return  commonUtils.fetchFileAsStreamResponse(service.printEnergyAllotmentOrderReport(id));


	}
	
	@RequestMapping(value = "/energysaleorder/{id}/_internal", method = RequestMethod.GET)
	public ResponseEntity<EnergySaleOrder> getEnergyChargeIdInternal(@PathVariable("id") String id) {
			return getEnergyChargeId(id);
	}
}
