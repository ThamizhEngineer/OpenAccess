package com.ss.oa.master.fuel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.master.Fuel;
import com.ss.oashared.common.CommonUtils;

@RestController
@RequestMapping(path = "/master/fuel")
public class FuelService {
	
	@Autowired
	FuelRepository fuelRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@GetMapping("/FindAll")
    public Iterable<Fuel> getFuel()
		{
			return fuelRepository.findAll();   
		
		}
	
	@GetMapping("/GetBy/{id}")
	public Optional<Fuel> getFuelById(@PathVariable String id)
	{
		return fuelRepository.findById(id);
}
	

	@GetMapping("/GetAll")
	public List<Fuel>getSubstation(@RequestParam(value = "fuelName", required = false) String fuelName,@RequestParam(value = "fuelCode", required = false) String fuelCode, @RequestParam(value = "fuelGroup", required = false) String fuelGroup) {
        return fuelRepository.getFuels(fuelName,fuelCode,fuelGroup);		
	}
	

	
	@PostMapping("/Post")
	public Fuel addNewFuel(@RequestBody Fuel fuel)
	{
		fuel.setId(commonUtils.generateId());
		return fuelRepository.save(fuel);
	}
	
	@PatchMapping("/Update/{id}")
	public Fuel saveFuel(@RequestBody Fuel fuel, @PathVariable String id)
	{
		return fuelRepository.save(fuel);
	}
}
