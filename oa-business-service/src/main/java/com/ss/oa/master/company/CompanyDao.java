package com.ss.oa.master.company;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.master.vo.CompanyShareHolder;
import com.ss.oa.master.vo.Meter;
@Scope("prototype")
public interface CompanyDao {
	public abstract List<Company> getAllCompanies(Map<String, String> criteria) throws Exception;
	public abstract List<CompanyServiceVO> getAllCompanyServices(Map<String, String> criteria) throws Exception;
	public abstract Company getCompanyById(String id);
	public abstract String addCompany(Company company);
	public abstract String updateCompany(String id,Company company);
	public abstract String deleteCompany(String id);
	public abstract String addCompanyService(CompanyServiceVO companyServiceVO);
	public abstract String addCompanyMeter(Meter meter);
	public abstract String addCompanyShareHolder(CompanyShareHolder companyShareHolder);
	public abstract String updateCompanyService(CompanyServiceVO companyServiceVO);
	public abstract String updateCompanyMeter(Meter meter);
	public abstract String updateCompanyShareHolder(CompanyShareHolder companyShareHolder);
}
