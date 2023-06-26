package com.ss.oa.ledger.energysaleorder;

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

import com.ss.oa.ledger.vo.EnergySaleOrder;
import com.ss.oa.ledger.vo.EnergySaleOrderLine;


@Component
@Scope("prototype")
public class EnergySaleOrderDaoImpl extends BaseDaoJdbc implements EnergySaleOrderDao {

	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<EnergySaleOrder> getAllEnergySaleOrders(Map<String, String> criteria) {
		String sql="select esorder.ID,esorder.T_ENERGY_SALE_ID,esorder.SELLER_COMP_SERV_ID,companyservice.COMP_SER_TYPE_CODE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE, esorder.SELLER_END_ORG_ID,esorder.MONTH,esorder.YEAR,esorder.INJECTING_VOLTAGE_CODE,code.value_desc INJECTING_VOLTAGE_NAME,esorder.FROM_DT,\n" + 
				"esorder.TO_DT,esorder.LOSS,esorder.MULTIPLE_BUYERS,esorder.USAGE_DETAIL_AVAIL,esorder.SIMPLE_ENERGY_SALE,esorder.TOTAL_C1,esorder.TOTAL_C2,esorder.TOTAL_C3,\n" + 
				"esorder.TOTAL_C4,esorder.TOTAL_C5,esorder.TOTAL_GEN_UNITS_SOLD,esorder.TOTAL_BC1,esorder.TOTAL_BC2,esorder.TOTAL_BC3,esorder.TOTAL_BC4,esorder.TOTAL_BC5,\n" + 
				"esorder.TOTAL_BANKING_UNITS_SOLD,esorder.TOTAL_UNITS_SOLD,esorder.D_SELL_COMP_NAME,esorder.D_SELL_COMP_CODE,esorder.D_SELL_COMP_SERV_NUMBER,esorder.D_SELL_ORG_NAME,esorder.D_SELL_ORG_CODE,esorder.TOTAL_GC1,esorder.TOTAL_GC2,esorder.TOTAL_GC3,esorder.TOTAL_GC4,esorder.TOTAL_GC5,esorder.BANKING_SERVICE_ID,esorder.BANKING_SERVICE_NUMBER,esorder.SELLER_COMP_ID,esorder.ALLOW_LOWER_SLOT_ADMT,esorder.CREATED_DATE from F_ENERGY_SALE_ORDER esorder\n" + 
				"left join T_ENERGY_SALE es on esorder.T_ENERGY_SALE_ID = es.ID \n" + 
				"left join v_codes code on code.value_code=esorder.injecting_voltage_code and code.list_code='VOLTAGE_CODE'\n" + 
				"left join V_COMPANY_SERVICE companyservice on esorder.SELLER_COMP_SERV_ID = companyservice.id \n" +
				"left join M_FUEL fuel on  companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE  where 1=1 ";

		if(!criteria.isEmpty())
		{
			if(criteria.get("org-id")!=null){
				sql += "and upper(esorder.SELLER_END_ORG_ID)  = upper('"+criteria.get("org-id")+"') ";
			}
			if(criteria.get("company-id")!=null){
				sql += "and upper(companyservice.M_COMPANY_ID)  = upper('"+criteria.get("company-id")+"') ";
			}
			if(criteria.get("company-name")!=null){
				sql += "and upper(esorder.D_SELL_COMP_NAME)  like upper('%"+criteria.get("company-name")+"%') ";
			}
			if(criteria.get("company-service-number")!=null){
				sql += "and upper(esorder.D_SELL_COMP_SERV_NUMBER) = upper('"+criteria.get("company-service-number")+"') ";
			}
			if(criteria.get("company-service-id")!=null){
				sql += "and upper(esorder.SELLER_COMP_SERV_ID) = upper('"+criteria.get("company-service-id")+"') ";
			}
			if(criteria.get("org-code")!=null){
				sql += "and upper(esorder.D_SELL_ORG_CODE)  = upper('"+criteria.get("org-code")+"') ";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(esorder.MONTH)  = upper('"+criteria.get("month")+"') ";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(esorder.YEAR)  = upper('"+criteria.get("year")+"') ";
			}
			if(criteria.get("fuelTypeCode")!=null){
				sql += "and upper(companyservice.FUEL_TYPE_CODE)  = upper('"+criteria.get("fuelTypeCode")+"') ";
			}
			if(criteria.get("fuelTypeName")!=null){
				sql += "and upper(fuel.FUEL_NAME)  = upper('"+criteria.get("fuelTypeName")+"') ";
			}
			if(criteria.get("fuelGroupe")!=null){
				sql += "and upper(fuel.FUEL_GROUP)  = upper('"+criteria.get("fuelGroupe")+"') ";
			}
		}
		return (ArrayList<EnergySaleOrder>) jdbcOperations.query(sql,new EnergySaleOrderMapper());
	}
	
	@Override
	public List<EnergySaleOrder> getAllEnergySaleOrdersBuyers(Map<String, String> criteria) {
		String sql="select esorder.ID,esorder.T_ENERGY_SALE_ID,esorder.SELLER_COMP_SERV_ID,companyservice.COMP_SER_TYPE_CODE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE, esorder.SELLER_END_ORG_ID,esorder.MONTH,esorder.YEAR,esorder.INJECTING_VOLTAGE_CODE,code.value_desc INJECTING_VOLTAGE_NAME,esorder.FROM_DT,\r\n" + 
				"esorder.TO_DT,esorder.LOSS,esorder.MULTIPLE_BUYERS,esorder.USAGE_DETAIL_AVAIL,esorder.SIMPLE_ENERGY_SALE,esorder.TOTAL_C1,esorder.TOTAL_C2,esorder.TOTAL_C3,\r\n" + 
				"esorder.TOTAL_C4,esorder.TOTAL_C5,esorder.TOTAL_GEN_UNITS_SOLD,esorder.TOTAL_BC1,esorder.TOTAL_BC2,esorder.TOTAL_BC3,esorder.TOTAL_BC4,esorder.TOTAL_BC5, \r\n" + 
				"esorder.TOTAL_BANKING_UNITS_SOLD,esorder.TOTAL_UNITS_SOLD,esorder.D_SELL_COMP_NAME,esorder.D_SELL_COMP_CODE,esorder.D_SELL_COMP_SERV_NUMBER,esorder.D_SELL_ORG_NAME,esorder.D_SELL_ORG_CODE,\r\n" + 
				"esorder.TOTAL_GC1,esorder.TOTAL_GC2,esorder.TOTAL_GC3,esorder.TOTAL_GC4,esorder.TOTAL_GC5,esorder.BANKING_SERVICE_ID,esorder.BANKING_SERVICE_NUMBER,esorder.SELLER_COMP_ID,esorder.ALLOW_LOWER_SLOT_ADMT,esorder.CREATED_DATE from F_ENERGY_SALE_ORDER esorder\r\n" + 
				"left join T_ENERGY_SALE es on esorder.T_ENERGY_SALE_ID = es.ID \r\n" + 
				"left join v_codes code on code.value_code=esorder.injecting_voltage_code and code.list_code='VOLTAGE_CODE'\r\n" + 
				"left join V_COMPANY_SERVICE companyservice on esorder.SELLER_COMP_SERV_ID = companyservice.id \r\n" + 
				"left join M_FUEL fuel on  companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE  where esorder.seller_comp_serv_id in \r\n" + 
				"(select distinct m_seller_comp_service_id from m_trade_relationship where m_buyer_comp_service_id in \r\n" + 
				"(select id from m_company_service where comp_ser_type_code='02'";

		if(!criteria.isEmpty())
		{
			if(criteria.get("org-id")!=null){
				sql += "and upper(M_ORG_ID)  = upper('"+criteria.get("org-id")+"'))) ";
				System.out.println("org-id - " + criteria.get("org-id"));
			}
//			if(criteria.get("org-id")!=null){
//				sql += "and upper(m_org_id)  = upper('"+criteria.get("org-id")+"')))";
//			}
			if(criteria.get("company-service-number")!=null){
				sql += "and upper(esorder.D_SELL_COMP_SERV_NUMBER) = upper('"+criteria.get("company-service-number")+"') ";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(esorder.MONTH)  = upper('"+criteria.get("month")+"') ";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(esorder.YEAR)  = upper('"+criteria.get("year")+"') ";
			}
			if(criteria.get("fuelTypeCode")!=null){
				sql += "and upper(companyservice.FUEL_TYPE_CODE)  = upper('"+criteria.get("fuelTypeCode")+"') ";
			}
		}
		return (ArrayList<EnergySaleOrder>) jdbcOperations.query(sql,new EnergySaleOrderMapper());
	}

	@Override
	public EnergySaleOrder getEnergySaleOrderById(String id) {
		EnergySaleOrder energySaleOrder = new EnergySaleOrder();
		List<EnergySaleOrderLine> energySaleOrderLines  = new ArrayList<EnergySaleOrderLine>();
		List<EnergyCharge> energyCharges = new ArrayList<EnergyCharge>();
		
		String sql="select esorder.ID,esorder.T_ENERGY_SALE_ID,esorder.SELLER_COMP_SERV_ID,companyservice.COMP_SER_TYPE_CODE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE,esorder.SELLER_END_ORG_ID,esorder.MONTH,esorder.YEAR,esorder.INJECTING_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as INJECTING_VOLTAGE_NAME,esorder.FROM_DT,\n" + 
				"esorder.TO_DT,esorder.LOSS,esorder.MULTIPLE_BUYERS,esorder.USAGE_DETAIL_AVAIL,esorder.SIMPLE_ENERGY_SALE,esorder.TOTAL_C1,esorder.TOTAL_C2,esorder.TOTAL_C3,\n" + 
				"esorder.TOTAL_C4,esorder.TOTAL_C5,esorder.TOTAL_GEN_UNITS_SOLD,esorder.TOTAL_BC1,esorder.TOTAL_BC2,esorder.TOTAL_BC3,esorder.TOTAL_BC4,esorder.TOTAL_BC5,\n" + 
				"esorder.TOTAL_BANKING_UNITS_SOLD,esorder.TOTAL_UNITS_SOLD,esorder.D_SELL_COMP_NAME,esorder.D_SELL_COMP_CODE,esorder.D_SELL_COMP_SERV_NUMBER,esorder.D_SELL_ORG_NAME,esorder.D_SELL_ORG_CODE,esorder.TOTAL_GC1,esorder.TOTAL_GC2,esorder.TOTAL_GC3,esorder.TOTAL_GC4,esorder.TOTAL_GC5,esorder.BANKING_SERVICE_ID,esorder.BANKING_SERVICE_NUMBER,esorder.SELLER_COMP_ID,esorder.ALLOW_LOWER_SLOT_ADMT,esorder.CREATED_DATE  from F_ENERGY_SALE_ORDER esorder\n" + 
				"left join T_ENERGY_SALE es on esorder.T_ENERGY_SALE_ID = es.ID \n" + 
				"left join v_company_service companyservice on esorder.SELLER_COMP_SERV_ID = companyservice.id \n" +
                "left join M_FUEL fuel on  companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE where esorder.ID=?";
		energySaleOrder = jdbcOperations.queryForObject(sql,new Object[]{id},new EnergySaleOrderMapper());
		String sql1="select esorderlines.ID,esorderlines.F_ENERGY_SALE_ORDER_ID,esorderlines.T_ENERGY_SALE_ID,esorderlines.BUYER_END_ORG_ID,esorderlines.BUYER_END_SS_ID,esorderlines.BUYER_COMP_SERV_ID,\n" + 
				"				esorderlines.C1,esorderlines.C2,esorderlines.C3,esorderlines.C4,esorderlines.C5,esorderlines.GEN_UNITS_SOLD,\n" + 
				"				esorderlines.BC1,esorderlines.BC2,esorderlines.BC3,esorderlines.BC4,esorderlines.BC5,esorderlines.BANKING_UNITS_SOLD,\n" + 
				"				esorderlines.TOTAL_UNITS_SOLD,esorderlines.D_BUYER_COMP_NAME,esorderlines.D_BUYER_COMP_CODE,esorderlines.D_BUYER_COMP_SERV_NAME,esorderlines.D_BUYER_ORG_NAME,esorderlines.D_BUYER_ORG_CODE,\n" + 
				"                esorderlines.D_BUYER_SS_NAME,esorderlines.GC1,esorderlines.GC2,esorderlines.GC3,esorderlines.GC4,esorderlines.GC5,companyservice.COMP_SER_TYPE_CODE,esorderlines.BUYER_COMP_ID,esorderlines.D_DRAWAL_VOLTAGE_CODE as DRAWAL_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as DRAWAL_VOLTAGE_NAME,\n" + 
				"                esorderlines.UNIT_COST,esorderlines.TOTAL_AMOUNT_PAYABLE,esorderlines.TOTAL_AMOUNT_CHARGABLE,esorderlines.NET_AMOUNT_PAYABLE from F_ENERGY_SALE_ORDER_LINES esorderlines\n" + 
				"                left join T_ENERGY_SALE es on esorderlines.T_ENERGY_SALE_ID = es.ID \n" + 
				"				left join V_COMPANY_SERVICE companyservice on esorderlines.BUYER_COMP_SERV_ID = companyservice.id where esorderlines.F_ENERGY_SALE_ORDER_ID=?";
		energySaleOrderLines = jdbcOperations.query(sql1,new Object[]{id},new EnergySaleOrderLineMapper());
		String sql2="select energycharges.ID,energycharges.REMARKS,energycharges.CHARGE_CODE,energycharges.CHARGE_DESC,\n" + 
				"energycharges.CHARGE_TYPE_CODE,energycharges.UNIT_CHARGE,energycharges.TOTAL_CHARGES,energycharges.F_ENERGY_SALE_ORDER_ID,companyservice.\"number\" as servicenumber,companyservice.m_company_name from F_ENERGY_CHARGES energycharges left join V_COMPANY_SERVICE companyservice on energycharges.M_COMPANY_SERVICE_ID = companyservice.id where F_ENERGY_SALE_ORDER_ID=?  order by energycharges.M_COMPANY_SERVICE_ID , energycharges.CHARGE_CODE";
		
		energyCharges = jdbcOperations.query(sql2,new Object[]{id},new EnergyChargeMapper());
		energySaleOrder.setEnergySaleOrderLines(energySaleOrderLines);
		energySaleOrder.setEnergyCharge(energyCharges);
		
		return energySaleOrder;
	}
	
	@Override
	public String addEnergySaleOrder(EnergySaleOrder energySaleOrder) {
		String result="";
		
		try {
			energySaleOrder.setId(generateId());
			String sql ="insert into F_ENERGY_SALE_ORDER(T_ENERGY_SALE_ID,SELLER_COMP_SERV_ID,SELLER_END_ORG_ID,MONTH,YEAR,INJECTING_VOLTAGE_CODE,FROM_DT,TO_DT,\n" + 
					"LOSS,MULTIPLE_BUYERS,USAGE_DETAIL_AVAIL,SIMPLE_ENERGY_SALE,TOTAL_C1,TOTAL_C2,TOTAL_C3,TOTAL_C4,\n" + 
					"TOTAL_C5,TOTAL_GEN_UNITS_SOLD,TOTAL_BC1,TOTAL_BC2,TOTAL_BC3,TOTAL_BC4,TOTAL_BC5,TOTAL_BANKING_UNITS_SOLD,\n" + 
					"TOTAL_UNITS_SOLD,D_SELL_COMP_NAME,D_SELL_COMP_CODE,D_SELL_COMP_SERV_NUMBER,D_SELL_ORG_NAME,D_SELL_ORG_CODE,TOTAL_GC1,TOTAL_GC2,TOTAL_GC3,TOTAL_GC4,TOTAL_GC5,BANKING_SERVICE_ID,BANKING_SERVICE_NUMBER,SELLER_COMP_ID,ALLOW_LOWER_SLOT_ADMT,CREATED_DATE,ID)values(\n" + 
					"?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?)";	
			if(jdbcOperations.update(sql,setEnergySaleOrderValues(energySaleOrder))>0) {
						result = energySaleOrder.getId();
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
	public String updateEnergySaleOrder(String id, EnergySaleOrder energySaleOrder) {
		String result="";
		
		try {
	
			String sql ="update F_ENERGY_SALE_ORDER set T_ENERGY_SALE_ID=?,SELLER_COMP_SERV_ID=?,SELLER_END_ORG_ID=?,MONTH=?,YEAR=?,INJECTING_VOLTAGE_CODE=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"LOSS=?,MULTIPLE_BUYERS=?,USAGE_DETAIL_AVAIL=?,SIMPLE_ENERGY_SALE=?,TOTAL_C1=?,TOTAL_C2=?,TOTAL_C3=?,TOTAL_C4=?,\n" + 
					"TOTAL_C5=?,TOTAL_GEN_UNITS_SOLD=?,TOTAL_BC1=?,TOTAL_BC2=?,TOTAL_BC3=?,TOTAL_BC4=?,TOTAL_BC5=?,TOTAL_BANKING_UNITS_SOLD=?,\n" + 
					"TOTAL_UNITS_SOLD=?,D_SELL_COMP_NAME=?,D_SELL_COMP_CODE=?,D_SELL_COMP_SERV_NUMBER=?,D_SELL_ORG_NAME=?,D_SELL_ORG_CODE=?,TOTAL_GC1=?,TOTAL_GC2=?,TOTAL_GC3=?,TOTAL_GC4=?,TOTAL_GC5=?BANKING_SERVICE_ID=?,BANKING_SERVICE_NUMBER=?,SELLER_COMP_ID=?,ALLOW_LOWER_SLOT_ADMT=?,CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'),where id=?";	
			if(jdbcOperations.update(sql,setEnergySaleOrderValues(energySaleOrder))>0) {
						result = energySaleOrder.getId();
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
	public String addEnergySaleOrderLine(EnergySaleOrderLine energySaleOrderLine) {
		String result="";
		
		try {
			energySaleOrderLine.setId(generateId());
			String sql ="insert into F_ENERGY_SALE_ORDER_LINES(\n" + 
					"F_ENERGY_SALE_ORDER_ID,T_ENERGY_SALE_ID,BUYER_END_ORG_ID,BUYER_END_SS_ID,BUYER_COMP_SERV_ID,C1,C2,C3,\n" + 
					"C4,C5,GEN_UNITS_SOLD,BC1,BC2,BC3,BC4,BC5,\n" + 
					"BANKING_UNITS_SOLD,TOTAL_UNITS_SOLD,D_BUYER_COMP_NAME,D_BUYER_COMP_CODE,D_BUYER_COMP_SERV_NAME,D_BUYER_ORG_NAME,D_BUYER_ORG_CODE,D_BUYER_SS_NAME,GC1,GC2,GC3,GC4,GC5,BUYER_COMP_ID,D_DRAWAL_VOLTAGE_CODE,UNIT_COST,TOTAL_AMOUNT_PAYABLE,TOTAL_AMOUNT_CHARGABLE,NET_AMOUNT_PAYABLE,ID)values(\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
			if(jdbcOperations.update(sql,setEnergySaleOrderLinesValues(energySaleOrderLine))>0) {
						result = energySaleOrderLine.getId();
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
	public String updateEnergySaleOrderLine(EnergySaleOrderLine energySaleOrderLine) {
		String result="";
		
		try {
			
			String sql ="update F_ENERGY_SALE_ORDER_LINES set F_ENERGY_SALE_ORDER_ID=?,T_ENERGY_SALE_ID=?,BUYER_END_ORG_ID=?,BUYER_END_SS_ID=?,BUYER_COMP_SERV_ID=?,C1=?,C2=?,C3=?,\n" + 
					"C4=?,C5=?,GEN_UNITS_SOLD=?,BC1=?,BC2=?,BC3=?,BC4=?,BC5=?,\n" + 
					"BANKING_UNITS_SOLD=?,TOTAL_UNITS_SOLD=?,D_BUYER_COMP_NAME=?,D_BUYER_COMP_CODE=?,D_BUYER_COMP_SERV_NAME=?,D_BUYER_ORG_NAME=?,D_BUYER_ORG_CODE=?,D_BUYER_SS_NAME=?,GC1=?,GC2=?,GC3=?,GC4=?,GC5=?,BUYER_COMP_ID=?,D_DRAWAL_VOLTAGE_CODE=?,,UNIT_COST=?,TOTAL_AMOUNT_PAYABLE=?,TOTAL_AMOUNT_CHARGABLE=?,NET_AMOUNT_PAYABLE=? where id=?";	
			if(jdbcOperations.update(sql,setEnergySaleOrderLinesValues(energySaleOrderLine))>0) {
						result = energySaleOrderLine.getId();
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
	public String addEnergyCharge(EnergyCharge energyCharge) {
		String result="";
		
		try {
			energyCharge.setId(generateId());
			String sql ="insert into F_ENERGY_CHARGES(REMARKS,CHARGE_CODE,CHARGE_DESC,CHARGE_TYPE_CODE,UNIT_CHARGE,TOTAL_CHARGES,F_ENERGY_SALE_ORDER_ID,ID)VALUES(?,?,?,?,?,?,?,?)";	
			if(jdbcOperations.update(sql,setEnergyChargeValues(energyCharge))>0) {
						result = energyCharge.getId();
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
	public String updateEnergyCharge(EnergyCharge energyCharge) {
		String result="";
		
		try {
			
			String sql ="update F_ENERGY_CHARGES set REMARKS=?,CHARGE_CODE=?,CHARGE_DESC=?,CHARGE_TYPE_CODE=?,UNIT_CHARGE=?,TOTAL_CHARGES=?,F_ENERGY_SALE_ORDER_ID=? where id=?";	
			if(jdbcOperations.update(sql,setEnergyChargeValues(energyCharge))>0) {
						result = energyCharge.getId();
					}else {
						result="Failure";				
					}
				}catch(Exception e) {
					result="Failure";
					e.printStackTrace();
				}
			return result;
	}

	
	final class EnergySaleOrderMapper implements RowMapper<EnergySaleOrder>{
			
			public EnergySaleOrderMapper() {
				super();
			}
	
			
			public EnergySaleOrder mapRow(ResultSet resultSet, int rownum) throws SQLException {
				EnergySaleOrder energySaleOrder=new EnergySaleOrder();
				
				energySaleOrder.setId(resultSet.getString("ID"));
				energySaleOrder.setEnergySaleId(resultSet.getString("T_ENERGY_SALE_ID"));
				energySaleOrder.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
				energySaleOrder.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
				energySaleOrder.setSellerCompanyServiceTypeCode(resultSet.getString("COMP_SER_TYPE_CODE"));
				energySaleOrder.setSellerEndOrgId(resultSet.getString("SELLER_END_ORG_ID"));
				energySaleOrder.setMonth(resultSet.getString("MONTH"));
				energySaleOrder.setYear(resultSet.getString("YEAR"));
				energySaleOrder.setInjectingVoltageCode(resultSet.getString("INJECTING_VOLTAGE_CODE"));
				energySaleOrder.setInjectingVoltageName(resultSet.getString("INJECTING_VOLTAGE_NAME"));
				energySaleOrder.setFromDate(resultSet.getString("FROM_DT"));
				energySaleOrder.setToDate(resultSet.getString("TO_DT"));
				energySaleOrder.setLoss(resultSet.getString("LOSS"));				
				energySaleOrder.setMultipleBuyers(resultSet.getString("MULTIPLE_BUYERS"));
				energySaleOrder.setUsageDetailAvail(resultSet.getString("USAGE_DETAIL_AVAIL"));
				energySaleOrder.setSimpleEnergySale(resultSet.getString("SIMPLE_ENERGY_SALE"));
				energySaleOrder.setTotalC1(resultSet.getString("TOTAL_C1"));
				energySaleOrder.setTotalC2(resultSet.getString("TOTAL_C2"));
				energySaleOrder.setTotalC3(resultSet.getString("TOTAL_C3"));
				energySaleOrder.setTotalC4(resultSet.getString("TOTAL_C4"));
				energySaleOrder.setTotalC5(resultSet.getString("TOTAL_C5"));
				energySaleOrder.setTotalBc1(resultSet.getString("TOTAL_BC1"));
				energySaleOrder.setTotalBc2(resultSet.getString("TOTAL_BC2"));
				energySaleOrder.setTotalBc3(resultSet.getString("TOTAL_BC3"));
				energySaleOrder.setTotalBc4(resultSet.getString("TOTAL_BC4"));
				energySaleOrder.setTotalBc5(resultSet.getString("TOTAL_BC5"));
				energySaleOrder.setTotalGenUnitsSold(resultSet.getString("TOTAL_GEN_UNITS_SOLD"));
				energySaleOrder.setTotalBankingUnitsSold(resultSet.getString("TOTAL_BANKING_UNITS_SOLD"));
				energySaleOrder.setTotalUnitsSold(resultSet.getString("TOTAL_UNITS_SOLD"));
				energySaleOrder.setSellerCompanyName(resultSet.getString("D_SELL_COMP_NAME"));
				energySaleOrder.setSellerCompanyCode(resultSet.getString("D_SELL_COMP_CODE"));
				energySaleOrder.setSellerCompanyServiceNumber(resultSet.getString("D_SELL_COMP_SERV_NUMBER"));				
				energySaleOrder.setSellerEndOrgName(resultSet.getString("D_SELL_ORG_NAME"));
				energySaleOrder.setSellerEndOrgCode(resultSet.getString("D_SELL_ORG_CODE"));
				energySaleOrder.setTotalGc1(resultSet.getString("TOTAL_GC1"));
				energySaleOrder.setTotalGc2(resultSet.getString("TOTAL_GC2"));
				energySaleOrder.setTotalGc3(resultSet.getString("TOTAL_GC3"));
				energySaleOrder.setTotalGc4(resultSet.getString("TOTAL_GC4"));
				energySaleOrder.setTotalGc5(resultSet.getString("TOTAL_GC5"));
				energySaleOrder.setSellerCompBankingServiceId(resultSet.getString("BANKING_SERVICE_ID"));
				energySaleOrder.setSellerCompBankingServiceNumber(resultSet.getString("BANKING_SERVICE_NUMBER"));
				energySaleOrder.setAllowLowerSlotAdmt(resultSet.getString("ALLOW_LOWER_SLOT_ADMT"));
				energySaleOrder.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
				energySaleOrder.setFuelTypeName(resultSet.getString("FUEL_TYPE_NAME"));
				energySaleOrder.setFuelGroupe(resultSet.getString("FUEL_GROUPE"));
				energySaleOrder.setCreatedDate(resultSet.getString("CREATED_DATE"));
				
				
				return energySaleOrder;
			}
	}
	
	final class EnergySaleOrderLineMapper implements RowMapper<EnergySaleOrderLine>{
		
		public EnergySaleOrderLineMapper() {
			super();
		}
	
		
		public EnergySaleOrderLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySaleOrderLine energySaleOrderLine=new EnergySaleOrderLine();
			energySaleOrderLine.setId(resultSet.getString("ID"));
			energySaleOrderLine.setEnergySaleOrderId(resultSet.getString("F_ENERGY_SALE_ORDER_ID"));
			energySaleOrderLine.setEnergySaleId(resultSet.getString("T_ENERGY_SALE_ID"));
			energySaleOrderLine.setBuyerEndOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			energySaleOrderLine.setBuyerEndSsId(resultSet.getString("BUYER_END_SS_ID"));
			energySaleOrderLine.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			energySaleOrderLine.setC1(resultSet.getString("C1"));
			energySaleOrderLine.setC2(resultSet.getString("C2"));
			energySaleOrderLine.setC3(resultSet.getString("C3"));
			energySaleOrderLine.setC4(resultSet.getString("C4"));
			energySaleOrderLine.setC5(resultSet.getString("C5"));
			energySaleOrderLine.setBc1(resultSet.getString("BC1"));
			energySaleOrderLine.setBc2(resultSet.getString("BC2"));
			energySaleOrderLine.setBc3(resultSet.getString("BC3"));
			energySaleOrderLine.setBc4(resultSet.getString("BC4"));
			energySaleOrderLine.setBc5(resultSet.getString("BC5"));			
			energySaleOrderLine.setGenUnitsSold(resultSet.getString("GEN_UNITS_SOLD"));
			energySaleOrderLine.setBankingUnitsSold(resultSet.getString("BANKING_UNITS_SOLD"));
			energySaleOrderLine.setTotalUnitsSold(resultSet.getString("TOTAL_UNITS_SOLD"));
			energySaleOrderLine.setBuyerCompanyCode(resultSet.getString("D_BUYER_COMP_CODE"));
			energySaleOrderLine.setBuyerCompanyName(resultSet.getString("D_BUYER_COMP_NAME"));
			energySaleOrderLine.setBuyerEndOrgCode(resultSet.getString("D_BUYER_ORG_CODE"));
			energySaleOrderLine.setBuyerEndOrgName(resultSet.getString("D_BUYER_ORG_NAME"));
			energySaleOrderLine.setBuyerEndSsName(resultSet.getString("D_BUYER_SS_NAME"));
			energySaleOrderLine.setBuyerCompanyServiceNumber(resultSet.getString("D_BUYER_COMP_SERV_NAME"));
			energySaleOrderLine.setGc1(resultSet.getString("GC1"));
			energySaleOrderLine.setGc2(resultSet.getString("GC2"));
			energySaleOrderLine.setGc3(resultSet.getString("GC3"));
			energySaleOrderLine.setGc4(resultSet.getString("GC4"));
			energySaleOrderLine.setGc5(resultSet.getString("GC5"));	
			energySaleOrderLine.setCompanyServiceTypeCode(resultSet.getString("COMP_SER_TYPE_CODE"));
			energySaleOrderLine.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			energySaleOrderLine.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			energySaleOrderLine.setDrawalVoltageName(resultSet.getString("DRAWAL_VOLTAGE_NAME"));
			energySaleOrderLine.setUnitCost(resultSet.getString("UNIT_COST"));
			energySaleOrderLine.setTotalAmountPayable(resultSet.getString("TOTAL_AMOUNT_PAYABLE"));
			energySaleOrderLine.setTotalAmountChargable(resultSet.getString("TOTAL_AMOUNT_CHARGABLE"));
			energySaleOrderLine.setNetAmountPayable(resultSet.getString("NET_AMOUNT_PAYABLE"));
			
			return energySaleOrderLine;
		}
	}
	
	final class EnergyChargeMapper implements RowMapper<EnergyCharge>{
		
		public EnergyChargeMapper() {
			super();
		}
	
		
		public EnergyCharge mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergyCharge energyCharge=new EnergyCharge();
			energyCharge.setId(resultSet.getString("ID"));
			energyCharge.setEnergySaleOrderId(resultSet.getString("F_ENERGY_SALE_ORDER_ID"));
			energyCharge.setChargeCode(resultSet.getString("CHARGE_CODE"));
			energyCharge.setChargeDesc(resultSet.getString("CHARGE_DESC"));
			energyCharge.setChargeTypeCode(resultSet.getString("CHARGE_TYPE_CODE"));
			energyCharge.setUnitCharge(resultSet.getString("UNIT_CHARGE"));
			energyCharge.setTotalCharges(resultSet.getString("TOTAL_CHARGES"));
			energyCharge.setRemarks(resultSet.getString("REMARKS"));
			energyCharge.setCompanyServiceNumber(resultSet.getString("servicenumber"));
			energyCharge.setCompanyName(resultSet.getString("m_company_name"));
			return energyCharge;
		}
	}
	
	private Object[] setEnergySaleOrderValues(EnergySaleOrder energySaleOrder) {
		return new Object[] {
			energySaleOrder.getEnergySaleId(),
			energySaleOrder.getSellerCompanyServiceId(),
			energySaleOrder.getSellerEndOrgId(),
			energySaleOrder.getMonth(),
			energySaleOrder.getYear(),
			energySaleOrder.getInjectingVoltageCode(),
			energySaleOrder.getFromDate(),
			energySaleOrder.getToDate(),
			energySaleOrder.getLoss(),
			energySaleOrder.getMultipleBuyers(),
			energySaleOrder.getUsageDetailAvail(),
			energySaleOrder.getSimpleEnergySale(),
			energySaleOrder.getTotalC1(),
			energySaleOrder.getTotalC2(),
			energySaleOrder.getTotalC3(),
			energySaleOrder.getTotalC4(),
			energySaleOrder.getTotalC5(),
			energySaleOrder.getTotalGenUnitsSold(),
			energySaleOrder.getTotalBc1(),
			energySaleOrder.getTotalBc2(),
			energySaleOrder.getTotalBc3(),
			energySaleOrder.getTotalBc4(),
			energySaleOrder.getTotalBc5(),
			energySaleOrder.getTotalBankingUnitsSold(),
			energySaleOrder.getTotalUnitsSold(),
			energySaleOrder.getSellerCompanyName(),
			energySaleOrder.getSellerCompanyCode(),
			energySaleOrder.getSellerCompanyServiceNumber(),
			energySaleOrder.getSellerEndOrgName(),
			energySaleOrder.getSellerEndOrgCode(),	
			energySaleOrder.getTotalGc1(),
			energySaleOrder.getTotalGc2(),
			energySaleOrder.getTotalGc3(),
			energySaleOrder.getTotalGc4(),
			energySaleOrder.getTotalGc5(),
			energySaleOrder.getSellerCompBankingServiceId(),
			energySaleOrder.getSellerCompBankingServiceNumber(),
			energySaleOrder.getSellerCompanyId(),
			energySaleOrder.getAllowLowerSlotAdmt(),
			energySaleOrder.getId(),
			energySaleOrder.getFuelTypeCode(),
			energySaleOrder.getFuelTypeName()
				
		};
		
	}

	private Object[] setEnergySaleOrderLinesValues(EnergySaleOrderLine energySaleOrderLines) {
		return new Object[] {
			energySaleOrderLines.getEnergySaleOrderId(),
			energySaleOrderLines.getEnergySaleId(),
			energySaleOrderLines.getBuyerEndOrgId(),
			energySaleOrderLines.getBuyerEndSsId(),
			energySaleOrderLines.getBuyerCompanyServiceId(),
			energySaleOrderLines.getC1(),
			energySaleOrderLines.getC2(),
			energySaleOrderLines.getC3(),
			energySaleOrderLines.getC4(),
			energySaleOrderLines.getC5(),
			energySaleOrderLines.getGenUnitsSold(),
			energySaleOrderLines.getBc1(),
			energySaleOrderLines.getBc2(),
			energySaleOrderLines.getBc3(),
			energySaleOrderLines.getBc4(),
			energySaleOrderLines.getBc5(),
			energySaleOrderLines.getBankingUnitsSold(),			
			energySaleOrderLines.getTotalUnitsSold(),
			energySaleOrderLines.getBuyerCompanyName(),
			energySaleOrderLines.getBuyerCompanyCode(),
			energySaleOrderLines.getBuyerCompanyServiceNumber(),
			energySaleOrderLines.getBuyerEndOrgName(),
			energySaleOrderLines.getBuyerEndOrgCode(),
			energySaleOrderLines.getBuyerEndSsName(),
			energySaleOrderLines.getGc1(),
			energySaleOrderLines.getGc2(),
			energySaleOrderLines.getGc3(),
			energySaleOrderLines.getGc4(),
			energySaleOrderLines.getGc5(),
			energySaleOrderLines.getBuyerCompanyId(),
			energySaleOrderLines.getDrawalVoltageCode(),
			energySaleOrderLines.getUnitCost(),
			energySaleOrderLines.getTotalAmountPayable(),
			energySaleOrderLines.getTotalAmountChargable(),
			energySaleOrderLines.getNetAmountPayable(),
			energySaleOrderLines.getId()			
		};
	}
	
	private Object[] setEnergyChargeValues(EnergyCharge energyCharge) {
		return new Object[] {
			energyCharge.getRemarks(),
			energyCharge.getChargeCode(),
			energyCharge.getChargeDesc(),
			energyCharge.getChargeTypeCode(),
			energyCharge.getUnitCharge(),
			energyCharge.getTotalCharges(),
			energyCharge.getEnergySaleOrderId(),
			energyCharge.getId()
					
		};
	}
	
	

}
