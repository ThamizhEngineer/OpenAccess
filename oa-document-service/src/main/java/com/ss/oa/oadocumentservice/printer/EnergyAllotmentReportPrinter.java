package com.ss.oa.oadocumentservice.printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ss.oa.oadocumentservice.vo.EnergyAllocation;

import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EnergyAllotmentReportPrinter {
	@Value("${file.location}")
	private String fileLocation;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "energy-allotment-rpt";
	private  final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) throws OpenAccessException {

		try {
			pl.setDocId(System.currentTimeMillis()+"");
			
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());

			// 1. Fetch Data for Print
			// 2. Form Context to be passed to template
			// 3. Create the HTML body using Thymeleaf with template
			// 4. generate pdf
			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

		} catch (OpenAccessException oae) {
		//	log.error(oae.getMessage());
			throw oae;
		} catch (Exception e) {
		//	log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			
		}

		return pl;
	}
	
	public List<EnergyAllocation> fetchReportData(PrintPayload pl){
		List<EnergyAllocation> EnergyAllocationList = new ArrayList<EnergyAllocation>();
		EnergyAllocation EnergyAllocation1 =  new EnergyAllocation();
		EnergyAllocation1.setCompanyName("SP APPARELS LTD");
		EnergyAllocation1.setCompanyServiceNumber("039204320232");
		EnergyAllocation1.setEdcName("Coimbatore South");
		EnergyAllocation1.setC1("10465");
		EnergyAllocation1.setC2("26472");
		EnergyAllocation1.setC3("3264");
		EnergyAllocation1.setC4("102173");
		EnergyAllocation1.setC5("32046");
		EnergyAllocation1.setTotal("174420");
		EnergyAllocation1.setInjectionVoltageName("33KV");
		EnergyAllocation1.setTranCharges("84500");
		EnergyAllocation1.setOmCharges("40208");
		EnergyAllocation1.setNegativeCharges("0");
		EnergyAllocation1.setSchedulingCharges("141264");
		EnergyAllocationList.add(EnergyAllocation1);
		EnergyAllocation EnergyAllocation2 =  new EnergyAllocation();
		EnergyAllocation2.setCompanyName("SP APPARELS LTD");
		EnergyAllocation2.setCompanyServiceNumber("039204320315");
		EnergyAllocation2.setEdcName("Coimbatore South");
		EnergyAllocation2.setC1("17648");
		EnergyAllocation2.setC2("29008");
		EnergyAllocation2.setC3("5429");
		EnergyAllocation2.setC4("129170");
		EnergyAllocation2.setC5("32661");
		EnergyAllocation2.setTotal("214116");
		EnergyAllocation2.setInjectionVoltageName("33KV");
		EnergyAllocation2.setTranCharges("84500");
		EnergyAllocation2.setOmCharges("40208");
		EnergyAllocation2.setNegativeCharges("0");
		EnergyAllocation2.setSchedulingCharges("242264");
		EnergyAllocationList.add(EnergyAllocation2);
		return EnergyAllocationList;
	}

	public Context setContext(List<EnergyAllocation> energyAllocationList) {
		final Context ctx = new Context(Locale.ENGLISH);
		try {
			ctx.setVariable("energyAllocationList", energyAllocationList);

		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}
	
}
