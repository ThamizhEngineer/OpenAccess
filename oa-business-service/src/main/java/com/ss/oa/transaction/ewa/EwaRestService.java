package com.ss.oa.transaction.ewa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ss.oa.transaction.vo.Ewa;

@Scope("prototype")
@RestController
public class EwaRestService extends TransactionRestService {
	
	@Autowired
	EwaService service ;
	
	
	@RequestMapping(value="/ewas",method=RequestMethod.GET)
	public ResponseEntity<List<Ewa>> getAllewas(HttpServletRequest request,
			@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompServiceNumber,			
			@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="from-date",required=false) String fromDate ,
			@RequestParam(name="to-date",required=false) String toDate,
			@RequestParam(name="injection-voltage-code",required=false) String sellerInjectionVoltageCode,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="ewa-code",required=false) String ewaCode,
			@RequestParam(name="fuel-type-code",required=false) String sellerPowerPlantFuelCode,
			@RequestParam(name="fuel-type-name",required=false) String sellerPowerPlantFuelDesc,
			@RequestParam(name="fuel-group",required=false) String fuelGroupe)
	
{
		
		try{
			Map<String,String> criteria = new HashMap<String,String>();
		
			if(sellerEdcId!=null) {
				criteria.put("seller-edc-id", sellerEdcId);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			
			if(sellerCompanyId!=null) {
				criteria.put("seller-company-id", sellerCompanyId);
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(ewaCode!=null) {
				criteria.put("ewa-code", ewaCode);
			}
			if(sellerPowerPlantFuelCode!=null) {
				criteria.put("fuel-type-code", sellerPowerPlantFuelCode);
			}
			if(sellerPowerPlantFuelDesc!=null) {
				criteria.put("fuel-type-name", sellerPowerPlantFuelDesc);
			}
			if(fuelGroupe!=null) {
				criteria.put("fuel-group", fuelGroupe);
			}
			
			return ResponseEntity.ok(service.getAllewas(criteria));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
}
	
	
	
	@RequestMapping(value="/ewa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ewa> getewaById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getewaById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	@RequestMapping(value="/ewa", method = RequestMethod.POST)
	public ResponseEntity<String> addewa(@RequestBody Ewa ewa)
	{	
		String result = "";
		try {
			result = service.addewa(ewa);
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
	
	@RequestMapping(value="/ewa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateewa(@PathVariable("id")String id,@RequestBody Ewa ewa)
	{
		String result = "";
		try {
			result = service.updateewa(id,ewa);
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
	@RequestMapping(value="/ewa/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveEwa(@PathVariable("id")String id,@RequestBody Ewa ewa)
	{
		String result = "";
		try {
			result = service.approveEwa(id, ewa);
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
	
	@RequestMapping(value="/ewa/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeEwa(@PathVariable("id")String id,@RequestBody Ewa ewa)
	{
		String result = "";
		try {
			result = service.completeEwa(id, ewa);
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
