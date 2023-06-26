package com.ss.oa.transaction.oaa;


import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.transaction.vo.Oaa;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class OaaDaoImpl extends BaseDaoJdbc implements OaaDao{


	@Resource
	private JdbcOperations jdbcOperations;	
	
	@Override
	public List<Oaa> getAllOaas(Map<String, String> criteria){
		
		String sql= "SELECT oaa.ID,oaa.BUYER_END_ORG_ID,buyerorg.NAME AS BUYER_END_ORG_NAME,oaa.BUYER_END_UTILITY,oaa.BUYER_COMP_SERV_ID,\n" + 
				"								vcompanyservice.\"number\" AS BUYER_COM_SERV_NUMBER,vcompanyservice.M_COMPANY_NAME AS BUYER_COMP_NAME,vcompanyservice.M_COMPANY_ID AS BUYER_COMP_ID, \n" + 
				"								oaa.DRAWAL_TRANS_SS_ID,buyerss.NAME AS DRAWL_TRANS_SS_NAME,oaa.DRAWAL_TRANS_VOLTAGE_CODE,dtransvcode.VALUE_DESC AS DRAWL_TRANS_VOLT_NAME,\n" + 
				"								vcompanyservice.m_feeder_id as DRAWAL_TRANS_FEEDER_ID,vcompanyservice.m_feeder_name  AS DRAWAL_TRANS_FEEDER_NAME,vcompanyservice.m_substation_id as DRAWAL_DIST_SS_ID,vcompanyservice.m_substation_name AS DRAWAL_DIST_SS_NAME,vcompanyservice.voltage_code as DRAWAL_DIST_VOLTAGE_CODE,vcompanyservice.voltage_name AS DRAWAL_DIST_VOLTAGE_DESC,oaa.AGMT_PERIOD_CODE,to_char(oaa.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT,to_char(oaa.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(oaa.TO_DT,'YYYY-MM-DD')TO_DT,oaa.C1_UNITS,\n" + 
				"								oaa.C2_UNITS,oaa.C3_UNITS,oaa.C4_UNITS,oaa.C5_UNITS,oaa.PROPOSED_TOTAL_UNITS,oaa.APPROVED_TOTAL_UNITS, \n" + 
				"								oaa.PAYMENT_TYPE_CODE,oaa.PAYMENT_BANK_DETAILS,oaa.PAYMENT_TXN_NO,oaa.PAYMENT_DATE,oaa.PAYMENT_AMOUNT,oaa.SELLER_IS_CAPTIVE, \n" + 
				"								oaa.SELLER_END_ORG_ID,sellerorg.NAME AS SELLER_ORG_NAME,oaa.SELLER_END_UTILITY,oaa.SELLER_COMP_SERV_ID,\n" + 
				"								sellercompanyservice.\"number\" AS SELLER_COM_SERV_NUMBER,sellercompanyservice.M_COMPANY_NAME AS SELLER_COMP_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMP_ID,sellercompanyservice.CAPACITY as SELLER_CAPACITY,\n" + 
				"								oaa.INJECTION_TRANS_SS_ID,injss.NAME AS INJECTION_TRANS_SS_NAME,oaa.INJECTION_TRANS_VOLTAGE_CODE,itransvcode.VALUE_DESC AS INJECTION_TRANS_VOLTAGE_DESC,sellercompanyservice.VOLTAGE_NAME AS INJECTION_TRANS_VOLT_NAME,\n" + 
				"								sellercompanyservice.m_feeder_id as INJECTION_TRANS_FEEDER_ID,sellercompanyservice.m_feeder_name AS INJECTION_TRANS_FEEDER_NAME,sellercompanyservice.m_substation_id as INJECTION_DIST_SS_ID,sellercompanyservice.m_substation_name AS INJECTION_DIST_SS_NAME,sellercompanyservice.voltage_code as INJECTION_DIST_VOLTAGE_CODE,sellercompanyservice.voltage_name as  INJECTION_DIST_VOLTAGE_DESC,to_char(oaa.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(oaa.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,oaa.STATUS_CODE,oaa.CODE,\n" + 
				"								oaa.OAA_APPROVAL_NUMBER,oaa.REMARKS,oaa.CREATED_BY,to_char(oaa.CREATED_DT,'YYYY-MM-DD')CREATED_DT,oaa.MODIFIED_BY,to_char(oaa.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,oaa.T_ES_INTENT_ID,oaa.LICENSEE,oaa.FLOW_TYPE_CODE,oaa.PEAK_UNITS,oaa.OFF_PEAK_UNITS,oaa.INTERVAL_TYPE_CODE ,oaa.SHARE_PERCENT,esintent.code as T_ES_INTENT_CODE,oaa.CODE as OAA_CODE\n" + 
				"								FROM T_OAA oaa   \n" + 
				"									LEFT JOIN V_COMPANY_SERVICE vcompanyservice ON oaa.BUYER_COMP_SERV_ID = vcompanyservice.ID \n" + 
				"									LEFT JOIN T_NOC noc ON oaa.T_ES_INTENT_ID = noc.T_ES_INTENT_ID AND oaa.BUYER_COMP_SERV_ID = noc.BUYER_COMP_SERV_ID\n" + 
				"									left join T_ES_INTENT esintent on oaa.T_ES_INTENT_ID = esintent.ID   \n" + 
				"									left join v_codes buyervoltage on oaa.DRAWAL_DIST_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE' \n" + 
				"									left join v_codes sellervoltage on oaa.INJECTION_DIST_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'\n" + 
				"									LEFT JOIN  V_COMPANY_SERVICE sellercompanyservice ON oaa.SELLER_COMP_SERV_ID = sellercompanyservice.ID \n" + 
				"									LEFT JOIN M_ORG buyerorg ON oaa.BUYER_END_ORG_ID = buyerorg.ID \n" + 
				"									LEFT JOIN M_ORG sellerorg ON oaa.SELLER_END_ORG_ID = sellerorg.ID\n" + 
				"									LEFT JOIN M_SUBSTATION buyerss ON oaa.DRAWAL_TRANS_SS_ID = buyerss.ID\n" + 
				"									LEFT JOIN M_SUBSTATION sellerss ON oaa.DRAWAL_DIST_SS_ID = sellerss.ID\n" + 
				"								    LEFT JOIN M_SUBSTATION injdiss ON oaa.INJECTION_DIST_SS_ID = injdiss.ID\n" + 
				"									LEFT JOIN M_SUBSTATION injss ON oaa.INJECTION_TRANS_SS_ID = injss.ID\n" + 
				"									LEFT JOIN M_FEEDER drfeeder ON oaa.DRAWAL_TRANS_FEEDER_ID = drfeeder.ID\n" + 
				"									LEFT JOIN M_FEEDER infeeder ON oaa.INJECTION_TRANS_FEEDER_ID = infeeder.ID\n" + 
				"									left join v_codes dtransvcode on oaa.DRAWAL_TRANS_VOLTAGE_CODE = dtransvcode.Value_Code  AND  dtransvcode.list_code = 'VOLTAGE_CODE' \n" + 
				"									left join v_codes itransvcode on oaa.INJECTION_TRANS_VOLTAGE_CODE = itransvcode.Value_Code  AND  itransvcode.list_code = 'VOLTAGE_CODE' WHERE 1=1";
		if(criteria.get("buyer-org-id")!=null) {
			sql += "and upper(oaa.BUYER_END_ORG_ID)= upper('"+criteria.get("buyer-org-id")+"')";
		}
		
		if(criteria.get("buyer-company-service-number")!=null) {
			sql += "and upper(vcompanyservice.\"number\" )= upper('"+criteria.get("buyer-company-service-number")+"')";
		}
		
		if(criteria.get("buyer-company-service-id")!=null) {
			sql += "and upper(oaa.BUYER_COMP_SERV_ID )= upper('"+criteria.get("buyer-company-service-id")+"')";
		}
		
	
		if(criteria.get("buyer-company-id")!=null) {
			sql += "and upper(vcompanyservice.M_COMPANY_ID)= upper('"+criteria.get("buyer-company-id")+"')";
		}
		

		if(criteria.get("buyer-company-name")!=null) {
			sql += "and upper(vcompanyservice.BUYER_COMP_NAME ) like upper('%"+criteria.get("buyer-company-name")+"%')";
		}	
		
		if(criteria.get("seller-org-id")!=null) {
			sql += "and upper(oaa.SELLER_END_ORG_ID)= upper('"+criteria.get("seller-org-id")+"')";
		}
		
		if(criteria.get("seller-company-name")!=null) {
			sql += "and upper(sellercompanyservice.M_COMPANY_NAME) like upper('%"+criteria.get("seller-company-name")+"%')";
		}
		
		if(criteria.get("seller-company-service-id")!=null) {
			sql += "and upper(oaa.SELLER_COMP_SERV_ID)= upper('"+criteria.get("seller-company-service-id")+"')";
		}
		
		if(criteria.get("seller-company-id")!=null) {
			sql += "and upper(sellercompanyservice.M_COMPANY_ID)= upper('"+criteria.get("seller-company-id")+"')";
		}
		
		
		if(criteria.get("status")!=null) {
			sql += "and upper(oaa.STATUS_CODE)= upper('"+criteria.get("status")+"')";
		}
		
		if(criteria.get("from-date")!=null) {
			sql += "and upper(oaa.FROM_DT)= upper('"+criteria.get("from-date")+"')";
		}
		
		if(criteria.get("to-date")!=null) {
			sql += "and upper(oaa.TO_DT)= upper('"+criteria.get("to-date")+"')";
		}
		if(criteria.get("code")!=null) {
			sql += "and upper(oaa.CODE) like upper('%"+criteria.get("code")+"%')";
		}
		if(criteria.get("es-intent-code")!=null) {
			sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
		}
		sql += " order by oaa.CREATED_DT desc";

		System.out.println(sql);
		return (ArrayList<Oaa>) jdbcOperations.query(sql,new OaaMapper());
}
	@Override
	public Oaa getOaaById(String id) {
		
		
		Oaa oaa = new Oaa();
		
		String sql = "SELECT oaa.ID,oaa.BUYER_END_ORG_ID,buyerorg.NAME AS BUYER_END_ORG_NAME,oaa.BUYER_END_UTILITY,oaa.BUYER_COMP_SERV_ID,\n" + 
				"								vcompanyservice.\"number\" AS BUYER_COM_SERV_NUMBER,vcompanyservice.M_COMPANY_NAME AS BUYER_COMP_NAME,vcompanyservice.M_COMPANY_ID AS BUYER_COMP_ID, \n" + 
				"								oaa.DRAWAL_TRANS_SS_ID,buyerss.NAME AS DRAWL_TRANS_SS_NAME,oaa.DRAWAL_TRANS_VOLTAGE_CODE,dtransvcode.VALUE_DESC AS DRAWL_TRANS_VOLT_NAME,\n" + 
				"								vcompanyservice.m_feeder_id as DRAWAL_TRANS_FEEDER_ID,vcompanyservice.m_feeder_name  AS DRAWAL_TRANS_FEEDER_NAME,vcompanyservice.m_substation_id as DRAWAL_DIST_SS_ID,vcompanyservice.m_substation_name AS DRAWAL_DIST_SS_NAME,vcompanyservice.voltage_code as DRAWAL_DIST_VOLTAGE_CODE,vcompanyservice.voltage_name AS DRAWAL_DIST_VOLTAGE_DESC,oaa.AGMT_PERIOD_CODE,to_char(oaa.AGREEMENT_DT,'YYYY-MM-DD')AGREEMENT_DT,to_char(oaa.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(oaa.TO_DT,'YYYY-MM-DD')TO_DT,oaa.C1_UNITS,\n" + 
				"								oaa.C2_UNITS,oaa.C3_UNITS,oaa.C4_UNITS,oaa.C5_UNITS,oaa.PROPOSED_TOTAL_UNITS,oaa.APPROVED_TOTAL_UNITS, \n" + 
				"								oaa.PAYMENT_TYPE_CODE,oaa.PAYMENT_BANK_DETAILS,oaa.PAYMENT_TXN_NO,oaa.PAYMENT_DATE,oaa.PAYMENT_AMOUNT,oaa.SELLER_IS_CAPTIVE, \n" + 
				"								oaa.SELLER_END_ORG_ID,sellerorg.NAME AS SELLER_ORG_NAME,oaa.SELLER_END_UTILITY,oaa.SELLER_COMP_SERV_ID,\n" + 
				"								sellercompanyservice.\"number\" AS SELLER_COM_SERV_NUMBER,sellercompanyservice.M_COMPANY_NAME AS SELLER_COMP_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMP_ID,sellercompanyservice.CAPACITY as SELLER_CAPACITY,\n" + 
				"								oaa.INJECTION_TRANS_SS_ID,injss.NAME AS INJECTION_TRANS_SS_NAME,oaa.INJECTION_TRANS_VOLTAGE_CODE,itransvcode.VALUE_DESC AS INJECTION_TRANS_VOLTAGE_DESC,sellercompanyservice.VOLTAGE_NAME AS INJECTION_TRANS_VOLT_NAME,\n" + 
				"								sellercompanyservice.m_feeder_id as INJECTION_TRANS_FEEDER_ID,sellercompanyservice.m_feeder_name AS INJECTION_TRANS_FEEDER_NAME,sellercompanyservice.m_substation_id as INJECTION_DIST_SS_ID,sellercompanyservice.m_substation_name AS INJECTION_DIST_SS_NAME,sellercompanyservice.voltage_code as INJECTION_DIST_VOLTAGE_CODE,sellercompanyservice.voltage_name as  INJECTION_DIST_VOLTAGE_DESC,to_char(oaa.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(oaa.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,oaa.STATUS_CODE,oaa.CODE,\n" + 
				"								oaa.OAA_APPROVAL_NUMBER,oaa.REMARKS,oaa.CREATED_BY,to_char(oaa.CREATED_DT,'YYYY-MM-DD')CREATED_DT,oaa.MODIFIED_BY,to_char(oaa.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,oaa.T_ES_INTENT_ID,oaa.LICENSEE,oaa.FLOW_TYPE_CODE,oaa.PEAK_UNITS,oaa.OFF_PEAK_UNITS,oaa.INTERVAL_TYPE_CODE ,oaa.SHARE_PERCENT,esintent.code as T_ES_INTENT_CODE,oaa.CODE as OAA_CODE\n" + 
				"								FROM T_OAA oaa    \n" + 
				"									LEFT JOIN V_COMPANY_SERVICE vcompanyservice ON oaa.BUYER_COMP_SERV_ID = vcompanyservice.ID \n" + 
				"									LEFT JOIN T_NOC noc ON oaa.T_ES_INTENT_ID = noc.T_ES_INTENT_ID AND oaa.BUYER_COMP_SERV_ID = noc.BUYER_COMP_SERV_ID\n" + 
				"									left join T_ES_INTENT esintent on oaa.T_ES_INTENT_ID = esintent.ID   \n" + 
				"									left join v_codes buyervoltage on oaa.DRAWAL_DIST_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE' \n" + 
				"									left join v_codes sellervoltage on oaa.INJECTION_DIST_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'\n" + 
				"									LEFT JOIN  V_COMPANY_SERVICE sellercompanyservice ON oaa.SELLER_COMP_SERV_ID = sellercompanyservice.ID \n" + 
				"									LEFT JOIN M_ORG buyerorg ON oaa.BUYER_END_ORG_ID = buyerorg.ID \n" + 
				"									LEFT JOIN M_ORG sellerorg ON oaa.SELLER_END_ORG_ID = sellerorg.ID\n" + 
				"									LEFT JOIN M_SUBSTATION buyerss ON oaa.DRAWAL_TRANS_SS_ID = buyerss.ID\n" + 
				"									LEFT JOIN M_SUBSTATION sellerss ON oaa.DRAWAL_DIST_SS_ID = sellerss.ID\n" + 
				"								    LEFT JOIN M_SUBSTATION injdiss ON oaa.INJECTION_DIST_SS_ID = injdiss.ID\n" + 
				"									LEFT JOIN M_SUBSTATION injss ON oaa.INJECTION_TRANS_SS_ID = injss.ID\n" + 
				"									LEFT JOIN M_FEEDER drfeeder ON oaa.DRAWAL_TRANS_FEEDER_ID = drfeeder.ID\n" + 
				"									LEFT JOIN M_FEEDER infeeder ON oaa.INJECTION_TRANS_FEEDER_ID = infeeder.ID\n" + 
				"									left join v_codes dtransvcode on oaa.DRAWAL_TRANS_VOLTAGE_CODE = dtransvcode.Value_Code  AND  dtransvcode.list_code = 'VOLTAGE_CODE' \n" + 
				"									left join v_codes itransvcode on oaa.INJECTION_TRANS_VOLTAGE_CODE = itransvcode.Value_Code  AND  itransvcode.list_code = 'VOLTAGE_CODE'   WHERE oaa.ID=?";
				
		oaa = jdbcOperations.queryForObject(sql,new Object[]{id},new OaaMapper());
		
		return oaa;
	}
	
	@Override
	public String addOaa(Oaa oaa) {
		String result="";
		
		try {
			oaa.setId(generateId());
			if(oaa.getCode()== null || oaa.getCode().isEmpty()){
				oaa.setCode(generateCode(Oaa.class.getSimpleName(),""));
			}
			String sql ="INSERT INTO T_OAA(BUYER_COMP_SERV_ID,BUYER_END_ORG_ID,BUYER_END_UTILITY,DRAWAL_TRANS_SS_ID,DRAWAL_TRANS_VOLTAGE_CODE,DRAWAL_TRANS_FEEDER_ID,DRAWAL_DIST_SS_ID,DRAWAL_DIST_VOLTAGE_CODE,\n" + 
					"AGMT_PERIOD_CODE,AGREEMENT_DT,FROM_DT,TO_DT,PROPOSED_TOTAL_UNITS,APPROVED_TOTAL_UNITS,SELLER_COMP_SERV_ID,SELLER_END_ORG_ID,SELLER_END_UTILITY,INJECTION_TRANS_SS_ID,INJECTION_TRANS_VOLTAGE_CODE,\n" + 
					"INJECTION_TRANS_FEEDER_ID,INJECTION_DIST_SS_ID,INJECTION_DIST_VOLTAGE_CODE,PAYMENT_TYPE_CODE,PAYMENT_BANK_DETAILS,PAYMENT_TXN_NO,PAYMENT_DATE,PAYMENT_AMOUNT,\n" + 
					"LICENSEE,C1_UNITS,C2_UNITS,C3_UNITS,C4_UNITS,C5_UNITS,APPLIED_DT,APPROVED_DT,STATUS_CODE,CODE,OAA_APPROVAL_NUMBER,REMARKS,CREATED_BY,CREATED_DATE,\n" + 
					"MODIFIED_BY,MODIFIED_DT,T_ES_INTENT_ID,SELLER_IS_CAPTIVE,FLOW_TYPE_CODE,PEAK_UNITS,OFF_PEAK_UNITS,INTERVAL_TYPE_CODE,SHARE_PERCENT,ID)\n" + 
					"values(?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?)";

			if(jdbcOperations.update(sql,setOaaValues(oaa))>0) {
				result = oaa.getId();
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
	public String updateOaa(String id, Oaa oaa) {
		String result="";
		
		try {

			String sql ="UPDATE T_OAA set BUYER_COMP_SERV_ID=?,BUYER_END_ORG_ID=?,BUYER_END_UTILITY=?,DRAWAL_TRANS_SS_ID=?,DRAWAL_TRANS_VOLTAGE_CODE=?,DRAWAL_TRANS_FEEDER_ID=?,DRAWAL_DIST_SS_ID=?,DRAWAL_DIST_VOLTAGE_CODE=?,\n" + 
					"AGMT_PERIOD_CODE=?,AGREEMENT_DT= TO_DATE(?,'YYYY-MM-DD'),FROM_DT= TO_DATE(?,'YYYY-MM-DD'),TO_DT=TO_DATE(?,'YYYY-MM-DD'),PROPOSED_TOTAL_UNITS=?,APPROVED_TOTAL_UNITS=?,SELLER_COMP_SERV_ID=?,SELLER_END_ORG_ID=?,SELLER_END_UTILITY=?,INJECTION_TRANS_SS_ID=?,INJECTION_TRANS_VOLTAGE_CODE=?,\n" + 
					"INJECTION_TRANS_FEEDER_ID=?,INJECTION_DIST_SS_ID=?,INJECTION_DIST_VOLTAGE_CODE=?,PAYMENT_TYPE_CODE=?,PAYMENT_BANK_DETAILS=?,PAYMENT_TXN_NO=?,PAYMENT_DATE=?,PAYMENT_AMOUNT=?,\n" + 
					"LICENSEE=?,C1_UNITS=?,C2_UNITS=?,C3_UNITS=?,C4_UNITS=?,C5_UNITS=?,APPLIED_DT= TO_DATE(?,'YYYY-MM-DD'),APPROVED_DT= TO_DATE(?,'YYYY-MM-DD'),STATUS_CODE=?,CODE=?,OAA_APPROVAL_NUMBER=?,REMARKS=?,CREATED_BY=?,CREATED_DATE= TO_DATE(?,'YYYY-MM-DD'),\n" + 
					"MODIFIED_BY=?,MODIFIED_DT= TO_DATE(?,'YYYY-MM-DD'),T_ES_INTENT_ID=?,SELLER_IS_CAPTIVE=?,FLOW_TYPE_CODE=?,PEAK_UNITS=?,OFF_PEAK_UNITS=?,INTERVAL_TYPE_CODE=?,SHARE_PERCENT=? where ID=?";

			if(jdbcOperations.update(sql,setOaaValues(oaa)) > 0){
				result =oaa.getId();
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
	
	private Object[] setOaaValues(Oaa oaa){
		return new Object[]{
				
            
				oaa.getBuyerCompServiceId(),
				oaa.getBuyerOrgId(),
				oaa.getBuyerEndUtility(),
				oaa.getDrawalTransSubstationId(),
				oaa.getDrawalTransVoltageCode(),
				oaa.getDrawalFeederId(),
				oaa.getDrawalDistSubstationId(),
				oaa.getDrawalDistVoltageCode(),
				oaa.getAgreementPeriodCode(),
				oaa.getAgreementDate(),
				oaa.getFromDate(),
				oaa.getToDate(),
				
				oaa.getProposedTotalUnits(),
				oaa.getApprovedTotalUnits(),
				
				oaa.getSellerCompServiceId(),
				oaa.getSellerOrgId(),
				oaa.getSellerEndUtility(),
				oaa.getInjectionTransSubstationId(),
				oaa.getInjectionTransVoltageCode(),
				oaa.getInjectionFeederId(),
				oaa.getInjectionDistSubstationId(),
				oaa.getInjectionDistVoltageCode(),
				
				oaa.getPaymentTypeCode(),
				oaa.getPaymentBankDetails(),
				oaa.getPaymentTxnNo(),
				oaa.getPaymentDate(),
				oaa.getPaymentAmount(),
				
				oaa.getLicensee(),
				oaa.getC1Units(),
				oaa.getC2Units(),
				oaa.getC3Units(),
				oaa.getC4Units(),
				oaa.getC5Units(),
				oaa.getAppliedDate(),
				oaa.getApprovedDate(),
				oaa.getStatusCode(),
				oaa.getCode(),
				oaa.getOaaAppNumber(),
				oaa.getRemarks(),
				oaa.getCreatedBy(),
				oaa.getCreatedDate(),
				oaa.getModifiedBy(),
				oaa.getModifiedDate(),
				oaa.gettEsIntentId(),
				oaa.getSellerIsCaptive(),
				oaa.getFlowTypeCode(),
				oaa.getPeakUnits(),
				oaa.getOffPeakUnits(),
				oaa.getIntervalTypeCode(),
				oaa.getSharePercent(),
				oaa.getId()

				
				};
	}
	

    public class OaaMapper implements RowMapper<Oaa>{

public OaaMapper() {
super();
}

@Override
public Oaa mapRow(ResultSet resultSet, int rownum) throws SQLException {

Oaa oaa = new Oaa();

oaa.setId(resultSet.getString("ID"));

oaa.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
oaa.setBuyerOrgId(resultSet.getString("BUYER_END_ORG_ID"));
oaa.setBuyerOrgName(resultSet.getString("BUYER_END_ORG_NAME"));
oaa.setBuyerEndUtility(resultSet.getString("BUYER_END_UTILITY"));
oaa.setDrawalTransSubstationId(resultSet.getString("DRAWAL_TRANS_SS_ID"));
oaa.setDrawalTransVoltageCode(resultSet.getString("DRAWAL_TRANS_VOLTAGE_CODE"));
oaa.setDrawalFeederId(resultSet.getString("DRAWAL_TRANS_FEEDER_ID"));
oaa.setDrawalDistSubstationId(resultSet.getString("DRAWAL_DIST_SS_ID"));
oaa.setDrawalDistVoltageCode(resultSet.getString("DRAWAL_DIST_VOLTAGE_CODE"));
oaa.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
oaa.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
oaa.setFromDate(resultSet.getString("FROM_DT"));
oaa.setToDate(resultSet.getString("TO_DT"));

oaa.setBuyerCompServiceNumber(resultSet.getString("BUYER_COM_SERV_NUMBER"));
oaa.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
oaa.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
oaa.setDrawalTransSubstationName(resultSet.getString("DRAWL_TRANS_SS_NAME"));

oaa.setDrawalTransVoltageDesc(resultSet.getString("DRAWL_TRANS_VOLT_NAME"));
oaa.setSellerCompServiceNumber(resultSet.getString("SELLER_COM_SERV_NUMBER"));

oaa.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
oaa.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
oaa.setInjectionTransSubstationName(resultSet.getString("INJECTION_TRANS_SS_NAME"));
oaa.setInjectionTransVoltageDesc(resultSet.getString("INJECTION_TRANS_VOLT_NAME"));
oaa.setSellerCapacity(resultSet.getString("SELLER_CAPACITY"));
oaa.setProposedTotalUnits(resultSet.getString("PROPOSED_TOTAL_UNITS"));
oaa.setApprovedTotalUnits(resultSet.getString("APPROVED_TOTAL_UNITS"));


oaa.setSellerCompServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
oaa.setSellerOrgId(resultSet.getString("SELLER_END_ORG_ID"));
oaa.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
oaa.setSellerEndUtility(resultSet.getString("SELLER_END_UTILITY"));
oaa.setInjectionTransSubstationId(resultSet.getString("INJECTION_TRANS_SS_ID"));
oaa.setInjectionTransVoltageCode(resultSet.getString("INJECTION_TRANS_VOLTAGE_CODE"));
oaa.setInjectionFeederId(resultSet.getString("INJECTION_TRANS_FEEDER_ID"));
oaa.setInjectionDistSubstationId(resultSet.getString("INJECTION_DIST_SS_ID"));
oaa.setInjectionDistVoltageCode(resultSet.getString("INJECTION_DIST_VOLTAGE_CODE"));

oaa.setPaymentTypeCode(resultSet.getString("PAYMENT_TYPE_CODE"));
oaa.setPaymentBankDetails(resultSet.getString("PAYMENT_BANK_DETAILS"));
oaa.setPaymentTxnNo(resultSet.getString("PAYMENT_TXN_NO"));
oaa.setPaymentDate(resultSet.getString("PAYMENT_DATE"));
oaa.setPaymentAmount(resultSet.getString("PAYMENT_AMOUNT"));
oaa.setSellerIsCaptive(resultSet.getNString("SELLER_IS_CAPTIVE"));



oaa.setLicensee(resultSet.getString("LICENSEE"));
oaa.setC1Units(resultSet.getString("C1_UNITS"));
oaa.setC2Units(resultSet.getString("C2_UNITS"));
oaa.setC3Units(resultSet.getString("C3_UNITS"));
oaa.setC4Units(resultSet.getString("C4_UNITS"));
oaa.setC5Units(resultSet.getString("C5_UNITS"));
oaa.setAppliedDate(resultSet.getString("APPLIED_DT"));
oaa.setApprovedDate(resultSet.getString("APPROVED_DT"));
oaa.setStatusCode(resultSet.getString("STATUS_CODE"));

oaa.setCode(resultSet.getString("CODE"));
oaa.setOaaAppNumber(resultSet.getString("OAA_APPROVAL_NUMBER"));
oaa.setRemarks(resultSet.getString("REMARKS"));
oaa.setCreatedBy(resultSet.getString("CREATED_BY"));
oaa.setCreatedDate(resultSet.getString("CREATED_DT"));
oaa.setModifiedBy(resultSet.getString("MODIFIED_BY"));
oaa.setModifiedDate(resultSet.getString("MODIFIED_DT"));
oaa.settEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
oaa.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));

oaa.setDrawalDistSubstationName(resultSet.getString("DRAWAL_DIST_SS_NAME"));
oaa.setDrawalDistVoltageDesc(resultSet.getString("DRAWAL_DIST_VOLTAGE_DESC"));
oaa.setInjectionDistSubstationName(resultSet.getString("INJECTION_DIST_SS_NAME"));
oaa.setInjectionDistVoltageDesc(resultSet.getString("INJECTION_DIST_VOLTAGE_DESC"));
oaa.setInjectionFeederName(resultSet.getString("INJECTION_TRANS_FEEDER_NAME"));
oaa.setDrawalFeederName(resultSet.getString("DRAWAL_TRANS_FEEDER_NAME"));
oaa.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
oaa.setPeakUnits(resultSet.getString("PEAK_UNITS"));
oaa.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));
oaa.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
oaa.setSharePercent(resultSet.getString("SHARE_PERCENT"));
oaa.setCode(resultSet.getString("OAA_CODE"));
return oaa;
}

}
	
}
