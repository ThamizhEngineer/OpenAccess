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

import com.google.gson.Gson;
import com.ss.oa.oadocumentservice.vo.GenerationSummary;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class OrgWiseGenerationSummaryPrinter {

	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${org-wise-gen-summary.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "org-wise-generation-summary";
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
	
public List<GenerationSummary> fetchReportData(PrintPayload pl) {	
		
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String month = ipCriteria.get("month");
		String year = ipCriteria.get("year");
		String flowTypeCode = ipCriteria.get("flowTypeCode");
		String isRec = ipCriteria.get("isRec");
		String orgId = ipCriteria.get("orgId");
		String fuelCode = ipCriteria.get("fuelCode");


		String url = "?dummy=1";
		
		if (month != null && !month.isEmpty()) { 
			url += "&month=" + month;
		}
		if (year != null && !year.isEmpty()) { 
			url += "&year=" + year;
		}
		if (flowTypeCode != null && !flowTypeCode.isEmpty()) { 
			url += "&flowTypeCode=" + flowTypeCode;
		}
		if (isRec != null && !isRec.isEmpty()) { 
			url += "&isRec=" + isRec;
		}
		if (orgId != null && !orgId.isEmpty()) { 
			url += "&orgId=" + orgId;
		}
		if (fuelCode != null && !fuelCode.isEmpty()) { 
			url += "&fuelCode=" + fuelCode;
		}
		
		
		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+url, GenerationSummary[].class));
	}


public Context setContext(List<GenerationSummary> generationSummary) {
	final Context ctx = new Context(Locale.ENGLISH);

	
	try {
		generationSummary.forEach(gs->{
		if(gs.getIsRec().contains("Y")){
			gs.setRec("Rec");
		  }else {
			gs.setRec("Non-Rec");
		  }
		});

		ctx.setVariable("generationSummary", generationSummary);
		ctx.setVariable("totalUni", generationSummary.get(1).getSumTotalUnits());
		ctx.setVariable("totalImp", generationSummary.get(1).getSumTotalImportUnits());
		ctx.setVariable("totalExp", generationSummary.get(1).getSumTotalExportUnits());
		ctx.setVariable("totalCap", generationSummary.get(1).getSumMachineCapacity());
		ctx.setVariable("totalService", generationSummary.get(1).getSumserviceCount());




	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return ctx;
}
}
