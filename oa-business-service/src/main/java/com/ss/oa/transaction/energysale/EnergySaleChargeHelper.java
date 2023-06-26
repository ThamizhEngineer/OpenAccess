package com.ss.oa.transaction.energysale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.EnergySaleCharge;
import com.ss.oa.transaction.vo.EnergySaleUsageSummary;
@Component
@Scope("prototype")
public class EnergySaleChargeHelper {
	@Autowired
	EnergySaleDao dao;
	
	
	String energySaleChargeId="";
	
	public String updateEnergySaleCharge(EnergySaleUsageSummary energySaleUsageSummary) {
		EnergySaleCharge energySaleCharge = new EnergySaleCharge();
		energySaleCharge.setCompanyServiceId(energySaleUsageSummary.getBuyerCompanyServiceId());
		energySaleCharge.setEnergySaleId(energySaleUsageSummary.getEnergySaleId());
			
		energySaleCharge.setEnabled("Y");
		if(energySaleUsageSummary.getC001Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC001Id());
			energySaleCharge.setChargeCode("C001");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC001TotalCharge());
			
//			System.out.println(energySaleCharge);
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC002Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC002Id());
			energySaleCharge.setChargeCode("C002");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC002TotalCharge());	
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		
		if(energySaleUsageSummary.getC003Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC003Id());
			energySaleCharge.setChargeCode("C003");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC003TotalCharge());	
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC004Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC004Id());
			energySaleCharge.setChargeCode("C004");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC004TotalCharge());	
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC005Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC005Id());
			energySaleCharge.setChargeCode("C005");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC005TotalCharge());	
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC006Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC006Id());
			energySaleCharge.setChargeCode("C006");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC006TotalCharge());
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC007Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC007Id());
			energySaleCharge.setChargeCode("C007");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC007TotalCharge());
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC008Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC008Id());
			energySaleCharge.setChargeCode("C008");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC008TotalCharge());
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC009Id()!=null) {
			energySaleCharge.setId(energySaleUsageSummary.getC009Id());
			energySaleCharge.setChargeCode("C009");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC009TotalCharge());
			energySaleChargeId=dao.updateEnergySaleCharge(energySaleCharge);
		}
		return energySaleChargeId;
	}
	
	public String addEnergySaleCharge(EnergySaleUsageSummary energySaleUsageSummary) {
		EnergySaleCharge energySaleCharge = new EnergySaleCharge();
		energySaleCharge.setCompanyServiceId(energySaleUsageSummary.getBuyerCompanyServiceId());
		energySaleCharge.setEnergySaleId(energySaleUsageSummary.getEnergySaleId());
		energySaleCharge.setEnabled("Y");
		if(energySaleUsageSummary.getC001Id()==null) {	
			energySaleCharge.setChargeCode("C001");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC001TotalCharge());
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC002Id()==null) {
			energySaleCharge.setChargeCode("C002");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC002TotalCharge());
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC003Id()==null) {
			energySaleCharge.setChargeCode("C003");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC003TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC004Id()==null) {
			energySaleCharge.setChargeCode("C004");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC004TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC005Id()==null) {
			energySaleCharge.setChargeCode("C005");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC005TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC006Id()==null) {
			energySaleCharge.setChargeCode("C006");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC006TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC007Id()==null) {
			energySaleCharge.setChargeCode("C007");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC007TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC008Id()==null) {
			energySaleCharge.setChargeCode("C008");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC008TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		if(energySaleUsageSummary.getC009Id()==null) {
			energySaleCharge.setChargeCode("C009");
			energySaleCharge.setTotalCharge(energySaleUsageSummary.getC009TotalCharge());	
			energySaleChargeId=dao.addEnergySaleCharge(energySaleCharge);
		}
		return energySaleChargeId;
	}

}
