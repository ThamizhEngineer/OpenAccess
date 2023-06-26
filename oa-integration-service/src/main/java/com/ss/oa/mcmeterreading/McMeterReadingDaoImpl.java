package com.ss.oa.mcmeterreading;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.vo.McMeterReading;

@Component
public class McMeterReadingDaoImpl extends BaseDaoJdbc implements McMeterReadingDao{
		@Value("${mdmsdb.schemaname}")     private String mdmsSchemaName;
		@Resource
		@Qualifier("mdmsJdbc")
		private JdbcOperations jdbcOperations;
		
		@Override
		public  List<McMeterReading> getAllMcMeterReadings(){
			String sql="SELECT '' ID, mc.OLDMETERNO, mc.NEWMETERNO, mc.STATUS,\n" + 
					"to_char(to_date(mc.modifydate,'dd-mm-yy')) as METER_CHANGE_DATE,to_char(to_date(to_date('01'||'-'||to_char(to_date(mc.modifydate,'dd-mm-yy'),'mm')||'-'||to_char(to_date(mc.modifydate,'dd-mm-yy'),'yy'),'dd-mm-yy'))) as INIT_STMT_DATE,\n" + 
					"to_char(to_date(to_char(last_day(mc.modifydate),'dd')||'-'||to_char(to_date(mc.modifydate,'dd-mm-yy'),'mm')||'-'||to_char(to_date(mc.modifydate,'dd-mm-yy'),'yy'),'dd-mm-yy')) as FINAL_STMT_DATE,\n" + 
					"to_char(to_date(mc.modifydate,'dd-mm-yy'),'mm') as RD_MNTH,to_char(to_date(mc.modifydate,'dd-mm-yy'),'yyyy') as RD_YR,\n" + 
					"OLDIKWHTOD1, OLDIKWHTOD2, OLDIKWHTOD3, OLDIKWHTOD4, OLDIKWHTOD5,OLDEKWHTOD1, OLETKWHTOD2, OLDEKWHTOD3, OLDEKWHTOD4, OLDEKWHTOD5,\n" + 
					"OLDIKVAH, OLDEKVAH, OLDQ1KVARH, OLDQ2KVARH, OLDQ3KVARH, OLDQ4KVARH, OLDMF,\n" + 
					"NEWIKWHTOD1, NEWIKWHTOD2, NEWIKWHTOD3, NEWIKWHTOD4, NEWIKWHTOD5, NEWEKWHTOD1, NEWEKWHTOD2, NEWEKWHTOD3, NEWEKWHTOD4, NEWEKWHTOD5,\n" + 
					"NEWIKVAH, NEWEKVAH, NEWQ1KVARH, NEWQ2KVARH, NEWQ3KVARH, NEWQ4KVARH, NEWMF, to_char(to_date(modifydate,'dd-mm-yy')) AS MODIFIED_DATE\n" + 
					"FROM "+mdmsSchemaName+".TBL_HIS_MASTER_METER_CHANGE mc WHERE 1=1";

			return (ArrayList<McMeterReading>) jdbcOperations.query(sql,new McMeterReadingMapper());		
		}
		
		final class McMeterReadingMapper implements RowMapper<McMeterReading>{
			
			public McMeterReadingMapper() {
				super();
			}
			
			@Override
			public McMeterReading mapRow(ResultSet resultSet, int rownum) throws SQLException {
				McMeterReading mcMeterReading=new McMeterReading();
			
				mcMeterReading.setId(resultSet.getString("ID"));
				mcMeterReading.setOldMeterNumber(resultSet.getString("OLDMETERNO"));
				mcMeterReading.setNewMeterNumber(resultSet.getString("NEWMETERNO"));
				mcMeterReading.setStatus(resultSet.getString("STATUS"));
				mcMeterReading.setMeterChangeDate(resultSet.getString("METER_CHANGE_DATE"));
				mcMeterReading.setInitialReadingDate(resultSet.getString("INIT_STMT_DATE"));
				mcMeterReading.setFinalReadingDate(resultSet.getString("FINAL_STMT_DATE"));
				mcMeterReading.setMonth(resultSet.getString("RD_MNTH"));
				mcMeterReading.setYear(resultSet.getString("RD_YR"));
				mcMeterReading.setOldImpKwhToD1(resultSet.getString("OLDIKWHTOD1"));
				mcMeterReading.setOldImpKwhToD2(resultSet.getString("OLDIKWHTOD2"));
				mcMeterReading.setOldImpKwhToD3(resultSet.getString("OLDIKWHTOD3"));
				mcMeterReading.setOldImpKwhToD4(resultSet.getString("OLDIKWHTOD4"));
				mcMeterReading.setOldImpKwhToD5(resultSet.getString("OLDIKWHTOD5"));
				mcMeterReading.setOldExpKwhToD1(resultSet.getString("OLDEKWHTOD1"));
				mcMeterReading.setOldExpKwhToD2(resultSet.getString("OLETKWHTOD2"));
				mcMeterReading.setOldExpKwhToD3(resultSet.getString("OLDEKWHTOD3"));
				mcMeterReading.setOldExpKwhToD4(resultSet.getString("OLDEKWHTOD4"));
				mcMeterReading.setOldExpKwhToD5(resultSet.getString("OLDEKWHTOD5"));
				mcMeterReading.setOldImpKvah(resultSet.getString("OLDIKVAH"));
				mcMeterReading.setOldExpKvah(resultSet.getString("OLDEKVAH"));
				mcMeterReading.setOldQ1Kvarh(resultSet.getString("OLDQ1KVARH"));
				mcMeterReading.setOldQ2Kvarh(resultSet.getString("OLDQ2KVARH"));
				mcMeterReading.setOldQ3Kvarh(resultSet.getString("OLDQ3KVARH"));
				mcMeterReading.setOldQ4Kvarh(resultSet.getString("OLDQ4KVARH"));
				mcMeterReading.setOldMf(resultSet.getString("OLDMF"));
				mcMeterReading.setNewImpKwhToD1(resultSet.getString("NEWIKWHTOD1"));
				mcMeterReading.setNewImpKwhToD2(resultSet.getString("NEWIKWHTOD2"));
				mcMeterReading.setNewImpKwhToD3(resultSet.getString("NEWIKWHTOD3"));
				mcMeterReading.setNewImpKwhToD4(resultSet.getString("NEWIKWHTOD4"));
				mcMeterReading.setNewImpKwhToD5(resultSet.getString("NEWIKWHTOD5"));
				mcMeterReading.setNewExpKwhToD1(resultSet.getString("NEWEKWHTOD1"));
				mcMeterReading.setNewExpKwhToD2(resultSet.getString("NEWEKWHTOD2"));
				mcMeterReading.setNewExpKwhToD3(resultSet.getString("NEWEKWHTOD3"));
				mcMeterReading.setNewExpKwhToD4(resultSet.getString("NEWEKWHTOD4"));
				mcMeterReading.setNewExpKwhToD5(resultSet.getString("NEWEKWHTOD5"));
				mcMeterReading.setNewImpKvah(resultSet.getString("NEWIKVAH"));
				mcMeterReading.setNewExpKvah(resultSet.getString("NEWEKVAH"));
				mcMeterReading.setNewQ1Kvarh(resultSet.getString("NEWQ1KVARH"));
				mcMeterReading.setNewQ2Kvarh(resultSet.getString("NEWQ2KVARH"));
				mcMeterReading.setNewQ3Kvarh(resultSet.getString("NEWQ3KVARH"));
				mcMeterReading.setNewQ4Kvarh(resultSet.getString("NEWQ4KVARH"));
				mcMeterReading.setNewMf(resultSet.getString("NEWMF"));
				mcMeterReading.setModifyDate(resultSet.getString("MODIFIED_DATE"));
				return mcMeterReading ;
			}
			
		  }
}
