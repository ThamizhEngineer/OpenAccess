package com.ss.oa.ledger.energysaleorder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.ledger.vo.EnergyCharge;
import com.ss.oa.ledger.vo.EnergySaleOrder;
import com.ss.oa.ledger.vo.EnergySaleOrderLine;
import com.ss.oa.transaction.documentgeneration.DocumentGenerationService;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@Component
@Scope("prototype")
public class EnergySaleOrderService {
	@Value("${print.url}")
	private String printUrl;
	
	@Autowired
	EnergySaleOrderDao dao;
	
	@Autowired
	DocumentGenerationService documentGenerationService;
	String energySaleOrderId="";
	String energySaleOrderLinesId="";
	String energyChargeId="";
	
	
	public EnergySaleOrderService() {
		super();
	}
	public List<EnergySaleOrder> getAllEnergySaleOrders(Map<String, String> criteria) {
	
		return dao.getAllEnergySaleOrders(criteria);
	}
	
	public List<EnergySaleOrder> getAllEnergySaleOrdersBuyers(Map<String, String> criteria) {
		
		return dao.getAllEnergySaleOrdersBuyers(criteria);
	}
	 
	public EnergySaleOrder getEnergySaleOrderById(String id) {
	
		return dao.getEnergySaleOrderById(id);
	}
	

	public String addEnergySaleOrder(EnergySaleOrder energySaleOrder) {
		System.out.println("In add esorder");
		energySaleOrderId = dao.addEnergySaleOrder(energySaleOrder);
		if(energySaleOrder.getEnergySaleOrderLines()!=null) {
			System.out.println("In add esorder lines");
			for(EnergySaleOrderLine energySaleOrderLines:energySaleOrder.getEnergySaleOrderLines()) {
				energySaleOrderLines.setEnergySaleOrderId(energySaleOrderId);
				System.out.println("In add esorder lines for loop");
				energySaleOrderLinesId=dao.addEnergySaleOrderLine(energySaleOrderLines);
			}
		}
		
		if(energySaleOrder.getEnergyCharge()!=null) {
			for(EnergyCharge energyCharges:energySaleOrder.getEnergyCharge())
			{
				energyCharges.setEnergySaleOrderId(energySaleOrderId);
				energyChargeId = dao.addEnergyCharge(energyCharges);
			}
		}
				
		return energySaleOrderId;
	}


	public String updateEnergySaleOrder(String id, EnergySaleOrder energySaleOrder) {
		energySaleOrderId = dao.updateEnergySaleOrder(id, energySaleOrder);
		if(energySaleOrder.getEnergySaleOrderLines()!=null) {
			for(EnergySaleOrderLine energySaleOrderLines:energySaleOrder.getEnergySaleOrderLines()) {
				if(energySaleOrderLines.getId()!=null && energySaleOrderLines.getId().trim().length()>0) {
					energySaleOrderLinesId = dao.updateEnergySaleOrderLine(energySaleOrderLines);
				}else {
					energySaleOrderLines.setEnergySaleOrderId(energySaleOrderId);
					energySaleOrderLinesId = dao.addEnergySaleOrderLine(energySaleOrderLines);
				}
				
			}
			
		}
		if(energySaleOrder.getEnergyCharge()!=null) {
			for(EnergyCharge energyCharges:energySaleOrder.getEnergyCharge()) {
				if(energyCharges.getId()!=null && energyCharges.getId().trim().length()>0) {
					energyChargeId = dao.updateEnergyCharge(energyCharges);
				}else {
					energyCharges.setEnergySaleOrderId(energySaleOrderId);
					energyChargeId = dao.addEnergyCharge(energyCharges);
				}
				
			}
			
		}
		return energySaleOrderId;
	}

   public PrintPayload  printEnergyAllotmentOrderReport(String energySaleOrderId) throws OpenAccessException {
		
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.EnergyAllotmentOrderReport);
		payload.getFilterCriteria().put("id", energySaleOrderId);
		String url=printUrl;
		System.out.println("Printer Caller");
		System.out.println(url);
		System.out.println(energySaleOrderId);
	    HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
	    payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}


}
