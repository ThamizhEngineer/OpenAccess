package com.ss.oa.transaction.standingclearence;

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
import com.ss.oa.transaction.vo.StandingClearence;

@Scope("prototype")
@RestController
public class StandingClearenceRestService extends TransactionRestService{

	@Autowired
	StandingClearenceService service;
	
	@RequestMapping(value="/standingclearences",method=RequestMethod.GET)
	public ResponseEntity<List<StandingClearence>> getAllStandingClearences(@RequestParam(name="buyer-comp-serv-id",required=false) String buyerCompServId,
			@RequestParam(name="sldc-name",required=false) String sldcName,
			@RequestParam(name="code",required=false) String code,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode)	
		
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(buyerCompServId!=null) {
				criteria.put("buyer-comp-serv-id", buyerCompServId);
			}
			if(sldcName!=null) {
				criteria.put("sldc-name", sldcName);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			{
			
		     	return ResponseEntity.ok(service.getAllStandingClearences(criteria));
				
			}    
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/standingclearence/{id}", method=RequestMethod.GET)
	public ResponseEntity<StandingClearence> getStandingClearenceById(@PathVariable("id") String id){
		try {
			
			return ResponseEntity.ok(service.getStandingClearenceById(id));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/standingclearence", method=RequestMethod.POST)
	public ResponseEntity<String> addStandingClearence(@RequestBody StandingClearence standingClearence){
		try {
			
			return ResponseEntity.ok(service.addStandingClearence(standingClearence));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}

	@RequestMapping(value="/standingclearence/{id}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateStandingClearence(@PathVariable("id") String id,@RequestBody StandingClearence standingClearence){
		try {
			
			return ResponseEntity.ok(service.updateStandingClearence(id,standingClearence));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/standingclearence/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeStandingClearence(@PathVariable("id")String id,@RequestBody StandingClearence standingclearence)
	{
		String result = "";
		try {
			result = service.completeStandingClearence(id, standingclearence);
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
	
	@RequestMapping(value="/standingclearence/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveStandingClearence(@PathVariable("id")String id,@RequestBody StandingClearence standingclearence)
	{
		String result = "";
		try {
			result = service.approveStandingClearence(id, standingclearence);
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
	
	@RequestMapping(value="/standingclearence/{id}/approveipasc", method = RequestMethod.PUT)
	public StandingClearence approveIpaStandingClearence(@PathVariable("id")String id,@RequestBody StandingClearence standingclearence){
		return service.approveIpaStandingClearence(standingclearence);
	}
	
}
