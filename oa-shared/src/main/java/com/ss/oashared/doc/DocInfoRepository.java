package com.ss.oashared.doc;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.DocInfo;

public interface DocInfoRepository extends CrudRepository<DocInfo, String>{
	
	Optional<DocInfo> findById(String id);
	
	@Query("SELECT doc FROM DocInfo doc WHERE doc.isEnabled='Y' AND doc.createdDt <= current_date - 10/24/60")
	List<DocInfo> getEnabled();
	

}
