package com.ss.oa.report.genericreportingservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.report.vo.GenericReportOutput;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@Component
@Scope("prototype")
public class GenericReportingService{
	@Value("${print.url}")	private String printUrl;
	@Autowired
	GenericReportingDao genericReportingDao;
	
	public List<GenericReportOutput> getGenericReportResult(String reportName, 
			Map<String, String> ipCriteria){
		return genericReportingDao.getGenericReportResult(reportName, ipCriteria);
	}
	public RestTemplate getTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		messageConverters.add(new MappingJackson2HttpMessageConverter());

		// Add the message converters to the restTemplate
		restTemplate.setMessageConverters(messageConverters); 

		return restTemplate;
	}
	
	public PrintPayload printReport(PrintPayload payload) throws IOException {
		System.out.println(payload);		
		RestTemplate restTemplate = getTemplate();

		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));

		payload  = restTemplate.postForObject(printUrl,request, PrintPayload.class); 

		return payload;
	}
}