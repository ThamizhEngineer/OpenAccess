package com.ss.oa.transaction.epa;

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
import com.ss.oa.transaction.vo.Epa;
import com.ss.oa.transaction.vo.EpaLine;

@Scope("prototype")
@Component
public class EpaDaoImpl extends BaseDaoJdbc implements EpaDao {

	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<Epa> getAllEpas (Map<String, String> criteria) {
		
		String sql="SELECT  epa.ID,epa.CODE,epa.SELLER_COMP_SERV_ID,epa.SELLER_END_ORG_ID,sellerorg.NAME AS SELLER_END_ORG_NAME,epa.AGMT_PERIOD_CODE,epa.FROM_DT,epa.TO_DT,epa.APPLIED_DT,epa.APPROVED_DT,\n" + 
				"						  				  				epa.STATUS_CODE,epa.AGREEMENT_NUMBER,epa.EPA_APPROVAL_NUMBER,epa.REMARKS,epa.CREATED_BY,epa.MODIFIED_BY,epa.MODIFIED_DT,\n" + 
				"						  				  				epa.T_ES_INTENT_ID,epa.SELLER_IS_CAPTIVE,epa.AGREEMENT_DT,sellercompanyservice.M_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID,sellercompanyservice.COMP_SER_TYPE_CODE,\n" + 
				"						  				  				sellercompanyservice.\"number\"  AS SELLER_COMP_SERVICE_NUMBER,sellercompanyservice.M_SUBSTATION_ID,sellercompanyservice.M_SUBSTATION_CODE,sellercompanyservice.M_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_ID,sellercompanyservice.M_FEEDER_CODE,sellercompanyservice.M_FEEDER_NAME,\n" + 
				"						  				  				sellercompanyservice.VOLTAGE_CODE,sellercompanyservice.VOLTAGE_NAME,powerplant.TOTAL_CAPACITY,epa.PROPOSED_TOTAL_UNITS,epa.APPROVED_TOTAL_UNITS,\n" + 
				"						  		                        powerplant.FUEL_TYPE_CODE as FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE,powerplant.DISTRICT_CODE,districtcode.VALUE_DESC as DISTRICT_NAME,epa.C1,epa.C2,epa.C3,epa.C4,epa.C5,epa.PEAK_UNITS,epa.OFF_PEAK_UNITS,\n" + 
				"				                                       epa.FROM_MONTH,epa.TO_YEAR,epa.FROM_YEAR,epa.TO_MONTH,epa.BUYER_COMP_SERV_ID,\n" + 
				"				                                        epa.FLOW_TYPE_CODE,epa.INTERVAL_TYPE_CODE,epa.SHARE_PERCENTAGE,epa.VOLTAGE_CODE,districtcode.VALUE_DESC as VOLTAGE_NAME,\n" + 
				"				                                       buyercompanyservice.\"number\"as BUYER_COMP_SERV_NUMBER, buyercompanyservice.M_COMPANY_ID as BUYER_COMP_ID, buyercompanyservice.M_COMPANY_NAME as BUYER_COMP_NAME, buyercompanyservice.M_COMPANY_CODE as BUYER_COMP_CODE, buyercompanyservice.M_ORG_ID as BUYER_ORG_ID,buyercompanyservice.M_ORG_CODE as BUYER_ORG_CODE, buyercompanyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
				"				                                        buyercompanyservice.M_SUBSTATION_ID as BUYER_SUBSTATION_ID, buyercompanyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, buyercompanyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME,buyercompanyservice.M_FEEDER_ID as BUYER_FEEDER_ID, buyercompanyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , buyercompanyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,\n" + 
				"                                                        esintent.code as T_ES_INTENT_CODE\n" + 
				"						  				  				FROM T_EPA epa\n" + 
				"				                                        LEFT JOIN V_COMPANY_SERVICE sellercompanyservice ON epa.SELLER_COMP_SERV_ID = sellercompanyservice.ID \n" + 
				"						  				  				LEFT JOIN M_ORG sellerorg ON epa.SELLER_END_ORG_ID = sellerorg.ID\n" + 
				"						  				  				LEFT JOIN M_POWERPLANT powerplant ON epa.SELLER_COMP_SERV_ID = powerplant.M_SERVICE_ID\n" + 
				"						  		                        LEFT JOIN V_CODES districtcode on powerplant.DISTRICT_CODE = districtcode.VALUE_CODE and districtcode.LIST_CODE = 'DISTRICT_CODE'\n" + 
				"                                                       LEFT JOIN M_FUEL fuel on powerplant.FUEL_TYPE_CODE =fuel.FUEL_CODE \n" +
//				"						  		                        LEFT JOIN V_CODES fueltypecode on powerplant.FUEL_TYPE_CODE = fueltypecode.VALUE_CODE and fueltypecode.LIST_CODE = 'FUEL_TYPE_CODE'\n" + 
				"				                                         LEFT JOIN V_CODES voltagecode on epa.VOLTAGE_CODE = voltagecode.VALUE_CODE and voltagecode.LIST_CODE = 'VOLTAGE_CODE'\n" + 
				"				                                        Left join v_company_service buyercompanyservice on epa.BUYER_COMP_SERV_ID = buyercompanyservice.id\n" + 
				"                                                        left join T_ES_INTENT esintent on epa.T_ES_INTENT_ID = esintent.id WHERE 1=1";
		System.out.println(sql);
		
        if(!criteria.isEmpty())
		{
			if(criteria.get("seller-org-id")!=null){
				sql += "and upper(epa.SELLER_END_ORG_ID)= upper('"+criteria.get("seller-org-id")+"')";
			}
			if(criteria.get("seller-company-service-number")!=null){
				sql += "and upper(vcompanyservice.sellerCompServiceNumber) = upper('"+criteria.get("seller-company-service-number")+"')";
			}
			if(criteria.get("es-intent-id")!=null){
				sql += "and upper(epa.tEsIntentId) = upper('"+criteria.get("es-intent-id")+"')";
			}
			if(criteria.get("agm-date")!=null){
				sql += "and upper(epa.agreementDate) = upper('"+criteria.get("agm-date")+"')";
			}
			if(criteria.get("agm-period-code")!=null){
				sql += "and upper(epa.agreementPeriodCode) = upper('"+criteria.get("agm-period-code")+"')";
			}
			if(criteria.get("agreement-number")!=null){
				sql += "and upper(epa.agreementNumber) = upper('"+criteria.get("agreement-number")+"')";
			}
			if(criteria.get("status")!=null){
				sql += "and upper(epa.statusCode) = upper('"+criteria.get("status")+"')";
			}
			if(criteria.get("code")!=null){
				sql += "and upper(epa.code) like upper('%"+criteria.get("code")+"%')";
			}
			if(criteria.get("es-intent-code")!=null){
				sql += "and upper(esintent.code) like upper('%"+criteria.get("es-intent-code")+"%')";
			}
			if(criteria.get("seller-company-service-id")!=null){
				sql += "and upper(epa.SELLER_COMP_SERV_ID) = upper('"+criteria.get("seller-company-service-id")+"')";
			}
			if(criteria.get("fuel-type-code")!=null){
				sql += "and upper(powerplant.FUEL_TYPE_CODE) = upper('"+criteria.get("fuel-type-code")+"')";
			}
			if(criteria.get("fuel-type-name")!=null){
				sql += "and upper(fuel.FUEL_NAME) = upper('"+criteria.get("fuel-type-name")+"')";
			}
			if(criteria.get("fuel-group")!=null){
				sql += "and upper(fuel.FUEL_GROUP) = upper('"+criteria.get("fuel-group")+"')";
			}
		}
		return jdbcOperations.query(sql, new EpaMapper());
		
	}
	
	public Epa getEpaById(String id) {
		 Epa epa= new Epa();
		 List<EpaLine> epalines =new ArrayList<EpaLine>();
			String sql="SELECT  epa.ID,epa.CODE,epa.SELLER_COMP_SERV_ID,epa.SELLER_END_ORG_ID,sellerorg.NAME AS SELLER_END_ORG_NAME,epa.AGMT_PERIOD_CODE,epa.FROM_DT,epa.TO_DT,epa.APPLIED_DT,epa.APPROVED_DT,\n" + 
					"						  				  				epa.STATUS_CODE,epa.AGREEMENT_NUMBER,epa.EPA_APPROVAL_NUMBER,epa.REMARKS,epa.CREATED_BY,epa.MODIFIED_BY,epa.MODIFIED_DT,\n" + 
					"						  				  				epa.T_ES_INTENT_ID,epa.SELLER_IS_CAPTIVE,epa.AGREEMENT_DT,sellercompanyservice.M_COMPANY_NAME,sellercompanyservice.M_COMPANY_ID,sellercompanyservice.COMP_SER_TYPE_CODE,\n" + 
					"						  				  				sellercompanyservice.\"number\"  AS SELLER_COMP_SERVICE_NUMBER,sellercompanyservice.M_SUBSTATION_ID,sellercompanyservice.M_SUBSTATION_CODE,sellercompanyservice.M_SUBSTATION_NAME,sellercompanyservice.M_FEEDER_ID,sellercompanyservice.M_FEEDER_CODE,sellercompanyservice.M_FEEDER_NAME,\n" + 
					"						  				  				sellercompanyservice.VOLTAGE_CODE,sellercompanyservice.VOLTAGE_NAME,powerplant.TOTAL_CAPACITY,epa.PROPOSED_TOTAL_UNITS,epa.APPROVED_TOTAL_UNITS,\n" + 
					"						  		                        powerplant.FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE,powerplant.DISTRICT_CODE,districtcode.VALUE_DESC as DISTRICT_NAME,epa.C1,epa.C2,epa.C3,epa.C4,epa.C5,epa.PEAK_UNITS,epa.OFF_PEAK_UNITS,\n" + 
					"				                                       epa.FROM_MONTH,epa.TO_YEAR,epa.FROM_YEAR,epa.TO_MONTH,epa.BUYER_COMP_SERV_ID,\n" + 
					"				                                        epa.FLOW_TYPE_CODE,epa.INTERVAL_TYPE_CODE,epa.SHARE_PERCENTAGE,epa.VOLTAGE_CODE,districtcode.VALUE_DESC as VOLTAGE_NAME,\n" + 
					"				                                       buyercompanyservice.\"number\"as BUYER_COMP_SERV_NUMBER, buyercompanyservice.M_COMPANY_ID as BUYER_COMP_ID, buyercompanyservice.M_COMPANY_NAME as BUYER_COMP_NAME, buyercompanyservice.M_COMPANY_CODE as BUYER_COMP_CODE, buyercompanyservice.M_ORG_ID as BUYER_ORG_ID,buyercompanyservice.M_ORG_CODE as BUYER_ORG_CODE, buyercompanyservice.M_ORG_NAME as BUYER_ORG_NAME,\n" + 
					"				                                        buyercompanyservice.M_SUBSTATION_ID as BUYER_SUBSTATION_ID, buyercompanyservice.M_SUBSTATION_CODE as BUYER_SUBSTATION_CODE, buyercompanyservice.M_SUBSTATION_NAME as BUYER_SUBSTATION_NAME,buyercompanyservice.M_FEEDER_ID as BUYER_FEEDER_ID, buyercompanyservice.M_FEEDER_CODE as BUYER_FEEDER_CODE , buyercompanyservice.M_FEEDER_NAME as BUYER_FEEDER_NAME,\n" + 
					"                                                        esintent.code as T_ES_INTENT_CODE\n" + 
					"						  				  				FROM T_EPA epa\n" + 
					"				                                        LEFT JOIN V_COMPANY_SERVICE sellercompanyservice ON epa.SELLER_COMP_SERV_ID = sellercompanyservice.ID \n" + 
					"						  				  				LEFT JOIN M_ORG sellerorg ON epa.SELLER_END_ORG_ID = sellerorg.ID\n" + 
					"						  				  				LEFT JOIN M_POWERPLANT powerplant ON epa.SELLER_COMP_SERV_ID = powerplant.M_SERVICE_ID\n" + 
					"						  		                        LEFT JOIN V_CODES districtcode on powerplant.DISTRICT_CODE = districtcode.VALUE_CODE and districtcode.LIST_CODE = 'DISTRICT_CODE'\n" + 
					"														LEFT JOIN M_FUEL fuel on powerplant.FUEL_TYPE_CODE = fuel.FUEL_CODE \n" +
//					"						  		                        LEFT JOIN V_CODES fueltypecode on powerplant.FUEL_TYPE_CODE = fueltypecode.VALUE_CODE and fueltypecode.LIST_CODE = 'FUEL_TYPE_CODE'\n" + 
					"				                                         LEFT JOIN V_CODES voltagecode on epa.VOLTAGE_CODE = voltagecode.VALUE_CODE and voltagecode.LIST_CODE = 'VOLTAGE_CODE'\n" + 
					"				                                        Left join v_company_service buyercompanyservice on epa.BUYER_COMP_SERV_ID = buyercompanyservice.id\n" + 
					"                                                        left join T_ES_INTENT esintent on epa.T_ES_INTENT_ID = esintent.id WHERE epa.id=?";
		  epa= jdbcOperations.queryForObject(sql,new Object[]{id}, new EpaMapper());

		  
		  String sql1 ="SELECT 	epaline.ID,epaline.T_EPA_ID,epaline.REMARKS,epaline.CREATED_BY,epaline.CREATED_DT,epaline.MODIFIED_BY,epaline.MODIFIED_DT,\n" + 
		  		"		epaline.M_GENERATOR_ID,epaline.PROPOSED_TOTAL_UNITS,epaline.APPROVED_TOTAL_UNITS,\n" + 
		  		"		generator.M_POWERPLANT_ID,generator.NAME AS GENERATOR_NAME,generator.NO_OF_UNITS,powerplant.COMMISSION_DATE,powerplant.DISTRICT_CODE,generator.capacity as GEN_CAPACITY\n" + 
		  		"		FROM T_EPA_LINES epaline\n" + 
		  		"		LEFT JOIN M_GENERATOR generator ON epaline.M_GENERATOR_ID = generator.ID\n" + 
		  		"		LEFT JOIN M_POWERPLANT powerplant ON generator.M_POWERPLANT_ID = powerplant.ID WHERE epaline.T_EPA_ID=? ";
		  
		  epalines = jdbcOperations.query(sql1,new Object[]{id}, new EpaLineMapper());
		  epa.setEpaLine(epalines);
			return epa;
		  		
	 
	}
	
	@Override
	public String addEpa(Epa epa) {
		String result="";
		try {
			epa.setId(generateId());
			if(epa.getCode()== null || epa.getCode().isEmpty()){
				epa.setCode(generateCode(Epa.class.getSimpleName(),""));
			}
			 System.out.println(epa);
			String sql ="INSERT INTO T_EPA(SELLER_COMP_SERV_ID, SELLER_END_ORG_ID, AGMT_PERIOD_CODE, FROM_DT, TO_DT, APPLIED_DT, APPROVED_DT,\n" + 
					"STATUS_CODE, AGREEMENT_NUMBER, EPA_APPROVAL_NUMBER, REMARKS,  T_ES_INTENT_ID, SELLER_IS_CAPTIVE, AGREEMENT_DT,\n" + 
					"PROPOSED_TOTAL_UNITS, APPROVED_TOTAL_UNITS,C1,C2,C3,C4,C5,\n" + 
					"PEAK_UNITS,OFF_PEAK_UNITS,FROM_MONTH,TO_YEAR,FROM_YEAR,TO_MONTH,BUYER_COMP_SERV_ID,\n" + 
					"FLOW_TYPE_CODE,INTERVAL_TYPE_CODE,SHARE_PERCENTAGE,VOLTAGE_CODE,CODE,ID)\n" + 
					"				VALUES(?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"                ?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"                ?,?,?,?,?,?,?,\n" + 
					"                 ?,?,?,?,?,?,?,\n" + 
					"                 ?,?,?,?,?,?)";
			if(jdbcOperations.update(sql,setEpaValues(epa))>0) {
				result= epa.getId();
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
	public String updateEpa(String id, Epa epa) {
		String result="";
		epa.setId(id);
		try {
			String sql ="UPDATE T_EPA SET SELLER_COMP_SERV_ID=?, SELLER_END_ORG_ID=?, AGMT_PERIOD_CODE=?, FROM_DT=TO_DATE(?,'yyyy-mm-dd'), TO_DT = TO_DATE(?,'yyyy-mm-dd'), APPLIED_DT = TO_DATE(?,'yyyy-mm-dd'), APPROVED_DT =TO_DATE(?,'yyyy-mm-dd'), \n" + 
					"			STATUS_CODE=?, AGREEMENT_NUMBER=?, EPA_APPROVAL_NUMBER=?, REMARKS=?,  T_ES_INTENT_ID=?, SELLER_IS_CAPTIVE=?, AGREEMENT_DT = TO_DATE(?,'yyyy-mm-dd'), \n" + 
					"			PROPOSED_TOTAL_UNITS=?, APPROVED_TOTAL_UNITS=?,C1=?,C2=?,C3=?,C4=?,C5=?, \n" + 
					"			PEAK_UNITS=?,OFF_PEAK_UNITS=?,FROM_MONTH=?,TO_YEAR=?,FROM_YEAR=?,TO_MONTH=?,BUYER_COMP_SERV_ID=?,\n" + 
					"			FLOW_TYPE_CODE=?,INTERVAL_TYPE_CODE=?,SHARE_PERCENTAGE=?,VOLTAGE_CODE=?,CODE=? WHERE ID=?";
					if(jdbcOperations.update(sql, setEpaValues(epa)) > 0){
				result = epa.getId();				
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
	public String addEpaline(EpaLine epaline) {
		String result="";
		try {
			epaline.setId(generateId());
			String sql = "INSERT INTO T_EPA_LINES\n" + 
					"(T_EPA_ID, REMARKS, CREATED_BY, CREATED_DT, MODIFIED_BY, MODIFIED_DT, M_GENERATOR_ID, PROPOSED_TOTAL_UNITS, APPROVED_TOTAL_UNITS,ID)\n" + 
					"VALUES(?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?)";
			if(jdbcOperations.update(sql,setEpalineValues(epaline))>0) {
				result= epaline.getId();
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
	public String updateEpaline(EpaLine epaline) {
    String result="";
		
		try {
			String sql ="UPDATE T_EPA_LINES\n" + 
					"SET T_EPA_ID=?, REMARKS=?, CREATED_BY=?, CREATED_DT=TO_DATE(?,'yyyy-mm-dd'), MODIFIED_BY=?, MODIFIED_DT=TO_DATE(?,'yyyy-mm-dd'), M_GENERATOR_ID=?, PROPOSED_TOTAL_UNITS=?, APPROVED_TOTAL_UNITS=? WHERE ID=?"; 
					if(jdbcOperations.update(sql, setEpalineValues(epaline)) > 0){
				result = epaline.getId();				
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
	
	private Object[] setEpaValues(Epa epa) {
		
		
		return new Object[] {
		
			
			epa.getSellerCompServiceId(),
			epa.getSellerOrgId(),
			epa.getAgreementPeriodCode(),
			epa.getFromDate(),
			epa.getToDate(),
			epa.getAppliedDate(),
			epa.getApprovedDate(),
			epa.getStatusCode(),
			epa.getAgreementNumber(),
			epa.getEpaAppNumber(),
			epa.getRemarks(),
			epa.getEsIntentId(),
			epa.getSellerIsCaptive(),
			epa.getAgreementDate(),
			epa.getProposedTotalUnits(),
			epa.getApprovedTotalUnits(),
			epa.getC1(),
			epa.getC2(),
			epa.getC3(),
			epa.getC4(),
			epa.getC5(),
			epa.getPeakUnits(),
			epa.getOffPeakUnits(),
	epa.getFromMonth(),
			epa.getToYear(),
			epa.getFromYear(),
			epa.getToMonth(),			
	epa.getBuyerCompanyServiceId(),
epa.getFlowTypeCode(),
epa.getIntervalTypeCode(),	
			epa.getSharePercent(),
			epa.getVoltageCode(),
			epa.getCode(),
			epa.getId(),

		
		};	
}
	
	private Object[] setEpalineValues(EpaLine epaline) {
		return new Object[] {		
				
				epaline.gettEpaId(),
				epaline.getRemarks(),
				epaline.getCreatedBy(),
				epaline.getCreatedDate(),
				epaline.getModifiedBy(),
				epaline.getModifiedDate(),
				epaline.getmGeneratorId(),
				epaline.getProposedTotalUnits(),
				epaline.getApprovedTotalUnits(),

				epaline.getId()			    
		};
	}

	
final class EpaMapper implements RowMapper<Epa>{
		
		public  EpaMapper() {
			super();
		}
		
		@Override
		public Epa mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Epa epa= new Epa();
			
			
			epa.setCode(resultSet.getString("CODE"));
			epa.setSellerCompServiceId(resultSet.getString("SELLER_COMP_SERV_ID"));
			epa.setSellerOrgId(resultSet.getString("SELLER_END_ORG_ID"));
			epa.setSellerOrgName(resultSet.getString("SELLER_END_ORG_NAME"));
            epa.setAgreementPeriodCode(resultSet.getString("AGMT_PERIOD_CODE"));
            epa.setFromDate(resultSet.getString("FROM_DT"));
            epa.setToDate(resultSet.getString("TO_DT"));
            epa.setAppliedDate(resultSet.getString("APPLIED_DT"));
            epa.setApprovedDate(resultSet.getString("APPROVED_DT"));
            epa.setStatusCode(resultSet.getString("STATUS_CODE"));
//          epa.setStatusDesc(resultSet.getString("STATUS_DESC"));
            epa.setAgreementNumber(resultSet.getString("AGREEMENT_NUMBER"));
            epa.setEpaAppNumber(resultSet.getString("EPA_APPROVAL_NUMBER"));
            epa.setRemarks(resultSet.getString("REMARKS"));
            epa.setCreatedBy(resultSet.getString("CREATED_BY"));
        
            epa.setModifiedBy(resultSet.getString("MODIFIED_BY"));
            epa.setModifiedDate(resultSet.getString("MODIFIED_DT"));
            epa.setEsIntentId(resultSet.getString("T_ES_INTENT_ID"));
			epa.setSellerIsCaptive(resultSet.getString("SELLER_IS_CAPTIVE"));
            epa.setAgreementDate(resultSet.getString("AGREEMENT_DT"));
			epa.setSellerCompanyName(resultSet.getString("M_COMPANY_NAME"));
			epa.setSellerCompanyId(resultSet.getString("M_COMPANY_ID"));
			epa.setSellerCompServiceNumber(resultSet.getString("SELLER_COMP_SERVICE_NUMBER"));
			epa.setSellerSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			epa.setSellerSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			epa.setSellerFeederId(resultSet.getString("M_FEEDER_ID"));
			epa.setSellerFeederName(resultSet.getString("M_FEEDER_NAME"));
			epa.setVoltageCode(resultSet.getString("VOLTAGE_CODE"));
			epa.setVoltageDesc(resultSet.getString("VOLTAGE_NAME"));
			epa.setTotalGenCapacity(resultSet.getString("TOTAL_CAPACITY"));
			epa.setApprovedTotalUnits(resultSet.getString("PROPOSED_TOTAL_UNITS"));
			epa.setProposedTotalUnits(resultSet.getString("APPROVED_TOTAL_UNITS"));
			epa.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
			epa.setFuelTypeName(resultSet.getString("FUEL_TYPE_NAME"));
			epa.setFuelGroupe(resultSet.getString("FUEL_GROUPE"));
			epa.setDistrictCode(resultSet.getString("DISTRICT_CODE"));
			epa.setDistrictName(resultSet.getString("DISTRICT_NAME"));
			epa.setBuyerCompanyServiceNumber(resultSet.getString("BUYER_COMP_SERV_NUMBER"));
			epa.setBuyerCompanyId(resultSet.getString("BUYER_COMP_ID"));
			epa.setBuyerCompanyName(resultSet.getString("BUYER_COMP_NAME"));
			epa.setBuyerCompanyCode(resultSet.getString("BUYER_COMP_CODE"));
			epa.setBuyerOrgCode(resultSet.getString("BUYER_ORG_CODE"));
			epa.setBuyerOrgName(resultSet.getString("BUYER_ORG_NAME"));
			epa.setBuyerSubstationId(resultSet.getString("BUYER_SUBSTATION_ID"));
			epa.setBuyerSubstationCode(resultSet.getString("BUYER_SUBSTATION_CODE"));
			epa.setBuyerSubstationName(resultSet.getString("BUYER_SUBSTATION_NAME"));
			epa.setBuyerFeederCode(resultSet.getString("BUYER_FEEDER_CODE"));
			epa.setBuyerFeederName(resultSet.getString("BUYER_FEEDER_NAME"));

			epa.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
			epa.setSharePercent(resultSet.getString("SHARE_PERCENTAGE"));
	epa.setFromMonth(resultSet.getString("FROM_MONTH"));
			epa.setToMonth(resultSet.getString("TO_MONTH"));
			epa.setFromYear(resultSet.getString("FROM_YEAR"));
			epa.setToYear(resultSet.getString("TO_YEAR"));
			epa.setBuyerCompanyServiceId(resultSet.getString("BUYER_COMP_SERV_ID"));
	epa.setBuyerOrgId(resultSet.getString("BUYER_ORG_ID"));
			epa.setC1(resultSet.getString("C1"));
			epa.setC2(resultSet.getString("C2"));
			epa.setC3(resultSet.getString("C3"));
			epa.setC4(resultSet.getString("C4"));
			epa.setC5(resultSet.getString("C5"));
			epa.setPeakUnits(resultSet.getString("PEAK_UNITS"));
			epa.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));
			epa.setFlowTypeCode(resultSet.getString("FLOW_TYPE_CODE"));
			epa.setEsIntentCode(resultSet.getString("T_ES_INTENT_CODE"));
            epa.setId(resultSet.getString("ID"));
            return 	epa ;
		}
}
final class EpaLineMapper implements RowMapper<EpaLine>{

	public EpaLineMapper() {
		super();
	}
	@Override
	public EpaLine mapRow(ResultSet resultSet, int rownum) throws SQLException {
		EpaLine epaline = new EpaLine();
		
		
		epaline.settEpaId(resultSet.getString("T_EPA_ID"));
        epaline.setCreatedBy(resultSet.getString("CREATED_BY"));
		epaline.setCreatedDate(resultSet.getString("CREATED_DT"));
		epaline.setModifiedBy(resultSet.getString("MODIFIED_BY"));
		epaline.setModifiedDate(resultSet.getString("MODIFIED_DT"));
		epaline.setmGeneratorId(resultSet.getString("M_GENERATOR_ID"));
		epaline.setProposedTotalUnits(resultSet.getString("PROPOSED_TOTAL_UNITS"));
		epaline.setApprovedTotalUnits(resultSet.getString("APPROVED_TOTAL_UNITS"));
		epaline.setPowerplantId(resultSet.getString("M_POWERPLANT_ID"));
		epaline.setGeneratorName(resultSet.getString("GENERATOR_NAME"));
		epaline.setNoOfUnits(resultSet.getString("NO_OF_UNITS"));
		epaline.setCommsionDate( resultSet.getString("COMMISSION_DATE"));
		epaline.setRemarks(resultSet.getString("REMARKS"));
		epaline.setDistrictCode(resultSet.getString("DISTRICT_CODE"));
		epaline.setGenCapacity(resultSet.getString("GEN_CAPACITY"));
        epaline.setId(resultSet.getString("ID"));
        
		return  epaline;
	}
}

}
