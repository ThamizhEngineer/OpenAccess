package com.ss.oa.transaction.TempMeterReadingImport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.common.Response;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.TempMeterReadingImport;
import com.ss.oa.transaction.vo.TempMeterReadingImportLines;
@Component
public class TempMeterReadingImportDaoImpl extends BaseDaoJdbc implements TempMeterReadingImportDao  {

	@Value("${db.batchinsert.size}")
	private int insertBatchSize;
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	Response result= new Response();
	
	@Override
	public List<TempMeterReadingImport> getAllTempMeterReadingImport(Map<String, String> criteria) {
		
			
			String sql="select id,code,import_dt,from_dt,to_dt,status,remarks,error,mr_source_code\n" + 
					   "from imp_mc_mr_header where 1=1 ";
			if(!criteria.isEmpty())
			{
				if(criteria.get("import-date")!=null){
					sql += "and trunc(import_dt) = UPPER (TO_DATE('"+criteria.get("import-date")+"','yyyy-MM-dd'))" ;
					
				}
				if(criteria.get("status")!=null){
					sql += "and upper(status)  = ('"+criteria.get("status")+"') ";
										
				}
				if(criteria.get("source")!=null){
					sql += "and upper(MR_SOURCE_CODE) = ('"+criteria.get("source")+"') ";
										
				}
				
				sql +="ORDER BY created_date  DESC";
				System.out.println(sql);
			}
			return (ArrayList<TempMeterReadingImport>) jdbcOperations.query(sql,new TempMeterReadingImportMapper());
		
	}

	@Override
	public TempMeterReadingImport getTempMeterReadingImportById(String id) {
		
		TempMeterReadingImport tempMeterReadingImport = new TempMeterReadingImport();
		List<TempMeterReadingImportLines> tempMeterReadingImportLines = new ArrayList<TempMeterReadingImportLines>();
		
		String sql="select id,code,import_dt,from_dt,to_dt,status,remarks,error,mr_source_code\n" + 
				   "from imp_mc_mr_header where id = ?";
		
		tempMeterReadingImport = jdbcOperations.queryForObject(sql,new Object[]{id},new TempMeterReadingImportMapper());
		
		String sql1 ="select lines.id as LINES_ID,lines.status_code,lines.remarks as LINES_REMARKS,lines.IMP_MC_MR_HEADER_ID,lines.METER_NO,lines.mf,lines.SERVICE_NO,lines.SYS_DT,lines.INIT_READING_DT,lines.FINAL_READING_DT,\n" + 
				"lines.IMP_INIT_S1,lines.IMP_INIT_S2,lines.IMP_INIT_S3,lines.IMP_INIT_S4,lines.IMP_INIT_S5,lines.IMP_FINAL_S1,lines.IMP_FINAL_S2,lines.IMP_FINAL_S3,lines.IMP_FINAL_S4,lines.IMP_FINAL_S5,\n" + 
				"lines.EXP_INIT_S1,lines.EXP_INIT_S2,lines.EXP_INIT_S3,lines.EXP_INIT_S4,lines.EXP_INIT_S5,lines.EXP_FINAL_S1,lines.EXP_FINAL_S2,lines.EXP_FINAL_S3,lines.EXP_FINAL_S4,lines.EXP_FINAL_S5,\n" + 
				"lines.IMP_RKVAH_INIT,lines.IMP_RKVAH_FINAL,lines.EXP_RKVAH_INIT,lines.EXP_RKVAH_FINAL,lines.IMP_KVAH_INIT,lines.IMP_KVAH_FINAL,lines.EXP_KVAH_INIT,lines.EXP_KVAH_FINAL,lines.READING_MONTH,lines.READING_YEAR,\n" + 
	
				"lines.READING_MONTH,lines.READING_YEAR\n" + 
				"from imp_mc_mr_header header\n" + 
				"left join imp_mc_mr_lines lines on header.id=lines.imp_mc_mr_header_id where lines.imp_mc_mr_header_id=?";
		
		tempMeterReadingImportLines = jdbcOperations.query(sql1,new Object[]{id},new TempMeterReadingImportLinesMapper());
		tempMeterReadingImport.setTempMeterReadingImportLines(tempMeterReadingImportLines);		
		return tempMeterReadingImport;
		
	}

	@Override
	public Response addTempMeterReadingImport(TempMeterReadingImport tempMeterReadingImport) {
		
		try {
			tempMeterReadingImport.setId(generateId());
			tempMeterReadingImport.setStatusCode("CREATED");
			if(tempMeterReadingImport.getCode()== null || tempMeterReadingImport.getCode().isEmpty()){
				tempMeterReadingImport.setCode(generateCode(MeterReadingImport.class.getSimpleName(),""));
			}
			String sql = "insert into imp_mc_mr_header(CODE,IMPORT_DT,STATUS,REMARKS,ERROR,MR_SOURCE_CODE,ID)values(?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setTempMeterReadingImportValues(tempMeterReadingImport))>0) {
				
				
				result.setResult("Success");
				result.setMessage("Energy Sale Intent "+tempMeterReadingImport.getCode()+"Created Successfully");
				result.setId(tempMeterReadingImport.getId());
				result.setCode(tempMeterReadingImport.getCode());
			}else {
				result.setResult("Failure");
			}
		}catch(Exception e) {
			result.setResult("Failure");
			e.printStackTrace();
		}
		return result;
	}
	
	public String addTempMeterReadingImportLines(TempMeterReadingImportLines tempMeterReadingImportLines) {
		String result="";
		
		try {
			tempMeterReadingImportLines.setId(generateId());
			if(tempMeterReadingImportLines.getStatusCode()==null||tempMeterReadingImportLines.getStatusCode().isEmpty()) {
				tempMeterReadingImportLines.setStatusCode("CREATED");
			}
			String sql = "insert into imp_mc_mr_lines (STATUS_CODE,REMARKS,IMP_MC_MR_HEADER_ID,METER_NO,MF,SERVICE_NO,SYS_DT,INIT_READING_DT,FINAL_READING_DT,IMP_INIT_S1,\n" + 
					"					IMP_INIT_S2,IMP_INIT_S3,IMP_INIT_S4,IMP_INIT_S5,IMP_FINAL_S1,IMP_FINAL_S2,IMP_FINAL_S3,IMP_FINAL_S4,IMP_FINAL_S5,EXP_INIT_S1,\n" + 
					"					EXP_INIT_S2,EXP_INIT_S3,EXP_INIT_S4,EXP_INIT_S5,EXP_FINAL_S1,EXP_FINAL_S2,EXP_FINAL_S3,EXP_FINAL_S4,EXP_FINAL_S5,IMP_RKVAH_INIT,\n" + 
					"					IMP_RKVAH_FINAL,EXP_RKVAH_INIT,EXP_RKVAH_FINAL,IMP_KVAH_INIT,IMP_KVAH_FINAL,EXP_KVAH_INIT,EXP_KVAH_FINAL,READING_MONTH,READING_YEAR,ID,DOWNLOADSTATUS)values \n" + 
					"					(?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,? ) ";
			
			if(jdbcOperations.update(sql,setTempMeterReadingImportLinesValues(tempMeterReadingImportLines))>0) {
				result = tempMeterReadingImportLines.getId();
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
	public String saveTempMeterReadingImportLinesByBatch(final List<TempMeterReadingImportLines> tempMeterReadingImportLines,String tempMeterReadingHeadid) {
		String result="";
		try {
			
			String sql = "insert into imp_mc_mr_lines (STATUS_CODE,REMARKS,IMP_MC_MR_HEADER_ID,METER_NO,MF,SERVICE_NO,SYS_DT,INIT_READING_DT,FINAL_READING_DT,IMP_INIT_S1,\n" + 
					"					IMP_INIT_S2,IMP_INIT_S3,IMP_INIT_S4,IMP_INIT_S5,IMP_FINAL_S1,IMP_FINAL_S2,IMP_FINAL_S3,IMP_FINAL_S4,IMP_FINAL_S5,EXP_INIT_S1,\n" + 
					"					EXP_INIT_S2,EXP_INIT_S3,EXP_INIT_S4,EXP_INIT_S5,EXP_FINAL_S1,EXP_FINAL_S2,EXP_FINAL_S3,EXP_FINAL_S4,EXP_FINAL_S5,IMP_RKVAH_INIT,\n" + 
					"					IMP_RKVAH_FINAL,EXP_RKVAH_INIT,EXP_RKVAH_FINAL,IMP_KVAH_INIT,IMP_KVAH_FINAL,EXP_KVAH_INIT,EXP_KVAH_FINAL,READING_MONTH,READING_YEAR,ID,DOWNLOADSTATUS)values \n" + 
					"					(?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,? ) ";
			
	    final int batchSize = insertBatchSize;
	    List<List<TempMeterReadingImportLines>> batchLists = Lists.partition(tempMeterReadingImportLines, batchSize);

	    for(List<TempMeterReadingImportLines> batch : batchLists) {  
	    	jdbcOperations.batchUpdate(sql, new BatchPreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps, int i)
	                    throws SQLException {
	            	String impRkvahInit="0";
	            	String expRkvahInit="0";
	            	TempMeterReadingImportLines TempMeterReadingImportLinesList = batch.get(i);
	            	
	            	if(TempMeterReadingImportLinesList.getImpRkvahInit()==null) {
	            		impRkvahInit="0";
	            		}
	            	else{
	            		impRkvahInit=TempMeterReadingImportLinesList.getImpRkvahInit();
	            	}
	            	
	            	if(TempMeterReadingImportLinesList.getExpRkvahInit()==null) {
	            		expRkvahInit="0";
	            	}else {
	            		expRkvahInit=TempMeterReadingImportLinesList.getExpRkvahInit();
	            	}
	            	
				    ps.setString(1, TempMeterReadingImportLinesList.getStatusCode());
					ps.setString(2, TempMeterReadingImportLinesList.getRemarks());
					ps.setString(3, tempMeterReadingHeadid);
					ps.setString(4, TempMeterReadingImportLinesList.getMeterNumber());
					ps.setString(5, TempMeterReadingImportLinesList.getMf());
					ps.setString(6, TempMeterReadingImportLinesList.getServiceNumber());
					ps.setString(7, TempMeterReadingImportLinesList.getSystemDate());
					ps.setString(8, TempMeterReadingImportLinesList.getInitReadingDate());
					ps.setString(9, TempMeterReadingImportLinesList.getFinalReadingDate());
					ps.setString(10, TempMeterReadingImportLinesList.getImpInitS1());
					ps.setString(11, TempMeterReadingImportLinesList.getImpInitS2());
					ps.setString(12, TempMeterReadingImportLinesList.getImpInitS3());
					ps.setString(13, TempMeterReadingImportLinesList.getImpInitS4());
					ps.setString(14, TempMeterReadingImportLinesList.getImpInitS5());
					ps.setString(15, TempMeterReadingImportLinesList.getImpFinalS1());
					ps.setString(16, TempMeterReadingImportLinesList.getImpFinalS2());
					ps.setString(17, TempMeterReadingImportLinesList.getImpFinalS3());
					ps.setString(18, TempMeterReadingImportLinesList.getImpFinalS4());
					ps.setString(19, TempMeterReadingImportLinesList.getImpFinalS5());
					ps.setString(20, TempMeterReadingImportLinesList.getExpInitS1());
					ps.setString(21, TempMeterReadingImportLinesList.getExpInitS2());
					ps.setString(22, TempMeterReadingImportLinesList.getExpInitS3());
					ps.setString(23, TempMeterReadingImportLinesList.getExpInitS4());
					ps.setString(24, TempMeterReadingImportLinesList.getExpInitS5());
					ps.setString(25, TempMeterReadingImportLinesList.getExpFinalS1());
					ps.setString(26, TempMeterReadingImportLinesList.getExpFinalS2());
					ps.setString(27, TempMeterReadingImportLinesList.getExpFinalS3());
					ps.setString(28, TempMeterReadingImportLinesList.getExpFinalS4());
					ps.setString(29, TempMeterReadingImportLinesList.getExpFinalS5());
//					ps.setString(30, MeterReadingImportLinesList.getImpRkvahInit());
					ps.setString(30, impRkvahInit);
					ps.setString(31, TempMeterReadingImportLinesList.getImpRkvahFinal());
//					ps.setString(32, MeterReadingImportLinesList.getExpRkvahInit());
					ps.setString(32, expRkvahInit);
					ps.setString(33, TempMeterReadingImportLinesList.getExpRkvahFinal());
					ps.setString(34, TempMeterReadingImportLinesList.getImpkVahInit());
					ps.setString(35, TempMeterReadingImportLinesList.getImpkVahFinal());
					ps.setString(36, TempMeterReadingImportLinesList.getExpkVahInit());
					ps.setString(37, TempMeterReadingImportLinesList.getExpkVahFinal());
					ps.setString(38, TempMeterReadingImportLinesList.getReadingMonthCode());
					ps.setString(39, TempMeterReadingImportLinesList.getReadingYear());
					TempMeterReadingImportLinesList.setId(generateId());
					ps.setString(40, TempMeterReadingImportLinesList.getId());
					ps.setString(41, TempMeterReadingImportLinesList.getDownloadStatus());
	            }
	            

	            @Override
	            public int getBatchSize() {
	                return batch.size();
	            }
	        });

	    }
	}catch(Exception e) {
		result = "FAILURE";
		e.printStackTrace();
	}
	    return result;
	}
	
	@Override
	public Response updateTempMeterReadingImport(String id, TempMeterReadingImport tempMeterReadingImport) {
	
		tempMeterReadingImport.setId(id);
		try {
			String sql="update imp_mc_mr_header set CODE=?, IMPORT_DT=TO_DATE(?,'yyyy-mm-dd'),STATUS=?,REMARKS=?,ERROR=?,MR_SOURCE_CODE=? where id=?";
			
			
			if(jdbcOperations.update(sql,setTempMeterReadingImportValues(tempMeterReadingImport)) > 0){
				result.setResult("Success");
				result.setMessage("Energy Sale Intent "+tempMeterReadingImport.getCode()+"Created Successfully");
				Map<String,String> response = new HashMap<String,String>();
				response.put("id", tempMeterReadingImport.getId());
				response.put("code",tempMeterReadingImport.getCode());
			}
			else{
				result.setResult("Failure");
			}
			
		}catch(Exception e) {
			result.setResult("Failure");
		}
		return result;
	}
	
	public String updateTempMeterReadingImportLines(TempMeterReadingImportLines tempMeterReadingImportLines) {
		String result="";
		
		try {
			String sql="update imp_mc_mr_lines set STATUS_CODE=?,REMARKS=?,IMP_MC_MR_HEADER_ID=?,METER_NO=?,MF=?,SERVICE_NO=?,SYS_DT=?,INIT_READING_DT=?,FINAL_READING_DT=?,IMP_INIT_S1=?,\n" + 
					"IMP_INIT_S2=?,IMP_INIT_S3=?,IMP_INIT_S4=?,IMP_INIT_S5=?,IMP_FINAL_S1=?,IMP_FINAL_S2=?,IMP_FINAL_S3=?,IMP_FINAL_S4=?,IMP_FINAL_S5=?,EXP_INIT_S1=?,\n" + 
					"EXP_INIT_S2=?,EXP_INIT_S3=?,EXP_INIT_S4=?,EXP_INIT_S5=?,EXP_FINAL_S1=?,EXP_FINAL_S2=?,EXP_FINAL_S3=?,EXP_FINAL_S4=?,EXP_FINAL_S5=?,IMP_RKVAH_INIT=?,\n" + 
					"IMP_RKVAH_FINAL=?,EXP_RKVAH_INIT=?,EXP_RKVAH_FINAL=?,IMP_KVAH_INIT=?,IMP_KVAH_FINAL=?,EXP_KVAH_INIT=?,EXP_KVAH_FINAL=?,READING_MONTH=?,READING_YEAR=?,DOWNLOADSTATUS=? where id= ?";
			
			if(jdbcOperations.update(sql,setTempMeterReadingImportLinesValues(tempMeterReadingImportLines)) > 0){
				result =tempMeterReadingImportLines.getId();
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
	
	
	private Object[] setTempMeterReadingImportValues(TempMeterReadingImport tempMeterReadingImport){
		return new Object[]{
				tempMeterReadingImport.getCode(),
				tempMeterReadingImport.getImportDate(),
				tempMeterReadingImport.getStatusCode(),
				tempMeterReadingImport.getRemarks(),
				tempMeterReadingImport.getError(),
				tempMeterReadingImport.getMrSourceCode(),
				tempMeterReadingImport.getId()
				
				};
	}
	
	private Object[] setTempMeterReadingImportLinesValues(TempMeterReadingImportLines tempMeterReadingImportLines){
		return new Object[]{
				tempMeterReadingImportLines.getStatusCode(),
				tempMeterReadingImportLines.getRemarks(),
				tempMeterReadingImportLines.getImpMcMrHeaderId(),
				tempMeterReadingImportLines.getMeterNumber(),
				tempMeterReadingImportLines.getMf(),
				tempMeterReadingImportLines.getServiceNumber(),
				tempMeterReadingImportLines.getSystemDate(),
				tempMeterReadingImportLines.getInitReadingDate(),
				tempMeterReadingImportLines.getFinalReadingDate(),
				tempMeterReadingImportLines.getImpInitS1(),
				tempMeterReadingImportLines.getImpInitS2(),
				tempMeterReadingImportLines.getImpInitS3(),
				tempMeterReadingImportLines.getImpInitS4(),
				tempMeterReadingImportLines.getImpInitS5(),
				tempMeterReadingImportLines.getImpFinalS1(),
				tempMeterReadingImportLines.getImpFinalS2(),
				tempMeterReadingImportLines.getImpFinalS3(),
				tempMeterReadingImportLines.getImpFinalS4(),
				tempMeterReadingImportLines.getImpFinalS5(),
				tempMeterReadingImportLines.getExpInitS1(),
				tempMeterReadingImportLines.getExpInitS2(),
				tempMeterReadingImportLines.getExpInitS3(),
				tempMeterReadingImportLines.getExpInitS4(),
				tempMeterReadingImportLines.getExpInitS5(),
				tempMeterReadingImportLines.getExpFinalS1(),
				tempMeterReadingImportLines.getExpFinalS2(),
				tempMeterReadingImportLines.getExpFinalS3(),
				tempMeterReadingImportLines.getExpFinalS4(),
				tempMeterReadingImportLines.getExpFinalS5(),
				tempMeterReadingImportLines.getImpRkvahInit(),
				tempMeterReadingImportLines.getImpRkvahFinal(),
				tempMeterReadingImportLines.getExpRkvahInit(),
				tempMeterReadingImportLines.getExpRkvahFinal(),
				tempMeterReadingImportLines.getImpkVahInit(),
				tempMeterReadingImportLines.getImpkVahFinal(),
				tempMeterReadingImportLines.getExpkVahInit(),
				tempMeterReadingImportLines.getExpkVahFinal(),
				tempMeterReadingImportLines.getReadingMonthCode(),
				tempMeterReadingImportLines.getReadingYear(),			
				tempMeterReadingImportLines.getId(),
				tempMeterReadingImportLines.getDownloadStatus(),
				};
	}
	
	
	public class  TempMeterReadingImportMapper implements RowMapper<TempMeterReadingImport>{
		
		public TempMeterReadingImportMapper() {
			super();
		}
		
		@Override
		public TempMeterReadingImport mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			TempMeterReadingImport tempMeterReadingImport = new TempMeterReadingImport();
			
			tempMeterReadingImport.setId(resultSet.getString("id"));
			tempMeterReadingImport.setCode(resultSet.getString("CODE"));
			tempMeterReadingImport.setImportDate(resultSet.getString("import_dt"));
			tempMeterReadingImport.setStatusCode(resultSet.getString("status"));
			tempMeterReadingImport.setRemarks(resultSet.getString("remarks"));
			tempMeterReadingImport.setError(resultSet.getString("error"));
			tempMeterReadingImport.setMrSourceCode(resultSet.getString("mr_source_code"));
			
			return tempMeterReadingImport;
		}
		
	}
	

	
	public class TempMeterReadingImportLinesMapper implements RowMapper<TempMeterReadingImportLines>{
		
		public TempMeterReadingImportLinesMapper() {
			super();
		}
		
		@Override
		public TempMeterReadingImportLines mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			TempMeterReadingImportLines tempMeterReadingImportLines = new TempMeterReadingImportLines();
			
			tempMeterReadingImportLines.setId(resultSet.getString("LINES_ID"));
			tempMeterReadingImportLines.setStatusCode(resultSet.getString("STATUS_CODE"));
			tempMeterReadingImportLines.setRemarks(resultSet.getString("LINES_REMARKS"));
			tempMeterReadingImportLines.setImpMcMrHeaderId(resultSet.getString("IMP_MC_MR_HEADER_ID"));
			tempMeterReadingImportLines.setMeterNumber(resultSet.getString("METER_NO"));
			tempMeterReadingImportLines.setMf(resultSet.getString("mf"));
			tempMeterReadingImportLines.setServiceNumber(resultSet.getString("SERVICE_NO"));
			tempMeterReadingImportLines.setSystemDate(resultSet.getString("SYS_DT"));
			tempMeterReadingImportLines.setInitReadingDate(resultSet.getString("INIT_READING_DT"));
			tempMeterReadingImportLines.setFinalReadingDate(resultSet.getString("FINAL_READING_DT"));
			tempMeterReadingImportLines.setImpInitS1(resultSet.getString("IMP_INIT_S1"));
			tempMeterReadingImportLines.setImpInitS2(resultSet.getString("IMP_INIT_S2"));
			tempMeterReadingImportLines.setImpInitS3(resultSet.getString("IMP_INIT_S3"));
			tempMeterReadingImportLines.setImpInitS4(resultSet.getString("IMP_INIT_S4"));
			tempMeterReadingImportLines.setImpInitS5(resultSet.getString("IMP_INIT_S5"));
			tempMeterReadingImportLines.setImpFinalS1(resultSet.getString("IMP_FINAL_S1"));
			tempMeterReadingImportLines.setImpFinalS2(resultSet.getString("IMP_FINAL_S2"));
			tempMeterReadingImportLines.setImpFinalS3(resultSet.getString("IMP_FINAL_S3"));
			tempMeterReadingImportLines.setImpFinalS4(resultSet.getString("IMP_FINAL_S4"));
			tempMeterReadingImportLines.setImpFinalS5(resultSet.getString("IMP_FINAL_S5"));
			tempMeterReadingImportLines.setExpInitS1(resultSet.getString("EXP_INIT_S1"));
			tempMeterReadingImportLines.setExpInitS2(resultSet.getString("EXP_INIT_S2"));
			tempMeterReadingImportLines.setExpInitS3(resultSet.getString("EXP_INIT_S3"));
			tempMeterReadingImportLines.setExpInitS4(resultSet.getString("EXP_INIT_S4"));
			tempMeterReadingImportLines.setExpInitS5(resultSet.getString("EXP_INIT_S5"));
			tempMeterReadingImportLines.setExpFinalS1(resultSet.getString("EXP_FINAL_S1"));
			tempMeterReadingImportLines.setExpFinalS2(resultSet.getString("EXP_FINAL_S2"));
			tempMeterReadingImportLines.setExpFinalS3(resultSet.getString("EXP_FINAL_S3"));
			tempMeterReadingImportLines.setExpFinalS4(resultSet.getString("EXP_FINAL_S4"));
			tempMeterReadingImportLines.setExpFinalS5(resultSet.getString("EXP_FINAL_S5"));
			tempMeterReadingImportLines.setImpRkvahInit(resultSet.getString("IMP_RKVAH_INIT"));
			tempMeterReadingImportLines.setImpRkvahFinal(resultSet.getString("IMP_RKVAH_FINAL"));
			tempMeterReadingImportLines.setExpRkvahInit(resultSet.getString("EXP_RKVAH_INIT"));
			tempMeterReadingImportLines.setExpRkvahFinal(resultSet.getString("EXP_RKVAH_FINAL"));
			tempMeterReadingImportLines.setImpkVahInit(resultSet.getString("IMP_KVAH_INIT"));
			tempMeterReadingImportLines.setImpkVahFinal(resultSet.getString("IMP_KVAH_FINAL"));
			tempMeterReadingImportLines.setExpkVahInit(resultSet.getString("EXP_KVAH_INIT"));
			tempMeterReadingImportLines.setExpkVahFinal(resultSet.getString("EXP_KVAH_FINAL"));
			tempMeterReadingImportLines.setReadingMonthCode(resultSet.getString("READING_MONTH"));
			tempMeterReadingImportLines.setReadingYear(resultSet.getString("READING_YEAR"));		
			
			return tempMeterReadingImportLines;
		}
		
	}


}
