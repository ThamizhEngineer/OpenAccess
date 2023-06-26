package com.ss.oa.report.vo;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
@Scope("prototype")
public class GenericReportHeaderOutput{
	
	private Map<Integer, String> headers;
	private List<GenericReportOutput> reportOutput;
	
	public Map<Integer, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<Integer, String> headers) {
		this.headers = headers;
	}
	public List<GenericReportOutput> getReportOutput() {
		return reportOutput;
	}
	public void setReportOutput(List<GenericReportOutput> reportOutput) {
		this.reportOutput = reportOutput;
	}
}