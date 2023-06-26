package com.ss.oa.generationreportsummary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ss.oa.report.vo.GenerationReportSummary;

@RestController
@RequestMapping("/report/login-page-data")
public class GenerationReportSummaryService {
	
	@Autowired
	GenerationReportSummaryRepo generationReportSummaryRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<GenerationReportSummary> getAllGsRepSummary(){
		return generationReportSummaryRepo.findAll();
	}

}
