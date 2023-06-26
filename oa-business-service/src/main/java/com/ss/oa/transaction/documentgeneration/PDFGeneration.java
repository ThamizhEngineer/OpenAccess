package com.ss.oa.transaction.documentgeneration;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.context.annotation.Scope;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.ss.oa.common.DocumentGenerationBase;
import com.ss.oa.transaction.vo.DocumentGeneration;

@Scope("prototype")
public class PDFGeneration extends DocumentGenerationBase{
	
	DocumentGeneration docGen = null;
	public void writeDocument(DocumentGeneration params) {
		
		this.docGen = params;
		super.params = this.docGen.getParams();
		
		try{
		/*String txt = "";
		File dir = new File("/Users/padmavathythiruvenkadam/RFR/test/test.pdf");
		
		if (dir != null && dir.isFile()){
			dir.setReadable(true);
	
	
			PdfReader reader = new PdfReader(dir.getAbsolutePath());
			for(int nop = 1; nop <= reader.getNumberOfPages(); nop++){
				txt += PdfTextExtractor.getTextFromPage(reader, nop);
			}
			reader.close();
		}
		*/
			String docxFileName = this.docGen.getInputFile();
			//String docxFileName = "/Users/padmavathythiruvenkadam/RFR/testWord/ttt.docx";
			//String docxFileName = "/Users/padmavathythiruvenkadam/RFR/testWord/ttt.doc";
			String txt = "";
			String ext = super.getExtension(docxFileName);
			switch(ext.toUpperCase()){
			case "DOC":
				txt = super.readDocFile(docxFileName);
				writePDFFile(txt);
				break;
			case "DOCX":
				readDocxFile(docxFileName);
				break;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	private void writePDFFile(String txt) throws IOException, DocumentException{
		FileOutputStream fos = null;
		Document doc = null;
		try{
			
		if(txt != null && txt != ""){
			doc = new Document();
				//String fileNameInput = fileName + ".pdf";
			//File opDir = new File("/Users/padmavathythiruvenkadam/RFR/test/op2.pdf");
			File opDir = new File(this.docGen.getOutputFile());	
			fos = new FileOutputStream(opDir.getAbsolutePath());
				
			PdfWriter.getInstance(doc, fos);
			
			doc.open();
				/*Paragraph p = new Paragraph();
				p.setAlignment(Element.ALIGN_JUSTIFIED);
				p.add("Hello ");
				
				doc.add(p);
				
				p = new Paragraph();
				p.add("{param1}");
				doc.add(p);
				
				doc.add(new Paragraph("testing this too", new Font()) );
				*/
				System.out.println(txt);
				//System.out.println(param1);
				//txt = txt.replace("{param1}", param1);
				
				
				Paragraph p = new Paragraph(txt);
				doc.add(p);
				
			}
		}
		catch(DocumentException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if (doc != null)
				doc.close();
			if(fos != null){
				fos.close();
			}
		}
	}
	
	private String readDocxFile(String fileName) throws IOException {
		XWPFDocument doc = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Document pdfDoc = null;
		
		try{
		File file = new File(fileName);
		fis = new FileInputStream(file.getAbsolutePath());
		doc=new XWPFDocument(fis);
		
		String readText = "";
		
		List<XWPFParagraph> paragraphs = doc.getParagraphs();
		
		pdfDoc = new Document();
		//String fileNameInput = fileName + ".pdf";
		File opDir = new File(this.docGen.getOutputFile());
		
		fos = new FileOutputStream(opDir.getAbsolutePath());
		
		PdfWriter.getInstance(pdfDoc, fos);
	
		pdfDoc.open();
		Paragraph para = null;
		
		if(paragraphs != null){
			for(XWPFParagraph paragraph: paragraphs){
				//readText += super.replaceParams(paragraph.getText());
				
				List<XWPFRun> runs = paragraph.getRuns();
				if(runs != null){
					readText = "";
					for(XWPFRun run: runs){
						readText += run.getText(0);
						//run.setText(replaceParams(readText), 0);
					}
					para = new Paragraph(replaceParams(readText));
					pdfDoc.add(para);
				}
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
		finally
		{
			if(doc != null)
				doc.close();
			if(fis!=null)
				fis.close();
			if(pdfDoc != null)
				pdfDoc.close();
			if(fos != null)
				fos.close();
		}
	}

}