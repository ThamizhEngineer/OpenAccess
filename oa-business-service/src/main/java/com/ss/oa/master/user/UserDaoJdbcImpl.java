package com.ss.oa.master.user;
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

import com.ss.oa.master.vo.User;

@Component
@Scope("prototype")
public class UserDaoJdbcImpl implements UserDao{


	@Resource
	private JdbcOperations jdbcOperations;
		
	
	@Override
	public List<User> getAllUser(Map<String, String> criteria) {
		//TO-DO based on the criteria, the sql will be appended with filter condition
		
		String sql="select * from m_user";
		return (ArrayList<User>) jdbcOperations.query(sql,new UserMapper());

	}
	
	@Override
	public User getUserById(String id) {
		String sql="select * from m_user where id=?";
		return jdbcOperations.queryForObject(sql,new Object[]{id},new UserMapper());
	}
	
	/*
	 * addUser 
	 * if success, output is primary key
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.user.UserDao#addUser(com.ss.oa.master.vo.User)
	 */
	@Override
	public String addUser(User user) {
		String result = "";
		try {
			//generate primary key
			String sql="select user_id_seq.nextval from dual";
			user.setId(jdbcOperations.queryForObject(sql,String.class));
			
			//insert record
			String sql1="insert into m_user(first_Name,last_name,type,enabled,m_org_id,m_company_id,id) values(?,?,?,?,?,?,?)";		
			if(jdbcOperations.update(sql1, setValues(user)) > 0){
				result =  user.getId();
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
	
	/*
	 * updateUser 
	 * if success, output is primary key of the record that was updated
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.user.UserDao#updateUser(java.lang.String, com.ss.oa.master.vo.User)
	 */
	@Override
	public String updateUser(String id, User user) {
		String result = "";
		user.setId(id);
		try {
			String sql="update m_user set first_name= ?,last_name= ?,type= ?,enabled= ?,m_org_id= ?,m_company_id= ? where id=?";		
			if(jdbcOperations.update(sql, setValues(user)) > 0)
			{
				result =  user.getId();
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
	
	/*
	 * deleteUser 
	 * if success, output is primary key of the record that was deleted
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.user.UserDao#deleteUser(java.lang.String)
	 */
	@Override
	public String deleteUser(String id) {
		String result = "";
		try {
			String sql="delete from m_user where id=?";
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
	

	private Object[] setValues(User user){
		return new Object[]{
				
				user.getFirstName(),
				user.getLastName(),
				user.getType(),
				user.getEnabled(),
				user.getOrgId(),
				user.getCompanyId(),
				user.getId()
				}; 
	}
	
	final class UserMapper implements RowMapper<User>
	{

		public UserMapper() {
				super();
		}

		public User mapRow(ResultSet resultset, int rownum) throws SQLException {
			User user=new User();
			user.setId(resultset.getString("ID"));
			user.setFirstName(resultset.getString("FIRST_NAME"));
			user.setLastName(resultset.getString("LAST_NAME"));
			user.setType(resultset.getString("TYPE"));
			user.setCompanyId(resultset.getString("M_COMPANY_ID"));
			user.setOrgId(resultset.getString("M_ORG_ID"));
			user.setEnabled(resultset.getString("ENABLED"));
			return user;
		}
	}

	}
