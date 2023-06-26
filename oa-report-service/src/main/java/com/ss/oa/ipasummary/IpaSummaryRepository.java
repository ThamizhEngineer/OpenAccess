package com.ss.oa.ipasummary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.IpaSummary;

public interface IpaSummaryRepository extends PagingAndSortingRepository<IpaSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.IpaSummary(ipa.id,ipa.powerSaleId,ipa.processTypeCode,ipa.code,ipa.genCompServId,ipa.saleTypeCode,"
			+ "ipa.fromDt,ipa.toDt,ipa.applnDt,ipa.proposedQuantum,ipa.agmtPeriodCode,ipa.statusCode,ipa.genCompId,ipa.genCompServNumber,"
			+ "ipa.fuelTypeCode,ipa.genEndOrgId,ipa.genVoltageCode,ipa.month,ipa.year) from IpaSummary ipa where NVL(LOWER(ipa.id),'0') LIKE LOWER(CONCAT('%',NVL(:id,''),'%')) and "
			+ "NVL(LOWER(ipa.processTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:processTypeCode,''),'%')) and NVL(LOWER(ipa.genCompServId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServId,''),'%')) and "
			+ "NVL(LOWER(ipa.saleTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:saleTypeCode,''),'%')) and NVL(LOWER(ipa.agmtPeriodCode),'0') LIKE LOWER(CONCAT('%',NVL(:agmtPeriodCode,''),'%')) and "
			+ "NVL(LOWER(ipa.statusCode),'0') LIKE LOWER(CONCAT('%',NVL(:statusCode,''),'%')) and NVL(LOWER(ipa.genCompId),'0') LIKE LOWER(CONCAT('%',NVL(:genCompId,''),'%')) and "
			+ "NVL(LOWER(ipa.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) and NVL(LOWER(ipa.genVoltageCode),'0') LIKE LOWER(CONCAT('%',NVL(:genVoltageCode,''),'%')) and "
			+ "NVL(LOWER(ipa.genCompServNumber),'0') LIKE LOWER(CONCAT('%',NVL(:genCompServNumber,''),'%')) and NVL(LOWER(ipa.genEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:genEndOrgId,''),'%')) and "
			+ "NVL(LOWER(ipa.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) and NVL(LOWER(ipa.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))")
	Page<IpaSummary> getIpaQuery(Pageable pageable,@Param("id")String id,@Param("processTypeCode")String processTypeCode,@Param("genCompServId")String genCompServId,@Param("saleTypeCode")String saleTypeCode,
			@Param("agmtPeriodCode")String agmtPeriodCode,@Param("statusCode")String statusCode,@Param("genCompId")String genCompId,
			@Param("fuelTypeCode")String fuelTypeCode,@Param("genVoltageCode")String genVoltageCode,
			@Param("genCompServNumber")String genCompServNumber,@Param("genEndOrgId")String genEndOrgId,@Param("month")String month,@Param("year")String year);

}
