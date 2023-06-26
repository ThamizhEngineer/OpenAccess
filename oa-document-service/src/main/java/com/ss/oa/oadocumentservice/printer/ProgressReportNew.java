package com.ss.oa.oadocumentservice.printer;

import java.util.Arrays;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import org.supercsv.cellprocessor.ParseInt;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class ProgressReportNew {

	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${progress-report-new.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	private  final String TEMPLATE_NAME = "progress-report-new";
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
		

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url , GenericReportOutput[].class));
	}

		public Context setContext(List<GenericReportOutput> progressReportNew, Map<String, String> ipCriteria) {
			final Context ctx = new Context(Locale.ENGLISH);
	
				
			try {
				System.out.println("in context--");

				ctx.setVariable("progressReportNew", progressReportNew);
				int totalMeterReading=0;
				int totalThirdParty=0;
				int totalCapacity =0;
				int totalAllotment =0;
				int totalManulaReading =0;
				for(GenericReportOutput i : progressReportNew) {
					totalMeterReading =totalMeterReading + Integer.parseInt(i.getOp4());
					totalThirdParty = totalThirdParty+ Integer.parseInt(i.getOp8());
					totalCapacity = totalCapacity+Integer.parseInt(i.getOp9());
					totalAllotment = totalAllotment+Integer.parseInt(i.getOp10());
					totalManulaReading = totalManulaReading+Integer.parseInt(i.getOp11());
//					System.out.println(sum);
//					System.out.println(i.getOp4());
//					System.out.println(i.getOp8());
//					System.out.println(i.getOp9());
//					System.out.println(i.getOp10());
//					System.out.println(i.getOp11());
				}
//				sum += s;
				System.out.println(totalMeterReading);
				System.out.println(totalThirdParty);
				System.out.println(totalCapacity);
				System.out.println(totalAllotment);
				System.out.println(totalManulaReading);
				ctx.setVariable("totalMeterReading", totalMeterReading);
				ctx.setVariable("totalThirdParty", totalThirdParty);
				ctx.setVariable("totalCapacity", totalCapacity);
				ctx.setVariable("totalAllotment", totalAllotment);
				ctx.setVariable("totalManulaReading", totalManulaReading);
				
				String ip1 = ipCriteria.get("ip1");
				ctx.setVariable("month", ip1);
				System.out.println(ip1);
				String ip2 = ipCriteria.get("ip2");
				ctx.setVariable("year", ip2);
				String ip3 = ipCriteria.get("ip3");
				ctx.setVariable("fuel", ip3);
//				System.out.println(totalMeterReading + ',' + totalThirdParty + ',' + totalCapacity + ',' +  totalAllotment
//						+ ',' + totalManulaReading);
				
				
//				System.out.println(progressReportNew);

			}catch(Exception e) {
				e.printStackTrace();
				throw new OpenAccessException(e.getLocalizedMessage());
			}
			return ctx;
		}


}
