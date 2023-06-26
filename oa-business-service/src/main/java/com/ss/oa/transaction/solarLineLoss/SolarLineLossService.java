package com.ss.oa.transaction.solarLineLoss;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.master.company.CompanyRepository;
import com.ss.oa.master.company.MeterRepository;
import com.ss.oa.master.company.ServiceRepository;
import com.ss.oa.model.master.Company;
import com.ss.oa.model.master.Meter;
import com.ss.oa.model.master.Service;
import com.ss.oa.transaction.substationloss.SubstationLossRepository;
import com.ss.oa.model.master.Feeder;

@RestController
@Scope("prototype")
@RequestMapping("/transaction/solar-line-loss")
public class SolarLineLossService {

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	JdbcOperations jdbcOperation;

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	FeederRepository feederRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	MeterReadingRepository meterReadingRepository;

	@Autowired
	SolarFeederEndRepository feederEndRepository;

	@Autowired
	SubstationLossRepository subatationLossRepository;

	@GetMapping
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> solarLineLoss(@RequestParam(value = "feeder_id", required = false) String feeder_id,
			@RequestParam(value = "reading_month", required = false) String reading_month,
			@RequestParam(value = "reading_year", required = false) String reading_year) {
		
		try {
			Map<String, Object> solarLineLoss = new HashMap<String, Object>();

			Feeder feeder = feederRepository.findById(feeder_id).get();

			SolarFeederEnd feederEnd = null;

			List<FeederLineLoss> feederLineLoss = new ArrayList<FeederLineLoss>();

			List<Service> services = serviceRepository.findByfeederId(feeder_id);

			if (services != null && !services.isEmpty()) {

				float bulkMeterReading = 0;
				float totalAllWegs = 0;

				for (Service service : services) {

					FeederLineLoss lineLoss = new FeederLineLoss();

					lineLoss.setFeederName(feeder.getNAME());
					lineLoss.setFeederVoltage(feeder.getVOLTAGE());
					lineLoss.setServiceNumber(service.getNumber());
					lineLoss.setFeederLineLength(feeder.getFEEDER_LINE_LENGTH());

					Company company = companyRepository.findById(service.getCompanyId());
					lineLoss.setCompanyName(company.getName());

					Meter meter = meterRepository.findByServiceId(service.getId());
					lineLoss.setCompanyMeterNo(meter.getMeterNumber());

					MeterReadingHDR meterReadingHDR = meterReadingRepository
							.findByMeterIdAndReadingMonthAndReadingYear(meter.getId(), reading_month, reading_year);

					if (meterReadingHDR != null) {
						
						lineLoss.setExportUnits(meterReadingHDR.getTOTAL_EXPORT_GEN());

						totalAllWegs += Float.parseFloat(meterReadingHDR.getTOTAL_EXPORT_GEN());
						
						feederEnd = feederEndRepository
								.findBySsFeederIdAndInitialReadingDateAndFinalReadingDateAndSectiontypeContaining(
										feeder_id, meterReadingHDR.getINIT_READING_DT(),
										meterReadingHDR.getINIT_READING_DT().plusMonths(1), "SSEND");

						if (feederEnd != null) {

							lineLoss.setFeederEndMeterNo(feederEnd.getMETERNO());

							bulkMeterReading = (((Integer.parseInt(feederEnd.getEXP_FINAL_S1())
									- Integer.parseInt(feederEnd.getEXP_INIT_S1()))
									+ (Integer.parseInt(feederEnd.getEXP_FINAL_S2())
											- Integer.parseInt(feederEnd.getEXP_INIT_S2()))
									+ (Integer.parseInt(feederEnd.getEXP_FINAL_S3())
											- Integer.parseInt(feederEnd.getEXP_INIT_S3()))
									+ (Integer.parseInt(feederEnd.getEXP_FINAL_S4())
											- Integer.parseInt(feederEnd.getEXP_INIT_S4()))
									+ (Integer.parseInt(feederEnd.getEXP_FINAL_S5())
											- Integer.parseInt(feederEnd.getEXP_INIT_S5())))
									* Float.parseFloat(feederEnd.getMF())) / 1000;

							lineLoss.setFeederEnd(String.valueOf(bulkMeterReading));

							float exportDifference = totalAllWegs - bulkMeterReading;
							float lossPercentage = 0;

							if (exportDifference > 0) {
								lossPercentage = (exportDifference / totalAllWegs) * 100;
							}

							if (!subatationLossRepository.existsByFeederIdAndMonthAndYear(feeder_id, reading_month,
									reading_year)) {
								solarLineLoss.put("success", true);
							} else {
								solarLineLoss.put("success", false);
							}

							DecimalFormat df = new DecimalFormat("0.000");

							solarLineLoss.put("serviceReadings", feederLineLoss);
							solarLineLoss.put("totalAllWegs", totalAllWegs);
							solarLineLoss.put("feederMeterNo", feederEnd.getMETERNO());
							solarLineLoss.put("bulkMeterReading", bulkMeterReading);
							solarLineLoss.put("exportsDifference", exportDifference);
							solarLineLoss.put("lineLossPercentage", Float.parseFloat(df.format(lossPercentage)));

							solarLineLoss.put("status", HttpStatus.OK);
							solarLineLoss.put("message", "Data's feteched sucessfully");
						}else {
							solarLineLoss.put("totalAllWegs", totalAllWegs);
							solarLineLoss.put("bulkMeterReading", bulkMeterReading);
							solarLineLoss.put("serviceReadings", feederLineLoss);
							solarLineLoss.put("status", HttpStatus.OK);
							solarLineLoss.put("message", "FeederEnd does not have reading for the provided details");
						}
						
					} else {
						solarLineLoss.put("serviceReadings", feederLineLoss);
						solarLineLoss.put("status", HttpStatus.OK);
						solarLineLoss.put("message",
								"Feeder services does not have HDR readings for the selected month and year");
					}
					feederLineLoss.add(lineLoss);
				}
				return ResponseEntity.ok(solarLineLoss);
			}
			solarLineLoss.put("status", HttpStatus.BAD_REQUEST);
			solarLineLoss.put("message", "No services available for the feeder");
			return ResponseEntity.ok(solarLineLoss);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
