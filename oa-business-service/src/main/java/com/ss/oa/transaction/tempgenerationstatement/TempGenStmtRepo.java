package com.ss.oa.transaction.tempgenerationstatement;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface TempGenStmtRepo extends CrudRepository<TempGenStmt, String> {


	    List<TempGenStmt> findBydispServiceNumberAndStmtMonthAndStmtYear(String dispServiceNumber,String stmtMonth,String stmtYear);
}
