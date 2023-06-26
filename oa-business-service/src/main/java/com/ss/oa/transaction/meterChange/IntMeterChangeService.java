package com.ss.oa.transaction.MeterChange;

import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;

@Scope("prototype")
public interface IntMeterChangeService {
	
	public IntMeterChange getTry();
	
	public ResponseEntity<?> fetchDetails(String serviceNo);
	
	public ResponseEntity<?> saveReadings(IntMeterChange meterChange);
}
