package com.ss.oa.transaction.excessbalance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.oa.transaction.vo.ExcessBalance;


public interface ExcessBalanceRepository extends CrudRepository<ExcessBalance, String> {
	 
	List<ExcessBalance> findByExcessUnitTypeAndCompanyServiceIdAndReadingMonthAndReadingYear(String excessUnitType, String companyServiceId,String readingMonth,String readingYear);

}
