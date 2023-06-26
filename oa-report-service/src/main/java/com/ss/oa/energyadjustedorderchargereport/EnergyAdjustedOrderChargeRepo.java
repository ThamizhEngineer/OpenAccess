package com.ss.oa.energyadjustedorderchargereport;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oashared.model.EnergyAdjustedOrderCharge;

public interface EnergyAdjustedOrderChargeRepo extends CrudRepository<EnergyAdjustedOrderCharge, Integer>{
	
	@Query("SELECT new com.ss.oashared.model.EnergyAdjustedOrderCharge(esc.id,esc.suplrCode,esc.serviceNo,esc.readingMnth,esc.readingYr,esc.meterReadingCharges,esc.oMCharges,esc.systemOperationCharges,esc.rkvahPenalty,esc.negativeEnergyCharges,schedulingCharges,esc.transmissionCharges)"
			+ "FROM EnergyAdjustedOrderCharge esc WHERE NVL(LOWER(esc.readingMnth),'0') LIKE LOWER(CONCAT('%',NVL(:readingMnth,''),'%'))"
			+ "AND NVL(LOWER(esc.readingYr),'0') LIKE LOWER(CONCAT('%',NVL(:readingYr,''),'%')) "
			+ "AND NVL(LOWER(esc.serviceNo),'0') LIKE LOWER(CONCAT('%',NVL(:serviceNo,''),'%')) "
			+ "AND NVL(LOWER(esc.suplrCode),'0') LIKE LOWER(CONCAT('%',NVL(:suplrCode,''),'%'))")
	List<EnergyAdjustedOrderCharge> getEsc(@Param ("readingMnth")String readingMnth,@Param ("readingYr")String readingYr,@Param ("serviceNo")String serviceNo,
			@Param ("suplrCode")String suplrCode);

}
