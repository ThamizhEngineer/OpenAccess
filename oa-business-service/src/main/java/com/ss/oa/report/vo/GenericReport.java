package com.ss.oa.report.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class GenericReport{
	
	private String reportName;
	private String sqlQuery;
	private Map<String, String> criteria;
	
	public GenericReport(){
		super();
		defineCriteria();
	}
	
	private void defineCriteria() {
		this.criteria = new HashMap<String, String>();
		this.criteria.put("##ip1##", "%");
		this.criteria.put("##ip2##", "%");
		this.criteria.put("##ip3##", "%");
		this.criteria.put("##ip4##", "%");
		this.criteria.put("##ip5##", "%");
		this.criteria.put("##ip6##", "%");
		this.criteria.put("##ip7##", "%");
		this.criteria.put("##ip8##", "%");
		this.criteria.put("##ip9##", "%");
		this.criteria.put("##ip10##", "%");
	}

	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getSqlQuery() {
		return sqlQuery;
	}
	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
	public Map<String, String> getCriteria() {
		return criteria;
	}
	public void setCriteria(Map<String, String> criteria) {
		this.criteria = criteria;
	}
	
}