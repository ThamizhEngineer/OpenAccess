package com.ss.oa.master.tariff;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Tariff;
@Scope("prototype")
public interface TariffDao {
	public abstract List<Tariff> getAllTariffs(Map<String,Date> criteria);
	public abstract Tariff getTariffById(String id);
	public abstract String addTariff(Tariff tariff);
	public abstract String updateTariff(String id,Tariff tariff);
	public abstract String deleteTariff(String id);
}
