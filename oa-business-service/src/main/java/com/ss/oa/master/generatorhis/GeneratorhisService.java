package com.ss.oa.master.generatorhis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.master.vo.Generatorhis;
import com.ss.oa.master.vo.VCompanyServiceHis;
import com.ss.oa.model.master.VtradeRelationship;

@RestController
@RequestMapping(path ="/master/generatorhis")
public class GeneratorhisService {
	
	@Autowired 
	GeneratorhisRepository generatorhisRepo;

	
	@Autowired
	TraderelationshipRepository tradeRepo;
	
	@Autowired
	VcompanyserviceRepository vcompanyRepo;
	
	//to get generator history by serviceno
	@CrossOrigin(value ="*")
	@GetMapping("/{mCompServNumber}")
	public List<Generatorhis> getByServiceNo(@PathVariable(value="mCompServNumber") String mCompServNumber){
	
		System.out.println(generatorhisRepo.findByMCompServNumber(mCompServNumber).size());
		return generatorhisRepo.findByMCompServNumber(mCompServNumber);
	}

	//Get details of generator by serviceNo in Vcompany Service
	@CrossOrigin(value ="*")
	@GetMapping("/detail/{mCompServNumber}")
	public Iterable<VCompanyServiceHis> getSellerDetail(@PathVariable(value="mCompServNumber") String mCompServNumber){
		return vcompanyRepo.findByMCompServNumber(mCompServNumber);
		
	}
	
	//Get buyer for particular generator
	@CrossOrigin(value ="*")
	@GetMapping("/trade/{sellerCompServiceNumber}")
	public List<VtradeRelationship> getBuyer(@PathVariable(value="sellerCompServiceNumber") String sellerCompServiceNumber){
		return tradeRepo.findBySellerCompServiceNumber(sellerCompServiceNumber);
		
	}
	
	
}



