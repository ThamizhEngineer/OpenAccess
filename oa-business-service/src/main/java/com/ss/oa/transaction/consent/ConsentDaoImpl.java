package com.ss.oa.transaction.consent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import com.ss.oa.common.BaseDaoJdbc;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.Consent;

@Scope("prototype")
@Component
public class ConsentDaoImpl extends BaseDaoJdbc implements ConsentDao{
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Consent> getAllConsents(Map<String, String> criteria){

		
		String sql = "SELECT consent.ID,consent.CODE,consent.BUYER_END_ORG_ID,vcompanyservice.M_ORG_NAME AS BUYER_ORG_NAME,consent.BUYER_COMP_SERV_ID,  \n" + 
				"				  vcompanyservice.\"number\" AS BUYER_SERVICE_NUMBER,vcompanyservice.M_COMPANY_NAME AS BUYER_COMPANY_NAME, \n" + 
				"				  vcompanyservice.M_COMPANY_ID AS BUYER_COMPANY_ID,consent.DRAWAL_SUBSTATION_ID, injdiss.NAME AS BUYER_SUBSTATION_NAME, \n" + 
				"				  consent.DRAWAL_FEEDER_ID,drfeeder.NAME AS DRAWAL_FEEDER_NAME,consent.DRAWAL_VOLTAGE_CODE,buyervoltage.VALUE_DESC,consent.IS_CAPTIVE,consent.AGMT_PERIOD_CODE,to_char(consent.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT, \n" + 
				"			      to_char(consent.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(consent.TO_DT,'YYYY-MM-DD')TO_DT,consent.T_NOC_ID,consent.PROPOSED_CAPACITY,consent.APPROVED_CAPACITY,consent.IS_ABT_INSTALLED, \n" + 
				"				  consent.NO_ABT_REASON,consent.HAS_REAL_TIME_CON,consent.EXEMPT_RC,consent.HAS_DUES,consent.DUE_DETAILS,consent.PENDING_CASE_DETAILS,consent.TECHNICAL_FEASIBILITY_DETAILS,\n" + 
				"			      consent.SELLER_END_ORG_ID,sellercompanyservice.M_ORG_NAME AS SELLER_ORG_NAME,  \n" + 
				"				  consent.SELLER_COMP_SERV_ID,sellercompanyservice.\"number\"  AS SELLER_SERVICE_NUMBER,  \n" + 
				"				  sellercompanyservice.M_COMPANY_NAME AS SELLER_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMPANY_ID, \n" + 
				"				  consent.INJECTION_SUBSTATION_ID,injss.NAME AS INJECTION_SUBSTATION_NAME,sellervoltage.VALUE_DESC AS INJECTION_VOLTAGE_NAME,consent.INJECTION_FEEDER_ID,infeeder.NAME AS injection_feeder_name,consent.INJECTION_VOLTAGE_CODE, \n" + 
				"				  to_char(consent.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(consent.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,consent.STATUS_CODE,consent.REMARKS,consent.CREATED_BY,to_char(consent.CREATED_DT,'YYYY-MM-DD')CREATED_DT,   \n" + 
				"				  consent.MODIFIED_BY,to_char(consent.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,consent.T_ES_INTENT_ID,esintent.code as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,consent.FLOW_TYPE_CODE \n" + 
				"				   FROM T_CONSENT consent    \n" + 
				"				 				         left join  V_COMPANY_SERVICE vcompanyservice on consent.BUYER_COMP_SERV_ID = vcompanyservice.id      \n" + 
				"				 				         left join T_NOC noc on consent.T_ES_INTENT_ID = noc.T_ES_INTENT_ID AND consent.BUYER_COMP_SERV_ID = noc.BUYER_COMP_SERV_ID      \n" + 
				"				 				         left join T_ES_INTENT esintent on consent.T_ES_INTENT_ID = esintent.ID     \n" + 
				"			 				             left join v_codes buyervoltage on consent.DRAWAL_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE'   \n" + 
				"				 				         left join v_codes sellervoltage on consent.INJECTION_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'   \n" + 
				"				 				         left join  V_COMPANY_SERVICE sellercompanyservice on esintent.SELLER_COMP_SERV_ID = sellercompanyservice.id   \n" + 
				"				 				         LEFT JOIN M_FEEDER drfeeder ON consent.DRAWAL_FEEDER_ID = drfeeder.ID \n" + 
				"				 						 LEFT JOIN M_FEEDER infeeder ON consent.INJECTION_FEEDER_ID = infeeder.ID \n" + 
				"				 						 LEFT JOIN M_SUBSTATION injdiss ON consent.DRAWAL_SUBSTATION_ID = injdiss.ID  \n" + 
				"				 					     LEFT JOIN M_SUBSTATION injss ON consent.INJECTION_SUBSTATION_ID = injss.ID  where 1=1"; 
				
		if(criteria.get("buyer-org-id")!=null) {
			sql += "and upper(consent.BUYER_END_ORG_ID)= upper('"+criteria.get("buyer-org-id")+"')";
		}
		
		if(criteria.get("buyer-company-service-number")!=null) {
			sql += "and upper(vcompanyservice.\"number\" )= upper('"+criteria.get("buyer-company-service-number")+"')";
		}
		
		if(criteria.get("buyer-company-service-id")!=null) {
			sql += "and upper(consent.BUYER_COMP_SERV_ID )= upper('"+criteria.get("buyer-company-service-id")+"')";
		}
		
		
		if(criteria.get("buyer-company-id")!=null) {
			sql += "and upper(vcompanyservice.M_COMPANY_ID)= upper('"+criteria.get("buyer-company-id")+"')";
		}
		

		if(criteria.get("buyer-company-name")!=null) {
			sql += "and upper(vcompanyservice.M_COMPANY_NAME ) like upper('%"+criteria.get("buyer-company-name")+"%')";
		}	
		
		
		if(criteria.get("status")!=null) {
			sql += "and upper(consent.STATUS_CODE)= upper('"+criteria.get("status")+"')";
		}
		
		if(criteria.get("from-date")!=null) {
			sql += "and upper(consent.FROM_DT)= upper('"+criteria.get("from-date")+"')";
		}
		
		if(criteria.get("to-date")!=null) {
			sql += "and upper(consent.TO_DT)= upper('"+criteria.get("to-date")+"')";
		}
		if(criteria.get("drawal-voltage-code")!=null) {
			sql += "and upper(consent.DRAWAL_VOLTAGE_CODE)= upper('"+criteria.get("drawal-voltage-code")+"')";
		}
		if(criteria.get("seller-company-service-number")!=null) {
			sql += "and upper(sellercompanyservice.\"number\" )= upper('"+criteria.get("seller-company-service-number")+"')";
		}	
		
		if(criteria.get("seller-company-id")!=null) {
			sql += "and upper(sellercompanyservice.M_COMPANY_ID)= upper('"+criteria.get("seller-company-id")+"')";
		}
		
		if(criteria.get("seller-company-name")!=null) {
			sql += "and upper(sellercompanyservice.M_COMPANY_NAME )like upper('%"+criteria.get("seller-company-name")+"%')";
		}
		if(criteria.get("es-intent-code")!=null) {
			sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
		}	
		if(criteria.get("seller-company-service-id")!=null) {
			sql += "and upper(consent.SELLER_COMP_SERV_ID )= upper('"+criteria.get("seller-company-service-id")+"')";
		}
		if(criteria.get("seller-edc-id")!=null) {
			sql += "and upper(consent.SELLER_END_ORG_ID)= upper('"+criteria.get("seller-edc-id")+"')";
		}
		if(criteria.get("consent-code")!=null) {
			sql += "and upper(consent.code) like upper('%"+criteria.get("consent-code")+"%')";
		}
		
	
		
	return (ArrayList<Consent>) jdbcOperations.query(sql,new ConsentMapper());
	}

	@Override
	public Consent getConsentById(String id) {
		
		Consent consent = new Consent();
		
		String sql = "SELECT consent.ID,consent.CODE,consent.BUYER_END_ORG_ID,vcompanyservice.M_ORG_NAME AS BUYER_ORG_NAME,consent.BUYER_COMP_SERV_ID,  \n" + 
				"				  vcompanyservice.\"number\" AS BUYER_SERVICE_NUMBER,vcompanyservice.M_COMPANY_NAME AS BUYER_COMPANY_NAME, \n" + 
				"				  vcompanyservice.M_COMPANY_ID AS BUYER_COMPANY_ID,consent.DRAWAL_SUBSTATION_ID, injdiss.NAME AS BUYER_SUBSTATION_NAME, \n" + 
				"				  consent.DRAWAL_FEEDER_ID,drfeeder.NAME AS DRAWAL_FEEDER_NAME,consent.DRAWAL_VOLTAGE_CODE,buyervoltage.VALUE_DESC,consent.IS_CAPTIVE,consent.AGMT_PERIOD_CODE,to_char(consent.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT, \n" + 
				"			      to_char(consent.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(consent.TO_DT,'YYYY-MM-DD')TO_DT,consent.T_NOC_ID,consent.PROPOSED_CAPACITY,consent.APPROVED_CAPACITY,consent.IS_ABT_INSTALLED, \n" + 
				"				  consent.NO_ABT_REASON,consent.HAS_REAL_TIME_CON,consent.EXEMPT_RC,consent.HAS_DUES,consent.DUE_DETAILS,consent.PENDING_CASE_DETAILS,consent.TECHNICAL_FEASIBILITY_DETAILS,\n" + 
				"			      consent.SELLER_END_ORG_ID,sellercompanyservice.M_ORG_NAME AS SELLER_ORG_NAME,  \n" + 
				"				  consent.SELLER_COMP_SERV_ID,sellercompanyservice.\"number\"  AS SELLER_SERVICE_NUMBER,  \n" + 
				"				  sellercompanyservice.M_COMPANY_NAME AS SELLER_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMPANY_ID, \n" + 
				"				  consent.INJECTION_SUBSTATION_ID,injss.NAME AS INJECTION_SUBSTATION_NAME,sellervoltage.VALUE_DESC AS INJECTION_VOLTAGE_NAME,consent.INJECTION_FEEDER_ID,infeeder.NAME AS injection_feeder_name,consent.INJECTION_VOLTAGE_CODE, \n" + 
				"				  to_char(consent.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(consent.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,consent.STATUS_CODE,consent.REMARKS,consent.CREATED_BY,to_char(consent.CREATED_DT,'YYYY-MM-DD')CREATED_DT,   \n" + 
				"				  consent.MODIFIED_BY,to_char(consent.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,consent.T_ES_INTENT_ID,esintent.code as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,consent.FLOW_TYPE_CODE \n" + 
				"				   FROM T_CONSENT consent    \n" + 
				"				 				         left join  V_COMPANY_SERVICE vcompanyservice on consent.BUYER_COMP_SERV_ID = vcompanyservice.id      \n" + 
				"				 				         left join T_NOC noc on consent.T_ES_INTENT_ID = noc.T_ES_INTENT_ID AND consent.BUYER_COMP_SERV_ID = noc.BUYER_COMP_SERV_ID      \n" + 
				"				 				         left join T_ES_INTENT esintent on consent.T_ES_INTENT_ID = esintent.ID     \n" + 
				"			 				             left join v_codes buyervoltage on consent.DRAWAL_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE'   \n" + 
				"				 				         left join v_codes sellervoltage on consent.INJECTION_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'   \n" + 
				"				 				         left join  V_COMPANY_SERVICE sellercompanyservice on esintent.SELLER_COMP_SERV_ID = sellercompanyservice.id   \n" + 
				"				 				         LEFT JOIN M_FEEDER drfeeder ON consent.DRAWAL_FEEDER_ID = drfeeder.ID \n" + 
				"				 						 LEFT JOIN M_FEEDER infeeder ON consent.INJECTION_FEEDER_ID = infeeder.ID \n" + 
				"				 						 LEFT JOIN M_SUBSTATION injdiss ON consent.DRAWAL_SUBSTATION_ID = injdiss.ID  \n" + 
				"				 					     LEFT JOIN M_SUBSTATION injss ON consent.INJECTION_SUBSTATION_ID = injss.ID where consent.ID=?";
				
		consent = jdbcOperations.queryForObject(sql,new Object[]{id},new ConsentMapper());
		
		return consent;
		
		
	}
	
	@Override
	public String addConsent(Consent consent) {
		String result="";
		
		try {
			consent.setId(generateId());
			if(consent.getCode()== null || consent.getCode().isEmpty()){
				consent.setCode(generateCode(Consent.class.getSimpleName(),""));
			}
			String sql = "  insert INTO T_CONSENT(CODE,BUYER_COMP_SERV_ID,BUYER_END_ORG_ID,DRAWAL_SUBSTATION_ID,DRAWAL_FEEDER_ID,DRAWAL_VOLTAGE_CODE,AGMT_PERIOD_CODE,AGREEMENT_DT,FROM_DT,TO_DT,T_NOC_ID, \n" + 
					"				                           PROPOSED_CAPACITY,APPROVED_CAPACITY,IS_ABT_INSTALLED,NO_ABT_REASON,HAS_REAL_TIME_CON,EXEMPT_RC,HAS_DUES,DUE_DETAILS,PENDING_CASE_DETAILS,TECHNICAL_FEASIBILITY_DETAILS,SELLER_COMP_SERV_ID,SELLER_END_ORG_ID,\n" + 
					"				                           INJECTION_SUBSTATION_ID,INJECTION_FEEDER_ID,INJECTION_VOLTAGE_CODE,APPLIED_DT,APPROVED_DT,STATUS_CODE,REMARKS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,\n" + 
					"				                           T_ES_INTENT_ID,IS_CAPTIVE,FLOW_TYPE_CODE,ID) VALUES\n" + 
					"				                           (?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,\n" + 
					"					                        ?,?,?,?,?,?,?,?,?,?,?,?,\n" + 
					"					                        ?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"					                        ?,?,?,?)	"; 
			
			if(jdbcOperations.update(sql,setConsentValues(consent))>0) {
				result = consent.getId();
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
	public String updateConsent(String id, Consent consent) {
		String result="";
		
		try {

			String sql = "UPDATE T_CONSENT SET CODE=?, BUYER_COMP_SERV_ID=?,BUYER_END_ORG_ID=?,DRAWAL_SUBSTATION_ID=?,DRAWAL_FEEDER_ID=?,\n" + 
					"DRAWAL_VOLTAGE_CODE=?,AGMT_PERIOD_CODE=?,AGREEMENT_DT=TO_DATE(?,'YYYY-MM-DD'),FROM_DT=TO_DATE(?,'YYYY-MM-DD'),TO_DT=TO_DATE(?,'YYYY-MM-DD'),T_NOC_ID=?,\n" + 
					"PROPOSED_CAPACITY=?,APPROVED_CAPACITY=?,IS_ABT_INSTALLED=?,NO_ABT_REASON=?,HAS_REAL_TIME_CON=?,EXEMPT_RC=?, HAS_DUES=?, DUE_DETAILS=?, PENDING_CASE_DETAILS=?, TECHNICAL_FEASIBILITY_DETAILS=?,\n" + 
					"SELLER_COMP_SERV_ID=?,SELLER_END_ORG_ID=?,INJECTION_SUBSTATION_ID=?,INJECTION_FEEDER_ID=?,INJECTION_VOLTAGE_CODE=?,APPLIED_DT=TO_DATE(?,'YYYY-MM-DD'),APPROVED_DT=TO_DATE(?,'YYYY-MM-DD'),\n" + 
					"STATUS_CODE=?,REMARKS=?,CREATED_BY=?,CREATED_DT=TO_DATE(?,'YYYY-MM-DD'),MODIFIED_BY=?,MODIFIED_DT=TO_DATE(?,'YYYY-MM-DD'),T_ES_INTENT_ID=?,IS_CAPTIVE=?,FLOW_TYPE_CODE=? WHERE ID=?";
					
			if(jdbcOperations.update(sql,setConsentValues(consent)) > 0){
				result =consent.getId();
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
	
	private Object[] setConsentValues(Consent consent){
		return new Object[]{
				consent.getCode(),
				consent.getBuyerCompServiceId(),
				consent.getBuyerOrgId(),
				consent.getDrawalSubstationId(),
				consent.getDrawalFeederId(),
				consent.getDrawalVoltageCode(),
				consent.getAgreementPeriodCode(),
				consent.getAgreementDate(),
				consent.getFromDate(),
				consent.getToDate(),
				consent.gettNocId(),
				consent.getProposedCapacity(),
				consent.getApprovedCapacity(),
				consent.getIsAbtInstalled(),
				consent.getNoAbtReason(),	
				consent.getHasRealTimeCon(),
				consent.getExemptRc(),
				consent.getHasDues(),
				consent.getDueDetails(),
				consent.getPendingCaseDetails(),
				consent.getTechnicalFeasibilityDetails(),
				consent.getSellerCompServiceId(),
				consent.getSellerOrgId(),
				consent.getInjectionSubstationId(),
				consent.getInjectionFeederId(),
				consent.getInjectionVoltageCode(),
				consent.getAppliedDate(),
				consent.getApprovedDate(),
				consent.getStatusCode(),
				consent.getRemarks(),
				consent.getCreatedBy(),
				consent.getCreatedDate(),
				consent.getModifiedBy(),
				consent.getModifiedDate(),
				consent.gettEsIntentId(),
				consent.getIsCaptive(),
				consent.getFlowTypeCode(),
				consent.getId()

				
				};
	}
	public class ConsentMapper implements RowMapper<Consent>{
		
		public ConsentMapper() {
			super();
		}
		
		@Override
		public Consent mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			Consent consent = new Consent();
			
			consent.setId(resultSet.getString("ID"));
			consent.setCode(resultSet.getString("CODE"));
			consent.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			consent.setBuyerOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			consent.setDrawalSubstationId(resultSet.getString("DRAWAL_SUBSTATION_ID"));
			consent.setDrawalFeederId(resultSet.getString("DRAWAL_FEEDER_ID"));
			consent.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			consent.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
			consent.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
			consent.setFromDate(resultSet.getString("FROM_DT"));
			consent.setToDate(resultSet.getString("TO_DT"));
			consent.settNocId(resultSet.getString("T_NOC_ID"));
			consent.setProposedCapacity(resultSet.getString("PROPOSED_CAPACITY"));
			consent.setApprovedCapacity(resultSet.getString("APPROVED_CAPACITY"));
			consent.setIsAbtInstalled(resultSet.getString("IS_ABT_INSTALLED"));
			consent.setNoAbtReason(resultSet.getString("NO_ABT_REASON"));
			consent.setHasRealTimeCon(resultSet.getString("HAS_REAL_TIME_CON"));
			consent.setExemptRc(resultSet.getString("EXEMPT_RC"));
			consent.setHasDues(resultSet.getString("HAS_DUES"));
			consent.setDueDetails(resultSet.getString("DUE_DETAILS"));
			consent.setPendingCaseDetails(resultSet.getString("PENDING_CASE_DETAILS"));
			consent.setTechnicalFeasibilityDetails(resultSet.getString("TECHNICAL_FEASIBILITY_DETAILS"));
			consent.setSellerOrgId(resultSet.getString("SELLER_END_ORG_ID"));
			consent.setSellerCompServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			consent.setInjectionSubstationId(resultSet.getString("INJECTION_SUBSTATION_ID"));
			consent.setInjectionFeederId(resultSet.getString("INJECTION_FEEDER_ID"));
			consent.setInjectionVoltageCode(resultSet.getString("INJECTION_VOLTAGE_CODE"));
			consent.setIsCaptive(resultSet.getString("IS_CAPTIVE"));

			consent.setAppliedDate(resultSet.getString("APPLIED_DT"));
			consent.setApprovedDate(resultSet.getString("APPROVED_DT"));
			consent.setStatusCode(resultSet.getString("STATUS_CODE"));
			
			consent.setRemarks(resultSet.getString("REMARKS"));
			consent.setCreatedBy(resultSet.getString("CREATED_BY"));
			consent.setCreatedDate(resultSet.getString("CREATED_DT"));
			consent.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			consent.setModifiedDate(resultSet.getString("MODIFIED_DT"));
			consent.settEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			
			consent.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			consent.setBuyerCompServiceNumber(resultSet.getString("BUYER_SERVICE_NUMBER"));
			consent.setBuyerCompanyName(resultSet.getString("BUYER_COMPANY_NAME"));
			consent.setBuyerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
			consent.setSellerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
			consent.setDrawalSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			consent.setDrawalFeederName(resultSet.getString("DRAWAL_FEEDER_NAME"));
			
			consent.setSellerCompanyName(resultSet.getString("SELLER_COMPANY_NAME"));
			consent.setSellerCompanyId(resultSet.getString("SELLER_COMPANY_ID"));
			consent.setSellerCompServiceNumber(resultSet.getString("SELLER_SERVICE_NUMBER"));
			consent.setInjectionVoltageDesc(resultSet.getString("INJECTION_VOLTAGE_NAME"));
			consent.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			consent.setInjectionFeederName(resultSet.getString("injection_feeder_name"));
			consent.setInjectionSubstationName(resultSet.getString("INJECTION_SUBSTATION_NAME"));
			consent.setDrawalVoltageDesc(resultSet.getString("VALUE_DESC"));
			consent.setEsIntentCode(resultSet.getString("ES_INTENT_CODE"));
			consent.setFromMonth(resultSet.getString("FROM_MONTH"));
			consent.setFromYear(resultSet.getString("FROM_YEAR"));
			consent.setToMonth(resultSet.getString("TO_MONTH"));
			consent.setToYear(resultSet.getString("TO_YEAR"));
			consent.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));


			return consent;
	}
	
}
}
	
	
	
	