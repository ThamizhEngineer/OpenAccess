package com.ss.oa.master.traderelationship;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.vo.TradeRelationship;


@Component
@Scope("prototype")
public class TradeRelationshipService {
	@Autowired
	TradeRelationshipDao dao;
	
	public TradeRelationshipService() {
		super();
	}
	
	public List<TradeRelationship> getAllTradeRelationships(Map<String, String> criteria) {
		
		return dao.getAllTradeRelationships(criteria);
	}

	public TradeRelationship getTradeRelationshipById(String tradeRelationshipId) {
	
		return dao.getTradeRelationshipById(tradeRelationshipId);
	}
	
	public String addTradeRelationship(TradeRelationship tradeRelationship) {
		// default IntervalTypeCode to Slot wise
		if(tradeRelationship.getIntervalTypeCode() == null || tradeRelationship.getIntervalTypeCode().isEmpty()){
			tradeRelationship.setIntervalTypeCode("02");
		}
			
		return dao.addTradeRelationship(tradeRelationship);
	}
	
	public String updateTradeRelationship(String tradeRelationshipId, TradeRelationship tradeRelationship) {
		
		return dao.updateTradeRelationship(tradeRelationshipId, tradeRelationship);
	}
	
public String deleteTradeRelationship(String tradeRelationshipId) {
		
		return dao.deleteTradeRelationship(tradeRelationshipId);
	}
	
	

}
