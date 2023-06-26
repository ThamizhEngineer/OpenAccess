package com.ss.oa.oadocumentservice.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.oadocumentservice.vo.GenerationStatementIdJpa;
import com.ss.oa.oadocumentservice.vo.GenerationStatementJpa;

public interface GenerationStatementRepo extends PagingAndSortingRepository<GenerationStatementJpa, String>{


	@Query("SELECT gs FROM GenerationStatementJpa gs "
			+"WHERE gs.stmtMonth = :stmtMonth AND gs.stmtYear =:stmtYear")
	Page<GenerationStatementIdJpa> gsPage(Pageable pageable,@Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);
	
	@Query("SELECT count(gs) FROM GenerationStatementJpa gs WHERE gs.docId=:docId  AND gs.stmtMonth = :stmtMonth AND gs.stmtYear =:stmtYear")
	long gsDocCount(@Param("docId") String docId, @Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);

	Page<GenerationStatementIdJpa> findByDocIdAndStmtMonthAndStmtYear(Pageable pageable,@Param("docId") String docId,@Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);

}
