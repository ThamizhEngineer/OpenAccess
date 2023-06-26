package com.ss.oa.integration.updateDrawalVoltageService;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
public interface UpdateDrawalVoltageRepository extends CrudRepository<UpdateDrawalVoltage, String> {
List<UpdateDrawalVoltage>findByServiceNo(String serviceNo);
}
