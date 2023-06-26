package com.ss.oa.report.reportservice;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.report.vo.Report;
@Scope("prototype")
public interface ReportServiceDao {
	public abstract List<Report>getGenerationPerOrg(Map<String,String> criteria);
	public abstract List<Report>getGenerationPerService(Map<String,String> criteria);
	public abstract List<Report>fetchGeneratorBasedConsumerUsage(Map<String,String> criteria);


}
