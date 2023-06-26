package com.ss.oa.report.progressreport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.report.vo.FlowType;

@Component
public class ProgressReportDaoImpl extends BaseDaoJdbc implements ProgressReportDao{
	@Resource
	private JdbcOperations jdbcOperations;
	
	
	@Override
	public String getCountOfCaptive() {
		String CaptiveServicesCount="select count(*) from v_company_service where comp_ser_type_code='03' and is_captive='N'";	
			 return CaptiveServicesCount;
	}
	
	public List<FlowType> getCountOfFlowTypes() {
		String flowTypeWiseCount="SELECT distinct flow_type_code as FLOW_TYPE_NAME,count(flow_type_code) AS FLOW_TYPE_COUNT  FROM t_gen_stmt WHERE flow_type_code is not null group by flow_type_code";	
		return  (ArrayList<FlowType>) jdbcOperations.query(flowTypeWiseCount,new FlowTypeMapper());
		
	}
	
	final class FlowTypeMapper implements RowMapper<FlowType>{
		public FlowTypeMapper() {
			super();
		}
		public FlowType mapRow(ResultSet resultSet, int rownum) throws SQLException {
			FlowType flowType=new FlowType();
			flowType.setFlowTypeName(resultSet.getString("FLOW_TYPE_NAME"));
			flowType.setFlowTypeCount(resultSet.getString("FLOW_TYPE_COUNT"));
			return flowType;
		}		
	}
}
