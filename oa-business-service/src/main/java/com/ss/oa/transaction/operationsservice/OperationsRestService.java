package com.ss.oa.transaction.operationsservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.TransactionRestService;




@Scope("prototype")
@RestController
public class OperationsRestService extends TransactionRestService {
	
  
	@Autowired
	private OperationsService service;

	@RequestMapping(value="/import-process-mri", method = RequestMethod.GET)
	public ResponseEntity<String> importAndProcessMri(HttpServletRequest request,@RequestParam(name="batch-id",required=false) String V_BATCH_ID)
	{
		
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(V_BATCH_ID!=null) criteria.put("batch-id",V_BATCH_ID);
			return ResponseEntity.ok(service.importAndProcessMri(criteria));
		}
			catch(Exception e)
			{
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}	
		}
		 
	
	@RequestMapping(value="/import-meter-readings", method = RequestMethod.GET)
	public ResponseEntity<String> importMeterReading(HttpServletRequest request,@RequestParam(name="batch-id",required=false) String V_BATCH_ID,
	                  @RequestParam(name="Month",required=false) String month,
		              @RequestParam(name="Year",required=false) String year,
		              @RequestParam(name="batch-code",required=false) String batchCode            
		             )
	
        {
		
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(V_BATCH_ID!=null) criteria.put("batch-id",V_BATCH_ID);
			if(month!=null) criteria.put("month",month);			
			if(year!=null) criteria.put("year",year);
			if(batchCode!=null) criteria.put("batch-code",batchCode);
			return ResponseEntity.ok(service.importMeterReading(criteria));
		}
			catch(Exception e)
			{
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}	
		}
		 
	
	@RequestMapping(value="/process-generation-statements", method = RequestMethod.GET)
	public ResponseEntity<String>ProcessGenerationStatements(HttpServletRequest request,@RequestParam(name="month",required=false) String V_MONTH,
	              @RequestParam(name="year",required=false) String V_YEAR
	             )

  {
	
	try{
		Map<String,String> criteria = new HashMap<String,String>();
		if(V_MONTH!=null) criteria.put("month",V_MONTH);
		if(V_YEAR!=null) criteria.put("year",V_YEAR);
		return ResponseEntity.ok(service.ProcessGenerationStatements(criteria));
	}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	
	@RequestMapping(value="/importmeterreadingfrommdms",method=RequestMethod.GET)
	public ResponseEntity<String> importMeterReadingFromMdms(HttpServletRequest request,@RequestParam(name="month",required=false) String month,
            @RequestParam(name="year",required=false) String year
           )
	{
		try {
			Map<String,String> criteria = new HashMap<String,String>();
			if(month!=null) criteria.put("month",month);
			if(year!=null) criteria.put("year",year);
			return ResponseEntity.ok(service.importMeterReadingFromMdms(month,year));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	
	 
	
}