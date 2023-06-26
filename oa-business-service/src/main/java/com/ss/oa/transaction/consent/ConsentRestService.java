package com.ss.oa.transaction.consent;

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
import com.ss.oa.transaction.vo.Consent;



@Scope("prototype")
@RestController
public class ConsentRestService extends TransactionRestService {

	@Autowired
	private ConsentService service;


	@RequestMapping(value="/consents", method = RequestMethod.GET)
	public ResponseEntity<List<Consent>> getAllConsents(@RequestParam(name="buyer-org-id",required=false) String buyerOrgId,			
			@RequestParam(name="buyer-company-service-number",required=false) String buyerCompanyServiceNumber,
			@RequestParam(name="buyer-company-service-id",required=false) String buyerCompanyServiceId,	
			@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
			@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
			@RequestParam(name="status",required=false) String status,
//			@RequestParam(name="buyerVoltageCode",required=false) String buyerVoltageCode,
			@RequestParam(name="from-date",required=false) String fromDate,
			@RequestParam(name="to-date",required=false) String toDate,
			@RequestParam(name="drawal-voltage-code",required=false) String drawalVoltageCode,
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,	
			@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="consent-code",required=false) String consentCode)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(buyerOrgId!=null) {
				criteria.put("buyer-org-id", buyerOrgId);
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
//			if(buyerVoltageCode!=null) {
//				criteria.put("voltage-code", buyerVoltageCode);
//			}
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
			if(consentCode!=null) {
				criteria.put("consent-code", consentCode);
			}
			
			return ResponseEntity.ok(service.getAllConsents(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/consent/{id}", method = RequestMethod.GET)
	public ResponseEntity<Consent> getConsentById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getConsentById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/consent", method = RequestMethod.POST)
	public ResponseEntity<String> addConsent(@RequestBody Consent consent)
	{	
		String result = "";
		try {
			result = service.addConsent(consent);
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
	
	@RequestMapping(value="/consent/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateConsent(@PathVariable("id")String id,@RequestBody  Consent consent)
	{
		String result = "";
		try {
			result = service.updateConsent(id,consent);
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
	
	@RequestMapping(value="/consent/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeConsent(@PathVariable("id")String conId,@RequestBody Consent consent)
	{
		String result = "";
		try {
			result = service.completeConsent(conId, consent);
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
	
	@RequestMapping(value="/consent/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveConsent(@PathVariable("id")String conId,@RequestBody Consent consent)
	{
		String result = "";
		try {
			result = service.approveConsent(conId, consent);
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
