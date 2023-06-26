package com.ss.oa.financialUnutilBanking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.oa.report.vo.FinancialUnutilBanking;

public interface FinancialUnutilBankingRepo extends CrudRepository<FinancialUnutilBanking, String>{
	
	List<FinancialUnutilBanking> findByStMonth(String month);
	List<FinancialUnutilBanking> findByStYear(String year);
	List<FinancialUnutilBanking> findByEdcNo(String edcNo);
	List<FinancialUnutilBanking> findByEdcNoAndStMonthAndStYear(String edcNo, String month,String year);

}
