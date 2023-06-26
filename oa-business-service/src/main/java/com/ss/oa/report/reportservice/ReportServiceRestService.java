package com.ss.oa.report.reportservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.ReportRestService;
import com.ss.oa.report.vo.Report;

@RestController
@Scope("prototype")
public class ReportServiceRestService extends ReportRestService {
	
	@Autowired
	ReportServiceService service;
	
	@RequestMapping(value="/org-quantum", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> getGenerationPerOrg(@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="fuel-type-code",required=false) String fuelTypeCode,
			@RequestParam(name="is-captive",required=false) String isCaptive,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year
			)
	{
		
		try {								
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			if(fuelTypeCode!=null) {
				criteria.put("fuel-type-code",fuelTypeCode);
			}
			if(isCaptive!=null) {
				criteria.put("is-captive",isCaptive);
			}
			if(month!=null) {
				criteria.put("month",month);
			}
			if(year!=null) {
				criteria.put("year",year);
			} 
	
			
			return ResponseEntity.ok(service.getGenerationPerOrg(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	@RequestMapping(value="/service-quantum", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> getGenerationPerService(@RequestParam(name="org-id",required=false) String orgId,
			@RequestParam(name="fuel-type-code",required=false) String fuelTypeCode,
			@RequestParam(name="is-captive",required=false) String isCaptive,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year
			)
	{
		
		try {								
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(orgId!=null) {
				criteria.put("org-id",orgId);
			}
			if(fuelTypeCode!=null) {
				criteria.put("fuel-type-code",fuelTypeCode);
			}
			if(isCaptive!=null) {
				criteria.put("is-captive",isCaptive);
			}
			if(month!=null) {
				criteria.put("month",month);
			}
			if(year!=null) {
				criteria.put("year",year);
			} 
	
			
			return ResponseEntity.ok(service.getGenerationPerService(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	@RequestMapping(value="/gen-consumer-usage", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> fetchGeneratorBasedConsumerUsage(@RequestParam(name="voltage-code",required=false) String voltageCode,
			@RequestParam(name="parent-org-code",required=false) String parentOrgCode,
			@RequestParam(name="is-captive",required=false) String isCaptive

			)
	{
		
		try {								
					
			Map<String,String> criteria =new HashMap<String,String>();
			if(voltageCode!=null) {
				criteria.put("voltage-code",voltageCode);
			}
			if(parentOrgCode!=null) {
				criteria.put("parent-org-code",parentOrgCode);
			}
			if(isCaptive!=null) {
				criteria.put("is-captive",isCaptive);
			}
			
	
			
			return ResponseEntity.ok(service.fetchGeneratorBasedConsumerUsage(criteria));
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	
	}
	
	
	

}

