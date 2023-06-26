package com.ss.oa.ledger.energycharge;
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
import com.ss.oa.ledger.vo.EnergyCharge;




@Component
@Scope("prototype")
public class EnergyChargeDaoImpl extends BaseDaoJdbc implements EnergyChargeDao {
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public  List<EnergyCharge> getAllEnergyCharges(Map<String,String>criteria){
		
		String sql=" select ec.ID,ec.REMARKS,ec.CHARGE_CODE,ec.CHARGE_DESC,ec.CHARGE_TYPE_CODE,ec.UNIT_CHARGE,ec.TOTAL_CHARGES, companyservice.M_COMPANY_ID,\n" + 
				"				 		esorder.SELLER_END_ORG_ID,esorder.\"MONTH\",esorder.\"YEAR\", esorder.D_SELL_COMP_CODE,esorder.D_SELL_COMP_NAME,esorder.SELLER_COMP_SERV_ID,esorder.D_SELL_COMP_SERV_NUMBER,esorder.D_SELL_ORG_CODE,esorder.D_SELL_ORG_NAME  from F_ENERGY_CHARGES ec \n" + 
				"				 		left join F_ENERGY_SALE_ORDER esorder ON esorder.ID = ec.F_ENERGY_SALE_ORDER_ID   \n" + 
				"			 		    left join M_COMPANY_SERVICE companyservice on esorder.SELLER_COMP_SERV_ID =companyservice.id  where 1=1 ";
	   
		
		if(!criteria.isEmpty())
		{
			if(criteria.get("seller-end-org-id")!=null){
				sql += "and upper(esorder.SELLER_END_ORG_ID)  like upper('%"+criteria.get("seller-end-org-id")+"%') ";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(esorder.month)  like upper('%"+criteria.get("month")+"%') ";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(esorder.year)  like upper('%"+criteria.get("year")+"%') ";
			}
			if(criteria.get("company-id")!=null){
				sql += "and upper(companyservice.M_COMPANY_ID)  like upper('%"+criteria.get("company-id")+"%') ";
			}
			
			if(criteria.get("company-service-id")!=null){
				sql += "and upper(esorder.SELLER_COMP_SERV_ID)  like upper('%"+criteria.get("company-service-id")+"%') ";
			}
		}
		return (ArrayList<EnergyCharge>) jdbcOperations.query(sql,new EnergyChargeMapper());
		
	} 
	
		@Override
		public EnergyCharge getEnergyChargeById(String id) {
			
			
			 
		String sql= "sselect ec.ID,ec.REMARKS,ec.CHARGE_CODE,ec.CHARGE_DESC,ec.CHARGE_TYPE_CODE,ec.UNIT_CHARGE,ec.TOTAL_CHARGES, companyservice.M_COMPANY_ID,\n" + 
				"				 		esorder.SELLER_END_ORG_ID,esorder.\"MONTH\",esorder.\"YEAR\", esorder.D_SELL_COMP_CODE,esorder.D_SELL_COMP_NAME,esorder.SELLER_COMP_SERV_ID,esorder.D_SELL_COMP_SERV_NUMBER,esorder.D_SELL_ORG_CODE,esorder.D_SELL_ORG_NAME  from F_ENERGY_CHARGES ec \n" + 
				"				 		left join F_ENERGY_SALE_ORDER esorder ON esorder.ID = ec.F_ENERGY_SALE_ORDER_ID   \n" + 
				"			 		    left join M_COMPANY_SERVICE companyservice on esorder.SELLER_COMP_SERV_ID =companyservice.id     WHERE ec.ID=?";
		
		return jdbcOperations.queryForObject(sql,new Object[]{id},new EnergyChargeMapper());
		
	   }
		
		
		@Override
		public String addEnergyCharge(EnergyCharge energyCharge) {
			String result="";
			try {
				System.out.println("in add dao impl");
				
				energyCharge.setId(generateId());
				System.out.println(energyCharge);
				String sql = "insert into F_ENERGY_CHARGES(ID,REMARKS,CHARGE_CODE,CHARGE_DESC,CHARGE_TYPE_CODE,UNIT_CHARGE,TOTAL_CHARGES,M_COMPANY_SERVICE_ID)\n" + 
						"       	values(?,?,?,?,?,?,?,?)"; 
				
				if(jdbcOperations.update(sql,setValues(energyCharge)) >0) {
					result = energyCharge.getId();
					System.out.println(result);
				}else {
					result = "FAILURE";
				}
			}catch(Exception e) {
				result = "FAILURE";
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		public String updateEnergyCharge(String energyChargeId,EnergyCharge energyCharge) {
			String result="";
			energyCharge.setId(energyChargeId);
			try {
				
				System.out.println("in add dao impl");
				
				
				System.out.println(energyCharge);
				
				String sql="update F_ENERGY_CHARGES set ID=?,REMARKS=?,CHARGE_CODE=?,CHARGE_DESC=?,CHARGE_TYPE_CODE=?,UNIT_CHARGE=?,TOTAL_CHARGES=?,M_COMPANY_SERVICE_ID=? where id= ?";
					
				
				if(jdbcOperations.update(sql,setValues(energyCharge)) > 0){
					result =energyCharge.getId();
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
		
		private Object[] setValues(EnergyCharge energyCharge){
			return new Object[]{			
				
					energyCharge.getTotalCharges(),
					energyCharge.getUnitCharge(),
					energyCharge.getChargeTypeCode(),
					energyCharge.getChargeDesc(),
					energyCharge.getChargeCode(),
					energyCharge.getRemarks(),
					energyCharge.getBuyerCompanyServiceId(),
					energyCharge.getId()
				
				};
		}
		
		final class EnergyChargeMapper implements RowMapper<EnergyCharge>{
			
			public EnergyChargeMapper() {
				super();
			}
			
			
			public EnergyCharge mapRow(ResultSet resultSet, int rownum) throws SQLException {
				EnergyCharge energyCharge=new EnergyCharge();
			
				energyCharge.setId(resultSet.getString("ID"));
				energyCharge.setRemarks(resultSet.getString("REMARKS"));
				energyCharge.setChargeCode(resultSet.getString("CHARGE_CODE"));
				energyCharge.setChargeDesc(resultSet.getString("CHARGE_DESC"));
				energyCharge.setChargeTypeCode(resultSet.getString("CHARGE_TYPE_CODE"));
				energyCharge.setUnitCharge(resultSet.getString("UNIT_CHARGE"));
				energyCharge.setTotalCharges(resultSet.getString("TOTAL_CHARGES"));
				energyCharge.setCompanyId(resultSet.getString("M_COMPANY_ID"));;
				energyCharge.setSellerEndOrgId(resultSet.getString("SELLER_END_ORG_ID"));
				energyCharge.setMonth(resultSet.getString("MONTH"));
				energyCharge.setYear(resultSet.getString("YEAR"));
				energyCharge.setCompanyName(resultSet.getString("D_SELL_COMP_NAME"));
				energyCharge.setCompanyCode(resultSet.getString("D_SELL_COMP_CODE"));
				energyCharge.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
				energyCharge.setSellerCompanyServiceNumber(resultSet.getString("D_SELL_COMP_SERV_NUMBER"));				
				energyCharge.setOrgName(resultSet.getString("D_SELL_ORG_NAME"));
				energyCharge.setOrgCode(resultSet.getString("D_SELL_ORG_CODE"));
				
				return energyCharge ;
			}
			
		  }
		
		}
	
 

