package com.ss.oa.transaction.tempgenerationstatement;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.master.company.MeterRepository;
import com.ss.oa.model.master.Meter;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import org.slf4j.Logger;
@RestController
@RequestMapping(path="transaction/temp-gen-stmts")
public class TempGenStmtService {
@Autowired
TempGenStmtRepo tempGenStmtRepo;

@Autowired
MeterRepository meterRepository;
@Autowired
CommonUtils commonUtils;
@Autowired
TempGenStmtChargeRepo tempGenStmtChargeRepo;

@Autowired
TempGenStmtSlotRepo tempGenStmtSlotRepo;
Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

@GetMapping
public Iterable<TempGenStmt> getAllTempGenStmt()throws OpenAccessException{
	return tempGenStmtRepo.findAll();
}
@GetMapping("/{id}")
public Optional<TempGenStmt> getTempGenStmtId(@PathVariable(value="id")String id)throws OpenAccessException{
	return tempGenStmtRepo.findById(id);
}
@PostMapping
public TempGenStmt addTempGenStmt(@RequestBody TempGenStmt tempGenStmt)throws OpenAccessException{
	String id=commonUtils.generateId();
	tempGenStmt.setId(id);
	if(tempGenStmt.getTempGenStmtCharges()!=null&&!tempGenStmt.getTempGenStmtCharges().isEmpty()) {
	for(TempGenStmtCharge tempGenStmtCharge:tempGenStmt.getTempGenStmtCharges()) {
		tempGenStmtCharge.setId(commonUtils.generateId());
		
		tempGenStmtChargeRepo.save(tempGenStmtCharge);
	}
	}
	 if (tempGenStmt.getTempGenStmtSlots()!=null&&!tempGenStmt.getTempGenStmtSlots().isEmpty()) {
	for(TempGenStmtSlot tempGenStmtSlot:tempGenStmt.getTempGenStmtSlots()) {
		tempGenStmtSlot.setId(commonUtils.generateId());
		
		tempGenStmtSlotRepo.save(tempGenStmtSlot);
	}
	 }
	return tempGenStmtRepo.save(tempGenStmt);
}

@PatchMapping("/{id}/update")
public TempGenStmt updateTempGenStmt(@RequestBody TempGenStmt tempGenStmt)throws OpenAccessException{
	return tempGenStmtRepo.save(tempGenStmt);
}
@PatchMapping("/{id}/lines")
public TempGenStmt addTempGenStmtLines(@RequestBody TempGenStmt tempGenStmt)throws OpenAccessException{
	if(tempGenStmt.getTempGenStmtCharges()!=null&&!tempGenStmt.getTempGenStmtCharges().isEmpty()) {
		for(TempGenStmtCharge tempGenStmtCharge:tempGenStmt.getTempGenStmtCharges()) {
			
			tempGenStmtChargeRepo.save(tempGenStmtCharge);
		}
	}else if (tempGenStmt.getTempGenStmtSlots()!=null&&!tempGenStmt.getTempGenStmtSlots().isEmpty()) {
		for(TempGenStmtSlot tempGenStmtSlot:tempGenStmt.getTempGenStmtSlots()) {
			
			tempGenStmtSlotRepo.save(tempGenStmtSlot);
		}
	}
	return tempGenStmt;
}

@DeleteMapping("/{id}/delete")
public void deleteTempGenStmt(@PathVariable(value="id")String id)throws OpenAccessException{
	tempGenStmtRepo.deleteById(id);
}

@DeleteMapping("/{id}/delete/temp-gen-stmt-slot")
public void deleteTempGenStmtSlot(@PathVariable(value="id")String id)throws OpenAccessException{
	tempGenStmtSlotRepo.deleteById(id);
}

@DeleteMapping("/{id}/delete/temp-gen-stmt-charges")
public void deleteTempGenStmtCharges(@PathVariable(value="id")String id)throws OpenAccessException{
	tempGenStmtChargeRepo.deleteById(id);
}
@GetMapping("/old-meter-reading")
public List<Meter> getOldMeterReading()throws OpenAccessException{
	List<Meter> oldMeters=new ArrayList<Meter>();
	
	List<TempGenStmt> tempGenStmtList=(List<TempGenStmt>) tempGenStmtRepo.findAll();
	for(TempGenStmt tempGenStmt:tempGenStmtList) {
		Meter meter=meterRepository.findById(tempGenStmt.getmCompanyMeterId()).get();
		if(meter.getEnabled().equals("N")) {
			oldMeters.add(meter);
		}
	
		
	}
	return oldMeters;
}

	@GetMapping("/new-meter-reading")
	public List<TempGenStmt> getTempGenStmtBySerNumAndMonthAndYear(@RequestParam(name="dispServiceNumber")String dispServiceNumber,@RequestParam(name="stmtMonth")String stmtMonth,@RequestParam(name="stmtYear")String stmtYear )throws OpenAccessException{
	if(dispServiceNumber!=null&&!dispServiceNumber.isEmpty()&&stmtMonth!=null&&!stmtMonth.isEmpty()&&stmtYear!=null&&!stmtYear.isEmpty())	{
		return tempGenStmtRepo.findBydispServiceNumberAndStmtMonthAndStmtYear(dispServiceNumber, stmtMonth, stmtYear);
	}
	return null;
	}
	
	@GetMapping("/new-meter-reading/_internal")
	public List<TempGenStmt> getTempGenStmtBySerNumAndMonthAndYearInternal(@RequestParam(name="dispServiceNumber")String dispServiceNumber,@RequestParam(name="stmtMonth")String stmtMonth,@RequestParam(name="stmtYear")String stmtYear )throws OpenAccessException{
	if(dispServiceNumber!=null&&!dispServiceNumber.isEmpty()&&stmtMonth!=null&&!stmtMonth.isEmpty()&&stmtYear!=null&&!stmtYear.isEmpty())	{
		return tempGenStmtRepo.findBydispServiceNumberAndStmtMonthAndStmtYear(dispServiceNumber, stmtMonth, stmtYear);
	}
	return null;
	}
}
