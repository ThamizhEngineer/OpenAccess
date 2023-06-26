package com.ss.oa.srcpreport;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.SrcpReport;

public interface SrcpReportRepository extends CrudRepository<SrcpReport, Integer> {
	
	@Query("SELECT new com.ss.oa.report.vo.SrcpReport(srcp.id,srcp.totalCapacitySum,srcp.totalGenerationSum,srcp.ncesDivisionCode,srcp.noOfWeg,srcp.month,srcp.year,srcp.installedBy)"
			+ "FROM SrcpReport srcp WHERE NVL(LOWER(srcp.ncesDivisionCode),'0') LIKE LOWER(CONCAT('%',NVL(:ncesDivisionCode,''),'%')) AND NVL(LOWER(srcp.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) "
			+ "AND NVL(LOWER(srcp.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) ")
	List<SrcpReport> getSrcp(@Param ("ncesDivisionCode")String ncesDivisionCode,
			@Param("month")String month,@Param("year")String year);
}
