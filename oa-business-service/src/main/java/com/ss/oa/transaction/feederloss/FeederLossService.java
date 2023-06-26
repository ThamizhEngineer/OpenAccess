package com.ss.oa.transaction.feederloss;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.transaction.vo.FeederLoss;
import com.ss.oashared.common.CommonUtils;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/feeder-loss")
public class FeederLossService {

	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	FeederLossRepo feederLossRepo;
	
	@CrossOrigin(origins = "*")
	@GetMapping("/findAll")
    public Iterable<FeederLoss> getAllFeederLoss(){
			return feederLossRepo.findAll();   
		}
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public List<FeederLoss>getFeederLoss(@RequestParam(name="month",required=false) String month,@RequestParam(name="year",required=false) String year,@RequestParam(name="org-id",required=false) String orgId) throws OpenAccessException {
		System.out.println("im here");
		return feederLossRepo.getFeederLoss(month, year, orgId);
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public FeederLoss getFeederLossById(@PathVariable(value="id")String id)throws OpenAccessException{
		return feederLossRepo.findById(id).get();	
	}
		
	@CrossOrigin(origins="*")
	@PostMapping
	public FeederLoss addFeederLoss(@RequestBody FeederLoss feederLoss)  throws OpenAccessException {
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		String batchKey=feederLoss.getOrgId()+"-"+feederLoss.getMonth()+"-"+feederLoss.getYear()+"-"+ ts;
		feederLoss.setId(commonUtils.generateId());
		feederLoss.setBatchKey(batchKey);
		return feederLossRepo.save(feederLoss);
	}
	
	@CrossOrigin(origins="*")
	@PatchMapping("/{id}")
	public FeederLoss updateFeederLoss(@PathVariable(value="id")String id,@RequestBody FeederLoss feederLoss)  throws OpenAccessException {
		return feederLossRepo.save(feederLoss);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteFeederById(@PathVariable(value = "id") String id) throws OpenAccessException {
		feederLossRepo.deleteById(id);
	}
}
