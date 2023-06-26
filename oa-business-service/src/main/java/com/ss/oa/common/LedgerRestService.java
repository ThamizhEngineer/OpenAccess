package com.ss.oa.common;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins="*")
@RequestMapping("/ledger")
@Scope("prototype")
public class LedgerRestService {
	
	public LedgerRestService() {
		super();
	}
}
