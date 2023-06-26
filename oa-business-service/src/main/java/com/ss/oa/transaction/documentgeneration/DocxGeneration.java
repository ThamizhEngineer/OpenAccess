package com.ss.oa.transaction.documentgeneration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ss.oa.common.DocumentGenerationBase;
import com.ss.oa.report.genericreportingservice.GenericReportingDao;
import com.ss.oa.report.vo.GenericReportHeaderOutput;
import com.ss.oa.report.vo.GenericReportOutput;
import com.ss.oa.transaction.vo.DocumentGeneration;

@Scope("prototype")
@Component
public class DocxGeneration extends DocumentGenerationBase {

	@Autowired
	GenericReportingDao genericReportingDao;
	
	DocumentGeneration docGen = null;
	public void writeDocument(DocumentGeneration params) throws IOException {
		this.docGen = params;
		super.params = this.docGen.getParams();
		try{
		String docxFileName = this.docGen.getInputFile();
			//String docxFileName = "/Users/padmavathythiruvenkadam/RFR/testWord/ttt.docx";
		//String docxFileName = "/Users/padmavathythiruvenkadam/RFR/testWord/ttt.doc";
		
		String ext = super.getExtension(docxFileName);
		switch(ext.toUpperCase()){
		case "DOC":
			writeDocxFile(super.readDocFile(docxFileName));
			break;
		case "DOCX":
			writeDocxFile(readDocxFile(docxFileName));
			break;
		}
		
		//String filefile = "";
		//readFile(filefile);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	private void readFile(String filefile) throws FileNotFoundException{
		try{
		File inFile = new File("/Users/padmavathythiruvenkadam/RFR/testWord/rrrr.rtf");
		Scanner scn = new Scanner(inFile);
		String line = "";
		
		while(scn.hasNextLine()){
			line += scn.nextLine();
			
		}
		line = replaceParams(line);
		System.out.println(line);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	private String readDocFile(String fileName) throws IOException {
		HWPFDocument doc = null;
		WordExtractor we = null;
		FileInputStream fis = null;
		try{
		File file = new File(fileName);
		fis = new FileInputStream(file);
		//Path path = Paths.get("/Users/padmavathythiruvenkadam/RFR/testWord/rrrr.rtf");
		//byte[] bytes = Files.readAllBytes(path);
		doc=new HWPFDocument(fis);
		we = new WordExtractor(doc);
		
		String readText = "";
		
		String[] paragraphs = we.getParagraphText();
		
		if(paragraphs != null){
			for(String paragraph: paragraphs){
				readText += super.replaceParams(paragraph);
			}
		}
		
		return readText;
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			if(we != null)
				we.close();
			if(doc != null)
				doc.close();
			if(fis !=null)
				fis.close();
		}
	}*/
	
	protected GenericReportHeaderOutput replaceTab(String readText){
		
		GenericReportHeaderOutput genReport = null;
		try{
				genReport = genericReportingDao.getGenericRepResult(readText, null);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return genReport;
	}
	
	private XWPFDocument readDocxFile(String fileName) throws IOException {
		XWPFDocument doc = null;
		FileInputStream fis = null;
		try{
			File file = new File(fileName);
			fis = new FileInputStream(file);
		//Path path = Paths.get("/Users/padmavathythiruvenkadam/RFR/testWord/rrrr.rtf");
		//byte[] bytes = Files.readAllBytes(path);
		doc=new XWPFDocument(fis);
		
		String readText = "";
		
		List<XWPFParagraph> paragraphs = doc.getParagraphs();
		
		if(paragraphs != null){
			for(XWPFParagraph paragraph: paragraphs){
				//readText += replaceParams(paragraph.getText());
				
				List<XWPFRun> runs = paragraph.getRuns();
				if(runs != null){
					for(XWPFRun run: runs){
						readText = run.getText(0);
						run.setText(super.replaceParams(readText), 0);
					}
				}
			}
			doc = buildTable(doc);
		}
		
		return doc;
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			if(doc != null)
				doc.close();
			if(fis != null)
				fis.close();
		}
	}

	private XWPFDocument buildTable(XWPFDocument doc){
		GenericReportHeaderOutput reportHeaderOutput = null;
		List<GenericReportOutput> reportOutputs = null;
		Map<Integer, String> headers = null;
		List<XWPFRun> runs = null;
		XWPFParagraph para = null;
		XWPFTable table = null;
		XWPFTableRow row = null;
		XmlCursor cursor = null;
		String reportOPTxt = "";
		String readText = "";
		String tableName = "";
		int tabIndex = -1;
		
		try{
		for(XWPFParagraph paragraph: doc.getParagraphs()){
			
			runs = paragraph.getRuns();
			if(runs != null){
				for(XWPFRun run: runs){
					readText = run.getText(0);
					
					if(readText != null && readText != "" && readText.contains("TAB-")){
						tabIndex = readText.indexOf("TAB-");
						tableName = readText.substring(tabIndex+4);
						System.out.println(tableName);
						run.setText(readText.replace("TAB-"+tableName, ""), 0);
						
						reportHeaderOutput = replaceTab(tableName);
						if(reportHeaderOutput != null){
						headers = reportHeaderOutput.getHeaders();
						if(headers != null && headers.size() > 0){
							int headerColIndex = 0;
							cursor = paragraph.getCTP().newCursor();
							table = doc.insertNewTbl(cursor);
							table.setWidth(75);
							table.setColBandSize(10);
							table.setRowBandSize(10);
							row = table.getRow(0);
						
							for(Map.Entry<Integer,String> header: headers.entrySet()){
								
								switch(header.getKey()){
								case 1:
									row.getCell(headerColIndex).removeParagraph(headerColIndex);
									para = row.getCell(headerColIndex).addParagraph();
									run = para.createRun();
									run.setBold(true);
									run.setText(header.getValue());
									break;
								case 2:
								case 3:
								case 4:
									row = setCellValue(row, header.getValue(), true, ++headerColIndex);
									break;
								}
							}
						}
						
						reportOutputs = reportHeaderOutput.getReportOutput();
		
						if(reportOutputs != null && reportOutputs.size() > 0){
							
							int colIndex = 0;
							for(GenericReportOutput	reportOutput:reportOutputs){
								System.out.println(table.getRow(0).getTableCells().size());
								System.out.println(table.getNumberOfRows());
								colIndex = 0;
								
								row = table.createRow();
								System.out.println("cells - " + row.getTableCells().size());
								
								reportOPTxt = reportOutput.getOp1();
								row.getCell(colIndex).setText((reportOPTxt != null || reportOPTxt != "") ? reportOPTxt : "");
				
								reportOPTxt = reportOutput.getOp2();
								row = setCellValue(row, reportOPTxt, false, ++colIndex);
				
								reportOPTxt = reportOutput.getOp3();
								row = setCellValue(row, reportOPTxt, false, ++colIndex);
				
								reportOPTxt = reportOutput.getOp4();
								row = setCellValue(row, reportOPTxt, false, ++colIndex);
				
							}
						}
					}
						tabIndex = -1;
						tableName = "";
					}
				}
			}
		}
		return doc;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	private XWPFTableRow setCellValue(XWPFTableRow row, String reportOPTxt, boolean isFirstRun, int colIndex) {
		XWPFParagraph para = null;
		XWPFRun run = null;
		
		if(isFirstRun){
			//row.createCell().setText((reportOPTxt != null || reportOPTxt != "") ? reportOPTxt : "");
			para = row.createCell().addParagraph();
			run = para.createRun();
			run.setBold(true);
			run.setText(reportOPTxt);
			row.getCell(colIndex).removeParagraph(0);
		}
		else
			row.getCell(colIndex).setText((reportOPTxt != null || reportOPTxt != "") ? reportOPTxt : "");
		
		return row;
	}
	
	private void writeDocxFile(XWPFDocument docAltered) throws IOException{
		FileOutputStream fos = null;
		try{
			File opDir = new File(this.docGen.getOutputFile());
			
			fos = new FileOutputStream(opDir.getAbsolutePath());
			if(docAltered != null){
				docAltered.write(fos);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(docAltered != null)
				docAltered.close();
			if(fos != null){
				fos.close();
			}
		}
	}
	
	private void writeDocxFile(String outputText) throws IOException{
		FileOutputStream fos = null;
		try{
		File opDir = new File(this.docGen.getOutputFile());
		
		fos = new FileOutputStream(opDir.getAbsolutePath());
		
		if(outputText!= null && outputText != ""){
			ByteArrayInputStream bytes = new ByteArrayInputStream(outputText.getBytes());
			XWPFDocument docx = new XWPFDocument(bytes);
			docx.write(fos);
			docx.close();
		}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(fos != null)
				fos.close();
		}
	}
	
	
	
}