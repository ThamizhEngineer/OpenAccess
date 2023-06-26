package com.ss.oa.master.code;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import com.ss.oa.master.vo.Code;
@Scope("prototype")
public interface CodeDao {
	
	public abstract List<Code> getAllCodes(Map<String,String> criteria);
	

}
