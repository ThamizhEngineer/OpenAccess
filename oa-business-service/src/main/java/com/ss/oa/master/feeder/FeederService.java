package com.ss.oa.master.feeder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.ss.oa.master.vo.Feeder;

@Component
@Scope("prototype")
public class FeederService {
	@Autowired
	FeederDao dao;
	public FeederService(){
		super();
	}
	public List<Feeder> getAllFeeders(Map<String,String> criteria) throws Exception
	{
		try {
			return dao.getAllFeeders(criteria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	} 
	public Feeder getFeederById(String id) throws Exception
	{
		try {
			return dao.getFeederById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	public String addFeeder(Feeder feeder)
	{
		return dao.addFeeder(feeder);
	}
	public String updateFeeder(String id,Feeder feeder)
	{
		return dao.updateFeeder(id,feeder);
	}
	public String deleteFeeder(String id)
	{
		return dao.deleteFeeder(id);
	}
}
