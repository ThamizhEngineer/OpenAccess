package com.ss.oa.transaction.ewa;

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
import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.EwaLine;

@Scope("prototype")
@Component
public class EwaDaoImpl extends BaseDaoJdbc implements EwaDao {
	
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<Ewa> getAllewas (Map<String, String> criteria) {
		 
		String sql="SELECT  ewa.ID,ewa.VERSION,ewa.CODE,ewa.EWA_APPROVAL_NUMBER,ewa.SELLER_END_ORG_ID,ewa.SELLER_COMP_SERV_ID,ewa.SELLER_IS_CAPTIVE,\n" + 
				"				ewa.AGMT_PERIOD_CODE,ewa.AGREEMENT_DT,to_char(ewa.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(ewa.TO_DT,'YYYY-MM-DD')TO_DT,ewa.TOTAL_PROPOSED_UNITS,ewa.TOTAL_INJECTION_PEAK_UNITS,ewa.TOTAL_INJECTION_OFF_PEAK_UNITS,\n" + 
				"				ewa.TOTAL_DRAWAL_PEAK_UNITS,ewa.TOTAL_DRAWAL_OFF_PEAK_UNITS,ewa.TOAL_APPROVED_UNITS,to_char(ewa.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(ewa.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,ewa.STATUS_CODE,sellerstatus.VALUE_DESC AS STATUS_DESC,\n" + 
				"				ewa.CREATED_BY,to_char(ewa.CREATED_DT,'YYYY-MM-DD')CREATED_DT,ewa.TOAL_APPROVED_UNITS,ewa.MODIFIED_BY,to_char(ewa.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,ewa.REMARKS,ewa.T_ES_INTENT_ID ,\n" + 
				"				sellercompanyservice.FUEL_TYPE_CODE AS SELLER_POWER_PLANT_FUEL_CODE,fuel.FUEL_NAME AS SELLER_POWER_PLANT_FUEL_DESC, fuel.FUEL_GROUP as FUEL_GROUPE,\n" + 
				"				sellercompanyservice.M_COMPANY_NAME AS SELLER_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMPANY_ID,sellercompanyservice.M_ORG_NAME AS SELLER_ORG_NAME,\n" + 
				"				sellercompanyservice.CAPACITY AS SELLER_CAPACITY ,sellercompanyservice.\"number\" AS SELLER_COMP_SERVICE_NUMBER,ewa.INJECTION_VOLTAGE_CODE AS SELLER_INJECTION_VOLTAGE_CODE,sellervoltage.VALUE_DESC AS SELLER_INJECTION_VOLTAGE_DESC,esintent.CODE as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,ewa.FLOW_TYPE_CODE\n" + 
				"				FROM T_EWA  ewa\n" + 
				"				left join V_COMPANY_SERVICE sellercompanyservice on ewa.SELLER_COMP_SERV_ID = sellercompanyservice.id 				\n" + 
				"				left join v_codes sellervoltage on ewa.INJECTION_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'\n" +
				"				left join M_FUEL fuel on sellercompanyservice.FUEL_TYPE_CODE=fuel.FUEL_CODE \n"+
				"                LEFT JOIN v_codes sellerstatus ON ewa.STATUS_CODE = sellerstatus.VALUE_CODE AND sellerstatus.LIST_CODE ='EWA_STATUS_CODE'\n" + 
				"                    left join T_ES_INTENT esintent on ewa.T_ES_INTENT_ID = esintent.ID  where 1=1 ";
        System.out.println(sql);
        
        if(!criteria.isEmpty())
		{
			if(criteria.get("seller-edc-id")!=null){
				sql += "and upper(ewa.SELLER_END_ORG_ID)= upper('"+criteria.get("seller-edc-id")+"')";
			}
			if(criteria.get("seller-company-service-id")!=null){
				sql += "and upper(ewa.SELLER_COMP_SERV_ID) = upper('"+criteria.get("seller-company-service-id")+"')";
			}
			if(criteria.get("seller-company-service-number")!=null){
				sql += "and upper(ewa.sellerCompServiceNumber) = upper('"+criteria.get("seller-company-service-number")+"')";
			}
			if(criteria.get("seller-company-id")!=null){
				sql += "and upper(sellercompanyservice.M_COMPANY_ID) = upper('"+criteria.get("seller-company-id")+"')";
			}
			if(criteria.get("seller-company-name")!=null){
				sql += "and upper(sellercompanyservice.M_COMPANY_NAME) = upper('"+criteria.get("seller-company-name")+"')";
			}
			if(criteria.get("status")!=null){
				sql += "and upper(ewa.statusCode) = upper('"+criteria.get("status")+"')";
			}
			if(criteria.get("from-date")!=null){
				sql += "and upper(ewa.fromDate) = upper('"+criteria.get("from-date")+"')";
			}
			if(criteria.get("to-date")!=null){
				sql += "and upper(ewa.toDate) = upper('"+criteria.get("to-date")+"')";
			}
			if(criteria.get("injection-voltage-code")!=null){
				sql += "and upper(ewa.sellerInjectionVoltageCode) = upper('"+criteria.get("injection-voltage-code")+"')";
			}
			if(criteria.get("es-intent-code")!=null) {
				sql += "and upper(esintent.CODE) like upper('%"+criteria.get("es-intent-code")+"%')";
			}	
			if(criteria.get("ewa-code")!=null) {
				sql += "and upper(ewa.CODE) like upper('%"+criteria.get("ewa-code")+"%')";
			}	
			if(criteria.get("fuel-type-code")!=null) {
				sql += "and upper(sellercompanyservice.FUEL_TYPE_CODE) like upper('%"+criteria.get("fuel-type-code")+"%')";
			}
			if(criteria.get("fuel-type-name")!=null) {
				sql += "and upper(fuel.FUEL_NAME) like upper('%"+criteria.get("fuel-type-name")+"%')";
			}
			if(criteria.get("fuel-group")!=null) {
				sql += "and upper(fuel.FUEL_GROUP) like upper('%"+criteria.get("fuel-group")+"%')";
			}
			
		}
		
		return  jdbcOperations.query(sql, new EwaMapper());
	
  }
	

	
	public Ewa getewaById(String id) {
		 Ewa ewa= new Ewa();
		 List<EwaLine> ewalines =new ArrayList<EwaLine>();
		  String sql ="SELECT  ewa.ID,ewa.VERSION,ewa.CODE,ewa.EWA_APPROVAL_NUMBER,ewa.SELLER_END_ORG_ID,ewa.SELLER_COMP_SERV_ID,ewa.SELLER_IS_CAPTIVE,\n" + 
		  		"				ewa.AGMT_PERIOD_CODE,ewa.AGREEMENT_DT,to_char(ewa.FROM_DT,'YYYY-MM-DD')FROM_DT,to_char(ewa.TO_DT,'YYYY-MM-DD')TO_DT,ewa.TOTAL_PROPOSED_UNITS,ewa.TOTAL_INJECTION_PEAK_UNITS,ewa.TOTAL_INJECTION_OFF_PEAK_UNITS,\n" + 
		  		"				ewa.TOTAL_DRAWAL_PEAK_UNITS,ewa.TOTAL_DRAWAL_OFF_PEAK_UNITS,ewa.TOAL_APPROVED_UNITS,to_char(ewa.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(ewa.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,ewa.STATUS_CODE,sellerstatus.VALUE_DESC AS STATUS_DESC,\n" + 
		  		"				ewa.CREATED_BY,to_char(ewa.CREATED_DT,'YYYY-MM-DD')CREATED_DT,ewa.TOAL_APPROVED_UNITS,ewa.MODIFIED_BY,to_char(ewa.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT,ewa.REMARKS,ewa.T_ES_INTENT_ID ,\n" + 
		  		"				sellercompanyservice.FUEL_TYPE_CODE AS SELLER_POWER_PLANT_FUEL_CODE,fuel.FUEL_NAME AS SELLER_POWER_PLANT_FUEL_DESC, fuel.FUEL_GROUP as FUEL_GROUPE,\n" + 
		  		"				sellercompanyservice.M_COMPANY_NAME AS SELLER_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID AS SELLER_COMPANY_ID,sellercompanyservice.M_ORG_NAME AS SELLER_ORG_NAME,\n" + 
		  		"				sellercompanyservice.CAPACITY AS SELLER_CAPACITY ,sellercompanyservice.\"number\" AS SELLER_COMP_SERVICE_NUMBER,ewa.INJECTION_VOLTAGE_CODE AS SELLER_INJECTION_VOLTAGE_CODE,sellervoltage.VALUE_DESC AS SELLER_INJECTION_VOLTAGE_DESC,esintent.CODE as ES_INTENT_CODE,esintent.FROM_MONTH,esintent.TO_MONTH,esintent.FROM_YEAR,esintent.TO_YEAR,ewa.FLOW_TYPE_CODE\n" + 
		  		"				FROM T_EWA  ewa\n" + 
		  		"				left join V_COMPANY_SERVICE sellercompanyservice on ewa.SELLER_COMP_SERV_ID = sellercompanyservice.id 				\n" + 
		  		"				left join M_FUEL fuel on sellercompanyservice.FUEL_TYPE_CODE=fuel.FUEL_CODE \n"+
		  		"				left join v_codes sellervoltage on ewa.INJECTION_VOLTAGE_CODE = sellervoltage.Value_Code  AND  sellervoltage.list_code = 'VOLTAGE_CODE'\n" + 
		  		"                LEFT JOIN v_codes sellerstatus ON ewa.STATUS_CODE = sellerstatus.VALUE_CODE AND sellerstatus.LIST_CODE ='EWA_STATUS_CODE'\n" + 
		  		"                    left join T_ES_INTENT esintent on ewa.T_ES_INTENT_ID = esintent.ID WHERE ewa.ID=?";
		  ewa= jdbcOperations.queryForObject(sql,new Object[]{id}, new EwaMapper());

		  
		  String sql1 = "SELECT ewaline.ID,ewaline.T_EWA_ID,ewaline.BUYER_END_ORG_ID, ewaline.BUYER_COMP_SERV_ID,\n" + 
		  		"ewaline.DRAWAL_VOLTAGE_CODE, buyervoltage.VALUE_DESC AS DRAWAL_VOLTAGE_DESC,\n" + 
		  		"ewaline.INJECTION_PEAK_UNITS,ewaline.INJECTION_OFF_PEAK_UNITS,\n" + 
		  		"ewaline.DRAWAL_OFF_PEAK_UNITS,ewaline.DRAWAL_PEAK_UNITS,to_char(ewaline.APPLIED_DT,'YYYY-MM-DD')APPLIED_DT,to_char(ewaline.APPROVED_DT,'YYYY-MM-DD')APPROVED_DT,\n" + 
		  		"ewaline.STATUS_CODE,buyerstatus.VALUE_DESC AS STATUS_DESC,ewaline.EWA_LINE_CHANGE_CODE,ewaline.PROPOSED_UNITS,ewaline.APPROVED_UNITS,ewaline.IS_CAPTIVE,\n" + 
		  		"ewaline.REMARKS,ewaline.CREATED_BY,to_char(ewaline.CREATED_DT,'YYYY-MM-DD')CREATED_DT, ewaline.MODIFIED_BY,to_char(ewaline.MODIFIED_DT,'YYYY-MM-DD')MODIFIED_DT ,\n" + 
		  		"vcompanyservice.M_ORG_NAME AS BUYER_ORG_NAME , ewa.EWA_APPROVAL_NUMBER,\n" + 
		  		"vcompanyservice.\"number\" AS BUYER_COMPANY_SERVICE_NUMBER ,\n" + 
		  		"vcompanyservice.M_COMPANY_NAME AS BUYER_COMPANY_NAME,vcompanyservice.M_COMPANY_ID AS BUYER_COMPANY_ID,\n" + 
		  		"vcompanyservice.CAPACITY AS BUYER_CAPACITY\n" + 
		  		"FROM T_EWA_LINE ewaline  \n" + 
		  		"LEFT JOIN V_COMPANY_SERVICE vcompanyservice on ewaline.BUYER_COMP_SERV_ID = vcompanyservice.id\n" + 
		  		"left join v_codes buyervoltage on ewaline.DRAWAL_VOLTAGE_CODE = buyervoltage .Value_Code  AND  buyervoltage .list_code = 'VOLTAGE_CODE' \n" + 
		  		"LEFT JOIN v_codes buyerstatus ON ewaline.STATUS_CODE = buyerstatus.VALUE_CODE AND buyerstatus.LIST_CODE ='EWA_LINE_STATUS_CODE'\n" + 
		  		"LEFT JOIN  T_EWA  ewa ON ewaline.T_EWA_ID = ewa.ID WHERE ewaline.T_EWA_ID=?";
		  
		  ewalines = jdbcOperations.query(sql1,new Object[]{id}, new EwaLineMapper());
		  ewa.setEwaLines(ewalines);
			return ewa;
		  		
	 
	}

	@Override
	public String addewa(Ewa ewa) {
		String result="";
		try {
			ewa.setId(generateId());
			if(ewa.getCode()== null || ewa.getCode().isEmpty()){
				ewa.setCode(generateCode(Ewa.class.getSimpleName(),""));
			}
			String sql = " INSERT INTO T_EWA( VERSION,CODE,EWA_APPROVAL_NUMBER, SELLER_END_ORG_ID, SELLER_COMP_SERV_ID,SELLER_IS_CAPTIVE,  \n" + 
					"				 AGMT_PERIOD_CODE,FROM_DT,TO_DT,TOTAL_PROPOSED_UNITS,TOTAL_INJECTION_PEAK_UNITS,TOTAL_INJECTION_OFF_PEAK_UNITS, \n" + 
					"				 TOTAL_DRAWAL_PEAK_UNITS,TOTAL_DRAWAL_OFF_PEAK_UNITS,TOAL_APPROVED_UNITS, APPLIED_DT, APPROVED_DT, STATUS_CODE,   \n" + 
					"				 CREATED_BY,CREATED_DT,MODIFIED_BY,MODIFIED_DT,REMARKS,T_ES_INTENT_ID,AGREEMENT_DT,INJECTION_VOLTAGE_CODE,FLOW_TYPE_CODE,ID)\n" + 
					"  values(?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?) "; 
			
			if(jdbcOperations.update(sql,setewaValues(ewa))>0) {
				result= ewa.getId();
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
	public String updateewa(String id, Ewa ewa) {
		String result="";
		ewa.setId(id);
		try {
			String sql ="		 UPDATE T_EWA SET VERSION=?,CODE=?,EWA_APPROVAL_NUMBER=?, SELLER_END_ORG_ID=?, SELLER_COMP_SERV_ID=?,SELLER_IS_CAPTIVE=?,  \n" + 
					"				 AGMT_PERIOD_CODE=?,FROM_DT=TO_DATE(?,'yyyy-mm-dd'),TO_DT=TO_DATE(?,'yyyy-mm-dd'),TOTAL_PROPOSED_UNITS=?,TOTAL_INJECTION_PEAK_UNITS=?,TOTAL_INJECTION_OFF_PEAK_UNITS=?, \n" + 
					"				 TOTAL_DRAWAL_PEAK_UNITS=?,TOTAL_DRAWAL_OFF_PEAK_UNITS=?,TOAL_APPROVED_UNITS=?, APPLIED_DT=TO_DATE(?,'yyyy-mm-dd'), APPROVED_DT=TO_DATE(?,'yyyy-mm-dd'), STATUS_CODE=?,   \n" + 
					"				 CREATED_BY=?,CREATED_DT=TO_DATE(?,'yyyy-mm-dd'),MODIFIED_BY=?,MODIFIED_DT=TO_DATE(?,'yyyy-mm-dd'),REMARKS=?,T_ES_INTENT_ID=?,AGREEMENT_DT=TO_DATE(?,'yyyy-mm-dd'),INJECTION_VOLTAGE_CODE=?,FLOW_TYPE_CODE=? WHERE ID=? ";
					
					if(jdbcOperations.update(sql, setewaValues(ewa)) > 0){
				result = ewa.getId();				
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
	public String addewaline(EwaLine ewaline) {
		String result="";
		try {
			ewaline.setId(generateId());
			String sql = "INSERT INTO T_EWA_LINE(T_EWA_ID,BUYER_END_ORG_ID, BUYER_COMP_SERV_ID, DRAWAL_VOLTAGE_CODE, INJECTION_PEAK_UNITS,\n" + 
					"		INJECTION_OFF_PEAK_UNITS,DRAWAL_OFF_PEAK_UNITS, DRAWAL_PEAK_UNITS, APPLIED_DT, APPROVED_DT,STATUS_CODE,EWA_LINE_CHANGE_CODE, \n" + 
					"		REMARKS, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT,PROPOSED_UNITS,APPROVED_UNITS,IS_CAPTIVE,ID ) \n" + 
					"		VALUES (?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?)";
			
			if(jdbcOperations.update(sql,setewalineValues(ewaline))>0) {
				result= ewaline.getId();
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
	public String updateewaline(EwaLine ewaline) {
    String result="";
		
		try {
			String sql = " UPDATE T_EWA_LINE SET T_EWA_ID=?, BUYER_END_ORG_ID=?, BUYER_COMP_SERV_ID=?, DRAWAL_VOLTAGE_CODE=?, INJECTION_PEAK_UNITS=?,\n" + 
					"		INJECTION_OFF_PEAK_UNITS=?,DRAWAL_OFF_PEAK_UNITS=?, DRAWAL_PEAK_UNITS=?, APPLIED_DT=TO_DATE(?,'yyyy-mm-dd'), APPROVED_DT=TO_DATE(?,'yyyy-mm-dd'),STATUS_CODE=?,EWA_LINE_CHANGE_CODE=?, \n" + 
					"		REMARKS=?, CREATED_BY=?, CREATED_DT=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DT =TO_DATE(?,'yyyy-mm-dd'),PROPOSED_UNITS=?,APPROVED_UNITS=?,IS_CAPTIVE=?  WHERE ID=?";
			if(jdbcOperations.update(sql, setewalineValues(ewaline)) > 0){
				result = ewaline.getId();				
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
	

	private Object[] setewaValues(Ewa ewa) {
		
	
		return new Object[] {
		
			ewa.getVersion(),
			ewa.getCode(),
			ewa.getEwaApprovalNumber(),
			ewa.getSellerOrgId(),
			ewa.getSellerCompServiceId(),
			ewa.getSellerIsCaptive(),
			ewa.getAgreementPeriodCode(),
			ewa.getFromDate(),
			ewa.getToDate(),
			ewa.getTotalProposedUnits(),
			ewa.getTotalInjectionPeakUnits(),
			ewa.getTotalInjectionOffPeakUnits(),
			ewa.getTotalDrawalPeakUnits(),
			ewa.getTotalDrawalOffPeakUnits(),
			ewa.getTotalApprovedUnits(),		
			ewa.getAppliedDate(),
			ewa.getApprovedDate(),
			ewa.getStatusCode(),
			ewa.getCreatedBy(),
			ewa.getCreatedDate(),
			ewa.getModifiedBy(),
			ewa.getModifiedDate(),
			ewa.getRemarks(),
			ewa.gettEsIntentId(),
			ewa.getAgreementDate(),
			ewa.getSellerInjectionVoltageCode(),
			ewa.getFlowTypeCode(),
			ewa.getId()
		
		};	
}
	private Object[] setewalineValues(EwaLine ewaline) {
		return new Object[] {		
				
				ewaline.getEwaId(),
				ewaline.getBuyerOrgId(),
				ewaline.getBuyerCompServiceId(),
				ewaline.getDrawalVoltageCode(),
				ewaline.getInjectionPeakUnits(),
				ewaline.getInjectionOffPeakUnits(),
				ewaline.getDrawalOffPeakUnits(),
				ewaline.getDrawalPeakUnits(),
				ewaline.getAppliedDate(),
			    ewaline.getApprovedDate(),
			    ewaline.getStatusCode(),
			    ewaline.getEwaLineChangeCode(),
			    ewaline.getRemarks(),
			    ewaline.getCreatedBy(),
			    ewaline.getCreatedDate(),
			    ewaline.getModifiedBy(),
			    ewaline.getModifiedDate(),
			    ewaline.getProposedUnits(),
			    ewaline.getApprovedUnits(),
			    ewaline.getIsCaptive(),
			    ewaline.getId()
			    
		};
	}
	
final class EwaMapper implements RowMapper<Ewa>{
		
		public  EwaMapper() {
			super();
		}
		
		@Override
		public Ewa mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Ewa ewa= new Ewa();
			
			
			ewa.setVersion(resultSet.getString("VERSION"));
			ewa.setCode(resultSet.getString("CODE"));
			ewa.setEwaApprovalNumber(resultSet.getString("EWA_APPROVAL_NUMBER"));
			ewa.setSellerOrgId(resultSet.getString("SELLER_END_ORG_ID"));
			ewa.setSellerOrgName(resultSet.getString("SELLER_ORG_NAME"));
			ewa.setSellerCompanyId(resultSet.getString("SELLER_COMPANY_ID"));
			ewa.setSellerCompanyName(resultSet.getString("SELLER_COMPANY_NAME"));
			ewa.setSellerCompServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			ewa.setSellerCompServiceNumber(resultSet.getString("SELLER_COMP_SERVICE_NUMBER"));
			ewa.setSellerCapacity(resultSet.getString("SELLER_CAPACITY"));
			ewa.setSellerPowerPlantFuelCode(resultSet.getString("SELLER_POWER_PLANT_FUEL_CODE") );
			ewa.setSellerPowerPlantFuelDesc(resultSet.getString("SELLER_POWER_PLANT_FUEL_DESC"));
			ewa.setFuelGroupe(resultSet.getString("FUEL_GROUPE"));
			ewa.setSellerInjectionVoltageCode(resultSet.getString("SELLER_INJECTION_VOLTAGE_CODE"));
            ewa.setSellerInjectionVoltageDesc(resultSet.getString("SELLER_INJECTION_VOLTAGE_DESC"));	
            ewa.setSellerIsCaptive(resultSet.getString("SELLER_IS_CAPTIVE"));
            ewa.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
            //ewa.setAgreementPeriodDesc(resultSet.getString("AGREEMENT_PERIOD_DESC") );
            ewa.setFromDate(resultSet.getString("FROM_DT"));
            ewa.setToDate(resultSet.getString("TO_DT"));
         // ewa.setPeriodDuration(resultSet.getString("PERIOD_DURATION") );
            ewa.setTotalApprovedUnits(resultSet.getString("TOAL_APPROVED_UNITS"));
            ewa.setTotalProposedUnits(resultSet.getString("TOTAL_PROPOSED_UNITS"));
            ewa.setTotalDrawalPeakUnits(resultSet.getString("TOTAL_DRAWAL_PEAK_UNITS"));
            ewa.setTotalDrawalOffPeakUnits(resultSet.getString("TOTAL_DRAWAL_OFF_PEAK_UNITS"));
            ewa.setTotalInjectionPeakUnits(resultSet.getString("TOTAL_INJECTION_PEAK_UNITS"));
            ewa.setTotalInjectionOffPeakUnits(resultSet.getString("TOTAL_INJECTION_OFF_PEAK_UNITS"));
            ewa.setAppliedDate(resultSet.getString("APPLIED_DT"));
            ewa.setApprovedDate(resultSet.getString("APPROVED_DT"));
            ewa.setStatusCode(resultSet.getString("STATUS_CODE"));
            ewa.setStatusDesc(resultSet.getString("STATUS_DESC"));
            ewa.setRemarks(resultSet.getString("REMARKS"));
            ewa.setCreatedBy(resultSet.getString("CREATED_BY"));
            ewa.setCreatedDate(resultSet.getString("CREATED_DT"));
            ewa.setModifiedBy(resultSet.getString("MODIFIED_BY"));
            ewa.setModifiedDate(resultSet.getString("MODIFIED_DT"));
            ewa.settEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
            ewa.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
            ewa.setEsIntentCode(resultSet.getString("ES_INTENT_CODE"));
            ewa.setFromMonth(resultSet.getString("FROM_MONTH"));
        	ewa.setToMonth(resultSet.getString("TO_MONTH"));
			ewa.setFromYear(resultSet.getString("FROM_YEAR"));
			ewa.setToYear(resultSet.getString("TO_YEAR"));	
			ewa.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
            ewa.setId(resultSet.getString("ID"));
            return 	ewa ;
		}
}

final class EwaLineMapper implements RowMapper<EwaLine>{

	public EwaLineMapper() {
		super();
	}
	@Override
	public EwaLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
		EwaLine ewaline = new EwaLine();
		
		
		ewaline.setEwaId(resultSet.getString("T_EWA_ID"));
        ewaline.setEwaApprovalNumber(resultSet.getString("EWA_APPROVAL_NUMBER"));
		ewaline.setBuyerOrgId(resultSet.getString("BUYER_END_ORG_ID"));
		ewaline.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
		ewaline.setBuyerCompanyId(resultSet.getString("BUYER_COMPANY_ID"));
		ewaline.setBuyerCompanyName(resultSet.getString("BUYER_COMPANY_NAME"));
		ewaline.setBuyerCompServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
		ewaline.setBuyerCompServiceNumber(resultSet.getString("BUYER_COMPANY_SERVICE_NUMBER"));
		ewaline.setBuyerCapacity(resultSet.getString("BUYER_CAPACITY"));
		ewaline.setDrawalVoltageCode(resultSet.getString("DRAWAL_VOLTAGE_CODE"));
		ewaline.setDrawalVoltageDesc(resultSet.getString("DRAWAL_VOLTAGE_DESC"));
		ewaline.setDrawalPeakUnits( resultSet.getString("DRAWAL_PEAK_UNITS"));
		ewaline.setDrawalOffPeakUnits(resultSet.getString("DRAWAL_OFF_PEAK_UNITS"));
//	//	ewaline.setLoss(resultSet.getString("LOSS"));
 		ewaline.setInjectionPeakUnits(resultSet.getString("INJECTION_PEAK_UNITS"));
 		ewaline.setInjectionOffPeakUnits(resultSet.getString("INJECTION_OFF_PEAK_UNITS"));
        ewaline.setAppliedDate(resultSet.getString("APPLIED_DT"));
        ewaline.setApprovedDate(resultSet.getString("APPROVED_DT"));
        ewaline.setRemarks(resultSet.getString("REMARKS"));
         ewaline.setStatusCode(resultSet.getString("STATUS_CODE"));
         ewaline.setStatusDesc(resultSet.getString("STATUS_DESC"));
         ewaline.setCreatedBy(resultSet.getString("CREATED_BY"));
        ewaline.setCreatedDate(resultSet.getString("CREATED_DT"));
         ewaline.setEwaLineChangeCode(resultSet.getString("EWA_LINE_CHANGE_CODE"));
     //   ewaline.setEwaLineChangeDesc(resultSet.getString("EWA_LINE_CHANGE_DESC"));
         ewaline.setModifiedBy(resultSet.getString("MODIFIED_BY"));
        ewaline.setModifiedDate(resultSet.getString("MODIFIED_DT"));
        ewaline.setProposedUnits(resultSet.getString("PROPOSED_UNITS"));
        ewaline.setApprovedUnits(resultSet.getString("APPROVED_UNITS"));
        ewaline.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
        ewaline.setId(resultSet.getString("ID"));
        
		return  ewaline;  
	}
}

}