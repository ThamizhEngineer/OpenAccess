package com.ss.oa.integration.samastNocReading;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.vo.ImpSamastAppLog;

public interface ImpSamastAppLogRepo extends CrudRepository<ImpSamastAppLog, Integer>{
	
	@Query("select lisa from com.ss.oa.vo.ImpSamastAppLog lisa WHERE lisa.appType =:appType AND ROWNUM <= 1 ORDER BY lisa.id DESC")
	ImpSamastAppLog findMax(@Param("appType")String appType);

}
