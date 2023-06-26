package com.ss.oa.transaction.energysalemultiadd;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.EnergySaleMultiAddHeader;

@Scope("prototype")
public interface EnergySaleMultiAddHeaderRepository extends CrudRepository<EnergySaleMultiAddHeader, String> {
	
	@Transactional
	@Modifying
	@Query("DELETE from com.ss.oa.transaction.vo.EnergySaleMultiAddHeader es where es.id= ?1")
	void delete(String id);
	


}
