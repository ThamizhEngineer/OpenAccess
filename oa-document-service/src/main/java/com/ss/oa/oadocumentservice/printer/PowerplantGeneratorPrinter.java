package com.ss.oa.oadocumentservice.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.Company;
import com.ss.oa.oadocumentservice.vo.Generator;
import com.ss.oa.oadocumentservice.vo.GenericReportOutput;
import com.ss.oa.oadocumentservice.vo.Meter;
import com.ss.oa.oadocumentservice.vo.Powerplant;
import com.ss.oa.oadocumentservice.vo.VtradeRelationship;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

@Service
public class PowerplantGeneratorPrinter {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${my-powerplant-gen.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private final String TEMPLATE_NAME = "powerplant-generator";
	private final String FILE_EXTENSION = "pdf";
	
	
	
	public PrintPayload process(PrintPayload pl) throws OpenAccessException {
		System.out.println("process for ppprint");

			System.out.println(pl);
		try {
			pl.setDocId(System.currentTimeMillis() + "");

			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId() + "." + pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation + "/" + TEMPLATE_NAME + "/" + pl.getFileName());

			// 1. Fetch Data for Print
			// 2. Form Context to be passed to template
			// 3. Create the HTML body using Thymeleaf with template
			// 4. generate pdf
			System.out.println("after set process pp");

			System.out.println(pl);

			commonUtils.generatePdf(pl,
					this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return pl;
	}
	
	public Powerplant fetchReportData(PrintPayload pl){
		Powerplant pp = null;
		
		System.out.println("print pp fetch report");
		System.out.println(pl);
		try {
			
			String url=dataServiceUrl+pl.getFilterCriteria().get("id")+"/_internal";
			System.out.println("url fetch report");
			System.out.println(url);
			log.info("url to get my-powerplant from oa service");
			log.info(url);
			pp = CommonUtils.getTemplate().getForObject(url, Powerplant.class);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return pp;
	}
	
	public Context setContext(Powerplant powerPlant) {
		final Context ctx = new Context(Locale.ENGLISH);
		System.out.println("powerPlant");
		System.out.println(powerPlant);
		
		List<VtradeRelationship> tradeRelationships=new ArrayList<VtradeRelationship>();
		tradeRelationships= powerPlant.getTradeRelationships();
		
		List<Generator> generatorList=new ArrayList<Generator>();
		generatorList.addAll(powerPlant.getGenerators());
		
		List<Company> compList=new ArrayList<Company>();
		compList.add(powerPlant.getCompany());
		ctx.setVariable("company", compList.get(0));
		String captiveStatus="";

		for(Company com:compList) {
			
			if(com.getIsCaptive().equals("Y")) {
				captiveStatus="Captive";
			}else {
				captiveStatus="STB";

			}
				
		}
		ctx.setVariable("captiveStatus", captiveStatus);

		System.out.println(compList);
		
		List<com.ss.oa.oadocumentservice.vo.Service>servicesList=new ArrayList<com.ss.oa.oadocumentservice.vo.Service>();
		for(Company company:compList) {
			servicesList.addAll(company.getServices());
			ctx.setVariable("service", servicesList.get(0));

			System.out.println(servicesList);
		}
		List<Meter> meterList=new ArrayList<Meter>();
	
		for(com.ss.oa.oadocumentservice.vo.Service service:servicesList) {
			meterList.addAll(service.getMeters());
			ctx.setVariable("meter", meterList.get(0));
		}

		
		try {
			ctx.setVariable("orgId", powerPlant.getOrgId());
			ctx.setVariable("powerPlant", powerPlant);
			ctx.setVariable("tradeRelationships", tradeRelationships);
			ctx.setVariable("generatorList", generatorList);
			
			ctx.setVariable("subStationName", powerPlant.getSubStationName());
			ctx.setVariable("totalCapacity", powerPlant.getTotalCapacity());
			ctx.setVariable("commissionDate", powerPlant.getCommissionDate());


			


		} catch (Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}

}
