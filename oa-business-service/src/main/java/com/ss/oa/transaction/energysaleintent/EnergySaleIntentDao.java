package com.ss.oa.transaction.energysaleintent;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.common.Response;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;

@Scope("prototype")
public interface EnergySaleIntentDao {
	
	public abstract List<EnergySaleIntent> getAllEnergySaleIntents(Map<String,String> criteria);
	public abstract EnergySaleIntent getEnergySaleIntentById(String id);
	public abstract Response addEnergySaleIntent(EnergySaleIntent energySaleIntent);
	public abstract String updateEnergySaleIntent(String id ,EnergySaleIntent energySaleIntent);
	public abstract String addEnergySaleIntentLine(EnergySaleIntentLine energySaleIntentLine);
	public abstract String updateEnergySaleIntentLine(EnergySaleIntentLine energySaleIntentLine);
	public abstract String updateApprovalEnergySaleIntent(String id ,EnergySaleIntent energySaleIntent);
	

}
