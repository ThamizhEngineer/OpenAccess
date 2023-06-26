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

import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class MasterWindReportPrinter {
	 @Value("${file.location}")
	    private String fileLocation;
	    
	    @Value("${master-wind-report.url}")
	    private String dataServiceUrl;
	    
	    @Autowired
	    CommonUtils commonUtils;

	    @Autowired
	    private TemplateEngine htmlTemplateEngine;
	    
	    private  final String TEMPLATE_NAME = "master-wind";
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

	        
	    
	   System.out.println(dataServiceUrl);
	        return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, GenericReportOutput[].class));
	    }

	public Context setContext(List<GenericReportOutput> masterWind,PrintPayload pl) {
	    final Context ctx = new Context(Locale.ENGLISH);
	    Map<String, String> ipCriteria = pl.getFilterCriteria();
	    String ip2 = ipCriteria.get("ip2");
        String ip3 = ipCriteria.get("ip3");
	    try {
	        System.out.println("in context--");
	        System.out.println("test"+masterWind);

	        ctx.setVariable("masterWind", masterWind);
	        ctx.setVariable("year", ip2);
	        ctx.setVariable("flow", ip3);
	    }catch(Exception e) {
	        e.printStackTrace();
	        throw new OpenAccessException(e.getLocalizedMessage());
	    }
	    return ctx;
	}
}
