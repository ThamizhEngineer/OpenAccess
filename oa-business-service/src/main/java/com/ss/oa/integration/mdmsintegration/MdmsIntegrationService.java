package com.ss.oa.integration.mdmsintegration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.integration.mdmsintegration.vo.MdmsIntegration;

@Component
@Scope("prototype")
public class MdmsIntegrationService {
	@Value("${integrationservice.endpoint.baseurl}")     private String serviceBaseUrl;
	
	public MdmsIntegrationService() {
		super();
	}
	
	public List<MdmsIntegration> importAllMr(){
		RestTemplate restTemplate = getTemplate();
		System.out.println(serviceBaseUrl);
		//List<MdmsIntegration> mdmsIntegrations=Arrays.asList(restTemplate.getForObject("http://35.185.177.10/oa-integration-service/integration-api/slotwisemeterreadings", MdmsIntegration[].class));
		List<MdmsIntegration> mdmsIntegrations=Arrays.asList(restTemplate.getForObject(serviceBaseUrl+"/slotwisemeterreadings", MdmsIntegration[].class));
//		System.out.println(mdmsIntegrations);
		return mdmsIntegrations;
	}
	public List<MdmsIntegration> importMrByMonthYear(String month,String year) {
		System.out.println(month);
		System.out.println(year);

		RestTemplate restTemplate = getTemplate();
		//MdmsIntegration mdmsIntegration =  restTemplate.getForObject("http://localhost:4208/oa-integration-service/integration-api/slotwisemeterreadings?month="+month+"&year="+year, MdmsIntegration.class);
		//MdmsIntegration mdmsIntegration =  restTemplate.getForObject(serviceBaseUrl+"/slotwisemeterreadings?month="+month+"&year="+year, MdmsIntegration.class);
		System.out.println("url accesed-"+serviceBaseUrl+"/slotwisemeterreadings?month="+month+"&year="+year);
		List<MdmsIntegration> mdmsIntegration=Arrays.asList(restTemplate.getForObject(serviceBaseUrl+"/slotwisemeterreadings?month="+month+"&year="+year, MdmsIntegration[].class));

//		System.out.println(mdmsIntegration);
		return mdmsIntegration;
	}
	public RestTemplate getTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		//Add the Jackson Message converter
		messageConverters.add(new MappingJackson2HttpMessageConverter());

		//Add the message converters to the restTemplate
		restTemplate.setMessageConverters(messageConverters); 

		return restTemplate;
	}

}
