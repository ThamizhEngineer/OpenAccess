package com.ss.oa.generationreportsummary;


import org.springframework.data.repository.CrudRepository;
import com.ss.oa.report.vo.GenerationReportSummary;


public interface GenerationReportSummaryRepo extends CrudRepository<GenerationReportSummary, String>{
	
}
