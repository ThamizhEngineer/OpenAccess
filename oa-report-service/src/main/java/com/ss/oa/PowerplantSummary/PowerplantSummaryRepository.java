package com.ss.oa.PowerplantSummary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.PowerplantSummary;



public interface PowerplantSummaryRepository extends CrudRepository<PowerplantSummary, String>{
Optional<PowerplantSummary> findById(String id);

@Query("SELECT new com.ss.oa.report.vo.PowerplantSummary(pp.id,pp.powerplantName,pp.orgId,pp.orgCode,pp.orgName,pp.companyId,pp.companyName,pp.sellerServiceId,pp.sellerServiceNumber, \n" + 
		"pp.voltageCode,pp.voltageName,pp.fuelCode,pp.fuelName,pp.windPassCode,pp.windPassName,pp.totalCapacity,pp.approvedCapacity,pp.commissionDate,pp.ncesDivisionCode,pp.ncesDesc,pp.substationId,pp.substationName,pp.feederId,pp.feederName,pp.wegGroupCode,pp.wegGroupDesc,pp.meterNumber) \n" + 
		"FROM PowerplantSummary pp where NVL(LOWER(pp.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId, ''),'%')) AND NVL(LOWER(pp.sellerServiceId),'0') "
		+ "LIKE LOWER(CONCAT('%',NVL(:sellerServiceId, ''),'%')) "
		+ "AND NVL(LOWER(pp.companyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyId, ''),'%'))"
		+ "AND NVL(LOWER(pp.fuelCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelCode, ''),'%'))"
		+ "AND NVL(LOWER(pp.voltageCode),'0') LIKE LOWER(CONCAT('%',NVL(:voltageCode, ''),'%')) "
		+ "AND NVL(LOWER(pp.ncesDivisionCode),'0') LIKE LOWER(CONCAT('%',NVL(:ncesDivisionCode, ''),'%'))"
		+ "AND NVL(LOWER(pp.substationId),'0') LIKE LOWER(CONCAT('%',NVL(:substationId, ''),'%'))"
		+ "AND NVL(LOWER(pp.meterNumber),'0') LIKE LOWER(CONCAT('%',NVL(:meterNumber, ''),'%'))"
		+ "AND NVL(LOWER(pp.sellerServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:sellerServiceNumber, ''),'%'))")
List<PowerplantSummary> getPpOrgCount(@Param("orgId")String orgId,@Param("sellerServiceId")String sellerServiceId,
		@Param("companyId")String companyId,@Param("fuelCode")String fuelCode,@Param("voltageCode")String voltageCode,
		@Param("ncesDivisionCode")String ncesDivisionCode,@Param("substationId")String substationId,@Param("meterNumber")String meterNumber,
		@Param("sellerServiceNumber")String  sellerServiceNumber);


}
