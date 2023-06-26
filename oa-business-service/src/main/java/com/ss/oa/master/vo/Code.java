package com.ss.oa.master.vo;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Code {
	
	String listCode;
	String listName;
	String valueCode;
	String valueDesc;
	String enabled;
	String attrib1;
	String attrib2;
	String attrib3;
	
	public Code() {
		super();
	}
	
	public Code(String listCode,String listName,String valueCode,String valueDesc,String enabled,
			String attrib1,	String attrib2,String attrib3) {
		super();
		this.listCode=listCode;
		this.listName=listName;
		this.valueCode=valueCode;
		this.valueDesc=valueDesc;
		this.enabled=enabled;
		this.attrib1=attrib1;
		this.attrib2=attrib2;
		this.attrib3=attrib3;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Code [listcode="+ listCode+"listname="+listName+"valuecode="+valueCode+"valuedesc="
				+valueDesc+"enabled="+enabled+"attrib1="+attrib1+"attrib2="+attrib2+"attrib3"+attrib3+"]";
	}
	
	public String getListCode() {
		return listCode;
	}
	
	public void setListCode(String listCode) {
		this.listCode = listCode;
	}
	
	public String getListName() {
		return listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public String getValueCode() {
		return valueCode;
	}
	
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	
	public String getValueDesc() {
		return valueDesc;
	}
	
	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getAttrib1() {
		return attrib1;
	}
	
	public void setAttrib1(String attrib1) {
		this.attrib1 = attrib1;
	}
	
	public String getAttrib2() {
		return attrib2;
	}
	
	public void setAttrib2(String attrib2) {
		this.attrib2 = attrib2;
	}
	
	public String getAttrib3() {
		return attrib3;
	}
	
	public void setAttrib3(String attrib3) {
		this.attrib3 = attrib3;
	}
	

}
