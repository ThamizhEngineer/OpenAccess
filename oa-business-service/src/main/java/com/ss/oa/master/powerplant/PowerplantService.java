package com.ss.oa.master.powerplant;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.ss.oa.common.OpenAccessException;

import com.ss.oa.master.vo.Generator;
import com.ss.oa.master.vo.Powerplant;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;
@Component
@Scope("prototype")
public class PowerplantService {
	@Autowired
	PowerplantDao dao;
	
	@Value("${print.url}")
	private String printUrl;
	
	String powerplantId="";
	String generatorId="";
	
	public PowerplantService(){
		super();
	}
	public List<Powerplant> getAllPowerplant(Map<String,String> criteria)
	{
		return dao.getAllPowerplants(criteria);
	} 
	public Powerplant getPowerplantById(String id)
	{
		return dao.getPowerplantById(id);
	}
	public String addPowerplant(Powerplant powerplant)
	{
		powerplantId = dao.addPowerplant(powerplant);
		for(Generator generator:powerplant.getGenerators())
		{			
			generator.setPowerplantId(powerplantId );
			dao.addGenerator(generator);
		}
		return powerplantId;
	}
	public String updatePowerplant(String id,Powerplant powerplant)
	{
		dao.updatePowerplant(id, powerplant);
		for (Generator generator :powerplant.getGenerators()) {
			if(generator.getId()!=null) {
				dao.updateGenerator(generator);
			}else {
				generator.setPowerplantId(id);
				dao.addGenerator(generator);
			}		
			
		}
		return id;
	}
	public String deletePowerplant(String id)
	{
		return dao.deletePowerplant(id);
	}
	
	public PrintPayload  printWegwithBuyer(PrintPayload payload) throws OpenAccessException {
		
		RestTemplate restTemplate = CommonUtils.getTemplate();

		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(printUrl,request, PrintPayload.class); 
		return payload;
	}
}
