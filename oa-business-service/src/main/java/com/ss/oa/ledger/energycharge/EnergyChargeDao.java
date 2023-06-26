package com.ss.oa.ledger.energycharge;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.ledger.vo.EnergyCharge;
 

@Scope("prototype")
public interface EnergyChargeDao {
 
	public abstract List<EnergyCharge> getAllEnergyCharges(Map<String,String> criteria);
	public abstract EnergyCharge getEnergyChargeById(String id);
	public abstract String addEnergyCharge(EnergyCharge energyCharge);
	public abstract String updateEnergyCharge(String id,EnergyCharge energyCharge);

}
