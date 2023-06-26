package com.ss.oa.master.company;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Company;
@Scope("prototype")
public interface CompanyRepository extends CrudRepository<Company, Integer>{
	
	@Query("SELECT new com.ss.oa.model.master.Company(company.id,company.code,company.name,company.companyTypeCode,company.companyTypeName,company.registrationNo,company.registrationDate,\n" + 
			"company.cobDate,company.incorpPlace,company.isCaptive,company.captivePurpose,company.pan,company.tan,company.gst,company.cst,\n" + 
			"company.enabled,company.remarks,company.isInternal,company.bankingServiceId,company.unadjustedServiceId,company.bankingServiceNumber,\n" + 
			"company.unadjustedServiceNumber,company.isBuyer,company.isSeller,company.createdDate,company.modifiedDate)\n" + 
			"FROM Company company")
	Iterable<Company> findAll();
	
	Company findById(String id);
	
	

}
