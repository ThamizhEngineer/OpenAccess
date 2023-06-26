package com.ss.oashared.doc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ss.oashared.model.DocInfo;

public class DocInfoHelper {
	
	@Autowired
	DocInfoRepository docInfoRepository;
	
	public Optional<DocInfo> findByDocId(String id){
		return docInfoRepository.findById(id);
	}
	
	public List<DocInfo> getDelete(){
		return docInfoRepository.getEnabled();
	}

}
