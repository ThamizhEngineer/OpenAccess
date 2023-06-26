package com.ss.oa.transaction.solarLineLoss;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Feeder;

@Scope("prototype")
public interface FeederRepository extends CrudRepository<Feeder, String> {

}
