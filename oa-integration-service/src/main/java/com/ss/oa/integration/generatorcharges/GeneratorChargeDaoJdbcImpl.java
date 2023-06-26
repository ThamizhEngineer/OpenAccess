package com.ss.oa.integration.generatorcharges;
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
import com.ss.oa.vo.GeneratorChargeForDAO;
@Component
public class GeneratorChargeDaoJdbcImpl extends BaseDaoJdbc implements GeneratorChargeDao {
	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<GeneratorChargeForDAO> getAllGeneratorCharges(Map<String, String> criteria) {
		String sql="SELECT ec.CHARGE_CODE, ec.CHARGE_DESC, ec.TOTAL_CHARGES charge_amount, es.D_SELL_COMP_NAME generator_name, es.D_SELL_COMP_SERV_NUMBER service_number,  es.\"MONTH\" billing_month, es.\"YEAR\" billing_year, es.D_SELL_ORG_NAME, es.D_SELL_ORG_CODE "+
					"FROM F_ENERGY_CHARGES ec LEFT JOIN F_ENERGY_SALE_ORDER es ON ec.F_ENERGY_SALE_ORDER_ID = es.id where  es.D_SELL_COMP_SERV_NUMBER is not null ";
		if(!criteria.isEmpty())
		{
			if(criteria.get("serviceNumber")!=null){
				sql += "AND es.D_SELL_COMP_SERV_NUMBER = '"+criteria.get("serviceNumber")+"' ";
			}
			if(criteria.get("billingMonth")!=null){
				sql += "and es.\"MONTH\"='"+criteria.get("billingMonth")+"' ";
			}
			if(criteria.get("billingYear")!=null){
				sql += "and es.\"YEAR\"='"+criteria.get("billingYear")+"' ";
			}
		}
		//System.out.println("sql-"+sql);
		
		return   (ArrayList<GeneratorChargeForDAO>) jdbcOperations.query(sql,new GeneratorChargeMapper());
			 
	}

	final class GeneratorChargeMapper implements RowMapper<GeneratorChargeForDAO>
	{

		public GeneratorChargeMapper() {
				super();
		}

		public GeneratorChargeForDAO mapRow(ResultSet resultset, int rownum) throws SQLException {
			GeneratorChargeForDAO generatorCharge=new GeneratorChargeForDAO();
			generatorCharge.setServiceNumber(resultset.getString("service_number"));
			generatorCharge.setGeneratorName(resultset.getString("generator_name"));
			generatorCharge.setBillingMonth(resultset.getString("billing_month"));
			generatorCharge.setBillingYear(resultset.getString("billing_year"));
			generatorCharge.setChargeCode(resultset.getString("charge_code"));
			generatorCharge.setChargeDesc(resultset.getString("charge_desc"));
			generatorCharge.setChargeAmount(resultset.getString("charge_amount"));
			return generatorCharge;
		}
	}
}
