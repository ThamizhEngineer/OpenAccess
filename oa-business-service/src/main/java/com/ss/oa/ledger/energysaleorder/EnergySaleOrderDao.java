package com.ss.oa.ledger.energysaleorder;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.ledger.vo.EnergyCharge;
import com.ss.oa.ledger.vo.EnergySaleOrder;
import com.ss.oa.ledger.vo.EnergySaleOrderLine;
@Scope("prototype")
public interface EnergySaleOrderDao {
	
	public abstract List<EnergySaleOrder> getAllEnergySaleOrders(Map<String,String> criteria);
	public abstract List<EnergySaleOrder> getAllEnergySaleOrdersBuyers(Map<String,String> criteria);
	public abstract EnergySaleOrder getEnergySaleOrderById(String id);
	public abstract String addEnergySaleOrder(EnergySaleOrder energySaleOrder);
	public abstract String updateEnergySaleOrder(String id,EnergySaleOrder energySaleOrder);
	public abstract String addEnergySaleOrderLine(EnergySaleOrderLine energySaleOrderLine);
	public abstract String updateEnergySaleOrderLine(EnergySaleOrderLine energySaleOrderLine);
	public abstract String addEnergyCharge(EnergyCharge energyCharge);
	public abstract String updateEnergyCharge(EnergyCharge energyCharge);

}
