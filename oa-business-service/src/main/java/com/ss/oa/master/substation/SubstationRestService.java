package com.ss.oa.master.substation;
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
import com.ss.oa.master.vo.Substation;
@RestController
@Scope("prototype")
public class SubstationRestService extends MasterRestService {
	@Autowired
	private SubstationService service;
	private static List<Substation> substations;
	@RequestMapping(value="/substations", method = RequestMethod.GET)
	public ResponseEntity <List<Substation>> getSubstations(@RequestParam(name="m_org_id",required=false) String org_id)
	{
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(org_id!=null){
				criteria.put("org_id",org_id);
				return ResponseEntity.ok(service.getAllSubstations(criteria));
			}
			else{
				if(substations==null || substations.isEmpty())
					substations = service.getAllSubstations(criteria);				
				return ResponseEntity.ok(substations);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	@RequestMapping(value="/substation/{id}", method = RequestMethod.GET)
	public ResponseEntity<Substation> getSubstationById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getSubstationById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/substation", method = RequestMethod.POST)
	public ResponseEntity<String> addSubstation(@RequestBody Substation substation)
	{
		String result = "";
		try {
			result = service.addSubstation(substation);
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
	
	@RequestMapping(value="/substation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateSubstation(@PathVariable("id")String id,@RequestBody Substation substation)
	{
		String result = "";
		try {
			result = service.updateSubstation(id,substation);
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

	@RequestMapping(value="/substation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSubstation(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deleteSubstation(id);
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
