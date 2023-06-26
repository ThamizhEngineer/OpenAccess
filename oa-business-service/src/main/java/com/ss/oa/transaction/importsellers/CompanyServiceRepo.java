package com.ss.oa.transaction.importsellers;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Service;

@Scope("prototype")
public interface CompanyServiceRepo extends CrudRepository<Service, String>{

	List<Service> findByNumber(String number);

}
