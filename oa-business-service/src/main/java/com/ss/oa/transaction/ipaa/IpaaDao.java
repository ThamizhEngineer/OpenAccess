package com.ss.oa.transaction.ipaa;

import java.util.List;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.Ipaa;
import com.ss.oa.transaction.vo.IpaaLine;

@Scope("prototype")
@Component
public interface IpaaDao {
	public abstract List<Ipaa> getAllIpaa(Map<String,String> criteria);
	public abstract Ipaa getIpaaById(String id);
	public abstract String addIpaa(Ipaa ipaa);
	public abstract String updateIpaa(String id ,Ipaa ipaa);
	public abstract String addIpaaLine(IpaaLine ipaaLine);
	public abstract String updateIpaaLine(IpaaLine ipaaLine);

}
