package com.ss.oa.transaction.companymeterchange;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.Meter;
import com.ss.oa.transaction.vo.CompanyMeterChange;

@Scope("prototype")
@Component
public class CompanyMeterChangeService {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired	
	CompanyMeterChangeDao dao;

	public List<CompanyMeterChange> getAllCompanyMeterChanges(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.getAllCompanyMeterChanges(criteria);
	}


	public CompanyMeterChange getCompanyMeterChangeById(String id) {
		// TODO Auto-generated method stub
		return dao.getCompanyMeterChangeById(id);
	}

	public String addCompanyMeterChange(CompanyMeterChange companyMeterChange) {
		// TODO Auto-generated method stub
		companyMeterChange.setStatusCode("CREATED");
		return dao.addCompanyMeterChange(companyMeterChange);
	}


	public String updateCompanyMeterChange(CompanyMeterChange companyMeterChange) {
		// TODO Auto-generated method stub
		return dao.updateCompanyMeterChange(companyMeterChange);
	}
	
	public String approveCompanyMeterChange(CompanyMeterChange companyMeterChange) {
System.out.println(companyMeterChange);
		Company company = companyService.getCompanyById(companyMeterChange.getCompanyId());
		for(Meter meter : company.getMeters()) {
			if(companyMeterChange.getIsMeterNumberChange()!=null && companyMeterChange.getIsMeterNumberChange().equals("Y")) {
				if(companyMeterChange.getNewMeterNumber()!=null) {
					meter.setMeterNumber(companyMeterChange.getNewMeterNumber());
				}
				if(companyMeterChange.getNewMeterMakeCode()!=null) {
					meter.setMeterMakeCode(companyMeterChange.getNewMeterMakeCode());
				}
				if(companyMeterChange.getNewIsAbtMeter()!=null) {
					meter.setIsAbtMeter(companyMeterChange.getNewIsAbtMeter());
				}
				if(companyMeterChange.getNewMf()!=null) {
					meter.setMultiplicationFactor(companyMeterChange.getNewMf());
				}
				if(companyMeterChange.getNewAccuracyClassCode()!=null) {
					meter.setAccuracyClassCode(companyMeterChange.getNewAccuracyClassCode());
				}
	
				
			}
			if(companyMeterChange.getIsMeterSetChange()!=null && companyMeterChange.getIsMeterSetChange().equals("Y")) {
				if(companyMeterChange.getNewMeterCt1()!=null) {
					meter.setMeterCt1(companyMeterChange.getNewMeterCt1());
				}
				if(companyMeterChange.getNewMeterCt2()!=null) {
					meter.setMeterCt2(companyMeterChange.getNewMeterCt2());
				}
				if(companyMeterChange.getNewMeterCt3()!=null) {
					meter.setMeterCt3(companyMeterChange.getNewMeterCt3());
				}
				if(companyMeterChange.getNewMeterPt1()!=null) {
					meter.setMeterPt1(companyMeterChange.getNewMeterPt1());
				}
				if(companyMeterChange.getNewMeterPt2()!=null) {
					meter.setMeterPt2(companyMeterChange.getNewMeterPt2());
				}
				if(companyMeterChange.getNewMeterPt3()!=null) {
					meter.setMeterPt3(companyMeterChange.getNewMeterPt3());
				}
		
				
			}
			if(companyMeterChange.getIsModemNumberChange()!=null && companyMeterChange.getIsModemNumberChange().equals("Y")) {
				if(companyMeterChange.getNewModemNumber()!=null) {
					meter.setModemNumber(companyMeterChange.getNewModemNumber());
				}
				
			}
		
			companyService.updateCompanyMeter(meter);			
		}
		companyMeterChange.setStatusCode("APPROVED");
	return dao.updateCompanyMeterChange(companyMeterChange);
	}

}
