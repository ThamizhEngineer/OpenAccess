package com.ss.oa.transaction.ipaastandingclearance;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.transaction.IpaStandingClearance;
import com.ss.oashared.common.CommonUtils;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/ipaa-sc")
public class IpaaStandingClearanceService {

	@Autowired
	IpaaStandingClearanceRepository ipaaStandingClearanceRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<IpaStandingClearance> getIpaScRepo(IpaStandingClearance ipaStandingClearance) {
		return ipaaStandingClearanceRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<IpaStandingClearance> getIpaScById(@PathVariable (value="id")String id)throws OpenAccessException {
		return ipaaStandingClearanceRepository.findById(id);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/ipaid/{ipaid}")
	public List<IpaStandingClearance> getIpaScByIpaId(@PathVariable (value="ipaid")String ipaid)throws OpenAccessException {
		return ipaaStandingClearanceRepository.findByIpaId(ipaid);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public IpaStandingClearance addIpaSc(@RequestBody IpaStandingClearance ipaStandingClearance) throws OpenAccessException {
		System.out.println(commonUtils.generateId());
		ipaStandingClearance.setId(commonUtils.generateId());
		System.out.println("ipaStandingClearance-"+ipaStandingClearance);
		return ipaaStandingClearanceRepository.save(ipaStandingClearance);

	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public IpaStandingClearance updateIpaScById(@RequestBody IpaStandingClearance ipaStandingClearance) throws OpenAccessException {
		return ipaaStandingClearanceRepository.save(ipaStandingClearance);

	}
	
}
