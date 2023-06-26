package com.ss.oa.transaction.importbankingbalance;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

import com.ss.oa.model.transaction.ImportBankingBalance;

@Scope("prototype")
public interface ImportBankingBalanceRepository extends CrudRepository<ImportBankingBalance,String>{

}
