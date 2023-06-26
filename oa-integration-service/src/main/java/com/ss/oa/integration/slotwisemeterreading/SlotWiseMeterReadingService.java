package com.ss.oa.integration.slotwisemeterreading;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.SlotWiseMeterReading;

@Component
public class SlotWiseMeterReadingService {
	@Autowired
	SlotWiseMeterReadingDao dao;
	
	public SlotWiseMeterReadingService() {
		super();
	}
	public  List<SlotWiseMeterReading> getAllSlotWiseMeterReading(Map<String,String>criteria){
		return dao.getAllSlotWiseMeterReading(criteria);
	}
	public  SlotWiseMeterReading getSlotWiseMeterReadingById(String id) {
		return dao.getSlotWiseMeterReadingById(id);
	}
}
