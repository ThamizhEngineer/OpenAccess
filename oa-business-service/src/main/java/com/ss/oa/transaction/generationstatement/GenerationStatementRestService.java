package com.ss.oa.transaction.generationstatement;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ss.oa.common.TransactionRestService;

import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oashared.common.CommonUtils;


@Scope("prototype")
@RestController
public class GenerationStatementRestService extends TransactionRestService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GenerationStatementService service;

	@Autowired
	CommonUtils commonUtils;
	
	@RequestMapping(value = "/generationstatements", method = RequestMethod.GET)
	public ResponseEntity<List<GenerationStatement>> getGenerationStatements(HttpServletRequest request,
			@RequestParam(name = "company-name", required = false) String companyName,
			@RequestParam(name = "edc-name", required = false) String orgName,
			@RequestParam(name = "company-id", required = false) String companyId,
			@RequestParam(name = "edc-id", required = false) String ordId,
			@RequestParam(name = "service-id", required = false) String companyServiceId,
			@RequestParam(name = "service-number", required = false) String companyServiceNumber,
			@RequestParam(name = "allocated", required = false) String allocated,
			@RequestParam(name = "statement-month", required = false) String statementMonth,
			@RequestParam(name = "statement-year", required = false) String statementYear,
			@RequestParam(name = "isCaptive", required = false) String isCaptive,
			@RequestParam(name = "fuelTypeCode", required = false) String fuelTypeCode,
			@RequestParam(name = "fuelTypeName", required = false) String fuelTypeName,
			@RequestParam(name = "fuelGroupe",required=false) String fuelGroupe,
			@RequestParam(name = "flowTypeCode",required=false) String flowTypeCode){

		try {
			Map<String, String> criteria = new HashMap<String, String>();
			if (orgName != null)
				criteria.put("edc_name", orgName);
			if (companyName != null)
				criteria.put("company_name", companyName);
			if (companyId != null)
				criteria.put("company-id", companyId);
			if (ordId != null)
				criteria.put("edc-id", ordId);
			if (companyServiceId != null)
				criteria.put("service-id", companyServiceId);
			if (companyServiceNumber != null)
				criteria.put("service-number", companyServiceNumber);
			if (allocated != null)
				criteria.put("allocated", allocated);
			if (statementMonth != null)
				criteria.put("statement-month", statementMonth);
			if (statementYear != null)
				criteria.put("statement-year", statementYear);
			if (isCaptive != null)
				criteria.put("isCaptive", isCaptive);
			if (fuelTypeCode != null)
				criteria.put("fuelTypeCode", fuelTypeCode);
			if (fuelTypeName != null)
				criteria.put("fuelTypeName", fuelTypeName);
			if (fuelGroupe != null)
				criteria.put("fuelGroupe", fuelGroupe);
			if (flowTypeCode != null)
				criteria.put("flowTypeCode", flowTypeCode);
			return ResponseEntity.ok(service.getAllGenerationStatement(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/buyergenerationstatements", method = RequestMethod.GET)
	public ResponseEntity<List<GenerationStatement>> getBuyerGenerationStatements(HttpServletRequest request,
			@RequestParam(name = "edc-id", required = false) String ordId,
			@RequestParam(name = "service-number", required = false) String companyServiceNumber,
			@RequestParam(name = "statement-month", required = false) String statementMonth,
			@RequestParam(name = "statement-year", required = false) String statementYear,
			@RequestParam(name = "fuelTypeCode", required = false) String fuelTypeCode){

		try {
			Map<String, String> criteria = new HashMap<String, String>();
			if (ordId != null)
				criteria.put("edc-id", ordId);
			if (companyServiceNumber != null)
				criteria.put("service-number", companyServiceNumber);
			if (statementMonth != null)
				criteria.put("statement-month", statementMonth);
			if (statementYear != null)
				criteria.put("statement-year", statementYear);
			if (fuelTypeCode != null)
				criteria.put("fuelTypeCode", fuelTypeCode);
			return ResponseEntity.ok(service.getAllBuyerGenerationStatement(criteria));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value = "/generationstatement/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenerationStatement> getGenerationStatementById(@PathVariable("id") String id) {
		try {
			log.info("Generationstatement-Id"+id);
			return ResponseEntity.ok(service.getGenerationStatementById(id));

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@RequestMapping(value = "/generationstatement", method = RequestMethod.POST)
	public ResponseEntity<String> addGenerationStatement(@RequestBody GenerationStatement generationStatement) {
		String result = "";
		try {
			result = service.addGenerationStatement(generationStatement);
			if (result.matches("FAILURE")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			} else {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {

			e.printStackTrace();
			result = "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}

	}

	@RequestMapping(value = "/generationstatement/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateGenerationStatement(@PathVariable("id") String id,
			@RequestBody GenerationStatement generationStatement) {
		String result = "";
		try {
			result = service.updateGenerationStatement(id, generationStatement);
			if (result.matches("FAILURE")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
			} else {
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAILURE";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}
	}

	

	@RequestMapping(value = "/all-gs-for-company", method = RequestMethod.GET)
	public ResponseEntity<List<GenerationStatement>> getFullGenerationStatementByCompanyName(@RequestBody GenerationStatement generationStatement) {
		try {
			List<GenerationStatement> gsFullList = new ArrayList<GenerationStatement>();
			Map<String, String> criteria = new HashMap<String, String>();

			if (generationStatement.getDispCompanyName() != null)
				criteria.put("company_name", generationStatement.getDispCompanyName());
			List<GenerationStatement> gsList = service.getAllGenerationStatement(criteria);
			System.out.println("gsList.size()-"+gsList.size());
			for (GenerationStatement gs : gsList) {
				gsFullList.add(service.getGenerationStatementById(gs.getId()));
			}
			System.out.println("gsFullList.size()-"+gsFullList.size());
			return ResponseEntity.ok(gsFullList);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@RequestMapping(value = "/generationstatement/{id}/print", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody>  printGenerationStatement(@PathVariable("id") String id)
			throws FileNotFoundException {
		return  commonUtils.fetchFileAsStreamResponse(service.printGenerationStatement(id));

	}
	@RequestMapping(value = "/generationstatement/_internal", method = RequestMethod.POST)
	public ResponseEntity<String> addGenerationStatementInternal(@RequestBody GenerationStatement generationStatement) {
		return addGenerationStatement(generationStatement);
}
	@RequestMapping(value = "/generationstatement/{id}/_internal", method = RequestMethod.GET)
	public ResponseEntity<GenerationStatement> getGenerationStatementByIdInternal(@PathVariable("id") String id) {
			return getGenerationStatementById(id);
		}
	
}
