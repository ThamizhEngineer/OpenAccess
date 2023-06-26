package com.ss.oa.transaction.companynamechange;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.CompanyNameChange;

@Scope("prototype")
public interface CompanyNameChangeDao {
	public abstract List<CompanyNameChange> getAllCompanyNameChanges(Map<String,String> criteria);
	public abstract CompanyNameChange getCompanyNameChangeById(String id);
	public abstract String addCompanyNameChange(CompanyNameChange companyNameChange);
	public abstract String updateCompanyNameChange(String id, CompanyNameChange companyNameChange);
}