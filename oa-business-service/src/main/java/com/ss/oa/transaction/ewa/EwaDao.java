package com.ss.oa.transaction.ewa;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Ewa;
import com.ss.oa.transaction.vo.EwaLine;

@Scope("prototype")
public interface EwaDao {

	public abstract List<Ewa> getAllewas(Map<String,String> criteria);
	public abstract Ewa getewaById(String id);
	public abstract String addewa(Ewa ewa);
	public abstract String updateewa(String id ,Ewa ewa);
	public abstract String addewaline(EwaLine ewaline);
	public abstract String updateewaline(EwaLine ewaline);
	
	
}
