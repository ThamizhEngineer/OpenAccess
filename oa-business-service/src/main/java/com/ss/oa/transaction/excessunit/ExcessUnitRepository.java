package com.ss.oa.transaction.excessunit;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.transaction.vo.ExcessUnit;

public interface ExcessUnitRepository extends CrudRepository<ExcessUnit, String> {
	
	@Query("SELECT exunit FROM ExcessUnit exunit\n"+
	" WHERE NVL(LOWER(exunit.companyServiceId),'0') LIKE LOWER(CONCAT('%',NVL(:supplierid,''),'%')) AND NVL(LOWER(exunit.readingMonth),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))"+
			" AND NVL(LOWER(exunit.readingYear),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))")
	List<ExcessUnit> getExcessUnitByCriteria(@Param("supplierid") String companyServiceId,
			@Param("month") String readingMonth,
			@Param("year") String readingYear);
	
	
	@Query("SELECT exunit FROM ExcessUnit exunit\n"+
			" WHERE NVL(LOWER(exunit.companyServiceNum),'0') LIKE LOWER(CONCAT('%',NVL(:supplierid,''),'%')) AND NVL(LOWER(exunit.readingMonth),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))"+
					" AND NVL(LOWER(exunit.readingYear),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))")
			List<ExcessUnit> getExcessUnitByCriteriaForPrint(@Param("supplierid") String companyServiceId,
					@Param("month") String readingMonth,
					@Param("year") String readingYear);

}
