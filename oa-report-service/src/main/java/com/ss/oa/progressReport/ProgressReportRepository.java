package com.ss.oa.progressReport;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.oa.report.vo.ProgressReport;

public interface ProgressReportRepository extends CrudRepository<ProgressReport, String> {
	
	List<ProgressReport> findByMonth(String month);
	List<ProgressReport> findByYear(String year);
	List<ProgressReport> findByMonthAndYear(String month,String year);

}
