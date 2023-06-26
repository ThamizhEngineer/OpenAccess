package com.ss.oa.deleteTransactionReasonSummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.DeleteTransactionReasonSummary;


@RestController
@RequestMapping("report/delete-transaction-reason-summaries")
public class DeleteTransactionReasonSummaryService {
	
	@Autowired
	DeleteTransactionReasonSummaryRepository  deleteTransactionReasonSummaryRepository;
	
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<DeleteTransactionReasonSummary>getdeleteTransactionReasonSummary(@RequestParam(value = "valueCode", required = false) String valueCode,
			@RequestParam(value = "valueDesc", required = false) String valueDesc) 
			{
				return deleteTransactionReasonSummaryRepository.getdeleteTransactionReasonSummary(valueCode,valueDesc);
			}


}
