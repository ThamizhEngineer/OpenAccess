package com.ss.oa.consentsummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.report.vo.ConsentSummary;

public interface ConsentSummaryRepository extends PagingAndSortingRepository<ConsentSummary, String>{

	@Query("SELECT new com.ss.oa.report.vo.ConsentSummary(con.id,con.powerSaleId,con.processTypeCode,con.code,"
			+ "con.genCompServId,con.saleTypeCode,con.applnDt,con.statusCode,con.genCompId,con.genCompServNumber,"
			+ "con.fuelTypeCode,con.genEndOrgId,con.genVoltageCode,con.consumerCompServId,con.consumerCompId,con.consumerEndOrgId,"
			+ "con.consumerVoltageCode,con.createdDate,con.modifiedDate,con.month,con.year,con.consumerEndOrgName ,con.consumerCompName) FROM ConsentSummary con where NVL(LOWER(con.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and "
			+ "NVL(LOWER(con.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(con.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<ConsentSummary> consentQuery(Pageable pageable,@Param("month")String month,@Param("year")String year,@Param("statusCode")String statusCode);
	
}
