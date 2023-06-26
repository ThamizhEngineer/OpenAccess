package com.ss.oa.oadocumentservice.repo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.oadocumentservice.vo.GenerationStatement;
import com.ss.oa.oadocumentservice.vo.GenerationStatementIdJpa;
import com.ss.oa.oadocumentservice.vo.GenerationStatementJpa;
@Scope
public interface GenunitsRepo extends CrudRepository<GenerationStatementJpa,Integer>{

	

	@Query(value="SELECT * FROM T_GEN_STMT where STMT_MONTH=?1 AND STMT_YEAR=?2 AND DISP_SERVICE_NUMBER=?3",nativeQuery=true)
	List<GenerationStatementJpa> findByUnits(String lineMonth, String lineYear, String comservno);
	
}

