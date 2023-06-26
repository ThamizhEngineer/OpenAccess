package com.ss.oa.transaction.companynamechange;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.vo.Company;
import com.ss.oa.transaction.vo.CompanyNameChange;

@Scope("prototype")
@Component
public class CompanyNameChangeService {
	
	@Autowired
	CompanyNameChangeDao dao;
	
	@Autowired
	CompanyService companyService;
	
	
	String companyNameChangeId;
	
	public CompanyNameChangeService() {
		
		super();

	}
	
	public List<CompanyNameChange> getAllCompanyNameChanges(Map<String,String> criteria){
		
		return dao.getAllCompanyNameChanges(criteria);
	}
	
	public CompanyNameChange getCompanyNameChangeById(String id) {
		
		return dao.getCompanyNameChangeById(id);
	}
	
	public String addCompanyNameChange(CompanyNameChange companyNameChange) {
		companyNameChangeId = dao.addCompanyNameChange(companyNameChange);
		System.out.println("companyNameChangeid" + companyNameChangeId);
		return companyNameChangeId;

			
		}
	
	public String updateCompanyNameChange(String id,CompanyNameChange companyNameChange) {
		companyNameChangeId = dao.updateCompanyNameChange(id,  companyNameChange);
		return companyNameChangeId;
	}
	
	public String approveCompanyNameChange(String id,CompanyNameChange companyNameChange) {
		
		Company company = companyService.getCompanyById(companyNameChange.getCompanyId());
		System.out.println(company);
		if(companyNameChange.getNewCompanyName()!=null) {
			company.setName(companyNameChange.getNewCompanyName());
			System.out.println(company);
			companyService.updateCompanyName(companyNameChange.getCompanyId(), company);	
			companyNameChange.setStatusCode("APPROVED");
			companyNameChangeId = dao.updateCompanyNameChange(id, companyNameChange);
		
		}
		
	
		return companyNameChangeId;
	
	}

}
