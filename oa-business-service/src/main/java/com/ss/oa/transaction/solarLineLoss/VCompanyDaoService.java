package com.ss.oa.transaction.solarLineLoss;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.vo.Vcompany;





@Scope("prototype")
@Component
public class VCompanyDaoService {
	@Autowired
	VCompanyDao Dao;
	
	public List<Vcompany> getAllFeeder(Map<String, String> criteria){
		
		return Dao.getAllFeeder(criteria);
	}
}
