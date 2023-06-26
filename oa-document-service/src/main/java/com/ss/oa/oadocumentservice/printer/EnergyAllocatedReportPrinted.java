// package com.ss.oa.oadocumentservice.printer;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Locale;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;
// import org.thymeleaf.TemplateEngine;
// import org.thymeleaf.context.Context;

// import com.ss.oa.oadocumentservice.vo.CeeReport;
// import com.ss.oa.oadocumentservice.vo.EnergyAllocatedReport;
// import com.ss.oa.oadocumentservice.vo.ProgressReport;
// import com.ss.oashared.common.CommonUtils;
// import com.ss.oashared.common.OpenAccessException;
// import com.ss.oashared.model.PrintPayload;

// @Service
// public class EnergyAllocatedReportPrinted {

// 	@Value("${file.location}")
// 	private String fileLocation;
	
// 	@Value("${EnergyAllocated-report.url}")
// 	private String dataServiceUrl;
	
// 	@Autowired
// 	CommonUtils commonUtils;
	
// 	@Autowired
// 	private TemplateEngine htmlTemplateEngine;
	
// 	private  final String TEMPLATE_NAME = "EnergyAllocatedReport";
// 	private  final String FILE_EXTENSION = "pdf";
	
// 	public PrintPayload process(PrintPayload pl) {
// 		try {
// 			pl.setDocId(System.currentTimeMillis()+"");
			
// 			pl.setFileExtension(FILE_EXTENSION);
// 			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
// 			pl.setFileNameToUser(pl.getFileName());
// 			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());
			
// 			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

// 		} catch (OpenAccessException oae) {
// 			throw oae;
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		} finally {
			
// 		}

// 		return pl;	
// 	}
// 	public List<EnergyAllocatedReport> fetchReportData(PrintPayload pl) {

// 		Map<String, String> ipCriteria = pl.getFilterCriteria();
// 		String serviceNo = ipCriteria.get("serviceNo");
// 		String month = ipCriteria.get("month");
// 		String year = ipCriteria.get("year");


		 
// 		String url = "?dummy=1";

// 		if (serviceNo != null && !serviceNo.isEmpty()) {
// 			url += "&typeOfShare=" + serviceNo;
// 		}
// 		if (month != null && !month.isEmpty()) {
// 			url += "&month=" + month;
// 		}
// 		if (year != null && !year.isEmpty()) {
// 			url += "&year=" + year;
// 		}


// 		System.out.println("url with crietria" + url);

// 		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, EnergyAllocatedReport[].class));
// 	}

// 	public Context setContext(List<EnergyAllocatedReport> energyAllocatedReport) {
// 		final Context ctx = new Context(Locale.ENGLISH);

		
// 		try {
// 			ctx.setVariable("energyAllocatedReport", energyAllocatedReport);

// 		}catch(Exception e) {
// 			e.printStackTrace();
// 			throw new OpenAccessException(e.getLocalizedMessage());
// 		}
// 		return ctx;
// 	}

// }

