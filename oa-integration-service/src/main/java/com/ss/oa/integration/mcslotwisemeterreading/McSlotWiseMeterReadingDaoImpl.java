package com.ss.oa.integration.mcslotwisemeterreading;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.vo.McSlotWiseMeterReading;
@Component
public class McSlotWiseMeterReadingDaoImpl extends BaseDaoJdbc implements McSlotWiseMeterReadingDao {
	@Resource
	@Qualifier("appJdbc")
	private JdbcOperations jdbcOperations;
	
	@Override
	public  List<McSlotWiseMeterReading> getAllSlotWiseMeterReading(Map<String,String>criteria){
		
		String sql="SELECT '' ID,slotwise.METERNO,slotwise.MF,slotwise.SERVICENO,to_char(slotwise.SYS_DT,'yyyy-mm-dd') SYS_DT,to_char(slotwise.INITIAL_READING_DATE,'yyyy-mm-dd') INITIAL_READING_DATE,\n" + 
				"                to_char(slotwise.FINAL_READING_DATE,'yyyy-mm-dd') FINAL_READING_DATE,slotwise.IMP_INIT_S1,slotwise.IMP_INIT_S2,slotwise.IMP_INIT_S3,slotwise.IMP_INIT_S4,\n" + 
				"                slotwise.IMP_INIT_S5,slotwise.IMP_FINAL_S1,slotwise.IMP_FINAL_S2,slotwise.IMP_FINAL_S3,slotwise.IMP_FINAL_S4,slotwise.IMP_FINAL_S5,\n" + 
				"                slotwise.EXP_INIT_S1,slotwise.EXP_INIT_S2,slotwise.EXP_INIT_S3,slotwise.EXP_INIT_S4,slotwise.EXP_INIT_S5,\n" + 
				"                slotwise.EXP_FINAL_S1,slotwise.EXP_FINAL_S2,slotwise.EXP_FINAL_S3,slotwise.EXP_FINAL_S4,slotwise.EXP_FINAL_S5,slotwise.IMP_KVAH_INIT,\n" + 
				"                slotwise.EXP_KVAH_INIT,slotwise.IMP_KVAH_FINAL,slotwise.EXP_KVAH_FINAL,slotwise.Q1_KVARH_INIT,slotwise.Q2_KVARH_INIT,slotwise.Q3_KVARH_INIT,slotwise.Q4_KVARH_INIT,slotwise.Q1_KVARH_FINAL,slotwise.DOWNLOADSTATUS,\n" + 
				"                slotwise.Q2_KVARH_FINAL,slotwise.Q3_KVARH_FINAL,slotwise.Q4_KVARH_FINAL,to_char(slotwise.initial_reading_Date,'mm') MONTH,to_char(slotwise.initial_reading_Date,'YYYY') year \n" + 
				"                FROM UV_TBL_HISTORY_SLOT_MCHANGE slotwise WHERE 1=1 ";
		
		if(!criteria.isEmpty())
		{
			if(criteria.get("meter-number")!=null){
				sql += "and upper(slotwise.METERNO)  like upper('%"+criteria.get("meter-number")+"%') ";
			}
			if(criteria.get("service-number")!=null){
				sql += "and upper(slotwise.SERVICENO)  like upper('%"+criteria.get("service-number")+"%') ";
			}
			if(criteria.get("initial-reading-date")!=null){
				sql += "and upper(slotwise.INITIAL_READING_DATE)  like upper('%"+criteria.get("initial-reading-date")+"%') ";
			}
			if(criteria.get("final-reading-date")!=null){
				sql += "and upper(slotwise.FINAL_READING_DATE)  like upper('%"+criteria.get("final-reading-date")+"%') ";
			}
			if(criteria.get("month")!=null){
				sql += "and upper(to_char(slotwise.initial_reading_Date,'mm'))  like upper('%"+criteria.get("month")+"%') ";
			}
			if(criteria.get("year")!=null){
				sql += "and upper(to_char(slotwise.initial_reading_Date,'yyyy'))  like upper('%"+criteria.get("year")+"%') ";
			}
		
		}
		System.out.println(sql);
		
		return (ArrayList<McSlotWiseMeterReading>) jdbcOperations.query(sql,new SlotWiseMeterReadingMapper());		
	}
	
	@Override
	public McSlotWiseMeterReading getSlotWiseMeterReadingById(String id) {
		
		
		 
	String sql="SELECT '' ID,slotwise.METERNO,slotwise.MF,slotwise.SERVICENO,to_char(slotwise.SYS_DT,'yyyy-mm-dd') SYS_DT,to_char(slotwise.INITIAL_READING_DATE,'yyyy-mm-dd') INITIAL_READING_DATE,\n" + 
			"                to_char(slotwise.FINAL_READING_DATE,'yyyy-mm-dd') FINAL_READING_DATE,slotwise.IMP_INIT_S1,slotwise.IMP_INIT_S2,slotwise.IMP_INIT_S3,slotwise.IMP_INIT_S4,\n" + 
			"                slotwise.IMP_INIT_S5,slotwise.IMP_FINAL_S1,slotwise.IMP_FINAL_S2,slotwise.IMP_FINAL_S3,slotwise.IMP_FINAL_S4,slotwise.IMP_FINAL_S5,\n" + 
			"                slotwise.EXP_INIT_S1,slotwise.EXP_INIT_S2,slotwise.EXP_INIT_S3,slotwise.EXP_INIT_S4,slotwise.EXP_INIT_S5,\n" + 
			"                slotwise.EXP_FINAL_S1,slotwise.EXP_FINAL_S2,slotwise.EXP_FINAL_S3,slotwise.EXP_FINAL_S4,slotwise.EXP_FINAL_S5,slotwise.IMP_KVAH_INIT,\n" + 
			"                slotwise.EXP_KVAH_INIT,slotwise.IMP_KVAH_FINAL,slotwise.EXP_KVAH_FINAL,slotwise.Q1_KVARH_INIT,slotwise.Q2_KVARH_INIT,slotwise.Q3_KVARH_INIT,slotwise.Q4_KVARH_INIT,slotwise.Q1_KVARH_FINAL,slotwise.DOWNLOADSTATUS,\n" + 
			"                slotwise.Q2_KVARH_FINAL,slotwise.Q3_KVARH_FINAL,slotwise.Q4_KVARH_FINAL,to_char(slotwise.initial_reading_Date,'mm') MONTH,to_char(slotwise.initial_reading_Date,'YYYY') year \n" + 
			"                FROM TANGEDCO.UV_TBL_HISTORY_SLOT_MCHANGE slotwise WHERE slotwise.ID=?";
	
	return jdbcOperations.queryForObject(sql,new Object[]{id},new SlotWiseMeterReadingMapper());
	
   }
	
	final class SlotWiseMeterReadingMapper implements RowMapper<McSlotWiseMeterReading>{
		
		public SlotWiseMeterReadingMapper() {
			super();
		}
		
		@Override
		public McSlotWiseMeterReading mapRow(ResultSet resultSet, int rownum) throws SQLException {
			McSlotWiseMeterReading slotWiseMeterReading=new McSlotWiseMeterReading();
		
			slotWiseMeterReading.setId(resultSet.getString("ID"));
			slotWiseMeterReading.setmF(resultSet.getString("MF"));
			slotWiseMeterReading.setMeterNumber(resultSet.getString("METERNO"));
			slotWiseMeterReading.setServiceNumber(resultSet.getString("SERVICENO"));
			slotWiseMeterReading.setSystemDate(resultSet.getString("SYS_DT"));
			slotWiseMeterReading.setInitialReadingDate(resultSet.getString("INITIAL_READING_DATE"));
			slotWiseMeterReading.setFinalReadingDate(resultSet.getString("FINAL_READING_DATE"));
			slotWiseMeterReading.setImpInitS1(resultSet.getString("IMP_INIT_S1"));
			slotWiseMeterReading.setImpInitS2(resultSet.getString("IMP_INIT_S2"));;
			slotWiseMeterReading.setImpInitS3(resultSet.getString("IMP_INIT_S3"));
			slotWiseMeterReading.setImpInitS4(resultSet.getString("IMP_INIT_S4"));
			slotWiseMeterReading.setImpInitS5(resultSet.getString("IMP_INIT_S5"));
			slotWiseMeterReading.setImpFinalS1(resultSet.getString("IMP_FINAL_S1"));
			slotWiseMeterReading.setImpFinalS2(resultSet.getString("IMP_FINAL_S2"));
			slotWiseMeterReading.setImpFinalS3(resultSet.getString("IMP_FINAL_S3"));
			slotWiseMeterReading.setImpFinalS4(resultSet.getString("IMP_FINAL_S4"));
			slotWiseMeterReading.setImpFinalS5(resultSet.getString("IMP_FINAL_S5"));
			slotWiseMeterReading.setExpInitS1(resultSet.getString("EXP_INIT_S1"));
			slotWiseMeterReading.setExpInitS2(resultSet.getString("EXP_INIT_S2"));
			slotWiseMeterReading.setExpInitS3(resultSet.getString("EXP_INIT_S3"));
			slotWiseMeterReading.setExpInitS4(resultSet.getString("EXP_INIT_S4"));
			slotWiseMeterReading.setExpInitS5(resultSet.getString("EXP_INIT_S5"));
			slotWiseMeterReading.setExpFinalS1(resultSet.getString("EXP_FINAL_S1"));	
			slotWiseMeterReading.setExpFinalS2(resultSet.getString("EXP_FINAL_S2"));				
			slotWiseMeterReading.setExpFinalS3(resultSet.getString("EXP_FINAL_S3"));				
			slotWiseMeterReading.setExpFinalS4(resultSet.getString("EXP_FINAL_S4"));				
			slotWiseMeterReading.setExpFinalS5(resultSet.getString("EXP_FINAL_S5"));				
			slotWiseMeterReading.setImpKvahInit(resultSet.getString("IMP_KVAH_INIT"));
			slotWiseMeterReading.setImpKvahFinal(resultSet.getString("IMP_KVAH_FINAL"));
			slotWiseMeterReading.setExpKvahInit(resultSet.getString("EXP_KVAH_INIT"));
			slotWiseMeterReading.setExpKvahFinal(resultSet.getString("EXP_KVAH_FINAL"));
			slotWiseMeterReading.setQ1KvarhFinal(resultSet.getString("Q1_KVARH_FINAL"));
			slotWiseMeterReading.setQ2KvarhFinal(resultSet.getString("Q2_KVARH_FINAL"));
			slotWiseMeterReading.setQ3KvarhFinal(resultSet.getString("Q3_KVARH_FINAL"));
			slotWiseMeterReading.setQ4KvarhFinal(resultSet.getString("Q4_KVARH_FINAL"));
			slotWiseMeterReading.setQ1KvarhInit(resultSet.getString("Q1_KVARH_INIT"));
			slotWiseMeterReading.setQ2KvarhInit(resultSet.getString("Q2_KVARH_INIT"));
			slotWiseMeterReading.setQ3KvarhInit(resultSet.getString("Q3_KVARH_INIT"));
			slotWiseMeterReading.setQ4KvarhInit(resultSet.getString("Q4_KVARH_INIT"));
			slotWiseMeterReading.setMonth(resultSet.getString("MONTH"));
			slotWiseMeterReading.setYear(resultSet.getString("YEAR"));
			slotWiseMeterReading.setDownloadStatus(resultSet.getString("DOWNLOADSTATUS"));


			
			return slotWiseMeterReading ;
		}
		
	  }

}
