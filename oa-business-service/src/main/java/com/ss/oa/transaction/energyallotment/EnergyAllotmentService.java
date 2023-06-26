package com.ss.oa.transaction.energyallotment;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.ss.oa.common.TransactionRestService;
import com.ss.oa.transaction.vo.EnergyAllotment;
import com.ss.oa.transaction.vo.EnergyAllotmentView;
import com.ss.oa.transaction.vo.EsCharge;
import com.ss.oa.transaction.energyallotment.EaChargeHelper;
import com.ss.oa.transaction.vo.EsUsageSummary;
import com.ss.oashared.common.CommonUtils;
import com.ss.oashared.common.OpenAccessException;
import oracle.jdbc.OracleTypes;
import com.ss.oa.transaction.energyallotment.EnergyAllotmentHelper;

@Service
@RestController
@RequestMapping("transaction/energyallotment")

public class EnergyAllotmentService extends TransactionRestService{
	
	
	@Resource
	private DataSource dataSource;
	@Autowired
	EnergyAllotmentRepository energyAllotmentRepository;
	@Autowired
	EnergyAllotmentHelper helper;
	@Autowired
	private CommonUtils commonUtils;
	@Autowired
	EaChargeHelper eaChargeHelper;
	
	Connection conn = null;
	CallableStatement stmt = null;
	
	String energySaleId="";
	String generationStatementId="";
	
	@CrossOrigin(origins = "*")  
	@GetMapping
	public List<EnergyAllotment> getByServiceId(
			@RequestParam(name="companyname",required=false) String sellerCompanyName,
			@RequestParam(name="servicenumber",required=false) String sellerCompanyServiceNumber,
			@RequestParam(name="edcid",required=false) String sellerEndOrgId,
			@RequestParam(name="companyid",required=false) String sellerCompanyId,
			@RequestParam(name="serviceid",required=false) String sellerCompanyServiceId,
			@RequestParam(name="isstb",required=false) String isStb,
 			@RequestParam(name="month",required=false) String month,
			@RequestParam(name="year",required=false) String year,
			@RequestParam(name="simpleEnergySale",required=false) String simpleEnergySale,
			@RequestParam(name="fuelTypeCode",required=false) String fuelTypeCode,
			@RequestParam(name="flowTypeCode",required=false) String flowTypeCode) 
		 {
			return energyAllotmentRepository.findBySearchCriteria(sellerCompanyName,sellerCompanyServiceNumber,sellerEndOrgId,sellerCompanyId,sellerCompanyServiceId,isStb,month,year,simpleEnergySale,fuelTypeCode,flowTypeCode);
		
    	}
	 
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<EnergyAllotment>getEnergySaleById(@PathVariable(value="id")String id)throws OpenAccessException{
		Optional<EnergyAllotment> energyAllotment= energyAllotmentRepository.findById(id);
		if (energyAllotment.isPresent()) {
			energyAllotment = processEsUsageSummariesFrCharges(energyAllotment);
			return energyAllotment;
		} else {
			throw new OpenAccessException("No allotment data");
		}
		
	}
	
	public Optional<EnergyAllotment> processEsUsageSummariesFrCharges(Optional<EnergyAllotment> ea) {
		for (EsUsageSummary ess : ea.get().getEsUsageSummaries()) {
			
			for (EsCharge esc : ea.get().getEsCharges()) {
				
   if(ess.getBuyerCompanyServiceId().equals(esc.getCompanyServiceId())) {
					
	   	if(esc.getChargeCode().equals("C001")) {
	   			ess.setC001Id(esc.getId());
	   			ess.setC001ChargeCode(esc.getChargeCode());
	   			ess.setC001ChargeDesc(esc.getChargeName());
	   			ess.setC001TotalCharge(esc.getTotalCharge());	
		}
		if(esc.getChargeCode().equals("C002")) {
				ess.setC002Id(esc.getId());
				ess.setC002ChargeCode(esc.getChargeCode());
				ess.setC002ChargeDesc(esc.getChargeName());
				ess.setC002TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C003")) {
			ess.setC003Id(esc.getId());
			ess.setC003ChargeCode(esc.getChargeCode());
			ess.setC003ChargeDesc(esc.getChargeName());
			ess.setC003TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C004")) {
			ess.setC004Id(esc.getId());
			ess.setC004ChargeCode(esc.getChargeCode());
			ess.setC004ChargeDesc(esc.getChargeName());
			ess.setC004TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C005")) {
			ess.setC005Id(esc.getId());
			ess.setC005ChargeCode(esc.getChargeCode());
			ess.setC005ChargeDesc(esc.getChargeName());
			ess.setC005TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C006")) {
			ess.setC006Id(esc.getId());
			ess.setC006ChargeCode(esc.getChargeCode());
			ess.setC006ChargeDesc(esc.getChargeName());
			ess.setC006TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C007")) {
			ess.setC007Id(esc.getId());
			ess.setC007ChargeCode(esc.getChargeCode());
			ess.setC007ChargeDesc(esc.getChargeName());
			ess.setC007TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C008")) {
			ess.setC008Id(esc.getId());
			ess.setC008ChargeCode(esc.getChargeCode());
			ess.setC008ChargeDesc(esc.getChargeName());
			ess.setC008TotalCharge(esc.getTotalCharge());	
		}					
		if(esc.getChargeCode().equals("C009")) {
			ess.setC009Id(esc.getId());
			ess.setC009ChargeCode(esc.getChargeCode());
			ess.setC009ChargeDesc(esc.getChargeName());
			ess.setC009TotalCharge(esc.getTotalCharge());	
		}	
					
	  }
				
	 }
			
	} 

				
	return ea;	
	}
	
	
	
	@CrossOrigin(origins="*")
	@PatchMapping("/update/{id}")
	public EnergyAllotment updateEa(@PathVariable(value="id") String id,@RequestBody EnergyAllotment energyAllotment){
		energyAllotment.setFromDate(convertDate(energyAllotment.getStringFromDate()));
		energyAllotment.setToDate(convertDate(energyAllotment.getStringToDate()));
		if(energyAllotment.getFuelGroupe()!=null && energyAllotment.getFuelGroupe().equalsIgnoreCase("RE"))
			eaChargeHelper.updateEnergySaleCharge(energyAllotment);
		return helper.processDataForUpdation(id, energyAllotment);
	}
	
	@GetMapping("/{stringdate}/convert-date")
	public LocalDate convertDate(@PathVariable(value="stringdate") String inputDateString) {
		System.out.println(inputDateString);
		LocalDate date = LocalDate.parse(inputDateString);
		System.out.println("inputDateString-"+inputDateString+"-date-"+date);
		return date;
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/create")
	public EnergyAllotment addNewEa(@RequestBody EnergyAllotment energyAllotment) {
		triggerAllotmentCreation(energyAllotment.getGenerationStatementId()); 
//	     for(EsCharge escharge : energyAllotment.getEsCharges()) {
//	    	 escharge.setCreatedDate(LocalDateTime.now().toString());
//	     }
		return energyAllotmentRepository.save(energyAllotment);
	}
	
	@CrossOrigin(origins="*")
	@PostMapping("/createFromDb")
	public String createEaUsingProcedure(@RequestBody EnergyAllotment energyAllotment) {
		System.out.println(energyAllotment.getGenerationStatementId());
		return triggerAllotmentCreation(energyAllotment.getGenerationStatementId());
	}
	
	private String triggerAllotmentCreation(String gsId) {
		String createEnergySaleFunction = "{call create_energy_sale(?,?)}"; 
		try {
		        conn = dataSource.getConnection();
		        stmt = conn.prepareCall(createEnergySaleFunction);
		        stmt.setString(1,gsId);
		        stmt.registerOutParameter(2, OracleTypes.VARCHAR);
		        stmt.execute();
		        energySaleId= stmt.getString(2);  
		        System.out.println(energySaleId);
				conn.close();
		} catch (Exception e) {
		      e.printStackTrace();
		}finally {
			
			try {
				if((stmt!= null) && (!stmt.isClosed())) {
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if((conn!= null) && (!conn.isClosed())) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return energySaleId;
	}
	
	@CrossOrigin(origins="*")
	@DeleteMapping("/delete/{id}")
	public String deleteEa(@PathVariable(value="id") String id,@RequestBody EnergyAllotment energyAllotment){
		 return helper.deleteFunc(id, energyAllotment);
	}
	
	@CrossOrigin(origins="*")
	@PatchMapping("/dummyGet")
	public EnergyAllotment dummyGet(@RequestBody EnergyAllotment energyAllotment) {
		return eaChargeHelper.updateEnergySaleCharge(energyAllotment);
	}
	
	
//	------------------------------------------------------------
	
	@CrossOrigin(origins="*")
	@DeleteMapping("/deleteFromDb")
	public String deleteEaUsingProcedure(@RequestBody EnergyAllotment energyAllotment) {
		 return helper.callDeleteTransByService(energyAllotment.getSellerCompanyServiceId(),energyAllotment.getSellerCompanyServiceNumber(),energyAllotment.getMonth(),energyAllotment.getYear(),null,null,null,null);
	}
	
}
