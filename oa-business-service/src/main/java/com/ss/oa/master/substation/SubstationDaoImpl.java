package com.ss.oa.master.substation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.master.vo.Substation;
@Component
@Scope("prototype")
public class SubstationDaoImpl extends BaseDaoJdbc implements SubstationDao{
	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<Substation> getAllSubstations(Map<String, String> criteria) {
		if(!criteria.isEmpty())
		{
			String org_id = null;
			for (Entry<String, String> element : criteria.entrySet()){
				org_id=element.getValue();
			}
			String sql="select * from m_substation where m_org_id=? order by name";
			return (ArrayList<Substation>) jdbcOperations.query(sql,new Object[]{org_id},new SubstationMapper());
		}
		else{
			String sql="select * from m_substation order by name";
			return (ArrayList<Substation>) jdbcOperations.query(sql,new SubstationMapper());
		}
	}

	@Override
	public Substation getSubstationById(String id) {
		String sql="select * from m_substation where id=?";
		return jdbcOperations.queryForObject(sql,new Object[]{id},new SubstationMapper());
	}

	@Override
	public String addSubstation(Substation substation) {
		String result = "";
		try {
			//generate primary key
			substation.setId(generateId());
			if(substation.getCode()== null || substation.getCode().isEmpty()){
				substation.setCode(generateCode(Substation.class.getSimpleName(),""));
			}
			//insert record
			String sql1="insert into m_substation(code,name,remarks,m_org_id,id) values(?,?,?,?,?)";		
			if(jdbcOperations.update(sql1, setValues(substation)) > 0){
				result =  substation.getId();
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String updateSubstation(String id,Substation substation) {
		String result = "";
		substation.setId(id);
		try {
			String sql="update m_substation set code= ?,name= ?,remarks= ?,m_org_id= ? where id=?";		
			if(jdbcOperations.update(sql,setValues(substation)) > 0){
				result = substation.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deleteSubstation(String id) {
		String result = "";
		try {
			String sql="delete from m_substation where id=?";
			if(jdbcOperations.update(sql,new Object[]{id})>0){
				result =  id;
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}
	private Object[] setValues(Substation substation){
		return new Object[]{
				substation.getCode(),
				substation.getName(),
				substation.getRemarks(),
				substation.getOrgId(),
				substation.getId()
				};
	}
	final class SubstationMapper implements RowMapper<Substation>
	{

		public SubstationMapper() {
				super();
		}

		public Substation mapRow(ResultSet resultset, int rownum) throws SQLException {
			Substation substation=new Substation();
			substation.setId(resultset.getString("id"));
			substation.setCode(resultset.getString("code"));
			substation.setName(resultset.getString("name"));
			substation.setRemarks(resultset.getString("remarks"));
			substation.setOrgId(resultset.getString("m_org_id"));
			return substation;
		}
	}
}
