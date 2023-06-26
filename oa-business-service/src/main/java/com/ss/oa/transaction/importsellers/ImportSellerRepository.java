package com.ss.oa.transaction.importsellers;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.ImportSeller;

@Scope("prototype")
public interface ImportSellerRepository extends CrudRepository<ImportSeller, String>{
	
}
