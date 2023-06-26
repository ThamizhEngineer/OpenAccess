package com.ss.oa.ledger.energyledger;

import java.util.List;
import java.util.Map;

import com.ss.oa.ledger.vo.EnergyLedger;

public interface EnergyLedgerDao {
	
	public abstract List<EnergyLedger> getAllEnergyLedgers(Map<String,String> criteria);
	public abstract EnergyLedger getEnergyLedgerById(String id);
	public abstract String addEnergyLedger(EnergyLedger energyLedger);
	public abstract String updateEnergyLedger(String id,EnergyLedger energyLedger);

}
