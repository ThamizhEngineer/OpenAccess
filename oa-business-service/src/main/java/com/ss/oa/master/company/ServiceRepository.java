package com.ss.oa.master.company;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.oa.model.master.Service;

@Scope("prototype")
@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {
	
	Service findByNumber(String number);

	List<Service> findByfeederId(String feeder_id);
	
}
