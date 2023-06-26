package com.ss.oa.mcmeterreading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.McMeterReading;

@Component
public class McMeterReadingService {
	@Autowired
    McMeterReadingDao dao;
	
	public McMeterReadingService() {
		super();
	}
	public  List<McMeterReading> getAllMcMeterReadings(){
		return dao.getAllMcMeterReadings();
	}
}
