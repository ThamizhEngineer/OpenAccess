package com.ss.oa.master.powerplant;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Generator;
@Scope("prototype")
public interface GeneratorRepository extends CrudRepository<Generator, String>{

}
