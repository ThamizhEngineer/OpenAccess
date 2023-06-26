package com.ss.oa.transaction.meterreadingimport;

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

import com.ss.oa.common.Response;
import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;

@Scope("prototype")
@RestController
public class MeterReadingImportRestService extends TransactionRestService{

	@Autowired
	private MeterReadingImportService service;
	
	
	@RequestMapping(value="/meterreadingimports", method = RequestMethod.GET)
	public ResponseEntity<List<MeterReadingImport>> getAllMeterReadingImports(@RequestParam(name="import-date",required=false) String importDate,
			@RequestParam(name="code",required=false) String code,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="source",required=false) String source)
	{
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(importDate!=null) {
				criteria.put("import-date",importDate);
			}
			if(code!=null) {
				criteria.put("code",code);
			}
			if(status!=null) {
				criteria.put("status",status);
			}
			if(source!=null) {
				criteria.put("source",source);
			}
			
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("importDate")) {
						System.out.println("criteria import-date-->"+element.getValue()+"");
						
					}
				}
				
			}
//			if(criteria.isEmpty()){
//				System.out.println("Please provide atleast one criteria");
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//			}
			
			return ResponseEntity.ok(service.getAllMeterReadingImports(criteria))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/meterreadingimport/{id}", method = RequestMethod.GET)
	public ResponseEntity<MeterReadingImport> getMeterReadingImportById(@PathVariable("id") String id)
	{
		try {			
			
			return ResponseEntity.ok(service.getMeterReadingImportById(id))   ;
		
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/meterreadingimport", method = RequestMethod.POST)
	public ResponseEntity<Response> addMeterReadingImport(@RequestBody MeterReadingImport meterReadingImport)
	{	
	
		try {
			
				return ResponseEntity.ok(service.addMeterReadingImport(meterReadingImport));
			
		} catch (Exception e) {

			e.printStackTrace();
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	

	
//	@RequestMapping(value="/mrBybatch", method = RequestMethod.POST)
//	public String adMrByBatch(@RequestBody List<MeterReadingImportLines> meterReadingImportLines)
//	{
//			return service.saveMrLinesByBatch(meterReadingImportLines);
//	}
	
			
	
	@RequestMapping(value="/meterreadingimport/process", method = RequestMethod.POST)
	public ResponseEntity<String> addAndProcessMeterReadingImport(@RequestBody MeterReadingImport meterReadingImport)
	{	
	
		try {
				return ResponseEntity.ok(service.addAndProcessMeterReadingImport(meterReadingImport));
			
		} catch (Exception e) {

			e.printStackTrace();
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/meterreadingimport/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateMeterReadingImport(@PathVariable("id")String id,@RequestBody  MeterReadingImport meterReadingImport)
	{
		try {
			
			return ResponseEntity.ok(service.updateMeterReadingImport(id,meterReadingImport));
		
	} catch (Exception e) {

		e.printStackTrace();
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	}
	
}
