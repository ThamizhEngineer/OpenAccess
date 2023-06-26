package com.ss.oa.applicationsummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.ApplicationOaaSearch;
import com.ss.oa.report.vo.ApplicationSearchSummary;
import com.ss.oa.report.vo.ApplicationSummary;


public interface ApplicationSummaryRepository extends CrudRepository<ApplicationSummary, String>{

	Iterable<ApplicationSummary> findByProcessTypeCode(String processTypeCode);

	Iterable<ApplicationSummary> findByPowerSaleId(String powerSaleId);

	Iterable<ApplicationSummary> findByConsumerCompServId(String consumerCompServId);

	Iterable<ApplicationSummary> findByGenCompServId(String powerSaleId);


	
    @Query("SELECT new com.ss.oa.report.vo.ApplicationSummary(ps.id,ps.processTypeCode,ps.saleTypeCode,ps.fuelTypeCode,ps.agmtPeriodCode,ps.oaaId,ps.powerSaleId,ps.genCompServId,ps.genCompServNumber,ps.genEndOrgId,ps.genVoltageCode,ps.fromDt,ps.toDt,ps.genCapacity,ps.proposedQuantum,ps.applnDt,ps.consumerCompServId,ps.consumerEndOrgId,ps.createdDate,ps.modifiedDate,ps.statusCode) "
    		+ "FROM ApplicationSummary ps WHERE NVL(LOWER(ps.powerSaleId),'0') LIKE LOWER(CONCAT('%',NVL(:powerSaleId,''),'%')) AND NVL(LOWER(ps.genCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServId,''),'%')) "
    		+ "AND NVL(LOWER(ps.genCompServNumber),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServNumber,''),'%')) AND NVL(LOWER(ps.genEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:genEndOrgId,''),'%')) "
    		+ "AND NVL(LOWER(ps.consumerCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:consumerCompServId,''),'%')) AND NVL(LOWER(ps.consumerEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:consumerEndOrgId,''),'%')) "
    		+ "AND NVL(LOWER(ps.processTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:processTypeCode,''),'%'))")
	List<ApplicationSummary> getPowerSales(@Param("powerSaleId")String powerSaleId,@Param("genCompServId")String genCompServId,@Param("genCompServNumber")String genCompServNumber,
			@Param("genEndOrgId")String genEndOrgId,@Param("consumerCompServId")String consumerCompServId,@Param("consumerEndOrgId")String consumerEndOrgId,@Param("processTypeCode")String processTypeCode);

    @Query("SELECT new com.ss.oa.report.vo.ApplicationSearchSummary(saleTypeCode,statusCode,COUNT(statusCode) as recCount)"
    		+ "FROM ApplicationSummary WHERE NVL(LOWER(processTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:processTypeCode,''),'%')) AND NVL(LOWER(month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND "
    		+ "NVL(LOWER(year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(genCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServId,''),'%')) GROUP BY saleTypeCode,statusCode")
    Iterable<ApplicationSearchSummary> findAppSearchSummary(@Param("processTypeCode")String processTypeCode,@Param("month")String month,@Param("year")String year,@Param("genCompServId")String genCompServId);
    
    @Query("SELECT new com.ss.oa.report.vo.ApplicationOaaSearch(agmtPeriodCode,statusCode,COUNT(statusCode) as recCount)"
    		+ "FROM ApplicationSummary WHERE NVL(LOWER(processTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:processTypeCode,''),'%')) GROUP BY agmtPeriodCode,statusCode")
    Iterable<ApplicationOaaSearch> findAppOaaSearch(@Param("processTypeCode")String processTypeCode);
}
