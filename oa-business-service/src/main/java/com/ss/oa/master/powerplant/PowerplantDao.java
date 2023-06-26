package com.ss.oa.master.powerplant;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Generator;
import com.ss.oa.master.vo.Powerplant;
@Scope("prototype")
public interface PowerplantDao {
	public abstract List<Powerplant> getAllPowerplants(Map<String, String> criteria);
	public abstract Powerplant getPowerplantById(String id);
	public abstract String addPowerplant(Powerplant powerplant);
	public abstract String updatePowerplant(String id,Powerplant powerplant);
	public abstract String deletePowerplant(String id);
	public abstract String addGenerator(Generator generator);
	public abstract String updateGenerator(Generator generator);
}