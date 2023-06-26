package com.ss.oa.master.fuel;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.model.master.Fuel;

public interface FuelRepository extends CrudRepository<Fuel, String>{
	
	
	@Query("SELECT new com.ss.oa.model.master.Fuel(f.id,f.fuelCode,f.fuelName,f.fuelGroup,f.createdBy,f.createdDate,f.modifiedBy,f.modifiedDate,f.isEnabled) FROM Fuel f WHERE NVL(LOWER(f.fuelName),'') LIKE LOWER(CONCAT('%',NVL(:fuelName,''),'%'))" 
			+ "AND NVL(LOWER(f.fuelCode),'0') LIKE LOWER(CONCAT('%',NVL(:fuelCode, ''),'%'))"
			+ "AND NVL(LOWER(f.fuelGroup),'0') LIKE LOWER(CONCAT('%',NVL(:fuelGroup, ''),'%'))")
	List<Fuel>getFuels(@Param("fuelName")String fuelName,@Param("fuelCode")String fuelCode,@Param("fuelGroup")String fuelGroup);

}
