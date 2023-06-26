package com.ss.oa.transaction.companynamechange;

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
import com.ss.oa.transaction.vo.CompanyNameChange;

@Scope("prototype")
@Component
public class CompanyNameChangeDaoImpl extends BaseDaoJdbc implements CompanyNameChangeDao {
	

	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<CompanyNameChange> getAllCompanyNameChanges(Map<String, String> criteria) {
		String sql = "select cnc.ID,cnc.CODE,cnc.M_EDC_ID,cnc.M_COMPANY_ID,cnc.M_COMPANY_SERVICE_ID,cnc.M_COMPANY_METER_ID,cnc.COMPANY_NAME_CHANGE_DATE,cnc.OLD_COMPANY_NAME,cnc.NEW_COMPANY_NAME,cnc.REMARKS,cnc.STATUS_CODE,cnc.FLOW_TYPE_CODE,\n" + 
				"COMPANYSERVICE.M_ORG_ID as M_ORG_ID,COMPANYSERVICE.M_COMPANY_CODE,COMPANYSERVICE.M_COMPANY_NAME,COMPANYSERVICE.M_ORG_CODE,COMPANYSERVICE.M_ORG_NAME,COMPANYSERVICE.\"number\" AS M_SERVICE_NUMBER\n" + 
				"from T_COMPANY_NAME_CHANGE cnc	left join v_company_service COMPANYSERVICE on cnc.M_COMPANY_SERVICE_ID = COMPANYSERVICE.id WHERE 1=1";

		 if(!criteria.isEmpty())
			{
				if(criteria.get("edc-id")!=null){
					sql += "and upper(cnc.M_EDC_ID)= upper('"+criteria.get("edc-id")+"')";
				}
				if(criteria.get("company-service-id")!=null){
					sql += "and upper(cnc.M_COMPANY_SERVICE_ID) = upper('"+criteria.get("company-service-id")+"')";
				}
		}  
		 
		return (ArrayList<CompanyNameChange>) jdbcOperations.query(sql,new CompanyNameChangeMapper());
	   
	} 
	@Override
	public CompanyNameChange getCompanyNameChangeById(String id) {
		CompanyNameChange companyNameChange = new CompanyNameChange();
		String sql = "select cnc.ID,cnc.CODE,cnc.M_EDC_ID,cnc.M_COMPANY_ID,cnc.M_COMPANY_SERVICE_ID,cnc.M_COMPANY_METER_ID,cnc.COMPANY_NAME_CHANGE_DATE,cnc.OLD_COMPANY_NAME,cnc.NEW_COMPANY_NAME,cnc.REMARKS,cnc.STATUS_CODE,cnc.FLOW_TYPE_CODE,\n" + 
				"COMPANYSERVICE.M_ORG_ID as M_ORG_ID,COMPANYSERVICE.M_COMPANY_CODE,COMPANYSERVICE.M_COMPANY_NAME,COMPANYSERVICE.M_ORG_CODE,COMPANYSERVICE.M_ORG_NAME,COMPANYSERVICE.\"number\" AS M_SERVICE_NUMBER\n" + 
				"from T_COMPANY_NAME_CHANGE cnc	left join v_company_service COMPANYSERVICE on cnc.M_COMPANY_SERVICE_ID = COMPANYSERVICE.id  WHERE cnc.ID=?";
		
		companyNameChange = jdbcOperations.queryForObject(sql,new Object[]{id},new CompanyNameChangeMapper());
		
		return companyNameChange;
	}

	@Override
	public String addCompanyNameChange(CompanyNameChange companyNameChange) {
    String result="";
		
		try {
			companyNameChange.setId(generateId());
			if(companyNameChange.getCode()== null || companyNameChange.getCode().isEmpty()){
				companyNameChange.setCode(generateCode(CompanyNameChange.class.getSimpleName(),""));
			}
			String sql = "INSERT INTO T_COMPANY_NAME_CHANGE(CODE,M_EDC_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,M_COMPANY_METER_ID,COMPANY_NAME_CHANGE_DATE,OLD_COMPANY_NAME,NEW_COMPANY_NAME,REMARKS,STATUS_CODE,FLOW_TYPE_CODE,ID)\n" + 
					" values(?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setCompanyNameChangeValues(companyNameChange))>0) {
				result =companyNameChange.getId();
			}else {
				result = "FAILURE";
			}
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
	}
	
	@Override
	public String updateCompanyNameChange(String id, CompanyNameChange companyNameChange) {
    String result="";
		
		try {

			String sql = "UPDATE T_COMPANY_NAME_CHANGE SET CODE=?,M_EDC_ID=?,M_COMPANY_ID=?,M_COMPANY_SERVICE_ID=?,M_COMPANY_METER_ID=?,COMPANY_NAME_CHANGE_DATE=TO_DATE(?,'yyyy-mm-dd'),OLD_COMPANY_NAME=?,NEW_COMPANY_NAME=?,REMARKS=?,"
					+ "    STATUS_CODE=?,FLOW_TYPE_CODE=? WHERE ID=?";
			
			if(jdbcOperations.update(sql,setCompanyNameChangeValues(companyNameChange)) > 0){
				result =companyNameChange.getId();
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
	
	
	private Object[] setCompanyNameChangeValues(CompanyNameChange companyNameChange){
		return new Object[]{
				
				companyNameChange.getCode(),
				companyNameChange.getOrgId(),
				companyNameChange.getCompanyId(),
				companyNameChange.getServiceId(),
				companyNameChange.getCompanyMeterId(),
				companyNameChange.getCompanyNameChangeDate(),
				companyNameChange.getOldCompanyName(),
				companyNameChange.getNewCompanyName(),
				companyNameChange.getRemarks(),
				companyNameChange.getStatusCode(),
				companyNameChange.getFlowTypeCode(),
				companyNameChange.getId()
				
		};
}
	
public class CompanyNameChangeMapper implements RowMapper<CompanyNameChange>{
		
		public CompanyNameChangeMapper() {
			super();
		}
		
		@Override
		public CompanyNameChange mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			CompanyNameChange companyNameChange= new CompanyNameChange();
			
			companyNameChange.setId(resultSet.getString("ID"));
			companyNameChange.setCode(resultSet.getString("CODE"));
			companyNameChange.setOrgId(resultSet.getString("M_EDC_ID"));
			companyNameChange.setCompanyId(resultSet.getString("M_COMPANY_ID"));
			companyNameChange.setServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
			companyNameChange.setCompanyMeterId(resultSet.getString("M_COMPANY_METER_ID"));
			companyNameChange.setCompanyNameChangeDate(resultSet.getString("COMPANY_NAME_CHANGE_DATE"));
			companyNameChange.setOldCompanyName(resultSet.getString("OLD_COMPANY_NAME"));
			companyNameChange.setNewCompanyName(resultSet.getString("NEW_COMPANY_NAME"));
			companyNameChange.setRemarks(resultSet.getString("REMARKS"));
			companyNameChange.setStatusCode(resultSet.getString("STATUS_CODE"));
			companyNameChange.setCompanyName(resultSet.getString("M_COMPANY_NAME"));
			companyNameChange.setCompanyCode(resultSet.getString("M_COMPANY_CODE"));
			companyNameChange.setServiceNumber(resultSet.getString("M_SERVICE_NUMBER"));
			companyNameChange.setOrgName(resultSet.getString("M_ORG_NAME"));
			companyNameChange.setOrgCode(resultSet.getString("M_ORG_CODE"));
			companyNameChange.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			return companyNameChange;
		}
		
       }
}