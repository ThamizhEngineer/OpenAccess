package com.ss.oa.transaction.documentgeneration;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.DocumentGeneration;

@Scope("prototype")
@RestController
public class DocumentGenerationRestService extends TransactionRestService{
	
	@Autowired
	DocumentGenerationService service;
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public void writeDocument(@RequestHeader(required = false) String callingProcName, @RequestBody DocumentGeneration docGen, @RequestHeader(required = false) String outputFormat, 
			@RequestHeader(required = false) String outputFolder) throws IOException{

	
		service.write(callingProcName, docGen, outputFormat, outputFolder);

		
	}
}