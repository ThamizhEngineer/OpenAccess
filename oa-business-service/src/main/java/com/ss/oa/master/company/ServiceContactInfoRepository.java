package com.ss.oa.master.company;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.ServiceContactInfo;
@Scope("prototype")
public interface ServiceContactInfoRepository extends CrudRepository<ServiceContactInfo, String> {
	ServiceContactInfo findByNumber(String number);
}
