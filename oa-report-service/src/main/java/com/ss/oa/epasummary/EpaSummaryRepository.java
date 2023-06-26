package com.ss.oa.epasummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ss.oa.report.vo.EpaSummary;

public interface EpaSummaryRepository extends PagingAndSortingRepository<EpaSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.EpaSummary(epa.id,epa.powerSaleId,epa.processTypeCode,epa.code,epa.genCompServId,"
			+ "epa.saleTypeCode,epa.fromDt,epa.toDt,epa.applnDt,epa.agmtPeriodCode,epa.statusCode,epa.genCompId,epa.genCompServNumber,"
			+ "epa.fuelTypeCode,epa.genEndOrgId,epa.genVoltageCode,epa.createdDate,epa.modifiedDate,epa.consumerCompServId,epa.consumerCompServNumber,"
			+ "epa.consumerCompId,epa.consumerEndOrgId,epa.consumerVoltageCode,epa.month,epa.year) from EpaSummary epa where "
			+ "NVL(LOWER(epa.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and NVL(LOWER(epa.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(epa.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<EpaSummary> epaQuery(Pageable pageable,@Param("month")String month,@Param("year")String year,@Param("statusCode")String statusCode);
	
}
