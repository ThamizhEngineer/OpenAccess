package com.ss.oa.transaction.energyallotment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.oa.transaction.vo.EnergyAllotment;
import com.ss.oa.transaction.vo.EsCharge;
import com.ss.oa.transaction.vo.EsUsageSummary;

@Service
public class EaChargeHelper {
	@Autowired
	EsChargeRepository esChargeRepo;

	
	public EnergyAllotment updateEnergySaleCharge(EnergyAllotment energyAllotment) {
		Map<String,EsCharge> chargesToUpdate = new HashMap<String,EsCharge>(); 
		List<EsCharge> chargeListFrmDB = esChargeRepo.findAllByEnergySaleId(energyAllotment.getId());

		for (EsUsageSummary energySaleUsageSummary : energyAllotment.getEsUsageSummaries()) {

			for (EsCharge energySaleCharge : chargeListFrmDB) {
				   if(!chargesToUpdate.containsKey(energySaleCharge.getId()) && energySaleUsageSummary.getBuyerCompanyServiceId().equals(energySaleCharge.getCompanyServiceId())) {
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C001")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC001Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC001ChargeCode())==false && energySaleUsageSummary.getC001ChargeCode().equals("C001")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC001TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C002")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC002Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC002ChargeCode())==false && energySaleUsageSummary.getC002ChargeCode().equals("C002")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC002TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C003")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC003Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC003ChargeCode())==false && energySaleUsageSummary.getC003ChargeCode().equals("C003")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC003TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C004")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC004Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC004ChargeCode())==false && energySaleUsageSummary.getC004ChargeCode().equals("C004")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC004TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C005")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC005Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC005ChargeCode())==false && energySaleUsageSummary.getC005ChargeCode().equals("C005")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC005TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C006")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC006Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC006ChargeCode())==false && energySaleUsageSummary.getC006ChargeCode().equals("C006")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC006TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C007")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC007Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC007ChargeCode())==false && energySaleUsageSummary.getC007ChargeCode().equals("C007")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC007TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C008")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC008Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC008ChargeCode())==false && energySaleUsageSummary.getC008ChargeCode().equals("C008")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC008TotalCharge());
								}	
							}						
					   }
					   
					   if (isNullOrEmpty(energySaleCharge.getChargeCode())==false && energySaleCharge.getChargeCode().equals("C009")) {
							if(isNullOrEmpty(energySaleUsageSummary.getC009Id())==false) {
								if (isNullOrEmpty(energySaleUsageSummary.getC009ChargeCode())==false && energySaleUsageSummary.getC009ChargeCode().equals("C009")) {
									energySaleCharge.setTotalCharge(energySaleUsageSummary.getC009TotalCharge());
								}	
							}						
					   }
					   chargesToUpdate.put(energySaleCharge.getId(), energySaleCharge);
				   }
			}
		}
		esChargeRepo.saveAll(chargesToUpdate.values());
		
		//charges will still be update as part of allocatio save
		energyAllotment.getEsCharges().clear();
		energyAllotment.getEsCharges().addAll(chargesToUpdate.values());
		
		chargesToUpdate=null; chargeListFrmDB=null;
		return energyAllotment;
	}
	
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}
