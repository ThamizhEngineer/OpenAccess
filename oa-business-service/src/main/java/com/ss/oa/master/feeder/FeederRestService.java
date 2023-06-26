package com.ss.oa.master.feeder;
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

import com.ss.oa.common.MasterRestService;
import com.ss.oa.master.vo.Feeder;

@RestController
@Scope("prototype")
public class FeederRestService extends MasterRestService {
	@Autowired
	private FeederService service;
	//private static List<Feeder> feeders;
	@RequestMapping(value="/feeders", method = RequestMethod.GET)
	public ResponseEntity <List<Feeder>> getFeeders(@RequestParam(name="substation-id",required=false) String substationId,
													@RequestParam(name="name",required=false) String feederName,
													@RequestParam(name="voltage",required=false) String feederVoltage)
	{
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(substationId!=null) criteria.put("substationId",substationId);
			if(feederName!=null) criteria.put("feederName",feederName);
			if(feederVoltage!=null) criteria.put("feederVoltage",feederVoltage);
			
			if(criteria.isEmpty()){
				System.out.println("Please provide atleast one criteria");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
			return ResponseEntity.ok(service.getAllFeeders(criteria));
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	@RequestMapping(value="/feeder/{id}", method = RequestMethod.GET)
	public ResponseEntity<Feeder> getFeederById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getFeederById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/feeder", method = RequestMethod.POST)
	public ResponseEntity<String> addFeeder(@RequestBody Feeder feeder)
	{
		String result = "";
		try {
			result = service.addFeeder(feeder);
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
	
	@RequestMapping(value="/feeder/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateFeeder(@PathVariable("id")String id,@RequestBody Feeder feeder)
	{
		String result = "";
		try {
			result = service.updateFeeder(id,feeder);
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

	@RequestMapping(value="/feeder/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFeeder(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deleteFeeder(id);
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
