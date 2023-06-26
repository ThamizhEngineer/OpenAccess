package com.ss.oa.transaction.standingclearence;

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
import com.ss.oa.transaction.vo.StandingClearence;

@Scope("prototype")
@Component
public class StandingClearenceDaoImpl extends BaseDaoJdbc implements StandingClearenceDao{
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<StandingClearence> getAllStandingClearences(Map<String, String> criteria) {
		String sql = "SELECT standingclearence.ID,standingclearence.SLDC_NAME,standingclearence.CODE,standingclearence.T_ES_INTENT_ID,esintent.code as T_ES_INTENT_CODE,standingclearence.M_ORG_ID,org.NAME as M_ORG_NAME,org.CODE as M_ORG_CODE,org.TYPE_CODE AS REGION,standingclearence.M_SUBSTATION_ID,substation.NAME as M_SUBSTATION_NAME,substation.CODE as M_SUBSTATION_CODE,\n" + 
				"				standingclearence.M_FEEDER_ID,feeder.NAME as M_FEEDER_NAME , feeder.CODE as M_FEEDER_CODE,standingclearence.MAX_DRAWAL_CEILING,\n" + 
				"                standingclearence.BUYER_COMP_SERV_ID,companyservice.\"number\" as BUYER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID,companyservice.M_COMPANY_CODE,companyservice.M_COMPANY_NAME,\n" + 
				"                standingclearence.STATUS_CODE,standingclearence.CREATED_BY,standingclearence.CREATED_DATE, standingclearence.FROM_DATE,standingclearence.FROM_MONTH,standingclearence.FROM_YEAR,standingclearence.MODIFIED_BY,standingclearence.MODIFIED_DATE,standingclearence.TO_DATE,standingclearence.TO_MONTH,standingclearence.TO_YEAR,\n" + 
				"                standingclearence.ENABLED,standingclearence.APPROVAL_DATE,standingclearence.SCHEDULING_CHARGES,standingclearence.SCHEDULING_CHARGES_PERCENT,standingclearence.STATE_TRANS_CHARGES,standingclearence.STATE_TRANS_CHARGES_PERCENT,standingclearence.STATE_TRANS_LOSS,standingclearence.STATE_TRANS_LOSS_PERCENT,standingclearence.FLOW_TYPE_CODE\n" + 
				"                FROM T_STANDING_CLEARENCE standingclearence\n" + 
				"				left join m_org org on standingclearence.M_ORG_ID =org.id\n" + 
				"				left join m_substation substation on standingclearence.M_SUBSTATION_ID =substation.id\n" + 
				"				left join m_feeder feeder on standingclearence.M_FEEDER_ID =feeder.id\n" + 
				"                left join v_company_service companyservice on standingclearence.BUYER_COMP_SERV_ID = companyservice.id\n" + 
				"				left join t_es_intent esintent on  standingclearence.T_ES_INTENT_ID =esintent.id Where 1=1 ";
		if(criteria.get("buyer-comp-serv-id")!=null) {
			sql += "and upper(standingclearence.buyerCompServId) like upper('"+criteria.get("buyer-comp-serv-id")+"')";
		}
		if(criteria.get("sldc-name")!=null) {
			sql += "and upper(standingclearence.REGION)= upper('"+criteria.get("sldc-name")+"')";
		}
		if(criteria.get("code")!=null) {
			sql += "and upper(standingclearence.CODE) like upper('%"+criteria.get("code")+"%')";
		}
		if(criteria.get("es-intent-code")!=null) {
			sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
		}
		
		System.out.println(sql);
		return jdbcOperations.query(sql, new StandingClearenceMapper());
	}

	@Override
	public StandingClearence getStandingClearenceById(String id) {
		StandingClearence standingClearence =  new StandingClearence();
		
		String sql= " SELECT standingclearence.ID,standingclearence.SLDC_NAME,standingclearence.CODE,standingclearence.T_ES_INTENT_ID,esintent.code as T_ES_INTENT_CODE,standingclearence.M_ORG_ID,org.NAME as M_ORG_NAME,org.CODE as M_ORG_CODE,org.TYPE_CODE AS REGION,standingclearence.M_SUBSTATION_ID,substation.NAME as M_SUBSTATION_NAME,substation.CODE as M_SUBSTATION_CODE,\n" + 
				"				standingclearence.M_FEEDER_ID,feeder.NAME as M_FEEDER_NAME , feeder.CODE as M_FEEDER_CODE,standingclearence.MAX_DRAWAL_CEILING,\n" + 
				"                standingclearence.BUYER_COMP_SERV_ID,companyservice.\"number\" as BUYER_COMP_SERV_NUMBER,companyservice.M_COMPANY_ID,companyservice.M_COMPANY_CODE,companyservice.M_COMPANY_NAME,\n" + 
				"                standingclearence.STATUS_CODE,standingclearence.CREATED_BY,standingclearence.CREATED_DATE, standingclearence.FROM_DATE,standingclearence.FROM_MONTH,standingclearence.FROM_YEAR,standingclearence.MODIFIED_BY,standingclearence.MODIFIED_DATE,standingclearence.TO_DATE,standingclearence.TO_MONTH,standingclearence.TO_YEAR,\n" + 
				"                standingclearence.ENABLED,standingclearence.APPROVAL_DATE,standingclearence.SCHEDULING_CHARGES,standingclearence.SCHEDULING_CHARGES_PERCENT,standingclearence.STATE_TRANS_CHARGES,standingclearence.STATE_TRANS_CHARGES_PERCENT,standingclearence.STATE_TRANS_LOSS,standingclearence.STATE_TRANS_LOSS_PERCENT,standingclearence.FLOW_TYPE_CODE\n" + 
				"                FROM T_STANDING_CLEARENCE standingclearence\n" + 
				"				left join m_org org on standingclearence.M_ORG_ID =org.id\n" + 
				"				left join m_substation substation on standingclearence.M_SUBSTATION_ID =substation.id\n" + 
				"				left join m_feeder feeder on standingclearence.M_FEEDER_ID =feeder.id\n" + 
				"                left join v_company_service companyservice on standingclearence.BUYER_COMP_SERV_ID = companyservice.id\n" + 
				"				left join t_es_intent esintent on  standingclearence.T_ES_INTENT_ID =esintent.id  where standingclearence.ID=?\n"  ;
	
		System.out.println(sql);
		standingClearence = jdbcOperations.queryForObject(sql,new Object[]{id}, new StandingClearenceMapper());
		return standingClearence ;
	}

	@Override
	public String addStandingClearence(StandingClearence standingClearence) {
		String result="";
		try {
			standingClearence.setId(generateId());
			if(standingClearence.getCode()== null || standingClearence.getCode().isEmpty()){
				standingClearence.setCode(generateCode(StandingClearence.class.getSimpleName(),""));
			}
			String sql = "INSERT INTO T_STANDING_CLEARENCE(SLDC_NAME,REGION,CODE,T_ES_INTENT_ID,M_ORG_ID,M_SUBSTATION_ID,M_FEEDER_ID,MAX_DRAWAL_CEILING,BUYER_COMP_SERV_ID,STATUS_CODE,CREATED_BY,\n" + 
					"			      MODIFIED_BY, FROM_DATE,FROM_MONTH,FROM_YEAR,MODIFIED_DATE,TO_DATE,TO_MONTH,TO_YEAR,APPROVAL_DATE,SCHEDULING_CHARGES,SCHEDULING_CHARGES_PERCENT,STATE_TRANS_CHARGES,STATE_TRANS_CHARGES_PERCENT,\n" + 
					"			       STATE_TRANS_LOSS,STATE_TRANS_LOSS_PERCENT,SYSTEM_OPR_CHARGES,SYSTEM_OPR_CHARGES_PERCENT,FLOW_TYPE_CODE,ID)  VALUES \n" + 
					"			     (?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,\n" + 
					"			           ?,?,?,?,?,?)";
		if(jdbcOperations.update(sql,setStandingClearenceValues(standingClearence))>0) {
				result=standingClearence.getId();
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
	public String updateStandingClearence(String id, StandingClearence standingClearence) {
		String result="";
		try {
			standingClearence.setId(id);
			String sql = "	UPDATE T_STANDING_CLEARENCE SET SLDC_NAME=?,REGION=?,CODE=?,T_ES_INTENT_ID=?,M_ORG_ID=?,M_SUBSTATION_ID=?,M_FEEDER_ID=?,MAX_DRAWAL_CEILING=?,BUYER_COMP_SERV_ID=?,STATUS_CODE=?,CREATED_BY=?, \n" + 
					"						      MODIFIED_BY=?, FROM_DATE=TO_DATE(?,'yyyy-mm-dd'),FROM_MONTH=?,FROM_YEAR=?,MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'),TO_DATE=TO_DATE(?,'yyyy-mm-dd'),TO_MONTH=?,TO_YEAR=?,APPROVAL_DATE=TO_DATE(?,'yyyy-mm-dd'),SCHEDULING_CHARGES=?,SCHEDULING_CHARGES_PERCENT=?,STATE_TRANS_CHARGES=?,STATE_TRANS_CHARGES_PERCENT=?, \n" + 
					"					       STATE_TRANS_LOSS=?,STATE_TRANS_LOSS_PERCENT=?,SYSTEM_OPR_CHARGES=?,SYSTEM_OPR_CHARGES_PERCENT=?,FLOW_TYPE_CODE=? WHERE ID=?";
			if(jdbcOperations.update(sql,setStandingClearenceValues(standingClearence))>0) {
				result=standingClearence.getId();
			}else {
				result="Failure";
			}
			
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
			
		}
		return result;
	}
	
	final class StandingClearenceMapper implements RowMapper<StandingClearence>{

		public StandingClearenceMapper() {
			super();
			
		}

		@Override
		public StandingClearence mapRow(ResultSet resultSet,int rowNum) throws SQLException {
			
			StandingClearence standingClearence = new StandingClearence();
			standingClearence.setId(resultSet.getString("ID"));
			standingClearence.setSldcName(resultSet.getString("SLDC_NAME"));
			standingClearence.setCode(resultSet.getString("CODE"));
			standingClearence.setEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			standingClearence.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));
			standingClearence.setOrgId(resultSet.getString("M_ORG_ID"));
			standingClearence.setOrgCode(resultSet.getString("M_ORG_CODE"));
			standingClearence.setOrgName(resultSet.getString("M_ORG_NAME"));
			standingClearence.setRegion(resultSet.getString("REGION"));
			standingClearence.setSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			standingClearence.setSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			standingClearence.setSubstationCode(resultSet.getString("M_SUBSTATION_CODE"));
			standingClearence.setFeederId(resultSet.getString("M_FEEDER_ID"));
			standingClearence.setFeederName(resultSet.getString("M_FEEDER_NAME"));
			standingClearence.setFeederCode(resultSet.getString("M_FEEDER_CODE"));
			standingClearence.setMaxDrawalCeiling(resultSet.getString("MAX_DRAWAL_CEILING"));
			standingClearence.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			standingClearence.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			standingClearence.setBuyerCompanyId(resultSet.getString("M_COMPANY_ID"));
			standingClearence.setBuyerCompanyCode(resultSet.getString("M_COMPANY_CODE"));
			standingClearence.setBuyerCompanyName(resultSet.getString("M_COMPANY_NAME"));
			standingClearence.setStatusCode(resultSet.getString("STATUS_CODE") );
			standingClearence.setCreatedBy(resultSet.getString("CREATED_BY"));
			standingClearence.setCreatedDate(resultSet.getString("CREATED_DATE"));
			standingClearence.setFromDate(resultSet.getString("FROM_DATE"));
			standingClearence.setFromMonth(resultSet.getString("FROM_MONTH"));
			standingClearence.setFromYear(resultSet.getString("FROM_YEAR"));
			standingClearence.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			standingClearence.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
			standingClearence.setToDate(resultSet.getString("TO_DATE"));
			standingClearence.setToMonth(resultSet.getString("TO_MONTH"));
			standingClearence.setToYear(resultSet.getString("TO_YEAR"));
			standingClearence.setEnabled(resultSet.getString("ENABLED"));
			standingClearence.setApprovalDate(resultSet.getString("APPROVAL_DATE"));
			standingClearence.setSchedulingCharges(resultSet.getString("SCHEDULING_CHARGES"));
			standingClearence.setSchedulingChargesPercent(resultSet.getString("SCHEDULING_CHARGES_PERCENT"));
			standingClearence.setStateTransCharges(resultSet.getString("STATE_TRANS_CHARGES"));
			standingClearence.setStateTransChargesPercent(resultSet.getString("STATE_TRANS_CHARGES_PERCENT"));
			standingClearence.setStateTransLoss(resultSet.getString("STATE_TRANS_LOSS"));
			standingClearence.setStateTransLossPercent(resultSet.getString("STATE_TRANS_LOSS_PERCENT"));
			standingClearence.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			return standingClearence;
		}

	}

	
	private Object[] setStandingClearenceValues(StandingClearence standingClearence) {
		return new Object[] {
				
				standingClearence.getSldcName(),
				standingClearence.getRegion(),
				standingClearence.getCode(),
				standingClearence.getEsIntentId(),
				standingClearence.getOrgId(),
				standingClearence.getSubstationId(),
				standingClearence.getFeederId(),
				standingClearence.getMaxDrawalCeiling(),
				standingClearence.getBuyerCompanyServiceId(),
				standingClearence.getStatusCode(),
				standingClearence.getCreatedBy(),
				standingClearence.getModifiedBy(),
				standingClearence.getFromDate(),
				standingClearence.getFromMonth(),
				standingClearence.getFromYear(),
				standingClearence.getModifiedDate(),
				standingClearence.getToDate(),
				standingClearence.getToMonth(),
				standingClearence.getToYear(),
				standingClearence.getApprovalDate(),
				standingClearence.getSchedulingCharges(),
				standingClearence.getSchedulingChargesPercent(),
				standingClearence.getStateTransCharges(),
				standingClearence.getStateTransChargesPercent(),
				standingClearence.getStateTransLoss(),
				standingClearence.getStateTransLossPercent(),
				standingClearence.getSystemOprCharges(),
				standingClearence.getSystemoprChargesPercent(),
				standingClearence.getFlowTypeCode(),
				standingClearence.getId()
		      };
				
	      }		
		

	
}

