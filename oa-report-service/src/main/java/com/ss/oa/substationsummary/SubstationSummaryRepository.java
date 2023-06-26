package com.ss.oa.substationsummary;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.SubstationSummary;


public interface SubstationSummaryRepository extends CrudRepository<SubstationSummary, String>{
    @Query("SELECT new com.ss.oa.report.vo.SubstationSummary(ss.id,ss.code,ss.name,ss.orgId,ss.typeOfSs) FROM SubstationSummary ss WHERE NVL(LOWER(ss.orgId),'') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%'))"
    		+ "AND NVL(LOWER(ss.typeOfSs),'0') LIKE LOWER(CONCAT('%',NVL(:typeOfSs, ''),'%'))")
    List<SubstationSummary> getSubstation(@Param("orgId")String orgId,@Param("typeOfSs")String typeOfSs);
        
}
