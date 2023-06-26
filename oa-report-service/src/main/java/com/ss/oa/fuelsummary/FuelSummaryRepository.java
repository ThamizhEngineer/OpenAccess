package com.ss.oa.fuelsummary;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.FuelSummary;


public interface FuelSummaryRepository extends CrudRepository<FuelSummary, String>{
	
	@Query("SELECT new com.ss.oa.report.vo.FuelSummary(ff.id,ff.fuelCode,ff.fuelName,ff.fuelGroup)"
			+ "FROM FuelSummary ff WHERE NVL(LOWER(ff.fuelCode),'') LIKE LOWER(CONCAT('%',NVL(:fuelCode,''),'%')) AND NVL(LOWER(ff.fuelName),'') LIKE LOWER(CONCAT('%',NVL(:fuelName,''),'%')) "
			+ "AND NVL(LOWER(ff.fuelGroup),'') LIKE LOWER(CONCAT('%',NVL(:fuelGroup,''),'%')) ")
	List<FuelSummary> getFuels(@Param ("fuelCode")String fuelCode,
			@Param("fuelName")String fuelName,@Param("fuelGroup")String fuelGroup);

}
