package com.ss.oashared.model;
import java.util.HashMap;

import java.util.Map;


public class PrintPayload {
	private PrintTypes name;
	private Map<String,String> filterCriteria;
	private String result;
	private String resultDesc;
	private String docPath;
	private String fileName;
	private String fileNameToUser;
	private String fileExtension;
	private String docId;

public enum PrintTypes
{
	GenerationStatement, 
	WegWithBuyerReport, 
	EnergyAllotmentReport,
	TnercEnergySummaryReport,
	ConsolidateEnergyAdjustmentReport,
	ConsolidateEnergyAdjustedChargesReport,
	GeneratorWiseGenerationReport,
	UnutilisedBankingReport,
	GenChargesAllocToHtReport,
	UnutilisedBankingMarNew,
	UnutilisedBankingMar2020,
	ProgressReportNew,
	SaleToBoardLedgerReport,
	ConsumerWiseEnergyAdjustmentOrderReport,
	UnimportedWegReport,
	PowerplantGenerator,
	EnergyAllotmentOrderReport,
	EnergyAdjustedOrderReport,
	CeeReport,
	SrcpReport,
	NilGenerationReport,
	TnercNetGenerationReport,
	UnalloctedGenStmtReport,
	EnergyAdjustedOrderChargeReport,
	ProgressReport,
	FinancialUnutilBankReport,
	orgWiseGenerationReport,
	EnergyAuditReport,
	SldcNocPrint,
	AmrDownloadReport,
	TechnicalInfoReport,
	TranscoInvoiceReport,
	TranscoInvoiceChargesReport,
	MeterReadingServicesImportStatusReport,
	SolarFeederEDCWiseReport,
	ConsumerNocPrint,
	MonthyProgressReportForTotal, 
	MasterSolarReport,
	MasterWindReport,
	GenerationstmtReport,
	SolarFeederLineLoss,
	TnercBankingReport092022
	//PlantWiseGenerationReportForStb
}


public PrintPayload() {
	super();
	this.filterCriteria = new HashMap<String,String>();
}

public PrintTypes getName() {
	return name;
}

public void setName(PrintTypes name) {
	this.name = name;
}

public Map<String, String> getFilterCriteria() {
	return filterCriteria;
}

public void setFilterCriteria(Map<String, String> filterCriteria) {
	this.filterCriteria = filterCriteria;
}

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}

public String getResultDesc() {
	return resultDesc;
}

public void setResultDesc(String resultDesc) {
	this.resultDesc = resultDesc;
}

public String getDocPath() {
	return docPath;
}

public void setDocPath(String docPath) {
	this.docPath = docPath;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getFileNameToUser() {
	return fileNameToUser;
}

public void setFileNameToUser(String fileNameToUser) {
	this.fileNameToUser = fileNameToUser;
}

public String getFileExtension() {
	return fileExtension;
}

public void setFileExtension(String fileExtension) {
	this.fileExtension = fileExtension;
}

public String getDocId() {
	return docId;
}

public void setDocId(String docId) {
	this.docId = docId;
}

@Override
public String toString() {
	return "PrintPayload [name=" + name + ", filterCriteria=" + filterCriteria + ", result=" + result + ", resultDesc="
			+ resultDesc + ", docPath=" + docPath + ", fileName=" + fileName + ", fileNameToUser=" + fileNameToUser
			+ ", fileExtension=" + fileExtension + ", docId=" + docId + "]";
}

	
}
