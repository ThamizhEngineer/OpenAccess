package com.ss.oa.transaction.TempMeterReadingImport;

import java.util.List;
import java.util.Map;

import com.ss.oa.common.Response;

import com.ss.oa.transaction.vo.TempMeterReadingImport;
import com.ss.oa.transaction.vo.TempMeterReadingImportLines;

public interface TempMeterReadingImportDao {
	public abstract List<TempMeterReadingImport> getAllTempMeterReadingImport(Map<String,String> criteria);
	public abstract TempMeterReadingImport getTempMeterReadingImportById(String id);
	public abstract Response addTempMeterReadingImport(TempMeterReadingImport tempMeterReadingImport);
	public abstract Response updateTempMeterReadingImport(String id,TempMeterReadingImport tempMeterReadingImport);
	public abstract String addTempMeterReadingImportLines(TempMeterReadingImportLines tempMeterReadingImportLines);
	public abstract String updateTempMeterReadingImportLines(TempMeterReadingImportLines tempMeterReadingImportLines);
	String saveTempMeterReadingImportLinesByBatch(List<TempMeterReadingImportLines> tempMeterReadingImportLines,String id);
}
