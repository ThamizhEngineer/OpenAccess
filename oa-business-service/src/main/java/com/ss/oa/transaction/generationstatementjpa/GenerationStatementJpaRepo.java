package com.ss.oa.transaction.generationstatementjpa;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ss.oa.transaction.jpamodel.GenerationStatementIdJpa;
import com.ss.oa.transaction.jpamodel.GenerationStatementJpa;

public interface GenerationStatementJpaRepo extends PagingAndSortingRepository<GenerationStatementJpa, String> {
	
	@Query("SELECT gs FROM GenerationStatementJpa gs "
			+"WHERE gs.stmtMonth = :stmtMonth AND gs.stmtYear =:stmtYear")
	Page<GenerationStatementJpa> gsPage(Pageable pageable,@Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);
	
	@Query("SELECT count(gs) FROM GenerationStatementJpa gs WHERE gs.docId=:docId  AND gs.stmtMonth = :stmtMonth AND gs.stmtYear =:stmtYear")
	long gsDocCount(@Param("docId") String docId, @Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);

	Page<GenerationStatementIdJpa> findByDocIdAndStmtMonthAndStmtYear(Pageable pageable,@Param("docId") String docId,@Param("stmtMonth") String stmtMonth,@Param("stmtYear") String stmtYear);

}

