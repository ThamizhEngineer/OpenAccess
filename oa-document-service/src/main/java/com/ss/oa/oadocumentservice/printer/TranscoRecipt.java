package com.ss.oa.oadocumentservice.printer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
import com.ss.oashared.model.TranscoInvoiceRecipt;


@Service
public class TranscoRecipt {
	@Value("${file.location}")
	private String fileLocation;
	

	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "TranscoRecipt";
	private  final String FILE_EXTENSION = "pdf";
	
	public TranscoInvoiceRecipt process(TranscoInvoiceRecipt transcoInvoiceRecipt) {
		try {
			
			transcoInvoiceRecipt.setDocId(System.currentTimeMillis()+"");
			
			transcoInvoiceRecipt.setFileExtension(FILE_EXTENSION);
			transcoInvoiceRecipt.setFileName(TEMPLATE_NAME + transcoInvoiceRecipt.getDocId()+"."+transcoInvoiceRecipt.getFileExtension());
			transcoInvoiceRecipt.setFileNameToUser(transcoInvoiceRecipt.getFileName());
			transcoInvoiceRecipt.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ transcoInvoiceRecipt.getFileName());
			
			
			commonUtils.generatePdfforRecipt(transcoInvoiceRecipt, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(transcoInvoiceRecipt)));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return transcoInvoiceRecipt;	
	}


public Context setContext(TranscoInvoiceRecipt transcoInvoiceRecipt) {
	final Context ctx = new Context(Locale.ENGLISH);
	
	
	try {
		System.out.println("in context--");
		System.out.println("test"+transcoInvoiceRecipt);
		transcoInvoiceRecipt.setAmountPaid(transcoInvoiceRecipt.getAmountPaid()+" /-");
        ctx.setVariable("Date", " /"+LocalDate.now());
		ctx.setVariable("transcoInvoiceRecipt",transcoInvoiceRecipt);

	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return ctx;
}

}
