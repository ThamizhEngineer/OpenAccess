package com.ss.oa.transaction.companymeterchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.TransactionRestService;


import com.ss.oa.transaction.vo.CompanyMeterChange;

@Scope("prototype")
@RestController
public class CompanyMeterChangeRestService extends TransactionRestService {
	
	@Autowired
	private CompanyMeterChangeService service;
	
	
	
	@RequestMapping(value="/companymeterchanges", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyMeterChange>> getAllCompanyMeterChanges(HttpServletRequest request,
			@RequestParam(name="edc-id",required=false) String edcId,
			@RequestParam(name="company-service-id",required=false) String companyServiceId,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="code",required=false) String code,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName
		
	)
	{
		try {
				
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(edcId!=null) {
				criteria.put("edc-id", edcId);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			
			if(companyServiceId!=null) {
				criteria.put("company-service-id", companyServiceId);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			return ResponseEntity.ok(service.getAllCompanyMeterChanges(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/companymeterchange/{id}", method = RequestMethod.GET)
	public ResponseEntity<CompanyMeterChange> getCompanyMeterChangeById(@PathVariable("id") String id,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName,
			@RequestHeader(name="token",required=false) String token)
	{
		try {			
			
			return ResponseEntity.ok(service.getCompanyMeterChangeById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/companymeterchange", method = RequestMethod.POST)
	public ResponseEntity<String> addCompanyMeterChange(@RequestBody CompanyMeterChange companyMeterChange,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName,
			@RequestHeader(name="token",required=false) String token)
	{	
		String result = "";
		try {
				
			result = service.addCompanyMeterChange(companyMeterChange);
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
	
	@RequestMapping(value="/companymeterchange/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCompanyMeterChange(@PathVariable("id")String id,@RequestBody CompanyMeterChange companyMeterChange,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName,
			@RequestHeader(name="token",required=false) String token)
	{
		String result = "";
		try {
			
			result = service.updateCompanyMeterChange(companyMeterChange);
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
	
	@RequestMapping(value="/companymeterchange/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveCompanyMeterChange(@PathVariable("id")String id,@RequestBody CompanyMeterChange companyMeterChange,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName,
			@RequestHeader(name="token",required=false) String token)
	{
		String result = "";
		try {
			
			result = service.approveCompanyMeterChange(companyMeterChange);
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
