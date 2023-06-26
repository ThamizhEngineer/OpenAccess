package com.ss.oa.esisummary;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.report.vo.EsiSummary;



public interface EsiSummaryRepository extends PagingAndSortingRepository<EsiSummary, String>{
	
	
	@Query("SELECT new com.ss.oa.report.vo.EsiSummary(esi.id,esi.powerSaleId,esi.processTypeCode,esi.genCompServId,esi.saleTypeCode,esi.fromDt,"
			+ "esi.toDt,esi.applnDt,esi.proposedQuantum,esi.agmtPeriodCode,esi.statusCode,esi.genCompId,esi.genCompServNumber,esi.fuelTypeCode,"
			+ "esi.genEndOrgId,esi.genVoltageCode,esi.createdDate,esi.month,esi.year,esi.genEndOrgName,esi.genCompName) from EsiSummary esi WHERE NVL(LOWER(esi.id),'0') LIKE LOWER(CONCAT('%',NVL(:id,''),'%')) AND "
			+ "NVL(LOWER(esi.genCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServId,''),'%')) AND NVL(LOWER(esi.saleTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:saleTypeCode,''),'%')) AND "
			+ "NVL(LOWER(esi.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) AND NVL(LOWER(esi.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) AND "
			+ "NVL(LOWER(esi.genEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:genEndOrgId,''),'%')) AND NVL(LOWER(esi.genVoltageCode),'0') LIKE LOWER(CONCAT('%',NVL(:genVoltageCode,''),'%')) AND "
			+ "NVL(LOWER(esi.genCompId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompId,''),'%')) AND NVL(LOWER(esi.agmtPeriodCode),'0') LIKE LOWER(CONCAT('%',NVL(:agmtPeriodCode,''),'%'))")
	Page<EsiSummary> getEsiQuery(Pageable pageable,@Param("id")String id,@Param("genCompServId")String genCompServId,@Param("saleTypeCode")String saleTypeCode,
			@Param("statusCode")String statusCode,@Param("fuelTypeCode")String fuelTypeCode,@Param("genEndOrgId")String genEndOrgId,@Param("genVoltageCode")String genVoltageCode,
			@Param("genCompId")String genCompId,@Param("agmtPeriodCode")String agmtPeriodCode);
     
}
