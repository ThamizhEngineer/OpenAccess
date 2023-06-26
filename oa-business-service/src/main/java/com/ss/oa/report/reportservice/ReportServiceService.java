package com.ss.oa.report.reportservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.report.vo.Report;
@Component
@Scope("prototype")
public class ReportServiceService {
	
	@Autowired
	ReportServiceDao dao;
	
	public List<Report> getGenerationPerOrg(Map<String, String> criteria) {
	
		return dao.getGenerationPerOrg(criteria);
	}

	
	public List<Report> getGenerationPerService(Map<String, String> criteria) {

		return dao.getGenerationPerService(criteria);
	}
	
	
	public List<Report> fetchGeneratorBasedConsumerUsage(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		return dao.fetchGeneratorBasedConsumerUsage(criteria);
	}
	
}
