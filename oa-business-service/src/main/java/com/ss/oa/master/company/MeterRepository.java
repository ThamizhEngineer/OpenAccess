package com.ss.oa.master.company;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Meter;

public interface MeterRepository extends CrudRepository<Meter, String> {
List<Meter> findByEnabled(String enabled);

Meter findByServiceId (String service);
}
