package com.ss.oa.integration.samastNocReading;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.vo.ImpSamastAppLog;

@RestController
public class SamastApplnImportRestService {
	
	@Autowired
	SamastApplnImportService samastApplnImportService;
	
	@Autowired
	ImpSamastAppLogRepo impSamastAppLogRepo;
	
	@RequestMapping(value="/samast-appln-import", method = RequestMethod.GET)
	public ResponseEntity<ImpSamastAppLog> importSamastAppln(){		
		try {		
			
			ImpSamastAppLog impSamastAppLog =findLatestRecord("APPLICATION");
			
			if(impSamastAppLog==null) {
				return ResponseEntity.ok(samastApplnImportService.processRecords("APPLICATION","new-record",LocalDate.now().toString()));
			}else {				
				String date=impSamastAppLog.getToDt().toString().replace("T"," ");
				return ResponseEntity.ok(samastApplnImportService.processRecords("APPLICATION",date,LocalDate.now().toString()));
			}						
		}catch(Exception e) {
			e.printStackTrace();				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
	@GetMapping("/find-max/{appType}")
	public ImpSamastAppLog findLatestRecord(@PathVariable(name = "appType")String appType) {
		return impSamastAppLogRepo.findMax(appType);
	}
	
	@RequestMapping(value="/samast-approval-import", method = RequestMethod.GET)
	public ResponseEntity<ImpSamastAppLog> importSamastByApprovalStatus(){		
		try {
			ImpSamastAppLog impSamastAppLog =findLatestRecord("APPROVAL");
			if(impSamastAppLog==null) {
				return ResponseEntity.ok(samastApplnImportService.processRecords("APPROVAL","new-record",LocalDate.now().toString()));
			}else {
				String date =impSamastAppLog.getToDt().toString().replace("T"," ");
				return ResponseEntity.ok(samastApplnImportService.processRecords("APPROVAL",date,LocalDate.now().toString()));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();				
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}

}
