package com.ss.oa.master.traderelationship;


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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oa.common.MasterRestService;
import com.ss.oa.master.vo.TradeRelationship;

@RestController
@Scope("prototype")
public class TradeRelationshipRestService extends  MasterRestService{
	
	@Autowired
	private TradeRelationshipService service;
	
	
	
	
	@RequestMapping(value="/traderelationships", method = RequestMethod.GET)
	public ResponseEntity<List<TradeRelationship>> getTradeRelationship(@RequestParam(name="seller-company-code",required=false) String sellerCompanyCode,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="buyer-edc",required=false) String buyerOrgId,
			@RequestParam(name="seller-edc",required=false) String sellerOrgId,
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompServiceNumber,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompServiceId,
			@RequestParam(name="buyerCompanyCode",required=false) String buyerCompanyCode,
			@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
			@RequestParam(name="buyer-company-service-number",required=false) String buyerCompServiceNumber,
			@RequestParam(name="buyer-company-service-id",required=false) String buyerCompServiceId,
			@RequestParam(name="quantum",required=false) String quantum,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName
			)
	{
		
		try {
		
	      
			Map<String,String> criteria =new HashMap<String,String>();
			if(sellerCompanyCode!=null) {
				criteria.put("sellerCompanyCode",sellerCompanyCode);
			}
			if(buyerOrgId!=null) {
				criteria.put("buyer-edc",buyerOrgId);
			}
			if(sellerOrgId!=null) {
				criteria.put("seller-edc",sellerOrgId);
			}
			if(sellerCompanyName!=null) {
				criteria.put("sellerCompanyName",sellerCompanyName);
			}
			if(sellerCompServiceNumber!=null) {
				criteria.put("sellerCompanyServiceNumber",sellerCompServiceNumber);
			}
			if(sellerCompServiceId!=null) {
				criteria.put("sellerCompanyServiceId",sellerCompServiceId);
			}
			if(buyerCompanyCode!=null) {
				criteria.put("buyerCompanyCode",buyerCompanyCode);
			}
			if(buyerCompanyName!=null) {
				criteria.put("buyerCompanyName",buyerCompanyName);
			}
			if(buyerCompServiceNumber!=null) {
				criteria.put("buyerCompanyServiceNumber",buyerCompServiceNumber);
			}
			if(buyerCompServiceId!=null) {
				criteria.put("buyerCompanyServiceId",buyerCompServiceId);
			}
			if(quantum!=null) {
				criteria.put("quantum",quantum);
			}
			if(month!=null) {
				criteria.put("month",month);
			}
			if(year!=null) {
				criteria.put("year",year);
			}
			
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("sellerCompanyCode")) {
						System.out.println("criteria seller_company_code -->"+element.getValue()+"");
						
					}
				}
				
			} 
	
			
			return ResponseEntity.ok(service.getAllTradeRelationships(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	
	}
	
	@RequestMapping(value="/traderelationship/{id}", method = RequestMethod.GET)
	public ResponseEntity<TradeRelationship> getTradeRelationshipById(@PathVariable("id")String tradeRelationshipId)
	{
		try {
			return ResponseEntity.ok(service.getTradeRelationshipById(tradeRelationshipId));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	
	@RequestMapping(value="/traderelationship", method = RequestMethod.POST)
	public ResponseEntity<String> addTradeRelationship(@RequestBody TradeRelationship tradeRelationship)
	{
		String result = "";
		try {
			result = service.addTradeRelationship(tradeRelationship);
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
	
	@RequestMapping(value="/traderelationship/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateTradeRelationship(@PathVariable("id")String tradeRelationshipId,@RequestBody TradeRelationship tradeRelationship)
	{
		String result = "";
		try {
			result = service.updateTradeRelationship(tradeRelationshipId,tradeRelationship);
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
	
	@RequestMapping(value="/traderelationship/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTradeRelationship(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deleteTradeRelationship(id);
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
