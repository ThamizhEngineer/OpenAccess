package com.ss.oa.transaction.importbankingbalance;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.oa.common.OpenAccessException;
import com.ss.oa.model.transaction.ImportBankingBalance;
import com.ss.oa.transaction.vo.BankingBalance;
import com.ss.oashared.common.CommonUtils;

@Scope("prototype")
@RestController
@RequestMapping("/transaction/impBankingBalance")
public class ImportBankingBalanceService {
	
	private String vRemarks;
	
	@Resource
	private DataSource dataSource;
	
	private SimpleJdbcCall processImpBankingBal;


	
	@Autowired
	private ImportBankingBalanceRepository importBankingBalanceRepository;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@CrossOrigin(origins = "*")
	@GetMapping
	public Iterable<ImportBankingBalance>getImpBnkingbal() throws OpenAccessException {
		return importBankingBalanceRepository.findAll();
	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public Optional<ImportBankingBalance> getbbById(@PathVariable(value="id")String id)throws OpenAccessException{
		return importBankingBalanceRepository.findById(id);
		
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping
	public ImportBankingBalance addImpBb(@RequestBody ImportBankingBalance importBankingBalance) throws OpenAccessException {
		importBankingBalance.setId(commonUtils.generateId());
		return importBankingBalanceRepository.save(importBankingBalance);

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/processImpBankingBalance")
	public String getBanking(@RequestBody BankingBalance bankingBalance) throws OpenAccessException {
		
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 String remarks=bankingBalance.getSellerCompanyServiceNumber()+'-'+ts;
		 
		ImportBankingBalance importBankingBalance=new ImportBankingBalance();
		importBankingBalance.setId(commonUtils.generateId());
		importBankingBalance.setBankingServiceId(bankingBalance.getBankingServiceId());
		importBankingBalance.setmCompanyId(bankingBalance.getBankingCompanyId());
		importBankingBalance.setC1(bankingBalance.getC1());
		importBankingBalance.setC2(bankingBalance.getC2());
		importBankingBalance.setC3(bankingBalance.getC3());
		importBankingBalance.setC4(bankingBalance.getC4());
		importBankingBalance.setC5(bankingBalance.getC5());
		importBankingBalance.setGenServiceNumber(bankingBalance.getSellerCompanyServiceNumber());
		importBankingBalance.setMonth(bankingBalance.getMonth());
		importBankingBalance.setYear(bankingBalance.getYear());
		importBankingBalance.setRemarks(remarks);
		importBankingBalanceRepository.save(importBankingBalance);
		
		
		Map<String,String> criteria = new HashMap<String,String>();
		if(remarks!=null) criteria.put("vRemarks",remarks);
		
		String s =CallProcessImpBankingBalance(criteria);
		return s;

	}
		
	
	public String CallProcessImpBankingBalance(Map<String, String> criteria) {
		
		vRemarks = criteria.get("vRemarks");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		processImpBankingBal = new SimpleJdbcCall(jdbcTemplate).withFunctionName("PROCESS_IMP_BANKING_BALANCE");
		System.out.println(vRemarks);

		return ProcessImpBankingBalanceInputs(vRemarks);
	}
	
	public String ProcessImpBankingBalanceInputs(String vRemarks) {
		
		String overWrite="Y";

		SqlParameterSource in = new MapSqlParameterSource().addValue("I_REMARKS", vRemarks).addValue("I_OVERWRITE",overWrite);
		System.out.println(in);

		return processImpBankingBal.executeFunction(String.class, in);
	
	}
	
	
}
