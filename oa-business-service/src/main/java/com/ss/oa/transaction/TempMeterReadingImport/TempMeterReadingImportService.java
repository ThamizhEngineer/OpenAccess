package com.ss.oa.transaction.TempMeterReadingImport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.common.Response;
import com.ss.oa.transaction.operationsservice.OperationsService;
import com.ss.oa.transaction.vo.TempMeterReadingImport;
import com.ss.oa.transaction.vo.TempMeterReadingImportLines;

@Component
public class TempMeterReadingImportService {
	@Autowired
	TempMeterReadingImportDao dao;
	@Autowired
	OperationsService operationsService;
	
	Response result= new Response();
	public TempMeterReadingImportService() {
		super();
	}
	
	public List<TempMeterReadingImport> getAllTempMeterReadingImport(Map<String,String> criteria){
		
		return dao.getAllTempMeterReadingImport(criteria);
	}
	
	public TempMeterReadingImport getTempMeterReadingImportById(String id) {
		
		return dao.getTempMeterReadingImportById(id);
	}
	
	

	public String addAndProcessTempMeterReadingImport(TempMeterReadingImport tempMeterReadingImport) throws Exception {
		Response response = this.addTempMeterReadingImport(tempMeterReadingImport);
		
		HashMap<String,String> criteria = new HashMap<String,String>();
		criteria.put("batch-id",response.getId());
		return operationsService.importAndProcessMri(criteria);
	}
	
	public Response addTempMeterReadingImport(TempMeterReadingImport tempMeterReadingImport) {
		try {
			result = dao.addTempMeterReadingImport(tempMeterReadingImport);
			
			if(tempMeterReadingImport.getTempMeterReadingImportLines()!=null)
			{

			dao.saveTempMeterReadingImportLinesByBatch(tempMeterReadingImport.getTempMeterReadingImportLines(),result.getId());
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
	
	public Response updateMeterReadingImport(String id,TempMeterReadingImport tempMeterReadingImport) {
		result = dao.updateTempMeterReadingImport(id, tempMeterReadingImport);
		if(tempMeterReadingImport.getTempMeterReadingImportLines()!=null)
		{
			for(TempMeterReadingImportLines tempMeterReadingImportLines:tempMeterReadingImport.getTempMeterReadingImportLines()) {
				if(tempMeterReadingImportLines.getId()!=null && tempMeterReadingImportLines.getId().trim().length()>0) {		
					
					dao.updateTempMeterReadingImportLines(tempMeterReadingImportLines);
				}else {
					tempMeterReadingImportLines.setImpMcMrHeaderId(id);
					dao.addTempMeterReadingImportLines(tempMeterReadingImportLines);
					
				}				
			}			
		}
		
		return result;

	}
}
