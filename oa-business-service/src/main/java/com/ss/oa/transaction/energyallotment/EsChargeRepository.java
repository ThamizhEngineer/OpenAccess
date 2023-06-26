package com.ss.oa.transaction.energyallotment;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
import com.ss.oa.transaction.vo.EsCharge;

public interface EsChargeRepository extends CrudRepository<EsCharge, String>{
	List<EsCharge>findAllByEnergySaleId(String energySaleId);

}
