package com.ss.oa.transaction.operationsservice;

  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Float;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.ss.oa.integration.mdmsintegration.MdmsIntegrationService;
import com.ss.oa.integration.mdmsintegration.vo.MdmsIntegration;
import com.ss.oa.transaction.logservice.LogServiceService;
import com.ss.oa.transaction.meterreadingimport.MeterReadingImportService;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;


@Component
public class OperationsService {
	 @Autowired
	OperationsDao dao;
	 @Autowired
	 MdmsIntegrationService mdmsIntegrationService;
	 @Autowired
	 MeterReadingImportService mRimportService;
	 @Autowired
	 LogServiceService log;
	 	 
	public OperationsService(){
		super();
	}
	
	

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
	
	public  String importMeterReading(Map<String, String> criteria) throws Exception
	{
		String meterReadingImportId ="";
		try {
			if(criteria.get("batch-code")!=null) {
				List<MeterReadingImport> meterReadingImportList =  new ArrayList<MeterReadingImport>();
			
				Map<String,String> mriCriteria = new HashMap<String,String>();
				mriCriteria.put("code",criteria.get("batch-code"));
				meterReadingImportList = mRimportService.getAllMeterReadingImports(mriCriteria);
				for(MeterReadingImport meterReadingImport :meterReadingImportList) {
					meterReadingImportId = meterReadingImport.getId();
					System.out.println(meterReadingImportId);
					break;
					
				}
				criteria.put("batch-id", meterReadingImportId);
			}
			System.out.println(criteria.get("batch-id"));
			return dao.importMeterReading(criteria);
		
		     } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	} 
	
	public  String ProcessGenerationStatements(Map<String, String> criteria) throws Exception
	{
		try {
			 return dao.ProcessGenerationStatements(criteria);
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

			mdmsList=mdmsIntegrationService.importMrByMonthYear(month, year);
			System.out.println("importMrByMonthYear");
		}
		else {
			mdmsList=mdmsIntegrationService.importAllMr();
		}
		//System.out.println(mdmsList);
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
		 String mrImportId="";

		MeterReadingImport mRImport = new MeterReadingImport();
		List<MeterReadingImportLines> mRImportLinesList = new ArrayList<MeterReadingImportLines>();
		mRImport.setMrSourceCode("01");

	 	for (MdmsIntegration mdmsIntegration : mdmsList) {
			try{
				MeterReadingImportLines mRImportLines = new MeterReadingImportLines();
				
				mRImportLines.setMeterNumber(mdmsIntegration.getMeterNumber());
				mRImportLines.setMf(mdmsIntegration.getmF());
				mRImportLines.setServiceNumber(mdmsIntegration.getServiceNumber());
				mRImportLines.setSystemDate(mdmsIntegration.getSystemDate());
				mRImportLines.setInitReadingDate(mdmsIntegration.getInitialReadingDate());
				mRImportLines.setFinalReadingDate(mdmsIntegration.getFinalReadingDate());
				//mRImportLines.setImpInitS1(mdmsIntegration.getImpInitS1());
				//mRImportLines.setImpInitS2(mdmsIntegration.getImpInitS2());
				//mRImportLines.setImpInitS3(mdmsIntegration.getImpInitS3());
				//mRImportLines.setImpInitS4(mdmsIntegration.getImpInitS4());
				//mRImportLines.setImpInitS5(mdmsIntegration.getImpInitS5());
				Float ImpFinalS1 = Float.parseFloat(mdmsIntegration.getImpFinalS1());
				ImpFinalS1 = ImpFinalS1/1000 ;
				mRImportLines.setImpFinalS1(ImpFinalS1.toString());
				Float ImpFinalS2 = Float.parseFloat(mdmsIntegration.getImpFinalS2());
				ImpFinalS2 = ImpFinalS2/1000 ;
				mRImportLines.setImpFinalS2(ImpFinalS2.toString());
				Float ImpFinalS3 = Float.parseFloat(mdmsIntegration.getImpFinalS3());
				ImpFinalS3 = ImpFinalS3/1000 ;
				mRImportLines.setImpFinalS3(ImpFinalS3.toString());
				Float ImpFinalS4 = Float.parseFloat(mdmsIntegration.getImpFinalS4());
				ImpFinalS4 = ImpFinalS4/1000 ;
				mRImportLines.setImpFinalS4(ImpFinalS4.toString());
				Float ImpFinalS5 = Float.parseFloat(mdmsIntegration.getImpFinalS5());
				ImpFinalS5 = ImpFinalS5/1000 ;
				mRImportLines.setImpFinalS5(ImpFinalS5.toString());
				//mRImportLines.setImpFinalS1(mdmsIntegration.getImpFinalS1());
				//mRImportLines.setImpFinalS2(mdmsIntegration.getImpFinalS2());
				//mRImportLines.setImpFinalS3(mdmsIntegration.getImpFinalS3());
				//mRImportLines.setImpFinalS4(mdmsIntegration.getImpFinalS4());
				//mRImportLines.setImpFinalS5(mdmsIntegration.getImpFinalS5());
				
				//mRImportLines.setExpInitS1(mdmsIntegration.getExpInitS1());
				//mRImportLines.setExpInitS2(mdmsIntegration.getExpInitS2());
				//mRImportLines.setExpInitS3(mdmsIntegration.getExpInitS3());
				//mRImportLines.setExpInitS4(mdmsIntegration.getExpInitS4());
				//mRImportLines.setExpInitS5(mdmsIntegration.getExpInitS5());
				Float ExpFinalS1 = Float.parseFloat(mdmsIntegration.getExpFinalS1());
				ExpFinalS1 = ExpFinalS1/1000 ;
				mRImportLines.setExpFinalS1(ExpFinalS1.toString());
				Float ExpFinalS2 = Float.parseFloat(mdmsIntegration.getExpFinalS2());
				ExpFinalS2 = ExpFinalS2/1000 ;
				mRImportLines.setExpFinalS2(ExpFinalS2.toString());
				Float ExpFinalS3 = Float.parseFloat(mdmsIntegration.getExpFinalS3());
				ExpFinalS3 = ExpFinalS3/1000 ;
				mRImportLines.setExpFinalS3(ExpFinalS3.toString());
				Float ExpFinalS4 = Float.parseFloat(mdmsIntegration.getExpFinalS4());
				ExpFinalS4 = ExpFinalS4/1000 ;
				mRImportLines.setExpFinalS4(ExpFinalS4.toString());
				Float ExpFinalS5 = Float.parseFloat(mdmsIntegration.getExpFinalS5());
				ExpFinalS5 = ExpFinalS5/1000 ;
				mRImportLines.setExpFinalS5(ExpFinalS5.toString());
				//mRImportLines.setExpFinalS1(mdmsIntegration.getExpFinalS1());
				//mRImportLines.setExpFinalS2(mdmsIntegration.getExpFinalS2());
				//mRImportLines.setExpFinalS3(mdmsIntegration.getExpFinalS3());
				//mRImportLines.setExpFinalS4(mdmsIntegration.getExpFinalS4());
				//mRImportLines.setExpFinalS5(mdmsIntegration.getExpFinalS5());
				mRImportLines.setReadingMonthCode(mdmsIntegration.getMonth());
				mRImportLines.setReadingYear(mdmsIntegration.getYear());
				mRImportLines.setImpkVahInit(transform(mdmsIntegration.getImpKvahInit()));
				mRImportLines.setExpkVahInit(transform(mdmsIntegration.getExpKvahInit()));
				Float ImpkVahFinal = Float.parseFloat(mdmsIntegration.getImpKvahFinal());
				ImpkVahFinal = ImpkVahFinal/1000 ;
				mRImportLines.setImpkVahFinal(ImpkVahFinal.toString());
				Float ExpkVahFinal = Float.parseFloat(mdmsIntegration.getExpKvahFinal());
				ExpkVahFinal = ExpkVahFinal/1000 ;
				mRImportLines.setExpkVahFinal(ExpkVahFinal.toString());
			//	mRImportLines.setImpkVahFinal(mdmsIntegration.getImpKvahFinal());
			//	mRImportLines.setExpkVahFinal(mdmsIntegration.getExpKvahFinal());
				mRImportLines.setImpRkvahInit(transform(mdmsIntegration.getImpKvarhInit()));
				mRImportLines.setExpRkvahInit(transform(mdmsIntegration.getExpKvarhInit()));
				Float ImpKvarhFinal=Float.parseFloat(mdmsIntegration.getQ1KvarhFinal()) +  Float.parseFloat(mdmsIntegration.getQ2KvarhFinal());
				ImpKvarhFinal = ImpKvarhFinal/1000 ;
				mRImportLines.setImpRkvahFinal(ImpKvarhFinal.toString());
				Float ExpKvarhFinal =Float.parseFloat(mdmsIntegration.getQ3KvarhFinal()) + Float.parseFloat(mdmsIntegration.getQ4KvarhFinal());
				ExpKvarhFinal = ExpKvarhFinal/1000 ;
				mRImportLines.setExpRkvahFinal(ExpKvarhFinal.toString());
			
				mRImportLinesList.add(mRImportLines);
				mRImport.setMeterReadingImportLines(mRImportLinesList);
		//		System.out.println(mRImportLines);
		//		System.out.println("Added MeterReadingImportLines");
			}
			catch(Exception e){
				System.out.println("Error Importing MDMS entry-"+mdmsIntegration);
				//e.printStackTrace();
			}
	 	}	

	 	mrImportId= mRimportService.addMeterReadingImport(mRImport).getId();

			
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("batch-id", mrImportId);
		String result = importAndProcessMri(criteria);

		log.add("oa-service", "OperationService", "addImportMeterReadingFromMdms", result, null,null,null,null);
		return result;
	}

}
	
