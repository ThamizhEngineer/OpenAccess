package com.ss.oa.transaction.meterreading;

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
import com.ss.oa.transaction.vo.MeterReading;
import com.ss.oa.transaction.vo.MeterReadingSlot;


@Scope("prototype")
@Component
public class MeterReadingDaoImpl extends BaseDaoJdbc implements MeterReadingDao {
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	@Override
	public List<MeterReading> getAllMeterReadings(Map<String, String> criteria) {
		
		String sql="select meterreading.ID,meterreading.M_COMPANY_METER_ID,\n" + 
				"				 	companymeter.METER_NUMBER,companymeter.MODEM_NUMBER,companyservice.M_ORG_ID,companyservice.M_ORG_NAME,companymeter.M_COMPANY_SERVICE_ID,companyservice.\"number\" as SERVICE_NUMBER,\n" + 
				"				    companyservice.M_COMPANY_ID,company.NAME as COMPANY_NAME,company.CODE as COMPANY_CODE,companyservice.FUEL_TYPE_CODE as FUEL_TYPE_CODE,fuel.FUEL_NAME as FUEL_TYPE_NAME,fuel.FUEL_GROUP as FUEL_GROUPE,\n" + 
				"				    meterreading.STATUS_CODE,meterreading.REMARKS,meterreading.MF,meterreading.SYS_DT,meterreading.READING_MONTH,meterreading.READING_YEAR,meterreading.INIT_READING_DT,meterreading.FINAL_READING_DT,\n" + 
				"				    meterreading.RKVAH_DIFF ,meterreading.RKVAH_UNITS ,meterreading.KVAH_DIFF,\n" + 
				"				 	meterreading.KVAH_UNITS ,meterreading.TOTAL_IMPORT_GEN ,meterreading.TOTAL_EXPORT_GEN,meterreading.IMP_KVAH_INIT,meterreading.IMP_KVAH_FINAL,\n" + 
				"				 	meterreading.EXP_KVAH_INIT,meterreading.EXP_KVAH_FINAL,meterreading.IMP_RKVAH_INIT,meterreading.IMP_RKVAH_FINAL,meterreading.EXP_RKVAH_INIT,\n" + 
				"				 	meterreading.EXP_RKVAH_FINAL,meterreading.MR_SOURCE_CODE from T_METER_READING_HDR meterreading\n" + 
				"				 	left join M_COMPANY_METER companymeter on meterreading.M_COMPANY_METER_ID = companymeter.id\n" + 
				"				 	left join v_COMPANY_SERVICE companyservice on companymeter.M_COMPANY_SERVICE_ID = companyservice.id\n" + 
				"					left join M_FUEL fuel on companyservice.FUEL_TYPE_CODE = fuel.FUEL_CODE \n" +			
				"				 	left join M_COMPANY company on companyservice.M_COMPANY_ID = company.id WHERE 1=1";
		
		if(!criteria.isEmpty())
		{
			for (Entry<String,String> element : criteria.entrySet()){
				if(element.getKey().equals("company-id"))
					sql+=" and companyservice.M_COMPANY_ID='"+element.getValue()+"'"; 
		
				if(element.getKey().equals("meter-id"))
					sql+=" and meterreading.M_COMPANY_METER_ID='"+element.getValue()+"'"; 
				if(element.getKey().equals("year"))
					sql+=" and meterreading.READING_YEAR='"+element.getValue()+"'"; 
				if(element.getKey().equals("month"))
					sql+=" and meterreading.READING_MONTH='"+element.getValue()+"'";
				if(element.getKey().equals("meter-number"))
					sql+=" and companymeter.METER_NUMBER='"+element.getValue()+"'"; 
				if(element.getKey().equals("company-service-id"))
					sql+=" and companymeter.M_COMPANY_SERVICE_ID='"+element.getValue()+"'"; 
				if(element.getKey().equals("modem-number"))
					sql+=" and companymeter.MODEM_NUMBER='"+element.getValue()+"'"; 
				if(element.getKey().equals("org-id"))
					sql+=" and companyservice.M_ORG_ID='"+element.getValue()+"'"; 
				if(element.getKey().equals("fuel-code"))
					sql+=" and companyservice.FUEL_TYPE_CODE='"+element.getValue()+"'"; 
				if(element.getKey().equals("company-service-number"))
					sql+=" and companyservice.\"number\"='"+element.getValue()+"'"; 
				if(element.getKey().equals("company-name"))
					sql+=" and company.name like '%"+element.getValue()+"%'"; 
				if(element.getKey().equals("fuel-type-name"))
					sql+=" and fuel.FUEL_NAME like '%"+element.getValue()+"%'";
				if(element.getKey().equals("fuel-group"))
					sql+=" and fuel.FUEL_GROUP like '%"+element.getValue()+"%'";
				if(element.getKey().equals("flowTypecode"))
					sql+=" and companyservice.TR_FLOW_TYPE_CODE like '%"+element.getValue()+"%'";
				
			}
		 
		}
		if(!sql.isEmpty())
		{
			sql+="  ORDER BY to_NUMBER(meterreading.reading_year) desc,to_NUMBER(meterreading.reading_month) desc ";
		}

		return (ArrayList<MeterReading>) jdbcOperations.query(sql,new MeterReadingMapper());
	
	}

	@Override
	public MeterReading getMeterReadingById(String id) {
		MeterReading meterReading = new MeterReading(); 
		List<MeterReadingSlot> meterReadingSlot = new ArrayList<MeterReadingSlot>();
		String sql="select meterreading.ID,meterreading.M_COMPANY_METER_ID,\n" + 
				"	companymeter.METER_NUMBER,companymeter.MODEM_NUMBER,companyservice.M_ORG_ID,companyservice.M_ORG_NAME,companymeter.M_COMPANY_SERVICE_ID,companyservice.\"number\" as SERVICE_NUMBER,\n" + 
				"   companyservice.M_COMPANY_ID,company.NAME as COMPANY_NAME,company.CODE as COMPANY_CODE,companyservice.Fuel_Type_Code as FUEL_TYPE_CODE,companyservice.Fuel_Type_Name as FUEL_TYPE_NAME,\n" + 
				"   meterreading.STATUS_CODE,meterreading.REMARKS,meterreading.MF,meterreading.SYS_DT,meterreading.READING_MONTH,meterreading.READING_YEAR,meterreading.INIT_READING_DT,meterreading.FINAL_READING_DT,\n" + 
				"	meterreading.RKVAH_DIFF ,meterreading.RKVAH_UNITS ,meterreading.KVAH_DIFF,\n" + 
				"	meterreading.KVAH_UNITS ,meterreading.TOTAL_IMPORT_GEN ,meterreading.TOTAL_EXPORT_GEN,meterreading.IMP_KVAH_INIT,meterreading.IMP_KVAH_FINAL,\n" + 
				"   meterreading.EXP_KVAH_INIT,meterreading.EXP_KVAH_FINAL,meterreading.IMP_RKVAH_INIT,meterreading.IMP_RKVAH_FINAL,meterreading.EXP_RKVAH_INIT,\n" + 
				"   meterreading.EXP_RKVAH_FINAL,meterreading.MR_SOURCE_CODE from T_METER_READING_HDR meterreading\n" + 
				"	left join M_COMPANY_METER companymeter on meterreading.M_COMPANY_METER_ID = companymeter.id\n" + 
				"	left join v_COMPANY_SERVICE companyservice on companymeter.M_COMPANY_SERVICE_ID = companyservice.id\n" + 
				"	left join M_COMPANY company on companyservice.M_COMPANY_ID = company.id  WHERE meterreading.ID=?";
		
		
		 meterReading = jdbcOperations.queryForObject(sql,new Object[]{id},new MeterReadingMapper());
		 
		 String sql2="select ID,T_METER_READING_HDR_ID,REMARKS,REF_NUMBER,SLOT_CODE,IMP_INIT,IMP_FINAL,IMP_DIFF,IMP_UNITS,EXP_INIT,EXP_FINAL,EXP_DIFF,EXP_UNITS from T_METER_READING_SLOT where T_METER_READING_HDR_ID = ?";
		
		 meterReadingSlot = jdbcOperations.query(sql2,new Object[]{id},new  MeterReadingSlotMapper());
		 meterReading.setMeterReadingSlot(meterReadingSlot);	
		return meterReading;
		
	}

	@Override
	public String addMeterReading(MeterReading meterReading) {
		String result="";
		
		try {
			meterReading.setId(generateId());
			String sql = "insert into T_METER_READING_HDR(M_COMPANY_METER_ID,STATUS_CODE,REMARKS,MF,SYS_DT,READING_MONTH,READING_YEAR,INIT_READING_DT,FINAL_READING_DT,\n" + 
					"RKVAH_DIFF,RKVAH_UNITS,KVAH_DIFF,KVAH_UNITS,TOTAL_IMPORT_GEN,TOTAL_EXPORT_GEN,MR_SOURCE_CODE,ID)\n" + 
					"values(?,?,?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?,?,?,?)"; 
			
			if(jdbcOperations.update(sql,setMeterReadingValues(meterReading))>0) {
				result = meterReading.getId();
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
	public String updateMeterReading(String id, MeterReading meterReading) {
		String result="";
		
		try {
			String sql="update T_METER_READING_HDR set M_COMPANY_METER_ID=?,STATUS_CODE=?,REMARKS=?,MF=?,SYS_DT=TO_DATE(?,'yyyy-mm-dd'),READING_MONTH=?,READING_YEAR=?,INIT_READING_DT=TO_DATE(?,'yyyy-mm-dd'),FINAL_READING_DT=TO_DATE(?,'yyyy-mm-dd'),\n" + 
					"RKVAH_DIFF=?,RKVAH_UNITS=?,KVAH_DIFF=?,KVAH_UNITS=?,TOTAL_IMPORT_GEN=?,TOTAL_EXPORT_GEN=?,MR_SOURCE_CODE=? where id= ?";
					
			
			if(jdbcOperations.update(sql,setMeterReadingValues(meterReading)) > 0){
				result =meterReading.getId();
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

	
	private Object[] setMeterReadingValues(MeterReading meterReading){
		return new Object[]{
				meterReading.getCompanyMeterId(),
				meterReading.getStatusCode(),
				meterReading.getRemarks(),
				meterReading.getFuelTypeCode(),
				meterReading.getFuelTypeName(),
				
				meterReading.getMf(),				
				meterReading.getSystemDate(),
				meterReading.getReadingMonthCode(),
				meterReading.getReadingYear(),
				meterReading.getInitReadingDate(),
				meterReading.getFinalReadingDate(),
	
				meterReading.getrKvahDifference(),
				meterReading.getrKvahUnits(),
				
				meterReading.getkVahDifference(),
				meterReading.getkVahUnits(),
				meterReading.getTotalImportGeneration(),
				meterReading.getTotalExportGeneration(),
				meterReading.getMrSourceCode(),
				meterReading.getId(),
				
//				meterReading.getModemNumber()
				
				};
	}
	
		public class MeterReadingMapper implements RowMapper<MeterReading>{
		
			public MeterReadingMapper() {
				super();
			}
			
			@Override
			public MeterReading mapRow(ResultSet resultSet, int rownum) throws SQLException {
				
				MeterReading meterReading = new MeterReading();
				
				meterReading.setId(resultSet.getString("id"));
				meterReading.setCompanyMeterId(resultSet.getString("m_company_meter_id"));
				meterReading.setCompanyMeterNumber(resultSet.getString("meter_number"));
				meterReading.setCompanyServiceId(resultSet.getString("M_COMPANY_SERVICE_ID"));
				meterReading.setCompanyServiceNumber(resultSet.getString("SERVICE_NUMBER"));
				meterReading.setCompanyId(resultSet.getString("M_COMPANY_ID")); 
				meterReading.setCompanyName(resultSet.getString("COMPANY_NAME"));
				meterReading.setCompanyCode(resultSet.getString("COMPANY_CODE"));
				meterReading.setFuelTypeCode(resultSet.getString("FUEL_TYPE_CODE"));
				meterReading.setFuelTypeName(resultSet.getString("FUEL_TYPE_NAME"));
		//		meterReading.setFuelGroupe(resultSet.getString("FUEL_GROUPE"));
				meterReading.setStatusCode(resultSet.getString("STATUS_CODE"));
				meterReading.setRemarks(resultSet.getString("REMARKS"));			
		
				meterReading.setMf(resultSet.getString("mf"));			
				meterReading.setSystemDate(resultSet.getString("SYS_DT"));
				meterReading.setReadingMonthCode(resultSet.getString("READING_MONTH"));
				meterReading.setReadingYear(resultSet.getString("READING_YEAR"));
				meterReading.setInitReadingDate(resultSet.getString("INIT_READING_DT"));
				meterReading.setFinalReadingDate(resultSet.getString("FINAL_READING_DT"));
			
				meterReading.setrKvahDifference(resultSet.getString("RKVAH_DIFF"));
				meterReading.setrKvahUnits(resultSet.getString("RKVAH_UNITS"));
			
				meterReading.setkVahInitial(resultSet.getString("IMP_KVAH_INIT"));
				meterReading.setkVahFinal(resultSet.getString("IMP_KVAH_FINAL"));
				meterReading.setrKvahInitial(resultSet.getString("IMP_RKVAH_INIT"));
				meterReading.setrKvahFinal(resultSet.getString("IMP_RKVAH_FINAL"));

				meterReading.seteXpKvahInitial(resultSet.getString("EXP_KVAH_INIT"));
				meterReading.seteXpKvahFinal(resultSet.getString("EXP_KVAH_FINAL"));
				meterReading.seteXpRkvahInitial(resultSet.getString("EXP_RKVAH_INIT"));
				meterReading.seteXpRkvahFinal(resultSet.getString("EXP_RKVAH_FINAL"));
				

			
				meterReading.setkVahDifference(resultSet.getString("KVAH_DIFF"));
				meterReading.setkVahUnits(resultSet.getString("KVAH_UNITS"));
				meterReading.setTotalImportGeneration(resultSet.getString("TOTAL_IMPORT_GEN"));
				meterReading.setTotalExportGeneration(resultSet.getString("TOTAL_EXPORT_GEN"));						
				
				meterReading.setModemNumber(resultSet.getString("MODEM_NUMBER"));
				meterReading.setMrSourceCode(resultSet.getString("MR_SOURCE_CODE"));
				meterReading.setOrgId(resultSet.getString("M_ORG_ID"));
				meterReading.setOrgName(resultSet.getString("M_ORG_NAME"));
				

				return meterReading;
		}
		
	}

		@Override
		public String addMeterReadingSlot(MeterReadingSlot meterReadingSlot) {
			String result="";
			
			try {
				meterReadingSlot.setId(generateId());
				String sql = "insert into  T_METER_READING_SLOT (T_METER_READING_HDR_ID,REMARKS,REF_NUMBER,SLOT_CODE,IMP_INIT,\n" + 
						"IMP_FINAL,IMP_DIFF,IMP_UNITS,EXP_INIT,EXP_FINAL,EXP_DIFF,EXP_UNITS,ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				
				if(jdbcOperations.update(sql,setMeterReadingSlotValues(meterReadingSlot))>0) {
					result = meterReadingSlot.getId();
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
		public String updateMeterReadingSlot( MeterReadingSlot meterReadingSlot) {
			String result="";
			
			try {
				String sql="update T_METER_READING_SLOT set T_METER_READING_HDR_ID=?,REMARKS=?,REF_NUMBER=?,SLOT_CODE=?,IMP_INIT=?,\n" + 
						"IMP_FINAL=?,IMP_DIFF=?,IMP_UNITS=?,EXP_INIT=?,EXP_FINAL=?,EXP_DIFF=?,EXP_UNITS=? where id= ?";
						
				
				if(jdbcOperations.update(sql,setMeterReadingSlotValues(meterReadingSlot)) > 0){
					result =meterReadingSlot.getId();
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
		
		
		private Object[] setMeterReadingSlotValues(MeterReadingSlot meterReadingSlot){
			return new Object[]{
				
				meterReadingSlot.getMeterReadingHeaderId(),
				meterReadingSlot.getRemarks(),
				meterReadingSlot.getReferenceNumber(),
				meterReadingSlot.getSlotCode(),
				meterReadingSlot.getImpInitial(),
				meterReadingSlot.getImpFinal(),
				meterReadingSlot.getImpDifference(),
				meterReadingSlot.getImpUnits(),
				meterReadingSlot.getExpInitial(),
				meterReadingSlot.getExpFinal(),
				meterReadingSlot.getExpDifference(),
				meterReadingSlot.getExpUnits(),
				meterReadingSlot.getId()
					
			};
		}
		
		public class MeterReadingSlotMapper implements RowMapper<MeterReadingSlot>{
			
			public MeterReadingSlotMapper() {
				super();
			}
			
			@Override
			public MeterReadingSlot mapRow(ResultSet resultSet, int rownum) throws SQLException {
				
				MeterReadingSlot meterReadingSlot = new MeterReadingSlot();
				
				meterReadingSlot.setId(resultSet.getString("ID"));
				meterReadingSlot.setMeterReadingHeaderId(resultSet.getString("T_METER_READING_HDR_ID"));
				meterReadingSlot.setRemarks(resultSet.getString("REMARKS"));
				meterReadingSlot.setReferenceNumber(resultSet.getString("REF_NUMBER"));
				meterReadingSlot.setSlotCode(resultSet.getString("SLOT_CODE"));
				meterReadingSlot.setImpInitial(resultSet.getString("IMP_INIT"));
				meterReadingSlot.setImpFinal(resultSet.getString("IMP_FINAL"));
				meterReadingSlot.setImpDifference(resultSet.getString("IMP_DIFF"));
				meterReadingSlot.setImpUnits(resultSet.getString("IMP_UNITS"));
				meterReadingSlot.setExpInitial(resultSet.getString("EXP_INIT"));
				meterReadingSlot.setExpFinal(resultSet.getString("EXP_FINAL"));
				meterReadingSlot.setExpDifference(resultSet.getString("EXP_DIFF"));
				meterReadingSlot.setExpUnits(resultSet.getString("EXP_UNITS"));
				
				return meterReadingSlot;
		}
		
	}
}
