package com.ss.oa.master.substation;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Substation;
@Scope("prototype")
public interface SubstationDao {
	public abstract List<Substation> getAllSubstations(Map<String, String> criteria);
	public abstract Substation getSubstationById(String id);
	public abstract String addSubstation(Substation substation);
	public abstract String updateSubstation(String id,Substation substation);
	public abstract String deleteSubstation(String id);
}
