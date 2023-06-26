package com.ss.oa.master.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.MasterRestService;
import com.ss.oa.master.vo.Code;

@RestController
@Scope("prototype")
public class CodeRestService extends MasterRestService {
	@Autowired
	private CodeService service;
	private static List<Code> code;
	@RequestMapping(value="/codes" , method = RequestMethod.GET)
	public ResponseEntity<List<Code>> getCodes(@RequestParam(name="list-code",required=false) String list_code)
	{
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(list_code!=null) {
				criteria.put("list_code",list_code);
			}
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("list_code")) {
						System.out.println("criteria list_code -->"+element.getValue()+"");
						
					}
				}
				return ResponseEntity.ok(service.getAllCodes(criteria));
			} else {
				
					code=service.getAllCodes(criteria);
					return ResponseEntity.ok(code);				
				
			}
		
		} catch(Exception e) {
				e.printStackTrace();				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	

	}
	@RequestMapping(value="/codes/_internal" , method = RequestMethod.GET)
	public List<Code> getCodesInternal(@RequestParam(name="list-code",required=false) String list_code)
	{
		try {
			Map<String,String> criteria =new HashMap<String,String>();
			if(list_code!=null) {
				criteria.put("list_code",list_code);
			}
			if(!criteria.isEmpty()) {
				for(Entry<String,String> element:criteria.entrySet()) {
					
					if(element.getKey().equals("list_code")) {
						System.out.println("criteria list_code -->"+element.getValue()+"");
						
					}
				}
				return service.getAllCodes(criteria);
			} else {
				
					code=service.getAllCodes(criteria);
								
				
			}
		
		} catch(Exception e) {
				e.printStackTrace();				
				
		}
		return (code);	
	
}
}
