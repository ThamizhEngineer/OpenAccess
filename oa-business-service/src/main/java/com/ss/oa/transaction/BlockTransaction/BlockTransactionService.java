package com.ss.oa.transaction.BlockTransaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.model.transaction.BlockTransaction;
@Controller
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/blocktransaction")
public class BlockTransactionService {
	
	@Autowired
	BlockTransactionRepository blocktransactionRepository;
	
	@GetMapping("/get")
		public Iterable<BlockTransaction> get(@RequestParam(name="mCompServiceNumber",required=false) String mCompServiceId ,
				@RequestParam(name="month",required=false) String month, 
				@RequestParam(name="year",required=false) String year) {
		
		return blocktransactionRepository.findall(mCompServiceId,year,month);
			
		}
//	@PostMapping("/create")
//	public Iterable<BlockTransaction> put(@RequestBody BlockTransaction blockTransaction) {
//		LocalDate localDate = LocalDate.now();
//		blockTransaction.setCreatedDt(localDate);
//		blockTransaction.setModifiedDate(localDate);
//		blockTransaction.setmOrgId("01");
//		System.out.println(blockTransaction);
//		
//		blocktransactionRepository.save(blockTransaction);
//		return blocktransactionRepository.findAll();
//	}
//	
//	
//	@GetMapping("/update")
//	public void update(@RequestParam(name="mCompServiceNumber",required=false) String mCompServiceId ,
//			@RequestParam(name="month",required=false) String month, 
//			@RequestParam(name="year",required=false) String year,@RequestParam(name="dummy",required=false) String dummy ) {
//		System.out.println("came in");
//		System.out.println(mCompServiceId);
//		//System.out.println(blocktransactionRepository.findByMCompServiceNumber(mCompServiceId));
//		
//		}
//	
//	
	@GetMapping("/test")
   public  BlockTransaction checking(@RequestParam(name="service-number",required=false) String mCompServiceId,@RequestParam(name="statement-month",required=false) String month,@RequestParam(name="statement-year",required=false) String year) {
		System.out.println(blocktransactionRepository.findByCombo(mCompServiceId,year,month));
		System.out.println("blockworks");
	if(blocktransactionRepository.findByCombo(mCompServiceId,year,month)!=null) {
		return blocktransactionRepository.findByCombo(mCompServiceId,year,month);
	}
	return blocktransactionRepository.findallnative();
	
		}
	@GetMapping("/updatestate")
public Iterable<BlockTransaction> updatestatus(@RequestParam(name="mCompServiceNumber",required=false) String mCompServiceI,@RequestParam(name="status",required=false) String status) {
		
		
		LocalDate localDate = LocalDate.now();
		//BlockTransaction fordate=blocktransactionRepository.statusUpdate(mCompServiceI);
		//fordate.setModifiedDate(localDate);
		blocktransactionRepository.statusUpdate(mCompServiceI,localDate);
		return blocktransactionRepository.findAll();
	}
}
