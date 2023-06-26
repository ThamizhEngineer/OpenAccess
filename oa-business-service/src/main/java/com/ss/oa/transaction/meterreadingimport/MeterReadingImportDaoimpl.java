package com.ss.oa.transaction.meterreadingimport;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.common.Response;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;

@Scope("prototype")
@Component
public class MeterReadingImportDaoimpl  extends BaseDaoJdbc implements MeterReadingImportDao {
	@Value("${db.batchinsert.size}")
	private int insertBatchSize;
	
	@Resource
	private JdbcOperations jdbcOperations;
	
	Response result= new Response();
	
	@Override
	public List<MeterReadingImport> getAllMeterReadingImports(Map<String, String> criteria) {
		
			
			String sql="select id,code,import_dt,from_dt,to_dt,status,remarks,error,mr_source_code\n" + 
					   "from imp_mr_header where 1=1 ";
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
			return (ArrayList<MeterReadingImport>) jdbcOperations.query(sql,new MeterReadingImportMapper());
		
	}

	@Override
	public MeterReadingImport getMeterReadingImportById(String id) {
		
		MeterReadingImport meterReadingImport = new MeterReadingImport();
		List<MeterReadingImportLines> meterReadingImportLines = new ArrayList<MeterReadingImportLines>();
		
		String sql="select id,code,import_dt,from_dt,to_dt,status,remarks,error,mr_source_code\n" + 
				   "from imp_mr_header where id = ?";
		
		meterReadingImport = jdbcOperations.queryForObject(sql,new Object[]{id},new MeterReadingImportMapper());
		
		String sql1 ="select lines.id as LINES_ID,lines.status_code,lines.remarks as LINES_REMARKS,lines.IMP_MR_HEADER_ID,lines.METER_NO,lines.mf,lines.SERVICE_NO,lines.SYS_DT,lines.INIT_READING_DT,lines.FINAL_READING_DT,\n" + 
				"lines.IMP_INIT_S1,lines.IMP_INIT_S2,lines.IMP_INIT_S3,lines.IMP_INIT_S4,lines.IMP_INIT_S5,lines.IMP_FINAL_S1,lines.IMP_FINAL_S2,lines.IMP_FINAL_S3,lines.IMP_FINAL_S4,lines.IMP_FINAL_S5,\n" + 
				"lines.EXP_INIT_S1,lines.EXP_INIT_S2,lines.EXP_INIT_S3,lines.EXP_INIT_S4,lines.EXP_INIT_S5,lines.EXP_FINAL_S1,lines.EXP_FINAL_S2,lines.EXP_FINAL_S3,lines.EXP_FINAL_S4,lines.EXP_FINAL_S5,\n" + 
				"lines.IMP_RKVAH_INIT,lines.IMP_RKVAH_FINAL,lines.EXP_RKVAH_INIT,lines.EXP_RKVAH_FINAL,lines.IMP_KVAH_INIT,lines.IMP_KVAH_FINAL,lines.EXP_KVAH_INIT,lines.EXP_KVAH_FINAL,lines.READING_MONTH,lines.READING_YEAR,\n" + 
	
				"lines.READING_MONTH,lines.READING_YEAR\n" + 
				"from imp_mr_header header\n" + 
				"left join imp_mr_lines lines on header.id=lines.imp_mr_header_id where lines.IMP_MR_HEADER_ID=?";
		
		meterReadingImportLines = jdbcOperations.query(sql1,new Object[]{id},new MeterReadingImportLinesMapper());
		meterReadingImport.setMeterReadingImportLines(meterReadingImportLines);		
		return meterReadingImport;
		
	}

	@Override
	public Response addMeterReadingImport(MeterReadingImport meterReadingImport) {
		
		try {
			meterReadingImport.setId(generateId());
			meterReadingImport.setStatusCode("CREATED");
			if(meterReadingImport.getCode()== null || meterReadingImport.getCode().isEmpty()){
				meterReadingImport.setCode(generateCode(MeterReadingImport.class.getSimpleName(),""));
			}
			String sql = "insert into IMP_MR_HEADER(CODE,IMPORT_DT,STATUS,REMARKS,ERROR,MR_SOURCE_CODE,ID)values(?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,?,?)";
			
			if(jdbcOperations.update(sql,setMeterReadingImportValues(meterReadingImport))>0) {
				
				
				result.setResult("Success");
				result.setMessage("Energy Sale Intent "+meterReadingImport.getCode()+"Created Successfully");
				result.setId(meterReadingImport.getId());
				result.setCode(meterReadingImport.getCode());
			}else {
				result.setResult("Failure");
			}
		}catch(Exception e) {
			result.setResult("Failure");
			e.printStackTrace();
		}
		return result;
	}
	
	public String addMeterReadingImportLines(MeterReadingImportLines meterReadingImportLines) {
		String result="";
		
		try {
			meterReadingImportLines.setId(generateId());
			if(meterReadingImportLines.getStatusCode()==null||meterReadingImportLines.getStatusCode().isEmpty()) {
				meterReadingImportLines.setStatusCode("CREATED");
			}
			String sql = "insert into IMP_MR_LINES (STATUS_CODE,REMARKS,IMP_MR_HEADER_ID,METER_NO,MF,SERVICE_NO,SYS_DT,INIT_READING_DT,FINAL_READING_DT,IMP_INIT_S1,\n" + 
					"					IMP_INIT_S2,IMP_INIT_S3,IMP_INIT_S4,IMP_INIT_S5,IMP_FINAL_S1,IMP_FINAL_S2,IMP_FINAL_S3,IMP_FINAL_S4,IMP_FINAL_S5,EXP_INIT_S1,\n" + 
					"					EXP_INIT_S2,EXP_INIT_S3,EXP_INIT_S4,EXP_INIT_S5,EXP_FINAL_S1,EXP_FINAL_S2,EXP_FINAL_S3,EXP_FINAL_S4,EXP_FINAL_S5,IMP_RKVAH_INIT,\n" + 
					"					IMP_RKVAH_FINAL,EXP_RKVAH_INIT,EXP_RKVAH_FINAL,IMP_KVAH_INIT,IMP_KVAH_FINAL,EXP_KVAH_INIT,EXP_KVAH_FINAL,READING_MONTH,READING_YEAR,ID)values \n" + 
					"					(?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,? ) ";
			
			if(jdbcOperations.update(sql,setMeterReadingImportLinesValues(meterReadingImportLines))>0) {
				result = meterReadingImportLines.getId();
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
	public String saveMrImportLinesByBatch(final List<MeterReadingImportLines> meterReadingImportLines,String impHeadid) {
		String result="";
		try {
			
			String sql = "insert into IMP_MR_LINES (STATUS_CODE,REMARKS,IMP_MR_HEADER_ID,METER_NO,MF,SERVICE_NO,SYS_DT,INIT_READING_DT,FINAL_READING_DT,IMP_INIT_S1,\n" + 
					"					IMP_INIT_S2,IMP_INIT_S3,IMP_INIT_S4,IMP_INIT_S5,IMP_FINAL_S1,IMP_FINAL_S2,IMP_FINAL_S3,IMP_FINAL_S4,IMP_FINAL_S5,EXP_INIT_S1,\n" + 
					"					EXP_INIT_S2,EXP_INIT_S3,EXP_INIT_S4,EXP_INIT_S5,EXP_FINAL_S1,EXP_FINAL_S2,EXP_FINAL_S3,EXP_FINAL_S4,EXP_FINAL_S5,IMP_RKVAH_INIT,\n" + 
					"					IMP_RKVAH_FINAL,EXP_RKVAH_INIT,EXP_RKVAH_FINAL,IMP_KVAH_INIT,IMP_KVAH_FINAL,EXP_KVAH_INIT,EXP_KVAH_FINAL,READING_MONTH,READING_YEAR,ID)values \n" + 
					"					(?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,?,  \n" + 
					"					 ?,?,?,?,?,?,?,?,?,? ) ";
			
	    final int batchSize = insertBatchSize;
	    List<List<MeterReadingImportLines>> batchLists = Lists.partition(meterReadingImportLines, batchSize);

	    for(List<MeterReadingImportLines> batch : batchLists) {  
	    	jdbcOperations.batchUpdate(sql, new BatchPreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps, int i)
	                    throws SQLException {
	            	String impRkvahInit="0";
	            	String expRkvahInit="0";
	            	MeterReadingImportLines MeterReadingImportLinesList = batch.get(i);
	            	
	            	if(MeterReadingImportLinesList.getImpRkvahInit()==null) {
	            		impRkvahInit="0";
	            		}
	            	else{
	            		impRkvahInit=MeterReadingImportLinesList.getImpRkvahInit();
	            	}
	            	
	            	if(MeterReadingImportLinesList.getExpRkvahInit()==null) {
	            		expRkvahInit="0";
	            	}else {
	            		expRkvahInit=MeterReadingImportLinesList.getExpRkvahInit();
	            	}
	            	
				    ps.setString(1, MeterReadingImportLinesList.getStatusCode());
					ps.setString(2, MeterReadingImportLinesList.getRemarks());
					ps.setString(3, impHeadid);
					ps.setString(4, MeterReadingImportLinesList.getMeterNumber());
					ps.setString(5, MeterReadingImportLinesList.getMf());
					ps.setString(6, MeterReadingImportLinesList.getServiceNumber());
					ps.setString(7, MeterReadingImportLinesList.getSystemDate());
					ps.setString(8, MeterReadingImportLinesList.getInitReadingDate());
					ps.setString(9, MeterReadingImportLinesList.getFinalReadingDate());
					ps.setString(10, MeterReadingImportLinesList.getImpInitS1());
					ps.setString(11, MeterReadingImportLinesList.getImpInitS2());
					ps.setString(12, MeterReadingImportLinesList.getImpInitS3());
					ps.setString(13, MeterReadingImportLinesList.getImpInitS4());
					ps.setString(14, MeterReadingImportLinesList.getImpInitS5());
					ps.setString(15, MeterReadingImportLinesList.getImpFinalS1());
					ps.setString(16, MeterReadingImportLinesList.getImpFinalS2());
					ps.setString(17, MeterReadingImportLinesList.getImpFinalS3());
					ps.setString(18, MeterReadingImportLinesList.getImpFinalS4());
					ps.setString(19, MeterReadingImportLinesList.getImpFinalS5());
					ps.setString(20, MeterReadingImportLinesList.getExpInitS1());
					ps.setString(21, MeterReadingImportLinesList.getExpInitS2());
					ps.setString(22, MeterReadingImportLinesList.getExpInitS3());
					ps.setString(23, MeterReadingImportLinesList.getExpInitS4());
					ps.setString(24, MeterReadingImportLinesList.getExpInitS5());
					ps.setString(25, MeterReadingImportLinesList.getExpFinalS1());
					ps.setString(26, MeterReadingImportLinesList.getExpFinalS2());
					ps.setString(27, MeterReadingImportLinesList.getExpFinalS3());
					ps.setString(28, MeterReadingImportLinesList.getExpFinalS4());
					ps.setString(29, MeterReadingImportLinesList.getExpFinalS5());
//					ps.setString(30, MeterReadingImportLinesList.getImpRkvahInit());
					ps.setString(30, impRkvahInit);
					ps.setString(31, MeterReadingImportLinesList.getImpRkvahFinal());
//					ps.setString(32, MeterReadingImportLinesList.getExpRkvahInit());
					ps.setString(32, expRkvahInit);
					ps.setString(33, MeterReadingImportLinesList.getExpRkvahFinal());
					ps.setString(34, MeterReadingImportLinesList.getImpkVahInit());
					ps.setString(35, MeterReadingImportLinesList.getImpkVahFinal());
					ps.setString(36, MeterReadingImportLinesList.getExpkVahInit());
					ps.setString(37, MeterReadingImportLinesList.getExpkVahFinal());
					ps.setString(38, MeterReadingImportLinesList.getReadingMonthCode());
					ps.setString(39, MeterReadingImportLinesList.getReadingYear());
	            	MeterReadingImportLinesList.setId(generateId());
					ps.setString(40, MeterReadingImportLinesList.getId());
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
	public Response updateMeterReadingImport(String id, MeterReadingImport meterReadingImport) {
	
		meterReadingImport.setId(id);
		try {
			String sql="update IMP_MR_HEADER set CODE=?, IMPORT_DT=TO_DATE(?,'yyyy-mm-dd'),STATUS=?,REMARKS=?,ERROR=?,MR_SOURCE_CODE=? where id=?";
			
			
			if(jdbcOperations.update(sql,setMeterReadingImportValues(meterReadingImport)) > 0){
				result.setResult("Success");
				result.setMessage("Energy Sale Intent "+meterReadingImport.getCode()+"Created Successfully");
				Map<String,String> response = new HashMap<String,String>();
				response.put("id", meterReadingImport.getId());
				response.put("code",meterReadingImport.getCode());
			}
			else{
				result.setResult("Failure");
			}
			
		}catch(Exception e) {
			result.setResult("Failure");
		}
		return result;
	}
	
	public String updateMeterReadingImportLines(MeterReadingImportLines meterReadingImportLines) {
		String result="";
		
		try {
			String sql="update IMP_MR_LINES set STATUS_CODE=?,REMARKS=?,IMP_MR_HEADER_ID=?,METER_NO=?,MF=?,SERVICE_NO=?,SYS_DT=?,INIT_READING_DT=?,FINAL_READING_DT=?,IMP_INIT_S1=?,\n" + 
					"IMP_INIT_S2=?,IMP_INIT_S3=?,IMP_INIT_S4=?,IMP_INIT_S5=?,IMP_FINAL_S1=?,IMP_FINAL_S2=?,IMP_FINAL_S3=?,IMP_FINAL_S4=?,IMP_FINAL_S5=?,EXP_INIT_S1=?,\n" + 
					"EXP_INIT_S2=?,EXP_INIT_S3=?,EXP_INIT_S4=?,EXP_INIT_S5=?,EXP_FINAL_S1=?,EXP_FINAL_S2=?,EXP_FINAL_S3=?,EXP_FINAL_S4=?,EXP_FINAL_S5=?,IMP_RKVAH_INIT=?,\n" + 
					"IMP_RKVAH_FINAL=?,EXP_RKVAH_INIT=?,EXP_RKVAH_FINAL=?,IMP_KVAH_INIT=?,IMP_KVAH_FINAL=?,EXP_KVAH_INIT=?,EXP_KVAH_FINAL=?,READING_MONTH=?,READING_YEAR=? where id= ?";
			
			if(jdbcOperations.update(sql,setMeterReadingImportLinesValues(meterReadingImportLines)) > 0){
				result =meterReadingImportLines.getId();
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
	
	
	private Object[] setMeterReadingImportValues(MeterReadingImport meterReadingImport){
		return new Object[]{
				meterReadingImport.getCode(),
				meterReadingImport.getImportDate(),
				meterReadingImport.getStatusCode(),
				meterReadingImport.getRemarks(),
				meterReadingImport.getError(),
				meterReadingImport.getMrSourceCode(),
				meterReadingImport.getId()
				
				};
	}
	
	private Object[] setMeterReadingImportLinesValues(MeterReadingImportLines meterReadingImportLines){
		return new Object[]{
				meterReadingImportLines.getStatusCode(),
				meterReadingImportLines.getRemarks(),
				meterReadingImportLines.getImpMrHeaderId(),
				meterReadingImportLines.getMeterNumber(),
				meterReadingImportLines.getMf(),
				meterReadingImportLines.getServiceNumber(),
				meterReadingImportLines.getSystemDate(),
				meterReadingImportLines.getInitReadingDate(),
				meterReadingImportLines.getFinalReadingDate(),
				meterReadingImportLines.getImpInitS1(),
				meterReadingImportLines.getImpInitS2(),
				meterReadingImportLines.getImpInitS3(),
				meterReadingImportLines.getImpInitS4(),
				meterReadingImportLines.getImpInitS5(),
				meterReadingImportLines.getImpFinalS1(),
				meterReadingImportLines.getImpFinalS2(),
				meterReadingImportLines.getImpFinalS3(),
				meterReadingImportLines.getImpFinalS4(),
				meterReadingImportLines.getImpFinalS5(),
				meterReadingImportLines.getExpInitS1(),
				meterReadingImportLines.getExpInitS2(),
				meterReadingImportLines.getExpInitS3(),
				meterReadingImportLines.getExpInitS4(),
				meterReadingImportLines.getExpInitS5(),
				meterReadingImportLines.getExpFinalS1(),
				meterReadingImportLines.getExpFinalS2(),
				meterReadingImportLines.getExpFinalS3(),
				meterReadingImportLines.getExpFinalS4(),
				meterReadingImportLines.getExpFinalS5(),
				meterReadingImportLines.getImpRkvahInit(),
				meterReadingImportLines.getImpRkvahFinal(),
				meterReadingImportLines.getExpRkvahInit(),
				meterReadingImportLines.getExpRkvahFinal(),
				meterReadingImportLines.getImpkVahInit(),
				meterReadingImportLines.getImpkVahFinal(),
				meterReadingImportLines.getExpkVahInit(),
				meterReadingImportLines.getExpkVahFinal(),
				meterReadingImportLines.getReadingMonthCode(),
				meterReadingImportLines.getReadingYear(),			
				meterReadingImportLines.getId(),
				
				};
	}
	
	
	public class MeterReadingImportMapper implements RowMapper<MeterReadingImport>{
		
		public MeterReadingImportMapper() {
			super();
		}
		
		@Override
		public MeterReadingImport mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			MeterReadingImport meterReadingImport = new MeterReadingImport();
			
			meterReadingImport.setId(resultSet.getString("id"));
			meterReadingImport.setCode(resultSet.getString("CODE"));
			meterReadingImport.setImportDate(resultSet.getString("import_dt"));
			meterReadingImport.setStatusCode(resultSet.getString("status"));
			meterReadingImport.setRemarks(resultSet.getString("remarks"));
			meterReadingImport.setError(resultSet.getString("error"));
			meterReadingImport.setMrSourceCode(resultSet.getString("mr_source_code"));
			
			return meterReadingImport;
		}
		
	}
	

	
	public class MeterReadingImportLinesMapper implements RowMapper<MeterReadingImportLines>{
		
		public MeterReadingImportLinesMapper() {
			super();
		}
		
		@Override
		public MeterReadingImportLines mapRow(ResultSet resultSet, int rownum) throws SQLException {
			
			MeterReadingImportLines meterReadingImportLines = new MeterReadingImportLines();
			
			meterReadingImportLines.setId(resultSet.getString("LINES_ID"));
			meterReadingImportLines.setStatusCode(resultSet.getString("STATUS_CODE"));
			meterReadingImportLines.setRemarks(resultSet.getString("LINES_REMARKS"));
			meterReadingImportLines.setImpMrHeaderId(resultSet.getString("IMP_MR_HEADER_ID"));
			meterReadingImportLines.setMeterNumber(resultSet.getString("METER_NO"));
			meterReadingImportLines.setMf(resultSet.getString("mf"));
			meterReadingImportLines.setServiceNumber(resultSet.getString("SERVICE_NO"));
			meterReadingImportLines.setSystemDate(resultSet.getString("SYS_DT"));
			meterReadingImportLines.setInitReadingDate(resultSet.getString("INIT_READING_DT"));
			meterReadingImportLines.setFinalReadingDate(resultSet.getString("FINAL_READING_DT"));
			meterReadingImportLines.setImpInitS1(resultSet.getString("IMP_INIT_S1"));
			meterReadingImportLines.setImpInitS2(resultSet.getString("IMP_INIT_S2"));
			meterReadingImportLines.setImpInitS3(resultSet.getString("IMP_INIT_S3"));
			meterReadingImportLines.setImpInitS4(resultSet.getString("IMP_INIT_S4"));
			meterReadingImportLines.setImpInitS5(resultSet.getString("IMP_INIT_S5"));
			meterReadingImportLines.setImpFinalS1(resultSet.getString("IMP_FINAL_S1"));
			meterReadingImportLines.setImpFinalS2(resultSet.getString("IMP_FINAL_S2"));
			meterReadingImportLines.setImpFinalS3(resultSet.getString("IMP_FINAL_S3"));
			meterReadingImportLines.setImpFinalS4(resultSet.getString("IMP_FINAL_S4"));
			meterReadingImportLines.setImpFinalS5(resultSet.getString("IMP_FINAL_S5"));
			meterReadingImportLines.setExpInitS1(resultSet.getString("EXP_INIT_S1"));
			meterReadingImportLines.setExpInitS2(resultSet.getString("EXP_INIT_S2"));
			meterReadingImportLines.setExpInitS3(resultSet.getString("EXP_INIT_S3"));
			meterReadingImportLines.setExpInitS4(resultSet.getString("EXP_INIT_S4"));
			meterReadingImportLines.setExpInitS5(resultSet.getString("EXP_INIT_S5"));
			meterReadingImportLines.setExpFinalS1(resultSet.getString("EXP_FINAL_S1"));
			meterReadingImportLines.setExpFinalS2(resultSet.getString("EXP_FINAL_S2"));
			meterReadingImportLines.setExpFinalS3(resultSet.getString("EXP_FINAL_S3"));
			meterReadingImportLines.setExpFinalS4(resultSet.getString("EXP_FINAL_S4"));
			meterReadingImportLines.setExpFinalS5(resultSet.getString("EXP_FINAL_S5"));
			meterReadingImportLines.setImpRkvahInit(resultSet.getString("IMP_RKVAH_INIT"));
	 		meterReadingImportLines.setImpRkvahFinal(resultSet.getString("IMP_RKVAH_FINAL"));
	 	    meterReadingImportLines.setExpRkvahInit(resultSet.getString("EXP_RKVAH_INIT"));
	 	    meterReadingImportLines.setExpRkvahFinal(resultSet.getString("EXP_RKVAH_FINAL"));
		  	meterReadingImportLines.setImpkVahInit(resultSet.getString("IMP_KVAH_INIT"));
			meterReadingImportLines.setImpkVahFinal(resultSet.getString("IMP_KVAH_FINAL"));
			meterReadingImportLines.setExpkVahInit(resultSet.getString("EXP_KVAH_INIT"));
			meterReadingImportLines.setExpkVahFinal(resultSet.getString("EXP_KVAH_FINAL"));
			meterReadingImportLines.setReadingMonthCode(resultSet.getString("READING_MONTH"));
			meterReadingImportLines.setReadingYear(resultSet.getString("READING_YEAR"));		
			
			return meterReadingImportLines;
		}
		
	}





}
