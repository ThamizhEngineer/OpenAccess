package com.ss.oa.integration.mcslotwisemeterreading;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.McSlotWiseMeterReading;

@Component
public class McSlotWiseMeterReadingService {
	@Autowired
	McSlotWiseMeterReadingDao dao;
	
	public McSlotWiseMeterReadingService() {
		super();
	}
	public  List<McSlotWiseMeterReading> getAllSlotWiseMeterReading(Map<String,String>criteria){
		return dao.getAllSlotWiseMeterReading(criteria);
	}
	public  McSlotWiseMeterReading getSlotWiseMeterReadingById(String id) {
		return dao.getSlotWiseMeterReadingById(id);
	}

}
