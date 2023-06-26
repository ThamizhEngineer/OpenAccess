package com.ss.oa.master.feeder;

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
import com.ss.oa.master.vo.Feeder;
@Component
@Scope("prototype")
public class FeederDaoImpl extends BaseDaoJdbc implements FeederDao {

	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<Feeder> getAllFeeders(Map<String, String> criteria) throws Exception {
		try {
			String sql="select * from m_feeder where 1=1 ";
			if(!criteria.isEmpty())
			{
				if(criteria.get("substationId")!=null){
					sql += "and m_substation_id = '"+criteria.get("substationId")+"' ";
				}
				if(criteria.get("feederName")!=null){
					sql += "and name like upper('%"+criteria.get("feederName")+"%') ";
				}
				if(criteria.get("feederVoltage")!=null){
					sql += "and voltage = '"+criteria.get("feederVoltage")+"' ";
				}
			}
			return (ArrayList<Feeder>) jdbcOperations.query(sql,new FeederMapper());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while fetching all feeders");
		}
		
	}

	@Override
	public Feeder getFeederById(String id) {
		String sql="select * from m_feeder where id=?";
		return jdbcOperations.queryForObject(sql,new Object[]{id},new FeederMapper());
	}

	@Override
	public String addFeeder(Feeder feeder) {
		String result = "";
		try {
			//generate primary key
			feeder.setId(generateId());
			if(feeder.getCode()== null || feeder.getCode().isEmpty()){
				feeder.setCode(generateCode(Feeder.class.getSimpleName(),""));
			}
			//insert record
			String sql1="insert into m_feeder(code,name,voltage,m_substation_id,remarks,enabled,id) values(?,?,?,?,?,?,?)";		
			if(jdbcOperations.update(sql1, setValues(feeder)) > 0){
				result =  feeder.getId();
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
	public String updateFeeder(String id,Feeder feeder) {
		String result = "";
		feeder.setId(id);
		try {
			String sql="update m_feeder set code= ?,name= ?,voltage= ?,m_substation_id= ?,remarks= ?,enabled= ? where id=?";		
			if(jdbcOperations.update(sql,setValues(feeder)) > 0){
				result = feeder.getId();
			}
			else{
				System.out.println("Feeder not updated");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deleteFeeder(String id) {
		String result = "";
		try {
			String sql="delete from m_feeder where id=?";
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
	private Object[] setValues(Feeder feeder){
		return new Object[]{
				feeder.getCode(),
				feeder.getName(),
				feeder.getVoltage(),
				feeder.getSubstationId(),
				feeder.getRemarks(),
				feeder.getEnabled(),
				feeder.getId()
				};
	}
	final class FeederMapper implements RowMapper<Feeder>
	{

		public FeederMapper() {
				super();
		}

		public Feeder mapRow(ResultSet resultset, int rownum) throws SQLException {
			Feeder feeder=new Feeder();
			feeder.setId(resultset.getString("id"));
			feeder.setCode(resultset.getString("code"));
			feeder.setName(resultset.getString("name"));
			feeder.setVoltage(resultset.getString("voltage"));
			feeder.setSubstationId(resultset.getString("m_substation_id"));
			feeder.setRemarks(resultset.getString("remarks"));
			feeder.setEnabled(resultset.getString("enabled"));
			return feeder;
		}
	}
}
