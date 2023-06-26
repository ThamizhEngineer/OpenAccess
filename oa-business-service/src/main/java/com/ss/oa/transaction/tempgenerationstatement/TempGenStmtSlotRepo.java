package com.ss.oa.transaction.tempgenerationstatement;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TempGenStmtSlotRepo extends CrudRepository<TempGenStmtSlot, String>{
	
}
