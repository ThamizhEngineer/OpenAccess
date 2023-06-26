package com.ss.oa.transaction.logservice;

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
import com.ss.oa.transaction.vo.LogService;

@Scope("prototype")
@RestController
public class LogServiceRestService extends  TransactionRestService{
	
	@Autowired
	private LogServiceService service;
	
	@RequestMapping(value="/logservices", method = RequestMethod.GET)
	public ResponseEntity <List<LogService>> getAllLogServices(@RequestParam(name="id",required=false) String Id,
													@RequestParam(name="process-type",required=false) String processType,
													@RequestParam(name="process-name",required=false) String processName,
													@RequestParam(name="activity-name",required=false) String activityName,
													@RequestParam(name="message",required=false) String message ,
													@RequestParam(name="result",required=false) String result,
													@RequestParam(name="created-by",required=false) String createdBy,
													@RequestParam(name="created-dt",required=false) String createdDt,
													@RequestParam(name="att1",required=false) String att1 ,
													@RequestParam(name="att2",required=false) String att2,
													@RequestParam(name="att3",required=false) String att3,
													@RequestParam(name="created-date",required=false) String createdDate,
													@RequestParam(name="enabled",required=false) String enabled)
															
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(Id!=null) {
				criteria.put("id", Id);
			}
			if(processType!=null) {
				criteria.put("process-type", processType);
			}
			
			if(processName!=null) {
				criteria.put("process-name", processName);
			}
			if(activityName!=null) {
				criteria.put("activity-name", activityName);
			}
			
			if(message!=null) {
				criteria.put("message", message);
			}
			if(result!=null) {
				criteria.put("result", result);
			}
			if(createdBy!=null) {
				criteria.put("created-by", createdBy);
			}
			if(createdDt!=null) {
				criteria.put("created-dt", createdDt);
			}
			if(att1!=null) {
 				criteria.put("att1", att1);
 			}
			if(att2!=null) {
				criteria.put("att1", att2);
			}
			if( att3!=null) {
				criteria.put("att3",  att3);
			}
			if(createdDate!=null) {
				criteria.put("created-date", createdDate);
			}
			if(enabled!=null) {
				criteria.put("enabled", enabled);
			}
			
			return ResponseEntity.ok(service.getAllLogServices(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/logservice/{id}", method = RequestMethod.GET)
	public ResponseEntity<LogService> getLogServiceById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getLogServiceById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/logservice", method = RequestMethod.POST)
	public ResponseEntity<String> addLogService(@RequestBody LogService logService)
	{
		String result = "";
		try {
			result = service.addLogService(logService);
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
	
	@RequestMapping(value="/logservice/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateLogService(@PathVariable("id")String id,@RequestBody LogService logService)
	{
		String result = "";
		try {
			result = service.updateLogService(id,logService);
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
	
	


