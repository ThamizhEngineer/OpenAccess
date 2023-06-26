package com.ss.oa.oadocumentservice.printer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.Vcompany;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class SolarFeederLineLossPrinter {
	 @Value("${file.location}")
	    private String fileLocation;
	    
	    @Value("${solar-feeder-line-loss.url}")
	    private String dataServiceUrl;
	    
	    @Autowired
	    CommonUtils commonUtils;

	    @Autowired
	    private TemplateEngine htmlTemplateEngine;
	    
	    private  final String TEMPLATE_NAME = "solarfeederlineloss";
	    private  final String FILE_EXTENSION = "pdf";
	    
	    
	    public PrintPayload process(PrintPayload pl) {
	        try {
	            pl.setDocId(System.currentTimeMillis()+"");
	            
	            pl.setFileExtension(FILE_EXTENSION);
	            pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
	            pl.setFileNameToUser(pl.getFileName());
	            pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());
	            
	            commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl),pl)));

	        } catch (OpenAccessException oae) {
	            throw oae;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            
	        }

	        return pl;  
	    }
	public List<Vcompany> fetchReportData(PrintPayload pl) { 
	        
	        Map<String, String> ipCriteria = pl.getFilterCriteria();
	        String orgId = ipCriteria.get("orgId");
	        String month = ipCriteria.get("month");
	        String year = ipCriteria.get("year");
	        String url = "";

	        if (orgId != null && !orgId.isEmpty()) { 
	            url += "?orgId=" + orgId;
	        }
	        if (month != null && !month.isEmpty()) { 
	            url += "&month=" + month;
	            }
	        if (year != null && !year.isEmpty()) { 
	            url += "&year=" + year;
	            }
	        
	        System.out.println("url with crietria--"+url);

	        
	    
	   System.out.println(dataServiceUrl);
	        return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, Vcompany[].class));
	    }

	public Context setContext(List<Vcompany> solarfeederlineloss,PrintPayload pl) {
	    final Context ctx = new Context(Locale.ENGLISH);
	    
	    
	    try {
	        System.out.println("in context--");
	        System.out.println("test"+solarfeederlineloss);
	        
	        Map<String, String> ipCriteria = pl.getFilterCriteria();
	        String month = ipCriteria.get("month");
	        String year = ipCriteria.get("year");
	        
	        for(Vcompany solar :solarfeederlineloss) {
	        	if(solar.getSubbulkreading()==null) {
	        		// calculation for exportdifference  in manual entry
	        Integer diff=Integer.parseInt(solar.getTotalExportUnits())-Integer.parseInt(solar.getBulkMeterReading());
	        String totalDiff=diff.toString();
	        	solar.setExportDifference(totalDiff);
	        	
	        	// calculation for lossunit  in manual entry
	        	Double loss=diff/Double.parseDouble(solar.getTotalExportUnits())*100;        	
	        	BigDecimal tempLoss= BigDecimal.valueOf(loss).setScale(3, RoundingMode.HALF_UP);
               	String Lossunit= tempLoss.toString();
               	solar.setLossUnits(Lossunit);
	        	
	        	}
	        	if(solar.getSubbulkreading()!=null) {
	        		// calculation for export difference
	    	        Integer diff=Integer.parseInt(solar.getTotalExportUnits())-Integer.parseInt(solar.getSubbulkreading());
	    	        String totalDiff=diff.toString();
	    	        	solar.setExportDifference(totalDiff);
	    	        
	    	        	// calculation for loss
	    	        	Double loss=diff/Double.parseDouble(solar.getTotalExportUnits())*100;        	
	    	        	BigDecimal tempLoss= BigDecimal.valueOf(loss).setScale(3, RoundingMode.HALF_UP);
	                   	String Lossunit= tempLoss.toString();
	                   	solar.setLossUnits(Lossunit);
	    	        	}
	        }

	        ctx.setVariable("solarfeederlineloss", solarfeederlineloss);
	        ctx.setVariable("month", month);
	        ctx.setVariable("year", year);

	    }catch(Exception e) {
	        e.printStackTrace();
	        throw new OpenAccessException(e.getLocalizedMessage());
	    }
	    return ctx;
	}


}
