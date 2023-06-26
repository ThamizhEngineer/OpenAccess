package com.ss.oa.transaction.meterreading;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.MeterReading;
import com.ss.oa.transaction.vo.MeterReadingSlot;

@Scope("prototype")
public interface MeterReadingDao {
	
	public abstract List<MeterReading> getAllMeterReadings(Map<String,String> criteria);
	public abstract MeterReading getMeterReadingById(String id);
	public abstract String addMeterReading(MeterReading meterReading);
	public abstract String updateMeterReading(String id,MeterReading meterReading);
	public abstract String addMeterReadingSlot(MeterReadingSlot meterReadingSlot);
	public abstract String updateMeterReadingSlot(MeterReadingSlot meterReadingSlot);

}
