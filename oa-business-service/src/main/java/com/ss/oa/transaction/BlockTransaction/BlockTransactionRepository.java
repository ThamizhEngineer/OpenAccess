package com.ss.oa.transaction.BlockTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ss.oa.model.transaction.BlockTransaction;

@EnableJpaRepositories
@Repository
public interface BlockTransactionRepository extends CrudRepository <BlockTransaction, Long>{
	
	
	
	
	
	@Query(value="select  * FROM T_BLOCK_TXN tbt WHERE M_COMP_SERVICE_NUMBER ='dummy'", nativeQuery = true)
	BlockTransaction findallnative();
	
	@Query(value=" SELECT * FROM  T_BLOCK_TXN WHERE M_COMP_SERVICE_NUMBER = ?1 OR  \"YEAR\" = ?2 OR \"MONTH\" = ?3", nativeQuery = true)
	List<BlockTransaction> findall(String id,String year,String month);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE  T_BLOCK_TXN  SET STATUS ='UnBlock',MODIFIED_DATE = ?2 WHERE M_COMP_SERVICE_NUMBER = ?1 ", nativeQuery = true)
     void statusUpdate( String mCompServiceI,LocalDate localdate);
	
	@Query(value="SELECT * FROM  T_BLOCK_TXN WHERE M_COMP_SERVICE_NUMBER = ?1 AND \"YEAR\" = ?2 AND \"MONTH\" = ?3", nativeQuery = true)
	BlockTransaction findByCombo(String id,String year,String month);
	
	
}
