package com.ss.oa.generationsummary;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.report.vo.GenerationSummary;

public interface GenerationSummaryRepository extends CrudRepository<GenerationSummary, String>{
	
	Optional<GenerationSummary> findById(String id);

	Iterable<GenerationSummary> findByOrgId(String orgId);

	Iterable<GenerationSummary> findBySellerServiceId(String sellerServiceId);

	Iterable<GenerationSummary> findBycompanyId(String companyId);

	Iterable<GenerationSummary> findByFuelCode(String fuelCode);

	Iterable<GenerationSummary> findByVoltageCode(String voltageCode);

    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.orgId,gs.orgName,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,gs.month,gs.year,gs.flowTypeCode,gs.isRec) \n" + 
    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%'))\n"
    		+ "GROUP BY gs.orgId,gs.orgName,gs.month,gs.year,gs.flowTypeCode,gs.isRec")
    List<GenerationSummary> orgWiseGeneration(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode);
    
	    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.orgId,gs.orgName,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,gs.month,gs.year,gs.flowTypeCode,gs.isRec,SUM(cast(COALESCE(gs.totalImportGeneration,0) as long)) as totalImportUnits,SUM(cast(COALESCE(gs.totalExportGeneration,0) as long)) as totalExportUnits,SUM(cast(COALESCE(gs.machineCapacity,0) as long)) as totalMachineCapacity,gs.fuelCode,gs.fuelName,COUNT(gs.sellerServiceNumber) as recCount,gs.dispFuelTypeGroup) \n" + 
	    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%')) AND LOWER(gs.isRec) LIKE LOWER(CONCAT('%',:isRec, '%')) AND LOWER(gs.orgId) LIKE LOWER(CONCAT('%',:orgId, '%')) AND LOWER(gs.dispFuelTypeGroup) LIKE LOWER(CONCAT('%',:dispFuelTypeGroup, '%'))\n"
	    		+ "GROUP BY gs.orgId,gs.orgName,gs.month,gs.year,gs.flowTypeCode,gs.isRec,gs.fuelCode,gs.fuelName,gs.dispFuelTypeGroup order by gs.orgId")
	    List<GenerationSummary> formedReport(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode,@Param("isRec")String isRec,@Param("orgId")String orgId,@Param("dispFuelTypeGroup")String dispFuelTypeGroup);
	    
	    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.orgId,gs.orgName,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,gs.month,gs.year,gs.flowTypeCode,gs.isRec,SUM(cast(COALESCE(gs.totalImportGeneration,0) as long)) as totalImportUnits,SUM(cast(COALESCE(gs.totalExportGeneration,0) as long)) as totalExportUnits,SUM(cast(COALESCE(gs.machineCapacity,0) as long)) as totalMachineCapacity,gs.fuelCode,gs.fuelName,gs.plantClassTypeCode, COUNT(gs.sellerServiceNumber) as recCount) \n" + 
	    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%')) AND LOWER(gs.isRec) LIKE LOWER(CONCAT('%',:isRec, '%')) AND LOWER(gs.orgId) LIKE LOWER(CONCAT('%',:orgId, '%')) AND LOWER(gs.fuelCode) LIKE LOWER(CONCAT('%',:fuelCode, '%')) AND LOWER(gs.plantClassTypeCode) LIKE LOWER(CONCAT('%',:plantClassTypeCode, '%'))\n"
	    		+ "GROUP BY gs.orgId,gs.orgName,gs.month,gs.year,gs.flowTypeCode,gs.isRec,gs.fuelCode,gs.fuelName,gs.plantClassTypeCode order by gs.orgId")
	    List<GenerationSummary> formedForStb(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode,@Param("isRec")String isRec,@Param("orgId")String orgId,@Param("fuelCode")String fuelCode,@Param("plantClassTypeCode")String plantClassTypeCode);
	    
    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.month,gs.year,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,gs.flowTypeCode) \n" + 
    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%'))\n"
    		+ "GROUP BY gs.month,gs.year,gs.flowTypeCode")
    List<GenerationSummary> flowTypeWiseGeneration(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode);
    
    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.month,gs.year,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,gs.flowTypeCode,gs.isRec) \n" + 
    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%'))\n"
    		+ "GROUP BY gs.month,gs.year,gs.flowTypeCode,gs.isRec")
    List<GenerationSummary> flowWithRec(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode);
    
    
    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.orgId,gs.orgName,gs.month,gs.year,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits) FROM GenerationSummary gs where LOWER(gs.orgId) LIKE LOWER(CONCAT('%',:orgId, '%')) GROUP BY gs.orgId,gs.orgName,gs.month,gs.year")
    List<GenerationSummary> monthWiseGeneration(@Param("orgId")String orgId);
    
    @Query("SELECT new com.ss.oa.report.vo.GenerationSummary(gs.orgId,gs.month,gs.year,SUM(cast(COALESCE(gs.netGeneration,0) as long)) as totalUnits,SUM(cast(COALESCE(gs.totalImportGeneration,0) as long)) as totalImportUnits,SUM(cast(COALESCE(gs.totalExportGeneration,0) as long)) as totalExportUnits) \n" + 
    		"FROM GenerationSummary gs where LOWER(gs.month) LIKE LOWER(CONCAT('%',:month, '%')) AND LOWER(gs.year) LIKE LOWER(CONCAT('%',:year, '%')) AND LOWER(gs.flowTypeCode) LIKE LOWER(CONCAT('%',:flowTypeCode, '%')) AND LOWER(gs.isRec) LIKE LOWER(CONCAT('%',:isRec, '%'))\n"
    		+ "GROUP BY gs.orgId,gs.month,gs.year")
    List<GenerationSummary> generationWise(@Param("month")String month,@Param("year")String year,@Param("flowTypeCode")String flowTypeCode,@Param("isRec")String isRec);
    


}
