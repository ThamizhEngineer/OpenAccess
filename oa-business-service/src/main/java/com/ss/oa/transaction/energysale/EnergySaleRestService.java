package com.ss.oa.transaction.energysale;

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
import com.ss.oa.transaction.vo.EnergySale;
import com.ss.oa.transaction.vo.EnergySaleCharge;
import com.ss.oa.transaction.vo.EnergySaleUsageSummary;


@RestController
@Scope("prototype")
public class EnergySaleRestService extends TransactionRestService {
	
	@Autowired
	public EnergySaleService service;
	
	@RequestMapping(value="/energysales", method = RequestMethod.GET)
	public ResponseEntity <List<EnergySale>> getAllEnergysales(HttpServletRequest request,			
			@RequestParam(name="company-id",required=false) String companyId,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="edc-id",required=false) String ordId,	
			@RequestParam(name="service-id",required=false) String companyServiceId,
			@RequestParam(name="service-number",required=false) String companyServiceNumber,
			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="generation-statement-id",required=false) String generationStatementId,
			@RequestParam(name="is-stb",required=false) String isStb,
			@RequestParam(name="multiple-buyers",required=false) String multipleBuyers,
			@RequestParam(name="simple-energy-sale",required=false) String simpleEnergySale,
			@RequestParam(name="fuelTypeCode",required=false) String fuelTypeCode,
			@RequestParam(name="fuelTypeName",required=false) String fuelTypeName,
			@RequestParam(name="fuelGroupe",required=false) String fuelGroupe,
			@RequestParam(name="flowTypeCode",required=false) String flowTypeCode)
	{
		
		try{
			Map<String,String> criteria = new HashMap<String,String>();
		
			
			if(companyId!=null) criteria.put("company-id",companyId);
			if(companyName!=null) criteria.put("company-name",companyName);
			if(ordId!=null) criteria.put("edc-id",ordId);
			if(companyServiceNumber!=null) criteria.put("service-number",companyServiceNumber);
			if(companyServiceId!=null) criteria.put("service-id",companyServiceId);
			if(month!=null) criteria.put("month",month);	
			if(year!=null) criteria.put("year",year);
			if(generationStatementId!=null) criteria.put("generation-statement-id",generationStatementId);
			if(isStb!=null) criteria.put("is-stb",isStb);
			if(multipleBuyers!=null) criteria.put("multiple-buyers",multipleBuyers);
			if(simpleEnergySale!=null) criteria.put("simple-energy-sale",simpleEnergySale);
			if(fuelTypeCode!=null) criteria.put("fuelTypeCode",fuelTypeCode);
			if(fuelTypeName!=null) criteria.put("fuelTypeName",fuelTypeName);
			if(fuelGroupe!=null) criteria.put("fuelGroupe",fuelGroupe);
			if(flowTypeCode!=null) criteria.put("flowTypeCode",flowTypeCode);
			
			return ResponseEntity.ok(service.getAllEnergySales(criteria));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	
	@RequestMapping(value="/energysale/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnergySale> getEnergySaleById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getEnergySaleById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energysale", method = RequestMethod.POST)
	public ResponseEntity<String> addEnergySale(@RequestBody EnergySale energySale)
	{	
		String result = "";
		try {
//			System.out.println("Es sale - > Rest >>"+energySale);
			result = service.addEnergySale(energySale);
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
	
	@RequestMapping(value="/energysale/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGenerationStatement(@PathVariable("id")String id,@RequestBody EnergySale energySale)
	{
		String result = "";
		try {
			result = service.updateEnergySale(id,energySale);
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
	
	@RequestMapping(value="/energysale/{id}/final", method = RequestMethod.PUT)
	public ResponseEntity<String> finalEnergySaleById(@PathVariable("id")String id,@RequestBody EnergySale energySale)
	{
		try {
			return ResponseEntity.ok(service.finalEnergySale(id, energySale));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/esusage/delete", method = RequestMethod.PUT)
	public ResponseEntity<String> DeleteEsUsageSummary(@RequestBody EnergySaleUsageSummary energySaleUsageSummary)
	{
		try {
			System.out.println("energySaleUsageSummary RestService");
			System.out.println(energySaleUsageSummary);

			return ResponseEntity.ok(service.DeleteEsUsageSummary(energySaleUsageSummary));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/escharge/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> DeleteEsCharge(@RequestBody EnergySaleCharge energySaleCharge)
	{
		try {
			System.out.println("RestService");
			System.out.println(energySaleCharge);

			return ResponseEntity.ok(service.DeleteEsCharge(energySaleCharge));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value="/energysale/{id}/multiadd", method = RequestMethod.POST)
	public ResponseEntity<EnergySale> multiAddEnergySale(@PathVariable("id")String id,@RequestBody EnergySale energySale)
	{
		try {
			return ResponseEntity.ok(service.multiAddEnergySale(id, energySale));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
