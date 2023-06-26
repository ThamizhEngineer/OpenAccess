package com.ss.oa.transaction.feederloss;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.transaction.vo.ExcessUnit;
import com.ss.oa.transaction.vo.FeederLoss;

public interface FeederLossRepo extends CrudRepository<FeederLoss,String> {

	@Query("SELECT feederLoss FROM FeederLoss feederLoss\n"+
			" WHERE NVL(LOWER(feederLoss.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(feederLoss.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))"+
					" AND NVL(LOWER(feederLoss.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%'))")
			List<FeederLoss> getFeederLoss(@Param("month") String month,
					@Param("year") String year,
					@Param("orgId") String orgId);
//	Iterable<FeederLoss> getFeederLoss(String month, String year, String orgId);


}
