package com.ss.oa.transaction.companymeterchange;

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

import com.ss.oa.transaction.vo.CompanyMeterChange;

@Scope("prototype")
@Component
public class CompanyMeterChangeDaoImpl extends BaseDaoJdbc implements CompanyMeterChangeDao {
	
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<CompanyMeterChange> getAllCompanyMeterChanges(Map<String, String> criteria) {
		String sql="SELECT CMC.ID,CMC.CODE,CMC.TYPE_OF_METER_CHANGE,CMC.M_EDC_ID,CMC.M_COMPANY_ID,CMC.M_COMPANY_SERVICE_ID,CMC.M_COMPANY_METER_ID,CMC.METER_CHANGE_DATE,CMC.OLD_METER_NUMBER,CMC.NEW_METER_NUMBER,CMC.OLD_METER_MAKE_CODE,CMC.NEW_METER_MAKE_CODE,\n" + 
				"CMC.OLD_ACCURACY_CLASS_CODE,CMC.NEW_ACCURACY_CLASS_CODE,CMC.OLD_IS_ABTMETER,CMC.NEW_IS_ABTMETER,CMC.OLD_MF,CMC.NEW_MF,CMC.OLD_MODEM_NUMBER,CMC.NEW_MODEM_NUMBER,CMC.OLD_METER_CT1,CMC.OLD_METER_CT2,CMC.OLD_METER_CT3,CMC.OLD_METER_PT1,\n" + 
				"CMC.OLD_METER_PT2,CMC.OLD_METER_PT3,CMC.NEW_METER_CT1,CMC.NEW_METER_CT2,CMC.NEW_METER_CT3,CMC.NEW_METER_PT1,CMC.NEW_METER_PT2,CMC.NEW_METER_PT3,CMC.IS_METER_NUMBER_CHANGE,CMC.IS_METER_SET_CHANGE,CMC.IS_MODEM_NUMBER_CHANGE,\n" + 
				"COMPANYSERVICE.M_COMPANY_CODE,COMPANYSERVICE.M_COMPANY_NAME,COMPANYSERVICE.M_ORG_CODE,COMPANYSERVICE.M_ORG_NAME,COMPANYSERVICE.\"number\" AS M_SERVICE_NUMBER,COMPANYSERVICE.M_SUBSTATION_ID,COMPANYSERVICE.M_SUBSTATION_NAME,COMPANYSERVICE.M_SUBSTATION_CODE,COMPANYSERVICE.M_FEEDER_ID,\n" + 
				"COMPANYSERVICE.M_FEEDER_NAME,COMPANYSERVICE.M_FEEDER_CODE,COMPANYSERVICE.M_COMPANY_METER_ID,COMPANYSERVICE.MODEM_NUMBER,COMPANYSERVICE.METER_NUMBER,COMPANYSERVICE.MF,COMPANYSERVICE.ACCURACY_CLASS_CODE,COMPANYSERVICE.IS_ABTMETER,COMPANYSERVICE.METER_MAKE_CODE,COMPANYSERVICE.METER_CT1,COMPANYSERVICE.METER_CT2,COMPANYSERVICE.METER_CT3,COMPANYSERVICE.METER_PT1,COMPANYSERVICE.METER_PT2,COMPANYSERVICE.METER_PT3,CMC.STATUS_CODE\n" + 
				"FROM T_COMPANY_METER_CHANGE CMC LEFT JOIN V_COMPANY_SERVICE COMPANYSERVICE ON CMC.M_COMPANY_SERVICE_ID = COMPANYSERVICE.ID WHERE 1=1";
        System.out.println(sql);
        
        if(!criteria.isEmpty())
		{
			if(criteria.get("edc-id")!=null){
				sql += "and upper(CMC.M_EDC_ID)= upper('"+criteria.get("edc-id")+"')";
			}
			if(criteria.get("company-service-id")!=null){
				sql += "and upper(CMC.M_COMPANY_SERVICE_ID) = upper('"+criteria.get("company-service-id")+"')";
			}
//			if(criteria.get("company-service-number")!=null){
//				sql += "and upper(ewa.sellerCompServiceNumber) = upper('"+criteria.get("company-service-number")+"')";
//			}
//			if(criteria.get("seller-company-id")!=null){
//				sql += "and upper(ewa.sellerCompanyId) = upper('"+criteria.get("seller-company-id")+"')";
//			}
//			if(criteria.get("seller-company-name")!=null){
//				sql += "and upper(ewa.sellerCompanyName) = upper('"+criteria.get("seller-company-name")+"')";
//			}
//			if(criteria.get("status")!=null){
//				sql += "and upper(ewa.statusCode) = upper('"+criteria.get("status")+"')";
//			}
//			
//			if(criteria.get("code")!=null) {
//				sql += "and upper(ewa.CODE) like upper('%"+criteria.get("code")+"%')";
//			}	
			
		}
		
		return  jdbcOperations.query(sql, new CmcMapper());
	}

	@Override
	public CompanyMeterChange getCompanyMeterChangeById(String id) {
		CompanyMeterChange companyMeterChange = new CompanyMeterChange();
		String sql="SELECT CMC.ID,CMC.CODE,CMC.TYPE_OF_METER_CHANGE,CMC.M_EDC_ID,CMC.M_COMPANY_ID,CMC.M_COMPANY_SERVICE_ID,CMC.M_COMPANY_METER_ID,CMC.METER_CHANGE_DATE,CMC.OLD_METER_NUMBER,CMC.NEW_METER_NUMBER,CMC.OLD_METER_MAKE_CODE,CMC.NEW_METER_MAKE_CODE,\n" + 
				"CMC.OLD_ACCURACY_CLASS_CODE,CMC.NEW_ACCURACY_CLASS_CODE,CMC.OLD_IS_ABTMETER,CMC.NEW_IS_ABTMETER,CMC.OLD_MF,CMC.NEW_MF,CMC.OLD_MODEM_NUMBER,CMC.NEW_MODEM_NUMBER,CMC.OLD_METER_CT1,CMC.OLD_METER_CT2,CMC.OLD_METER_CT3,CMC.OLD_METER_PT1,\n" + 
				"CMC.OLD_METER_PT2,CMC.OLD_METER_PT3,CMC.NEW_METER_CT1,CMC.NEW_METER_CT2,CMC.NEW_METER_CT3,CMC.NEW_METER_PT1,CMC.NEW_METER_PT2,CMC.NEW_METER_PT3,CMC.IS_METER_NUMBER_CHANGE,CMC.IS_METER_SET_CHANGE,CMC.IS_MODEM_NUMBER_CHANGE,\n" + 
				"COMPANYSERVICE.M_COMPANY_CODE,COMPANYSERVICE.M_COMPANY_NAME,COMPANYSERVICE.M_ORG_CODE,COMPANYSERVICE.M_ORG_NAME,COMPANYSERVICE.\"number\" AS M_SERVICE_NUMBER,COMPANYSERVICE.M_SUBSTATION_ID,COMPANYSERVICE.M_SUBSTATION_NAME,COMPANYSERVICE.M_SUBSTATION_CODE,COMPANYSERVICE.M_FEEDER_ID,\n" + 
				"COMPANYSERVICE.M_FEEDER_NAME,COMPANYSERVICE.M_FEEDER_CODE,COMPANYSERVICE.M_COMPANY_METER_ID,COMPANYSERVICE.MODEM_NUMBER,COMPANYSERVICE.METER_NUMBER,COMPANYSERVICE.MF,COMPANYSERVICE.ACCURACY_CLASS_CODE,COMPANYSERVICE.IS_ABTMETER,COMPANYSERVICE.METER_MAKE_CODE,COMPANYSERVICE.METER_CT1,COMPANYSERVICE.METER_CT2,COMPANYSERVICE.METER_CT3,COMPANYSERVICE.METER_PT1,COMPANYSERVICE.METER_PT2,COMPANYSERVICE.METER_PT3,CMC.STATUS_CODE\n" + 
				"FROM T_COMPANY_METER_CHANGE CMC LEFT JOIN V_COMPANY_SERVICE COMPANYSERVICE ON CMC.M_COMPANY_SERVICE_ID = COMPANYSERVICE.ID  WHERE CMC.ID=? ";
		companyMeterChange=jdbcOperations.queryForObject(sql,new Object[]{id}, new CmcMapper());
		return companyMeterChange;
	}

	@Override
	public String addCompanyMeterChange(CompanyMeterChange companyMeterChange) {
		String result="";
		try {
			companyMeterChange.setId(generateId());
			if(companyMeterChange.getCode()== null || companyMeterChange.getCode().isEmpty()){
				companyMeterChange.setCode(generateCode(CompanyMeterChange.class.getSimpleName(),""));
			}
			String sql ="INSERT INTO T_COMPANY_METER_CHANGE (CODE,TYPE_OF_METER_CHANGE,M_EDC_ID,M_COMPANY_ID,M_COMPANY_SERVICE_ID,M_COMPANY_METER_ID,\n" + 
					"METER_CHANGE_DATE,OLD_METER_NUMBER,NEW_METER_NUMBER,OLD_METER_MAKE_CODE,NEW_METER_MAKE_CODE,OLD_ACCURACY_CLASS_CODE,NEW_ACCURACY_CLASS_CODE,\n" + 
					"OLD_IS_ABTMETER,NEW_IS_ABTMETER,OLD_MF,NEW_MF,OLD_MODEM_NUMBER,NEW_MODEM_NUMBER,OLD_METER_CT1,\n" + 
					"OLD_METER_CT2,OLD_METER_CT3,OLD_METER_PT1,OLD_METER_PT2,OLD_METER_PT3,NEW_METER_CT1,NEW_METER_CT2,\n" + 
					"NEW_METER_CT3,NEW_METER_PT1,NEW_METER_PT2,NEW_METER_PT3,IS_METER_NUMBER_CHANGE,IS_METER_SET_CHANGE,IS_MODEM_NUMBER_CHANGE,STATUS_CODE,ID) VALUES (?,?,?,?,?,?,\n" + 
					"TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setCmcValues(companyMeterChange))>0) {
				result= companyMeterChange.getId();
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
	public String updateCompanyMeterChange(CompanyMeterChange companyMeterChange) {
		String result="";

		try {
			String sql ="UPDATE T_COMPANY_METER_CHANGE SET CODE=?,TYPE_OF_METER_CHANGE=?,M_EDC_ID=?,M_COMPANY_ID=?,M_COMPANY_SERVICE_ID=?,M_COMPANY_METER_ID=?,\n" + 
					"METER_CHANGE_DATE=TO_DATE(?,'yyyy-mm-dd'),OLD_METER_NUMBER=?,NEW_METER_NUMBER=?,OLD_METER_MAKE_CODE=?,NEW_METER_MAKE_CODE=?,OLD_ACCURACY_CLASS_CODE=?,NEW_ACCURACY_CLASS_CODE=?,\n" + 
					"OLD_IS_ABTMETER=?,NEW_IS_ABTMETER=?,OLD_MF=?,NEW_MF=?,OLD_MODEM_NUMBER=?,NEW_MODEM_NUMBER=?,OLD_METER_CT1=?,\n" + 
					"OLD_METER_CT2=?,OLD_METER_CT3=?,OLD_METER_PT1=?,OLD_METER_PT2=?,OLD_METER_PT3=?,NEW_METER_CT1=?,NEW_METER_CT2=?,\n" + 
					"NEW_METER_CT3=?,NEW_METER_PT1=?,NEW_METER_PT2=?,NEW_METER_PT3=?,IS_METER_NUMBER_CHANGE=?,IS_METER_SET_CHANGE=?,IS_MODEM_NUMBER_CHANGE=?,STATUS_CODE=? WHERE ID=?";
			
					
			  if(jdbcOperations.update(sql,setCmcValues(companyMeterChange)) > 0){
				result = companyMeterChange.getId();				
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
	
	
	private Object[] setCmcValues(CompanyMeterChange companyMeterChange) {
		return new Object[] {		
				
			companyMeterChange.getCode(),
			companyMeterChange.getTypeOfMeterChange(),
			companyMeterChange.getOrgId(),
			companyMeterChange.getCompanyId(),
			companyMeterChange.getServiceId(),
			companyMeterChange.getCompanyMeterId(),			
			companyMeterChange.getMeterChangeDate(),
			companyMeterChange.getOldMeterNumber(),
			companyMeterChange.getNewMeterNumber(),
			companyMeterChange.getOldMeterMakeCode(),
			companyMeterChange.getNewMeterMakeCode(),
			companyMeterChange.getOldAccuracyClassCode(),
			companyMeterChange.getNewAccuracyClassCode(),
			companyMeterChange.getOldIsAbtMeter(),
			companyMeterChange.getNewIsAbtMeter(),
			companyMeterChange.getOldMf(),
			companyMeterChange.getNewMf(),
			companyMeterChange.getOldModemNumber(),
			companyMeterChange.getNewModemNumber(),
			companyMeterChange.getOldMeterCt1(),
			companyMeterChange.getOldMeterCt2(),
			companyMeterChange.getOldMeterCt3(),
			companyMeterChange.getOldMeterPt1(),
			companyMeterChange.getOldMeterPt2(),
			companyMeterChange.getOldMeterPt3(),
			companyMeterChange.getNewMeterCt1(),			
			companyMeterChange.getNewMeterCt2(),			
			companyMeterChange.getNewMeterCt3(),			
			companyMeterChange.getNewMeterPt1(),			
			companyMeterChange.getNewMeterPt2(),	
			companyMeterChange.getNewMeterPt3(),
			companyMeterChange.getIsMeterNumberChange(),
			companyMeterChange.getIsMeterSetChange(),
			companyMeterChange.getIsModemNumberChange(),
			companyMeterChange.getStatusCode(),
			companyMeterChange.getId()
			
					    
		};
	}
	
	final class CmcMapper implements RowMapper<CompanyMeterChange>{
		
		

		public CmcMapper() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public CompanyMeterChange mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			CompanyMeterChange companyMeterChange = new CompanyMeterChange();
			companyMeterChange.setId(resultSet.getString("ID"));
			companyMeterChange.setCode(resultSet.getString("CODE"));
			companyMeterChange.setOrgId(resultSet.getString("M_EDC_ID"));
			companyMeterChange.setCompanyId(resultSet.getString("M_COMPANY_ID"));
			companyMeterChange.setServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
			companyMeterChange.setCompanyMeterId(resultSet.getString("M_COMPANY_METER_ID"));
			companyMeterChange.setMeterChangeDate(resultSet.getString("METER_CHANGE_DATE"));
			companyMeterChange.setOldMeterNumber(resultSet.getString("OLD_METER_NUMBER"));
			companyMeterChange.setNewMeterNumber(resultSet.getString("NEW_METER_NUMBER"));
			companyMeterChange.setOldMeterMakeCode(resultSet.getString("OLD_METER_MAKE_CODE"));
			companyMeterChange.setNewMeterMakeCode(resultSet.getString("NEW_METER_MAKE_CODE"));
			companyMeterChange.setOldAccuracyClassCode(resultSet.getString("OLD_ACCURACY_CLASS_CODE"));
			companyMeterChange.setNewAccuracyClassCode(resultSet.getString("NEW_ACCURACY_CLASS_CODE"));
			companyMeterChange.setOldIsAbtMeter(resultSet.getString("OLD_IS_ABTMETER"));
			companyMeterChange.setNewIsAbtMeter(resultSet.getString("NEW_IS_ABTMETER"));
			companyMeterChange.setOldMf(resultSet.getString("OLD_MF"));
			companyMeterChange.setNewMf(resultSet.getString("NEW_MF"));
			companyMeterChange.setOldModemNumber(resultSet.getString("OLD_MODEM_NUMBER"));
			companyMeterChange.setNewModemNumber(resultSet.getString("NEW_MODEM_NUMBER"));
			companyMeterChange.setOldMeterCt1(resultSet.getString("OLD_METER_CT1"));
			companyMeterChange.setNewMeterCt1(resultSet.getString("NEW_METER_CT1"));
			companyMeterChange.setOldMeterCt2(resultSet.getString("OLD_METER_CT2"));
			companyMeterChange.setNewMeterCt2(resultSet.getString("NEW_METER_CT2"));
			companyMeterChange.setOldMeterCt3(resultSet.getString("OLD_METER_CT3"));
			companyMeterChange.setNewMeterCt3(resultSet.getString("NEW_METER_CT3"));
			companyMeterChange.setOldMeterPt1(resultSet.getString("OLD_METER_PT1"));
			companyMeterChange.setNewMeterPt1(resultSet.getString("NEW_METER_PT1"));
			companyMeterChange.setOldMeterPt2(resultSet.getString("OLD_METER_PT2"));
			companyMeterChange.setNewMeterPt2(resultSet.getString("NEW_METER_PT2"));
			companyMeterChange.setOldMeterPt3(resultSet.getString("OLD_METER_PT3"));
			companyMeterChange.setNewMeterPt3(resultSet.getString("NEW_METER_PT3"));
			companyMeterChange.setIsMeterNumberChange(resultSet.getString("IS_METER_NUMBER_CHANGE"));
			companyMeterChange.setIsMeterSetChange(resultSet.getString("IS_METER_SET_CHANGE"));
			companyMeterChange.setIsModemNumberChange(resultSet.getString("IS_MODEM_NUMBER_CHANGE"));
			companyMeterChange.setCompanyName(resultSet.getString("M_COMPANY_NAME"));
			companyMeterChange.setCompanyCode(resultSet.getString("M_COMPANY_CODE"));
			companyMeterChange.setServiceNumber(resultSet.getString("M_SERVICE_NUMBER"));
			companyMeterChange.setOrgName(resultSet.getString("M_ORG_NAME"));
			companyMeterChange.setOrgCode(resultSet.getString("M_ORG_CODE"));
			companyMeterChange.setSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			companyMeterChange.setSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			companyMeterChange.setSubstationCode(resultSet.getString("M_SUBSTATION_CODE"));
			companyMeterChange.setFeederId(resultSet.getString("M_FEEDER_ID"));
			companyMeterChange.setFeederName(resultSet.getString("M_FEEDER_NAME"));
			companyMeterChange.setFeederCode(resultSet.getString("M_FEEDER_CODE"));
			companyMeterChange.setMeterNumber(resultSet.getString("METER_NUMBER"));
			companyMeterChange.setModemNumber(resultSet.getString("MODEM_NUMBER"));
			companyMeterChange.setMultiplicationFactor(resultSet.getString("MF"));
			companyMeterChange.setAccuracyClassCode(resultSet.getString("ACCURACY_CLASS_CODE"));
			companyMeterChange.setIsAbtMeter(resultSet.getString("IS_ABTMETER"));
			companyMeterChange.setMeterMakeCode(resultSet.getString("METER_MAKE_CODE"));
			companyMeterChange.setMeterCt1(resultSet.getString("METER_CT1"));
			companyMeterChange.setMeterCt2(resultSet.getString("METER_CT2"));
			companyMeterChange.setMeterCt3(resultSet.getString("METER_CT3"));
			companyMeterChange.setMeterPt1(resultSet.getString("METER_PT1"));
			companyMeterChange.setMeterPt2(resultSet.getString("METER_PT2"));
			companyMeterChange.setMeterPt3(resultSet.getString("METER_PT3"));
			companyMeterChange.setStatusCode(resultSet.getString("STATUS_CODE"));
			return companyMeterChange;
		}
		
		
		
		
		
		
	}

}
