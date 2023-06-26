package com.ss.oa.master.tariff;
import java.sql.Date;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.ss.oa.master.vo.Tariff;

@Component
@Scope("prototype")
public class TariffDaoJdbcImpl extends BaseDaoJdbc implements TariffDao {


	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<Tariff> getAllTariffs(Map<String,Date> criteria) {

		Date from_date=null,to_date=null;
		String sql="select * from m_tariff";
		if(!criteria.isEmpty())
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			for (Entry<String, Date> element : criteria.entrySet()){
				if(element.getKey().equals("from_date"))
					from_date=element.getValue();
				if(element.getKey().equals("to_date"))
					to_date=element.getValue();
			}
				if(from_date!=null&&to_date!=null)
				{
					String sql1=sql+" where from_date>='"+sdf.format(from_date)+"' and to_date<='"+sdf.format(to_date)+"'";
					System.out.println(sql1);
					return (ArrayList<Tariff>) jdbcOperations.query(sql1,new TariffMapper());
				}  
				else if(from_date!=null&&to_date==null)
				{
					System.out.println(from_date.toString());
					String sql1=sql+" where from_date>='"+sdf.format(from_date)+"'";
					System.out.println(sql1);
					return (ArrayList<Tariff>) jdbcOperations.query(sql1,new TariffMapper());
				}  
				else
				{
					System.out.println(to_date.toString());
					String sql1=sql+" where to_date<='"+sdf.format(to_date)+"'";
					System.out.println(sql1);
					return (ArrayList<Tariff>) jdbcOperations.query(sql1,new TariffMapper());
				}  		
		}
		else
			return (ArrayList<Tariff>) jdbcOperations.query(sql,new TariffMapper());


	}
	@Override
	public Tariff getTariffById(String id) {
		String sql="select * from m_tariff where id=?";
		return jdbcOperations.queryForObject(sql,new Object[]{id},new TariffMapper());
	}
	
	/*
	 * addTariff 
	 * if success, output is primary key
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.tariff.TariffDao#addTariff(com.ss.oa.master.vo.Tariff)
	 */
	@Override
	public String addTariff(Tariff tariff) {
		String result = "";
		try {
			tariff.setId(generateId());
			//insert record
			String sql1="insert into m_tariff(type,from_date,to_date,weg_group_code,rate,reference,id) values(?,?,?,?,?,?,?)";		
			if(jdbcOperations.update(sql1, setValues(tariff)) > 0){
				result =  tariff.getId();
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
	 * updateTariff 
	 * if success, output is primary key of the record that was updated
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.tariff.TariffDao#updateTariff(java.lang.String, com.ss.oa.master.vo.Tariff)
	 */
	@Override
	public String updateTariff(String id, Tariff tariff) {
		String result = "";
		tariff.setId(id);
		try {
			String sql="update m_tariff set type= ?,from_date= ?,to_date= ?,weg_group_code= ?,rate= ?,reference= ? where id=?";		
			if(jdbcOperations.update(sql, setValues(tariff)) > 0){
				result =  tariff.getId();
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
	 * deleteTariff 
	 * if success, output is primary key of the record that was deleted
	 * if failure, output is FAILURE
	 * @see com.ss.oa.master.tariff.TariffDao#deleteTariff(java.lang.String)
	 */
	@Override
	public String deleteTariff(String id) {
		String result = "";
		try {
			String sql="delete from m_tariff where id=?";
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
	private Object[] setValues(Tariff tariff){
		return new Object[]{
				tariff.getType(),
				tariff.getFrom_date(),
				tariff.getTo_date(),
				tariff.getWeg_group_code(),
				tariff.getRate(),
				tariff.getReference(),
				tariff.getId()
				};
	}
	final class TariffMapper implements RowMapper<Tariff>
	{

		public TariffMapper() {
				super();
		}

		public Tariff mapRow(ResultSet resultset, int rownum) throws SQLException {
			Tariff tariff=new Tariff();
			tariff.setId(resultset.getString("id"));
			tariff.setRate(resultset.getString("rate"));
			tariff.setType(resultset.getString("type"));
			tariff.setReference(resultset.getString("reference"));
			tariff.setWeg_group_code(resultset.getString("weg_group_code"));
			tariff.setFrom_date(resultset.getDate("from_date"));
			tariff.setTo_date(resultset.getDate("to_date"));
			return tariff;
		}
	}
}
