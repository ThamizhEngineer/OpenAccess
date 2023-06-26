package com.ss.oa.integration.energyadjustments;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.EnergyAdjustment;
import com.ss.oa.vo.EnergyAdjustmentForDAO;
import com.ss.oa.vo.EnergySource;
import com.ss.oa.vo.Unit;
import com.ss.oa.vo.Charge;
@Component
public class EnergyAdjustmentService {
	Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	@Autowired
	EnergyAdjustmentDao dao;
	
	public EnergyAdjustmentService(){
		super();
	}
	

	/* LOGIC - getAllEnergyAdjustments
	 * 
	firstRecord = true;
		 
	 In Loop thru DAOResults
		 set currentBuyerId only from dao
		 if prevBuyerId is different than currentBuyerId
		 		 if firstRecord
		 		 	set firstRecord = false
		 		 	set the values for currentEA from DAO
		 		 else
		 		 	add currentEA to eaList
				 	set prevBuyerId = currentBuyerId
				 	set the values for currentEA from DAO
		create new object for EnergySource
		set the values for EnergySource from DAO
		add the energySource to currentEA
	End Loop
	
	if dao had records
		add it to the eaList
	*/
	
	public List<EnergyAdjustment> getAllEnergyAdjustments(Map<String,String> criteria)
	{
		List<EnergyAdjustment> energyAdjustments = new ArrayList<EnergyAdjustment>();
		List<EnergyAdjustmentForDAO> energyAdjustmentForDAOs = dao.getAllEnergyAdjustments(criteria);
//		System.out.println("energyAdjustmentForDAOs");
//		System.out.println(energyAdjustmentForDAOs);
		String currentBuyerId = "";
		String prevBuyerId = "";
		int sequence = 1;
		EnergyAdjustment currentEA = new EnergyAdjustment();
		Boolean firstRecord =true;
		for (EnergyAdjustmentForDAO eaDAO : energyAdjustmentForDAOs) {
//			System.out.println("eaDAO");
//			System.out.println(eaDAO);
			
			currentBuyerId = eaDAO.getServiceNumber();
			if(!prevBuyerId.equalsIgnoreCase(currentBuyerId)){
				if(firstRecord){
					firstRecord = false;
					currentEA.setServiceNumber(eaDAO.getServiceNumber());
					currentEA.setOrgCode(eaDAO.getOrgCode());
					currentEA.setOrgName(eaDAO.getOrgName());
					currentEA.setBuyerName(eaDAO.getBuyerName());
					currentEA.setBillingMonth(eaDAO.getBillingMonth());
					currentEA.setBillingYear(eaDAO.getBillingYear());
				}
				else {
					energyAdjustments.add(currentEA);
					prevBuyerId = currentBuyerId;
					sequence = 1;
					currentEA = new EnergyAdjustment();
					currentEA.setServiceNumber(eaDAO.getServiceNumber());
					currentEA.setOrgCode(eaDAO.getOrgCode());
					currentEA.setOrgName(eaDAO.getOrgName());
					currentEA.setBuyerName(eaDAO.getBuyerName());
					currentEA.setBillingMonth(eaDAO.getBillingMonth());
					currentEA.setBillingYear(eaDAO.getBillingYear());
					
				}
			}
			currentEA.getEnergySources().add(getEnergySource(eaDAO));
			
			sequence++;
	
		}
		if(energyAdjustmentForDAOs!=null && energyAdjustmentForDAOs.size()>0){
			energyAdjustments.add(currentEA);
		}
		return energyAdjustments;
	}
	
	private String nullChecks(String invalue) {
		// null check
		String newValue="0";
		if(invalue == null || invalue.isEmpty()) {
			newValue = "0";
		}else {
//			newValue = invalue.trim().replaceAll("\\s+", " ");
			newValue = invalue.replaceAll("[^a-zA-Z0-9 -]", "");

		}
		return newValue;
			
	}
	
	private EnergySource getEnergySource(EnergyAdjustmentForDAO eaDAO) {
		Float drC1 = (Float.parseFloat(eaDAO.getDr_c1()));
		Float drC2 = (Float.parseFloat(eaDAO.getDr_c2()));
		Float drC3 = (Float.parseFloat(eaDAO.getDr_c3()));
		Float drC4 = (Float.parseFloat(eaDAO.getDr_c4()));
		Float drC5 = (Float.parseFloat(eaDAO.getDr_c5()));
		Integer roundDr1=Math.round(drC1);
		Integer roundDr2=Math.round(drC2);
		Integer roundDr3=Math.round(drC3);
		Integer roundDr4=Math.round(drC4);
		Integer roundDr5=Math.round(drC5);

		Unit c1 = new Unit(nullChecks(eaDAO.getInj_c1()), nullChecks(eaDAO.getTl_c1()), nullChecks(eaDAO.getDl_c1()), nullChecks(eaDAO.getL_c1()), nullChecks(roundDr1.toString()));
		Unit c2 = new Unit(nullChecks(eaDAO.getInj_c2()), nullChecks(eaDAO.getTl_c2()), nullChecks(eaDAO.getDl_c2()), nullChecks(eaDAO.getL_c2()), nullChecks(roundDr2.toString()));
		Unit c3 = new Unit(nullChecks(eaDAO.getInj_c3()), nullChecks(eaDAO.getTl_c3()), nullChecks(eaDAO.getDl_c3()), nullChecks(eaDAO.getL_c3()), nullChecks(roundDr3.toString()));
		Unit c4 = new Unit(nullChecks(eaDAO.getInj_c4()), nullChecks(eaDAO.getTl_c4()), nullChecks(eaDAO.getDl_c4()), nullChecks(eaDAO.getL_c4()), nullChecks(roundDr4.toString()));
		Unit c5 = new Unit(nullChecks(eaDAO.getInj_c5()), nullChecks(eaDAO.getTl_c5()), nullChecks(eaDAO.getDl_c5()), nullChecks(eaDAO.getL_c5()), nullChecks(roundDr5.toString()));
		log.info("c1-"+c1+"c2-"+c2+"c3-"+c3+"c4-"+c4+"c5-"+c5);

		List<Charge> charges = new ArrayList<Charge>();
		charges.add(new Charge(eaDAO.getC001_CHARGE_CODE(), eaDAO.getC001_CHARGE_DESC(), eaDAO.getC001_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC002_CHARGE_CODE(), eaDAO.getC002_CHARGE_DESC(), eaDAO.getC002_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC003_CHARGE_CODE(), eaDAO.getC003_CHARGE_DESC(), eaDAO.getC003_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC004_CHARGE_CODE(), eaDAO.getC004_CHARGE_DESC(), eaDAO.getC004_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC005_CHARGE_CODE(), eaDAO.getC005_CHARGE_DESC(), eaDAO.getC005_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC006_CHARGE_CODE(), eaDAO.getC006_CHARGE_DESC(), eaDAO.getC006_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC007_CHARGE_CODE(), eaDAO.getC007_CHARGE_DESC(), eaDAO.getC007_TOTAL_CHARGES()));
		charges.add(new Charge(eaDAO.getC008_CHARGE_CODE(), eaDAO.getC008_CHARGE_DESC(), eaDAO.getC008_TOTAL_CHARGES()));
		EnergySource energySource = new EnergySource("1",eaDAO.getSellerServiceNumber(),eaDAO.getSellerServiceName(),null,eaDAO.getAllowLowerSlotAdmt(),eaDAO.getCommissionDate(),eaDAO.getIsRec(),eaDAO.getAdjustmentPriority(), c1,c2,c3,c4,c5,charges);
		
		return energySource;
	}
}
