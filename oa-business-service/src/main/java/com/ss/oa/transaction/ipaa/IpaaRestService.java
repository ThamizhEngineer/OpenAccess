package com.ss.oa.transaction.ipaa;

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
import com.ss.oa.model.transaction.IpaStandingClearance;
import com.ss.oa.transaction.vo.Ipaa;



@Scope("prototype")
@RestController
public class IpaaRestService extends TransactionRestService{
	@Autowired
	IpaaService service;
	
	@Autowired
	IpaaBusinessHelper ipaHelper;
	
	@RequestMapping(value="/ipaas",method=RequestMethod.GET)
	public ResponseEntity<List<Ipaa>> getAllIpaa(@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
																		@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,
																		@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
																		@RequestParam(name="buyer-company-service-number",required=false) String buyerCompanyServiceNumber,
																		@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
																		@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
																		@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
																		@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
																		@RequestParam(name="status",required=false) String status,
																		@RequestParam(name="period",required=false) String period,
																		@RequestParam(name="es-intent-code",required=false) String esIntentCode,
																		@RequestParam(name="code",required=false) String ipaaCode){
		
		try {
			
			Map<String,String> criteria = new HashMap<String,String>();
			//introduced buyer company id,name ,service number for search option but did not implement it for simplicity
			if(sellerEdcId!=null) {
				criteria.put("seller-edc-id", sellerEdcId);
			}
			
			if(sellerCompanyServiceNumber!=null) {
				criteria.put("seller-company-service-number", sellerCompanyServiceNumber);
			}
			if(buyerCompanyServiceNumber!=null) {
				criteria.put("buyer-company-service-number", buyerCompanyServiceNumber);
			}
			if(sellerCompanyId!=null) {
				criteria.put("seller-company-id", sellerCompanyId);
			}
			if(sellerCompanyName!=null) {
				criteria.put("seller-company-name", sellerCompanyName);
			}
			if(buyerCompanyId!=null) {
				criteria.put("buyer-company-id", buyerCompanyId);
			}
			if(buyerCompanyName!=null) {
				criteria.put("buyer-company-name", buyerCompanyName);
			}
			if(status!=null) {
				criteria.put("status", status);
			}
			if(period!=null) {
				criteria.put("period", period);
			}
			if(sellerCompanyServiceId!=null) {
				criteria.put("seller-company-service-id", sellerCompanyServiceId);
			}
			if(esIntentCode!=null) {
				criteria.put("es-intent-code", esIntentCode);
			}
			if(ipaaCode!=null) {
				criteria.put("code", ipaaCode);
			}
			System.out.println(criteria.get(buyerCompanyId));
			return ResponseEntity.ok(service.getAllIpaa(criteria));

			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@RequestMapping(value="/ipaa/{id}", method=RequestMethod.GET)
	public ResponseEntity<Ipaa> getIpaaById(@PathVariable("id") String id){
		try {
			return ResponseEntity.ok(service.getIpaaById(id));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
		
	}
	
	@RequestMapping(value="/ipaa",method=RequestMethod.POST)
	public ResponseEntity<String> addIpaa(@RequestBody Ipaa ipaa){
		try {
			return ResponseEntity.ok(service.addIpaa(ipaa));
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
			
			
		}
	}
	@RequestMapping(value="/ipaa/{id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateIpaa(@PathVariable("id")String id,@RequestBody Ipaa ipaa){
		try {
			return ResponseEntity.ok(service.updateIpaa(id,ipaa));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
	@RequestMapping(value="/ipaa/{id}/complete", method = RequestMethod.PUT)
	public ResponseEntity<String> completeIpaa(@PathVariable("id")String id,@RequestBody Ipaa ipaa)
	{
		String result = "";
		try {
			result = service.completeIpaa(id, ipaa);
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
	
	@RequestMapping(value="/ipaa/{id}/approve", method = RequestMethod.PUT)
	public ResponseEntity<String> approveIpaa(@PathVariable("id")String id,@RequestBody Ipaa ipaa)
	{
		String result = "";
		try {
			result = service.approveIpaa(id, ipaa);
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

//---------------- Apply for standing clearance---------------------------------	
	@RequestMapping(value="/ipaa/applysc", method = RequestMethod.POST)
	public String applyForStandingClearance(
			@RequestBody IpaStandingClearance ipaStandingClearance){
		System.out.println(ipaStandingClearance);
		return ipaHelper.addIpaSc(ipaStandingClearance);
	}
}
