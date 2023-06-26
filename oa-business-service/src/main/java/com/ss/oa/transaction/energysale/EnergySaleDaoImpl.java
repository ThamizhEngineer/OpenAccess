package com.ss.oa.transaction.energysale;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.transaction.vo.EnergySale;
import com.ss.oa.transaction.vo.EnergySaleCharge;
import com.ss.oa.transaction.vo.EnergySaleDescription;
import com.ss.oa.transaction.vo.EnergySaleUsageDetail;
import com.ss.oa.transaction.vo.EnergySaleUsageSummary;

//import oracle.net.aso.e;

@Component
@Scope("prototype")
public class EnergySaleDaoImpl extends BaseDaoJdbc implements EnergySaleDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	
	
	@Resource
	private DataSource dataSource;
	
	private SimpleJdbcCall functionEnergySaleConfirmation;
	
	private SimpleJdbcCall functionCalculateEnergySaleCharges;

	@Override
	public List<EnergySale> getAllEnergySales(Map<String, String> criteria) {
		
		String sql = "select es.ID,es.T_GEN_STMT_ID,es.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER,companyservice.m_company_id as SELLER_COMP_ID,companyservice.m_company_name as SELLER_COMP_NAME,companyservice.m_company_CODE as SELLER_COMP_CODE,\n" + 
				"  es.SELLER_END_ORG_ID,companyservice.M_ORG_NAME as SELLER_END_ORG_NAME ,companyservice.M_ORG_CODE as SELLER_END_ORG_CODE,companyservice.M_SUBSTATION_ID as SELLER_END_SUBSTATION_ID,companyservice.M_SUBSTATION_NAME as SELLER_END_SUBSTATION_NAME,companyservice.M_SUBSTATION_CODE as SELLER_END_SUBSTATION_CODE,\n" + 
				"  companyservice.M_FEEDER_ID as SELLER_END_FEEDER_ID,companyservice.M_FEEDER_NAME as SELLER_END_FEEDER_NAME,companyservice.M_FEEDER_CODE as SELLER_END_FEEDER_CODE, es.MONTH,es.YEAR,es.INJECTING_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as INJECTING_VOLTAGE_NAME, es.FROM_DT,es.TO_DT,es.LOSS,es.MULTIPLE_BUYERS,es.USAGE_DETAIL_AVAIL,es.SIMPLE_ENERGY_SALE,es.C1,es.C2,es.C3,es.C4,es.C5,es.NET_GENERATION,\n" + 
				"  es.NET_ALLOCATION,es.status_code,es.bc1,es.bc2,es.bc3,es.bc4,es.bc5,es.TOTAL_BANK_UNITS_USED,es.gc1,es.gc2,es.gc3,es.gc4,es.gc5,companyservice.BANKING_SERVICE_ID as SELLER_BANKING_SERVICE_ID,companyservice.BANKING_SERVICE_NUMBER as SELLER_BANKING_SERVICE_NUMBER,\n" + 
				"  companyservice.TL_SERVICE_ID as SELLER_TL_SERVICE_ID,companyservice.TL_SERVICE_NUMBER as SELLER_TL_SERVICE_NUMBER,companyservice.DL_SERVICE_ID as SELLER_DL_SERVICE_ID,companyservice.DL_SERVICE_NUMBER as SELLER_DL_SERVICE_NUMBER,companyservice.UNADJUSTED_SERVICE_ID as SELLER_UNADJUSTED_SERV_ID,companyservice.UNADJUSTED_SERVICE_NUMBER as SELLER_UNADJUSTED_SERV_NUMBER,\n" + 
				"  es.IS_STB,es.AVAIL_C1,es.AVAIL_C2,es.AVAIL_C3,es.AVAIL_C4,es.AVAIL_C5,es.AVAIL_GC1,es.AVAIL_GC2,es.AVAIL_GC3,es.AVAIL_GC4,es.AVAIL_GC5,es.AVAIL_BC1,es.AVAIL_BC2,es.AVAIL_BC3,es.AVAIL_BC4,es.AVAIL_BC5,es.NET_CHARGES_ALLOCATED,es.ALLOW_LOWER_SLOT_ADMT,companyservice.capacity as SANCTIONED_CAPACITY,es.SAVED_ONCE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE, fuel.FUEL_NAME as FUEL_TYPE_NAME, fuel.FUEL_GROUP as FUEL_GROUPE, gs.FLOW_TYPE_CODE \n" + 
				"  from T_ENERGY_SALE es\n" + 
				"  left JOIN T_GEN_STMT gs ON es.T_GEN_STMT_ID = gs.id \n" +
				"  left join v_company_service companyservice on es.SELLER_COMP_SERV_ID =companyservice.id \n" +
				"  left join M_FUEL fuel on companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE  where 1=1";
		if(!criteria.isEmpty())
		{
			if(criteria.get("company-id")!=null){
				sql += "and upper(company.id)= upper('"+criteria.get("company-id")+"')";
			}
			if(criteria.get("company-name")!=null){
				sql += "and upper(companyservice.m_company_name) like upper('%"+criteria.get("company-name")+"%')";
			}
			if(criteria.get("edc-id")!=null){
				sql += "and upper(es.SELLER_END_ORG_ID) = upper('"+criteria.get("edc-id")+"')";
			}
			if(criteria.get("service-number")!=null){
				sql += "and upper(companyservice.\"number\") = upper('"+criteria.get("service-number")+"')";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(es.MONTH) = upper('"+criteria.get("month")+"')";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(es.YEAR) = upper('"+criteria.get("year")+"')";
			}
			if(criteria.get("generation-statement-id")!=null){
				sql += "and upper(es.T_GEN_STMT_ID) = upper('"+criteria.get("generation-statement-id")+"')";
			}
			if(criteria.get("is-stb")!=null){
				sql += "and upper(es.IS_STB) = upper('"+criteria.get("is-stb")+"')";
			}
			if(criteria.get("multiple-buyers")!=null){
				sql += "and nvl(upper(es.MULTIPLE_BUYERS),'') = nvl(upper('"+criteria.get("multiple-buyers")+"'),'')";
			}
			if(criteria.get("simple-energy-sale")!=null){
				sql += "and nvl(upper(es.SIMPLE_ENERGY_SALE),'') = nvl(upper('"+criteria.get("simple-energy-sale")+"'),'')";
			}
			if(criteria.get("fuelTypeCode")!=null){
				sql += "and nvl(upper(companyservice.FUEL_TYPE_CODE),'') = nvl(upper('"+criteria.get("fuelTypeCode")+"'),'')";
			}
			if(criteria.get("fuelTypeName")!=null){
				sql += "and nvl(upper(fuel.FUEL_NAME),'') = nvl(upper('"+criteria.get("fuelTypeName")+"'),'')";
			}
			if(criteria.get("fuelGroupe")!=null){
				sql += "and nvl(upper(fuel.FUEL_GROUP),'') = nvl(upper('"+criteria.get("fuelGroupe")+"'),'')";
			}if(criteria.get("flowTypeCode")!=null){
				sql += "and nvl(upper(gs.FLOW_TYPE_CODE),'') = nvl(upper('"+criteria.get("fuelGroupe")+"'),'')";
			}
			
		}
		return (ArrayList<EnergySale>)jdbcOperations.query(sql, new EnergySaleMapper());
	}

	@Override
	public EnergySale getEnergySaleById(String id) {
		EnergySale energySale =new EnergySale();
		List<EnergySaleUsageDetail> energySaleUsageDetails = new ArrayList<EnergySaleUsageDetail>();
		List<EnergySaleUsageSummary> energySaleUsageSummaries = new ArrayList<EnergySaleUsageSummary>();
		List<EnergySaleDescription> energySaleDescriptionList = new ArrayList<EnergySaleDescription>();

		List<EnergySaleCharge> energySaleCharges = new ArrayList<EnergySaleCharge>();
		String sql="select es.ID,es.T_GEN_STMT_ID,es.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER,companyservice.m_company_id as SELLER_COMP_ID,companyservice.m_company_name as SELLER_COMP_NAME,companyservice.m_company_CODE as SELLER_COMP_CODE,\n" + 
				"  es.SELLER_END_ORG_ID,companyservice.M_ORG_NAME as SELLER_END_ORG_NAME ,companyservice.M_ORG_CODE as SELLER_END_ORG_CODE,companyservice.M_SUBSTATION_ID as SELLER_END_SUBSTATION_ID,companyservice.M_SUBSTATION_NAME as SELLER_END_SUBSTATION_NAME,companyservice.M_SUBSTATION_CODE as SELLER_END_SUBSTATION_CODE,\n" + 
				"  companyservice.M_FEEDER_ID as SELLER_END_FEEDER_ID,companyservice.M_FEEDER_NAME as SELLER_END_FEEDER_NAME,companyservice.M_FEEDER_CODE as SELLER_END_FEEDER_CODE, es.MONTH,es.YEAR,es.INJECTING_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as INJECTING_VOLTAGE_NAME, es.FROM_DT,es.TO_DT,es.LOSS,es.MULTIPLE_BUYERS,es.USAGE_DETAIL_AVAIL,es.SIMPLE_ENERGY_SALE,es.C1,es.C2,es.C3,es.C4,es.C5,es.NET_GENERATION,\n" + 
				"  es.NET_ALLOCATION,es.status_code,es.bc1,es.bc2,es.bc3,es.bc4,es.bc5,es.TOTAL_BANK_UNITS_USED,es.gc1,es.gc2,es.gc3,es.gc4,es.gc5,companyservice.BANKING_SERVICE_ID as SELLER_BANKING_SERVICE_ID,companyservice.BANKING_SERVICE_NUMBER as SELLER_BANKING_SERVICE_NUMBER,\n" + 
				"  companyservice.TL_SERVICE_ID as SELLER_TL_SERVICE_ID,companyservice.TL_SERVICE_NUMBER as SELLER_TL_SERVICE_NUMBER,companyservice.DL_SERVICE_ID as SELLER_DL_SERVICE_ID,companyservice.DL_SERVICE_NUMBER as SELLER_DL_SERVICE_NUMBER,companyservice.UNADJUSTED_SERVICE_ID as SELLER_UNADJUSTED_SERV_ID,companyservice.UNADJUSTED_SERVICE_NUMBER as SELLER_UNADJUSTED_SERV_NUMBER,\n" + 
				"  es.IS_STB,es.AVAIL_C1,es.AVAIL_C2,es.AVAIL_C3,es.AVAIL_C4,es.AVAIL_C5,es.AVAIL_GC1,es.AVAIL_GC2,es.AVAIL_GC3,es.AVAIL_GC4,es.AVAIL_GC5,es.AVAIL_BC1,es.AVAIL_BC2,es.AVAIL_BC3,es.AVAIL_BC4,es.AVAIL_BC5,es.NET_CHARGES_ALLOCATED,es.ALLOW_LOWER_SLOT_ADMT,companyservice.capacity as SANCTIONED_CAPACITY,es.SAVED_ONCE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE, fuel.FUEL_NAME as FUEL_TYPE_NAME, fuel.FUEL_GROUP as FUEL_GROUPE \n" + 
				"  from T_ENERGY_SALE es\n" + 
				"  left join v_company_service companyservice on es.SELLER_COMP_SERV_ID =companyservice.id \n" +
				"  left join M_FUEL fuel on companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE where es.Id=?";
		energySale = jdbcOperations.queryForObject(sql,new Object[]{id}, new EnergySaleMapper());
		
		String sql1="select esdetail.ID,esdetail.T_ENERGY_SALE_ID,esdetail.BUYER_COMP_SERV_ID,companyservice.\"number\" as BUYER_COMP_SERV__NUMBER,company.id as BUYER_COMP_ID,company.name as BUYER_COMP_NAME,company.CODE as BUYER_COMP_CODE,\n" + 
				"esdetail.USAGE_DATE,esdetail.C1,esdetail.C2,esdetail.C3,esdetail.C4,esdetail.C5,TOTAL\n" + 
				"from T_ES_USAGE_DETAIL esdetail\n" + 
				"left join M_COMPANY_SERVICE companyservice on esdetail.BUYER_COMP_SERV_ID =companyservice.id\n" + 
				"left join M_COMPANY company on companyservice.M_COMPANY_ID = company.id where esdetail.T_ENERGY_SALE_ID=?";
		energySaleUsageDetails = jdbcOperations.query(sql1,new Object[]{id}, new EnergySaleUsageDetailMapper());
		
		String sql2="select  essummary.ID,essummary.T_ENERGY_SALE_ID,trade.QUANTUM,trade.id as M_TRADE_RELATIONSHIP_ID,trade.AGREEMENT_TYPE,companyservice.M_ORG_ID as BUYER_END_ORG_ID,companyservice.M_ORG_NAME as BUYER_END_ORG_NAME,\n" + 
				"		companyservice.M_ORG_CODE as BUYER_END_ORG_CODE, companyservice.M_SUBSTATION_ID as BUYER_END_SUBSTATION_ID,\n" + 
				"		companyservice.M_SUBSTATION_NAME as BUYER_END_SUBSTATION_NAME,companyservice.M_SUBSTATION_CODE as BUYER_END_SUBSTATION_CODE,\n" + 
				"	    companyservice.M_FEEDER_ID as BUYER_END_FEEDER_ID,companyservice.M_FEEDER_NAME as BUYER_END_FEEDER_NAME,trade.M_BUYER_COMP_SERVICE_ID,\n" + 
				"	    companyservice.M_FEEDER_CODE as BUYER_END_FEEDER_CODE,essummary.BUYER_COMP_SERV_ID,companyservice.\"number\" as BUYER_COMP_SERV_NUMBER,\n" + 
				"	    companyservice.m_company_id as BUYER_COMP_ID,companyservice.m_company_name as BUYER_COMP_NAME,companyservice.m_company_CODE as BUYER_COMP_CODE,\n" + 
				"		essummary.C1,essummary.C2,essummary.C3,essummary.C4,essummary.C5,essummary.TOTAL,companyservice.VOLTAGE_CODE as DRAWAL_VOLTAGE_CODE,\n" + 
				"		companyservice.VOLTAGE_NAME as DRAWAL_VOLTAGE_NAME,companyservice.BANKING_SERVICE_ID as BUYER_BANKING_SERVICE_ID,\n" + 
				"		companyservice.BANKING_SERVICE_NUMBER as BUYER_BANKING_SERVICE_NUMBER,\n" + 
				"		companyservice.TL_SERVICE_ID as BUYER_TL_SERVICE_ID,companyservice.TL_SERVICE_NUMBER as BUYER_TL_SERVICE_NUMBER,\n" + 
				"		companyservice.DL_SERVICE_ID as BUYER_DL_SERVICE_ID,companyservice.DL_SERVICE_NUMBER as BUYER_DL_SERVICE_NUMBER,\n" + 
				"		companyservice.UNADJUSTED_SERVICE_ID as BUYER_UNADJUSTED_SERV_ID,companyservice.UNADJUSTED_SERVICE_NUMBER as BUYER_UNADJUSTED_SERV_NUMBER,\n" + 
				"        essummary.UNIT_COST,essummary.TOTAL_AMOUNT_PAYABLE,\n" + 
				"		essummary.TOTAL_AMOUNT_CHARGABLE,essummary.NET_AMOUNT_PAYABLE \n" + 
				"		from T_ES_USAGE_SUMMARY essummary	   \n" + 
				"		left join M_TRADE_RELATIONSHIP trade on essummary.M_TRADE_RELATIONSHIP_ID= trade.ID \n" + 
				"		left join V_COMPANY_SERVICE companyservice on essummary.BUYER_COMP_SERV_ID =companyservice.id \n" + 
				"		where essummary.T_ENERGY_SALE_ID =?";
		energySaleUsageSummaries =jdbcOperations.query(sql2,new Object[]{id}, new EnergySaleUsageSummaryMapper());



		String sql3="select escharge.id,escharge.T_ENERGY_SALE_ID,escharge.M_COMP_SERV_ID,escharge.CHARGE_CODE,escharge.TOTAL_CHARGE,escharge.ENABLED \n" + 
				"				from T_ES_CHARGE escharge\n" + 
				"                where escharge.T_ENERGY_SALE_ID=?";
		energySaleCharges =jdbcOperations.query(sql3,new Object[]{id}, new EnergySaleChargeMapper());
		
//		String sqlCharge =getChargeDescription(energySaleCharge);
//		energySaleCharges =jdbcOperations.query(sqlCharge,new Object[]{id}, new EnergySaleChargeMapper());
//		List<EnergySaleCharge> energySaleCharges = new ArrayList<EnergySaleCharge>();


		EnergySaleDescription energySaleDescription = new EnergySaleDescription();
		String sqlCharge =getChargeDescription(energySaleDescription);
//		System.out.println(sqlCharge);

		energySaleDescriptionList=jdbcOperations.query(sqlCharge, new EnergySaleDescriptionMapper());
//		System.out.println("energySaleDescriptionList");
//
//		System.out.println(energySaleDescriptionList);

		for(EnergySaleDescription energySaleDescriptions:energySaleDescriptionList) {
			
			for(EnergySaleCharge energySaleCharge:energySaleCharges) {
				
				if(energySaleDescriptions.getChargeCode().equals(energySaleCharge.getChargeCode())) {
					energySaleCharge.setChargeName(energySaleDescriptions.getChargeDesc());
				}
			}
			
			
		}
		
		for(EnergySaleUsageSummary energySaleUsageSummary:energySaleUsageSummaries) {
			
//			System.out.println("energySaleUsageSummaries");
//			System.out.println(energySaleUsageSummaries);
			
			for(EnergySaleCharge energySaleCharge:energySaleCharges) {
				
//				System.out.println("energySaleChargeList");
//				System.out.println(energySaleCharges);

			if(energySaleUsageSummary.getBuyerCompanyServiceId().equals(energySaleCharge.getCompanyServiceId())){
				
				energySaleCharge.setCompanyServiceNumber(energySaleUsageSummary.getBuyerCompanyServiceNumber());
				energySaleCharge.setOrgId(energySaleUsageSummary.getBuyerEndOrgId());
				energySaleCharge.setOrgCode(energySaleUsageSummary.getBuyerEndOrgCode());
				energySaleCharge.setOrgName(energySaleUsageSummary.getBuyerEndOrgName());
				energySaleCharge.setCompanyId(energySaleUsageSummary.getBuyerCompanyId());
				energySaleCharge.setCompanyName(energySaleUsageSummary.getBuyerCompanyName());
				energySaleCharge.setCompanyCode(energySaleUsageSummary.getBuyerCompanyCode());
				energySaleCharge.setSubstationId(energySaleUsageSummary.getBuyerEndSsId());
				energySaleCharge.setSubstationCode(energySaleUsageSummary.getBuyerEndSsCode());
				energySaleCharge.setSubstationName(energySaleUsageSummary.getBuyerEndSsName());
				energySaleCharge.setFeederId(energySaleUsageSummary.getBuyerEndFeederId());
				energySaleCharge.setFeederCode(energySaleUsageSummary.getBuyerEndFeederCode());
				energySaleCharge.setFeederName(energySaleUsageSummary.getBuyerEndFeederName());
				
//				energySaleCharges.add(energySaleCharge);

				
			}
		}
		}
		
		for(EnergySaleUsageSummary energySaleUsageSummary:energySaleUsageSummaries) {
			
			for(EnergySaleCharge energySaleCharge:energySaleCharges) {
				
				if(energySaleUsageSummary.getBuyerCompanyServiceId().equals(energySaleCharge.getCompanyServiceId())) {
					
					if(energySaleCharge.getChargeCode().equals("C001")) {
						energySaleUsageSummary.setC001Id(energySaleCharge.getId());
						energySaleUsageSummary.setC001ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC001ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC001TotalCharge(energySaleCharge.getTotalCharge());	
					}
					if(energySaleCharge.getChargeCode().equals("C002")) {
						energySaleUsageSummary.setC002Id(energySaleCharge.getId());
						energySaleUsageSummary.setC002ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC002ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC002TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C003")) {
						energySaleUsageSummary.setC003Id(energySaleCharge.getId());
						energySaleUsageSummary.setC003ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC003ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC003TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C004")) {
						energySaleUsageSummary.setC004Id(energySaleCharge.getId());
						energySaleUsageSummary.setC004ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC004ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC004TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C005")) {
						energySaleUsageSummary.setC005Id(energySaleCharge.getId());
						energySaleUsageSummary.setC005ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC005ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC005TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C006")) {
						energySaleUsageSummary.setC006Id(energySaleCharge.getId());
						energySaleUsageSummary.setC006ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC006ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC006TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C007")) {
						energySaleUsageSummary.setC007Id(energySaleCharge.getId());
						energySaleUsageSummary.setC007ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC007ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC007TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C008")) {
						energySaleUsageSummary.setC008Id(energySaleCharge.getId());
						energySaleUsageSummary.setC008ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC008ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC008TotalCharge(energySaleCharge.getTotalCharge());	
					}					if(energySaleCharge.getChargeCode().equals("C009")) {
						energySaleUsageSummary.setC009Id(energySaleCharge.getId());
						energySaleUsageSummary.setC009ChargeCode(energySaleCharge.getChargeCode());
						energySaleUsageSummary.setC009ChargeDesc(energySaleCharge.getChargeName());
						energySaleUsageSummary.setC009TotalCharge(energySaleCharge.getTotalCharge());	
					}
					
					
				}
				
			}
			
		}



		energySale.setEnergySaleUsageDetails(energySaleUsageDetails);
		energySale.setEnergySaleUsageSummaries(energySaleUsageSummaries);
		energySale.setEnergySaleCharges(energySaleCharges);

		
		
		return energySale;
	}
	

	@Override
	public String addEnergySale(EnergySale energySale) {
		String result="";
		try {
			energySale.setId(generateId());
			energySale.setStatusCode("CREATED");
			String sql = "insert into T_ENERGY_SALE(T_GEN_STMT_ID,SELLER_COMP_SERV_ID,SELLER_END_ORG_ID,MONTH,YEAR,INJECTING_VOLTAGE_CODE,\n" + 
					"FROM_DT,TO_DT,LOSS,MULTIPLE_BUYERS,USAGE_DETAIL_AVAIL,SIMPLE_ENERGY_SALE,C1,C2,C3,C4,C5,NET_GENERATION,NET_ALLOCATION,STATUS_CODE,BC1,BC2,BC3,BC4,BC5,TOTAL_BANK_UNITS_USED,GC1,GC2,GC3,GC4,GC5,IS_STB,AVAIL_C1,AVAIL_C2,AVAIL_C3,AVAIL_C4,AVAIL_C5,AVAIL_GC1,AVAIL_GC2,AVAIL_GC3,AVAIL_GC4,AVAIL_GC5,AVAIL_BC1,AVAIL_BC2,AVAIL_BC3,AVAIL_BC4,AVAIL_BC5,NET_CHARGES_ALLOCATED,ALLOW_LOWER_SLOT_ADMT,SAVED_ONCE,ID) values(?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergySaleValues(energySale))>0) {
				result= energySale.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateEnergySale(String id, EnergySale energySale) {
		String result="";
		energySale.setId(id);
		try {
			String sql = "update T_ENERGY_SALE set T_GEN_STMT_ID=?,SELLER_COMP_SERV_ID=?,SELLER_END_ORG_ID=?,MONTH=?,YEAR=?,INJECTING_VOLTAGE_CODE=?, \n" + 
					"FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),LOSS=?,MULTIPLE_BUYERS=?,USAGE_DETAIL_AVAIL=?,SIMPLE_ENERGY_SALE=?,C1=?,C2=?,C3=?,C4=?,C5=?,NET_GENERATION=?,NET_ALLOCATION=?,STATUS_CODE=?,BC1=?,BC2=?,BC3=?,BC4=?,BC5=?,TOTAL_BANK_UNITS_USED=?,GC1=?,GC2=?,GC3=?,GC4=?,GC5=?,IS_STB=?,AVAIL_C1=?,AVAIL_C2=?,AVAIL_C3=?,AVAIL_C4=?,AVAIL_C5=?,AVAIL_GC1=?,AVAIL_GC2=?,AVAIL_GC3=?,AVAIL_GC4=?,AVAIL_GC5=?,AVAIL_BC1=?,AVAIL_BC2=?,AVAIL_BC3=?,AVAIL_BC4=?,AVAIL_BC5=?,NET_CHARGES_ALLOCATED=?,ALLOW_LOWER_SLOT_ADMT=?,SAVED_ONCE=? where id=?";
			if(jdbcOperations.update(sql, setEnergySaleValues(energySale)) > 0){
				result = energySale.getId();				
			}
			else{
				result =  "FAILURE";
			}
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
		
	}
	

	
	@Override
	public String addEnergySaleUsageDetail(EnergySaleUsageDetail energySaleUsageDetail) {
		String result="";
		try {
			energySaleUsageDetail.setId(generateId());
			String sql = "insert into T_ES_USAGE_DETAIL (T_ENERGY_SALE_ID,BUYER_COMP_SERV_ID,USAGE_DATE,C1,C2,\n" + 
					"C3,C4,C5,TOTAL,ID) values(?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergySaleUsageDetailValues(energySaleUsageDetail))>0) {
				result= energySaleUsageDetail.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateEnergySaleUsageDetail(EnergySaleUsageDetail energySaleUsageDetail) {
		String result="";
		
		try {
			String sql = "update T_ES_USAGE_DETAIL set T_ENERGY_SALE_ID=?,BUYER_COMP_SERV_ID=?,USAGE_DATE=TO_DATE(?,'yyyy-mm-dd'),C1=?,C2=?, \n" + 
					"C3=?,C4=?,C5=?,TOTAL=? where id=?";
			if(jdbcOperations.update(sql, setEnergySaleUsageDetailValues(energySaleUsageDetail)) > 0){
				result = energySaleUsageDetail.getId();				
			}
			else{
				result =  "FAILURE";
			}
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String addEnergySaleUsageSummary(EnergySaleUsageSummary energySaleUsageSummary) {
		String result="";
		try {
			energySaleUsageSummary.setId(generateId());
			String sql = "insert into T_ES_USAGE_SUMMARY(T_ENERGY_SALE_ID,M_TRADE_RELATIONSHIP_ID,BUYER_END_ORG_ID,BUYER_END_SS_ID,BUYER_COMP_SERV_ID,C1,C2,\n" + 
					"C3,C4,C5,TOTAL,UNIT_COST,TOTAL_AMOUNT_PAYABLE,TOTAL_AMOUNT_CHARGABLE,NET_AMOUNT_PAYABLE,ID)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergySaleUsageSummaryValues(energySaleUsageSummary))>0) {
				result= energySaleUsageSummary.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateEnergySaleUsageSummary(EnergySaleUsageSummary energySaleUsageSummary) {
		String result="";		
		try {
			String sql = "update T_ES_USAGE_SUMMARY set T_ENERGY_SALE_ID=?,M_TRADE_RELATIONSHIP_ID=?,BUYER_END_ORG_ID=?,BUYER_END_SS_ID=?,BUYER_COMP_SERV_ID=?,C1=?,C2=?, \n" + 
					"C3=?,C4=?,C5=?,TOTAL=?,UNIT_COST=?,TOTAL_AMOUNT_PAYABLE=?,TOTAL_AMOUNT_CHARGABLE=?,NET_AMOUNT_PAYABLE=? where id=?";
			if(jdbcOperations.update(sql, setEnergySaleUsageSummaryValues(energySaleUsageSummary)) > 0){
				result = energySaleUsageSummary.getId();				
			}
			else{
				result =  "FAILURE";
			}
		}catch(Exception e) {
			result = "FAILURE";
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String addEnergySaleCharge(EnergySaleCharge energySaleCharge) {
		String result ="";
		try {
			energySaleCharge.setId(generateId());
			String sql = "insert into T_ES_CHARGE (T_ENERGY_SALE_ID,M_COMP_SERV_ID,CHARGE_CODE,TOTAL_CHARGE,ENABLED,ID)values(?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergySaleChargeValues(energySaleCharge))>0) {
				result= energySaleCharge.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateEnergySaleCharge(EnergySaleCharge energySaleCharge) {
		String result ="";
		try {
		
//		System.out.println("dao update"+energySaleCharge);
			String sql = "update T_ES_CHARGE set T_ENERGY_SALE_ID=?,M_COMP_SERV_ID=?,CHARGE_CODE=?,TOTAL_CHARGE=?,ENABLED=? where  ID=?";
//			System.out.println(sql);
			if(jdbcOperations.update(sql,setEnergySaleChargeValues(energySaleCharge))>0) {
				result= energySaleCharge.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
//		System.out.println("dao result");
//		System.out.println(result);
		return result;
	}
	
	@Override
	public String DeleteEsUsageSummary(EnergySaleUsageSummary energySaleUsageSummary) {
		String result ="";
		
		try {
            String sql = "DELETE FROM T_ES_USAGE_SUMMARY WHERE T_ENERGY_SALE_ID = '" +  energySaleUsageSummary.getEnergySaleId()+  
                    "' AND BUYER_COMP_SERV_ID = '" + energySaleUsageSummary.getBuyerCompanyServiceId() + "'"; 
			System.out.println("DAO imp usage summary");
			System.out.println(sql);  

        	jdbcOperations.update(sql); 
			result= energySaleUsageSummary.getId();	

		}catch(Exception e) {
			result= "Failue";
			e.printStackTrace();
		}

		return result;
	}
	
	@Override
	public String DeleteEnergySaleCharge(EnergySaleCharge energySaleCharge) {
		String result ="";
		
		try {
            String sql = "DELETE FROM T_ES_CHARGE WHERE T_ENERGY_SALE_ID = '" +  energySaleCharge.getEnergySaleId()+  
                    "' AND M_COMP_SERV_ID = '" + energySaleCharge.getCompanyServiceId() + "'"; 
			System.out.println("DAO imp");
			System.out.println(sql);  

        	jdbcOperations.update(sql); 
			result= energySaleCharge.getId();	

		}catch(Exception e) {
			result= "Failue";
			e.printStackTrace();
		}

		return result;
	}
	
	
	private Object[] setEnergySaleValues(EnergySale energySale) {
		return new Object[] {
			energySale.getGenerationStatementId(),
			energySale.getSellerCompanyServiceId(),
			energySale.getSellerEndOrgId(),
			energySale.getMonth(),
			energySale.getYear(),
			energySale.getInjectingVoltageCode(),
			energySale.getFromDate(),
			energySale.getToDate(),
			energySale.getLoss(),
			energySale.getMultipleBuyers(),
			energySale.getUsageDetailAvail(),
			energySale.getSimpleEnergySale(),
			energySale.getC1(),
			energySale.getC2(),
			energySale.getC3(),
			energySale.getC4(),
			energySale.getC5(),
			energySale.getNetGeneration(),
			energySale.getNetAllocation(),
			energySale.getStatusCode(),
			energySale.getBc1(),
			energySale.getBc2(),
			energySale.getBc3(),
			energySale.getBc4(),
			energySale.getBc5(),
			energySale.getTotalBankUnitsUsed(),
			energySale.getGc1(),
			energySale.getGc2(),
			energySale.getGc3(),
			energySale.getGc4(),
			energySale.getGc5(),
			energySale.getIsStb(),
			energySale.getAvailc1(),
			energySale.getAvailc2(),
			energySale.getAvailc3(),
			energySale.getAvailc4(),
			energySale.getAvailc5(),
			energySale.getAvailGc1(),
			energySale.getAvailGc2(),
			energySale.getAvailGc3(),
			energySale.getAvailGc4(),
			energySale.getAvailGc5(),
			energySale.getAvailBc1(),
			energySale.getAvailBc2(),
			energySale.getAvailBc3(),
			energySale.getAvailBc4(),
			energySale.getAvailBc5(),
			energySale.getNetChargesAllocated(),
			energySale.getAllowLowerSlotAdmt(),
			energySale.getSavedOnce(),
			energySale.getId()

		};
	}
	
	private Object[] setEnergySaleUsageDetailValues(EnergySaleUsageDetail energySaleUsageDetail) {
		return new Object[] {
				energySaleUsageDetail.getEnergySaleId(),
				energySaleUsageDetail.getBuyerCompanyServiceId(),
				energySaleUsageDetail.getUsageDate(),
				energySaleUsageDetail.getC1(),
				energySaleUsageDetail.getC2(),
				energySaleUsageDetail.getC3(),
				energySaleUsageDetail.getC4(),
				energySaleUsageDetail.getC5(),
				energySaleUsageDetail.getTotal(),
				energySaleUsageDetail.getId()				
		};
	}
	
	private Object[] setEnergySaleUsageSummaryValues(EnergySaleUsageSummary energySaleUsageSummary) {
		return new Object[] {
				energySaleUsageSummary.getEnergySaleId(),
				energySaleUsageSummary.getTradeRelationshipId(),
				energySaleUsageSummary.getBuyerEndOrgId(),
				energySaleUsageSummary.getBuyerEndSsId(),
				energySaleUsageSummary.getBuyerCompanyServiceId(),
				energySaleUsageSummary.getC1(),
				energySaleUsageSummary.getC2(),
				energySaleUsageSummary.getC3(),
				energySaleUsageSummary.getC4(),
				energySaleUsageSummary.getC5(),
				energySaleUsageSummary.getTotal(),
				energySaleUsageSummary.getUnitCost(),
				energySaleUsageSummary.getTotalAmountPayable(),
				energySaleUsageSummary.getTotalAmountChargable(),
				energySaleUsageSummary.getNetAmountPayable(),
				energySaleUsageSummary.getId()
		};
	}
	
	final class EnergySaleMapper implements RowMapper<EnergySale> {
		public EnergySaleMapper() {
			super();
		}

		@Override
		public EnergySale mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySale energySale =  new EnergySale();
			energySale.setId(resultSet.getString("ID"));
			energySale.setGenerationStatementId(resultSet.getString("T_GEN_STMT_ID"));
			energySale.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			energySale.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			energySale.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
			energySale.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
			energySale.setSellerCompanyCode(resultSet.getString("SELLER_COMP_CODE"));
			energySale.setSellerEndOrgId(resultSet.getString("SELLER_END_ORG_ID"));
			energySale.setSellerEndOrgName(resultSet.getString("SELLER_END_ORG_NAME"));
			energySale.setSellerEndOrgCode(resultSet.getString("SELLER_END_ORG_CODE"));			
			energySale.setSellerEndSubstationId(resultSet.getString("SELLER_END_SUBSTATION_ID"));
			energySale.setSellerEndSubstationName(resultSet.getString("SELLER_END_SUBSTATION_NAME"));
			energySale.setSellerEndSubstationCode(resultSet.getString("SELLER_END_SUBSTATION_CODE"));			
			energySale.setSellerEndFeederId(resultSet.getString("SELLER_END_FEEDER_ID"));
			energySale.setSellerEndFeederName(resultSet.getString("SELLER_END_FEEDER_NAME"));
			energySale.setSellerEndFeederCode(resultSet.getString("SELLER_END_FEEDER_CODE"));			
			energySale.setMonth(resultSet.getString("MONTH"));
			energySale.setYear(resultSet.getString("YEAR"));
			energySale.setInjectingVoltageCode(resultSet.getString("INJECTING_VOLTAGE_CODE"));
			energySale.setInjectingVoltageName(resultSet.getString("INJECTING_VOLTAGE_NAME"));
			energySale.setFromDate(resultSet.getString("FROM_DT"));
			energySale.setToDate(resultSet.getString("TO_DT"));
			energySale.setLoss(resultSet.getString("LOSS") );
			energySale.setMultipleBuyers(resultSet.getString("MULTIPLE_BUYERS"));
			energySale.setUsageDetailAvail(resultSet.getString("USAGE_DETAIL_AVAIL"));
			energySale.setSimpleEnergySale(resultSet.getString("SIMPLE_ENERGY_SALE"));
			energySale.setC1(resultSet.getString("C1"));
			energySale.setC2(resultSet.getString("C2"));
			energySale.setC3(resultSet.getString("C3"));
			energySale.setC4(resultSet.getString("C4"));
			energySale.setC5(resultSet.getString("C5"));
			energySale.setNetGeneration(resultSet.getString("NET_GENERATION"));
			energySale.setNetAllocation(resultSet.getString("NET_ALLOCATION"));
			energySale.setStatusCode(resultSet.getString("STATUS_CODE"));
			energySale.setBc1(resultSet.getString("BC1"));
			energySale.setBc2(resultSet.getString("BC2"));
			energySale.setBc3(resultSet.getString("BC3"));
			energySale.setBc4(resultSet.getString("BC4"));
			energySale.setBc5(resultSet.getString("BC5"));
			energySale.setTotalBankUnitsUsed(resultSet.getString("TOTAL_BANK_UNITS_USED"));
			energySale.setGc1(resultSet.getString("GC1"));
			energySale.setGc2(resultSet.getString("GC2"));
			energySale.setGc3(resultSet.getString("GC3"));
			energySale.setGc4(resultSet.getString("GC4"));
			energySale.setGc5(resultSet.getString("GC5"));
			energySale.setIsStb(resultSet.getString("IS_STB"));
			energySale.setAvailc1(resultSet.getString("AVAIL_C1"));
			energySale.setAvailc2(resultSet.getString("AVAIL_C2"));
			energySale.setAvailc3(resultSet.getString("AVAIL_C3"));
			energySale.setAvailc4(resultSet.getString("AVAIL_C4"));
			energySale.setAvailc5(resultSet.getString("AVAIL_C5"));
			energySale.setAvailGc1(resultSet.getString("AVAIL_GC1"));
			energySale.setAvailGc2(resultSet.getString("AVAIL_GC2"));
			energySale.setAvailGc3(resultSet.getString("AVAIL_GC3"));
			energySale.setAvailGc4(resultSet.getString("AVAIL_GC4"));
			energySale.setAvailGc5(resultSet.getString("AVAIL_GC5"));
			energySale.setAvailBc1(resultSet.getString("AVAIL_BC1"));
			energySale.setAvailBc2(resultSet.getString("AVAIL_BC2"));
			energySale.setAvailBc3(resultSet.getString("AVAIL_BC3"));
			energySale.setAvailBc4(resultSet.getString("AVAIL_BC4"));
			energySale.setAvailBc5(resultSet.getString("AVAIL_BC5"));			
			energySale.setSellerCompBankingServiceId(resultSet.getString("SELLER_BANKING_SERVICE_ID"));
			energySale.setSellerCompBankingServiceNumber(resultSet.getString("SELLER_BANKING_SERVICE_NUMBER"));
			energySale.setSellerTlServiceId(resultSet.getString("SELLER_TL_SERVICE_ID"));			
			energySale.setSellerTlServiceNumber(resultSet.getString("SELLER_TL_SERVICE_NUMBER"));
			energySale.setSellerDlServiceId(resultSet.getString("SELLER_DL_SERVICE_ID"));
			energySale.setSellerDlServiceNumber(resultSet.getString("SELLER_DL_SERVICE_NUMBER"));
			energySale.setSellerUnAdjustedServiceId(resultSet.getString("SELLER_UNADJUSTED_SERV_ID"));
			energySale.setSellerUnAdjustedServiceNumber(resultSet.getString("SELLER_UNADJUSTED_SERV_NUMBER"));
			energySale.setNetChargesAllocated(resultSet.getString("NET_CHARGES_ALLOCATED"));
			energySale.setAllowLowerSlotAdmt(resultSet.getString("ALLOW_LOWER_SLOT_ADMT"));
			energySale.setSanctionedCapacity(resultSet.getString("SANCTIONED_CAPACITY"));
			energySale.setSavedOnce(resultSet.getString("SAVED_ONCE"));
			energySale.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
			energySale.setFuelTypeName(resultSet.getString("FUEL_TYPE_NAME"));
			energySale.setFuelGroupe(resultSet.getString("FUEL_GROUPE"));
			
			
			return energySale;
		}
	}
	
	final class EnergySaleUsageDetailMapper implements  RowMapper<EnergySaleUsageDetail>{
		
		public EnergySaleUsageDetailMapper(){
			super();
		}

		@Override
		public EnergySaleUsageDetail mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySaleUsageDetail energySaleUsageDetail = new EnergySaleUsageDetail();
			energySaleUsageDetail.setId(resultSet.getString("ID"));
			energySaleUsageDetail.setEnergySaleId(resultSet.getString("T_ENERGY_SALE_ID"));
			energySaleUsageDetail.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			energySaleUsageDetail.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV__NUMBER"));
			energySaleUsageDetail.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			energySaleUsageDetail.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			energySaleUsageDetail.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			energySaleUsageDetail.setUsageDate(resultSet.getString("USAGE_DATE"));
			energySaleUsageDetail.setC1(resultSet.getString("C1"));
			energySaleUsageDetail.setC2(resultSet.getString("C2"));
			energySaleUsageDetail.setC3(resultSet.getString("C3"));
			energySaleUsageDetail.setC4(resultSet.getString("C4"));
			energySaleUsageDetail.setC5(resultSet.getString("C5"));
			energySaleUsageDetail.setTotal(resultSet.getString("TOTAL"));
			
			return energySaleUsageDetail;
		}
	}

final class EnergySaleUsageSummaryMapper implements  RowMapper<EnergySaleUsageSummary>{
		
		public EnergySaleUsageSummaryMapper(){
			super();
		}

		@Override
		public EnergySaleUsageSummary mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySaleUsageSummary energySaleUsageSummary = new EnergySaleUsageSummary();
			energySaleUsageSummary.setId(resultSet.getString("ID"));
			energySaleUsageSummary.setQuantum(resultSet.getString("quantum"));
			energySaleUsageSummary.setTradeRelationshipId(resultSet.getString("M_TRADE_RELATIONSHIP_ID"));
			energySaleUsageSummary.setEnergySaleId(resultSet.getString("T_ENERGY_SALE_ID"));
			energySaleUsageSummary.setBuyerEndOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			energySaleUsageSummary.setBuyerEndOrgName(resultSet.getString("BUYER_END_ORG_NAME"));
			energySaleUsageSummary.setBuyerEndOrgCode(resultSet.getString("BUYER_END_ORG_CODE"));
			energySaleUsageSummary.setBuyerEndSsId(resultSet.getString("BUYER_END_SUBSTATION_ID"));
			energySaleUsageSummary.setBuyerEndSsName(resultSet.getString("BUYER_END_SUBSTATION_NAME"));
			energySaleUsageSummary.setBuyerEndSsCode(resultSet.getString("BUYER_END_SUBSTATION_CODE"));			
			energySaleUsageSummary.setBuyerEndFeederId(resultSet.getString("BUYER_END_FEEDER_ID"));
			energySaleUsageSummary.setBuyerEndFeederName(resultSet.getString("BUYER_END_FEEDER_NAME"));
			energySaleUsageSummary.setBuyerEndFeederCode(resultSet.getString("BUYER_END_FEEDER_CODE"));
			energySaleUsageSummary.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			energySaleUsageSummary.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			energySaleUsageSummary.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			energySaleUsageSummary.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			energySaleUsageSummary.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			energySaleUsageSummary.setC1(resultSet.getString("C1"));
			energySaleUsageSummary.setC2(resultSet.getString("C2"));
			energySaleUsageSummary.setC3(resultSet.getString("C3"));
			energySaleUsageSummary.setC4(resultSet.getString("C4"));
			energySaleUsageSummary.setC5(resultSet.getString("C5"));
			energySaleUsageSummary.setTotal(resultSet.getString("TOTAL"));
			energySaleUsageSummary.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			energySaleUsageSummary.setDrawalVoltageName(resultSet.getString("DRAWAL_VOLTAGE_NAME"));
			energySaleUsageSummary.setBuyerCompBankingServiceId(resultSet.getString("BUYER_BANKING_SERVICE_ID"));
			energySaleUsageSummary.setBuyerCompBankingServiceNumber(resultSet.getString("BUYER_BANKING_SERVICE_NUMBER"));
			energySaleUsageSummary.setBuyerTlServiceId(resultSet.getString("BUYER_TL_SERVICE_ID"));
			energySaleUsageSummary.setBuyerTlServiceNumber(resultSet.getString("BUYER_TL_SERVICE_NUMBER"));
			energySaleUsageSummary.setBuyerDlServiceId(resultSet.getString("BUYER_DL_SERVICE_ID"));
			energySaleUsageSummary.setBuyerDlServiceNumber(resultSet.getString("BUYER_DL_SERVICE_NUMBER"));
			energySaleUsageSummary.setBuyerUnAdjustedServiceId(resultSet.getString("BUYER_UNADJUSTED_SERV_ID"));
			energySaleUsageSummary.setBuyerUnAdjustedServiceNumber(resultSet.getString("BUYER_UNADJUSTED_SERV_NUMBER"));
//			energySaleUsageSummary.setC001Id(resultSet.getString("C001_ID"));
//			energySaleUsageSummary.setC001ChargeCode(resultSet.getString("C001_CHARGE_CODE"));
//			energySaleUsageSummary.setC001ChargeDesc(resultSet.getString("C001_CHARGE_DESC"));
//			energySaleUsageSummary.setC001TotalCharge(resultSet.getString("C001_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC002Id(resultSet.getString("C002_ID"));
//			energySaleUsageSummary.setC002ChargeCode(resultSet.getString("C002_CHARGE_CODE"));
//			energySaleUsageSummary.setC002ChargeDesc(resultSet.getString("C002_CHARGE_DESC"));
//			energySaleUsageSummary.setC002TotalCharge(resultSet.getString("C002_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC003Id(resultSet.getString("C003_ID"));
//			energySaleUsageSummary.setC003ChargeCode(resultSet.getString("C003_CHARGE_CODE"));
//			energySaleUsageSummary.setC003ChargeDesc(resultSet.getString("C003_CHARGE_DESC"));
//			energySaleUsageSummary.setC003TotalCharge(resultSet.getString("C003_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC004Id(resultSet.getString("C004_ID"));
//			energySaleUsageSummary.setC004ChargeCode(resultSet.getString("C004_CHARGE_CODE"));
//			energySaleUsageSummary.setC004ChargeDesc(resultSet.getString("C004_CHARGE_DESC"));
//			energySaleUsageSummary.setC004TotalCharge(resultSet.getString("C004_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC005Id(resultSet.getString("C005_ID"));
//			energySaleUsageSummary.setC005ChargeCode(resultSet.getString("C005_CHARGE_CODE"));
//			energySaleUsageSummary.setC005ChargeDesc(resultSet.getString("C005_CHARGE_DESC"));
//			energySaleUsageSummary.setC005TotalCharge(resultSet.getString("C005_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC006Id(resultSet.getString("C006_ID"));
//			energySaleUsageSummary.setC006ChargeCode(resultSet.getString("C006_CHARGE_CODE"));
//			energySaleUsageSummary.setC006ChargeDesc(resultSet.getString("C006_CHARGE_DESC"));
//			energySaleUsageSummary.setC006TotalCharge(resultSet.getString("C006_TOTAL_CHARGE"));
//			energySaleUsageSummary.setC007Id(resultSet.getString("C007_ID"));
//			energySaleUsageSummary.setC007ChargeCode(resultSet.getString("C007_CHARGE_CODE"));
//			energySaleUsageSummary.setC007ChargeDesc(resultSet.getString("C007_CHARGE_DESC"));
//			energySaleUsageSummary.setC007TotalCharge(resultSet.getString("C007_TOTAL_CHARGE"));
			energySaleUsageSummary.setAgreementType(resultSet.getString("AGREEMENT_TYPE"));
			energySaleUsageSummary.setUnitCost(resultSet.getString("UNIT_COST"));
			energySaleUsageSummary.setTotalAmountPayable(resultSet.getString("TOTAL_AMOUNT_PAYABLE"));
			energySaleUsageSummary.setTotalAmountChargable(resultSet.getString("TOTAL_AMOUNT_CHARGABLE"));
			energySaleUsageSummary.setNetAmountPayable(resultSet.getString("NET_AMOUNT_PAYABLE"));
			return energySaleUsageSummary;
		}
	}


public String calculateEnergySaleCharges(String id, EnergySale energySale) {
	
//	System.out.println("calculateEnergySaleCharges");
//	System.out.println(id);
//	System.out.println(energySale);

	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.setResultsMapCaseInsensitive(true);
	functionCalculateEnergySaleCharges = new SimpleJdbcCall(jdbcTemplate).withFunctionName("CALC_ES_CHARGES");
	SqlParameterSource in = new MapSqlParameterSource().addValue("v_es_id", id);
//	System.out.println(id);

	return functionCalculateEnergySaleCharges.executeFunction(String.class, in);
	
}

@Override
public String finalEnergySale(String id, EnergySale energySale) {
	
	calculateEnergySaleCharges(id, energySale);
//	System.out.println("finalEnergySale");
//	System.out.println(id);
//	System.out.println(energySale);

	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.setResultsMapCaseInsensitive(true);
	functionEnergySaleConfirmation = new SimpleJdbcCall(jdbcTemplate).withFunctionName("ENERGY_SALE_CONFIRMATION");
	SqlParameterSource in = new MapSqlParameterSource().addValue("V_ES_ID", id);
	System.out.println(id);

	return functionEnergySaleConfirmation.executeFunction(String.class, in);
	
}

final class EnergySaleDescriptionMapper implements RowMapper<EnergySaleDescription>{

	public EnergySaleDescriptionMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public EnergySaleDescription mapRow(ResultSet resultSet, int intNum) throws SQLException {
		EnergySaleDescription energySaleDescription = new EnergySaleDescription(); 
		energySaleDescription.setId(resultSet.getString("ID"));
		energySaleDescription.setChargeCode(resultSet.getString("CHARGE_CODE"));
		energySaleDescription.setChargeDesc(resultSet.getString("CHARGE_DESC"));
		energySaleDescription.setChargeTypeCode(resultSet.getString("CHARGE_TYPE_CODE"));
		energySaleDescription.setCreatedDate(resultSet.getString("CREATED_DATE"));
		energySaleDescription.setEnabled(resultSet.getString("ENABLED"));
		energySaleDescription.setRemarks(resultSet.getString("REMARKS"));
		energySaleDescription.setUnitCharge(resultSet.getString("UNIT_CHARGE"));

		return energySaleDescription;
	}
	
}



final class EnergySaleChargeMapper implements RowMapper<EnergySaleCharge>{

	public EnergySaleChargeMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public EnergySaleCharge mapRow(ResultSet resultSet, int intNum) throws SQLException {
		
		EnergySaleCharge energySaleCharge = new EnergySaleCharge(); 
		energySaleCharge.setId(resultSet.getString("ID"));
		energySaleCharge.setEnergySaleId(resultSet.getString("T_ENERGY_SALE_ID"));
		energySaleCharge.setCompanyServiceId(resultSet.getString("M_COMP_SERV_ID"));
		energySaleCharge.setChargeCode(resultSet.getString("CHARGE_CODE"));
		energySaleCharge.setTotalCharge(resultSet.getString("TOTAL_CHARGE"));
//		energySaleCharge.setEnabled(resultSet.getc);
		return energySaleCharge;
	}
	
}

private Object[] setEnergySaleChargeValues(EnergySaleCharge energySaleCharge) {
	return new Object[] {
			energySaleCharge.getEnergySaleId(),
			energySaleCharge.getCompanyServiceId(),
			energySaleCharge.getChargeCode(),
			energySaleCharge.getTotalCharge(),
			energySaleCharge.getEnabled(),
			energySaleCharge.getId()
	};
}

final class EnergySaleCountMapper implements RowMapper<EnergySale> {
	public EnergySaleCountMapper() {
		super();
	}

	@Override
	public EnergySale mapRow(ResultSet resultSet, int rownum) throws SQLException {
		EnergySale energySale =  new EnergySale();
		energySale.setGenStmtCount(resultSet.getString("gen_stmt_count"));
		return energySale;
	}
}
@Override
public EnergySale getEsCountByGenStmt(String generationStatementId) {

	String sql = "select count(*) gen_stmt_count from t_energy_sale where 1=1";
	
	
		if(generationStatementId!=null){
			sql += "and nvl(upper(T_GEN_STMT_ID),'') = nvl(upper('"+generationStatementId+"'),'')";
		}
	
	return jdbcOperations.queryForObject(sql, new EnergySaleCountMapper());
}
}
