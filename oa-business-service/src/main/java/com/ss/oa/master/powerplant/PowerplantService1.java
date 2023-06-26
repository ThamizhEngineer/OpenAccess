package com.ss.oa.master.powerplant;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
import org.springframework.web.client.RestTemplate;

import com.ss.oa.common.BaseDaoJdbc;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.master.company.CompanyRepository;
import com.ss.oa.master.company.MeterRepository;
import com.ss.oa.master.company.ServiceRepository;
import com.ss.oa.master.importtraderelationship.ImportTradeService;
import com.ss.oa.master.traderelationship.TradeRelationshipRepository;
import com.ss.oa.master.traderelationship.VtradeRelationshipRepository;
import com.ss.oa.master.vo.Company;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.model.master.Generator;
import com.ss.oa.model.master.ImportTraderelationship;
import com.ss.oa.model.master.Meter;
import com.ss.oa.model.master.Powerplant;
import com.ss.oa.model.master.Service;
import com.ss.oa.model.master.VtradeRelationship;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

import oracle.jdbc.OracleTypes;

@RestController
@RequestMapping("/master/powerplantnew")
@Scope("prototype")
public class PowerplantService1 extends BaseDaoJdbc{
	@Value("${printapi.url}")
	private String printapi;
	
	private SimpleJdbcCall functionDeleteTxt;
	
	private SimpleJdbcCall deleteAddCommercials;


	
	@Resource
	private DataSource dataSource;
	
	@Autowired
	private PowerplantRepository powerplantRepository;
	
	@Autowired
	private GeneratorRepository generatorRepository;
	
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	VtradeRelationshipRepository VtradeRelationshipRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	MeterRepository meterRepository;
	
	@Autowired
	ImportTradeService importTradeService;
	
	private String rdRemarks;
	private String serviceNumber;
	private String rdMonth;
	private String rdYear;
	private String vRemarks;



	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<Powerplant> getPowerplant(@RequestParam(value="plantTypeCode",required=false)String plantTypeCode,
			@RequestParam(value="fuelTypeCode",required=false)String fuelTypeCode,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="orgId",required=false)String orgId,
			@RequestParam(value="companyId",required=false)String companyId,
			@RequestParam(value="serviceId",required=false)String serviceId) throws OpenAccessException {
		boolean searchByplantTypeCode=false,searchByfuelTypeCode=false,searchBycode=false,searchByorgId=false,searchBycompanyId=false;
		boolean searchByserviceId=false,searchByOrgIdAndServiceId=false;
		
		if(serviceId!=null&&!serviceId.isEmpty()) {
			searchByserviceId=true;
		}
		if(searchByserviceId) {
			return powerplantRepository.findByServiceId(serviceId);
		}
				
		if(plantTypeCode!=null&&!plantTypeCode.isEmpty()) {
			searchByplantTypeCode=true;
		}
		if(searchByplantTypeCode) {
			return powerplantRepository.findByPlantTypeCode(plantTypeCode);
		}
		if(fuelTypeCode!=null&&!fuelTypeCode.isEmpty()) {
			searchByfuelTypeCode=true;
		}
		if(searchByfuelTypeCode) {
			return powerplantRepository.findByFuelTypeCode(fuelTypeCode);
		}
		if(code!=null&&!code.isEmpty()) {
			searchBycode=true;
		}
		if(orgId!=null&&!orgId.isEmpty()&&serviceId!=null&&!serviceId.isEmpty()) {
			searchByOrgIdAndServiceId=true;
		}
		if(searchByOrgIdAndServiceId) {
			return powerplantRepository.findByOrgIdAndServiceId(orgId, serviceId);
		}
		if(searchBycode) {
			return powerplantRepository.findByCode(code);
		}
		if(orgId!=null&&!orgId.isEmpty()) {
			searchByorgId=true;
		}
		if(searchByorgId) {
			return powerplantRepository.findByOrgId(orgId);
		}
		if(companyId!=null&&!companyId.isEmpty()) {
			searchBycompanyId=true;
		}
		if(searchBycompanyId) {
			return powerplantRepository.findByCompanyId(companyId);
		}

		return powerplantRepository.findAll();
	}
	

	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public List<Powerplant> getPowerplantById(@PathVariable(value="id")String id)throws OpenAccessException{
		List<Powerplant> powerplants= new ArrayList<Powerplant>();
		Powerplant pp = powerplantRepository.findById(id);

		pp.setTradeRelationships(VtradeRelationshipRepository.findBySellerCompServiceId(pp.getServiceId()));
		pp.setCompany(companyRepository.findById(pp.getCompanyId()));
		String serviceId=pp.getServiceId();
		for (Service service : pp.getCompany().getServices()) {
			if(service.getId().equals(serviceId)) {
				//create service transient - get and set service using service id
				pp.setServices(serviceRepository.findById(serviceId));
			}
		}
		powerplants.add(powerplantRepository.findById(id)); 

		return powerplants;
		
	}
	//-----------------------------------------------------
	
	
	@CrossOrigin(origins = "*")
	@PutMapping("/updatepp/{id}")
	public Powerplant updatePowerplantForGenerator(@RequestBody Powerplant powerplant) throws OpenAccessException {
		List<ImportTraderelationship>  importTraderelationships= new ArrayList<ImportTraderelationship>();
		
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		
		System.out.println("Powerplant------>"+powerplant);

		//-----------
		String companyid=powerplant.getServices().get().getCompanyId();
		String serviceId=powerplant.getServices().get().getId();
		String serviceNumber=powerplant.getServices().get().getNumber();
		String Impremarks=powerplant.getServices().get().getNumber()+'-'+ts;
		

		System.out.println("companyid------>"+companyid);
		System.out.println("serviceId------>"+serviceId);

		//-----------
		com.ss.oa.model.master.Company company=companyRepository.findById(companyid);
		
		String flowType="";
		String captiveType="";
		flowType=powerplant.getServices().get().getFlowTypeCode();
		if(flowType!="" ||flowType!=null) {
			
			if(flowType.equals("THIRD-PARTY")) {
				flowType="THIRD-PARTY";
				captiveType="N";
			}
			else if(flowType.equals("IS-CAPTIVE")){
				flowType="IS-CAPTIVE";
				captiveType="Y";
			}
			else if(flowType.equals("STB")){
				flowType="STB";
				captiveType="N";
			}
		}
		
		System.out.println("flowType>"+flowType+"--captiveType>"+captiveType);

		String companyName=powerplant.getCompanyName();
		String eMailId=powerplant.getEmailId();

		company.setName(companyName);
		company.setEmailId(eMailId);
		company.setIsCaptive(captiveType);

		System.out.println("company------>"+company);
		companyRepository.save(company);
		

		Service service = serviceRepository.findById(serviceId).get();
		System.out.println("service------>"+service);

		service.setSubstationId(powerplant.getSubStationId());
		service.setFeederId(powerplant.getFeederId());
		service.setCapacity(powerplant.getTotalCapacity());
		service.setTotalcapacity(powerplant.getTotalCapacity());
		service.setIsRec(powerplant.getServices().get().getIsRec());
		service.setVoltageCode(powerplant.getServices().get().getVoltageCode());
		service.setFlowTypeCode(flowType);
		
		serviceRepository.save(service);
		
		Service Opservice = powerplant.getServices().get();

		for(Meter meters:service.getMeters()) {

			for(Meter opMeters:Opservice.getMeters()) {
				String OpmeterNumber=opMeters.getMeterNumber();
				
				meters.setMeterNumber(OpmeterNumber);
				meters.setPtRatio(opMeters.getPtRatio());
				meters.setCtRatio(opMeters.getCtRatio());
				meters.setAccuracyClassCode(opMeters.getAccuracyClassCode());
				meters.setMeterMakeCode(opMeters.getMeterMakeCode());
				meters.setModemNumber(opMeters.getModemNumber());
				meters.setMultiplicationFactor(opMeters.getMultiplicationFactor());
				meters.setIsAbtMeter(opMeters.getIsAbtMeter());
				
				meterRepository.save(meters);
			}
		}

		for(com.ss.oa.model.master.VtradeRelationship vtrades:powerplant.getTradeRelationships()) {
			ImportTraderelationship imp=new ImportTraderelationship();
		System.out.println(vtrades);
			imp.setId(generateId());
			imp.setSellerCompanyServiceNo(vtrades.getSellerCompServiceNumber());
			imp.setBuyerCompanyServiceNo(vtrades.getBuyerCompServiceNumber());
			imp.setFromDate(vtrades.getFromDate());
			imp.setToDate(vtrades.getToDate());
			imp.setSharePercent("100");
			imp.setImportRemarks(Impremarks);
			importTraderelationships.add(imp);
		}
		
		importTradeService.addImportTrade(importTraderelationships);
		System.out.println(importTraderelationships);

//		Map<String,String> criteria = new HashMap<String,String>();
//		if(Impremarks!=null) criteria.put("rdRemarks",Impremarks);
//		if(serviceNumber!=null) criteria.put("serviceNumber",serviceNumber);
//		criteria.put("rdMonth","01");
//		criteria.put("rdYear","2019");


		
		Map<String,String> delCriteria = new HashMap<String,String>();
		if(Impremarks!=null) delCriteria.put("rdRemarks",Impremarks);
	
		DeleteCommercialsUsingServiceNumber(delCriteria);
//		DeleteTxtUsingServiceNumber(criteria);
		powerplant.setCurrentUpdationStatus("UPDATED");
		return powerplantRepository.save(powerplant);

	}
	
	
//	public String DeleteTxtUsingServiceNumber(Map<String, String> criteria) {
//		
//		rdRemarks = criteria.get("rdRemarks");
//		serviceNumber = criteria.get("serviceNumber");
//		rdMonth = criteria.get("rdMonth");
//		rdYear = criteria.get("rdYear");
//
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.setResultsMapCaseInsensitive(true);
//		functionDeleteTxt = new SimpleJdbcCall(jdbcTemplate).withFunctionName("DELETE_BY_SERVICE_CALL");
//		
//		return CallDeleteTxt(rdRemarks,serviceNumber,rdMonth,rdYear);
//	}
	
//	public String CallDeleteTxt(String rdRemarks, String serviceNumber,String rdMonth,String rdYear) {
//
//		SqlParameterSource in = new MapSqlParameterSource().addValue("I_REMARKS", rdRemarks).addValue("I_SERVICE_NUMBER", serviceNumber)
//				.addValue("I_READING_MONTH", rdMonth).addValue("I_READING_YEAR", rdYear);
//		return functionDeleteTxt.executeFunction(String.class, in);
//	
//	}
	
	
	public String DeleteCommercialsUsingServiceNumber(Map<String, String> criteria) {
		
		rdRemarks = criteria.get("rdRemarks");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		deleteAddCommercials = new SimpleJdbcCall(jdbcTemplate).withFunctionName("DELETE_ADD_COMMERCIALS");
		return CallDeleteAddCommercials(rdRemarks);
	}
	
	public String CallDeleteAddCommercials(String rdRemarks) {

		SqlParameterSource in = new MapSqlParameterSource().addValue("V_REMARKS", rdRemarks);
		return deleteAddCommercials.executeFunction(String.class, in);
	}

	//-----------------------------------------------------

	@CrossOrigin(origins="*")
	@GetMapping("/{id}/_internal")
	public Powerplant getPowerplantByInternal(@PathVariable(value="id")String id)throws OpenAccessException{
		List<Powerplant> powerplants= new ArrayList<Powerplant>();
		powerplants.add(powerplantRepository.findById(id));
		for(Powerplant powerplant:powerplants) {
			powerplant.setTradeRelationships(VtradeRelationshipRepository.findBySellerCompServiceId(powerplant.getServiceId()));
			powerplant.setCompany(companyRepository.findById(powerplant.getCompanyId()));		
		}
		
		return powerplantRepository.findById(id);
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}/print")
	public ResponseEntity<StreamingResponseBody>
  powerplantGeneratorPrintCall(@PathVariable("id") String id)
			throws FileNotFoundException {
		return  commonUtils.fetchFileAsStreamResponse(printPowerplantGenerator(id));

	}
	
	public PrintPayload  printPowerplantGenerator(String id) throws OpenAccessException {
		
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.PowerplantGenerator);
		payload.getFilterCriteria().put("id", id);
		String url=printapi;
		System.out.println("powerplant-generator print call");
		System.out.println(url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}


	@CrossOrigin(origins = "*")
	@PostMapping
	public Powerplant addPowerplant(@RequestBody Powerplant Powerplant) throws OpenAccessException {
		Powerplant.setId(commonUtils.generateId());
		
		return powerplantRepository.save(Powerplant);

	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public Powerplant updatePowerplantById(@RequestBody Powerplant powerplant) throws OpenAccessException {
		return powerplantRepository.save(powerplant);

	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deletePowerplantById(@PathVariable(value = "id") int id) throws OpenAccessException {
		powerplantRepository.deleteById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}/lines")
	public Powerplant addorUpdatePowerPlantLines(@RequestBody Powerplant powerplants)throws OpenAccessException{
		if(powerplants.getGenerators()!=null && !powerplants.getGenerators().isEmpty())
			System.out.println("1");
			for(Generator generator:powerplants.getGenerators() ) {
				System.out.println("2");
			  generator.setId(commonUtils.generateId());
			  generatorRepository.save(generator);
			  
			}
		return powerplants;
		
	}


}
