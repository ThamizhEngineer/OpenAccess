package com.ss.oa.master.traderelationship;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.VtradeRelationship;

@Scope("prototype")
public interface VtradeRelationshipRepository extends CrudRepository<VtradeRelationship, String>{
	List<VtradeRelationship> findBySellerCompServiceId(String sellerCompServiceId);
	
	List<VtradeRelationship> findBySellerCompServiceNumber(String sellerCompServiceNumber);

}
