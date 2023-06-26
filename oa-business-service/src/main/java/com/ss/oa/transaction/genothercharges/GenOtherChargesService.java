package com.ss.oa.transaction.genothercharges;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.vo.GenOtherCharges;
import com.ss.oa.transaction.vo.GenOtherSubCharge;
import com.ss.oashared.common.CommonUtils;



@Scope("prototype")
@RestController
@RequestMapping("/transaction/genothercharges")
public class GenOtherChargesService {
	
	int auditCharges=0;
	int adminCharges=0;
	int meterCharges=0;
	int miscellonousCharges=0;
	@Autowired
	GenOtherChargesRepository genOtherChargesRepository;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	GenOtherSubChargeRepository genOtherSubChargeRepository;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public List<GenOtherCharges> getAllGenOtherCharges(		@RequestParam(value = "sellerCompanyServiceId",required = false) String sellerCompanyServiceId,
			@RequestParam(value = "sellerOrgId", required = false) String sellerOrgId,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "year", required = false) String year) {
		
		return searchGenOtherCharges(sellerCompanyServiceId,sellerOrgId,month,year);
		
	}
	
	private  List<GenOtherCharges> searchGenOtherCharges(String sellerCompanyServiceId,String sellerOrgId, String month, String year) {
		// TODO Auto-generated method stub
		return genOtherChargesRepository.searchGenOtherCharges(sellerCompanyServiceId, sellerOrgId, month, year);
	}
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<GenOtherCharges> getGenOtherChargesById(@PathVariable(value="id") String id) {
		return genOtherChargesRepository.findById(id);
	}

	@CrossOrigin(origins="*")
	@PostMapping
	public GenOtherCharges addGenOtherCharges(@RequestBody GenOtherCharges genOtherCharges) {	
		return genOtherChargesRepository.save(genOtherCharges);
	}
	
	 
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public GenOtherCharges patchGenOtherCharges(@PathVariable(value="id") String id,@RequestBody GenOtherCharges genOtherCharges) {
		return genOtherChargesRepository.save(genOtherCharges);
	}
	
	@PostMapping("/add")
	public GenOtherCharges addOrUpdateGenOtherChargesLines(@RequestBody GenOtherCharges genOtherCharges) throws OpenAccessException {
		Integer total=0;
		List<GenOtherCharges> genOtherChargesFromDb = getAllGenOtherCharges(genOtherCharges.getSellerCompanyServiceId(),genOtherCharges.getSellerOrgId(),genOtherCharges.getMonth(),genOtherCharges.getYear());
		
//		System.out.println(genOtherChargesFromDb);
//		System.out.println(genOtherChargesFromDb.size());
//		System.out.println(genOtherChargesFromDb.isEmpty());
		if(genOtherChargesFromDb.isEmpty() && (genOtherChargesFromDb.size()==0)){
//			System.out.println("inside if");
			if (genOtherCharges.getGenOtherSubCharges() != null && !genOtherCharges.getGenOtherSubCharges().isEmpty())
			{
				String hdid=commonUtils.generateId();
				genOtherCharges.setId(hdid);
		
				for (GenOtherSubCharge genOtherSubCharge : genOtherCharges.getGenOtherSubCharges()) {
					Integer totalCharge = commonUtils.toInt(genOtherSubCharge.getTotalCharge());
					genOtherSubCharge.setId(commonUtils.generateId());
					genOtherSubCharge.settGenOtherChargeId(hdid);
//					System.out.println(genOtherCharges.getTotalCharges());
					total=total+totalCharge;
					genOtherSubChargeRepository.save(genOtherSubCharge);
					
	  			}
			genOtherCharges.setTotalCharges(String.valueOf(total));
			genOtherChargesRepository.save(genOtherCharges);
			}
		
		}
			return genOtherCharges;
	}
	
	@PatchMapping("/update")
	public GenOtherCharges updateGenOtherChargesLines(@RequestBody GenOtherCharges genOtherCharges) throws OpenAccessException {
		Integer total=0;
		if (genOtherCharges.getGenOtherSubCharges() != null && !genOtherCharges.getGenOtherSubCharges().isEmpty())
		{
			
			for (GenOtherSubCharge genOtherSubCharge : genOtherCharges.getGenOtherSubCharges()) {
				if(genOtherSubCharge.getId()!=null) {
					Integer totalCharge = commonUtils.toInt(genOtherSubCharge.getTotalCharge());
					total=total+totalCharge;
					genOtherSubChargeRepository.save(genOtherSubCharge);
				}else {
					Integer totalCharge = commonUtils.toInt(genOtherSubCharge.getTotalCharge());
					genOtherSubCharge.setId(commonUtils.generateId());
					genOtherSubCharge.settGenOtherChargeId(genOtherCharges.getId());
					total=total+totalCharge;
					genOtherSubChargeRepository.save(genOtherSubCharge);
				}
		}
		genOtherCharges.setTotalCharges(String.valueOf(total));
		genOtherChargesRepository.save(genOtherCharges);
		}
		return genOtherCharges;
	}
}
