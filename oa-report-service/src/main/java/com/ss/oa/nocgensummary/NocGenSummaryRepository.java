package com.ss.oa.nocgensummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.NocGenSummary;


public interface NocGenSummaryRepository extends PagingAndSortingRepository<NocGenSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.NocGenSummary(nocgen.id,nocgen.powerSaleId,nocgen.processTypeCode,nocgen.code,"
			+ "nocgen.genCompServId,nocgen.saleTypeCode,nocgen.applnDt,nocgen.statusCode,nocgen.genCompId,nocgen.genCompServNumber,"
			+ "nocgen.fuelTypeCode,nocgen.genEndOrgId,nocgen.genVoltageCode,nocgen.month,nocgen.year) from NocGenSummary nocgen where NVL(LOWER(nocgen.id),'0') LIKE LOWER(CONCAT('%',NVL(:id,''),'%')) and "
			+ "NVL(LOWER(nocgen.processTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:processTypeCode,''),'%')) and NVL(LOWER(nocgen.genCompId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompId,''),'%')) and \n" + 
			"NVL(LOWER(nocgen.genCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServId,''),'%')) and NVL(LOWER(nocgen.saleTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:saleTypeCode,''),'%')) and \n" + 
			"NVL(LOWER(nocgen.genCompServNumber),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServNumber,''),'%')) and NVL(LOWER(nocgen.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and \n" + 
			"NVL(LOWER(nocgen.genVoltageCode),'0') LIKE LOWER(CONCAT('%',NVL(:genVoltageCode,''),'%')) and NVL(LOWER(nocgen.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) and \n" + 
			"NVL(LOWER(nocgen.genEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:genEndOrgId,''),'%')) and NVL(LOWER(nocgen.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) and NVL(LOWER(nocgen.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))")
	Page<NocGenSummary> getNocQuery(Pageable pageable,@Param("id")String id,@Param("processTypeCode")String processTypeCode,
			@Param("genCompId")String genCompId,@Param("genCompServId")String genCompServId,
			@Param("saleTypeCode")String saleTypeCode,@Param("genCompServNumber")String genCompServNumber,
			@Param("statusCode")String statusCode,@Param("genVoltageCode")String genVoltageCode,
			@Param("fuelTypeCode")String fuelTypeCode,@Param("genEndOrgId")String genEndOrgId,@Param("month")String month,@Param("year")String year);
}
