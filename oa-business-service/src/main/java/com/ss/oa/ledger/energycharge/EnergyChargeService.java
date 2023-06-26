package com.ss.oa.ledger.energycharge;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.ledger.vo.EnergyCharge;

@Component
@Scope("prototype")
public class EnergyChargeService {
	@Autowired
	EnergyChargeDao dao;
	
	public EnergyChargeService() {
		super();
	}

	public  List<EnergyCharge> getAllEnergyCharges(Map<String,String> criteria){
		return dao.getAllEnergyCharges(criteria);
	}
	
	public  EnergyCharge getEnergyChargeById(String id) {
		return dao.getEnergyChargeById(id);
	}

	public String addEnergyCharge(EnergyCharge  energyCharge ) {
		
		return dao.addEnergyCharge(energyCharge );
	}
	
	public String updateEnergyCharge(String energyChargeId, EnergyCharge  energyCharge) {
		
		return dao.updateEnergyCharge(energyChargeId, energyCharge);
	}
	
	
}
