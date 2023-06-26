package com.ss.oa.master.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.MasterRestService;
import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.model.master.Service;
import com.ss.oa.model.master.ServiceContactInfo;
import com.ss.oashared.model.AuthUser;


@RestController
@Scope("prototype")
public class CompanyRestService extends MasterRestService{
	@Autowired
	private CompanyService service;
	
	@Autowired
	AuthUser authUser;
	@CrossOrigin(origins="*")
	@RequestMapping(value="/companies", method = RequestMethod.GET)
	public ResponseEntity <List<Company>> getCompanies(HttpServletRequest request,@RequestParam(name="company-code",required=false) String companyCode,
			@RequestParam(name="company-name",required=false) String companyName,
			@RequestParam(name="service-associated",required=false) String serviceAssociated,
			@RequestParam(name="enabled",required=false) String enabled,
			@RequestParam(name="is-buyer",required=false) String isBuyer,
			@RequestParam(name="is-seller",required=false) String isSeller,
			@RequestHeader(name="systemkeycode",required=false) String systemKeyCode,
			@RequestHeader(name="username",required=false) String userName
			)
	{
		
		try{
		
			
			Map<String,String> criteria = new HashMap<String,String>();
			if(companyCode!=null) criteria.put("company_code",companyCode);
			if(companyName!=null) criteria.put("company_name",companyName);
			if(serviceAssociated!=null) criteria.put("service_associated",serviceAssociated);
			if(enabled!=null) criteria.put("enabled",enabled);
			if(isBuyer!=null) criteria.put("is-buyer",isBuyer);
			if(isSeller!=null) criteria.put("is-seller",isSeller);
			
//			if(criteria.isEmpty()){
//				System.out.println("Please provide atleast one criteria");
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//			}
			return ResponseEntity.ok(service.getAllCompanies(criteria));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="/companyservices", method = RequestMethod.GET)
	public ResponseEntity <List<CompanyServiceVO>> getAllCompanyServices(@RequestParam(name="type",required=false) String type,
			@RequestParam(name="edc-id",required=false) String edcId,
			@RequestParam(name="comp-service-numbers",required=false) String compServiceNumbers,
			@RequestParam(name="comp-service-id",required=false) String compServiceId,
			@RequestParam(name="enabled",required=false,defaultValue="Y") String enabled,
			@RequestParam(name="is-internal",required=false,defaultValue="N") String isInternal
			)
	{
		try{
			Map<String,String> criteria = new HashMap<String,String>();
			if(type!=null) criteria.put("type",type);
			if(edcId!=null && !edcId.trim().isEmpty()) criteria.put("edc-id",edcId);
			if(compServiceNumbers!=null) criteria.put("comp-service-numbers",compServiceNumbers);
			if(compServiceId!=null) criteria.put("comp-service-id",compServiceId);
			if(enabled!=null) criteria.put("enabled",enabled);
			if(enabled!=null) criteria.put("is-internal",isInternal);
			System.out.println(compServiceNumbers);
			System.out.println(compServiceId);
			System.out.println(enabled);
			
			if(criteria.isEmpty()){
				System.out.println("Please provide atleast one criteria");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
			
			return ResponseEntity.ok(service.getAllCompanyServices(criteria));
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}	
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<Company> getCompanyById(@PathVariable("id")String id)
	{
		try {
			return ResponseEntity.ok(service.getCompanyById(id));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value="/company", method = RequestMethod.POST)
	public ResponseEntity<String> addCompany(@RequestBody Company company)
	{
		String result = "";
		try {
			result = service.addCompany(company);
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
	@CrossOrigin(origins="*")
	@RequestMapping(value="/company/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCompany(@PathVariable("id")String id,@RequestBody Company company)
	{
		String result = "";
		try {
			result = service.updateCompany(id,company);
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
	@CrossOrigin(origins="*")
	@RequestMapping(value="/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCompany(@PathVariable("id")String id)
	{
		String result = "";
		try {
			result = service.deleteCompany(id);
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
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/company/service/{companyServiceId}/contact-info", method = RequestMethod.GET)
	public ResponseEntity<ServiceContactInfo> getServiceContactInfoById(@PathVariable("companyServiceId")String companyServiceId)
	{
		try {
			return ResponseEntity.ok(service.getServiceContactInfoById(companyServiceId));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/company/service/{companyServiceId}/verify-contact-info")
	public ResponseEntity<ServiceContactInfo> validateServiceContactInfo(@PathVariable(value = "companyServiceId") int companyServiceId, @RequestBody Service serviceInput) throws OpenAccessException {
		try {
			return ResponseEntity.ok(service.validateServiceContactInfo(companyServiceId, serviceInput));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	

}
