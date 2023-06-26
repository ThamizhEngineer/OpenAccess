package com.ss.oa.report.progressreport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.FlowType;

@RestController
public class ProgressReportRestService extends ProgressReportService{
	
	@Autowired
	ProgressReportService progressReportService;
	
	@RequestMapping(value="/getreport", method = RequestMethod.GET)
	public List<FlowType> getRprt()
	{
		return progressReportService.getReport();
	}
}
