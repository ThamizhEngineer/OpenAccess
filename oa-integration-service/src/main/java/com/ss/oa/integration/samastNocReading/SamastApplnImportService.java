package com.ss.oa.integration.samastNocReading;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.ExtSamastAppln;
import com.ss.oa.vo.ExtSamastApplnApproval;
import com.ss.oa.vo.ImpSamastAppLog;
import com.ss.oa.vo.IntSamstAppln;
import com.ss.oa.vo.IntSamstApplnOutput;
import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Component
public class SamastApplnImportService {
	//@Value("${samastdb.importappldateformatjava}")     private String importApplDateFormat;
	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	ExtSamstApplnDao extSamstApplnDao;
	
	@Autowired 
	IntSamstApplnRepo intSamstApplnRepo;
	
	@Autowired
	ExtSamastApplnApprovalRepo extSamastApplnApprovalRepo;
	
	@Autowired
	ImpSamastAppLogRepo impSamastAppLogRepo;
	
	@Autowired
	CommonUtils commonUtils;
	
	
	
	public SamastApplnImportService() {
		super();
	}

	// this will remove the time and use only the date part
	private String convertDate(String fromDt) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if(fromDt.equals("new-record")) {
			return fromDt;
		}else {
			return LocalDate.parse(fromDt.split("\\ ", 2)[0],dateTimeFormatter).toString();
		}				
	}


	public ImpSamastAppLog processRecords(String context,String fromDt,String toDt){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//DateTimeFormatter applDateFormatter = DateTimeFormatter.ofPattern(importApplDateFormat);
		String converDate=convertDate(fromDt);		
		List<ExtSamastAppln> extSamstApplns = null;
		if(context.equalsIgnoreCase("APPLICATION")) {
			extSamstApplns = extSamstApplnDao.getSamastNocByStatus(converDate,toDt);
		}else
		{
			extSamstApplns = extSamstApplnDao.getSamastNocByApprovalStatus(converDate,toDt);
		}
		List<IntSamstAppln> intSamstApplns = new ArrayList<IntSamstAppln>();
		
		String batchKey = commonUtils.generateId();
		
		for(ExtSamastAppln ext:extSamstApplns) {
			
			IntSamstAppln intSamst = new IntSamstAppln();
						
			
			intSamst.setApplNo(ext.getApplNo());
			intSamst.setAppId(Integer.parseInt(ext.getAppId()));
			intSamst.setApplRefNo(ext.getApplRefNo());
			intSamst.setApplDate(LocalDate.parse(ext.getApplDate().split("\\ ", 2)[0]));
			intSamst.setAppCategory(ext.getAppCategory());
			intSamst.setCustomerName(ext.getCustomerName());
			intSamst.setEntityInj(ext.getEntityInj());
			intSamst.setEntityInjEdc(ext.getEntityInjEdc());
			intSamst.setEntityDrl(ext.getEntityDrl());
			intSamst.setEntityDrlEdc(ext.getEntityDrlEdc());
			intSamst.setPeriodStartDate(LocalDate.parse(ext.getPeriodStartDate().split("\\ ", 2)[0],dateTimeFormatter));
			intSamst.setPeriodEndDate(LocalDate.parse(ext.getPeriodEndDate().split("\\ ", 2)[0],dateTimeFormatter));
			
			if(ext.getQuantum()!=null) {
				intSamst.setQuantum(Float.parseFloat(ext.getQuantum()));
			}	
			
			
			intSamst.setCategory1(ext.getCategory1());
			intSamst.setCategory2(ext.getCategory2());			
			intSamst.setEorRegType(ext.getEorRegType());
			intSamst.setEosGcApprovalNumber(ext.getEosGcApprovalNumber());			
			intSamst.setEntityDrlEdc(ext.getEntityDrlEdc());
			intSamst.setEosFeederNameSeller(ext.getEosFeederNameSeller());
			intSamst.setEosVolLvlFeeder(ext.getEosVolLvlFeeder());
			intSamst.setEosInjSubstation(ext.getEosInjSubstation());
			intSamst.setEosVolLvlSubstation(ext.getEosVolLvlSubstation());
			
			if(ext.getEocLossPer()!=null) {
				intSamst.setEocLossPer(Float.parseFloat(ext.getEocLossPer()));
			}	
			if(ext.getEvacuationCapacity()!=null) {
				intSamst.setEvacuationCapacity(Float.parseFloat(ext.getEvacuationCapacity()));
			}			
			intSamst.setEosUtilInjEmbed(ext.getEosUtilInjEmbed());
			intSamst.setEosUtilInjEmbedLabel(ext.getEosUtilInjEmbedLabel());
			intSamst.setEobUtilDrawalEmbedLabel(ext.getEobUtilDrawalEmbedLabel());
			intSamst.setEobUtilDraEmbed(ext.getEobUtilDraEmbed());
			intSamst.setEobHtConsumerNumber(ext.getEobHtConsumerNumber());
			intSamst.setEobFeederNameBuyer(ext.getEobFeederNameBuyer());
			intSamst.setEobVolLvlFeeder(ext.getEobVolLvlFeeder());
			intSamst.setEobDraSubstation(ext.getEobDraSubstation());
			intSamst.setEobVolLvlSubstation(ext.getEobVolLvlSubstation());
			
			if(ext.getEobDrawalLimit()!=null) {
				intSamst.setEobDrawalLimit(Float.parseFloat(ext.getEobDrawalLimit()));
			}	
			
			
			intSamst.setEobBuyerType(ext.getEobBuyerType());
			intSamst.setEoiRegName(ext.getEoiRegName());
			intSamst.setEoiRegAddress(ext.getEoiRegAddress());
			intSamst.setEoiRegMobile1No(ext.getEoiRegMobile1No());
			intSamst.setEdcStatus(ext.getEdcStatus());
			intSamst.setApplicationStatus(ext.getApplicationStatus());
			intSamst.setAppType(ext.getAppType());
			intSamst.setSellerEdcCode(ext.getSellerEdcCode());
			intSamst.setBuyerEdcCode(ext.getBuyerEdcCode());
			intSamst.setIpApprNo(ext.getIpApprNo());
			intSamst.setApprovalNo(ext.getApprovalNo());
			
			intSamst.setBatchKey(batchKey);
			intSamst.setIsClean("");
			intSamst.setInputRemarks(null);
			
			intSamstApplns.add(intSamst);						
		}		
		
		intSamstApplnRepo.saveAll(intSamstApplns);
		
		ImpSamastAppLog outPut = formOutput(batchKey,context,fromDt,String.valueOf(intSamstApplns.size()));	
		String processResult = processIntAdjData(batchKey,context);
		outPut.setRemarks(processResult); //update processing-result in log table
		impSamastAppLogRepo.save(outPut);
		return outPut;
	}
//	
//	public ImpSamastAppLog processApprovals(String fromDt,String toDt){
//		
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String converDate=convertDate(fromDt);	
//		
//		List<ExtSamastAppln> extSamstApplns = extSamstApplnDao.getSamastNocByApprovalStatus(converDate,toDt);
//		List<ExtSamastApplnApproval> extSamastApplnApproval = new ArrayList<ExtSamastApplnApproval>();
//		String batchKey = commonUtils.generateId();		
//		for(ExtSamastAppln ext:extSamstApplns) {
//			
//			ExtSamastApplnApproval extSamst = new ExtSamastApplnApproval();
//						
//			
//			extSamst.setApplNo(ext.getApplNo());
//			extSamst.setAppId(Integer.parseInt(ext.getAppId()));
//			extSamst.setApplRefNo(ext.getApplRefNo());
//			extSamst.setApplDate(LocalDate.parse(ext.getApplDate().split("\\ ", 2)[0],dateTimeFormatter));
//			extSamst.setAppCategory(ext.getAppCategory());
//			extSamst.setCustomerName(ext.getCustomerName());
//			extSamst.setEntityInj(ext.getEntityInj());
//			extSamst.setEntityInjEdc(ext.getEntityInjEdc());
//			extSamst.setEntityDrl(ext.getEntityDrl());
//			extSamst.setEntityDrlEdc(ext.getEntityDrlEdc());
//			extSamst.setPeriodStartDate(LocalDate.parse(ext.getPeriodStartDate().split("\\ ", 2)[0],dateTimeFormatter));
//			extSamst.setPeriodEndDate(LocalDate.parse(ext.getPeriodEndDate().split("\\ ", 2)[0],dateTimeFormatter));
//			
//			if(ext.getQuantum()!=null) {
//				extSamst.setQuantum(Float.parseFloat(ext.getQuantum()));
//			}	
//			
//			
//			extSamst.setCategory1(ext.getCategory1());
//			extSamst.setCategory2(ext.getCategory2());			
//			extSamst.setEorRegType(ext.getEorRegType());
//			extSamst.setEosGcApprovalNumber(ext.getEosGcApprovalNumber());			
//			extSamst.setEntityDrlEdc(ext.getEntityDrlEdc());
//			extSamst.setEosFeederNameSeller(ext.getEosFeederNameSeller());
//			extSamst.setEosVolLvlFeeder(ext.getEosVolLvlFeeder());
//			extSamst.setEosInjSubstation(ext.getEosInjSubstation());
//			extSamst.setEosVolLvlSubstation(ext.getEosVolLvlSubstation());
//			
//			if(ext.getEocLossPer()!=null) {
//				extSamst.setEocLossPer(Float.parseFloat(ext.getEocLossPer()));
//			}	
//			if(ext.getEvacuationCapacity()!=null) {
//				extSamst.setEvacuationCapacity(Float.parseFloat(ext.getEvacuationCapacity()));
//			}			
//			extSamst.setEosUtilInjEmbed(ext.getEosUtilInjEmbed());
//			extSamst.setEosUtilInjEmbedLabel(ext.getEosUtilInjEmbedLabel());
//			extSamst.setEobUtilDrawalEmbedLabel(ext.getEobUtilDrawalEmbedLabel());
//			extSamst.setEobUtilDraEmbed(ext.getEobUtilDraEmbed());
//			extSamst.setEobHtConsumerNumber(ext.getEobHtConsumerNumber());
//			extSamst.setEobFeederNameBuyer(ext.getEobFeederNameBuyer());
//			extSamst.setEobVolLvlFeeder(ext.getEobVolLvlFeeder());
//			extSamst.setEobDraSubstation(ext.getEobDraSubstation());
//			extSamst.setEobVolLvlSubstation(ext.getEobVolLvlSubstation());
//			
//			if(ext.getEobDrawalLimit()!=null) {
//				extSamst.setEobDrawalLimit(Float.parseFloat(ext.getEobDrawalLimit()));
//			}	
//			
//			
//			extSamst.setEobBuyerType(ext.getEobBuyerType());
//			extSamst.setEoiRegName(ext.getEoiRegName());
//			extSamst.setEoiRegAddress(ext.getEoiRegAddress());
//			extSamst.setEoiRegMobile1No(ext.getEoiRegMobile1No());
//			extSamst.setEdcStatus(ext.getEdcStatus());
//			extSamst.setApplicationStatus(ext.getApplicationStatus());
//			extSamst.setAppType(ext.getAppType());
//			
//			extSamst.setBatchKey(batchKey);
//			extSamst.setIsClean("");
//			extSamst.setInputRemarks(null);
//			
//			extSamastApplnApproval.add(extSamst);						
//		}		
//		
//		extSamastApplnApprovalRepo.saveAll(extSamastApplnApproval);
//		ImpSamastAppLog outPut = formOutput(batchKey,"APPROVAL",fromDt,String.valueOf(extSamastApplnApproval.size()));	
//		String processResult = processIntAdjData(batchKey,"APPROVAL");
//		outPut.setRemarks(processResult); //update processing-result in log table
//		impSamastAppLogRepo.save(outPut);
//		return outPut;
//		
//	}

	
	public ImpSamastAppLog formOutput(String batchKey,String appType,String fromDt,String noOfRecords) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ImpSamastAppLog outPut = new ImpSamastAppLog();
		outPut.setNoOfRecords(noOfRecords);
		outPut.setBatchKey(batchKey);
		outPut.setAppType(appType);	
		if(!fromDt.equals("new-record")) {
			outPut.setFromDt(LocalDateTime.parse(convertDate(fromDt)+" 00:00:00",dateTimeFormatter));
		}		
		outPut.setToDt(LocalDateTime.now().minusMinutes(10));
		outPut.setRemarks(noOfRecords+"- Records imported for processing.");
		return impSamastAppLogRepo.save(outPut);
	}
	
	
	
	
	public String processIntAdjData(String batchKey,String impType) {
		String samastApplns = "{call IMP_SAMAST_APPLN.PROCESS_BATCH(?,?,?,?)}"; 
		String res3= null;
		String res4= null;
		Connection conn = null;
		CallableStatement stmt = null;
			try {
					conn =  dataSource.getConnection();
					stmt = conn.prepareCall(samastApplns);				        
			        stmt.setString(1, batchKey);
			        stmt.setString(2, impType);
			        stmt.registerOutParameter(3,OracleTypes.VARCHAR);  
			        stmt.registerOutParameter(4,OracleTypes.VARCHAR);  
			        stmt.execute();
			        res3= stmt.getString(3);
			        res4= stmt.getString(4);
			        stmt.close();
			        conn.close();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
				
				try {
					if((stmt!= null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if((conn!= null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			res4 = (res4==null)?"":res4;
			return res3+"-"+res4;
				
		}
}
