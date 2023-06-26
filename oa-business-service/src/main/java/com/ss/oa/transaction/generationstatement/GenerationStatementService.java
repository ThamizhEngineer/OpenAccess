package com.ss.oa.transaction.generationstatement;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.ss.oa.common.OpenAccessException;

import com.ss.oa.transaction.documentgeneration.DocumentGenerationService;
import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.GenerationStatementCharge;
import com.ss.oa.transaction.vo.GenerationStatementSlot;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.model.PrintPayload;

@Scope("prototype")
@Component
public class GenerationStatementService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Value("${print.url}")
	private String printUrl;

	@Autowired
	GenerationStatementDao dao;

	@Autowired
	DocumentGenerationService documentGenerationService;
	String generationStatementId = "";
	String generationStatementChargeId = "";
	String generationStatementSlotId = "";

	public GenerationStatementService() {
		super();
	}

	public List<GenerationStatement> getAllGenerationStatement(Map<String, String> criteria) throws Exception {
		try {
			return dao.getAllGenerationStatements(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	public List<GenerationStatement> getAllBuyerGenerationStatement(Map<String, String> criteria) throws Exception {
		try {
			return dao.getAllBuyerGenerationStatements(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}
	
	public GenerationStatement getGenerationStatementById(String id) {
		return dao.getGenerationStatementById(id);
	}

	public String addGenerationStatement(GenerationStatement generationStatement) {

		generationStatementId = dao.addGenerationStatement(generationStatement);
		//System.out.println("generationStatementId" + generationStatementId);
		if (generationStatement.getGenerationStatementCharges() != null) {
			for (GenerationStatementCharge generationStatementCharge : generationStatement
					.getGenerationStatementCharges()) {
				generationStatementCharge.setGenerationStatementId(generationStatementId);
				dao.addGenerationStatementCharge(generationStatementCharge);
			}
		}
		if (generationStatement.getGenerationStatementSlots() != null) {
			for (GenerationStatementSlot generationStatementSlot : generationStatement.getGenerationStatementSlots()) {
				generationStatementSlot.setGenerationStatementId(generationStatementId);
				dao.addGenerationStatementSlot(generationStatementSlot);
			}
		}

		return generationStatementId;
	}

	public String updateGenerationStatement(String id, GenerationStatement generationStatement) {
		generationStatementId = dao.updateGenerationStatement(id, generationStatement);
		if (generationStatement.getGenerationStatementCharges() != null) {
			for (GenerationStatementCharge generationStatementCharge : generationStatement
					.getGenerationStatementCharges()) {
				if (generationStatementCharge.getId() != null
						&& generationStatementCharge.getId().trim().length() > 0) {
					dao.updateGenerationStatementCharge(generationStatementCharge);
				} else {
					generationStatementCharge.setGenerationStatementId(id);
					dao.addGenerationStatementCharge(generationStatementCharge);
				}

			}
		}

		if (generationStatement.getGenerationStatementSlots() != null) {
			for (GenerationStatementSlot generationStatementSlot : generationStatement.getGenerationStatementSlots()) {
				if (generationStatementSlot.getId() != null && generationStatementSlot.getId().trim().length() > 0) {
					dao.updateGenerationStatementSlot(generationStatementSlot);
				} else {
					generationStatementSlot.setGenerationStatementId(id);
					dao.addGenerationStatementSlot(generationStatementSlot);
				}

			}
		}
		return generationStatementId;

	}
 

	
	public PrintPayload  printGenerationStatement(String generationStatementId) throws OpenAccessException {
		
		RestTemplate restTemplate = CommonUtils.getTemplate();
		PrintPayload payload = new PrintPayload();
		payload.setName(PrintPayload.PrintTypes.GenerationStatement);
		payload.getFilterCriteria().put("id", generationStatementId);
		String url=printUrl;
		log.info("Generationstatement-Print-Url-"+url);
		HttpEntity<PrintPayload> request = new HttpEntity<PrintPayload>(payload, CommonUtils.getHttpHeader("", ""));
		payload  = restTemplate.postForObject(url,request, PrintPayload.class); 
		return payload;
	}

}
