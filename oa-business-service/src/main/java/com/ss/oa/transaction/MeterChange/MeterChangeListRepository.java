package com.ss.oa.transaction.MeterChange;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeterChangeListRepository extends JpaRepository<MeterChangeList,String> {
	
	
	@Query(value="SELECT * FROM METER_CHANGE_LIST WHERE MONTH = ?1 AND YEAR = ?2 ", nativeQuery=true)
	List<MeterChangeList>findByServicenoAndMonth(String Month,String Year);

}
