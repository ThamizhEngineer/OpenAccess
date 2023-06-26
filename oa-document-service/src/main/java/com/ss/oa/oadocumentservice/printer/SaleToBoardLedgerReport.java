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
public class SaleToBoardLedgerReport {

	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${sale-to-board-ledger-report.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "Sale-To-Board-Ledger-Report";
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

		public Context setContext(List<GenericReportOutput> SaleToBoardLedgerList) {
			final Context ctx = new Context(Locale.ENGLISH);
	
				
			try {
				System.out.println("in context--");

				ctx.setVariable("SaleToBoardLedgerList", SaleToBoardLedgerList);
				double totalop4=0;
				double totalop5=0;
//				double totalop6=0;
//				double totalop7=0;
//				double totalop8=0;
//				double totalop9=0;
				double totalop10=0;
				double totalop11=0;
				double totalop12=0;
				double totalop13=0;
				double totalop14=0;
				double totalop15=0;
//				double totalop16=0;
//				double totalop17=0;
//				double totalop18=0;
//				double totalop19=0;
//				
//				double totalop20=0;
				double totalop21=0;
				double totalop22=0;
				double totalop23=0;
				double totalop24=0;
				double totalop25=0;
				double totalop26=0;
				double totalop27=0;
				DecimalFormat f = new DecimalFormat("0.00");
				
				
				for(GenericReportOutput row:SaleToBoardLedgerList) {
					
				
					
					if(row.getOp4()!=null) {
					totalop4 = totalop4 + Double.parseDouble(row.getOp4());
					}
					if(row.getOp5()!=null) {
					totalop5 = totalop5 + Double.parseDouble(row.getOp5());
					}
//					if(row.getOp6()!=null) {
////					totalop6 = totalop6 + Double.parseDouble(row.getOp6());
//					}
//					if(row.getOp7()!=null) {
////					totalop7 = totalop7 + Double.parseDouble(row.getOp7());
//					}
//					if(row.getOp8()!=null) {
////					totalop8 = totalop8 + Double.parseDouble(row.getOp8());
//					}
//					if(row.getOp9()!=null) {
////					totalop9 = totalop9 + Double.parseDouble(row.getOp9());
//					}
					if(row.getOp10()!=null) {
					totalop10 = totalop10 + Double.parseDouble(row.getOp10());
					}
					if(row.getOp11()!=null) {
					totalop11 = totalop11 + Double.parseDouble(row.getOp11());
					}
					if(row.getOp12()!=null) {
					totalop12= totalop12 + Double.parseDouble(row.getOp12());
					}
					if(row.getOp13()!=null) {
					totalop13 = totalop13 + Double.parseDouble(row.getOp13());
					}
					if(row.getOp14()!=null) {
					totalop14 = totalop14 + Double.parseDouble(row.getOp14());
					}
					if(row.getOp15()!=null) {
					totalop15 = totalop15 + Double.parseDouble(row.getOp15());
					}
//					totalop16 = totalop16 + Double.parseDouble(row.getOp16());
//					totalop17 = totalop17 + Double.parseDouble(row.getOp17());
//					totalop18 = totalop18 + Double.parseDouble(row.getOp18());
//					totalop19 = totalop19 + Double.parseDouble(row.getOp19());
//					totalop20 = totalop20 + Double.parseDouble(row.getOp20());
					if(row.getOp21()!=null) {
					totalop21 = totalop21 + Double.parseDouble(row.getOp21());
					}
					if(row.getOp22()!=null) {
					totalop22=  totalop22 + Double.parseDouble(row.getOp22());
					}
					if(row.getOp23()!=null) {
					totalop23 = totalop23 + Double.parseDouble(row.getOp23());
					}
					if(row.getOp24()!=null) {
					totalop24 = totalop24 + Double.parseDouble(row.getOp24());
					}
					if(row.getOp25()!=null) {
					totalop25 = totalop25 + Double.parseDouble(row.getOp25());
					}
					if(row.getOp26()!=null) {
					totalop26 = totalop26 + Double.parseDouble(row.getOp26());
					}
					if(row.getOp27()!=null) {
					totalop27 = totalop27 + Double.parseDouble(row.getOp27());
					}
				}
				ctx.setVariable("totalop4",f.format(totalop4));
				ctx.setVariable("totalop5",f.format(totalop5));
//				ctx.setVariable("totalop6",f.format(totalop6));
//				ctx.setVariable("totalop7",f.format(totalop7));
//				ctx.setVariable("totalop8",f.format(totalop8));
//				ctx.setVariable("totalop9",f.format(totalop9));
				ctx.setVariable("totalop10",f.format(totalop10));
				ctx.setVariable("totalop11",f.format(totalop11));
				ctx.setVariable("totalop12",f.format(totalop12));
				ctx.setVariable("totalop13",f.format(totalop13));
				
				ctx.setVariable("totalop14",f.format(totalop14));
				ctx.setVariable("totalop15",f.format(totalop15));
//				ctx.setVariable("totalop16",f.format(totalop16));
//				ctx.setVariable("totalop17",f.format(totalop17));
//				ctx.setVariable("totalop18",f.format(totalop18));
//				ctx.setVariable("totalop19",f.format(totalop19));
//				ctx.setVariable("totalop20",f.format(totalop20));
				ctx.setVariable("totalop21",f.format(totalop21));
				ctx.setVariable("totalop22",f.format(totalop22));
				ctx.setVariable("totalop23",f.format(totalop23));
				
				ctx.setVariable("totalop24",f.format(totalop24));
				ctx.setVariable("totalop25",f.format(totalop25));
				ctx.setVariable("totalop26",f.format(totalop26));
				ctx.setVariable("totalop27",f.format(totalop27));
				
				}catch(Exception e) {
				e.printStackTrace();
				throw new OpenAccessException(e.getLocalizedMessage());
			}
			return ctx;
		}

}
