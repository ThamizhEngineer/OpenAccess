package com.ss.oa.ledger.energyledger;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.ledger.vo.EnergyLedger;

@Component
@Scope("prototype")
public class EnergyLedgerService {
	@Autowired
	EnergyLedgerDao dao;
	
	public EnergyLedgerService() {
		super();
	}
	
	public List<EnergyLedger> getAllEnergyLedgers(Map<String, String> criteria) {
		
		return dao.getAllEnergyLedgers(criteria);
	}
	
	public EnergyLedger getEnergyLedgerById(String id) {
		
		return dao.getEnergyLedgerById(id);
	}
	
	public String addEnergyLedger(EnergyLedger energyLedger) {
	
		return dao.addEnergyLedger(energyLedger);
	}
	
	public String updateEnergyLedger(String id, EnergyLedger energyLedger) {

		return dao.updateEnergyLedger(id, energyLedger);
	}

}
