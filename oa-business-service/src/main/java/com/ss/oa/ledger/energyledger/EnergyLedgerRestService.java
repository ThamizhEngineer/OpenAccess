package com.ss.oa.ledger.energyledger;

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

import com.ss.oa.common.LedgerRestService;

import com.ss.oa.ledger.vo.EnergyLedger;


@RestController
@Scope("prototype")
public class EnergyLedgerRestService extends LedgerRestService {
	@Autowired
	EnergyLedgerService service;
	
	@RequestMapping(value="/energyledgers", method = RequestMethod.GET)
	public ResponseEntity<List<EnergyLedger>> getEnergyLedgers(@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="org-code",required=false) String orgCode,
			@RequestParam(name="ledger-date",required=false) String ledgerDate,
			@RequestParam(name="month",required=false) String month ,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="company-id",required=false) String companyId,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="company-service-id",required=false) String companyServiceId,
			@RequestParam(name="company-service-number",required=false) String companyServiceNumber
			
			)
	{
		
		try {
									
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			if(orgCode!=null) {
				criteria.put("org-code",orgCode);
			}	
			
			if(ledgerDate!=null) {
				criteria.put("ledger-date",ledgerDate);
			}
			
			if(month!=null) {
				criteria.put("month",month);
			}
			if(year!=null) {
				criteria.put("year",year);
			} 
			if(companyId!=null) {
				criteria.put("company-id",companyId);
			}
			if(companyName!=null) {
				criteria.put("company-name",companyName);
			}
			if(companyServiceId!=null) {
				criteria.put("company-service-id",companyServiceId);
			}
			if(companyServiceNumber!=null) {
				criteria.put("company-service-number",companyServiceNumber);
			}
	
	
			
			return ResponseEntity.ok(service.getAllEnergyLedgers(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	@RequestMapping(value="/energyledger/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnergyLedger> getEnergyLedgerId(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getEnergyLedgerById(id));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energyledger", method = RequestMethod.POST)
	public ResponseEntity<String> addEnergyLedger(@RequestBody EnergyLedger energyLedger)
	{
		try {
			return ResponseEntity.ok(service.addEnergyLedger(energyLedger));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energyledger/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEnergyLedger(@PathVariable("id")String id,@RequestBody EnergyLedger energyLedger)
	{
		try {
			return ResponseEntity.ok(service.updateEnergyLedger(id, energyLedger));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

}
