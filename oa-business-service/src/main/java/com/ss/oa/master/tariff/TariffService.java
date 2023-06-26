package com.ss.oa.master.tariff;
import java.sql.Date;
import java.util.List;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.vo.Tariff;

@Component
@Scope("prototype")
public class TariffService {
	
	@Autowired
	TariffDao dao;
	
	public TariffService(){
		super();
	}
	public List<Tariff> getAllTariff(Map<String,Date> criteria)
	{
		return dao.getAllTariffs(criteria);
	} 
	public Tariff getTariffById(String id)
	{
		return dao.getTariffById(id);
	}
	public String addTariff(Tariff tariff)
	{
		return dao.addTariff(tariff);
	}
	public String updateTariff(String id,Tariff tariff)
	{
		return dao.updateTariff(id, tariff);
	}
	public String deleteTariff(String id)
	{
		return dao.deleteTariff(id);
	}
}
