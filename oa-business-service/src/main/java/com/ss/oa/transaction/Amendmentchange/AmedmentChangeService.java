package com.ss.oa.transaction.Amendmentchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.integration.AmendmentChangeIntegration.AmendmentIntegrationService;
import com.ss.oa.integration.mdmsintegration.vo.MdmsIntegration;
import com.ss.oa.transaction.TempMeterReadingImport.TempMeterReadingImportService;
import com.ss.oa.transaction.logservice.LogServiceService;
import com.ss.oa.transaction.operationsservice.OperationsDao;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;
import com.ss.oa.transaction.vo.TempMeterReadingImport;
import com.ss.oa.transaction.vo.TempMeterReadingImportLines;
@RestController
public class AmedmentChangeService {
	 @Autowired
	 AmendmentIntegrationService amendmentIntegrationService;
	 @Autowired
	 LogServiceService log;
	 @Autowired
	 AmendmentDao dao;
	 @Autowired
	 TempMeterReadingImportService tempMeterReadingImportService;
//	 @Autowired
//	T dao;
	 public  String importAndProcessMri(Map<String, String> criteria) throws Exception
		{
			try { 
				System.out.println(criteria.get("batch-id"));
				return dao.importAndProcessMri(criteria);
			
		     } catch (Exception e) { 
			e.printStackTrace();
			throw e;
		   }
			
		} 
	public String importMeterReadingFromMdms(String month,String year) throws Exception {
		//MdmsIntegrationService mdmsIntegrationService= new MdmsIntegrationService();
		String result = "Issue With Process. Please check logs";
		log.add("oa-service", "OperationService", "importMeterReadingFromMdms", "Start", null, month+"-"+year,null,null);
		MdmsIntegration mdms = new MdmsIntegration();
		List<MdmsIntegration> mdmsList = new ArrayList<MdmsIntegration>();
		if(month!=null&&year!=null){
			mdms.setMonth(month);
			mdms.setYear(year);

			mdmsList=amendmentIntegrationService.importMrByMonthYear(month, year);
			System.out.println("importMrByMonthYear");
		}
		else {
			mdmsList=amendmentIntegrationService.importAllMr();
		}
		System.out.println(mdmsList);
		log.add("oa-service", "OperationService", "importMeterReadingFromMdms", mdmsList.size()+" records imported from MDMS", null, month+"-"+year,null,null);
		
		if(mdmsList.size()>0){
			result = addImportMeterReadingFromMdms(mdmsList);
		}
		
		return result;
	}
	private String transform(String amrValue) {
		// divide by 1000
		String newValue="0";
		if(amrValue == null || amrValue.isEmpty()) {
			newValue = "0";
		}else {
			float floatValue = Float.parseFloat(amrValue);
			newValue = floatValue/1000 + "";
		}
		return newValue;
			
	}
	private String addImportMeterReadingFromMdms(List<MdmsIntegration> mdmsList) throws Exception {
		 String tempMeterReadingId="";
		 String result="";
		TempMeterReadingImport tempMeterReadingImport = new TempMeterReadingImport();
		List<TempMeterReadingImportLines> tempMeterReadingImportLinesList = new ArrayList<TempMeterReadingImportLines>();
		tempMeterReadingImport.setMrSourceCode("01");

	 	for (MdmsIntegration mdmsIntegration : mdmsList) {
			try{
				TempMeterReadingImportLines tempMeterReadingImportLines = new TempMeterReadingImportLines();
				tempMeterReadingImportLines.setStatusCode("CREATED");
				tempMeterReadingImportLines.setDownloadStatus(mdmsIntegration.getDownloadStatus());
				tempMeterReadingImportLines.setMeterNumber(mdmsIntegration.getMeterNumber());
				tempMeterReadingImportLines.setMf(mdmsIntegration.getmF());
				tempMeterReadingImportLines.setServiceNumber(mdmsIntegration.getServiceNumber());
				tempMeterReadingImportLines.setSystemDate(mdmsIntegration.getSystemDate());
				tempMeterReadingImportLines.setInitReadingDate(mdmsIntegration.getInitialReadingDate());
				tempMeterReadingImportLines.setFinalReadingDate(mdmsIntegration.getFinalReadingDate());
				Float ImpFinalS1 = Float.parseFloat(mdmsIntegration.getImpFinalS1());
				ImpFinalS1 = ImpFinalS1/1000 ;
				tempMeterReadingImportLines.setImpFinalS1(ImpFinalS1.toString());
				Float ImpFinalS2 = Float.parseFloat(mdmsIntegration.getImpFinalS2());
				ImpFinalS2 = ImpFinalS2/1000 ;
				tempMeterReadingImportLines.setImpFinalS2(ImpFinalS2.toString());
				Float ImpFinalS3 = Float.parseFloat(mdmsIntegration.getImpFinalS3());
				ImpFinalS3 = ImpFinalS3/1000 ;
				tempMeterReadingImportLines.setImpFinalS3(ImpFinalS3.toString());
				Float ImpFinalS4 = Float.parseFloat(mdmsIntegration.getImpFinalS4());
				ImpFinalS4 = ImpFinalS4/1000 ;
				tempMeterReadingImportLines.setImpFinalS4(ImpFinalS4.toString());
				Float ImpFinalS5 = Float.parseFloat(mdmsIntegration.getImpFinalS5());
				ImpFinalS5 = ImpFinalS5/1000 ;
				tempMeterReadingImportLines.setImpFinalS5(ImpFinalS5.toString());
				Float ExpFinalS1 = Float.parseFloat(mdmsIntegration.getExpFinalS1());
				ExpFinalS1 = ExpFinalS1/1000 ;
				tempMeterReadingImportLines.setExpFinalS1(ExpFinalS1.toString());
				Float ExpFinalS2 = Float.parseFloat(mdmsIntegration.getExpFinalS2());
				ExpFinalS2 = ExpFinalS2/1000 ;
				tempMeterReadingImportLines.setExpFinalS2(ExpFinalS2.toString());
				Float ExpFinalS3 = Float.parseFloat(mdmsIntegration.getExpFinalS3());
				ExpFinalS3 = ExpFinalS3/1000 ;
				tempMeterReadingImportLines.setExpFinalS3(ExpFinalS3.toString());
				Float ExpFinalS4 = Float.parseFloat(mdmsIntegration.getExpFinalS4());
				ExpFinalS4 = ExpFinalS4/1000 ;
				tempMeterReadingImportLines.setExpFinalS4(ExpFinalS4.toString());
				Float ExpFinalS5 = Float.parseFloat(mdmsIntegration.getExpFinalS5());
				ExpFinalS5 = ExpFinalS5/1000 ;
				tempMeterReadingImportLines.setExpFinalS5(ExpFinalS5.toString());
				tempMeterReadingImportLines.setReadingMonthCode(mdmsIntegration.getMonth());
				tempMeterReadingImportLines.setReadingYear(mdmsIntegration.getYear());
				tempMeterReadingImportLines.setImpkVahInit(transform(mdmsIntegration.getImpKvahInit()));
				tempMeterReadingImportLines.setExpkVahInit(transform(mdmsIntegration.getExpKvahInit()));
				Float ImpkVahFinal = Float.parseFloat(mdmsIntegration.getImpKvahFinal());
				ImpkVahFinal = ImpkVahFinal/1000 ;
				tempMeterReadingImportLines.setImpkVahFinal(ImpkVahFinal.toString());
				Float ExpkVahFinal = Float.parseFloat(mdmsIntegration.getExpKvahFinal());
				ExpkVahFinal = ExpkVahFinal/1000 ;
				tempMeterReadingImportLines.setExpkVahFinal(ExpkVahFinal.toString());
				tempMeterReadingImportLines.setImpRkvahInit(transform(mdmsIntegration.getImpKvarhInit()));
				tempMeterReadingImportLines.setExpRkvahInit(transform(mdmsIntegration.getExpKvarhInit()));
				Float ImpKvarhFinal=Float.parseFloat(mdmsIntegration.getQ1KvarhFinal()) +  Float.parseFloat(mdmsIntegration.getQ2KvarhFinal());
				ImpKvarhFinal = ImpKvarhFinal/1000 ;
				tempMeterReadingImportLines.setImpRkvahFinal(ImpKvarhFinal.toString());
				Float ExpKvarhFinal =Float.parseFloat(mdmsIntegration.getQ3KvarhFinal()) + Float.parseFloat(mdmsIntegration.getQ4KvarhFinal());
				ExpKvarhFinal = ExpKvarhFinal/1000 ;
				tempMeterReadingImportLines.setExpRkvahFinal(ExpKvarhFinal.toString());
			
				tempMeterReadingImportLinesList.add(tempMeterReadingImportLines);
				tempMeterReadingImport.setTempMeterReadingImportLines(tempMeterReadingImportLinesList);
				System.out.println(tempMeterReadingImportLinesList);
				System.out.println("Added MeterReadingImportLines");
			}
			catch(Exception e){
				System.out.println("Error Importing MDMS entry-"+mdmsIntegration);
				//e.printStackTrace();
			}
	 	}	

	 	tempMeterReadingId= tempMeterReadingImportService.addTempMeterReadingImport(tempMeterReadingImport).getId();

//			
//		Map<String, String> criteria = new HashMap<String, String>();
//		criteria.put("batch-id", tempMeterReadingId);
//		 result = importAndProcessMri(criteria);

		log.add("oa-service", "OperationService", "addImportMeterReadingFromMdms", result, null,null,null,null);
		return result;
	}

	
}
