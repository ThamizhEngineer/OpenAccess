//package com.ss.oa.energyallocatedreport;
//
//import java.util.List;
//
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import com.ss.oa.report.vo.EnergyAllocatedReport;
//import com.ss.oa.report.vo.SrcpReport;
//
//public interface EnergyAllocatedReportRepository extends CrudRepository<EnergyAllocatedReport, Integer>{
//	
//	@Query("SELECT new com.ss.oa.report.vo.EnergyAllocatedReport(e.id,e.serviceNo,e.month,e.year,e.openingBalance,e.netGeneration,e.allotedGross,e.allottedNet,e.adjustedNet,e.htBanking,e.closingWithSurplus) FROM EnergyAllocatedReport e WHERE NVL(LOWER(e.serviceNo),'') LIKE LOWER(CONCAT('%',NVL(:serviceNo,''),'%'))" 
//			+ "AND NVL(LOWER(e.month),'0') LIKE LOWER(CONCAT('%',NVL(:month, ''),'%'))"
//			+ "AND NVL(LOWER(e.year),'0') LIKE LOWER(CONCAT('%',NVL(:year, ''),'%'))")
//	List<EnergyAllocatedReport>getEnergy(@Param("serviceNo")String serviceNo,@Param("month")String month,@Param("year")String year);
//	
//
//}
//
//
//
////@Query("SELECT new com.ss.oa.report.vo.SrcpReport(srcp.id,srcp.totalCapacitySum,srcp.totalGenerationSum,srcp.ncesDivisionCode,srcp.noOfWeg,srcp.month,srcp.year,srcp.installedBy)"
////		+ "FROM SrcpReport srcp WHERE NVL(LOWER(srcp.ncesDivisionCode),'0') LIKE LOWER(CONCAT('%',NVL(:ncesDivisionCode,''),'%')) AND NVL(LOWER(srcp.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) "
////		+ "AND NVL(LOWER(srcp.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%')) ")
////List<SrcpReport> getSrcp(@Param ("ncesDivisionCode")String ncesDivisionCode,
////		@Param("month")String month,@Param("year")String year);