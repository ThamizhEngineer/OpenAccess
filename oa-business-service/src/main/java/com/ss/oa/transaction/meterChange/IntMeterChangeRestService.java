package com.ss.oa.transaction.MeterChange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.master.company.ServiceRepository;

@RestController
@RequestMapping("/transaction/meterChange")
public class IntMeterChangeRestService {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	IntMeterChangeService meterService;
	
	@GetMapping("what")
	public IntMeterChange getTry() {
		return meterService.getTry();
	}
	
	@GetMapping("fetchService")
	public ResponseEntity<?> fetchDetails(@RequestParam String serviceNo) {
		return meterService.fetchDetails(serviceNo);
	}
	
	@PostMapping("saveReadings")
	public ResponseEntity<?> saveReadings(@RequestBody IntMeterChange meterReadings) {
		return ResponseEntity.ok(meterService.saveReadings(meterReadings));
	}
}
