package com.ss.oa.oadocumentservice.repo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.oadocumentservice.vo.ExtSamastApplnApproval;

@Scope
public interface ExtSamastApplnApprovalDocRepo extends CrudRepository<ExtSamastApplnApproval, Integer>{

	List<ExtSamastApplnApproval> findByEobHtConsumerNumber(String eobHtConsumerNumber);

	List<ExtSamastApplnApproval> findByEosGcApprovalNumber(String eosGcApprovalNumber);

}
