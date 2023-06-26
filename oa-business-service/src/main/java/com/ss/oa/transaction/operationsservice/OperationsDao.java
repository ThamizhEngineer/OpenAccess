package com.ss.oa.transaction.operationsservice;


import java.util.Map;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public interface OperationsDao {
     
	public abstract String importMeterReading(Map<String, String> criteria);
	public abstract String ProcessGenerationStatements(Map<String, String> criteria);
	public abstract String importAndProcessMri(Map<String, String> criteria);
	
}
