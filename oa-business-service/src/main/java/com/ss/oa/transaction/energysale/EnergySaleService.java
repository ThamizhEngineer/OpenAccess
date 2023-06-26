package com.ss.oa.transaction.energysale;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.ledger.energyledger.EnergyLedgerService;
import com.ss.oa.ledger.energysaleorder.EnergySaleOrderService;
import com.ss.oa.master.company.CompanyService;
import com.ss.oa.master.traderelationship.TradeRelationshipService;
import com.ss.oa.master.vo.CompanyServiceVO;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.transaction.generationstatement.GenerationStatementService;
import com.ss.oa.transaction.losscalculation.LossCalculationService;
import com.ss.oa.transaction.vo.EnergySale;
import com.ss.oa.transaction.vo.EnergySaleCharge;
import com.ss.oa.transaction.vo.EnergySaleUsageDetail;
import com.ss.oa.transaction.vo.EnergySaleUsageSummary;
import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.GenerationStatementSlot;
import com.ss.oashared.common.CommonUtils;

import oracle.jdbc.OracleTypes;

@Component
@Scope("prototype")
public class EnergySaleService {
	
	
	@Resource
	private DataSource dataSource;
	@Autowired
	EnergySaleDao dao;
	@Autowired
	EnergySaleOrderService esOrderService;
	@Autowired
	GenerationStatementService gsService;
	@Autowired
	EnergyLedgerService energyLedgerService;
	@Autowired
	LossCalculationService lossCalculationService;
	@Autowired
	CompanyService companyService;
	@Autowired
	TradeRelationshipService tradeRelationshipService;
	@Autowired
	EnergySaleChargeHelper energySaleChargeHelper;
	@Autowired
	private CommonUtils commonUtils;
	
	Connection conn = null;
	CallableStatement stmt = null;
	
	
	String energySaleId="";
	String energySaleUsageDetailId="";
	String energySaleUsageSummaryId="";
	String generationStatementId="";
	String energySaleChargeId ="";
	public List<EnergySale> getAllEnergySales(Map<String, String> criteria) throws Exception{
		try {
		return dao.getAllEnergySales(criteria);	
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

	public EnergySale getEnergySaleById(String id) {
		EnergySale energySale= dao.getEnergySaleById(id);
//		energySale=settingChargeCode(energySale);
		return energySale;
	}

	
	public String addEnergySale(EnergySale energySale) {
		
		String createEnergySaleFunction = "{call create_energy_sale(?,?)}"; 
		
	
			try {
			        conn = dataSource.getConnection();
			        stmt = conn.prepareCall(createEnergySaleFunction);
			        stmt.setString(1, energySale.getGenerationStatementId());
			        stmt.registerOutParameter(2, OracleTypes.VARCHAR);
			      
			        stmt.execute();
			        energySaleId= stmt.getString(2);   
			        System.out.println(energySaleId);
					conn.close();

			        
			      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
				
				try {
					if((stmt!= null) && (!stmt.isClosed())) {
						stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if((conn!= null) && (!conn.isClosed())) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
		
		return energySaleId;
	}


	public String updateEnergySale(String id, EnergySale energySale) {
		
		
	if(energySale.getStatusCode()!=null && energySale.getStatusCode().equals("APPROVED")) {
			
			throw new OpenAccessException("Energy Sale Update Failed - cannot update approved energy sale");	
			
		}else {
		energySaleId = dao.updateEnergySale(id, energySale);
		if(energySale.getEnergySaleUsageDetails()!=null) {
			for(EnergySaleUsageDetail  energySaleUsageDetail :energySale.getEnergySaleUsageDetails()) {
				if(energySaleUsageDetail.getId()!=null &&energySaleUsageDetail.getId().trim().length()>0)
				{
					dao.updateEnergySaleUsageDetail(energySaleUsageDetail);
					
				}else {
					energySaleUsageDetail.setEnergySaleId(energySaleId);
					dao.addEnergySaleUsageDetail(energySaleUsageDetail);
				}
				
				
			}
		}
		}
		
		if(energySale.getEnergySaleUsageSummaries()!=null) {
			
			for(EnergySaleUsageSummary  energySaleUsageSummary :energySale.getEnergySaleUsageSummaries()) {
				if(energySaleUsageSummary.getId()!=null &&energySaleUsageSummary.getId().trim().length()>0)
				{
					dao.updateEnergySaleUsageSummary(energySaleUsageSummary);
					if(energySaleUsageSummary.getC001Id()!=null ||energySaleUsageSummary.getC002Id()!=null||energySaleUsageSummary.getC003Id()!=null ||energySaleUsageSummary.getC004Id()!=null ||energySaleUsageSummary.getC005Id()!=null||energySaleUsageSummary.getC006Id()!=null||energySaleUsageSummary.getC007Id()!=null||energySaleUsageSummary.getC008Id()!=null) {
						energySaleChargeHelper.updateEnergySaleCharge(energySaleUsageSummary);
					}else {
						energySaleChargeHelper.addEnergySaleCharge(energySaleUsageSummary);
					}
					
					
				}else {
					
					dao.addEnergySaleUsageSummary(energySaleUsageSummary);
					energySaleChargeHelper.addEnergySaleCharge(energySaleUsageSummary);
				}
				
				
			}
		}
		

		return energySaleId;
	}
	public String DeleteEsUsageSummary(EnergySaleUsageSummary energySaleUsageSummary) {
		System.out.println("Service EnergySaleUsageSummary");
		System.out.println(energySaleUsageSummary);
		
		
		EnergySaleCharge energySaleCharge = new EnergySaleCharge();
		energySaleCharge.setEnergySaleId(energySaleUsageSummary.getEnergySaleId());
		energySaleCharge.setCompanyServiceId(energySaleUsageSummary.getBuyerCompanyServiceId());
		DeleteEsCharge(energySaleCharge);
		return dao.DeleteEsUsageSummary(energySaleUsageSummary);
		
	}
	public String DeleteEsCharge(EnergySaleCharge energySaleCharge) {
		System.out.println("Service");
		System.out.println(energySaleCharge);
		return dao.DeleteEnergySaleCharge(energySaleCharge);
		
	}
	
	public String finalEnergySale(String id, EnergySale energySale) {
		updateEnergySale(id, energySale);
		return dao.finalEnergySale(id, energySale);
		
	}
	 
	public EnergySale multiAddEnergySale(String id, EnergySale energySale)  {
		List<String> buyerCompServiceNumberList=new ArrayList<String>();
		List<CompanyServiceVO> buyerCompServiceList =  new ArrayList<CompanyServiceVO> ();
		EnergySaleUsageSummary esUsageSummary = new EnergySaleUsageSummary();
		List<TradeRelationship> tradeRelationshipList = new ArrayList<TradeRelationship>();
		List<EnergySaleUsageSummary> esUsageSummaryInvalidDataList = new ArrayList<EnergySaleUsageSummary>();
		EnergySale energySaleTrade = new EnergySale();
	
		energySaleTrade = dao.getEnergySaleById(id);
		if(energySale.getEnergySaleUsageSummaries()!=null) {
			for(EnergySaleUsageSummary energySaleUsageSummary:energySale.getEnergySaleUsageSummaries()) {
				
				 buyerCompServiceNumberList.add(energySaleUsageSummary.getBuyerCompanyServiceNumber());
				
				
			}
			String[] buyerCompServiceNumbers = buyerCompServiceNumberList.toArray(new String[buyerCompServiceNumberList.size()]);
		
			Map<String,String> criteria = new HashMap<String,String>();
			criteria.put("comp-service-numbers",Stream.of(buyerCompServiceNumbers).collect(Collectors.joining("','")));
			criteria.put("type","02");		
			try {
				buyerCompServiceList =companyService.getAllCompanyServices(criteria);	
				
				
			
					
					for(EnergySaleUsageSummary energySaleUsageSummary:energySale.getEnergySaleUsageSummaries()) {
						
//						 if(energySaleUsageSummary.getBuyerCompanyServiceNumber().equals(buyerCompServiceNumbers)) {
//								
//								System.out.println("Service Number Not Valid");
//								EnergySaleUsageSummary esUsageSummaryInvalidData = new EnergySaleUsageSummary();
//								
//								esUsageSummaryInvalidData.setBuyerCompanyServiceNumber(energySaleUsageSummary.getBuyerCompanyServiceNumber());
//								esUsageSummaryInvalidData.setC1(energySaleUsageSummary.getC1());
//								esUsageSummaryInvalidData.setC2(energySaleUsageSummary.getC2());
//								esUsageSummaryInvalidData.setC3(energySaleUsageSummary.getC3());
//								esUsageSummaryInvalidData.setC4(energySaleUsageSummary.getC4());
//								esUsageSummaryInvalidData.setC5(energySaleUsageSummary.getC5());
//								esUsageSummaryInvalidData.setRemarks("Service Number Not Valid");
//								
//								esUsageSummaryInvalidDataList.add(esUsageSummaryInvalidData);	
//														}
//						
						for(CompanyServiceVO buyerCompService:buyerCompServiceList) {
					

						if(energySaleUsageSummary.getBuyerCompanyServiceNumber().equals(buyerCompService.getNumber())) {

							Map<String,String> tradeRelationshipCriteria = new HashMap<String,String>();
							tradeRelationshipCriteria.put("buyerCompanyServiceId",buyerCompService.getId());
							tradeRelationshipCriteria.put("month",energySaleTrade.getMonth());
							tradeRelationshipCriteria.put("year",energySaleTrade.getYear());
							tradeRelationshipCriteria.put("sellerCompanyServiceId",energySaleTrade.getSellerCompanyServiceId());
							tradeRelationshipList=tradeRelationshipService.getAllTradeRelationships(tradeRelationshipCriteria);
							
							 for(TradeRelationship tradeRelationship:tradeRelationshipList) {
								 if(buyerCompService.getId().equals(tradeRelationship.getBuyerCompServiceId())){
									 
									 // checking if the the consumer entry already exists in EnergySaleUsageSummary
									 boolean esuExists = false;
									 for(EnergySaleUsageSummary esSummary:energySaleTrade.getEnergySaleUsageSummaries()) {
										 if(esSummary.getBuyerCompanyServiceNumber().equals(energySaleUsageSummary.getBuyerCompanyServiceNumber())) {
											 esuExists = true; break;
										 }
									 }

									 if(esuExists) {
										
										 EnergySaleUsageSummary esUsageSummaryInvalidDataTrade = new EnergySaleUsageSummary();
//										 System.out.println(energySaleUsageSummary.getBuyerCompanyServiceId());
//											System.out.println("Service Number already Exist");
											esUsageSummaryInvalidDataTrade.setBuyerCompanyServiceNumber(energySaleUsageSummary.getBuyerCompanyServiceNumber());
											
											esUsageSummaryInvalidDataTrade.setRemarks("Service Number already Exist");
											esUsageSummaryInvalidDataTrade.setC1(energySaleUsageSummary.getC1());
											esUsageSummaryInvalidDataTrade.setC2(energySaleUsageSummary.getC2());
											esUsageSummaryInvalidDataTrade.setC3(energySaleUsageSummary.getC3());
											esUsageSummaryInvalidDataTrade.setC4(energySaleUsageSummary.getC4());
											esUsageSummaryInvalidDataTrade.setC5(energySaleUsageSummary.getC5());
											
											esUsageSummaryInvalidDataList.add(esUsageSummaryInvalidDataTrade);
											
									 }else {
										 esUsageSummary.setEnergySaleId(id);
										 esUsageSummary.setTradeRelationshipId(tradeRelationship.getId());
											esUsageSummary.setBuyerCompanyServiceNumber(buyerCompService.getNumber());
											esUsageSummary.setBuyerCompanyServiceId(buyerCompService.getId());
											esUsageSummary.setBuyerEndOrgId(buyerCompService.getOrgId());
											esUsageSummary.setBuyerEndSsId(buyerCompService.getSubstationId());
											esUsageSummary.setC1(energySaleUsageSummary.getC1());
											esUsageSummary.setC2(energySaleUsageSummary.getC2());
											esUsageSummary.setC3(energySaleUsageSummary.getC3());
											esUsageSummary.setC4(energySaleUsageSummary.getC4());
											esUsageSummary.setC5(energySaleUsageSummary.getC5());
											Integer total =commonUtils.toInt(energySaleUsageSummary.getC1())+commonUtils.toInt(energySaleUsageSummary.getC2())+commonUtils.toInt(energySaleUsageSummary.getC3())+commonUtils.toInt(energySaleUsageSummary.getC4())+commonUtils.toInt(energySaleUsageSummary.getC5());
											esUsageSummary.setTotal(total.toString());
											esUsageSummary.setC001ChargeCode(energySaleUsageSummary.getC001ChargeCode());
											esUsageSummary.setC001TotalCharge(energySaleUsageSummary.getC001TotalCharge());
											esUsageSummary.setC002ChargeCode(energySaleUsageSummary.getC002ChargeCode());
											esUsageSummary.setC002TotalCharge(energySaleUsageSummary.getC002TotalCharge());
											esUsageSummary.setC003ChargeCode(energySaleUsageSummary.getC003ChargeCode());
											esUsageSummary.setC003TotalCharge(energySaleUsageSummary.getC003TotalCharge());
											esUsageSummary.setC004ChargeCode(energySaleUsageSummary.getC004ChargeCode());
											esUsageSummary.setC004TotalCharge(energySaleUsageSummary.getC004TotalCharge());
											esUsageSummary.setC005ChargeCode(energySaleUsageSummary.getC005ChargeCode());
											esUsageSummary.setC005TotalCharge(energySaleUsageSummary.getC005TotalCharge());
											esUsageSummary.setC006ChargeCode(energySaleUsageSummary.getC006ChargeCode());
											esUsageSummary.setC006TotalCharge(energySaleUsageSummary.getC006TotalCharge());
											esUsageSummary.setC007ChargeCode(energySaleUsageSummary.getC007ChargeCode());
											esUsageSummary.setC007TotalCharge(energySaleUsageSummary.getC007TotalCharge());
											esUsageSummary.setC008ChargeCode(energySaleUsageSummary.getC008ChargeCode());
											esUsageSummary.setC008TotalCharge(energySaleUsageSummary.getC008TotalCharge());
											
											dao.addEnergySaleUsageSummary(esUsageSummary);
											dao.updateEnergySale(id, energySale);
											energySaleChargeHelper.addEnergySaleCharge(esUsageSummary);
											
									 }
										

								 }

									
							 }
							 if(tradeRelationshipList.isEmpty()) {
								 EnergySaleUsageSummary esUsageSummaryInvalidDataTrade = new EnergySaleUsageSummary();
									
									esUsageSummaryInvalidDataTrade.setBuyerCompanyServiceNumber(energySaleUsageSummary.getBuyerCompanyServiceNumber());
									
									esUsageSummaryInvalidDataTrade.setRemarks("TradeRelationship Does not Exit");
									esUsageSummaryInvalidDataTrade.setC1(energySaleUsageSummary.getC1());
									esUsageSummaryInvalidDataTrade.setC2(energySaleUsageSummary.getC2());
									esUsageSummaryInvalidDataTrade.setC3(energySaleUsageSummary.getC3());
									esUsageSummaryInvalidDataTrade.setC4(energySaleUsageSummary.getC4());
									esUsageSummaryInvalidDataTrade.setC5(energySaleUsageSummary.getC5());
									
									esUsageSummaryInvalidDataList.add(esUsageSummaryInvalidDataTrade);
							 
							 }
							 
						}
					}
					
			
				}
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				System.out.println("exception");
				e.printStackTrace();
			}		
			
		}
		energySale = dao.getEnergySaleById(id);
		energySale.setInvalidDataList(esUsageSummaryInvalidDataList);
		return energySale;
	}
}
