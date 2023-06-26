package com.ss.oa.ledger.energyledger;

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
import com.ss.oa.ledger.vo.EnergyLedger;

@Component
@Scope("prototype")
public class EnergyLedgerDaoImpl extends BaseDaoJdbc implements EnergyLedgerDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<EnergyLedger> getAllEnergyLedgers(Map<String, String> criteria) {
		
		String sql="select ID,M_ORG_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,SERVICE_TYPE_CODE,MONTH,YEAR,FROM_DT,\n" + 
				"TO_DT,C1,C2,C3,C4,C5,LEDGER_DATE,ENERGY_IN,\n" + 
				"ENERGY_OUT,NULLIFY,REMARKS,F_ENERGY_SALE_ORDER_ID,D_COMP_NAME,D_COMP_CODE,D_COMP_SERV_NUMBER,D_ORG_NAME,D_ORG_CODE,F_ENERGY_SALE_ORDER_LINES_ID,ALLOW_LOWER_SLOT_ADMT from F_ENERGY_LEDGER where 1=1";
		
		if(!criteria.isEmpty())
		{
			if(criteria.get("org-id")!=null){
				sql += "and upper(M_ORG_ID)  like upper('%"+criteria.get("org-id")+"%') ";
			}
			if(criteria.get("org-code")!=null){
				sql += "and upper(D_ORG_CODE)  like upper('%"+criteria.get("org-code")+"%') ";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(month)  like upper('%"+criteria.get("month")+"%') ";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(year)  like upper('%"+criteria.get("year")+"%') ";
			}
			if(criteria.get("company-id")!=null){
				sql += "and upper(M_COMPANY_ID)  = upper('"+criteria.get("company-id")+"') ";
			}
			if(criteria.get("company-name")!=null){
				sql += "and upper(D_COMP_NAME)  like upper('%"+criteria.get("company-name")+"%') ";
			}
			if(criteria.get("company-service-number")!=null){
				sql += "and upper(D_COMP_SERV_NUMBER) like upper('%"+criteria.get("company-service-number")+"%') ";
			}
			if(criteria.get("company-service-id")!=null){
				sql += "and upper(M_COMPANY_SERVICE_ID) = upper('"+criteria.get("company-service-id")+"') ";
			}
			
//			if(criteria.get("ledger-date")!=null){
//				sql += "and trunc(LEDGER_DATE) = UPPER (TO_DATE('"+criteria.get("ledger-date")+"','yyyy-MM-dd'))" ;
//				
//			}

		}
		System.out.println(sql);
			 return   (ArrayList<EnergyLedger>) jdbcOperations.query(sql,new EnergyLedgerMapper());
	}

	@Override
	public EnergyLedger getEnergyLedgerById(String id) {
		
		String sql="select ID,M_ORG_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,SERVICE_TYPE_CODE,MONTH,YEAR,FROM_DT,\n" + 
				"TO_DT,C1,C2,C3,C4,C5,LEDGER_DATE,ENERGY_IN,\n" + 
				"ENERGY_OUT,NULLIFY,REMARKS,F_ENERGY_SALE_ORDER_ID,D_COMP_NAME,D_COMP_CODE,D_COMP_SERV_NUMBER,D_ORG_NAME,D_ORG_CODE,F_ENERGY_SALE_ORDER_LINES_ID,ALLOW_LOWER_SLOT_ADMT from F_ENERGY_LEDGER where id=?";
		
		return  jdbcOperations.queryForObject(sql,new Object[]{id},new EnergyLedgerMapper());
	}
	
	@Override
	public String addEnergyLedger(EnergyLedger energyLedger) {
		
		String result="";
		try {
			energyLedger.setId(generateId());
			String sql="insert into F_ENERGY_LEDGER (M_ORG_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,SERVICE_TYPE_CODE,MONTH,YEAR,FROM_DT,TO_DT,\n" + 
					"C1,C2,C3,C4,C5,LEDGER_DATE,ENERGY_IN,ENERGY_OUT,\n" + 
					"NULLIFY,REMARKS,F_ENERGY_SALE_ORDER_ID,D_COMP_NAME,D_COMP_CODE,D_COMP_SERV_NUMBER,D_ORG_NAME,D_ORG_CODE,F_ENERGY_SALE_ORDER_LINES_ID,ALLOW_LOWER_SLOT_ADMT,ID)\n" + 
					"values(?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,\n" + 
					"?,?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergyLederValues(energyLedger))>0) {
				result = energyLedger.getId();
			}else {
				result="Failure";				
			}
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateEnergyLedger(String id, EnergyLedger energyLedger) {
		String result="";
		try {
			
			String sql="update F_ENERGY_LEDGER SET M_ORG_ID=?,M_COMPANY_ID=?,M_COMPANY_SERVICE_ID=?,SERVICE_TYPE_CODE=?,MONTH=?,YEAR=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"C1=?,C2=?,C3=?,C4=?,C5=?,LEDGER_DATE=TO_DATE(?,'yyyy-mm-dd'),ENERGY_IN=?,ENERGY_OUT=?,\n" + 
					"NULLIFY=?,REMARKS=?,F_ENERGY_SALE_ORDER_ID=?,D_COMP_NAME=?,D_COMP_CODE=?,D_COMP_SERV_NUMBER=?,D_ORG_NAME=?,D_ORG_CODE=?,F_ENERGY_SALE_ORDER_LINES_ID=?,ALLOW_LOWER_SLOT_ADMT=? where id=?";
			if(jdbcOperations.update(sql,setEnergyLederValues(energyLedger))>0) {
				result = energyLedger.getId();
			}else {
				result="Failure";				
			}
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
		}
		return result;
	
	}
	
	private Object[] setEnergyLederValues(EnergyLedger energyLedger) {
		return new Object[] {
				energyLedger.getOrgId(),
				energyLedger.getCompanyId(),
				energyLedger.getCompanyServiceId(),
				energyLedger.getServiceTypeCode(),
				energyLedger.getMonth(),
				energyLedger.getYear(),
				energyLedger.getFromDate(),
				energyLedger.getToDate(),
				energyLedger.getC1(),
				energyLedger.getC2(),
				energyLedger.getC3(),
				energyLedger.getC4(),
				energyLedger.getC5(),
				energyLedger.getLedgerDate(),
				energyLedger.getEnergyIn(),
				energyLedger.getEnergyOut(),
				energyLedger.getNullify(),
				energyLedger.getRemarks(),
				energyLedger.getEnergySaleOrderId(),
				energyLedger.getCompanyName(),
				energyLedger.getCompanyCode(),
				energyLedger.getCompanyServiceNumber(),
				energyLedger.getOrgName(),
				energyLedger.getOrgCode(),
				energyLedger.getEnergySaleOrderLineId(),
				energyLedger.getAllowLowerSlotAdmt(),
				energyLedger.getId()	
				
		};
	}
	
	final class EnergyLedgerMapper implements RowMapper<EnergyLedger>{
			
			public EnergyLedgerMapper() {
				super();
			}
	
			
			public EnergyLedger mapRow(ResultSet resultSet, int rownum) throws SQLException {
				EnergyLedger energyLedger=new EnergyLedger();
				
				energyLedger.setId(resultSet.getString("ID"));
				energyLedger.setOrgId(resultSet.getString("M_ORG_ID"));			
				energyLedger.setCompanyId(resultSet.getString("M_COMPANY_ID"));
				energyLedger.setCompanyServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
				energyLedger.setServiceTypeCode(resultSet.getString("SERVICE_TYPE_CODE"));
				energyLedger.setMonth(resultSet.getString("MONTH"));
				energyLedger.setYear(resultSet.getString("YEAR"));
				energyLedger.setFromDate(resultSet.getString("FROM_DT"));
				energyLedger.setToDate(resultSet.getString("TO_DT"));
				energyLedger.setC1(resultSet.getString("C1"));
				energyLedger.setC2(resultSet.getString("C2"));
				energyLedger.setC3(resultSet.getString("C3"));
				energyLedger.setC4(resultSet.getString("C4"));
				energyLedger.setC5(resultSet.getString("C5"));
				energyLedger.setLedgerDate(resultSet.getString("LEDGER_DATE"));
				energyLedger.setEnergyIn(resultSet.getString("ENERGY_IN"));
				energyLedger.setEnergyOut(resultSet.getString("ENERGY_OUT"));
				energyLedger.setNullify(resultSet.getString("NULLIFY"));
				energyLedger.setRemarks(resultSet.getString("REMARKS"));
				energyLedger.setEnergySaleOrderId(resultSet.getString("F_ENERGY_SALE_ORDER_ID"));
				energyLedger.setCompanyCode(resultSet.getString("D_COMP_CODE"));
				energyLedger.setCompanyName(resultSet.getString("D_COMP_NAME"));
				energyLedger.setOrgCode(resultSet.getString("D_ORG_CODE"));
				energyLedger.setOrgName(resultSet.getString("D_ORG_NAME"));
				energyLedger.setCompanyServiceNumber(resultSet.getString("D_COMP_SERV_NUMBER"));	
				energyLedger.setEnergySaleOrderLineId(resultSet.getString("F_ENERGY_SALE_ORDER_LINES_ID"));;
				energyLedger.setAllowLowerSlotAdmt(resultSet.getString("ALLOW_LOWER_SLOT_ADMT"));
				return energyLedger;
			}
			
			
	}




}
