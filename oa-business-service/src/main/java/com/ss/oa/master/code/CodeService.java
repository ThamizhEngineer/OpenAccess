package com.ss.oa.master.code;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.master.vo.Code;

@Component
@Scope("prototype")
public class CodeService {

	@Autowired
	CodeDao dao;
	
	public CodeService() {
		super();
	}
	
	public List<Code> getAllCodes(Map<String,String> criteria){
		return dao.getAllCodes(criteria);
	}
}
