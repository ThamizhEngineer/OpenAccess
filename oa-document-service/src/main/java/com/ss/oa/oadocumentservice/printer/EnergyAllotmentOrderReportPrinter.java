package com.ss.oa.oadocumentservice.printer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ss.oa.oadocumentservice.vo.EnergyAllotmentOrder;
import com.ss.oa.oadocumentservice.vo.EnergyAllotmentOrderLine;
import com.ss.oa.oadocumentservice.vo.EsChargeForPrint;
import com.ss.oa.oadocumentservice.vo.GenerationStatementSlot;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.ExcessUnit;
import com.ss.oashared.model.PrintPayload;

@Service
public class EnergyAllotmentOrderReportPrinter<EnergyCharge> {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Value("${file.location}")
	private String fileLocation;
	 
	@Value("${energy-allotment-order-data.url}")
	private String dataServiceUrl;
	
	@Value("${banking-balance-data.url}")
	private String bankingServiceUrl;
	
	@Value("${energy-allotment-order-data-banking.url}")
	private String dataServiceUrlForBanking;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;

	private  final String TEMPLATE_NAME = "energy-allotment-order";
	private  final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) throws OpenAccessException {

		try {
			String id = pl.getFilterCriteria().get("id");
			pl.setDocId(id);
			
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
			log.error(oae.getMessage());
			throw oae;
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			
		}

		return pl;
	}
	
	public Context setContext(EnergyAllotmentOrder energyAllotmentOrder) {
		final Context ctx = new Context(Locale.ENGLISH);
//		System.out.println("energySaleOrderLines"+energyAllotmentOrder.getEnergySaleOrderLines());
//		System.out.println(energyAllotmentOrder);
		try {

			
		ctx.setVariable("sellerEndOrgName", energyAllotmentOrder.getSellerEndOrgName());
		ctx.setVariable("sellerCompanyServiceNumber", energyAllotmentOrder.getSellerCompanyServiceNumber());
		ctx.setVariable("sellerCompanyName", energyAllotmentOrder.getSellerCompanyName());
		ctx.setVariable("month", commonUtils.monthConversionString(energyAllotmentOrder.getMonth()));
		ctx.setVariable("year", energyAllotmentOrder.getYear());
        ctx.setVariable("fuelGroupe", energyAllotmentOrder.getFuelGroupe());
		ctx.setVariable("injectingVoltage", energyAllotmentOrder.getInjectingVoltageName());
		ctx.setVariable("totalC1", energyAllotmentOrder.getTotalC1());
		ctx.setVariable("totalC2", energyAllotmentOrder.getTotalC2());
		ctx.setVariable("totalC3", energyAllotmentOrder.getTotalC3());
		ctx.setVariable("totalC4", energyAllotmentOrder.getTotalC4());
		ctx.setVariable("totalC5", energyAllotmentOrder.getTotalC5());
		ctx.setVariable("totalGenUnitsSold", energyAllotmentOrder.getTotalGenUnitsSold());
		ctx.setVariable("totalBc1", energyAllotmentOrder.getTotalBc1());
		ctx.setVariable("totalBc2", energyAllotmentOrder.getTotalBc2());
		ctx.setVariable("totalBc3", energyAllotmentOrder.getTotalBc3());
		ctx.setVariable("totalBc4", energyAllotmentOrder.getTotalBc4());
		ctx.setVariable("totalBc5", energyAllotmentOrder.getTotalBc5());
		ctx.setVariable("totalBankingUnitsSold", energyAllotmentOrder.getTotalBankingUnitsSold());
		ctx.setVariable("totalGC1", energyAllotmentOrder.getTotalGc1());
		ctx.setVariable("totalGC2", energyAllotmentOrder.getTotalGc2());
		ctx.setVariable("totalGC3", energyAllotmentOrder.getTotalGc3());
		ctx.setVariable("totalGC4", energyAllotmentOrder.getTotalGc4());
		ctx.setVariable("totalGC5", energyAllotmentOrder.getTotalGc5());
		ctx.setVariable("allowLowerSlotAdmt", energyAllotmentOrder.getAllowLowerSlotAdmt());
		ctx.setVariable("totalUnitsSold", energyAllotmentOrder.getTotalUnitsSold());
		ctx.setVariable("createdDate", CommonUtils.convertDateFormat(energyAllotmentOrder.getCreatedDate(), "DD/MM/YYYY"));
		
		
//		System.out.println("print charges");
// 		System.out.println(energyAllotmentOrder.getEnergyCharge());
// 		System.out.println("getEnergySaleOrderLines");
// 		System.out.println(energyAllotmentOrder.getEnergySaleOrderLines());
		ctx.setVariable("energySaleOrderLines", energyAllotmentOrder.getEnergySaleOrderLines());
 		ctx.setVariable("energyCharges", energyAllotmentOrder.getEnergyCharge());
     	
		
		List<EsChargeForPrint> esChargeForPrintList = new ArrayList<EsChargeForPrint>();

		List<EnergyAllotmentOrderLine> esorderLines=new ArrayList<EnergyAllotmentOrderLine>();
		esorderLines=energyAllotmentOrder.getEnergySaleOrderLines();
//		System.out.println(esorderLines);


	
	ctx.setVariable("energySaleOrderLines", energyAllotmentOrder.getEnergySaleOrderLines());
	float c1Value=0;
	float c2Value=0;
	float c3Value=0;
	float c4Value=0;
	float c5Value=0;
	float totalAllocated=0;

		for(EnergyAllotmentOrderLine energyAllotmentOrderLine:energyAllotmentOrder.getEnergySaleOrderLines()) {
//		System.out.println("in-for-list");

		c1Value+=Float.parseFloat(energyAllotmentOrderLine.getC1());
		c2Value+=Float.parseFloat(energyAllotmentOrderLine.getC2());
		c3Value+=Float.parseFloat(energyAllotmentOrderLine.getC3());
		c4Value+=Float.parseFloat(energyAllotmentOrderLine.getC4());
		c5Value+=Float.parseFloat(energyAllotmentOrderLine.getC5());
		totalAllocated+=Float.parseFloat(energyAllotmentOrderLine.getTotalUnitsSold());
	}
		ctx.setVariable("c1Value", c1Value);
//	System.out.println(c1Value);
	ctx.setVariable("c2Value", c2Value);
//	System.out.println(c2Value);
	ctx.setVariable("c3Value", c3Value);
//	System.out.println(c3Value);
	ctx.setVariable("c4Value", c4Value);
//	System.out.println(c4Value);
	ctx.setVariable("c5Value", c5Value);
//	System.out.println(c5Value);
	ctx.setVariable("totalAllocated", totalAllocated);
	List<ExcessUnit> ExessbankingUnits = fetchReportDataForBanking(energyAllotmentOrder.getSellerCompanyServiceNumber(),energyAllotmentOrder.getMonth(),energyAllotmentOrder.getYear());
    ctx.setVariable("ExessbankingUnits", ExessbankingUnits);
   Integer totalBankC1 =0;
   Integer totalBankC2 =0;
   Integer totalBankC3 =0;
   Integer totalBankC4 =0;
   Integer totalBankC5 =0;
   Integer totalBanktotal =0;
    for(int i =0 ; i < ExessbankingUnits.size(); i++) {
    	
    	
    	totalBankC1 = commonUtils.toInt(ExessbankingUnits.get(i).getCurrC1())+totalBankC1;
    	totalBankC2 = commonUtils.toInt(ExessbankingUnits.get(i).getCurrC2())+totalBankC2;
    	totalBankC3 = commonUtils.toInt(ExessbankingUnits.get(i).getCurrC3())+totalBankC3;
    	totalBankC4 = commonUtils.toInt(ExessbankingUnits.get(i).getCurrC4())+totalBankC4;
    	totalBankC5 = commonUtils.toInt(ExessbankingUnits.get(i).getCurrC5())+totalBankC5;
    	totalBanktotal = commonUtils.toInt(ExessbankingUnits.get(i).getCurrTotalUnits())+totalBanktotal;
    	}
    
    ctx.setVariable("totalBankC1", totalBankC1);
    ctx.setVariable("totalBankC2", totalBankC2);
    ctx.setVariable("totalBankC3", totalBankC3);
    ctx.setVariable("totalBankC4", totalBankC4);
    ctx.setVariable("totalBankC5", totalBankC5);
    ctx.setVariable("totalBanktotal", totalBanktotal);
   
    
    
    
    
    
    
    
 	if(esorderLines!=null) {
 	
		esorderLines.forEach(line->{
			
//			System.out.println(line);
			if(line!=null) {
				EsChargeForPrint esChargeForPrint = new EsChargeForPrint();
				esChargeForPrint.setCompanyName(line.getBuyerCompanyName());
				esChargeForPrint.setCompanyServiceNumber(line.getBuyerCompanyServiceNumber());
//				System.out.println("in loop 2");

				energyAllotmentOrder.getEnergyCharge().forEach(charge->{
					
					if(charge!=null) {
						if(charge.getCompanyServiceNumber().equals(esChargeForPrint.getCompanyServiceNumber())){
							
//							System.out.println("in loop 3");

							if(charge.getChargeCode().equals("C001")) {
								esChargeForPrint.setC001TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C002")) {
								esChargeForPrint.setC002TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C003")) {
								esChargeForPrint.setC003TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C004")) {
								esChargeForPrint.setC004TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C005")) {
								esChargeForPrint.setC005TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C006")) {
								esChargeForPrint.setC006TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C007")) {
								esChargeForPrint.setC007TotalCharge(charge.getTotalCharges());	
							}
							if(charge.getChargeCode().equals("C008")) {
								esChargeForPrint.setC008TotalCharge(charge.getTotalCharges());	
							}
						}
					}
					
				});
				
				esChargeForPrintList.add(esChargeForPrint);
			}
			
			
		});
		int c1TotalValue=0;
		int c2TotalValue=0;
		int c3TotalValue=0;
		int c4TotalValue=0;
		int c5TotalValue=0;
		int c6TotalValue=0;
		int c7TotalValue=0;
		int c8TotalValue=0;
		for(EsChargeForPrint esChargeForPrint1:esChargeForPrintList) {
			c1TotalValue+=commonUtils.toInt(esChargeForPrint1.getC001TotalCharge());
			c2TotalValue+=commonUtils.toInt(esChargeForPrint1.getC002TotalCharge());
			c3TotalValue+=commonUtils.toInt(esChargeForPrint1.getC003TotalCharge());
			c4TotalValue+=commonUtils.toInt(esChargeForPrint1.getC004TotalCharge());
			c5TotalValue+=commonUtils.toInt(esChargeForPrint1.getC005TotalCharge());
			c6TotalValue+=commonUtils.toInt(esChargeForPrint1.getC006TotalCharge());
			c7TotalValue+=commonUtils.toInt(esChargeForPrint1.getC007TotalCharge());
			c8TotalValue+=commonUtils.toInt(esChargeForPrint1.getC008TotalCharge());
			
		}
		ctx.setVariable("c1TotalValue", c1TotalValue);
		
		ctx.setVariable("c2TotalValue", c2TotalValue);
		
		ctx.setVariable("c3TotalValue", c3TotalValue);
		
		ctx.setVariable("c4TotalValue", c4TotalValue);
		
		ctx.setVariable("c5TotalValue", c5TotalValue);
		
		ctx.setVariable("c6TotalValue", c6TotalValue);
		ctx.setVariable("c7TotalValue", c7TotalValue);
		ctx.setVariable("c8TotalValue", c8TotalValue);

		
//		ctx.setVariable("esChargeForPrintList", esChargeForPrintList);
		
     	ctx.setVariable("energySaleOrderLines", energyAllotmentOrder.getEnergySaleOrderLines());
		ctx.setVariable("energyCharges", energyAllotmentOrder.getEnergyCharge());
		ctx.setVariable("esChargeForPrintList", esChargeForPrintList);
	}
//		System.out.println(esChargeForPrintList);
 
	}   
		catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	 }
	
	public EnergyAllotmentOrder fetchReportData(PrintPayload pl){
		EnergyAllotmentOrder eso = null;
		try {
			
			String url=dataServiceUrl+pl.getFilterCriteria().get("id")+"/_internal";
			log.debug("url to getenergy sale order print from oa service-"+url);
			
			eso = CommonUtils.getTemplate().getForObject(url, EnergyAllotmentOrder.class);
			//log.info(eso+"");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return eso;
	}
	public List<ExcessUnit> fetchReportDataForBanking(String ServiceNo,String Month,String Year){
		List<ExcessUnit> Exs = null;
		try {
			
			String url=dataServiceUrlForBanking+"/_internal"+"?companyServiceId="+ServiceNo+"&readingMonth="+Month+"&readingYear="+Year;
			log.debug("url to getenergy sale order print from oa service-"+url);
			
		Exs = Arrays.asList(CommonUtils.getTemplate().getForObject(url,ExcessUnit[].class));
		System.out.println("printing banking---"+Exs);
			//log.info(eso+"");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new OpenAccessException(e.getMessage());
		}
		return Exs;
	}
	
	
		
	}