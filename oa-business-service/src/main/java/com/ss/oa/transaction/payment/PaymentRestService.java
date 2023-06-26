package com.ss.oa.transaction.payment;

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


import com.ss.oa.transaction.vo.Payment;

@Scope("prototype")
@RestController
public class PaymentRestService extends TransactionRestService  {
	
	@Autowired
	private PaymentService service;
	
	

	@RequestMapping(value="/payments", method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getAllPayments(@RequestParam(name="payment-context-code",required=false) String paymentContextCode	,	
			@RequestParam(name="context-ref-num",required=false) String contextRefNum	
		)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(paymentContextCode!=null) {
				criteria.put("payment-context-code", paymentContextCode);
			}
			if(contextRefNum!=null) {
				criteria.put("context-ref-num", contextRefNum);
			}

			
			return ResponseEntity.ok(service.getAllPayments(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/payment/{id}", method = RequestMethod.GET)
	public ResponseEntity<Payment> getPaymentById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getPaymentById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.POST)
	public ResponseEntity<String> addPayment(@RequestBody Payment payment)
	{	
		String result = "";
		try {
			result = service.addPayment(payment);
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
	
	@RequestMapping(value="/payment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePayment(@PathVariable("id")String id,@RequestBody  Payment payment)
	{
		String result = "";
		try {
			result = service.updatePayment(id, payment);
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
