package com.ss.oa.transaction.energyallotment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.oa.transaction.vo.EnergyAllotment;
import com.ss.oa.transaction.vo.EnergyAllotmentView;

public interface EnergyAllotmentRepository extends CrudRepository <EnergyAllotment, String>{
	
    Iterable <EnergyAllotment>findBysellerCompanyServiceId(String companyServiceId);
    
    Iterable<EnergyAllotment> findBysellerCompanyServiceNumber(String sellerCompanyServiceNumber);
	
	Iterable <EnergyAllotment>findBymonth(String month);
	
	Iterable<EnergyAllotment>findByyear(String year);
	
	Iterable<EnergyAllotment>findBysimpleEnergySale(String simpleEnergySale);
	
	Iterable <EnergyAllotment>findBysellerCompanyServiceIdAndMonth(String companyServiceId,String month);
	
	Iterable<EnergyAllotment>findBysellerCompanyServiceIdAndMonthAndYear(String companyServiceId,String month,String year);
	
	Iterable<EnergyAllotment> findBysellerCompanyServiceNumberAndMonth(String sellerCompanyServiceNumber,String month);

	Iterable<EnergyAllotment> findBysellerCompanyServiceNumberAndMonthAndYear(String sellerCompanyServiceNumber,String month,String year);

	
	Optional <EnergyAllotment>findById(String id);
	
//	List <EnergyAllotment> save(String energyAllotment);
//	
//	public abstract EnergyAllotment getEnergyAllotmentById(String id);
//	
//	
	@Query("SELECT ea FROM EnergyAllotment ea\n" + 
		" WHERE NVL(LOWER(ea.sellerCompanyName),'0') LIKE LOWER(CONCAT('%',NVL(:companyname,''),'%')) AND NVL(LOWER(ea.sellerCompanyServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:servicenumber,''),'%'))AND NVL(LOWER(ea.sellerEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:edcid,''),'%'))"
	     +"AND NVL(LOWER(ea.sellerCompanyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyid,''),'%')) AND NVL(LOWER(ea.sellerCompanyServiceId),'0') LIKE LOWER(CONCAT('%',NVL(:serviceid ,''),'%')) AND NVL(LOWER(ea.isStb),'0') LIKE LOWER(CONCAT('%',NVL(:isstb,''),'%')) "
	    + "AND NVL(LOWER(ea.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(ea.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) AND NVL(LOWER(ea.simpleEnergySale),'0') LIKE LOWER(CONCAT('%',NVL(:simpleEnergySale,''),'%'))")
	    List<EnergyAllotment> getByServiceId(@Param("companyname") String sellerCompanyName,
			@Param("servicenumber") String sellerCompanyServiceNumber,
			@Param("edcid") String sellerEndOrgId,
			@Param("companyid") String sellerCompanyId,
			@Param("serviceid") String sellerCompanyServiceId,
			@Param("isstb") String isStb,
			@Param("month") String month,
		    @Param("year") String year,
			@Param("simpleEnergySale") String simpleEnergySale);
		
	@Query("SELECT new com.ss.oa.transaction.vo.EnergyAllotment(ea.id, ea.sellerCompanyServiceId, ea.sellerCompanyServiceNumber, ea.sellerEndOrgName, ea.sellerCompanyName, ea.month , ea.year , ea.injectingVoltageName , ea.netGeneration, ea.netAllocation, ea.netChargesAllocated, ea.totalBankUnitsUsed , ea.statusCode , ea.fuelGroupe , ea.createdDate ) FROM EnergyAllotment ea\n" +
			" WHERE NVL(LOWER(ea.sellerCompanyName),'0') LIKE LOWER(CONCAT('%',NVL(:companyname,''),'%')) AND NVL(LOWER(ea.sellerCompanyServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:servicenumber,''),'%'))AND NVL(LOWER(ea.sellerEndOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:edcid,''),'%'))"
		     +"AND NVL(LOWER(ea.sellerCompanyId),'0') LIKE LOWER(CONCAT('%',NVL(:companyid,''),'%')) AND NVL(LOWER(ea.sellerCompanyServiceId),'0') LIKE LOWER(CONCAT('%',NVL(:serviceid ,''),'%')) AND NVL(LOWER(ea.isStb),'0') LIKE LOWER(CONCAT('%',NVL(:isstb,''),'%')) "
		    + "AND NVL(LOWER(ea.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(ea.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) AND NVL(LOWER(ea.simpleEnergySale),'0') LIKE LOWER(CONCAT('%',NVL(:simpleEnergySale,''),'%')) AND NVL(LOWER(ea.fuelTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelTypeCode,''),'%')) AND NVL(LOWER(ea.flowTypeCode),'0') LIKE LOWER(CONCAT('%',NVL(:flowTypeCode,''),'%'))")
		    List<EnergyAllotment> findBySearchCriteria(@Param("companyname") String sellerCompanyName,
				@Param("servicenumber") String sellerCompanyServiceNumber,
				@Param("edcid") String sellerEndOrgId,
				@Param("companyid") String sellerCompanyId,
				@Param("serviceid") String sellerCompanyServiceId,
				@Param("isstb") String isStb,
				@Param("month") String month,
			    @Param("year") String year,
				@Param("simpleEnergySale") String simpleEnergySale,
		    	@Param("fuelTypeCode") String fuelTypeCode,
		    	@Param("flowTypeCode") String flowTypeCode);	
}
