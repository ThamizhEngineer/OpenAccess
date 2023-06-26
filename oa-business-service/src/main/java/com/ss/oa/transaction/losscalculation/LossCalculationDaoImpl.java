package com.ss.oa.transaction.losscalculation;

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
import com.ss.oa.transaction.vo.LossCalculation;

@Scope("prototype")
@Component
public class LossCalculationDaoImpl extends BaseDaoJdbc implements LossCalculationDao  {
	@Resource
	private JdbcOperations jdbcOperations;	

	@Override
	public List<LossCalculation> fetchLossCalculation(Map<String, String> criteria) {
		// TODO Auto-generated method stub
		
		String ID=null;
		String sql="select * from M_LOSS_CALC_CHART";
		if(!criteria.isEmpty()) {
			
			for(Entry<String,String> element:criteria.entrySet()) {
				if(element.getKey().equals("ID")) {
					ID=element.getValue();
					
				}
			}
			if(ID!=null) {
				sql=sql+"where ID='"+ID+"'"; 
			}	
			
		} 		
			
		 return   (ArrayList<LossCalculation>) jdbcOperations.query(sql,new LossCalculationMapper());
		
	}

	
	final class LossCalculationMapper implements RowMapper<LossCalculation>{
		
		public LossCalculationMapper() {
			super();
		}

		
		public LossCalculation mapRow(ResultSet resultset, int rownum) throws SQLException {
			LossCalculation losscalculation = new LossCalculation();
			losscalculation.setId(resultset.getString("ID"));
			losscalculation.setInjectionVoltageCode(resultset.getString("INJECTION_VOLTAGE_CODE"));
			losscalculation.setDrawalVoltageCode(resultset.getString("DRAWAL_VOLTAGE_CODE"));
			losscalculation.setTransLossPercent(resultset.getString("TRANS_LOSS_PERCENT"));
			losscalculation.setDistLossPercent(resultset.getString("DIST_LOSS_PERCENT"));
			losscalculation.setTotalLossPercent(resultset.getString("TOTAL_LOSS_PERCENT"));

			
			return losscalculation;
		}
		
	}



}
