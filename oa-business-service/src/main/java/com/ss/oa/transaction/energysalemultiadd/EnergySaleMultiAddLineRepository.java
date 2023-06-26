package com.ss.oa.transaction.energysalemultiadd;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.EnergySaleMultiAddLine;

@Scope("prototype")
public interface EnergySaleMultiAddLineRepository extends CrudRepository<EnergySaleMultiAddLine, String> {
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.oa.transaction.vo.EnergySaleMultiAddLine esLine where esLine.energySaleMultiAddHeaderId= ?1")
	void delete(String id);
}
