package com.ss.oa.oadocumentservice.printer;

import java.io.IOException;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ss.oa.oadocumentservice.vo.EnergyAdjustedOrder;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import com.ss.oashared.model.PrintPayload;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
@Service
public class EnergyAdjustedOrderReportPrinter {
	@Value("${file.location}")
	private String fileLocation;
	
	@Value("${energy-adjusted-order.url}")
	private String dataServiceUrl;
	
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	
	

	private  final String TEMPLATE_NAME = "energy-adj-order";
	private  final String FILE_EXTENSION = "pdf";

	public PrintPayload process(PrintPayload pl) {
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
			throw oae;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

		return pl;	
	}
	
	
	public List<EnergyAdjustedOrder> fetchReportData(PrintPayload pl) {	
		
		Map<String, String> ipCriteria = pl.getFilterCriteria();
		String readingMonth = ipCriteria.get("readingMonth");
		String readingYear = ipCriteria.get("readingYear");
		String sellerServiceNumber = ipCriteria.get("seller-service-number");
		String url = "?dummy=1";
		if (readingMonth != null && !readingMonth.isEmpty()) { 
			url += "&readingMonth=" + readingMonth;
		}
		if (readingYear != null && !readingYear.isEmpty()) { 
			url += "&readingYear=" + readingYear;
		}
		if (sellerServiceNumber != null && !sellerServiceNumber.isEmpty()) { 
			url += "&seller-service-number=" + sellerServiceNumber;
		}
		System.out.println("url with crietria--"+url);
		return Arrays.asList(CommonUtils.getTemplate().getForObject(dataServiceUrl+url, EnergyAdjustedOrder[].class));
	}
	
	public Context setContext(List<EnergyAdjustedOrder> EnergyAdjOrderList) {
		final Context ctx = new Context(Locale.ENGLISH);
		
		
		EnergyAdjustedOrder energyAdjustedOrder= new EnergyAdjustedOrder();
		
		float SetterLossPercentage = 0f;
		for (int i = 0 ; i < EnergyAdjOrderList.size(); i++) {
			
			
			float getterLossPercentage = Float.parseFloat(EnergyAdjOrderList.get(i).getLossPercent());  
			SetterLossPercentage = Float.sum(getterLossPercentage, SetterLossPercentage);
		}
		
           energyAdjustedOrder.setTotalLossPercentage(SetterLossPercentage);
		
		
		EnergyAdjOrderList.forEach(energyAdjOrder -> {
			try {
				
				
				energyAdjOrder.setAdjustedC1(""+commonUtils.toInt(energyAdjOrder.getAdjustedC1()));
				energyAdjOrder.setAdjustedC2(""+commonUtils.toInt(energyAdjOrder.getAdjustedC2()));
				energyAdjOrder.setAdjustedC3(""+commonUtils.toInt(energyAdjOrder.getAdjustedC3()));
				energyAdjOrder.setAdjustedC4(""+commonUtils.toInt(energyAdjOrder.getAdjustedC4()));
				energyAdjOrder.setAdjustedC5(""+commonUtils.toInt(energyAdjOrder.getAdjustedC5()));
				
				energyAdjOrder.setTotaladj((long) (commonUtils.toInt(energyAdjOrder.getAdjustedC1())+
						commonUtils.toInt(energyAdjOrder.getAdjustedC2())+
						commonUtils.toInt(energyAdjOrder.getAdjustedC3())+
						commonUtils.toInt(energyAdjOrder.getAdjustedC4())+
						commonUtils.toInt(energyAdjOrder.getAdjustedC5())));
				
				
			    energyAdjOrder.setAllotedC1(""+commonUtils.toInt(energyAdjOrder.getAllotedC1()));
				energyAdjOrder.setAllotedC2(""+commonUtils.toInt(energyAdjOrder.getAllotedC2()));
				energyAdjOrder.setAllotedC3(""+commonUtils.toInt(energyAdjOrder.getAllotedC3()));
				energyAdjOrder.setAllotedC4(""+commonUtils.toInt(energyAdjOrder.getAllotedC4()));
				energyAdjOrder.setAllotedC5(""+commonUtils.toInt(energyAdjOrder.getAllotedC5()));
				
				energyAdjOrder.setTotalallo((long) (commonUtils.toInt(energyAdjOrder.getAllotedC1())+
						commonUtils.toInt(energyAdjOrder.getAllotedC2())+
						commonUtils.toInt(energyAdjOrder.getAllotedC3())+
						commonUtils.toInt(energyAdjOrder.getAllotedC4())+
						commonUtils.toInt(energyAdjOrder.getAllotedC5())));
				

				
				energyAdjOrder.setAllotedNetC1(""+commonUtils.toInt(energyAdjOrder.getAllotedNetC1()));
				energyAdjOrder.setAllotedNetC2(""+commonUtils.toInt(energyAdjOrder.getAllotedNetC2()));
				energyAdjOrder.setAllotedNetC3(""+commonUtils.toInt(energyAdjOrder.getAllotedNetC3()));
				energyAdjOrder.setAllotedNetC4(""+commonUtils.toInt(energyAdjOrder.getAllotedNetC4()));
				energyAdjOrder.setAllotedNetC5(""+commonUtils.toInt(energyAdjOrder.getAllotedNetC5()));
				
				energyAdjOrder.setTotalallonet((long) (commonUtils.toInt(energyAdjOrder.getAllotedNetC1())+
						commonUtils.toInt(energyAdjOrder.getAllotedNetC2())+
					    commonUtils.toInt(energyAdjOrder.getAllotedNetC3())+
					    commonUtils.toInt(energyAdjOrder.getAllotedNetC4())+
					    commonUtils.toInt(energyAdjOrder.getAllotedNetC5())));
	
				
				
				energyAdjOrder.setHtBankingC1(""+commonUtils.toInt(energyAdjOrder.getHtBankingC1()));
				energyAdjOrder.setHtBankingC2(""+commonUtils.toInt(energyAdjOrder.getHtBankingC2()));
				energyAdjOrder.setHtBankingC3(""+commonUtils.toInt(energyAdjOrder.getHtBankingC3()));
				energyAdjOrder.setHtBankingC4(""+commonUtils.toInt(energyAdjOrder.getHtBankingC4()));
				energyAdjOrder.setHtBankingC5(""+commonUtils.toInt(energyAdjOrder.getHtBankingC5()));
				
				energyAdjOrder.setTotalhtBanking((long) (commonUtils.toInt(energyAdjOrder.getHtBankingC1())+
						commonUtils.toInt(energyAdjOrder.getHtBankingC2())+
						commonUtils.toInt(energyAdjOrder.getHtBankingC3())+
						commonUtils.toInt(energyAdjOrder.getHtBankingC4())+
						commonUtils.toInt(energyAdjOrder.getHtBankingC5())));
				
				
			
				energyAdjOrder.setHtBankingWithLossC1(""+commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC1()));
				energyAdjOrder.setHtBankingWithLossC2(""+commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC2()));
				energyAdjOrder.setHtBankingWithLossC3(""+commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC3()));
				energyAdjOrder.setHtBankingWithLossC4(""+commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC4()));
				energyAdjOrder.setHtBankingWithLossC5(""+commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC5()));
				
			
				
				energyAdjOrder.setTotalhtBankingwithloss((long) (commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC1())+
						commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC2())+
						commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC3())+
						commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC4())+
						commonUtils.toInt(energyAdjOrder.getHtBankingWithLossC5())));
				
				
				
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		});
		
		try {
			
			Long SetterAdjC1 = 0l;
			Long SetterAdjC2 = 0l;
			Long SetterAdjC3 = 0l;
			Long SetterAdjC4 = 0l;
			Long SetterAdjC5 = 0l;
			Long SetterAdjT = 0l;
			
			Long SetterAllGrC1 = 0l;
			Long SetterAllGrC2 = 0l;
			Long SetterAllGrC3 = 0l;
			Long SetterAllGrC4 = 0l;
			Long SetterAllGrC5 = 0l;
			Long SetterAllGrT = 0l;
			
			Long SetterAllnetC1 = 0l;
			Long SetterAllnetC2 = 0l;
			Long SetterAllnetC3 = 0l;
			Long SetterAllnetC4 = 0l;
			Long SetterAllnetC5 = 0l;
			Long SetterAllnetT = 0l;
			
			Long SetterBanknetC1 = 0l;
			Long SetterBanknetC2 = 0l;
			Long SetterBanknetC3 = 0l;
			Long SetterBanknetC4 = 0l;
			Long SetterBanknetC5 = 0l;
			Long SetterBanknetT = 0l;
			
			
			Long SetterBankLossC1 = 0l;
			Long SetterBankLossC2 = 0l;
			Long SetterBankLossC3 = 0l;
			Long SetterBankLossC4 = 0l;
			Long SetterBankLossC5 = 0l;
			Long SetterBankLossT = 0l;
			
			
			
			for (int i = 0 ; i < EnergyAdjOrderList.size(); i++) {
			
			 Long getterAdjC1 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAdjustedC1());
			 SetterAdjC1 = SetterAdjC1 + getterAdjC1 ;
			 Long getterAdjC2 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAdjustedC2());
			 SetterAdjC2 = SetterAdjC2 + getterAdjC2 ;
			 Long getterAdjC3 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAdjustedC3());
			 SetterAdjC3 = SetterAdjC3 + getterAdjC3 ;
			 Long getterAdjC4 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAdjustedC4());
			 SetterAdjC4 = SetterAdjC4 + getterAdjC4 ;
			 Long getterAdjC5 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAdjustedC5());
			 SetterAdjC5 = SetterAdjC5 + getterAdjC5 ;
			 Long getterAdjT =EnergyAdjOrderList.get(i).getTotaladj();
			 SetterAdjT = SetterAdjT+ getterAdjT ;
			
			 
			 Long getterAllGrC1 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedC1());
			 SetterAllGrC1 = SetterAllGrC1 + getterAllGrC1 ;
			 Long getterAllGrC2 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedC2());
			 SetterAllGrC2 = SetterAllGrC2 + getterAllGrC2 ;
			 Long getterAllGrC3 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedC3());
			 SetterAllGrC3 = SetterAllGrC3 + getterAllGrC3 ;
			 Long getterAllGrC4 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedC4());
			 SetterAllGrC4 = SetterAllGrC4 + getterAllGrC4 ;
			 Long getterAllGrC5 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedC5());
			 SetterAllGrC5 = SetterAllGrC5 + getterAllGrC5 ;
			 Long getterAllGrT =EnergyAdjOrderList.get(i).getTotalallo();
			 SetterAllGrT = SetterAllGrT + getterAllGrT ;
			 
			 Long getterAllNetC1 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedNetC1());
			 SetterAllnetC1 = SetterAllnetC1 + getterAllNetC1 ;
			 Long getterAllNetC2 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedNetC2());
			 SetterAllnetC2 = SetterAllnetC2 + getterAllNetC2 ;
			 Long getterAllNetC3 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedNetC3());
			 SetterAllnetC3 = SetterAllnetC3 + getterAllNetC3 ;
			 Long getterAllNetC4 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedNetC4());
			 SetterAllnetC4 = SetterAllnetC4 + getterAllNetC4 ;
			 Long getterAllNetC5 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getAllotedNetC5());
			 SetterAllnetC5 = SetterAllnetC5 + getterAllNetC5 ;
			 Long getterAllNetT =EnergyAdjOrderList.get(i).getTotalallonet();
			 SetterAllnetT = SetterAllnetT + getterAllNetT ;
			 
			 Long getterBankNetC1 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingC1());
			 SetterBanknetC1 = SetterBanknetC1 + getterBankNetC1 ;
			 Long getterBankNetC2 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingC2());
			 SetterBanknetC2 = SetterBanknetC2 + getterBankNetC2 ;
			 Long getterBankNetC3 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingC3());
			 SetterBanknetC3 = SetterBanknetC3 + getterBankNetC3 ;
			 Long getterBankNetC4 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingC4());
			 SetterBanknetC4 = SetterBanknetC4 + getterBankNetC4 ;
			 Long getterBankNetC5 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingC5());
			 SetterBanknetC5 = SetterBanknetC5 + getterBankNetC5 ;
			 Long getterBankNetT =EnergyAdjOrderList.get(i).getTotalhtBanking();
			 SetterBanknetT = SetterBanknetT + getterBankNetT ;
			 
			 
			 Long getterBankLossC1 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingWithLossC1());
			 SetterBankLossC1 = SetterBankLossC1 + getterBankLossC1 ;
			 Long getterBankLossC2 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingWithLossC2());
			 SetterBankLossC2 = SetterBankLossC2 + getterBankLossC2 ;
			 Long getterBankLossC3 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingWithLossC3());
			 SetterBankLossC3 = SetterBankLossC3 + getterBankLossC3 ;
			 Long getterBankLossC4 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingWithLossC4());
			 SetterBankLossC4 = SetterBankLossC4 + getterBankLossC4 ;
			 Long getterBankLossC5 =(long) commonUtils.toInt(EnergyAdjOrderList.get(i).getHtBankingWithLossC5());
			 SetterBankLossC5 = SetterBankLossC5 + getterBankLossC5 ;
			 Long getterBankLossT =EnergyAdjOrderList.get(i).getTotalhtBankingwithloss();
			 SetterBankLossT = SetterBankLossT + getterBankLossT ;
			 
			
			}
			
			energyAdjustedOrder.setTotaladjustedC1(SetterAdjC1);
			energyAdjustedOrder.setTotaladjustedC2(SetterAdjC2);
			energyAdjustedOrder.setTotaladjustedC3(SetterAdjC3);
			energyAdjustedOrder.setTotaladjustedC4(SetterAdjC4);
			energyAdjustedOrder.setTotaladjustedC5(SetterAdjC5);
			energyAdjustedOrder.setTotalupadj(SetterAdjT);
			
			energyAdjustedOrder.setTotalalloctedgrossC1(SetterAllGrC1);
			energyAdjustedOrder.setTotalalloctedgrossC2(SetterAllGrC2);
			energyAdjustedOrder.setTotalalloctedgrossC3(SetterAllGrC3);
			energyAdjustedOrder.setTotalalloctedgrossC4(SetterAllGrC4);
			energyAdjustedOrder.setTotalalloctedgrossC5(SetterAllGrC5);
			energyAdjustedOrder.setTotalupalloctedgross(SetterAllGrT);
			
			energyAdjustedOrder.setTotalalloctednetC1(SetterAllnetC1);
			energyAdjustedOrder.setTotalalloctednetC2(SetterAllnetC2);
			energyAdjustedOrder.setTotalalloctednetC3(SetterAllnetC3);
			energyAdjustedOrder.setTotalalloctednetC4(SetterAllnetC4);
			energyAdjustedOrder.setTotalalloctednetC5(SetterAllnetC5);
			energyAdjustedOrder.setTotalupalloctednet(SetterAllnetT);
			
			
			energyAdjustedOrder.setTotalhtBankingC1(SetterBanknetC1);
			energyAdjustedOrder.setTotalhtBankingC2(SetterBanknetC2);
			energyAdjustedOrder.setTotalhtBankingC3(SetterBanknetC3);
			energyAdjustedOrder.setTotalhtBankingC4(SetterBanknetC4);
			energyAdjustedOrder.setTotalhtBankingC5(SetterBanknetC5);
			energyAdjustedOrder.setTotaluphtBanking(SetterBanknetT);
			
			energyAdjustedOrder.setTotalhtBankingWithLossC1(SetterBankLossC1);
			energyAdjustedOrder.setTotalhtBankingWithLossC2(SetterBankLossC2);
			energyAdjustedOrder.setTotalhtBankingWithLossC3(SetterBankLossC3);
			energyAdjustedOrder.setTotalhtBankingWithLossC4(SetterBankLossC4);
			energyAdjustedOrder.setTotalhtBankingWithLossC5(SetterBankLossC5);
			energyAdjustedOrder.setTotaluphtBankingWithLoss(SetterBankLossT);
			
			
			ctx.setVariable("EnergyAdjOrderList", EnergyAdjOrderList);
			ctx.setVariable("EnergyAdjOrder", energyAdjustedOrder);

		}catch(Exception e) {
			e.printStackTrace();
			throw new OpenAccessException(e.getLocalizedMessage());
		}
		return ctx;
	}
	
	
//	public String generateXsl(List<EnergyAdjustedOrder> energyAdjOrderList) throws IOException {
//		String jsonString = "Your JSON string";
//		HashMap<String,String> map = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>(){}.getType());
//		return "success"; 
//	}

}
