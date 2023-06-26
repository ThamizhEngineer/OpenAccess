package com.ss.oa.transaction.agreement;

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
import com.ss.oa.transaction.vo.Agreement;

@Scope("prototype")
@RestController
public class AgreementRestService  extends TransactionRestService {
	

	@Autowired
	private AgreementService service;
	
	@RequestMapping(value="/agreements", method = RequestMethod.GET)
	public ResponseEntity<List<Agreement>> getAllConsents(HttpServletRequest request,@RequestParam(name="buyer-org-id",required=false) String buyerOrgId,
			@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
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
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			return ResponseEntity.ok(service.getAllAgreements(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/agreement/{id}", method = RequestMethod.GET)
	public ResponseEntity<Agreement> getAgreementById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getAgreementById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/agreement", method = RequestMethod.POST)
	public ResponseEntity<String> addAgreement(@RequestBody Agreement agreement)
	{	
		String result = "";
		try {
			result = service.addAgreement(agreement);
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
	
	@RequestMapping(value="/agreement/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAgreement(@PathVariable("id")String id,@RequestBody Agreement agreement)
	{
		String result = "";
		try {
			result = service.updateAgreement(id, agreement);
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
	
	@RequestMapping(value="/agreement/{id}/signed", method = RequestMethod.PUT)
	public ResponseEntity<String> agreementSigned(@PathVariable("id")String id,@RequestBody Agreement agreement)
	{
		String result = "";
		try {
			result = service.agreementSigned(id, agreement);
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
	
	@RequestMapping(value="/agreement/{id}/cancelled", method = RequestMethod.PUT)
	public ResponseEntity<String> agreementCancelled(@PathVariable("id")String id,@RequestBody Agreement agreement)
	{
		String result = "";
		try {
			result = service.agreementCancelled(id, agreement);
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
