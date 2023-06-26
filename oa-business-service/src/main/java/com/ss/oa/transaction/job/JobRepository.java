package com.ss.oa.transaction.job;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.ss.oa.model.transaction.Job;

@Component
public interface JobRepository extends CrudRepository<Job,String>{


}
