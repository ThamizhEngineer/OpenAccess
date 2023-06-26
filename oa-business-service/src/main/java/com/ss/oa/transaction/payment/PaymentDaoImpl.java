package com.ss.oa.transaction.payment;

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
import com.ss.oa.transaction.vo.Payment;

@Scope("prototype")
@Component
public class PaymentDaoImpl extends BaseDaoJdbc implements PaymentDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Payment> getAllPayments(Map<String, String> criteria) {
		String sql="select payment.id,payment.code,payment.from_company_id,payment.invoice_number,payment.invoice_date,payment.bank_name,payment.branch_name,\n" + 
				"payment.mode_of_payment,payment.instrument_number,payment.payment_date,payment.amount,payment.remarks,\n" + 
				"payment.payment_context_code,payment.context_ref_number,payment.from_comp_service_id,payment.to_company_id,payment.to_comp_service_id,\n" + 
				"fromcompany.m_company_code as from_company_code,fromcompany.m_company_name as from_company_name,\n" + 
				"fromcompany.\"number\" as from_company_serv_number,fromcompany.m_org_id as from_comp_org_id,fromcompany.m_org_code as from_comp_org_code,fromcompany.m_org_name as from_comp_org_name,fromcompany.m_substation_id as from_comp_ss_id,fromcompany.m_substation_code as from_comp_ss_code,fromcompany.m_substation_name as from_comp_ss_name,fromcompany.m_feeder_id as from_comp_feeder_id,\n" + 
				"fromcompany.m_feeder_code as from_comp_feeder_code,fromcompany.m_feeder_name as from_comp_feeder_name,\n" + 
				"tocompany.m_company_code as to_company_code,tocompany.m_company_name as to_company_name,\n" + 
				"tocompany.\"number\" as to_company_serv_number,tocompany.m_org_id as to_comp_org_id,tocompany.m_org_code as to_comp_org_code,tocompany.m_org_name as to_comp_org_name,tocompany.m_substation_id as to_comp_ss_id,tocompany.m_substation_code as to_comp_ss_code,tocompany.m_substation_name as to_comp_ss_name,tocompany.m_feeder_id as to_comp_feeder_id,\n" + 
				"tocompany.m_feeder_code as to_comp_feeder_code,tocompany.m_feeder_name as to_comp_feeder_name from t_payment payment\n" + 
				"left join v_company_service fromcompany on payment.from_comp_service_id = fromcompany.id \n" + 
				"left join v_company_service tocompany on payment.to_comp_service_id = tocompany.id  where 1=1";
		
		if(criteria.get("payment-context-code")!=null) {
			sql += "and upper(payment.payment_context_code)= upper('"+criteria.get("payment-context-code")+"')";
		}
		if(criteria.get("context-ref-num")!=null) {
			sql += "and upper(payment.context_ref_number)= upper('"+criteria.get("context-ref-num")+"')";
		}
		System.out.println(sql);
		
		
		return  jdbcOperations.query(sql, new PaymentMapper());
	}

	@Override
	public Payment getPaymentById(String id) {
		
		Payment payment = new Payment();
		
		String sql="select payment.id,payment.code,payment.from_company_id,payment.invoice_number,payment.invoice_date,payment.bank_name,payment.branch_name,\n" + 
				"payment.mode_of_payment,payment.instrument_number,payment.payment_date,payment.amount,payment.remarks,\n" + 
				"payment.payment_context_code,payment.context_ref_number,payment.from_comp_service_id,payment.to_company_id,payment.to_comp_service_id,\n" + 
				"fromcompany.m_company_code as from_company_code,fromcompany.m_company_name as from_company_name,\n" + 
				"fromcompany.\"number\" as from_company_serv_number,fromcompany.m_org_id as from_comp_org_id,fromcompany.m_org_code as from_comp_org_code,fromcompany.m_org_name as from_comp_org_name,fromcompany.m_substation_id as from_comp_ss_id,fromcompany.m_substation_code as from_comp_ss_code,fromcompany.m_substation_name as from_comp_ss_name,fromcompany.m_feeder_id as from_comp_feeder_id,\n" + 
				"fromcompany.m_feeder_code as from_comp_feeder_code,fromcompany.m_feeder_name as from_comp_feeder_name,\n" + 
				"tocompany.m_company_code as to_company_code,tocompany.m_company_name as to_company_name,\n" + 
				"tocompany.\"number\" as to_company_serv_number,tocompany.m_org_id as to_comp_org_id,tocompany.m_org_code as to_comp_org_code,tocompany.m_org_name as to_comp_org_name,tocompany.m_substation_id as to_comp_ss_id,tocompany.m_substation_code as to_comp_ss_code,tocompany.m_substation_name as to_comp_ss_name,tocompany.m_feeder_id as to_comp_feeder_id,\n" + 
				"tocompany.m_feeder_code as to_comp_feeder_code,tocompany.m_feeder_name as to_comp_feeder_name from t_payment payment\n" + 
				"left join v_company_service fromcompany on payment.from_company_id = fromcompany.m_company_id \n" + 
				"left join v_company_service tocompany on payment.to_company_id = tocompany.m_company_id where payment.ID =?";
		
	
		payment = jdbcOperations.queryForObject(sql,new Object[]{id}, new PaymentMapper());
		 return  payment;
	}

	@Override
	public String addPayment(Payment payment) {
		String result="";
		try {
			payment.setId(generateId());
			if(payment.getCode()== null || payment.getCode().isEmpty()){
				payment.setCode(generateCode(Payment.class.getSimpleName(),""));
			}
			String sql ="INSERT INTO T_PAYMENT(CODE,FROM_COMPANY_ID,INVOICE_NUMBER,INVOICE_DATE,BANK_NAME,BRANCH_NAME,\n" + 
					"MODE_OF_PAYMENT,INSTRUMENT_NUMBER,PAYMENT_DATE,AMOUNT,REMARKS,PAYMENT_CONTEXT_CODE,\n" + 
					"CONTEXT_REF_NUMBER,FROM_COMP_SERVICE_ID,TO_COMPANY_ID,TO_COMP_SERVICE_ID,ID) VALUES (?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,\n" + 
					"?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,"
					+ "?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setPaymentValues(payment))>0) {
				result= payment.getId();
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
	public String updatePayment(String id, Payment payment) {
		String result="";
		try {
			payment.setId(id);
			String sql ="UPDATE T_PAYMENT SET CODE=?,FROM_COMPANY_ID=?,INVOICE_NUMBER=?,INVOICE_DATE=TO_DATE(?,'yyyy-mm-dd'),BANK_NAME=?,BRANCH_NAME=?,\n" + 
					"MODE_OF_PAYMENT=?,INSTRUMENT_NUMBER=?,PAYMENT_DATE=TO_DATE(?,'yyyy-mm-dd'),AMOUNT=?,REMARKS=?,PAYMENT_CONTEXT_CODE=?,CONTEXT_REF_NUMBER=?,FROM_COMP_SERVICE_ID=?,TO_COMPANY_ID=?,TO_COMP_SERVICE_ID=? WHERE ID=?" ; 
					
			
			if(jdbcOperations.update(sql,setPaymentValues(payment))>0) {
				result= payment.getId();
			}else {
				result = "Failure";
			}
			}catch(Exception e) {
				result= "Failure";
				e.printStackTrace();
			
		}
		return result;
	
	}
	
	final class PaymentMapper implements RowMapper<Payment>{
		
		

		public PaymentMapper() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public Payment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Payment payment = new Payment();
			payment.setId(resultSet.getString("ID"));
			payment.setCode(resultSet.getString("CODE"));
			payment.setFromCompanyId(resultSet.getString("FROM_COMPANY_ID"));	
			payment.setInvoiceNumber(resultSet.getString("INVOICE_NUMBER"));
			payment.setInvoiceDate(resultSet.getString("INVOICE_DATE"));
			payment.setBankName(resultSet.getString("BANK_NAME"));
			payment.setBranchName(resultSet.getString("BRANCH_NAME"));
			payment.setModeOfPayment(resultSet.getString("MODE_OF_PAYMENT"));
			payment.setInstrumentNumber(resultSet.getString("INSTRUMENT_NUMBER"));
			payment.setPaymentDate(resultSet.getString("PAYMENT_DATE"));
			payment.setAmount(resultSet.getString("AMOUNT"));
			payment.setRemarks(resultSet.getString("REMARKS"));
			payment.setPaymentContextCode(resultSet.getString("PAYMENT_CONTEXT_CODE"));
			payment.setContextRefNumber(resultSet.getString("CONTEXT_REF_NUMBER"));
			payment.setFromCompanyServiceId(resultSet.getString("from_comp_service_id"));
			payment.setToCompanyId(resultSet.getString("to_company_id"));
			payment.setToCompanyServiceId(resultSet.getString("to_comp_service_id"));
			payment.setFromCompanyCode(resultSet.getString("from_company_code"));
			payment.setFromCompanyName(resultSet.getString("from_company_name"));
			payment.setFromCompanyServiceNumber(resultSet.getString("from_company_serv_number"));
			payment.setFromCompanyOrgId(resultSet.getString("from_comp_org_id"));
			payment.setFromCompanyOrgCode(resultSet.getString("from_comp_org_code"));
			payment.setFromCompanyOrgName(resultSet.getString("from_comp_org_name"));
			payment.setFromCompanySubstationId(resultSet.getString("from_comp_ss_id"));
			payment.setFromCompanySubstationCode(resultSet.getString("from_comp_ss_code"));
			payment.setFromCompanySubstationName(resultSet.getString("from_comp_ss_name"));
			payment.setFromCompanyFeederId(resultSet.getString("from_comp_feeder_id"));
			payment.setFromCompanyFeederCode(resultSet.getString("from_comp_feeder_code"));
			payment.setFromCompanyFeederName(resultSet.getString("from_comp_feeder_name"));
			payment.setToCompanyCode(resultSet.getString("to_company_code"));
			payment.setToCompanyName(resultSet.getString("to_company_name"));
			payment.setToCompanyServiceNumber(resultSet.getString("to_company_serv_number"));
			payment.setToCompanyOrgId(resultSet.getString("to_comp_org_id"));
			payment.setToCompanyOrgCode(resultSet.getString("to_comp_org_code"));
			payment.setToCompanyOrgName(resultSet.getString("to_comp_org_name"));
			payment.setToCompanySubstationId(resultSet.getString("to_comp_ss_id"));
			payment.setToCompanySubstationCode(resultSet.getString("to_comp_ss_code"));
			payment.setToCompanySubstationName(resultSet.getString("to_comp_ss_name"));
			payment.setToCompanyFeederId(resultSet.getString("to_comp_feeder_id"));
			payment.setToCompanyFeederCode(resultSet.getString("to_comp_feeder_code"));
			payment.setToCompanyFeederName(resultSet.getString("to_comp_feeder_name"));

			return payment;
		}
		
	}

	private Object[] setPaymentValues(Payment payment) {
		return new Object[] {		
				payment.getCode(),
				payment.getFromCompanyId(),
				payment.getInvoiceNumber(),
				payment.getInvoiceDate(),
				payment.getBankName(),
				payment.getBranchName(),
				payment.getModeOfPayment(),
				payment.getInstrumentNumber(),
				payment.getPaymentDate(),
				payment.getAmount(),
				payment.getRemarks(),
				payment.getPaymentContextCode(),
				payment.getContextRefNumber(),
				payment.getFromCompanyServiceId(),
				payment.getToCompanyId(),
				payment.getToCompanyServiceId(),
				payment.getId(),			
					    
		};
	}
	
}
