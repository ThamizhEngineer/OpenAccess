package com.ss.oa.master.generatorhis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.oa.model.master.VtradeRelationship;

@Repository
public interface TraderelationshipRepository extends JpaRepository< VtradeRelationship, String>{

	List<VtradeRelationship> findBySellerCompServiceNumber(String sellerCompServiceNumber);

}
