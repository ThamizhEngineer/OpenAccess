package com.ss.oa.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name ="V_CODES")
@Getter
public class DeleteTransactionReasonSummary {

	@Id
	@Column(name="ATTRIB1")
	private String attrib1 ;
	@Column(name="ATTRIB2")
	private String attrib2 ;
	@Column(name="ATTRIB3")
	private String attrib3 ;
	@Column(name="ENABLED")
	private String enabled ; 
	@Column(name="LIST_CODE")
	private String listCode ;
	@Column(name="LIST_NAME")
	private String listName ;
	@Column(name="VALUE_CODE")
	private String valueCode ;
	@Column(name="VALUE_DESC")
	private String valueDesc ;
	
	
	public DeleteTransactionReasonSummary() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "DeleteTransactionReasonSummary [attrib1=" + attrib1 + ", attrib2=" + attrib2 + ", attrib3=" + attrib3
				+ ", enabled=" + enabled + ", listCode=" + listCode + ", listName=" + listName + ", valueCode="
				+ valueCode + ", valueDesc=" + valueDesc + "]";
	}


	public DeleteTransactionReasonSummary(String attrib1, String attrib2, String attrib3, String enabled,
			String listCode, String listName, String valueCode, String valueDesc) {
		super();
		this.attrib1 = attrib1;
		this.attrib2 = attrib2;
		this.attrib3 = attrib3;
		this.enabled = enabled;
		this.listCode = listCode;
		this.listName = listName;
		this.valueCode = valueCode;
		this.valueDesc = valueDesc;
	}


	public DeleteTransactionReasonSummary(String listCode, String listName, String valueCode, String valueDesc) {
		super();
		this.listCode = listCode;
		this.listName = listName;
		this.valueCode = valueCode;
		this.valueDesc = valueDesc;
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


	public String getEnabled() {
		return enabled;
	}


	public void setEnabled(String enabled) {
		this.enabled = enabled;
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

	
}

