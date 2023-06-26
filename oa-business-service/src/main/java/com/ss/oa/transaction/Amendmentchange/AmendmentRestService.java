package com.ss.oa.transaction.Amendmentchange;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AmendmentRestService {
	@Autowired
	private AmedmentChangeService service;
	@RequestMapping(value="/import-mr-for-meter-change",method=RequestMethod.GET)
	public ResponseEntity<String> importMeterReadingFromMdms(HttpServletRequest request,@RequestParam(name="month",required=false) String month,
            @RequestParam(name="year",required=false) String year
           )
	{
		try {
			Map<String,String> criteria = new HashMap<String,String>();
			if(month!=null) criteria.put("month",month);
			if(year!=null) criteria.put("year",year);
			return ResponseEntity.ok(service.importMeterReadingFromMdms(month,year));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			
		}
	}
	
}
