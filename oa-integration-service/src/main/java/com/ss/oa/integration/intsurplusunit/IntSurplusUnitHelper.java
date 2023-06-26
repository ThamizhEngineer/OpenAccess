package com.ss.oa.integration.intsurplusunit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Component
public class IntSurplusUnitHelper {
	
	@Autowired
	private CommonUtils commonUtils;
	@Resource
	private DataSource dataSource;
	
	Connection conn = null;
	CallableStatement stmt = null;
	
	
	public String incrementSurplusUnits(String iHtSurplusUnitsId) {
		
	String incrementSurplusUnitsFunction = "{call BANKING_BALANCE.increment_surplus_units(?,?,?)}"; 
	
	String res= null;
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(incrementSurplusUnitsFunction);
		        stmt.setString(1, iHtSurplusUnitsId);
		        stmt.registerOutParameter(2, OracleTypes.VARCHAR);
		        stmt.registerOutParameter(3, OracleTypes.VARCHAR);
		        stmt.execute();
		        res= stmt.getString(3);   
		        System.out.println(res);
		        System.out.println(stmt.getString(3)); 
		        conn.close();
		      
		} catch (Exception e) {
		      e.printStackTrace();
		} finally {
			
			try {
				if((stmt!= null) && (!stmt.isClosed())) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if((conn!= null) && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			return res;
	}
}
