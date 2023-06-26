package com.ss.oa.transaction.sldcNoc;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.ExtSamastApplnApproval;

@Scope
public interface ExtSamastApplnApprovalRepo extends CrudRepository<ExtSamastApplnApproval, Integer>{

	List<ExtSamastApplnApproval> findByEobHtConsumerNumber(String eobHtConsumerNumber);

	List<ExtSamastApplnApproval> findByEosGcApprovalNumber(String eosGcApprovalNumber);

	@Query(value="SELECT * FROM EXT_SAMAST_APPLN_APPROVAL WHERE EOS_GC_APPROVAL_NUMBER=?1 and APP_TYPE= ?2 and APPROVAL_NO Like %?3% ", nativeQuery=true)
	List<ExtSamastApplnApproval> findByRevisedData(String eosGcApprovalNumber,String appType ,String approvalNo );

}
