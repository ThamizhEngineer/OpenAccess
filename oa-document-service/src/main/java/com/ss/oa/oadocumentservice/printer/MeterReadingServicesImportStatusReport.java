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
public class MeterReadingServicesImportStatusReport {
    
    @Value("${file.location}")
    private String fileLocation;
    
    @Value("${meter-reading-services-status.url}")
    private String dataServiceUrl;
    
    @Autowired
    CommonUtils commonUtils;

    @Autowired
    private TemplateEngine htmlTemplateEngine;
    
    private  final String TEMPLATE_NAME = "monthly-progress-report";
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

        
    
   System.out.println(dataServiceUrl);
        return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+ url, GenericReportOutput[].class));
    }

public Context setContext(List<GenericReportOutput> meterReadingImportReport,PrintPayload pl) {
    final Context ctx = new Context(Locale.ENGLISH);
    
    Map<String, String> ipCriteria = pl.getFilterCriteria();
    String ip2 = ipCriteria.get("ip2");
    String ip3 = ipCriteria.get("ip3");
    String ip4 = ipCriteria.get("ip4");
    
    try {
        System.out.println("in context--");
        System.out.println("test"+meterReadingImportReport);
        ctx.setVariable("month", ip2);
        ctx.setVariable("year", ip3);
        ctx.setVariable("fuel", ip4);
        ctx.setVariable("meterReadingImportReport", meterReadingImportReport);
        
        Integer totalServices=meterReadingImportReport.stream().filter(extt -> extt.getOp3().equals(extt.getOp3())).mapToInt(exett -> Integer.parseInt(exett.getOp3())).sum(); 
        ctx.setVariable("totalServices", totalServices);
        
        Integer totalCaptive=meterReadingImportReport.stream().filter(extt -> extt.getOp4().equals(extt.getOp4())).mapToInt(exett -> Integer.parseInt(exett.getOp4())).sum(); 
        ctx.setVariable("totalCaptive", totalCaptive);
        
        Integer totalThird=meterReadingImportReport.stream().filter(extt -> extt.getOp5().equals(extt.getOp5())).mapToInt(exett -> Integer.parseInt(exett.getOp5())).sum(); 
        ctx.setVariable("totalThird", totalThird);
        
        Integer totalSTB=meterReadingImportReport.stream().filter(extt -> extt.getOp6().equals(extt.getOp6())).mapToInt(exett -> Integer.parseInt(exett.getOp6())).sum(); 
        ctx.setVariable("totalSTB", totalSTB);
        
        Integer totalAmr=meterReadingImportReport.stream().filter(extt -> extt.getOp7().equals(extt.getOp7())).mapToInt(exett -> Integer.parseInt(exett.getOp7())).sum(); 
        ctx.setVariable("totalAmr", totalAmr);
        
        Integer totalCaptiveAMR=meterReadingImportReport.stream().filter(extt -> extt.getOp8().equals(extt.getOp8())).mapToInt(exett -> Integer.parseInt(exett.getOp8())).sum(); 
        ctx.setVariable("totalCaptiveAMR", totalCaptiveAMR);
        
        Integer totalThirdAMR=meterReadingImportReport.stream().filter(extt -> extt.getOp9().equals(extt.getOp9())).mapToInt(exett -> Integer.parseInt(exett.getOp9())).sum(); 
        ctx.setVariable("totalThirdAMR", totalThirdAMR);
        
        Integer totalSTBAMR=meterReadingImportReport.stream().filter(extt -> extt.getOp10().equals(extt.getOp10())).mapToInt(exett -> Integer.parseInt(exett.getOp10())).sum(); 
        ctx.setVariable("totalSTBAMR", totalSTBAMR);
        
        Integer totalManual=meterReadingImportReport.stream().filter(extt -> extt.getOp11().equals(extt.getOp11())).mapToInt(exett -> Integer.parseInt(exett.getOp11())).sum(); 
        ctx.setVariable("totalManual", totalManual);
        
        Integer totalCaptiveMANUAL=meterReadingImportReport.stream().filter(extt -> extt.getOp12().equals(extt.getOp12())).mapToInt(exett -> Integer.parseInt(exett.getOp12())).sum(); 
        ctx.setVariable("totalCaptiveMANUAL", totalCaptiveMANUAL);
        
        Integer totalThirdMANUAL=meterReadingImportReport.stream().filter(extt -> extt.getOp13().equals(extt.getOp13())).mapToInt(exett -> Integer.parseInt(exett.getOp13())).sum(); 
        ctx.setVariable("totalThirdMANUAL", totalThirdMANUAL);
        
        Integer totalSTBMANUAL=meterReadingImportReport.stream().filter(extt -> extt.getOp14().equals(extt.getOp14())).mapToInt(exett -> Integer.parseInt(exett.getOp14())).sum(); 
        ctx.setVariable("totalSTBMANUAL", totalSTBMANUAL);
        
        Integer totalNonread=meterReadingImportReport.stream().filter(extt -> extt.getOp15().equals(extt.getOp15())).mapToInt(exett -> Integer.parseInt(exett.getOp15())).sum(); 
        ctx.setVariable("totalNonread", totalNonread);

        Integer totalCaptiveMNR=meterReadingImportReport.stream().filter(extt -> extt.getOp16().equals(extt.getOp16())).mapToInt(exett -> Integer.parseInt(exett.getOp16())).sum(); 
        ctx.setVariable("totalCaptiveMNR", totalCaptiveMNR);
        
        Integer totalThirdMNR=meterReadingImportReport.stream().filter(extt -> extt.getOp17().equals(extt.getOp17())).mapToInt(exett -> Integer.parseInt(exett.getOp17())).sum(); 
        ctx.setVariable("totalThirdMNR", totalThirdMNR);
        
        Integer totalSTBMNR=meterReadingImportReport.stream().filter(extt -> extt.getOp18().equals(extt.getOp18())).mapToInt(exett -> Integer.parseInt(exett.getOp18())).sum(); 
        ctx.setVariable("totalSTBMNR", totalSTBMNR);
       
        
    }catch(Exception e) {
        e.printStackTrace();
        throw new OpenAccessException(e.getLocalizedMessage());
    }
    return ctx;
}

}

