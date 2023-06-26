package com.ss.oa.transaction.energysale;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.EnergySale;
import com.ss.oa.transaction.vo.EnergySaleCharge;
import com.ss.oa.transaction.vo.EnergySaleUsageDetail;
import com.ss.oa.transaction.vo.EnergySaleUsageSummary;
@Scope("prototype")
public interface EnergySaleDao {
	
	public abstract List<EnergySale> getAllEnergySales(Map<String,String> criteria);
	public abstract EnergySale getEnergySaleById(String id);
	public abstract String addEnergySale(EnergySale energySale);
	public abstract String updateEnergySale(String id,EnergySale energySale);
	public abstract String addEnergySaleUsageDetail(EnergySaleUsageDetail energySaleUsageDetail);
	public abstract String updateEnergySaleUsageDetail(EnergySaleUsageDetail energySaleUsageDetail);
	public abstract String addEnergySaleUsageSummary(EnergySaleUsageSummary energySaleUsageSummary);
	public abstract String updateEnergySaleUsageSummary(EnergySaleUsageSummary energySaleUsageSummary);
	public abstract String finalEnergySale(String id,EnergySale energySale);
	public abstract String addEnergySaleCharge(EnergySaleCharge energySaleCharge);
	public abstract String updateEnergySaleCharge(EnergySaleCharge energySaleCharge);
	public abstract String DeleteEnergySaleCharge(EnergySaleCharge energySaleCharge);
	public abstract String DeleteEsUsageSummary(EnergySaleUsageSummary energySaleUsageSummary);
	public abstract EnergySale getEsCountByGenStmt(String genStmtId);
	

}
