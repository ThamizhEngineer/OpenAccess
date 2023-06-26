package com.ss.oa.master.signup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.master.vo.Signup;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.master.vo.Generator;
@Component
@Scope("prototype")
public class SignupDaoImpl extends BaseDaoJdbc implements SignupDao {
	

	@Resource
	private JdbcOperations jdbcOperations;	

	@Override
	public List<Signup> getAllSignup(Map<String, String> criteria) {
		
		//String sql="select * from m_signup ";
		String sql="SELECT ID, COMPANY_NAME, PURPOSE_CODE, REGISTRATION_NO, REGISTRATION_DATE, COMMISSION_DATE, VOLTAGE_CODE,\n" + 
				"  HTSC_NUMBER,HTSC_NUMBER_OLD,TARIFF, TOTAL_CAPACITY, IS_CAPTIVE, USER_NAME, USER_ID, PASSWORD, POWERPLANT_TYPE_CODE, POWERPLANT_NAME,\n" + 
				"  FUEL_CODE, NO_OF_GENERATOR, GENERATOR_MODEL_CODE, IS_REC, WIND_PASS_CODE, LOCATION, ADDRESS_LINE,  \n" + 
				"  VILLAGE, TALUK_CODE, CITY, DISTRICT_CODE, STATE_CODE, M_ORG_ID, M_SUBSTATION_ID, M_FEEDER_ID, IS_COMPLETE, COMPANY_ADDRESS, \n" + 
				"	APPROVAL_DT, PLANT_CLASS_TYPE_CODE, BUYER_TYPE_CODE, GENERATOR_CAPACITY, METER_NUMBER, METER_MAKE_CODE, ACCURACY_CLASS_TYPE_CODE, IS_ABT_METER,   \n" + 
				"  MULTIPLICATION_FACTOR, APPLICATION_DT, VOLTAGE_NAME, POWERPLANT_TYPE_NAME, FUEL_NAME, GENERATOR_MODEL_NAME, TALUK_NAME, DISTRICT_NAME, STATE_NAME, \n" + 
				"  M_ORG_NAME, M_SUBSTATION_NAME, M_FEEDER_NAME, PLANT_CLASS_TYPE_NAME, BUYER_TYPE_NAME, METER_MAKE_NAME, ACCURACY_CLASS_TYPE_NAME,PURPOSE_NAME,\n" + 
				"	WIND_PASS_NAME,MODEM_NO,GU_MODEL1,GU_CAPACITY1,NO_OF_GU1,GU_MODEL2,GU_CAPACITY2,NO_OF_GU2,GU_MODEL3,GU_CAPACITY3,NO_OF_GU3,GU_MODEL4,GU_CAPACITY4,\n" + 
				"	NO_OF_GU4,GU_MODEL5,GU_CAPACITY5,NO_OF_GU5,GU_MODEL6,GU_CAPACITY6,NO_OF_GU6,METER_CT_1,METER_CT_2,METER_CT_3,METER_BT_1,METER_BT_2,METER_BT_3,SURPLUS_ENERGY_CODE,\n" + 
				"  TURBINE_SL_NO,TURBINE_ROTOR_DIA,TURBINE_HUB_HEIGHT,IS_DLMS_METER,METER_CT_RATIO,METER_PT_RATIO,TOTAL_GENERATOR_UNIT,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ACCELERATED_DEPRECIATION FROM V_SIGNUP  where 1=1 ";
		
	
		
		
		if(!criteria.isEmpty()) {
			
			for(Entry<String,String> element:criteria.entrySet()) {
				if(element.getKey().equals("company-name")) {
					sql=sql+" and upper(COMPANY_NAME) like upper('%"+element.getValue()+"%') ";
					
				}else if(element.getKey().equals("org-id")) {
					sql=sql+" and nvl(M_ORG_ID,'')='"+element.getValue()+"' ";
					
				}if(element.getKey().equals("purpose-code")) {
					sql=sql+" and PURPOSE_CODE='"+element.getValue()+"' ";
					
				}if(element.getKey().equals("remarks")) {
					sql=sql+" and REMARKS='"+element.getValue()+"' ";
					
				}if(element.getKey().equals("surplus-energy-code")) {
					sql=sql+" and SURPLUS_ENERGY_CODE='"+element.getValue()+"' ";
					
				}if(element.getKey().equals("is-complete")) {
                    sql=sql+" and nvl(IS_COMPLETE,'N')='"+element.getValue()+"' ";
                    
                }
				if(element.getKey().equals("htsc-number")) {
					sql=sql+" and upper(HTSC_NUMBER) like upper('%"+element.getValue()+"%') ";
					
				}
				
				if(element.getKey().equals("htsc-number-old")) {
					sql=sql+" and upper(HTSC_NUMBER_OLD) like upper('%"+element.getValue()+"%') ";
					
				}
				if(element.getKey().equals("is-captive")) {
					sql=sql+" and upper(IS_CAPTIVE)= upper('"+element.getValue()+"') ";
					
				}
				if(element.getKey().equals("is-complete")) {
					sql=sql+" and upper(IS_COMPLETE) = upper('"+element.getValue()+"') ";
					
				}
				if(element.getKey().equals("meter-number")) {
					sql=sql+" and upper(METER_NUMBER) like upper('%"+element.getValue()+"%') ";
					
				}
				
				
			}
			
		} 	
		
		sql += " order by upper(HTSC_NUMBER) ";
		
		String sqlMapper="";
		
if(!criteria.isEmpty()) {
			
			for(Entry<String,String> element:criteria.entrySet()) {
				if(element.getKey().equals("limit")) {
					
					sqlMapper +="SELECT * FROM ("+sql+")WHERE ROWNUM <="+element.getValue();
					
				}
				
				
			}
			
		} 	
		
		
		 return   (ArrayList<Signup>) jdbcOperations.query(sql,new SignupMapper());
		
		
	}

	@Override
	public Signup getSignupById(String signupId) {
		Signup signup = new Signup();
		String sql="    SELECT ID, COMPANY_NAME, PURPOSE_CODE, REGISTRATION_NO, REGISTRATION_DATE, COMMISSION_DATE, VOLTAGE_CODE,\n" + 
				"		HTSC_NUMBER,HTSC_NUMBER_OLD,TARIFF, TOTAL_CAPACITY, IS_CAPTIVE, USER_NAME, USER_ID, PASSWORD, POWERPLANT_TYPE_CODE, POWERPLANT_NAME,\n" + 
				"		FUEL_CODE, NO_OF_GENERATOR, GENERATOR_MODEL_CODE, IS_REC, WIND_PASS_CODE, LOCATION, ADDRESS_LINE,  \n" + 
				"		VILLAGE, TALUK_CODE, CITY, DISTRICT_CODE, STATE_CODE, M_ORG_ID, M_SUBSTATION_ID, M_FEEDER_ID, IS_COMPLETE, COMPANY_ADDRESS, \n" + 
				"		APPROVAL_DT, PLANT_CLASS_TYPE_CODE, BUYER_TYPE_CODE, GENERATOR_CAPACITY, METER_NUMBER, METER_MAKE_CODE, ACCURACY_CLASS_TYPE_CODE, IS_ABT_METER,   \n" + 
				"		MULTIPLICATION_FACTOR, APPLICATION_DT, VOLTAGE_NAME, POWERPLANT_TYPE_NAME, FUEL_NAME, GENERATOR_MODEL_NAME, TALUK_NAME, DISTRICT_NAME, STATE_NAME, \n" + 
				"	    M_ORG_NAME, M_SUBSTATION_NAME, M_FEEDER_NAME, PLANT_CLASS_TYPE_NAME, BUYER_TYPE_NAME, METER_MAKE_NAME, ACCURACY_CLASS_TYPE_NAME,PURPOSE_NAME,\n" + 
				"	    WIND_PASS_NAME,MODEM_NO,GU_MODEL1,GU_CAPACITY1,NO_OF_GU1,GU_MODEL2,GU_CAPACITY2,NO_OF_GU2,GU_MODEL3,GU_CAPACITY3,NO_OF_GU3,GU_MODEL4,GU_CAPACITY4,\n" + 
				"		 NO_OF_GU4,GU_MODEL5,GU_CAPACITY5,NO_OF_GU5,GU_MODEL6,GU_CAPACITY6,NO_OF_GU6,METER_CT_1,METER_CT_2,METER_CT_3,METER_BT_1,METER_BT_2,METER_BT_3,SURPLUS_ENERGY_CODE,\n" + 
				"		TURBINE_SL_NO,TURBINE_ROTOR_DIA,TURBINE_HUB_HEIGHT,IS_DLMS_METER,METER_CT_RATIO,METER_PT_RATIO,TOTAL_GENERATOR_UNIT,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ACCELERATED_DEPRECIATION  FROM V_SIGNUP  where id=? ";
		
		signup = jdbcOperations.queryForObject(sql,new Object[]{signupId},new SignupMapper());
		List<Generator> genUnits = new ArrayList<Generator>();
		if((signup.getGuModel1()!=null && !signup.getGuModel1().equals("0") )||signup.getNoOfGu1()!= null && !signup.getNoOfGu1().equals("0")|| signup.getGuCapacity1()!=null && !signup.getGuCapacity1().equals("0")) {			
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel1());
			generator.setCapacity(signup.getGuCapacity1());
			generator.setNoOfUnits(signup.getNoOfGu1());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		if((signup.getGuModel2()!=null && !signup.getGuModel2().equals("0") )||signup.getNoOfGu2()!= null && !signup.getNoOfGu2().equals("0")|| signup.getGuCapacity2()!=null && !signup.getGuCapacity2().equals("0")) {	
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel2());
			generator.setCapacity(signup.getGuCapacity2());
			generator.setNoOfUnits(signup.getNoOfGu2());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		if((signup.getGuModel3()!=null && !signup.getGuModel3().equals("0") )||signup.getNoOfGu3()!= null && !signup.getNoOfGu3().equals("0")|| signup.getGuCapacity3()!=null && !signup.getGuCapacity3().equals("0")) {	
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel3());
			generator.setCapacity(signup.getGuCapacity3());
			generator.setNoOfUnits(signup.getNoOfGu3());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		if((signup.getGuModel4()!=null && !signup.getGuModel4().equals("0") )||signup.getNoOfGu4()!= null && !signup.getNoOfGu4().equals("0")|| signup.getGuCapacity4()!=null && !signup.getGuCapacity4().equals("0")) {		
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel4());
			generator.setCapacity(signup.getGuCapacity4());
			generator.setNoOfUnits(signup.getNoOfGu4());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		if((signup.getGuModel5()!=null && !signup.getGuModel5().equals("0") )||signup.getNoOfGu5()!= null && !signup.getNoOfGu5().equals("0")|| signup.getGuCapacity5()!=null && !signup.getGuCapacity5().equals("0")) {
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel5());
			generator.setCapacity(signup.getGuCapacity5());
			generator.setNoOfUnits(signup.getNoOfGu5());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		if((signup.getGuModel6()!=null && !signup.getGuModel6().equals("0") )||signup.getNoOfGu6()!= null && !signup.getNoOfGu6().equals("0")|| signup.getGuCapacity6()!=null && !signup.getGuCapacity6().equals("0")) {
			Generator generator = new Generator();
			generator.setMakeCode(signup.getGuModel6());
			generator.setCapacity(signup.getGuCapacity6());
			generator.setNoOfUnits(signup.getNoOfGu6());
			genUnits.add(generator);			
			signup.setGenUnits(genUnits);			
		}
		
		
		String sql1=" select trade.ID,trade.QUANTUM,to_char(trade.FROM_DATE,'YYYY-MM-DD') FROM_DATE,to_char(trade.TO_DATE,'YYYY-MM-DD') TO_DATE,trade.STATUS_CODE,statuscode.value_desc as STATUS_Name, \n" + 
				"				 trade.M_BUYER_COMPANY_ID,buyerservice.M_COMPANY_NAME as M_BUYER_COMPANY_NAME, buyerservice.M_COMPANY_CODE as M_BUYER_COMPANY_CODE,buyerservice.M_ORG_ID as M_BUYER_ORG_ID,buyerservice.M_ORG_CODE as M_BUYER_ORG_CODE,buyerservice.M_ORG_NAME as M_BUYER_ORG_NAME,trade.M_BUYER_COMP_SERVICE_ID,trade.IS_CAPTIVE,trade.SHARE_PERCENT,\n" + 
				"				 buyerservice.\"number\" as M_BUYER_COMP_SERVICE_NUMBER,trade.REMARKS, trade.M_SIGNUP_ID, trade.C1, trade.C2, trade.C3, trade.C4, trade.C5,trade.PEAK_UNITS,trade.OFF_PEAK_UNITS,trade.INTERVAL_TYPE_CODE,intervaltypecode.VALUE_DESC as INTERVAL_TYPE_NAME from M_SIGNUP_TRADE_REL trade\n" + 
				"				 left join v_company_service buyerservice on buyerservice.id = trade.M_BUYER_COMP_SERVICE_ID\n" + 
				"				 left join v_codes statuscode on trade.STATUS_CODE= statuscode.value_code and statuscode.list_code='TRADE_REL_STATUS_CODE'\n" + 
				"                   left join v_codes intervaltypecode on trade.INTERVAL_TYPE_CODE= intervaltypecode.value_code and intervaltypecode.list_code='INTERVAL_TYPE_CODE' where trade.M_SIGNUP_ID = '"+signupId+"'";
		
			
		 signup.setSignupTradeRelationships( (ArrayList<TradeRelationship>) jdbcOperations.query(sql1,new TradeRelationshipMapper()));  
		 return signup;
		
	}

	@Override
	public String addSignup(Signup signup) {
		String result = "";
		try {
			//generate primary key
			signup.setId(generateId());
			signup.setIsComplete("N");
			if(signup.getPowerPlantName()==null) {
				signup.setPowerPlantName(signup.getOrgId()+"-"+signup.getCompanyName());
			}
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			signup.setCreatedDate(dateFormat.format(date));
		
		
			String sql1="INSERT into m_signup(COMPANY_NAME,PURPOSE,REGISTRATION_NO,REGISTRATION_DATE,COMMISSION_DATE,VOLTAGE,\n" + 
					"HTSC_NUMBER,HTSC_NUMBER_OLD,TARIFF,TOTAL_CAPACITY,IS_CAPTIVE,USER_NAME,USER_ID,PASSWORD,\n" + 
					"POWERPLANT_TYPE,POWERPLANT_NAME,FUEL,NO_OF_GENERATOR,GENERATOR_MODEL,IS_REC,\n" + 
					"WIND_PASS_CODE,LOCATION,ADDRESS_LINE,VILLAGE,TALUK_CODE,CITY,DISTRICT_CODE,STATE_CODE,\n" + 
					"M_ORG_ID,M_SUBSTATION_ID,M_FEEDER_ID,IS_COMPLETE,COMPANY_ADDRESS,APPROVAL_DT,PLANT_CLASS_TYPE_CODE,\n" + 
					"BUYER_TYPE_CODE,GENERATOR_CAPACITY,METER_NUMBER,METER_MAKE_CODE,ACCURACY_CLASS_TYPE_CODE,IS_ABT_METER,MULTIPLICATION_FACTOR,APPLICATION_DT,MODEM_NO,"
					+ "GU_MODEL1,GU_CAPACITY1,NO_OF_GU1,GU_MODEL2,GU_CAPACITY2,NO_OF_GU2,GU_MODEL3,GU_CAPACITY3,NO_OF_GU3,GU_MODEL4,GU_CAPACITY4,NO_OF_GU4,GU_MODEL5,GU_CAPACITY5,NO_OF_GU5,GU_MODEL6,GU_CAPACITY6,NO_OF_GU6,"
					+ "METER_CT_1,METER_CT_2,METER_CT_3,METER_BT_1,METER_BT_2,METER_BT_3,SURPLUS_ENERGY_CODE, TURBINE_SL_NO,TURBINE_ROTOR_DIA,TURBINE_HUB_HEIGHT,IS_DLMS_METER,METER_CT_RATIO,METER_PT_RATIO,TOTAL_GENERATOR_UNIT,CREATED_DATE,MODIFIED_DATE,ACCELERATED_DEPRECIATION,ID)values\n" + 
					"(?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,?,?,?,\n" + 
					"?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,\n" + 
					"?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?)";
		
			if(jdbcOperations.update(sql1, setValues(signup)) > 0){
				result =signup.getId();
				
			}
			else{
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public String updateSignup(String signupId, Signup signup) {
		String result = "";
		signup.setId(signupId);
		try {
			//String sql="update m_signup set IS_PRIMARY=?,VERSION=?,CODE=?,NAME=?,PLANT_TYPE_CODE=?,FUEL_TYPE_CODE=?,M_SERVICE_ID=?,M_ORG_ID=?,T_GRID_CONN_APPLN_ID=?,TOTAL_CAPACITY=?,M_SUBSTATION_ID=?,INTERFACE_VOLTAGE_PHASE=?,INTERFACE_VOLTAGE_FREQUENCY=?,COMMISSION_DATE=?,PURPOSE=?,ENABLED=?,STATUS=?,LINE1=?,CITY=?,STATE_CODE=?,PINCODE=?,VILLAGE=?,TALUK_CODE=?,DISTRICT_CODE=?,PLS_SF_NO=?,PL_VILLAGE=?,PL_TOWN=?,PL_TALUK_CODE=?,PL_DISTRICT_CODE=?,WIND_PASS_CODE=?,WIND_ZONE_AREA_CODE=? where id=?";		
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			signup.setModifiedDate(dateFormat.format(date));
		
			
			String sql ="update m_signup set COMPANY_NAME=?,PURPOSE=?,REGISTRATION_NO=?,REGISTRATION_DATE=TO_DATE(?,'yyyy-mm-dd'),COMMISSION_DATE=TO_DATE(?,'yyyy-mm-dd'),VOLTAGE=?,\n" + 
					"HTSC_NUMBER=?,HTSC_NUMBER_OLD=?,TARIFF=?,TOTAL_CAPACITY=?,IS_CAPTIVE=?,USER_NAME=?,USER_ID=?,PASSWORD=?,\n" + 
					"POWERPLANT_TYPE=?,POWERPLANT_NAME=?,FUEL=?,NO_OF_GENERATOR=?,GENERATOR_MODEL=?,IS_REC=?,\n" + 
					"WIND_PASS_CODE=?,LOCATION=?,ADDRESS_LINE=?,VILLAGE=?,TALUK_CODE=?,CITY=?,DISTRICT_CODE=?,STATE_CODE=?,\n" + 
					"M_ORG_ID=?,M_SUBSTATION_ID=?,M_FEEDER_ID=?,IS_COMPLETE=?,COMPANY_ADDRESS=?,APPROVAL_DT=TO_DATE(?,'yyyy-mm-dd'),PLANT_CLASS_TYPE_CODE=?,\n" + 
					"BUYER_TYPE_CODE=?,GENERATOR_CAPACITY=?,METER_NUMBER=?,METER_MAKE_CODE=?,ACCURACY_CLASS_TYPE_CODE=?,IS_ABT_METER=?,MULTIPLICATION_FACTOR=?,APPLICATION_DT=TO_DATE(?,'yyyy-mm-dd'),MODEM_NO=?,"
					+ "GU_MODEL1=?,GU_CAPACITY1=?,NO_OF_GU1=?,GU_MODEL2=?,GU_CAPACITY2=?,NO_OF_GU2=?,GU_MODEL3=?,GU_CAPACITY3=?,NO_OF_GU3=?,GU_MODEL4=?,GU_CAPACITY4=?,NO_OF_GU4=?,GU_MODEL5=?,GU_CAPACITY5=?,NO_OF_GU5=?,GU_MODEL6=?,GU_CAPACITY6=?,NO_OF_GU6=?,"
					+ "METER_CT_1=?,METER_CT_2=?,METER_CT_3=?,METER_BT_1=?,METER_BT_2=?,METER_BT_3=?,SURPLUS_ENERGY_CODE=?,TURBINE_SL_NO=?,TURBINE_ROTOR_DIA=?,TURBINE_HUB_HEIGHT=?,IS_DLMS_METER=?,METER_CT_RATIO=?,METER_PT_RATIO =? ,TOTAL_GENERATOR_UNIT=?,CREATED_DATE=TO_DATE(?,'yyyy-mm-dd'),MODIFIED_DATE=TO_DATE(?,'yyyy-mm-dd'),ACCELERATED_DEPRECIATION=? where id=?";		
			
			if(jdbcOperations.update(sql,setValues(signup)) > 0){
				result =signup.getId();
			}
			else{
				
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
		
	}
	

	
	private Object[] setValues(Signup signup){
		return new Object[]{	
				
			
				signup.getCompanyName(),				
				signup.getPurposeCode(),
				signup.getRegistrationNumber(),
				signup.getRegistrationDate(),
				signup.getDateOfCommission(),			
				signup.getVoltageCode(),
				signup.getHtscNumber(),
				signup.getHtscNumberOld(),
				signup.getTariff(),
				signup.getTotalCapacity(),
				signup.getIsCaptive(),
				signup.getUserName(),
				signup.getUserId(),
				signup.getPassword(),
				signup.getPowerPlantTypeCode(),				
				signup.getPowerPlantName(),
				signup.getFuelCode(),				
				signup.getNoOfGenerators(),			
				signup.getGeneratorModelCode(),			
				signup.getIsREC(),
				signup.getWindPassCode(),
				signup.getLocation(),
				signup.getAddressLine(),
				signup.getVillage(),		
				signup.getTalukCode(),			
				signup.getCity(),			
				signup.getDistrictCode(),			
				signup.getStateCode(),	
				signup.getOrgId(),				
				signup.getSubstationId(),				
				signup.getFeederId(),
				signup.getIsComplete(),
				signup.getCompanyAddress(),
				signup.getDateOfApproval(),
				signup.getPlantClassTypeCode(),
				signup.getBuyerTypeCode(),
				signup.getGeneratorCapacity(),
				signup.getMeterNumber(),
				signup.getMeterMakeCode(),
				signup.getAccuracyClassCode(),
				signup.getIsAbtMeter(),
				signup.getMultiplicationFactor(),
				signup.getApplicationDate(),
				signup.getModemNumber(),
				signup.getGuModel1(),
				signup.getGuCapacity1(),
				signup.getNoOfGu1(),
				signup.getGuModel2(),
				signup.getGuCapacity2(),
				signup.getNoOfGu2(),
				signup.getGuModel3(),
				signup.getGuCapacity3(),
				signup.getNoOfGu3(),
				signup.getGuModel4(),
				signup.getGuCapacity4(),
				signup.getNoOfGu4(),
				signup.getGuModel5(),
				signup.getGuCapacity5(),
				signup.getNoOfGu5(),
				signup.getGuModel6(),
				signup.getGuCapacity6(),
				signup.getNoOfGu6(),
				signup.getMeterCt1(),
				signup.getMeterCt2(),
				signup.getMeterCt3(),
				signup.getMeterBt1(),
				signup.getMeterBt2(),
				signup.getMeterBt3(),
				signup.getSurplusEnergyCode(),
				signup.getTurbineSlNo(),
				signup.getTurbineRotorDia(),
				signup.getTurbineHubHeight(),
				signup.getIsDlmsMeter(),
				signup.getMeterCtRatio(),
				signup.getMeterPtRatio(),
				signup.getTotalGeneratorUnit(),
				signup.getCreatedDate(),
				signup.getModifiedDate(),
				signup.getAcceleratedDepreciation(),
				signup.getId()
			};
	}
	
final class SignupMapper implements RowMapper<Signup>{
		
		public SignupMapper() {
			super();
		}

		
		public Signup mapRow(ResultSet resultset, int rownum) throws SQLException {
			Signup signup = new Signup();		
			
			signup.setId(resultset.getString("ID"));
			signup.setCompanyName(resultset.getString("COMPANY_NAME"));			
			signup.setPurposeCode(resultset.getString("PURPOSE_CODE"));
			signup.setRegistrationNumber(resultset.getString("REGISTRATION_NO"));
			signup.setRegistrationDate(resultset.getString("REGISTRATION_DATE"));
			signup.setDateOfCommission(resultset.getString("COMMISSION_DATE"));			
			signup.setVoltageCode(resultset.getString("VOLTAGE_CODE"));
			signup.setHtscNumber(resultset.getString("HTSC_NUMBER"));
			signup.setHtscNumberOld(resultset.getString("HTSC_NUMBER_OLD"));
			signup.setTariff(resultset.getString("TARIFF"));
			signup.setTotalCapacity(resultset.getString("TOTAL_CAPACITY"));
			signup.setIsCaptive(resultset.getString("IS_CAPTIVE"));
			signup.setUserName(resultset.getString("USER_NAME"));
			signup.setUserId(resultset.getString("USER_ID"));
			signup.setPassword(resultset.getString("PASSWORD"));
			signup.setPowerPlantTypeCode(resultset.getString("POWERPLANT_TYPE_CODE"));
			signup.setPowerPlantName(resultset.getString("POWERPLANT_NAME"));
			signup.setFuelCode(resultset.getString("FUEL_CODE"));
			signup.setNoOfGenerators(resultset.getString("NO_OF_GENERATOR"));	
			signup.setGeneratorModelCode(resultset.getString("GENERATOR_MODEL_CODE"));
			signup.setIsREC(resultset.getString("IS_REC"));
			signup.setWindPassCode(resultset.getString("WIND_PASS_CODE"));
			signup.setLocation(resultset.getString("LOCATION"));
			signup.setAddressLine(resultset.getString("ADDRESS_LINE"));
			signup.setVillage(resultset.getString("VILLAGE"));
			signup.setTalukCode(resultset.getString("TALUK_CODE"));
			signup.setCity(resultset.getString("CITY"));
			signup.setDistrictCode(resultset.getString("DISTRICT_CODE"));
			signup.setStateCode(resultset.getString("STATE_CODE"));
			signup.setOrgId(resultset.getString("M_ORG_ID"));
			signup.setSubstationId(resultset.getString("M_SUBSTATION_ID"));
			signup.setFeederId(resultset.getString("M_FEEDER_ID"));
			signup.setIsComplete(resultset.getString("IS_COMPLETE"));
			signup.setCompanyAddress(resultset.getString("COMPANY_ADDRESS"));
			signup.setDateOfApproval(resultset.getString("APPROVAL_DT"));
			signup.setPlantClassTypeCode(resultset.getString("PLANT_CLASS_TYPE_CODE"));
			signup.setBuyerTypeCode(resultset.getString("BUYER_TYPE_CODE"));
			signup.setGeneratorCapacity(resultset.getString("GENERATOR_CAPACITY"));
			signup.setMeterNumber(resultset.getString("METER_NUMBER"));
			signup.setMeterMakeCode(resultset.getString("METER_MAKE_CODE"));
			signup.setAccuracyClassCode(resultset.getString("ACCURACY_CLASS_TYPE_CODE"));
			signup.setIsAbtMeter(resultset.getString("IS_ABT_METER"));
			signup.setMultiplicationFactor(resultset.getString("MULTIPLICATION_FACTOR"));
			signup.setApplicationDate(resultset.getString("APPLICATION_DT"));
			signup.setVoltageName(resultset.getString("VOLTAGE_NAME"));			
			signup.setPowerPlantTypeName(resultset.getString("POWERPLANT_TYPE_NAME"));				
			signup.setFuelName(resultset.getString("FUEL_NAME"));		
			signup.setGeneratorModelName(resultset.getString("GENERATOR_MODEL_NAME"));		
			signup.setTalukName(resultset.getString("TALUK_NAME"));	
			signup.setDistrictName(resultset.getString("DISTRICT_NAME"));								
			signup.setStateName(resultset.getString("STATE_NAME"));
			signup.setOrgName(resultset.getString("M_ORG_NAME"));				
			signup.setSubstationName(resultset.getString("M_SUBSTATION_NAME"));			
			signup.setFeederName(resultset.getString("M_FEEDER_NAME"));
			signup.setPlantClassTypeName(resultset.getString("PLANT_CLASS_TYPE_NAME"));
			signup.setBuyerTypeName(resultset.getString("BUYER_TYPE_NAME"));
			signup.setMeterMakeName(resultset.getString("METER_MAKE_NAME"));
			signup.setAccuracyClassName(resultset.getString("ACCURACY_CLASS_TYPE_NAME"));
			signup.setPurposeName(resultset.getString("PURPOSE_NAME"));		
			signup.setWindPassName(resultset.getString("WIND_PASS_NAME"));
			signup.setModemNumber(resultset.getString("MODEM_NO"));
			signup.setGuModel1(resultset.getString("GU_MODEL1"));
			signup.setGuCapacity1(resultset.getString("GU_CAPACITY1"));
			signup.setNoOfGu1(resultset.getString("NO_OF_GU1"));
			signup.setGuModel2(resultset.getString("GU_MODEL2"));
			signup.setGuCapacity2(resultset.getString("GU_CAPACITY2"));
			signup.setNoOfGu2(resultset.getString("NO_OF_GU2"));
			signup.setGuModel3(resultset.getString("GU_MODEL3"));
			signup.setGuCapacity3(resultset.getString("GU_CAPACITY3"));
			signup.setNoOfGu3(resultset.getString("NO_OF_GU3"));
			signup.setGuModel4(resultset.getString("GU_MODEL4"));
			signup.setGuCapacity4(resultset.getString("GU_CAPACITY4"));
			signup.setNoOfGu4(resultset.getString("NO_OF_GU4"));
			signup.setGuModel5(resultset.getString("GU_MODEL5"));
			signup.setGuCapacity5(resultset.getString("GU_CAPACITY5"));
			signup.setNoOfGu5(resultset.getString("NO_OF_GU5"));
			signup.setGuModel6(resultset.getString("GU_MODEL6"));
			signup.setGuCapacity6(resultset.getString("GU_CAPACITY6"));
			signup.setNoOfGu6(resultset.getString("NO_OF_GU6"));
			signup.setMeterCt1(resultset.getString("METER_CT_1"));
			signup.setMeterCt2(resultset.getString("METER_CT_2"));
			signup.setMeterCt3(resultset.getString("METER_CT_3"));
			signup.setMeterBt1(resultset.getString("METER_BT_1"));
			signup.setMeterBt2(resultset.getString("METER_BT_2"));
			signup.setMeterBt3(resultset.getString("METER_BT_3"));
			signup.setSurplusEnergyCode(resultset.getString("SURPLUS_ENERGY_CODE"));
			signup.setTurbineSlNo(resultset.getString("TURBINE_SL_NO"));
			signup.setTurbineRotorDia(resultset.getString("TURBINE_ROTOR_DIA"));
			signup.setTurbineHubHeight(resultset.getString("TURBINE_HUB_HEIGHT"));
			signup.setIsDlmsMeter(resultset.getString("IS_DLMS_METER"));
			signup.setMeterCtRatio(resultset.getString("METER_CT_RATIO"));
			signup.setMeterPtRatio(resultset.getString("METER_PT_RATIO"));
			signup.setTotalGeneratorUnit(resultset.getString("TOTAL_GENERATOR_UNIT"));
			signup.setCreatedBy(resultset.getString("CREATED_BY"));
			signup.setCreatedDate(resultset.getString("CREATED_DATE"));
			signup.setModifiedBy(resultset.getString("MODIFIED_BY"));
			signup.setModifiedDate(resultset.getString("MODIFIED_DATE"));
			signup.setAcceleratedDepreciation(resultset.getString("ACCELERATED_DEPRECIATION"));
			return signup;
		}
		
	}


@Override
public String addSignupTradeRelationship(TradeRelationship tradeRelationship) {
	String result="";
	try {
		tradeRelationship.setId(generateId());
		
		String sql="insert into M_SIGNUP_TRADE_REL(QUANTUM,FROM_DATE,TO_DATE,STATUS_CODE,M_BUYER_COMPANY_ID,M_BUYER_COMP_SERVICE_ID,IS_CAPTIVE,SHARE_PERCENT, " + 
				"REMARKS,M_SIGNUP_ID,C1,C2,C3,C4,C5,PEAK_UNITS,OFF_PEAK_UNITS,INTERVAL_TYPE_CODE,ID) values(?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		if(jdbcOperations.update(sql, setSignupTradeRelValues(tradeRelationship)) > 0){
			result =tradeRelationship.getId();
		
		}else{
			result =  "FAILURE";
		}		
		
	}catch(Exception e) {
		result="FAILURE";
		e.printStackTrace();
	}
	return result;
}

@Override
public String updateSignupTradeRelationship(TradeRelationship tradeRelationship) {
	String result = "";
	
	try {		
		String sql="update M_SIGNUP_TRADE_REL set QUANTUM = ?,FROM_DATE=TO_DATE(?,'yyyy-mm-dd'),TO_DATE= TO_DATE(?,'yyyy-mm-dd'),STATUS_CODE=?,M_BUYER_COMPANY_ID=?,M_BUYER_COMP_SERVICE_ID=?,IS_CAPTIVE=?,SHARE_PERCENT=?,REMARKS=?,M_SIGNUP_ID=?,C1=?,C2=?,C3=?,C4=?,C5=?,PEAK_UNITS=?,OFF_PEAK_UNITS=?,INTERVAL_TYPE_CODE=? where id=?";
		
		if(jdbcOperations.update(sql,setSignupTradeRelValues(tradeRelationship)) > 0){
			result =tradeRelationship.getId();
		}
		else{
			
			result =  "FAILURE";
		}
	} catch (Exception e) {
		result =  "FAILURE";
		e.printStackTrace();
	}
	return result;
}

private Object[] setSignupTradeRelValues(TradeRelationship tradeRelationship){
	return new Object[]{			
		
			tradeRelationship.getQuantum(),
			tradeRelationship.getFromDate(),
			tradeRelationship.getToDate(),
			tradeRelationship.getStatusCode(),
			tradeRelationship.getBuyerCompanyId(),
			tradeRelationship.getBuyerCompServiceId(),
			tradeRelationship.getIsCaptive(),
			tradeRelationship.getSharePercent(),
			tradeRelationship.getRemarks(),
			tradeRelationship.getSignupId(),
			tradeRelationship.getC1(),
			tradeRelationship.getC2(),
			tradeRelationship.getC3(),
			tradeRelationship.getC4(),
			tradeRelationship.getC5(),
			tradeRelationship.getPeakUnits(),
			tradeRelationship.getOffPeakUnits(),
			tradeRelationship.getIntervalTypeCode(),
			tradeRelationship.getId()
		
		};
}
}

final class TradeRelationshipMapper implements RowMapper<TradeRelationship>{
	
	public TradeRelationshipMapper() {
		super();
	}

	
	public TradeRelationship mapRow(ResultSet resultSet, int rownum) throws SQLException {
		TradeRelationship tradeRelationship=new TradeRelationship();
		
		tradeRelationship.setId(resultSet.getString("ID"));
		tradeRelationship.setQuantum(resultSet.getString("QUANTUM"));
		tradeRelationship.setFromDate(resultSet.getString("FROM_DATE"));
		tradeRelationship.setToDate(resultSet.getString("TO_DATE"));
		tradeRelationship.setStatusCode(resultSet.getString("STATUS_CODE"));
		tradeRelationship.setStatusName(resultSet.getString("STATUS_NAME"));
		tradeRelationship.setBuyerCompanyId(resultSet.getString("M_BUYER_COMPANY_ID"));
		tradeRelationship.setBuyerCompanyCode(resultSet.getString("M_BUYER_COMPANY_Code"));
		tradeRelationship.setBuyerCompanyName(resultSet.getString("M_BUYER_COMPANY_Name"));
		tradeRelationship.setBuyerCompServiceId(resultSet.getString("M_BUYER_COMP_SERVICE_ID"));
		tradeRelationship.setBuyerCompServiceNumber(resultSet.getString("M_BUYER_COMP_SERVICE_NUMBER"));
		tradeRelationship.setBuyerOrgId(resultSet.getString("M_BUYER_ORG_ID"));
		tradeRelationship.setBuyerOrgName(resultSet.getString("M_BUYER_ORG_NAME"));
		tradeRelationship.setBuyerOrgCode(resultSet.getString("M_BUYER_ORG_CODE"));
		tradeRelationship.setIsCaptive(resultSet.getString("IS_CAPTIVE"));
		tradeRelationship.setSharePercent(resultSet.getString("SHARE_PERCENT"));

		
		tradeRelationship.setRemarks(resultSet.getString("REMARKS"));
		tradeRelationship.setSignupId(resultSet.getString("M_SIGNUP_ID"));
		tradeRelationship.setC1(resultSet.getString("C1"));
		tradeRelationship.setC2(resultSet.getString("C2"));
		tradeRelationship.setC3(resultSet.getString("C3"));
		tradeRelationship.setC4(resultSet.getString("C4"));
		tradeRelationship.setC5(resultSet.getString("C5"));
		tradeRelationship.setPeakUnits(resultSet.getString("PEAK_UNITS"));
		tradeRelationship.setOffPeakUnits(resultSet.getString("OFF_PEAK_UNITS"));
		tradeRelationship.setIntervalTypeCode(resultSet.getString("INTERVAL_TYPE_CODE"));
		tradeRelationship.setIntervalTypeName(resultSet.getString("INTERVAL_TYPE_NAME"));
		return tradeRelationship;
	}
}
