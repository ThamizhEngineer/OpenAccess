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

import com.ss.oa.oadocumentservice.vo.CeeReport;
import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
@Service
public class CeeReportPrinter {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${cee-report.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "CeeReport";
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

	public List<CeeReport> fetchReportData(PrintPayload pl) {

		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String typeOfShare = ipCriteria.get("typeOfShare");
		String installedBy = ipCriteria.get("installedBy");
		String month = ipCriteria.get("month");
		String year = ipCriteria.get("year");
		String fuelTypeCode = ipCriteria.get("fuelTypeCode");
		String fuelTypeName = ipCriteria.get("fuelTypeName");

		 
		String url = "?dummy=1";

		if (typeOfShare != null && !typeOfShare.isEmpty()) {
			url += "&typeOfShare=" + typeOfShare;
		}
		if (installedBy != null && !installedBy.isEmpty()) {
			url += "&installedBy=" + installedBy;
		}
		if (fuelTypeCode != null && !fuelTypeCode.isEmpty()) {
			url += "&fuelTypeCode=" + fuelTypeCode;
		}
		if (month != null && !month.isEmpty()) {
			url += "&month=" + month;
		}
		if (year != null && !year.isEmpty()) {
			url += "&year=" + year;
		}
		if (fuelTypeName != null && !fuelTypeName.isEmpty()) {
			url += "&fuelTypeName=" + fuelTypeName;
		}

		System.out.println("url with crietria" + url);

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl + url, CeeReport[].class));
	}

		public Context setContext(List<CeeReport> CeeReportlist) {
			final Context ctx = new Context(Locale.ENGLISH);
			
			double totalofnetgenerationPs=0;
			double totaloftotalcapacityPs=0;
			double totalofnetgenerationSs=0;
			double totaloftotalcapacitySs=0;
			double totalofnetgenerationCs=0;
			double totaloftotalcapacityCs=0;
			DecimalFormat f = new DecimalFormat("0.00");
			
				try {
					for(CeeReport ceelist:CeeReportlist) {
						
						
						if(ceelist.getTypeOfShare() == null){
							ceelist.setTypeOfShare("");
							
						}
						
					
					if(ceelist.getTypeOfShare()!=null || ceelist.getTypeOfShare().isEmpty()) {
						
						
						
						if(ceelist.getTypeOfShare().equals("PS")){
							ceelist.setPsNetGenerationSum(ceelist.getNetGenerationSum());
							ceelist.setPsTotalCapacitySum(ceelist.getTotalCapacitySum());	
							
							if(ceelist.getPsNetGenerationSum()!=null) {
								totalofnetgenerationPs = totalofnetgenerationPs + Double.parseDouble(ceelist.getPsNetGenerationSum());
								}
								if(ceelist.getPsTotalCapacitySum() !=null) {
				                totaloftotalcapacityPs = totaloftotalcapacityPs + Double.parseDouble(ceelist.getPsTotalCapacitySum());
								}
							
						
							}
						else{
							ceelist.setPsNetGenerationSum("0");
							ceelist.setPsTotalCapacitySum("0");
							}
						
						if(ceelist.getTypeOfShare().equals("SS")){
							ceelist.setSsNetGenerationSum(ceelist.getNetGenerationSum());	
							ceelist.setSsTotalCapacitySum(ceelist.getTotalCapacitySum());	
							
							if(ceelist.getSsNetGenerationSum()!=null) {
								totalofnetgenerationSs = totalofnetgenerationSs + Double.parseDouble(ceelist.getSsNetGenerationSum());
								}
								if(ceelist.getSsTotalCapacitySum() !=null) {
				                totaloftotalcapacitySs = totaloftotalcapacitySs + Double.parseDouble(ceelist.getSsTotalCapacitySum());
								}
							
							
							
							}
						else{
							ceelist.setSsNetGenerationSum("0");
							ceelist.setSsTotalCapacitySum("0");}
						
						
						if(ceelist.getTypeOfShare().equals("CS")){
							ceelist.setCsNetGenerationSum(ceelist.getNetGenerationSum());
							ceelist.setCsTotalCapacitySum(ceelist.getTotalCapacitySum());	
							
							
							if(ceelist.getCsNetGenerationSum()!=null) {
								totalofnetgenerationCs = totalofnetgenerationCs + Double.parseDouble(ceelist.getCsNetGenerationSum());
								}
								if(ceelist.getCsTotalCapacitySum() !=null) {
				                totaloftotalcapacityCs = totaloftotalcapacityCs + Double.parseDouble(ceelist.getCsTotalCapacitySum());
								}
							
							
							
							}
						else{
							ceelist.setCsNetGenerationSum("0");
							ceelist.setCsTotalCapacitySum("0");}
					}
						}
				}
				
		 
			
					
			
			catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
				
		
			try {
				System.out.println("in context--");
				System.out.println(CeeReportlist);

				ctx.setVariable("totalofnetgenerationPs",f.format(totalofnetgenerationPs));
				ctx.setVariable("totaloftotalcapacityPs",f.format(totaloftotalcapacityPs));
				ctx.setVariable("totalofnetgenerationSs",f.format(totalofnetgenerationSs));
				ctx.setVariable("totaloftotalcapacitySs",f.format(totaloftotalcapacitySs));
				ctx.setVariable("totalofnetgenerationCs",f.format(totalofnetgenerationCs));
				ctx.setVariable("totaloftotalcapacityCs",f.format(totaloftotalcapacityCs));
				ctx.setVariable("CeeReportlist", CeeReportlist);

			}catch(Exception e) {
				e.printStackTrace();
				throw new OpenAccessException(e.getLocalizedMessage());
			}
			return ctx;
		}
}
