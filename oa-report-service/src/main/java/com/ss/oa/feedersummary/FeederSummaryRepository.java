package com.ss.oa.feedersummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.FeederSummary;


public interface FeederSummaryRepository extends CrudRepository<FeederSummary, String>{
	
    @Query("SELECT new com.ss.oa.report.vo.FeederSummary(ff.id,ff.code,ff.name,ff.voltage,ff.substationId) FROM FeederSummary ff WHERE NVL(LOWER(ff.substationId),'') LIKE LOWER(CONCAT('%',NVL(:substationId,''),'%'))")
    List<FeederSummary> getFeeder(@Param("substationId")String substationId);

}
