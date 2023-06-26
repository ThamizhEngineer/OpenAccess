package com.ss.oa.oadocumentservice.printer;

import java.text.DecimalFormat;
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

@Service
public class TnercEnergySummaryReportPrint {

	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${tnerc-energy-summary-report.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "tnerc-energy-summary-report";
	private  final String FILE_EXTENSION = "pdf";
	
	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis()+"");
			
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());
			
			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

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
		
		System.out.println("url with crietria--"+url);

		
	

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, GenericReportOutput[].class));
	}

public Context setContext(List<GenericReportOutput> tnercEnergySummaryReport) {
	final Context ctx = new Context(Locale.ENGLISH);
	
	
	try {
		System.out.println("in context--");
		System.out.println("test"+tnercEnergySummaryReport);

		ctx.setVariable("tnercEnergySummaryReport", tnercEnergySummaryReport);
		double totalop3=0;
		double totalop4=0;
		double totalop5=0;
		double totalop6=0;
		double totalop7=0;
		double totalop8=0;
		double totalop9=0;
		double totalop10=0;
		double totalop11=0;
		double totalop12=0;
		double totalop13=0;
		DecimalFormat f = new DecimalFormat("0.00");
		for(GenericReportOutput row:tnercEnergySummaryReport) {
			
			
			totalop3 = totalop3 + Double.parseDouble(row.getOp3());
			totalop4 = totalop4 + Double.parseDouble(row.getOp4());
			totalop5 = totalop5 + Double.parseDouble(row.getOp5());
			totalop6 = totalop6 + Double.parseDouble(row.getOp6());
			totalop7 = totalop7 + Double.parseDouble(row.getOp7());
			totalop8 = totalop8 + Double.parseDouble(row.getOp8());
			totalop9 = totalop9 + Double.parseDouble(row.getOp9());
			totalop10 = totalop10 + Double.parseDouble(row.getOp10());
			totalop11 = totalop11 + Double.parseDouble(row.getOp11());
			totalop12= totalop12 + Double.parseDouble(row.getOp12());
			totalop13 = totalop13 + Double.parseDouble(row.getOp13());
			
			
			
			
		}
		
		ctx.setVariable("totalop3",f.format(totalop3));
		ctx.setVariable("totalop4",f.format(totalop4));
		ctx.setVariable("totalop5",f.format(totalop5));
		ctx.setVariable("totalop6",f.format(totalop6));
		ctx.setVariable("totalop7",f.format(totalop7));
		ctx.setVariable("totalop8",f.format(totalop8));
		ctx.setVariable("totalop9",f.format(totalop9));
		ctx.setVariable("totalop10",f.format(totalop10));
		ctx.setVariable("totalop11",f.format(totalop11));
		ctx.setVariable("totalop12",f.format(totalop12));
		ctx.setVariable("totalop13",f.format(totalop13));
		
		
	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return ctx;
}
}
