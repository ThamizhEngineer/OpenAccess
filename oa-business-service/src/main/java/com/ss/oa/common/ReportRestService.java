package com.ss.oa.common;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins="*")
@RequestMapping("/report")
@Scope("prototype")
public class ReportRestService {

	public ReportRestService() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
