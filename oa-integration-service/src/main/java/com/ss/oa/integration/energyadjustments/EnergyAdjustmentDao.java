package com.ss.oa.integration.energyadjustments;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.vo.EnergyAdjustmentForDAO;

public interface EnergyAdjustmentDao {
	public abstract List<EnergyAdjustmentForDAO> getAllEnergyAdjustments(Map<String,String> criteria);
}
