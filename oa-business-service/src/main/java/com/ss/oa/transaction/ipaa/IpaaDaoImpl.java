package com.ss.oa.transaction.ipaa;

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
import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.IpaaLine;

@Scope("prototype")
@Component
public class IpaaDaoImpl extends BaseDaoJdbc implements IpaaDao {
	@Resource
	private JdbcOperations jdbcOperations;

	
	@Override
	public List<Ipaa> getAllIpaa(Map<String, String> criteria) {
		String sql="select ipaa.ID,ipaa.CODE,ipaa.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER ,companyservice.M_COMPANY_ID as SELLER_COMP_ID,\n" + 
				"								companyservice.M_COMPANY_NAME as SELLER_COMP_NAME,companyservice.M_COMPANY_CODE as SELLER_COMP_CODE,companyservice.M_ORG_ID as SELLER_ORG_ID,companyservice.M_ORG_NAME as SELLER_ORG_NAME,companyservice.M_ORG_CODE as SELLER_ORG_CODE,\n" + 
				"								ipaa.AGMT_PERIOD_CODE,ipaa.FROM_DT,ipaa.TO_DT,ipaa.IS_CAPTIVE,ipaa.STATUS_CODE,ipaa.FROM_MONTH,ipaa.FROM_YEAR,ipaa.TO_MONTH,ipaa.TO_YEAR,ipaa.ENABLED,esintent.id as T_ES_INETNT_ID, esintent.CODE as T_ES_INETNT_CODE,ipaa.FLOW_TYPE_CODE,ipaa.PROPOSED_CAPACITY\n" + 
				"								from T_INPRINCIPLE_APPLN ipaa\n" + 
				"								left join V_COMPANY_SERVICE companyservice on ipaa.SELLER_COMP_SERV_ID = companyservice.ID\n" + 
				"                                left join t_es_intent esintent on ipaa.T_ES_INTENT_ID = esintent.id where 1=1";
		
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
				sql += "and upper(companyservice.M_COMPANY_NAME)= upper('"+criteria.get("seller-company-name")+"')";
			}
			
			
			
			if(criteria.get("status")!=null) {
				sql += "and upper(ipaa.STATUS_CODE)= upper('"+criteria.get("status")+"')";
			}
			
			if(criteria.get("period")!=null) {
				sql += "and upper(ipaa.AGMT_PERIOD_CODE)= upper('"+criteria.get("period")+"')";
			}
			if(criteria.get("es-intent-code")!=null) {
				sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
			}	
			if(criteria.get("seller-company-service-id")!=null) {
				sql += "and upper(ipaa.SELLER_COMP_SERV_ID )= upper('"+criteria.get("seller-company-service-id")+"')";
			}
			if(criteria.get("seller-edc-id")!=null) {
				sql += "and upper(companyservice.M_ORG_ID)= upper('"+criteria.get("seller-edc-id")+"')";
			}
			if(criteria.get("code")!=null) {
				sql += "and upper(ipaa.CODE) like upper('%"+criteria.get("code")+"%')";
			}	
			
			
			
			
			
		
		System.out.println(sql);
		
		return  jdbcOperations.query(sql, new IpaaMapper());
		
	}

	@Override
	public Ipaa getIpaaById(String id) {
		Ipaa ipaa = new Ipaa();
		List<IpaaLine> ipaaLines =new ArrayList<IpaaLine>();
		String sql="select ipaa.ID,ipaa.CODE,ipaa.SELLER_COMP_SERV_ID,companyservice.\"number\" as SELLER_COMP_SERV_NUMBER ,companyservice.M_COMPANY_ID as SELLER_COMP_ID,\n" + 
				"								companyservice.M_COMPANY_NAME as SELLER_COMP_NAME,companyservice.M_COMPANY_CODE as SELLER_COMP_CODE,companyservice.M_ORG_ID as SELLER_ORG_ID,companyservice.M_ORG_NAME as SELLER_ORG_NAME,companyservice.M_ORG_CODE as SELLER_ORG_CODE,\n" + 
				"								ipaa.AGMT_PERIOD_CODE,ipaa.FROM_DT,ipaa.TO_DT,ipaa.IS_CAPTIVE,ipaa.STATUS_CODE,ipaa.FROM_MONTH,ipaa.FROM_YEAR,ipaa.TO_MONTH,ipaa.TO_YEAR,ipaa.ENABLED,esintent.id as T_ES_INETNT_ID, esintent.CODE as T_ES_INETNT_CODE,ipaa.FLOW_TYPE_CODE,ipaa.PROPOSED_CAPACITY\n" + 
				"								from T_INPRINCIPLE_APPLN ipaa\n" + 
				"								left join V_COMPANY_SERVICE companyservice on ipaa.SELLER_COMP_SERV_ID = companyservice.ID\n" + 
				"                                left join t_es_intent esintent on ipaa.T_ES_INTENT_ID = esintent.id where ipaa.ID =?";
		
		String sql1="SELECT ipaaLine.ID,ipaaLine.T_INPRINCIPLE_APPLN_ID,ipaaLine.BUYER_END_ORG_ID, ipaaLine.BUYER_COMP_SERV_ID,\n" + 
				"		  		ipaaLine.DRAWAL_VOLTAGE_CODE, vcompanyservice.voltage_name AS DRAWAL_VOLTAGE_DESC,\n" + 
				"		  		ipaaLine.INJECTION_PEAK_UNITS,ipaaLine.INJECTION_OFF_PEAK_UNITS,\n" + 
				"		  		ipaaLine.DRAWAL_OFF_PEAK_UNITS,ipaaLine.DRAWAL_PEAK_UNITS,to_char(ipaaLine.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(ipaaLine.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,\n" + 
				"		  		ipaaLine.STATUS_CODE,buyerstatus.VALUE_DESC AS STATUS_DESC,ipaaLine.PROPOSED_UNITS,ipaaLine.APPROVED_UNITS,ipaaLine.IS_CAPTIVE,\n" + 
				"		  		ipaaLine.REMARKS,ipaaLine.CREATED_BY,to_char(ipaaLine.CREATED_DT,'YYYY-MM-DD')CREATED_DT, ipaaLine.MODIFIED_BY,to_char(ipaaLine.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT ,\n" + 
				"		  		vcompanyservice.M_ORG_NAME AS BUYER_ORG_NAME ,\n" + 
				"		  		vcompanyservice.\"number\" AS BUYER_COMPANY_SERVICE_NUMBER ,\n" + 
				"		  		vcompanyservice.M_COMPANY_NAME AS BUYER_COMPANY_NAME,vcompanyservice.M_COMPANY_ID AS BUYER_COMPANY_ID,\n" + 
				"		  		vcompanyservice.CAPACITY AS BUYER_CAPACITY\n" + 
				"		  		FROM T_INPRINCIPLE_APPLN_LINE ipaaLine   \n" + 
				"		  		LEFT JOIN V_COMPANY_SERVICE vcompanyservice on ipaaLine.BUYER_COMP_SERV_ID = vcompanyservice.id\n" + 
				"		  		left join v_codes buyervoltage on ipaaLine.DRAWAL_VOLTAGE_CODE = buyervoltage.Value_Code  AND  buyervoltage.list_code = 'VOLTAGE_CODE' \n" + 
				"		  		LEFT JOIN v_codes buyerstatus ON ipaaLine.STATUS_CODE = buyerstatus.VALUE_CODE AND buyerstatus.LIST_CODE ='EWA_LINE_STATUS_CODE'\n" + 
				"		  		LEFT JOIN  T_INPRINCIPLE_APPLN  ipaa ON ipaaLine.T_INPRINCIPLE_APPLN_ID = ipaa.ID where ipaa.ID =? ";
		
		ipaa = jdbcOperations.queryForObject(sql,new Object[]{id}, new IpaaMapper());
		ipaaLines = jdbcOperations.query(sql1,new Object[]{id}, new IpaaLineMapper());
		ipaa.setIpaaLines(ipaaLines);
		
		return ipaa;
		
	}

	@Override
	public String addIpaa(Ipaa ipaa) {
		String result="";
		try {
			ipaa.setId(generateId());
			if(ipaa.getCode()== null || ipaa.getCode().isEmpty()){
				ipaa.setCode(generateCode(Ipaa.class.getSimpleName(),""));
			}
			String sql = "insert into T_INPRINCIPLE_APPLN(CODE,T_ES_INTENT_ID,SELLER_COMP_SERV_ID,AGMT_PERIOD_CODE,FROM_DT,TO_DT,STATUS_CODE,FROM_MONTH,FROM_YEAR,TO_MONTH,TO_YEAR,FLOW_TYPE_CODE,PROPOSED_CAPACITY,ID)values(?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setIpaaValues(ipaa))>0) {
				result = ipaa.getId();
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
	public String updateIpaa(String id, Ipaa ipaa) {
		String result="";
		try {
			ipaa.setId(id);
			String sql = "update T_INPRINCIPLE_APPLN set CODE=?,T_ES_INTENT_ID=?,SELLER_COMP_SERV_ID=?,AGMT_PERIOD_CODE=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),STATUS_CODE=?,FROM_MONTH=?,FROM_YEAR=?,TO_MONTH=?,TO_YEAR=?,FLOW_TYPE_CODE=?,PROPOSED_CAPACITY=? where id=?";
			if(jdbcOperations.update(sql,setIpaaValues(ipaa))>0) {
				result = ipaa.getId();
			}else {
				result = "Failure";
			}
		}catch(Exception e) {
			result = "Failure";
			e.printStackTrace();
		}
		
		return result;
	}
	
	private Object[] setIpaaValues(Ipaa ipaa) {
		return new Object[] {
			ipaa.getCode(),
			ipaa.getEsIntentId(),
			ipaa.getSellerCompanyServiceId(),
			ipaa.getAgmtPeriodCode(),
			ipaa.getFromDate(),
			ipaa.getToDate(),
			ipaa.getStatusCode(),
			ipaa.getFromMonth(),
			ipaa.getFromYear(),
			ipaa.getToMonth(),
			ipaa.getToYear(),	
			ipaa.getFlowTypeCode(),
			ipaa.getProposedCapacity(),
			ipaa.getId()
		};
	}
	
	final class IpaaMapper implements RowMapper<Ipaa>{
		

		public IpaaMapper() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public Ipaa mapRow(ResultSet resultSet, int intNum) throws SQLException {
			Ipaa ipaa = new Ipaa();
			ipaa.setId(resultSet.getString("ID"));
			ipaa.setCode(resultSet.getString("CODE"));
			ipaa.setSellerCompanyServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			ipaa.setSellerCompanyServiceNumber(resultSet.getString("SELLER_COMP_SERV_NUMBER"));
			ipaa.setSellerCompanyId(resultSet.getString("SELLER_COMP_ID"));
			ipaa.setSellerCompanyName(resultSet.getString("SELLER_COMP_NAME"));
			ipaa.setSellerCompanyCode(resultSet.getString("SELLER_COMP_CODE"));
			ipaa.setSellerOrgId(resultSet.getString("SELLER_ORG_ID"));
			ipaa.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			ipaa.setSellerOrgCode(resultSet.getString("SELLER_ORG_CODE"));
			ipaa.setAgmtPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
			ipaa.setFromDate(resultSet.getString("FROM_DT"));
			ipaa.setToDate(resultSet.getString("TO_DT"));
			ipaa.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
			ipaa.setStatusCode(resultSet.getString("STATUS_CODE"));
			ipaa.setFromMonth(resultSet.getString("FROM_MONTH"));
			ipaa.setFromYear(resultSet.getString("FROM_YEAR"));
			ipaa.setToMonth(resultSet.getString("TO_MONTH"));
			ipaa.setToYear(resultSet.getString("TO_YEAR"));
			ipaa.setEnabled(resultSet.getString("ENABLED"));
			ipaa.setEsIntentId(resultSet.getString("T_ES_INETNT_ID"));
			ipaa.setEsIntentCode(resultSet.getString("T_ES_INETNT_CODE"));
			ipaa.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			ipaa.setProposedCapacity(resultSet.getString("PROPOSED_CAPACITY"));
			return ipaa;
		}
		
		
	}
	


	@Override
	public String addIpaaLine(IpaaLine ipaaLine) {
		String result="";
		try {
			ipaaLine.setId(generateId());
			String sql = "INSERT INTO T_INPRINCIPLE_APPLN_LINE(T_INPRINCIPLE_APPLN_ID,BUYER_END_ORG_ID, BUYER_COMP_SERV_ID, DRAWAL_VOLTAGE_CODE, INJECTION_PEAK_UNITS,\n" + 
					"		INJECTION_OFF_PEAK_UNITS,DRAWAL_OFF_PEAK_UNITS, DRAWAL_PEAK_UNITS, APPLIED_DT, APPROVED_DT,STATUS_CODE, \n" + 
					"		REMARKS,PROPOSED_UNITS,APPROVED_UNITS,IS_CAPTIVE,ID ) \n" + 
					"		VALUES (?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setIpaaLineValues(ipaaLine))>0) {
				result= ipaaLine.getId();
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
	public String updateIpaaLine(IpaaLine ipaaLine) {
		 String result="";
			
			try {
				String sql = " UPDATE  T_INPRINCIPLE_APPLN_LINE SET T_INPRINCIPLE_APPLN_ID=?,BUYER_END_ORG_ID=?, BUYER_COMP_SERV_ID=?, DRAWAL_VOLTAGE_CODE=?, INJECTION_PEAK_UNITS=?,\n" + 
						"							INJECTION_OFF_PEAK_UNITS=?,DRAWAL_OFF_PEAK_UNITS=?, DRAWAL_PEAK_UNITS=?, APPLIED_DT=?, APPROVED_DT=?,STATUS_CODE=?,  \n" + 
						"							REMARKS=?,PROPOSED_UNITS=?,APPROVED_UNITS=?,IS_CAPTIVE=?  WHERE ID=?";
				if(jdbcOperations.update(sql,setIpaaLineValues(ipaaLine))>0) {
					result= ipaaLine.getId();
				}else {
					result = "Failue";
				}
				}catch(Exception e) {
					result= "Failue";
					e.printStackTrace();
				}
			return result;
	}
	final class IpaaLineMapper implements RowMapper<IpaaLine>{

		public IpaaLineMapper() {
			super();
		}
		@Override
		public IpaaLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
			IpaaLine ipaaLine = new IpaaLine();
			
			
			ipaaLine.setIpaaId(resultSet.getString("T_INPRINCIPLE_APPLN_ID"));
	      
			ipaaLine.setBuyerOrgId(resultSet.getString("BUYER_END_ORG_ID"));
			ipaaLine.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			ipaaLine.setBuyerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
			ipaaLine.setBuyerCompanyName(resultSet.getString("BUYER_COMPANY_NAME"));
			ipaaLine.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
			ipaaLine.setBuyerCompServiceNumber(resultSet.getString("BUYER_COMPANY_SERVICE_NUMBER"));
			ipaaLine.setBuyerCapacity(resultSet.getString("BUYER_CAPACITY"));
			ipaaLine.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
			ipaaLine.setDrawalVoltageDesc(resultSet.getString("DRAWAL_VOLTAGE_DESC"));
			ipaaLine.setDrawalPeakUnits( resultSet.getString("DRAWAL_PEAK_UNITS"));
			ipaaLine.setDrawalOffPeakUnits(resultSet.getString("DRAWAL_OFF_PEAK_UNITS"));
	 		ipaaLine.setInjectionPeakUnits(resultSet.getString("INJECTION_PEAK_UNITS"));
	 		ipaaLine.setInjectionOffPeakUnits(resultSet.getString("INJECTION_OFF_PEAK_UNITS"));
	        ipaaLine.setAppliedDate(resultSet.getString("APPLIED_DT"));
	        ipaaLine.setApprovedDate(resultSet.getString("APPROVED_DT"));
	        ipaaLine.setRemarks(resultSet.getString("REMARKS"));
	         ipaaLine.setStatusCode(resultSet.getString("STATUS_CODE"));
	         ipaaLine.setStatusDesc(resultSet.getString("STATUS_DESC"));
	         ipaaLine.setCreatedBy(resultSet.getString("CREATED_BY"));
	        ipaaLine.setCreatedDate(resultSet.getString("CREATED_DT"));
	   
	         ipaaLine.setModifiedBy(resultSet.getString("MODIFIED_BY"));
	        ipaaLine.setModifiedDate(resultSet.getString("MODIFIED_DT"));
	        ipaaLine.setProposedUnits(resultSet.getString("PROPOSED_UNITS"));
	        ipaaLine.setApprovedUnits(resultSet.getString("APPROVED_UNITS"));
	        ipaaLine.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
	        ipaaLine.setId(resultSet.getString("ID"));
	        
			return  ipaaLine;
		}
	}
	private Object[] setIpaaLineValues(IpaaLine ipaaLine) {
		return new Object[] {		
				
				ipaaLine.getIpaaId(),
				ipaaLine.getBuyerOrgId(),
				ipaaLine.getBuyerCompServiceId(),
				ipaaLine.getDrawalVoltageCode(),
				ipaaLine.getInjectionPeakUnits(),
				ipaaLine.getInjectionOffPeakUnits(),
				ipaaLine.getDrawalOffPeakUnits(),
				ipaaLine.getDrawalPeakUnits(),
				ipaaLine.getAppliedDate(),
			    ipaaLine.getApprovedDate(),
			    ipaaLine.getStatusCode(),
			    ipaaLine.getRemarks(),
			    ipaaLine.getProposedUnits(),
			    ipaaLine.getApprovedUnits(),
			    ipaaLine.getIsCaptive(),
			    ipaaLine.getId()
			    
		};
	}

}
