package com.ss.oa.transaction.oaa;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.Oaa;

@Scope("prototype")
public interface OaaDao {
	
	public abstract List<Oaa> getAllOaas(Map<String,String> criteria);
	public abstract Oaa getOaaById(String id);
	public abstract String addOaa(Oaa oaa);
	public abstract String updateOaa(String id, Oaa oaa);
}
