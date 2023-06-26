package com.ss.oa.ceereport;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.CeeReport;

public interface CeeReportRepository extends CrudRepository<CeeReport, Integer> {
	
	@Query("SELECT new com.ss.oa.report.vo.CeeReport(ce.id,ce.totalCapacitySum,ce.typeOfShare,ce.installedBy,ce.netGenerationSum,ce.month,ce.year,ce.fuelTypeCode,ce.fuelTypeName)"
			+ "FROM CeeReport ce WHERE NVL(LOWER(ce.typeOfShare),'0') LIKE LOWER(CONCAT('%',NVL(:typeOfShare,''),'%'))"
			+ "AND NVL(LOWER(ce.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))  AND NVL(LOWER(ce.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))"
			+ "AND NVL(LOWER(ce.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%'))")	
	List<CeeReport> getCee(@Param ("typeOfShare")String typeOfShare,@Param("month")String month,@Param("year")String year,@Param ("fuelTypeCode")String fuelTypeCode);			
}
