package com.ss.oa.integration.slotwisemeterreading;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.vo.SlotWiseMeterReading;

public interface SlotWiseMeterReadingDao {

	public abstract List<SlotWiseMeterReading> getAllSlotWiseMeterReading(Map<String,String>criteria);

	public abstract SlotWiseMeterReading getSlotWiseMeterReadingById(String id);
}
