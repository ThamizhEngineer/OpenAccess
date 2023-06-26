package com.ss.oa.transaction.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.transaction.Job;
import com.ss.oashared.common.CommonUtils;

@Service
@RestController
@RequestMapping("/transaction/job")
public class JobService {
	
	@Autowired
	JobRepository repo;
	
	@Autowired
	CommonUtils common;
	
	@CrossOrigin
	@GetMapping
	public Iterable<Job> getJobs(){
		return repo.findAll();
	}

	@CrossOrigin(origins="*")
	@PostMapping
	public Job addJob(@RequestBody Job job) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		job.setId(common.generateId());
		job.setJobStatusCode("INIT");
		job.setJobCode("CONFIRM-ENERGY-ALLOTMENT");
		job.setCreatedDate(timeStamp);
		job.setCreatedName("JOB-SERVICE");
		return repo.save(job);
	} 
	
	
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	public  Job updateJob(@RequestBody Job job) throws OpenAccessException {
		job.setJobStatusCode("COMPLETE-SERVICE");
		System.out.println("job-Update-testing");
		return repo.save(job);

	}
	public String MeterChangeJob(String month,String year) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        Job job = new Job();
		job.setId(common.generateId());
		job.setJobStatusCode("INIT");
		job.setJobCode("METER-CHANGE");
		job.setCreatedDate(timeStamp);
		job.setCreatedName("JOB-SERVICE");
		job.setMonth(month);
		job.setYear(year);
		repo.save(job);
		
		return "SUCCESS";
	}

}
