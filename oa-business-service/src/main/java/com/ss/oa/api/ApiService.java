package com.ss.oa.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.company.CompanyRepository;
import com.ss.oa.master.powerplant.PowerplantService1;
import com.ss.oa.master.traderelationship.TradeRelationshipRepository;
import com.ss.oa.master.traderelationship.VtradeRelationshipRepository;
import com.ss.oa.model.master.Generator;
import com.ss.oa.model.master.Powerplant;
import com.ss.oa.model.master.VtradeRelationship;
import com.ss.oa.transaction.energysaleintent.EnergySaleIntentRestService;
import com.ss.oa.transaction.meterreading.MeterReadingRestService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.MeterReading;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@RestController
@RequestMapping("/api/")
public class ApiService extends BaseDaoJdbc{
	
	@Autowired
	private PowerplantService1 powerplantService1;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private EnergySaleIntentRestService energySaleIntentRestService;
	
	
	@Autowired
	private MeterReadingRestService meterReadingRestService;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/my-powerplants/{serviceId}")
	public List<Powerplant> getPowerplants(@PathVariable(value="serviceId")String serviceId) throws OpenAccessException {
		List<Powerplant> myPowerPlants = new ArrayList<Powerplant>();
		for (Powerplant powerplant : powerplantService1.getPowerplant("", "", "", "", "", serviceId)) {
			myPowerPlants.addAll(powerplantService1.getPowerplantById(powerplant.getId()));
			break;
		}
		return myPowerPlants;
	}
	
	
	@CrossOrigin(origins="*")
	@GetMapping("/my-powerplants/{id}/print")
	public ResponseEntity<StreamingResponseBody>
  printPowerplants(@PathVariable("id") String id)
			throws FileNotFoundException {
		return  commonUtils.fetchFileAsStreamResponse(powerplantService1.printPowerplantGenerator(id));

	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/es-intents")
	public ResponseEntity<List<EnergySaleIntent>>  getEsIntents(@RequestParam(name="seller-edc-id",required=false) String sellerEdcId,
			@RequestParam(name="seller-company-service-number",required=false) String sellerCompanyServiceNumber,
			@RequestParam(name="seller-company-service-id",required=false) String sellerCompanyServiceId,
			@RequestParam(name="buyer-company-service-number",required=false) String buyerCompanyServiceNumber,
			@RequestParam(name="seller-company-id",required=false) String sellerCompanyId,
			@RequestParam(name="seller-company-name",required=false) String sellerCompanyName,
			@RequestParam(name="buyer-company-id",required=false) String buyerCompanyId,
			@RequestParam(name="buyer-company-name",required=false) String buyerCompanyName,
			@RequestParam(name="status",required=false) String status,
			@RequestParam(name="period",required=false) String period,
			@RequestParam(name="es-intent-code",required=false) String esIntentCode,
			@RequestParam(name="flow-type-code",required=false) String flowTypeCode){
		return  energySaleIntentRestService.getAllEnergySaleIntents(sellerEdcId, sellerCompanyServiceNumber, sellerCompanyServiceId, buyerCompanyServiceNumber, sellerCompanyId, sellerCompanyName, buyerCompanyId, buyerCompanyName, status, period, esIntentCode, flowTypeCode);

	}
	
//	Transaction - Meter Reading 
	@CrossOrigin(origins = "*")
	@GetMapping("/meterreading/{id}")
	public ResponseEntity<MeterReading> getMeterReadingById(@PathVariable(value="id")String id) throws OpenAccessException {
		return meterReadingRestService.getMeterReadingById(id);
	}


}
