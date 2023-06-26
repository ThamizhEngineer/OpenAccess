package com.ss.oa.report.genericreportingservice;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.report.vo.GenericReportHeaderOutput;
import com.ss.oa.report.vo.GenericReportOutput;
@Scope("prototype")
public interface GenericReportingDao{
	public List<GenericReportOutput> getGenericReportResult(String reportName, 
			Map<String, String> ipCriteria);
	public GenericReportHeaderOutput getGenericRepResult(String reportName, 
			Map<String, String> ipCriteria);
}