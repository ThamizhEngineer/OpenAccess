package com.ss.oa.master.signup;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.ss.oa.common.MasterRestService;
import com.ss.oa.master.vo.Signup;

@RestController
@Scope("prototype")
public class SignupRestService extends MasterRestService{
	
	@Autowired
	private SignupService service;
	@RequestMapping(value="/signups", method = RequestMethod.GET)
	public ResponseEntity<List<Signup>> getSignup(@RequestParam(name="company-name",required=false) String companyName,	
			@RequestParam(name="htsc-number",required=false) String htscNumber,	
			@RequestParam(name="htsc-number-old",required=false) String htscNumberOld,
			@RequestParam(name="is-captive",required=false) String isCaptive,
			@RequestParam(name="is-complete",required=false) String isComplete,
			@RequestParam(name="meter-number",required=false) String meterNumber,			
			@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="limit",required=false,defaultValue="100") String limit,
			@RequestParam(name="purpose-code",required=false) String purposeCode,
			@RequestParam(name="remarks",required=false) String remarks,
			@RequestParam(name="surplus-energy-code",required=false) String surplusEnergyCode)
	
	
	{
		
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(companyName!=null) {
				criteria.put("company-name",companyName);
			}
		
			if(htscNumber!=null) {
				criteria.put("htsc-number",htscNumber);
			}
			
			if(htscNumberOld!=null) {
				criteria.put("htsc-number-old",htscNumberOld);
			}
			
			if(isCaptive!=null) {
				criteria.put("is-captive",isCaptive);
			}
			if(isComplete!=null) {
				criteria.put("is-complete",isComplete);
			}
			if(meterNumber!=null) {
				criteria.put("meter-number",meterNumber);
			}
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			
			if(isComplete!=null) {
				criteria.put("isComplete",isComplete);
			}
			
			if(remarks!=null) {
				criteria.put("remarks",remarks);
			}
			
			if(purposeCode!=null) {
				criteria.put("purpose-code",purposeCode);
			}
			if(surplusEnergyCode!=null) {
				criteria.put("surplus-energy-code",surplusEnergyCode);
			}
			if(limit!=null) {
				criteria.put("limit",limit);
			}
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("companyName")) {
						System.out.println("criteria company_name -->"+element.getValue()+"");
						
					}
				}
				
			} 
	
			//return ResponseEntity.ok(signups);
			return ResponseEntity.ok(service.getAllSignup(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	
	}
	
	@RequestMapping(value="/signup/{id}", method = RequestMethod.GET)
	public ResponseEntity<Signup> getSignupById(@PathVariable("id")String signupId)
	{
		try {
			return ResponseEntity.ok(service.getSignupById(signupId));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<String> addSignup(@RequestBody Signup signup)
	{
		String result = "";
		try {
			result = service.addSignup(signup);
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
	
	@RequestMapping(value="/signup/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSignup(@PathVariable("id")String signupId,@RequestBody Signup signup)
	{
		String result = "";
		try {
			result = service.updateSignup(signupId,signup);
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
	
	@RequestMapping(value="/signup/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeSignup(@PathVariable("id")String signupId,@RequestBody Signup signup)
	{
		String result = "";
		try {
			result = service.completeSignup(signupId,signup);
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
	
	@RequestMapping(value="/signups/complete", method = RequestMethod.GET)
	public ResponseEntity<String> completeManySignups(@RequestParam(name="remarks",required=false) String remarks)
	{
		String result = "";
		try {
			result = service.completeManySignups(remarks);
			if(result.matches("FAILURE")){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			}
			else{
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result =  "FAILURE - "+e.getMessage();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}
}
