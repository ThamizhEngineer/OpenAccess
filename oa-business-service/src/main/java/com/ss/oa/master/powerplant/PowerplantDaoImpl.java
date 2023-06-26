package com.ss.oa.master.powerplant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.master.vo.Powerplant;
import com.ss.oa.master.vo.Generator;
@Component
@Scope("prototype")
public class PowerplantDaoImpl extends BaseDaoJdbc implements PowerplantDao  {
	@Resource
	private JdbcOperations jdbcOperations;
	@Override
	public List<Powerplant> getAllPowerplants(Map<String, String> criteria) { 
		String sql="select powerplant.ID,powerplant.IS_PRIMARY,powerplant.VERSION,powerplant.CODE,powerplant.NAME,powerplant.PLANT_TYPE_CODE,plantcodes.Value_Desc as PLANT_TYPE_NAME,\n" + 
				"powerplant.FUEL_TYPE_CODE,fuelcodes.Value_Desc as FUEL_TYPE_NAME,powerplant.M_SERVICE_ID,service.\"number\" as SERVICE_NUMBER,\n" + 
				"company.id as COMPANY_ID ,company.code as COMPANY_CODE ,company.name as COMPANY_NAME,powerplant.M_ORG_ID,\n" + 
				"org.code as M_ORG_CODE, org.name as M_ORG_NAME,powerplant.T_GRID_CONN_APPLN_ID,powerplant.TOTAL_CAPACITY,\n" + 
				"powerplant.m_substation_id,substation.code as M_SUBSTATION_CODE,substation.name as M_SUBSTATION_NAME,powerplant.\n" + 
				"INTERFACE_VOLTAGE_PHASE,powerplant.INTERFACE_VOLTAGE_FREQUENCY,powerplant.COMMISSION_DATE,powerplant.PURPOSE,powerplant.ENABLED,powerplant.STATUS,\n" + 
				"powerplant.LINE1,powerplant.CITY,powerplant.STATE_CODE,statecodes.Value_Desc as STATE_NAME,powerplant.PINCODE,powerplant.VILLAGE,powerplant.TALUK_CODE,\n" + 
				"talukcodes.Value_Desc as TALUK_NAME,powerplant.DISTRICT_CODE,districtcodes.VALUE_DESC as DISTRICT_NAME,powerplant.PLS_SF_NO,\n" + 
				"powerplant.PL_VILLAGE,powerplant.PL_TOWN,powerplant.PL_TALUK_CODE,pltalukcodes.Value_Desc as PL_TALUK_NAME,powerplant.PL_DISTRICT_CODE,pldistrictcodes.VALUE_DESC as PL_DISTRICT_NAME,\n" + 
				"powerplant.WIND_PASS_CODE,windpasscode.VALUE_DESC as WIND_PASS_NAME,powerplant.WIND_ZONE_AREA_CODE,powerplant.APPLICATION_DT,powerplant.APPROVAL_DT,\n" + 
				"powerplant.REMARKS,powerplant.CREATED_BY,powerplant.CREATED_DATE,powerplant.MODIFIED_BY,powerplant.MODIFIED_DATE\n" + 
				"FROM m_powerplant powerplant\n" + 
				"left join v_codes plantcodes on powerplant.PLANT_TYPE_CODE= plantcodes.Value_Code    AND  plantcodes.list_code = 'PLANT_TYPE_CODE'\n" + 
				"left join v_codes fuelcodes on powerplant.FUEL_TYPE_CODE= fuelcodes.Value_Code    AND  fuelcodes.list_code = 'FUEL_TYPE_CODE'\n" + 
				"left join m_org org on org.id=powerplant.M_ORG_ID\n" + 
				"left join m_substation substation on substation.id = powerplant.M_SUBSTATION_ID\n" + 
				"left join v_codes talukcodes on powerplant.Taluk_Code = talukcodes.Value_Code AND  talukcodes.list_code = 'TALUK_CODE'\n" + 
				"left join v_codes districtcodes on powerplant.District_Code = districtcodes.Value_Code AND  districtcodes.list_code = 'DISTRICT_CODE'\n" + 
				"left join v_codes statecodes on powerplant.State_Code = statecodes.Value_Code AND  statecodes.list_code = 'STATE_CODE'\n" + 
				"left join v_codes pltalukcodes on powerplant.PL_TALUK_CODE = pltalukcodes.Value_Code AND  pltalukcodes.list_code = 'TALUK_CODE'\n" + 
				"left join v_codes pldistrictcodes on powerplant.PL_DISTRICT_CODE = pldistrictcodes.Value_Code AND  pldistrictcodes.list_code = 'DISTRICT_CODE'\n" + 
				"left join v_codes windpasscode on powerplant.WIND_PASS_CODE = windpasscode.Value_Code and windpasscode.list_code = 'WIND_PASS_CODE'\n" + 
				"left join m_company_service service on service.id=powerplant.M_SERVICE_ID \n" + 
				"left join m_company company on service.m_company_id = company.id where 1=1";
		if(!criteria.isEmpty())
		{
			for (Entry<String,String> element : criteria.entrySet()){
				if(element.getKey().equals("plant_type_code"))
					sql+=" and powerplant.plant_type_code='"+element.getValue()+"'"; 
				if(element.getKey().equals("fuel_code"))
					sql+=" and powerplant.fuel_type_code='"+element.getValue()+"'"; 
				if(element.getKey().equals("code"))
					sql+=" and powerplant.code='"+element.getValue()+"'"; 
				if(element.getKey().equals("org_id"))
					sql+=" and powerplant.m_org_id='"+element.getValue()+"'"; 
				if(element.getKey().equals("company_id"))
					sql+=" and powerplant.m_company_id='"+element.getValue()+"'"; 
			}
				
		}
		System.out.println("sql-"+sql);
		return (ArrayList<Powerplant>) jdbcOperations.query(sql,new PowerplantMapper());
	}

	@Override
	public Powerplant getPowerplantById(String id) {
		
		Powerplant powerplant =  new Powerplant();
		List<Generator> generator = new ArrayList<Generator>();
		
		String sql ="select powerplant.ID,powerplant.IS_PRIMARY,powerplant.VERSION,powerplant.CODE,powerplant.NAME,powerplant.PLANT_TYPE_CODE,plantcodes.Value_Desc as PLANT_TYPE_NAME,\n" + 
				"powerplant.FUEL_TYPE_CODE,fuelcodes.Value_Desc as FUEL_TYPE_NAME,powerplant.M_SERVICE_ID,service.\"number\" as SERVICE_NUMBER,\n" + 
				"company.id as COMPANY_ID ,company.code as COMPANY_CODE ,company.name as COMPANY_NAME,powerplant.M_ORG_ID,\n" + 
				"org.code as M_ORG_CODE, org.name as M_ORG_NAME,powerplant.T_GRID_CONN_APPLN_ID,powerplant.TOTAL_CAPACITY,\n" + 
				"powerplant.m_substation_id,substation.code as M_SUBSTATION_CODE,substation.name as M_SUBSTATION_NAME,powerplant.\n" + 
				"INTERFACE_VOLTAGE_PHASE,powerplant.INTERFACE_VOLTAGE_FREQUENCY,powerplant.COMMISSION_DATE,powerplant.PURPOSE,powerplant.ENABLED,powerplant.STATUS,\n" + 
				"powerplant.LINE1,powerplant.CITY,powerplant.STATE_CODE,statecodes.Value_Desc as STATE_NAME,powerplant.PINCODE,powerplant.VILLAGE,powerplant.TALUK_CODE,\n" + 
				"talukcodes.Value_Desc as TALUK_NAME,powerplant.DISTRICT_CODE,districtcodes.VALUE_DESC as DISTRICT_NAME,powerplant.PLS_SF_NO,\n" + 
				"powerplant.PL_VILLAGE,powerplant.PL_TOWN,powerplant.PL_TALUK_CODE,pltalukcodes.Value_Desc as PL_TALUK_NAME,powerplant.PL_DISTRICT_CODE,pldistrictcodes.VALUE_DESC as PL_DISTRICT_NAME,\n" + 
				"powerplant.WIND_PASS_CODE,windpasscode.VALUE_DESC as WIND_PASS_NAME,powerplant.WIND_ZONE_AREA_CODE,powerplant.APPLICATION_DT,powerplant.APPROVAL_DT,\n" + 
				"powerplant.REMARKS,powerplant.CREATED_BY,powerplant.CREATED_DATE,powerplant.MODIFIED_BY,powerplant.MODIFIED_DATE\n" + 
				"FROM m_powerplant powerplant\n" + 
				"left join v_codes plantcodes on powerplant.PLANT_TYPE_CODE= plantcodes.Value_Code    AND  plantcodes.list_code = 'PLANT_TYPE_CODE'\n" + 
				"left join v_codes fuelcodes on powerplant.FUEL_TYPE_CODE= fuelcodes.Value_Code    AND  fuelcodes.list_code = 'FUEL_TYPE_CODE'\n" + 
				"left join m_org org on org.id=powerplant.M_ORG_ID\n" + 
				"left join m_substation substation on substation.id = powerplant.M_SUBSTATION_ID\n" + 
				"left join v_codes talukcodes on powerplant.Taluk_Code = talukcodes.Value_Code AND  talukcodes.list_code = 'TALUK_CODE'\n" + 
				"left join v_codes districtcodes on powerplant.District_Code = districtcodes.Value_Code AND  districtcodes.list_code = 'DISTRICT_CODE'\n" + 
				"left join v_codes statecodes on powerplant.State_Code = statecodes.Value_Code AND  statecodes.list_code = 'STATE_CODE'\n" + 
				"left join v_codes pltalukcodes on powerplant.PL_TALUK_CODE = pltalukcodes.Value_Code AND  pltalukcodes.list_code = 'TALUK_CODE'\n" + 
				"left join v_codes pldistrictcodes on powerplant.PL_DISTRICT_CODE = pldistrictcodes.Value_Code AND  pldistrictcodes.list_code = 'DISTRICT_CODE'\n" + 
				"left join v_codes windpasscode on powerplant.WIND_PASS_CODE = windpasscode.Value_Code and windpasscode.list_code = 'WIND_PASS_CODE'\n" + 
				"left join m_company_service service on service.id=powerplant.M_SERVICE_ID \n" + 
				"left join m_company company on service.m_company_id = company.id where powerplant.id = ?";
		
		
		
		String sql1= "select generator.id,generator.m_powerplant_id ,generator.name,generator.make_code,generatormodelcode.value_desc as MAKE_CODE_NAME,generator.serial_no,generator.rotor_dia, \n" + 
				"generator.hub_height,generator.reference_id,generator.capacity,generator.voltage_code,voltagecode.value_desc as VOLTAGE_NAME,generator.enabled,generator.no_of_units\n" + 
				"from m_generator generator\n" + 
				"left join v_codes generatormodelcode on generator.make_code = generatormodelcode.value_code and generatormodelcode.list_code='GENERATOR_MAKE_CODE'\n" + 
				"left join v_codes voltagecode on generator.voltage_code=voltagecode.value_code and voltagecode.list_code='VOLTAGE_CODE' where m_powerplant_id=?";
		
		 powerplant = jdbcOperations.queryForObject(sql,new Object[]{id},new PowerplantMapper());
		 generator =jdbcOperations.query(sql1,new Object[]{id},new GeneratorMapper());
		 powerplant.setGenerators(generator);
		
		
		return powerplant;
	}

	@Override
	public String addPowerplant(Powerplant powerplant) {
		String result = "";
		try {
			//generate primary key
			powerplant.setId(generateId());
			if(powerplant.getCode()== null || powerplant.getCode().isEmpty()){
				powerplant.setCode(generateCode(Powerplant.class.getSimpleName(),""));
			}
			//insert record			
			String sql1="insert into m_Powerplant(IS_PRIMARY,VERSION,CODE,NAME,PLANT_TYPE_CODE,FUEL_TYPE_CODE,M_SERVICE_ID,M_ORG_ID,T_GRID_CONN_APPLN_ID,TOTAL_CAPACITY,m_substation_id,INTERFACE_VOLTAGE_PHASE,INTERFACE_VOLTAGE_FREQUENCY,COMMISSION_DATE,PURPOSE,ENABLED,STATUS,LINE1,CITY,STATE_CODE,PINCODE,VILLAGE,TALUK_CODE,DISTRICT_CODE,PLS_SF_NO,PL_VILLAGE,PL_TOWN,PL_TALUK_CODE,PL_DISTRICT_CODE,WIND_PASS_CODE,WIND_ZONE_AREA_CODE,APPLICATION_DT,APPROVAL_DT,ID)values(?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?)";		
			if(jdbcOperations.update(sql1, setValues(powerplant)) > 0){
				result =powerplant.getId();				
				
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
	public String updatePowerplant(String id,Powerplant powerplant) {
		String result = "";
		powerplant.setId(id);
		try {
			String sql="update m_Powerplant set IS_PRIMARY=?,VERSION=?,CODE=?,NAME=?,PLANT_TYPE_CODE=?,FUEL_TYPE_CODE=?,M_SERVICE_ID=?,M_ORG_ID=?,T_GRID_CONN_APPLN_ID=?,TOTAL_CAPACITY=?,M_SUBSTATION_ID=?,INTERFACE_VOLTAGE_PHASE=?,INTERFACE_VOLTAGE_FREQUENCY=?,COMMISSION_DATE=TO_DATE(?,'yyyy-mm-dd'),PURPOSE=?,ENABLED=?,STATUS=?,LINE1=?,CITY=?,STATE_CODE=?,PINCODE=?,VILLAGE=?,TALUK_CODE=?,DISTRICT_CODE=?,PLS_SF_NO=?,PL_VILLAGE=?,PL_TOWN=?,PL_TALUK_CODE=?,PL_DISTRICT_CODE=?,WIND_PASS_CODE=?,WIND_ZONE_AREA_CODE=?,APPLICATION_DT=TO_DATE(?,'yyyy-mm-dd'),APPROVAL_DT=TO_DATE(?,'yyyy-mm-dd') where id=?";
			
			if(jdbcOperations.update(sql,setValues(powerplant)) > 0){
				result =powerplant.getId();
			}
			else{
				System.out.println("hi");
				result =  "FAILURE";
			}
		} catch (Exception e) {
			result =  "FAILURE";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deletePowerplant(String id) {
		String result = "";
		try {
			String sql="delete from m_Powerplant where id=?";
			if(jdbcOperations.update(sql,new Object[]{id})>0){
				result =  id;
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
	
	public String addGenerator(Generator generator) {
	
		 String result ="";		 
		 try {
			 
			 generator.setId(generateId());
			 String sql = "insert into m_generator(m_powerplant_id,name,make_code,serial_no,rotor_dia,hub_height,capacity,REFERENCE_ID,voltage_code,enabled,no_of_units,id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			 if(jdbcOperations.update(sql,setGeneratorValues(generator))>0) {
				 result = generator.getId();				 
			 }else{
					result =  "FAILURE";
				}
			 
		 }catch(Exception e) {
			 result =  "FAILURE";
			 e.printStackTrace();
		 }
		
		
		return result;
	}
	
	public String updateGenerator(Generator generator) {

		String result="";
		try {
					
			String sql="update m_generator set m_powerplant_id=?,name=?,make_code=?,serial_no=?,rotor_dia=?,hub_height=?,capacity=?,REFERENCE_ID=?,voltage_Code=?,enabled=?,no_of_units=? where id=?";
			
		if(jdbcOperations.update(sql, setGeneratorValues(generator))>0) {
			result=generator.getId();	
			
		}else {
			result = "FAILURE";
			
		}
		System.out.println(result);
		}catch(Exception e) {
			result="FAILURE";
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
	}
	
	private Object[] setValues(Powerplant powerplant){
		return new Object[]{
				powerplant.getIsPrimary(),
				powerplant.getVersion(),
				powerplant.getCode(),
				powerplant.getName(),
				powerplant.getPlantTypeCode(),
				powerplant.getFuelTypeCode(),
				powerplant.getServiceId(),
				powerplant.getOrgId(),
				powerplant.getGcId(),
				powerplant.getTotalCapacity(),
				powerplant.getSubStationId(),
				powerplant.getInterfaceVoltagePhase(),
				powerplant.getInterfaceVoltageFrequency(),
				powerplant.getCommissionDate(),
				powerplant.getPurpose(),
				powerplant.getEnabled(),
				powerplant.getStatus(),
				powerplant.getLine1(),
				powerplant.getCity(),
				powerplant.getStateCode(),
				powerplant.getPinCode(),
				powerplant.getVillage(),
				powerplant.getTalukCode(),
				powerplant.getDistrictCode(),
				powerplant.getPlSfNo(),
				powerplant.getPlVillage(),
				powerplant.getPlTown(),
				powerplant.getPlTalukCode(),
				powerplant.getPlDistrictCode(),
				powerplant.getWindPassCode(),
				powerplant.getWindZoneAreaCode(),
				powerplant.getApplicationDate(),
				powerplant.getApprovalDate(),
				powerplant.getId()
				};
	}
	
	private Object[] setGeneratorValues(Generator generator){
		return new Object[]{
				generator.getPowerplantId(),
				generator.getName(),
				generator.getMakeCode(),
				generator.getSerialNumber(),
				generator.getRotorDia(),
				generator.getHubHeight(),
				generator.getCapacity(),
				generator.getRefId(),
				generator.getVoltageCode(),
				generator.getEnabled(),
				generator.getNoOfUnits(),
				generator.getId()
				
				};
	}
	
	final class PowerplantMapper implements RowMapper<Powerplant>
	{

		public PowerplantMapper() {
				super();
		}

		public Powerplant mapRow(ResultSet resultset, int rownum) throws SQLException {
			Powerplant powerplant=new Powerplant();
			powerplant.setId(resultset.getString("id"));
			powerplant.setIsPrimary(resultset.getString("is_primary"));
			powerplant.setVersion(resultset.getString("version"));
			powerplant.setCode(resultset.getString("code"));
			powerplant.setName(resultset.getString("name"));
			powerplant.setPlantTypeCode(resultset.getString("plant_type_code"));
			powerplant.setPlantTypeName(resultset.getString("plant_type_name"));
			powerplant.setFuelTypeCode(resultset.getString("fuel_type_code"));	
			powerplant.setFuelTypeName(resultset.getString("fuel_type_name"));	
			powerplant.setServiceId(resultset.getString("m_service_id"));	
			powerplant.setServiceNumber(resultset.getString("SERVICE_NUMBER"));
			powerplant.setCompanyId(resultset.getString("COMPANY_ID"));
			powerplant.setCompanyCode(resultset.getString("COMPANY_CODE"));
			powerplant.setCompanyName(resultset.getString("COMPANY_NAME"));
			powerplant.setOrgId(resultset.getString("m_org_id"));
			powerplant.setOrgCode(resultset.getString("m_org_code"));
			powerplant.setOrgName(resultset.getString("m_org_name"));
			powerplant.setGcId(resultset.getString("T_GRID_CONN_APPLN_ID"));
			powerplant.setTotalCapacity(resultset.getString("total_capacity"));
			powerplant.setSubStationId(resultset.getString("m_substation_id"));		
			powerplant.setSubStationCode(resultset.getString("m_substation_code"));	
			powerplant.setSubStationName(resultset.getString("m_substation_name"));	
			powerplant.setInterfaceVoltagePhase(resultset.getString("INTERFACE_VOLTAGE_PHASE"));
			powerplant.setInterfaceVoltageFrequency(resultset.getString("INTERFACE_VOLTAGE_FREQUENCY"));
			powerplant.setCommissionDate(resultset.getString("COMMISSION_DATE"));
			powerplant.setPurpose(resultset.getString("purpose"));
			powerplant.setEnabled(resultset.getString("enabled"));
			powerplant.setStatus(resultset.getString("status"));
			powerplant.setLine1(resultset.getString("line1"));
			powerplant.setCity(resultset.getString("city"));
			powerplant.setStateCode(resultset.getString("state_code"));
			powerplant.setStateName(resultset.getString("state_name"));
			powerplant.setPinCode(resultset.getString("pincode"));
			powerplant.setVillage(resultset.getString("village"));
			powerplant.setTalukCode(resultset.getString("taluk_code"));		
			powerplant.setTalukName(resultset.getString("taluk_name"));	
			powerplant.setDistrictCode(resultset.getString("district_code"));	
			powerplant.setDistrictName(resultset.getString("district_name"));
			powerplant.setPlSfNo(resultset.getString("PLS_SF_NO"));
			powerplant.setPlVillage(resultset.getString("pl_village"));
			powerplant.setPlTown(resultset.getString("pl_town"));
			powerplant.setPlTalukCode(resultset.getString("PL_TALUK_CODE"));
			powerplant.setPlTalukName(resultset.getString("PL_TALUK_NAME"));
			powerplant.setPlDistrictCode(resultset.getString("PL_DISTRICT_CODE"));
			powerplant.setPlDistrictName(resultset.getString("PL_DISTRICT_NAME"));
			powerplant.setWindPassCode(resultset.getString("WIND_PASS_CODE"));
			powerplant.setWindPassName(resultset.getString("WIND_PASS_NAME"));
			powerplant.setWindZoneAreaCode(resultset.getString("WIND_ZONE_AREA_CODE"));	
			powerplant.setApplicationDate(resultset.getString("APPLICATION_DT"));
			powerplant.setApprovalDate(resultset.getString("APPROVAL_DT"));

			return powerplant;
		}
	}
	
	final class GeneratorMapper implements RowMapper<Generator>
	{

		public GeneratorMapper() {
				super();
		}

		public Generator mapRow(ResultSet resultset, int rownum) throws SQLException {
			Generator generator = new Generator();
			generator.setId(resultset.getString("id"));
			generator.setPowerplantId(resultset.getString("m_powerplant_id"));
			generator.setName(resultset.getString("name"));
			generator.setMakeCode(resultset.getString("make_code"));
			generator.setMakeName(resultset.getString("make_code_name"));
			generator.setSerialNumber(resultset.getString("serial_no"));
			generator.setRotorDia(resultset.getString("rotor_dia"));
			generator.setHubHeight(resultset.getString("hub_height"));
			generator.setRefId(resultset.getString("reference_id"));
			generator.setCapacity(resultset.getString("capacity"));
			generator.setVoltageCode(resultset.getString("voltage_code"));
			generator.setVoltageName(resultset.getString("voltage_name"));
			generator.setEnabled(resultset.getString("enabled"));
			generator.setNoOfUnits(resultset.getString("NO_OF_UNITS"));
			
			return generator;
		}
	}
}

