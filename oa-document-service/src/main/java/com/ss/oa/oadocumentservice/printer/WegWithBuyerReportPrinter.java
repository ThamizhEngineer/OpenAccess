package com.ss.oa.oadocumentservice.printer;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import com.ss.oa.oadocumentservice.vo.WegPowerPlant;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class WegWithBuyerReportPrinter {
	@Value("${file.location}")
	private String fileLocation;

	@Value("${weg-with-buyer-data.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "weg-with-buyer-rpt";
	private  final String FILE_EXTENSION = "pdf";
	
	public PrintPayload process(PrintPayload pl) throws OpenAccessException {

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

	public List<WegPowerPlant> fetchReportData(PrintPayload pl) {
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		System.out.println("ipCriteria");

		System.out.println(ipCriteria);

		String orgId = ipCriteria.get("orgId");
		String capacity = ipCriteria.get("capacity");
		String makeCode = ipCriteria.get("makeCode");
		
		String url = "?dummy=1";
		if (orgId != null && !orgId.isEmpty()) { 
			url += "&orgId=" + orgId;
		}
		if (capacity != null && !capacity.isEmpty()) { 
			url += "&capacity=" + capacity;
		}
		if (makeCode != null && !makeCode.isEmpty()) { 
			url += "&makeCode=" + makeCode;

		}
		System.out.println("url");
		System.out.println(url);


		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, WegPowerPlant[].class));
	}

	public Context setContext(List<WegPowerPlant> wegList) {
		final Context ctx = new Context(Locale.ENGLISH);
		System.out.println("weg wiht buyer");
		System.out.println(wegList);

		try {
			ctx.setVariable("wegList", wegList);

		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}
	
}
