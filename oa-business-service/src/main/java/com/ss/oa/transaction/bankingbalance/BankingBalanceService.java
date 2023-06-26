package com.ss.oa.transaction.bankingbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.transaction.excessbalance.ExcessBalanceRepository;
import com.ss.oa.transaction.vo.BankingBalance;
import com.ss.oa.transaction.vo.ExcessBalance;

@Scope("prototype")
@Component
public class BankingBalanceService {
	
	@Autowired
	BankingBalanceDao dao;
	
	@Autowired
	ExcessBalanceRepository excessBalanceRepository;
	
	String bankingBalanceId;

	public BankingBalanceService() {
		super();
	}
	
	private BankingBalance formBankingBal(ExcessBalance exs){
//		System.out.println(exs);
		BankingBalance bal = new BankingBalance();
		bal.setId(exs.getId());
		bal.setBankingCompanyId(exs.getCompanyId());
		bal.setBankingServiceId(exs.getBankingServiceId());
		bal.setBankingServiceNumber(exs.getBankingServiceNum());
		bal.setC1(exs.getCurrC1());
		bal.setC2(exs.getCurrC2());
		bal.setC3(exs.getCurrC3());
		bal.setC4(exs.getCurrC4());
		bal.setC5(exs.getCurrC5()); 
		bal.setCurrC1((exs.getDecrEa1C1()==null || exs.getDecrEa1C1().isEmpty())?"0":exs.getDecrEa1C1());
		bal.setCurrC2((exs.getDecrEa1C1()==null || exs.getDecrEa1C2().isEmpty())?"0":exs.getDecrEa1C2());
		bal.setCurrC3((exs.getDecrEa1C1()==null || exs.getDecrEa1C3().isEmpty())?"0":exs.getDecrEa1C3());
		bal.setCurrC4((exs.getDecrEa1C1()==null || exs.getDecrEa1C4().isEmpty())?"0":exs.getDecrEa1C4());
		bal.setCurrC5((exs.getDecrEa1C1()==null || exs.getDecrEa1C5().isEmpty())?"0":exs.getDecrEa1C5());
		return bal;
	}
	public List<BankingBalance> getAllBankingBalances(Map<String,String> criteria){
		List<BankingBalance> responseList = new ArrayList<BankingBalance>();
		for (ExcessBalance excessBalance : excessBalanceRepository.findByExcessUnitTypeAndCompanyServiceIdAndReadingMonthAndReadingYear("BANKING", criteria.get("seller-company-service-id"), criteria.get("month"), criteria.get("year"))) {
			responseList.add(formBankingBal(excessBalance));
		}

		System.out.println(responseList);
		return responseList;
	}
	
	public BankingBalance getBankingBalancesById(String id) {
		
		return dao.getBankingBalancesById(id);
	}
	public String addBankingBalance(BankingBalance bankingBalance) {
		bankingBalanceId = dao.addBankingBalance(bankingBalance);
		System.out.println("bankingBalanceid" + bankingBalanceId);
		return bankingBalanceId;

			
		}
	
	public String updateBankingBalance(String id,BankingBalance bankingBalance) {
		bankingBalanceId = dao.updateBankingBalance(id, bankingBalance);

		return bankingBalanceId;
	}
}
