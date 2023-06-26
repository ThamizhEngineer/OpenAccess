package com.ss.oa.ZenInvoice;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.oashared.model.ZenInvoice;


@EnableJpaRepositories
@Repository
public interface ZenIntegrationRepository  extends CrudRepository<ZenInvoice,String>{
	
	@Query(value="SELECT * FROM EINVDATA WHERE SERVICENO = ?1 AND BILL_MNTH = ?2 AND BILL_YR = ?3" , nativeQuery=true)
	
	ZenInvoice findById(String serviceNo, String billMonth, String billYear);

	Iterable<ZenInvoice> findAllById(String id);

	

}