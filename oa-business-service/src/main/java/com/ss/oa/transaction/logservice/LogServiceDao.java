package com.ss.oa.transaction.logservice;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.LogService;

@Scope("prototype")
public interface LogServiceDao {

	public abstract List<LogService> getAllLogServices(Map<String,String> criteria);
	public abstract LogService getLogServiceById(String id);
	public abstract String addLogService(LogService logService);
	public abstract String updateLogService(String id,LogService logService);
}
