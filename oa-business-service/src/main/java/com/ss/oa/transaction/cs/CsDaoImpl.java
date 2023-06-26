package com.ss.oa.transaction.cs;

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
import com.ss.oa.transaction.vo.Cs;
import com.ss.oa.transaction.vo.CsCaptiveUserContribution;
import com.ss.oa.transaction.vo.CsEquityShareVotingRight;
import com.ss.oa.transaction.vo.CsLoan;
import com.ss.oa.transaction.vo.CsQuantumAllocation;

@Scope("prototype")
@Component
public class CsDaoImpl extends BaseDaoJdbc implements CsDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Cs> getAllCs(Map<String, String> criteria) {
		
		String sql ="select cs.ID,cs.CODE,cs.SELLER_COMP_SERV_ID,cs.SELLER_EDC_ID,cs.SELLER_COMPANY_ID,cs.AGREEMENT_DATE,cs.EFFECTIVE_DATE,cs.FROM_DATE,cs.TO_DATE,cs.FROM_MONTH,cs.TO_YEAR,cs.FROM_YEAR,cs.TO_MONTH,\n" + 
				"cs.AGREEMENT_PERIOD_CODE,cs.FLOW_TYPE_CODE,cs.STATUS_CODE,cs.ID_TOTAL_COST,cs.ID_TOTAL_CURRENCY,cs.ID_TOTAL_EXCHANGE_RATE,cs.APPROVED_CAPACITY,cs.VOLTAGE_CODE,\n" + 
				"sellercompanyservice.\"number\"as SELLER_COMP_SERV_NUMBER, sellercompanyservice.M_COMPANY_NAME as SELLER_COMP_NAME, sellercompanyservice.M_COMPANY_CODE as SELLER_COMP_CODE, sellercompanyservice.M_ORG_ID as SELLER_ORG_ID,sellercompanyservice.M_ORG_CODE as SELLER_ORG_CODE, sellercompanyservice.M_ORG_NAME as SELLER_ORG_NAME,\n" + 
				"sellercompanyservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID, sellercompanyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE, sellercompanyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_ID as SELLER_FEEDER_ID, sellercompanyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE , sellercompanyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,\n" + 
				"voltagecode.VALUE_DESC as VOLTAGE_NAME,cs.APPROVED_CAPACITY,cs.T_ES_INTENT_ID,esIntent.code as T_ES_INTENT_CODE\n" + 
				"from T_cs cs\n" + 
				"left join v_company_service sellercompanyservice on cs.SELLER_COMP_SERV_ID = sellercompanyservice.id\n" + 
				" LEFT JOIN V_CODES voltagecode on cs.VOLTAGE_CODE = voltagecode.VALUE_CODE and voltagecode.LIST_CODE = 'VOLTAGE_CODE'"
				+ " LEFT JOIN T_ES_INTENT esIntent on cs.T_ES_INTENT_ID = esIntent.id  where 1=1 " ;
		
		if(!criteria.isEmpty())
		{
			if(criteria.get("seller-edc-id")!=null){
				sql += "and upper(cs.SELLER_EDC_ID)= upper('"+criteria.get("seller-edc-id")+"')";
			}
			if(criteria.get("seller-company-service-id")!=null){
				sql += "and upper(cs.SELLER_COMP_SERV_ID) = upper('"+criteria.get("seller-company-service-id")+"')";
			}
		
			if(criteria.get("status")!=null){
				sql += "and upper(cs.STATUS_CODE) = upper('"+criteria.get("status")+"')";
			}
		
				
			if(criteria.get("code")!=null) {
				sql += "and upper(cs.CODE) like upper('%"+criteria.get("code")+"%')";
			}	
			
		}
        System.out.println(sql);
 	return (ArrayList<Cs>) jdbcOperations.query(sql,new CsMapper());
	}

	@Override
	public Cs getCsById(String id) {
		Cs cs = new Cs();
		List<CsLoan> idCsLoanList=new ArrayList<CsLoan>();
		 List<CsCaptiveUserContribution> csCaptiveUserContributionList=new ArrayList<CsCaptiveUserContribution>();
		 List<CsEquityShareVotingRight> csEquityShareVotingRights=new ArrayList<CsEquityShareVotingRight>();
		 List<CsQuantumAllocation> csQuantumAllocationList=new ArrayList<CsQuantumAllocation>();
		String sql ="select cs.ID,cs.CODE,cs.SELLER_COMP_SERV_ID,cs.SELLER_EDC_ID,cs.SELLER_COMPANY_ID,cs.AGREEMENT_DATE,cs.EFFECTIVE_DATE,cs.FROM_DATE,cs.TO_DATE,cs.FROM_MONTH,cs.TO_YEAR,cs.FROM_YEAR,cs.TO_MONTH,\n" + 
				"cs.AGREEMENT_PERIOD_CODE,cs.FLOW_TYPE_CODE,cs.STATUS_CODE,cs.ID_TOTAL_COST,cs.ID_TOTAL_CURRENCY,cs.ID_TOTAL_EXCHANGE_RATE,cs.APPROVED_CAPACITY,cs.VOLTAGE_CODE,\n" + 
				"sellercompanyservice.\"number\"as SELLER_COMP_SERV_NUMBER, sellercompanyservice.M_COMPANY_NAME as SELLER_COMP_NAME, sellercompanyservice.M_COMPANY_CODE as SELLER_COMP_CODE, sellercompanyservice.M_ORG_ID as SELLER_ORG_ID,sellercompanyservice.M_ORG_CODE as SELLER_ORG_CODE, sellercompanyservice.M_ORG_NAME as SELLER_ORG_NAME,\n" + 
				"sellercompanyservice.M_SUBSTATION_ID as SELLER_SUBSTATION_ID, sellercompanyservice.M_SUBSTATION_CODE as SELLER_SUBSTATION_CODE, sellercompanyservice.M_SUBSTATION_NAME as SELLER_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_ID as SELLER_FEEDER_ID, sellercompanyservice.M_FEEDER_CODE as SELLER_FEEDER_CODE , sellercompanyservice.M_FEEDER_NAME as SELLER_FEEDER_NAME,\n" + 
				"voltagecode.VALUE_DESC as VOLTAGE_NAME,cs.APPROVED_CAPACITY,cs.T_ES_INTENT_ID,esIntent.code as T_ES_INTENT_CODE\n" + 
				"from T_cs cs\n" + 
				"left join v_company_service sellercompanyservice on cs.SELLER_COMP_SERV_ID = sellercompanyservice.id\n" + 
				" LEFT JOIN V_CODES voltagecode on cs.VOLTAGE_CODE = voltagecode.VALUE_CODE and voltagecode.LIST_CODE = 'VOLTAGE_CODE'"
				+ " LEFT JOIN T_ES_INTENT esIntent on cs.T_ES_INTENT_ID = esIntent.id  where cs.id=? " ;
		cs = jdbcOperations.queryForObject(sql,new Object[]{id},new CsMapper());
		
		
	
		String sql1=" SELECT loan.ID,loan.T_CS_ID,loan.LOAN_ORIGIN,loan.SOURCE_NAME,loan.SOURCE_ADDRESS,loan.LOAN_AMOUNT,loan.CURRENCY,loan.EXCHANGE_RATE,loan.REMARKS,\n" + 
				"		loan.CREATED_BY,loan.CREATED_DATE,loan.MODIFIED_BY,loan.MODIFIED_DATE,loan.ENABLED\n" + 
				"		FROM OPEN_ACCESS.T_CS_LOAN loan where loan.T_CS_ID=?";
		
		idCsLoanList=jdbcOperations.query(sql1,new Object[]{id}, new CsLoanMapper());
		
		String sql2="SELECT tablea.ID,tablea.T_CS_ID,tablea.CLASS_OF_EQUTIY_SHARES,tablea.NUMBER_OF_EQUTIY_SHARES,tablea.VALUE_OF_EQUTIY_SHARES,tablea.AMOUNT_OF_EQUTIY_SHARES,\n" + 
				"                    tablea.NUMBER_OF_VOTING_RIGHTS,tablea.PERCT_IN_EQUTIY_SHARES,tablea.PERCT_IN_VOTING_RIGHTS,tablea.PERCT_IN_VOTING_WITH_EQUITY,tablea.REMARKS,\n" + 
				"		 		tablea.CREATED_BY,tablea.CREATED_DATE,tablea.MODIFIED_BY,tablea.MODIFIED_DATE,tablea.ENABLED\n" + 
				"		 		FROM OPEN_ACCESS.T_CS_ID_TABLEA tablea where tablea.T_CS_ID=? ";
		csEquityShareVotingRights=jdbcOperations.query(sql2,new Object[]{id}, new CsEquityShareVotingRightMapper());
		 
		 
	
		 
		String sql3="SELECT tableb.ID,tableb.T_CS_ID,tableb.CLASS_OF_SHAREHOLDER,tableb.NUMBER_OF_EQUTIY_SHARES,tableb.VALUE_OF_EQUTIY_SHARES,tableb.AMOUNT_OF_EQUTIY_SHARES,\n" + 
				"                    tableb.NUMBER_OF_VOTING_RIGHTS,tableb.PERCT_IN_EQUTIY_SHARES,tableb.PERCT_IN_VOTING_RIGHTS,tableb.PERCT_IN_VOTING_WITH_EQUITY,tableb.REMARKS,\n" + 
				"                    tableb.CREATED_BY,tableb.CREATED_DATE,tableb.MODIFIED_BY,tableb.MODIFIED_DATE,tableb.ENABLED\n" + 
				"                    FROM OPEN_ACCESS.T_CS_ID_TABLEB  tableb where tableb.T_CS_ID=? ";
		csCaptiveUserContributionList=jdbcOperations.query(sql3,new Object[]{id}, new CsCaptiveUserContributionMapper());
		
		
		String sql4 = "  SELECT quantamallocation.ID,quantamallocation.T_CS_ID,quantamallocation.BUYER_COMP_SERV_ID,quantamallocation.BUYER_ORG_ID,quantamallocation.CAPTIVE_COMPANY_NAME,\n" + 
				"	quantamallocation.QUANTUM,quantamallocation.INJECTING_VOLTAGE_CODE,involtagecode.VALUE_DESC AS INJECTION_VOLTAGE_NAME,quantamallocation.DRAWAL_VOLTAGE_CODE,\n" + 
				"	drvoltagecode.VALUE_DESC AS DRAWAL_VOLTAGE_NAME,quantamallocation.REMARKS,quantamallocation.CREATED_BY,quantamallocation.CREATED_DATE,quantamallocation.MODIFIED_BY,quantamallocation.MODIFIED_DATE,quantamallocation.ENABLED,\n" + 
				"	companyservice.\"number\"as BUYER_COMP_SERV_NUMBER, companyservice.M_COMPANY_ID as BUYER_COMP_ID, companyservice.M_COMPANY_NAME as BUYER_COMP_NAME, companyservice.M_COMPANY_CODE as BUYER_COMP_CODE,companyservice.M_ORG_CODE as BUYER_ORG_CODE, companyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
				"	companyservice.M_SUBSTATION_ID as BUYER_SUBSTATION_ID, companyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, companyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME,companyservice.M_FEEDER_ID as BUYER_FEEDER_ID, companyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , companyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME\n" + 
				"	FROM OPEN_ACCESS.T_CS_QUANTUM_ALLOCATION quantamallocation\n" + 
				"	LEFT JOIN V_CODES involtagecode ON quantamallocation.INJECTING_VOLTAGE_CODE = involtagecode.VALUE_CODE AND involtagecode.LIST_CODE='VOLTAGE_CODE'\n" + 
				"	LEFT JOIN V_CODES drvoltagecode ON quantamallocation.DRAWAL_VOLTAGE_CODE = drvoltagecode.VALUE_CODE AND drvoltagecode.LIST_CODE='VOLTAGE_CODE'\n" + 
				"	left join v_company_service companyservice on quantamallocation.BUYER_COMP_SERV_ID = companyservice.id where quantamallocation.T_CS_ID=?";
		
		csQuantumAllocationList=jdbcOperations.query(sql4,new Object[]{id}, new CsQuantumAllocationMapper());
		cs.setCsLoans(idCsLoanList);
		cs.setCsCaptiveUserContributions(csCaptiveUserContributionList);
		cs.setCsEquityShareVotingRights(csEquityShareVotingRights);
		cs.setCsQuantumAllocations(csQuantumAllocationList);
		return cs;
	}

	@Override
	public String addCs(Cs cs) {
		String result="";
		try {
			cs.setId(generateId());
			if(cs.getCode()== null || cs.getCode().isEmpty()){
				cs.setCode(generateCode(Cs.class.getSimpleName(),""));
			}
			 System.out.println(cs);
			String sql ="INSERT INTO T_CS (CODE,SELLER_COMP_SERV_ID,SELLER_EDC_ID,SELLER_COMPANY_ID,AGREEMENT_DATE,EFFECTIVE_DATE,FROM_DATE,\n" + 
					"TO_DATE,FROM_MONTH,TO_YEAR,FROM_YEAR,TO_MONTH,AGREEMENT_PERIOD_CODE,FLOW_TYPE_CODE,\n" + 
					"STATUS_CODE,ID_TOTAL_COST,ID_TOTAL_CURRENCY,ID_TOTAL_EXCHANGE_RATE,APPROVED_CAPACITY,VOLTAGE_CODE,T_ES_INTENT_ID,ID) VALUES (?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setCsValues(cs))>0) {
				result= cs.getId();
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
	public String updateCs(String id, Cs cs) {
		String result="";

		try {
			String sql ="UPDATE T_CS SET CODE=?,SELLER_COMP_SERV_ID=?,SELLER_EDC_ID=?,SELLER_COMPANY_ID=?,AGREEMENT_DATE=TO_DATE(?,'yyyy-mm-dd'),EFFECTIVE_DATE=TO_DATE(?,'yyyy-mm-dd'),FROM_DATE=TO_DATE(?,'yyyy-mm-dd'), \n" + 
					"				TO_DATE=TO_DATE(?,'yyyy-mm-dd'),FROM_MONTH=?,TO_YEAR=?,FROM_YEAR=?,TO_MONTH=?,AGREEMENT_PERIOD_CODE=?,FLOW_TYPE_CODE=?,\n" + 
					"				STATUS_CODE=?,ID_TOTAL_COST=?,ID_TOTAL_CURRENCY=?,ID_TOTAL_EXCHANGE_RATE=?,APPROVED_CAPACITY=?,VOLTAGE_CODE=?,T_ES_INTENT_ID=? WHERE ID=?";
			
					
					if(jdbcOperations.update(sql, setCsValues(cs)) > 0){
				result = cs.getId();				
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
	public String addCsCaptiveUserContribution(CsCaptiveUserContribution csCaptiveUserContribution) {
		String result="";
		try {
			csCaptiveUserContribution.setId(generateId());
			String sql ="INSERT INTO T_CS_ID_TABLEB\n" + 
					"(T_CS_ID, CLASS_OF_SHAREHOLDER, NUMBER_OF_EQUTIY_SHARES, VALUE_OF_EQUTIY_SHARES, \n" + 
					"AMOUNT_OF_EQUTIY_SHARES, NUMBER_OF_VOTING_RIGHTS, PERCT_IN_EQUTIY_SHARES, PERCT_IN_VOTING_RIGHTS, PERCT_IN_VOTING_WITH_EQUITY,\n" + 
					"REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
			
			if(jdbcOperations.update(sql,setCsCaptiveUserContribution(csCaptiveUserContribution))>0) {
				result= csCaptiveUserContribution.getId();
			}else {
				result = "Failure";
			}
			}catch(Exception e) {
				result= "Failure";
				e.printStackTrace();
			
		}
		return result;
	
	}

	@Override
	public String updateCsCaptiveUserContribution(CsCaptiveUserContribution csCaptiveUserContribution) {
		String result="";

		try {
			String sql ="UPDATE T_CS_ID_TABLEB\n" + 
					"SET T_CS_ID=?, CLASS_OF_SHAREHOLDER=?, NUMBER_OF_EQUTIY_SHARES=?, VALUE_OF_EQUTIY_SHARES=?, AMOUNT_OF_EQUTIY_SHARES=?\n" + 
					", NUMBER_OF_VOTING_RIGHTS=?, PERCT_IN_EQUTIY_SHARES=?, PERCT_IN_VOTING_RIGHTS=?, PERCT_IN_VOTING_WITH_EQUITY=?, REMARKS=?,\n" + 
					"CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
					"WHERE ID=?";
			
					
					if(jdbcOperations.update(sql, setCsCaptiveUserContribution(csCaptiveUserContribution)) > 0){
				result = csCaptiveUserContribution.getId();				
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
	public String addCsEquityShareVotingRight(CsEquityShareVotingRight csEquityShareVotingRight) {
		String result="";
		try {
			csEquityShareVotingRight.setId(generateId());
			String sql ="INSERT INTO T_CS_ID_TABLEA\n" + 
					"(T_CS_ID, CLASS_OF_EQUTIY_SHARES, NUMBER_OF_EQUTIY_SHARES, VALUE_OF_EQUTIY_SHARES, AMOUNT_OF_EQUTIY_SHARES, NUMBER_OF_VOTING_RIGHTS, \n" + 
					"PERCT_IN_EQUTIY_SHARES, PERCT_IN_VOTING_RIGHTS, PERCT_IN_VOTING_WITH_EQUITY, REMARKS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
			
			if(jdbcOperations.update(sql,setCsEquityShareVotingRight(csEquityShareVotingRight))>0) {
				result= csEquityShareVotingRight.getId();
			}else {
				result = "Failure";
			}
			}catch(Exception e) {
				result= "Failure";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateCsEquityShareVotingRight(CsEquityShareVotingRight csEquityShareVotingRight) {
		String result="";
		
		try {
			String sql ="UPDATE T_CS_ID_TABLEA\n" + 
					"SET T_CS_ID=?, CLASS_OF_EQUTIY_SHARES=?, NUMBER_OF_EQUTIY_SHARES=?, VALUE_OF_EQUTIY_SHARES=?, AMOUNT_OF_EQUTIY_SHARES=?,\n" + 
					"NUMBER_OF_VOTING_RIGHTS=?, PERCT_IN_EQUTIY_SHARES=?, PERCT_IN_VOTING_RIGHTS=?, PERCT_IN_VOTING_WITH_EQUITY=?, REMARKS=?,\n" + 
					"CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
					"WHERE ID=?";
			
					
					if(jdbcOperations.update(sql, setCsEquityShareVotingRight(csEquityShareVotingRight)) > 0){
				result = csEquityShareVotingRight.getId();				
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
	public String addCsLoan(CsLoan csLoan) {
		String result="";
		try {
			csLoan.setId(generateId());
			String sql ="INSERT INTO T_CS_LOAN\n" + 
					"( T_CS_ID, LOAN_ORIGIN, SOURCE_NAME, SOURCE_ADDRESS, LOAN_AMOUNT, CURRENCY, EXCHANGE_RATE, REMARKS,\n" + 
					"CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
			
			if(jdbcOperations.update(sql,setCsLoan(csLoan))>0) {
				result= csLoan.getId();
			}else {
				result = "Failure";
			}
			}catch(Exception e) {
				result= "Failure";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateCsLoan(CsLoan csLoan) {
		String result="";

		try {
			String sql ="UPDATE T_CS_LOAN\n" + 
					"SET T_CS_ID=?, LOAN_ORIGIN=?, SOURCE_NAME=?, SOURCE_ADDRESS=?, LOAN_AMOUNT=?, CURRENCY=?, EXCHANGE_RATE=?, \n" + 
					"REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
					"WHERE ID=?";
			
					
					if(jdbcOperations.update(sql, setCsLoan(csLoan)) > 0){
				result = csLoan.getId();				
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
	public String addCsQuantumAllocation(CsQuantumAllocation csQuantumAllocation) {
		String result="";
		try {
			csQuantumAllocation.setId(generateId());
			String sql ="INSERT INTO T_CS_QUANTUM_ALLOCATION\n" + 
					"(T_CS_ID, BUYER_COMP_SERV_ID, BUYER_ORG_ID, CAPTIVE_COMPANY_NAME, QUANTUM, INJECTING_VOLTAGE_CODE, DRAWAL_VOLTAGE_CODE, REMARKS,\n" + 
					"CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, ENABLED, ID)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?)";
			
			if(jdbcOperations.update(sql,setCsQuantumAllocation(csQuantumAllocation))>0) {
				result= csQuantumAllocation.getId();
			}else {
				result = "Failure";
			}
			}catch(Exception e) {
				result= "Failure";
				e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public String updateCsQuantumAllocation(CsQuantumAllocation csQuantumAllocation) {
		String result="";
		
		try {
			String sql ="UPDATE T_CS_QUANTUM_ALLOCATION\n" + 
					"SET T_CS_ID=?, BUYER_COMP_SERV_ID=?, BUYER_ORG_ID=?, CAPTIVE_COMPANY_NAME=?, QUANTUM=?, INJECTING_VOLTAGE_CODE=?,\n" + 
					"DRAWAL_VOLTAGE_CODE=?, REMARKS=?, CREATED_BY=?, CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'), ENABLED=?\n" + 
					"WHERE ID=?";
			
					
					if(jdbcOperations.update(sql, setCsQuantumAllocation(csQuantumAllocation)) > 0){
				result = csQuantumAllocation.getId();				
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

	
	final class CsMapper implements RowMapper<Cs>{

		@Override
		public Cs mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Cs cs = new Cs();
			cs.setId(resultSet.getString("ID"));
			cs.setCode(resultSet.getString("CODE"));		
			cs.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			cs.setSellerCompanyId(resultSet.getString("SELLER_COMPANY_ID"));
			cs.setSellerOrgId(resultSet.getString("SELLER_EDC_ID"));
			cs.setAgreementDate(resultSet.getString("AGREEMENT_DATE"));
			cs.setEffectiveDate(resultSet.getString("EFFECTIVE_DATE"));
			cs.setFromDate(resultSet.getString("FROM_DATE"));
			cs.setToDate(resultSet.getString("TO_DATE"));		
			cs.setFromMonth(resultSet.getString("FROM_MONTH"));
			cs.setToMonth(resultSet.getString("TO_MONTH"));
			cs.setFromYear(resultSet.getString("FROM_YEAR"));
			cs.setToYear(resultSet.getString("TO_YEAR"));		
			cs.setSellerSubstationId(resultSet.getString("SELLER_SUBSTATION_ID"));
			cs.setSellerFeederId(resultSet.getString("SELLER_FEEDER_ID"));
			cs.setAgreementPeriodCode(resultSet.getString("AGREEMENT_PERIOD_CODE"));
			cs.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));	
			cs.setStatusCode(resultSet.getString("STATUS_CODE"));
			cs.setVoltageCode(resultSet.getString("VOLTAGE_CODE"));
			cs.setVoltageName(resultSet.getString("VOLTAGE_NAME"));
			cs.setSellerCompanyCode(resultSet.getString("SELLER_COMP_CODE"));
			cs.setSellerOrgCode(resultSet.getString("SELLER_ORG_CODE"));
			cs.setSellerSubstationCode(resultSet.getString("SELLER_SUBSTATION_CODE"));
			cs.setSellerFeederCode(resultSet.getString("SELLER_FEEDER_CODE"));
			cs.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
			cs.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			cs.setSellerSubstationName(resultSet.getString("SELLER_SUBSTATION_NAME"));
			cs.setSellerFeederName(resultSet.getString("SELLER_FEEDER_NAME"));
			cs.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			cs.setIdTotalCost(resultSet.getString("ID_TOTAL_COST"));
			cs.setIdTotalCurrency(resultSet.getString("ID_TOTAL_CURRENCY"));
			cs.setIdTotalExchangeRate(resultSet.getString("ID_TOTAL_EXCHANGE_RATE"));
			cs.setApprovedCapacity(resultSet.getString("APPROVED_CAPACITY"));
			cs.setEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			cs.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));
			return cs;
		}
		
	}
	
	final class CsQuantumAllocationMapper implements RowMapper<CsQuantumAllocation>{

		public CsQuantumAllocationMapper() {
			super();
		}
		@Override
		public CsQuantumAllocation mapRow(ResultSet resultSet, int rownum) throws SQLException {
			CsQuantumAllocation csQuantumAllocation = new CsQuantumAllocation();
			
			
			csQuantumAllocation.setCsId(resultSet.getString("T_CS_ID"));
	        csQuantumAllocation.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			csQuantumAllocation.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
			csQuantumAllocation.setCaptiveCompanyName(resultSet.getString("CAPTIVE_COMPANY_NAME"));
			csQuantumAllocation.setQuantum(resultSet.getString("QUANTUM"));			
			csQuantumAllocation.setInjectionVoltageCode(resultSet.getString("INJECTING_VOLTAGE_CODE"));
			csQuantumAllocation.setInjectionVoltageName(resultSet.getString("INJECTION_VOLTAGE_NAME"));
			csQuantumAllocation.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			csQuantumAllocation.setDrawalVoltageName(resultSet.getString("DRAWAL_VOLTAGE_NAME"));
			csQuantumAllocation.setCreatedBy(resultSet.getString("CREATED_BY"));
			csQuantumAllocation.setCreatedDate(resultSet.getString("CREATED_DATE"));
			csQuantumAllocation.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			csQuantumAllocation.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
			csQuantumAllocation.setEnabled( resultSet.getString("ENABLED"));
			csQuantumAllocation.setRemarks(resultSet.getString("REMARKS"));
			csQuantumAllocation.setBuyerCompServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			csQuantumAllocation.setBuyerCompId(resultSet.getString("BUYER_COMP_ID"));
			csQuantumAllocation.setBuyerCompName(resultSet.getString("BUYER_COMP_NAME"));
			csQuantumAllocation.setBuyerCompCode(resultSet.getString("BUYER_COMP_CODE"));
			csQuantumAllocation.setBuyerOrgCode(resultSet.getString("BUYER_ORG_CODE"));
			csQuantumAllocation.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			csQuantumAllocation.setBuyerSubstationId(resultSet.getString("BUYER_SUBSTATION_ID"));
			csQuantumAllocation.setBuyerSubstationCode(resultSet.getString("BUYER_SUBSTATION_CODE"));
			csQuantumAllocation.setBuyerSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			csQuantumAllocation.setBuyerFeederId(resultSet.getString("BUYER_FEEDER_ID"));
			csQuantumAllocation.setBuyerFeederCode(resultSet.getString("BUYER_FEEDER_CODE"));
			csQuantumAllocation.setBuyerFeederName(resultSet.getString("BUYER_FEEDER_NAME"));
	        csQuantumAllocation.setId(resultSet.getString("ID"));
	        
			return  csQuantumAllocation;
		}
	}
	
	final class CsCaptiveUserContributionMapper implements RowMapper<CsCaptiveUserContribution>{

		public CsCaptiveUserContributionMapper() {
			super();
		}
		@Override
		public CsCaptiveUserContribution mapRow(ResultSet resultSet, int rownum) throws SQLException {
			CsCaptiveUserContribution csCaptiveUserContribution = new CsCaptiveUserContribution();			
			csCaptiveUserContribution.setCsId(resultSet.getString("T_CS_ID"));
			csCaptiveUserContribution.setClassOfShareHolder(resultSet.getString("CLASS_OF_SHAREHOLDER"));
			csCaptiveUserContribution.setNoOfEquityShares(resultSet.getString("NUMBER_OF_EQUTIY_SHARES"));
			csCaptiveUserContribution.setValueOfEquityShares(resultSet.getString("VALUE_OF_EQUTIY_SHARES"));
			csCaptiveUserContribution.setAmountOfEquityShares(resultSet.getString("AMOUNT_OF_EQUTIY_SHARES"));
			csCaptiveUserContribution.setNoOfVotingRights(resultSet.getString("NUMBER_OF_VOTING_RIGHTS"));
			csCaptiveUserContribution.setPercentageHoldingInEquityShares(resultSet.getString("PERCT_IN_EQUTIY_SHARES"));
			csCaptiveUserContribution.setPercentageHoldingInVotingRights( resultSet.getString("PERCT_IN_VOTING_RIGHTS"));
			csCaptiveUserContribution.setPercentageHoldingInVotingWithEquity(resultSet.getString("PERCT_IN_VOTING_WITH_EQUITY"));
			csCaptiveUserContribution.setRemarks(resultSet.getString("REMARKS"));
			csCaptiveUserContribution.setEnabled(resultSet.getString("ENABLED"));
	        csCaptiveUserContribution.setCreatedBy(resultSet.getString("CREATED_BY"));
			csCaptiveUserContribution.setCreatedDate(resultSet.getString("CREATED_DATE"));
			csCaptiveUserContribution.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			csCaptiveUserContribution.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
	        csCaptiveUserContribution.setId(resultSet.getString("ID"));
	        
			return  csCaptiveUserContribution;
		}
	}
	
	final class CsEquityShareVotingRightMapper implements RowMapper<CsEquityShareVotingRight>{

		public CsEquityShareVotingRightMapper() {
			super();
		}
		@Override
		public CsEquityShareVotingRight mapRow(ResultSet resultSet, int rownum) throws SQLException {
			CsEquityShareVotingRight csQuityShareVotingRight = new CsEquityShareVotingRight();
			
			
			csQuityShareVotingRight.setCsId(resultSet.getString("T_CS_ID"));
	        csQuityShareVotingRight.setClassOfEquityShares(resultSet.getString("CLASS_OF_EQUTIY_SHARES"));
			csQuityShareVotingRight.setNoOfEquityShares(resultSet.getString("NUMBER_OF_EQUTIY_SHARES"));
			csQuityShareVotingRight.setValueOfEquityShares(resultSet.getString("VALUE_OF_EQUTIY_SHARES"));
			csQuityShareVotingRight.setAmountOfEquityShares(resultSet.getString("AMOUNT_OF_EQUTIY_SHARES"));
			csQuityShareVotingRight.setNoOfVotingRights(resultSet.getString("NUMBER_OF_VOTING_RIGHTS"));
			csQuityShareVotingRight.setPercentageHoldingInEquityShares(resultSet.getString("PERCT_IN_EQUTIY_SHARES"));
			csQuityShareVotingRight.setPercentageHoldingInVotingRights(resultSet.getString("PERCT_IN_VOTING_RIGHTS"));
			csQuityShareVotingRight.setPercentageHoldingInVotingWithEquity( resultSet.getString("PERCT_IN_VOTING_WITH_EQUITY"));
			csQuityShareVotingRight.setRemarks(resultSet.getString("REMARKS"));
			csQuityShareVotingRight.setCreatedBy(resultSet.getString("CREATED_BY"));
			csQuityShareVotingRight.setCreatedDate(resultSet.getString("CREATED_DATE"));
			csQuityShareVotingRight.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			csQuityShareVotingRight.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
			csQuityShareVotingRight.setEnabled(resultSet.getString("ENABLED"));
	        csQuityShareVotingRight.setId(resultSet.getString("ID"));
	        
			return  csQuityShareVotingRight;
		}
	}
	
	final class CsLoanMapper implements RowMapper<CsLoan>{

		public CsLoanMapper() {
			super();
		}
		@Override
		public CsLoan mapRow(ResultSet resultSet, int rownum) throws SQLException {
			CsLoan idCsLoan = new CsLoan();
			
			
			idCsLoan.setCsId(resultSet.getString("T_CS_ID"));
			idCsLoan.setLoanOrigin(resultSet.getString("LOAN_ORIGIN"));
			idCsLoan.setLoanSourceName(resultSet.getString("SOURCE_NAME"));
			idCsLoan.setLoanSourceAddress(resultSet.getString("SOURCE_ADDRESS"));
			idCsLoan.setLoanAmount(resultSet.getString("LOAN_AMOUNT"));
			idCsLoan.setLoanCurrency(resultSet.getString("CURRENCY"));
			idCsLoan.setLoanExchangeRate(resultSet.getString("EXCHANGE_RATE"));
			idCsLoan.setRemarks( resultSet.getString("REMARKS"));
	        idCsLoan.setCreatedBy(resultSet.getString("CREATED_BY"));
			idCsLoan.setCreatedDate(resultSet.getString("CREATED_DATE"));
			idCsLoan.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			idCsLoan.setModifiedDate(resultSet.getString("MODIFIED_DATE"));
			idCsLoan.setEnabled(resultSet.getString("ENABLED"));
	        idCsLoan.setId(resultSet.getString("ID"));
	        
			return  idCsLoan;
		}
	}
	
	private Object[] setCsValues(Cs cs) {
		return new Object[] {		
				
			cs.getCode(),
			cs.getSellerCompanyServiceId(),
			cs.getSellerOrgId(),
			cs.getSellerCompanyId(),
			cs.getAgreementDate(),
			cs.getEffectiveDate(),
			cs.getFromDate(),
			cs.getToDate(),
			cs.getFromMonth(),
			cs.getToYear(),
			cs.getFromYear(),
			cs.getToMonth(),
			cs.getAgreementPeriodCode(),
			cs.getFlowTypeCode(),
			cs.getStatusCode(),
			cs.getIdTotalCost(),
			cs.getIdTotalCurrency(),
			cs.getIdTotalExchangeRate(),
			cs.getApprovedCapacity(),
			cs.getVoltageCode(),
			cs.getEsIntentId(),
			cs.getId()
					    
		};
	}
	
	private Object[] setCsLoan(CsLoan idCsLoan) {
		return new Object[] {		
				
				idCsLoan.getCsId(),
				idCsLoan.getLoanOrigin(),
				idCsLoan.getLoanSourceName(),
				idCsLoan.getLoanSourceAddress(),
				idCsLoan.getLoanAmount(),
				idCsLoan.getLoanCurrency(),
				idCsLoan.getLoanExchangeRate(),
				idCsLoan.getRemarks(),

				idCsLoan.getCreatedBy(),
				idCsLoan.getCreatedDate(),
				idCsLoan.getModifiedBy(),
				idCsLoan.getModifiedDate(),
				idCsLoan.getEnabled(),

				idCsLoan.getId()			    
		};
	}
	private Object[] setCsCaptiveUserContribution(CsCaptiveUserContribution csCaptiveUserContribution) {
		return new Object[] {		
				
				csCaptiveUserContribution.getCsId(),
				csCaptiveUserContribution.getClassOfShareHolder(),
				csCaptiveUserContribution.getNoOfEquityShares(),
				csCaptiveUserContribution.getValueOfEquityShares(),
				csCaptiveUserContribution.getAmountOfEquityShares(),
				csCaptiveUserContribution.getNoOfVotingRights(),
				csCaptiveUserContribution.getPercentageHoldingInEquityShares(),
				csCaptiveUserContribution.getPercentageHoldingInVotingRights(),
				csCaptiveUserContribution.getPercentageHoldingInVotingWithEquity(),
				
				csCaptiveUserContribution.getRemarks(),
				csCaptiveUserContribution.getCreatedBy(),
				csCaptiveUserContribution.getCreatedDate(),
				csCaptiveUserContribution.getModifiedBy(),
				csCaptiveUserContribution.getModifiedDate(),
				csCaptiveUserContribution.getEnabled(),

				csCaptiveUserContribution.getId()			    
		};
	}
	
	private Object[] setCsEquityShareVotingRight(CsEquityShareVotingRight csEquityShareVotingRight) {
		return new Object[] {		
				
				csEquityShareVotingRight.getCsId(),
				csEquityShareVotingRight.getClassOfEquityShares(),
				csEquityShareVotingRight.getNoOfEquityShares(),
				csEquityShareVotingRight.getValueOfEquityShares(),
				csEquityShareVotingRight.getAmountOfEquityShares(),
				csEquityShareVotingRight.getNoOfVotingRights(),
				csEquityShareVotingRight.getPercentageHoldingInEquityShares(),
				csEquityShareVotingRight.getPercentageHoldingInVotingRights(),
				csEquityShareVotingRight.getPercentageHoldingInVotingWithEquity(),
							
				csEquityShareVotingRight.getRemarks(),
				csEquityShareVotingRight.getCreatedBy(),
				csEquityShareVotingRight.getCreatedDate(),
				csEquityShareVotingRight.getModifiedBy(),
				csEquityShareVotingRight.getModifiedDate(),
				csEquityShareVotingRight.getEnabled(),

				csEquityShareVotingRight.getId()			    
		};
	}
	private Object[] setCsQuantumAllocation(CsQuantumAllocation csQuantumAllocation) {
		return new Object[] {		
				
				csQuantumAllocation.getCsId(),
				csQuantumAllocation.getBuyerCompServiceId(),
				csQuantumAllocation.getBuyerOrgId(),
				csQuantumAllocation.getCaptiveCompanyName(),
				csQuantumAllocation.getQuantum(),
				csQuantumAllocation.getInjectionVoltageCode(),
				csQuantumAllocation.getDrawalVoltageCode(),
				csQuantumAllocation.getRemarks(),
				csQuantumAllocation.getCreatedBy(),
				csQuantumAllocation.getCreatedDate(),
				csQuantumAllocation.getModifiedBy(),
				csQuantumAllocation.getModifiedDate(),
				csQuantumAllocation.getEnabled(),

				csQuantumAllocation.getId()			    
		};
	}
}
