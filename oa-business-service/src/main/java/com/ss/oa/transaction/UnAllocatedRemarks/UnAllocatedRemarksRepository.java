package com.ss.oa.transaction.UnAllocatedRemarks;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.ss.oa.model.transaction.UnAllocatedRemarks;

@EnableJpaRepositories
@Repository

public interface UnAllocatedRemarksRepository extends CrudRepository <UnAllocatedRemarks, String>{
	
	
	
	
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE A_UNALLOCATED_SERVICE SET AUDIT_REMARKS = ?3 WHERE M_COMP_SERVICE_NUMBER = ?1  AND STMT_MONTH = ?2 AND STMT_YEAR = ?4"
			, nativeQuery = true)
	void UpdateRemarks(String mCompServiceId,String Month,String remarks,String year);
	
	
	@Query(value="SELECT * FROM A_UNALLOCATED_SERVICE WHERE M_ORG_ID LIKE  CONCAT('%',NVL( ?1,'')) AND  STMT_MONTH LIKE  CONCAT('%',NVL( ?2,'')) AND STMT_YEAR LIKE CONCAT('%',NVL( ?3,'')) AND FLOW_TYPE_CODE LIKE CONCAT('%',NVL( ?4,''))"
			, nativeQuery = true)
	List<UnAllocatedRemarks>findbycombo(String edcId,String Month,String year, String flowtype);
	
	@Query(value="SELECT *  FROM A_UNALLOCATED_SERVICE aus WHERE M_COMP_SERVICE_NUMBER = ?1 AND STMT_YEAR = ?3 AND STMT_MONTH = ?2"
			, nativeQuery = true)
	UnAllocatedRemarks ForDetail(String dispnum,String Month,String Year);
	

}
