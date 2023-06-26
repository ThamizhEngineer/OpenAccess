package com.ss.oa.master.company;
 
import java.time.LocalDateTime; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.common.OpenAccessException;

import com.ss.oa.model.master.Company;
import com.ss.oa.model.master.Meter;
import com.ss.oa.model.master.Service;
import com.ss.oashared.common.CommonUtils; 

@RestController
@RequestMapping("/master/Companiesnew")
@Scope("prototype")
public class CompanyService1 extends BaseDaoJdbc {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private ShareHolderRepository shareHolderRepository;
	
	@Autowired
	private MeterRepository meterRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	



	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Company>getCompany() throws OpenAccessException {
		return companyRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable(value="id")String id)throws OpenAccessException{
		return companyRepository.findById(id);
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/_internal/number/{number}")
	public Service getServiceNumber(@PathVariable(value="number")String number)throws OpenAccessException{
		return serviceRepository.findByNumber(number);
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public Company addCompany(@RequestBody Company Companyvo) throws OpenAccessException {
		System.out.println(commonUtils.generateId());
		Companyvo.setId(commonUtils.generateId());
		return companyRepository.save(Companyvo);

	}
	
//	@CrossOrigin(origins = "*")
//	@PatchMapping("/{id}/lines")
//	public Company addOrUpdateCompanyLines(@RequestBody Company Companyvo) throws OpenAccessException {
//		   if (Companyvo.getServices() != null && !Companyvo.getServices().isEmpty()) 
//			   for(Service service:Companyvo.getServices()) {
//				   service.setId(commonUtils.generateId());
//				   serviceRepository.save(service);
//				   }
//		   if(Companyvo.getShareHolders()!=null && !Companyvo.getShareHolders().isEmpty())
//			   for(CompanyShareHolder companyShareHolder:Companyvo.getShareHolders()) {
//				  companyShareHolder.setId(commonUtils.generateId()); 
//				  shareHolderRepository.save(companyShareHolder);
//			   }	   
//		return Companyvo;
//	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/servicelines")
	
	public Service addServiceLines(@RequestBody Service service)throws OpenAccessException{
		   if (service.getMeters() != null && !service.getMeters().isEmpty()) {
			   for(Meter meters:service.getMeters()) {
				   meters.setId(commonUtils.generateId());
				   meterRepository.save(meters);

				   }
		   }
		return service;
		
	}
	

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Company updateCompanyById(@RequestBody Company Companyvo) throws OpenAccessException {
		return companyRepository.save(Companyvo);

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteCompanyById(@PathVariable(value = "id") int id) throws OpenAccessException {
		companyRepository.deleteById(id);
	}
	

	@CrossOrigin(origins="*")
	@GetMapping("/service/{companyServiceId}")
	public Service getCompanyServiceById(@PathVariable(value="companyServiceId") String companyServiceId)throws OpenAccessException{
		return serviceRepository.findById(companyServiceId).get();
		
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/service/{companyServiceId}/verify-contact-info")
	public Service validateServiceContactInfo(@PathVariable(value = "companyServiceId") int companyServiceId, @RequestBody Service serviceInput) throws OpenAccessException {
		Service serviceFromDb = getCompanyServiceById(companyServiceId+"");
		serviceFromDb.setContactFullName(serviceInput.getContactFullName());
		serviceFromDb.setContactDesignation(serviceInput.getContactDesignation());
		serviceFromDb.setContactEmail(serviceInput.getContactEmail());
		serviceFromDb.setContactPhoneNo(serviceInput.getContactPhoneNo());
		serviceFromDb.setRegOfficeAddr(serviceInput.getRegOfficeAddr());
		serviceFromDb.setPlantAddr(serviceInput.getPlantAddr());
		serviceFromDb.setIsContactVerified("Y");
		serviceFromDb.setContactLastVerifiedDate(LocalDateTime.now());
		return serviceRepository.save(serviceFromDb);

	}


}

