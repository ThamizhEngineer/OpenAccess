package com.ss.oa.oadocumentservice.repo;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.VCompanyService;

@Scope("prototype")
public interface VCompanyServiceDocrepo extends CrudRepository<VCompanyService, Integer>{

	VCompanyService findByMCompServNumber(String mCompServNumber);
	
}
