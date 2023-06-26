package com.ss.oa.transaction.sldcNoc;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.VCompanyService;

@Scope("prototype")
public interface VCompanyServicerepo extends CrudRepository<VCompanyService, String>{

	VCompanyService findByMCompServNumber(String mCompServNumber);

}
