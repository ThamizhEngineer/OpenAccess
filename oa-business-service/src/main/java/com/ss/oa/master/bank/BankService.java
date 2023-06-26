package com.ss.oa.master.bank;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.master.Bank;
import com.ss.oa.model.master.Fuel;
import com.ss.oashared.common.CommonUtils;

@RestController
@RequestMapping(path = "/master/bank")
public class BankService {

	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private BankRepository bankRepository;
	
	@GetMapping("/FindAll")
    public Iterable<Bank> getBank()
		{
			return bankRepository.findAll();   
		}
	
	@GetMapping("/GetBy/{id}")
	public Optional<Bank> getBankById(@PathVariable String id)
	{
		return bankRepository.findById(id);
    }
	
	
	@PostMapping("/Post")
	public Bank addBank(@RequestBody Bank bank)
	{
		bank.setBankId(commonUtils.generateId());
		return bankRepository.save(bank);
	}
	
	@PatchMapping("/Update/{id}")
	public Bank saveBank(@RequestBody Bank bank, @PathVariable String id)
	{
		return bankRepository.save(bank);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBankById(@PathVariable(value = "id") String id) throws OpenAccessException {
		bankRepository.deleteById(id);
	}
}
