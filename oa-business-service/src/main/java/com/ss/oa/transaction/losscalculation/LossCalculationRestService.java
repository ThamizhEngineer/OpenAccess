package com.ss.oa.transaction.losscalculation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.LossCalculation;

@Scope("prototype")
@RestController
public class LossCalculationRestService extends TransactionRestService  {
	@Autowired
	private LossCalculationService service;
	private static List<LossCalculation> losscalculation;
	@RequestMapping(value="/losscalculations" , method = RequestMethod.GET)

	public ResponseEntity<List<LossCalculation>> fetchLossCalculation(@RequestParam(name="ID",required=false) String ID)
	{
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(ID!=null) {
				criteria.put("ID",ID);
			}
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("ID")) {
						System.out.println("criteria ID -->"+element.getValue()+"");
						
					}
				}
				return ResponseEntity.ok(service.fetchLossCalculation(criteria));
			} else {
				
				losscalculation=service.fetchLossCalculation(criteria);
					return ResponseEntity.ok(losscalculation);				
				
			}
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	

	}
	@RequestMapping(value="/calculate-loss", method = RequestMethod.GET)
	public ResponseEntity<LossCalculation> calculateLoss(@RequestParam(name="injection-voltage") String injectionVoltage,@RequestParam(name="drawal-voltage") String drawalVoltage,@RequestParam(name="injected-units") String injectedUnits)
	{
		try {
			return ResponseEntity.ok(service.calculateLoss(injectionVoltage,drawalVoltage,injectedUnits));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	

}
