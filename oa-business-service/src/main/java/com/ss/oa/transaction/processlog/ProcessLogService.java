package com.ss.oa.transaction.processlog;

import java.util.ArrayList;
import java.util.List;

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

import com.ss.oa.common.BaseDaoJdbc;
import com.ss.oa.transaction.vo.ProcessLog;
import com.ss.oa.transaction.vo.PsConsumerStatus;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/processlog")
public class ProcessLogService extends BaseDaoJdbc{
	
	@Autowired
	ProcessLogRepository  processLogRepository;
	
	@Autowired
	PsConsumerStatusRepository psConsumerStatusRepository;
	
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public List<ProcessLog> getAllProcessLog() {
		return (ArrayList<ProcessLog>)processLogRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public ProcessLog getProcessLogById(@PathVariable(value="id") Integer id) {
		return processLogRepository.findById(id).get();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/ps")
	public List<ProcessLog> getSellerLogById(@RequestParam(value = "tEnergySaleId", required = false) String tEnergySaleId) {
		return processLogRepository.findOnlySeller(tEnergySaleId);
	}
	
	
//	@CrossOrigin(origins="*")
//	@GetMapping("/ps/{tenergysaleid}")
//	public List<ProcessLog> getProcessLogByps(@PathVariable(value="tenergysaleid") String tenergysaleid) {
//		return processLogRepository.findByTEnergySaleId(tenergysaleid);
//	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/ps/status/{tEsId}")
	public List<PsConsumerStatus> getPsConStatus(@PathVariable(value="tEsId") String tEsId) {
		System.out.println("status con id = "+tEsId);
		return psConsumerStatusRepository.findByTEsId(tEsId);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping
	public ProcessLog addProcessLog(@RequestBody  ProcessLog processLog) {
		return processLogRepository.save(processLog);
	}
	
	
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public ProcessLog patchProcessLog(@PathVariable(value="id") Integer id,@RequestBody ProcessLog processLog) {
		return processLogRepository.save(processLog);
	}

}
