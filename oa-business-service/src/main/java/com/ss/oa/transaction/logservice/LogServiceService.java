package com.ss.oa.transaction.logservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.ss.oa.transaction.vo.LogService;

@Scope("prototype")
@Component
public class LogServiceService {
	
	@Autowired
	LogServiceDao dao;
	
	public LogServiceService() {
		super();
	}
	
	public List<LogService> getAllLogServices(Map<String,String> criteria) throws Exception
	{
		try {
			return dao.getAllLogServices(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	} 
	
	public LogService getLogServiceById(String id) throws Exception
	{
		try {
			return dao.getLogServiceById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
	
	public String addLogService(LogService logService)
	{
		String result = "";
		try {
			result = dao.addLogService(logService) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public void add(String processType, String processName,String activityName, String message, String result, String att1, String att2, String att3)
	{
		LogService logService = new LogService(null, processType, processName, activityName, message, result, "admin" , null, att1, att2, att3, null, null);
		try {
			addLogService(logService) ;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String updateLogService(String id,LogService logService)
	{
		return dao.updateLogService(id,logService) ;
	}

	
}
