package com.ss.oa.common;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;


public class BaseDaoJdbc {

	@Resource
	private JdbcOperations jdbcOperations;
	
	public BaseDaoJdbc() {
		super();
	}
	
	public String generateId(){
		UUID uuid = UUID.randomUUID();
        return uuid.toString();
	}
	
	public String generateCode(String className, String param){
		try {
			Map<String, String[]> valuesMap = new HashMap<String, String[]>(); 
			String[] values = valuesMap.get(className);
			String sql="select "+values[1]+".nextval from dual";
			return values[0]+jdbcOperations.queryForObject(sql,String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	public static void  main(String args[]) {
		
		BaseDaoJdbc baseDaoJdbc = new BaseDaoJdbc();
		System.out.println(baseDaoJdbc.generateId()); 
	}

}
