package com.ss.oa.transaction.cs;

import org.springframework.web.bind.annotation.RestController;

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
import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.Cs;

@Scope("prototype")
@RestController
public class CsRestService  extends TransactionRestService {


	@Autowired
	private CsService service;
	
	@RequestMapping(value="/css", method = RequestMethod.GET)
	public ResponseEntity<List<Cs>> getAllConsents(HttpServletRequest request,@RequestParam(name="buyer-org-id",required=false) String buyerOrgId,
			@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="code",required=false) String code
	)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(sellerEdcId!=null) {
				criteria.put("seller-edc-id", sellerEdcId);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			return ResponseEntity.ok(service.getAllCs(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/cs/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cs> getCsById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getCsById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/cs", method = RequestMethod.POST)
	public ResponseEntity<String> addCs(@RequestBody Cs cs)
	{	
		String result = "";
		try {
			result = service.addCs(cs);
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
	
	@RequestMapping(value="/cs/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCs(@PathVariable("id")String id,@RequestBody Cs cs)
	{
		String result = "";
		try {
			result = service.updateCs(id, cs);
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
	
	@RequestMapping(value="/cs/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeCs(@PathVariable("id")String id,@RequestBody Cs cs)
	{
		String result = "";
		try {
			result = service.completeCs(id, cs);
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
