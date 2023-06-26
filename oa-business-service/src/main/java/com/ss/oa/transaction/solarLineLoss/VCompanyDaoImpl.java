package com.ss.oa.transaction.solarLineLoss;

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
import com.ss.oa.transaction.vo.Vcompany;
@Scope("prototype")
@Component
public class VCompanyDaoImpl extends BaseDaoJdbc implements VCompanyDao {
	@Resource
	private JdbcOperations jdbcOperations;

	@Override
	public List<Vcompany> getAllFeeder(Map<String, String> criteria){
		String id="";
		String month="";
		String year="";
		String finalMonth="";
		String finalYear="";
		Integer nMonth=0;
		Integer nYear=0;
		
		  if(!criteria.isEmpty())
			{
				if(criteria.get("orgId")!=null){
					id = criteria.get("orgId");
				}
				if(criteria.get("month")!=null){
					month = criteria.get("month");
				}
				if(criteria.get("year")!=null){
					year = criteria.get("year");
				}
				if(criteria.get("month")!=null &&criteria.get("year")!=null ){
					month = criteria.get("month");
					year = criteria.get("year");
					
					nMonth=Integer.parseInt(month)+1;
					nYear= Integer.parseInt(year)+1;
					
					if(nMonth<10) {
						finalMonth='0'+nMonth.toString();
						finalYear=year;
					}
					else if(nMonth>12) {
						finalMonth="01";
						finalYear=nYear.toString();
					}
					else {							
						finalMonth=nMonth.toString();
						finalYear=year;		
				}
					
				}
				
			}
		  
		  
		  String sql="SELECT mcs.M_ORG_ID,mo.NAME AS M_ORG_NAME , mcs.M_SUBSTATION_ID ,ms.NAME AS M_SUBSTATION_NAME , mcs.M_FEEDER_ID,mf.NAME AS M_FEEDER_NAME,sum(DISTINCT (A.TOTAL_EXPORT_GEN)) AS TOTALEXPORTUNIT, count(DISTINCT (mcs.\"number\")) AS TOTALSERVICES, count(DISTINCT (A.M_COMPANY_METER_ID)) AS BILLEDSERVICES,B.bulkMeterReading,B.METERNO,C.BULK_METER_READING AS subbulkreading FROM M_COMPANY_SERVICE mcs\n"
		  		+ "LEFT JOIN M_SUBSTATION ms ON mcs.M_SUBSTATION_ID= ms.ID \n"
		  		+ "LEFT JOIN M_ORG mo ON mcs.M_ORG_ID =mo.ID \n"
		  		+ "LEFT JOIN M_FEEDER mf ON mcs.M_FEEDER_ID =mf.ID \n"
		  		+ "LEFT JOIN V_COMPANY_SERVICE vcs ON mcs.\"number\" =vcs.\"number\" \n"
		  		+ "LEFT JOIN M_COMPANY_METER mcm ON mcs.ID =mcm.M_COMPANY_SERVICE_ID \n"
		  		+ "LEFT JOIN (\n"
		  		+ "SELECT tmrh1.TOTAL_EXPORT_GEN,TMRH1.M_COMPANY_METER_ID FROM T_METER_READING_HDR tmrh1 \n"
		  		+ "LEFT JOIN M_COMPANY_METER mcm2 ON tmrh1.M_COMPANY_METER_ID=mcm2.ID  \n"
		  		+ "WHERE tmrh1.READING_MONTH ='"+month+"' AND tmrh1.READING_YEAR= '"+year+"'\n"
		  		+ ")A ON mcm.ID =A.M_COMPANY_METER_ID  \n"
		  		+ "LEFT join(\n"
		  		+ "SELECT  METERNO,SSFEEDERID,(((EXP_FINAL_S1-EXP_INIT_S1)+(EXP_FINAL_S2-EXP_INIT_S2)+(EXP_FINAL_S3-EXP_INIT_S3)+(EXP_FINAL_S4-EXP_INIT_S4)+(EXP_FINAL_S5-EXP_INIT_S5))*regexp_replace(MF, ',', '')/1000) AS bulkMeterReading FROM AMR_TBL_SOLAR_FEEDER_END asf\n"
		  		+ "LEFT JOIN M_COMPANY_SERVICE mcs2 ON asf.SSFEEDERID = mcs2.M_FEEDER_ID \n"
		  		+ "WHERE to_char(INITIAL_READING_DATE,'mmyyyy')='"+month+year+"' AND to_char(FINAL_READING_DATE,'mmyyyy')='"+finalMonth+finalYear+"'\n"
		  		+ ")B ON MCS.M_FEEDER_ID=B.SSFEEDERID\n"
		  		+ "LEFT JOIN ( \n"
		  		+" SELECT tsl.BULK_METER_READING,tsl.M_FEEDER_ID FROM T_SUBSTATION_LOSS tsl \n"
		  		+"  LEFT JOIN M_COMPANY_SERVICE mcs3 ON tsl.M_FEEDER_ID=mcs3.M_FEEDER_ID \n"
		  		+" WHERE tsl.\"MONTH\"='"+month+"' AND tsl.\"YEAR\"='"+year+"' \n"
		  		+"	)C ON MCS.M_FEEDER_ID=C.M_FEEDER_ID \n"
		  		+ "WHERE mcs.M_ORG_ID ='"+id+"' AND LENGTH(mcs.\"number\")=12 AND mcs.M_FEEDER_ID IS NOT NULL  AND vcs.TYPE_OF_SS ='SECTION 10(1)SS'\n"
		  		+ "GROUP BY mcs.M_ORG_ID,mo.NAME  ,ms.NAME ,mcs.M_SUBSTATION_ID ,MCS .M_FEEDER_ID ,mf.NAME ,B.bulkMeterReading,B.METERNO,C.BULK_METER_READING \n"
		  		+"ORDER BY ms.NAME";
		  
		  
		System.out.println(sql);
		
	  
	    return jdbcOperations.query(sql, new GcMapper());
	}
	
	final class GcMapper implements RowMapper<Vcompany>{
		public  GcMapper() {
			super();
		}
		
		@Override
		public Vcompany mapRow(ResultSet resultSet, int rownum) throws SQLException {
			Vcompany gc= new Vcompany();
			
//			gc.setServiceNumber(resultSet.getString("\"number\""));
			gc.setOrgId(resultSet.getString("M_ORG_ID"));
			gc.setOrgName(resultSet.getString("M_ORG_NAME"));
			gc.setSubstationId(resultSet.getString("M_SUBSTATION_ID"));
			gc.setSubstationName(resultSet.getString("M_SUBSTATION_NAME"));
			gc.setFeederId(resultSet.getString("M_FEEDER_ID"));
			gc.setFeederName(resultSet.getString("M_FEEDER_NAME"));
			gc.setTotalExportUnits(resultSet.getString("TOTALEXPORTUNIT"));
			gc.setTotalServices(resultSet.getString("TOTALSERVICES"));
			gc.setBilledService(resultSet.getString("BILLEDSERVICES"));
			gc.setBulkMeterReading(resultSet.getString("bulkMeterReading"));
			gc.setMeterNo(resultSet.getString("METERNO"));
			gc.setSubbulkreading(resultSet.getString("subbulkreading"));
			
			  return 	gc ;
		}
	}
}
