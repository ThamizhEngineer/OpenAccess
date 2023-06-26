package com.ss.oa.report.genericreportingservice;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ss.oa.report.vo.GenericReport;
import com.ss.oa.report.vo.GenericReportHeaderOutput;
import com.ss.oa.report.vo.GenericReportOutput;
import com.ss.oa.common.ReportMetaData;
import com.ss.oa.common.SQLHandler;

@Component
@Scope("prototype")
public class GenericReportingDaoImpl implements GenericReportingDao{

	@Resource
	JdbcOperations jdbcOperations;
	ArrayList<GenericReport> genericRptQueries = new ArrayList<GenericReport>();
	ReportMetaData rptData = new ReportMetaData();
	
	@Override
	public List<GenericReportOutput> getGenericReportResult(String reportName, Map<String, String> ipCriteria) {
		
		String sql = getSqlQuery(reportName, ipCriteria);
		if(sql != "" && sql != null){
		List<GenericReportOutput> rptOutput = (ArrayList<GenericReportOutput>)jdbcOperations.query(sql, 
				new GenericReportOutputMapper());
		return rptOutput;
		}
		else{
			return new ArrayList<GenericReportOutput>();
		}
	}
	
	@Override
	public GenericReportHeaderOutput getGenericRepResult(String reportName, Map<String, String> ipCriteria) {
		
		GenericReportHeaderOutput rptOutput = new GenericReportHeaderOutput();
		Map<Integer, String> header = new HashMap<Integer, String>();
		switch(reportName){
		case "GENERATOR-WISE-CONSUMER-REPORT":
			header.put(1, "Edc Name");
			header.put(2, "Company Name");
			header.put(3, "Location");
			header.put(4, "Injecting Voltage");
			break;
			
		case "PERIOD-BASED-APPROVAL-REPORT":
			
			break;
		case "PERIOD-BASED-AMENDMENT-REPORT":
			
			break;
		case "TOTAL-TRANSACTION-DETAILS":
			header.put(1, "Approval Number");
			header.put(2, "Applied Date");
			header.put(3, "Company Name");
			header.put(4, "Number");
			break;
		case "CONSUMER-REPORT":
			break;
			
		case "GENERATION-REPORT":
			break;
			
		case "WEG-GENERATOR-REPORT":
			
			break;
		case "WEG-TRANSACTION-STATUS":
             
		     break;
		     
		case "NIL-GENERATION-REPORT":
			
			break;
		     
		case "ENERGY-LEDGER-REPORT":
			
			 break;
			 
		case "REPORT-BASED-BANKING-REPORT":
			
			 break;
			 
		case "EDC-BASED-ENERGY-GENERATION-CHARGES-REPORT":
		  
		     break;
		     
		default:
			break;
		}
		rptOutput.setHeaders(header);
		rptOutput.setReportOutput(getGenericReportResult(reportName, ipCriteria));
		
		return rptOutput;
	}
	
	String getSqlQuery(String reportName, Map<String, String> ipCriteria){
		String sql = "";
		
		Map<String, String> defaultCriteria = null;
		if(genericRptQueries.size() == 0) {
			genericRptQueries = (ArrayList<GenericReport>)rptData.getGenericReports();
		}
		GenericReport genRpt = null;
		for (int rptIndex = 0; rptIndex < genericRptQueries.size(); rptIndex++){
			genRpt = genericRptQueries.get(rptIndex);
			if(genRpt.getReportName().equals(reportName)) 
				break;
			else
				genRpt = null;
		}
		
		if(genRpt != null){
		sql = genRpt.getSqlQuery();
		defaultCriteria = genRpt.getCriteria();
		setInputParams(ipCriteria, defaultCriteria);
		sql = frameQuery(sql, defaultCriteria);
		}
		else {
			sql = SQLHandler.GetCustomQuery(reportName, ipCriteria);
		}
		
		return sql;
	}

	private String frameQuery(String sql, Map<String, String> defaultCriteria){
		String rptCriteriaKey = "";
		if(sql != null && sql.trim() != "" && defaultCriteria.size() > 0){
			int keyIndex = -1;
			for (Map.Entry<String, String> rptCriteria : defaultCriteria.entrySet()){
				rptCriteriaKey = "##" + rptCriteria.getKey() + "##";
				keyIndex = sql.indexOf(rptCriteriaKey);
				if(keyIndex >= 0)
					sql = sql.replace(rptCriteriaKey, rptCriteria.getValue());
			}
		}
		
		return sql;
	}

	private void setInputParams(Map<String, String> ipCriteria, Map<String, String> defaultCriteria) {
		String ipCriteriaKey = "";
		String rptCriteriaKey = "";
		for (Map.Entry<String, String> inputCriteria : ipCriteria.entrySet()){
			ipCriteriaKey = inputCriteria.getKey();
			for(Map.Entry<String, String> reportCriteria : defaultCriteria.entrySet()){
				
				rptCriteriaKey = reportCriteria.getKey();
				if (ipCriteriaKey.equals(rptCriteriaKey)){
					reportCriteria.setValue(inputCriteria.getValue());
					break;
				}
			}
		}
	}
	
	final class GenericReportOutputMapper implements RowMapper<GenericReportOutput>{

		int colCount = 0;
		public GenericReportOutputMapper(){
			super();
		}
		@Override
		public GenericReportOutput mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			GenericReportOutput rptOutput = new GenericReportOutput();
			
			if(colCount == 0)
				colCount = getColumnCount(resultSet);
			
			for (int colIndex = 1; colIndex <= 41; colIndex++){
				switch (colIndex){
				case 1:
					if (colIndex <= colCount)
					rptOutput.setOp1(resultSet.getString(colIndex));
					else
						rptOutput.setOp1("");
					break;
				case 2:
					if (colIndex <= colCount)
					rptOutput.setOp2(resultSet.getString(colIndex));
					else
						rptOutput.setOp2("");
					break;
				case 3:
					if (colIndex <= colCount)
					rptOutput.setOp3(resultSet.getString(colIndex));
					else
						rptOutput.setOp3("");
					break;
				case 4:
					if (colIndex <= colCount)
					rptOutput.setOp4(resultSet.getString(colIndex));
					else
						rptOutput.setOp4("");
					break;
				case 5:
					if (colIndex <= colCount)
					rptOutput.setOp5(resultSet.getString(colIndex));
					else
						rptOutput.setOp5("");
					break;
				case 6:
					if (colIndex <= colCount)
					rptOutput.setOp6(resultSet.getString(colIndex));
					else
						rptOutput.setOp6("");
					break;
				case 7:
					if (colIndex <= colCount)
					rptOutput.setOp7(resultSet.getString(colIndex));
					else
						rptOutput.setOp7("");
					break;
				case 8:
					if (colIndex <= colCount)
					rptOutput.setOp8(resultSet.getString(colIndex));
					else
						rptOutput.setOp8("");
					break;
				case 9:
					if (colIndex <= colCount)
					rptOutput.setOp9(resultSet.getString(colIndex));
					else
						rptOutput.setOp9("");
					break;
				case 10:
					if (colIndex <= colCount)
					rptOutput.setOp10(resultSet.getString(colIndex));
					else
						rptOutput.setOp10("");
					break;
				case 11:
					if (colIndex <= colCount)
					rptOutput.setOp11(resultSet.getString(colIndex));
					else
						rptOutput.setOp11("");
					break;
				case 12:
					if (colIndex <= colCount)
					rptOutput.setOp12(resultSet.getString(colIndex));
					else
						rptOutput.setOp12("");
					break;
				case 13:
					if (colIndex <= colCount)
					rptOutput.setOp13(resultSet.getString(colIndex));
					else
						rptOutput.setOp13("");
					break;
				case 14:
					if (colIndex <= colCount)
					rptOutput.setOp14(resultSet.getString(colIndex));
					else
						rptOutput.setOp14("");
					break;
				case 15:
					if (colIndex <= colCount)
					rptOutput.setOp15(resultSet.getString(colIndex));
					else
						rptOutput.setOp15("");
					break;
				case 16:
					if (colIndex <= colCount)
					rptOutput.setOp16(resultSet.getString(colIndex));
					else
						rptOutput.setOp16("");
					break;
				case 17:
					if (colIndex <= colCount)
					rptOutput.setOp17(resultSet.getString(colIndex));
					else
						rptOutput.setOp17("");
					break;
				case 18:
					if (colIndex <= colCount)
					rptOutput.setOp18(resultSet.getString(colIndex));
					else
						rptOutput.setOp18("");
					break;
				case 19:
					if (colIndex <= colCount)
					rptOutput.setOp19(resultSet.getString(colIndex));
					else
						rptOutput.setOp19("");
					break;
				case 20:
					if (colIndex <= colCount)
					rptOutput.setOp20(resultSet.getString(colIndex));
					else
						rptOutput.setOp20("");
					break;
				case 21:
					if (colIndex <= colCount)
					rptOutput.setOp21(resultSet.getString(colIndex));
					else
						rptOutput.setOp21("");
					break;
				case 22:
					if (colIndex <= colCount)
					rptOutput.setOp22(resultSet.getString(colIndex));
					else
						rptOutput.setOp22("");
					break;
				case 23:
					if (colIndex <= colCount)
					rptOutput.setOp23(resultSet.getString(colIndex));
					else
						rptOutput.setOp23("");
					break;
				case 24:
					if (colIndex <= colCount)
					rptOutput.setOp24(resultSet.getString(colIndex));
					else
						rptOutput.setOp24("");
					break;
				case 25:
					if (colIndex <= colCount)
					rptOutput.setOp25(resultSet.getString(colIndex));
					else
						rptOutput.setOp25("");
					break;
				case 26:
					if (colIndex <= colCount)
					rptOutput.setOp26(resultSet.getString(colIndex));
					else
						rptOutput.setOp26("");
					break;
				case 27:
					if (colIndex <= colCount)
					rptOutput.setOp27(resultSet.getString(colIndex));
					else
						rptOutput.setOp27("");
					break;
				case 28:
					if (colIndex <= colCount)
					rptOutput.setOp28(resultSet.getString(colIndex));
					else
						rptOutput.setOp28("");
					break;
				case 29:
					if (colIndex <= colCount)
					rptOutput.setOp29(resultSet.getString(colIndex));
					else
						rptOutput.setOp29("");
				case 30:
					if (colIndex <= colCount)
					rptOutput.setOp30(resultSet.getString(colIndex));
					else
						rptOutput.setOp30("");
					break;
				case 31:
					if (colIndex <= colCount)
					rptOutput.setOp31(resultSet.getString(colIndex));
					else
						rptOutput.setOp31("");
					break;
				case 32:
					if (colIndex <= colCount)
					rptOutput.setOp32(resultSet.getString(colIndex));
					else
						rptOutput.setOp32("");
					break;
				case 33:
					if (colIndex <= colCount)
					rptOutput.setOp33(resultSet.getString(colIndex));
					else
						rptOutput.setOp33("");
					break;
				case 34:
					if (colIndex <= colCount)
					rptOutput.setOp34(resultSet.getString(colIndex));
					else
						rptOutput.setOp34("");
					break;
				case 35:
					if (colIndex <= colCount)
					rptOutput.setOp35(resultSet.getString(colIndex));
					else
						rptOutput.setOp35("");
					break;
				case 36:
					if (colIndex <= colCount)
					rptOutput.setOp36(resultSet.getString(colIndex));
					else
						rptOutput.setOp36("");
					break;
				case 37:
					if (colIndex <= colCount)
					rptOutput.setOp37(resultSet.getString(colIndex));
					else
						rptOutput.setOp37("");
					break;
				case 38:
					if (colIndex <= colCount)
					rptOutput.setOp38(resultSet.getString(colIndex));
					else
						rptOutput.setOp38("");
					break;
				case 39:
					if (colIndex <= colCount)
					rptOutput.setOp39(resultSet.getString(colIndex));
					else
						rptOutput.setOp39("");
					break;
				case 40:
					if (colIndex <= colCount)
					rptOutput.setOp40(resultSet.getString(colIndex));
					else
						rptOutput.setOp40("");
					break;
				case 41:
					if (colIndex <= colCount)
					rptOutput.setOp41(resultSet.getString(colIndex));
					else
						rptOutput.setOp41("");
					break;
				default:
					
					break;
				}
			}
			return rptOutput;
		}
		
		private int getColumnCount(ResultSet resultSet){
			try{
			ResultSetMetaData metaData = resultSet.getMetaData();
			return metaData.getColumnCount();
			
			}
			catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		}
		
	}	
}