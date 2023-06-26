package com.ss.oa.transaction.processlog;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.ProcessLog;
import java.lang.String;
import java.util.List;

@Scope("prototype")
@Component
public interface ProcessLogRepository extends CrudRepository<ProcessLog,Integer> {
	
	List<ProcessLog> findByTEnergySaleId(String tenergysaleid);
	
	
	@Query("SELECT new com.ss.oa.transaction.vo.ProcessLog(pro.id,pro.processName,pro.logDt,pro.processStatus,"
			+ "pro.userId,pro.tEnergySaleId,pro.genCompId,pro.genServiceId,pro.genEdcId,pro.processId,pro.attr1,"
			+ "pro.attr2,pro.attr3,pro.createdBy,pro.createdDate,pro.modifiedBy,pro.modifiedDate,"
			+ "pro.remarks,pro.esiNumber) from ProcessLog pro where NOT(pro.processName ='CONSENT' OR pro.processName ='NOC') and "
			+ "NVL(LOWER(pro.tEnergySaleId),'0') LIKE LOWER(CONCAT('%',NVL(:tEnergySaleId,''),'%')) ORDER BY pro.logDt asc")
	List<ProcessLog> findOnlySeller(@Param("tEnergySaleId")String tEnergySaleId);
    

}
