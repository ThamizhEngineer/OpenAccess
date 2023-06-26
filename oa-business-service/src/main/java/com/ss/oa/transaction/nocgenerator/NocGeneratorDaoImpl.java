package com.ss.oa.transaction.nocgenerator;

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

import com.ss.oa.transaction.vo.NocGenerator;
import com.ss.oa.transaction.vo.NocGeneratorLine;

@Scope("prototype")
@Component
public class NocGeneratorDaoImpl extends BaseDaoJdbc implements NocGeneratorDao {

	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<NocGenerator> getAllNocGenerators(Map<String, String> criteria) {
		String sql ="SELECT nocgenerator.ID,nocgenerator.CODE,nocgenerator.T_ES_INTENT_ID,nocgenerator.T_INPRINCIPLE_APPLN_ID,nocgenerator.M_ORG_ID,org.NAME as M_ORG_NAME,org.CODE as M_ORG_CODE,nocgenerator.M_SUBSTATION_ID,substation.NAME as M_SUBSTATION_NAME,substation.CODE as M_SUBSTATION_CODE,\n" + 
				"				nocgenerator.M_FEEDER_ID,feeder.NAME as M_FEEDER_NAME , feeder.CODE as M_FEEDER_CODE ,nocgenerator.FUEL_TYPE_CODE,nocgenerator.CAPACITY,nocgenerator.AUXILIARY_CONSUMPTION,nocgenerator.IN_HOUSE_CONSUMPTION,nocgenerator.EX_BUS,nocgenerator.APPROVED_POWER_CAPACITY,nocgenerator.T_INPRIN_APPLN_QUANTUM,nocgenerator.T_INPRIN_APPLN_APPRV_DATE,\n" + 
				"				nocgenerator.IS_ONLINE_DATA_MONITORING,nocgenerator.IS_TANGEDCO,nocgenerator.TANGEDCO_REF_NUMBER,nocgenerator.TANGEDCO_APPROVED_QUANTUM,nocgenerator.TANGEDCO_DATED,nocgenerator.TANGEDCO_TILL_DATE,nocgenerator.CAPTIVE_QUANTUM,\n" + 
				"				nocgenerator.THIRD_PARTY_QUANTUM,nocgenerator.TRADER_QUANTUM,nocgenerator.OTHER_QUANTUM,nocgenerator.TOTAL_POWER_SALE_COMMITMENTS,nocgenerator.MAXIMUM_SURPLUS_QUANTUM,esintent.code as T_ES_INTENT_CODE,\n" + 
				"                nocgenerator.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID,companyservice.M_COMPANY_CODE,companyservice.M_COMPANY_NAME,companyservice.VOLTAGE_CODE,companyservice.VOLTAGE_NAME,\n" + 
				"                nocgenerator.STATUS_CODE,nocgenerator.FLOW_TYPE_CODE FROM T_NOC_GENERATOR nocgenerator\n" + 
				"				left join m_org org on nocgenerator.M_ORG_ID =org.id\n" + 
				"				left join m_substation substation on nocgenerator.M_SUBSTATION_ID =substation.id\n" + 
				"				left join m_feeder feeder on nocgenerator.M_FEEDER_ID =feeder.id\n" + 
				"                left join v_company_service companyservice on nocgenerator.SELLER_COMP_SERV_ID = companyservice.id\n" + 
				"				left join t_es_intent esintent on  nocgenerator.T_ES_INTENT_ID =esintent.id Where 1=1 \n";
		if(criteria.get("es-intent-code")!=null) {
			sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
		}
		if(criteria.get("edc-id")!=null) {
			sql += "and upper(nocgenerator.M_ORG_ID)= upper('"+criteria.get("edc-id")+"')";
		}
		if(criteria.get("code")!=null) {
			sql += "and upper(nocgenerator.CODE) like upper('%"+criteria.get("code")+"%')";
		}
		if(criteria.get("seller-company-service-id")!=null){
			sql += "and upper(nocgenerator.SELLER_COMP_SERV_ID) = upper('"+criteria.get("seller-company-service-id")+"')";
		}
		System.out.println(sql);
		return jdbcOperations.query(sql, new NocGeneratorMapper());
	}

	@Override
	public NocGenerator getNocGeneratorById(String id) {
		NocGenerator nocGenerator =  new NocGenerator();
		List<NocGeneratorLine> nocGeneratorLines =new ArrayList<NocGeneratorLine>();
		String sql ="SELECT nocgenerator.ID,nocgenerator.CODE,nocgenerator.T_ES_INTENT_ID,nocgenerator.T_INPRINCIPLE_APPLN_ID,nocgenerator.M_ORG_ID,org.NAME as M_ORG_NAME,org.CODE as M_ORG_CODE,nocgenerator.M_SUBSTATION_ID,substation.NAME as M_SUBSTATION_NAME,substation.CODE as M_SUBSTATION_CODE,\n" + 
				"				nocgenerator.M_FEEDER_ID,feeder.NAME as M_FEEDER_NAME , feeder.CODE as M_FEEDER_CODE ,nocgenerator.FUEL_TYPE_CODE,nocgenerator.CAPACITY,nocgenerator.AUXILIARY_CONSUMPTION,nocgenerator.IN_HOUSE_CONSUMPTION,nocgenerator.EX_BUS,nocgenerator.APPROVED_POWER_CAPACITY,nocgenerator.T_INPRIN_APPLN_QUANTUM,nocgenerator.T_INPRIN_APPLN_APPRV_DATE,\n" + 
				"				nocgenerator.IS_ONLINE_DATA_MONITORING,nocgenerator.IS_TANGEDCO,nocgenerator.TANGEDCO_REF_NUMBER,nocgenerator.TANGEDCO_APPROVED_QUANTUM,nocgenerator.TANGEDCO_DATED,nocgenerator.TANGEDCO_TILL_DATE,nocgenerator.CAPTIVE_QUANTUM,\n" + 
				"				nocgenerator.THIRD_PARTY_QUANTUM,nocgenerator.TRADER_QUANTUM,nocgenerator.OTHER_QUANTUM,nocgenerator.TOTAL_POWER_SALE_COMMITMENTS,nocgenerator.MAXIMUM_SURPLUS_QUANTUM,esintent.code as T_ES_INTENT_CODE,\n" + 
				"                nocgenerator.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID,companyservice.M_COMPANY_CODE,companyservice.M_COMPANY_NAME,companyservice.VOLTAGE_CODE,companyservice.VOLTAGE_NAME,\n" + 
				"               nocgenerator.STATUS_CODE,nocgenerator.FLOW_TYPE_CODE FROM T_NOC_GENERATOR nocgenerator\n" + 
				"				left join m_org org on nocgenerator.M_ORG_ID =org.id\n" + 
				"				left join m_substation substation on nocgenerator.M_SUBSTATION_ID =substation.id\n" + 
				"				left join m_feeder feeder on nocgenerator.M_FEEDER_ID =feeder.id\n" + 
				"                left join v_company_service companyservice on nocgenerator.SELLER_COMP_SERV_ID = companyservice.id\n" + 
				"				left join t_es_intent esintent on  nocgenerator.T_ES_INTENT_ID =esintent.id where nocgenerator.ID=?\n";
		
		String sql1="SELECT nocGeneratorLine.ID,nocGeneratorLine.T_Noc_Generator_ID,nocGeneratorLine.BUYER_END_ORG_ID, nocGeneratorLine.BUYER_COMP_SERV_ID,\n" + 
				"		  		nocGeneratorLine.DRAWAL_VOLTAGE_CODE,  vcompanyservice.voltage_name AS DRAWAL_VOLTAGE_DESC,\n" + 
				"		  		nocGeneratorLine.INJECTION_PEAK_UNITS,nocGeneratorLine.INJECTION_OFF_PEAK_UNITS,\n" + 
				"		  		nocGeneratorLine.DRAWAL_OFF_PEAK_UNITS,nocGeneratorLine.DRAWAL_PEAK_UNITS,to_char(nocGeneratorLine.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(nocGeneratorLine.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,\n" + 
				"		  		nocGeneratorLine.STATUS_CODE,buyerstatus.VALUE_DESC AS STATUS_DESC,nocGeneratorLine.PROPOSED_UNITS,nocGeneratorLine.APPROVED_UNITS,nocGeneratorLine.IS_CAPTIVE,\n" + 
				"		  		nocGeneratorLine.REMARKS,nocGeneratorLine.CREATED_BY,to_char(nocGeneratorLine.CREATED_DT,'YYYY-MM-DD')CREATED_DT, nocGeneratorLine.MODIFIED_BY,to_char(nocGeneratorLine.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT ,\n" + 
				"		  		vcompanyservice.M_ORG_NAME AS BUYER_ORG_NAME ,\n" + 
				"		  		vcompanyservice.\"number\" AS BUYER_COMPANY_SERVICE_NUMBER ,\n" + 
				"		  		vcompanyservice.M_COMPANY_NAME AS BUYER_COMPANY_NAME,vcompanyservice.M_COMPANY_ID AS BUYER_COMPANY_ID,\n" + 
				"		  		vcompanyservice.CAPACITY AS BUYER_CAPACITY\n" + 
				"		  		FROM T_Noc_Generator_LINE nocGeneratorLine   \n" + 
				"		  		LEFT JOIN V_COMPANY_SERVICE vcompanyservice on nocGeneratorLine.BUYER_COMP_SERV_ID = vcompanyservice.id\n" + 
				"		  		left join v_codes buyervoltage on nocGeneratorLine.DRAWAL_VOLTAGE_CODE = buyervoltage .Value_Code  AND  buyervoltage .list_code = 'VOLTAGE_CODE' \n" + 
				"		  		LEFT JOIN v_codes buyerstatus ON nocGeneratorLine.STATUS_CODE = buyerstatus.VALUE_CODE AND buyerstatus.LIST_CODE ='EWA_LINE_STATUS_CODE'\n" + 
				"		  		LEFT JOIN  T_Noc_Generator nocgenerator ON nocGeneratorLine.T_Noc_Generator_ID = nocgenerator.ID where nocGeneratorLine.T_Noc_Generator_ID=?";
		System.out.println(sql);
		nocGenerator = jdbcOperations.queryForObject(sql,new Object[]{id},  new NocGeneratorMapper());
		nocGeneratorLines = jdbcOperations.query(sql1,new Object[]{id}, new NocGeneratorLineMapper());
		nocGenerator.setNocGeneratorLines(nocGeneratorLines);
		return nocGenerator;
	}

	@Override
	public String addNocGenerator(NocGenerator nocGenerator) {
		String result="";
		try {
			nocGenerator.setId(generateId());
			if(nocGenerator.getCode()== null || nocGenerator.getCode().isEmpty()){
				nocGenerator.setCode(generateCode(NocGenerator.class.getSimpleName(),""));
			}
			String sql = "insert into T_NOC_GENERATOR (CODE,T_ES_INTENT_ID,T_INPRINCIPLE_APPLN_ID,M_ORG_ID,M_SUBSTATION_ID,M_FEEDER_ID,\n" + 
					"FUEL_TYPE_CODE,CAPACITY,AUXILIARY_CONSUMPTION,IN_HOUSE_CONSUMPTION,EX_BUS,APPROVED_POWER_CAPACITY,T_INPRIN_APPLN_QUANTUM,\n" + 
					"T_INPRIN_APPLN_APPRV_DATE,IS_ONLINE_DATA_MONITORING,IS_TANGEDCO,TANGEDCO_REF_NUMBER,TANGEDCO_APPROVED_QUANTUM,TANGEDCO_DATED,TANGEDCO_TILL_DATE,\n" + 
					"CAPTIVE_QUANTUM,THIRD_PARTY_QUANTUM,TRADER_QUANTUM,OTHER_QUANTUM,TOTAL_POWER_SALE_COMMITMENTS,MAXIMUM_SURPLUS_QUANTUM,SELLER_COMP_SERV_ID,STATUS_CODE,FLOW_TYPE_CODE,ID) values (?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setNocGeneratorValues(nocGenerator))>0) {
				result=nocGenerator.getId();
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
	public String updateNocGenerator(String id, NocGenerator nocGenerator) {
		String result="";
		try {
			nocGenerator.setId(id);
			String sql = " update T_NOC_GENERATOR set CODE=?,T_ES_INTENT_ID=?,T_INPRINCIPLE_APPLN_ID=?,M_ORG_ID=?,M_SUBSTATION_ID=?,M_FEEDER_ID=?,\n" + 
					"FUEL_TYPE_CODE=?,CAPACITY=?,AUXILIARY_CONSUMPTION=?,IN_HOUSE_CONSUMPTION=?,EX_BUS=?,APPROVED_POWER_CAPACITY=?,T_INPRIN_APPLN_QUANTUM=?,\n" + 
					"T_INPRIN_APPLN_APPRV_DATE=?,IS_ONLINE_DATA_MONITORING=?,IS_TANGEDCO=?,TANGEDCO_REF_NUMBER=?,TANGEDCO_APPROVED_QUANTUM=?,TANGEDCO_DATED=TO_DATE(?,'yyyy-mm-dd'),TANGEDCO_TILL_DATE=TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"CAPTIVE_QUANTUM=?,THIRD_PARTY_QUANTUM=?,TRADER_QUANTUM=?,OTHER_QUANTUM=?,TOTAL_POWER_SALE_COMMITMENTS=?,MAXIMUM_SURPLUS_QUANTUM=?,SELLER_COMP_SERV_ID=?,STATUS_CODE=?,FLOW_TYPE_CODE=? where id=?";
			if(jdbcOperations.update(sql,setNocGeneratorValues(nocGenerator))>0) {
				result=nocGenerator.getId();
			}else {
				result="Failure";
			}
			
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
			
		}
		return result;
	}
	
	final class NocGeneratorMapper implements RowMapper<NocGenerator>{

		public NocGeneratorMapper() {
			super();
			
		}
		@Override
		public NocGenerator mapRow(ResultSet resultSet, int rownum) throws SQLException {
			NocGenerator nocGenerator = new NocGenerator();
			nocGenerator.setId(resultSet.getString("ID"));
			nocGenerator.setCode(resultSet.getString("CODE"));
			nocGenerator.setEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			nocGenerator.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));
			nocGenerator.setIpaaId(resultSet.getString("T_INPRINCIPLE_APPLN_ID"));
			nocGenerator.setOrgId(resultSet.getString("M_ORG_ID"));
			nocGenerator.setOrgName(resultSet.getString("M_ORG_NAME"));
			nocGenerator.setOrgCode(resultSet.getString("M_ORG_CODE"));
			nocGenerator.setSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			nocGenerator.setSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			nocGenerator.setSubstationCode(resultSet.getString("M_SUBSTATION_CODE"));
			nocGenerator.setFeederId(resultSet.getString("M_FEEDER_ID"));
			nocGenerator.setFeederName(resultSet.getString("M_FEEDER_NAME"));
			nocGenerator.setFeederCode(resultSet.getString("M_FEEDER_CODE"));
			nocGenerator.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
			nocGenerator.setCapacity(resultSet.getString("CAPACITY"));
			nocGenerator.setAuxiliaryConsumption(resultSet.getString("AUXILIARY_CONSUMPTION"));
			nocGenerator.setInHouseConsumption(resultSet.getString("IN_HOUSE_CONSUMPTION"));
			nocGenerator.setExBus(resultSet.getString("EX_BUS"));
			nocGenerator.setApprovedPowerCapacity(resultSet.getString("APPROVED_POWER_CAPACITY"));
			nocGenerator.setIpaaQuantum(resultSet.getString("T_INPRIN_APPLN_QUANTUM"));
			nocGenerator.setIpaaApprovedDate(resultSet.getString("T_INPRIN_APPLN_APPRV_DATE"));
			nocGenerator.setIsOnlineDataMonitoring(resultSet.getString("IS_ONLINE_DATA_MONITORING"));
			nocGenerator.setIsTangedco(resultSet.getString("IS_TANGEDCO"));
			nocGenerator.setTangedcoRefNumber(resultSet.getString("TANGEDCO_REF_NUMBER"));
			nocGenerator.setTangedcoApprovedQuantum(resultSet.getString("TANGEDCO_APPROVED_QUANTUM"));
			nocGenerator.setTangedcoDated(resultSet.getString("TANGEDCO_DATED"));
			nocGenerator.setTangedcoTillDate(resultSet.getString("TANGEDCO_TILL_DATE"));
			nocGenerator.setCaptiveQuantum(resultSet.getString("CAPTIVE_QUANTUM"));
			nocGenerator.setThirdPartyQuantum(resultSet.getString("THIRD_PARTY_QUANTUM"));
			nocGenerator.setTraderQuantum(resultSet.getString("TRADER_QUANTUM"));
			nocGenerator.setOtherQuantum(resultSet.getString("OTHER_QUANTUM"));
			nocGenerator.setTotalPowerSaleCommitments(resultSet.getString("TOTAL_POWER_SALE_COMMITMENTS"));
			nocGenerator.setMaximumSurplusQuantum(resultSet.getString("MAXIMUM_SURPLUS_QUANTUM"));
			nocGenerator.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			nocGenerator.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			nocGenerator.setSellerCompanyId(resultSet.getString("M_COMPANY_ID"));
			nocGenerator.setSellerCompanyCode(resultSet.getString("M_COMPANY_CODE"));
			nocGenerator.setSellerCompanyName(resultSet.getString("M_COMPANY_NAME"));
			nocGenerator.setSellerInjectingVoltageCode(resultSet.getString("VOLTAGE_CODE"));
			nocGenerator.setSellerInjectingVoltageName(resultSet.getString("VOLTAGE_NAME"));
			nocGenerator.setStatusCode(resultSet.getString("STATUS_CODE"));
			nocGenerator.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			return nocGenerator;
		}
	}
	private Object[] setNocGeneratorValues(NocGenerator nocGenerator) {
		return new Object[] {
				nocGenerator.getCode(),
				nocGenerator.getEsIntentId(),
				nocGenerator.getIpaaId(),
				nocGenerator.getOrgId(),
				nocGenerator.getSubstationId(),
				nocGenerator.getFeederId(),	
				nocGenerator.getFuelTypeCode(),
				nocGenerator.getCapacity(),
				nocGenerator.getAuxiliaryConsumption(),
				nocGenerator.getInHouseConsumption(),
				nocGenerator.getExBus(),
				nocGenerator.getApprovedPowerCapacity(),
				nocGenerator.getIpaaQuantum(),
				nocGenerator.getIpaaApprovedDate(),
				nocGenerator.getIsOnlineDataMonitoring(),
				nocGenerator.getIsTangedco(),
				nocGenerator.getTangedcoRefNumber(),
				nocGenerator.getTangedcoApprovedQuantum(),
				nocGenerator.getTangedcoDated(),
				nocGenerator.getTangedcoTillDate(),
				nocGenerator.getCaptiveQuantum(),
				nocGenerator.getThirdPartyQuantum(),
				nocGenerator.getTraderQuantum(),
				nocGenerator.getOtherQuantum(),
				nocGenerator.getTotalPowerSaleCommitments(),
				nocGenerator.getMaximumSurplusQuantum(),
				nocGenerator.getSellerCompanyServiceId(),
				nocGenerator.getStatusCode(),
				nocGenerator.getFlowTypeCode(),
		        nocGenerator.getId(),
		};
	}
	
	
	
	
	
	final class NocGeneratorLineMapper implements RowMapper<NocGeneratorLine>{

		public NocGeneratorLineMapper() {
			super();
		}
		@Override
		public NocGeneratorLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
			NocGeneratorLine nocGeneratorLine = new NocGeneratorLine();
			
			
			nocGeneratorLine.setNocGeneratorId(resultSet.getString("T_Noc_Generator_ID"));
	      
			nocGeneratorLine.setBuyerOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			nocGeneratorLine.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			nocGeneratorLine.setBuyerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
			nocGeneratorLine.setBuyerCompanyName(resultSet.getString("BUYER_COMPANY_NAME"));
			nocGeneratorLine.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			nocGeneratorLine.setBuyerCompServiceNumber(resultSet.getString("BUYER_COMPANY_SERVICE_NUMBER"));
			nocGeneratorLine.setBuyerCapacity(resultSet.getString("BUYER_CAPACITY"));
			nocGeneratorLine.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			nocGeneratorLine.setDrawalVoltageDesc(resultSet.getString("DRAWAL_VOLTAGE_DESC"));
			nocGeneratorLine.setDrawalPeakUnits( resultSet.getString("DRAWAL_PEAK_UNITS"));
			nocGeneratorLine.setDrawalOffPeakUnits(resultSet.getString("DRAWAL_OFF_PEAK_UNITS"));

	 		nocGeneratorLine.setInjectionPeakUnits(resultSet.getString("INJECTION_PEAK_UNITS"));
	 		nocGeneratorLine.setInjectionOffPeakUnits(resultSet.getString("INJECTION_OFF_PEAK_UNITS"));
	        nocGeneratorLine.setAppliedDate(resultSet.getString("APPLIED_DT"));
	        nocGeneratorLine.setApprovedDate(resultSet.getString("APPROVED_DT"));
	        nocGeneratorLine.setRemarks(resultSet.getString("REMARKS"));
	         nocGeneratorLine.setStatusCode(resultSet.getString("STATUS_CODE"));
	         nocGeneratorLine.setStatusDesc(resultSet.getString("STATUS_DESC"));
	         nocGeneratorLine.setCreatedBy(resultSet.getString("CREATED_BY"));
	        nocGeneratorLine.setCreatedDate(resultSet.getString("CREATED_DT"));
	   
	         nocGeneratorLine.setModifiedBy(resultSet.getString("MODIFIED_BY"));
	        nocGeneratorLine.setModifiedDate(resultSet.getString("MODIFIED_DT"));
	        nocGeneratorLine.setProposedUnits(resultSet.getString("PROPOSED_UNITS"));
	        nocGeneratorLine.setApprovedUnits(resultSet.getString("APPROVED_UNITS"));
	        nocGeneratorLine.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
	        nocGeneratorLine.setId(resultSet.getString("ID"));
	        
			return  nocGeneratorLine;
		}
	}
	@Override
	public String addNocGeneratorLine(NocGeneratorLine nocGeneratorLine) {
	
		String result="";
		try {
			nocGeneratorLine.setId(generateId());
			String sql = "INSERT INTO T_Noc_Generator_LINE(T_Noc_Generator_ID,BUYER_END_ORG_ID, BUYER_COMP_SERV_ID, DRAWAL_VOLTAGE_CODE, INJECTION_PEAK_UNITS,\n" + 
					"		INJECTION_OFF_PEAK_UNITS,DRAWAL_OFF_PEAK_UNITS, DRAWAL_PEAK_UNITS, APPLIED_DT, APPROVED_DT,STATUS_CODE, \n" + 
					"		REMARKS,PROPOSED_UNITS,APPROVED_UNITS,IS_CAPTIVE,ID ) \n" + 
					"		VALUES (?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setNocGeneratorLineValues(nocGeneratorLine))>0) {
				result= nocGeneratorLine.getId();
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
	public String updateNocGeneratorLine(NocGeneratorLine nocGeneratorLine) {
		String result="";
		try {
		
			String sql = "update T_Noc_Generator_LINE set T_Noc_Generator_ID=?,BUYER_END_ORG_ID=?, BUYER_COMP_SERV_ID=?, DRAWAL_VOLTAGE_CODE=?, INJECTION_PEAK_UNITS=?,\n" + 
					"		INJECTION_OFF_PEAK_UNITS=?,DRAWAL_OFF_PEAK_UNITS=?, DRAWAL_PEAK_UNITS=?, APPLIED_DT=?, APPROVED_DT=?,STATUS_CODE=?, \n" + 
					"		REMARKS=?,PROPOSED_UNITS=?,APPROVED_UNITS=?,IS_CAPTIVE=? where ID=?";
			
			if(jdbcOperations.update(sql,setNocGeneratorLineValues(nocGeneratorLine))>0) {
				result= nocGeneratorLine.getId();
			}else {
				result = "Failue";
			}
			}catch(Exception e) {
				result= "Failue";
				e.printStackTrace();
			
		}
		return result;
	}
	
	private Object[] setNocGeneratorLineValues(NocGeneratorLine nocGeneratorLine) {
		return new Object[] {		
				
				nocGeneratorLine.getNocGeneratorId(),
				nocGeneratorLine.getBuyerOrgId(),
				nocGeneratorLine.getBuyerCompServiceId(),
				nocGeneratorLine.getDrawalVoltageCode(),
				nocGeneratorLine.getInjectionPeakUnits(),
				nocGeneratorLine.getInjectionOffPeakUnits(),
				nocGeneratorLine.getDrawalOffPeakUnits(),
				nocGeneratorLine.getDrawalPeakUnits(),
				nocGeneratorLine.getAppliedDate(),
			    nocGeneratorLine.getApprovedDate(),
			    nocGeneratorLine.getStatusCode(),
			    nocGeneratorLine.getRemarks(),
			    nocGeneratorLine.getProposedUnits(),
			    nocGeneratorLine.getApprovedUnits(),
			    nocGeneratorLine.getIsCaptive(),
			    nocGeneratorLine.getId()
			    
		};
	}

}
