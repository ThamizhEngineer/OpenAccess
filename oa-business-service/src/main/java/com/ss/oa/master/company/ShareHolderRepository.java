package com.ss.oa.master.company;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.CompanyShareHolder;


@Scope("prototype")
public interface ShareHolderRepository extends CrudRepository<CompanyShareHolder, String>{


}
