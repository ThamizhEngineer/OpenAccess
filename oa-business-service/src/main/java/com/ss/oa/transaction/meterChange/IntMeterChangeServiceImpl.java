package com.ss.oa.transaction.MeterChange;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ss.oa.master.company.CompanyRepository;
import com.ss.oa.master.company.MeterRepository;
import com.ss.oa.master.company.ServiceRepository;
import com.ss.oa.master.powerplant.PowerplantRepository;
import com.ss.oa.model.master.Meter;
import com.ss.oa.model.master.Service;

@Component
public class IntMeterChangeServiceImpl implements IntMeterChangeService {

	@Autowired
	IntMeterChangeRepository meterChangeRepo;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	PowerplantRepository powerPlantRepository;
	
	@Autowired
	MeterRepository meterRepository;

	@Override
	public IntMeterChange getTry() {
		try {
			return meterChangeRepo.findById("101").get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ResponseEntity<?> fetchDetails(String serviceNo) {
		Map<String, Object> serviceDetails = new HashMap<>();
		try {
			Service service = serviceRepository.findByNumber(serviceNo);
			if(service != null) {
				Meter meter = meterRepository.findByServiceId(service.getId());
				serviceDetails.put("meterNumber", meter.getMeterNumber());
				serviceDetails.put("mfValue", meter.getMultiplicationFactor());
				serviceDetails.put("flowTypeCode", service.getFlowTypeCode());
				serviceDetails.put("companyName", companyRepository.findById(service.getCompanyId()).getName());
				serviceDetails.put("commissionDate", powerPlantRepository.findByServiceId(service.getId()).iterator().next().getCommissionDate());
			}else {
				serviceDetails.put("status", HttpStatus.CONFLICT);
				serviceDetails.put("message", "Enter valid service no");
			   return ResponseEntity.ok(serviceDetails);
			}
			serviceDetails.put("status", HttpStatus.OK);
			serviceDetails.put("message","Data fetched successfully");
			return ResponseEntity.ok(serviceDetails);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> saveReadings(IntMeterChange meterReadings) {
		try {
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYY:HHmmss");
			String importRemarks = "EDC"
					+ serviceRepository.findByNumber(meterReadings.getServiceNo()).getOrgId()
					+ "_" + meterReadings.getServiceNo().substring(8) + "_"+date.format(formatter);
			System.out.println(importRemarks);
			meterReadings.setImportRemarks(importRemarks);
		    meterChangeRepo.save(meterReadings);
			return ResponseEntity.ok("Readings saved sucessfully");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
