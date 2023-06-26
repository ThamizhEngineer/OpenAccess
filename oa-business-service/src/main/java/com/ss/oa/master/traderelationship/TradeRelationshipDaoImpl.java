package com.ss.oa.master.traderelationship;

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

import com.ss.oa.master.vo.TradeRelationship;
@Component
@Scope("prototype")
public class TradeRelationshipDaoImpl extends BaseDaoJdbc implements TradeRelationshipDao {
	
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<TradeRelationship> getAllTradeRelationships(Map<String, String> criteria) {
			
		String month="";
		String year="";
		String day="";
		String date="";
		
			String sql="select trade.ID,trade.QUANTUM,trade.FROM_DATE,FROM_DATE,to_char(from_date,'mm') month,to_char(from_date,'yyyy') year ,trade.TO_DATE,trade.STATUS_CODE,statuscode.value_desc as STATUS_Name,trade.M_SELLER_COMPANY_ID,sellercompany.name as M_SELLER_COMPANY_NAME, sellercompany.CODE as M_SELLER_COMPANY_CODE ,\n" + 
					"    trade.M_SELLER_COMP_SERVICE_ID,sellerservice.\"number\" as M_SELLER_COMP_SERVICE_NUMBER,trade.M_BUYER_COMPANY_ID,buyercompany.name as M_BUYER_COMPANY_NAME, buyercompany.CODE as M_BUYER_COMPANY_CODE,trade.M_BUYER_COMP_SERVICE_ID,\n" + 
					"	buyerservice.\"number\" as M_BUYER_COMP_SERVICE_NUMBER,trade.REFERENCENUMBER,trade.REMARKS, trade.C1, trade.C2, trade.C3, trade.C4, trade.C5,\n" + 
					"	trade.IS_CAPTIVE,trade.PEAK_UNITS,trade.OFF_PEAK_UNITS,trade.INTERVAL_TYPE_CODE,trade.SHARE_PERCENT,intervaltypecode.VALUE_DESC as INTERVAL_TYPE_NAME,buyerservice.M_ORG_ID AS BUYER_ORG_ID,sellerservice.M_ORG_ID AS SELLER_ORG_ID,trade.TRADE_RELATIONSHIP_SOURCE_CODE,trade.AGREEMENT_TYPE,trade.FLOW_TYPE_CODE\n" + 
					"	from m_trade_relationship trade\n" + 
					"	left join m_company sellercompany on sellercompany.id=trade.M_SELLER_COMPANY_ID\n" + 
					"	left join m_company buyercompany on buyercompany.id=trade.M_BUYER_COMPANY_ID\n" + 
					"	left join m_company_service sellerservice on sellerservice.id = trade.M_SELLER_COMP_SERVICE_ID \n" + 
					"	left join m_company_service buyerservice on buyerservice.id = trade.M_BUYER_COMP_SERVICE_ID\n" + 
					"	left join v_codes statuscode on trade.STATUS_CODE= statuscode.value_code and statuscode.list_code='TRADE_REL_STATUS_CODE'\n" + 
					"    left join v_codes intervaltypecode on trade.INTERVAL_TYPE_CODE= intervaltypecode.value_code and intervaltypecode.list_code='INTERVAL_TYPE_CODE' WHERE 1=1";
			if(!criteria.isEmpty())
			{
				if(criteria.get("sellerCompanyName")!=null){
					sql += "and upper(sellercompany.NAME)  like upper('%"+criteria.get("sellerCompanyName")+"%') ";
				}
				if(criteria.get("seller-edc")!=null){
					sql += "and upper(sellerservice.M_ORG_ID)  like upper('%"+criteria.get("seller-edc")+"%') ";
				}
				if(criteria.get("buyer-edc")!=null){
					sql += "and upper(buyerservice.M_ORG_ID)  like upper('%"+criteria.get("buyer-edc")+"%') ";
				}
				if(criteria.get("sellerCompanyCode")!=null){
					sql += "and upper(sellercompany.CODE)  like upper('%"+criteria.get("sellerCompanyCode")+"%')";
				}
				if(criteria.get("sellerCompanyServiceNumber")!=null){
					sql += "and upper(sellerservice.\"number\") like upper('%"+criteria.get("sellerCompanyServiceNumber")+"%') ";
				}
				if(criteria.get("sellerCompanyServiceId")!=null){
					sql += "and upper(trade.M_SELLER_COMP_SERVICE_ID) like upper('%"+criteria.get("sellerCompanyServiceId")+"%') ";
				}
				if(criteria.get("buyerCompanyName")!=null){
					sql += "and upper(buyercompany.name)  like upper('%"+criteria.get("buyerCompanyName")+"%') ";
				}
				if(criteria.get("buyerCompanyCode")!=null){
					sql += "and upper(buyercompany.CODE) like upper('%"+criteria.get("buyerCompanyCode")+"%') ";
				}
				if(criteria.get("buyerCompanyServiceNumber")!=null){
					sql += "and upper(buyerservice.\"number\")  like upper('%"+criteria.get("buyerCompanyServiceNumber")+"%')";
				}
				if(criteria.get("buyerCompanyServiceId")!=null){
					sql += "and upper(trade.M_BUYER_COMP_SERVICE_ID)  like upper('%"+criteria.get("buyerCompanyServiceId")+"%')";
				}
				if(criteria.get("quantum")!=null){
					sql += "and upper(trade.QUANTUM)  like upper('%"+criteria.get("quantum")+"%')";
				}
				if(criteria.get("month")!=null && criteria.get("year")!=null){
					month = criteria.get("month");
					year = criteria.get("year");
					day ="01";
					date=year+"-"+month+"-"+day;
//					System.out.println(date);
					sql += "and upper(TO_DATE('"+date+"','yyyy-mm-dd')) between trade.FROM_DATE and trade.TO_DATE";
				
				
				}
				
				
			}
//			sql += " order by nvl(trade.CREATED_DATE,'')  desc ";
			sql += " order by m_seller_comp_service_number , M_BUYER_COMP_SERVICE_NUMBER ";

		
				
			 return   (ArrayList<TradeRelationship>) jdbcOperations.query(sql,new TradeRelationshipMapper());
			
	}

	@Override
	public TradeRelationship getTradeRelationshipById(String tradeRelationshipId) {
		System.out.println(tradeRelationshipId);
		String sql="    select trade.ID,trade.QUANTUM,trade.FROM_DATE,FROM_DATE,to_char(from_date,'mm') month,to_char(from_date,'yyyy') year ,trade.TO_DATE,trade.STATUS_CODE,statuscode.value_desc as STATUS_Name,trade.M_SELLER_COMPANY_ID,sellercompany.name as M_SELLER_COMPANY_NAME, sellercompany.CODE as M_SELLER_COMPANY_CODE ,\n" + 
				"    trade.M_SELLER_COMP_SERVICE_ID,sellerservice.\"number\" as M_SELLER_COMP_SERVICE_NUMBER,trade.M_BUYER_COMPANY_ID,buyercompany.name as M_BUYER_COMPANY_NAME, buyercompany.CODE as M_BUYER_COMPANY_CODE,trade.M_BUYER_COMP_SERVICE_ID,\n" + 
				"	buyerservice.\"number\" as M_BUYER_COMP_SERVICE_NUMBER,trade.REFERENCENUMBER,trade.REMARKS, trade.C1, trade.C2, trade.C3, trade.C4, trade.C5,\n" + 
				"	trade.IS_CAPTIVE,trade.PEAK_UNITS,trade.OFF_PEAK_UNITS,trade.INTERVAL_TYPE_CODE,trade.SHARE_PERCENT,intervaltypecode.VALUE_DESC as INTERVAL_TYPE_NAME,buyerservice.M_ORG_ID AS BUYER_ORG_ID,sellerservice.M_ORG_ID AS SELLER_ORG_ID,trade.TRADE_RELATIONSHIP_SOURCE_CODE,trade.AGREEMENT_TYPE,trade.FLOW_TYPE_CODE\n" + 
				"	from m_trade_relationship trade\n" + 
				"	left join m_company sellercompany on sellercompany.id=trade.M_SELLER_COMPANY_ID\n" + 
				"	left join m_company buyercompany on buyercompany.id=trade.M_BUYER_COMPANY_ID\n" + 
				"	left join m_company_service sellerservice on sellerservice.id = trade.M_SELLER_COMP_SERVICE_ID \n" + 
				"	left join m_company_service buyerservice on buyerservice.id = trade.M_BUYER_COMP_SERVICE_ID\n" + 
				"	left join v_codes statuscode on trade.STATUS_CODE= statuscode.value_code and statuscode.list_code='TRADE_REL_STATUS_CODE'\n" + 
				"    left join v_codes intervaltypecode on trade.INTERVAL_TYPE_CODE= intervaltypecode.value_code and intervaltypecode.list_code='INTERVAL_TYPE_CODE' WHERE trade.ID=?";
			
		 return   jdbcOperations.queryForObject(sql,new Object[]{tradeRelationshipId},new TradeRelationshipMapper());
		
	}

	@Override
	public String addTradeRelationship(TradeRelationship tradeRelationship) {
		String result="";
		try {
			System.out.println("in add dao impl");
			
			tradeRelationship.setId(generateId());
		
			String sql="insert into m_trade_relationship(QUANTUM,FROM_DATE,TO_DATE,STATUS_CODE,M_SELLER_COMPANY_ID,M_SELLER_COMP_SERVICE_ID,M_BUYER_COMPANY_ID,M_BUYER_COMP_SERVICE_ID,REFERENCENUMBER,\n" + 
					"REMARKS,C1,C2,C3,C4,C5,IS_CAPTIVE,PEAK_UNITS,OFF_PEAK_UNITS,INTERVAL_TYPE_CODE,SHARE_PERCENT,TRADE_RELATIONSHIP_SOURCE_CODE,AGREEMENT_TYPE,ID) values(?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			tradeRelationship.setStatusCode("Active");
			if(jdbcOperations.update(sql, setValues(tradeRelationship)) > 0){
				result =tradeRelationship.getId();
			
			}else{
				result =  "FAILURE";
			}		
			
		}catch(Exception e) {
			result="FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String updateTradeRelationship(String tradeRelationshipId, TradeRelationship tradeRelationship) {
		String result = "";
		tradeRelationship.setId(tradeRelationshipId);
		try {
			//String sql="update m_signup set IS_PRIMARY=?,VERSION=?,CODE=?,NAME=?,PLANT_TYPE_CODE=?,FUEL_TYPE_CODE=?,M_SERVICE_ID=?,M_ORG_ID=?,T_GRID_CONN_APPLN_ID=?,TOTAL_CAPACITY=?,M_SUBSTATION_ID=?,INTERFACE_VOLTAGE_PHASE=?,INTERFACE_VOLTAGE_FREQUENCY=?,COMMISSION_DATE=?,PURPOSE=?,ENABLED=?,STATUS=?,LINE1=?,CITY=?,STATE_CODE=?,PINCODE=?,VILLAGE=?,TALUK_CODE=?,DISTRICT_CODE=?,PLS_SF_NO=?,PL_VILLAGE=?,PL_TOWN=?,PL_TALUK_CODE=?,PL_DISTRICT_CODE=?,WIND_PASS_CODE=?,WIND_ZONE_AREA_CODE=? where id=?";		
			String sql="update m_trade_relationship set QUANTUM = ?,FROM_DATE=TO_DATE(?,'yyyy-mm-dd'),TO_DATE= TO_DATE(?,'yyyy-mm-dd'),STATUS_CODE=?,M_SELLER_COMPANY_ID=?,M_SELLER_COMP_SERVICE_ID=?,M_BUYER_COMPANY_ID=?,M_BUYER_COMP_SERVICE_ID=?,REFERENCENUMBER=?,REMARKS=?,C1=?,C2=?,C3=?,C4=?,C5=?,IS_CAPTIVE=?,PEAK_UNITS=?,OFF_PEAK_UNITS=?,INTERVAL_TYPE_CODE=?,SHARE_PERCENT=?,TRADE_RELATIONSHIP_SOURCE_CODE=?,AGREEMENT_TYPE=? where id=?";
			
			if(jdbcOperations.update(sql,setValues(tradeRelationship)) > 0){
				result =tradeRelationship.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}
	
	private Object[] setValues(TradeRelationship tradeRelationship){
		return new Object[]{			
			
				tradeRelationship.getQuantum(),
				tradeRelationship.getFromDate(),
				tradeRelationship.getToDate(),
				tradeRelationship.getStatusCode(),
				tradeRelationship.getSellerCompanyId(),
				tradeRelationship.getSellerCompServiceId(),
				tradeRelationship.getBuyerCompanyId(),
				tradeRelationship.getBuyerCompServiceId(),
				tradeRelationship.getReferenceNumber(),
				tradeRelationship.getRemarks(),
				tradeRelationship.getC1(),
				tradeRelationship.getC2(),
				tradeRelationship.getC3(),
				tradeRelationship.getC4(),
				tradeRelationship.getC5(),
				tradeRelationship.getIsCaptive(),
				tradeRelationship.getPeakUnits(),
				tradeRelationship.getOffPeakUnits(),
				tradeRelationship.getIntervalTypeCode(),
				tradeRelationship.getSharePercent(),
				tradeRelationship.getTraderelationshipSourceCode(),
				tradeRelationship.getAgreementType(),
				tradeRelationship.getId()
			
			};
	}
	
final class TradeRelationshipMapper implements RowMapper<TradeRelationship>{
		
		public TradeRelationshipMapper() {
			super();
		}

		
		public TradeRelationship mapRow(ResultSet resultSet, int rownum) throws SQLException {
			TradeRelationship tradeRelationship=new TradeRelationship();
			
			tradeRelationship.setId(resultSet.getString("ID"));
			tradeRelationship.setQuantum(resultSet.getString("QUANTUM"));
			tradeRelationship.setFromDate(resultSet.getString("FROM_DATE"));
			tradeRelationship.setMonth(resultSet.getString("month"));
			tradeRelationship.setYear(resultSet.getString("year"));
			tradeRelationship.setToDate(resultSet.getString("TO_DATE"));
			tradeRelationship.setStatusCode(resultSet.getString("STATUS_CODE"));
			tradeRelationship.setStatusName(resultSet.getString("STATUS_NAME"));
			tradeRelationship.setSellerCompanyId(resultSet.getString("M_SELLER_COMPANY_ID"));
			tradeRelationship.setSellerCompanyCode(resultSet.getString("M_SELLER_COMPANY_CODE"));
			tradeRelationship.setSellerCompanyName(resultSet.getString("M_SELLER_COMPANY_NAME"));
			tradeRelationship.setSellerCompServiceId(resultSet.getString("M_SELLER_COMP_SERVICE_ID"));
			tradeRelationship.setSellerCompServiceNumber(resultSet.getString("M_SELLER_COMP_SERVICE_NUMBER"));
			tradeRelationship.setBuyerCompanyId(resultSet.getString("M_BUYER_COMPANY_ID"));
			tradeRelationship.setBuyerCompanyCode(resultSet.getString("M_BUYER_COMPANY_Code"));
			tradeRelationship.setBuyerCompanyName(resultSet.getString("M_BUYER_COMPANY_NAME"));
			tradeRelationship.setBuyerCompServiceId(resultSet.getString("M_BUYER_COMP_SERVICE_ID"));
			tradeRelationship.setBuyerCompServiceNumber(resultSet.getString("M_BUYER_COMP_SERVICE_NUMBER"));
			
			tradeRelationship.setReferenceNumber(resultSet.getString("REFERENCENUMBER"));
			tradeRelationship.setRemarks(resultSet.getString("REMARKS"));
			tradeRelationship.setC1(resultSet.getString("C1"));
			tradeRelationship.setC2(resultSet.getString("C2"));
			tradeRelationship.setC3(resultSet.getString("C3"));
			tradeRelationship.setC4(resultSet.getString("C4"));
			tradeRelationship.setC5(resultSet.getString("C5"));
			tradeRelationship.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			tradeRelationship.setPeakUnits(resultSet.getString("PEAK_UNITS"));
			tradeRelationship.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));
			tradeRelationship.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
			tradeRelationship.setSellerOrgId(resultSet.getString("SELLER_ORG_ID"));
			tradeRelationship.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
			tradeRelationship.setIntervalTypeName(resultSet.getString("INTERVAL_TYPE_NAME"));
			tradeRelationship.setSharePercent(resultSet.getString("SHARE_PERCENT"));
			tradeRelationship.setAgreementType(resultSet.getString("AGREEMENT_TYPE"));

			tradeRelationship.setTraderelationshipSourceCode(resultSet.getString("TRADE_RELATIONSHIP_SOURCE_CODE"));
			tradeRelationship.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));

			
			return tradeRelationship;
		}
}

@Override
public String deleteTradeRelationship(String tradeRelationshipId) {
	String result = "";
	try {
		String sql="delete from m_trade_relationship where id=?";
		if(jdbcOperations.update(sql,new Object[]{tradeRelationshipId})>0){
			result =  tradeRelationshipId;
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

	

}
