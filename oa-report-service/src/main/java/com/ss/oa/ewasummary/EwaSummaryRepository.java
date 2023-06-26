package com.ss.oa.ewasummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.ss.oa.report.vo.EwaSummary;


public interface EwaSummaryRepository extends PagingAndSortingRepository<EwaSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.EwaSummary(ewa.id,ewa.powerSaleId,ewa.processTypeCode,ewa.code,"
			+ "ewa.genCompServId,ewa.saleTypeCode,ewa.fromDt,ewa.toDt,ewa.applnDt,ewa.agmtPeriodCode,ewa.statusCode,ewa.genCompId,"
			+ "ewa.genCompServNumber,ewa.fuelTypeCode,ewa.genEndOrgId,ewa.genVoltageCode,ewa.createdDate,ewa.modifiedDate,"
			+ "ewa.month,ewa.year,ewa.genEndOrgName,ewa.genCompName) from EwaSummary ewa where NVL(LOWER(ewa.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and "
			+ "NVL(LOWER(ewa.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and "
			+ "NVL(LOWER(ewa.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<EwaSummary> ewaQuery(Pageable Pageable,@Param("month")String month,@Param("year")String year,@Param("statusCode")String statusCode);

}
