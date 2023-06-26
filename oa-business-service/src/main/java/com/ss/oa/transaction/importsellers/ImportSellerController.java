package com.ss.oa.transaction.importsellers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.model.master.Service;
import com.ss.oa.transaction.vo.ImportSeller;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/import-seller/")
public class ImportSellerController {

	@Autowired
	ImportSellerRepository importSellerRepo;

	@Autowired
	CompanyServiceRepo companyserviceRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("all")
	public Iterable<ImportSeller> getall() {
		return importSellerRepo.findAll();
	}

	@PostMapping("add")
	public ImportSeller addData(@RequestBody ImportSeller importSeller) {
		LocalDateTime localDate = LocalDateTime.now();
	//	DateTimeFormatter Format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String lastFourDigit = importSeller.getGeneratorServiceNoNew()
				.substring(importSeller.getGeneratorServiceNoNew().length() - 4);
		importSeller.setRemarks("EDC_" + importSeller.getmOrgId() + "_" + lastFourDigit + "_"
				+ importSeller.getFlowTypeCode() + "_" + localDate);
		importSeller.setFuel(importSeller.getFuelCode());
		importSeller.setCommisionDate(importSeller.getCommisionDate().plusDays(1));
		//LocalDate date=importSeller.getCommisionDate().format(Format);
		//System.out.println("print---"+date);
		String day="";
		String Month="";
		if (importSeller.getCommisionDate().getDayOfMonth()<10) {
			day="0"+importSeller.getCommisionDate().getDayOfMonth();
		}
		else {
			day=String.valueOf(importSeller.getCommisionDate().getDayOfMonth());
		}
		
        if (importSeller.getCommisionDate().getMonthValue()<10) {
        	Month="0"+importSeller.getCommisionDate().getMonthValue();
		}
        else {
        	Month=String.valueOf(importSeller.getCommisionDate().getMonthValue());
        }
		
		importSeller.setCommissionDateStr(day+"/"+Month+"/"+
		importSeller.getCommisionDate().getYear()		
				);
		
        importSeller.setNatureOfBoard(importSeller.getFlowTypeCode());
        Integer Calculation=(Integer.parseInt(importSeller.getNoOfGenUnits())*Integer.parseInt(importSeller.getGenUnitCapacity()));
        String totalCapacity=Calculation.toString();
        importSeller.setTotalCapacityKw(totalCapacity);
        
		return importSellerRepo.save(importSeller);
	}
	
	@GetMapping("getserviceno")
	public String getServiceNo(@RequestParam(value="number",required=false) String number) {
		
		 List<Service> noofService=companyserviceRepo.findByNumber(number);
		 if(noofService.size()>0) {
           return "Service no already available";
		 }
		 else {
		 return "New service no";
	}
	}

}