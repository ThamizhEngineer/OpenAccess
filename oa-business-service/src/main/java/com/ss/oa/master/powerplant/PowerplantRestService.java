package com.ss.oa.master.powerplant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.oa.common.MasterRestService;



import com.ss.oa.master.vo.Powerplant;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@RestController
@Scope("prototype")
public class PowerplantRestService extends MasterRestService{
	@Autowired
	private PowerplantService service;
	
	@Autowired
	CommonUtils commonUtils;
	
	private static List<Powerplant> powerplants;
	@RequestMapping(value="/powerplants", method = RequestMethod.GET)
	public ResponseEntity<List<Powerplant>> getPowerplants(@RequestParam(name="plant-type-code",required=false) String plant_type_code,
														@RequestParam(name="fuel-code",required=false) String fuel_code,
														@RequestParam(name="code",required=false) String code,
														@RequestParam(name="org-id",required=false) String org_id,
														@RequestParam(name="company-id",required=false) String company_id,
														@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
														@RequestHeader(name="username",required=false) String userName
														)
	{
		try {
			
				
			Map<String,String> criteria = new HashMap<String,String>();
			if(plant_type_code!=null)
			{
				criteria.put("plant_type_code",plant_type_code);
			}
			if(fuel_code!=null)
			{
				criteria.put("fuel_code",fuel_code);
			}
			if(code!=null)
			{
				criteria.put("code",code);
			}
			if(org_id!=null)
			{
				criteria.put("org_id",org_id);
			}
			if(company_id!=null)
			{
				criteria.put("company_id",company_id);
			}
			
			if(!criteria.isEmpty())
			{
				return ResponseEntity.ok(service.getAllPowerplant(criteria));
			}
			else{
				if(powerplants==null || powerplants.isEmpty())
					powerplants = service.getAllPowerplant(criteria);				
				return ResponseEntity.ok(powerplants);
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@RequestMapping(value="/powerplant/{id}", method = RequestMethod.GET)
	public ResponseEntity<Powerplant> getPowerplantById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getPowerplantById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/powerplant", method = RequestMethod.POST)
	public ResponseEntity<String> addPowerplant(@RequestBody Powerplant powerplant)
	{	
		String result = "";
		try {
			result = service.addPowerplant(powerplant);
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
	
	@RequestMapping(value="/powerplant/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePowerplant(@PathVariable("id")String id,@RequestBody Powerplant powerplant)
	{
		String result = "";
		try {
			result = service.updatePowerplant(id,powerplant);
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

	@RequestMapping(value="/powerplant/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePowerplant(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deletePowerplant(id);
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
	
	
	@RequestMapping(value = "/powerplant/print-report/{name}", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody>
 PrintGenerationStatement(@PathVariable("name") String name,@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "capacity", required = false) String capacity,
			@RequestParam(value = "makeCode", required = false) String makeCode)
			throws IOException, FileNotFoundException {
		Map<String, String> ipCriteria = new HashMap<String, String>();
		if (orgId != null && orgId.trim() != "")
			ipCriteria.put("orgId", orgId);
		if (capacity != null && capacity.trim() != "")
			ipCriteria.put("capacity", capacity);
		if (makeCode != null && makeCode.trim() != "")
			ipCriteria.put("makeCode", makeCode);

		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.WegWithBuyerReport);
		

		payload.getFilterCriteria().put("orgId",orgId);
		payload.getFilterCriteria().put("capacity",capacity);
		payload.getFilterCriteria().put("makeCode",makeCode);	
		System.out.println(payload);

		return  commonUtils.fetchFileAsStreamResponse(service.printWegwithBuyer(payload));
	}
	
}

