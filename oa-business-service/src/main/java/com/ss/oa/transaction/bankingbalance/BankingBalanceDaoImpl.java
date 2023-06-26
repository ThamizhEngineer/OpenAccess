package com.ss.oa.transaction.bankingbalance;

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
import com.ss.oa.transaction.vo.BankingBalance;

@Scope("prototype")
@Component
public class BankingBalanceDaoImpl extends BaseDaoJdbc implements BankingBalanceDao {
	
	@Resource
	private JdbcOperations jdbcOperations;	
	
	@Override
	public List<BankingBalance> getAllBankingBalances(Map<String, String> criteria){
		
		String sql= "SELECT banking.ID,banking.M_COMPANY_ID,banking.BANKING_SERVICE_ID,banking.C1,banking.C2,banking.C3,banking.C4,banking.C5,banking.REMARKS,\n" + 
				"banking.CREATED_BY,banking.CREATED_DT,banking.MODIFIED_BY,banking.MODIFIED_DT,sellercompanyservice.BANKING_SERVICE_NUMBER,\n" + 
				"sellercompanyservice.M_ORG_ID,sellercompanyservice.M_COMPANY_NAME,sellercompanyservice.M_ORG_NAME,sellercompanyservice.M_SUBSTATION_ID,\n" + 
				"sellercompanyservice.M_SUBSTATION_NAME,sellercompanyservice.\"number\" AS seller_company_service_number,\n" + 
				"sellercompanyservice.ID AS SELLER_COMP_SERVICE_ID,banking.month,banking.year,banking.CURR_C1,banking.CURR_C2,banking.CURR_C3,banking.CURR_C4,banking.CURR_C5,"
				+ "banking.CALCULATED FROM T_BANKING_BALANCE banking\n" + 
				"LEFT JOIN V_COMPANY_SERVICE sellercompanyservice ON  banking.BANKING_SERVICE_ID = sellercompanyservice.BANKING_SERVICE_ID AND sellercompanyservice.COMP_SER_TYPE_CODE='03' WHERE 1=1";
				
				
		if(criteria.get("seller-org-id")!=null) {
			sql += "and upper(sellercompanyservice.M_ORG_ID)= upper('"+criteria.get("seller-org-id")+"')";
		}
		
		if(criteria.get("seller-company-service-number")!=null) {
			sql += "and upper(sellercompanyservice.\"number\" )= upper('"+criteria.get("seller-company-service-number")+"')";
		}
		
		if(criteria.get("seller-company-service-id")!=null) {
			sql += "and upper(sellercompanyservice.id )= upper('"+criteria.get("seller-company-service-id")+"')";
		}
		
		
		if(criteria.get("company-id")!=null) {
			sql += "and upper(banking.M_COMPANY_ID)= upper('"+criteria.get("company-id")+"')";
		}
		
		if(criteria.get("banking-service-number")!=null) {
			sql += "and upper(sellercompanyservice.BANKING_SERVICE_NUMBER)= upper('"+criteria.get("banking-service-number")+"')";
		}
		
		
		if(criteria.get("created-date")!=null) {
			sql += "and upper(banking.CREATED_DT)= upper('"+criteria.get("created-date")+"')";
		}
		
		if(criteria.get("modified-date")!=null) {
			sql += "and upper(banking.MODIFIED_DT)= upper('"+criteria.get("modified-date")+"')";
		}
		
		if(criteria.get("month")!=null) {
			sql += "and upper(banking.month)= upper('"+criteria.get("month")+"')";
		}
		
		if(criteria.get("year")!=null) {
			sql += "and upper(banking.year)= upper('"+criteria.get("year")+"')";
		}
		
		return (ArrayList<BankingBalance>) jdbcOperations.query(sql,new BankingBalanceMapper());

	}
	
	@Override
	public BankingBalance getBankingBalancesById(String id) {
		
		
		BankingBalance bankingBalance = new BankingBalance();
		
		String sql = "SELECT banking.ID,banking.M_COMPANY_ID,banking.BANKING_SERVICE_ID,banking.C1,banking.C2,banking.C3,banking.C4,banking.C5,banking.REMARKS,\n" + 
				"banking.CREATED_BY,banking.CREATED_DT,banking.MODIFIED_BY,banking.MODIFIED_DT,sellercompanyservice.BANKING_SERVICE_NUMBER,\n" + 
				"sellercompanyservice.M_ORG_ID,sellercompanyservice.M_COMPANY_NAME,sellercompanyservice.M_ORG_NAME,sellercompanyservice.M_SUBSTATION_ID,\n" + 
				"sellercompanyservice.M_SUBSTATION_NAME,sellercompanyservice.\"number\" AS seller_company_service_number,\n" + 
				"sellercompanyservice.ID AS SELLER_COMP_SERVICE_ID,banking.month,banking.year,banking.CURR_C1,banking.CURR_C2,banking.CURR_C3,banking.CURR_C4,banking.CURR_C5,"
				+ "banking.CALCULATED FROM T_BANKING_BALANCE banking\n" + 
				"LEFT JOIN V_COMPANY_SERVICE sellercompanyservice ON  banking.BANKING_SERVICE_ID = sellercompanyservice.BANKING_SERVICE_ID AND sellercompanyservice.COMP_SER_TYPE_CODE='03' WHERE banking.ID=?"; 
				
				
		bankingBalance = jdbcOperations.queryForObject(sql,new Object[]{id},new BankingBalanceMapper());

		return bankingBalance;
	}
	
	@Override
	public String addBankingBalance(BankingBalance bankingBalance) {
		String result="";
		
		try {
			bankingBalance.setId(generateId());
			String sql ="INSERT INTO T_BANKING_BALANCE (M_COMPANY_ID,BANKING_SERVICE_ID,C1,C2,C3,C4,C5,REMARKS,CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,MONTH,YEAR,CURR_C1,CURR_C2,CURR_C3,CURR_C4,CURR_C5,CALCULATED,ID) VALUES (?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setBankingBalanceValues(bankingBalance))>0) {
				result = bankingBalance.getId();
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
	public String updateBankingBalance(String id, BankingBalance bankingBalance) {
		String result="";
		
		try {

			String sql ="UPDATE T_BANKING_BALANCE SET M_COMPANY_ID=?,BANKING_SERVICE_ID=?,C1=?,C2=?,C3=?,C4=?,C5=?,REMARKS=?,CREATED_BY=?,CREATED_DT=TO_DATE(?,'YYYY-MM-DD'),MODIFIED_BY=?,MODIFIED_DT=TO_DATE(?,'YYYY-MM-DD'),MONTH=?,YEAR=?,CURR_C1=?,CURR_C2=?,CURR_C3=?,CURR_C4=?,CURR_C5=?,CALCULATED=? WHERE ID=?";
			if(jdbcOperations.update(sql,setBankingBalanceValues(bankingBalance)) > 0){
				result =bankingBalance.getId();
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
	
	private Object[] setBankingBalanceValues(BankingBalance bankingBalance){
		return new Object[]{
				
            
				bankingBalance.getBankingCompanyId(),
				bankingBalance.getBankingServiceId(),
				bankingBalance.getC1(),
				bankingBalance.getC2(),
				bankingBalance.getC3(),
				bankingBalance.getC4(),
				bankingBalance.getC5(),
				bankingBalance.getRemarks(),
				bankingBalance.getCreatedBy(),
				bankingBalance.getCreatedDate(),
				bankingBalance.getModifiedBy(),
				bankingBalance.getModifiedDate(),
				bankingBalance.getMonth(),
				bankingBalance.getYear(),
				bankingBalance.getCurrC1(),
				bankingBalance.getCurrC2(),
				bankingBalance.getCurrC3(),
				bankingBalance.getCurrC4(),
				bankingBalance.getCurrC5(),
				bankingBalance.getCalculated(),
				bankingBalance.getId()

				
				};
	}
	
	
	  public class BankingBalanceMapper implements RowMapper<BankingBalance>{

		  public BankingBalanceMapper() {
		  super();
		  }

		  @Override
		  public BankingBalance mapRow(ResultSet resultSet, int rownum) throws SQLException {

			  BankingBalance bankingBalance = new BankingBalance();

			  bankingBalance.setId(resultSet.getString("ID"));
			  bankingBalance.setBankingCompanyId(resultSet.getString("M_COMPANY_ID"));
			  bankingBalance.setBankingServiceId(resultSet.getString("BANKING_SERVICE_ID"));
			  bankingBalance.setC1(resultSet.getString("C1"));
			  bankingBalance.setC2(resultSet.getString("C2"));
			  bankingBalance.setC3(resultSet.getString("C3"));
			  bankingBalance.setC4(resultSet.getString("C4"));
			  bankingBalance.setC5(resultSet.getString("C5"));
			  bankingBalance.setRemarks(resultSet.getString("REMARKS"));
			  bankingBalance.setCreatedBy(resultSet.getString("CREATED_BY"));
			  bankingBalance.setCreatedDate(resultSet.getString("CREATED_DT"));
			  bankingBalance.setModifiedBy(resultSet.getString("MODIFIED_BY"));
			  bankingBalance.setModifiedDate(resultSet.getString("MODIFIED_DT"));

			  
			  
			  bankingBalance.setBankingServiceNumber(resultSet.getString("BANKING_SERVICE_NUMBER"));
			  bankingBalance.setSellerOrgId(resultSet.getString("M_ORG_ID"));
			  bankingBalance.setSellerSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			  bankingBalance.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERVICE_ID"));
			  bankingBalance.setSellerCompanyServiceNumber(resultSet.getString("seller_company_service_number"));
			  bankingBalance.setSellerOrgName(resultSet.getString("M_ORG_NAME"));
			  bankingBalance.setSellerSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			  bankingBalance.setCompanyName(resultSet.getString("M_COMPANY_NAME"));
			  bankingBalance.setMonth(resultSet.getString("MONTH"));
			  bankingBalance.setYear(resultSet.getString("YEAR"));
			  bankingBalance.setCurrC1(resultSet.getString("CURR_C1"));
			  bankingBalance.setCurrC2(resultSet.getString("CURR_C2"));
			  bankingBalance.setCurrC3(resultSet.getString("CURR_C3"));
			  bankingBalance.setCurrC4(resultSet.getString("CURR_C4"));
			  bankingBalance.setCurrC5(resultSet.getString("CURR_C5"));
			  bankingBalance.setCalculated(resultSet.getString("CALCULATED"));
			 

		 

		  return bankingBalance;
		  }

		  }
}
