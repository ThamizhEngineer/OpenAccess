package com.ss.oa.master.importtraderelationship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.model.master.ImportTraderelationship;

@RestController
@RequestMapping("/master/importTrade")
@Scope("prototype")
public class ImportTradeService extends BaseDaoJdbc{
	
	@Autowired
	ImportTradeRepository impRepo;

	@CrossOrigin(origins = "*")
	@PostMapping("/addImpTrade")
	public List<ImportTraderelationship> addImportTrade(@RequestBody List<ImportTraderelationship> importTraderelationships) {
		return (List<ImportTraderelationship>) impRepo.saveAll(importTraderelationships);
		
	}
}
