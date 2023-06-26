package com.ss.oa.transaction.substationloss;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import com.ss.oa.transaction.vo.SubstationLoss;

@Scope("prototype")
public interface SubstationLossRepository extends CrudRepository<SubstationLoss, String> {
	
	
    @Query("SELECT new com.ss.oa.transaction.vo.SubstationLoss(ss.id, ss.orgId, ss.orgName, ss.substationId, ss.substationName,ss.lossPercent, ss.batchKey, ss.month, ss.year, ss.bulkMeterReading,\n" + 
    		"ss.totalAllWegs, ss.enabled, ss.createdBy, ss.createdDt) FROM SubstationLoss  ss WHERE NVL(LOWER(ss.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(ss.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) AND "
    		+ "NVL(LOWER(ss.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%'))")
    List<SubstationLoss> getSubstationLoss(@Param("month")String month,@Param("year")String year,@Param("orgId")String orgId);
    
    public Boolean existsByFeederIdAndMonthAndYear(String feederId, String month, String year);

}
