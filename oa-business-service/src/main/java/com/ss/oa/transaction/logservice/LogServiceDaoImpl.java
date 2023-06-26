package com.ss.oa.transaction.logservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.transaction.vo.LogService;

@Scope("prototype")
@Component
public class LogServiceDaoImpl extends BaseDaoJdbc implements  LogServiceDao{

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<LogService> getAllLogServices(Map<String, String> criteria) {
		
			String sql="SELECT ls.ID,ls.PROCESS_TYPE ,ls.PROCESS_NAME,ls.ACTIVITY_NAME,ls.MESSAGE,ls.\"result\",\n" + 
					"  ls.CREATED_BY,ls.CREATED_DT, ls.ATT1, ls.ATT2, ls.ATT3,ls.CREATED_DATE,ls.ENABLED FROM T_ACTIVITY_LOG ls  where 1=1 " ;
			 
					if(!criteria.isEmpty())
					{
						if(criteria.get("id")!=null){
							sql += "and upper(ID)= upper('"+criteria.get("id")+"')";
						}
						if(criteria.get("process-type")!=null){
							sql += "and upper(PROCESS_TYPE) like upper('%"+criteria.get("process-type")+"%')";
						}
						if(criteria.get("process-name")!=null){
							sql += "and upper(PROCESS_NAME) = upper('"+criteria.get("process-name")+"')";
						}
						if(criteria.get("activity-name")!=null){
							sql += "and upper(ACTIVITY_NAME) = upper('"+criteria.get("activity-name")+"')";
						}
						if(criteria.get("message")!=null){
							sql += "and upper(MESSAGE) = upper('"+criteria.get("message")+"')";
						}
						if(criteria.get("result")!=null){
							sql += "and upper(\"result\") = upper('"+criteria.get("result")+"')";
						}
						if(criteria.get("created-by")!=null){
							sql += "and upper(CREATED_BY) = upper('"+criteria.get("created-by")+"')";
						}
						if(criteria.get("created-dt")!=null){
							sql += "and upper(CREATED_DT) = upper('"+criteria.get("created-dt")+"')";
						}
						if(criteria.get("att1")!=null){
							sql += "and upper(ATT1) = upper('"+criteria.get("att1")+"')";
						}
						if(criteria.get("att2")!=null){
							sql += "and upper(ATT2) = (upper('"+criteria.get("att2")+"')";
						}
						if(criteria.get("att3")!=null){
							sql += "and upper(ATT3) = (upper('"+criteria.get("att3")+"')";
						}
						if(criteria.get("created-date")!=null){
							sql += "and upper(CREATED_DATE) = upper('"+criteria.get("created-date")+"')";
						}
						if(criteria.get("enabled")!=null){
							sql += "and upper(ENABLED) = upper('"+criteria.get("enabled")+"')";
						}
						sql += " order by to_number(id) desc";
						
					}
					return (ArrayList<LogService>)jdbcOperations.query(sql, new LogServiceMapper());
				}
	

	@Override
	public LogService getLogServiceById(String id) {
		
		LogService  logService  = new LogService ();
		
		String sql ="SELECT ls.ID,ls.PROCESS_TYPE ,ls.PROCESS_NAME,ls.ACTIVITY_NAME,ls.MESSAGE,ls.\"result\",\n" + 
				"  ls.CREATED_BY,ls.CREATED_DT, ls.ATT1, ls.ATT2, ls.ATT3,ls.CREATED_DATE,ls.ENABLED FROM T_ACTIVITY_LOG ls where ls.ID=?" ;
		
		logService = jdbcOperations.queryForObject(sql,new Object[]{id},new LogServiceMapper());
		
		return logService;
		
		
	}

	@Override
	public String addLogService(LogService logService) {
		String result="";
		
		try {
			logService.setId(generateCode(LogService.class.getSimpleName(),""));
			String sql=" INSERT INTO T_ACTIVITY_LOG(PROCESS_TYPE,PROCESS_NAME,ACTIVITY_NAME,MESSAGE,\"result\",\n" + 
					"  CREATED_BY,CREATED_DT,ATT1,ATT2,ATT3,ID) VALUES (?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?)" ;
			
			if(jdbcOperations.update(sql, setLogServiceValues(logService)) > 0){
				result =  logService.getId();
			}
			else{
				result =  "failure";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}

		
	
	@Override
	public String updateLogService(String id, LogService logService) {
		
		
String result="";
		
		try {

			String sql = "UPDATE T_ACTIVITY_LOG SET PROCESS_TYPE=?,PROCESS_NAME=?,ACTIVITY_NAME=?,MESSAGE=?,\"result\"=?,\n" + 
					"  CREATED_BY=?,CREATED_DT=TO_DATE(?,'yyyy-mm-dd'),ATT1=?,ATT2=?,ATT3=?  WHERE ID=?";
					System.out.println("sql");
			if(jdbcOperations.update(sql,setLogServiceValues(logService)) > 0){
				result =logService.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
			
			
		}catch(Exception e) {
			result="FAILURE";
			e.printStackTrace();
		}
		return result;
		
	}
	

	 
	
	private Object[] setLogServiceValues(LogService logService) {
		return new Object[] {
					
						logService.getProcessType(),
						logService.getProcessName(),
						logService.getActivityName(),
						logService.getMessage(),
						logService.getResult(),
						logService.getCreatedBy(),
						logService.getCreatedDt(),
						logService.getAtt1(),
						logService.getAtt2(),
						logService.getAtt3(),
						logService.getId()
						
		};
	}
	
    public class LogServiceMapper implements RowMapper<LogService>{
		
		public LogServiceMapper() {
			super();
		}

		@Override
		public LogService mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			LogService  logService  = new LogService ();
			
			logService.setId(resultSet.getString("ID"));
			logService.setProcessType(resultSet.getString("PROCESS_TYPE"));
			 logService.setProcessName(resultSet.getString("PROCESS_NAME"));
			 logService.setActivityName(resultSet.getString("ACTIVITY_NAME"));
			 logService.setMessage(resultSet.getString("MESSAGE"));
			 logService.setResult(resultSet.getString("result"));
			 logService.setCreatedBy(resultSet.getString("CREATED_BY"));
			 logService.setCreatedDt(resultSet.getString("CREATED_DT"));
			 logService.setAtt1(resultSet.getString("ATT1"));
			 logService.setAtt2(resultSet.getString("ATT2"));
			 logService.setAtt3(resultSet.getString("ATT3"));
			 logService.setCreatedDate(resultSet.getString("CREATED_DATE"));
			 logService.setEnabled(resultSet.getString("ENABLED"));

			return logService ;
		}

    }
    
}
