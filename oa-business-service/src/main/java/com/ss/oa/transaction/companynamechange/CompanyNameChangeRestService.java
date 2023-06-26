package com.ss.oa.transaction.companynamechange;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.CompanyNameChange;


@Scope("prototype")
@RestController
public class CompanyNameChangeRestService extends TransactionRestService {
	
	@Autowired
	private CompanyNameChangeService service;
	

	@RequestMapping(value="/companynamechanges", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyNameChange>> getAllCompanyNameChanges(HttpServletRequest request,
			@RequestParam(name="edc-id",required=false) String edcId,
			@RequestParam(name="company-service-id",required=false) String companyServiceId ,
			@RequestParam(name="code",required=false) String code
	    )		
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(edcId!=null) {
				criteria.put("edc-id", edcId);
			}
			
			if(companyServiceId!=null) {
				criteria.put("company-service-id", companyServiceId);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
					return ResponseEntity.ok(service.getAllCompanyNameChanges(criteria));
			
				}catch(Exception e) {
					e.printStackTrace();
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
				}
			}
			


@RequestMapping(value="/companynamechange/{id}", method = RequestMethod.GET)
public ResponseEntity<CompanyNameChange> getCompanyNameChangeById(@PathVariable("id") String id)
{
	try {			
		
		return ResponseEntity.ok(service.getCompanyNameChangeById(id))   ;
	
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}

@RequestMapping(value="/companynamechange", method = RequestMethod.POST)
public ResponseEntity<String> addCompanyNameChange(@RequestBody CompanyNameChange companyNameChange)
{	
	String result = "";
	try {
		result = service.addCompanyNameChange(companyNameChange);
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

@RequestMapping(value="/companynamechange/{id}", method = RequestMethod.PUT)
public ResponseEntity<String> updateCompanyNameChange(@PathVariable("id")String id,@RequestBody  CompanyNameChange companyNameChange)
{
	String result = "";
	try {
		result = service.updateCompanyNameChange(id,companyNameChange);
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


@RequestMapping(value="/companynamechange/{id}/approve", method = RequestMethod.PUT)
public ResponseEntity<String> approveCompanyNameChange(@PathVariable("id")String id,@RequestBody CompanyNameChange companyNameChange)
{
	String result = "";
	try {
		result = service.approveCompanyNameChange(id, companyNameChange);
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
