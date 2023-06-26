package com.ss.oa.oadocumentservice.printer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.FinancialUnutilBanking;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class FinancialUnutilBankingPrint {
	
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${fin-unutil-banking.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "financial-unutil-banking";
	private  final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis()+"");
			
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());

			// 1. Fetch Data for Print
			// 2. Form Context to be passed to template
			// 3. Create the HTML body using Thymeleaf with template
			// 4. generate pdf
			
			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

		} catch (OpenAccessException oae) {
			throw oae; 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return pl;	
	}
	
	
	public List<FinancialUnutilBanking> fetchReportData(PrintPayload pl) {	
		
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String edcNo = ipCriteria.get("edcNo");
		String month = ipCriteria.get("month");
		String year = ipCriteria.get("year");
		String url = "?dummy=1";
		if (edcNo != null && !edcNo.isEmpty()) { 
			url += "&edcNo=" + edcNo;
		}
		if (month != null && !month.isEmpty()) { 
			url += "&month=" + month;
		}
		if (year != null && !year.isEmpty()) { 
			url += "&year=" + year;
		}
		
//		System.out.println("url with crietria--"+url);
		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+url, FinancialUnutilBanking[].class));
	}
	
	public Context setContext(List<FinancialUnutilBanking> unutilBanking) {
		final Context ctx = new Context(Locale.ENGLISH);

		
		try {
			ctx.setVariable("unutilBanking", unutilBanking);

		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}

}
