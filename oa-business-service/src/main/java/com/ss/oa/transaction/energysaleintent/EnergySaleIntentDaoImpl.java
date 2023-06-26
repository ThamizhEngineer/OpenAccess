package com.ss.oa.transaction.energysaleintent;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.common.Response;

import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;

@Scope("prototype")
@Component
public class EnergySaleIntentDaoImpl extends BaseDaoJdbc implements EnergySaleIntentDao {
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<EnergySaleIntent> getAllEnergySaleIntents(Map<String, String> criteria) {
		
		String sql="select esintent.ID,esintent.CODE,esintent.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER ,companyservice.M_COMPANY_ID as SELLER_COMP_ID,\n" + 
				"companyservice.M_COMPANY_NAME as SELLER_COMP_NAME,companyservice.M_COMPANY_NAME as SELLER_COMP_CODE,companyservice.M_ORG_ID as SELLER_END_ORG_ID,\n" + 
				"companyservice.M_ORG_NAME as SELLER_END_ORG_NAME,companyservice.M_ORG_CODE as SELLER_END_ORG_CODE,esintent.AGMT_PERIOD_CODE,esintent.FROM_DT,esintent.TO_DT,\n" + 
				"esintent.IS_CAPTIVE,esintent.STATUS_CODE,esintent.FROM_MONTH,esintent.FROM_YEAR,esintent.TO_MONTH,esintent.TO_YEAR ,esintent.T_EWA_ID  as T_EWA_ID,\n" + 
				"ewa.STATUS_CODE as EWA_STATUS_CODE,ewa.TOAL_APPROVED_UNITS as EWA_TOAL_APPROVED_UNITS,\n" + 
				"esintent.FLOW_TYPE_CODE ,companyservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID,companyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE,\n" + 
				"companyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,companyservice.M_FEEDER_ID as SELLER_FEEDER_ID,companyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE,\n" + 
				"companyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,companyservice.VOLTAGE_CODE as SELLER_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as SELLER_VOLTAGE_NAME,\n" + 
				"esintent.T_INPRINCIPLE_APPLN_ID,ipaa.STATUS_CODE as IPAA_STATUS_CODE,esintent.T_NOC_GENERATOR_ID,nocgenerator.status_code as T_NOC_GENERATOR_STATUS_CODE,esintent.PROPOSED_CAPACITY,\n" + 
				"esintent.SLDC_AWAITED_DT,esintent.SLDC_APPROVAL_DT,esintent.SLDC_REJECTED_DT,esintent.SLDC_APPROVED,esintent.SLDC_REMARKS\n" + 
				"from T_ES_INTENT esintent\n" + 
				"left join V_COMPANY_SERVICE companyservice on esintent.SELLER_COMP_SERV_ID = companyservice.ID										\n" + 
				"left join T_EWA ewa on esintent.id = ewa.T_ES_INTENT_ID \n" + 
				"left join T_INPRINCIPLE_APPLN ipaa on esintent.id = ipaa.T_ES_INTENT_ID\n" + 
				"left join T_NOC_GENERATOR nocgenerator on esintent.id =  nocgenerator.T_ES_INTENT_ID  where 1=1";
		
			if(criteria.get("edc-id")!=null) {
				sql += "and upper(companyservice.M_ORG_ID)= upper('"+criteria.get("edc-id")+"')";
			}
			
			if(criteria.get("seller-company-service-number")!=null) {
				sql += "and upper(companyservice.\"number\" )= upper('"+criteria.get("seller-company-service-number")+"')";
			}
			
			
			
			if(criteria.get("seller-company-id")!=null) {
				sql += "and upper(companyservice.M_COMPANY_ID)= upper('"+criteria.get("seller-company-id")+"')";
			}
			
			if(criteria.get("seller-company-name")!=null) {
				sql += "and upper(company.id)= upper('"+criteria.get("seller-company-name")+"')";
			}
			
			
			
			if(criteria.get("status")!=null) {
				sql += "and upper(esintent.STATUS_CODE)= upper('"+criteria.get("status")+"')";
			}
			
			if(criteria.get("period")!=null) {
				sql += "and upper(esintent.AGMT_PERIOD_CODE)= upper('"+criteria.get("period")+"')";
			}
			if(criteria.get("es-intent-code")!=null) {
				sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
			}	
			if(criteria.get("seller-company-service-id")!=null) {
				sql += "and upper(esintent.SELLER_COMP_SERV_ID )= upper('"+criteria.get("seller-company-service-id")+"')";
			}
			if(criteria.get("seller-edc-id")!=null) {
				sql += "and upper(companyservice.M_ORG_ID)= upper('"+criteria.get("seller-edc-id")+"')";
			}
			if(criteria.get("flow-type-code")!=null) {
				sql += "and upper(esintent.FLOW_TYPE_CODE)= upper('"+criteria.get("flow-type-code")+"')";
			}
			
			
			
			
			
		
		System.out.println(sql);
		
		return  jdbcOperations.query(sql, new EnergySaleIntentMapper());
	}

	@Override
	public EnergySaleIntent getEnergySaleIntentById(String id) {
		EnergySaleIntent energySaleIntent =new EnergySaleIntent();
		List<EnergySaleIntentLine> energySaleIntentLines =new ArrayList<EnergySaleIntentLine>();
		String sql ="select esintent.ID,esintent.CODE,esintent.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER ,companyservice.M_COMPANY_ID as SELLER_COMP_ID,\n" + 
				"																companyservice.M_COMPANY_NAME as SELLER_COMP_NAME,companyservice.M_COMPANY_NAME as SELLER_COMP_CODE,companyservice.M_ORG_ID as SELLER_END_ORG_ID,companyservice.M_ORG_NAME as SELLER_END_ORG_NAME,companyservice.M_ORG_CODE as SELLER_END_ORG_CODE,\n" + 
				"																esintent.AGMT_PERIOD_CODE,esintent.FROM_DT,esintent.TO_DT,esintent.IS_CAPTIVE,esintent.STATUS_CODE,esintent.FROM_MONTH,esintent.FROM_YEAR,esintent.TO_MONTH,esintent.TO_YEAR ,esintent.T_EWA_ID  as T_EWA_ID,ewa.STATUS_CODE as EWA_STATUS_CODE,ewa.TOAL_APPROVED_UNITS as EWA_TOAL_APPROVED_UNITS,\n" + 
				"								                                esintent.FLOW_TYPE_CODE ,companyservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID,companyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE,companyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,companyservice.M_FEEDER_ID as SELLER_FEEDER_ID,companyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE,companyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,\n" + 
				"								                                companyservice.VOLTAGE_CODE as SELLER_VOLTAGE_CODE,companyservice.VOLTAGE_NAME as SELLER_VOLTAGE_NAME,esintent.T_INPRINCIPLE_APPLN_ID,ipaa.STATUS_CODE as IPAA_STATUS_CODE,esintent.T_NOC_GENERATOR_ID,nocgenerator.status_code as T_NOC_GENERATOR_STATUS_CODE,esintent.PROPOSED_CAPACITY,esintent.SLDC_AWAITED_DT,esintent.SLDC_APPROVAL_DT,esintent.SLDC_REJECTED_DT,esintent.SLDC_APPROVED,esintent.SLDC_REMARKS\n" + 
				"																from T_ES_INTENT esintent\n" + 
				"															    left join V_COMPANY_SERVICE companyservice on esintent.SELLER_COMP_SERV_ID = companyservice.ID										\n" + 
				"												                left join T_EWA ewa on esintent.id = ewa.T_ES_INTENT_ID \n" + 
				"								                                left join T_INPRINCIPLE_APPLN ipaa on esintent.id = ipaa.T_ES_INTENT_ID\n" + 
				"								                                left join T_NOC_GENERATOR nocgenerator on esintent.id =  nocgenerator.T_ES_INTENT_ID where esintent.ID = ?";
		
		 energySaleIntent = jdbcOperations.queryForObject(sql,new Object[]{id}, new EnergySaleIntentMapper());
		 
		 String sql1="select esintentline.ID,esintentline.T_EST_INTENT_ID,esintentline.BUYER_COMP_SERV_ID,companyservice.\"number\" as BUYER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID as BUYER_COMP_ID,\n" + 
		 		"		 				company.name as BUYER_COMP_NAME,company.code as BUYER_COMP_CODE,companyservice.M_ORG_ID as BUYER_END_ORG_ID,org.name as BUYER_END_ORG_NAME,org.code as BUYER_END_ORG_CODE,esintentline.STATUS_CODE,esintentline.T_NOC_ID,esintentline.T_CONSENT_ID,\n" + 
		 		"		 				esintentline.T_EPA_ID,esintentline.T_OA_AGMT_ID,esintentline.PROPOSED_QUANTUM,noc.STATUS_CODE as NOC_STATUS_CODE ,consent.STATUS_CODE as CONSENT_STATUS_CODE,oaa.STATUS_CODE as OA_AGMT_STATUS_CODE,epa.STATUS_CODE as EPA_STATUS_CODE,esintentline.M_TRADE_RELATIONSHIP_ID,esintentline.IS_CAPTIVE,\n" + 
		 		"		 				noc.APPROVED_CAPACITY as NOC_APPROVED_CAPACITY, consent.APPROVED_CAPACITY as CONSENT_APPROVED_CAPACITY,consent.DRAWAL_VOLTAGE_CODE,buyervoltage.VALUE_DESC as DRAWAL_VOLTAGE_DESC,consent.INJECTION_VOLTAGE_CODE,sellervoltage.VALUE_DESC AS INJECTION_VOLTAGE_NAME,\n" + 
		 		"		 				esintentline.T_STANDING_CLEARENCE_ID,standingclearence.STATUS_CODE as T_STAND_CLEAR_STATUS_CODE,ipaaline.id as IPAA_LINE_ID,ipaaline.APPROVED_UNITS as IPAA_LINE_APPROVED_UNITS,nocgenline.id as NOC_GENERATOR_ID,nocgenline.APPROVED_UNITS as NOC_GENERATOR_APPROVED_UNITS,\n" + 
		 		"                        ewaline.id as T_EWA_LINE_ID,ewaline.APPROVED_UNITS as T_EWA_LINE_APPROVED_CAPACITY,SHARED_PERCENTAGE\n" + 
		 		"		 				 from T_ES_INTENT_LINE esintentline\n" + 
		 		"		 				 left join M_COMPANY_SERVICE companyservice on esintentline.BUYER_COMP_SERV_ID = companyservice.ID\n" + 
		 		"		 				 left join M_COMPANY company on companyservice.M_COMPANY_ID  = company.ID\n" + 
		 		"		 				 left join m_org org on companyservice.M_ORG_ID = org.ID\n" + 
		 		"		 				 left join t_noc noc on esintentline.T_NOC_ID = noc.id \n" + 
		 		"                         left join t_consent consent on esintentline.T_CONSENT_ID = consent.id \n" + 
		 		"		 				 left join T_STANDING_CLEARENCE standingclearence on esintentline.T_STANDING_CLEARENCE_ID = standingclearence.id \n" + 
		 		"		 				 left join v_codes buyervoltage on consent.DRAWAL_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE'\n" + 
		 		"		 		         left join v_codes sellervoltage on consent.INJECTION_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'\n" + 
		 		"		 				 left join T_EPA epa on esintentline.T_EPA_ID = epa.id    \n" + 
		 		"		 				 left join t_oaa oaa on esintentline.T_OA_AGMT_ID = oaa.id\n" + 
		 		"		 		         left join T_INPRINCIPLE_APPLN ipaa on esintentline.T_EST_INTENT_ID = ipaa.T_ES_INTENT_ID\n" + 
		 		"		 		         left join T_INPRINCIPLE_APPLN_LINE ipaaline on ipaa.id = ipaaline.T_INPRINCIPLE_APPLN_ID and ipaaline.BUYER_COMP_SERV_ID = esintentline.BUYER_COMP_SERV_ID \n" + 
		 		"		 		         left join T_NOC_GENERATOR nocgen on esintentline.T_EST_INTENT_ID = nocgen.T_ES_INTENT_ID\n" + 
		 		"		 		         left join T_NOC_GENERATOR_LINE nocgenline on nocgen.id = nocgenline.T_NOC_GENERATOR_ID and nocgenline.BUYER_COMP_SERV_ID = esintentline.BUYER_COMP_SERV_ID\n" + 
		 		"                         left join T_EWA ewa on esintentline.T_EST_INTENT_ID = ewa.T_ES_INTENT_ID   \n" + 
		 		"                         left join T_EWA_LINE ewaline on ewa.id = ewaline.T_EWA_ID and ewaline.BUYER_COMP_SERV_ID = esintentline.BUYER_COMP_SERV_ID    where esintentline.T_EST_INTENT_ID=?";
		 energySaleIntentLines = jdbcOperations.query(sql1,new Object[]{id}, new EnergySaleIntentLineMapper());
		 energySaleIntent.setEnergySaleIntentLines(energySaleIntentLines);
		return energySaleIntent;
	}

	@Override
	public Response addEnergySaleIntent(EnergySaleIntent energySaleIntent) {
		Response result= new Response();
		try {
			energySaleIntent.setId(generateId());
			if(energySaleIntent.getCode()== null || energySaleIntent.getCode().isEmpty()){
				energySaleIntent.setCode(generateCode(EnergySaleIntent.class.getSimpleName(),""));
			}
			String sql = "insert into T_ES_INTENT(CODE,SELLER_COMP_SERV_ID,AGMT_PERIOD_CODE,FROM_DT,TO_DT,IS_CAPTIVE,STATUS_CODE,FROM_MONTH,FROM_YEAR,TO_MONTH,TO_YEAR,T_EWA_ID,FLOW_TYPE_CODE,T_INPRINCIPLE_APPLN_ID,T_NOC_GENERATOR_ID,PROPOSED_CAPACITY,SLDC_AWAITED_DT,ID)values(?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?)";
			if(jdbcOperations.update(sql,setEnergySaleIntentValues(energySaleIntent))>0) {
				result.setResult("Success");
				result.setMessage("Energy Sale Intent "+energySaleIntent.getCode()+"Created Successfully");
				result.setId(energySaleIntent.getId());
				result.setCode(energySaleIntent.getCode());
				Map<String,String> response = new HashMap<String,String>();
				response.put("id", energySaleIntent.getId());
				response.put("code",energySaleIntent.getCode());
			
			}else {
				result.setResult("Failure");
			}
		}catch(Exception e) {
			result.setResult("Failure");
			e.printStackTrace();
		}
		
//		return result;
		return result;
	}

	@Override
	public String updateEnergySaleIntent(String id ,EnergySaleIntent energySaleIntent) {
		String result="";
		try {
			energySaleIntent.setId(id);
			String sql = "update T_ES_INTENT set CODE=?, SELLER_COMP_SERV_ID=?,AGMT_PERIOD_CODE=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),IS_CAPTIVE=?,STATUS_CODE=?,FROM_MONTH=?,FROM_YEAR=?,TO_MONTH=?,TO_YEAR=?,T_EWA_ID=?,FLOW_TYPE_CODE=?,T_INPRINCIPLE_APPLN_ID=?,T_NOC_GENERATOR_ID=?,PROPOSED_CAPACITY=?,SLDC_AWAITED_DT=TO_DATE(?,'yyyy-mm-dd') where id=?";
			if(jdbcOperations.update(sql,setEnergySaleIntentValues(energySaleIntent))>0) {
				result = energySaleIntent.getId();
			}else {
				result = "Failure";
			}
		}catch(Exception e) {
			result = "Failure";
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String addEnergySaleIntentLine(EnergySaleIntentLine energySaleIntentLine) {
		String result="";
		try {
			energySaleIntentLine.setId(generateId());
			String sql = "insert into T_ES_INTENT_LINE(T_EST_INTENT_ID,BUYER_COMP_SERV_ID,STATUS_CODE,T_NOC_ID,\n" + 
					"T_CONSENT_ID,T_EPA_ID,T_OA_AGMT_ID,PROPOSED_QUANTUM,M_TRADE_RELATIONSHIP_ID,IS_CAPTIVE,T_STANDING_CLEARENCE_ID,SHARED_PERCENTAGE,ID)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEnergySaleIntentLineValues(energySaleIntentLine))>0) {
				result = energySaleIntentLine.getId();
			}else {
				result = "Failure";
			}
		}catch(Exception e) {
			result = "Failure";
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String updateEnergySaleIntentLine(EnergySaleIntentLine energySaleIntentLine) {
		String result="";
		try {
		
			String sql = "update T_ES_INTENT_LINE set T_EST_INTENT_ID=?,BUYER_COMP_SERV_ID=?,STATUS_CODE=?,T_NOC_ID=?,\n" + 
					"T_CONSENT_ID=?,T_EPA_ID=?,T_OA_AGMT_ID=?,PROPOSED_QUANTUM=?,M_TRADE_RELATIONSHIP_ID=?,IS_CAPTIVE=?,T_STANDING_CLEARENCE_ID=?,SHARED_PERCENTAGE=? where id=?";
			if(jdbcOperations.update(sql,setEnergySaleIntentLineValues(energySaleIntentLine))>0) {
				result = energySaleIntentLine.getId();
			}else {
				result = "Failure";
			}
		}catch(Exception e) {
			result = "Failure";
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Object[] setEnergySaleIntentValues(EnergySaleIntent energySaleIntent) {
		return new Object[] {
			energySaleIntent.getCode(),
			energySaleIntent.getSellerCompanyServiceId(),
			energySaleIntent.getAgmtPeriodCode(),
			energySaleIntent.getFromDate(),
			energySaleIntent.getToDate(),
			energySaleIntent.getIsCaptive(),
			energySaleIntent.getStatusCode(),
			energySaleIntent.getFromMonth(),
			energySaleIntent.getFromYear(),
			energySaleIntent.getToMonth(),
			energySaleIntent.getToYear(),
			energySaleIntent.getEwaId(),
			energySaleIntent.getFlowTypeCode(),
			energySaleIntent.getIpaaId(),
			energySaleIntent.getNocGeneratorId(),
			energySaleIntent.getProposedCapacity(),
			energySaleIntent.getSldcAwaitedDt(),
			energySaleIntent.getId()
		};
	}
	
	private Object[] setEnergySaleIntentLineValues(EnergySaleIntentLine energySaleIntentLine) {
		return new Object[] {			
			energySaleIntentLine.getEnergySaleIntentId(),
			energySaleIntentLine.getBuyerCompanyServiceId(),
			energySaleIntentLine.getStatusCode(),
			energySaleIntentLine.getNocId(),
			energySaleIntentLine.getConsentId(),
			energySaleIntentLine.getEpaId(),
			energySaleIntentLine.getOaAgmtId(),
			energySaleIntentLine.getProposedQuantum(),
			energySaleIntentLine.getTradeRelationshipId(),
			energySaleIntentLine.getIsCaptive(),
			energySaleIntentLine.getStandingClearenceId(),
			energySaleIntentLine.getSharedPercentage(),
			energySaleIntentLine.getId()
		};
	}
	
	final class EnergySaleIntentMapper implements RowMapper<EnergySaleIntent>{
		
		public  EnergySaleIntentMapper() {
			super();
		}

		@Override
		public EnergySaleIntent mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySaleIntent energySaleIntent = new EnergySaleIntent();
			energySaleIntent.setId(resultSet.getString("ID"));
			energySaleIntent.setCode(resultSet.getString("CODE"));
			energySaleIntent.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			energySaleIntent.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			energySaleIntent.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
			energySaleIntent.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
			energySaleIntent.setSellerCompanyCode(resultSet.getString("SELLER_COMP_CODE"));
			energySaleIntent.setSellerEndOrgId(resultSet.getString("SELLER_END_ORG_ID"));
			energySaleIntent.setSellerEndOrgName(resultSet.getString("SELLER_END_ORG_NAME"));
			energySaleIntent.setSellerEndOrgCode(resultSet.getString("SELLER_END_ORG_CODE"));
			energySaleIntent.setAgmtPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
			energySaleIntent.setFromDate(resultSet.getString("FROM_DT"));
			energySaleIntent.setToDate(resultSet.getString("TO_DT"));
			energySaleIntent.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			energySaleIntent.setStatusCode(resultSet.getString("STATUS_CODE"));
			energySaleIntent.setFromMonth(resultSet.getString("FROM_MONTH"));
			energySaleIntent.setFromYear(resultSet.getString("FROM_YEAR"));
			energySaleIntent.setToMonth(resultSet.getString("TO_MONTH"));
			energySaleIntent.setToYear(resultSet.getString("TO_YEAR"));
			energySaleIntent.setEwaId(resultSet.getString("T_EWA_ID"));
			energySaleIntent.setEwaStatusCode(resultSet.getString("EWA_STATUS_CODE"));
			energySaleIntent.setEwaTotalApprovedCapacity(resultSet.getString("EWA_TOAL_APPROVED_UNITS"));
			energySaleIntent.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			energySaleIntent.setSellerSubstationid(resultSet.getString("SELLER_SUBSTATION_ID"));
			energySaleIntent.setSellerSubstationCode(resultSet.getString("SELLER_SUBSTATION_CODE"));
			energySaleIntent.setSellerSubstationName(resultSet.getString("SELLER_SUBSTATION_NAME"));
			energySaleIntent.setSellerFeederId(resultSet.getString("SELLER_FEEDER_ID"));
			energySaleIntent.setSellerFeederCode(resultSet.getString("SELLER_FEEDER_CODE"));
			energySaleIntent.setSellerFeederName(resultSet.getString("SELLER_FEEDER_NAME"));
			energySaleIntent.setSellerInjectingVoltageCode(resultSet.getString("SELLER_VOLTAGE_CODE"));
			energySaleIntent.setSellerInjectingVoltageName(resultSet.getString("SELLER_VOLTAGE_NAME"));
			energySaleIntent.setIpaaId(resultSet.getString("T_INPRINCIPLE_APPLN_ID"));
			energySaleIntent.setIpaaStatusCode(resultSet.getString("IPAA_STATUS_CODE"));
			energySaleIntent.setNocGeneratorId(resultSet.getString("T_NOC_GENERATOR_ID"));
			energySaleIntent.setNocGeneratorStatusCode(resultSet.getString("T_NOC_GENERATOR_STATUS_CODE"));
			energySaleIntent.setProposedCapacity(resultSet.getString("PROPOSED_CAPACITY"));
			energySaleIntent.setSldcAwaitedDt(resultSet.getString("SLDC_AWAITED_DT"));
			energySaleIntent.setSldcApprovalDt(resultSet.getString("SLDC_APPROVAL_DT"));
			energySaleIntent.setSldcRejectedDt(resultSet.getString("SLDC_REJECTED_DT"));
			energySaleIntent.setSldcApproved(resultSet.getString("SLDC_APPROVED"));
			energySaleIntent.setSldcRemarks(resultSet.getString("SLDC_REMARKS"));
		
			return energySaleIntent;
		}
		
	}
	
	final class EnergySaleIntentLineMapper implements RowMapper<EnergySaleIntentLine>{

		public EnergySaleIntentLineMapper() {
			super();
		}
		@Override
		public EnergySaleIntentLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
			EnergySaleIntentLine energySaleIntentLine = new EnergySaleIntentLine();
			energySaleIntentLine.setId(resultSet.getString("ID"));
			energySaleIntentLine.setEnergySaleIntentId(resultSet.getString("T_EST_INTENT_ID"));
			energySaleIntentLine.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			energySaleIntentLine.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			energySaleIntentLine.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			energySaleIntentLine.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			energySaleIntentLine.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			energySaleIntentLine.setBuyerEndOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			energySaleIntentLine.setBuyerEndOrgName(resultSet.getString("BUYER_END_ORG_NAME"));
			energySaleIntentLine.setBuyerEndOrgCode(resultSet.getString("BUYER_END_ORG_CODE"));
			energySaleIntentLine.setStatusCode(resultSet.getString("STATUS_CODE"));
			energySaleIntentLine.setNocId(resultSet.getString("T_NOC_ID"));
			energySaleIntentLine.setConsentId(resultSet.getString("T_CONSENT_ID"));
			energySaleIntentLine.setEpaId(resultSet.getString("T_EPA_ID"));
			energySaleIntentLine.setOaAgmtId(resultSet.getString("T_OA_AGMT_ID"));			
			energySaleIntentLine.setProposedQuantum(resultSet.getString("PROPOSED_QUANTUM"));
			energySaleIntentLine.setNocStatusCode(resultSet.getString("NOC_STATUS_CODE"));
			energySaleIntentLine.setConsentStatusCode(resultSet.getString("CONSENT_STATUS_CODE"));
			energySaleIntentLine.setOaAgmtStatusCode(resultSet.getString("OA_AGMT_STATUS_CODE"));
			energySaleIntentLine.setEpaStatusCode(resultSet.getString("EPA_STATUS_CODE"));
			energySaleIntentLine.setTradeRelationshipId(resultSet.getString("M_TRADE_RELATIONSHIP_ID"));
			energySaleIntentLine.setNocApprovedCapacity(resultSet.getString("NOC_APPROVED_CAPACITY"));
			energySaleIntentLine.setConsentApprovedCapacity(resultSet.getString("CONSENT_APPROVED_CAPACITY"));
			energySaleIntentLine.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			energySaleIntentLine.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			energySaleIntentLine.setDrawalVoltageName(resultSet.getString("DRAWAL_VOLTAGE_DESC"));
			energySaleIntentLine.setInjectingVoltageCode(resultSet.getString("INJECTION_VOLTAGE_CODE"));
			energySaleIntentLine.setInjectingVoltageName(resultSet.getString("INJECTION_VOLTAGE_NAME"));
			energySaleIntentLine.setStandingClearenceId(resultSet.getString("T_STANDING_CLEARENCE_ID"));
			energySaleIntentLine.setStandingClearenceStatusCode(resultSet.getString("T_STAND_CLEAR_STATUS_CODE"));
			energySaleIntentLine.setIpaaLineId(resultSet.getString("IPAA_LINE_ID"));
			energySaleIntentLine.setIpaaLineApprovedCapacity(resultSet.getString("IPAA_LINE_APPROVED_UNITS"));
			energySaleIntentLine.setNocGeneratorLineId(resultSet.getString("NOC_GENERATOR_ID"));
			energySaleIntentLine.setNocGeneratorLineApprovedCapacity(resultSet.getString("NOC_GENERATOR_APPROVED_UNITS"));
			energySaleIntentLine.setEwaLineId(resultSet.getString("T_EWA_LINE_ID"));
			energySaleIntentLine.setEwaLineApprovedCapacity(resultSet.getString("T_EWA_LINE_APPROVED_CAPACITY"));
			energySaleIntentLine.setSharedPercentage(resultSet.getString("SHARED_PERCENTAGE"));

			return energySaleIntentLine;
		}
	}
	

	
	@Override
	public String updateApprovalEnergySaleIntent(String id ,EnergySaleIntent energySaleIntent) {
		String result="";
		try {
			energySaleIntent.setId(id);
			String sql = "update T_ES_INTENT set SLDC_AWAITED_DT=TO_DATE(?,'yyyy-mm-dd'),SLDC_APPROVAL_DT=TO_DATE(?,'yyyy-mm-dd')"
					+ ",SLDC_REJECTED_DT=TO_DATE(?,'yyyy-mm-dd'),SLDC_APPROVED=?,SLDC_REMARKS=?,STATUS_CODE=? where id=?";
			if(jdbcOperations.update(sql,setApprovalEnergySaleIntentValues(energySaleIntent))>0) {
				result = energySaleIntent.getId();
			}else {
				result = "Failure";
			}
		}catch(Exception e) {
			result = "Failure";
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	private Object[] setApprovalEnergySaleIntentValues(EnergySaleIntent energySaleIntent) {
		return new Object[] {
	
			energySaleIntent.getSldcAwaitedDt(),
			energySaleIntent.getSldcApprovalDt(),
			energySaleIntent.getSldcRejectedDt(),
			energySaleIntent.getSldcApproved(),
			energySaleIntent.getSldcRemarks(),
			energySaleIntent.getStatusCode(),
			energySaleIntent.getId()
		};
	}
	
		

}
