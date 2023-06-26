package com.ss.oa.transaction.energysaleintent;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.Response;
import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.EnergySaleIntent;

@Scope("prototype")
@RestController
public class EnergySaleIntentRestService extends TransactionRestService{
	@Autowired
	EnergySaleIntentService service;
	
	@RequestMapping(value="/energysaleintents",method=RequestMethod.GET)
	public ResponseEntity<List<EnergySaleIntent>> getAllEnergySaleIntents(@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
																		@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,
																		@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
																		@RequestParam(name="buyer-company-service-number",required=false) String buyerCompanyServiceNumber,
																		@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
																		@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
																		@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
																		@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
																		@RequestParam(name="status",required=false) String status,
																		@RequestParam(name="period",required=false) String period,
																		@RequestParam(name="es-intent-code",required=false) String esIntentCode,
																		@RequestParam(name="flow-type-code",required=false) String flowTypeCode){
		
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			//introduced buyer company id,name ,service number for search option but did not implement it for simplicity
			if(sellerEdcId!=null) {
				criteria.put("seller-edc-id", sellerEdcId);
			}
			
			if(sellerCompanyServiceNumber!=null) {
				criteria.put("seller-company-service-number", sellerCompanyServiceNumber);
			}
			if(buyerCompanyServiceNumber!=null) {
				criteria.put("buyer-company-service-number", buyerCompanyServiceNumber);
			}
			if(sellerCompanyId!=null) {
				criteria.put("seller-company-id", sellerCompanyId);
			}
			if(sellerCompanyName!=null) {
				criteria.put("seller-company-name", sellerCompanyName);
			}
			if(buyerCompanyId!=null) {
				criteria.put("buyer-company-id", buyerCompanyId);
			}
			if(buyerCompanyName!=null) {
				criteria.put("buyer-company-name", buyerCompanyName);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			if(period!=null) {
				criteria.put("period", period);
			}
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(flowTypeCode!=null) {
				criteria.put("flow-type-code", flowTypeCode);
			}
			
			System.out.println(criteria.get(buyerCompanyId));
			return ResponseEntity.ok(service.getAllEnergySaleIntents(criteria));

			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/energysaleintent/{id}", method=RequestMethod.GET)
	public ResponseEntity<EnergySaleIntent> getEnergySaleIntentById(@PathVariable("id") String id){
		try {
			return ResponseEntity.ok(service.getEnergySaleIntentById(id));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
		
	}
	
	@RequestMapping(value="/energysaleintent",method=RequestMethod.POST)
	public ResponseEntity<Response> addEnergySaleIntent(@RequestBody EnergySaleIntent energySaleIntent){
		try {
			System.out.println("Rest Service for ESINTENT (ADD)");
			System.out.println(energySaleIntent);
			return ResponseEntity.ok(service.addEnergySaleIntent(energySaleIntent));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
			
			
		}
	}
	@RequestMapping(value="/energysaleintent/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateEnergySaleIntent(@PathVariable("id")String id,@RequestBody EnergySaleIntent energySaleIntent){
		try {
			return ResponseEntity.ok(service.updateEnergySaleIntent(id,energySaleIntent));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	@RequestMapping(value="/energysaleintent/{id}/process",method=RequestMethod.PUT)
	public ResponseEntity<String> processEnergySaleIntent(@PathVariable("id")String id){
		try {
			return ResponseEntity.ok(service.processEnergySaleIntent(id));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}

	@RequestMapping(value="/energysaleintent/{id}/{status}",method=RequestMethod.PUT)
	public ResponseEntity<String> sldcApprovalEnergySaleIntent(@PathVariable("id")String id,
			@PathVariable("status")String status,@RequestBody EnergySaleIntent energySaleIntent){
		try {
			return ResponseEntity.ok(service.sldcApprovalEnergySaleIntent(id,status,energySaleIntent));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
}
