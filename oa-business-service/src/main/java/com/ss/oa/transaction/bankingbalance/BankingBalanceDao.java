package com.ss.oa.transaction.bankingbalance;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.transaction.vo.BankingBalance;

@Scope("prototype")
public interface BankingBalanceDao {

	public abstract List<BankingBalance> getAllBankingBalances(Map<String, String> criteria);

	public abstract BankingBalance getBankingBalancesById(String id);

	public abstract String addBankingBalance(BankingBalance bankingBalance);

	public abstract String updateBankingBalance(String id, BankingBalance bankingBalance);

}
