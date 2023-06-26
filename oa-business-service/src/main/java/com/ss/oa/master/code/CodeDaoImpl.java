package com.ss.oa.master.code;

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
import com.ss.oa.master.vo.Code;
@Component
@Scope("prototype")
public class CodeDaoImpl extends BaseDaoJdbc implements CodeDao {

	@Resource
	private JdbcOperations jdbcOperations;	
	@Override
	public List<Code> getAllCodes(Map<String, String> criteria) {
		String list_code=null;
		String sql="select * from v_codes ";
		if(!criteria.isEmpty()) {
			
			for(Entry<String,String> element:criteria.entrySet()) {
				if(element.getKey().equals("list_code")) {
					list_code=element.getValue();
					
				}
			}
			if(list_code!=null) {
				sql=sql+"where list_code='"+list_code+"'"; 
			}	
			
		} 		
			
		 return   (ArrayList<Code>) jdbcOperations.query(sql,new CodeMapper());
		
	}
	
	final class CodeMapper implements RowMapper<Code>{
		
		public CodeMapper() {
			super();
		}

		
		public Code mapRow(ResultSet resultset, int rownum) throws SQLException {
			Code code = new Code();
			code.setListCode(resultset.getString("list_code"));
			code.setListName(resultset.getString("list_name"));
			code.setValueCode(resultset.getString("value_code"));
			code.setValueDesc(resultset.getString("value_desc"));
			code.setEnabled(resultset.getString("enabled"));
			code.setAttrib1(resultset.getString("attrib1"));
			code.setAttrib2(resultset.getString("attrib2"));
			code.setAttrib3(resultset.getString("attrib3"));
			
			return code;
		}
		
	}

}
