package com.ss.oa.transaction.epa;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Epa;
import com.ss.oa.transaction.vo.EpaLine;

@Scope("prototype")
public interface EpaDao {

	public abstract	List<Epa> getAllEpas(Map<String, String> criteria);

	public abstract Epa getEpaById(String id);

	public abstract String addEpaline(EpaLine epaline);

	public abstract  String addEpa(Epa epa);

	public abstract	String updateEpa(String id, Epa epa);

	public abstract	String updateEpaline(EpaLine epaline);

}
