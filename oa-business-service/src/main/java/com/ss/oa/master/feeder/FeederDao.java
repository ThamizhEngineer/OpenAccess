package com.ss.oa.master.feeder;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Feeder;
@Scope("prototype")
public interface FeederDao {
		public abstract List<Feeder> getAllFeeders(Map<String, String> criteria) throws Exception;
		public abstract Feeder getFeederById(String id) throws Exception;
		public abstract String addFeeder(Feeder feeder);
		public abstract String updateFeeder(String id,Feeder feeder);
		public abstract String deleteFeeder(String id);
	}
