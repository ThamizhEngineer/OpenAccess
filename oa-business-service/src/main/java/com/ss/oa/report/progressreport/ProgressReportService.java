package com.ss.oa.report.progressreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.oa.report.vo.FlowType;

@Component
public class ProgressReportService{
	@Autowired
	ProgressReportDao dao;
	public List<FlowType> getReport() {
		return dao.getCountOfFlowTypes();
	}
}
