package com.ss.oa.transaction.noc;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.transaction.vo.Noc;

@Scope("prototype")
@Component
public class NocDaoImpl extends BaseDaoJdbc implements NocDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Noc> getAllNocs(Map<String, String> criteria) {	
	
	
		String sql="select noc.ID,noc.CODE,noc.TYPE_CODE,noc.STATUS_CODE,noc.BUYER_COMP_SERV_ID,noc.T_ES_INTENT_ID,noc.PROPOSED_CAPACITY,noc.EXEMPT_RC,noc.HAS_DUES,  \n" + 
				"				 								noc.DUE_DETAILS,noc.PENDING_CASE_DETAILS,noc.TECHNICAL_FEASIBILITY_DETAILS,noc.APPROVED_CAPACITY, \n" + 
				"				 								buyerservice.\"number\" as BUYER_COMP_SERV_NUMBER,buyerservice.M_COMPANY_ID as BUYER_COMPANY_ID,    \n" + 
				"				 								buyerservice.M_COMPANY_NAME as BUYER_COMPANY_NAME ,buyerservice.M_COMPANY_CODE as BUYER_COMPANY_CODE,buyerservice.M_ORG_ID as BUYER_ORG_ID,  \n" + 
				"				 								buyerservice.M_ORG_NAME as BUYER_ORG_NAME,buyerservice.M_ORG_CODE as BUYER_ORG_CODE, \n" + 
				"				 								buyerservice.COMP_SER_TYPE_CODE as BUYER_COMP_SER_TYPE_CODE,buyerservice.CAPACITY as BUYER_CAPACITY, \n" + 
				"				 								buyerservice.M_SUBSTATION_ID as BUYER_SUBSTATION_ID,buyerservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME,  \n" + 
				"				 								buyerservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE ,buyerservice.M_FEEDER_ID as BUYER_FEEDER_ID,  \n" + 
				"				 							    buyerservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,buyerservice.M_FEEDER_CODE as BUYER_FEEDER_CODE,  \n" + 
				"				 								buyerservice.VOLTAGE_CODE as BUYER_VOLTAGE_CODE,buyervoltage.Value_Desc as BUYER_VOLTAGE_NAME,  \n" + 
				"				 								esintent.SELLER_COMP_SERV_ID,sellerservice.\"number\" as SELLER_COMP_SERV_NUMBER,sellerservice.M_COMPANY_ID as SELLER_COMPANY_ID,sellerservice.M_COMPANY_NAME as SELLER_COMPANY_NAME ,sellerservice.M_COMPANY_CODE as SELLER_COMPANY_CODE,sellerservice.M_ORG_ID as SELLER_ORG_ID,sellerservice.M_ORG_NAME as SELLER_ORG_NAME,sellerservice.M_ORG_CODE as SELLER_ORG_CODE,sellerservice.COMP_SER_TYPE_CODE as SELLER_COMP_SER_TYPE_CODE,sellerservice.CAPACITY as SELLER_CAPACITY,  \n" + 
				"				 								sellerservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID,sellerservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellerservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE ,sellerservice.M_FEEDER_ID as SELLER_FEEDER_ID, sellerservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,sellerservice.M_FEEDER_CODE as SELLER_FEEDER_CODE,sellerservice.VOLTAGE_CODE as SELLER_VOLTAGE_CODE,sellervoltage.Value_Desc as SELLER_VOLTAGE_NAME, \n" + 
				"				 								to_char(esintent.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(esintent.TO_DT,'YYYY-MM-DD')TO_DT,esintent.CODE as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,esintent.IS_CAPTIVE,esintentline.PROPOSED_QUANTUM,companymeter.ID as SELLER_COMPANY_METER_ID,companymeter.METER_NUMBER as SELLER_COMPANY_METER_NUMBER,companymeter.IS_ABTMETER ,companymeter.MODEM_NUMBER, \n" + 
				"				 								noc.AGMT_PERIOD_CODE,to_char(noc.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT,noc.IS_CAPTIVE,buyerservice.CAPACITY as BUYER_SANCTIONED_CAPACITY,sellerservice.CAPACITY as SELLER_SANCTIONED_CAPACITY,noc.FLOW_TYPE_CODE \n" + 
				"				 								from T_NOC noc \n" + 
				"				 								left join V_COMPANY_SERVICE buyerservice on noc.BUYER_COMP_SERV_ID= buyerservice.id \n" + 
				"				 								left join v_codes buyervoltage on buyerservice.VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE'    \n" + 
				"				 							  left join T_ES_INTENT esintent on noc.T_ES_INTENT_ID = esintent.ID  \n" + 
				"				 								left join V_COMPANY_SERVICE sellerservice on esintent.SELLER_COMP_SERV_ID= sellerservice.id \n" + 
				"				 								left join v_codes sellervoltage on sellerservice.VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'  \n" + 
				"				 								left join T_ES_INTENT_LINE esintentline on noc.T_ES_INTENT_ID = esintentline.T_EST_INTENT_ID AND noc.BUYER_COMP_SERV_ID = esintentline.BUYER_COMP_SERV_ID  \n" + 
				"				 								left join M_COMPANY_METER companymeter on esintent.SELLER_COMP_SERV_ID = companymeter.M_COMPANY_SERVICE_ID  \n" + 
				"				                                left join m_powerplant buyerpowerplant on buyerpowerplant.M_SERVICE_ID =  noc.BUYER_COMP_SERV_ID \n" + 
				"				                                 left join M_GENERATOR buyergenerator on buyergenerator.M_POWERPLANT_ID = buyerpowerplant.id WHERE 1=1";
		
		if(criteria.get("buyer-edc-id")!=null) {
			sql += "and upper(buyerservice.M_ORG_ID)= upper('"+criteria.get("buyer-edc-id")+"')";
		}
		if(criteria.get("seller-edc-id")!=null) {
			sql += "and upper(sellerservice.M_ORG_ID)= upper('"+criteria.get("seller-edc-id")+"')";
		}
		
		if(criteria.get("seller-company-id")!=null) {
			sql += "and upper(sellerservice.M_COMPANY_ID)= upper('"+criteria.get("seller-company-id")+"')";
		}
		
		if(criteria.get("buyer-company-service-number")!=null) {
			sql += "and upper(buyerservice.\"number\" )= upper('"+criteria.get("buyer-company-service-number")+"')";
		}
		
		if(criteria.get("buyer-company-service-id")!=null) {
			sql += "and upper(noc.BUYER_COMP_SERV_ID )= upper('"+criteria.get("buyer-company-service-id")+"')";
		}
		
		
		if(criteria.get("buyer-company-id")!=null) {
			sql += "and upper(buyerservice.M_COMPANY_ID)= upper('"+criteria.get("buyer-company-id")+"')";
		}
		
		if(criteria.get("voltage-code")!=null) {
			sql += "and upper(buyerservice.VOLTAGE_CODE)= upper('"+criteria.get("voltage-code")+"')";
		}
		
		if(criteria.get("buyer-company-name")!=null) {
			sql += "and upper(buyerservice.M_COMPANY_NAME )= upper('"+criteria.get("buyer-company-name")+"')";
		}	
		
		
		if(criteria.get("status")!=null) {
			sql += "and upper(noc.STATUS_CODE)= upper('"+criteria.get("status")+"')";
		}
		
		if(criteria.get("from-date")!=null) {
			sql += "and upper(esintent.FROM_DT)= upper('"+criteria.get("from-date")+"')";
		}
		
		if(criteria.get("to-date")!=null) {
			sql += "and upper(esintent.TO_DT)= upper('"+criteria.get("to-date")+"')";
		}
		if(criteria.get("drawal-voltage-code")!=null) {
			sql += "and upper(sellerservice.VOLTAGE_CODE)= upper('"+criteria.get("drawal-voltage-code")+"')";
		}
		
		if(criteria.get("seller-company-service-number")!=null) {
			sql += "and upper(sellerservice.\"number\" )= upper('"+criteria.get("seller-company-service-number")+"')";
		}	
		
		if(criteria.get("seller-company-id")!=null) {
			sql += "and upper(sellerservice.M_COMPANY_ID)= upper('"+criteria.get("seller-company-id")+"')";
		}
		
		if(criteria.get("seller-company-name")!=null) {
			sql += "and upper(sellerservice.M_COMPANY_NAME )= upper('"+criteria.get("seller-company-name")+"')";
		}
		if(criteria.get("es-intent-code")!=null) {
			sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
		}
		if(criteria.get("noc-code")!=null) {
			sql += "and upper(noc.CODE) like upper('%"+criteria.get("noc-code")+"%')";
		}
		if(criteria.get("seller-company-service-id")!=null) {
			sql += "and upper(esintent.SELLER_COMP_SERV_ID )= upper('"+criteria.get("seller-company-service-id")+"')";
		}
		
		return jdbcOperations.query(sql, new NocMapper());
	}

	@Override
	public Noc getNocById(String id) {
		
		String sql="select noc.ID,noc.CODE,noc.TYPE_CODE,noc.STATUS_CODE,noc.BUYER_COMP_SERV_ID,noc.T_ES_INTENT_ID,noc.PROPOSED_CAPACITY,noc.EXEMPT_RC,noc.HAS_DUES,  \n" + 
				"				 								noc.DUE_DETAILS,noc.PENDING_CASE_DETAILS,noc.TECHNICAL_FEASIBILITY_DETAILS,noc.APPROVED_CAPACITY, \n" + 
				"				 								buyerservice.\"number\" as BUYER_COMP_SERV_NUMBER,buyerservice.M_COMPANY_ID as BUYER_COMPANY_ID,    \n" + 
				"				 								buyerservice.M_COMPANY_NAME as BUYER_COMPANY_NAME ,buyerservice.M_COMPANY_CODE as BUYER_COMPANY_CODE,buyerservice.M_ORG_ID as BUYER_ORG_ID,  \n" + 
				"				 								buyerservice.M_ORG_NAME as BUYER_ORG_NAME,buyerservice.M_ORG_CODE as BUYER_ORG_CODE, \n" + 
				"				 								buyerservice.COMP_SER_TYPE_CODE as BUYER_COMP_SER_TYPE_CODE,buyerservice.CAPACITY as BUYER_CAPACITY, \n" + 
				"				 								buyerservice.M_SUBSTATION_ID as BUYER_SUBSTATION_ID,buyerservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME,  \n" + 
				"				 								buyerservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE ,buyerservice.M_FEEDER_ID as BUYER_FEEDER_ID,  \n" + 
				"				 							buyerservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,buyerservice.M_FEEDER_CODE as BUYER_FEEDER_CODE,  \n" + 
				"				 								buyerservice.VOLTAGE_CODE as BUYER_VOLTAGE_CODE,buyervoltage.Value_Desc as BUYER_VOLTAGE_NAME,  \n" + 
				"				 								esintent.SELLER_COMP_SERV_ID,sellerservice.\"number\" as SELLER_COMP_SERV_NUMBER,sellerservice.M_COMPANY_ID as SELLER_COMPANY_ID,sellerservice.M_COMPANY_NAME as SELLER_COMPANY_NAME ,sellerservice.M_COMPANY_CODE as SELLER_COMPANY_CODE,sellerservice.M_ORG_ID as SELLER_ORG_ID,sellerservice.M_ORG_NAME as SELLER_ORG_NAME,sellerservice.M_ORG_CODE as SELLER_ORG_CODE,sellerservice.COMP_SER_TYPE_CODE as SELLER_COMP_SER_TYPE_CODE,sellerservice.CAPACITY as SELLER_CAPACITY,  \n" + 
				"				 								sellerservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID,sellerservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellerservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE ,sellerservice.M_FEEDER_ID as SELLER_FEEDER_ID, sellerservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,sellerservice.M_FEEDER_CODE as SELLER_FEEDER_CODE,sellerservice.VOLTAGE_CODE as SELLER_VOLTAGE_CODE,sellervoltage.Value_Desc as SELLER_VOLTAGE_NAME, \n" + 
				"				 								to_char(esintent.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(esintent.TO_DT,'YYYY-MM-DD')TO_DT,esintent.CODE as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,esintent.IS_CAPTIVE,esintentline.PROPOSED_QUANTUM,companymeter.ID as SELLER_COMPANY_METER_ID,companymeter.METER_NUMBER as SELLER_COMPANY_METER_NUMBER,companymeter.IS_ABTMETER ,companymeter.MODEM_NUMBER, \n" + 
				"				 								noc.AGMT_PERIOD_CODE,to_char(noc.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT,noc.IS_CAPTIVE,buyerservice.CAPACITY as BUYER_SANCTIONED_CAPACITY,sellerservice.CAPACITY as SELLER_SANCTIONED_CAPACITY,noc.FLOW_TYPE_CODE \n" + 
				"				 								from T_NOC noc \n" + 
				"				 								left join V_COMPANY_SERVICE buyerservice on noc.BUYER_COMP_SERV_ID= buyerservice.id \n" + 
				"				 								left join v_codes buyervoltage on buyerservice.VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE'    \n" + 
				"				 							  left join T_ES_INTENT esintent on noc.T_ES_INTENT_ID = esintent.ID  \n" + 
				"				 								left join V_COMPANY_SERVICE sellerservice on esintent.SELLER_COMP_SERV_ID= sellerservice.id \n" + 
				"				 								left join v_codes sellervoltage on sellerservice.VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'  \n" + 
				"				 								left join T_ES_INTENT_LINE esintentline on noc.T_ES_INTENT_ID = esintentline.T_EST_INTENT_ID AND noc.BUYER_COMP_SERV_ID = esintentline.BUYER_COMP_SERV_ID  \n" + 
				"				 								left join M_COMPANY_METER companymeter on esintent.SELLER_COMP_SERV_ID = companymeter.M_COMPANY_SERVICE_ID  \n" + 
				"				                                left join m_powerplant buyerpowerplant on buyerpowerplant.M_SERVICE_ID =  noc.BUYER_COMP_SERV_ID \n" + 
				"				                                 left join M_GENERATOR buyergenerator on buyergenerator.M_POWERPLANT_ID = buyerpowerplant.id WHERE noc.ID=? ";
		
		return jdbcOperations.queryForObject(sql,new Object[]{id}, new NocMapper());
	}

	@Override
	public String addNoc(Noc noc) {
		String result="";
		try {
			noc.setId(generateId());
			if(noc.getCode()== null || noc.getCode().isEmpty()){
				noc.setCode(generateCode(Noc.class.getSimpleName(),""));
			}
			String sql = "insert into T_NOC (CODE,TYPE_CODE,STATUS_CODE,BUYER_COMP_SERV_ID,T_ES_INTENT_ID,PROPOSED_CAPACITY,EXEMPT_RC,\n" + 
					"HAS_DUES,DUE_DETAILS,PENDING_CASE_DETAILS,TECHNICAL_FEASIBILITY_DETAILS,APPROVED_CAPACITY,AGMT_PERIOD_CODE,AGREEMENT_DT,IS_CAPTIVE,FLOW_TYPE_CODE,ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?)";
			if(jdbcOperations.update(sql,setNocValues(noc))>0) {
				result=noc.getId();
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
	public String updateNoc(String id,Noc noc) {
		String result="";
		try {
			noc.setId(id);
			String sql = "update T_NOC set CODE=?,TYPE_CODE=?,STATUS_CODE=?,BUYER_COMP_SERV_ID=?,T_ES_INTENT_ID=?,PROPOSED_CAPACITY=?,EXEMPT_RC=?,\n" + 
					"HAS_DUES=?,DUE_DETAILS=?,PENDING_CASE_DETAILS=?,TECHNICAL_FEASIBILITY_DETAILS=?,APPROVED_CAPACITY=?,AGMT_PERIOD_CODE=?,AGREEMENT_DT= TO_DATE(?,'YYYY-MM-DD'),IS_CAPTIVE=?,FLOW_TYPE_CODE=? where id=?";
			if(jdbcOperations.update(sql,setNocValues(noc))>0) {
				result=noc.getId();
			}else {
				result="Failure";
			}
			
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
			
		}
		return result;
	}
	
	private Object[] setNocValues(Noc noc) {
		return new Object[] {
				noc.getCode(),
				noc.getTypeCode(),
				noc.getStatusCode(),
				noc.getBuyerCompanyServiceId(),
				noc.getEnergySaleIntentId(),
				noc.getProposedCapacity(),
				noc.getExemptRc(),
				noc.getHasDues(),
				noc.getDueDetails(),
				noc.getPendingCaseDetails(),
				noc.getTechnicalFeasibilityDetails(),
				noc.getApprovedCapacity(),
				noc.getAgreementPeriodCode(),
				noc.getAgreementDate(),
				noc.getIsCaptive(),
				noc.getFlowTypeCode(),
				noc.getId()
		};
	}
	
	final class NocMapper implements RowMapper<Noc>{

		public NocMapper() {
			super();
			
		}
		@Override
		public Noc mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Noc noc=new Noc();
			noc.setId(resultSet.getString("ID"));
			noc.setCode(resultSet.getString("CODE"));
			noc.setTypeCode(resultSet.getString("TYPE_CODE"));
			noc.setStatusCode(resultSet.getString("STATUS_CODE"));
			noc.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			noc.setEnergySaleIntentId(resultSet.getString("T_ES_INTENT_ID"));
			noc.setProposedCapacity(resultSet.getString("PROPOSED_CAPACITY"));
			noc.setExemptRc(resultSet.getString("EXEMPT_RC"));
			noc.setHasDues(resultSet.getString("HAS_DUES"));
			noc.setDueDetails(resultSet.getString("DUE_DETAILS"));
			noc.setPendingCaseDetails(resultSet.getString("PENDING_CASE_DETAILS"));
			noc.setTechnicalFeasibilityDetails(resultSet.getString("TECHNICAL_FEASIBILITY_DETAILS"));
			noc.setApprovedCapacity(resultSet.getString("APPROVED_CAPACITY"));
			noc.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			noc.setBuyerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
			noc.setBuyerCompanyName(resultSet.getString("BUYER_COMPANY_NAME"));
			noc.setBuyerCompanyCode(resultSet.getString("BUYER_COMPANY_CODE"));
			noc.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
			noc.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			noc.setBuyerOrgCode(resultSet.getString("BUYER_ORG_CODE"));
			noc.setBuyerCompanyServiceTypeCode(resultSet.getString("BUYER_COMP_SER_TYPE_CODE"));
			noc.setBuyerSanctionedCapacity(resultSet.getString("BUYER_CAPACITY"));;
			noc.setBuyerSubstationId(resultSet.getString("BUYER_SUBSTATION_ID"));
			noc.setBuyerSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			noc.setBuyerSubstationCode(resultSet.getString("BUYER_SUBSTATION_CODE"));
			noc.setBuyerFeederId(resultSet.getString("BUYER_FEEDER_ID"));
			noc.setBuyerFeederName(resultSet.getString("BUYER_FEEDER_NAME"));
			noc.setBuyerFeederCode(resultSet.getString("BUYER_FEEDER_CODE"));
			noc.setBuyerVoltageCode(resultSet.getString("BUYER_VOLTAGE_CODE"));
			noc.setBuyerVoltageName(resultSet.getString("BUYER_VOLTAGE_NAME"));
			noc.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			noc.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			noc.setSellerCompanyId(resultSet.getString("SELLER_COMPANY_ID"));
			noc.setSellerCompanyName(resultSet.getString("SELLER_COMPANY_NAME"));
			noc.setSellerCompanyCode(resultSet.getString("SELLER_COMPANY_CODE"));
			noc.setSellerOrgId(resultSet.getString("SELLER_ORG_ID"));
			noc.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			noc.setSellerOrgCode(resultSet.getString("SELLER_ORG_CODE"));
			noc.setSellerCompanyServiceTypeCode(resultSet.getString("SELLER_COMP_SER_TYPE_CODE"));
			noc.setSellerSanctionedCapacity(resultSet.getString("SELLER_CAPACITY"));;
			noc.setSellerSubstationId(resultSet.getString("SELLER_SUBSTATION_ID"));
			noc.setSellerSubstationName(resultSet.getString("SELLER_SUBSTATION_NAME"));
			noc.setSellerSubstationCode(resultSet.getString("SELLER_SUBSTATION_CODE"));
			noc.setSellerFeederId(resultSet.getString("SELLER_FEEDER_ID"));
			noc.setSellerFeederName(resultSet.getString("SELLER_FEEDER_NAME"));
			noc.setSellerFeederCode(resultSet.getString("SELLER_FEEDER_CODE"));
			noc.setSellerVoltageCode(resultSet.getString("SELLER_VOLTAGE_CODE"));
			noc.setSellerVoltageName(resultSet.getString("SELLER_VOLTAGE_NAME"));
			noc.setFromDate(resultSet.getString("FROM_DT"));
			noc.setToDate(resultSet.getString("TO_DT"));
			noc.setEsIntentCode(resultSet.getString("ES_INTENT_CODE"));
			noc.setFromMonth(resultSet.getString("FROM_MONTH"));
			noc.setToMonth(resultSet.getString("TO_MONTH"));
			noc.setFromYear(resultSet.getString("FROM_YEAR"));
			noc.setToYear(resultSet.getString("TO_YEAR"));			
			noc.setProposedCapacity(resultSet.getString("PROPOSED_QUANTUM"));
			noc.setSellerCompanyMeterId(resultSet.getString("SELLER_COMPANY_METER_ID"));
			noc.setSellerCompanyMeterNumber(resultSet.getString("SELLER_COMPANY_METER_NUMBER"));
			noc.setIsAbtMeter(resultSet.getString("IS_ABTMETER"));
			noc.setModemNumber(resultSet.getString("MODEM_NUMBER"));
			noc.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			noc.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
			noc.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
			noc.setBuyerSanctionedCapacity(resultSet.getString("BUYER_SANCTIONED_CAPACITY"));
			noc.setSellerSanctionedCapacity(resultSet.getString("SELLER_SANCTIONED_CAPACITY"));
			noc.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			
			return noc;
		}

		
		
	}

}
