package com.ss.oa.transaction.vo;

import java.util.List;

public class DocumentGeneration {
	private String templateType;
	private String templateName;
	private String inputFile;
	private String outputFile;
	private List<DocGenKeyValue> params;
	
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getInputFile() {
		return inputFile;
	}
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	public List<DocGenKeyValue> getParams() {
		return params;
	}
	public void setParams(List<DocGenKeyValue> params) {
		this.params = params;
	}
}
