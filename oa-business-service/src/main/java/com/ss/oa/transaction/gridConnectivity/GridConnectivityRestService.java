package com.ss.oa.transaction.gridConnectivity;



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
import com.ss.oa.transaction.vo.GridConnectivity;


@Scope("prototype")
@RestController
public class GridConnectivityRestService extends TransactionRestService {

	
	@RequestMapping(value="/gridconnectivities", method = RequestMethod.GET)
	public ResponseEntity<List<GridConnectivity>> getAllGcs(@RequestParam(name="application-number",required=false) String applnNumber,			
			@RequestParam(name="plant-name",required=false) String plantName,			
			@RequestParam(name="generating-plant-type",required=false) String plantTypeCode,
			@RequestParam(name="fuel",required=false) String fuelCode,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="application-status",required=false) String statusCode)
	{
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			
			if(applnNumber!=null) {
				criteria.put("application-number", applnNumber);
			}
			
			if(plantName!=null) {
				criteria.put("plant-name", plantName);
			}
			
			if(plantTypeCode!=null) {
				criteria.put("generating-plant-type", plantTypeCode);
			}
			if(fuelCode!=null) {
				criteria.put("fuel", fuelCode);
			}
			if(companyName!=null) {
				criteria.put("company-name", companyName);
			}
			if(statusCode!=null) {
				criteria.put("application-status", statusCode);
			}


			
			return ResponseEntity.ok(service.getAllGcs(criteria));
	
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/gridconnectivity/{id}", method = RequestMethod.GET)
	public ResponseEntity<GridConnectivity> getGcById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getGcById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/gridconnectivity", method = RequestMethod.POST)
	public ResponseEntity<String> addGc(@RequestBody GridConnectivity gc)
	{	
		String result = "";
		try {
			result = service.addGc(gc);
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
	
	@RequestMapping(value="/gridconnectivity/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGc(@PathVariable("id")String id,@RequestBody GridConnectivity gc)
	{
		String result = "";
		try {
			result = service.updateGc(id,gc);
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
	
	@Autowired(required = true)
	private GridConnectivityService service;
	@RequestMapping(value="/gridconnectivity/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeSignup(@PathVariable("id")String signupId,@RequestBody GridConnectivity gridconnectivity)
	{
		String result = "";
		try {
			result = service.completeGridConnectivity(gridconnectivity);
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
