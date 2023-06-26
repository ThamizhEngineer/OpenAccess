package com.ss.oa.master.traderelationship;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.TradeRelationship;
@Scope("prototype")
public interface TradeRelationshipDao {
	public abstract List<TradeRelationship> getAllTradeRelationships(Map<String,String> criteria);
	public abstract TradeRelationship getTradeRelationshipById(String tradeRelationshipId);
	public abstract String addTradeRelationship(TradeRelationship tradeRelationship);
	public abstract String updateTradeRelationship(String tradeRelationshipId , TradeRelationship tradeRelationship);
	public abstract String deleteTradeRelationship(String tradeRelationshipId );

}
