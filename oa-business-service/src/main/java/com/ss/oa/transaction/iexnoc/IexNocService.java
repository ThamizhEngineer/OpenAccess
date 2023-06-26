package com.ss.oa.transaction.iexnoc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.transaction.IexNoc;
import com.ss.oa.model.transaction.IpaStandingClearance;
import com.ss.oa.transaction.ipaa.IpaaBusinessHelper;
import com.ss.oa.transaction.ipaastandingclearance.IpaaStandingClearanceRepository;
import com.ss.oa.transaction.vo.StandingClearence;
import com.ss.oashared.common.CommonUtils;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/iex-noc")
public class IexNocService {
	
	@Autowired
	IexNocRepository iexNocRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	IpaaBusinessHelper ipaHelper;
	
	@Autowired
	IpaaStandingClearanceRepository ipaRepo;

	
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<IexNoc> getIexNoc(Model model, Pageable pageable) {
        Page<IexNoc> pages = getIexNocRepo(pageable);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("totalElements", pages.getTotalElements());
        model.addAttribute("size", pages.getSize());
        model.addAttribute("iexNoc", pages.getContent());

		return pages;
        
	}


	public Page<IexNoc> getIexNocRepo(Pageable pageable) {
		return iexNocRepository.findAll(pageable);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<IexNoc> getIexNocById(@PathVariable (value="id")String id)throws OpenAccessException {
		return iexNocRepository.findById(id);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public IexNoc addIexNoc(@RequestBody IexNoc iexNoc) throws OpenAccessException {
		System.out.println(commonUtils.generateId());
		iexNoc.setId(commonUtils.generateId());
		return iexNocRepository.save(iexNoc);

	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public IexNoc updateIexNocById(@RequestBody IexNoc iexNoc) throws OpenAccessException {
		return iexNocRepository.save(iexNoc);

	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/approve/{id}")
	public String approveIexNocById(@RequestBody IexNoc iexNoc) throws OpenAccessException {
		iexNoc.setStatusCode("APPROVED");
		System.out.println("iexnoc-"+iexNoc);
		String iexNocId =iexNoc.getId();
		System.out.println("iesNocId-"+iexNocId);
		iexNocRepository.save(iexNoc);
		IpaStandingClearance sc = ipaRepo.findByIexNocId(iexNocId);
		System.out.println("sc-"+sc);
		String ipaScId = sc.getId();
		System.out.println("ipascid-"+ipaScId);
		ipaHelper.procesStandingClearance(ipaScId);
		return ipaScId;

	}
}
