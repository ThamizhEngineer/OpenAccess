package com.ss.oa.transaction.MeterChange;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface MeterChangeMcRepository extends CrudRepository<MeterChangeMc,String>{
	
	@Query(value="SELECT * FROM T_GEN_STMT_SLOT_MC WHERE SERVICE_NO = ?1 AND READING_MONTH = ?2 AND READING_YEAR = ?3 ", nativeQuery=true)
	List<MeterChangeMc> findbyCombo(String serviceno,String month,String year);
	
	}