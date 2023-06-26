package com.ss.oa.transaction.deletetransaction;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ss.oa.model.transaction.DeleteTransaction;
import com.ss.oa.transaction.vo.DeleteTransactionLog;
import com.ss.oashared.model.VCompanyService;

public interface DeleteTransactionRepository extends CrudRepository<DeleteTransaction,String>{
	
	@Query("SELECT new com.ss.oa.model.transaction.DeleteTransaction(intd.remarks,intd.genServiceNumber, intd.fuelGroupName,intd.orgId, intd.readingMonth, intd.readingYear, intd.deletedLedger, intd.deletedEs, intd.deletedGs, intd.deletedMr, intd.createdBy, intd.createdDt, intd.modifiedBy, intd.modifiedDt, intd.deleteRemarks, intd.processed)"
			+ "From DeleteTransaction intd where NVL(LOWER(intd.remarks),'0') LIKE LOWER(CONCAT('%',NVL(:remarks,''),'%')) AND NVL(LOWER(intd.genServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:genServiceNumber,''),'%')) AND NVL(LOWER(intd.readingMonth),'0') LIKE LOWER(CONCAT('%',NVL(:readingMonth,''),'%')) AND NVL(LOWER(intd.readingYear),'0') LIKE LOWER(CONCAT('%',NVL(:readingYear,''),'%'))")
          List<DeleteTransaction> getDeleteTransaction(@Param("remarks")String remarks,@Param("genServiceNumber")String genServiceNumber,@Param("readingMonth")String readingMonth,@Param("readingYear")String readingYear);	

	@Query("SELECT new com.ss.oa.model.transaction.DeleteTransaction(intd.remarks,intd.genServiceNumber, intd.fuelGroupName,intd.orgId,intd.readingMonth, intd.readingYear, intd.deletedLedger, intd.deletedEs, intd.deletedGs, intd.deletedMr, intd.createdBy, intd.createdDt, intd.modifiedBy, intd.modifiedDt, intd.deleteRemarks, intd.processed)"
			+ "From DeleteTransaction intd where NVL(LOWER(intd.orgId),'0') LIKE LOWER(CONCAT('%',NVL(:orgId,''),'%')) AND NVL(LOWER(intd.genServiceNumber),'0') LIKE LOWER(CONCAT('%',NVL(:genServiceNumber,''),'%')) AND NVL(LOWER(intd.readingMonth),'0') LIKE LOWER(CONCAT('%',NVL(:readingMonth,''),'%')) AND NVL(LOWER(intd.readingYear),'0') LIKE LOWER(CONCAT('%',NVL(:readingYear,''),'%'))")
          List<DeleteTransaction> getTransaction(@Param("orgId")String orgId, @Param("genServiceNumber")String genServiceNumber,@Param("readingMonth")String readingMonth,@Param("readingYear")String readingYear);	

	@Query("select new  com.ss.oashared.model.VCompanyService(vcs.orgId,vcs.fuelGroupName) from VCompanyService vcs where vcs.mCompServNumber = :serviceNumber")
    List<VCompanyService> findBySerNum(String serviceNumber);
	DeleteTransaction findByGenServiceNumberAndReadingMonthAndReadingYear(String genServiceNumber,String readingMonth,String readingYear);
	
	@Query("SELECT new com.ss.oa.transaction.vo.DeleteTransactionLog(intd.id,intd.serviceNo,intd.mOrgId, intd.month,intd.year,intd.remarks, intd.reading, intd.statement, intd.allotment, intd.ledger, intd.createdDate, intd.createdBy, intd.result)"
			+ "From DeleteTransactionLog intd where NVL(LOWER(intd.mOrgId),'0') LIKE LOWER(CONCAT('%',NVL(:mOrgId,''),'%')) AND NVL(LOWER(intd.serviceNo),'0') LIKE LOWER(CONCAT('%',NVL(:serviceNo,''),'%')) AND NVL(LOWER(intd.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(intd.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))")
          List<DeleteTransactionLog> getTransactionLog(@Param("mOrgId")String mOrgId, @Param("serviceNo")String serviceNo,@Param("month")String month,@Param("year")String year);
	
//	@Query("SELECT new com.ss.oa.transaction.vo.DeleteTransactionLog(intd.id,intd.serviceNo,intd.mOrgId, intd.month,intd.year,intd.remarks, intd.reading, intd.statement, intd.allotment, intd.ledger, intd.createdDate, intd.createdBy, intd.result)"
//			+ "From DeleteTransactionLog intd where  NVL(LOWER(intd.serviceNo),'0') LIKE LOWER(CONCAT('%',NVL(:serviceNo,''),'%')) AND NVL(LOWER(intd.month),'0') LIKE LOWER(CONCAT('%',NVL(:month,''),'%')) AND NVL(LOWER(intd.year),'0') LIKE LOWER(CONCAT('%',NVL(:year,''),'%'))")
//	DeleteTransactionLog getByIdLog(String serviceNo,String month,String year);
}
