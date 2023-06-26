package com.ss.oa.ledger.oahtcharges;

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
import com.ss.oa.ledger.vo.OaHtCharges;
import com.ss.oashared.common.CommonUtils;

@RestController
@RequestMapping("/ledger/oa-ht-charges")
@Scope("prototype")
public class OaHtChargesService {
	
	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	OaHtChargesRepository oaHtChargesRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<OaHtCharges> getAllOaHtCharges() throws OpenAccessException {
		return oaHtChargesRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<OaHtCharges> getOaHtChargesById(@PathVariable(value="id")String id)throws OpenAccessException{
		return oaHtChargesRepository.findById(id);
		
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/seller-comp-serv-number/{id}")
	public List<OaHtCharges> getOaHtChargesBySellerCompServNumber(@PathVariable(value="id")String sellerCompanyServiceNumber)throws OpenAccessException{
		return oaHtChargesRepository.findBySellerCompanyServiceNumber(sellerCompanyServiceNumber);
		
	}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public OaHtCharges addOaHtCharges(@RequestBody OaHtCharges oaHtCharges) throws OpenAccessException {
		oaHtCharges.setId(commonUtils.generateId());		
		return oaHtChargesRepository.save(oaHtCharges);

	}
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	public OaHtCharges updateOaHtCharges(@RequestBody OaHtCharges oaHtCharges) throws OpenAccessException {
		return oaHtChargesRepository.save(oaHtCharges);

	}
	
}
