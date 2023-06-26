package com.ss.oa.transaction.bankingbalance;

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
import com.ss.oa.transaction.vo.BankingBalance;

@Scope("prototype")
@RestController
public class BankingBalanceRestService extends TransactionRestService{
	
	@Autowired
	private BankingBalanceService service;
	
	
	@RequestMapping(value="/bankingbalances", method = RequestMethod.GET)
	public ResponseEntity<List<BankingBalance>> getAllBankingBalances(@RequestParam(name="seller-org-id",required=false) String sellerOrgId,			
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,			
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,	
			@RequestParam(name="company-id",required=false) String bankingCompanyId,
			@RequestParam(name="banking-service-number",required=false) String bankingServiceNumber,
			@RequestParam(name="created-date",required=false) String createdDate,
			@RequestParam(name="modified-date",required=false) String modifiedDate,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(sellerOrgId!=null) {
				criteria.put("seller-org-id", sellerOrgId);
			}
			
			if(sellerCompanyServiceNumber!=null) {
				criteria.put("seller-company-service-number", sellerCompanyServiceNumber);
			}
			
			if(bankingCompanyId!=null) {
				criteria.put("company-id", bankingCompanyId);
			}

			if(bankingServiceNumber!=null) {
				criteria.put("banking-service-number", bankingServiceNumber);
			}
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			if(createdDate!=null) {
				criteria.put("created-date", createdDate);
			}
			if(modifiedDate!=null) {
				criteria.put("modified-date", modifiedDate);
			}
			if(month!=null) {
				criteria.put("month", month);
			}
			if(year!=null) {
				criteria.put("year", year);
			}

			
			return ResponseEntity.ok(service.getAllBankingBalances(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/bankingbalance/{id}", method = RequestMethod.GET)
	public ResponseEntity<BankingBalance> getBankingBalancesById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getBankingBalancesById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/bankingbalance", method = RequestMethod.POST)
	public ResponseEntity<String> addBankingBalance(@RequestBody BankingBalance bankingBalance)
	{	
		String result = "";
		try {
			result = service.addBankingBalance(bankingBalance);
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
	
	@RequestMapping(value="/bankingbalance/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBankingBalance(@PathVariable("id")String id,@RequestBody  BankingBalance bankingBalance)
	{
		String result = "";
		try {
			result = service.updateBankingBalance(id,bankingBalance);
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

	@RequestMapping(value="/bankingbalances/_internal", method = RequestMethod.GET)
	public ResponseEntity<List<BankingBalance>> getBankingBalancesForOrderPrint(@RequestParam(name="seller-org-id",required=false) String sellerOrgId,			
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,			
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,	
			@RequestParam(name="company-id",required=false) String bankingCompanyId,
			@RequestParam(name="banking-service-number",required=false) String bankingServiceNumber,
			@RequestParam(name="created-date",required=false) String createdDate,
			@RequestParam(name="modified-date",required=false) String modifiedDate,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year){
  
		return getAllBankingBalances(sellerOrgId, sellerCompanyServiceNumber, sellerCompanyServiceId, 
				bankingCompanyId, bankingServiceNumber, createdDate, modifiedDate, month, year);
	}
}
