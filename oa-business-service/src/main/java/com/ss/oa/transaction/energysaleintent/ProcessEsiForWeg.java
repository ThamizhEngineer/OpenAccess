package com.ss.oa.transaction.energysaleintent;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.transaction.vo.EnergySaleIntent;
import com.ss.oa.transaction.vo.EnergySaleIntentLine;
import com.ss.oa.transaction.vo.ProcessLog;

@Scope("prototype")
@Component
public class ProcessEsiForWeg {

	
	
	public ProcessEsiForWeg() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	EnergySaleIntentService energySaleIntentService;

	@Autowired
	EsiProcessHelper esiProcessHelper;
	
	@Autowired
	TradeRelationshipService tradeRelationshipService;
	
	@Resource
	private DataSource dataSource;

	private SimpleJdbcCall functionTrsFromEwa;
	String energySaleIntentId="";
	
	String ipaaId="";
	
	String nocGeneratorId="";
	
	String ewaId=""; 
	
	String nocId ="";
	
	String consentId="";
	
	String ooaId="";
	

	public String processEnergySaleIntentForWeg(EnergySaleIntent energySaleIntent) {

		energySaleIntentId = energySaleIntent.getId();
		ProcessLog  processLog = new ProcessLog();
		processLog.settEnergySaleId(energySaleIntentId);	
		processLog.setGenCompId(energySaleIntent.getSellerCompanyId());
		processLog.setGenServiceId(energySaleIntent.getSellerCompanyServiceId());
		processLog.setGenEdcId(energySaleIntent.getSellerEndOrgId());
		if (energySaleIntent.getFlowTypeCode().equals("WEG")) {
			
			if(energySaleIntent.getIpaaId()==null || energySaleIntent.getIpaaId().isEmpty()) {
				ipaaId = esiProcessHelper.addIpaa(energySaleIntent);
				System.out.println("ipaaId"+ipaaId);
				energySaleIntent.setIpaaId(ipaaId);
				energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
				processLog.setProcessName("IPAA");
				processLog.setProcessStatus("CREATED");
				processLog.setProcessId(ipaaId);
				esiProcessHelper.addProcessLog(processLog);
			}else if( energySaleIntent.getIpaaId()!=null&& (energySaleIntent.getNocGeneratorId()==null || energySaleIntent.getNocGeneratorId().isEmpty() ) ) {
				if(energySaleIntent.getIpaaStatusCode()!=null&& energySaleIntent.getIpaaStatusCode().equals("COMPLETED")) {
					nocGeneratorId = esiProcessHelper.addNocGeneratorForWeg(energySaleIntent);
					System.out.println("nocGeneratorId"+nocGeneratorId);
					energySaleIntent.setNocGeneratorId(nocGeneratorId);
					energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
					processLog.setProcessName("NOC-GENERATOR");
					processLog.setProcessStatus("CREATED");
					processLog.setProcessId(nocGeneratorId);
					esiProcessHelper.addProcessLog(processLog);
				}
			} else if(energySaleIntent.getNocGeneratorId()!=null && (energySaleIntent.getEwaId()==null || energySaleIntent.getEwaId().isEmpty()) ) {
				if(energySaleIntent.getNocGeneratorStatusCode()!=null&& energySaleIntent.getNocGeneratorStatusCode().equals("COMPLETED")) {
					
				ewaId = esiProcessHelper.addEwaForWeg(energySaleIntent)	;
				System.out.println("ewaId"+ewaId);
				energySaleIntent.setEwaId(ewaId);
				energySaleIntentService.updateEnergySaleIntent(energySaleIntentId, energySaleIntent);
				processLog.setProcessName("EWA");
				processLog.setProcessStatus("CREATED");
				processLog.setProcessId(ewaId);
				esiProcessHelper.addProcessLog(processLog);
				}
			}

			if(energySaleIntent.getEwaStatusCode()!=null && energySaleIntent.getEwaStatusCode().equals("COMPLETED") ) {
			
				if (energySaleIntent.getEnergySaleIntentLines() != null) {
					for (EnergySaleIntentLine energySaleIntentLine : energySaleIntent.getEnergySaleIntentLines()) {
						if (energySaleIntentLine.getId() != null) {	
							String completeEsiResult="";
			
							if (energySaleIntent.getEwaId()!=null && (energySaleIntentLine.getTradeRelationshipId() == null || energySaleIntentLine.getNocId().isEmpty())) {
							
								JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
								jdbcTemplate.setResultsMapCaseInsensitive(true);
								functionTrsFromEwa = new SimpleJdbcCall(jdbcTemplate).withFunctionName("complete_esi");

								SqlParameterSource in = new MapSqlParameterSource().addValue("v_es_intent_id", energySaleIntent.getId());
								completeEsiResult=functionTrsFromEwa.executeFunction(String.class, in);
								System.out.println(completeEsiResult);
								processLog.setProcessName("ENERGY-SALE-INTENT");
								processLog.setProcessStatus("COMPLETED");
								esiProcessHelper.addProcessLog(processLog);
								
							}				
						
							
						
		
					}
							
						}
					}
			}
			
	
		}
		
	
		return energySaleIntentId;
	}

}
