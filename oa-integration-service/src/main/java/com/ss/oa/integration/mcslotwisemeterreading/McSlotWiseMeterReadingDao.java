package com.ss.oa.integration.mcslotwisemeterreading;

import java.util.List;
import java.util.Map;

import com.ss.oa.vo.McSlotWiseMeterReading;

public interface McSlotWiseMeterReadingDao {
	
	public abstract List<McSlotWiseMeterReading> getAllSlotWiseMeterReading(Map<String,String>criteria);
	
	public abstract McSlotWiseMeterReading getSlotWiseMeterReadingById(String id);

}
