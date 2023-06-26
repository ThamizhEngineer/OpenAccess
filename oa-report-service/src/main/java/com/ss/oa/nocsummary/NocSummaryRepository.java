package com.ss.oa.nocsummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ss.oa.report.vo.NocSummary;


public interface NocSummaryRepository extends PagingAndSortingRepository<NocSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.NocSummary(noc.id,noc.powerSaleId,noc.processTypeCode,noc.code,noc.consumerCompServId,"
			+ "noc.saleTypeCode,noc.applnDt,noc.proposedQuantum,noc.agmtPeriodCode,noc.statusCode,noc.consumerCompId,noc.consumerCompServNumber,"
			+ "noc.fuelTypeCode,noc.consumerEndOrgId,noc.consumerVoltageCode,noc.month,noc.year,noc.consumerEndOrgName ,noc.consumerCompName) From NocSummary noc WHERE NVL(LOWER(noc.id),'0') LIKE LOWER(CONCAT('%',NVL(:id,''),'%')) AND "
			+ "NVL(LOWER(noc.consumerCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:consumerCompServId,''),'%')) AND NVL(LOWER(noc.saleTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:saleTypeCode,''),'%')) AND "
			+ "NVL(LOWER(noc.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) AND NVL(LOWER(noc.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) AND "
			+ "NVL(LOWER(noc.consumerEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:consumerEndOrgId,''),'%')) AND NVL(LOWER(noc.consumerVoltageCode),'0') LIKE LOWER(CONCAT('%',NVL(:consumerVoltageCode,''),'%')) AND "
			+ "NVL(LOWER(noc.consumerCompId),'0') LIKE LOWER(CONCAT('%',NVL(:consumerCompId,''),'%')) AND NVL(LOWER(noc.agmtPeriodCode),'0') LIKE LOWER(CONCAT('%',NVL(:agmtPeriodCode,''),'%')) and "
			+ "NVL(LOWER(noc.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(noc.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<NocSummary> getNocQuery(Pageable pageable,@Param("id")String id,@Param("consumerCompServId")String consumerCompServId,@Param("saleTypeCode")String saleTypeCode,
			@Param("statusCode")String statusCode,@Param("fuelTypeCode")String fuelTypeCode,@Param("consumerEndOrgId")String consumerEndOrgId,@Param("consumerVoltageCode")String consumerVoltageCode,
			@Param("consumerCompId")String consumerCompId,@Param("agmtPeriodCode")String agmtPeriodCode,@Param("month")String month,@Param("year")String year);

}
