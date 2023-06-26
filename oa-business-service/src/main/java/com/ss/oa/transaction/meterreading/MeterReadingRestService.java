package com.ss.oa.transaction.meterreading;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.MeterReading;


@Scope("prototype")
@RestController
public class MeterReadingRestService extends TransactionRestService {

	@Autowired
	private MeterReadingService service;
	

	@RequestMapping(value="/meterreadings", method = RequestMethod.GET)
	public ResponseEntity<List<MeterReading>> getAllMeterReadings(@RequestParam(name="company-id",required=false) String companyId,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="meter-id",required=false) String meterId,
			@RequestParam(name="meter-number",required=false) String meterNumber,
			@RequestParam(name="company-service-id",required=false) String companyServiceId,
			@RequestParam(name="company-service-number",required=false) String companyServiceNumber,
			@RequestParam(name="modem-number",required=false) String modemNumber,
			@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="year",required=false) String readingYear,
			@RequestParam(name="month",required=false) String readingMonthCode,
			@RequestParam(name="fuel-code",required=false) String fuelTypeCode,
			@RequestParam(name="fuel-type-name",required=false) String fuelTypeName,
			@RequestParam(name="fuel-group",required=false) String fuelGroupe,
	        @RequestParam(name="flowType-code",required=false) String flowTypecode)


			
	{
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(companyId!=null) {
				criteria.put("company-id",companyId);
			}
			if(companyName!=null) {
				criteria.put("company-name",companyName);
			}
			if(meterId!=null) {
				criteria.put("meter-id",meterId);
			}
			if(meterNumber!=null) {	
				criteria.put("meter-number",meterNumber);
			}
			if(companyServiceId!=null) {
				criteria.put("company-service-id",companyServiceId);
			}
			if(companyServiceNumber!=null) {
				criteria.put("company-service-number",companyServiceNumber);
			}
			if(modemNumber!=null) {
				criteria.put("modem-number",modemNumber);
			}
			if(readingYear!=null) {
				criteria.put("year",readingYear);
			}
			if(readingMonthCode!=null) {
				criteria.put("month",readingMonthCode);
			}
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			if(fuelTypeCode!=null) {
				criteria.put("fuel-code",fuelTypeCode);
			}
			if(fuelTypeName!=null) {
				criteria.put("fuel-type-name",fuelTypeName);
			}
			if(fuelGroupe!=null) {
				criteria.put("fuel-group",fuelGroupe);
			}
			if(flowTypecode!=null) {
				criteria.put("flowTypecode",flowTypecode);
			}
			
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("companyId")) {
						System.out.println("criteria companyId-->"+element.getValue()+"");
						
					}
				}
				
			}
//			if(criteria.isEmpty()){
//				System.out.println("Please provide atleast one criteria");
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//			}
			
			return ResponseEntity.ok(service.getAllMeterReadings(criteria))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/meterreading/{id}", method = RequestMethod.GET)
	public ResponseEntity<MeterReading> getMeterReadingById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getMeterReadingById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/meterreading", method = RequestMethod.POST)
	public ResponseEntity<String> addMeterReading(@RequestBody MeterReading meterReading)
	{	
		String result = "";
		try {
			result = service.addMeterReading(meterReading);
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
	
	@RequestMapping(value="/meterreading/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateMeterReading(@PathVariable("id")String id,@RequestBody  MeterReading meterReading)
	{
		String result = "";
		try {
			result = service.updateMeterReading(id,meterReading);
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
