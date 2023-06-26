package com.ss.oa.oadocumentservice.printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import com.ss.oashared.model.EnergyAdjustedOrderCharge;
import com.ss.oa.oadocumentservice.vo.EnergyOrderCharge;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;
@Service
public class EnergyAdjustedOrderChargeReportPrinter {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${es-charge-report.url}")
	private String dataServiceUrl;
	
	@Value("${es-order-charge-report.url}")
	private String oaChargeUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	private  final String TEMPLATE_NAME = "energy-adj-order-charge-report";
	private  final String FILE_EXTENSION = "pdf";
	
	public PrintPayload process(PrintPayload pl) {
		try {
			pl.setDocId(System.currentTimeMillis()+"");
			
			pl.setFileExtension(FILE_EXTENSION);
			pl.setFileName(TEMPLATE_NAME + pl.getDocId()+"."+pl.getFileExtension());
			pl.setFileNameToUser(pl.getFileName());
			pl.setDocPath(fileLocation  +"/"+TEMPLATE_NAME+"/"+ pl.getFileName());
			
			commonUtils.generatePdf(pl, this.htmlTemplateEngine.process(TEMPLATE_NAME, setContext(fetchReportData(pl))));

		} catch (OpenAccessException oae) {
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return pl;	
	}
	
public List<EnergyAdjustedOrderCharge> fetchReportData(PrintPayload pl) {	
		
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String suplrCode = ipCriteria.get("suplrCode");
		String serviceNo = ipCriteria.get("serviceNo");
		String readingMnth = ipCriteria.get("readingMnth");
		String readingYr = ipCriteria.get("readingYr");
		String meterReadingCharges = ipCriteria.get("meterReadingCharges");
		String oMCharges = ipCriteria.get("oMCharges");
		String SystemOperationCharges = ipCriteria.get("SystemOperationCharges");
		String rkvahPenalty = ipCriteria.get("rkvahPenalty");
		String negativeEnergyCharges = ipCriteria.get("negativeEnergyCharges");
		String schedulingCharges = ipCriteria.get("schedulingCharges");
		String transmissionCharges = ipCriteria.get("transmissionCharges");
		
		
		String url = "?dummy=1";
		
		if (suplrCode != null && !suplrCode.isEmpty()) { 
			url += "&suplrCode=" + suplrCode;
		}
		if (serviceNo != null && !serviceNo.isEmpty()) { 
			url += "&serviceNo=" + serviceNo;
			}
		if (readingMnth != null && !readingMnth.isEmpty()) { 
			url += "&readingMnth=" + readingMnth;
			}
		if (readingYr != null && !readingYr.isEmpty()) { 
			url += "&readingYr=" + readingYr;
			}
		if (meterReadingCharges != null && !meterReadingCharges.isEmpty()) { 
			url += "&meterReadingCharges=" + meterReadingCharges;
			}
		if (oMCharges != null && !oMCharges.isEmpty()) { 
			url += "&oMCharges=" + oMCharges;
			}
		if (SystemOperationCharges != null && !SystemOperationCharges.isEmpty()) { 
			url += "&SystemOperationCharges=" + SystemOperationCharges;
			}
		if (rkvahPenalty != null && !rkvahPenalty.isEmpty()) { 
			url += "&rkvahPenality=" + rkvahPenalty;
			}
		if (negativeEnergyCharges != null && !negativeEnergyCharges.isEmpty()) { 
			url += "&negativeEnergyCharges=" + negativeEnergyCharges;
			}
		if (schedulingCharges != null && !schedulingCharges.isEmpty()) { 
			url += "&schedulingCharges=" + schedulingCharges;
			}
		if (transmissionCharges != null && !transmissionCharges.isEmpty()) { 
			url += "&transmissionCharges=" + transmissionCharges;
			}
	
		
		System.out.println("url with crietria--"+url);

		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+url, EnergyAdjustedOrderCharge[].class));
	}

		public Context setContext(List<EnergyAdjustedOrderCharge> EnergyAdjustedOrderChargelist) {
			final Context ctx = new Context(Locale.ENGLISH);
			List<EnergyOrderCharge> oaList=new ArrayList<EnergyOrderCharge>();
			Map<String, String> cr = new HashMap<String, String>();

			EnergyAdjustedOrderChargelist.forEach(energyAdjOrder -> {
				try {
					energyAdjOrder.setSuplrCode(energyAdjOrder.getSuplrCode());
					energyAdjOrder.setServiceNo(energyAdjOrder.getServiceNo());
					energyAdjOrder.setReadingMnth(energyAdjOrder.getReadingMnth());
					energyAdjOrder.setReadingYr(energyAdjOrder.getReadingYr());
					energyAdjOrder.setMeterReadingCharges(energyAdjOrder.getMeterReadingCharges());
					energyAdjOrder.setoMCharges(energyAdjOrder.getoMCharges());
					energyAdjOrder.setSystemOperationCharges(energyAdjOrder.getSystemOperationCharges());
					energyAdjOrder.setRkvahPenalty(energyAdjOrder.getRkvahPenalty());
					energyAdjOrder.setNegativeEnergyCharges(energyAdjOrder.getNegativeEnergyCharges());
					energyAdjOrder.setSchedulingCharges(energyAdjOrder.getSchedulingCharges());
					energyAdjOrder.setTransmissionCharges(energyAdjOrder.getTransmissionCharges());
					cr.put("sellerServiceNumber",energyAdjOrder.getSuplrCode());
					cr.put("month",energyAdjOrder.getReadingMnth());
					cr.put("year",energyAdjOrder.getReadingYr());

		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			System.out.println("cr");
			System.out.println(cr);
			oaList=getOaCharge(cr);
			System.out.println("oa-list");
			System.out.println(oaList);

			try {
				System.out.println("in context--");
				ctx.setVariable("oaList", oaList);
				ctx.setVariable("EnergyAdjustedOrderChargelist", EnergyAdjustedOrderChargelist);

			}catch(Exception e) {
				e.printStackTrace();
				throw new OpenAccessException(e.getLocalizedMessage());
			}
			return ctx;
	}
		
		public List<EnergyOrderCharge> getOaCharge(Map<String, String>cr){
			List<EnergyOrderCharge> list=new ArrayList<EnergyOrderCharge>();
			
			String url = "?dummy=1";
			String sellerServiceNumber = cr.get("sellerServiceNumber");
			String buyerServiceNumber = cr.get("buyerServiceNumber");
			String month = cr.get("month");
			String year = cr.get("year");
						
			if (sellerServiceNumber != null && !sellerServiceNumber.isEmpty()) { 
				url += "&sellerServiceNumber=" + sellerServiceNumber;
			}
			if (buyerServiceNumber != null && !buyerServiceNumber.isEmpty()) { 
				url += "&buyerServiceNumber=" + buyerServiceNumber;
				}
			if (month != null && !month.isEmpty()) { 
				url += "&month=" + month;
				}
			if (year != null && !year.isEmpty()) { 
				url += "&year=" + year;
				}
			System.out.println(url);
			list=Arrays.asList(CommonUtils.getTemplate().getForObject(oaChargeUrl+url, EnergyOrderCharge[].class));
			System.out.println("oa-charge");
			System.out.println(list);
			return list;
		}



}
