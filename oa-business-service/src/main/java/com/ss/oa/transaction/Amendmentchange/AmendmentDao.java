package com.ss.oa.transaction.Amendmentchange;

import java.util.Map;

public interface AmendmentDao {
	  
		public abstract String importMeterReading(Map<String, String> criteria);
		public abstract String ProcessGenerationStatements(Map<String, String> criteria);
		public abstract String importAndProcessMri(Map<String, String> criteria);
}
