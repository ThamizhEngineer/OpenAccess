package com.ss.oa.transaction.meterreadingimport;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.common.Response;
import com.ss.oa.transaction.vo.MeterReadingImport;
import com.ss.oa.transaction.vo.MeterReadingImportLines;

@Scope("prototype")
public interface MeterReadingImportDao {
	
	public abstract List<MeterReadingImport> getAllMeterReadingImports(Map<String,String> criteria);
	public abstract MeterReadingImport getMeterReadingImportById(String id);
	public abstract Response addMeterReadingImport(MeterReadingImport meterReadingImport);
	public abstract Response updateMeterReadingImport(String id,MeterReadingImport meterReadingImport);
	public abstract String addMeterReadingImportLines(MeterReadingImportLines meterReadingImportLines);
	public abstract String updateMeterReadingImportLines(MeterReadingImportLines meterReadingImportLines);
	String saveMrImportLinesByBatch(List<MeterReadingImportLines> meterReadingImportLines,String id);

}
