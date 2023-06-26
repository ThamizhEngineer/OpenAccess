package com.ss.oa.transaction.agreement;

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
import com.ss.oa.transaction.vo.Agreement;
import com.ss.oa.transaction.vo.AgreementLine;

@Scope("prototype")
@Component
public class AgreementDaoImpl extends BaseDaoJdbc implements AgreementDao {
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Agreement> getAllAgreements(Map<String, String> criteria) {
		String sql="select agreement.ID,agreement.CODE,agreement.AGMT_VERSION,agreement.SELLER_COMP_SERV_ID,agreement.SELLER_COMPANY_ID,agreement.SELLER_EDC_ID,agreement.AGREEMENT_DATE,agreement.IS_ACTIVE,\n" + 
				"agreement.FROM_DATE,agreement.TO_DATE,agreement.TOTAL_UNITS,agreement.C1,agreement.C2,agreement.C3,agreement.C4,agreement.C5,agreement.PEAK_UNITS,agreement.OFF_PEAK_UNITS,\n" + 
				"agreement.SIGNATORY_PARTY1,agreement.SIGNATORY_PARTY2,agreement.SIGNATORY_PARTY3,agreement.HAS_LINES,agreement.ENABLED,agreement.FROM_MONTH,agreement.TO_YEAR,agreement.FROM_YEAR,agreement.TO_MONTH,agreement.BUYER_COMP_SERV_ID,\n" + 
				"agreement.VERSION_CHANGE_REASON_CODE,agreement.BUYER_EDC_ID,agreement.BUYER_COMP_ID,agreement.SUBSTATION_ID as SELLER_SUBSTATION_ID,agreement.FEEDER_ID as SELLER_FEEDER_ID,agreement.AGREEMENT_PERIOD_CODE,agreement.FLOW_TYPE,agreement.IS_LATEST,agreement.INTERVAL_TYPE_CODE,\n" + 
				"agreement.SHARE_PERCENTAGE,agreement.IS_CAPTIVE,agreement.STATUS_CODE,agreement.T_ES_INTENT_ID,agreement.T_EWA_ID,agreement.T_OAA_ID,agreement.T_NOC_GENERATOR_ID,agreement.T_INPRINCIPLE_APPLN_ID,agreement.T_EPA_ID,\n" + 
				"sellercompanyservice.\"number\"as SELLER_COMP_SERV_NUMBER,sellercompanyservice.M_COMPANY_ID as SELLER_COMP_ID,sellercompanyservice.M_COMPANY_NAME as SELLER_COMP_NAME,sellercompanyservice.M_COMPANY_CODE as SELLER_COMP_CODE,sellercompanyservice.M_ORG_CODE as SELLER_ORG_CODE,sellercompanyservice.M_ORG_NAME as SELLER_ORG_NAME,\n" + 
				"sellercompanyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE,sellercompanyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE ,sellercompanyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,\n" + 
				" buyercompanyservice.\"number\"as BUYER_COMP_SERV_NUMBER, buyercompanyservice.M_COMPANY_ID as BUYER_COMP_ID, buyercompanyservice.M_COMPANY_NAME as BUYER_COMP_NAME, buyercompanyservice.M_COMPANY_CODE as BUYER_COMP_CODE, buyercompanyservice.M_ORG_CODE as BUYER_ORG_CODE, buyercompanyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
				" buyercompanyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, buyercompanyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME, buyercompanyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , buyercompanyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,esintent.code as T_ES_INTENT_CODE\n" + 
				"from F_AGREEMENT agreement\n" + 
				"left join v_company_service sellercompanyservice on agreement.SELLER_COMP_SERV_ID = sellercompanyservice.id\n" + 
				"left join v_company_service buyercompanyservice on agreement.BUYER_COMP_SERV_ID = buyercompanyservice.id "	+ 
				"left join T_ES_INTENT esintent on agreement.T_ES_INTENT_ID = esintent.ID where 1=1";
	
		  
        if(!criteria.isEmpty())
		{
			if(criteria.get("seller-edc-id")!=null){
				sql += "and upper(agreement.SELLER_EDC_ID)= upper('"+criteria.get("seller-edc-id")+"')";
			}
			if(criteria.get("seller-company-service-id")!=null){
				sql += "and upper(agreement.SELLER_COMP_SERV_ID) = upper('"+criteria.get("seller-company-service-id")+"')";
			}
		
			if(criteria.get("status")!=null){
				sql += "and upper(agreement.STATUS_CODE) = upper('"+criteria.get("status")+"')";
			}
		
			if(criteria.get("es-intent-code")!=null) {
				sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
			}	
			if(criteria.get("code")!=null) {
				sql += "and upper(agreement.CODE) like upper('%"+criteria.get("code")+"%')";
			}	
			
		}
        System.out.println(sql);
		
		
		return (ArrayList<Agreement>) jdbcOperations.query(sql,new AgreementMapper());
		
		
	}

	@Override
	public Agreement getAgreementById(String id) {
		Agreement agreement = new Agreement();
		List<AgreementLine> agreementLines = new ArrayList<AgreementLine>();
		String sql="select agreement.ID,agreement.CODE,agreement.AGMT_VERSION,agreement.SELLER_COMP_SERV_ID,agreement.SELLER_COMPANY_ID,agreement.SELLER_EDC_ID,agreement.AGREEMENT_DATE,agreement.IS_ACTIVE,\n" + 
				"agreement.FROM_DATE,agreement.TO_DATE,agreement.TOTAL_UNITS,agreement.C1,agreement.C2,agreement.C3,agreement.C4,agreement.C5,agreement.PEAK_UNITS,agreement.OFF_PEAK_UNITS,\n" + 
				"agreement.SIGNATORY_PARTY1,agreement.SIGNATORY_PARTY2,agreement.SIGNATORY_PARTY3,agreement.HAS_LINES,agreement.ENABLED,agreement.FROM_MONTH,agreement.TO_YEAR,agreement.FROM_YEAR,agreement.TO_MONTH,agreement.BUYER_COMP_SERV_ID,\n" + 
				"agreement.VERSION_CHANGE_REASON_CODE,agreement.BUYER_EDC_ID,agreement.BUYER_COMP_ID,agreement.SUBSTATION_ID as SELLER_SUBSTATION_ID,agreement.FEEDER_ID as SELLER_FEEDER_ID,agreement.AGREEMENT_PERIOD_CODE,agreement.FLOW_TYPE,agreement.IS_LATEST,agreement.INTERVAL_TYPE_CODE,\n" + 
				"agreement.SHARE_PERCENTAGE,agreement.IS_CAPTIVE,agreement.STATUS_CODE,agreement.T_ES_INTENT_ID,agreement.T_EWA_ID,agreement.T_OAA_ID,agreement.T_NOC_GENERATOR_ID,agreement.T_INPRINCIPLE_APPLN_ID,agreement.T_EPA_ID,\n" + 
				"sellercompanyservice.\"number\"as SELLER_COMP_SERV_NUMBER,sellercompanyservice.M_COMPANY_ID as SELLER_COMP_ID,sellercompanyservice.M_COMPANY_NAME as SELLER_COMP_NAME,sellercompanyservice.M_COMPANY_CODE as SELLER_COMP_CODE,sellercompanyservice.M_ORG_CODE as SELLER_ORG_CODE,sellercompanyservice.M_ORG_NAME as SELLER_ORG_NAME,\n" + 
				"sellercompanyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE,sellercompanyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE ,sellercompanyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,\n" + 
				" buyercompanyservice.\"number\"as BUYER_COMP_SERV_NUMBER, buyercompanyservice.M_COMPANY_ID as BUYER_COMP_ID, buyercompanyservice.M_COMPANY_NAME as BUYER_COMP_NAME, buyercompanyservice.M_COMPANY_CODE as BUYER_COMP_CODE, buyercompanyservice.M_ORG_CODE as BUYER_ORG_CODE, buyercompanyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
				" buyercompanyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, buyercompanyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME, buyercompanyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , buyercompanyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,esintent.code as T_ES_INTENT_CODE\n" + 
				"from F_AGREEMENT agreement\n" + 
				"left join v_company_service sellercompanyservice on agreement.SELLER_COMP_SERV_ID = sellercompanyservice.id\n" + 
				"left join v_company_service buyercompanyservice on agreement.BUYER_COMP_SERV_ID = buyercompanyservice.id "+
				"left join T_ES_INTENT esintent on agreement.T_ES_INTENT_ID = esintent.ID where agreement.ID = ?";
		String sql1="select agreementline.ID,agreementline.CODE,agreementline.F_AGREEMENT_ID,agreementline.BUYER_COMP_SERV_ID,agreementline.BUYER_ORG_ID,agreementline.BUYER_SUBSTATION_ID,agreementline.BUYER_FEEDER_ID,agreementline.DRAWAL_VOLTAGE_CODE,\n" + 
				"agreementline.PROPOSED_CAPACITY,agreementline.APPROVED_CAPACITY,agreementline.AGMT_PERIOD_CODE,agreementline.FLOW_TYPE_CODE,agreementline.FROM_DT,agreementline.TO_DT,agreementline.FROM_MONTH,agreementline.TO_YEAR,agreementline.FROM_YEAR,agreementline.TO_MONTH,\n" + 
				"agreementline.APPLIED_DT,agreementline.APPROVED_DT,agreementline.AGREEMENT_DT,agreementline.IS_CAPTIVE,agreementline.INTERVAL_TYPE_CODE,agreementline.C1,agreementline.C2,agreementline.C3,agreementline.C4,agreementline.C5,\n" + 
				"agreementline.PEAK_UNITS,agreementline.OFF_PEAK_UNITS,agreementline.SHARE_PERCENTAGE,agreementline.PAYMENT_TYPE_CODE,agreementline.PAYMENT_BANK_DETAILS,agreementline.PAYMENT_TXN_NO,agreementline.PAYMENT_DATE,agreementline.PAYMENT_AMOUNT,agreementline.LICENSEE,\n" + 
				"agreementline.STATE_TRANS_LOSS,agreementline.STATE_TRANS_LOSS_PERCENT,agreementline.STATE_TRANS_CHARGES,agreementline.STATE_TRANS_CHARGES_PERCENT,agreementline.SCHEDULING_CHARGES,agreementline.SCHEDULING_CHARGES_PERCENT,agreementline.SYSTEM_OPR_CHARGES,agreementline.SYSTEM_OPR_CHARGES_PERCENT,\n" + 
				"agreementline.T_ES_INTENT_LINE_ID,agreementline.T_NOC_ID,agreementline.T_CONSENT_ID,agreementline.T_EWA_LINE_ID,agreementline.T_INPRINCIPLE_APPLN_LINE_ID,agreementline.T_NOC_GENERATOR_LINE_ID,agreementline.T_STANDING_CLEARENCE_ID,agreementline.STATUS_CODE,\n" + 
				" buyercompanyservice.\"number\"as BUYER_COMP_SERV_NUMBER,buyercompanyservice.M_COMPANY_ID as BUYER_COMP_ID, buyercompanyservice.M_COMPANY_NAME as BUYER_COMP_NAME,buyercompanyservice.M_COMPANY_CODE as BUYER_COMP_CODE, buyercompanyservice.M_ORG_CODE as BUYER_ORG_CODE, buyercompanyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
				" buyercompanyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, buyercompanyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME, buyercompanyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , buyercompanyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME\n" + 
				"from F_AGREEMENT_LINE agreementline\n" + 
				"left join v_company_service buyercompanyservice on agreementline.BUYER_COMP_SERV_ID = buyercompanyservice.id where agreementline.F_AGREEMENT_ID=? ";
		
		agreement = jdbcOperations.queryForObject(sql,new Object[]{id},new AgreementMapper());
		agreementLines = jdbcOperations.query(sql1,new Object[]{id},new AgreementLineMapper());
		agreement.setAgreementLines(agreementLines);
		
		
		return agreement;
	}

	@Override
	public String addAgreement(Agreement agreement) {
		String result="";
		try {
			agreement.setId(generateId());
			if(agreement.getCode()== null || agreement.getCode().isEmpty()){
				agreement.setCode(generateCode(Agreement.class.getSimpleName(),""));
			}
			String sql = "insert into F_AGREEMENT (CODE,SELLER_COMP_SERV_ID,SELLER_EDC_ID,AGREEMENT_DATE,IS_ACTIVE,FROM_DATE,\n" + 
					"TO_DATE,TOTAL_UNITS,C1,C2,C3,C4,C5,PEAK_UNITS,\n" + 
					"OFF_PEAK_UNITS,SIGNATORY_PARTY1,SIGNATORY_PARTY2,SIGNATORY_PARTY3,HAS_LINES,FROM_MONTH,TO_YEAR,FROM_YEAR,\n" + 
					"TO_MONTH,BUYER_COMP_SERV_ID,VERSION_CHANGE_REASON_CODE,BUYER_EDC_ID,BUYER_COMP_ID,SUBSTATION_ID,FEEDER_ID,AGREEMENT_PERIOD_CODE,\n" + 
					"FLOW_TYPE,IS_LATEST,INTERVAL_TYPE_CODE,SHARE_PERCENTAGE,IS_CAPTIVE,STATUS_CODE,T_ES_INTENT_ID,T_EWA_ID,\n" + 
					"T_OAA_ID,T_NOC_GENERATOR_ID,T_INPRINCIPLE_APPLN_ID,AGMT_VERSION,SELLER_COMPANY_ID,T_EPA_ID,T_CS_ID,ID) VALUES(?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),"
					+ "TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setAgreementValues(agreement))>0) {
				result=agreement.getId();
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
	public String updateAgreement(String id, Agreement agreement) {
		String result="";
		try {
			agreement.setId(id);
			
			String sql = "UPDATE F_AGREEMENT SET CODE=?,SELLER_COMP_SERV_ID=?,SELLER_EDC_ID=?,AGREEMENT_DATE=TO_DATE(?,'yyyy-mm-dd'),IS_ACTIVE=?,FROM_DATE=TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"TO_DATE=TO_DATE(?,'yyyy-mm-dd'),TOTAL_UNITS=?,C1=?,C2=?,C3=?,C4=?,C5=?,PEAK_UNITS=?,\n" + 
					"OFF_PEAK_UNITS=?,SIGNATORY_PARTY1=?,SIGNATORY_PARTY2=?,SIGNATORY_PARTY3=?,HAS_LINES=?,FROM_MONTH=?,TO_YEAR=?,FROM_YEAR=?,\n" + 
					"TO_MONTH=?,BUYER_COMP_SERV_ID=?,VERSION_CHANGE_REASON_CODE=?,BUYER_EDC_ID=?,BUYER_COMP_ID=?,SUBSTATION_ID=?,FEEDER_ID=?,AGREEMENT_PERIOD_CODE=?,\n" + 
					"FLOW_TYPE=?,IS_LATEST=?,INTERVAL_TYPE_CODE=?,SHARE_PERCENTAGE=?,IS_CAPTIVE=?,STATUS_CODE=?,T_ES_INTENT_ID=?,T_EWA_ID=?,\n" + 
					"T_OAA_ID=?,T_NOC_GENERATOR_ID=?,T_INPRINCIPLE_APPLN_ID=?,AGMT_VERSION=?,SELLER_COMPANY_ID=?,T_EPA_ID=?, T_CS_ID=? WHERE ID=? ";
			if(jdbcOperations.update(sql,setAgreementValues(agreement))>0) {
				result=agreement.getId();
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
	public String addAgreementLine(AgreementLine agreementLine) {
		String result="";
		try {
			agreementLine.setId(generateId());
			
			String sql = "insert into F_AGREEMENT_LINE (F_AGREEMENT_ID,BUYER_COMP_SERV_ID,BUYER_ORG_ID,BUYER_SUBSTATION_ID,BUYER_FEEDER_ID,DRAWAL_VOLTAGE_CODE,PROPOSED_CAPACITY,APPROVED_CAPACITY,\n" + 
					"AGMT_PERIOD_CODE,FLOW_TYPE_CODE,FROM_DT,TO_DT,FROM_MONTH,TO_YEAR,FROM_YEAR,TO_MONTH,\n" + 
					"APPLIED_DT,APPROVED_DT,AGREEMENT_DT,IS_CAPTIVE,INTERVAL_TYPE_CODE,C1,C2,C3,\n" + 
					"C4,C5,PEAK_UNITS,OFF_PEAK_UNITS,SHARE_PERCENTAGE,PAYMENT_TYPE_CODE,PAYMENT_BANK_DETAILS,PAYMENT_TXN_NO,\n" + 
					"PAYMENT_DATE,PAYMENT_AMOUNT,LICENSEE,STATE_TRANS_LOSS,STATE_TRANS_LOSS_PERCENT,STATE_TRANS_CHARGES,STATE_TRANS_CHARGES_PERCENT,SCHEDULING_CHARGES,\n" + 
					"SCHEDULING_CHARGES_PERCENT,SYSTEM_OPR_CHARGES,SYSTEM_OPR_CHARGES_PERCENT,T_ES_INTENT_LINE_ID,T_NOC_ID,T_CONSENT_ID,T_EWA_LINE_ID,T_INPRINCIPLE_APPLN_LINE_ID,\n" + 
					"T_NOC_GENERATOR_LINE_ID,T_STANDING_CLEARENCE_ID,STATUS_CODE,ID) VALUES(?,?,?,?,?,?,?,?,"
					+ "?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,"
					+ "TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?)";
			if(jdbcOperations.update(sql,setAgreementLineValues(agreementLine))>0) {
				result=agreementLine.getId();
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
	public String updateAgreementLine(AgreementLine agreementLine) {
		String result="";
		try {
			
			
			String sql = "UPDATE F_AGREEMENT_LINE SET F_AGREEMENT_ID=?,BUYER_COMP_SERV_ID=?,BUYER_ORG_ID=?,BUYER_SUBSTATION_ID=?,BUYER_FEEDER_ID=?,DRAWAL_VOLTAGE_CODE=?,PROPOSED_CAPACITY=?,APPROVED_CAPACITY=?,\n" + 
					"		AGMT_PERIOD_CODE=?,FLOW_TYPE_CODE=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),FROM_MONTH=?,TO_YEAR=?,FROM_YEAR=?,TO_MONTH=?,\n" + 
					"		APPLIED_DT=TO_DATE(?,'yyyy-mm-dd'),APPROVED_DT=TO_DATE(?,'yyyy-mm-dd'),AGREEMENT_DT=TO_DATE(?,'yyyy-mm-dd'),IS_CAPTIVE=?,INTERVAL_TYPE_CODE=?,C1=?,C2=?,C3=?,\n" + 
					"		C4=?,C5=?,PEAK_UNITS=?,OFF_PEAK_UNITS=?,SHARE_PERCENTAGE=?,PAYMENT_TYPE_CODE=?,PAYMENT_BANK_DETAILS=?,PAYMENT_TXN_NO=?,\n" + 
					"		PAYMENT_DATE=?,PAYMENT_AMOUNT=?,LICENSEE=?,STATE_TRANS_LOSS=?,STATE_TRANS_LOSS_PERCENT=?,STATE_TRANS_CHARGES=?,STATE_TRANS_CHARGES_PERCENT=?,SCHEDULING_CHARGES=?,\n" + 
					"		SCHEDULING_CHARGES_PERCENT=?,SYSTEM_OPR_CHARGES=?,SYSTEM_OPR_CHARGES_PERCENT=?,T_ES_INTENT_LINE_ID=?,T_NOC_ID=?,T_CONSENT_ID=?,T_EWA_LINE_ID=?,T_INPRINCIPLE_APPLN_LINE_ID=?, \n" + 
					"		T_NOC_GENERATOR_LINE_ID=?,T_STANDING_CLEARENCE_ID=?,STATUS_CODE=? WHERE ID=? ";
			if(jdbcOperations.update(sql,setAgreementLineValues(agreementLine))>0) {
				result=agreementLine.getId();
			}else {
				result="Failure";
			}
			
		}catch(Exception e) {
			result="Failure";
			e.printStackTrace();
			
		}
		return result;
	}
	
	private Object[] setAgreementValues(Agreement agreement) {
		return new Object[] {
				agreement.getCode(),
				agreement.getSellerCompanyServiceId(),				
				agreement.getSellerOrgId(),			
				agreement.getAgreementDate(),
				agreement.getIsCaptive(),
				agreement.getFromDate(),
				agreement.getToDate(),
				agreement.getTotalUnits(),
				agreement.getC1(),
				agreement.getC2(),
				agreement.getC3(),
				agreement.getC4(),
				agreement.getC5(),
				agreement.getPeakUnits(),
				agreement.getOffPeakUnits(),
				agreement.getSignatoryParty1(),
				agreement.getSignatoryParty2(),
				agreement.getSignatoryParty3(),
				agreement.getHasLines(),
				agreement.getFromMonth(),
				agreement.getToYear(),
				agreement.getFromYear(),
				agreement.getToMonth(),
				agreement.getBuyerCompanyServiceId(),
				agreement.getVersionChangeReasonCode(),
				agreement.getBuyerOrgId(),
				agreement.getBuyerCompanyId(),
				agreement.getSellerSubstationid(),
				agreement.getSellerFeederId(),
				agreement.getAgreementPeriodCode(),
				agreement.getFlowTypeCode(),
				agreement.getIsLatest(),
				agreement.getIntervalTypeCode(),
				agreement.getSharePercent(),
				agreement.getIsCaptive(),
				agreement.getStatusCode(),
				agreement.getEsIntentId(),
				agreement.getEwaId(),
				agreement.getOaaId(),
				agreement.getNocGeneratorId(),
				agreement.getIpaaId(),
				agreement.getVersion(),
				agreement.getSellerCompanyId(),
				agreement.getEpaId(),
				agreement.getCsId(),
				agreement.getId()
			
		};
	}
	private Object[] setAgreementLineValues(AgreementLine agreementLine) {
		return new Object[] {
			
				agreementLine.getAgreementId(),
				agreementLine.getBuyerCompanyServiceId(),
				agreementLine.getBuyerOrgId(),
				agreementLine.getBuyerSubstationid(),
				agreementLine.getBuyerFeederId(),
				agreementLine.getDrawalVoltageCode(),
				agreementLine.getProposedCapacity(),
				agreementLine.getApprovedCapacity(),
				agreementLine.getAgreementPeriodCode(),
				agreementLine.getFlowTypeCode(),
				agreementLine.getFromDate(),
				agreementLine.getToDate(),
				agreementLine.getFromMonth(),
				agreementLine.getToYear(),
				agreementLine.getFromYear(),
				agreementLine.getToMonth(),
				agreementLine.getAppliedDate(),
				agreementLine.getApprovedDate(),
				agreementLine.getAgreementDate(),
				agreementLine.getIsCaptive(),
				agreementLine.getIntervalTypeCode(),			
				agreementLine.getC1(),
				agreementLine.getC2(),
				agreementLine.getC3(),
				agreementLine.getC4(),
				agreementLine.getC5(),
				agreementLine.getPeakUnits(),
				agreementLine.getOffPeakUnits(),			
				agreementLine.getSharePercent(),
				agreementLine.getPaymentTypeCode(),
				agreementLine.getPaymentBankDetails(),
				agreementLine.getPaymentTxnNo(),
				agreementLine.getPaymentDate(),
				agreementLine.getPaymentAmount(),
				agreementLine.getLicensee(),
				agreementLine.getStateTransLoss(),
				agreementLine.getStateTransLossPercent(),
				agreementLine.getStateTransCharges(),
				agreementLine.getStateTransChargesPercent(),
				agreementLine.getSchedulingCharges(),
				agreementLine.getSchedulingChargesPercent(),
				agreementLine.getSystemOprCharges(),
				agreementLine.getSystemoprChargesPercent(),				
				agreementLine.getEsIntentLineId(),
				agreementLine.getNocId(),
				agreementLine.getConsentId(),
				agreementLine.getEwaLineId(),
				agreementLine.getIpaaLineId(),
				agreementLine.getNocGeneratorLineId(),
				agreementLine.getStandingClearenceId(),
				agreementLine.getStatusCode(),
				agreementLine.getId()
			
		};
	}
	final class AgreementMapper implements RowMapper<Agreement>{

		@Override
		public Agreement mapRow(ResultSet resultSet, int intNum) throws SQLException {
			Agreement agreement = new Agreement();
			agreement.setId(resultSet.getString("ID"));
			agreement.setCode(resultSet.getString("CODE"));
			agreement.setVersion(resultSet.getString("AGMT_VERSION"));
			agreement.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			agreement.setSellerCompanyId(resultSet.getString("SELLER_COMPANY_ID"));
			agreement.setSellerOrgId(resultSet.getString("SELLER_EDC_ID"));
			agreement.setAgreementDate(resultSet.getString("AGREEMENT_DATE"));
			agreement.setIsActive(resultSet.getString("IS_ACTIVE"));
			agreement.setFromDate(resultSet.getString("FROM_DATE"));
			agreement.setToDate(resultSet.getString("TO_DATE"));
			agreement.setTotalUnits(resultSet.getString("TOTAL_UNITS"));
			agreement.setC1(resultSet.getString("C1"));
			agreement.setC2(resultSet.getString("C2"));
			agreement.setC3(resultSet.getString("C3"));
			agreement.setC4(resultSet.getString("C4"));
			agreement.setC5(resultSet.getString("C5"));
			agreement.setPeakUnits(resultSet.getString("PEAK_UNITS"));
			agreement.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));
			agreement.setSignatoryParty1(resultSet.getString("SIGNATORY_PARTY1"));
			agreement.setSignatoryParty2(resultSet.getString("SIGNATORY_PARTY2"));
			agreement.setSignatoryParty3(resultSet.getString("SIGNATORY_PARTY3"));
			agreement.setHasLines(resultSet.getString("HAS_LINES"));
			agreement.setEnabled(resultSet.getString("ENABLED"));
			agreement.setFromMonth(resultSet.getString("FROM_MONTH"));
			agreement.setToMonth(resultSet.getString("TO_MONTH"));
			agreement.setFromYear(resultSet.getString("FROM_YEAR"));
			agreement.setToYear(resultSet.getString("TO_YEAR"));
			agreement.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			agreement.setVersionChangeReasonCode(resultSet.getString("VERSION_CHANGE_REASON_CODE"));
			agreement.setBuyerOrgId(resultSet.getString("BUYER_EDC_ID"));
			agreement.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			agreement.setSellerSubstationid(resultSet.getString("SELLER_SUBSTATION_ID"));
			agreement.setSellerFeederId(resultSet.getString("SELLER_FEEDER_ID"));
			agreement.setAgreementPeriodCode(resultSet.getString("AGREEMENT_PERIOD_CODE"));
			agreement.setFlowTypeCode(resultSet.getString("FLOW_TYPE"));
			agreement.setIsLatest(resultSet.getString("IS_LATEST"));
			agreement.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
			agreement.setSharePercent(resultSet.getString("SHARE_PERCENTAGE"));
			agreement.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			agreement.setStatusCode(resultSet.getString("STATUS_CODE"));
			agreement.setEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			agreement.setEwaId(resultSet.getString("T_EWA_ID"));
			agreement.setOaaId(resultSet.getString("T_OAA_ID"));
			agreement.setNocGeneratorId(resultSet.getString("T_NOC_GENERATOR_ID"));
			agreement.setIpaaId(resultSet.getString("T_INPRINCIPLE_APPLN_ID"));
			agreement.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			agreement.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
			agreement.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
			agreement.setSellerCompanyCode(resultSet.getString("SELLER_COMP_CODE"));
			agreement.setSellerOrgCode(resultSet.getString("SELLER_ORG_CODE"));
			agreement.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			agreement.setSellerSubstationCode(resultSet.getString("SELLER_SUBSTATION_CODE"));
			agreement.setSellerSubstationName(resultSet.getString("SELLER_SUBSTATION_NAME"));
			agreement.setSellerFeederCode(resultSet.getString("SELLER_FEEDER_CODE"));
			agreement.setSellerFeederName(resultSet.getString("SELLER_FEEDER_NAME"));
			agreement.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			agreement.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			agreement.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			agreement.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			agreement.setBuyerOrgCode(resultSet.getString("BUYER_ORG_CODE"));
			agreement.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			agreement.setBuyerSubstationCode(resultSet.getString("BUYER_SUBSTATION_CODE"));
			agreement.setBuyerSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			agreement.setBuyerFeederCode(resultSet.getString("BUYER_FEEDER_CODE"));
			agreement.setBuyerFeederName(resultSet.getString("BUYER_FEEDER_NAME"));
			agreement.setEpaId(resultSet.getString("T_EPA_ID"));
			agreement.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));
			return agreement;
		}
		
	}
	
	final class AgreementLineMapper implements RowMapper<AgreementLine>{

		@Override
		public AgreementLine mapRow(ResultSet resultSet, int intNum) throws SQLException {
			AgreementLine agreementLine = new AgreementLine();
			agreementLine.setId(resultSet.getString("ID"));
			agreementLine.setCode(resultSet.getString("CODE"));	
			agreementLine.setAgreementId(resultSet.getString("F_AGREEMENT_ID"));
			agreementLine.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			agreementLine.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
		
			agreementLine.setBuyerSubstationid(resultSet.getString("BUYER_SUBSTATION_ID"));
			agreementLine.setBuyerFeederId(resultSet.getString("BUYER_FEEDER_ID"));
			agreementLine.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			agreementLine.setProposedCapacity(resultSet.getString("PROPOSED_CAPACITY"));
			agreementLine.setApprovedCapacity(resultSet.getString("APPROVED_CAPACITY"));
			agreementLine.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
			agreementLine.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));		
			agreementLine.setFromDate(resultSet.getString("FROM_DT"));
			agreementLine.setToDate(resultSet.getString("TO_DT"));			
			agreementLine.setFromMonth(resultSet.getString("FROM_MONTH"));
			agreementLine.setToMonth(resultSet.getString("TO_MONTH"));
			agreementLine.setFromYear(resultSet.getString("FROM_YEAR"));
			agreementLine.setToYear(resultSet.getString("TO_YEAR"));
			agreementLine.setAppliedDate(resultSet.getString("APPLIED_DT"));
			agreementLine.setApprovedDate(resultSet.getString("APPROVED_DT"));
			agreementLine.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
			agreementLine.setIsCaptive(resultSet.getString("IS_CAPTIVE"));			
			agreementLine.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
			agreementLine.setC1(resultSet.getString("C1"));
			agreementLine.setC2(resultSet.getString("C2"));	
			agreementLine.setC3(resultSet.getString("C3"));
			agreementLine.setC4(resultSet.getString("C4"));
			agreementLine.setC5(resultSet.getString("C5"));
			agreementLine.setPeakUnits(resultSet.getString("PEAK_UNITS"));
			agreementLine.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));	
			agreementLine.setSharePercent(resultSet.getString("SHARE_PERCENTAGE"));
			agreementLine.setPaymentTypeCode(resultSet.getString("PAYMENT_TYPE_CODE"));
			agreementLine.setPaymentBankDetails(resultSet.getString("PAYMENT_BANK_DETAILS"));
			agreementLine.setPaymentTxnNo(resultSet.getString("PAYMENT_TXN_NO"));
			agreementLine.setPaymentDate(resultSet.getString("PAYMENT_DATE"));
			agreementLine.setPaymentAmount(resultSet.getString("PAYMENT_AMOUNT"));
			agreementLine.setStateTransLoss(resultSet.getString("STATE_TRANS_LOSS"));
			agreementLine.setStateTransLossPercent(resultSet.getString("STATE_TRANS_LOSS_PERCENT"));
			agreementLine.setStateTransCharges(resultSet.getString("STATE_TRANS_CHARGES"));
			agreementLine.setStateTransChargesPercent(resultSet.getString("STATE_TRANS_CHARGES_PERCENT"));
			agreementLine.setSchedulingCharges(resultSet.getString("SCHEDULING_CHARGES"));
			agreementLine.setSchedulingChargesPercent(resultSet.getString("SCHEDULING_CHARGES_PERCENT"));
			agreementLine.setSystemOprCharges(resultSet.getString("SYSTEM_OPR_CHARGES"));
			agreementLine.setSystemoprChargesPercent(resultSet.getString("SYSTEM_OPR_CHARGES_PERCENT"));			
			agreementLine.setStatusCode(resultSet.getString("STATUS_CODE"));
			agreementLine.setEsIntentLineId(resultSet.getString("T_ES_INTENT_LINE_ID"));
			agreementLine.setNocId(resultSet.getString("T_NOC_ID"));
			agreementLine.setConsentId(resultSet.getString("T_CONSENT_ID"));
			agreementLine.setEwaLineId(resultSet.getString("T_EWA_LINE_ID"));		
			agreementLine.setNocGeneratorLineId(resultSet.getString("T_NOC_GENERATOR_LINE_ID"));
			agreementLine.setIpaaLineId(resultSet.getString("T_INPRINCIPLE_APPLN_LINE_ID"));
			agreementLine.setStandingClearenceId(resultSet.getString("T_STANDING_CLEARENCE_ID"));
			agreementLine.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			agreementLine.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			agreementLine.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			agreementLine.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			agreementLine.setBuyerOrgCode(resultSet.getString("BUYER_ORG_CODE"));
			agreementLine.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			agreementLine.setBuyerSubstationCode(resultSet.getString("BUYER_SUBSTATION_CODE"));
			agreementLine.setBuyerSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			agreementLine.setBuyerFeederCode(resultSet.getString("BUYER_FEEDER_CODE"));
			agreementLine.setBuyerFeederName(resultSet.getString("BUYER_FEEDER_NAME"));
			return agreementLine;
		}
		
	}

	


}
