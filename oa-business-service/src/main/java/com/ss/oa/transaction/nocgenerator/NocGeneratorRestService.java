package com.ss.oa.transaction.nocgenerator;

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
import com.ss.oa.transaction.vo.NocGenerator;

@Scope("prototype")
@RestController
public class NocGeneratorRestService extends TransactionRestService {
	@Autowired
	NocGeneratorService service;
	
	@RequestMapping(value="/noc-generators",method=RequestMethod.GET)
	public ResponseEntity<List<NocGenerator>> getAllNocGenerators(@RequestParam(name="edc-id",required=false) String edcId,	
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
			@RequestParam(name="code",required=false) String code)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(edcId!=null) {
				criteria.put("edc-id", edcId);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			
			return ResponseEntity.ok(service.getAllNocGenerators(criteria));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/noc-generator/{id}", method=RequestMethod.GET)
	public ResponseEntity<NocGenerator> getNocById(@PathVariable("id") String id){
		try {
			
			return ResponseEntity.ok(service.getNocGeneratorById(id));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/noc-generator", method=RequestMethod.POST)
	public ResponseEntity<String> addNocGenerator(@RequestBody NocGenerator nocGenerator){
		try {
			
			return ResponseEntity.ok(service.addNocGenerator(nocGenerator));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}

	@RequestMapping(value="/noc-generator/{id}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateNocGenerator(@PathVariable("id") String id,@RequestBody NocGenerator nocGenerator){
		try {
			
			return ResponseEntity.ok(service.updateNocGenerator(id,nocGenerator));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/noc-generator/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeNocGenerator(@PathVariable("id")String id,@RequestBody NocGenerator nocGenerator)
	{
		String result = "";
		try {
			result = service.completeNocGenerator(id, nocGenerator);
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
	
	@RequestMapping(value="/noc-generator/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveNocGenerator(@PathVariable("id")String id,@RequestBody NocGenerator nocGenerator)
	{
		String result = "";
		try {
			result = service.approveNocGenerator(id, nocGenerator);
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
