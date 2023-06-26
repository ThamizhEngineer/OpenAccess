package com.ss.oa.BankingIntegrationService;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.oashared.model.BankingIntegration;

@EnableJpaRepositories
@Repository
public interface BankingIntegrationRepository extends CrudRepository<BankingIntegration,String> {

}
