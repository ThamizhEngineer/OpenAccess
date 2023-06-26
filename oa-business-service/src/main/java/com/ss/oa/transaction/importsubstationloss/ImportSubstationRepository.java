package com.ss.oa.transaction.importsubstationloss;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.transaction.vo.ImportSubstationLoss;

@Scope("prototype")
public interface ImportSubstationRepository extends CrudRepository<ImportSubstationLoss, String> {
	
	 @Query("SELECT new com.ss.oa.transaction.vo.ImportSubstationLoss(imp.id, imp.orgId, imp.orgName, imp.substationId, imp.substationName,imp.lossPercent, imp.batchKey, imp.month, imp.year, imp.resultDesc, imp.bulkMeterReading,\n" + 
	 		"			imp.totalAllWegs, imp.isImported, imp.enabled, imp.createdBy, imp.createdDt,	imp.modifiedBy, imp.modifiedDt) FROM ImportSubstationLoss  imp WHERE NVL(LOWER(imp.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(imp.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) AND "
	    		+ "NVL(LOWER(imp.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%'))")
	    List<ImportSubstationLoss> getImportSubstationLoss(@Param("month")String month,@Param("year")String year,@Param("orgId")String orgId);
}
