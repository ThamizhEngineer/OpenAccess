package com.ss.oa.energyadjustedorderreport;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ss.oa.report.vo.EnergyAdjustedOrder;

public interface EnergyAdjustedOrderReportRepository extends CrudRepository<EnergyAdjustedOrder, String>{

	List<EnergyAdjustedOrder> findByReadingMonth(String readingMonth);
	List<EnergyAdjustedOrder> findByReadingYear(String readingYear);
	List<EnergyAdjustedOrder> findBySellerServiceNumber(String sellerServiceNumber);
	List<EnergyAdjustedOrder> findByReadingMonthAndReadingYearAndSellerServiceNumber(String readingMonth,String readingYear,String sellerServiceNumber);

}
