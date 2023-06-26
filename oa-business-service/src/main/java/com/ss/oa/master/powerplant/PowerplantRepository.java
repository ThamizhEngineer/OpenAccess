package com.ss.oa.master.powerplant;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.master.Powerplant;
@Scope("prototype")
public interface PowerplantRepository extends CrudRepository<Powerplant, Integer>{

	Iterable<Powerplant> findAll();

	Powerplant findById(String id);

	Iterable<Powerplant> findByPlantTypeCode(String plantTypeCode);

	Iterable<Powerplant> findByFuelTypeCode(String fuelTypeCode);

	Iterable<Powerplant> findByCode(String code);

	Iterable<Powerplant> findByOrgId(String orgId);

	Iterable<Powerplant> findByCompanyId(String companyId);
	
	Iterable<Powerplant> findByServiceId(String serviceId);
	
	Iterable<Powerplant> findByOrgIdAndServiceId(String orgId,String serviceId);
	

	


}
