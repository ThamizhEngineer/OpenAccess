package com.ss.oa.transaction.oaa;

import org.springframework.web.bind.annotation.RestController;

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

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.Oaa;


@Scope("prototype")
@RestController
public class OaaRestService extends TransactionRestService{

	@Autowired
	private OaaService service;


	@RequestMapping(value="/oaas", method = RequestMethod.GET)
	public ResponseEntity<List<Oaa>> getAllOaas(@RequestParam(name="buyer-org-id",required=false) String buyerOrgId,			
			@RequestParam(name="buyer-company-service-number",required=false) String buyerCompServiceNumber,
			@RequestParam(name="buyer-company-service-id",required=false) String buyerCompServiceId,
			@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
			@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
			@RequestParam(name="seller-org-id",required=false) String sellerOrgId,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompServiceId,
			@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
			@RequestParam(name="status",required=false) String status,
//			@RequestParam(name="buyerVoltageCode",required=false) String buyerVoltageCode,
			@RequestParam(name="from-date",required=false) String FromDate,
			@RequestParam(name="to-date",required=false) String ToDate,
			@RequestParam(name="code",required=false) String code,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode
			)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(buyerOrgId!=null) {
				criteria.put("buyer-org-id", buyerOrgId);
			}
			
			if(buyerCompServiceNumber!=null) {
				criteria.put("buyer-company-service-number", buyerCompServiceNumber);
			}
			if(buyerCompServiceId!=null) {
				criteria.put("buyer-company-service-id", buyerCompServiceId);
			}
			
			if(buyerCompanyId!=null) {
				criteria.put("buyer-company-id", buyerCompanyId);
			}
			if(buyerCompanyName!=null) {
				criteria.put("buyer-company-name", buyerCompanyName);
			}
			if(sellerOrgId!=null) {
				criteria.put("seller-org-id", sellerOrgId);
			}
			if(sellerCompanyName!=null) {
				criteria.put("seller-company-name", sellerCompanyName);
			}
			if(sellerCompServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompServiceId);
			}
			if(sellerCompanyId!=null) {
				criteria.put("seller-company-id", sellerCompanyId);
			}		
//			if(buyerVoltageCode!=null) {
//				criteria.put("voltage-code", buyerVoltageCode);
//			}
			if(status!=null) {
				criteria.put("status", status);
			}
			if(FromDate!=null) {
				criteria.put("from-date", FromDate);
			}
			if(ToDate!=null) {
				criteria.put("to-date", ToDate);
			}
			if(code!=null) {
				criteria.put("code", code);
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			
			return ResponseEntity.ok(service.getAllOaa(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/oaa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Oaa> getOaaById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getOaaById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/oaa", method = RequestMethod.POST)
	public ResponseEntity<String> addOaa(@RequestBody Oaa oaa)
	{	
		String result = "";
		try {
			result = service.addOaa(oaa);
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
	
	@RequestMapping(value="/oaa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateOaa(@PathVariable("id")String id,@RequestBody  Oaa oaa)
	{
		String result = "";
		try {
			result = service.updateOaa(id,oaa);
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
	
	@RequestMapping(value="/oaa/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeOaa(@PathVariable("id")String oaaId,@RequestBody Oaa oaa)
	{
		String result = "";
		try {
			result = service.completeOaa(oaaId, oaa);
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

	@RequestMapping(value="/oaa/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveOaa(@PathVariable("id")String oaaId,@RequestBody Oaa oaa)
	{
		String result = "";
		try {
			result = service.approveOaa(oaaId, oaa);
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
