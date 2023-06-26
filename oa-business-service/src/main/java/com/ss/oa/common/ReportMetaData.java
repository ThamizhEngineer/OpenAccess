package com.ss.oa.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.report.vo.GenericReport;
@Scope("prototype")
public class ReportMetaData {

	public List<GenericReport> getGenericReports() {

		List<GenericReport> reportingQueries = new ArrayList<GenericReport>();
		Map<String, String> criteria = new HashMap<String, String>();

		GenericReport rep = new GenericReport();
		rep.setReportName("Emp-Select");
		rep.setSqlQuery("select * from employee where empid like '##ip1##'");
		criteria.put("ip1", "%");
		rep.setCriteria(criteria);
		reportingQueries.add(rep);

		rep = new GenericReport();
		criteria.clear();
		rep.setReportName("Org-Select");
		rep.setSqlQuery("select * from M_ORG where id like '##ip1##' and type_code like '##ip2##'");
		criteria.put("ip1", "%");
		criteria.put("ip2", "%");
		rep.setCriteria(criteria);
		reportingQueries.add(rep);

		return reportingQueries;
	}
}