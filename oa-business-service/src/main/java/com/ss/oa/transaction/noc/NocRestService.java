package com.ss.oa.transaction.noc;

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

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.Noc;

@Scope("prototype")
@RestController
public class NocRestService extends TransactionRestService {
	@Autowired
	NocService service;
	
	@RequestMapping(value="/nocs",method=RequestMethod.GET)
	public ResponseEntity<List<Noc>> getAllNocs(@RequestParam(name="buyer-edc-id",required=false) String buyerEdcId,
			@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="buyer-company-service-number",required=false) String buyerCompanyServiceNumber,	
			@RequestParam(name="buyer-company-service-id",required=false) String buyerCompanyServiceId,	
			@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
			@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="buyer-voltage-code",required=false) String buyerVoltageCode,
			@RequestParam(name="from-date",required=false) String fromDate,
			@RequestParam(name="to-date",required=false) String toDate,
			@RequestParam(name="drawal-voltage-code",required=false) String drawalVoltageCode,
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,	
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,	
			@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="noc-code",required=false) String nocCode)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(buyerEdcId!=null) {
				criteria.put("buyer-edc-id", buyerEdcId);
			}
			if(sellerEdcId!=null) {
				criteria.put("seller-edc-id", sellerEdcId);
			}
			if(buyerCompanyServiceNumber!=null) {
				criteria.put("buyer-company-service-number", buyerCompanyServiceNumber);
			}
			if(buyerCompanyServiceId!=null) {
				criteria.put("buyer-company-service-id", buyerCompanyServiceId);
			}
			
			if(buyerCompanyId!=null) {
				criteria.put("buyer-company-id", buyerCompanyId);
			}
			if(buyerCompanyName!=null) {
				criteria.put("buyer-company-name", buyerCompanyName);
			}
			if(buyerVoltageCode!=null) {
				criteria.put("voltage-code", buyerVoltageCode);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			if(fromDate!=null) {
				criteria.put("from-date", fromDate);
			}
			if(toDate!=null) {
				criteria.put("to-date", toDate);
			}
			if(drawalVoltageCode!=null) {
				criteria.put("drawal-voltage-code", drawalVoltageCode);
			}
			
			if(sellerCompanyServiceNumber!=null) {
				criteria.put("seller-company-service-number", sellerCompanyServiceNumber);
			}
			
			if(sellerCompanyId!=null) {
				criteria.put("seller-company-id", sellerCompanyId);
			}
			if(sellerCompanyName!=null) {
				criteria.put("seller-company-name", sellerCompanyName);
			}
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(nocCode!=null) {
				criteria.put("noc-code", nocCode);
			}
			
			return ResponseEntity.ok(service.getAllNocs(criteria));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/noc/{id}", method=RequestMethod.GET)
	public ResponseEntity<Noc> getNocById(@PathVariable("id") String id){
		try {
			
			return ResponseEntity.ok(service.getNocById(id));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/noc", method=RequestMethod.POST)
	public ResponseEntity<String> addNoc(@RequestBody Noc noc){
		try {
			
			return ResponseEntity.ok(service.addNoc(noc));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}

	@RequestMapping(value="/noc/{id}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateNoc(@PathVariable("id") String id,@RequestBody Noc noc){
		try {
			
			return ResponseEntity.ok(service.updateNoc(id,noc));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/noc/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeNoc(@PathVariable("id")String id,@RequestBody Noc aggrementNoc)
	{
		String result = "";
		try {
			result = service.completeNoc(id, aggrementNoc);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result =  "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}
	
	@RequestMapping(value="/noc/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveNoc(@PathVariable("id")String id,@RequestBody Noc noc)
	{
		String result = "";
		try {
			result = service.approveNoc(id, noc);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result =  "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}
}
