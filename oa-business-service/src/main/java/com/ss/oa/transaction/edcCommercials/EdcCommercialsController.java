package com.ss.oa.transaction.edcCommercials;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.master.company.CompanyRepository;
import com.ss.oa.master.company.ServiceRepository;
import com.ss.oa.master.importtraderelationship.ImportTradeRepository;
import com.ss.oa.master.org.OrgRepository;
import com.ss.oa.master.powerplant.PowerplantRepository;
import com.ss.oa.model.master.Company;
import com.ss.oa.model.master.ImportTraderelationship;
import com.ss.oa.model.master.Service;

@RestController
@Scope("prototype")
@RequestMapping("/transaction/edc-commercials")
public class EdcCommercialsController {

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	OrgRepository orgRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	PowerplantRepository powerplantRepository;

	@Autowired
	ImportTradeRepository importTradeRel;

	@GetMapping("/fetchSeller")
	public ResponseEntity<?> serviceDetails(@RequestParam(value = "serviceNo", required = false) String serviceNo) {

		Map<String, Object> serviceDetails = new HashMap<String, Object>();

		try {
			Service service = serviceRepository.findByNumber(serviceNo);
			if (service != null && service.getFuelTypeCode().matches("SOLAR|WIND")) {

				Company company = companyRepository.findById(service.getCompanyId());
				if (company != null && company.getIsSeller().equals("Y")) {
					serviceDetails.put("sellerEndEdc", service.getOrgName());
					serviceDetails.put("companyName", company.getName());
					serviceDetails.put("flowTypeCode", service.getFlowTypeCode());
					serviceDetails.put("fuelType", service.getFuelTypeCode());
					serviceDetails.put("commissionDate", powerplantRepository.findByServiceId(service.getId())
							.iterator().next().getCommissionDate());
				} else {
					serviceDetails.put("status", HttpStatus.CONFLICT);
					serviceDetails.put("message", serviceNo + " is not a seller");
					return ResponseEntity.ok(serviceDetails);
				}
			} else {
				serviceDetails.put("status", HttpStatus.CONFLICT);
				serviceDetails.put("message",
						"Enter valid Service number or Invalid Fuel type code it needs to be WIND OR SOLAR");
				return ResponseEntity.ok(serviceDetails);
			}
			serviceDetails.put("status", HttpStatus.OK);
			serviceDetails.put("message", "Data Fetched Sucessfully");
			return ResponseEntity.ok(serviceDetails);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@GetMapping("/fetchBuyer")
	public ResponseEntity<?> buyerDetails(@RequestParam(value = "serviceNo", required = false) String serviceNo) {

		Map<String, Object> buyerDetails = new HashMap<String, Object>();

		try {
			Service service = serviceRepository.findByNumber(serviceNo);
			if (service != null) {

				Company company = companyRepository.findById(service.getCompanyId());
				if (company != null && company.getIsBuyer().equals("Y")) {

					buyerDetails.put("buyerEndEdc", orgRepository.findById(service.getOrgId()).get().getName());
					buyerDetails.put("companyName", companyRepository.findById(service.getCompanyId()).getName());
					buyerDetails.put("flowTypeCode", service.getFlowTypeCode());
				} else {
					buyerDetails.put("status", HttpStatus.OK);
					buyerDetails.put("message", "Seller cannot be added as a consumer");
					return ResponseEntity.ok(buyerDetails);
				}
			} else {
				buyerDetails.put("status", HttpStatus.OK);
				buyerDetails.put("message", "Enter valid Service number");
				return ResponseEntity.ok(buyerDetails);
			}

			buyerDetails.put("status", HttpStatus.OK);
			buyerDetails.put("message", "Data Fetched Sucessfully");
			return ResponseEntity.ok(buyerDetails);

		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@PostMapping("/saveCommercials")
	public ResponseEntity<?> importTradeCommercials(
			@RequestBody(required = false) List<ImportTraderelationship> importCommercials) {

		try {
			Map<String, Object> response = new HashMap<String, Object>();

			if (!importCommercials.isEmpty()) {
				LocalDateTime date = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYY:HHmmss");
				for (ImportTraderelationship commercial : importCommercials) {
					commercial.setIntervalTypeName("SHARE_PERCENTAGE");
					commercial.setIntervalTypeCode("04");
					commercial.setSharePercent("100");
					commercial.setFlowTypeCode(commercial.getFlowTypeCode());
					if (commercial.getFlowTypeCode().equals("THIRD-PARTY")) {
						String importRemarks = "EDC"
								+ serviceRepository.findByNumber(commercial.getSellerCompanyServiceNo()).getOrgId()
								+ "_" + commercial.getSellerCompanyServiceNo().substring(8) + "TP" + "_"
								+ date.format(formatter);
						System.out.println(importRemarks);
						commercial.setImportRemarks(importRemarks);
					} else {
						String importRemarks = "EDC"
								+ serviceRepository.findByNumber(commercial.getSellerCompanyServiceNo()).getOrgId()
								+ "_" + commercial.getSellerCompanyServiceNo().substring(8) + "_CAP" + "_"
								+ date.format(formatter);
						System.out.println(importRemarks);
						commercial.setImportRemarks(importRemarks);
					}
					importTradeRel.save(commercial);
				}
			} else {
				response.put("status", HttpStatus.NO_CONTENT);
				response.put("message", "No Commercial Details Found");
				return ResponseEntity.ok(response);
			}
			response.put("status", HttpStatus.OK);
			response.put("message", "Consumer Gets Added Sucessfully");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
