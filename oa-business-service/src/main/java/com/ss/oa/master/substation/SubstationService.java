package com.ss.oa.master.substation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.vo.Substation;
@Component
@Scope("prototype")
public class SubstationService {
	@Autowired
	SubstationDao dao;
	public SubstationService(){
		super();
	}
	public List<Substation> getAllSubstations(Map<String,String> criteria)
	{
		return dao.getAllSubstations(criteria);
	} 
	public Substation getSubstationById(String id)
	{
		return dao.getSubstationById(id);
	}
	public String addSubstation(Substation substation)
	{
		return dao.addSubstation(substation);
	}
	public String updateSubstation(String id,Substation substation)
	{
		return dao.updateSubstation(id,substation);
	}
	public String deleteSubstation(String id)
	{
		return dao.deleteSubstation(id);
	}
}
