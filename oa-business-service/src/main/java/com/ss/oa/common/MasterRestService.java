package com.ss.oa.common;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins="*")
@RequestMapping("/master")
@Scope("prototype")
public class MasterRestService{

	public MasterRestService() {
		super();
		
	}	
	

}
