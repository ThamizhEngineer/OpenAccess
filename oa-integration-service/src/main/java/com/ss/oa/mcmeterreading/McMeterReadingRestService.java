package com.ss.oa.mcmeterreading;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.IntegrationRestService;
import com.ss.oa.vo.McMeterReading;
import com.ss.oashared.common.OpenAccessException;

@RestController
public class McMeterReadingRestService extends IntegrationRestService{

	@Autowired
	McMeterReadingService service;
	
 @RequestMapping(value="/mcmeterreadings", method = RequestMethod.GET)
	public ResponseEntity<List<McMeterReading>> getMcMeterReadings(){
	return ResponseEntity.ok(service.getAllMcMeterReadings());
  }
 
	@RequestMapping(value="/mcmeterreadings/_internal", method = RequestMethod.GET)
	public List<McMeterReading> getSlotWiseMeterReadingInternal()throws OpenAccessException{
			return service.getAllMcMeterReadings();
		
	}
}
