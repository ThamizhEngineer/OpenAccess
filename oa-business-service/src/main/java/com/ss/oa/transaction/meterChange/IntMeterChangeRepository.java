package com.ss.oa.transaction.MeterChange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntMeterChangeRepository extends JpaRepository<IntMeterChange,String> {

	public IntMeterChange findByserviceNo(String serviceNo);
}
