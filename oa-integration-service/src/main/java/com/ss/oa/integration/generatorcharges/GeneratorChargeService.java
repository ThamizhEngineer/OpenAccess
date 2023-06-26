package com.ss.oa.integration.generatorcharges;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.vo.GeneratorCharge;
import com.ss.oa.vo.GeneratorChargeForDAO;
import com.ss.oa.vo.Charge;
@Component
public class GeneratorChargeService {
	@Autowired
	GeneratorChargeDao dao;
	
	public GeneratorChargeService(){
		super();
	}
	

	/* LOGIC - getAllGeneratorCharges
	 * 
	firstRecord = true;
		 
	 In Loop thru DAOResults
		 set currentId only from dao
		 if prevId is different than currentId
		 		 if firstRecord
		 		 	set firstRecord = false
		 		 	set the values for currentGC from DAO
		 		 else
		 		 	add currentGC to ecList
				 	set prevId = currentId
				 	set the values for currentGC from DAO
		create new object for GeneratorCharge
		set the values for GeneratorCharge from DAO
		add the charges to currentGC
	End Loop
	
	if dao had records
		add it to the eaList
	*/
	
	public List<GeneratorCharge> getAllGeneratorCharges(Map<String,String> criteria)
	{
		List<GeneratorCharge> generatorCharges = new ArrayList<GeneratorCharge>();
		List<GeneratorChargeForDAO> generatorChargeForDAOs = dao.getAllGeneratorCharges(criteria);
		String currentId = "";
		String prevId = "";
		GeneratorCharge currentGC = new GeneratorCharge();
		Boolean firstRecord =true;
		for (GeneratorChargeForDAO gcDAO : generatorChargeForDAOs) {
			currentId = gcDAO.getServiceNumber();
			if(!prevId.equalsIgnoreCase(currentId)){
				if(firstRecord){
					firstRecord = false;
					currentGC.setServiceNumber(gcDAO.getServiceNumber());
					currentGC.setGeneratorName(gcDAO.getGeneratorName());
					currentGC.setBillingMonth(gcDAO.getBillingMonth());
					currentGC.setBillingYear(gcDAO.getBillingYear());
				}
				else {
					generatorCharges.add(currentGC);
					prevId = currentId;
					currentGC = new GeneratorCharge();
					currentGC.setServiceNumber(gcDAO.getServiceNumber());
					currentGC.setGeneratorName(gcDAO.getGeneratorName());
					currentGC.setBillingMonth(gcDAO.getBillingMonth());
					currentGC.setBillingYear(gcDAO.getBillingYear());
					
				}
			}
			currentGC.getCharges().add(new Charge(gcDAO.getChargeCode(), gcDAO.getChargeDesc(),gcDAO.getChargeAmount()));
		}
		if(generatorChargeForDAOs!=null && generatorChargeForDAOs.size()>0){
			generatorCharges.add(currentGC);
		}
		return generatorCharges;
	} 
}
