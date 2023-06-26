package com.ss.oa.transaction.solarsubstationloss;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface FeederSolarLineLossRepository extends CrudRepository<FeederSolarLineLoss, String>{
	
	public Boolean existsByFeederIdAndMonthAndYear(String feederId, String month, String year);
}
