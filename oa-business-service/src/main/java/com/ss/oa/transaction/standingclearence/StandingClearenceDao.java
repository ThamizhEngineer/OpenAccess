package com.ss.oa.transaction.standingclearence;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.StandingClearence;

@Scope("prototype")
@Component
public interface StandingClearenceDao {
	public abstract List<StandingClearence> getAllStandingClearences(Map<String,String> criteria);
	public abstract StandingClearence getStandingClearenceById(String id);
	public abstract String addStandingClearence(StandingClearence standingClearence);
	public abstract String updateStandingClearence(String id,StandingClearence standingClearence);	
}
