package com.ss.oa.oadocumentservice.printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class ConsumerWiseEnergyAdjustmentOrderReport {


	@Value("${docs.folder.delimitter}")
	private String delimitter;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${Consumer-wise-energy-adjustment-order-report.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "Consumer-wise-energy-adjustment-order-report";
//	private  final String FILE_EXTENSION = "pdf";
	
	
	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis()+"");			
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +delimitter+TEMPLATE_NAME+delimitter+ pl.getFileName());
			if(pl.getFileExtension().equalsIgnoreCase("csv")) 
				commonUtils.generateLargeFile(pl,formData(fetchReportData(pl)));
			else
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
		
		System.out.println("url with crietria--"+url);

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, GenericReportOutput[].class));
	}

public Context setContext(List<GenericReportOutput> consWiseEnergy) {
	final Context ctx = new Context(Locale.ENGLISH); 
	Integer totalgen =0;
	Integer totalnet =0;
	Integer totaladj =0;
	try {
		for(int i=0;i<=consWiseEnergy.size()-1;i++) {
			
		    totalgen = commonUtils.toInteger(consWiseEnergy.get(i).getOp6())+ totalgen;
			totalnet = commonUtils.toInteger(consWiseEnergy.get(i).getOp7())+ totalnet;	 
			totaladj = commonUtils.toInteger(consWiseEnergy.get(i).getOp11())+ totaladj;
			
		}
		ctx.setVariable("totaladj", totaladj);
		ctx.setVariable("totalgen", totalgen);
		ctx.setVariable("totalnet", totalnet);
		ctx.setVariable("consWiseEnergy", consWiseEnergy);

	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return ctx;
}

public StringBuilder formData(List<GenericReportOutput> consWiseEnergy) {
	final StringBuilder contentSB = new StringBuilder();	
	
	try { 
		
		LinkedHashSet<String> conServNumSet = new LinkedHashSet<String>(); 
		LinkedHashSet<String> genKeySet = new LinkedHashSet<String>(); 
		HashMap<String, String> adjUnitMap= new HashMap<String, String>();  
		List<String> headRow1 = new ArrayList<String>(Arrays.asList("Month","Year","EdcName","GenServNum","GenCompanyName","ExportGeneration","NetGeneration","EdcName","ServiceNumber","ConsumerName","AdjustedUnits"));
		List<String> headRow = new ArrayList<String>(Arrays.asList("Generation Details","","","","","","","Consumption Details",""));
		
		List<String> conServNumList = new ArrayList<String>();
		List<List> rows =  new ArrayList<List>();
		String cellValue = "";
		for(GenericReportOutput row:consWiseEnergy) {
			System.out.println(row);
		   String genKey="", consNum="";
		   genKey= row.getOp1()+"---"+row.getOp2()+"---"+row.getOp3()+"---"+row.getOp4()+"---"+row.getOp5()+"---"+row.getOp6()+"---"+row.getOp7()+"---"+row.getOp8()+"---"+row.getOp9()+"---"+row.getOp10()+"---"+row.getOp11(); 
		  //consNum = row.getOp11();
		   conServNumSet.add(consNum); 
		   genKeySet.add(genKey); 
		  // adjUnitMap.put(genKey+"---"+consNum, row.getOp11()); 
		
		} 
		conServNumList = new ArrayList<String>(conServNumSet);
		// adding =" to make sure trailing zero's are not skipping at start
		conServNumList.forEach((c)-> headRow.add("=\""+c+"\""));
		//conServNumList.forEach((c)-> headRow1.add("=\""+c+"\""));
		System.out.println(genKeySet);
		for(String genKey : genKeySet) {
			List<String> row = new ArrayList<String>(); 
			System.out.println(genKey);
			
			row.add("\""+genKey.split("---")[0]+"\"");
			row.add("\""+genKey.split("---")[1]+"\"");
			row.add("\""+genKey.split("---")[2]+"\"");
			row.add("=\""+genKey.split("---")[3]+"\""); //genServNum is a number. to avoid truncating zero, add =
			row.add("\""+genKey.split("---")[4]+"\"");
			row.add("\""+genKey.split("---")[5]+"\"");
			row.add("\""+genKey.split("---")[6]+"\"");
			row.add("\""+genKey.split("---")[7]+"\"");
			row.add("\""+genKey.split("---")[8]+"\"");
			row.add("\""+genKey.split("---")[9]+"\"");
			row.add("\""+genKey.split("---")[10]+"\"");
			//row.add("\""+genKey.split("---")[11]+"\"");
			
//			for(String conServNum : conServNumList) { 
//				String units = adjUnitMap.get(genKey+"---"+conServNum) == null ?"0":adjUnitMap.get(genKey+"---"+conServNum);
//				row.add(units);
//			}			
			rows.add(row); 
		}
		System.out.println("rows-"+rows);
		headRow.forEach((head) -> contentSB.append(head+","));
		 // headRow1.forEach((head) -> contentSB.append(head+","));
		// remove last comma(,) from contentSB
		contentSB.setLength(contentSB.length() - 1);
		contentSB.append("\n");
		headRow1.forEach((head) -> contentSB.append(head+","));
		contentSB.append("\n");
		for (List<String> row : rows) {
//			for (int i = 0; i < row.size(); i++) {
//				// add single-quote to genServNum
//				 cellValue = (i==3) ?"=\""+row.get(i)+"\"" :row.get(i); 
//				 contentSB.append(cellValue+",");				
//			} 
			row.forEach((cellVal) -> contentSB.append(cellVal+","));
			// remove last comma(,) from contentSB
			contentSB.setLength(contentSB.length() - 1);
			contentSB.append("\n");
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		throw new OpenAccessException(e.getLocalizedMessage());
	}
	return contentSB;
}

}
