package com.ss.oa.deleteTransactionReasonSummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.DeleteTransactionReasonSummary;

public interface DeleteTransactionReasonSummaryRepository extends CrudRepository<DeleteTransactionReasonSummary,String> {


	
	@Query("SELECT new com.ss.oa.report.vo.DeleteTransactionReasonSummary(reason.listCode,reason.listName,reason.valueCode,reason.valueDesc)"
			+ "FROM  DeleteTransactionReasonSummary reason WHERE NVL(LOWER(reason.valueCode),'') LIKE LOWER(CONCAT('%',NVL(:valueCode,''),'%'))"
			+ "AND NVL(LOWER(reason.valueDesc),'') LIKE LOWER(CONCAT('%',NVL(:valueDesc,''),'%'))  AND reason.listCode='DEL_TXN_PURPOSE_CODE' ")
	
	List<DeleteTransactionReasonSummary> getdeleteTransactionReasonSummary(@Param ("valueCode")String valueCode,
			@Param("valueDesc")String valueDesc);

}
