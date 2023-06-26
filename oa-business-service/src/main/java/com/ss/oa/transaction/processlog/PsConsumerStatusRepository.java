package com.ss.oa.transaction.processlog;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.PsConsumerStatus;

@Scope("prototype")
public interface PsConsumerStatusRepository extends CrudRepository<PsConsumerStatus,Integer> {

	List<PsConsumerStatus> findByTEsId(String tEsId);


}
