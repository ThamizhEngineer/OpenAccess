package com.ss.oa.transaction.solarLineLoss;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface MeterReadingRepository extends CrudRepository<MeterReadingHDR, String> {

	MeterReadingHDR findByMeterIdAndReadingMonthAndReadingYear(String meterId, String reading_Month,
			String reading_Year);
}
