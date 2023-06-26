package com.ss.oa.transaction.noc;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Noc;

@Scope("prototype")
public interface NocDao {
	
	public abstract List<Noc> getAllNocs(Map<String,String> criteria);
	public abstract Noc getNocById(String id);
	public abstract String addNoc(Noc noc);
	public abstract String updateNoc(String id,Noc noc);
}
