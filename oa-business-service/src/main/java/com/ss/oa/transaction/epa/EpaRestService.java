package com.ss.oa.transaction.epa;

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
import com.ss.oa.transaction.vo.Epa;

@Scope("prototype")
@RestController
public class EpaRestService extends TransactionRestService {
	
	@Autowired
	EpaService service;
	
	@RequestMapping(value="/epas", method = RequestMethod.GET)
	public ResponseEntity<List<Epa>> getAllEpas(@RequestParam(name="seller-org-id",required=false) String sellerOrgId,			
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompServiceNumber,	
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompServiceId,		
			@RequestParam(name="es-intent-id",required=false) String tEsIntentId,
			@RequestParam(name="agm-date",required=false) String agreementDate,
			@RequestParam(name="agm-period-code",required=false) String agreementPeriodCode,
			@RequestParam(name="agreement-number",required=false) String agreementNumber,
			@RequestParam(name="status",required=false) String statusCode,
			@RequestParam(name="code",required=false) String code,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="fuel-type-code",required=false) String fuelTypeCode,
			@RequestParam(name="fuel-type-name",required=false) String fuelTypeName,
			@RequestParam(name="fuel-group",required=false) String fuelGroupe)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(sellerOrgId!=null) {
				criteria.put("seller-org-id", sellerOrgId);
			}
			
			if(sellerCompServiceNumber!=null) {
				criteria.put("seller-company-service-number", sellerCompServiceNumber);
			}
			
			if(tEsIntentId!=null) {
				criteria.put("es-intent-id", tEsIntentId);
			}
			if(agreementDate!=null) {
				criteria.put("agm-date", agreementDate);
			}
			if(agreementPeriodCode!=null) {
				criteria.put("agm-period-code", agreementPeriodCode);
			}
			if(agreementNumber!=null) {
				criteria.put("agreement-number", agreementNumber);
			}
			if(statusCode!=null) {
				criteria.put("status", statusCode);
				
			}
			if(code!=null) {
				criteria.put("code", code);
				
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
				
			}
			if(sellerCompServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompServiceId);
			}
			if(fuelTypeCode!=null) {
				criteria.put("fuel-type-code", fuelTypeCode);
			}
			if(fuelTypeName!=null) {
				criteria.put("fuel-type-name", fuelTypeName);
			}
			if(fuelGroupe!=null) {
				criteria.put("fuel-group", fuelGroupe);
			}

			
			return ResponseEntity.ok(service.getAllEpas(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/epa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Epa> getepaById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getEpaById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/epa", method = RequestMethod.POST)
	public ResponseEntity<String> addEpa(@RequestBody Epa epa)
	{	
		String result = "";
		try {
			result = service.addEpa(epa);
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
	
	@RequestMapping(value="/epa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEpa(@PathVariable("id")String id,@RequestBody Epa epa)
	{
		String result = "";
		try {
			result = service.updateEpa(id,epa);
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
	
	@RequestMapping(value="/epa/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveEpa(@PathVariable("id")String id,@RequestBody Epa epa)
	{
		String result = "";
		try {
			result = service.approveEpa(id, epa);
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
	
	@RequestMapping(value="/epa/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeEpa(@PathVariable("id")String id,@RequestBody Epa epa)
	{
		String result = "";
		try {
			result = service.completeEpa(id, epa);
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
