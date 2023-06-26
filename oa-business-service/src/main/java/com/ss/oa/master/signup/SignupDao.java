package com.ss.oa.master.signup;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Signup;
import com.ss.oa.master.vo.TradeRelationship;
@Scope("prototype")
public interface SignupDao {	
	public abstract List<Signup> getAllSignup(Map<String,String> criteria);
	public abstract Signup getSignupById(String signupId);
	public abstract String addSignup(Signup signup);
	public abstract String updateSignup(String signupId,Signup signup);
	public abstract String addSignupTradeRelationship(TradeRelationship tradeRelationship);
	public abstract String updateSignupTradeRelationship(TradeRelationship tradeRelationship);
	public abstract String generateCode(String className, String param);
	public abstract String generateId();

}
