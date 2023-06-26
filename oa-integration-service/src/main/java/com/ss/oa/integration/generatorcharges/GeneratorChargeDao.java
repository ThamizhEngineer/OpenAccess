package com.ss.oa.integration.generatorcharges;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.vo.GeneratorChargeForDAO;

public interface GeneratorChargeDao {
	public abstract List<GeneratorChargeForDAO> getAllGeneratorCharges(Map<String,String> criteria);
}
