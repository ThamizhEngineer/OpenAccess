package com.ss.oa.integration.samastNocReading;

import java.util.List;
import java.util.Map;

import com.ss.oa.vo.ExtSamastAppln;

public interface ExtSamstApplnDao {

	List<ExtSamastAppln> getSamastNocByStatus(String fromDate,String Todate);
	
	List<ExtSamastAppln> getSamastNocByApprovalStatus(String fromDt,String toDt);

}
