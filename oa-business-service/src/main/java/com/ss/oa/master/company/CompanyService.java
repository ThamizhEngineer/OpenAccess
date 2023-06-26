package com.ss.oa.master.company;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.master.vo.CompanyShareHolder;
import com.ss.oa.master.vo.Meter;
import com.ss.oa.model.master.Service;
import com.ss.oa.model.master.ServiceContactInfo;
@Component
@Scope("prototype")
public class CompanyService {
	@Autowired
	public CompanyDao dao;

	@Autowired
	private ServiceContactInfoRepository serviceContactInfoRepository;
	
	String companyId="";
	String companyServiceId="";
	String companyMeterId="";
	String companyShareHolderId="";
	public CompanyService(){
		super();
	}
	
	@PostConstruct
    public void init() {
    }
	
	public List<Company> getAllCompanies(Map<String,String> criteria) throws Exception
	{
		try {
			return dao.getAllCompanies(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	public List<CompanyServiceVO> getAllCompanyServices(Map<String,String> criteria) throws Exception
	{

		try {
			return dao.getAllCompanyServices(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	} 
	public Company getCompanyById(String id)
	{
		return dao.getCompanyById(id);
	}
	public String addCompany(Company company)
	{
		Boolean meterCreated = false; 
		companyId = dao.addCompany(company);
		System.out.println("companyId"+companyId);
		if(company.getServices()!=null) {
			for(CompanyServiceVO service :company.getServices()){
				service.setCompanyId(companyId);
				companyServiceId = dao.addCompanyService(service);	
				System.out.println("companyServiceId"+companyServiceId);
				
				// typically in addCompany, only one meter is expected to be created
				if(company.getMeters()!=null && company.getMeters().size()==1 && !meterCreated) {
					Meter meter = company.getMeters().get(0);
					meter.setServiceId(companyServiceId);
					companyMeterId = dao.addCompanyMeter(meter);
					System.out.println("companyMeterId"+companyMeterId);
					meterCreated = true;
				}				
			}
		}
		if(company.getShareHolders()!=null) {
			for(CompanyShareHolder companyShareHolder:company.getShareHolders()) {
				companyShareHolder.setCompanyId(companyId);
				companyShareHolderId = dao.addCompanyShareHolder(companyShareHolder);
				System.out.println("companyShareHolderId"+companyShareHolderId);
			}
		}
		System.out.println("companyId"+companyId);
		return companyId;
	}
	public String updateCompany(String id,Company company)
	{
		dao.updateCompany(id,company);
		for(CompanyServiceVO service :company.getServices()){
			
			if(service.getId()!=null && service.getId().trim().length()>0) {
				dao.updateCompanyService(service);			
				
			}else {	
				
					service.setCompanyId(id);
					companyServiceId=dao.addCompanyService(service);						 
				
			}
		}
		for(Meter meter:company.getMeters()) {
			if(meter.getId()!=null && meter.getId().trim().length()>0) {
				dao.updateCompanyMeter(meter);
			}else {
				//meter.setServiceId(companyServiceId);
				dao.addCompanyMeter(meter);
			}
		}
		for(CompanyShareHolder companyShareHolder:company.getShareHolders()) {
			if(companyShareHolder.getId()!=null && companyShareHolder.getId().trim().length()>0) {
				dao.updateCompanyShareHolder(companyShareHolder);
			}else {
				companyShareHolder.setCompanyId(id);
				dao.addCompanyShareHolder(companyShareHolder);
			}
			
		}
		return dao.updateCompany(id,company);
	}
	public String deleteCompany(String id)
	{
		return dao.deleteCompany(id);
	}
	
	public String updateCompanyMeter(Meter meter) {
		return dao.updateCompanyMeter(meter);
	}
	
	public String updateCompanyName(String id,Company company) {
		return dao.updateCompany(id, company);
	}
	

	public ServiceContactInfo getServiceContactInfoById(@PathVariable(value="companyServiceId") String companyServiceId)throws OpenAccessException{
		return serviceContactInfoRepository.findById(companyServiceId).get();
		
	}
	public ServiceContactInfo validateServiceContactInfo(@PathVariable(value = "companyServiceId") int companyServiceId, @RequestBody Service serviceInput) throws OpenAccessException {
		
		Company companyFromDb = dao.getCompanyById(serviceInput.getCompanyId());
		if(companyFromDb != null) {
			companyFromDb.setCompanyTypeCode(serviceInput.getCompanyTypeCode());
			companyFromDb.setPan(serviceInput.getPan());
			companyFromDb.setCin(serviceInput.getCin());
			companyFromDb.setLlpin(serviceInput.getLlpin());
			companyFromDb.setGst(serviceInput.getGst());
			companyFromDb.setTan(serviceInput.getTan());
			dao.updateCompany(serviceInput.getCompanyId(),companyFromDb);
		}
		
		ServiceContactInfo contactInfoFromDb = getServiceContactInfoById(companyServiceId+"");
		contactInfoFromDb.setContactFullName(serviceInput.getContactFullName());
		contactInfoFromDb.setContactDesignation(serviceInput.getContactDesignation());
		contactInfoFromDb.setContactEmail(serviceInput.getContactEmail());
		contactInfoFromDb.setContactPhoneNo(serviceInput.getContactPhoneNo());
		contactInfoFromDb.setRegOfficeAddr(serviceInput.getRegOfficeAddr());
		contactInfoFromDb.setPlantAddr(serviceInput.getPlantAddr());
		contactInfoFromDb.setIsContactVerified("Y");
		contactInfoFromDb.setContactLastVerifiedDate(LocalDateTime.now());
		
		contactInfoFromDb.setBankAccountNo(serviceInput.getBankAccountNo());
		contactInfoFromDb.setBankIfscCode(serviceInput.getBankIfscCode());
		contactInfoFromDb.setBankName(serviceInput.getBankName());
		return serviceContactInfoRepository.save(contactInfoFromDb);

	}
}
