package com.ss.oa.report.progressreport;


import java.util.List;

import com.ss.oa.report.vo.FlowType;

public interface ProgressReportDao {

	String getCountOfCaptive();

	List<FlowType> getCountOfFlowTypes();

}
