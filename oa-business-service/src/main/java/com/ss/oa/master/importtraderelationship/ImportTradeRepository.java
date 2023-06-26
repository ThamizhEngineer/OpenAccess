package com.ss.oa.master.importtraderelationship;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.ImportTraderelationship;

@Scope("prototype")
public interface ImportTradeRepository extends CrudRepository<ImportTraderelationship, String> {
	
}
