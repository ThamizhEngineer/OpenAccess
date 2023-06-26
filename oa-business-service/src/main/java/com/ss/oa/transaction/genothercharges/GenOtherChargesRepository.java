package com.ss.oa.transaction.genothercharges;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.GenOtherCharges;
import org.springframework.data.repository.query.Param;

@Scope("prototype")

public interface GenOtherChargesRepository extends CrudRepository<GenOtherCharges, String> {

	@Query("select new com.ss.oa.transaction.vo.GenOtherCharges(gen.id,gen.sellerCompanyName,gen.sellerCompanyServiceId,gen.sellerCompanyServiceNumber,gen.sellerOrgId,gen.sellerOrgName,gen.month,gen.year)" 
			+ " from GenOtherCharges gen where NVL(LOWER(gen.sellerCompanyServiceId),'') LIKE LOWER(CONCAT('%',NVL(:sellerCompanyServiceId,''),'%')) "
			+ "AND NVL(LOWER(gen.sellerOrgId),'') LIKE LOWER(CONCAT('%',NVL(:sellerOrgId,''), '%')) "
			+ "AND NVL(LOWER(gen.month),'') LIKE LOWER(CONCAT('%',NVL(:month,''), '%')) "
			+ "AND NVL(LOWER(gen.year),'') LIKE LOWER(CONCAT('%',NVL(:year,''), '%'))")
	List<GenOtherCharges> searchGenOtherCharges(@Param("sellerCompanyServiceId")String sellerCompanyServiceId,@Param("sellerOrgId")String sellerOrgId,@Param("month")String month,@Param("year")String year);
}
