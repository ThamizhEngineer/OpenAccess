package com.ss.oa.oadocumentservice.printer;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
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

@Service
public class UnutilisedBankingReport {

	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${Unutilised-banking-report.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "Unutilised-banking-report";
	private  final String FILE_EXTENSION = "pdf";
	
	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis()+"");
			
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());
			
			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl),pl.getFilterCriteria())));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return pl;	
	}
	
public List<GenericReportOutput> fetchReportData(PrintPayload pl) {	
		
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String ip1 = ipCriteria.get("ip1");
		String ip2 = ipCriteria.get("ip2");
		String ip3 = ipCriteria.get("ip3");
		String ip4 = ipCriteria.get("ip4");
		String ip5 = ipCriteria.get("ip5");
		String ip6 = ipCriteria.get("ip6");
		String ip7 = ipCriteria.get("ip7");

		String url = "";

		if (ip1 != null && !ip1.isEmpty()) { 
			url += "&ip1=" + ip1;
		}
		if (ip2 != null && !ip2.isEmpty()) { 
			url += "&ip2=" + ip2;
			}
		if (ip3 != null && !ip3.isEmpty()) { 
			url += "&ip3=" + ip3;
			}
		if (ip4 != null && !ip4.isEmpty()) { 
			url += "&ip4=" + ip4;
			}
		if (ip5 != null && !ip5.isEmpty()) { 
			url += "&ip5=" + ip5;
			}
		if (ip6 != null && !ip6.isEmpty()) { 
			url += "&ip6=" + ip6;
			}
		if (ip7 != null && !ip7.isEmpty()) { 
			url += "&ip7=" + ip7;
			}
		
		System.out.println("url with crietria--"+url);

		
	

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, GenericReportOutput[].class));
	}

public Context setContext(List<GenericReportOutput> unutilisedBanking, Map<String, String> ipCriteria) {
	final Context ctx = new Context(Locale.ENGLISH);
	
	
	try {
		System.out.println("in context--");
		System.out.println(unutilisedBanking);
		
		ctx.setVariable("unutilisedBanking", unutilisedBanking);
    	double totalUnutilisedEneregy=0;
		double totalPowerPurchasecostPayable=0;
		double totalop5=0;
		double totalop6=0;
		
		LocalDate localDate = null;
		
		DecimalFormat f = new DecimalFormat("0.00");
		for(GenericReportOutput row:unutilisedBanking) {
			String result = row.getOp4().substring(0, row.getOp4().length() - 11);
			localDate = LocalDate.parse(result);
			String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	        System.out.println(formattedDate);
			row.setOp4(formattedDate);
			
			totalUnutilisedEneregy = totalUnutilisedEneregy + Double.parseDouble(row.getOp7());
			totalPowerPurchasecostPayable =totalPowerPurchasecostPayable + Double.parseDouble(row.getOp8());
			totalop5=totalop5+ Double.parseDouble(row.getOp5());
			totalop6=totalop6+Double.parseDouble(row.getOp6());
			
		}
//		ctx.setVariable("unutilisedBanking", unutilisedBanking);
		System.out.println(f.format(totalUnutilisedEneregy));
		System.out.println(f.format(totalPowerPurchasecostPayable));
		
		ctx.setVariable("totalUnutilisedEneregy",f.format(totalUnutilisedEneregy));
		ctx.setVariable("totalPowerPurchasecostPayable", f.format(totalPowerPurchasecostPayable));
		ctx.setVariable("totalop5",f.format(totalop5));
		ctx.setVariable("totalop6",f.format(totalop6));
		
		

	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return ctx;
}

}
