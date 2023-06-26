package com.ss.oa.reportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oashared.common.OpenAccessException;



@RestController
@RequestMapping(path="report/weg-with-consumers")
public class WegPowerPlantService {
	@Autowired
	WegPowerPlantRepository wegPowerPlantRepository;
	
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<WegPowerplant> getWegPowerPlants(@RequestParam(value="orgId",required=false)String orgId,@RequestParam(value="capacity",required=false)String capacity,@RequestParam(value="makeCode",required=false)String makeCode)throws OpenAccessException{
		boolean searchByorgId=false,searchBycapacity=false,searchBymakeCode=false,searchByorgIdAndcapacityAndmakecode=false;
		if(orgId!=null&&!orgId.isEmpty()) {
			searchByorgId=true;
		}
		if(searchByorgId) {
			return wegPowerPlantRepository.findByOrgId(orgId);
		}
		if(capacity!=null&&!capacity.isEmpty()) {
			searchBycapacity=true;
		}
		if(searchBycapacity) {
			return wegPowerPlantRepository.findByCapacity(capacity);
		}
		if(makeCode!=null&&!makeCode.isEmpty()) {
			searchBymakeCode=true;
		}
		if(searchBymakeCode) {
			return wegPowerPlantRepository.findByMakeCode(makeCode);
		}
		if(orgId!=null&&!orgId.isEmpty()&&capacity!=null&&!capacity.isEmpty()&&makeCode!=null&&!makeCode.isEmpty()) {
			searchByorgIdAndcapacityAndmakecode=true;
		}
		if(searchByorgIdAndcapacityAndmakecode) {
			return wegPowerPlantRepository.findByOrgIdAndCapacityAndMakeCode(orgId, capacity, makeCode);
		}
		return wegPowerPlantRepository.findAll();
		
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/_internal")
	public Iterable<WegPowerplant> getWegPowerPlantsInternal(@RequestParam(value="orgId",required=false)String orgId,@RequestParam(value="capacity",required=false)String capacity,@RequestParam(value="makeCode",required=false)String makeCode)throws OpenAccessException{
		return getWegPowerPlants(orgId, capacity, makeCode);
		
	}

}
