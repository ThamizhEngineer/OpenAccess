package com.ss.oa.feedersummary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.report.vo.FeederSummary;


@RestController
@RequestMapping("/report/feeder-summaries")
public class FeederSummaryService {

	@Autowired
	FeederSummaryRepository feederSummaryRepository;
	
	@CrossOrigin(origins="*")
	@GetMapping
	public List<FeederSummary>getFeeder(@RequestParam(value = "substationId", required = false) String substationId) {
        return feederSummaryRepository.getFeeder(substationId);
		
	}
}
