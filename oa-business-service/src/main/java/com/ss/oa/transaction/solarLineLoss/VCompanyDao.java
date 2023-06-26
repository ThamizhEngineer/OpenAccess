package com.ss.oa.transaction.solarLineLoss;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Vcompany;
import com.ss.oashared.model.VCompanyService;

@Scope("prototype")
public interface VCompanyDao {
	
	public abstract List<Vcompany> getAllFeeder(Map<String, String> criteria);
}
