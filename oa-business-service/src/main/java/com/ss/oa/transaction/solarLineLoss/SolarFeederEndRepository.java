package com.ss.oa.transaction.solarLineLoss;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;

@Scope("prototype")
public interface SolarFeederEndRepository extends CrudRepository<SolarFeederEnd, String> {

	SolarFeederEnd findBySsFeederIdAndInitialReadingDateAndFinalReadingDateAndSectiontypeContaining(String feederId,
			LocalDateTime initialDate, LocalDateTime finalDate, String sectionType);
	
}
