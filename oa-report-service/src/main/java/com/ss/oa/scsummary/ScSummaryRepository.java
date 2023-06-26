package com.ss.oa.scsummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.report.vo.ScSummary;

public interface ScSummaryRepository extends PagingAndSortingRepository<ScSummary, String>{

	
	
	@Query("SELECT new com.ss.oa.report.vo.ScSummary(sc.id,sc.powerSaleId,sc.processTypeCode,sc.code,sc.consumerCompServId,sc.saleTypeCode,"
			+ "sc.fromDt,sc.toDt,sc.applnDt,sc.statusCode,sc.consumerCompId,sc.consumerCompServNumber,sc.consumerEndOrgId,sc.consumerVoltageCode,"
			+ "sc.createdDate,sc.modifiedDate,sc.month,sc.year) from ScSummary sc where "
			+ "NVL(LOWER(sc.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and NVL(LOWER(sc.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(sc.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<ScSummary> scQuery(Pageable pageable,@Param("month")String month,@Param("year")String year,
			@Param("statusCode")String statusCode);
	
}
