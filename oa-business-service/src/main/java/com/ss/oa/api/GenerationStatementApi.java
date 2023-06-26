package com.ss.oa.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.transaction.generationstatement.GenerationStatementRestService;
import com.ss.oa.transaction.vo.GenerationStatement;
@RestController
@RequestMapping("/api/gs")
@Scope("prototype")
public class GenerationStatementApi extends BaseDaoJdbc{
	
	@Autowired
	GenerationStatementRestService generationStatementRestService;
	
	@CrossOrigin(origins="*")
	@GetMapping("/generationstatements")
	public ResponseEntity <List<GenerationStatement>>  getGs(HttpServletRequest request,
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
			@RequestParam(name = "fuelGroupe", required=false) String fuelGroupe,
			@RequestParam(name = "flowTypeCode",required=false) String flowTypeCode){
		return  generationStatementRestService.getGenerationStatements(request, companyName, orgName, companyId, ordId, companyServiceId, companyServiceNumber, allocated, statementMonth, statementYear, isCaptive, fuelTypeCode, fuelTypeName, fuelGroupe,flowTypeCode);
	}
	
	@RequestMapping(value="/generationstatement/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenerationStatement> getGsById(@PathVariable("id")String id)
	{
		return generationStatementRestService.getGenerationStatementById(id);
	}
}
