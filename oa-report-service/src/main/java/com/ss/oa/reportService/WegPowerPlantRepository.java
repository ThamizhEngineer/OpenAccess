package com.ss.oa.reportService;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WegPowerPlantRepository extends CrudRepository<WegPowerplant, String> {
List<WegPowerplant> findByOrgId(String orgId);
List<WegPowerplant> findByCapacity(String capacity);
List<WegPowerplant> findByMakeCode(String makeCode);
List<WegPowerplant> findByOrgIdAndCapacityAndMakeCode(String orgId,String capacity,String makeCode);
}
