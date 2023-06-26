package com.ss.oa.transaction.meterreadingimport;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.common.Response;
import com.ss.oa.transaction.operationsservice.OperationsService;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;


@Component
public class MeterReadingImportService {
	
	@Autowired
	MeterReadingImportDao dao;
	@Autowired
	OperationsService operationsService;
	
	Response result= new Response();
	public MeterReadingImportService() {
		super();
	}
	
	public List<MeterReadingImport> getAllMeterReadingImports(Map<String,String> criteria){
		
		return dao.getAllMeterReadingImports(criteria);
	}
	
	public MeterReadingImport getMeterReadingImportById(String id) {
		
		return dao.getMeterReadingImportById(id);
	}
	
	

	public String addAndProcessMeterReadingImport(MeterReadingImport meterReadingImport) throws Exception {
		Response response = this.addMeterReadingImport(meterReadingImport);
		
		HashMap<String,String> criteria = new HashMap<String,String>();
		criteria.put("batch-id",response.getId());
		return operationsService.importAndProcessMri(criteria);
	}
	
	public Response addMeterReadingImport(MeterReadingImport meterReadingImport) {
		try {
			result = dao.addMeterReadingImport(meterReadingImport);
			
			if(meterReadingImport.getMeterReadingImportLines()!=null)
			{

			dao.saveMrImportLinesByBatch(meterReadingImport.getMeterReadingImportLines(),result.getId());
//				for(MeterReadingImportLines meterReadingImportLines:meterReadingImport.getMeterReadingImportLines()) {
//					meterReadingImportLines.setImpMrHeaderId(result.getId());
//					dao.addMeterReadingImportLines(meterReadingImportLines);
//				}
				
			}
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
	}
	
	

//	public String saveMrLinesByBatch(List<MeterReadingImportLines> meterReadingImportLines) {
//		String ss="success";
//		dao.saveMrImportLinesByBatch(meterReadingImportLines);
//
//		return ss;
//	}
	
	public Response updateMeterReadingImport(String id,MeterReadingImport meterReadingImport) {
		result = dao.updateMeterReadingImport(id,meterReadingImport);
		if(meterReadingImport.getMeterReadingImportLines()!=null)
		{
			for(MeterReadingImportLines meterReadingImportLines:meterReadingImport.getMeterReadingImportLines()) {
				if(meterReadingImportLines.getId()!=null && meterReadingImportLines.getId().trim().length()>0) {		
					
					dao.updateMeterReadingImportLines(meterReadingImportLines);
				}else {
					meterReadingImportLines.setImpMrHeaderId(id);
					dao.addMeterReadingImportLines(meterReadingImportLines);
					
				}				
			}			
		}
		
		return result;

	}
}
