package com.ss.oa.transaction.energyallotment;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ss.oa.master.traderelationship.TradeRelationshipDao;
import com.ss.oa.master.vo.TradeRelationship;
import com.ss.oa.transaction.generationstatement.GenerationStatementDao;
import com.ss.oa.transaction.generationstatement.GenerationStatementService;
import com.ss.oa.transaction.vo.EnergyAllotment;
import com.ss.oa.transaction.vo.EsCharge;
import com.ss.oa.transaction.vo.EsUsageDetail;
import com.ss.oa.transaction.vo.EsUsageSummary;
import com.ss.oa.transaction.vo.GenerationStatement;
import com.ss.oa.transaction.vo.GenerationStatementCharge;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;

import oracle.jdbc.OracleTypes;

@Service
@Async("asyncThreadPoolTaskExecutor")
public class EnergyAllotmentHelper {
	
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private DataSource dataSource;
	@Autowired
	EnergyAllotmentService energyAllotmentService;
	@Autowired
	EnergyAllotmentRepository energyAllotmentRepository;
	@Autowired
	EsUsageDetailRepository esUsageDetailRepository;
	@Autowired
	EsUsageSummaryRepository esUsageSummaryRepository;
	@Autowired
	EsChargeRepository esChargeRepository;
	@Autowired
	GenerationStatementService gsService;
	@Autowired
	GenerationStatementDao dao;
	@Autowired
	TradeRelationshipDao trService;
	@Autowired
	private CommonUtils commonUtils;
	
	String energySaleId="";
	
	Connection conn1 = null;
	CallableStatement stmt1 = null;
	

	
	private Float totalesC001=0f;private Float totalesC002=0f;private Float totalesC003=0f;private Float totalesC004=0f;
	private Float totalesC005=0f;private Float totalesC006=0f;private Float totalesC007=0f;private Float totalesC008=0f;
	private Float totalesC009=0f;
	
	private Float totalgsC001=0f;private Float totalgsC002=0f;private Float totalgsC003=0f;private Float totalgsC004=0f;
	private Float totalgsC005=0f;private Float totalgsC006=0f;private Float totalgsC007=0f;private Float totalgsC008=0f;
	private Float totalgsC009=0f;
	
	
//	----Validation Starts-----------------------------------------------------------------
	
	public EnergyAllotment processDataForUpdation(String id, EnergyAllotment energyAllotment) {

		Map<String, Float> esTotalCharges = new HashMap<String, Float>();
		Map<String, Float> gsCharges = new HashMap<String, Float>();
		Map<String, Float> esUnits = new HashMap<String, Float>();
		Map<String, Float> esAvailUnits = new HashMap<String, Float>();
		energyAllotment.setEnergyAllotmentStatus("Applied");
		System.out.println("processDataForUpdation start - energyAllotment-"+energyAllotment);
		if (energyAllotment.getStatusCode() != null && energyAllotment.getStatusCode().equals("APPROVED")) {
			throw new OpenAccessException("Energy Allotment Already Completed- Cannot Update EnergySale");
		} else {
//			if (energyAllotment != null) {
//				energyAllotment.setEnergyAllotmentStatus("Charges Fetching Data");
//				esTotalCharges = fetchEsTotalCharges(energyAllotment);
//				if (!energyAllotment.getGenerationStatementId().isEmpty()) {
//					gsCharges = fetchGsCharges(energyAllotment.getGenerationStatementId());
//				}
//				if (energyAllotment.getEnergyAllotmentStatus().equals("Charges Fetching Completed")) {
//					energyAllotment = validateCharges(esTotalCharges, gsCharges, energyAllotment);
//				}
//			}
//			if (energyAllotment.getEnergyAllotmentStatus().equals("Charges Validation Completed")) {
//				if (energyAllotment != null && energyAllotment.getFuelGroupe().equals("RE")) {
//					energyAllotment.setEnergyAllotmentStatus("Units Fetching Data");
//					esUnits = fetchEsUnits(energyAllotment);
//					esAvailUnits = fetchEsAvailUnits(energyAllotment);
//					if (energyAllotment.getEnergyAllotmentStatus().equals("Units Fetching Completed")) {
//						energyAllotment = validateWindUnits(esUnits, esAvailUnits, energyAllotment);
//					}
//				}
//				if (energyAllotment != null && !energyAllotment.getFuelGroupe().equals("RE")) {
//					if (energyAllotment.getEnergyAllotmentStatus().equals("Charges Validation Completed")) {
//						energyAllotment = validateFossilUnits(energyAllotment);
//					}
//				}
//			}
//			System.out.println("processDataForUpdation before save - energyAllotment-"+energyAllotment);
//			if (energyAllotment.getEnergyAllotmentStatus().equals("Units Validation Completed")) {
//				energyAllotment.setEnergyAllotmentStatus("CleanRecord");
//				energyAllotmentRepository.save(energyAllotment);
//			}
			
			//commented out the above validations as its confusing the user
			energyAllotmentRepository.save(energyAllotment);
		}
		return energyAllotment;
	}
	
	
//	------------------delete by id-----------------------
	
	public String deleteFunc(String id,EnergyAllotment energyAllotment) {
		GenerationStatement gs = gsService.getGenerationStatementById(energyAllotment.getGenerationStatementId());
		if(energyAllotment.getEsCharges()!=null) {
			for(EsCharge esCharge:energyAllotment.getEsCharges()) {
				if(esCharge.getId()!=null&&esCharge.getEnergySaleId().equals(energyAllotment.getId())) {
					esChargeRepository.delete(esCharge);}
			}}
		if(energyAllotment.getEsUsageSummaries()!=null) {
			for(EsUsageSummary esUsageSummary:energyAllotment.getEsUsageSummaries()) {
				if(esUsageSummary.getId()!=null&&esUsageSummary.getEnergySaleId().equals(energyAllotment.getId())) {
					esUsageSummaryRepository.delete(esUsageSummary);}
			}}
		if(energyAllotment.getEsUsageDetails()!=null) {
			for(EsUsageDetail esUsageDetail:energyAllotment.getEsUsageDetails()) {
				if(esUsageDetail.getId()!=null&&esUsageDetail.getEnergySaleId().equals(energyAllotment.getId())) {
					esUsageDetailRepository.delete(esUsageDetail);}
			}}
			gs.setStatusCode("CREATED");
			System.out.println(gs);
			dao.updateGenerationStatement(energyAllotment.getGenerationStatementId(),gs);
			energyAllotmentRepository.delete(energyAllotment);
			return "success";
			}
//    ------------delete by package------------------------
	public String callDeleteTransByService(String remarks,String serviceNumber,String readingMonth, String readingYear,String deleteLedger,String deleteEs,String deleteGs,String deleteMr) {
		deleteLedger="Y";deleteEs="Y";deleteGs="N";deleteMr="N";
		String deleteFunction = "{call DELETE_TXN.DELETE_BY_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		String res= "successful";
		
		try {
		        conn1 = dataSource.getConnection();
		        stmt1 = conn1.prepareCall(deleteFunction);
		        stmt1.setString(1, remarks);
		        stmt1.setString(2, serviceNumber);
		        stmt1.setString(3, readingMonth);
		        stmt1.setString(4, readingYear);
		        stmt1.setString(5, deleteLedger);
		        stmt1.setString(6, deleteEs);
		        stmt1.setString(7, deleteGs);
		        stmt1.setString(8, deleteMr);
		        stmt1.registerOutParameter(9, OracleTypes.VARCHAR);
		        stmt1.registerOutParameter(10, OracleTypes.VARCHAR);
		        stmt1.execute();
		        res= stmt1.getString(8);
		        log.info("Out-8-"+res);
		        conn1.close();
		      
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if((stmt1!= null) && (!stmt1.isClosed())) {
					stmt1.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if((conn1!= null) && (!conn1.isClosed())) {
					conn1.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			return res;
	}
	
//	----Formatting Charge data for validation-------------------------------------------------------------------
	
	private Map<String, Float> fetchEsTotalCharges(EnergyAllotment energyAllotment) {
		Map<String, Float> esChargeTotal = new  HashMap<String, Float>();
		if(energyAllotment.getEnergyAllotmentStatus().equals("Charges Fetching Data")) {
		for (EsCharge esCharge : energyAllotment.getEsCharges()) {
			if (esCharge.getChargeCode().equals("C001")) {
				totalesC001 = totalesC001 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC001", totalesC001);
			}else if(esCharge.getChargeCode().equals("C002")){
				totalesC002 = totalesC002+ Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC002", totalesC002);
			}else if(esCharge.getChargeCode().equals("C003")) {
				totalesC003 = totalesC003 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC003", totalesC003);
			}else if(esCharge.getChargeCode().equals("C004")) {
				totalesC004 = totalesC004 +Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC004", totalesC004);
			}else if(esCharge.getChargeCode().equals("C005")) {
				totalesC005 = totalesC005 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC005", totalesC005);
			}else if(esCharge.getChargeCode().equals("C006")) {
				totalesC006 = totalesC006 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC006", totalesC006);
			}else if(esCharge.getChargeCode().equals("C007")) {
				totalesC007 = totalesC007 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC007", totalesC007);
			}else if(esCharge.getChargeCode().equals("C008")) {
				totalesC008 = totalesC008 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC008", totalesC008);
			}else if(esCharge.getChargeCode().equals("C009")) {
				totalesC009 = totalesC009 + Float.parseFloat(esCharge.getTotalCharge());
				esChargeTotal.put("totalesC009", totalesC009);
			}}
			energyAllotment.setEnergyAllotmentStatus("Charges Fetching Completed");}
		else {energyAllotment.setEnergyAllotmentStatus("Charges Fetching Faileure");}
			
		 return esChargeTotal;
	}
	
	private Map<String, Float> fetchGsCharges(String gsId) {
		GenerationStatement gs = gsService.getGenerationStatementById(gsId);
		Map<String, Float> gsChargeTotal = new  HashMap<String, Float>();
		for(GenerationStatementCharge gsCharge:gs.getGenerationStatementCharges()) {
			if(gsCharge.getChargeCode().equals("C001")) {
				totalgsC001= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC001", totalgsC001);
			}else if(gsCharge.getChargeCode().equals("C002")){
				totalgsC002= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC002", totalgsC002);
			}else if(gsCharge.getChargeCode().equals("C003")){
				totalgsC003= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC003", totalgsC003);
			}else if(gsCharge.getChargeCode().equals("C004")){
				totalgsC004= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC004", totalgsC004);
			}else if(gsCharge.getChargeCode().equals("C005")){
				totalgsC005= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC005", totalgsC005);
			}else if(gsCharge.getChargeCode().equals("C006")){
				totalgsC006= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC006", totalgsC006);
			}else if(gsCharge.getChargeCode().equals("C007")){
				totalgsC007= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC007", totalgsC007);
			}else if(gsCharge.getChargeCode().equals("C008")){
				totalgsC008= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC008", totalgsC008);
			}else if(gsCharge.getChargeCode().equals("C009")){
				totalgsC009= Float.parseFloat(gsCharge.getTotalCharges());
				gsChargeTotal.put("totalgsC009", totalgsC009);
			}}
		return gsChargeTotal;
	}

//	-----Validate--------------------------------------------------
	
	private EnergyAllotment validateCharges(Map<String, Float> esChargeTotal,Map<String, Float> gsChargeTotal, EnergyAllotment energyAllotment) {
		Map<String, String> chargeErrorCodes = fetchChargeErrorCodes();
			if(energyAllotment.getEnergyAllotmentStatus().equals("Charges Fetching Completed")) {
				energyAllotment.setEnergyAllotmentStatus("Charges Validation Started");}
			if(energyAllotment.getEnergyAllotmentStatus().equals("Charges Validation Started")) {
				for(EsCharge esCharge:energyAllotment.getEsCharges()) {
					if(esCharge.getChargeCode().equals("C001")) {
						if(esChargeTotal.get("totalesC001").equals(gsChargeTotal.get("totalgsC001"))) {
							esCharge.setResultCode("C001-Success");
							esCharge.setResultDesc("ValidC001Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C001-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C001"));}}
					if(esCharge.getChargeCode().equals("C002")) {
						if(esChargeTotal.get("totalesC002").equals(gsChargeTotal.get("totalgsC002"))) {
							esCharge.setResultCode("C002-Success");
							esCharge.setResultDesc("ValidC002Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C002-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C002"));}}
					if(esCharge.getChargeCode().equals("C003")) {
						if(esChargeTotal.get("totalesC003").equals(gsChargeTotal.get("totalgsC003"))) {
							esCharge.setResultCode("C003-Success");
							esCharge.setResultDesc("ValidC003Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C003-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C003"));}}
					if(esCharge.getChargeCode().equals("C004")) {
						if(esChargeTotal.get("totalesC004").equals(gsChargeTotal.get("totalgsC004"))) {
							esCharge.setResultCode("C004-Success");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C004-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C004"));}}
					if(esCharge.getChargeCode().equals("C005")) {
						if(esChargeTotal.get("totalesC005").equals(gsChargeTotal.get("totalgsC005"))) {
							esCharge.setResultCode("C005-Success");
							esCharge.setResultDesc("ValidC005Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C005-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C005"));}}
					if(esCharge.getChargeCode().equals("C006")) {
						if(esChargeTotal.get("totalesC006").equals(gsChargeTotal.get("totalgsC006"))) {
							esCharge.setResultCode("C006-Success");
							esCharge.setResultDesc("ValidC006Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C006-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C006"));}}
					if(esCharge.getChargeCode().equals("C007")) {
						if(esChargeTotal.get("totalesC007").equals(gsChargeTotal.get("totalgsC007"))) {
							esCharge.setResultCode("C007-Success");
							esCharge.setResultDesc("ValidC007Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C007-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C007"));}}
					if(esCharge.getChargeCode().equals("C008")) {
						if(esChargeTotal.get("totalesC008").equals(gsChargeTotal.get("totalgsC008"))) {
							esCharge.setResultCode("C008-Success");
							esCharge.setResultDesc("ValidC008Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C008-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C008"));}}
					if(esCharge.getChargeCode().equals("C009")) {
						if(esChargeTotal.get("totalesC009").equals(gsChargeTotal.get("totalgsC009"))) {
							esCharge.setResultCode("C009-Success");
							esCharge.setResultDesc("ValidC009Data");}
						else {
							esCharge.setTotalCharge("0");
							esCharge.setResultCode("C009-Failure");
							esCharge.setResultDesc(chargeErrorCodes.get("C009"));}}
					} 
				energyAllotment.setEnergyAllotmentStatus("Charges Validation Completed");}
			else {energyAllotment.setEnergyAllotmentStatus("Charges Validation Faileure");}
				return energyAllotment;
				}
	
// ---- Formatting Unit data for validation (wind)-------------------------------------------------------------------
	
	private Map<String, Float> fetchEsUnits (EnergyAllotment energyAllotment) {
		Map<String, Float> esTotalUnits = new  HashMap<String, Float>();
		System.out.println("esunits--"+esTotalUnits);
		Float totalC1=0f;Float totalC2=0f;Float totalC3=0f;Float totalC4=0f;Float totalC5=0f;
		if(energyAllotment.getEnergyAllotmentStatus().equals("Units Fetching Data")) {
		for(EsUsageSummary esUsageSummary:energyAllotment.getEsUsageSummaries()) {
			if(esUsageSummary.getC1()!=null) {
				totalC1 = totalC1 + Float.parseFloat(esUsageSummary.getC1());
				esTotalUnits.put("totalC1", totalC1);}
			else {totalC1=0f;} 
			 if (esUsageSummary.getC2()!=null) {
				totalC2 = totalC2 + Float.parseFloat(esUsageSummary.getC2());
				esTotalUnits.put("totalC2", totalC2);}
			 else {totalC2=0f;} 
			 if (esUsageSummary.getC3()!=null) {
				totalC3 = totalC3 + Float.parseFloat(esUsageSummary.getC3());
				esTotalUnits.put("totalC3", totalC3);}
			 else {totalC3=0f;} 
			 if (esUsageSummary.getC4()!=null) {
				totalC4 = totalC4 + Float.parseFloat(esUsageSummary.getC4());
				esTotalUnits.put("totalC4", totalC4);}
			 else {totalC4=0f;} 
			 if (esUsageSummary.getC5()!=null) {
				totalC5 = totalC5 + Float.parseFloat(esUsageSummary.getC5());
				esTotalUnits.put("totalC5", totalC5);}
			 else {totalC5=0f;} 
		}
		 energyAllotment.setEnergyAllotmentStatus("Units Fetching Completed");}
		else {energyAllotment.setEnergyAllotmentStatus("Units Fetching Faileure");}
		System.out.println("esunits--"+esTotalUnits);
	return esTotalUnits;
	}
	
	private Map<String, Float> fetchEsAvailUnits(EnergyAllotment energyAllotment){
		Map<String, Float> esAvailTotalUnits= new HashMap<String, Float>();
		System.out.println("esAvailTotalUnits--"+esAvailTotalUnits);
		Float availBC1=0f; Float aBC1=0f;Float availBC2=0f; Float aBC2=0f;Float availBC3=0f; Float aBC3=0f;Float availBC4=0f; Float aBC4=0f;Float availBC5=0f; Float aBC5=0f;
		Float totalAvailC1=0f;Float totalAvailC2=0f;Float totalAvailC3=0f;Float totalAvailC4=0f;Float totalAvailC5=0f;
		if(energyAllotment.getC1()!=null) {
			availBC1 = commonUtils.round(energyAllotment.getAvailBc1());
			totalAvailC1=Float.parseFloat(energyAllotment.getAvailc1())+(availBC1);
			esAvailTotalUnits.put("totalAvailC1", totalAvailC1);}
		else {totalAvailC1=0f;}
		if(energyAllotment.getC2()!=null) {
			availBC2 = commonUtils.round(energyAllotment.getAvailBc2());
			totalAvailC2=Float.parseFloat(energyAllotment.getAvailc2())+(availBC2);
			esAvailTotalUnits.put("totalAvailC2", totalAvailC2);}
		else {totalAvailC2=0f;}
		if(energyAllotment.getC3()!=null) {
			availBC3 = commonUtils.round(energyAllotment.getAvailBc3());
			totalAvailC3=Float.parseFloat(energyAllotment.getAvailc3())+(availBC3);
			esAvailTotalUnits.put("totalAvailC3", totalAvailC3);}
		else {totalAvailC3=0f;}
		if(energyAllotment.getC4()!=null) {
			availBC4 = commonUtils.round(energyAllotment.getAvailBc4());
			totalAvailC4=Float.parseFloat(energyAllotment.getAvailc4())+(availBC4);
			esAvailTotalUnits.put("totalAvailC4", totalAvailC4);}
		else {totalAvailC4=0f;}
		if(energyAllotment.getC5()!=null) {
			availBC5 = commonUtils.round(energyAllotment.getAvailBc5());
			totalAvailC5=Float.parseFloat(energyAllotment.getAvailc5())+(availBC5);
			esAvailTotalUnits.put("totalAvailC5", totalAvailC5);}
		else {totalAvailC5=0f;}
		System.out.println("esAvailTotalUnits--"+esAvailTotalUnits);
		return esAvailTotalUnits;
	}
	
		
//	-----Units Validation For wind------------------------------------------------------
	
	private EnergyAllotment validateWindUnits (Map<String, Float> esTotalUnits,Map<String, Float> esAvailTotalUnits,EnergyAllotment energyAllotment) {
		Map<String, String> unitsErrorCodes = fetchUnitsErrorCodes();
		if(energyAllotment.getEnergyAllotmentStatus().equals("Units Fetching Completed")) {
			energyAllotment.setEnergyAllotmentStatus("Units Validation Started");}
		if(energyAllotment.getEnergyAllotmentStatus().equals("Units Validation Started")) {
			for(EsUsageSummary esUsageSummary:energyAllotment.getEsUsageSummaries()) {
				if(esUsageSummary.getC1()!=null) {
				if(esTotalUnits.get("totalC1")<=esAvailTotalUnits.get("totalAvailC1")) {					
					esUsageSummary.setResultCode("C1-Success");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC1Data");}	
				else {
					esUsageSummary.setC1("0");
					esUsageSummary.setResultCode("C1-Failure");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C1"));}}
				if(esUsageSummary.getC2()!=null) {
				if(esTotalUnits.get("totalC2")<=esAvailTotalUnits.get("totalAvailC2")) {
					esUsageSummary.setResultCode("C2-Success");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC2Data");}	
				else {
					esUsageSummary.setC2("0");
					esUsageSummary.setResultCode("C2-Failure");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C2"));}}
				if(esUsageSummary.getC3()!=null) {
				if(esTotalUnits.get("totalC3")<=esAvailTotalUnits.get("totalAvailC3")) {
					esUsageSummary.setResultCode("C3-Success");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC3Data");}	
				else {energyAllotment.setEnergyAllotmentStatus("Units Validation Completed");
					esUsageSummary.setC3("0");
					esUsageSummary.setResultCode("C3-Failure");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C3"));}}
				if(esUsageSummary.getC4()!=null) {
				if(esTotalUnits.get("totalC4")<=esAvailTotalUnits.get("totalAvailC4")) {
					esUsageSummary.setResultCode("C4-Success");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC4Data");}	
				else {
					esUsageSummary.setC4("0");
					esUsageSummary.setResultCode("C4-Failure");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C4"));}}
				if(esUsageSummary.getC5()!=null) {
				if(esTotalUnits.get("totalC5")<=esAvailTotalUnits.get("totalAvailC5")) {
					esUsageSummary.setResultCode("C5-Success");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC5Data");}	
				else {
					esUsageSummary.setC5("0");
					esUsageSummary.setResultCode("C5-Failure");
					esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C5"));}}
			}
			energyAllotment.setEnergyAllotmentStatus("Units Validation Completed");}
		else {energyAllotment.setEnergyAllotmentStatus("Units Validation Faileure");}
			return energyAllotment;
		}
	
//	-------units validation for fossil-fuel-----------------------------------
	
	private EnergyAllotment validateFossilUnits (EnergyAllotment energyAllotment){
		Map<String, String> unitsErrorCodes = fetchUnitsErrorCodes(); 
		
		if(energyAllotment.getEnergyAllotmentStatus().equals("Charges Validation Completed")) {
			energyAllotment.setEnergyAllotmentStatus("Units Validation Started");
			}
		
		if(energyAllotment.getEnergyAllotmentStatus().equals("Units Validation Started")) {
		 for(EsUsageSummary esUsageSummary:energyAllotment.getEsUsageSummaries()) {
			 
			 TradeRelationship trade=trService.getTradeRelationshipById(esUsageSummary.getTradeRelationshipId());
			 System.out.println(energyAllotment.getEnergyAllotmentStatus());
			 
			 if(esUsageSummary.getBuyerCompanyServiceId().equals(trade.getBuyerCompServiceId())) {
				 if(esUsageSummary.getC1()!=null) {
				  if(convertTheValue(esUsageSummary.getC1())<=convertTheValue(trade.getC1())) {
					 esUsageSummary.setResultCode("C1-Success");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC1Data");
					 }	
				 else {
					 esUsageSummary.setC1("0");
					 esUsageSummary.setResultCode("C1-Failure");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C1"));}}
				 if(esUsageSummary.getC2()!=null) {
				  if(convertTheValue(esUsageSummary.getC2())<=convertTheValue(trade.getC2())) {
					 esUsageSummary.setResultCode("C2-Success");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC2Data");
					 }	
				  else {
					 esUsageSummary.setC2("0");
					 esUsageSummary.setResultCode("C2-Failure");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C2"));
					 }}
				 if(esUsageSummary.getC3()!=null) {
				  if(convertTheValue(esUsageSummary.getC3())<=convertTheValue(trade.getC3())) {
					 esUsageSummary.setResultCode("C3-Success");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC3Data");
					 }	
				  else {
					 esUsageSummary.setC3("0");
					 esUsageSummary.setResultCode("C3-Failure");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C3"));}}
				 if(esUsageSummary.getC4()!=null) {
				   if(convertTheValue(esUsageSummary.getC4())<=convertTheValue(trade.getC4())) {
					 esUsageSummary.setResultCode("C4-Success");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC4Data");
					 }	
				  else {
					 esUsageSummary.setC4("0");
					 esUsageSummary.setResultCode("C4-Failure");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C4"));}}
				 if(esUsageSummary.getC5()!=null) {
				   if(convertTheValue(esUsageSummary.getC5())<=convertTheValue(trade.getC5())) {
					 esUsageSummary.setResultCode("C5-Success");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+"ValidC5Data");
					 }	
				 else {
					 esUsageSummary.setC5("0");
					 esUsageSummary.setResultCode("C5-Failure");
					 esUsageSummary.setResultDesc(resultCheck(esUsageSummary.getResultDesc())+unitsErrorCodes.get("C5"));}}
				 }
			 }
		 energyAllotment.setEnergyAllotmentStatus("Units Validation Completed");}
		else {
			energyAllotment.setEnergyAllotmentStatus("Units Validation Failure");}
		return energyAllotment;
		}
	
//	-----Null&empty check-----------------------------------------------------------------
	
	  public static boolean isNullOrEmpty(String str) {
	        if(str != null && !str.trim().isEmpty())
	            return false;
	        return true;    
	    }	
	  
	  public Float convertTheValue(String input) {
		  Float output=0f;
		  if(!input.isEmpty()||!input.contains(null)) {
			  output = Float.parseFloat(input);
			  return output;
		  }
		  else {
			  return output;
		  }
		}
	  
	  private String resultCheck(String inputResult) {
		  String outputResult="";
	        if(inputResult != null && !inputResult.trim().isEmpty()) {
			  return inputResult;
		  }
	        else {
		  return "error-"+outputResult;
	        }
	  }
	
	
//	----Maps For Validations-------------------------------------------------------------
	
	private  Map<String, String> fetchChargeErrorCodes(){
	       Map<String, String> chargeErrorCodes = new HashMap<String, String>();
	chargeErrorCodes.put("C001", "error in Meter reading charges - C001");
	chargeErrorCodes.put("C002", "error in O&M Charges - C002");
	chargeErrorCodes.put("C003", "error in Transmission Charges - C003");
	chargeErrorCodes.put("C004", "error in System Operation Charges - C004");
	chargeErrorCodes.put("C005", "error in RKvah Penalty - C005");
	chargeErrorCodes.put("C006", "error in Negative Energy Charges - C006");
	chargeErrorCodes.put("C007", "error in Scheduling Charges - C007");
	chargeErrorCodes.put("C008", "error in Other Charges - C008");
	chargeErrorCodes.put("C009", "error in Parallel Operation Charges - C009");
	return chargeErrorCodes;
	}

	private  Map<String, String> fetchUnitsErrorCodes(){
	       Map<String, String> unitsErrorCodes = new HashMap<String, String>();
	unitsErrorCodes.put("C1", "Allocated C1 is greater than available C1");
	unitsErrorCodes.put("C2", "Allocated C2 is greater than available C2");
	unitsErrorCodes.put("C3", "Allocated C3 is greater than available C3");
	unitsErrorCodes.put("C4", "Allocated C4 is greater than available C4");
	unitsErrorCodes.put("C5", "Allocated C5 is greater than available C5");
	return unitsErrorCodes;
	}
	
}
