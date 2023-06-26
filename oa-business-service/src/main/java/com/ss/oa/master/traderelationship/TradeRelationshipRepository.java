package com.ss.oa.master.traderelationship;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.TradeRelationship;
@Scope("prototype")
public interface TradeRelationshipRepository extends CrudRepository<TradeRelationship, String>{
List<TradeRelationship> findBySellerCompServiceId(String sellerCompServiceId);
}
