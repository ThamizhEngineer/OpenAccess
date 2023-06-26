package com.ss.oa.transaction.sldcNoc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.transaction.vo.SldcNoc;
import com.ss.oa.transaction.vo.SldcNocLine;

@Scope("prototype")
public interface SldcNocRepo extends CrudRepository<SldcNoc, String>{
	List<SldcNoc> findByNocPurpose(String purpose);
	
	@Query("select sldc from com.ss.oa.transaction.vo.SldcNoc sldc where NVL(LOWER(sldc.compServNo),'') LIKE LOWER(CONCAT('%',NVL(:compServNo,''),'%'))"
			+ "AND NVL(LOWER(sldc.edcId),'') LIKE LOWER(CONCAT('%',NVL(:edcId,''),'%'))"
			+ "AND NVL(LOWER(sldc.cntrctDemand),'') LIKE LOWER(CONCAT('%',NVL(:cntrctDemand,''),'%'))"
			+ "AND NVL(LOWER(sldc.nocCode),'') LIKE LOWER(CONCAT('%',NVL(:nocCode,''),'%'))"
			+ "AND NVL(LOWER(sldc.appliedNo),'') LIKE LOWER(CONCAT('%',NVL(:appliedNo,''),'%'))"
			+ "AND NVL(LOWER(sldc.appliedDt),'') LIKE LOWER(CONCAT('%',NVL(:appliedDt,''),'%'))"
			+ "AND NVL(LOWER(sldc.status),'') LIKE LOWER(CONCAT('%',NVL(:status,''),'%'))"
			+ "AND NVL(LOWER(sldc.nocPurpose),'') LIKE LOWER(CONCAT('%',NVL(:nocPurpose,''),'%'))")
	List<SldcNoc> searchSldcNoc(@Param("compServNo")String compServNo,@Param("edcId")String edcId,@Param("cntrctDemand")String cntrctDemand,
			@Param("nocCode")String nocCode,@Param("appliedNo")String appliedNo,@Param("appliedDt")String appliedDt,@Param("status")String status,@Param("nocPurpose")String nocPurpose);

	@Query("select sldc from com.ss.oa.transaction.vo.SldcNoc sldc where sldc.appliedDt=:appliedDt")
	List<SldcNoc> findByAppliedDt(@Param("appliedDt")String appliedDt);
	
	List<SldcNoc> findByAppliedDtBetween(LocalDate fromDate, LocalDate toDate);

	@Query("select sldc from com.ss.oa.transaction.vo.SldcNocLine sldc where NVL(LOWER(sldc.quantum),'') LIKE LOWER(CONCAT('%',NVL(:quantum,''),'%'))"
			+ "AND NVL(LOWER(sldc.tSldcNocId),'') LIKE LOWER(CONCAT('%',NVL(:tSldcNocId,''),'%'))"
			+ "AND NVL(LOWER(sldc.commitType),'') LIKE LOWER(CONCAT('%',NVL(:commitType,''),'%'))"
			+ "AND NVL(LOWER(sldc.flowType),'') LIKE LOWER(CONCAT('%',NVL(:flowType,''),'%'))"
			+ "AND NVL(LOWER(sldc.isExisting),'') LIKE LOWER(CONCAT('%',NVL(:isExisting,''),'%'))")
	List<SldcNocLine> searchApprovalNoc(@Param("quantum")String quantum,@Param("tSldcNocId")String tSldcNocId,@Param("commitType")String commitType,
			@Param("flowType")String flowType,@Param("isExisting")String isExisting);

//	Optional<SldcNocLine> findByNocId(String tSldcNocId);

}
