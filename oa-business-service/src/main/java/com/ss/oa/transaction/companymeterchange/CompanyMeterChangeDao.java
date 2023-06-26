package com.ss.oa.transaction.companymeterchange;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.CompanyMeterChange;

@Scope("prototype")
public interface CompanyMeterChangeDao {
	
	public abstract List<CompanyMeterChange> getAllCompanyMeterChanges(Map<String,String> criteria);
	public abstract CompanyMeterChange getCompanyMeterChangeById(String id);
	public abstract String addCompanyMeterChange(CompanyMeterChange companyMeterChange);
	public abstract String updateCompanyMeterChange(CompanyMeterChange companyMeterChange);
}
