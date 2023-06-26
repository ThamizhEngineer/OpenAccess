package com.ss.oa.energyorderchargereport;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.report.vo.EnergyOrderCharge;


public interface EnergyOrderChargeReportRepo extends CrudRepository<EnergyOrderCharge, Integer>{

	@Query("SELECT new com.ss.oa.report.vo.EnergyOrderCharge(esc.id,esc.sellerServiceNumber,esc.buyerServiceNumber,esc.month,esc.year,esc.meterReadingCharges,esc.oMCharges,esc.systemOperationCharges,esc.rkvahPenalty,esc.negativeEnergyCharges,schedulingCharges,esc.transmissionCharges)"
			+ "FROM EnergyOrderCharge esc WHERE NVL(LOWER(esc.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%'))"
			+ "AND NVL(LOWER(esc.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) "
			+ "AND NVL(LOWER(esc.buyerServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:buyerServiceNumber,''),'%')) "
			+ "AND NVL(LOWER(esc.sellerServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:sellerServiceNumber,''),'%'))")
	List<EnergyOrderCharge> getByFilter(@Param ("month")String month,@Param ("year")String year,@Param ("buyerServiceNumber")String buyerServiceNumber,
			@Param ("sellerServiceNumber")String sellerServiceNumber);
}
